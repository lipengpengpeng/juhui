package cc.messcat.vo;

import java.io.File;

/**
 * 商家入驻步骤，模型驱动
 * @author Administrator
 *
 */
public class StoreJoinApplyVo {
	
	//店铺及联系人信息
	private String company_name;//公司名称
	//公司地址
	private String pri;//省份
	private String city;//城市
	private String area;//地区
	//
	private String priSecond;//省份
	private String citySecond;//城市
	private String areaSecond;//地区
	private String companyAddress; //eg:广东省 广州市 天河区
	private String company_address_detail;//公司详细地址
	private String contacts_name;//联系人姓名
	private String contacts_phone;//联系人电话
	private String contacts_email;//联系人邮箱
	
	//b2c
	private Integer  company_registered_capital;//注册资金
	
	
	//身体证信息
	//身体证姓名 b2c  经营范围b2c
	private String business_sphere;
	//身份证号c2c
	private String business_licence_number;//营业执照号b2c
	//手执身份证照片
	//private String IDphoto;
	private File IDphoto;
	private String IDphotoFileName;
	private String IDphotoContentType;
	
	//营业执照信息（副本）
	private String business_licence_start;//营业执照有效期开始
	private String business_licence_end;//营业执照有效期结束
	//营业执照电子版
	private File business_licence_number_electronic;
	private String business_licence_number_electronicFileName;
	private String business_licence_number_electronicContentType;
	//组织机构代码
	private String organization_code;
	//step2
	private String bank_account_name;
	private String bank_account_number;
	private String bank_name;//开户银行支行名称
	
	private Boolean is_settlement_account;//是否此账号为结算账号
	
	private String settlement_bank_account_name;//银行开户名
	private String settlement_bank_account_number;//公司银行账号
	private String settlement_bank_name;
	private String tax_registration_certificate;
	
	//step3
	private String sellerName;//卖家帐号
	private String storeName;//店铺名称
	private String storeNo;//店铺号码
	private String sc_id;//店铺分类
	private String store_class_ids[];//经营类目编号集合
	private String store_class_names[];//店铺分类名称集合
 
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String companyName) {
		company_name = companyName;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getCompany_address_detail() {
		return company_address_detail;
	}
	public void setCompany_address_detail(String companyAddressDetail) {
		company_address_detail = companyAddressDetail;
	}
	public String getContacts_name() {
		return contacts_name;
	}
	public void setContacts_name(String contactsName) {
		contacts_name = contactsName;
	}
	public String getContacts_phone() {
		return contacts_phone;
	}
	public void setContacts_phone(String contactsPhone) {
		contacts_phone = contactsPhone;
	}
	public String getContacts_email() {
		return contacts_email;
	}
	public void setContacts_email(String contactsEmail) {
		contacts_email = contactsEmail;
	}
	public String getBusiness_sphere() {
		return business_sphere;
	}
	public void setBusiness_sphere(String businessSphere) {
		business_sphere = businessSphere;
	}
	public String getBusiness_licence_number() {
		return business_licence_number;
	}
	public void setBusiness_licence_number(String businessLicenceNumber) {
		business_licence_number = businessLicenceNumber;
	}
	public File getIDphoto() {
		return IDphoto;
	}
	public void setIDphoto(File iDphoto) {
		IDphoto = iDphoto;
	}
	public String getIDphotoFileName() {
		return IDphotoFileName;
	}
	public void setIDphotoFileName(String iDphotoFileName) {
		IDphotoFileName = iDphotoFileName;
	}
	public String getIDphotoContentType() {
		return IDphotoContentType;
	}
	public void setIDphotoContentType(String iDphotoContentType) {
		IDphotoContentType = iDphotoContentType;
	}
	public String getPri() {
		return pri;
	}
	public void setPri(String pri) {
		this.pri = pri;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getSettlement_bank_account_name() {
		return settlement_bank_account_name;
	}
	public void setSettlement_bank_account_name(String settlementBankAccountName) {
		settlement_bank_account_name = settlementBankAccountName;
	}
	public String getSettlement_bank_account_number() {
		return settlement_bank_account_number;
	}
	public void setSettlement_bank_account_number(String settlementBankAccountNumber) {
		settlement_bank_account_number = settlementBankAccountNumber;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String[] getStore_class_ids() {
		return store_class_ids;
	}
	public void setStore_class_ids(String[] storeClassIds) {
		store_class_ids = storeClassIds;
	}
	public String getSc_id() {
		return sc_id;
	}
	public void setSc_id(String scId) {
		sc_id = scId;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String[] getStore_class_names() {
		return store_class_names;
	}
	public void setStore_class_names(String[] storeClassNames) {
		store_class_names = storeClassNames;
	}
	public Integer getCompany_registered_capital() {
		return company_registered_capital;
	}
	public void setCompany_registered_capital(Integer companyRegisteredCapital) {
		company_registered_capital = companyRegisteredCapital;
	}
 
	public String getBusiness_licence_start() {
		return business_licence_start;
	}
	public void setBusiness_licence_start(String businessLicenceStart) {
		business_licence_start = businessLicenceStart;
	}
	public String getBusiness_licence_end() {
		return business_licence_end;
	}
	public void setBusiness_licence_end(String businessLicenceEnd) {
		business_licence_end = businessLicenceEnd;
	}
	public File getBusiness_licence_number_electronic() {
		return business_licence_number_electronic;
	}
	public void setBusiness_licence_number_electronic(
			File businessLicenceNumberElectronic) {
		business_licence_number_electronic = businessLicenceNumberElectronic;
	}
	public String getBusiness_licence_number_electronicFileName() {
		return business_licence_number_electronicFileName;
	}
	public void setBusiness_licence_number_electronicFileName(
			String businessLicenceNumberElectronicFileName) {
		business_licence_number_electronicFileName = businessLicenceNumberElectronicFileName;
	}
	public String getBusiness_licence_number_electronicContentType() {
		return business_licence_number_electronicContentType;
	}
	public void setBusiness_licence_number_electronicContentType(
			String businessLicenceNumberElectronicContentType) {
		business_licence_number_electronicContentType = businessLicenceNumberElectronicContentType;
	}
	public String getPriSecond() {
		return priSecond;
	}
	public void setPriSecond(String priSecond) {
		this.priSecond = priSecond;
	}
	public String getCitySecond() {
		return citySecond;
	}
	public void setCitySecond(String citySecond) {
		this.citySecond = citySecond;
	}
	public String getAreaSecond() {
		return areaSecond;
	}
	public void setAreaSecond(String areaSecond) {
		this.areaSecond = areaSecond;
	}
	public String getOrganization_code() {
		return organization_code;
	}
	public void setOrganization_code(String organizationCode) {
		organization_code = organizationCode;
	}
	public Boolean getIs_settlement_account() {
		return is_settlement_account;
	}
	public void setIs_settlement_account(Boolean isSettlementAccount) {
		is_settlement_account = isSettlementAccount;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bankName) {
		bank_name = bankName;
	}
	public String getBank_account_name() {
		return bank_account_name;
	}
	public void setBank_account_name(String bankAccountName) {
		bank_account_name = bankAccountName;
	}
	public String getBank_account_number() {
		return bank_account_number;
	}
	public void setBank_account_number(String bankAccountNumber) {
		bank_account_number = bankAccountNumber;
	}
	public String getSettlement_bank_name() {
		return settlement_bank_name;
	}
	public void setSettlement_bank_name(String settlementBankName) {
		settlement_bank_name = settlementBankName;
	}
	public String getTax_registration_certificate() {
		return tax_registration_certificate;
	}
	public void setTax_registration_certificate(String taxRegistrationCertificate) {
		tax_registration_certificate = taxRegistrationCertificate;
	}
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}

}
