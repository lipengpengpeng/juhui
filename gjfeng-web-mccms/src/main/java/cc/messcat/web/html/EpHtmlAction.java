// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov Date: 2010-5-13
// 14:43:11
// Home Page : http://members.fortunecity.com/neshkov/dj.html - Check often for
// new version!
// Decompiler options: packimports(3)
// Source File Name: EpHtmlAction.java

package cc.messcat.web.html;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import cc.messcat.entity.Authorities;
import cc.messcat.entity.EnterpriseColumn;
import cc.messcat.entity.EnterpriseNews;
import cc.messcat.entity.WebSite;
import cc.messcat.entity.WebSkin;
import cc.messcat.entity.WebSkinColor;
import cc.messcat.front.IndexInfoBean;
import cc.messcat.service.system.WebSiteManagerDao;
import cc.modules.commons.MesscatPage;
import cc.modules.commons.PageAction;
import cc.modules.constants.Constants;
import cc.modules.util.Const;
import cc.modules.util.FtpUpload;
import cc.modules.util.StaticFreemarker;

import com.opensymphony.xwork2.ActionContext;

/**
 * @author handsomeWU
 * 
 */
public class EpHtmlAction extends PageAction {

	private static final long serialVersionUID = 1L;
	private static final String SLASH = Constants.FILE_SEPARATOR;
	private StaticFreemarker sf;
	private WebSiteManagerDao webSiteManagerDao;
	private WebSite webSite;
	private Map map;
	private String message;
	private List columnlist;
	private List indexlist;
	private List linklist;
	private List adlist;
	private String tempColumnId;
	private String epColumnId;
	private String columnName;
	private List subColumnList;
	private String subColumnId;
	private String subColumnName;
	private List<IndexInfoBean> newslist;

	public boolean showMesscat;

	private WebSkin webSkin;
	private WebSkinColor webSkinColor;
	/**
	 * 是否开启静态网页
	 */
	private String htmlState;

	/**
	 * 搜索时使用
	 */
	private String title;

	/**
	 * FTP上传
	 */
	private FtpUpload ftpUpload;
	private String uploadPara;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public EpHtmlAction() {
		message = "";
		columnlist = new ArrayList();
		indexlist = new ArrayList();
		linklist = new ArrayList();
		subColumnList = new ArrayList();
		sf = new StaticFreemarker();
	}

	public String execute() throws Exception {
		this.showMesscat = false;
		findColumns();
		return Constants.SUCCESS;
	}

	/**
	 * 显示操作成功信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateExecute() throws Exception {
		String result = "";
		result = execute();
		showMesscat = true;
		return result;
	}

	/**
	 * 模糊查询新闻内容
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String query() throws Exception {

		map = (Map) ActionContext.getContext().getSession();

		webSite = webSiteManagerDao.getWebSite();
		this.setWebSkin(this.webSkinManagerDao.getWebSkinById(this.webSite.getDefaultSkin()));
		if (this.getWebSkin() == null)
			return "input";
		this.setWebSkinColor(this.webSkinColorManagerDao.getWebSkinColorByWebSkinId(this.getWebSkin().getId()));

		this.map.put("ctx", webSite.getDomain());

		// currentUserName为路径
		this.map.put("currentUserName", "admin");

		this.map.put("webSkinColor", this.getWebSkinColor());

		this.map.put("webSite", webSite);
		findColumns();
		findFrontModules();
		findLinks(map);

		IndexInfoBean index;
		for (Iterator iterator = indexlist.iterator(); iterator.hasNext(); this.map.put(index.getEnterpriseColumn().getFrontNum()
			.toString(), index))
			index = (IndexInfoBean) iterator.next();

		// 检测title的有效性，如果为sql注入
		if (!Const.voidSQL(title)) {
			this.map.put("searchNews", null);
			this.map.put("title", "广州讯猫软件告诉你, 请不要作出无谓的抵抗!");

		} else {
			this.map.put("searchNews", this.epNewsManagerDao.findNews(title));
			this.map.put("title", title);
		}

		return Constants.SUCCESS;
	}

	/**
	 * 查询是否开启静态网页
	 * 
	 * @return
	 * @throws Exception
	 */
	public String setHtml() throws Exception {

		// 针对用户名进行建立html页面
		// String currentUserName =
		// (String)ServletActionContext.getContext().getSession().get("currentUserName");
		clearMessages();
		showMesscat = false;

		String path = ServletActionContext.getServletContext().getRealPath(Constants.FILE_SEPARATOR) + "index.html";

		File file = new File(path);
		if (file.exists())
			htmlState = "1";
		else
			htmlState = "0";

		return "setHtml";
	}

	public String setHtmlByName() throws Exception {
		// String currentUserName =
		// (String)ServletActionContext.getContext().getSession().get("currentUserName");

		clearMessages();
		showMesscat = false;

		// name = 1 则 开启静态网页
		if ("1".equals(htmlState)) {
			this.addActionMessage("开启静态网页成功");
		} else {
			// name = 0 则 删除所有静态网页
			String path = ServletActionContext.getServletContext().getRealPath(Constants.FILE_SEPARATOR) + "index.html";

			File file = new File(path);
			if (file.exists()) {
				try {
					file.delete();
				} catch (Exception e) {
					return "setHtml";
				}
			}
			this.addActionMessage("成功关闭静态网页");
		}
		this.showMesscat = true;
		return "setHtml";
	}

	/**
	 * 初始化必要的数据
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void init() throws Exception {

		// 清除消息
		this.clearMessages();
		this.showMesscat = false;

		// 清除缓存
		if (this.map != null) {
			this.map.clear();
		}
		this.map = new HashMap();

		// 获取站点信息和主题
		this.webSite = this.webSiteManagerDao.getWebSite();
		if (this.webSite != null) {
			this.webSkin = this.webSkinManagerDao.getWebSkinById(this.webSite.getDefaultSkin());
			if (this.webSkin != null) {
				this.webSkinColor = this.webSkinColorManagerDao.getWebSkinColorByWebSkinId(this.webSkin.getId());
				this.map.put("webSkinColor", this.webSkinColor);
			}
		}
		this.map.put("webSite", this.webSite);

		// 获取栏目
		this.findColumns();
		this.map.put("columnlist", this.columnlist);

		// 获取链接
		this.findLinks(this.map);
		this.map.put("linklist", this.linklist);

		// 设置一级栏目id
		this.map.put("fatherColumnId", this.epColumnId);

	}

	/**
	 * 建立主页
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String createIndex() throws Exception {

		// 初始化
		this.init();

		// 获取主页全部栏目
		this.findFrontModules();

		// 获取广告
		this.findAds(this.map);

		// 显示flash
		this.map.put("index_flash", 1);

		this.createIndexHtml();

		// 清除flash
		this.map.remove("index_flash");

		// 清除map
		this.map.clear();

		return Constants.DO_SUCCESS;
	}

	@SuppressWarnings("unchecked")
	private void createIndexHtml() throws Exception {

		IndexInfoBean index;
		for (Iterator iterator = this.indexlist.iterator(); iterator.hasNext();) {
			index = (IndexInfoBean) iterator.next();
			this.map.put(index.getEnterpriseColumn().getFrontNum().toString(), index);
			index = null;
		}
		try {
			this.sf.init(this.webSite, "templates"+Constants.FILE_SEPARATOR + this.webSite.getWebSkin().getFilename(), "index.ftl", "", Constants.FILE_SEPARATOR+"index.html",
				this.map);
			this.addActionMessage("生成主页"+Constants.FILE_SEPARATOR+"index.html 成功!");
		} catch (Exception e) {
			this.addActionMessage("主页"+Constants.FILE_SEPARATOR+"index.html生成失败!");
		}
	}

	@SuppressWarnings("unchecked")
	public String createMore() throws Exception {

		// 初始化
		this.init();

		// 显示flash
		this.map.put("index_flash", 1);

		// 生成该栏目的所有news页面
		this.createNewsHtml(this.epColumnId);

		// 生成该栏目的新闻列表页面
		this.createMoreHtml(this.epColumnId);

		// 清除flash
		this.map.remove("index_flash");

		// 清除map
		this.map.clear();

		this.addActionMessage("成功生成所有页面(不包括显示在上面的错误页面)");

		return Constants.DO_SUCCESS;

	}

	/**
	 * 建立新闻more页面
	 * 
	 * @param columnId
	 *            一级栏目
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void createMoreHtml(String columnId) throws Exception {

		// 获取栏目以及栏目名
		EnterpriseColumn column = epColumnManagerDao.getEnterpriseColumn(Long.valueOf(columnId));
		if (column == null || !"list".equals(column.getTypeColumn().getTemplateType())) {
			throw new Exception("你到底搞了什么？栏目id居然都给弄丢了！");
		}
		this.columnName = column.getNames();
		this.map.put("htmlFile", columnId);

		// 如果是一级或二级栏目，生成路径有所不一样。
		String packageName = this.webSite.getHtmlName() + Constants.FILE_SEPARATOR + column.getId();
		if (column.getFather().longValue() != 0) {
			packageName = this.webSite.getHtmlName() + Constants.FILE_SEPARATOR + this.epColumnId + Constants.FILE_SEPARATOR + column.getId();

			// 设置二级栏目名
			this.map.put("subColumnName", column.getNames());
		} else {
			this.map.put("columnName", this.columnName);
			this.map.put("fatherColumnId", null);
		}

		// 生成该栏目所有分页的页面
		List allNewsByColumn = this.epNewsManagerDao.findNews(column.getId());
		this.map.put("totalNumber", Integer.valueOf(allNewsByColumn.size()));

		final int pageNum = 20;
		this.map.put("perPageNumber", Integer.valueOf(pageNum));
		List eachPageNewsList = MesscatPage.splitPageNews(allNewsByColumn, pageNum);
		if (eachPageNewsList != null) {

			this.map.put("endPage", Integer.valueOf(eachPageNewsList.size() - 1));

			for (int i = 0; i < eachPageNewsList.size(); i++) {
				this.map.put("resultList", eachPageNewsList.get(i));
				this.map.put("pageIndex", Integer.valueOf(i));
				String htmlPath = Constants.FILE_SEPARATOR + column.getId() + "_" + i + ".html";
				try {
					this.sf.init(this.webSite, "templates"+Constants.FILE_SEPARATOR + this.webSite.getWebSkin().getFilename(), "news_more.ftl",
						packageName, htmlPath, this.map);
				} catch (Exception e) {
					this.addActionMessage("生成页面:" + packageName + htmlPath + " 失败!");
				}
			}
		} else {
			this.map.put("resultList", null);
			String htmlName = Constants.FILE_SEPARATOR + column.getId() + "_0.html";
			try {
				this.sf.init(this.webSite, "templates"+Constants.FILE_SEPARATOR + this.webSite.getWebSkin().getFilename(), "news_more.ftl", packageName,
					htmlName, this.map);
			} catch (Exception e) {
				this.addActionMessage("生成页面:" + packageName + htmlName + " 失败!");
			}
		}

		// 保存栏目的路径
		Authorities authorities = this.authoritiesManagerDao.getByName(column.getNames());
		if (authorities != null) {
			authorities.setDisplayName(column.getNames());
			authorities.setName(column.getNames());
			column.setHtmlName(Constants.FILE_SEPARATOR + packageName + Constants.FILE_SEPARATOR + column.getId() + "_0.html");
			epColumnManagerDao.updateEnterpriseColumn(column, authorities);
		}
	}

	/**
	 * 建立新闻 Most List 页面
	 */
	@SuppressWarnings("unchecked")
	public String createMostList() throws Exception {

		// 初始化
		this.init();

		// 显示flash
		this.map.put("index_flash", 1);

		// 生成该栏目的所有news页面
		this.createNewsHtml(this.epColumnId);

		// 生成该栏目的多模块列表页面
		this.createMostListHtml(this.epColumnId);

		// 清除flash
		this.map.remove("index_flash");

		this.addActionMessage("成功生成所有页面(不包括显示在上面的错误页面)");

		return Constants.DO_SUCCESS;

	}

	/**
	 * 建立新闻 Most List 页面
	 * 
	 * index.html username/columnId/columnId_i.html
	 * username/columnId/subColumnId/subColumnId_i.html
	 * packageName/pageName.html
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void createMostListHtml(String epColumnId) throws Exception {

		if ("151".equals(epColumnId)) {
			System.out.println();
		}

		// 根据栏目id获取栏目对象并设置栏目
		EnterpriseColumn column = this.epColumnManagerDao.getEnterpriseColumn(Long.valueOf(epColumnId));
		if (column == null || !"mostlist".equals(column.getTypeColumn().getTemplateType())) {
			throw new Exception("你到底搞了什么？栏目id居然都给弄丢了！");
		}

		// 设置栏目名
		this.columnName = column.getNames();
		this.map.put("columnName", this.columnName);

		// 设置子栏目列表
		this.subColumnList = this.epColumnManagerDao.findSubColumn(column.getId());
		this.map.put("subColumnList", this.subColumnList);

		// 获取子栏目的新闻
		this.newslist = new ArrayList<IndexInfoBean>();
		Long subColumnId = 0L;
		List<EnterpriseNews> newsList = null;
		List<EnterpriseNews> newsPhotoList = null;
		EnterpriseColumn subColumn = null;
		IndexInfoBean indexInfoBean = null;
		for (Iterator iter = this.subColumnList.iterator(); iter.hasNext();) {
			indexInfoBean = new IndexInfoBean();
			subColumn = (EnterpriseColumn) iter.next();
			subColumnId = subColumn.getId();
			newsList = this.epNewsManagerDao.findEpNewsByFrontNum(7L, subColumn);
			newsPhotoList = this.epNewsManagerDao.findPhotoNews(subColumnId);
			indexInfoBean.setEnterpriseColumn(subColumn);
			indexInfoBean.setEnterpriseNewsList(newsList);
			indexInfoBean.setEnterprisePhotoNewsList(newsPhotoList);
			this.newslist.add(indexInfoBean);
			newsList = null;
			newsPhotoList = null;
			subColumn = null;
			indexInfoBean = null;
		}
		this.map.put("newslist", this.newslist);

		// 生成一级栏目most list html
		String packageName = this.webSite.getHtmlName() + Constants.FILE_SEPARATOR + column.getId();

		// 页面路径
		String htmlName = Constants.FILE_SEPARATOR + column.getId() + "_0.html";

		// 生成页面
		try {
			this.sf.init(this.webSite, "templates"+Constants.FILE_SEPARATOR + this.webSite.getWebSkin().getFilename(), "news_list_more.ftl", packageName,
				htmlName, this.map);
		} catch (Exception e) {
			addActionMessage("生成页面:" + packageName + htmlName + " 失败!");
		}

		// 保存栏目的路径
		Authorities authorities = this.authoritiesManagerDao.getByName(column.getNames());
		if (authorities != null) {
			authorities.setDisplayName(column.getNames());
			authorities.setName(column.getNames());
			column.setHtmlName(Constants.FILE_SEPARATOR + packageName + htmlName);
			epColumnManagerDao.updateEnterpriseColumn(column, authorities);
		}

		// 生成所有子栏目是list的html
		if (this.subColumnList != null && !this.subColumnList.isEmpty()) {
			for (Iterator itr = subColumnList.iterator(); itr.hasNext();) {
				subColumn = (EnterpriseColumn) itr.next();
				if (subColumn != null) {
					if ("list".equals(subColumn.getTypeColumn().getTemplateType())) {
						this.createMoreHtml(String.valueOf(subColumn.getId()));
					}
					subColumn = null;
				}
			}
		}

	}

	/**
	 * 根据传进来的栏目ID，生成这个栏目以及这个栏目的子栏目的所有的网页
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String createNews() throws Exception {

		// 初始化
		this.init();

		this.createNewsHtml(this.epColumnId);

		return Constants.DO_SUCCESS;

	}

	/**
	 * 建立栏目的新闻页面
	 * 
	 * @param epColumnId
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void createNewsHtml(String epColumnId) throws Exception {

		// 获取栏目
		EnterpriseColumn column = this.epColumnManagerDao.getEnterpriseColumn(Long.valueOf(epColumnId));
		this.map.put("columnName", column.getNames());
		this.map.put("fatherColumnId", column.getId());

		// 获取子栏目列表
		this.subColumnList = this.epColumnManagerDao.findSubColumn(column.getId());
		this.map.put("subColumnList", this.subColumnList);

		// 设置路径
		final String packageName = this.webSite.getHtmlName() + Constants.FILE_SEPARATOR + epColumnId;

		// 如果该一级栏目是content类型的，而且有二级栏目，则将该一级栏目链接到第一个子栏目
		if ("content".equals(column.getTypeColumn().getTemplateType())) {

			// 没有二级栏目则直接生成html
			if (this.subColumnList == null || this.subColumnList.isEmpty()) {
				String htmlName = Constants.FILE_SEPARATOR + epColumnId + "_0.html";
				EnterpriseNews news = this.epNewsManagerDao.getEpNewsByColumnId(column.getId());

				this.createNewsHtml(packageName, htmlName, news, column);

			} else {
				EnterpriseColumn subColumn = null;

				// 获取第一个二级栏目的news，然后给一级栏目建立一个拷贝
				subColumn = (EnterpriseColumn) this.subColumnList.get(0);
				String htmlName = Constants.FILE_SEPARATOR + epColumnId + "_0.html";

				// 释放每一条新闻所占用的内存
				if (this.map.containsKey("news")) {
					this.map.put("news", null);
				}
				EnterpriseNews firstNews = this.epNewsManagerDao.getEpNewsByColumnId(subColumn.getId());
				this.map.put("news", firstNews);

				try {
					this.sf.init(this.webSite, "templates"+Constants.FILE_SEPARATOR + this.webSite.getWebSkin().getFilename(), "news.ftl", packageName,
						htmlName, this.map);
				} catch (Exception e) {
					this.addActionMessage("生成页面:" + packageName + htmlName + " 失败!");
				}

				// 遍历二级栏目并生成所有的news
				for (Iterator itr = this.subColumnList.iterator(); itr.hasNext();) {
					subColumn = (EnterpriseColumn) itr.next();
					if (subColumn != null) {
						this.map.put("subColumnName", subColumn.getNames());

						EnterpriseNews news = this.epNewsManagerDao.getEpNewsByColumnId(subColumn.getId());

						String subColumnPackageName = packageName + Constants.FILE_SEPARATOR + subColumn.getId();
						htmlName = Constants.FILE_SEPARATOR + subColumn.getId() + "_0.html";

						this.createNewsHtml(subColumnPackageName, htmlName, news, subColumn);

						news = null;
						subColumn = null;
					}
				}
			}

		} else {

			String newPackageName = this.webSite.getHtmlName();
			this.createNewsHtmlWithNewsList(newPackageName, column);

			EnterpriseColumn subColumn = null;
			for (Iterator itr = this.subColumnList.iterator(); itr.hasNext();) {
				subColumn = (EnterpriseColumn) itr.next();
				if (subColumn != null) {
					this.map.put("subColumnName", subColumn.getNames());

					this.createNewsHtmlWithNewsList(packageName, subColumn);

					subColumn = null;
				}
			}
		}
		addActionMessage("成功生成所有页面(不包括显示在上面的错误页面)");
	}

	@SuppressWarnings("unchecked")
	private void createNewsHtmlWithNewsList(String packageName, EnterpriseColumn column) {
		List columnNewsList = this.epNewsManagerDao.findNews(column.getId());
		if (columnNewsList != null && !columnNewsList.isEmpty()) {
			EnterpriseNews news = null;
			for (Iterator columnNewsItr = columnNewsList.iterator(); columnNewsItr.hasNext();) {
				news = (EnterpriseNews) columnNewsItr.next();
				String newPackageName = packageName + Constants.FILE_SEPARATOR + column.getId();
				String htmlName = Constants.FILE_SEPARATOR + column.getId() + "_" + news.getId() + "_news.html";
				if ("content".equals(column.getTypeColumn().getTemplateType())) {
					htmlName = Constants.FILE_SEPARATOR + column.getId() + "_0.html";
				}

				this.createNewsHtml(newPackageName, htmlName, news, column);

				news = null;
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void createNewsHtml(String packageName, String htmlName, EnterpriseNews news, EnterpriseColumn column) {
		try {
			// 释放每一条新闻所占用的内存
			if (this.map.containsKey("news")) {
				this.map.put("news", null);
			}
			this.map.put("news", news);

			this.sf.init(this.webSite, "templates"+Constants.FILE_SEPARATOR + this.webSite.getWebSkin().getFilename(), "news.ftl", packageName, htmlName,
				this.map);

			news.setHtmlName(Constants.FILE_SEPARATOR + packageName + htmlName);
			news.setEnterpriseColumn(column);
			this.epNewsManagerDao.updateEpNews(news);
		} catch (Exception e) {
			this.addActionMessage("生成页面:" + packageName + htmlName + " 失败!");
		}
	}

	/**
	 * 生成所有的静态页面
	 */
	@SuppressWarnings("unchecked")
	public String createWeb() throws Exception {

		// 获取栏目
		List allColumnlist = this.epColumnManagerDao.findSubColumn(0L);

		// 遍历所有一级栏目，然后生成html
		EnterpriseColumn column;
		Iterator iterator = allColumnlist.iterator();
		while (iterator.hasNext()) {
			column = (EnterpriseColumn) iterator.next();
			this.epColumnId = String.valueOf(column.getId());
			if ("content".equals(column.getTypeColumn().getTemplateType())) {
				this.createNews();
			} else if ("list".equals(column.getTypeColumn().getTemplateType())) {
				this.createMore();
			} else if ("mostlist".equals(column.getTypeColumn().getTemplateType())) {
				this.createMostList();
			}
		}

		this.createIndex();

		return Constants.DO_SUCCESS;

	}

	/**
	 * 查看所有的一级栏目
	 * 
	 * @throws Exception
	 */
	public void findColumns() throws Exception {
		this.columnlist = this.epColumnManagerDao.findSubColumn(0L);
	}

	public void findFrontModules() throws Exception {
		this.indexlist = this.epColumnManagerDao.findFrontInfoFrontNumNotNull();
	}

	/**
	 * 查找所有的友情链接和按照标识查找友情链接
	 * 
	 * @param map
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void findLinks(Map map) throws Exception {
		this.linklist = this.epLinksManagerDao.getEpLinksByClassAndSize(Long.valueOf(100L));
		if (map == null)
			return;
		List<String> tempLinkList = this.epLinksManagerDao.getDistinctFrontNum();
		for (String temp : tempLinkList) {
			this.map.put(temp, this.epLinksManagerDao.findAllByFrontNum(temp));
		}
	}

	/**
	 * 查找所有广告和按照标识查找广告
	 * 
	 * @param map
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void findAds(Map map) throws Exception {
		// adlist = epAdManagerDao.getEpAdByClassAndSize(Long.valueOf(100L));
		if (map == null)
			return;
		List<String> tempAdList = this.epAdManagerDao.getDistinctFrontNum();
		for (String temp : tempAdList) {
			this.map.put(temp, this.epAdManagerDao.findAllByFrontNum(temp));
		}
	}

	/**
	 * FTP上传html到服务器
	 * 
	 * @return
	 * @throws Exception
	 */
	public String ftpUpload() throws Exception {

		final String rootPath = ServletActionContext.getServletContext().getRealPath(SLASH);

		// 获取站点信息
		this.webSite = this.webSiteManagerDao.getWebSite();
		final String webFolder = this.webSite.getHtmlName();
		final String parentFolderForHtml = rootPath + webFolder;

		String uploadPath = "";

		// 主页
		if ("index".equals(this.uploadPara)) {
			uploadPath = rootPath + "index.html";
			this.ftpUpload.ftpUpload(uploadPath, null);
		}

		// 网站地图
		else if ("webmap".equals(this.uploadPara)) {
			uploadPath = parentFolderForHtml + SLASH + "webmap.html";
			this.ftpUpload.ftpUpload(uploadPath, webFolder);
		}

		// 全站
		else if ("web".equals(this.uploadPara)) {

			// 先上传index.html
			uploadPath = rootPath + "index.html";
			this.ftpUpload.ftpUpload(uploadPath, null);

			uploadPath = parentFolderForHtml;
			this.ftpUpload.ftpUpload(uploadPath, null);
		}

		// 用户上传的资料或图片，也就是upload文件夹
		else if ("upload".equals(this.uploadPara)) {
			uploadPath = rootPath + "upload";
			this.ftpUpload.ftpUpload(uploadPath, null);
		}

		// 用户上传的文件，也就是userfiles文件夹
		else if ("userfiles".equals(this.uploadPara)) {
			uploadPath = rootPath + "userfiles";
			this.ftpUpload.ftpUpload(uploadPath, null);
		}

		// 其它所有栏目
		else {
			uploadPath = parentFolderForHtml + SLASH + this.uploadPara;
			this.ftpUpload.ftpUpload(uploadPath, webFolder);
		}

		return this.execute();

	}

	public StaticFreemarker getSf() {
		return sf;
	}

	public void setSf(StaticFreemarker sf) {
		this.sf = sf;
	}

	public List getColumnlist() {
		return columnlist;
	}

	public void setColumnlist(List columnlist) {
		this.columnlist = columnlist;
	}

	public List getIndexlist() {
		return indexlist;
	}

	public void setIndexlist(List indexlist) {
		this.indexlist = indexlist;
	}

	public List getLinklist() {
		return linklist;
	}

	public void setLinklist(List linklist) {
		this.linklist = linklist;
	}

	public String getEpColumnId() {
		return epColumnId;
	}

	public void setEpColumnId(String epColumnId) {
		this.epColumnId = epColumnId;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getSubColumnId() {
		return subColumnId;
	}

	public void setSubColumnId(String subColumnId) {
		this.subColumnId = subColumnId;
	}

	public String getSubColumnName() {
		return subColumnName;
	}

	public void setSubColumnName(String subColumnName) {
		this.subColumnName = subColumnName;
	}

	public List getSubColumnList() {
		return subColumnList;
	}

	public void setSubColumnList(List subColumnList) {
		this.subColumnList = subColumnList;
	}

	public String getTempColumnId() {
		return tempColumnId;
	}

	public void setTempColumnId(String tempColumnId) {
		this.tempColumnId = tempColumnId;
	}

	public WebSiteManagerDao getWebSiteManagerDao() {
		return webSiteManagerDao;
	}

	public void setWebSiteManagerDao(WebSiteManagerDao webSiteManagerDao) {
		this.webSiteManagerDao = webSiteManagerDao;
	}

	public WebSite getWebSite() {
		return webSite;
	}

	public void setWebSite(WebSite webSite) {
		this.webSite = webSite;
	}

	public String getHtmlState() {
		return htmlState;
	}

	public void setHtmlState(String htmlState) {
		this.htmlState = htmlState;
	}

	public WebSkin getWebSkin() {
		return webSkin;
	}

	public void setWebSkin(WebSkin webSkin) {
		this.webSkin = webSkin;
	}

	public WebSkinColor getWebSkinColor() {
		return webSkinColor;
	}

	public void setWebSkinColor(WebSkinColor webSkinColor) {
		this.webSkinColor = webSkinColor;
	}

	public List getAdlist() {
		return adlist;
	}

	public void setAdlist(List adlist) {
		this.adlist = adlist;
	}

	public boolean isShowMesscat() {
		return showMesscat;
	}

	public void setShowMesscat(boolean showMesscat) {
		this.showMesscat = showMesscat;
	}

	public FtpUpload getFtpUpload() {
		return ftpUpload;
	}

	public void setFtpUpload(FtpUpload ftpUpload) {
		this.ftpUpload = ftpUpload;
	}

	public String getUploadPara() {
		return uploadPara;
	}

	public void setUploadPara(String uploadPara) {
		this.uploadPara = uploadPara;
	}

}