package cc.messcat.gjfeng.common.fastpay.payForOther;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import org.apache.log4j.Logger;

public class TrustAnyVerifier implements HostnameVerifier {
	protected final Logger logger = Logger.getLogger(getClass());
	
	public boolean verify(String hostname, SSLSession session) {
		logger.debug(">>> " + hostname + " " + session);
		return true;
	}
}
