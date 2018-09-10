package cc.messcat.gjfeng.common.pay;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class clientUtil {
	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException {
//		sendPost();
		sendGet();
//		uriRequset();
	}
	
	public static void uriRequset() throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpUriRequest requset = RequestBuilder.post()
				.setUri("http://www.baidu.com/")
				.addHeader(new BasicHeader(HttpHeaders.CONTENT_TYPE, ContentType.TEXT_HTML.toString()))
				.build();
		CloseableHttpResponse response = httpClient.execute(requset);
		System.out.println(response.toString());
	}
	
	public static void sendGet(){
		CloseableHttpClient httpClient = getClient();
		HttpGet httpGet = new HttpGet("http://www.baidu.com/");
		try {
			CloseableHttpResponse  response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();	
			System.out.println("------------------------------");
			System.out.println("status:"+response.getStatusLine());
			if(entity!=null){
				System.out.println("content:"+EntityUtils.toString(entity));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				httpClient.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
	}
	
	public static void sendPost(){
		CloseableHttpClient httpClient = getClient();
//		HttpUriRequest request = RequestBuilder.post()
//				.addHeader(new BasicHeader(HttpHeaders.CONTENT_TYPE,org.apache.http.entity.ContentType.TEXT_HTML.toString()))
//				.setUri("http://10.0.0.25/alipay.trade.wap.pay-java-utf-8/return.jsp")
//				.addParameter("out_trade_no","201789113633640")
//				.addParameter("trade_status", "1")
//				.addParameter("trade_no", "2017080921001004670200180362")
//				.build();
		HttpPost httppost = new HttpPost("http://10.0.0.25/alipay.trade.wap.pay-java-utf-8/return.jsp");
		httppost.addHeader("User-Agent","Mozilla/5.0");
//		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
//        urlParameters.add(new BasicNameValuePair("userName", "Pankaj Kumar"));

		List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
        formparams.add(new BasicNameValuePair("out_trade_no", "house"));  
        formparams.add(new BasicNameValuePair("trade_no", "trade_no"));  
        formparams.add(new BasicNameValuePair("trade_status", "trade_status"));  
        UrlEncodedFormEntity uefEntity;  
        CloseableHttpResponse response = null;
        try {  
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");  
            httppost.setEntity(uefEntity);  
            System.out.println("executing request " + httppost.getURI());  
            response = httpClient.execute(httppost);  
            try {  
                HttpEntity entity = response.getEntity();  
                if (entity != null) {  
                    System.out.println("--------------------------------------");  
                    System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));  
                    System.out.println("--------------------------------------");  
                }  
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e1) {  
            e1.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            // 关闭连接,释放资源    
            try {  
            	httpClient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        System.out.println(response.toString());
		System.out.println("完成");
		System.out.println(response.toString());
	}
	
	private static CloseableHttpClient getClient(){
		return HttpClients.createDefault();
	}
	
}
