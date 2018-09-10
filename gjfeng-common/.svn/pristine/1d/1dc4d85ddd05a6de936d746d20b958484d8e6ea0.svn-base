package cc.messcat.gjfeng.common.fastpay.payForOther;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import cc.messcat.gjfeng.common.fastpay.payForOther.MyKeyStoreUtil.KeyStoreType;



public class HttpClient {
	private String requestUrl;
	private int decryptTimeout;
	private String keyStore;
	private String keyStorePwd;
	private String trustStore;
	private String trustStorePwd;
	
	public HttpClient(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	
	public HttpClient(String requestUrl, int decryptTimeout, String keyStore, String keyStorePwd, String trustStore, String trustStorePwd) {
		this.requestUrl = requestUrl;
		this.decryptTimeout = decryptTimeout;
		this.keyStore = keyStore;
		this.keyStorePwd = keyStorePwd;
		this.trustStore = trustStore;
		this.trustStorePwd = trustStorePwd;
	}
	
	public String submitUrl(String reqStr) {
		if (requestUrl.startsWith("https")) {
			return https(reqStr);
		} else {
			return http(reqStr);
		}
	}
	
	public String http(String reqStr) {
		String encoding = "UTF-8";
		String result = "";
		HttpURLConnection httpURLConnection = null;
		URL url;
		try {
			url = new URL(requestUrl);
			httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setConnectTimeout(decryptTimeout);
			httpURLConnection.setReadTimeout(decryptTimeout);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=" + encoding);
			httpURLConnection.setRequestMethod("POST");
			
			PrintStream out = null;
			try {
				httpURLConnection.connect();
				out = new PrintStream(httpURLConnection.getOutputStream(), false, encoding);
				out.print(reqStr);
				out.flush();
			} catch (Exception e) {
				throw e;
			} finally {
				if (out != null) {
					out.close();
				}
			}

			InputStream in = null;
			StringBuilder sb = new StringBuilder(1024);
			BufferedReader br = null;
			String temp = null;
			try {
				if (200 == httpURLConnection.getResponseCode()) {
					in = httpURLConnection.getInputStream();
					br = new BufferedReader(new InputStreamReader(in, encoding));
					while ((temp = br.readLine()) != null) {
						sb.append(temp);
					}
				} else {
					in = httpURLConnection.getErrorStream();
					br = new BufferedReader(new InputStreamReader(in, encoding));
					while ((temp = br.readLine()) != null) {
						sb.append(temp);
					}
				}
				result = sb.toString();
				
			} catch (Exception e) {
				throw e;
			} finally {
				if (br != null) {
					br.close();
				}
				if (in != null) {
					in.close();
				}
				if (httpURLConnection != null) {
					httpURLConnection.disconnect();
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			result = "连接超时";
		}
		return result;
	}
	
	public String https(String reqStr) {
//		System.setProperty("javax.net.ssl.keyStore", config.getKeyStore());
//      System.setProperty("javax.net.ssl.keyStorePassword", config.getKeyStorePassword());
//      System.setProperty("javax.net.ssl.keyStoreType", "JKS");
//        
//		System.setProperty("javax.net.ssl.trustStore", config.getTrustStore());
//		System.setProperty("javax.net.ssl.trustStorePassword", config.getTrustStorePassword());
		
		HttpsURLConnection.setDefaultHostnameVerifier(new TrustAnyVerifier());
		String encoding = "GBK";
		String result = "";
		HttpsURLConnection httpURLConnection = null;
		URL url;
		try {
			url = new URL(requestUrl);
			httpURLConnection = (HttpsURLConnection) url.openConnection();
			httpURLConnection.setSSLSocketFactory(getSSLSocketFactory());
			
			httpURLConnection.setConnectTimeout(decryptTimeout);
			httpURLConnection.setReadTimeout(decryptTimeout);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=" + encoding);
			httpURLConnection.setRequestMethod("POST");
			
			PrintStream out = null;
			try {
				httpURLConnection.connect();
				out = new PrintStream(httpURLConnection.getOutputStream(), false, encoding);
				out.print(reqStr);
				out.flush();
			} catch (Exception e) {
				throw e;
			} finally {
				if (out != null) {
					out.close();
				}
			}

			InputStream in = null;
			StringBuilder sb = new StringBuilder(1024);
			BufferedReader br = null;
			String temp = null;
			try {
				if (200 == httpURLConnection.getResponseCode()) {
					in = httpURLConnection.getInputStream();
					br = new BufferedReader(new InputStreamReader(in, encoding));
					while ((temp = br.readLine()) != null) {
						sb.append(temp);
					}
				} else {
					in = httpURLConnection.getErrorStream();
					br = new BufferedReader(new InputStreamReader(in, encoding));
					while ((temp = br.readLine()) != null) {
						sb.append(temp);
					}
				}
				result = sb.toString();
				
			} catch (Exception e) {
				throw e;
			} finally {
				if (br != null) {
					br.close();
				}
				if (in != null) {
					in.close();
				}
				if (httpURLConnection != null) {
					httpURLConnection.disconnect();
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			result = "���ӳ�ʱ";
		}
		return result;
	}
	
	public SSLSocketFactory getSSLSocketFactory() {
        //MyKeyManager keyManager = new MyKeyManager(KeyStoreType.PKCS12, keyStore, keyStorePwd.toCharArray());
		MyKeyManager keyManager = new MyKeyManager(KeyStoreType.JKS, keyStore, keyStorePwd.toCharArray());
        MyTrustManager trustManager = new MyTrustManager(trustStore, trustStorePwd.toCharArray());
        MySSLContext context = new MySSLContext("SSLv3", keyManager, trustManager);
        return context.getSSLContext().getSocketFactory();
    }
}
