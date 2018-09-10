package cc.messcat.gjfeng.entity;

import java.util.Date;

/**
 * GjfUsersInfo entity. @author MyEclipse Persistence Tools
 */
public class GjfUsersInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String loginName;
	
	private String password;
	
	private String name;
	
	private String area;
	
	private String workunit;
	
	private String idcardno;
	
	private String sex;
	
	private String workphone;
	
	private String fax;
	
	private String email;
	
	private String mobile;
	
	private String state;
	
	private Long editor;
	
	private Date edittime;
	
	private String remark;
	
	private String address;
	
	private String county;

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getWorkunit() {
		return this.workunit;
	}

	public void setWorkunit(String workunit) {
		this.workunit = workunit;
	}

	public String getIdcardno() {
		return this.idcardno;
	}

	public void setIdcardno(String idcardno) {
		this.idcardno = idcardno;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getWorkphone() {
		return this.workphone;
	}

	public void setWorkphone(String workphone) {
		this.workphone = workphone;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getEditor() {
		return this.editor;
	}

	public void setEditor(Long editor) {
		this.editor = editor;
	}

	public Date getEdittime() {
		return this.edittime;
	}

	public void setEdittime(Date edittime) {
		this.edittime = edittime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

}