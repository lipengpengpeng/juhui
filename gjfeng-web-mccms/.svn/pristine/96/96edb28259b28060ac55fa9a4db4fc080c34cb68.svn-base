package cc.modules.commons;

import javax.servlet.http.HttpServletRequest;

import cc.messcat.bases.BaseAction;
import cc.messcat.gjfeng.common.bean.Pager;

public class PageAction extends BaseAction {

	private static final long serialVersionUID = 0x97ca2171f5168088L;
	protected int pageSize;
	protected int pageNo;
	protected Pager pager;
	protected HttpServletRequest request;

	public PageAction() {
		pageSize = 10;
		pageNo = 1;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	

}