package cc.messcat.gjfeng.common.wechat.popular.support;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;



import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.constant.CommonProperties;
import cc.messcat.gjfeng.common.pay.wechat.weixin.popular.api.TokenAPI;
import cc.messcat.gjfeng.common.pay.wechat.weixin.popular.bean.Token;
import cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo;

/**
 * TokenManager token 自动刷新
 * 
 * @author LiYi
 *
 */
public class TokenManager {

	private static Logger log = LoggerFactory.getLogger(TokenManager.class);

	public static Map<String, String> tokenMap = new LinkedHashMap<String, String>();

	private static Map<String, Timer> timerMap = new HashMap<String, Timer>();

	private static Map<String, Token> tokenMaps = new HashMap();

	@Autowired
	@Qualifier("memberInfoDubbo")
	private static GjfMemberInfoDubbo memberInfoDubbo;

	/**
	 * 初始化token 刷新，每118分钟刷新一次。
	 * 
	 * @param appid
	 * @param secret
	 * @throws InterruptedException
	 */
	public static void init(final String appid, final String secret) throws InterruptedException {
		if (timerMap.containsKey(appid)) {
			timerMap.get(appid).cancel();
		}
		Timer timer = new Timer(true);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				// 获取access_token
				Token token = null;
				boolean flag = false;
				int i = 0;
				do {
					try {
						if (flag) {
							log.error("TokenManager类----init方法-----获取ticket 异常，2.5s后再尝试获取,当前执行次数：" + (i++));
							Thread.sleep(2500);
						}
						token = TokenAPI.token(appid, secret);
						tokenMap.put(appid, token.getAccess_token());
						flag = true;
					} catch (Exception e) {
						log.error("TokenManager类----init方法-----获取token失败：" + e);
					}
				} while (!ObjValid.isValid(token) || ObjValid.isValid(token.getErrcode()));
				log.error("获取access_token:" + token.getAccess_token());
			}
		}, 0, 3600 * 1000);
		timerMap.put(appid, timer);
	}

	/**
	 * 获取第一个appid 的 access_token 适用于单一微信号
	 * 
	 * @param appid
	 * @return
	 */
	public static String getToken(String appid) {
		Long expired_Date = null;
		long currentTime = System.currentTimeMillis();
		Token templ = tokenMaps.get(appid);
		if (templ != null) {
			expired_Date = templ.getExpired_Date();
			if (currentTime < expired_Date)
				return templ.getAccess_token();
		}
		
		 Token tokens =
		 TokenAPI.token(CommonProperties.GJFENG_APP_ID,CommonProperties.GJFENG_APPSECRET); expired_Date = new Long(7080*1000) + currentTime;
		 tokens.setExpired_Date(expired_Date); 
		 tokenMaps.put(appid, tokens);
		 System.out.println(tokens.getErrmsg());
		 System.out.println(tokens.getErrcode());

		
		return tokens.getAccess_token();
	}

}
