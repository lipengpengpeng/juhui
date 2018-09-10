package cc.messcat.gjfeng.entity;

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

/**
 * GjfStoreInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gjf_store_info")
public class GjfStoreInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name = "STORE_NAME_", length = 50)
	private String storeName;
	
	@Column(name = "STORE_NUM_", length = 50)
	private String storeNum;
	
	@ManyToOne
	@JoinColumn(name="MEMBER_ID_")
	private GjfMemberInfo memberId;
	
	@Column(name = "SELLER_NAME_", length = 50)
	private String sellerName;
	
	@Column(name = "SELLER_MOBILE_", length = 20)
	private String sellerMobile;
	
	@Column(name = "SELLER_EMAIL_", length = 100)
	private String sellerEmail;
	
	@ManyToOne
	@JoinColumn(name="PROVINCE_ID_")
	private GjfAddressProvince provinceId;
	
	@ManyToOne
	@JoinColumn(name="CITY_ID_")
	private GjfAddressCity cityId;
	
	@ManyToOne
	@JoinColumn(name="AREA_ID_")
	private GjfAddressArea areaId;
	
	@Column(name = "ADDRESS_DETAIL_", length = 200)
	private String addressDetail;
	
	@Column(name = "LONGITUDE_")
	private Double longitude;
	
	@Column(name = "LATITUDE_")
	private Double latitude;
	
	@Column(name = "STORE_ADD_TIME_")
	private Date storeAddTime;
	
	@Column(name = "STORE_ADUIT_TIME_")
	private Date storeAduitTime;
	
	@Column(name = "STORE_START_TIME_")
	private Date storeStartTime;
	
	@Column(name = "STORE_END_TIME_")
	private Date storeEndTime;
	
	@Column(name = "STORE_CLOSE_REMARK_", length = 255)
	private String storeCloseRemark;
	
	@Column(name = "STORE_LOGO_URL_", length = 255)
	private String storeLogoUrl;
	
	@Column(name = "STORE_BANNER_")
	private String storeBanner;
	
	@Column(name = "STORE_KEYWORDS_", length = 255)
	private String storeKeywords;
	
	@Column(name = "STORE_DESCRIPTION_")
	private String storeDescription;
	
	@Column(name = "STORE_RECOMMEND_", length = 1)
	private String storeRecommend;
	
	@Column(name = "STORE_DIVIDENDS_TOTAL_MONEY_", precision=10, scale=2)
	private BigDecimal storeDividendsTotalMoney;
	
	@Column(name = "STORE_DIVIDENDS_TOTAL_MONEY_BLA_", precision=10, scale=2)
	private BigDecimal storeDividendsTotalMoneyBla;
	
	@Column(name = "STORE_DIVIDENDS_MONEY_BLA_", precision=10, scale=2)
	private BigDecimal storeDividendsMoneyBla;
	
	@Column(name = "STORE_DIVIDENDS_NUM_", precision=10, scale=2)
	private BigDecimal storeDividendsNum;
	
	@Column(name = "STORE_SALE_TOTAL_MONEY_", precision=10, scale=2)
	private BigDecimal storeSaleTotalMoney;
	
	@Column(name = "STORE_BENEFIT_TOTAL_MONEY_", precision=10, scale=2)
	private BigDecimal storeBenefitTotalMoney;
	
	@Column(name = "STORE_CREDIT_SCORE_", length = 10)
	private Long storeCreditScore;
	
	@Column(name = "STORE_DESCCREDIT_SCORE_", precision=10, scale=2)
	private BigDecimal storeDesccreditScore;
	
	@Column(name = "STORE_SERVICE_CREDIT_SCORE_", precision=10, scale=2)
	private BigDecimal storeServiceCreditScore;
	
	@Column(name = "STORE_DELIVERY_CREDIT_SCORE_", precision=10, scale=2)
	private BigDecimal storeDeliveryCreditScore;
	
	@Column(name = "STORE_COLLECT_NUM_", length = 10)
	private Long storeCollectNum;
	
	@Column(name = "STORE_SALES_NUM_", length = 10)
	private Long storeSalesNum;
	
	@Column(name = "STORE_BEF_CUSTOMER_", length = 200)
	private String storeBefCustomer;
	
	@Column(name = "STORE_AFT_CUSTOMER_", length = 200)
	private String storeAftCustomer;
	
	@Column(name = "STORE_WORKING_TIME_", length = 100)
	private String storeWorkingTime;
	
	@Column(name = "STORE_FREE_PRICE_", precision=10, scale=2)
	private BigDecimal storeFreePrice;
	
	@Column(name = "STORE_IS_OWN_SHOP_", length = 1)
	private String storeIsOwnShop;
	
	@Column(name = "STORE_MARGIN_", precision=10, scale=2)
	private BigDecimal storeMargin;
	
	@Column(name = "STORE_REAL_INCOME_RATIO_", precision=10, scale=2)
	private BigDecimal storeRealIncomeRatio;
	
	@Column(name = "STORE_REAL_GIFT_RATIO_", precision=10, scale=2)
	private BigDecimal storeRealGiftRatio;
	
	@Column(name = "STORE_PRO_", length = 1)
	private String storePro;
	
	@Column(name = "STORE_TYPE_", length = 1)
	private String storeType;
	
	@Column(name = "STORE_STATUS_", length = 1)
	private String storeStatus;
	
	@Column(name = "AUDIT_STATUS_REASON_", length = 200)
	private String auditStatusReason;
	
	@Column(name = "IS_DIVI_", length = 1)
	private String isDivi;
	
	@Column(name = "IS_DEL_", length = 1)
	private String isDel;
	
	@Column(name = "TOKEN_", length = 200)
	private String token;
	
	@Column(name = "PAY_MCH_ID_", length = 300)
	private String payMchId;
	
	@Column(name = "PAY_KEY_", length = 300)
	private String payKey;
	
	@Column(name = "DEDUCT_DIVI_NUM_", precision=20, scale=6)
	private BigDecimal deductDiviNum;
	
	@Column(name = "LAST_TIME_DIVI_MONEY_", precision=20, scale=2)
	private BigDecimal lastTimeDiviMoney;
	
	@Column(name = "IS_ACTIVITY_STORE_", length=1)
	private String isActivityStore;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreNum() {
		return storeNum;
	}

	public void setStoreNum(String storeNum) {
		this.storeNum = storeNum;
	}

	public GjfMemberInfo getMemberId() {
		return memberId;
	}

	public void setMemberId(GjfMemberInfo memberId) {
		this.memberId = memberId;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getSellerMobile() {
		return sellerMobile;
	}

	public void setSellerMobile(String sellerMobile) {
		this.sellerMobile = sellerMobile;
	}

	public String getSellerEmail() {
		return sellerEmail;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

	public GjfAddressProvince getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(GjfAddressProvince provinceId) {
		this.provinceId = provinceId;
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

	public Date getStoreAddTime() {
		return storeAddTime;
	}

	public void setStoreAddTime(Date storeAddTime) {
		this.storeAddTime = storeAddTime;
	}

	public Date getStoreAduitTime() {
		return storeAduitTime;
	}

	public void setStoreAduitTime(Date storeAduitTime) {
		this.storeAduitTime = storeAduitTime;
	}

	public Date getStoreStartTime() {
		return storeStartTime;
	}

	public void setStoreStartTime(Date storeStartTime) {
		this.storeStartTime = storeStartTime;
	}

	public Date getStoreEndTime() {
		return storeEndTime;
	}

	public void setStoreEndTime(Date storeEndTime) {
		this.storeEndTime = storeEndTime;
	}

	public String getStoreCloseRemark() {
		return storeCloseRemark;
	}

	public void setStoreCloseRemark(String storeCloseRemark) {
		this.storeCloseRemark = storeCloseRemark;
	}

	public String getStoreLogoUrl() {
		return storeLogoUrl;
	}

	public void setStoreLogoUrl(String storeLogoUrl) {
		this.storeLogoUrl = storeLogoUrl;
	}

	public String getStoreBanner() {
		return storeBanner;
	}

	public void setStoreBanner(String storeBanner) {
		this.storeBanner = storeBanner;
	}

	public String getStoreKeywords() {
		return storeKeywords;
	}

	public void setStoreKeywords(String storeKeywords) {
		this.storeKeywords = storeKeywords;
	}

	public String getStoreDescription() {
		return storeDescription;
	}

	public void setStoreDescription(String storeDescription) {
		this.storeDescription = storeDescription;
	}

	public String getStoreRecommend() {
		return storeRecommend;
	}

	public void setStoreRecommend(String storeRecommend) {
		this.storeRecommend = storeRecommend;
	}

	public Long getStoreCreditScore() {
		return storeCreditScore;
	}

	public void setStoreCreditScore(Long storeCreditScore) {
		this.storeCreditScore = storeCreditScore;
	}

	public BigDecimal getStoreDesccreditScore() {
		return storeDesccreditScore;
	}

	public void setStoreDesccreditScore(BigDecimal storeDesccreditScore) {
		this.storeDesccreditScore = storeDesccreditScore;
	}

	public BigDecimal getStoreServiceCreditScore() {
		return storeServiceCreditScore;
	}

	public void setStoreServiceCreditScore(BigDecimal storeServiceCreditScore) {
		this.storeServiceCreditScore = storeServiceCreditScore;
	}

	public BigDecimal getStoreDeliveryCreditScore() {
		return storeDeliveryCreditScore;
	}

	public void setStoreDeliveryCreditScore(BigDecimal storeDeliveryCreditScore) {
		this.storeDeliveryCreditScore = storeDeliveryCreditScore;
	}

	public Long getStoreCollectNum() {
		return storeCollectNum;
	}

	public void setStoreCollectNum(Long storeCollectNum) {
		this.storeCollectNum = storeCollectNum;
	}

	public Long getStoreSalesNum() {
		return storeSalesNum;
	}

	public void setStoreSalesNum(Long storeSalesNum) {
		this.storeSalesNum = storeSalesNum;
	}

	public String getStoreBefCustomer() {
		return storeBefCustomer;
	}

	public void setStoreBefCustomer(String storeBefCustomer) {
		this.storeBefCustomer = storeBefCustomer;
	}

	public String getStoreAftCustomer() {
		return storeAftCustomer;
	}

	public void setStoreAftCustomer(String storeAftCustomer) {
		this.storeAftCustomer = storeAftCustomer;
	}

	public String getStoreWorkingTime() {
		return storeWorkingTime;
	}

	public void setStoreWorkingTime(String storeWorkingTime) {
		this.storeWorkingTime = storeWorkingTime;
	}

	public BigDecimal getStoreFreePrice() {
		return storeFreePrice;
	}

	public void setStoreFreePrice(BigDecimal storeFreePrice) {
		this.storeFreePrice = storeFreePrice;
	}

	public String getStoreIsOwnShop() {
		return storeIsOwnShop;
	}

	public void setStoreIsOwnShop(String storeIsOwnShop) {
		this.storeIsOwnShop = storeIsOwnShop;
	}

	public BigDecimal getStoreMargin() {
		return storeMargin;
	}

	public void setStoreMargin(BigDecimal storeMargin) {
		this.storeMargin = storeMargin;
	}

	public BigDecimal getStoreRealIncomeRatio() {
		return storeRealIncomeRatio;
	}

	public void setStoreRealIncomeRatio(BigDecimal storeRealIncomeRatio) {
		this.storeRealIncomeRatio = storeRealIncomeRatio;
	}

	public BigDecimal getStoreRealGiftRatio() {
		return storeRealGiftRatio;
	}

	public void setStoreRealGiftRatio(BigDecimal storeRealGiftRatio) {
		this.storeRealGiftRatio = storeRealGiftRatio;
	}

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

	public String getStoreStatus() {
		return storeStatus;
	}

	public void setStoreStatus(String storeStatus) {
		this.storeStatus = storeStatus;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getStorePro() {
		return storePro;
	}

	public void setStorePro(String storePro) {
		this.storePro = storePro;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAuditStatusReason() {
		return auditStatusReason;
	}

	public void setAuditStatusReason(String auditStatusReason) {
		this.auditStatusReason = auditStatusReason;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getStoreDividendsTotalMoney() {
		return storeDividendsTotalMoney;
	}

	public void setStoreDividendsTotalMoney(BigDecimal storeDividendsTotalMoney) {
		this.storeDividendsTotalMoney = storeDividendsTotalMoney;
	}

	public BigDecimal getStoreDividendsMoneyBla() {
		return storeDividendsMoneyBla;
	}

	public void setStoreDividendsMoneyBla(BigDecimal storeDividendsMoneyBla) {
		this.storeDividendsMoneyBla = storeDividendsMoneyBla;
	}

	public BigDecimal getStoreDividendsNum() {
		return storeDividendsNum;
	}

	public void setStoreDividendsNum(BigDecimal storeDividendsNum) {
		this.storeDividendsNum = storeDividendsNum;
	}

	public BigDecimal getStoreSaleTotalMoney() {
		return storeSaleTotalMoney;
	}

	public void setStoreSaleTotalMoney(BigDecimal storeSaleTotalMoney) {
		this.storeSaleTotalMoney = storeSaleTotalMoney;
	}

	public BigDecimal getStoreBenefitTotalMoney() {
		return storeBenefitTotalMoney;
	}

	public void setStoreBenefitTotalMoney(BigDecimal storeBenefitTotalMoney) {
		this.storeBenefitTotalMoney = storeBenefitTotalMoney;
	}

	public BigDecimal getStoreDividendsTotalMoneyBla() {
		return storeDividendsTotalMoneyBla;
	}

	public void setStoreDividendsTotalMoneyBla(BigDecimal storeDividendsTotalMoneyBla) {
		this.storeDividendsTotalMoneyBla = storeDividendsTotalMoneyBla;
	}

	public String getIsDivi() {
		return isDivi;
	}

	public void setIsDivi(String isDivi) {
		this.isDivi = isDivi;
	}

	public String getPayMchId() {
		return payMchId;
	}

	public void setPayMchId(String payMchId) {
		this.payMchId = payMchId;
	}

	public String getPayKey() {
		return payKey;
	}

	public void setPayKey(String payKey) {
		this.payKey = payKey;
	}

	public BigDecimal getDeductDiviNum() {
		return deductDiviNum;
	}

	public void setDeductDiviNum(BigDecimal deductDiviNum) {
		this.deductDiviNum = deductDiviNum;
	}

	public BigDecimal getLastTimeDiviMoney() {
		return lastTimeDiviMoney;
	}

	public void setLastTimeDiviMoney(BigDecimal lastTimeDiviMoney) {
		this.lastTimeDiviMoney = lastTimeDiviMoney;
	}

	public String getIsActivityStore() {
		return isActivityStore;
	}

	public void setIsActivityStore(String isActivityStore) {
		this.isActivityStore = isActivityStore;
	}
	
	
}