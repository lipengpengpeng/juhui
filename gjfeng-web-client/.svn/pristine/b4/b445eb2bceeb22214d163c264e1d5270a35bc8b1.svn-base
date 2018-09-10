package com.wechat.popular.api;

import java.nio.charset.Charset;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;

import com.alibaba.dubbo.remoting.p2p.Group;
import com.wechat.popular.bean.BaseResult;
import com.wechat.popular.bean.FollowResult;
import com.wechat.popular.bean.User;
import com.wechat.popular.client.LocalHttpClient;
import com.wechat.popular.support.TokenManager;

import cc.messcat.gjfeng.config.WaixiConfig;

public class UserAPI extends BaseAPI{

	/**
	 * 获取用户基本信息
	 * @param access_token
	 * @param openid
	 * @return
	 * @throws InterruptedException 
	 */
	public static User userInfo(String access_token,String openid) throws InterruptedException{
		HttpUriRequest httpUriRequest = RequestBuilder.get()
				.setUri(BASE_URI+"/cgi-bin/user/info")
				.addParameter("access_token",access_token)
				.addParameter("openid",openid)
				.addParameter("lang","zh_CN")
				.build();
		User user = LocalHttpClient.executeJsonResult(httpUriRequest,User.class);
		if("40001".equals(user.getErrcode())){
			System.out.println("access_token过期");
			String token = TokenManager.getToken(WaixiConfig.GJFENG_APP_ID());//过期的token
			//TokenManager.init(WeChatPublicNumberConfig.GJFENG_APP_ID(), CommonProperties.appsecret);
			System.out.println("APPID:"+WaixiConfig.GJFENG_APP_ID()+"----------------"+"APPSECRET:"+WaixiConfig.GJFENG_APPSECRET());
			while (true) {
				if (!(TokenManager.getToken(WaixiConfig.GJFENG_APP_ID()).toString().equals(token))){//新的token
					token = TokenManager.getToken(WaixiConfig.GJFENG_APP_ID());
					break;
				}
			}
			 httpUriRequest = RequestBuilder.post()
			.setUri(BASE_URI+"/cgi-bin/user/info")
			.addParameter("access_token",token)
			.addParameter("openid",openid)
			.addParameter("lang","zh_CN")
			.build();
			user = LocalHttpClient.executeJsonResult(httpUriRequest,User.class);
		}
		return user;
	}
	
	/**
	 * 获取关注列表
	 * @param access_token
	 * @param next_openid 第一次获取使用null
	 * @return
	 */
	public static void userGetBatchget(String access_token){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(BASE_URI+"/cgi-bin/user/info/batchget")
				.addParameter("access_token",access_token)				
				.build();
		
		System.out.println(LocalHttpClient.executeJsonResult(httpUriRequest,User.class));
	}

	/**
	 * 获取关注列表
	 * @param access_token
	 * @param next_openid 第一次获取使用null
	 * @return
	 */
	public static FollowResult userGet(String access_token,String next_openid){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(BASE_URI+"/cgi-bin/user/get")
				.addParameter("access_token",access_token)
				.addParameter("next_openid", next_openid==null?"":next_openid)
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,FollowResult.class);
	}

	/**
	 * 创建分组
	 * @param access_token
	 * @param name
	 * @return
	 */
	public static Group groupsCreate(String access_token,String name){
		String groupJson = "{\"group\":{\"name\":\""+name+"\"}}";
		HttpUriRequest httpUriRequest = RequestBuilder.post()
										.setHeader(jsonHeader)
										.setUri(BASE_URI+"/cgi-bin/groups/create")
										.addParameter("access_token", access_token)
										.setEntity(new StringEntity(groupJson,Charset.forName("utf-8")))
										.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,Group.class);
	}

	/**
	 * 查询所有分组
	 * @param access_token
	 * @return
	 */
	public static Group groupsGet(String access_token){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(BASE_URI+"/cgi-bin/groups/get")
				.addParameter("access_token", access_token)
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,Group.class);
	}

	/**
	 * 查询用户所在分组
	 * @param access_token
	 * @param openid
	 * @return
	 */
	public static Group groupsGetid(String access_token,String openid){
		String groupJson = "{\"openid\":\""+openid+"\"}";
		HttpUriRequest httpUriRequest = RequestBuilder.post()
										.setHeader(jsonHeader)
										.setUri(BASE_URI+"/cgi-bin/groups/getid")
										.addParameter("access_token", access_token)
										.setEntity(new StringEntity(groupJson,Charset.forName("utf-8")))
										.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,Group.class);
	}

	/**
	 * 修改分组名
	 * @param access_token
	 * @param id 分组ID
	 * @param name	分组名
	 * @return
	 */
	public static BaseResult groupsUpdate(String access_token,String id,String name){
		String groupJson = "{\"group\":{\"id\":"+id+",\"name\":\""+name+"\"}}";
		HttpUriRequest httpUriRequest = RequestBuilder.post()
										.setHeader(jsonHeader)
										.setUri(BASE_URI+"/cgi-bin/groups/update")
										.addParameter("access_token", access_token)
										.setEntity(new StringEntity(groupJson,Charset.forName("utf-8")))
										.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,BaseResult.class);
	}

	/**
	 * 移动用户分组
	 * @param access_token
	 * @param openid
	 * @param to_groupid
	 * @return
	 */
	public static BaseResult groupsMembersUpdate(String access_token,String openid,String to_groupid){
		String groupJson = "{\"openid\":\""+openid+"\",\"to_groupid\":"+to_groupid+"}";
		HttpUriRequest httpUriRequest = RequestBuilder.post()
										.setHeader(jsonHeader)
										.setUri(BASE_URI+"/cgi-bin/groups/menbers/update")
										.addParameter("access_token", access_token)
										.setEntity(new StringEntity(groupJson,Charset.forName("utf-8")))
										.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,BaseResult.class);
	}

}
