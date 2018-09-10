package cc.messcat.gjfeng.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * GjfStoreInfo entity. @author MyEclipse Persistence Tools
 */
public class GjfStoreInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	private Long id;
	
	private String storeName;
	
	private String storeNum;
	
	private GjfMemberInfo memberId;
	
	private String sellerName;
	
	private String sellerMobile;
	
	private String sellerEmail;
	
	private GjfAddressProvince provinceId;
	
	private GjfAddressCity cityId;
	
	private GjfAddressArea areaId;
	
	private String addressDetail;
	
	private Double longitude;
	
	private Double latitude;
	
	private Date storeAddTime;
	
	private Date storeAduitTime;
	
	private Date storeStartTime;
	
	private Date storeEndTime;
	
	private String storeCloseRemark;
	
	private String storeLogoUrl;
	
	private String storeBanner;
	
	private String storeKeywords;
	
	private String storeDescription;
	
	private String storeRecommend;
	
	private BigDecimal storeDividendsTotalMoney;
	
	private BigDecimal storeDividendsTotalMoneyBla;
	
	private BigDecimal storeDividendsMoneyBla;
	
	private BigDecimal storeDividendsNum;
	
	private BigDecimal storeSaleTotalMoney;
	
	private BigDecimal storeBenefitTotalMoney;
	
	private Long storeCreditScore;
	
	private BigDecimal storeDesccreditScore;
	
	private BigDecimal storeServiceCreditScore;
	
	private BigDecimal storeDeliveryCreditScore;
	
	private Long storeCollectNum;
	
	private Long storeSalesNum;
	
	private String storeBefCustomer;
	
	private String storeAftCustomer;
	
	private String storeWorkingTime;
	
	private BigDecimal storeFreePrice;
	
	private String storeIsOwnShop;
	
	private BigDecimal storeMargin;
	
	private BigDecimal storeRealIncomeRatio;
	
	private BigDecimal storeRealGiftRatio;
	
	private String storePro;
	
	private String storeType;
	
	private String storeStatus;
	
	private String auditStatusReason;
	
	private String isDivi;
	
	private String isDel;
	
	private String token;		

	private String payMchId;
	
	private String payKey;
	
	private BigDecimal deductDiviNum;
	
	private BigDecimal lastTimeDiviMoney;
	
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

	

	public BigDecimal getStoreDividendsTotalMoneyBla() {
		return storeDividendsTotalMoneyBla;
	}

	public void setStoreDividendsTotalMoneyBla(BigDecimal storeDividendsTotalMoneyBla) {
		this.storeDividendsTotalMoneyBla = storeDividendsTotalMoneyBla;
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