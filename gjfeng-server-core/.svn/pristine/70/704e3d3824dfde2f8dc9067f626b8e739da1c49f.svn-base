package cc.messcat.gjfeng.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * GjfOrderAddress entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gjf_order_address")
public class GjfOrderAddress implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="ORDER_ID_")
	private GjfOrderInfo orderId;
	
	@Column(name = "RECIVER_NAME_", length = 50)
	private String reciverName;
	
	@Column(name = "RECIVER_MOBILE_", length = 15)
	private String reciverMobile;
	
	@ManyToOne
	@JoinColumn(name="RECIVER_PROVINCE_ID_")
	private GjfAddressProvince reciverProvinceId;
	
	@ManyToOne
	@JoinColumn(name="RECIVER_CITY_ID_")
	private GjfAddressCity reciverCityId;
	
	@ManyToOne
	@JoinColumn(name="RECIVER_AREA_ID_")
	private GjfAddressArea reciverAreaId;
	
	@Column(name = "SHIPPING_FEE_AMOUNT_", precision = 10, scale=2)
	private BigDecimal shippingFeeAmount;
	
	@Column(name = "COURIER_NAME_", length = 20)
	private String courierName;
	
	@Column(name = "SHIPPING_CODE_", length = 50)
	private String shippingCode;
	
	@Column(name = "RECIVER_DETAIL_ADDRESS_", length = 100)
	private String reciverDetailAddress;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GjfOrderInfo getOrderId() {
		return orderId;
	}

	public void setOrderId(GjfOrderInfo orderId) {
		this.orderId = orderId;
	}

	public String getReciverName() {
		return reciverName;
	}

	public void setReciverName(String reciverName) {
		this.reciverName = reciverName;
	}

	public String getReciverMobile() {
		return reciverMobile;
	}

	public void setReciverMobile(String reciverMobile) {
		this.reciverMobile = reciverMobile;
	}

	public GjfAddressProvince getReciverProvinceId() {
		return reciverProvinceId;
	}

	public void setReciverProvinceId(GjfAddressProvince reciverProvinceId) {
		this.reciverProvinceId = reciverProvinceId;
	}

	public GjfAddressCity getReciverCityId() {
		return reciverCityId;
	}

	public void setReciverCityId(GjfAddressCity reciverCityId) {
		this.reciverCityId = reciverCityId;
	}

	public GjfAddressArea getReciverAreaId() {
		return reciverAreaId;
	}

	public void setReciverAreaId(GjfAddressArea reciverAreaId) {
		this.reciverAreaId = reciverAreaId;
	}

	public BigDecimal getShippingFeeAmount() {
		return shippingFeeAmount;
	}

	public void setShippingFeeAmount(BigDecimal shippingFeeAmount) {
		this.shippingFeeAmount = shippingFeeAmount;
	}

	public String getCourierName() {
		return courierName;
	}

	public void setCourierName(String courierName) {
		this.courierName = courierName;
	}

	public String getShippingCode() {
		return shippingCode;
	}

	public void setShippingCode(String shippingCode) {
		this.shippingCode = shippingCode;
	}

	public String getReciverDetailAddress() {
		return reciverDetailAddress;
	}

	public void setReciverDetailAddress(String reciverDetailAddress) {
		this.reciverDetailAddress = reciverDetailAddress;
	}

}