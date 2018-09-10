package cc.messcat.gjfeng.common.wechat.popular.api;


import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Value;

public abstract class BaseAPI {
	
	protected static final String BASE_URI = "https://api.weixin.qq.com";
	protected static final String MEDIA_URI = "http://file.api.weixin.qq.com";
	protected static final String QRCODE_DOWNLOAD_URI = "https://mp.weixin.qq.com";
	protected static final String MCH_URI = "https://api.mch.weixin.qq.com";
	protected static final String OPEN_URI = "https://open.weixin.qq.com";
	
	protected static Header jsonHeader = new BasicHeader(HttpHeaders.CONTENT_TYPE,ContentType.APPLICATION_JSON.toString());
	protected static Header xmlHeader = new BasicHeader(HttpHeaders.CONTENT_TYPE,ContentType.APPLICATION_XML.toString());
	
	protected static final String REQ_MESSAGE_TYPE_EVENT = "event";  
	  
    /** 
     * 事件类型：subscribe(关注) 
     */  
	protected static final String EVENT_TYPE_SUBSCRIBE = "subscribe";  
	  
    /** 
     * 事件类型：unsubscribe(取消关注) 
     */  
	protected static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";  
	  
    /** 
     * 事件类型：CLICK(自定义菜单点击事件) 
     */  
	protected static final String EVENT_TYPE_CLICK = "CLICK";
}
