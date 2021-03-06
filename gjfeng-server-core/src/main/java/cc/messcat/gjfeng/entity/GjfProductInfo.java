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
 * GjfProductInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gjf_product_info")
public class GjfProductInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="STORE_ID_")
	private GjfStoreInfo storeId;
	
	@ManyToOne
	@JoinColumn(name="COLUMN_ID_")
	private GjfEnterpriseColumn columnId;
	
	@Column(name = "SERIAL_NUM_", length = 50)
	private String serialNum;
	
	@Column(name = "NAME_", length = 200)
	private String name;
	
	@Column(name = "KEYWORDS_", length = 200)
	private String keywords;
	
	@Column(name = "DESCRIPTION_", length = 500)
	private String description;
	
	@Column(name = "CONTENT_")
	private String content;
	
	@Column(name = "NOTICE_")
	private String notice;
	
	@Column(name = "INDATE_")
	private String indate;
	
	@Column(name = "PRICE_", precision=10, scale=2)
	private BigDecimal price;
	
	@Column(name = "MARKET_PRICE_", precision=10, scale=2)
	private BigDecimal marketPrice;
	
	@Column(name = "GCOST_PRICE_", precision=10, scale=2)
	private BigDecimal gcostPrice;
	
	@Column(name = "SALES_NUM_", length = 10)
	private Long salesNum;
	
	@Column(name = "VIEW_NUM_", length = 10)
	private Long viewNum;
	
	@Column(name = "COLLECTION_NUM_", length = 10)
	private Long collectionNum;
	
	@Column(name = "IS_NEW_", length = 1)
	private String isNew;
	
	@Column(name = "IS_SALE_", length = 1)
	private String isSale;
	
	@Column(name = "IS_HOT_", length = 1)
	private String isHot;
	
	@Column(name = "IS_QBUY_", length = 1)
	private String isQbuy;
	
	@Column(name = "IS_RECOMMEND", length = 1)
	private String isRecommend;
	
	@Column(name = "IMG_URL_", length = 100)
	private String imgUrl;
	
	@Column(name = "ADD_TIME_")
	private Date addTime;
	
	@Column(name = "UPDATE_TIME_")
	private Date updateTime;
	
	@Column(name = "REPERTORY_", length = 10)
	private Long repertory;
	
	@Column(name = "SCORE_", precision = 10,scale = 2)
	private BigDecimal score;
	
	@Column(name = "ADUIT_STATUS_", length = 1)
	private String aduitStatus;
	
	@Column(name = "STATUS_", length = 1)
	private String status;
	
	@Column(name = "REMARK_", length = 1)
	private String remark;
	
	@Column(name = "REMARK_INFO_", length = 200)
	private String remarkInfo;
	
	@Column(name = "TOKEN_", length = 200)
	private String token;
	
	@Column(name = "PARA1_", length = 200)
	private String para1;
	
	@Column(name = "PARA2_", length = 200)
	private String para2;
	
	@Column(name = "PARA3_", length = 200)
	private String para3;
	
	@Column(name = "PARA4_", length = 200)
	private String para4;
	
	@Column(name = "PARA5_", length = 200)
	private String para5;
	
	@Column(name = "PARA6_", length = 200)
	private String para6;
	
	@Column(name = "PARA7_", length = 200)
	private String para7;
	
	@Column(name = "PARA8_", length = 200)
	private String para8;
	
	@Column(name = "IS_CAN_USER_COU_", length = 1)
	private String isCanUserCou;
	
	@Column(name="POSTAGE", precision=20, scale=2)
	private BigDecimal postage;
	
	@Column(name = "NET_PROD_ID_", length = 10)
	private Long netProId;
	
	@Column(name = "TYPE_", length = 1)
	private String type;
	
	@Column(name = "SOURCE_GOODS_", length = 1)
	private String suorceGoods;
	
	@Column(name = "POINT_NUM_", length = 11)
	private Integer pointNum;
	
	@Column(name = "POINT_NICE_PRICE_", precision=20, scale=2)
	private BigDecimal pointNicePrice;
	
	@Column(name = "PURCHAS_NUM_", length = 11)
	private Integer purchasNum;
	
	@Column(name = "IS_HAVA_REP_", length = 1)
	private String isHavaRep;
	
	@Column(name = "BENERFIT_MONEY_", precision=20, scale=2)
	private BigDecimal benerfitMoney;
	
	@Column(name = "IS_WHOLESALE_", length = 1)
	private String isWholesale; 
	
	@Column(name="STANDARD_PRICE_",precision=10,scale=2)
	private BigDecimal standardPrice;
	
	@Column(name="HONOUR_PRICE_",precision=10,scale=2)
	private BigDecimal honourPrice;
	
	@Column(name="IS_UPGRADE_PRO_",length = 1)
	private String isUpgradePro;
	
	@Column(name="MULTIPLE_NUMBER_",length = 50)
	private String multipleNumber;
	
	@Column(name = "DERECT_MEMBER_VOR_", precision=20, scale=2)
	private BigDecimal derectMemberVor;
	
	@Column(name = "FIRST_DERECT_MEMBER_VOR", precision=20, scale=2)
	private BigDecimal firstDerectMemberVor;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GjfStoreInfo getStoreId() {
		return this.storeId;
	}

	public void setStoreId(GjfStoreInfo storeId) {
		this.storeId = storeId;
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

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public GjfEnterpriseColumn getColumnId() {
		return columnId;
	}

	public void setColumnId(GjfEnterpriseColumn columnId) {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSuorceGoods() {
		return suorceGoods;
	}

	public void setSuorceGoods(String suorceGoods) {
		this.suorceGoods = suorceGoods;
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

	public Long getNetProId() {
		return netProId;
	}

	public void setNetProId(Long netProId) {
		this.netProId = netProId;
	}

	public Integer getPurchasNum() {
		return purchasNum;
	}

	public void setPurchasNum(Integer purchasNum) {
		this.purchasNum = purchasNum;
	}

	public String getIsHavaRep() {
		return isHavaRep;
	}

	public void setIsHavaRep(String isHavaRep) {
		this.isHavaRep = isHavaRep;
	}

	public String getRemarkInfo() {
		return remarkInfo;
	}

	public void setRemarkInfo(String remarkInfo) {
		this.remarkInfo = remarkInfo;
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