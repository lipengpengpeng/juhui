package cc.messcat.gjfeng.common.fastpay.payForOther;

import java.security.KeyStore;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;

import cc.messcat.gjfeng.common.fastpay.payForOther.MyKeyStoreUtil.KeyStoreType;




public class MyKeyManager {

	private KeyStore ks;
	private char[] password;

	public MyKeyManager(String keyStore, char[] password) {
		this(KeyStoreType.JKS, keyStore, password);
	}

	public MyKeyManager(KeyStoreType type, String keyStore, char[] password) {
		this.password = password;
		this.ks = MyKeyStoreUtil.loadKeyStore(type, keyStore, password);
	}

	public KeyManager[] getKeyManagers() {
		try {
			KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
			kmf.init(ks, password);
			return kmf.getKeyManagers();
		} catch (Exception e) {	
			e.printStackTrace();
			//throw new KeyStoreRuntimeException("cannot get KeyManagers", e);
		}
		return null;
	}
}
