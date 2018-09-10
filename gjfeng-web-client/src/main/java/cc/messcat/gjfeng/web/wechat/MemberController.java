package cc.messcat.gjfeng.web.wechat;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import com.h5pay.api.H5PayUtil;
import com.wechat.WeixinUtil;
import com.wechat.popular.api.QrcodeAPI;
import com.wechat.popular.api.TokenAPI;
import com.wechat.popular.api.UserAPI;
import com.wechat.popular.bean.QrcodeTicket;
import com.wechat.popular.bean.Token;
import com.wechat.popular.bean.User;
import com.wechat.popular.bean.pay.PayJsRequest;

import cc.messcat.gjfeng.common.constant.CommonProperties;
import cc.messcat.gjfeng.common.constant.CommonStatus;
import cc.messcat.gjfeng.common.exception.LoggerPrint;
import cc.messcat.gjfeng.common.fastpay.payForOther.FastPayDemoTest;
import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.util.BeanUtilsEx;
import cc.messcat.gjfeng.common.util.DateHelper;
import cc.messcat.gjfeng.common.util.GenerateQrCodeUtil;
import cc.messcat.gjfeng.common.util.HttpXmlClient;
import cc.messcat.gjfeng.common.util.JsonUtil;
import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.util.RandUtil;
import cc.messcat.gjfeng.common.util.Sha256;
import cc.messcat.gjfeng.common.util.StringUtil;
import cc.messcat.gjfeng.common.vo.app.MemberInfoVo;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.common.web.BaseController;
import cc.messcat.gjfeng.config.WaixiConfig;
import cc.messcat.gjfeng.dubbo.core.GjfAddressDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfLoginDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfStoreInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfTradeDubbo;
import cc.messcat.gjfeng.entity.GjfAccessToken;
import cc.messcat.gjfeng.entity.GjfFhTreasureInfo;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.entity.GjfMemberTrade;
import cc.messcat.gjfeng.entity.GjfWeiXinPayInfo;
import cc.messcat.gjfeng.upload.UploadFileUtil;
import cc.messcat.gjfeng.util.SessionUtil;
import cc.messcat.gjfeng.web.app.v1.LoginControllerV1;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import prePay.PrePayUtil;

@Controller
@RequestMapping("wx/member/")
public class MemberController extends BaseController {

	@Value("${gjfeng.client.project.url}")
	private String projectName;

	@Value("${upload.member.head.path}")
	private String uploadFilePath;

	@Value("${upload.member.idcard.path}")
	private String imageFolderName;

	@Value("${dubbo.application.web.client}")
	private String projectNames;

	@Value("${upload.member.code.path}")
	private String imageFolderNames;

	@Autowired
	@Qualifier("request")
	private HttpServletRequest request;

	@Autowired
	@Qualifier("response")
	private HttpServletResponse response;

	@Autowired
	@Qualifier("memberInfoDubbo")
	private GjfMemberInfoDubbo memberInfoDubbo;

	@Autowired
	@Qualifier("loginDubbo")
	private GjfLoginDubbo loginDubbo;
	
	@Autowired
	@Qualifier("storeInfoDubbo")
	private GjfStoreInfoDubbo storeInfoDubbo;


	@Autowired
	@Qualifier("addressDubbo")
	private GjfAddressDubbo addressDubbo;

	@Autowired
	@Qualifier("tradeDubbo")
	private GjfTradeDubbo tradeDubbo;

	/**
	 * @描述 我的信息
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "my", method = RequestMethod.GET)
	public ModelAndView myInfo() {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			String account = SessionUtil.getAccountSession(request);
			resultVo = memberInfoDubbo.findMemberByMobile(account);
			GjfFhTreasureInfo fh = (GjfFhTreasureInfo) tradeDubbo.findFhTreasureInfo(account).getResult();
			if (!BeanUtil.isValid(fh)) {
				model.put("fhTreasureInfo", 0);
			} else {
				model.put("fhTreasureInfo", fh.getFhTreasureMoney());
			}
			model.put("resultVo", resultVo);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, MemberController.class);
			model.put("resultVo", resultVo);
		}
		return new ModelAndView("wx/o2o-shop/my-info", model);
	}

	
	/**
	 * @描述 我的信息
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "appmyInfo", method = RequestMethod.GET)
	public ModelAndView appmyInfo(String num) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			String account = SessionUtil.getAccountSession(request);
			resultVo = memberInfoDubbo.findMemberByMobile(account);
			model.put("resultVo", resultVo);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, MemberController.class);
			model.put("resultVo", resultVo);
		}
		return new ModelAndView("wx/o2o-shop/my-info", model);
	}
	/**
	 * @描述 跳转到编辑我的信息
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "toUpdate", method = RequestMethod.GET)
	public ModelAndView toUpdate() {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			String account = SessionUtil.getAccountSession(request);
			model.put("resultVo", memberInfoDubbo.findMemberByMobile(account));
		} catch (Exception e) {
			model.put("resultVo", LoggerPrint.getResult(e, MemberController.class));
		}
		return new ModelAndView("wx/o2o-shop/my-info-update", model);
	}

	/**
	 * @描述 编辑我的信息
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ModelAndView update(GjfMemberInfo gjfMemberInfo, MultipartFile headImage) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			String account = SessionUtil.getAccountSession(request);
			if (ObjValid.isNotValid(gjfMemberInfo)) {
				gjfMemberInfo = new GjfMemberInfo();
				gjfMemberInfo.setMobile(account);
			}
			if (!headImage.isEmpty()) {
				String fileName = UploadFileUtil.upload(headImage, request, uploadFilePath);
				gjfMemberInfo.setImgHeadUrl(projectName + uploadFilePath + "/" + fileName);
			}
			gjfMemberInfo.setMobile(account);
			resultVo = memberInfoDubbo.updateMemberByWxOrApp(gjfMemberInfo);
			//resultVo = memberInfoDubbo.findMemberByMobile(account);
			model.put("resultVo", resultVo);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, MemberController.class);
			model.put("resultVo", resultVo);
		}
		return new ModelAndView("wx/o2o-shop/my-info-waiting", model);
	}

	/**
	 * 图片上传
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "uploadImg", method = RequestMethod.POST)
	@ResponseBody
	public Object uploadImg(MultipartFile file) {
		String url = "";
		try {
			if (!file.isEmpty()) {
				String fileName = UploadFileUtil.upload(file, request, uploadFilePath);
				url = projectName + uploadFilePath + "/" + fileName;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	/**
	 * @描述 跳转到我的二维码
	 * @author Karhs
	 * @date 2017年1月12日
	 * @return
	 */
	@RequestMapping(value = "myQr", method = RequestMethod.GET)
	public ModelAndView myQr() {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			String account = SessionUtil.getAccountSession(request);
			MemberInfoVo memberInfoVo = (MemberInfoVo) memberInfoDubbo.findMemberByMobile(account).getResult();
			if (!BeanUtil.isValid(memberInfoVo.getImgQrUrl())) {
				// 获取token

				GjfAccessToken accessToken = (GjfAccessToken) memberInfoDubbo.getAccessToken("1").getResult();
				Token tokens = new Token();
				if (BeanUtil.isValid(accessToken)) {
					Date time = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

					if (sdf.parse(sdf.format(accessToken.getExpirationTime())).compareTo(sdf.parse(sdf.format(time))) == 1) {
						tokens.setAccess_token(accessToken.getToken());
					} else {
						tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID, CommonProperties.GJFENG_APPSECRET);
						// 如果请求失败重新获取token
						int i = 0;
						while (!ObjValid.isValid(tokens.getAccess_token()) && i < 5) { //
							tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID, CommonProperties.GJFENG_APPSECRET);
							if (!ObjValid.isValid(tokens.getAccess_token())) { // 若取不到snsToken、则不获取openid;
								Thread.sleep(500); // 睡眠0.5 再取
							}
							i++;
						}
						GjfAccessToken token = new GjfAccessToken(CommonProperties.GJFENG_APP_ID, CommonProperties.GJFENG_APPSECRET,
							"1");

						token.setToken(tokens.getAccess_token());
						memberInfoDubbo.addAccessToken(token);
					}
				} else {
					tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID, CommonProperties.GJFENG_APPSECRET);
					// 如果请求失败重新获取token
					int i = 0;
					while (!ObjValid.isValid(tokens.getAccess_token()) && i < 5) { //
						tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID, CommonProperties.GJFENG_APPSECRET);
						if (!ObjValid.isValid(tokens.getAccess_token())) { // 若取不到snsToken、则不获取openid;
							Thread.sleep(500); // 睡眠0.5 再取
						}
						i++;
					}
					GjfAccessToken token = new GjfAccessToken(CommonProperties.GJFENG_APP_ID, CommonProperties.GJFENG_APPSECRET,
						"1");

					token.setToken(tokens.getAccess_token());
					memberInfoDubbo.addAccessToken(token);
				}

				String access_token = tokens.getAccess_token();

				// 生成二维码
				QrcodeTicket qrcodeTicket = QrcodeAPI.qrcodeCreateFinalStr(access_token, memberInfoVo.getId().toString());
				if ("40001".equals(qrcodeTicket.getErrcode())) {
					tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID, CommonProperties.GJFENG_APPSECRET);
					GjfAccessToken token = new GjfAccessToken(CommonProperties.GJFENG_APP_ID, CommonProperties.GJFENG_APPSECRET,
						"1");
					token.setToken(tokens.getAccess_token());
					memberInfoDubbo.addAccessToken(token);
					qrcodeTicket = QrcodeAPI.qrcodeCreateFinalStr(access_token, memberInfoVo.getId().toString());
				}
				String url = "";
				String path = "";
				try {
					url = GenerateQrCodeUtil.generateQrcode(request, qrcodeTicket.getUrl(), projectNames, imageFolderNames);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (BeanUtil.isValid(url)) {
					path = projectName + imageFolderNames + "/" + url;
				}
				memberInfoVo.setImgQrUrl(path);
				GjfMemberInfo info = memberInfoDubbo.findMember(memberInfoVo.getMobile());
				info.setImgQrUrl(path);
				memberInfoDubbo.updateMemberCode(info);
			}
			model.put("resultVo", memberInfoDubbo.findMemberByMobile(account));
		} catch (Exception e) {
			model.put("resultVo", LoggerPrint.getResult(e, MemberController.class));
		}
		return new ModelAndView("wx/o2o-shop/my-ercode", model);
	}

	/**
	 * @描述 跳转到实名制
	 * @author Karhs
	 * @date 2017年1月12日
	 * @return
	 */
	@RequestMapping(value = "toRealName", method = RequestMethod.GET)
	public ModelAndView toRealName() {
		String account = SessionUtil.getAccountSession(request);
		GjfMemberInfo gjfMemberInfo = memberInfoDubbo.findMember(account);
		if (ObjValid.isNotValid(gjfMemberInfo) || gjfMemberInfo.getIsReadName().equals("1")) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("resultVo", new ResultVo(400, "您已经实名认证了", null));
			return new ModelAndView("wx/o2o-shop/apply-waiting", model);
		}
		Map<String, Object> map=new HashMap<>();
		resultVo=memberInfoDubbo.findSetBaseInfoByKey("IDCARTSET");
		map.put("resultVo", resultVo);
		return new ModelAndView("wx/o2o-shop/my-real-name",map);
	}

	/**
	 * @描述 实名制提交
	 * @author Karhs
	 * @date 2017年1月12日
	 * @param file1
	 * @param file2
	 * @return
	 */
	@RequestMapping(value = "realName", method = RequestMethod.POST)
	public ModelAndView realName(String idCardName, String idCardNo, String idCardImg1, String idCardImg2, String idCardImg3,
		String fileName) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			String account = SessionUtil.getAccountSession(request);
			String url = "";
			// 用户身份证正面
			if (!idCardImg1.isEmpty()) {
				String[] str = idCardImg1.split(",");
				String fils = UploadFileUtil.uploadBase63(fileName, str[1], request, imageFolderName);
				url = projectName + imageFolderName + "/" + fils;
			}

			// 用户身份证反面
			String idCardBehImg = "";
			if (!idCardImg2.isEmpty()) {
				String[] str = idCardImg2.split(",");
				String fils = UploadFileUtil.uploadBase63(fileName, str[1], request, imageFolderName);
				idCardBehImg = projectName + imageFolderName + "/" + fils;
			}

			// 手持身份证图片
			String idCardHandImg = "";
			if (!idCardImg3.isEmpty()) {
				String[] str = idCardImg3.split(",");
				String fils = UploadFileUtil.uploadBase63(fileName, str[1], request, imageFolderName);
				idCardHandImg = projectName + imageFolderName + "/" + fils;
			}
			model.put("resultVo",
				memberInfoDubbo.updateMemberIdCard(account, idCardName, idCardNo, url, idCardBehImg, idCardHandImg, fileName));
			/*
			 * GjfMemberInfo f=memberInfoDubbo.findMember(account);
			 * model.put("resultVo1", f);
			 */
		} catch (Exception e) {
			model.put("resultVo", LoggerPrint.getResult(e, MemberController.class));
		}
		return new ModelAndView("wx/o2o-shop/apply-waiting-raelName", model);
	}

	/**
	 * @描述 我的钱包
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "myWallet", method = RequestMethod.GET)
	public ModelAndView myWallet() {
		Map<String, Object> model = new HashMap<String, Object>();
		try {

			// 判断当前时间是否是0:00 - 8:00,只有不是在这个时间内才可以访问

			String account = SessionUtil.getAccountSession(request);
			model.put("resultVo", memberInfoDubbo.findMyWallet(account));
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, TradeContrller.class);
			model.put("resultVo", resultVo);
		}
		return new ModelAndView("wx/o2o-shop/my-wallet", model);
	}

	/**
	 * @描述 跳转到我的收藏
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "toMyCollect", method = RequestMethod.GET)
	public ModelAndView myCollect() {
		return new ModelAndView("wx/o2o-shop/my-collect");
	}

	/**
	 * @描述 查询我的收藏信息
	 * @author Karhs
	 * @date 2017年2月6日
	 * @param pageNo
	 * @param pageSize
	 * @param collectType
	 * @return
	 */
	@RequestMapping(value = "myCollect", method = RequestMethod.GET)
	public ModelAndView myCollect(Integer pageNo, Integer pageSize, @RequestParam("collectType") String collectType) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			String account = SessionUtil.getAccountSession(request);
			resultVo = memberInfoDubbo.findMemberByMobile(account);
			model.put("resultVo", memberInfoDubbo.findMyCollect(account, collectType, ObjValid.isNotValid(pageNo) ? 1 : pageNo,
				ObjValid.isNotValid(pageSize) ? 10 : pageSize));
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, MemberController.class);
			model.put("resultVo", resultVo);
		}
		String jspUrl = "";
		if (collectType.equals("1")) {
			jspUrl = "wx/o2o-shop/my-collect-store-ajax";
		} else if (collectType.equals("2")) {
			jspUrl = "wx/o2o-shop/my-collect-goods-ajax";
		}
		return new ModelAndView(jspUrl, model);
	}

	/**
	 * @描述 跳转到去设置支付密码
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "toSetPay", method = RequestMethod.GET)
	public ModelAndView toSetPay() {
		return new ModelAndView("wx/o2o-shop/set-pay-pwd");
	}

	/**
	 * @描述 设置支付密码
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "setPay", method = RequestMethod.POST)
	@ResponseBody
	public Object setPay(@RequestParam("pwd1") String pwd1, @RequestParam("pwd2") String pwd2,
		@RequestParam("mobile") String mobile) {
		try {
			String account = SessionUtil.getAccountSession(request);
			if (!account.equals(mobile)) {
				resultVo = new ResultVo(400, "手机号码不正确", null);
			}
			resultVo = loginDubbo.updatePayPwd(mobile, pwd1, pwd2);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, MemberController.class);
		}
		return resultVo;
	}

	/**
	 * 获取父级用户
	 * 
	 * @param memId
	 * @return
	 */
	@RequestMapping(value = "findSuperMember", method = RequestMethod.GET)
	@ResponseBody
	public Object findSuperMember(@RequestParam("superId") Long superId) {
		try {
			resultVo = memberInfoDubbo.findMemberById(superId);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, MemberController.class);
		}
		return resultVo;
	}

	/**
	 * 根据手机号码获取用户信息
	 * 
	 * @param memId
	 * @return
	 */
	@RequestMapping(value = "findMemberByMoblie", method = RequestMethod.GET)
	@ResponseBody
	public Object findMemberByMoblie(String mobile) {
		try {
			String account = SessionUtil.getAccountSession(request);
			if (mobile.equals(account)) {
				return new ResultVo(400, "消费会员不能是自己", null);
			}
			resultVo = memberInfoDubbo.findMemberByMobile(mobile);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, MemberController.class);
		}
		return resultVo;
	}

	/**
	 * @描述 跳转到绑定手机页面
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "toBindMobile", method = RequestMethod.GET)
	public ModelAndView toBindMobile() {
		return new ModelAndView("wx/o2o-shop/my-bind-mobile");
	}
	

	/**
	 * @描述app 绑定手机
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "bindMobileWeiXin", method = RequestMethod.GET)
	@ResponseBody
	public Object bindMobileWeiXin(@RequestParam("mobile") String mobile,String password) {
		Object unionId = request.getSession().getAttribute("unionId");
		
		if (ObjValid.isNotValid(unionId)) {
			return new ResultVo(400, "用户不存在，绑定失败", null);
		}		
		resultVo = loginDubbo.bindMobie(String.valueOf(unionId), mobile,password);
		if (resultVo.getCode() == 200) {
			request.getSession().setAttribute("account", mobile);			
		}
		return resultVo;
	}
	

	/**
	 * @描述app 绑定手机
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "bindMobile", method = RequestMethod.GET)
	@ResponseBody
	public Object bindMobile(@RequestParam("mobile") String mobile,String password) {
		Object unionId = request.getSession().getAttribute("unionId");
		//Object unionId="oYZdfv6-R9U4cpi0k41JORzoxj40";
		
		if (ObjValid.isNotValid(unionId)) {
			return new ResultVo(400, "用户不存在，绑定失败", null);
		}
		
		resultVo = loginDubbo.bindMobie(String.valueOf(unionId), mobile, password);
		if (resultVo.getCode() == 200) {
			request.getSession().setAttribute("account", mobile);
			//app
		 /* resultVo = memberInfoDubbo.findMemberByMobile(mobile);
		    GjfMemberInfo member= memberInfoDubbo.findMember(mobile);			
			if (ObjValid.isValid(member)) {				
				if("1".equals(member.getIsDel())){
					request.getSession().setAttribute("gjfMemberInfo", member);
					if (StringUtil.isNotBlank(member.getMobile())) {						
						request.getSession().setAttribute("account", member.getMobile());
						if (member.getType().equals("1")) {
							Object o=storeInfoDubbo.findStoreByAccount(member.getMobile()).getResult();
							if (ObjValid.isValid(o)) {
								request.getSession().setAttribute("gjfStoreInfo",o);
							}
						}
					}					
					request.getSession().setAttribute("unionId", member.getUnionId());
				}else{
					request.getSession().removeAttribute("account");
					request.getSession().removeAttribute("unionId");
				}
				resultVo.setResult(member);
				resultVo.setMsg("绑定成功");
			}else{
				//return new ResultVo(400, "用户不存在，绑定失败", null);
				MemberInfoVo memVo=(MemberInfoVo) loginDubbo.register(mobile,"123456", "", "0", "", "0",0L, "").getResult();
				request.getSession().setAttribute("account", memVo.getMobile());
				resultVo.setResult(memVo);
				resultVo.setMsg("绑定成功");
			}*/
		}
		return resultVo;
	}

	/**
	 * @描述 跳转到授信额度页面
	 * @author Karhs
	 * @date 2017年1月12日
	 * @return
	 */
	@RequestMapping(value = "shouXin", method = RequestMethod.GET)
	public ModelAndView shouXin() {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			String account = SessionUtil.getAccountSession(request);
			GjfMemberInfo gjfMemberInfo = memberInfoDubbo.findMember(account);
			if (ObjValid.isNotValid(gjfMemberInfo) || gjfMemberInfo.getType().equals("0")) {
				model.put("resultVo", new ResultVo(400, "您没有权限访问该页面", null));
				model.put("resultStatus", "1");
				return new ModelAndView("wx/o2o-shop/apply-waiting", model);
			}
			model.put("resultVo", new ResultVo(200, "查询成功",
				ObjValid.isNotValid(gjfMemberInfo) ? null : BeanUtilsEx.copyBean(MemberInfoVo.class, gjfMemberInfo)));
		} catch (Exception e) {
			model.put("resultVo", LoggerPrint.getResult(e, MemberController.class));
		}
		return new ModelAndView("wx/o2o-shop/shouxin", model);
	}

	/**
	 * @描述 跳转到购买授信额度页面
	 * @date 2017年1月12日
	 * @return
	 */
	@RequestMapping(value = "goBuyShouXin", method = RequestMethod.GET)
	public ModelAndView goBuyShouXin(String shouXinMenoy) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			String account = SessionUtil.getAccountSession(request);
			GjfMemberInfo gjfMemberInfo = memberInfoDubbo.findMember(account);
			if (ObjValid.isNotValid(gjfMemberInfo) || gjfMemberInfo.getType().equals("0")) {
				model.put("resultVo", new ResultVo(400, "您没有权限访问该页面", null));
				model.put("resultStatus", "1");
				return new ModelAndView("wx/o2o-shop/apply-waiting", model);
			}
			model.put("shouXinMenoy", shouXinMenoy);
		} catch (Exception e) {
			model.put("resultVo", LoggerPrint.getResult(e, MemberController.class));
		}
		return new ModelAndView("wx/o2o-shop/shouxin-buy", model);
	}
			

	/**
	 * 充值授信额度
	 * 
	 * @return
	 */
	@RequestMapping(value = "addShouXin", method = RequestMethod.POST)
	@ResponseBody
	public Object addShouXin(String type, Double tradeMoney,String shouType,String fileImage) {
		try {
			String account = SessionUtil.getAccountSession(request);
			if (!BeanUtil.isValid(type) && !BeanUtil.isValid(tradeMoney)) {
				return new ResultVo(400, "参数为空", null);
			}
			resultVo = tradeDubbo.addShouXin(type, account, tradeMoney,shouType,fileImage);
			if ("1".equals(type) && resultVo.getCode() == 200) {
				GjfMemberTrade trade = (GjfMemberTrade) resultVo.getResult();
				// 获取微信配置信息
				GjfWeiXinPayInfo payInfo = (GjfWeiXinPayInfo) tradeDubbo.findWeiXinBaseInfo("0").getResult();
				//聚合付
				/*String token_id = H5PayUtil.weiXinPay(payInfo, request, trade.getTradeNo(), trade.getTradeMoney().toString(),
					"充值授信额度", CommonProperties.GJFENG_WEIXIN_NOTIFY_SHOUXIN, CommonProperties.GJFENG_WEIXIN_CALLBACK_URL_SHOUXIN,
					trade.getMemberId().getOpenId());
				System.out.println("-----返回成功" + token_id);
				resultVo.setResult(token_id);*/
			   //利楚
				/*PosPrepayRe posPrepayRe=PrePayUtil.posPrePayRe(payInfo,request,trade.getTradeNo(),trade.getTradeMoney().toString(),"充值授信额度",trade.getMemberId().getOpenId(),"http://xj.gjfeng.net/gjfeng-web-client/wx/notify/payTradeNotifyLiChu");
				resultVo.setResult(posPrepayRe);*/
				//微信原生
				PayJsRequest json=WeixinUtil.unifiedorderResult(request,trade.getTradeNo(),"充值授信额度","JSAPI",trade.getTradeMoney().toString(),CommonProperties.GJFENG_WEIXIN_JSPAY_NOTIFY_URL_SHOUXIN,trade.getMemberId().getOpenId());
				System.out.println(json);
				resultVo.setResult(json);
			}
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, MemberController.class);
		}
		return resultVo;
	}

	/**
	 * 充值授信额度
	 * 
	 * @return
	 */
	PrimeUnionThread primeUnionThread = null;

	@RequestMapping(value = "addShouXinByH5", method = RequestMethod.GET)
	public ModelAndView addShouXinByH5(String type, Double tradeMoney,String shouType,String fileImage) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			String account = SessionUtil.getAccountSession(request);
			resultVo = tradeDubbo.addShouXin(type, account, tradeMoney,shouType,fileImage);

			if ("3".equals(type) && resultVo.getCode() == 200) {
				GjfMemberTrade trade = (GjfMemberTrade) resultVo.getResult();
				//网银支付方式1
				/*
				Map<String, String> obj = H5PayUtil.H5PayResult(request, response, trade.getMemberId(),
					trade.getTradeMoney().toString(), trade.getTradeNo(), "充值授信额度", "充值授信额度描述",
					CommonProperties.GJFENG_H5_NOTIFY_SHOUXIN);
				model.put("obj", obj);
				// 定义线程
				primeUnionThread = new PrimeUnionThread(trade.getTradeNo());
				primeUnionThread.start();*/
				
				//网银支付方式2 快捷支付
				//String dypass = FastPayDemoTest.getDyPass(trade.getTradeNo().toString());
				//FastPayDemoTest.fastApply(trade.getTradeNo(),dypass,trade.getTradeMoney().toString(),trade.getMemberId().getMobile(),String accNo,String name,String idCardNo,String retUrl);
				model.put("orderId", trade.getTradeNo().toString());
				model.put("payMoney", trade.getTradeMoney().doubleValue());
				model.put("isReadName", trade.getMemberId().getIsReadName());
				model.put("idCard", trade.getMemberId().getIdCard());
				model.put("mobile", trade.getMemberId().getMobile());
				model.put("retUrl", CommonProperties.GJFENG_WANGYIN_PAY_NOTIFY_URL_SHOUXIN);
				// 查询用户最新的资金情况和所有的银行卡列表
				resultVo = tradeDubbo.toDrawCash(account);
				model.put("resultVo", resultVo);
			}
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, MemberController.class);
		}
		//return new ModelAndView("wx/online-shop/H5cashier", model);
		return new ModelAndView("wx/o2o-shop/H5payConfirm", model);
	}

	// 线程
	class PrimeUnionThread extends Thread {
		private String out_trade_on;

		PrimeUnionThread(String out_trade_on) {
			this.out_trade_on = out_trade_on;
		}

		public void run() {
			try {
				for (int i = 0; i < 3; i++) {
					if (i == 0) {
						Thread.sleep(180000);
					}
					if (i == 1) {
						Thread.sleep(480000);
					}
					System.out.println("线程开启");
					String xml = H5PayUtil.queryH5Order(request, out_trade_on);
					System.out.println(xml);
					String[] str = xml.split("&");
					Map<String, String> map = new HashMap<String, String>();
					for (int j = 0; j < str.length; j++) {
						String str0 = str[j];
						String[] str1 = str0.split("=");
						if (str1.length == 1) {
							map.put(str1[0], "");
						} else {
							for (int k = 0; k < str1.length; k++) {
								map.put(str1[0], str1[1]);
							}
						}
					}
					System.out.println(map);
					/*"000000".equals(map.get("retCode"))&&*/
					if ("0".equals(map.get("status"))) {
						String out_trade_no1 = map.get("merchOrderId");
						String trade_no = map.get("orderId");
						resultVo = tradeDubbo.updateShouXinPayStatus(out_trade_no1, trade_no, "1");
						if (resultVo.getCode() == 200) {
							System.out.println("----线程结束-----");
							primeUnionThread.interrupt();
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	/**
	 * @描述 跳转到购买授信额度页面
	 * @date 2017年1月12日
	 * @return
	 */
	@RequestMapping(value = "goOfflineBuyShouXin", method = RequestMethod.GET)
	public ModelAndView goOfflineBuyShouXin(String shouXinMenoy) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			String account = SessionUtil.getAccountSession(request);
			GjfMemberInfo gjfMemberInfo = memberInfoDubbo.findMember(account);
			if (ObjValid.isNotValid(gjfMemberInfo) || gjfMemberInfo.getType().equals("0")) {
				model.put("resultVo", new ResultVo(400, "您没有权限访问该页面", null));
				model.put("resultStatus", "1");
				return new ModelAndView("wx/o2o-shop/apply-waiting", model);
			}
			model.put("shouXinMenoy", shouXinMenoy);
		} catch (Exception e) {
			model.put("resultVo", LoggerPrint.getResult(e, MemberController.class));
		}
		return new ModelAndView("wx/o2o-shop/offline-shouxin-buy", model);
	}
			
	

	/**
	 * @描述 跳转到赠送额度页面
	 * @date 2017年1月12日
	 * @return
	 */
	@RequestMapping(value = "goGiveShouXin", method = RequestMethod.GET)
	public ModelAndView goGiveShouXin(String shouXinMenoy) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			model.put("shouXinMenoy", shouXinMenoy);
		} catch (Exception e) {
			model.put("resultVo", LoggerPrint.getResult(e, MemberController.class));
		}
		return new ModelAndView("wx/o2o-shop/shouxin-give", model);
	}

	/**
	 * 获取授信额度记录
	 * 
	 * @return
	 */
	@RequestMapping(value = "goAllShouXin", method = RequestMethod.GET)
	public ModelAndView goAllShouXin(Integer pageNo, Integer pageSize) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			String account = SessionUtil.getAccountSession(request);
			resultVo = tradeDubbo.getAllShouXin(pageNo, pageSize, account);
			model.put("resultVo", resultVo);
		} catch (Exception e) {
			LoggerPrint.getResult(e, MemberController.class);
		}
		return new ModelAndView("wx/o2o-shop/shouxin-history", model);
	}

	/**
	 * 获取授信额度记录
	 * 
	 * @return
	 */
	@RequestMapping(value = "getAllShouXin", method = RequestMethod.GET)
	@ResponseBody
	public Object getAllShouXin(Integer pageNo, Integer pageSize) {
		try {
			String account = SessionUtil.getAccountSession(request);
			resultVo = tradeDubbo.getAllShouXin(pageNo, pageSize, account);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, MemberController.class);
		}
		return resultVo;
	}

	/**
	 * 我的钱包首页数据统计
	 * 
	 * @return
	 */
	@RequestMapping(value = "countMemberInfo", method = RequestMethod.GET)
	@ResponseBody
	public Object countMemberInfo(String type) {
		try {
			String account = SessionUtil.getAccountSession(request);
			resultVo = memberInfoDubbo.findMemberCountInfo(type, account);
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> map = new HashMap<>();
			map.put("benefitYesterdayMoney", 0.00); // 今日收益
			map.put("cumulativeMoney", 0.00);
			map.put("balanceMoney", 0.00);
			map.put("dividendsTotalMoney", 0.00);
			map.put("consumptionMoney", 0.00);
			map.put("canMoney", 0.00);
			map.put("canParticipate", 0.00);
			map.put("saleTotalMoney", 0.00);
			resultVo = new ResultVo(400, "网络异常,请重试", map);
		}
		return resultVo;
	}

	/**
	 * 添加旧数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "addMemberData", method = RequestMethod.GET)
	@ResponseBody
	public Object addMemberData(String url, String type, String status) {
		try {
			resultVo = memberInfoDubbo.addMemberData(url, type, status);
		} catch (Exception e) {
			LoggerPrint.getResult(e, MemberController.class);
		}
		return resultVo;
	}

	/**
	 * 添加旧数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "addMemberInfoGetUniId", method = RequestMethod.GET)
	@ResponseBody
	public Object addMemberInfoGetUniId(String url) {
		try {
			String str = HttpXmlClient.get(url);
			JSONObject json = JSONObject.fromObject(str);
			if (Integer.parseInt(json.getString("status")) == 200) {
				JSONArray list = json.getJSONArray("result");
				Token tokens = null;
				if (BeanUtil.isValid(list)) {
					for (int i = 0; i < list.size(); i++) {
						JSONObject map = list.getJSONObject(i);
						GjfMemberInfo info = new GjfMemberInfo();
						info.setId(map.getLong("id"));
						// info.setUnionId(map.getString("openid"));
						info.setOpenId(map.getString("openid"));
						info.setMobile(map.getString("mobile"));
						info.setNickName(map.getString("nickName"));

						String sex = map.getString("sex");
						if (Integer.parseInt(sex) == -1) {
							info.setSex("0");
						}
						if (Integer.parseInt(sex) == 0) {
							info.setSex("1");
						}
						if (Integer.parseInt(sex) == 1) {
							info.setSex("2");
						}
						info.setImgHeadUrl(map.getString("imgHeadUrl"));
						info.setSuperId(map.getLong("superId"));
						info.setBalanceMoney(new BigDecimal(map.getDouble("balanceMoney")));
						info.setConsumptionMoney(new BigDecimal(map.getDouble("consumptionMoney")));
						info.setCumulativeMoney(new BigDecimal(map.getDouble("cumulativeMoney")));
						info.setDividendsMoneyBla(new BigDecimal("0.00"));
						info.setWithdrawalMoney(new BigDecimal(map.getDouble("withdrawalMoney")));
						info.setDividendsTotalMoney(new BigDecimal(map.getDouble("dividendsTotalMoney")));
						info.setDividendsNum(new BigDecimal(map.getDouble("dividendsNum")));

						info.setInsuranceMoney(new BigDecimal(0.00));
						info.setDirectMemberTotalMoney(new BigDecimal(0.00));
						info.setDirectMerchantsTotalMoney(new BigDecimal(0.00));
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						try {
							if (BeanUtil.isValid(map.getLong("addTime"))) {
								Long lt = map.getLong("addTime");
								String time = sdf.format(lt);
								info.setAddTime(sdf.parse(time));
							} else {
								info.setAddTime(new Date());
							}

							if (BeanUtil.isValid(map.getLong("editTime"))) {
								Long lt = map.getLong("editTime");
								String time = sdf.format(lt);
								info.setEditTime(sdf.parse(time));
							}

							if (BeanUtil.isValid(map.getLong("lastLoginTime"))) {
								Long lt = map.getLong("lastLoginTime");
								String time = sdf.format(lt);
								info.setLastLoginTime(sdf.parse(time));
							}

						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						info.setType(map.getString("type"));
						info.setIdentity("0");
						info.setIsBuy("1");
						info.setIsComments("1");
						info.setIsDel("1");
						info.setIsMessage("1");
						info.setIsReport("1");
						info.setIsReadName("0");
						info.setStatus("1");
						info.setToken(Sha256.getSha256Hash(info.getMobile(), info.getStatus(), CommonStatus.SIGN_KEY_NUM));
						info.setRealNameState("0");

						if (tokens == null || tokens.getErrcode() == "40001") {
							tokens = TokenAPI.token(WaixiConfig.GJFENG_APP_ID(), WaixiConfig.GJFENG_APPSECRET());
						}
						// new
						// PrimeUnionThread(info,map.getString("openid"),tokens.getAccess_token()).start();
						User user = UserAPI.userInfo(tokens.getAccess_token(), map.getString("openid"));
						if (!"40013".equals(user.getErrcode()) || !"40003".equals(user.getErrcode())
							|| !"".equals(user.getErrcode())) {// openid有问题
							info.setUnionId(user.getUnionid());
						}
						memberInfoDubbo.addMemberDataGetUnId(info);
					}
				}
			}
		} catch (Exception e) {
			LoggerPrint.getResult(e, MemberController.class);
		}
		return resultVo;
	}

	/**
	 * @描述 没有用户信息
	 * @author Karhs
	 * @date 2017年2月28日
	 * @return
	 */
	@RequestMapping(value = "noMember", method = RequestMethod.GET)
	public ModelAndView noMember() {
		return new ModelAndView("wx/o2o-shop/wx-no-member");
	}

	/**
	 * @描述 获取用户下级
	 * @return
	 */
	@RequestMapping(value = "getMemberLowerLevel", method = RequestMethod.GET)
	public ModelAndView getMemberLowerLevel(Long superId, Integer pageNo, Integer pageSize) {
		Map<String, Object> model = new HashMap<>();
		try {
			resultVo = memberInfoDubbo.getMemberLowerLevel(superId, pageNo, pageSize);
			model.put("resultVo", resultVo);
			model.put("superId", superId);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, MemberController.class);
			model.put("resultVo", resultVo);
		}
		return new ModelAndView("wx/o2o-shop/my-recommend", model);
	}

	/**
	 * @描述 异步获取用户下级
	 * @return
	 */
	@RequestMapping(value = "getMemberLowerLevels", method = RequestMethod.GET)
	@ResponseBody
	public Object getMemberLowerLevels(Long superId, Integer pageNo, Integer pageSize) {

		try {
			resultVo = memberInfoDubbo.getMemberLowerLevel(superId, pageNo, pageSize);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, MemberController.class);

		}
		return resultVo;
	}

	/**
	 * 实名认证（银行）
	 * 
	 * @param idCardNo
	 * @param idCardImg1
	 * @param idCardName
	 * @return
	 */
	@RequestMapping(value = "realNameByBankCrad", method = RequestMethod.POST)
	public ModelAndView realNameByBankCrad(String idCardNo, String idCardImg1, String idCardName) {
		Map<String, Object> model = new HashMap<>();
		Map<String, String> map = new HashMap<>();
		try {
			String account = SessionUtil.getAccountSession(request);
			String xml = H5PayUtil.realName(idCardImg1.trim(), idCardName.trim(), idCardNo.toUpperCase().trim());
			System.out.println(xml);
			String[] str = xml.split("&");

			for (int j = 0; j < str.length; j++) {
				String str0 = str[j];
				String[] str1 = str0.split("=");
				if (str1.length == 1) {
					map.put(str1[0], "");
				} else {
					for (int k = 0; k < str1.length; k++) {
						map.put(str1[0], str1[1]);
					}
				}
			}
			System.out.println(map);
			if ("000000".equals(map.get("retCode")) && Integer.parseInt(map.get("status")) == 0) {
				resultVo = memberInfoDubbo.updateMemberIdCard(account, idCardName, idCardNo, "1", "2", "", "");
				resultVo.setCode(200);
				resultVo.setMsg("审核成功");
				model.put("resultVo", resultVo);
			} else if ("000000".equals(map.get("retCode")) && Integer.parseInt(map.get("status")) == 1) {
				resultVo = memberInfoDubbo.updateMemberIdCard(account, idCardName, idCardNo, "0", "3", "", "");
				resultVo.setCode(400);
				resultVo.setMsg(map.get("retMsg"));
				model.put("resultVo", resultVo);
			}else if("200002".equals(map.get("retCode"))){
				resultVo = memberInfoDubbo.updateMemberIdCard(account, idCardName, idCardNo, "0", "3", "", "");
				resultVo.setCode(400);
				resultVo.setMsg(map.get("retMsg"));
				model.put("resultVo", resultVo);
			}else{
				resultVo = memberInfoDubbo.updateMemberIdCard(account, idCardName, idCardNo, "0", "3", "", "");
				resultVo.setCode(400);
				resultVo.setMsg("网络异常，请稍后重试");
				model.put("resultVo", resultVo);
			}

		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, MemberController.class);
		}
		return new ModelAndView("wx/o2o-shop/apply-waiting-raelName", model);
	}
	
	/**
	 * 实名认证（yly）
	 * 
	 * @param idCardNo
	 * @param idCardImg1
	 * @param idCardName
	 * @return
	 */
	@RequestMapping(value = "realNameByAly", method = RequestMethod.POST)
	public ModelAndView realNameByAly(String idCardNo, String idCardName) {
		Map<String, Object> model = new HashMap<>();
		try {
			String account = SessionUtil.getAccountSession(request);
			String xml = H5PayUtil.olyrealName(idCardName.trim(),idCardNo.trim());
			Map<String, Object> map=JsonUtil.parseJSON2Map(xml);
			System.out.println(map);
			System.out.println(map.get("isok"));
			System.out.println(map.get("code"));
			if("1".equals(map.get("isok").toString())&&"1".equals(map.get("code").toString())){
				resultVo = memberInfoDubbo.updateMemberIdCard(account, idCardName, idCardNo, "1", "2", "", "");
				resultVo.setCode(200);
				resultVo.setMsg("审核成功");
				model.put("resultVo", resultVo);
			}
			if("1".equals(map.get("isok").toString())&&"2".equals(map.get("code").toString())){
				resultVo = memberInfoDubbo.updateMemberIdCard(account, idCardName, idCardNo, "0", "3", "", "");
				resultVo=new ResultVo();
				resultVo.setCode(400);
				resultVo.setMsg("信息不一致");
				model.put("resultVo", resultVo);
			}
			if("1".equals(map.get("isok").toString())&&"3".equals(map.get("code").toString())){
				resultVo = memberInfoDubbo.updateMemberIdCard(account, idCardName, idCardNo, "0", "3", "", "");
				resultVo=new ResultVo();
				resultVo.setCode(400);
				resultVo.setMsg("无此身份证号码");
				model.put("resultVo", resultVo);
			}
			if("0".equals(map.get("isok").toString())&&"11".equals(map.get("code").toString())){
				resultVo = memberInfoDubbo.updateMemberIdCard(account, idCardName, idCardNo, "0", "3", "", "");
				resultVo=new ResultVo();
				resultVo.setCode(400);
				resultVo.setMsg("参数不正确");
				model.put("resultVo", resultVo);
			}
			if("0".equals(map.get("isok").toString())&&"20".equals(map.get("code").toString())){
				resultVo = memberInfoDubbo.updateMemberIdCard(account, idCardName, idCardNo, "0", "3", "", "");
				resultVo=new ResultVo();
				resultVo.setCode(400);
				resultVo.setMsg("身份证中心维护中");
				model.put("resultVo", resultVo);
			}
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, MemberController.class);
		}
		return new ModelAndView("wx/o2o-shop/apply-waiting-raelName",model);
	}

	/**
	 * @描述 没有用户信息
	 * @author Karhs
	 * @date 2017年2月28日
	 * @return
	 */
	@RequestMapping(value = "toRealNameWait", method = RequestMethod.GET)
	public ModelAndView toRealNameWait() {
		return new ModelAndView("wx/o2o-shop/apply-waiting-raelName");
	}
	/**
	 *根据用户身份证号码获取用户信息
	 * @param idCard
	 * @return
	 */
	@RequestMapping(value = "findMemberInfoByIdCard", method = RequestMethod.GET)
	@ResponseBody
	public Object findMemberInfoByIdCard(String idCard){
		
		try{
			resultVo=memberInfoDubbo.findMemberInfoByIdCard(idCard);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return resultVo;	
	}
	
	
	
	/**
	 *APP生成用户二维码
	 * @param idCard
	 * @return
	 */
	@RequestMapping(value = "appMyQr", method = RequestMethod.GET)
	public ModelAndView appMyQr(){	
		Map<String, Object> model=new HashMap<>();
		try{
			String account = SessionUtil.getAccountSession(request);
			MemberInfoVo memberInfoVo = (MemberInfoVo) memberInfoDubbo.findMemberByMobile(account).getResult();
			if(!BeanUtil.isValid(memberInfoVo.getImgAppQrUrl())){
				String path = "";
				
				String	url = GenerateQrCodeUtil.generateQrcode(request, projectName+"/wx/member/toMemberRegister?superId="+memberInfoVo.getId(), projectNames, imageFolderNames);
				
				if (BeanUtil.isValid(url)) {
					path = projectName + imageFolderNames + "/" + url;
				}
				memberInfoVo.setImgQrUrl(path);
				GjfMemberInfo info = memberInfoDubbo.findMember(memberInfoVo.getMobile());
				info.setImgAppQrUrl(path);
				memberInfoDubbo.updateMemberCode(info);
			}
			resultVo=memberInfoDubbo.findMemberByMobile(account);
			model.put("resultVo", resultVo);
		}catch(Exception e){
			e.printStackTrace();
		}		
		return new ModelAndView("wx/o2o-shop/my-ercode", model);
	}
	
	/**
	 * @描述 跳转到用户注册页面
	 * @author Karhs
	 * @date 2017年2月28日
	 * @return
	 */
	@RequestMapping(value = "toMemberRegister", method = RequestMethod.GET)
	public ModelAndView toMemberRegister(Long superId) {
		Map<String, Object> model = new HashMap<>();
		try {			
			model.put("superId", superId);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, MemberController.class);
			model.put("resultVo", resultVo);
		}
		//return new ModelAndView("wx/o2o-shop/member-register", model);
		return new ModelAndView("wx/o2o-shop/app-new-register", model);	
	}
	
	/**
	 * app用户注册
	 * @param nickName
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value="memberRegister", method = RequestMethod.POST)
	@ResponseBody
	public Object memberRegister(@RequestParam("nickName") String nickName,@RequestParam("mobile") String mobile,String superId,String password){
		try{
			/*if(!BeanUtil.isValid(nickName)){
				return new ResultVo(400,"昵称不能为空",null);
			}*/
			if(!BeanUtil.isValid(mobile)){
				return new ResultVo(400,"电话号码不能为空",null);
			}
			MemberInfoVo memberInfoVo = (MemberInfoVo) memberInfoDubbo.findMemberByMobile(mobile).getResult();
			if(memberInfoVo!=null){
				return new ResultVo(400,"用户已存在",null);
			}

			if(!BeanUtil.isValid(superId)){
				superId="0";
			}
			resultVo =loginDubbo.register(mobile,password, nickName, "0", "", "0",Long.valueOf(superId), "");
		}catch(Exception e){
			e.printStackTrace();
			resultVo = LoggerPrint.getResult(e, LoginControllerV1.class);
		}
		return resultVo;
	}
			
	/**
	 * 手动回调
	 * @param trade_no
	 * @param pay_no
	 * @param type 1让利回调  2授信回调
	 * @return
	 */
	@RequestMapping(value="/payNotifyByHand",method = RequestMethod.POST)
	@ResponseBody
	public Object payNotifyByHand(String trade_no,String pay_no,String type){
		try{
			if("1".equals(type)){
				resultVo=tradeDubbo.updateStoreBenefitPayStatus(trade_no, pay_no, "1");
			}
			if("2".equals(type)){
				resultVo=tradeDubbo.updateShouXinPayStatus(trade_no, pay_no, "1");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultVo;
	}
	
	/**
	 * 快捷支付申请
	 */
	@RequestMapping(value="/yinLPay",method = RequestMethod.POST)
	@ResponseBody
	public Object yinLPay(String orderId,String payMoney,String retUrl,String accNo,String mobile){
		try{
			String account = SessionUtil.getAccountSession(request);
			GjfMemberInfo memberInfo=memberInfoDubbo.findMember(account);
			
			//获取动态秘钥
			String dypass = FastPayDemoTest.getDyPass(orderId);
			if(!BeanUtil.isValid(dypass)){
				return new ResultVo(400, "获取动态秘钥失败", null);
			}
			Map<String, String> fastMap=FastPayDemoTest.fastApply(orderId,dypass,payMoney,mobile,accNo,memberInfo.getName(),memberInfo.getIdCard(), retUrl);
			if(fastMap==null){
				return new ResultVo(400, "快捷支付请求失败", null);
			}
			if(!"00".equals(fastMap.get("resultCode"))){
				return new ResultVo(400, fastMap.get("resultDesc"), null);
			}
			if("00".equals(fastMap.get("resultCode"))){
				return new ResultVo(200, "预下单成功", fastMap);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ResultVo(400, "预下单失败", null);
	}
	
	
	/**
	 * 跳转到快捷支付确认页面
	 */
	@RequestMapping(value="/goConfirmPay",method = RequestMethod.GET)
	public ModelAndView goConfirmPay(String orderId,String payMoney,String accNo,String mobile){
		Map<String, Object> model=new HashMap<>();
		try{
			String account = SessionUtil.getAccountSession(request);
			GjfMemberInfo memberInfo=memberInfoDubbo.findMember(account);
			model.put("orderId", orderId);
			model.put("payMoney", payMoney);
			model.put("accNo", accNo);
			model.put("mobile", mobile);
			model.put("isReadName", memberInfo.getIsReadName());
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("wx/o2o-shop/H5payConfirmViCode", model);	
	}
	
	/**
	 * 快捷支付确认
	 */
	@RequestMapping(value="/yinLPayCofirom",method = RequestMethod.POST)
	@ResponseBody
	public Object yinLPayCofirom(String orderId,String payMoney,String verifyCode,String accNo,String mobile){
		try{
			String account = SessionUtil.getAccountSession(request);
			GjfMemberInfo memberInfo=memberInfoDubbo.findMember(account);
			
			//获取动态秘钥
			String dypass = FastPayDemoTest.getDyPass(orderId);
			if(!BeanUtil.isValid(dypass)){
				return new ResultVo(400, "获取动态秘钥失败", null);
			}
			Map<String, String> fastMap=FastPayDemoTest.fastConfirm(orderId, dypass, verifyCode,payMoney, mobile, accNo,memberInfo.getName(),memberInfo.getIdCard());
			if(fastMap==null){
				return new ResultVo(400, "支付请求失败", null);
			}
			if(!"00".equals(fastMap.get("resultCode"))){				
				return new ResultVo(400, fastMap.get("resultDesc"), null);
			}
			if("00".equals(fastMap.get("resultCode"))){
				return new ResultVo(200, "支付成功", fastMap);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ResultVo(400, "预下单失败", null);
	}
	
	/**
	 * 进入运营中心
	 * @return
	 */
	@RequestMapping(value="/opcenter",method = RequestMethod.GET)
	public Object opcenter(){
		Map<String, Object> model=new HashMap<>();
		try{
			String account = SessionUtil.getAccountSession(request);
			resultVo=memberInfoDubbo.findMemberOpcenterBenefitMoney(account);
			model.put("resultVo", resultVo);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("wx/o2o-shop/opcenter", model);	
	}
	
	/**
	 * 获取运营中心历史记录
	 * @return
	 */
	@RequestMapping(value="/opcenterHistory",method = RequestMethod.GET)
	public Object opcenterHistory(Long memId){
		Map<String, Object> model=new HashMap<>();
		try{			
			resultVo=memberInfoDubbo.findOpcenterHistory(1, 20, memId);
			model.put("resultVo", resultVo);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("wx/o2o-shop/opcenter-history", model);	
	}
	
	/**
	 * 运营中心商家列表
	 * @return
	 */
	@RequestMapping(value="/opcenterMchInfo",method = RequestMethod.GET)
	public Object opcenterMchInfo(Integer pageNo,Integer pageSize,Long memId){
		Map<String, Object> model=new HashMap<>();
		try{
			resultVo=memberInfoDubbo.findOpcenterMchInfo(pageNo, pageSize, memId);
			model.put("resultVo", resultVo);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("wx/o2o-shop/opcenter-list", model);	
	}
				
}
