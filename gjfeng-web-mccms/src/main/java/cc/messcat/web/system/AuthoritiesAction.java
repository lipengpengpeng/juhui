// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov Date: 2010-5-13
// 14:49:05
// Home Page : http://members.fortunecity.com/neshkov/dj.html - Check often for
// new version!
// Decompiler options: packimports(3)
// Source File Name: AuthoritiesAction.java

package cc.messcat.web.system;

import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cc.messcat.entity.Authorities;
import cc.modules.commons.PageAction;

public class AuthoritiesAction extends PageAction {

	private static final long serialVersionUID = -9136647805483977904L;
	private Authorities authorities;
	private List authoritiesList;
	private Long id;
	private String name;
	private String displayName;
	
	/**
	 * 修改页面布局开始
	 */
	private String menu;	//菜单树
	private String column;	//栏目树
	private List<Authorities> function;	//功能列表 
	private String tree;	//生成树
	private String type;	//权限类型
	private String fatherAuthId;	//父级权限ID
	private String fatherAuthName;	//父级权限名
	private String returnValue;		//默认返回值
	private String returnValueId;	//默认返回值ID
	
	public AuthoritiesAction() {
		authorities = new Authorities();
	}

	public String execute() throws Exception {
		
		/**
		 * 生成菜单树
		 */
		List<Authorities> menuList = authoritiesManagerDao.findAuthoritiesByType("0");
		String menuUrl = "authoritiesAction!edit.action?id=";
		menuUrl = "javascript: loadMenu('";
		menu = generateTreeByList(menuList,"menu",menuUrl);
		
		/**
		 * 生成栏目树
		 */
		List<Authorities> columnList = authoritiesManagerDao.findAuthoritiesByType("1");
		String columnUrl = "authoritiesAction!edit.action?id=";
		columnUrl = "javascript: loadMenu('";
		column = generateTreeByList(columnList,"column",columnUrl);
		
		/**
		 * 生成功能列表
		 */
		function = authoritiesManagerDao.findAuthoritiesByType("2");
		
		return "success";
	}

	public String view() throws Exception {
		authorities = authoritiesManagerDao.getAuthorities(id);
		return "view";
	}

	public String edit() throws Exception {
		Authorities tempAuthorities = authoritiesManagerDao.getAuthorities(id);
		//查询出父级权限名
		if(tempAuthorities.getAuthoritiesId() != 0){
			Authorities fatherAuthorities = authoritiesManagerDao.getAuthorities(tempAuthorities.getAuthoritiesId());
			fatherAuthName = fatherAuthorities.getDisplayName();
		}else{
			if(tempAuthorities.getAuthoritiesType().equals("0")){
				fatherAuthName = "MCCMS";
			}else if(tempAuthorities.getAuthoritiesType().equals("1")){
				fatherAuthName = "总站";
			}
		}
		setAuthorities(tempAuthorities);
		return "edit";
	}

	public String checkName() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String name = request.getParameter("name");
		String orgName = request.getParameter("orgName");
		name = URLDecoder.decode(name, "UTF-8");
		orgName = URLDecoder.decode(orgName, "UTF-8");
		if (authoritiesManagerDao.isNameUnique(name, orgName))
			return renderText("true");
		else
			return renderText("false");
	}

	public String checkDisplayName() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String displayName = request.getParameter("displayName");
		String orgName = request.getParameter("orgName");
		displayName = URLDecoder.decode(displayName, "UTF-8");
		orgName = URLDecoder.decode(orgName, "UTF-8");
		if (authoritiesManagerDao.isDisplayNameUnique(displayName, orgName))
			return renderText("true");
		else
			return renderText("false");
	}

	public String update() throws Exception {
		initAuthorities();
		
		//把父级权限存进数据库
		if(!fatherAuthName.equals("MCCMS") && !fatherAuthName.equals("总站")){
			Authorities fatherAuthorities = authoritiesManagerDao.getAuthByDisplayName(fatherAuthName);
			if(fatherAuthorities != null){
				authorities.setAuthoritiesId(fatherAuthorities.getId());
			}else{
				authorities.setAuthoritiesId(0L);
			}
		}else{
			authorities.setAuthoritiesId(0L);
		}
		
		authoritiesManagerDao.updateAuthorities(authorities);
		return "update";
	}

	public String add_page() throws Exception {
		
		if(id != null && !"".equals(id)){
			Authorities tempAuthorities = authoritiesManagerDao.getAuthorities(id);
			fatherAuthName = tempAuthorities.getDisplayName();
			type = tempAuthorities.getAuthoritiesType();
		}
		
		return "add_page";
	}

	public String add() throws Exception {
		initAuthorities();
		
		//把父级权限存进数据库
		if(!fatherAuthName.equals("MCCMS") && !fatherAuthName.equals("总站")){
			Authorities fatherAuthorities = authoritiesManagerDao.getAuthByDisplayName(fatherAuthName);
			if(fatherAuthorities != null){
				authorities.setAuthoritiesId(fatherAuthorities.getId());
			}else{
				authorities.setAuthoritiesId(0L);
			}
		}else{
			authorities.setAuthoritiesId(0L);
		}
		
		authoritiesManagerDao.addAuthorities(authorities);
		return "add";
	}

	public String delete() throws Exception {
		authoritiesManagerDao.deleteAuthorities(id);
		return "delete";
	}

	public String generateTree() throws Exception{
		//默认返回值为父级权限名，编辑页面专用
		if(id != null && !"".equals(id)){
			Authorities tempAuthorities = authoritiesManagerDao.getAuthorities(id);
			if(tempAuthorities != null){
				//查询出父级权限名
				if(tempAuthorities.getAuthoritiesId() != 0){
					Authorities fatherAuthorities = authoritiesManagerDao.getAuthorities(tempAuthorities.getAuthoritiesId());
					returnValue = fatherAuthorities.getDisplayName();
					returnValueId = fatherAuthorities.getId().toString();
				}else{
					if(type.equals("0")){
						returnValue = "MCCMS";
						returnValueId = "0";
					}else if(type.equals("1")){
						returnValue = "总站";
						returnValueId = "0";
					}
				}
			}
		}else{
			returnValue = "";
			returnValueId = "0";
		}
		
		//默认返回值为父级权限名，新增页面专用
		//查询出父级权限名
		if(fatherAuthId != null && !"".equals(fatherAuthId)){
			Authorities tempAuthorities = authoritiesManagerDao.getAuthorities(Long.valueOf(fatherAuthId));
			if(tempAuthorities.getAuthoritiesId() != 0){
				returnValue = tempAuthorities.getDisplayName();
				returnValueId = tempAuthorities.getId().toString();
			}else{
				if(type.equals("0")){
					returnValue = "MCCMS";
					returnValueId = "0";
				}else if(type.equals("1")){
					returnValue = "总站";
					returnValueId = "0";
				}
			}
		}
		
		//生成树
		List<Authorities> treeList = authoritiesManagerDao.findAuthoritiesByTypeAndId(type, id);
		String treeUrl = "javascript:selectHandle('";
		if(type.equals("0")){
			tree = "tree.add(0,-1,'MCCMS',\"javascript:selectHandle('MCCMS')\",'MCCMS');";
		}else if(type.equals("1")){
			tree = "tree.add(0,-1,'总站',\"javascript:selectHandle('总站')\",'总站');";
		}
		
		StringBuilder treeBuilder = new StringBuilder();
		//menu.add(0,-1,'MCCMS',"javascript:selectHandle('仪器设备')",'MCCMS');
		for (Authorities authorities : treeList) {
			treeBuilder.append("tree").append(".add(").append(authorities.getId()).append(",")
				.append(authorities.getAuthoritiesId()).append(",'")
				.append(authorities.getDisplayName()).append("',\"")
				.append(treeUrl).append(authorities.getDisplayName()).append("')\",'")
				.append(authorities.getName()).append("');");
		}
		tree += treeBuilder.toString();
		
		return "tree";
	}
	
	public void initAuthorities() {
		authorities.setName(name);
		authorities.setDisplayName(displayName);
	}
	
	/**
	 * 用于根据列表生成树
	 * @return 树的String
	 */
	private String generateTreeByList(List<Authorities> treeList,String treeName,String url){
		
		StringBuilder treeBuilder = new StringBuilder();
		//menu.add(0,-1,'MCCMS','','MCCMS');
		for (Authorities authorities : treeList) {
			treeBuilder.append(treeName).append(".add(").append(authorities.getId()).append(",")
				.append(authorities.getAuthoritiesId()).append(",'")
				.append(authorities.getDisplayName()).append("',\"")
				.append(url).append(authorities.getId()).append("');\",'")
				.append(authorities.getName()).append("');");
		}
		
		return treeBuilder.toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Authorities getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Authorities authorities) {
		this.authorities = authorities;
	}

	public List getAuthoritiesList() {
		return authoritiesList;
	}

	public void setAuthoritiesList(List authoritiesList) {
		this.authoritiesList = authoritiesList;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public List<Authorities> getFunction() {
		return function;
	}

	public void setFunction(List<Authorities> function) {
		this.function = function;
	}

	public String getTree() {
		return tree;
	}

	public void setTree(String tree) {
		this.tree = tree;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFatherAuthName() {
		return fatherAuthName;
	}

	public void setFatherAuthName(String fatherAuthName) {
		this.fatherAuthName = fatherAuthName;
	}

	public String getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}

	public String getReturnValueId() {
		return returnValueId;
	}

	public void setReturnValueId(String returnValueId) {
		this.returnValueId = returnValueId;
	}

	public String getFatherAuthId() {
		return fatherAuthId;
	}

	public void setFatherAuthId(String fatherAuthId) {
		this.fatherAuthId = fatherAuthId;
	}

}