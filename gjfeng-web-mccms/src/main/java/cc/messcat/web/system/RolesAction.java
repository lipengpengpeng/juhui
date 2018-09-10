package cc.messcat.web.system;

import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cc.messcat.entity.Authorities;
import cc.messcat.entity.Roles;
import cc.messcat.entity.RolesAuthorities;
import cc.messcat.entity.RolesAuthoritiesId;
import cc.modules.commons.PageAction;

public class RolesAction extends PageAction {

	private static final long serialVersionUID = -6540194798244773037L;
	private Roles roles;
	private List rolesList;
	private List authoritiesList;
	private Long id;
	private String name;
	private List checkedMenu;
	
	/**
	 * 修改页面布局开始
	 */
	private String menu;				//菜单树
	private String column;				//栏目树
	private List<Authorities> function;	//功能列表
	private int menuSize;				//菜单列表长度
	private int columnSize;				//栏目列表长度

	public RolesAction() {
		roles = new Roles();
	}

	public String execute() throws Exception {
		super.pager = rolesManagerDao.findRoles(pageSize, pageNo, "-1");
		rolesList = super.pager.getResultList();
		return "success";
	}

	public String view() throws Exception {
		roles = rolesManagerDao.getRoles(id);
		return "view";
	}

	public String edit() throws Exception {
		Roles role = rolesManagerDao.getRoles(id);
		setRoles(role);
		
		/**
		 * 生成菜单树
		 */
		List<Authorities> menuList = authoritiesManagerDao.findAuthoritiesByType("0");
		String menuUrl = "javascript:void(0);";
		menuSize = menuList.size();
		menu = generateTreeByList(menuList, role, "menu", menuUrl);
		
		/**
		 * 生成栏目树
		 */
		List<Authorities> columnList = authoritiesManagerDao.findAuthoritiesByType("1");
		String columnUrl = "javascript:void(0);";
		columnSize = columnList.size();
		column = generateTreeByList(columnList, role, "column", columnUrl);
		
		/**
		 * 生成功能列表
		 */
		function = authoritiesManagerDao.findAuthoritiesByType("2");
		
		return "edit";
	}

	public String checkName() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String name = request.getParameter("name");
		String orgName = request.getParameter("orgName");
		name = URLDecoder.decode(name, "UTF-8");
		orgName = URLDecoder.decode(orgName, "UTF-8");
		if (rolesManagerDao.isNameUnique(name, orgName))
			return renderText("true");
		else
			return renderText("false");
	}

	public String update() throws Exception {
		// initRoles();
		rolesManagerDao.updateRoles(roles);
		rolesAuthoritiesManagerDao.deleteRolesAuthoritiesByRoles(roles.getId());
		cc.messcat.entity.Authorities authorities;
		if(checkedMenu != null){
			for (Iterator iterator = checkedMenu.iterator(); iterator.hasNext();) {
				String check = (String) iterator.next();
				//排除掉总站的两个checkbox传回的值
				if(check.equals("undefined"))
					continue;
				authorities = authoritiesManagerDao.getAuthorities(Long.valueOf(check));
				rolesAuthoritiesManagerDao.addRolesAuthorities(new RolesAuthorities(new RolesAuthoritiesId(roles, authorities), roles, authorities));
			}
		}

		return "update";
	}

	public String add_page() throws Exception {

		/**
		 * 生成菜单树
		 */
		List<Authorities> menuList = authoritiesManagerDao.findAuthoritiesByType("0");
		String menuUrl = "javascript:void(0);";
		menuSize = menuList.size();
		menu = generateTreeByList(menuList, null, "menu", menuUrl);
		
		/**
		 * 生成栏目树
		 */
		List<Authorities> columnList = authoritiesManagerDao.findAuthoritiesByType("1");
		String columnUrl = "javascript:void(0);";
		columnSize = columnList.size();
		column = generateTreeByList(columnList, null, "column", columnUrl);
		
		/**
		 * 生成功能列表
		 */
		function = authoritiesManagerDao.findAuthoritiesByType("2");
		
		return "add_page";
	}

	@SuppressWarnings("rawtypes")
	public String add() throws Exception {
		try {
			initRoles();
			rolesManagerDao.addRoles(roles);
			cc.messcat.entity.Authorities authorities;
			if (null != checkedMenu) {
				for (Iterator iterator = checkedMenu.iterator(); iterator.hasNext();) {
					String check = (String) iterator.next();
					//排除掉总站的两个checkbox传回的值
					if(check.equals("undefined"))
						continue;
					authorities = authoritiesManagerDao.getAuthorities(Long.valueOf(check));
					rolesAuthoritiesManagerDao.addRolesAuthorities(new RolesAuthorities(new RolesAuthoritiesId(roles, authorities), roles, authorities));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "add";
	}

	public String delete() throws Exception {
		rolesManagerDao.deleteRoles(id);
		return "delete";
	}

	public void initRoles() {
		roles.setName(name);
	}
	
	/**
	 * 用于根据列表与角色所拥有的权限值生成树
	 * @return 树的String
	 */
	private String generateTreeByList(List<Authorities> treeList, Roles role, String treeName, String url){
		
		StringBuilder treeBuilder = new StringBuilder();
		//menu.add(0,-1,'MCCMS','','MCCMS','',0,true);
		for (Authorities authorities : treeList) {
			treeBuilder.append(treeName).append(".add(").append(authorities.getId()).append(",")
				.append(authorities.getAuthoritiesId()).append(",'")
				.append(authorities.getDisplayName()).append("','")
				.append(url).append("','")
				.append(authorities.getName()).append("','',")
				.append(authorities.getId());
			
			//根据角色所拥有权限判断此节点是否选中
			if(role != null){
				Set<RolesAuthorities> rolesAuthSet = role.getRolesAuthoritieses();
				for (RolesAuthorities rolesAuthorities : rolesAuthSet) {
					if(rolesAuthorities.getAuthorities().getId() == authorities.getId()){
						treeBuilder.append(",").append(true);
						break;
					}
				}
			}
			
			treeBuilder.append(");");
		}
		
		return treeBuilder.toString();
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	public List getRolesList() {
		return rolesList;
	}

	public void setRolesList(List rolesList) {
		this.rolesList = rolesList;
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

	public List getAuthoritiesList() {
		return authoritiesList;
	}

	public void setAuthoritiesList(List authoritiesList) {
		this.authoritiesList = authoritiesList;
	}

	public List getCheckedMenu() {
		return checkedMenu;
	}

	public void setCheckedMenu(List checkedMenu) {
		this.checkedMenu = checkedMenu;
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

	public int getMenuSize() {
		return menuSize;
	}

	public void setMenuSize(int menuSize) {
		this.menuSize = menuSize;
	}

	public int getColumnSize() {
		return columnSize;
	}

	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}

}