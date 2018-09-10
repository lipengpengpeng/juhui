package cc.messcat.gjfeng.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * GjfMemberAddress entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gjf_member_address")
public class GjfMemberAddress implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="MEMBER_ID_")
	private GjfMemberInfo memberId;
	
	@Column(name = "CONSIGNEE_NAME_", length = 50)
	private String consigneeName;
	
	@Column(name = "CONSIGNEE_SEX_", length = 1)
	private String consigneeSex;
	
	@Column(name = "MOBILE_", length = 15)
	private String mobile;
	
	@ManyToOne
	@JoinColumn(name="PROVICE_ID_")
	private GjfAddressProvince proviceId;
	
	@ManyToOne
	@JoinColumn(name="CITY_ID_")
	private GjfAddressCity cityId;
	
	@ManyToOne
	@JoinColumn(name="AREA_ID_")
	private GjfAddressArea areaId;
	
	@ManyToOne
	@JoinColumn(name="TOWN_ID_")
	private GjfAddressTown townId;
	
	@Column(name = "ADDRESS_DETAIL_", length = 300)
	private String addressDetail;
	
	@Column(name = "ZIP_CODE_", length = 10)
	private String zipCode;
	
	@Column(name = "IS_DEFAULT_", length = 1)
	private String isDefault;
	
	@Column(name = "ADDRESS_SOUCE_", length = 1)
	private String addressSouce;
	
	@Column(name = "NET_ADRESS_ID_", length = 100)
	private String netAddressId;

	public String getNetAddressId() {
		return netAddressId;
	}

	public void setNetAddressId(String netAddressId) {
		this.netAddressId = netAddressId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GjfMemberInfo getMemberId() {
		return memberId;
	}

	public void setMemberId(GjfMemberInfo memberId) {
		this.memberId = memberId;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public String getConsigneeSex() {
		return consigneeSex;
	}

	public void setConsigneeSex(String consigneeSex) {
		this.consigneeSex = consigneeSex;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public GjfAddressProvince getProviceId() {
		return proviceId;
	}

	public void setProviceId(GjfAddressProvince proviceId) {
		this.proviceId = proviceId;
	}

	public GjfAddressCity getCityId() {
		return cityId;
	}

	public void setCityId(GjfAddressCity cityId) {
		this.cityId = cityId;
	}

	public GjfAddressArea getAreaId() {
		return areaId;
	}

	public void setAreaId(GjfAddressArea areaId) {
		this.areaId = areaId;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getAddressSouce() {
		return addressSouce;
	}

	public void setAddressSouce(String addressSouce) {
		this.addressSouce = addressSouce;
	}

	public GjfAddressTown getTownId() {
		return townId;
	}

	public void setTownId(GjfAddressTown townId) {
		this.townId = townId;
	}
	

}