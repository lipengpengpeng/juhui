package cc.messcat.gjfeng.common.vo.app;

import java.io.Serializable;

import cc.messcat.gjfeng.entity.GjfAddressArea;
import cc.messcat.gjfeng.entity.GjfAddressCity;
import cc.messcat.gjfeng.entity.GjfAddressProvince;
import cc.messcat.gjfeng.entity.GjfAddressTown;

/**
 * GjfMemberAddress entity. @author MyEclipse Persistence Tools
 */
public class MemberAddressVo implements Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String consigneeName;
	
	private String consigneeSex;
	
	private String mobile;
	
	private GjfAddressProvince proviceId;
	
	private GjfAddressCity cityId;
	
	private GjfAddressArea areaId;
	
	private GjfAddressTown townId;
	
	private String addressDetail;
	
	private String zipCode;
	
	private String isDefault;
	
	private String addressSouce;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public GjfAddressTown getTownId() {
		return townId;
	}

	public void setTownId(GjfAddressTown townId) {
		this.townId = townId;
	}

	public String getAddressSouce() {
		return addressSouce;
	}

	public void setAddressSouce(String addressSouce) {
		this.addressSouce = addressSouce;
	}

}