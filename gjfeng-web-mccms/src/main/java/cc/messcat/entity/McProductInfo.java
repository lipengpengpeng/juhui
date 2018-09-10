package cc.messcat.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * McProductInfo entity. @author MyEclipse Persistence Tools
 */

public class McProductInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
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
	private String aduitStatus; //商品审核(0:未通过 1:通过 2:审核中)
	private String remark;
	private String token;
	private String status;   //商品状态(0:下架 1:正常 2:违规)
	private String para1;	//图片1
	private String para2;	//图片2
	private String para3;	//图片3
	private String para4;
	private String para5;
	private String para6;
	private String para7;
	private String para8;
	private String isCanUserCou;
	private String isWholesale; 
	
	private String isUpgradePro;
	
	/**
	 * 保存图片名称，已‘；’分割
	 */
	private String productImage;

	/**
	 * 保存分割后的图片名称
	 */
	private String[] productImageList;
	
	/**
	 * 产品-栏目
	 */
	//private Set<ProductColumn> columnList = new HashSet<ProductColumn>();
	
	public McProductInfo() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public BigDecimal getGcostPrice() {
		return gcostPrice;
	}

	public void setGcostPrice(BigDecimal gcostPrice) {
		this.gcostPrice = gcostPrice;
	}

	public Long getSalesNum() {
		return salesNum;
	}

	public void setSalesNum(Long salesNum) {
		this.salesNum = salesNum;
	}

	public Long getViewNum() {
		return viewNum;
	}

	public void setViewNum(Long viewNum) {
		this.viewNum = viewNum;
	}

	public Long getCollectionNum() {
		return collectionNum;
	}

	public void setCollectionNum(Long collectionNum) {
		this.collectionNum = collectionNum;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public String getIsSale() {
		return isSale;
	}

	public void setIsSale(String isSale) {
		this.isSale = isSale;
	}

	public String getIsHot() {
		return isHot;
	}

	public void setIsHot(String isHot) {
		this.isHot = isHot;
	}

	public String getIsQbuy() {
		return isQbuy;
	}

	public void setIsQbuy(String isQbuy) {
		this.isQbuy = isQbuy;
	}

	public String getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(String isRecommend) {
		this.isRecommend = isRecommend;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getRepertory() {
		return repertory;
	}

	public void setRepertory(Long repertory) {
		this.repertory = repertory;
	}

	public String getAduitStatus() {
		return aduitStatus;
	}

	public void setAduitStatus(String aduitStatus) {
		this.aduitStatus = aduitStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPara1() {
		return para1;
	}

	public void setPara1(String para1) {
		this.para1 = para1;
	}

	public String getPara2() {
		return para2;
	}

	public void setPara2(String para2) {
		this.para2 = para2;
	}

	public String getPara3() {
		return para3;
	}

	public void setPara3(String para3) {
		this.para3 = para3;
	}

	public String getPara4() {
		return para4;
	}

	public void setPara4(String para4) {
		this.para4 = para4;
	}

	public String getPara5() {
		return para5;
	}

	public void setPara5(String para5) {
		this.para5 = para5;
	}

	public String getPara6() {
		return para6;
	}

	public void setPara6(String para6) {
		this.para6 = para6;
	}

	public String getPara7() {
		return para7;
	}

	public void setPara7(String para7) {
		this.para7 = para7;
	}

	public String getPara8() {
		return para8;
	}

	public void setPara8(String para8) {
		this.para8 = para8;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String[] getProductImageList() {
		return productImageList;
	}

	public void setProductImageList(String[] productImageList) {
		this.productImageList = productImageList;
	}

	/*public Set<ProductColumn> getColumnList() {
		return columnList;
	}

	public void setColumnList(Set<ProductColumn> columnList) {
		this.columnList = columnList;
	}*/

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public GjfStoreInfo getStoreId() {
		return storeId;
	}

	public void setStoreId(GjfStoreInfo storeId) {
		this.storeId = storeId;
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

	public String getIndate() {
		return indate;
	}

	public void setIndate(String indate) {
		this.indate = indate;
	}

	public EnterpriseColumn getColumnId() {
		return columnId;
	}

	public void setColumnId(EnterpriseColumn columnId) {
		this.columnId = columnId;
	}

	public String getIsCanUserCou() {
		return isCanUserCou;
	}

	public void setIsCanUserCou(String isCanUserCou) {
		this.isCanUserCou = isCanUserCou;
	}

	public String getIsWholesale() {
		return isWholesale;
	}

	public void setIsWholesale(String isWholesale) {
		this.isWholesale = isWholesale;
	}

	public String getIsUpgradePro() {
		return isUpgradePro;
	}

	public void setIsUpgradePro(String isUpgradePro) {
		this.isUpgradePro = isUpgradePro;
	}

	
}