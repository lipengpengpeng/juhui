package cc.messcat.gjfeng.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "gjf_enterprise_ad")
public class GjfEnterpriseAd implements Serializable {

	private static final long serialVersionUID = 1L;

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", length = 10)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="USERS")
	private GjfUsersInfo users;

	@Column(name = "FRONT_NUM", length = 20)
	private String frontNum;
	
	@Column(name = "ORDER_COLUMN", precision = 10,scale=2)
	private BigDecimal orderColumn;

	@Column(name = "NAMES", length = 50)
	private String names;
	
	@Column(name = "PHOTO", length = 255)
	private String photo;
	
	@Column(name = "ADDRESS", length = 100)
	private String address;
	
	@Column(name = "INTRO")
	private String intro;
	
	@Column(name = "ACOST")
	private Long acost;
	
	@Column(name = "CLIENT_NAME", length = 50)
	private String clientName;
	
	@Column(name = "INIT_TIME")
	private Date initTime;
	
	@Column(name = "END_TIME")
	private Date endTime;
	
	@Column(name = "EDIT_TIME")
	private Date editTime;
	
	@Column(name = "STATE", length = 1)
	private String state;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GjfUsersInfo getUsers() {
		return users;
	}

	public void setUsers(GjfUsersInfo users) {
		this.users = users;
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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
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

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public BigDecimal getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(BigDecimal orderColumn) {
		this.orderColumn = orderColumn;
	}

	public Long getAcost() {
		return acost;
	}

	public void setAcost(Long acost) {
		this.acost = acost;
	}
}