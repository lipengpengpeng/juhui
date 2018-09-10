package com.wechat.popular.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.wechat.popular.api.TokenAPI;
import com.wechat.popular.api.UserAPI;
import com.wechat.popular.bean.EventMessage;
import com.wechat.popular.bean.Token;
import com.wechat.popular.bean.User;
import com.wechat.popular.util.XMLConverUtil;

import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.util.PropertiesFileReader;
import cc.messcat.gjfeng.config.WaixiConfig;
import cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo;
import cc.messcat.gjfeng.entity.GjfAccessToken;

public class EvenAttention{
	
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	
	Logger logger=Logger.getLogger(EvenAttention.class);
	
	@Autowired
	@Qualifier("memberInfoDubbo")
	private GjfMemberInfoDubbo memberInfoDubbo;

	
	
	@SuppressWarnings("static-access")
	public void executeEven(){
		//this.LOG.info("关注注销-事件：", "1");
		/*HttpServletRequest request = ServletActionContext.getRequest();
    	HttpServletResponse response = ServletActionContext.getResponse();*/
    	ServletInputStream inputStream = null;
    	ServletOutputStream outputStream = null;
    	EventMessage eventMessage = null;
        try {
			inputStream = request.getInputStream();
			outputStream = response.getOutputStream();
			//this.LOG.info("关注注销-事件：",  request +":"+ response + ":" + inputStream + ":" + outputStream);
			eventMessage = XMLConverUtil.convertToObject(EventMessage.class,inputStream);
			//this.LOG.info("微信message:", eventMessage+"");
			
			String openid = eventMessage.getFromUserName();
			
			UserAPI userObj = new UserAPI();
			
			String appID = PropertiesFileReader.get("weichat.appId", "/conf.properties");
			String appsecret = PropertiesFileReader.get("weichat.appsecret", "/conf.properties");
			//Token token = TokenAPI.token(appID, appsecret);
			
			GjfAccessToken accessToken=(GjfAccessToken) memberInfoDubbo.getAccessToken("1").getResult();					
			Token tokens=new Token();
			if(BeanUtil.isValid(accessToken)){
				Date time=new Date();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				try {
					if(sdf.parse(sdf.format(accessToken.getExpirationTime())).compareTo(sdf.parse(sdf.format(time)))==1){
						
						tokens.setAccess_token(accessToken.getToken());
					}else{
						 tokens = TokenAPI.token(WaixiConfig.GJFENG_APP_ID(),
							WaixiConfig.GJFENG_APPSECRET());
					
						GjfAccessToken token=new GjfAccessToken(WaixiConfig.GJFENG_APP_ID(),WaixiConfig.GJFENG_APPSECRET(),"1");											
						token.setToken(tokens.getAccess_token());
						memberInfoDubbo.addAccessToken(token);
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				 tokens = TokenAPI.token(WaixiConfig.GJFENG_APP_ID(),
					WaixiConfig.GJFENG_APPSECRET());																		
				GjfAccessToken token=new GjfAccessToken(WaixiConfig.GJFENG_APP_ID(),WaixiConfig.GJFENG_APPSECRET(),"1");
				token.setToken(tokens.getAccess_token());
				memberInfoDubbo.addAccessToken(token);
			}			
			
			User user = userObj.userInfo(tokens.getAccess_token(), openid);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		writer.println("jfm");
	}
	
}
