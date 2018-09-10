package com.wechat.popular.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.container.page.Menu;
import com.wechat.popular.bean.BaseResult;
import com.wechat.popular.bean.MenuButtons;
import com.wechat.popular.client.LocalHttpClient;
import com.wechat.popular.support.TokenManager;

import cc.messcat.gjfeng.common.constant.CommonProperties;


public class MenuAPI extends BaseAPI{

	/**
	 * 创建菜单
	 * @param access_token
	 * @param menuJson 菜单json 数据 例如{\"button\":[{\"type\":\"click\",\"name\":\"今日歌曲\",\"key\":\"V1001_TODAY_MUSIC\"},{\"type\":\"click\",\"name\":\"歌手简介\",\"key\":\"V1001_TODAY_SINGER\"},{\"name\":\"菜单\",\"sub_button\":[{\"type\":\"view\",\"name\":\"搜索\",\"url\":\"http://www.soso.com/\"},{\"type\":\"view\",\"name\":\"视频\",\"url\":\"http://v.qq.com/\"},{\"type\":\"click\",\"name\":\"赞一下我们\",\"key\":\"V1001_GOOD\"}]}]}
	 * @return
	 */
	public static BaseResult menuCreate(String access_token,String menuJson){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
										.setHeader(jsonHeader)
										.setUri(BASE_URI+"/cgi-bin/menu/create")
										.addParameter("access_token", access_token)
										.setEntity(new StringEntity(menuJson,Charset.forName("utf-8")))
										.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,BaseResult.class);
	}

	/**
	 * 创建菜单
	 * @param access_token
	 * @param menuButtons
	 * @return
	 */
	public static BaseResult menuCreate(String access_token,MenuButtons menuButtons){
		String str = JsonUtil.toJSONString(menuButtons);
		return menuCreate(access_token,str);
	}

	/**
	 * 获取菜单
	 * @param access_token
	 * @return
	 */
	public static Menu menuGet(String access_token){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
					.setUri(BASE_URI+"/cgi-bin/menu/get")
					.addParameter("access_token", access_token)
					.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,Menu.class);
	}

	/**
	 * 删除菜单
	 * @param access_token
	 * @return
	 */
	public static BaseResult menuDelete(String access_token){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(BASE_URI+"/cgi-bin/menu/delete")
				.addParameter("access_token", access_token)
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,BaseResult.class);
	}
	
	/**
	 * 组装菜单数据
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private static MenuButtons getMenu() throws UnsupportedEncodingException {
		
		MenuButtons menuButtons = new MenuButtons();
		
											
		/*MenuButtons.Button mainBtn1 = new MenuButtons.Button();
		mainBtn1.setName("会员商城");
		mainBtn1.setType("view");
		String url1 = URLEncoder.encode(CommonProperties.CLIENT_PROJECT_URL+"/wx/index/supplyChainOnlineShopIndex", "UTF-8");
		mainBtn1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+CommonProperties.GJFENG_APP_ID+"&redirect_uri="+url1+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
		
		MenuButtons.Button mainBtn2 = new MenuButtons.Button();
		mainBtn2.setName("商家采购");
		mainBtn2.setType("view");
		String url2 = URLEncoder.encode(CommonProperties.CLIENT_PROJECT_URL+"/wx/index/merchantOnline", "UTF-8");
		//String url2 = URLEncoder.encode(CommonProperties.CLIENT_PROJECT_URL+"/wx/cart/my", "UTF-8");
		mainBtn2.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+CommonProperties.GJFENG_APP_ID+"&redirect_uri="+url2+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
					
		MenuButtons.Button mainBtn3 = new MenuButtons.Button();
		mainBtn3.setName("个人中心");
		mainBtn3.setType("view");
		String url3 = URLEncoder.encode(CommonProperties.CLIENT_PROJECT_URL+"/wx/member/my", "UTF-8");
		mainBtn3.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+CommonProperties.GJFENG_APP_ID+"&redirect_uri="+url3+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
		*/
		
		MenuButtons.Button mainBtn1 = new MenuButtons.Button();
		mainBtn1.setName("附近门店");
		mainBtn1.setType("view");
		String url1 = URLEncoder.encode(CommonProperties.CLIENT_PROJECT_URL+"/wx/index/o2o", "UTF-8");
		mainBtn1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+CommonProperties.GJFENG_APP_ID+"&redirect_uri="+url1+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
		
			
		MenuButtons.Button mainBtn2 = new MenuButtons.Button();
		mainBtn2.setName("网上商城");
		mainBtn2.setType("view");
		String url2 = URLEncoder.encode(CommonProperties.CLIENT_PROJECT_URL+"/wx/index/online", "UTF-8");
		mainBtn2.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+CommonProperties.GJFENG_APP_ID+"&redirect_uri="+url2+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
		
				
		
		MenuButtons.Button mainBtn3 = new MenuButtons.Button();
		mainBtn3.setName("更多操作");
		
		MenuButtons.Button childBtn7 = new MenuButtons.Button();
		childBtn7.setName("个人中心");
		childBtn7.setType("view");
		String url6 = URLEncoder.encode(CommonProperties.CLIENT_PROJECT_URL+"/wx/member/my", "UTF-8");
		childBtn7.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+CommonProperties.GJFENG_APP_ID+"&redirect_uri="+url6+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
		
		
		MenuButtons.Button childBtn8 = new MenuButtons.Button();
		childBtn8.setName("我要邀请");
		childBtn8.setType("view");
		String url7 = URLEncoder.encode(CommonProperties.CLIENT_PROJECT_URL+"/wx/member/myQr", "UTF-8");
		childBtn8.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+CommonProperties.GJFENG_APP_ID+"&redirect_uri="+url7+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
		
		
		MenuButtons.Button childBtn9 = new MenuButtons.Button();
		childBtn9.setName("福利账户");
		childBtn9.setType("view");
		String url8 = URLEncoder.encode(CommonProperties.CLIENT_PROJECT_URL+"/wx/member/myWallet", "UTF-8");
		childBtn9.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+CommonProperties.GJFENG_APP_ID+"&redirect_uri="+url8+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
		
		
		/*MenuButtons.Button childBtn10 = new MenuButtons.Button();
		childBtn10.setName("授信管理");
		childBtn10.setType("view");
		String url9 = URLEncoder.encode(CommonProperties.CLIENT_PROJECT_URL+"/wx/member/shouXin", "UTF-8");
		childBtn10.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+CommonProperties.GJFENG_APP_ID+"&redirect_uri="+url9+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
		*/
		MenuButtons.Button childBtn11 = new MenuButtons.Button();
		childBtn11.setName("销售录入");
		childBtn11.setType("view");
		String url10 = URLEncoder.encode(CommonProperties.CLIENT_PROJECT_URL+"/wx/trade/toBenefit", "UTF-8");
		childBtn11.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+CommonProperties.GJFENG_APP_ID+"&redirect_uri="+url10+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
		
		
		
		List<MenuButtons.Button> button3List = new ArrayList<MenuButtons.Button>();
		button3List.add(childBtn7);
		button3List.add(childBtn8);
		button3List.add(childBtn9);
		//button3List.add(childBtn10);
		button3List.add(childBtn11);
		mainBtn3.setSub_button(button3List);
		
		
		menuButtons.setButton(new MenuButtons.Button[]{mainBtn1,mainBtn2,mainBtn3});	
		//menuButtons.setButton(new MenuButtons.Button[]{mainBtn1});
		
		return menuButtons;
	}
	
	public static void main(String[] args) throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:spring*.xml");
		System.out.println(CommonProperties.GJFENG_APP_ID+"**"+CommonProperties.GJFENG_APPSECRET);
		Logger logger=Logger.getLogger(MenuAPI.class);
		TokenManager.init(CommonProperties.GJFENG_APP_ID, CommonProperties.GJFENG_APPSECRET);
		//logger.info(WeChatPublicNumberConfig.GJFENG_APP_ID()+"----------"+WeChatPublicNumberConfig.GJFENG_APPSECRET());
		String token = TokenManager.getToken(CommonProperties.GJFENG_APP_ID);
		//System.out.println(CommonProperties.GJFENG_APP_ID()+"***"+CommonProperties.GJFENG_APPSECRET());
		//Token tokens = TokenAPI.token(WeChatPublicNumberConfig.GJFENG_APP_ID(), WeChatPublicNumberConfig.GJFENG_APPSECRET());
		//System.out.println("DDD");
		System.out.println(token);
		BaseResult baseResult = MenuAPI.menuCreate(token, getMenu());
		System.out.println(baseResult.getErrcode()+"--"+baseResult.getErrmsg());
	}
}