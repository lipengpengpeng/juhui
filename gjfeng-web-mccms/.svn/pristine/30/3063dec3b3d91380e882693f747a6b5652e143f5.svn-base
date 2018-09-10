package cc.messcat.web.front;

import java.util.List;

import cc.messcat.entity.EnterpriseColumn;
import cc.messcat.entity.EnterpriseNews;
import cc.modules.constants.PageConstants;

/**
 * 处理并获取新闻列表页面所有的数据及属性
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("unchecked")
public class EpNewsMoreAction extends FrontAction {

	private static final long serialVersionUID = -5754036333272247675L;

	/**
	 * 栏目的新闻列表
	 */
	private List<EnterpriseNews> newsList;

	/**
	 * 选中的栏目
	 */
	private EnterpriseColumn selectColumn;

	/**
	 * 首页入口
	 */
	public String execute() throws Exception {
		super.init();
		this.newsMoreInit();
		String result = PageConstants.NEWS_MORE;

		// 前台传过来的页面类型
		if (super.selectPageType != null && !"".equals(super.selectPageType)) {
			if ("list".equals(super.selectPageType)) {
				this.newsMore();
			}
			// 处理特殊页面的特殊需求
			else if ("picture".equals(super.selectPageType)) {
				result = this.picture();
			}
		}
		return result;
	}

	/**
	 * 新闻栏目more页面
	 * 
	 * @return
	 * @throws Exception
	 */
	private void newsMoreInit() throws Exception {

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
	 * 普通新闻列表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	private void newsMore() throws Exception {

		// 设置查询条件
		EnterpriseNews news = new EnterpriseNews();
		news.setState("1");
		news.setEnterpriseColumn(this.selectColumn);

		// 设置分页
		super.pageSize = 19;
		super.pager = super.epNewsManagerDao.findEpNews(super.pageSize, super.pageNo, news);
		this.newsList = super.pager.getResultList();

	}

	/**
	 * 图片新闻列表页面
	 * 
	 * @return
	 */
	private String picture() throws Exception {
		
		// 设置查询条件
		EnterpriseNews news = new EnterpriseNews();
		news.setState("1");
		news.setIsPrimPhoto("1");
		news.setEnterpriseColumn(this.selectColumn);

		// 设置分页
		super.pageSize = 8;
		super.pager = super.epNewsManagerDao.findEpNews(super.pageSize, super.pageNo, news);
		this.newsList = super.pager.getResultList();
		
		return "picture";
	}

	public List<EnterpriseNews> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<EnterpriseNews> newsList) {
		this.newsList = newsList;
	}

	public EnterpriseColumn getSelectColumn() {
		return selectColumn;
	}

	public void setSelectColumn(EnterpriseColumn selectColumn) {
		this.selectColumn = selectColumn;
	}

}
