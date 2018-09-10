package cc.messcat.gjfeng.entity;

import java.util.Date;

/**
 * 用户地址实体
 * @author Karhs
 * @date 2017-01-03 12:01
 * @version 1.0
 */
public class GjfAddress implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String receiver;
	
	private GjfMemberInfo member;
	
	private GjfAddressProvince province;
	
	private GjfAddressCity city;
	
	private GjfAddressArea area;
	
	private String address;
	
	private String postalCode;
	
	private String phone;
	
	private Date addTime;
	
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