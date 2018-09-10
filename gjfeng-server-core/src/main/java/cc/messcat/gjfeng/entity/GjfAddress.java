package cc.messcat.gjfeng.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 用户地址实体
 * @author Karhs
 * @date 2017-01-03 12:01
 * @version 1.0
 */
@Entity
@Table(name = "gjf_address")
public class GjfAddress implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name = "RECEIVER_", length = 20)
	private String receiver;
	
	@ManyToOne
	@JoinColumn(name="MEMBER_")
	private GjfMemberInfo member;
	
	@ManyToOne
	@JoinColumn(name="PROVINCE_")
	private GjfAddressProvince province;
	
	@ManyToOne
	@JoinColumn(name="CITY_")
	private GjfAddressCity city;
	
	@ManyToOne
	@JoinColumn(name="AREA_")
	private GjfAddressArea area;
	
	@Column(name = "ADDRESS_", length = 300)
	private String address;
	
	@Column(name = "POSTAL_CODE_", length = 10)
	private String postalCode;
	
	@Column(name = "PHONE_", length = 15)
	private String phone;
	
	@Column(name = "ADD_TIME_", length = 19)
	private Date addTime;
	
	@Column(name = "EDIT_TIME_", length = 19)
	private Date editTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public GjfMemberInfo getMember() {
		return member;
	}

	public void setMember(GjfMemberInfo member) {
		this.member = member;
	}

	public GjfAddressProvince getProvince() {
		return province;
	}

	public void setProvince(GjfAddressProvince province) {
		this.province = province;
	}

	public GjfAddressCity getCity() {
		return city;
	}

	public void setCity(GjfAddressCity city) {
		this.city = city;
	}

	public GjfAddressArea getArea() {
		return area;
	}

	public void setArea(GjfAddressArea area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}
}