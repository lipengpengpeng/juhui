package cc.messcat.gjfeng.common.wechat.popular.api;


import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import cc.messcat.gjfeng.common.pay.wechat.weixin.popular.bean.Token;
import cc.messcat.gjfeng.common.pay.wechat.weixin.popular.client.LocalHttpClient;


public class TokenAPI extends BaseAPI{

	/**
	 * 获取access_token
	 * @param appid
	 * @param secret
	 * @return
	 */
	public static Token token(String appid,String secret){
		HttpUriRequest httpUriRequest = RequestBuilder.get()
				.setUri(BASE_URI + "/cgi-bin/token")
				.addParameter("grant_type","client_credential")
				.addParameter("appid", appid)
				.addParameter("secret", secret)
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,Token.class);
	}
}
