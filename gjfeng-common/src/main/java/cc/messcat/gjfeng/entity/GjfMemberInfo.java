package cc.messcat.gjfeng.entity;

import java.math.BigDecimal;
import java.util.Date;


/**
 * GjfMemberInfo entity. @author MyEclipse Persistence Tools
 */
public class GjfMemberInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	private Long id;
	
	private String unionId;
	
	private String name;
	
	private String mobile;
	
	private String password;
	
	private String payPassword;
	
	private String nickName;
	
	private String sex;
	
	private String email;
	
	private String idCard;
	
	private String imgIdCardBeforeUrl;
	
	private String imgIdCardBehindUrl;
	
	private String imgIdCardHandheldUrl;
	
	private String imgHeadUrl;
	
	private String imgQrUrl;
	
	private String imgAppQrUrl;
	
	private Long superId;
	
	private GjfAddressProvince proviceId;
	
	private GjfAddressCity cityId;
	
	private GjfAddressArea areaId;
	
	private String adrress;
	
	private String remark;
	
	private BigDecimal balanceMoney;
	
	private BigDecimal consumptionMoney;
	
	private BigDecimal cumulativeMoney;
	
	private BigDecimal lowLevelCumulativeMoney;
	
	private BigDecimal withdrawalMoney;
	
	private BigDecimal dividendsMoneyBla;
	
	private BigDecimal dividendsTotalMoney;
	
	private BigDecimal dividendsNum;
	
	private BigDecimal directMemberTotalMoney;
	
	private BigDecimal directMerchantsTotalMoney;
	
	private BigDecimal agentMoney;
	
	private BigDecimal agentTotalMoney;
	
	private BigDecimal insuranceMoney;
	
	private Date addTime;
	
	private Date editTime;
	
	private Date agentStartDate;
	
	private Date agentEndDate;
	
	private Date lastLoginTime;
	
	private String isReadName;
	
	private String type;
	
	private String identity;
	
	private String isReport;
	
	private String isBuy;
	
	private String isMessage;
	
	private String isComments;
	
	private String isDivi;
	
	private String status;
	
	private String isDel;
	
	private String token;
	
	private String realNameState;
	
	private BigDecimal lineOfCrade;
	
	private String openId;
	
	private Date bindTime;
	
	private String isConfirm;
	
	private String isRegisterNet;
	
	private BigDecimal dividendsRewardMoney;
	
	private BigDecimal recommendRewardMoney;
	
	private BigDecimal indiRewardMoney;
	
	private String isMeetConsumption;
	
	private BigDecimal lastDeductDiviNum;
	
	private Date firstConsumptionTime;
	
	private BigDecimal reserveDiviNum;
	
	private BigDecimal lastGetBackDiviMoney;
	
	private BigDecimal deductDiviNum;
	
	private BigDecimal lastTimeDiviMoney;
	
	private BigDecimal opcenterMoney;
	
	private String isOpcenter;
	
	private BigDecimal opcenterTotalMoney; 
	
	private String isActiveMember;
	
	private String merchantModel;
	
	private String merchantType;
	
	private BigDecimal directMemberMoney;
	
	private BigDecimal memberVoucherMoney;
	
	private BigDecimal merchantOperationMoney;
	
	private Integer twoStarMemberNum;
	
	private Integer threeStarMemberNum;
	
	private Integer totalMemberNum;
	
	private BigDecimal merchantFirstCousumptionMoney;
	
	private BigDecimal merchantFirstCumulativeMoney;
	
	private BigDecimal merchantSecondCousumptionMoney;
	
	private BigDecimal merchantSecondCumulativeMoney;
	
	private BigDecimal merchantThreeCousumptionMoney;
	
	private BigDecimal merchantThreeCumulativeMoney;
	
	private BigDecimal merchantFirstDiviNum;
	
	private BigDecimal merchantSecondDiviNum;
	
	private BigDecimal merchantThreeDiviNum;

	public String getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(String isConfirm) {
		this.isConfirm = isConfirm;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getImgIdCardBeforeUrl() {
		return imgIdCardBeforeUrl;
	}

	public void setImgIdCardBeforeUrl(String imgIdCardBeforeUrl) {
		this.imgIdCardBeforeUrl = imgIdCardBeforeUrl;
	}

	public String getImgIdCardBehindUrl() {
		return imgIdCardBehindUrl;
	}

	public void setImgIdCardBehindUrl(String imgIdCardBehindUrl) {
		this.imgIdCardBehindUrl = imgIdCardBehindUrl;
	}

	public String getImgHeadUrl() {
		return imgHeadUrl;
	}

	public void setImgHeadUrl(String imgHeadUrl) {
		this.imgHeadUrl = imgHeadUrl;
	}

	public String getImgQrUrl() {
		return imgQrUrl;
	}

	public void setImgQrUrl(String imgQrUrl) {
		this.imgQrUrl = imgQrUrl;
	}

	public Long getSuperId() {
		return superId;
	}

	public void setSuperId(Long superId) {
		this.superId = superId;
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

	public String getAdrress() {
		return adrress;
	}

	public void setAdrress(String adrress) {
		this.adrress = adrress;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getBalanceMoney() {
		return balanceMoney;
	}

	public void setBalanceMoney(BigDecimal balanceMoney) {
		this.balanceMoney = balanceMoney;
	}

	public BigDecimal getConsumptionMoney() {
		return consumptionMoney;
	}

	public void setConsumptionMoney(BigDecimal consumptionMoney) {
		this.consumptionMoney = consumptionMoney;
	}

	public BigDecimal getCumulativeMoney() {
		return cumulativeMoney;
	}

	public void setCumulativeMoney(BigDecimal cumulativeMoney) {
		this.cumulativeMoney = cumulativeMoney;
	}

	public BigDecimal getWithdrawalMoney() {
		return withdrawalMoney;
	}

	public void setWithdrawalMoney(BigDecimal withdrawalMoney) {
		this.withdrawalMoney = withdrawalMoney;
	}

	public BigDecimal getDividendsMoneyBla() {
		return dividendsMoneyBla;
	}

	public void setDividendsMoneyBla(BigDecimal dividendsMoneyBla) {
		this.dividendsMoneyBla = dividendsMoneyBla;
	}

	public BigDecimal getDividendsTotalMoney() {
		return dividendsTotalMoney;
	}

	public void setDividendsTotalMoney(BigDecimal dividendsTotalMoney) {
		this.dividendsTotalMoney = dividendsTotalMoney;
	}

	public BigDecimal getDividendsNum() {
		return dividendsNum;
	}

	public void setDividendsNum(BigDecimal dividendsNum) {
		this.dividendsNum = dividendsNum;
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

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getIsReadName() {
		return isReadName;
	}

	public void setIsReadName(String isReadName) {
		this.isReadName = isReadName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getIsReport() {
		return isReport;
	}

	public void setIsReport(String isReport) {
		this.isReport = isReport;
	}

	public String getIsBuy() {
		return isBuy;
	}

	public void setIsBuy(String isBuy) {
		this.isBuy = isBuy;
	}

	public String getIsMessage() {
		return isMessage;
	}

	public void setIsMessage(String isMessage) {
		this.isMessage = isMessage;
	}

	public String getIsComments() {
		return isComments;
	}

	public void setIsComments(String isComments) {
		this.isComments = isComments;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getImgIdCardHandheldUrl() {
		return imgIdCardHandheldUrl;
	}

	public void setImgIdCardHandheldUrl(String imgIdCardHandheldUrl) {
		this.imgIdCardHandheldUrl = imgIdCardHandheldUrl;
	}

	public BigDecimal getLowLevelCumulativeMoney() {
		return lowLevelCumulativeMoney;
	}

	public void setLowLevelCumulativeMoney(BigDecimal lowLevelCumulativeMoney) {
		this.lowLevelCumulativeMoney = lowLevelCumulativeMoney;
	}

	public String getRealNameState() {
		return realNameState;
	}

	public void setRealNameState(String realNameState) {
		this.realNameState = realNameState;
	}

	public BigDecimal getAgentMoney() {
		return agentMoney;
	}

	public void setAgentMoney(BigDecimal agentMoney) {
		this.agentMoney = agentMoney;
	}

	public BigDecimal getAgentTotalMoney() {
		return agentTotalMoney;
	}

	public void setAgentTotalMoney(BigDecimal agentTotalMoney) {
		this.agentTotalMoney = agentTotalMoney;
	}

	public Date getAgentStartDate() {
		return agentStartDate;
	}

	public void setAgentStartDate(Date agentStartDate) {
		this.agentStartDate = agentStartDate;
	}

	public Date getAgentEndDate() {
		return agentEndDate;
	}

	public void setAgentEndDate(Date agentEndDate) {
		this.agentEndDate = agentEndDate;
	}

	public BigDecimal getLineOfCrade() {
		return lineOfCrade;
	}

	public void setLineOfCrade(BigDecimal lineOfCrade) {
		this.lineOfCrade = lineOfCrade;
	}

	public BigDecimal getInsuranceMoney() {
		return insuranceMoney;
	}

	public void setInsuranceMoney(BigDecimal insuranceMoney) {
		this.insuranceMoney = insuranceMoney;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public BigDecimal getDirectMemberTotalMoney() {
		return directMemberTotalMoney;
	}

	public void setDirectMemberTotalMoney(BigDecimal directMemberTotalMoney) {
		this.directMemberTotalMoney = directMemberTotalMoney;
	}

	public BigDecimal getDirectMerchantsTotalMoney() {
		return directMerchantsTotalMoney;
	}

	public void setDirectMerchantsTotalMoney(BigDecimal directMerchantsTotalMoney) {
		this.directMerchantsTotalMoney = directMerchantsTotalMoney;
	}

	public Date getBindTime() {
		return bindTime;
	}

	public void setBindTime(Date bindTime) {
		this.bindTime = bindTime;
	}

	public String getIsDivi() {
		return isDivi;
	}

	public void setIsDivi(String isDivi) {
		this.isDivi = isDivi;
	}

	public String getImgAppQrUrl() {
		return imgAppQrUrl;
	}

	public void setImgAppQrUrl(String imgAppQrUrl) {
		this.imgAppQrUrl = imgAppQrUrl;
	}

	public String getIsRegisterNet() {
		return isRegisterNet;
	}

	public void setIsRegisterNet(String isRegisterNet) {
		this.isRegisterNet = isRegisterNet;
	}

	public BigDecimal getDividendsRewardMoney() {
		return dividendsRewardMoney;
	}

	public void setDividendsRewardMoney(BigDecimal dividendsRewardMoney) {
		this.dividendsRewardMoney = dividendsRewardMoney;
	}

	public BigDecimal getRecommendRewardMoney() {
		return recommendRewardMoney;
	}

	public void setRecommendRewardMoney(BigDecimal recommendRewardMoney) {
		this.recommendRewardMoney = recommendRewardMoney;
	}

	public BigDecimal getIndiRewardMoney() {
		return indiRewardMoney;
	}

	public void setIndiRewardMoney(BigDecimal indiRewardMoney) {
		this.indiRewardMoney = indiRewardMoney;
	}

	public String getIsMeetConsumption() {
		return isMeetConsumption;
	}

	public void setIsMeetConsumption(String isMeetConsumption) {
		this.isMeetConsumption = isMeetConsumption;
	}

	public BigDecimal getLastDeductDiviNum() {
		return lastDeductDiviNum;
	}

	public void setLastDeductDiviNum(BigDecimal lastDeductDiviNum) {
		this.lastDeductDiviNum = lastDeductDiviNum;
	}

	public Date getFirstConsumptionTime() {
		return firstConsumptionTime;
	}

	public void setFirstConsumptionTime(Date firstConsumptionTime) {
		this.firstConsumptionTime = firstConsumptionTime;
	}

	public BigDecimal getReserveDiviNum() {
		return reserveDiviNum;
	}

	public void setReserveDiviNum(BigDecimal reserveDiviNum) {
		this.reserveDiviNum = reserveDiviNum;
	}

	public BigDecimal getLastGetBackDiviMoney() {
		return lastGetBackDiviMoney;
	}

	public void setLastGetBackDiviMoney(BigDecimal lastGetBackDiviMoney) {
		this.lastGetBackDiviMoney = lastGetBackDiviMoney;
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

	public BigDecimal getOpcenterMoney() {
		return opcenterMoney;
	}

	public void setOpcenterMoney(BigDecimal opcenterMoney) {
		this.opcenterMoney = opcenterMoney;
	}

	public String getIsOpcenter() {
		return isOpcenter;
	}

	public void setIsOpcenter(String isOpcenter) {
		this.isOpcenter = isOpcenter;
	}

	public BigDecimal getOpcenterTotalMoney() {
		return opcenterTotalMoney;
	}

	public void setOpcenterTotalMoney(BigDecimal opcenterTotalMoney) {
		this.opcenterTotalMoney = opcenterTotalMoney;
	}

	public String getIsActiveMember() {
		return isActiveMember;
	}

	public void setIsActiveMember(String isActiveMember) {
		this.isActiveMember = isActiveMember;
	}

	public String getMerchantModel() {
		return merchantModel;
	}

	public void setMerchantModel(String merchantModel) {
		this.merchantModel = merchantModel;
	}

	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}

	public BigDecimal getDirectMemberMoney() {
		return directMemberMoney;
	}

	public void setDirectMemberMoney(BigDecimal directMemberMoney) {
		this.directMemberMoney = directMemberMoney;
	}

	public BigDecimal getMemberVoucherMoney() {
		return memberVoucherMoney;
	}

	public void setMemberVoucherMoney(BigDecimal memberVoucherMoney) {
		this.memberVoucherMoney = memberVoucherMoney;
	}

	public BigDecimal getMerchantOperationMoney() {
		return merchantOperationMoney;
	}

	public void setMerchantOperationMoney(BigDecimal merchantOperationMoney) {
		this.merchantOperationMoney = merchantOperationMoney;
	}

	public Integer getTwoStarMemberNum() {
		return twoStarMemberNum;
	}

	public void setTwoStarMemberNum(Integer twoStarMemberNum) {
		this.twoStarMemberNum = twoStarMemberNum;
	}

	public Integer getThreeStarMemberNum() {
		return threeStarMemberNum;
	}

	public void setThreeStarMemberNum(Integer threeStarMemberNum) {
		this.threeStarMemberNum = threeStarMemberNum;
	}

	public Integer getTotalMemberNum() {
		return totalMemberNum;
	}

	public void setTotalMemberNum(Integer totalMemberNum) {
		this.totalMemberNum = totalMemberNum;
	}

	public BigDecimal getMerchantFirstCousumptionMoney() {
		return merchantFirstCousumptionMoney;
	}

	public void setMerchantFirstCousumptionMoney(BigDecimal merchantFirstCousumptionMoney) {
		this.merchantFirstCousumptionMoney = merchantFirstCousumptionMoney;
	}

	public BigDecimal getMerchantFirstCumulativeMoney() {
		return merchantFirstCumulativeMoney;
	}

	public void setMerchantFirstCumulativeMoney(BigDecimal merchantFirstCumulativeMoney) {
		this.merchantFirstCumulativeMoney = merchantFirstCumulativeMoney;
	}

	public BigDecimal getMerchantSecondCousumptionMoney() {
		return merchantSecondCousumptionMoney;
	}

	public void setMerchantSecondCousumptionMoney(BigDecimal merchantSecondCousumptionMoney) {
		this.merchantSecondCousumptionMoney = merchantSecondCousumptionMoney;
	}

	public BigDecimal getMerchantSecondCumulativeMoney() {
		return merchantSecondCumulativeMoney;
	}

	public void setMerchantSecondCumulativeMoney(BigDecimal merchantSecondCumulativeMoney) {
		this.merchantSecondCumulativeMoney = merchantSecondCumulativeMoney;
	}

	public BigDecimal getMerchantThreeCousumptionMoney() {
		return merchantThreeCousumptionMoney;
	}

	public void setMerchantThreeCousumptionMoney(BigDecimal merchantThreeCousumptionMoney) {
		this.merchantThreeCousumptionMoney = merchantThreeCousumptionMoney;
	}

	public BigDecimal getMerchantThreeCumulativeMoney() {
		return merchantThreeCumulativeMoney;
	}

	public void setMerchantThreeCumulativeMoney(BigDecimal merchantThreeCumulativeMoney) {
		this.merchantThreeCumulativeMoney = merchantThreeCumulativeMoney;
	}

	public BigDecimal getMerchantFirstDiviNum() {
		return merchantFirstDiviNum;
	}

	public void setMerchantFirstDiviNum(BigDecimal merchantFirstDiviNum) {
		this.merchantFirstDiviNum = merchantFirstDiviNum;
	}

	public BigDecimal getMerchantSecondDiviNum() {
		return merchantSecondDiviNum;
	}

	public void setMerchantSecondDiviNum(BigDecimal merchantSecondDiviNum) {
		this.merchantSecondDiviNum = merchantSecondDiviNum;
	}

	public BigDecimal getMerchantThreeDiviNum() {
		return merchantThreeDiviNum;
	}

	public void setMerchantThreeDiviNum(BigDecimal merchantThreeDiviNum) {
		this.merchantThreeDiviNum = merchantThreeDiviNum;
	}
	
	
			
}