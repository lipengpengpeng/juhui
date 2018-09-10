package cc.messcat.gjfeng.web.wechat;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.h5pay.api.H5PayUtil;

import cc.messcat.gjfeng.common.bean.Pager;
import cc.messcat.gjfeng.common.bean.Pager;
import cc.messcat.gjfeng.common.constant.CommonConstant;
import cc.messcat.gjfeng.common.constant.CommonConstant;
import cc.messcat.gjfeng.common.constant.CommonProperties;
import cc.messcat.gjfeng.common.exception.LoggerPrint;
import cc.messcat.gjfeng.common.util.GenerateQrCodeUtil;
import cc.messcat.gjfeng.common.util.HttpXmlClient;
import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.util.ResponseBean;
import cc.messcat.gjfeng.common.util.StringUtil;
import cc.messcat.gjfeng.common.vo.app.OrderAddModel;
import cc.messcat.gjfeng.common.vo.app.OrderAddVo;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.common.vo.app.StoreInfoVo;
import cc.messcat.gjfeng.common.vo.app.StoreJoinVo;
import cc.messcat.gjfeng.common.web.BaseController;
import cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfOrderInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfStoreInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfTradeDubbo;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.entity.GjfOrderInfo;
import cc.messcat.gjfeng.entity.GjfStoreInfo;
import cc.messcat.gjfeng.entity.GjfStoreInfo;
import cc.messcat.gjfeng.entity.GjfWeiXinPayInfo;
import cc.messcat.gjfeng.upload.UploadFileUtil;
import cc.messcat.gjfeng.util.SessionUtil;

@Controller
@RequestMapping("wx/store/")
public class StoreController extends BaseController {

	@Value("${gjfeng.client.project.url}")
	private String projectName;

	@Value("${upload.store.electronic.path}")
	private String uploadFilePath;

	@Value("${upload.store.banner.path}")
	private String uploadImagePath;

	@Value("${dubbo.application.web.client}")
	private String projectNames;
	
	@Value("${upload.member.code.path}")
	private String imageFolderNames;

	@Autowired
	@Qualifier("storeInfoDubbo")
	private GjfStoreInfoDubbo storeInfoDubbo;

	@Autowired
	@Qualifier("memberInfoDubbo")
	private GjfMemberInfoDubbo memberInfoDubbo;

	@Autowired
	@Qualifier("request")
	private HttpServletRequest request;

	@Autowired
	@Qualifier("response")
	private HttpServletResponse response;
	
	@Autowired
	@Qualifier("orderInfoDubbo")
	private GjfOrderInfoDubbo orderInfoDubbo;
	
	@Autowired
	@Qualifier("tradeDubbo")
	private GjfTradeDubbo tradeDubbo;

	/**
	 * @描述 我是商家
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "my", method = RequestMethod.GET)
	public ModelAndView myStore() {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			String account = SessionUtil.getAccountSession(request);
			resultVo = storeInfoDubbo.findStoreByAccount(account);
			model.put("resultVo", resultVo);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, StoreController.class);
			model.put("resultVo", resultVo);
		}
		StoreInfoVo storeInfoVo = (StoreInfoVo) resultVo.getResult();
		if (ObjValid.isValid(storeInfoVo) && "1".equals(storeInfoVo.getStoreStatus())) {
			return new ModelAndView("wx/o2o-shop/store-info", model);
		} else {
			return new ModelAndView("wx/o2o-shop/store-info-auditStatus", model);
		}

	}

	/**
	 * @描述 跳转到申请商家入驻
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "toAdd/{storeType}", method = RequestMethod.GET)
	public ModelAndView toAdd(@PathVariable("storeType") String storeType) {
		Map<String, Object> model = new HashMap<String, Object>();
		String pathUrl = "wx/o2o-shop/my-real-name";
		try {
			// 先判断用户是否实名制
			String account = SessionUtil.getAccountSession(request);
			GjfMemberInfo gjfMemberInfo = memberInfoDubbo.findMember(account);
			/*if (gjfMemberInfo.getIsReadName().equals("0")) {
				return new ModelAndView(pathUrl);
			}*/

			if (StringUtil.isNotBlank(storeType) && storeType.equals("1")) {
				model.put("storeType", "1");
				pathUrl = "wx/o2o-shop/store-apply-company";
			} else {
				model.put("storeType", "0");
				pathUrl = "wx/o2o-shop/store-apply-personal";
			}
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, StoreController.class);
			model.put("storeType", "0");
		}
		return new ModelAndView(pathUrl, model);
	}
	
	
	@RequestMapping(value = "/imageUploadBStore", method = RequestMethod.POST)
	@ResponseBody
	public Object storeImageFileByBase64(@RequestParam String fileName, @RequestParam String fileContent) throws Exception {
		String url="";
		if (!fileContent.isEmpty()) {
			String[] str = fileContent.split(",");
			String fils = UploadFileUtil.uploadBase63(fileName, str[1], request, uploadFilePath);
			url = projectName + uploadFilePath + "/" + fils;
		}
		return new ResponseBean(CommonConstant.SUCCESS_CODE_200, CommonConstant.MSG_SUCCESS, url);
	}

	/**
	 * @描述 商家入驻申请
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ModelAndView addStore(StoreInfoVo storeInfoVo, StoreJoinVo storeJoinVo, String storefileName) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			if (ObjValid.isNotValid(storeInfoVo)) {
				resultVo = new ResultVo(400, "提交失败，参数不全", null);
				model.put("resultVo", resultVo);
				return new ModelAndView("wx/o2o-shop/store-apply-waiting", model);
			}
			/*if (!file.isEmpty()) {
				String fileName = UploadFileUtil.upload(file, request, uploadFilePath);
				if (ObjValid.isNotValid(storeJoinVo)) {
					storeJoinVo = new StoreJoinVo();
				}
				storeJoinVo.setBusinessLicenceNumberElectronic(projectName + uploadFilePath + "/" + fileName);
			} else {
				resultVo = new ResultVo(400, "文件上传失败", null);
				model.put("resultVo", resultVo);
				return new ModelAndView("wx/o2o-shop/store-apply-waiting", model);
			}*/
			System.out.println(storefileName);
			storeJoinVo.setBusinessLicenceNumberElectronic(storefileName);
			String account = SessionUtil.getAccountSession(request);
			resultVo = storeInfoDubbo.addStore(storeInfoVo, storeJoinVo, account);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, StoreController.class);
		}
		model.put("resultVo", resultVo);
		return new ModelAndView("wx/o2o-shop/apply-waiting", model);
	}

	/**
	 * @描述 跳转到查询店铺简介
	 * @author Karhs
	 * @date 2017年1月12日
	 * @return
	 */
	@RequestMapping(value = "intro", method = RequestMethod.GET)
	public ModelAndView findIntro() {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			String account = SessionUtil.getAccountSession(request);
			model.put("resultVo", storeInfoDubbo.findStoreByAccount(account));
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, StoreController.class);
			model.put("resultVo", null);
		}
		return new ModelAndView("wx/o2o-shop/store-introduce", model);
	}

	/**
	 * @描述 跳转到修改店铺简介
	 * @author Karhs
	 * @date 2017年1月12日
	 * @return
	 */
	@RequestMapping(value = "toUpdateIntro", method = RequestMethod.GET)
	public ModelAndView toUpdateIntro() {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			String account = SessionUtil.getAccountSession(request);
			model.put("resultVo", storeInfoDubbo.findStoreByAccount(account));
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, StoreController.class);
			model.put("resultVo", null);
		}
		return new ModelAndView("wx/o2o-shop/store-intro-update", model);
	}

	/**
	 * @描述 修改店铺简介
	 * @author Karhs
	 * @date 2017年1月12日
	 * @param description
	 * @return
	 */
	@RequestMapping(value = "updateIntro", method = RequestMethod.POST)
	@ResponseBody
	public Object updateIntro(@RequestParam("description") String description) {
		try {
			Long storeId = SessionUtil.getStoreIdSession(request);
			resultVo = storeInfoDubbo.updateStoreDescription(storeId, description);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, StoreController.class);
		}
		return resultVo;
	}

	/**
	 * @描述 修改店铺banner图
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "updateBanner", method = RequestMethod.POST)
	public ModelAndView updateBanner(MultipartFile file) {
		try {
			Long storeId = SessionUtil.getStoreIdSession(request);
			String bannerImgUrl = "";
			if (!file.isEmpty()) {
				String fils = UploadFileUtil.upload(file, request, uploadImagePath);
				bannerImgUrl = projectName + uploadImagePath + "/" + fils;
			}
			storeInfoDubbo.updateStoreBanner(storeId, bannerImgUrl);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, StoreController.class);
		}
		return myStore();
	}

	/**
	 * @描述 跳转到修改店铺地址页面
	 * @author Karhs
	 * @date 2017年1月12日
	 * @return
	 */
	@RequestMapping(value = "toUpdateAddressInfo", method = RequestMethod.GET)
	public ModelAndView toUpdateAddressInfo() {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			String account = SessionUtil.getAccountSession(request);
			model.put("resultVo", storeInfoDubbo.findStoreByAccount(account));
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, StoreController.class);
			model.put("resultVo", null);
		}
		return new ModelAndView("wx/o2o-shop/store-address-update", model);
	}

	/**
	 * @描述 修改店铺简介
	 * @author Karhs
	 * @date 2017年1月12日
	 * @param description
	 * @return
	 */
	@RequestMapping(value = "updateAddressInfo", method = RequestMethod.POST)
	@ResponseBody
	public Object updateAddressInfo(String sellerMobile, String cityValue, String addressDetail) {
		try {
			Long storeId = SessionUtil.getStoreIdSession(request);
			resultVo = storeInfoDubbo.modifyStoreAddress(storeId, sellerMobile, cityValue, addressDetail);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, StoreController.class);
		}
		return resultVo;
	}
	
	/**
	 * 根据用户信息获取店铺信息
	 * @return
	 */
	@RequestMapping(value = "getStoreInfoByMemId", method = RequestMethod.POST)
	@ResponseBody
	public Object getStoreInfoByMemId() {
		try {
			String account = SessionUtil.getAccountSession(request);
			resultVo = storeInfoDubbo.findStoreByAccount(account);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, StoreController.class);
		}
		return resultVo;
	}
	
	/**
	 * 测试
	 * @return
	 */
	@RequestMapping(value = "findStoreByAccount",	 method = RequestMethod.GET)
	@ResponseBody
	public Object findStoreByAccount(){
		try {		
			resultVo=storeInfoDubbo.findStoreByPager(1,1000,"","","","","");
			Pager pager=(Pager) resultVo.getResult();
			List<GjfStoreInfo> list=pager.getResultList();
			for(int i=0;i<list.size();i++){
				GjfStoreInfo gjfStoreInfo=list.get(i);
				System.out.println(gjfStoreInfo.getSellerMobile());
				System.out.println(gjfStoreInfo.getMemberId().getMobile());
				storeInfoDubbo.findStoreByAccount(gjfStoreInfo.getMemberId().getMobile());
			}
			

		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, StoreController.class);
		}
		return resultVo;
	}
	
	/**
	 * 用户买单
	 * @return
	 */
	SweepPayThread sweepPayThread=null;
	@RequestMapping(value = "payStoreOrder", method = RequestMethod.GET)
	@ResponseBody
	public Object payStoreOrder(OrderAddModel orderAddModel, @RequestParam("payType") String payType,
			Long couponsId,String remark,String type,String mobile,Long storeId){		
		try {
			String account="";
			if("1".equals(type)){
				account = SessionUtil.getAccountSession(request);
			}else{
				account=mobile;
			}	
			//查询店铺信息
			GjfStoreInfo storeInfo=  (GjfStoreInfo) storeInfoDubbo.findStoreByStoreId(storeId).getResult();
			if(!StringUtil.isNotBlank(storeInfo.getPayMchId())&&!StringUtil.isNotBlank(storeInfo.getPayKey())){
				return new ResultVo(400,"店铺信息不全，支付功能还未开放，敬请期待",null);
			}
			List<OrderAddVo> orderAddVos = orderAddModel.getOrderAddVos();
			resultVo = orderInfoDubbo.addOrder(account, orderAddVos, "0", payType, remark, couponsId, 0L,"0","0",null,null,new BigDecimal(0));

			if (resultVo.getCode() == 200) {
				GjfOrderInfo gjfOrderInfo = (GjfOrderInfo) resultVo.getResult();				
				if (payType.equals("1")) {
					// 调用微信支付
					String token_id = H5PayUtil.weixinSweepPay(storeInfo.getPayMchId(),storeInfo.getPayKey(),request, 
							gjfOrderInfo.getOfflinePayAmount().toString(),gjfOrderInfo.getOrderSn(), "购买商品",CommonProperties.GJFENG_SWEEP_NOTIFY_ORDER);
					
					if(StringUtil.isNotBlank(token_id)){
						resultVo.setResult(token_id);
						// 定义线程
						sweepPayThread = new SweepPayThread(gjfOrderInfo.getOrderSn(),gjfOrderInfo.getToken());
						sweepPayThread.start();
					}else{
						resultVo=new ResultVo(400,"商户信息设置问题，请检查店铺设置的商户信息是否正确");
					}
					
				}
				
				if (payType.equals("2")) {
					// 调用支付宝支付					
					String token_id = H5PayUtil.alipaySweepPay(storeInfo.getPayMchId(),storeInfo.getPayKey(),request, 
							gjfOrderInfo.getOfflinePayAmount().toString(),gjfOrderInfo.getOrderSn(), "购买商品",CommonProperties.GJFENG_SWEEP_NOTIFY_ORDER);
					if(StringUtil.isNotBlank(token_id)){
						resultVo.setResult(token_id);
						// 定义线程
						sweepPayThread = new SweepPayThread(gjfOrderInfo.getOrderSn(),gjfOrderInfo.getToken());
						sweepPayThread.start();
					}else{
						resultVo=new ResultVo(400,"商户信息设置问题，请检查店铺设置的商户信息是否正确");
					}
				}
			}

		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, OrderController.class);
		}
		return resultVo;
	}
	
	// 线程
	 class SweepPayThread extends Thread {
			private String out_trade_on;
			private String token;

			SweepPayThread(String out_trade_on,String token) {
				this.out_trade_on = out_trade_on;
				this.token=token;
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
						resultVo=orderInfoDubbo.findOrderBySn(out_trade_on,token);
						GjfOrderInfo orderInfo=(GjfOrderInfo) resultVo.getResult();
						if("0".equals(orderInfo.getOrderStatus())){				
							Map<String, Object> map= H5PayUtil.alipaySweepPay(orderInfo.getStoreId().getPayMchId(),orderInfo.getStoreId().getPayKey(), out_trade_on);
							if(map!=null){
								String out_trade_no1 = (String) map.get("out_trade_no");
								String trade_no = (String) map.get("transaction_id");
								orderInfoDubbo.updateOrderStatus(out_trade_no1,trade_no, "1", null, null, "1");								
							}		
						}else{
							sweepPayThread.interrupt();
						}
														
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	
	/**
	 * 跳转到输入支付金额页面
	 * @return
	 */
	@RequestMapping(value="goInputMoneyPage",method = RequestMethod.GET)
	public Object goInputMoneyPage(Long storeId){
		Map<String, Object> model = new HashMap<String, Object>();		
		model.put("storeId", storeId);		
		return new ModelAndView("wx/o2o-shop/store_pay_type",model);
	}
	
	/**
	 * 跳转到输入支付金额页面
	 * @return
	 */
	@RequestMapping(value="goMoneyPage",method = RequestMethod.GET)
	public Object goMoneyPage(Long storeId){
		Map<String, Object> model = new HashMap<String, Object>();		
		try {
			String url = URLEncoder.encode(CommonProperties.CLIENT_PROJECT_URL+"/wx/store/goInputMoneyPage?storeId="+storeId, "UTF-8");
			model.put("storeId", storeId);
			model.put("url", url);
			//model.put("appId", );
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("wx/o2o-shop/store_buy_empty",model);
	}
	
	@RequestMapping(value="acutPage",method = RequestMethod.GET)
	@ResponseBody
	public Object acutPage(Long storeId){
		String url;
		try {

			url = URLEncoder.encode(CommonProperties.CLIENT_PROJECT_URL+"/wx/store/goInputMoneyPage?storeId="+storeId, "UTF-8");
			//String poUrl="https://open.weixin.qq.com/connect/oauth2/authorize?appid="++"&redirect_uri="+url+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
			//String xml=HttpXmlClient.get(poUrl);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   return null;
	}
		
	/*
	 * 商家生成支付二维码
	 */
	@RequestMapping(value="createPayQr",method = RequestMethod.GET)
	public ModelAndView createPayQr(Long storeId){
		Map<String, Object> model = new HashMap<String, Object>();
		String qrurl="";
		try {			
			qrurl= GenerateQrCodeUtil.generateQrcode(request, projectName+"/wx/store/goMoneyPage?storeId="+storeId, projectNames, imageFolderNames);
			model.put("url", projectName+imageFolderNames+"/"+qrurl);			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return new ModelAndView("wx/o2o-shop/store_pay_code",model);
	}
	
	/**
	 * 附近根据条件搜索店铺
	 * 
	 * @return
	 */
	@RequestMapping(value = "o2o/stores")
	public ModelAndView o2oStoresByAllColumn(Integer pageNo, Integer pageSize, Long columnId,
			@RequestParam("orderType") String orderType, String columnType, String likeValue) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			Double longitude = SessionUtil.getLatOrLong(request, "longitude");
			Double latitude = SessionUtil.getLatOrLong(request, "latitude");
//			Long cityId = SessionUtil.getCityCode(request);
			String cityName = (String)request.getSession().getAttribute("cityName");
			param.put("pageNo", ObjValid.isNotValid(pageNo) ? 1 : pageNo);
			param.put("pageSize", ObjValid.isNotValid(pageSize) ? 10 : pageSize);
			param.put("orderType", orderType);
			param.put("likeValue", likeValue);
			param.put("longitude", longitude);
			param.put("latitude", latitude);
			param.put("cityName", cityName);
			resultVo = storeInfoDubbo.findStoreByColumn(columnId, param);
			model.put("stores", resultVo);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, StoreController.class);
			model.put("stores", resultVo);
		}
		return new ModelAndView("wx/o2o-shop/index-subcolumn-store-ajax", model);
	}

	/**
	 * 商店信息
	 * 
	 * @param storeId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "o2o/store/{id}")
	public ModelAndView o2oStore(@PathVariable("id") Long storeId) {
		Double longitude = SessionUtil.getLatOrLong(request, "longitude");
		Double latitude = SessionUtil.getLatOrLong(request, "latitude");
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param= new HashMap<>();
		param.put("id", storeId);
		param.put("longitude", longitude);
		param.put("latitude", latitude);
		ResultVo resultVo = storeInfoDubbo.findStores(param);
		List<StoreInfoVo> storeInfoVos = (List<StoreInfoVo>)resultVo.getResult();
		if(storeInfoVos.size() == 0){
			throw new RuntimeException("店铺不存在");
		}
		model.put("store", storeInfoVos.get(0));
		return new ModelAndView("wx/o2o-shop/store-detail", model);
	}
}
