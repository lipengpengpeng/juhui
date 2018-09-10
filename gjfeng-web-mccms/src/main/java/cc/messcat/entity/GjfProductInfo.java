package cc.messcat.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;

/**
 * GjfProductInfo entity. @author MyEclipse Persistence Tools
 */
public class GjfProductInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	private Long id;
  	
	private GjfStoreInfo storeId;

	private EnterpriseColumn columnId;
	
	private String serialNum;
	
	private String name;
	
	private String keywords;
	
	private String description;
	
	private String content;
	
	private String notice;
	
	private String indate;
	
	private BigDecimal price;
	
	private BigDecimal marketPrice;
	
	private BigDecimal gcostPrice;
	
	private Long salesNum;
	
	private Long viewNum;
	
	private Long collectionNum;
	
	private String isNew;
	
	private String isSale;
	
	private String isHot;
	
	private String isQbuy;
	
	private String isRecommend;
	
	private String imgUrl;
	
	private Date addTime;
	
	private Date updateTime;
	
	private Long repertory;
	
	private BigDecimal score;
	
	private String aduitStatus;
	
	private String remark;
	
	private String remarkInfo;
	
	private String status;
	
	private String token;
	
	private String para1;
	
	private String para2;
	
	private String para3;
	
	private String para4;
	
	private String para5;
	
	private String para6;
	
	private String para7;
	
	private String para8;
	
	private String isCanUserCou;
	
	private BigDecimal postage;
	
	private Integer pointNum;
	
	private BigDecimal pointNicePrice;
	
	private Integer purchasNum;
	
	private BigDecimal benerfitMoney;
	
    private String isWholesale; 
	
	private BigDecimal standardPrice;
	
	private BigDecimal honourPrice;
	
	private String isUpgradePro;
	
	private String multipleNumber;
	
	private BigDecimal derectMemberVor;
	
	private BigDecimal firstDerectMemberVor;

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerialNum() {
		return this.serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getMarketPrice() {
		return this.marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public BigDecimal getGcostPrice() {
		return this.gcostPrice;
	}

	public void setGcostPrice(BigDecimal gcostPrice) {
		this.gcostPrice = gcostPrice;
	}

	public Long getSalesNum() {
		return this.salesNum;
	}

	public void setSalesNum(Long salesNum) {
		this.salesNum = salesNum;
	}

	public Long getViewNum() {
		return this.viewNum;
	}

	public void setViewNum(Long viewNum) {
		this.viewNum = viewNum;
	}

	public Long getCollectionNum() {
		return this.collectionNum;
	}

	public void setCollectionNum(Long collectionNum) {
		this.collectionNum = collectionNum;
	}

	public String getIsNew() {
		return this.isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public String getIsSale() {
		return this.isSale;
	}

	public void setIsSale(String isSale) {
		this.isSale = isSale;
	}

	public String getIsHot() {
		return this.isHot;
	}

	public void setIsHot(String isHot) {
		this.isHot = isHot;
	}

	public String getIsQbuy() {
		return this.isQbuy;
	}

	public void setIsQbuy(String isQbuy) {
		this.isQbuy = isQbuy;
	}

	public String getIsRecommend() {
		return this.isRecommend;
	}

	public void setIsRecommend(String isRecommend) {
		this.isRecommend = isRecommend;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getRepertory() {
		return this.repertory;
	}

	public void setRepertory(Long repertory) {
		this.repertory = repertory;
	}

	public String getAduitStatus() {
		return this.aduitStatus;
	}

	public void setAduitStatus(String aduitStatus) {
		this.aduitStatus = aduitStatus;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPara1() {
		return this.para1;
	}

	public void setPara1(String para1) {
		this.para1 = para1;
	}

	public String getPara2() {
		return this.para2;
	}

	public void setPara2(String para2) {
		this.para2 = para2;
	}

	public String getPara3() {
		return this.para3;
	}

	public void setPara3(String para3) {
		this.para3 = para3;
	}

	public String getPara4() {
		return this.para4;
	}

	public void setPara4(String para4) {
		this.para4 = para4;
	}

	public String getPara5() {
		return this.para5;
	}

	public void setPara5(String para5) {
		this.para5 = para5;
	}

	public String getPara6() {
		return this.para6;
	}

	public void setPara6(String para6) {
		this.para6 = para6;
	}

	public String getPara7() {
		return this.para7;
	}

	public void setPara7(String para7) {
		this.para7 = para7;
	}

	public String getPara8() {
		return this.para8;
	}

	public void setPara8(String para8) {
		this.para8 = para8;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getRemarkInfo() {
		return remarkInfo;
	}

	public void setRemarkInfo(String remarkInfo) {
		this.remarkInfo = remarkInfo;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public GjfStoreInfo getStoreId() {
		return storeId;
	}

	public void setStoreId(GjfStoreInfo storeId) {
		this.storeId = storeId;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public EnterpriseColumn getColumnId() {
		return columnId;
	}

	public void setColumnId(EnterpriseColumn columnId) {
		this.columnId = columnId;
	}

	public String getIndate() {
		return indate;
	}

	public void setIndate(String indate) {
		this.indate = indate;
	}

	public String getIsCanUserCou() {
		return isCanUserCou;
	}

	public void setIsCanUserCou(String isCanUserCou) {
		this.isCanUserCou = isCanUserCou;
	}

	public BigDecimal getPostage() {
		return postage;
	}

	public void setPostage(BigDecimal postage) {
		this.postage = postage;
	}

	public Integer getPointNum() {
		return pointNum;
	}

	public void setPointNum(Integer pointNum) {
		this.pointNum = pointNum;
	}

	public BigDecimal getPointNicePrice() {
		return pointNicePrice;
	}

	public void setPointNicePrice(BigDecimal pointNicePrice) {
		this.pointNicePrice = pointNicePrice;
	}

	public Integer getPurchasNum() {
		return purchasNum;
	}

	public void setPurchasNum(Integer purchasNum) {
		this.purchasNum = purchasNum;
	}

	public BigDecimal getBenerfitMoney() {
		return benerfitMoney;
	}

	public void setBenerfitMoney(BigDecimal benerfitMoney) {
		this.benerfitMoney = benerfitMoney;
	}

	public String getIsWholesale() {
		return isWholesale;
	}

	public void setIsWholesale(String isWholesale) {
		this.isWholesale = isWholesale;
	}

	public BigDecimal getStandardPrice() {
		return standardPrice;
	}

	public void setStandardPrice(BigDecimal standardPrice) {
		this.standardPrice = standardPrice;
	}

	public BigDecimal getHonourPrice() {
		return honourPrice;
	}

	public void setHonourPrice(BigDecimal honourPrice) {
		this.honourPrice = honourPrice;
	}

	public String getIsUpgradePro() {
		return isUpgradePro;
	}

	public void setIsUpgradePro(String isUpgradePro) {
		this.isUpgradePro = isUpgradePro;
	}

	public String getMultipleNumber() {
		return multipleNumber;
	}

	public void setMultipleNumber(String multipleNumber) {
		this.multipleNumber = multipleNumber;
	}

	public BigDecimal getDerectMemberVor() {
		return derectMemberVor;
	}

	public void setDerectMemberVor(BigDecimal derectMemberVor) {
		this.derectMemberVor = derectMemberVor;
	}

	public BigDecimal getFirstDerectMemberVor() {
		return firstDerectMemberVor;
	}

	public void setFirstDerectMemberVor(BigDecimal firstDerectMemberVor) {
		this.firstDerectMemberVor = firstDerectMemberVor;
	}
	
	

}