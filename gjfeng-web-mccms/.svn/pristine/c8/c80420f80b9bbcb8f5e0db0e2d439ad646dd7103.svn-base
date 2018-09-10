// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov Date: 2010-5-13
// 14:52:12
// Home Page : http://members.fortunecity.com/neshkov/dj.html - Check often for
// new version!
// Decompiler options: packimports(3)
// Source File Name: UsersAction.java

package cc.messcat.web.style;

import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cc.modules.commons.PageAction;
import cc.messcat.entity.WebSite;
import cc.messcat.entity.WebSkin;
import cc.messcat.entity.WebSkinColor;

public class WebSkinAction extends PageAction {

	private static final long serialVersionUID = -7073119857735063044L;

	private List webSkinList;

	private WebSite webSite;

	private WebSkin webSkin;

	private List webSkinColorList;

	private Long id;
	private String names;
	private String filename;
	private String content;

	private Long webSkinColorId;

	public Long getWebSkinColorId() {
		return webSkinColorId;
	}

	public void setWebSkinColorId(Long webSkinColorId) {
		this.webSkinColorId = webSkinColorId;
	}

	/**
	 * 查询所有模板信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String execute() throws Exception {
		super.pager = this.webSkinManagerDao.findWebSkin(pageSize, pageNo, "-1");
		webSkinList = super.pager.getResultList();

		// 验证哪一个是默认的模板
		webSite = this.webSiteManagerDao.getWebSite();

		return "success";
	}

	/**
	 * 查询模板信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		this.setWebSkin(this.webSkinManagerDao.getWebSkinById(id));
		return "view";
	}

	/**
	 * 跳到修改模板页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		this.setWebSkin(this.webSkinManagerDao.getWebSkinById(id));
		return "edit";
	}

	/**
	 * 修改模板信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		initWebsite();
		this.webSkinManagerDao.updateWebSkin(webSkin);
		return "do_success";
	}

	/**
	 * 跳转到添加模板页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add_page() throws Exception {
		return "add_page";
	}

	/**
	 * 添加模板
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		initWebsite();
		webSkinManagerDao.addWebSkin(webSkin);

		return "do_success";
	}

	/**
	 * 删除模板
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		webSkinManagerDao.deleteWebSkin(id);
		return "do_success";
	}

	/**
	 * 修改站点使用的模板
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateDefaultSkin() throws Exception {
		webSite = this.webSiteManagerDao.getWebSite();
		webSite.setDefaultSkin(id);
		this.webSiteManagerDao.updateWebSite(webSite);
		return "do_success";
	}

	public void initWebsite() {
		webSkin.setNames(names);
		webSkin.setContent(content);
		webSkin.setFilename(filename);
	}

	public String checkName() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String names = request.getParameter("names");
		String orgName = request.getParameter("orgName");
		names = URLDecoder.decode(names, "UTF-8");
		orgName = URLDecoder.decode(orgName, "UTF-8");
		if (webSkinManagerDao.isNameUnique(names, orgName))
			return renderText("true");
		else
			return renderText("false");
	}

	public String style() throws Exception {
		// this.setWebSkinList(this.webSkinManagerDao.findWebSkin());
		//	    
		// this.setWebSite(this.webSiteManagerDao.getWebSite());
		//    	
		// for(WebSkin skin : (List<WebSkin>)this.getWebSkinList()){
		// skin.setState("0");
		// for(Long id : this.webSite.getWebSkinIds()){
		// if(skin.getId().equals(id)){
		// skin.setState("1");
		// }
		// }
		// }

		this.webSite = this.webSiteManagerDao.getWebSite();
		this.setWebSkin(this.webSkinManagerDao.getWebSkinById(this.webSite.getDefaultSkin()));

		if (this.getWebSkin() == null)
			return "input";

		this.setWebSkinColorList(this.webSkinColorManagerDao.findWebSkinColor(this.webSkin.getId()));

		return "style_success";
	}

	public String update_style() throws Exception {

		this.setWebSkinColorList(this.webSkinColorManagerDao.findWebSkinColor(this.webSkinColorManagerDao.getWebSkinColorById(
			webSkinColorId).getWebSkinId()));
		for (WebSkinColor webSkinColor : this.getWebSkinColorList()) {
			if (webSkinColorId.equals(webSkinColor.getId())) {
				webSkinColor.setIsDefaultId("1");
				this.webSkinColorManagerDao.updateWebSkinColor(webSkinColor);
			} else {
				webSkinColor.setIsDefaultId("0");
				this.webSkinColorManagerDao.updateWebSkinColor(webSkinColor);
			}
		}

		// WebSkinColor web =
		// this.webSkinColorManagerDao.getWebSkinColorById(webSkinColorId);
		// this.webSiteManagerDao.updateWebSite(webSite);

		return "style_success";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List getWebSkinList() {
		return webSkinList;
	}

	public void setWebSkinList(List webSkinList) {
		this.webSkinList = webSkinList;
	}

	public WebSkin getWebSkin() {
		return webSkin;
	}

	public void setWebSkin(WebSkin webSkin) {
		this.webSkin = webSkin;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public WebSite getWebSite() {
		return webSite;
	}

	public void setWebSite(WebSite webSite) {
		this.webSite = webSite;
	}

	public List<WebSkinColor> getWebSkinColorList() {
		return webSkinColorList;
	}

	public void setWebSkinColorList(List webSkinColorList) {
		this.webSkinColorList = webSkinColorList;
	}

}