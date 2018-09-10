package cc.messcat.web.front;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import cc.messcat.entity.EnterpriseAd;
import cc.messcat.entity.EnterpriseColumn;
import cc.messcat.entity.Standby;
import cc.messcat.entity.WebSite;
import cc.messcat.front.IndexInfoBean;
import cc.modules.commons.PageAction;
import cc.modules.constants.PageConstants;
import cc.modules.util.CollectionUtil;
import cc.modules.util.StringUtil;

/**
 * 处理并获取所有页面的数据及属性
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("unchecked")
public class FrontAction extends PageAction {

	private static final long serialVersionUID = 7966893419851841748L;

	/**
	 * 全局模块信息，核心变量
	 */
	public Map map;

	/**
	 * 所有的一级栏目以及它们底下的所有子栏目列表
	 */
	public List<EnterpriseColumn> columns;

	/**
	 * 获取当前站点信息
	 */
	public WebSite webSite;

	/**
	 * 导航信息，这个map只用在页面顶部的导航上(top.jsp)
	 */
	public Map topMap;

	/**
	 * 选中的栏目ID
	 */
	public Long selectColumnId;

	/**
	 * 选中的栏目的一级栏目，适用于非首页的右边菜单(right.jsp)
	 */
	public EnterpriseColumn selectFirstLevelColumn;

	/**
	 * 选中的栏目的二级栏目，适用于非首页的右边菜单(right.jsp)，用于标上绿色并展开该栏目
	 */
	public Long selectSecondLevelId;

	/**
	 * 选中的栏目的三级栏目，适用于非首页的右边菜单(right.jsp)，用于标上黑色
	 */
	public Long selectThirdLevelId;

	/**
	 * 选中的新闻ID
	 */
	public Long selectNewsId;

	/**
	 * 当前位置(location.jsp)
	 */
	public List<EnterpriseColumn> location;

	/**
	 * 选中的页面类型
	 */
	public String selectPageType;

	/**
	 * 公共信息管理表
	 */
	private Standby standbyList;

	/**
	 * 页面所有前端模块
	 */
	private List<IndexInfoBean> indexList;
	
	public String message;//错误提示

	/**
	 * 子类覆盖该方法
	 */
	public String execute() throws Exception {
		return PageConstants.INDEX_KEY;
	}

	/**
	 * 初始化所有公共所需的信息或模块
	 * 
	 * @throws Exception
	 */
	public void init() throws Exception {

		if (this.map == null) {
			this.map = new HashMap();
		}
		if (this.location == null) {
			this.location = new ArrayList<EnterpriseColumn>();
		}
		this.findFrontMudoles();
		//this.findWebSite();
		this.findAllColumns();
		//this.findLinks();
		this.findAds();
		//this.findStandBy();
		this.findColumnIdFromFront();
		this.findNewsIdFromFront();
		this.findPageTypeFromFront();
	}

	/**
	 * 获取当前站点的所有栏目
	 * 
	 * @return
	 */
	void findAllColumns() {
		List<EnterpriseColumn> allColumns = super.epColumnManagerDao.findAllEnterpriseColumn();
		this.columns = this.convertColumn(allColumns, null);

		// 这里把主栏目的信息放入导航map里，更好地给前台处理
		if (CollectionUtil.isListNotEmpty(this.columns)) {
			if (this.topMap == null) {
				this.topMap = new HashMap();
			}
			for (EnterpriseColumn col : this.columns) {
				this.topMap.put(col.getFrontNum(), col);
			}
		}
	}

	/**
	 * 递归把子栏目列表放入相应的父栏目里
	 * 
	 * @param allColumns
	 * @param fatherColumn
	 * @return
	 */
	private List<EnterpriseColumn> convertColumn(List<EnterpriseColumn> allColumns, EnterpriseColumn fatherColumn) {
		List<EnterpriseColumn> columnList = new ArrayList<EnterpriseColumn>();

		if (CollectionUtil.isListNotEmpty(allColumns)) {
			for (EnterpriseColumn col : allColumns) {
				if (fatherColumn == null) {
					if (col.getFather().longValue() == 0) {
						col.setSubColumnList(this.convertColumn(allColumns, col));
						columnList.add(col);
					}
				} else {
					if (col.getFather().longValue() == fatherColumn.getId().longValue()) {
						col.setSubColumnList(this.convertColumn(allColumns, col));
						columnList.add(col);
					}
				}
			}
		}

		return columnList;
	}

	/**
	 * 查找所有的标示为非空的栏目模块,包括了栏目，与栏目新闻
	 * 
	 * @throws Exception
	 */
	private void findFrontMudoles() throws Exception {
		this.indexList = super.epColumnManagerDao.findFrontInfoFrontNumNotNull();

		// 把所有非空的栏目模块放入到map里
		if (CollectionUtil.isListNotEmpty(this.indexList)) {
			for (Iterator iterator = this.indexList.iterator(); iterator.hasNext();) {
				IndexInfoBean index = (IndexInfoBean) iterator.next();
				if (index.getEnterpriseColumn() != null) {
					this.map.put(index.getEnterpriseColumn().getFrontNum(), index);
				}
			}
		}
	}

	/**
	 * 根据标识,查看友情链接信息
	 * 
	 * @throws Exception
	 */
	private void findLinks() throws Exception {
		List<String> linkList = super.epLinksManagerDao.getDistinctFrontNum();
		if (CollectionUtil.isListNotEmpty(linkList)) {
			for (String link : linkList) {
				this.map.put(link, super.epLinksManagerDao.findAllByFrontNum(link));
			}
		}
		linkList = null;
	}

	/**
	 * 查找所有广告和按照标识查找广告
	 * 
	 * @throws Exception
	 */
	private void findAds() throws Exception {
		List<String> adList = super.epAdManagerDao.getDistinctFrontNum();
		List<EnterpriseAd> tempAdLists = null;
		List<EnterpriseAd> adLists = new ArrayList<EnterpriseAd>();
		if (CollectionUtil.isListNotEmpty(adList)) {
			for (String ad : adList) {
				tempAdLists = super.epAdManagerDao.findAllByFrontNum(ad);
				 if(tempAdLists!=null && tempAdLists.size()>0){
					 for(EnterpriseAd e:tempAdLists){
						 adLists.add(e);
					 }
				 }
//				this.map.put(ad, super.epAdManagerDao.findAllByFrontNum(ad));
			}
			this.map.put("ad", adLists);
		}
	}

	/**
	 * 查找当前站点信息
	 * 
	 * @throws Exception
	 */
	private void findWebSite() throws Exception {
		this.webSite = super.webSiteManagerDao.getWebSite();
	}

	/**
	 * 查找备用字段表
	 * 
	 * @throws Exception
	 * @author Andy Lin
	 */
	private void findStandBy() throws Exception {
		standbyList = this.standbyManagerDao.getFirstEntity();
	}

	/**
	 * 根据选中的栏目ID来获取一级栏目
	 * 
	 * @throws Exception
	 */
	void findSelectFirstLevelColumn(Long id) {
		this.selectFirstLevelColumn = this.findSelectFirstLevelColumn(id, this.columns, null);
	}

	/**
	 * 1. 根据选中的栏目ID来获取一级栏目
	 * 
	 * 2. 填充location
	 * 
	 * @throws Exception
	 */
	private EnterpriseColumn findSelectFirstLevelColumn(Long id, List<EnterpriseColumn> columnList, EnterpriseColumn column) {
		if (CollectionUtil.isListNotEmpty(columnList)) {
			int size = columnList.size();
			for (int i = 0; i < size; i++) {
				EnterpriseColumn col = (EnterpriseColumn) columnList.get(i);
				if (column == null) {
					if (id.longValue() == col.getId().longValue()) {
						column = col;
						this.location.add(col);
						break;
					} else {
						column = this.findSelectFirstLevelColumn(id, col.getSubColumnList(), column);
					}
				} else {
					if (col.getFather().longValue() == 0) {
						EnterpriseColumn selectFirstLevelColumn = (EnterpriseColumn) columnList.get(i - 1);
						this.location.add(selectFirstLevelColumn);
						return selectFirstLevelColumn;
					} else {
						EnterpriseColumn selectPreviousLevelColumn = (EnterpriseColumn) columnList.get(i - 1);
						this.location.add(selectPreviousLevelColumn);
					}
					break;
				}
				if (i == size - 1) {
					if (column != null) {
						column = col;
						this.location.add(col);
						break;
					}
				}
			}
		}
		return column;
	}

	/**
	 * 当前位置的处理
	 * 
	 * @throws Exception
	 */
	void findLocation() {
		if (CollectionUtil.isListNotEmpty(this.location)) {
			List<EnterpriseColumn> newLocation = new ArrayList<EnterpriseColumn>();
			int size = this.location.size();
			for (int i = size - 1; i >= 0; i--) {
				EnterpriseColumn col = this.location.get(i);
				newLocation.add(col);
				if (i == (size - 2)) {
					this.selectSecondLevelId = col.getId();
				} else if (i == (size - 3)) {
					this.selectThirdLevelId = col.getId();
				}
			}
			this.location = newLocation;
		}
	}

	/**
	 * 从前台获取到栏目ID
	 */
	private void findColumnIdFromFront() {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		String columnId = request.getParameter("columnId");
		if (StringUtil.isNotBlank(columnId)) {
			this.selectColumnId = Long.parseLong(columnId);
		} else {
			this.selectColumnId = null;
		}
	}

	/**
	 * 从前台获取到新闻ID
	 */
	private void findNewsIdFromFront() {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		String newsId = request.getParameter("newsId");
		if (StringUtil.isNotBlank(newsId)) {
			this.selectNewsId = Long.parseLong(newsId);
		} else {
			this.selectNewsId = null;
		}
	}

	/**
	 * 从前台获取到页面类型
	 */
	private void findPageTypeFromFront() {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		String pageType = request.getParameter("pageType");
		if (StringUtil.isNotBlank(pageType)) {
			this.selectPageType = pageType;
		} else {
			this.selectPageType = null;
		}
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public List<EnterpriseColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<EnterpriseColumn> columns) {
		this.columns = columns;
	}

	public WebSite getWebSite() {
		return webSite;
	}

	public void setWebSite(WebSite webSite) {
		this.webSite = webSite;
	}

	public Map getTopMap() {
		return topMap;
	}

	public void setTopMap(Map topMap) {
		this.topMap = topMap;
	}

	public Long getSelectColumnId() {
		return selectColumnId;
	}

	public void setSelectColumnId(Long selectColumnId) {
		this.selectColumnId = selectColumnId;
	}

	public EnterpriseColumn getSelectFirstLevelColumn() {
		return selectFirstLevelColumn;
	}

	public void setSelectFirstLevelColumn(EnterpriseColumn selectFirstLevelColumn) {
		this.selectFirstLevelColumn = selectFirstLevelColumn;
	}

	public Long getSelectNewsId() {
		return selectNewsId;
	}

	public void setSelectNewsId(Long selectNewsId) {
		this.selectNewsId = selectNewsId;
	}

	public Long getSelectSecondLevelId() {
		return selectSecondLevelId;
	}

	public void setSelectSecondLevelId(Long selectSecondLevelId) {
		this.selectSecondLevelId = selectSecondLevelId;
	}

	public Long getSelectThirdLevelId() {
		return selectThirdLevelId;
	}

	public void setSelectThirdLevelId(Long selectThirdLevelId) {
		this.selectThirdLevelId = selectThirdLevelId;
	}

	public List<EnterpriseColumn> getLocation() {
		return location;
	}

	public void setLocation(List<EnterpriseColumn> location) {
		this.location = location;
	}

	public String getSelectPageType() {
		return selectPageType;
	}

	public void setSelectPageType(String selectPageType) {
		this.selectPageType = selectPageType;
	}

	public Standby getStandbyList() {
		return standbyList;
	}

	public void setStandbyList(Standby standbyList) {
		this.standbyList = standbyList;
	}

	public List<IndexInfoBean> getIndexList() {
		return indexList;
	}

	public void setIndexList(List<IndexInfoBean> indexList) {
		this.indexList = indexList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
