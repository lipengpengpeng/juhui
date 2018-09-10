package cc.messcat.web.system;

import cc.messcat.entity.WebSite;
import cc.modules.commons.PageAction;
import cc.modules.constants.Constants;

public class WebSiteAction extends PageAction {

	private static final long serialVersionUID = -887654596052275857L;
	private WebSite webSite;
	private Long id;
	private String names;
	private String title;
	private String domain;
	private String port;
	private String company;
	private String copyright;
	private String recodeCode;
	private String htmlName;
	public boolean showMesscat = false;

	public WebSiteAction() {
		webSite = new WebSite();
	}

	/**
	 * 查询系统配置信息
	 */
	public String execute() throws Exception {
		showMesscat = false;
		try {
			webSite = webSiteManagerDao.getWebSite();
			if (webSite != null)
				webSite.setRecodeCode(webSite.getRecodeCode().trim());

		} catch (Exception e) {
			addActionMessage("查询错误,请重新输入!");
			return Constants.INPUT;
		}
		return Constants.SUCCESS;
	}

	/**
	 * 修改系统配置信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		clearMessages();
		Long defaultSkin = webSiteManagerDao.getWebSite().getDefaultSkin();
		webSite.setDefaultSkin(defaultSkin);
		try {
			if (webSite.getId() == null)
				webSiteManagerDao.addWebSite(webSite);
			else
				webSiteManagerDao.updateWebSite(webSite);
		} catch (Exception e) {
			addActionMessage("输入错误,请重新输入!");
			e.printStackTrace();
			return Constants.INPUT;
		}
		showMesscat = true;
		addActionMessage("修改配置信息成功!");
		//更新application里面的webSite信息
		/*webSite = webSiteManagerDao.getWebSite();
		if (webSite != null)
			webSite.setRecodeCode(webSite.getRecodeCode().trim());*/
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

	public WebSite getWebSite() {
		return webSite;
	}

	public void setWebSite(WebSite webSite) {
		this.webSite = webSite;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getRecodeCode() {
		return recodeCode;
	}

	public void setRecodeCode(String recodeCode) {
		this.recodeCode = recodeCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getHtmlName() {
		return htmlName;
	}

	public void setHtmlName(String htmlName) {
		this.htmlName = htmlName;
	}

}