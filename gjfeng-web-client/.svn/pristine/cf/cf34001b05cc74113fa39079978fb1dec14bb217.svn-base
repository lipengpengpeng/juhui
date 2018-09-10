package cc.messcat.gjfeng.web.wechat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.h5pay.api.H5PayUtil;
import com.wechat.popular.api.QrcodeAPI;
import com.wechat.popular.api.TicketAPI;
import com.wechat.popular.api.TokenAPI;
import com.wechat.popular.bean.QrcodeTicket;
import com.wechat.popular.bean.Ticket;
import com.wechat.popular.bean.Token;
import com.wechat.popular.support.TicketManager;
import com.wechat.popular.util.JsUtil;

import cc.messcat.gjfeng.common.constant.CommonProperties;
import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.util.GenerateQrCodeUtil;
import cc.messcat.gjfeng.common.util.HttpXmlClient;
import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.web.BaseController;
import cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfTradeDubbo;
import cc.messcat.gjfeng.entity.GjfAccessToken;
import cc.messcat.gjfeng.entity.GjfWeiXinPayInfo;

@Controller
@RequestMapping("wx/")
public class WechatController extends BaseController {

	@Autowired
	@Qualifier("request")
	private HttpServletRequest request;
	
	@Value("${dubbo.application.web.client}")
	private String projectName;

	@Value("${upload.member.head.path}")
	private String imageFolderName;
	
	@Value("${upload.member.code.path}")
	private String qrCodeImg;
	
	@Autowired
	@Qualifier("memberInfoDubbo")
	private GjfMemberInfoDubbo memberInfoDubbo;
	
	@Autowired
	@Qualifier("tradeDubbo")
	private GjfTradeDubbo tradeDubbo;
	
	

	/**
	 * @描述 获取Token
	 * @author Karhs
	 * @date 2016年10月24日
	 * @param appid
	 * @param appsecret
	 * @return
	 */
	@RequestMapping(value = "token", method = RequestMethod.POST)
	@ResponseBody
	public Object getToken(@RequestParam("appid") String appid, @RequestParam("appsecret") String appsecret) {
		try {

		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * @描述 获取Ticket
	 * @author Karhs
	 * @date 2016年10月24日
	 * @param appid
	 * @param appsecret
	 * @return
	 */
	@RequestMapping(value = "ticket", method = RequestMethod.POST)
	@ResponseBody
	public Object getTicket(@RequestParam("appid") String appid, @RequestParam("appsecret") String appsecret) {
		try {

		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * @描述 获取微信分享签名
	 * @author Karhs
	 * @date 2016年10月24日
	 * @param appid
	 * @param appsecret
	 * @return
	 */
	@RequestMapping(value = "shareSign", method = RequestMethod.POST)
	@ResponseBody
	public Object getWxShareSign(@RequestParam("appid") String appid, @RequestParam("appsecret") String appsecret) {
		try {
			String basePath = request.getScheme() + "://" + request.getServerName();
			String requestURI = request.getRequestURI();
			String queryString = request.getQueryString();
			String fullPath = basePath + requestURI;
			if (ObjValid.isValid(queryString)) {
				fullPath += "?" + queryString;
			}

			System.out.println("getWechatShareUrl--" + fullPath);
			// return wechatDubboService.getWechatShareSign(appid, appsecret,
			// fullPath);
		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * @描述 获取本地微信签名
	 * @author Karhs
	 * @date 2016年10月24日
	 * @param appid
	 * @param appsecret
	 * @return
	 */
	@RequestMapping(value = "localShareSign", method = RequestMethod.POST)
	@ResponseBody
	public Object getWxLocalShareSign() {
		String sign = "";
		try {
			String basePath = request.getScheme() + "://" + request.getServerName();
			String requestURI = request.getRequestURI();
			String queryString = request.getQueryString();
			String fullPath = basePath + requestURI;
			if (ObjValid.isValid(queryString)) {
				fullPath += "?" + queryString;
			}
			System.out.println("getWechatShareUrl--" + fullPath);
			TicketManager.init(CommonProperties.GJFENG_APP_ID);
			String jsapi_ticket = TicketManager.getTicket(CommonProperties.GJFENG_APP_ID);
			sign = JsUtil.generateConfigJson(jsapi_ticket, true, CommonProperties.GJFENG_APP_ID, fullPath, "getLocation");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sign;
	}

	/**
	 * @描述 发起微信支付
	 * @author Karhs
	 * @date 2016年10月24日
	 * @param orderNum
	 * @param payType
	 * @param amount
	 * @param productTitle
	 * @param payNotityUrl
	 * @param appid
	 * @param partnerId
	 * @param partnerKey
	 * @return
	 */
	@RequestMapping(value = "paySign", method = RequestMethod.POST)
	@ResponseBody
	public Object getPaySign(String orderNum, int payType, Double amount, String productTitle, String payNotityUrl, String appid,
		String partnerId, String partnerKey) {
		try {
			/*
			 * Map session = ActionContext.getContext().getSession();
			 * session.put("WIDtotal_fee", orderNum); session.put("WIDsubject",
			 * orderNum);
			 */
			if (payType == 1) {
				String openId = (String) request.getSession().getAttribute("openid");
				String ip = request.getRemoteHost();
				/*
				 * String json = wechatDubboService.getWechatPay(openId,
				 * orderNum, productTitle, amount==null?"0.0" :
				 * amount.toString(), payNotityUrl, appid, partnerId,
				 * partnerKey,ip); return json;
				 */
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "localSign", method = RequestMethod.POST)
	@ResponseBody
	public Object getIndex(HttpServletRequest request, String lastPath) {

		Map<String, String> mav = new HashMap<>();

		//Token tokens = TokenAPI.token(WeChatPublicNumberConfig.GJFENG_APP_ID(), WeChatPublicNumberConfig.GJFENG_APPSECRET());
		
		GjfAccessToken accessToken=(GjfAccessToken) memberInfoDubbo.getAccessToken("1").getResult();					
		Token tokens=new Token();
		if(BeanUtil.isValid(accessToken)){
			Date time=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			try {
				if(sdf.parse(sdf.format(accessToken.getExpirationTime())).compareTo(sdf.parse(sdf.format(time)))==1){
					
					tokens.setAccess_token(accessToken.getToken());
				}else{
					 tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID,
						CommonProperties.GJFENG_APPSECRET);
				
					GjfAccessToken token=new GjfAccessToken(CommonProperties.GJFENG_APP_ID,CommonProperties.GJFENG_APPSECRET,"1");											
					token.setToken(tokens.getAccess_token());
					memberInfoDubbo.addAccessToken(token);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			 tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID,
				CommonProperties.GJFENG_APPSECRET);																		
			GjfAccessToken token=new GjfAccessToken(CommonProperties.GJFENG_APP_ID,CommonProperties.GJFENG_APPSECRET,"1");
			token.setToken(tokens.getAccess_token());
			memberInfoDubbo.addAccessToken(token);
		}			
		

		long currentTime = System.currentTimeMillis();
		Long expired_Date = new Long(7080 * 1000) + currentTime;
		tokens.setExpired_Date(expired_Date);

		String access_token = tokens.getAccess_token();

		Ticket ticket = TicketAPI.ticketGetticket(access_token);
		String jsapi_ticket = ticket.getTicket();

		// 获取签名signature
		String noncestr = UUID.randomUUID().toString();
		String timestamp = Long.toString(System.currentTimeMillis() / 1000);
		// 获取请求url
		String path = request.getContextPath();
		StringBuffer url0 = request.getRequestURL();
		String queryString = request.getQueryString();
		// 以为我配置的菜单是http://yo.bbdfun.com/first_maven_project/，最后是有"/"的，所以url也加上了"/"
		//String url = request.getScheme() + "://" + request.getServerName() + path + lastPath;
		String url = lastPath;
		String str = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url;
		// sha1加密
		String signature = HttpXmlClient.SHA1(str);
		mav.put("signature", signature);
		mav.put("timestamp", timestamp);
		mav.put("noncestr", noncestr);
		mav.put("appId", CommonProperties.GJFENG_APP_ID);
		/*
		 * System.out.println("jsapi_ticket=" + jsapi_ticket);
		 * System.out.println("noncestr=" + noncestr);
		 * System.out.println("timestamp=" + timestamp);
		 * System.out.println("url=" + url); System.out.println("str=" + str);
		 * System.out.println("signature=" + signature);
		 */
		return mav;
	}

	
	/**
	 * 生成永久二维码
	 * @return
	 */
	@RequestMapping(value = "cratePermanentCode", method = RequestMethod.POST)
	@ResponseBody
	public String cratePermanentCode(String superId){
		//String account=SessionUtil.getAccountSession(request);
		GjfAccessToken accessToken=(GjfAccessToken) memberInfoDubbo.getAccessToken("1").getResult();
		Token tokens=new Token();
		if(BeanUtil.isValid(accessToken)){
			Date time=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			try {
				if(sdf.parse(sdf.format(accessToken.getExpirationTime())).compareTo(sdf.parse(sdf.format(time)))==1){						
					tokens.setAccess_token(accessToken.getToken());
				}else{
					 tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID,
						CommonProperties.GJFENG_APPSECRET);						
					GjfAccessToken token=new GjfAccessToken();
					token.setAppId(CommonProperties.GJFENG_APP_ID);
					token.setAppsecret(CommonProperties.GJFENG_APPSECRET);
					token.setToken(tokens.getAccess_token());
					token.setType("1");
					memberInfoDubbo.addAccessToken(token);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			 tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID,
				CommonProperties.GJFENG_APPSECRET);			
			GjfAccessToken token=new GjfAccessToken();
			token.setAppId(CommonProperties.GJFENG_APP_ID);
			token.setAppsecret(CommonProperties.GJFENG_APPSECRET);
			token.setToken(tokens.getAccess_token());
			token.setType("1");
			memberInfoDubbo.addAccessToken(token);
		}
		String access_token = tokens.getAccess_token();
		//int rsult=new java.util.Random().nextInt(100001);

		QrcodeTicket qrcodeTicket=QrcodeAPI.qrcodeCreateFinalStr(access_token, superId);

		String url="";
		try {
			url=GenerateQrCodeUtil.generateQrcode(request,qrcodeTicket.getUrl(), projectName, qrCodeImg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*resultVo=memberInfoDubbo.findMemberByMobile(account);
		GjfMemberInfo info;
		if(BeanUtil.isValid(resultVo.getResult())){
			 info=(GjfMemberInfo) resultVo.getResult();
			 info.setImgQrUrl(url);
		}*/
		
		return url;
	}
	
	/**
	 *测试
	 */
	@RequestMapping(value = "testPay", method = RequestMethod.POST)
	@ResponseBody
	public void testPay(){
		try{
			GjfWeiXinPayInfo payInfo=(GjfWeiXinPayInfo) tradeDubbo.findWeiXinBaseInfo("0").getResult();
			H5PayUtil.weiXinPay(payInfo,request, "5487854545645645", "0.1", "购买商品","ddf","reer","DGERERG");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}


}
