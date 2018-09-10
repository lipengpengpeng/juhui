package cc.messcat.web.front;

import java.util.List;

import cc.messcat.entity.EnterpriseColumn;
import cc.messcat.entity.EnterpriseNews;
import cc.modules.constants.ActionConstants;
import cc.modules.constants.PageConstants;

/**
 * 处理并获取新闻列表页面所有的数据及属性
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("unchecked")
public class EpNewsListMoreAction extends FrontAction {

	private static final long serialVersionUID = -5754036333272247675L;

	/**
	 * 选中的栏目
	 */
	private EnterpriseColumn selectColumn;
	
	//分页查询新闻列表
	private List<EnterpriseNews> enterpriseNewsList;
	
	private String title;
	
	/**
	 * 需跳转的栏目Link
	 */
	private String colLink;

	/**
	 * 首页入口
	 */
	public String execute() throws Exception {
		super.init();
		this.newsListMoreInit();
		String result = PageConstants.NEWS_LIST_MORE;

		// 前台传过来的页面类型
		if (super.selectPageType != null && !"".equals(super.selectPageType)) {
			// 处理特殊页面的特殊需求
			if ("showcol".equals(super.selectPageType)) {
				result = "news_list";
			}
			else if("colList".equals(super.selectPageType)){
				result = this.colList();
			}
			else if("carBbs".equals(super.selectPageType)){
				result = this.carBbs();
			}
		}
		return result;
	}

	/**
	 * 模板列表listmore页面
	 * 
	 * @return
	 * @throws Exception
	 */
	private void newsListMoreInit() throws Exception {

		// 前台传过来的栏目ID
		if (super.selectColumnId == null) {
			throw new Exception("栏目ID没有了，你自己看着办吧！");
		}

		this.selectColumn = super.epColumnManagerDao.getEnterpriseColumn(super.selectColumnId);

		// 设置菜单
		super.findSelectFirstLevelColumn(super.selectColumnId);

		// 设置页面上的当前位置
		super.findLocation();

	}

	/**
	 * 展示第一个子栏目（直到叶节点）
	 * 
	 * @return
	 */
	private String showcol() throws Exception {
		EnterpriseColumn leafColumn = this.findLeafColumn(this.selectColumn);
		if(leafColumn == this.selectColumn){
			throw new Exception("死循环啦，改改你的pageType吧！");
		}
		this.colLink = leafColumn.getLinkUrl();
		return "showcol";
	}
	
	/**
	 * 递归找出当前栏目的在叶节点上的第一个子栏目
	 * @param column
	 * @return
	 */
	private EnterpriseColumn findLeafColumn(EnterpriseColumn column){
		if(column.getSubColumnList() != null && column.getSubColumnList().size() > 0){
			return this.findLeafColumn(column.getSubColumnList().get(0));
		}else{
			return column;
		}
	}
	
	private String colList() throws Exception {

		EnterpriseNews temp = new EnterpriseNews();

		EnterpriseColumn enterpriseColumn = new EnterpriseColumn();
		if (title != null)
			temp.setTitle(title);
		
		enterpriseColumn.setId(selectColumn.getId());
		enterpriseColumn.setState("-1");
		temp.setEnterpriseColumn(enterpriseColumn);

		/**
		 * This line code will throw the java.lang.OutOfMemoryError: Java
		 * heap space
		 */
		//每页显示20条新闻
		super.pageSize = 20;
		super.pager = epNewsManagerDao.findEpNews(super.pageSize, pageNo, temp);

		return "col_list";
	}
	
	private String carBbs() throws Exception {
		
		return "car_bbs";
	}

	public EnterpriseColumn getSelectColumn() {
		return selectColumn;
	}

	public void setSelectColumn(EnterpriseColumn selectColumn) {
		this.selectColumn = selectColumn;
	}

	public String getColLink() {
		return colLink;
	}

	public void setColLink(String colLink) {
		this.colLink = colLink;
	}

	public List<EnterpriseNews> getEnterpriseNewsList() {
		return enterpriseNewsList;
	}

	public void setEnterpriseNewsList(List<EnterpriseNews> enterpriseNewsList) {
		this.enterpriseNewsList = enterpriseNewsList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
