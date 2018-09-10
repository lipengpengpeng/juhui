package cc.messcat.web.dynamic;

import java.util.Date;
import java.util.List;

import cc.modules.commons.PageAction;
import cc.modules.constants.ActionConstants;
import cc.messcat.entity.EnterpriseLinks;

public class EpLinksAction extends PageAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<EnterpriseLinks> enterpriseLinksList;
	private EnterpriseLinks enterpriseLinks;
	private Long id;
	private String names;
	private String address;
	private String intro;
	private Date initTime;
	private Date endTime;
	private String state;
	private Long orderColumn;
	private String frontNum;

	public EpLinksAction() {
		enterpriseLinks = new EnterpriseLinks();
	}

	public void setId(Long id) {
		this.id = id;
	}

	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		try {
			super.pager = epLinksManagerDao.findEpLinks(pageSize, pageNo, "-1");
			enterpriseLinksList = super.pager.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ActionConstants.SUCCESS_KEY;
	}

	/**
	 * 查看一条友情链接信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		setEnterpriseLinks(epLinksManagerDao.getEpLinks(id));
		return ActionConstants.VIEW_KEY;
	}

	/**
	 * 查看一条友情链接信息，并跳转到添加页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		setEnterpriseLinks(epLinksManagerDao.getEpLinks(id));
		return ActionConstants.EDIT_KEY;
	}

	/**
	 * 修改一条友情链接信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		initEnterpriseLinks();
		epLinksManagerDao.updateEnterpriseLinks(enterpriseLinks);
		return ActionConstants.UPDATE_KEY;
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add_page() throws Exception {
		return ActionConstants.ADD_PAGE_KEY;
	}

	/**
	 * 添加一条友情链接信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		initEnterpriseLinks();
		epLinksManagerDao.addEpLinks(enterpriseLinks);
		return "add";
	}

	/**
	 * 删除一条友情链接信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		epLinksManagerDao.deleteEnterpriseLinks(id);
		return "delete";
	}

	/**
	 * 初始化实体对象
	 */
	private void initEnterpriseLinks() {
		enterpriseLinks.setNames(names);
		enterpriseLinks.setAddress(address);
		enterpriseLinks.setIntro(intro.trim());
		enterpriseLinks.setInitTime(initTime);
		enterpriseLinks.setEndTime(endTime);
		enterpriseLinks.setState(state);
		enterpriseLinks.setOrderColumn(orderColumn);
		enterpriseLinks.setFrontNum(frontNum);
	}

	public Long getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(Long orderColumn) {
		this.orderColumn = orderColumn;
	}

	public String getFrontNum() {
		return frontNum;
	}

	public void setFrontNum(String frontNum) {
		this.frontNum = frontNum;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getInitTime() {
		return initTime;
	}

	public void setInitTime(Date initTime) {
		this.initTime = initTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List<EnterpriseLinks> getEnterpriseLinksList() {
		return enterpriseLinksList;
	}

	public void setEnterpriseLinksList(List<EnterpriseLinks> enterpriseLinksList) {
		this.enterpriseLinksList = enterpriseLinksList;
	}

	public EnterpriseLinks getEnterpriseLinks() {
		return enterpriseLinks;
	}

	public void setEnterpriseLinks(EnterpriseLinks enterpriseLinks) {
		this.enterpriseLinks = enterpriseLinks;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}