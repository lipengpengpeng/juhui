package cc.messcat.gjfeng.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * GjfUsersInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gjf_users_info")
public class GjfUsersInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", length = 10)
	private Long id;
	
	@Column(name = "LOGIN_NAME", length = 20)
	private String loginName;
	
	@Column(name = "PASSWORD", length = 100)
	private String password;
	
	@Column(name = "NAME", length = 20)
	private String name;
	
	@Column(name = "AREA", length = 12)
	private String area;
	
	@Column(name = "WORKUNIT", length = 64)
	private String workunit;
	
	@Column(name = "IDCARDNO", length = 18)
	private String idcardno;
	
	@Column(name = "SEX", length = 1)
	private String sex;
	
	@Column(name = "WORKPHONE", length = 64)
	private String workphone;
	
	@Column(name = "FAX", length = 64)
	private String fax;
	
	@Column(name = "EMAIL", length = 30)
	private String email;
	
	@Column(name = "MOBILE", length = 64)
	private String mobile;
	
	@Column(name = "STATE", length = 1)
	private String state;
	
	@Column(name = "EDITOR", precision = 10)
	private Long editor;
	
	@Column(name = "EDITTIME")
	private Date edittime;
	
	@Column(name = "REMARK", length = 100)
	private String remark;
	
	@Column(name = "ADDRESS", length = 100)
	private String address;
	
	@Column(name = "COUNTY", length = 100)
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