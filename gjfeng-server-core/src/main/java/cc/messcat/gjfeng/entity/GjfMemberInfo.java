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
 * GjfMemberInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gjf_member_info")
public class GjfMemberInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name = "UNION_ID_", length = 50)
	private String unionId;
	
	@Column(name = "NAME_", length = 50)
	private String name;
	
	@Column(name = "MOBILE_", length = 15)
	private String mobile;
	
	@Column(name = "PASSWORD_", length = 100)
	private String password;
	
	@Column(name = "PAY_PASSWORD_", length = 100)
	private String payPassword;
	
	@Column(name = "NICK_NAME_", length = 255)
	private String nickName;
	
	@Column(name = "SEX_", length = 1)
	private String sex;
	
	@Column(name = "EMAIL_", length = 50)
	private String email;
	
	@Column(name = "ID_CARD_", length = 20)
	private String idCard;
	
	@Column(name = "IMG_ID_CARD_BEFORE_URL_", length = 200)
	private String imgIdCardBeforeUrl;
	
	@Column(name = "IMG_ID_CARD_BEHIND_URL_", length = 200)
	private String imgIdCardBehindUrl;
	
	@Column(name = "IMG_ID_CARD_HANDHELD_URL_", length = 200)
	private String imgIdCardHandheldUrl;
	
	@Column(name = "IMG_HEAD_URL_", length = 500)
	private String imgHeadUrl;
	
	@Column(name = "IMG_QR_URL_", length = 200)
	private String imgQrUrl;
	
	@Column(name = "IMG_APP_QR_URL_", length = 200)
	private String imgAppQrUrl;
	
	@Column(name = "SUPER_ID_", length = 10)
	private Long superId;
	
	@ManyToOne
	@JoinColumn(name="PROVICE_ID_")
	private GjfAddressProvince proviceId;
	
	@ManyToOne
	@JoinColumn(name="CITY_ID_")
	private GjfAddressCity cityId;
	
	@ManyToOne
	@JoinColumn(name="AREA_ID_")
	private GjfAddressArea areaId;
	
	@Column(name = "ADRRESS_", length = 100)
	private String adrress;
	
	@Column(name = "REMARK_", length = 500)
	private String remark;
	
	@Column(name = "BALANCE_MONEY_", precision=10, scale=2)
	private BigDecimal balanceMoney;
	
	@Column(name = "CONSUMPTION_MONEY_", precision=10, scale=2)
	private BigDecimal consumptionMoney;
	
	@Column(name = "CUMULATIVE_MONEY_", precision=10, scale=2)
	private BigDecimal cumulativeMoney;
	
	@Column(name = "LOW_LEVEL_CUMULATIVE_MONEY_", precision=10, scale=2)
	private BigDecimal lowLevelCumulativeMoney;
	
	@Column(name = "WITHDRAWAL_MONEY_", precision=10, scale=2)
	private BigDecimal withdrawalMoney;
	
	@Column(name = "DIVIDENDS_MONEY_BLA_", precision=10, scale=2)
	private BigDecimal dividendsMoneyBla;
	
	@Column(name = "DIVIDENDS_TOTAL_MONEY_", precision=10, scale=2)
	private BigDecimal dividendsTotalMoney;
	
	@Column(name = "DIVIDENDS_NUM_", precision=10, scale=6)
	private BigDecimal dividendsNum;
	
	@Column(name = "DIRECT_MEMBER_TOTAL_MONEY_", precision=10, scale=6)
	private BigDecimal directMemberTotalMoney;
	
	@Column(name = "DIRECT_MERCHANTS_TOTAL_MONEY_", precision=10, scale=6)
	private BigDecimal directMerchantsTotalMoney;
	
	@Column(name = "AGENT_MONEY_", precision=10, scale=2)
	private BigDecimal agentMoney;
	
	@Column(name = "AGENT_TOTAL_MONEY_", precision=10, scale=2)
	private BigDecimal agentTotalMoney;
	
	@Column(name = "INSURANCE_MONEY_", precision=10, scale=2)
	private BigDecimal insuranceMoney;
	
	@Column(name = "ADD_TIME_")
	private Date addTime;
	
	@Column(name = "EDIT_TIME_")
	private Date editTime;
	
	@Column(name = "AGENT_START_DATE_")
	private Date agentStartDate;
	
	@Column(name = "AGENT_END_DATE_")
	private Date agentEndDate;
	
	@Column(name = "LAST_LOGIN_TIME_")
	private Date lastLoginTime;
	
	@Column(name = "IS_READ_NAME_", length = 1)
	private String isReadName;
	
	@Column(name = "TYPE_", length = 1)
	private String type;
	
	@Column(name = "IDENTITY_", length = 1)
	private String identity;
	
	@Column(name = "IS_REPORT_", length = 1)
	private String isReport;
	
	@Column(name = "IS_BUY_", length = 1)
	private String isBuy;
	
	@Column(name = "IS_MESSAGE_", length = 1)
	private String isMessage;
	
	@Column(name = "IS_COMMENTS_", length = 1)
	private String isComments;
	
	@Column(name = "IS_DIVI_", length = 1)
	private String isDivi;
	
	@Column(name = "STATUS_", length = 1)
	private String status;
	
	@Column(name = "IS_DEL_", length = 1)
	private String isDel;
	
	@Column(name = "TOKEN_", length = 200)
	private String token;
	
	@Column(name = "REAL_NAME_STATE_", length = 1)
	private String realNameState;
	
	@Column(name = "LINE_OF_CREDIT_", precision=10, scale=2)
	private BigDecimal lineOfCrade;
	
	@Column(name = "OPEN_ID", length = 100)
	private String openId;
	
	@Column(name = "BIND_TIME")
	private Date bindTime;
	
	@Column(name = "IS_CONFIRM_",length=1)
	private String isConfirm;
		
	@Column(name = "IS_REGISTER_NET_",length=1)
	private String isRegisterNet;
	
	@Column(name = "DIVIDENDS_REWARD_MONEY_", precision=20, scale=2)
	private BigDecimal dividendsRewardMoney;
	
	@Column(name = "RECOMMEND_REWARD_MONEY_", precision=20, scale=2)
	private BigDecimal recommendRewardMoney;
	
	@Column(name = "INDI_REWARD_MONEY_", precision=20, scale=2)
	private BigDecimal indiRewardMoney;
	
	@Column(name = "IS_MEET_CONSUMPTION_", length=1)
	private String isMeetConsumption;
	
	@Column(name = "LAST_DEDUCT_DIVI_NUM_", precision=20, scale=6)
	private BigDecimal lastDeductDiviNum;
	
	@Column(name = "FIRST_CONSUMPTION_TIME_")
	private Date firstConsumptionTime;
	
	@Column(name = "RESERVE_DIVI_NUM_", precision=20, scale=6)
	private BigDecimal reserveDiviNum;
	
	@Column(name = "LAST_GET_BACK_DIVI_MONEY_", precision=20, scale=2)
	private BigDecimal lastGetBackDiviMoney;
	
	@Column(name = "DEDUCT_DIVI_NUM_", precision=20, scale=6)
	private BigDecimal deductDiviNum;
	
	@Column(name = "LAST_TIME_DIVI_MONEY_", precision=20, scale=2)
	private BigDecimal lastTimeDiviMoney;
	
	@Column(name = "OPCENTER_MONEY_", precision=20, scale=2)
	private BigDecimal opcenterMoney;
	
	@Column(name = "IS_OPCENTER_",length=1)
	private String isOpcenter;
	
	@Column(name = "OPCENTER_TOTAL_MONEY_", precision=20, scale=2)
	private BigDecimal opcenterTotalMoney; 
	
	@Column(name = "IS_ACTIVE_MEMBER_",length=1)
	private String isActiveMember;
	
	@Column(name = "MERCHANT_TYPE_",length=1)
	private String merchantType;
	
	@Column(name = "MERCHANT_MODEL_",length=1)
	private String merchantModel;
	
	@Column(name = "DIRECT_MEMBER_MONEY_", precision=20, scale=2)
	private BigDecimal directMemberMoney;
	
	@Column(name = "MEMBER_VOUCHER_MONEY_", precision=20, scale=2)
	private BigDecimal memberVoucherMoney;
	
	@Column(name = "MERCHANT_OPERATIONS_MONEY_", precision=20, scale=2)
	private BigDecimal merchantOperationMoney;
	
	@Column(name = "TWO_STAR_MEMBER_NUM_", length=11)
	private Integer twoStarMemberNum;
	
	@Column(name = "THREE_STAR_MEMBER_NUM_", length=11)
	private Integer threeStarMemberNum;
	
	@Column(name = "TOTAL_MEMBER_NUM_", length=11)
	private Integer totalMemberNum;
	
	@Column(name = "MERCHANT_FIRST_CONSUMPTION_MONEY_", precision=20, scale=2)
	private BigDecimal merchantFirstCousumptionMoney;
	
	@Column(name = "MERCHANT_FIRST_CUMULATIVE_MONEY_", precision=20, scale=2)
	private BigDecimal merchantFirstCumulativeMoney;
	
	@Column(name = "MERCHANT_SECOND_CONSUMPTION_MONEY_", precision=20, scale=2)
	private BigDecimal merchantSecondCousumptionMoney;
	
	@Column(name = "MERCHANT_SECOND_CUMULATIVE_MONEY_", precision=20, scale=2)
	private BigDecimal merchantSecondCumulativeMoney;
	
	@Column(name = "MERCHANT_THREE_CONSUMPTION_MONEY_", precision=20, scale=2)
	private BigDecimal merchantThreeCousumptionMoney;
	
	@Column(name = "MERCHANT_THREE_CUMULATIVE_MONEY_", precision=20, scale=2)
	private BigDecimal merchantThreeCumulativeMoney;
	
	@Column(name = "MERCHANT_FIRST_DIVI_NUM_", precision=20, scale=6)
	private BigDecimal merchantFirstDiviNum;
	
	@Column(name = "MERCHANT_SECOND_DIVI_NUM_", precision=20, scale=6)
	private BigDecimal merchantSecondDiviNum;
	
	@Column(name = "MERCHANT_THREE_DIVI_NUM_", precision=20, scale=6)
	private BigDecimal merchantThreeDiviNum;
		
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

	public String getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(String isConfirm) {
		this.isConfirm = isConfirm;
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

	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}

	public String getMerchantModel() {
		return merchantModel;
	}

	public void setMerchantModel(String merchantModel) {
		this.merchantModel = merchantModel;
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