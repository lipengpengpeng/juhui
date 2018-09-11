package cc.messcat.gjfeng.config;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cc.messcat.gjfeng.common.constant.CommonProperties;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dubbo.webclient.GjfWeixinInfoDubbo;
import cc.messcat.gjfeng.entity.GjfWeiXinPayInfo;

/**
 * 微信公众号配置信息
 * 
 * @author Jimmy
 *
 */
public class WaixiConfig implements InitializingBean  {

	private static Logger log = Logger.getLogger(WaixiConfig.class);
	/**
	 * 微信公众号信息
	 */
	private String GJFENG_APP_ID;
	private String GJFENG_APPSECRET;
	private String GJFENG_PARTNER;
	private String GJFENG_PARTNER_KEY;
	/**
	 * H5
	 */
	private String GJFENG_H5_MERID;
	private String GJFENG_H5_KEY;
	@Autowired
	@Qualifier("weixinInfoDubbo")
	private GjfWeixinInfoDubbo weixinInfoDubbo;
	private static WaixiConfig me;

	public static String GJFENG_APP_ID() {
		return me.GJFENG_APP_ID;
	}

	public static String GJFENG_APPSECRET() {
		return me.GJFENG_APPSECRET;
	}

	public static String GJFENG_PARTNER() {
		return me.GJFENG_PARTNER;
	}

	public static String GJFENG_PARTNER_KEY() {
		return me.GJFENG_PARTNER_KEY;
	}

	public static String GJFENG_H5_MERID() {
		return me.GJFENG_H5_MERID;
	}

	public static String GJFENG_H5_KEY() {
		return me.GJFENG_H5_KEY;
	}

	/**
	 * 重读配置信息,修改了配置都应该调用一下。1.应用启动，2.修改配置配置，3.启用配置信息
	 */
	public static void reload() {
		log.info("重读配置");
		reloadWeixinPublicNumber();
		reloadH5();
	}

	/**
	 * 重读微信公众号配置
	 */
	private static void reloadWeixinPublicNumber() {
		ResultVo resultVo = me.weixinInfoDubbo.getUsingWeixinPublicNumberInfo();
		GjfWeiXinPayInfo publicNumberInfo = (GjfWeiXinPayInfo) resultVo.getResult();
		if (publicNumberInfo != null) {
			me.GJFENG_APP_ID = publicNumberInfo.getMchId();
			me.GJFENG_APPSECRET = publicNumberInfo.getPayKey();
			me.GJFENG_PARTNER = publicNumberInfo.getPartner();
			me.GJFENG_PARTNER_KEY = publicNumberInfo.getPartnerKey();
		}
	}

	/**
	 * 重读h5配置
	 */
	private static void reloadH5() {
		ResultVo resultVo = me.weixinInfoDubbo.getUsingH5Info();
		GjfWeiXinPayInfo h5Info = (GjfWeiXinPayInfo) resultVo.getResult();
		if (h5Info != null) {
			me.GJFENG_H5_MERID = h5Info.getMchId();
			me.GJFENG_H5_KEY = h5Info.getPayKey();
		}
	}

	protected static HttpClient httpClient = HttpClientFactory.createHttpClient(100, 10);

	/**
	 * 请求微信客户端项目重读配置
	 */
	public static void requestClientReload() {
		String url = CommonProperties.CLIENT_PROJECT_URL + "/wx/WechatConfig/reload";
		HttpPost httpPost = new HttpPost(url);
		try {
			httpClient.execute(httpPost);
		} catch (ClientProtocolException e) {
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		me = this;
		reload();
	}

}
