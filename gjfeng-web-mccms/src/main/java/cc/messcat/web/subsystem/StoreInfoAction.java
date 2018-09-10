package cc.messcat.web.subsystem;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import cc.messcat.gjfeng.common.bean.Pager;
import cc.messcat.gjfeng.common.constant.CommonProperties;
import cc.messcat.gjfeng.common.exception.LoggerPrint;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.common.vo.app.StoreInfoVo;
import cc.messcat.gjfeng.common.vo.app.StoreJoinVo;
import cc.messcat.gjfeng.entity.GjfAddressArea;
import cc.messcat.gjfeng.entity.GjfAddressCity;
import cc.messcat.gjfeng.entity.GjfAddressProvince;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.entity.GjfStoreInfo;
import cc.messcat.gjfeng.entity.GjfStoreJoinin;
import cc.modules.commons.PageAction;
import cc.modules.constants.ActionConstants;
import cc.modules.util.CommonUpload;
import cc.modules.util.PropertiesFileReader;
import net.sf.json.JSONObject;

public class StoreInfoAction extends PageAction  {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String token; 
	private String likeValue;
	private String storePro;	//店铺属性  0: O2O  1: 网上商城
	private String storeType; 	//店铺类型  0:个体入住  1:企业入驻
	private String storeStatus;	//店铺状态(0:关闭 1:开启 2:审核中 3:审核失败)
	private String account;
	private String aduitStatus; //店铺状态(0:关闭 1:开启 2:审核中 3:审核失败)
	private String description; //店铺简介(店铺描述)
	private GjfStoreInfo gjfStoreInfo;
	private String bannerImgUrl; //店铺横幅（多图用;隔开）
	
	private String auditStatusReason;//审核理由
	
	private Long fatherId;
	
	private String addressType;
	
	// 商家入驻模型驱动
	private StoreInfoVo storeInfoVo;
	private StoreJoinVo storeJoinVo;
	
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
	
	private String uploadImageFileName;
	
	private File uploadImage;
	
	private File uploadImageBanner;
	
	private String uploadImageBannerFileName;
	
	private CommonUpload comUpload;
	
	private String savePath = "/upload/enterprice" ;
	
	private String type;
	
	private Double tradeMoney;
	
	private String identity;
	
	private String name;
	private String tradeNo;
	private String addTime;
	private String endTime;
	private String payType;
	private String tradeStatus;
	private String isDivi;
	
	private GjfStoreJoinin storeJoin;
	
	private String op;
	private String ste;
	private Object object;
	
	private String payMchId;
	
	private String payKey;
	
	private String isActivityStore;
	
	
	
	public StoreInfoAction(){
		comUpload=new CommonUpload();
	}
	/**
	 * 分页查询店铺列表
	 * @return
	 */
	public String retrieveStoreByPager(){
		try {
			resultVo = storeInfoDubbo.findStoreByPager(pageNo, pageSize, likeValue, storePro, storeType, storeStatus,isActivityStore);
			pager = (Pager) resultVo.getResult();
			if ("1".equals(op)) {
				return "store_diviNum_list";
			}
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, StoreInfoAction.class);
		}
		return SUCCESS;
	}
	
	/**
	 * 提交店铺申请
	 * @return
	 */
	public String addStore(){
		try {
			resultVo = storeInfoDubbo.addStore(storeInfoVo, storeJoinVo, account);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, StoreInfoAction.class);
		}
		return SUCCESS;
	}
	
	/**
	 * 根据Id查询店铺
	 * @return
	 */
	public String  retrieveStoreById(){
		try {
			resultVo = storeInfoDubbo.findStoreById(id,token);
			this.storeJoin = (GjfStoreJoinin) storeInfoDubbo.findStoreJoin(id, token).getResult();
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, StoreInfoAction.class);
		}
		return "views";
	}
	
	/**
	 * 根据账户查询店铺
	 * @return
	 */
	public String retrieveStoreByAccount(){
		try {
			resultVo = storeInfoDubbo.findStoreByAccount(account);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, StoreInfoAction.class);
		}
		return SUCCESS;
	}
	
	/**
	 * 跳转到编辑页面
	 * @return
	 */
	public String eidtPage(){
		try {
			resultVo = storeInfoDubbo.findStoreById(id,token);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, StoreInfoAction.class);
		}
		return "goEditPage";
	}
	
	
	/**
	 * 跳转到审核页面
	 * @return
	 */
	public String goStatusPage(){
		try {
			resultVo = storeInfoDubbo.findStoreJoin(id, token);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, StoreInfoAction.class);
		}
		return "goStatusPage";
	}
		
	/**
	 * 修改店铺审核状态
	 * @return
	 */
	public String modifyAduitStatus(){
		try {
			resultVo = storeInfoDubbo.updateAduitStatus(id,aduitStatus,token,auditStatusReason);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, StoreInfoAction.class);
		}
		return SUCCESS;
	}
	
	/**
	 * 删除店铺
	 * @return
	 */
	public String delStore(){
		try {
			resultVo = storeInfoDubbo.delStore(id, token);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, StoreInfoAction.class);
		}
		return "delete_store";
	}
	
	/**
	 * 修改店铺简介
	 * @return
	 */
	public String modifyStoreDescription(){
		try {
			resultVo = storeInfoDubbo.updateStoreDescription(id, description);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, StoreInfoAction.class);
		}
		return SUCCESS;
	}
	
	/**
	 * 修改店铺信息
	 * @return
	 */
	public String updateStore(){
		try {
			GjfStoreInfo gjfStoreInfo=new GjfStoreInfo();
			gjfStoreInfo.setAddressDetail(addressDetail);
			gjfStoreInfo.setSellerEmail(sellerEmail);
			gjfStoreInfo.setSellerMobile(sellerMobile);
			gjfStoreInfo.setStoreCloseRemark(storeCloseRemark);
			gjfStoreInfo.setSellerName(sellerName);
			gjfStoreInfo.setStoreBefCustomer(storeBefCustomer);
			gjfStoreInfo.setStoreKeywords(storeKeywords);
			gjfStoreInfo.setId(id);
			
			gjfStoreInfo.setProvinceId(provinceId);
			gjfStoreInfo.setCityId(cityId);
			gjfStoreInfo.setAreaId(areaId);
			gjfStoreInfo.setIsDivi(isDivi);
			gjfStoreInfo.setStoreName(storeName);
			gjfStoreInfo.setStorePro(storePro);
			gjfStoreInfo.setStoreType(storeType);
			gjfStoreInfo.setStoreWorkingTime(storeWorkingTime);
			gjfStoreInfo.setStoreStatus(storeStatus);
			gjfStoreInfo.setStoreAddTime(storeAddTime);
			gjfStoreInfo.setStoreAduitTime(storeAduitTime);
			gjfStoreInfo.setPayMchId(payMchId);
			gjfStoreInfo.setPayKey(payKey);
			//
			resultVo=storeInfoDubbo.findStoreById(id, token);
			GjfStoreInfo storeInfo=(GjfStoreInfo) resultVo.getResult();
			// 更新图片
			comUpload.setHandleType("update");
			setPic();
			
			if (uploadImageFileName != null) {
				comUpload.setUpload(uploadImage);// 上传的File文件
				comUpload.setUploadFileName(uploadImageFileName);// 上传文件的文件名
				comUpload.setHandleType("");
				comUpload.setOldUploadFileName(storeInfo.getStoreLogoUrl());
				if (!comUpload.uploadFile()) {
					return ActionConstants.INPUT_KEY;
				} else {
					String img=CommonProperties.GJFENG_MCCMS_PROJECT_URL+savePath+"/"+comUpload.getUploadFileName();
					gjfStoreInfo.setStoreLogoUrl(img);					
				}
			}
			
			if (uploadImageBannerFileName != null) {
				comUpload.setUpload(uploadImageBanner);// 上传的File文件
				comUpload.setUploadFileName(uploadImageBannerFileName);// 上传文件的文件名								
				comUpload.setOldUploadFileName(storeInfo.getStoreBanner());
				comUpload.setHandleType("");
				if (!comUpload.uploadFile()) {
					return ActionConstants.INPUT_KEY;
				} else {
					String img=CommonProperties.GJFENG_MCCMS_PROJECT_URL+savePath+"/"+comUpload.getUploadFileName();
					gjfStoreInfo.setStoreBanner(img);					
				}
			}
			resultVo = storeInfoDubbo.updateStore(gjfStoreInfo);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, StoreInfoAction.class);
		}
		return "edit_success";
	}
	/**
	 * 设置上传图片通用信息
	 * 
	 * @throws Exception
	 */
	private void setPic() throws Exception {
		//comUpload.setMaxSize(comUpload.getPhotoMaxSize());// 上传文件最大尺寸
		//comUpload.setSavePath(Constants.FILE_SEPARATOR + "upload" + Constants.FILE_SEPARATOR + "goodImages");// 上传文件保存路径
		comUpload.setSavePath("/upload/goodImages");// 上传文件保存路径
		comUpload.setAllowTypes(comUpload.getAllowTypePhoto());// 上传文件所允许的格式
	}
	
	/**
	 * 修改店铺的Banner
	 * @return
	 */
	public String modifyStoreBanner(){
		try {
			resultVo = storeInfoDubbo.updateStoreBanner(id, bannerImgUrl);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, StoreInfoAction.class);
		}
		return SUCCESS;
	}
	
	/**
	 * 后台消费充值授信额度
	 * @return
	 */
	public String rechargeLineOfCredit(){
		JSONObject json = new JSONObject();
		try {
			resultVo = storeInfoDubbo.updateLineOfCreadit(id, token, account, tradeMoney, type);
			json = JSONObject.fromObject(resultVo);
		} catch (Exception e) {
			e.printStackTrace();
			json = JSONObject.fromObject(new ResultVo(400,"操作出错",null));
		}
		return renderText(json == null ? null : json.toString());
	}
	
	/**
	 * 授信充值记录
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String findLineCreditRechargeByPage(){
		try {
			resultVo = storeInfoDubbo.findRechargeLineCreditByPage(pageNo, pageSize, tradeNo, name, addTime,endTime, payType, tradeStatus,ste);
			pager = (Pager) resultVo.getResult();
			List list = (List) storeInfoDubbo.findRechargeLineCredit(tradeNo, name, addTime,endTime, payType, tradeStatus).getResult();
			object = list.get(0);
			if("1".equals(ste)){ //导出操作
				request=ServletActionContext.getRequest();//获取请求对象;
				return "rechargeList_export";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "recharge_list";
	}
	
	/**
	 * 后台重新定位
	 * @return
	 */
	public String refreshLocation(){
		JSONObject json = new JSONObject();
		try {
			resultVo = storeInfoDubbo.updateLocation(id,token);
			json = JSONObject.fromObject(resultVo);
		} catch (Exception e) {
			e.printStackTrace();
			json = JSONObject.fromObject(new ResultVo(400,"操作出错",null));
		}
		return renderText(json == null ? null : json.toString());
	}
	
	
	/**
	 * 查询代理下店铺
	 * @return
	 */
	public String findStoreByAgent(){
		try {
			resultVo = storeInfoDubbo.findStoreByAgent(pageNo, pageSize, id, token, identity);
			pager = (Pager) resultVo.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "agentStores";
	}
	
	/**
	 * 查看代理下商家的流水
	 * @return
	 */
	public String findStoreBenefitByAgent(){
		try {
			resultVo = storeInfoDubbo.findStoreBenefitByAgent(pageNo, pageSize, id, token, identity);
			pager = (Pager) resultVo.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "storeBenefit_Agent";
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getLikeValue() {
		return likeValue;
	}

	public void setLikeValue(String likeValue) {
		this.likeValue = likeValue;
	}

	public String getStorePro() {
		return storePro;
	}

	public void setStorePro(String storePro) {
		this.storePro = storePro;
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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAduitStatus() {
		return aduitStatus;
	}

	public void setAduitStatus(String aduitStatus) {
		this.aduitStatus = aduitStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public GjfStoreInfo getGjfStoreInfo() {
		return gjfStoreInfo;
	}

	public void setGjfStoreInfo(GjfStoreInfo gjfStoreInfo) {
		this.gjfStoreInfo = gjfStoreInfo;
	}

	public String getBannerImgUrl() {
		return bannerImgUrl;
	}

	public void setBannerImgUrl(String bannerImgUrl) {
		this.bannerImgUrl = bannerImgUrl;
	}

	public StoreInfoVo getStoreInfoVo() {
		return storeInfoVo;
	}

	public void setStoreInfoVo(StoreInfoVo storeInfoVo) {
		this.storeInfoVo = storeInfoVo;
	}

	public StoreJoinVo getStoreJoinVo() {
		return storeJoinVo;
	}

	public void setStoreJoinVo(StoreJoinVo storeJoinVo) {
		this.storeJoinVo = storeJoinVo;
	}

	public String getAuditStatusReason() {
		return auditStatusReason;
	}

	public void setAuditStatusReason(String auditStatusReason) {
		this.auditStatusReason = auditStatusReason;
	}

	public Long getFatherId() {
		return fatherId;
	}

	public void setFatherId(Long fatherId) {
		this.fatherId = fatherId;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
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

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
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

	public String getUploadImageFileName() {
		return uploadImageFileName;
	}

	public void setUploadImageFileName(String uploadImageFileName) {
		this.uploadImageFileName = uploadImageFileName;
	}
	
	/**
	 * 获取图片保存路径，放在配置文件app.properties
	 * @return
	 * @throws Exception
	 */
	public String getSaveImgPath() throws Exception {
		String imgPath = PropertiesFileReader.get("upload.store.banner.path", "/upload.properties");
		//String realPath=ServletActionContext.getRequest().getRealPath(imgPath);
		String realPath = ServletActionContext.getRequest().getSession().getServletContext().getRealPath(imgPath);
		return realPath;
	}
	
	/**
	 * 获取图片保存路径，放在配置文件app.properties
	 * @return
	 * @throws Exception
	 */
	public String getDomainName() throws Exception {
		String domainName = PropertiesFileReader.get("gjfeng.mccms.project.url", "/conf.properties");
		//String realPath = ServletActionContext.getRequest().getSession().getServletContext().getRealPath(domainName);
		return domainName;
	}

	public File getUploadImage() {
		return uploadImage;
	}

	public void setUploadImage(File uploadImage) {
		this.uploadImage = uploadImage;
	}

	public CommonUpload getComUpload() {
		return comUpload;
	}

	public void setComUpload(CommonUpload comUpload) {
		this.comUpload = comUpload;
	}
	public File getUploadImageBanner() {
		return uploadImageBanner;
	}
	public void setUploadImageBanner(File uploadImageBanner) {
		this.uploadImageBanner = uploadImageBanner;
	}
	public String getUploadImageBannerFileName() {
		return uploadImageBannerFileName;
	}
	public void setUploadImageBannerFileName(String uploadImageBannerFileName) {
		this.uploadImageBannerFileName = uploadImageBannerFileName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getTradeMoney() {
		return tradeMoney;
	}
	public void setTradeMoney(Double tradeMoney) {
		this.tradeMoney = tradeMoney;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getSte() {
		return ste;
	}
	public void setSte(String ste) {
		this.ste = ste;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getTradeStatus() {
		return tradeStatus;
	}
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	public GjfStoreJoinin getStoreJoin() {
		return storeJoin;
	}
	public void setStoreJoin(GjfStoreJoinin storeJoin) {
		this.storeJoin = storeJoin;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public String getIsDivi() {
		return isDivi;
	}
	public void setIsDivi(String isDivi) {
		this.isDivi = isDivi;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
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
	public String getIsActivityStore() {
		return isActivityStore;
	}
	public void setIsActivityStore(String isActivityStore) {
		this.isActivityStore = isActivityStore;
	}
	
	
	
}
