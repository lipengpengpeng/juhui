package cc.messcat.gjfeng.common.pay.wechat.weixin.popular.client;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;

import cc.messcat.gjfeng.common.constant.CommonProperties;
import cc.messcat.gjfeng.common.pay.wechat.weixin.popular.util.ClientCustomSSL;

public class LocalHttpClient {
	
	public static void main(String[] args) {
		URL url = LocalHttpClient.class.getClassLoader().getResource("apiclient_cert.p12");
		System.out.println(url);
	}

	protected static HttpClient httpClient = HttpClientFactory.createHttpClient(100,10);

	private static Map<String,HttpClient> httpClient_mchKeyStore = new HashMap<String, HttpClient>();
	public static void init(int maxTotal,int maxPerRoute){
		httpClient = HttpClientFactory.createHttpClient(maxTotal,maxPerRoute);
	}

	/**
	 * 初始化   MCH HttpClient KeyStore
	 * @param keyStoreName  keyStore 名称
	 * @param keyStoreFilePath 私钥文件路径
	 * @param mch_id
	 * @param maxTotal
	 * @param maxPerRoute
	 */
	public static void initMchKeyStore(String keyStoreName,String keyStoreFilePath,String mch_id,int maxTotal,int maxPerRoute){
		try {
			KeyStore keyStore = KeyStore.getInstance(keyStoreName);
			URL url = LocalHttpClient.class.getClassLoader().getResource("apiclient_cert.p12");
			 FileInputStream instream = null;
			try {
				instream = new FileInputStream(new File(url.toURI()));
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 keyStore.load(instream,mch_id.toCharArray());
			 instream.close();
			 HttpClient httpClient = HttpClientFactory.createKeyMaterialHttpClient(keyStore, mch_id, new String[]{"TLSv1"}, maxTotal, maxPerRoute);
			 httpClient_mchKeyStore.put(mch_id, httpClient);
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static HttpResponse execute(HttpUriRequest request){
		try {
			return httpClient.execute(request);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T execute(HttpUriRequest request,ResponseHandler<T> responseHandler){
		try {
			return httpClient.execute(request, responseHandler);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 数据返回自动JSON对象解析
	 * @param request
	 * @param clazz
	 * @return
	 */
	public static <T> T executeJsonResult(HttpUriRequest request,Class<T> clazz){
		return execute(request,JsonResponseHandler.createResponseHandler(clazz));
	}

	/**
	 * 数据返回自动XML对象解析
	 * @param request
	 * @param clazz
	 * @return
	 */
	public static <T> T executeXmlResult(HttpUriRequest request,Class<T> clazz){
		return execute(request,XmlResponseHandler.createResponseHandler(clazz));
	}

	/**
	 * MCH keyStore 请求 数据返回自动XML对象解析
	 * @param mch_id
	 * @param request
	 * @param clazz
	 * @return
	 */
	public static <T> T keyStoreExecuteXmlResult(String mch_id,HttpUriRequest request,Class<T> clazz){
		try {
//			KeyStore keyStore = KeyStore.getInstance("PKCS12");
//			InputStream url = ClassLoader.getSystemResourceAsStream("apiclient_cert.p12"); 
//			InputStream url = LocalHttpClient.class.getResourceAsStream("apiclient_cert.p12");
//			URL url = LocalHttpClient.class.getClassLoader().getResource("apiclient_cert.p12");
			
			KeyStore keyStore  = KeyStore.getInstance("PKCS12");
	        URL url2 = ClientCustomSSL.class.getClassLoader().getResource("apiclient_cert.p12");
	        URI uri = null;
			try {
				uri = url2.toURI();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        FileInputStream instream = new FileInputStream(new File(uri));//P12文件目录
	        try {
	            keyStore.load(instream, mch_id.toCharArray());
	        } finally {
	            instream.close();
	        }
			 HttpClient httpClient = HttpClientFactory.createKeyMaterialHttpClient(keyStore, mch_id, new String[]{"TLSv1"}, 1000, 100);
			 return httpClient.execute(request,XmlResponseHandler.createResponseHandler(clazz));
//			return httpClient_mchKeyStore.get(mch_id).execute(request,XmlResponseHandler.createResponseHandler(clazz))
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		}
		return null;
	}
}
