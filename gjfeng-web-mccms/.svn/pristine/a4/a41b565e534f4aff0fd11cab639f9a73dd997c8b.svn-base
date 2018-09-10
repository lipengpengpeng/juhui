package cc.messcat.web.front;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cc.modules.constants.PageConstants;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 截取所有的URL，然后进行分发
 * 
 * @author Administrator
 * 
 */
public class NavigationAction extends ActionSupport {

	private static final long serialVersionUID = 6334885098042281744L;

	/**
	 * 栏目ID
	 */
	private String columnId;

	/**
	 * 新闻ID
	 */
	private String newsId;

	/**
	 * 页面类型
	 */
	private String pageType;

	/**
	 * 总入口然后根据相关的类型进行分发
	 */
	public String execute(){
		String navigation = PageConstants.INDEX_KEY;
		try {
			// 前台传过来的参数
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			String columnType = request.getParameter("columnType");
			this.pageType = request.getParameter("pageType");
			this.columnId = request.getParameter("columnId");
			this.newsId = request.getParameter("newsId");

			// 如果pageType为空，则与栏目类型相同
			if (pageType == null || "".equals(pageType))
				this.pageType = columnType;

			// 判断需要跳到哪个页面
			if ("mostlist".equals(columnType)) {
				navigation = PageConstants.NEWS_LIST_MORE;
			} else if ("list".equals(columnType)) {
				navigation = PageConstants.NEWS_MORE;
			} else if ("content".equals(columnType)) {
				navigation = PageConstants.CONTENT;
			} else if ("product".equals(columnType)) {
				if (newsId != null) {
					navigation = PageConstants.PRODUCT;
				} else {
					navigation = PageConstants.PRODUCT_LIST;
				}
			} else if ("other".equals(columnType)) {
				navigation = PageConstants.OTHER;
			} else if ("news".equals(columnType)) {
				navigation = PageConstants.NEWS;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return navigation;
	}

	public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

}
