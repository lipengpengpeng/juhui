package cc.messcat.web.front;

import java.util.ArrayList;
import java.util.List;

import cc.messcat.entity.EnterpriseColumn;
import cc.messcat.entity.EnterpriseNews;
import cc.modules.constants.PageConstants;
import cc.modules.util.CollectionUtil;
import cc.modules.util.CommonDownload;
import cc.modules.util.PropertiesFileReader;

/**
 * 处理并获取新闻页面的数据及属性
 * 
 * @author Administrator
 * 
 */
public class EpFrontNewsAction extends FrontAction {

	private static final long serialVersionUID = 2441570372422185617L;

	/**
	 * 新闻
	 */
	private EnterpriseNews enterpriseNews;
	private EnterpriseNews perNews;//上一条新闻
	private EnterpriseNews newxNews;//下一条新闻

	/**
	 * 新闻外链
	 */
	private String linkUrl;

	/**
	 * 选中的栏目
	 */
	private EnterpriseColumn selectColumn;
	
	/**
	 * 新闻页面入口
	 */
	public String execute() throws Exception {
		super.init();
		this.contentToNews();
		String result = this.news();
		
		// 前台传过来的页面类型
		if(super.selectPageType != null && !"".equals(super.selectPageType)){
			//处理特殊页面的特殊需求
			if("pro_news".equals(super.selectPageType)){
				result = this.proNews();
			} else if ("login".equals(super.selectPageType)) {
				result = this.login();
			} else if ("register".equals(super.selectPageType)) {
				result = this.register();
			} else if ("iwantsell".equals(super.selectPageType)) {
				result = this.iwantsell();
			} else if("basicInfo".equals(super.selectPageType)) {
				result = this.basicInfo();
			} else if("service_center".equals(super.selectPageType)) {
				result = this.service_center();
			}
		}
		return result;
	}

	private String service_center() {
		// TODO Auto-generated method stub
		return "service_center";
	}

	private String basicInfo() {
		// TODO Auto-generated method stub
		return "basicInfo";
	}

	private String iwantsell() {
		// TODO Auto-generated method stub
		return "iwantsell";
	}

	/**
	 * 查看一条新闻页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private String news() throws Exception {

		// 前台传过来的新闻ID
		if (super.selectNewsId == null) {
			throw new Exception("新闻ID没有了！");
		}

		this.enterpriseNews = super.epNewsManagerDao.getEpNews(super.selectNewsId);
		if (this.enterpriseNews == null) {
			return PageConstants.ERROR_KEY;
		}

		this.selectColumn = super.epColumnManagerDao.getEnterpriseColumn(this.enterpriseNews.getEnterpriseColumn().getId());
		
		// 设置菜单
		super.findSelectFirstLevelColumn(this.enterpriseNews.getEnterpriseColumn().getId());

		// 设置页面上的当前位置
		super.findLocation();

		// 判断是否外链新闻
		String otherLink = this.enterpriseNews.getOtherUrl();
		if (otherLink != null && !"".equals(otherLink)) {
			this.linkUrl = otherLink;
			return "linkUrl";
		}

		// 判断是否文件新闻
		if (enterpriseNews.getIsPrimPhoto() != null && "2".equals(enterpriseNews.getIsPrimPhoto()) && "news".equals(super.selectPageType)) {
			String rootPath = PropertiesFileReader.get("upload.file.path", "/app.properties");
			String fileName = enterpriseNews.getFileInfo();
			CommonDownload cd = new CommonDownload();
			cd.downloadFile(fileName, rootPath);
			return null;
		}
		for(EnterpriseColumn col : super.location) {
			if(col.getId().intValue() == selectColumn.getFather().intValue()) {
				List<EnterpriseNews> newsList = epNewsManagerDao.findNewsByFaterColumn(col.getId());
				if(CollectionUtil.isListNotEmpty(newsList)) {
					//正确排序，倒序
					List<EnterpriseNews> newsList0=new ArrayList<EnterpriseNews>();
					int index = newsList.size();
					for(EnterpriseNews news0 : newsList) {
						newsList0.add(newsList.get(index-1));
						if(index!=0)index--;
					}
					
					int size = newsList.size();
					int select = 0;
					for(EnterpriseNews news0 : newsList0) {
						if(news0.getId().intValue() == enterpriseNews.getId().intValue()) {
							break;
						}
						select += 1;
					}
					if(select - 1 >= 0)
						this.perNews = newsList0.get(select-1);
					if(select + 1 < size)
						this.newxNews = newsList0.get(select+1);
				}
			}
		}

		return PageConstants.NEWS;
	}
	
	/**
	 * 根据栏目ID获取到新闻ID
	 */
	private void contentToNews(){
		if(super.selectNewsId == null && super.selectColumnId != null){
			EnterpriseNews epNews = this.epNewsManagerDao.getEpNewsByColumnId(super.selectColumnId);
			super.selectNewsId = epNews.getId().longValue();
		}
	}
	
	private String proNews() throws Exception {
		return "pro_news";
	}
	private String login() throws Exception {
		return "login";
	}
	private String register() throws Exception {
		return "register";
	}

	public EnterpriseNews getEnterpriseNews() {
		return enterpriseNews;
	}

	public void setEnterpriseNews(EnterpriseNews enterpriseNews) {
		this.enterpriseNews = enterpriseNews;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public EnterpriseColumn getSelectColumn() {
		return selectColumn;
	}

	public void setSelectColumn(EnterpriseColumn selectColumn) {
		this.selectColumn = selectColumn;
	}

	public EnterpriseNews getPerNews() {
		return perNews;
	}

	public void setPerNews(EnterpriseNews perNews) {
		this.perNews = perNews;
	}

	public EnterpriseNews getNewxNews() {
		return newxNews;
	}

	public void setNewxNews(EnterpriseNews newxNews) {
		this.newxNews = newxNews;
	}

}
