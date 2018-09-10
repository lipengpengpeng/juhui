package cc.modules.util;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts2.ServletActionContext;

/**
 * 方便前端集成开发的mtag标签
 * 用于统一页面填写链接地址
 * @author Andyxixi
 *
 */

public class FrontLink extends TagSupport {

	private static final long serialVersionUID = -6493316332924012246L;
	private static final String NEWS_URL = "/navigation.htm?columnType=news&newsId=";
	private static final String PRO_URL = "/navigation.htm?columnType=product&newsId=";
	private static final String COLUMN_ID = "&columnId=";
	private static final String PAGE_TYPE = "&pageType=";
	
	public static String newsLink(String newsId) {
		return newsLink(newsId, null);
	}

	public static String newsLink(String newsId, String pageType) {

		StringBuffer sb = new StringBuffer();
		String ctx = ServletActionContext.getRequest().getContextPath();
		
		//组装字符串
		sb.append(ctx).append(NEWS_URL).append(newsId);
		if(pageType != null && !"".equals(pageType)){
			sb.append(PAGE_TYPE).append(pageType);
		}

		return sb.toString();

	}
	
	public static String proLink(String colId, String proId) {
		return proLink(colId, proId, null);
	}
	
	public static String proLink(String colId, String proId, String pageType) {
		
		StringBuffer sb = new StringBuffer();
		String ctx = ServletActionContext.getRequest().getContextPath();
		
		//组装字符串
		sb.append(ctx).append(PRO_URL).append(proId).append(COLUMN_ID).append(colId);
		if(pageType != null && !"".equals(pageType)){
			sb.append(PAGE_TYPE).append(pageType);
		}
		
		return sb.toString();
		
	}
}
