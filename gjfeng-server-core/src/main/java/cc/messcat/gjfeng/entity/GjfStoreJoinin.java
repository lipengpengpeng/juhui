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
 * GjfStoreJoinin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gjf_store_joinin")
public class GjfStoreJoinin implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "STORE_ID_")
	private GjfStoreInfo storeId;

	@Column(name = "COMPANY_NAME_", length = 50)
	private String companyName;

	@Column(name = "COMPANY_PHONE_", length = 20)
	private String companyPhone;

	@Column(name = "BUSINESS_LICENCE_NUMBER_", length = 50)
	private String businessLicenceNumber;

	@Column(name = "BUSINESS_LICENCE_ADDRESS_", length = 50)
	private String businessLicenceAddress;

	@Column(name = "BUSINESS_LICENCE_START_")
	private String businessLicenceStart;

	@Column(name = "BUSINESS_LICENCE_END_")
	private String businessLicenceEnd;

	@Column(name = "BUSINESS_SPHERE_", length = 1000)
	private String businessSphere;

	@Column(name = "BUSINESS_LICENCE_NUMBER_ELECTRONIC_", length = 200)
	private String businessLicenceNumberElectronic;

	@Column(name = "COMPANY_REGISTERED_CAPITAL_", precision = 10, scale = 2)
	private BigDecimal companyRegisteredCapital;
	
	@Column(name = "TAX_REGISTRATION_CERTIFICATE_", length = 100)
	private String taxRegistrationCertificate;
	
	@Column(name = "ORGANIZATION_CODE_", length = 100)
	private String organizationCode;

	@Column(name = "BANK_ACCOUNT_NAME_", length = 50)
	private String bankAccountName;

	@Column(name = "BANK_ACCOUNT_NUMBER_", length = 50)
	private String bankAccountNumber;

	@Column(name = "BANK_NAME_", length = 50)
	private String bankName;

	@Column(name = "BANK_ADDRESS_", length = 50)
	private String bankAddress;

	@Column(name = "IS_SETTLEMENT_ACCOUNT_", length = 1)
	private String isSettlementAccount;

	@Column(name = "SETTLEMENT_BANK_ACCOUNT_NAME_", length = 50)
	private String settlementBankAccountName;

	@Column(name = "SETTLEMENT_BANK_ACCOUNT_NUMBER_", length = 50)
	private String settlementBankAccountNumber;

	@Column(name = "SETTLEMENT_BANK_NAME_", length = 50)
	private String settlementBankName;

	@Column(name = "SETTLEMENT_BANK_ADDRESS_", length = 50)
	private String settlementBankAddress;

	@Column(name = "JOININ_MESSAGE_", length = 200)
	private String joininMessage;

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyPhone() {
		return this.companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public String getBusinessLicenceNumber() {
		return this.businessLicenceNumber;
	}

	public void setBusinessLicenceNumber(String businessLicenceNumber) {
		this.businessLicenceNumber = businessLicenceNumber;
	}

	public String getBusinessLicenceAddress() {
		return this.businessLicenceAddress;
	}

	public void setBusinessLicenceAddress(String businessLicenceAddress) {
		this.businessLicenceAddress = businessLicenceAddress;
	}

	public String getBusinessSphere() {
		return this.businessSphere;
	}

	public void setBusinessSphere(String businessSphere) {
		this.businessSphere = businessSphere;
	}

	public String getBusinessLicenceNumberElectronic() {
		return this.businessLicenceNumberElectronic;
	}

	public void setBusinessLicenceNumberElectronic(String businessLicenceNumberElectronic) {
		this.businessLicenceNumberElectronic = businessLicenceNumberElectronic;
	}

	public String getBankAccountName() {
		return this.bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getBankAccountNumber() {
		return this.bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAddress() {
		return this.bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public String getIsSettlementAccount() {
		return this.isSettlementAccount;
	}

	public void setIsSettlementAccount(String isSettlementAccount) {
		this.isSettlementAccount = isSettlementAccount;
	}

	public String getSettlementBankAccountName() {
		return this.settlementBankAccountName;
	}

	public void setSettlementBankAccountName(String settlementBankAccountName) {
		this.settlementBankAccountName = settlementBankAccountName;
	}

	public String getSettlementBankAccountNumber() {
		return this.settlementBankAccountNumber;
	}

	public void setSettlementBankAccountNumber(String settlementBankAccountNumber) {
		this.settlementBankAccountNumber = settlementBankAccountNumber;
	}

	public String getSettlementBankName() {
		return this.settlementBankName;
	}

	public void setSettlementBankName(String settlementBankName) {
		this.settlementBankName = settlementBankName;
	}

	public String getSettlementBankAddress() {
		return this.settlementBankAddress;
	}

	public void setSettlementBankAddress(String settlementBankAddress) {
		this.settlementBankAddress = settlementBankAddress;
	}

	public String getJoininMessage() {
		return this.joininMessage;
	}

	public void setJoininMessage(String joininMessage) {
		this.joininMessage = joininMessage;
	}

	public GjfStoreInfo getStoreId() {
		return storeId;
	}

	public void setStoreId(GjfStoreInfo storeId) {
		this.storeId = storeId;
	}

	public BigDecimal getCompanyRegisteredCapital() {
		return companyRegisteredCapital;
	}

	public void setCompanyRegisteredCapital(BigDecimal companyRegisteredCapital) {
		this.companyRegisteredCapital = companyRegisteredCapital;
	}

	public String getTaxRegistrationCertificate() {
		return taxRegistrationCertificate;
	}

	public void setTaxRegistrationCertificate(String taxRegistrationCertificate) {
		this.taxRegistrationCertificate = taxRegistrationCertificate;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public String getBusinessLicenceStart() {
		return businessLicenceStart;
	}

	public void setBusinessLicenceStart(String businessLicenceStart) {
		this.businessLicenceStart = businessLicenceStart;
	}

	public String getBusinessLicenceEnd() {
		return businessLicenceEnd;
	}

	public void setBusinessLicenceEnd(String businessLicenceEnd) {
		this.businessLicenceEnd = businessLicenceEnd;
	}

}