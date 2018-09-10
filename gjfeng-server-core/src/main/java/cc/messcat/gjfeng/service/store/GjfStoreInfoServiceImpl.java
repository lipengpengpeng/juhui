package cc.messcat.gjfeng.service.store;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import cc.messcat.gjfeng.common.bean.Pager;
import cc.messcat.gjfeng.common.constant.CommonProperties;
import cc.messcat.gjfeng.common.constant.CommonStatus;
import cc.messcat.gjfeng.common.exception.MyException;
import cc.messcat.gjfeng.common.util.BaiduApi;
import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.util.BeanUtilsEx;
import cc.messcat.gjfeng.common.util.DateHelper;
import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.util.RandUtil;
import cc.messcat.gjfeng.common.util.Sha256;
import cc.messcat.gjfeng.common.util.StringUtil;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.common.vo.app.StoreInfoVo;
import cc.messcat.gjfeng.common.vo.app.StoreJoinVo;
import cc.messcat.gjfeng.dao.store.GjfStoreInfoDao;
import cc.messcat.gjfeng.entity.GjfAddressArea;
import cc.messcat.gjfeng.entity.GjfAddressCity;
import cc.messcat.gjfeng.entity.GjfAddressProvince;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.entity.GjfStoreInfo;
import cc.messcat.gjfeng.entity.GjfStoreJoinin;
import cc.messcat.gjfeng.service.member.GjfMemberInfoService;
import cc.messcat.gjfeng.service.member.GjfTradeService;
import net.sf.json.JSONObject;

@Service("gjfStoreInfoService")
public class GjfStoreInfoServiceImpl implements GjfStoreInfoService {

	@Autowired
	@Qualifier("gjfStoreInfoDao")
	private GjfStoreInfoDao gjfStoreInfoDao;

	@Autowired
	@Qualifier("gjfMemberInfoService")
	private GjfMemberInfoService gjfMemberInfoService;

	@Autowired
	@Qualifier("gjfTradeService")
	private GjfTradeService gjfTradeService;

	@Autowired
	@Qualifier("notifyJmsTemplate")
	private JmsTemplate notifyJmsTemplate;

	/*
	 * 提交店铺申请
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.store.GjfStoreInfoService#addStore(cc.messcat.
	 * gjfeng.common.vo.app.StoreInfoVo,
	 * cc.messcat.gjfeng.common.vo.app.StoreJoinVo)
	 */
	@Override
	public ResultVo addStore(StoreInfoVo storeInfoVo, StoreJoinVo storeJoinVo, String account) throws Exception {
		if (ObjValid.isNotValid(storeInfoVo) || StringUtil.isBlank(storeInfoVo.getStorePro())
			|| (!"0".equals(storeInfoVo.getStorePro()) && !"1".equals(storeInfoVo.getStorePro()))) {
			return new ResultVo(400, "信息有误，请重新填写", null);
		}
		if ("0".equals(storeInfoVo.getStorePro()) && (StringUtil.isBlank(storeInfoVo.getStoreType())
			|| (!"0".equals(storeInfoVo.getStoreType()) && !"1".equals(storeInfoVo.getStoreType())))) {
			return new ResultVo(400, "店铺类型必须为个体入驻 或企业入驻！", null);
		}
		if (StringUtil.isBlank(account)) {
			return new ResultVo(400, "用户不存在", null);
		}
		GjfMemberInfo gjfMemberInfo = gjfMemberInfoService.findMember(account);
		if (ObjValid.isNotValid(gjfMemberInfo)) {
			return new ResultVo(400, "用户不存在", null);
		}
		Map<String, Object> attrs0 = new HashMap<String, Object>();
		attrs0.put("memberId.mobile", account);
		GjfStoreInfo storeInfo = gjfStoreInfoDao.query(GjfStoreInfo.class, attrs0);
		if (ObjValid.isValid(storeInfo)) {
			if (!storeInfo.getStoreStatus().equals("3")) {
				if (storeInfo.getStoreStatus().equals("0")) {
					return new ResultVo(400, "您的店铺已经被关闭", null);
				}
				if (storeInfo.getStoreStatus().equals("1")) {
					return new ResultVo(400, "您的店铺已经成功入驻了，不需要重复提交", null);
				}
				if (storeInfo.getStoreStatus().equals("2")) {
					return new ResultVo(400, "您的店铺入驻申请正在审核中，请耐心等待", null);
				}
			} else {
				// 因为已经申请过了，所以重新申请将直接修改其资料
				return updateStore(gjfMemberInfo, storeInfo, storeInfoVo, storeJoinVo);
			}

		}
		/*if (gjfMemberInfo.getIsReadName().equals("0")) {
			return new ResultVo(400, "请先进行实名制", null);
		}*/
		if (StringUtil.isBlank(storeInfoVo.getStoreName())) {
			return new ResultVo(400, "店铺名称不能为空", null);
		}
		if (StringUtil.isBlank(storeInfoVo.getSellerName())) {
			return new ResultVo(400, "联系人不能为空", null);
		}
		if (StringUtil.isBlank(storeInfoVo.getSellerMobile())) {
			return new ResultVo(400, "联系电话不能为空", null);
		}
		/*if (StringUtil.isBlank(storeInfoVo.getSellerEmail())) {
			return new ResultVo(400, "联系邮箱不能为空", null);
		}*/
		if (StringUtil.isBlank(storeInfoVo.getStoreCitys())) {
			return new ResultVo(400, "店铺省份城市不能为空", null);
		}
		if (StringUtil.isBlank(storeInfoVo.getAddressDetail())) {
			return new ResultVo(400, "店铺详细地址不能为空", null);
		}
		
		/*if (StringUtil.isBlank(storeInfoVo.getStoreLogoUrl())) { 
			throw new MyException(400, "店铺Logo不能为空", null); 
		} 
		if(StringUtil.isBlank(storeInfoVo.getStoreDescription())) { 
			throw new MyException(400, "店铺描述不能为空", null); 
		}		 
		if (StringUtil.isBlank(storeJoinVo.getBankAccountName())) {
			return new ResultVo(400, "开户名不能为空", null);
		}
		if (StringUtil.isBlank(storeJoinVo.getBankAccountNumber())) {
			return new ResultVo(400, "开户卡号不能为空", null);
		}*/
		// 判断店铺名称是否存在，判断当前用户是否开店了
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("storeName", storeInfoVo.getStoreName());
		GjfStoreInfo info = gjfStoreInfoDao.query(GjfStoreInfo.class, attrs);
		if (ObjValid.isValid(info)) {
			return new ResultVo(400, "该店铺名称已经存在", null);
		}

		/*if (StringUtil.isBlank(storeJoinVo.getBusinessLicenceNumber())) {
			return new ResultVo(400, "营业执照号不能为空", null);
		}
		if (StringUtil.isBlank(storeJoinVo.getBusinessLicenceAddress())) {
			return new ResultVo(400, "营业执所在地不能为空", null);
		}*/
		if (StringUtil.isBlank(storeJoinVo.getBusinessLicenceStart())) {
			return new ResultVo(400, "营业执照有效期开始时间不能为空", null);
		}
		if (StringUtil.isBlank(storeJoinVo.getBusinessLicenceEnd())) {
			return new ResultVo(400, "营业执照有效期结束时间不能为空", null);
		}

		if (StringUtil.isBlank(storeJoinVo.getBusinessLicenceNumberElectronic())) {
			return new ResultVo(400, "营业执照电子版不能为空", null);
		}

		// 企业入驻
		/*if ("1".equals(storeInfoVo.getStoreType())) {
			if (StringUtil.isBlank(storeJoinVo.getBankName())) {
				return new ResultVo(400, "开户银行支行不能为空", null);
			}
			if (StringUtil.isBlank(storeJoinVo.getBankAddress())) {
				return new ResultVo(400, "开户银行所在地不能为空", null);
			}
			if (StringUtil.isBlank(storeJoinVo.getSettlementBankAccountName())) {
				return new ResultVo(400, "结算银行开户名不能为空", null);
			}
			if (StringUtil.isBlank(storeJoinVo.getSettlementBankAccountNumber())) {
				return new ResultVo(400, "结算银行卡号不能为空", null);
			}
			if (StringUtil.isBlank(storeJoinVo.getSettlementBankName())) {
				return new ResultVo(400, "结算银行支行不能为空", null);
			}
			if (StringUtil.isBlank(storeJoinVo.getSettlementBankAddress())) {
				return new ResultVo(400, "结算银行所在地不能为空", null);
			}

			if (null == storeJoinVo.getCompanyRegisteredCapital()) {
				return new ResultVo(400, "注册资金不能为空", null);
			}
			if (StringUtil.isBlank(storeJoinVo.getTaxRegistrationCertificate())) {
				return new ResultVo(400, "税务登记号不能为空", null);
			}
			if (StringUtil.isBlank(storeJoinVo.getOrganizationCode())) {
				return new ResultVo(400, "组织机构代码不能为空", null);
			}

		}*/
		GjfStoreInfo gjfStoreInfo = new GjfStoreInfo();
		GjfStoreJoinin gjfStoreJoinin = new GjfStoreJoinin();
		gjfStoreInfo.setStoreName(storeInfoVo.getStoreName());
		gjfStoreInfo.setStoreNum(DateHelper.dataToString(new Date(), "yyyyMMddHHmmss") + RandUtil.getRandomStr(2));
		gjfStoreInfo.setMemberId(gjfMemberInfo);
		gjfStoreInfo.setSellerName(storeInfoVo.getSellerName());
		gjfStoreInfo.setSellerMobile(storeInfoVo.getSellerMobile());
		gjfStoreInfo.setSellerEmail(storeInfoVo.getSellerEmail());

		/*
		 * gjfStoreInfo.setLatitude(23.1357768997); //TODO 先默认经纬度
		 * gjfStoreInfo.setLongitude(113.2416165593);
		 */

		String[] addressId = storeInfoVo.getStoreCitys().split(",");
		Map<String, Object> proAttrs = new HashMap<String, Object>();
		Map<String, Object> cityAttrs = new HashMap<String, Object>();
		Map<String, Object> areaAttrs = new HashMap<String, Object>();
		Object proObj = null;
		Object cityObj = null;
		Object areaObj = null;
		if (addressId.length > 0) {
			proAttrs.put("provinceId", Long.valueOf(addressId[0]));
			proObj = gjfStoreInfoDao.query(GjfAddressProvince.class, proAttrs);
			if (ObjValid.isNotValid(proObj)) {
				throw new MyException(400, "省份不能为空", null);
			}
		}
		if (addressId.length > 1) {
			cityAttrs.put("cityId", Long.valueOf(addressId[1]));
			cityObj = gjfStoreInfoDao.query(GjfAddressCity.class, cityAttrs);
			if (ObjValid.isNotValid(cityObj)) {
				throw new MyException(400, "城市不能为空", null);
			}
		}
		if (addressId.length > 2) {
			areaAttrs.put("areaId", Long.valueOf(addressId[2]));
			areaObj = gjfStoreInfoDao.query(GjfAddressArea.class, areaAttrs);
		}
		gjfStoreInfo.setProvinceId((GjfAddressProvince) proObj);
		gjfStoreInfo.setCityId((GjfAddressCity) cityObj);
		if (ObjValid.isValid(areaObj)) {
			gjfStoreInfo.setAreaId((GjfAddressArea) areaObj);
		}
		gjfStoreInfo.setAddressDetail(storeInfoVo.getAddressDetail());
		gjfStoreInfo.setStoreAddTime(new Date());
		if (StringUtil.isNotBlank(storeInfoVo.getStoreLogoUrl())) {
			gjfStoreInfo.setStoreLogoUrl(storeInfoVo.getStoreLogoUrl());
		}
		if (StringUtil.isNotBlank(storeInfoVo.getStoreBanner())) {
			gjfStoreInfo.setStoreBanner(storeInfoVo.getStoreBanner());
		}
		if (StringUtil.isNotBlank(storeInfoVo.getStoreKeywords())) {
			gjfStoreInfo.setStoreKeywords(storeInfoVo.getStoreKeywords());
		}
		if (StringUtil.isNotBlank(storeInfoVo.getStoreDescription())) {
			gjfStoreInfo.setStoreDescription(storeInfoVo.getStoreDescription());
		}

		gjfStoreInfo.setStoreDividendsTotalMoney(new BigDecimal(0.00));
		gjfStoreInfo.setStoreDividendsTotalMoneyBla(new BigDecimal(0.00));
		gjfStoreInfo.setStoreDividendsNum(new BigDecimal(0.00));
		gjfStoreInfo.setStoreDividendsMoneyBla(new BigDecimal(0.00));
		gjfStoreInfo.setStoreSaleTotalMoney(new BigDecimal(0.00));
		gjfStoreInfo.setStoreBenefitTotalMoney(new BigDecimal(0.00));

		gjfStoreInfo.setStoreRecommend("0");
		gjfStoreInfo.setStoreCreditScore(0L);
		gjfStoreInfo.setStoreDesccreditScore(new BigDecimal(5.00));
		gjfStoreInfo.setStoreServiceCreditScore(new BigDecimal(5.00));
		gjfStoreInfo.setStoreDeliveryCreditScore(new BigDecimal(5.00));
		gjfStoreInfo.setStoreCollectNum(0L);
		gjfStoreInfo.setStoreSalesNum(0L);
		gjfStoreInfo.setStoreBefCustomer(null);
		gjfStoreInfo.setStoreAftCustomer(null);
		gjfStoreInfo.setStoreWorkingTime("7X24");
		gjfStoreInfo.setStoreFreePrice(new BigDecimal(0.00));
		gjfStoreInfo.setStoreIsOwnShop(storeInfoVo.getStoreIsOwnShop());
		gjfStoreInfo.setStoreMargin(new BigDecimal(0.00));
		gjfStoreInfo.setStoreRealGiftRatio(new BigDecimal(12));
		gjfStoreInfo.setStoreRealIncomeRatio(new BigDecimal(88));
		gjfStoreInfo.setStorePro(storeInfoVo.getStorePro());
		gjfStoreInfo.setStoreType(storeInfoVo.getStoreType());
		gjfStoreInfo.setStoreStatus("2");
		gjfStoreInfo.setIsDivi("1");
		gjfStoreInfo.setIsDel("1");
		gjfStoreInfo.setIsActivityStore("1");
		gjfStoreInfo.setDeductDiviNum(new BigDecimal(0.000000));
		gjfStoreInfo.setLastTimeDiviMoney(new BigDecimal(0.00));
		gjfStoreInfo.setIsActivityStore("0");
		gjfStoreInfo
			.setToken(Sha256.getSha256Hash(gjfStoreInfo.getStoreNum(), gjfStoreInfo.getStoreName(), CommonStatus.SIGN_KEY_NUM));
		// 根据选择的地址调用地图API获取详细的经纬度 TODO
		// gjfStoreInfo.setLongitude(longitude);
		// gjfStoreInfo.setDimension(dimension);
		gjfStoreInfo.setStoreIsOwnShop("0");
		gjfStoreInfoDao.save(gjfStoreInfo);

		gjfStoreJoinin.setBankAccountName(storeJoinVo.getBankAccountName());
		gjfStoreJoinin.setBankAccountNumber(storeJoinVo.getBankAccountNumber());
		gjfStoreJoinin.setStoreId(gjfStoreInfo);
		// 个体入驻
		if ("0".equals(storeInfoVo.getStoreType())) {
			gjfStoreJoinin.setIsSettlementAccount("1");
		} else {
			String isSettlementAccount = storeJoinVo.getIsSettlementAccount();
			if (StringUtil.isBlank(isSettlementAccount) || (!"0".equals(isSettlementAccount) && !"1".equals(isSettlementAccount))) {
				gjfStoreJoinin.setIsSettlementAccount("0");
			} else {
				gjfStoreJoinin.setIsSettlementAccount(isSettlementAccount);
			}

			gjfStoreJoinin.setBankName(storeJoinVo.getBankName());
			gjfStoreJoinin.setBankAddress(storeJoinVo.getBankAddress());
			gjfStoreJoinin.setSettlementBankAccountName(storeJoinVo.getSettlementBankAccountName());
			gjfStoreJoinin.setSettlementBankAccountNumber(storeJoinVo.getSettlementBankAccountNumber());
			gjfStoreJoinin.setSettlementBankName(storeJoinVo.getSettlementBankName());
			gjfStoreJoinin.setSettlementBankAddress(storeJoinVo.getSettlementBankAddress());
			gjfStoreJoinin.setCompanyRegisteredCapital(storeJoinVo.getCompanyRegisteredCapital());
			gjfStoreJoinin.setTaxRegistrationCertificate(storeJoinVo.getTaxRegistrationCertificate());
			gjfStoreJoinin.setOrganizationCode(storeJoinVo.getOrganizationCode());

		}
		// 判断是否作为结算账户

		gjfStoreJoinin.setBusinessLicenceNumber(storeJoinVo.getBusinessLicenceNumber());
		gjfStoreJoinin.setBusinessLicenceAddress(storeJoinVo.getBusinessLicenceAddress());
		gjfStoreJoinin.setBusinessLicenceNumberElectronic(storeJoinVo.getBusinessLicenceNumberElectronic());
		gjfStoreJoinin.setBusinessSphere(storeJoinVo.getBusinessSphere());
		gjfStoreJoinin.setBusinessLicenceStart(storeJoinVo.getBusinessLicenceStart());
		gjfStoreJoinin.setBusinessLicenceEnd(storeJoinVo.getBusinessLicenceEnd());
		gjfStoreJoinin.setBusinessLicenceNumber(storeJoinVo.getBusinessLicenceNumber());
		gjfStoreInfoDao.save(gjfStoreJoinin);
		
		updateAduitStatus(gjfStoreInfo.getId(),"1",gjfStoreInfo.getToken(),"");

		// sendMessage(gjfStoreInfo);
		return new ResultVo(200, "提交成功，请等待审核", null);
	}

	@Override
	public ResultVo updateStore(GjfMemberInfo gjfMemberInfo, GjfStoreInfo gjfStoreInfo, StoreInfoVo storeInfoVo,
		StoreJoinVo storeJoinVo) throws Exception {
		if (gjfMemberInfo.getIsReadName().equals("0")) {
			throw new MyException(400, "请先进行实名制", null);
		}
		if (StringUtil.isBlank(storeInfoVo.getStoreName())) {
			throw new MyException(400, "店铺名称不能为空", null);
		}
		if (StringUtil.isBlank(storeInfoVo.getSellerName())) {
			throw new MyException(400, "联系人不能为空", null);
		}
		if (StringUtil.isBlank(storeInfoVo.getSellerMobile())) {
			throw new MyException(400, "联系电话不能为空", null);
		}
		if (StringUtil.isBlank(storeInfoVo.getSellerEmail())) {
			throw new MyException(400, "联系邮箱不能为空", null);
		}
		if (StringUtil.isBlank(storeInfoVo.getStoreCitys())) {
			throw new MyException(400, "店铺省份城市不能为空", null);
		}
		if (StringUtil.isBlank(storeInfoVo.getAddressDetail())) {
			throw new MyException(400, "店铺详细地址不能为空", null);
		}
		if (StringUtil.isBlank(storeJoinVo.getBankAccountName())) {
			throw new MyException(400, "开户名不能为空", null);
		}
		if (StringUtil.isBlank(storeJoinVo.getBankAccountNumber())) {
			throw new MyException(400, "开户卡号不能为空", null);
		}
		// 判断店铺名称是否存在，判断当前用户是否开店了
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("storeName", storeInfoVo.getStoreName());
		GjfStoreInfo info = gjfStoreInfoDao.query(GjfStoreInfo.class, attrs);
		if (ObjValid.isValid(info) && info.getMemberId().getId().longValue() != gjfMemberInfo.getId().longValue()) {
			throw new MyException(400, "该店铺名称已经存在", null);
		}
		// 企业入驻
		if ("1".equals(storeInfoVo.getStoreType())) {
			if (StringUtil.isBlank(storeJoinVo.getBankName())) {
				throw new MyException(400, "开户银行支行不能为空", null);
			}
			if (StringUtil.isBlank(storeJoinVo.getBankAddress())) {
				throw new MyException(400, "开户银行所在地不能为空", null);
			}
			if (StringUtil.isBlank(storeJoinVo.getSettlementBankAccountName())) {
				throw new MyException(400, "结算银行开户名不能为空", null);
			}
			if (StringUtil.isBlank(storeJoinVo.getSettlementBankAccountNumber())) {
				throw new MyException(400, "结算银行卡号不能为空", null);
			}
			if (StringUtil.isBlank(storeJoinVo.getSettlementBankName())) {
				throw new MyException(400, "结算银行支行不能为空", null);
			}
			if (StringUtil.isBlank(storeJoinVo.getSettlementBankAddress())) {
				throw new MyException(400, "结算银行所在地不能为空", null);
			}
			if (StringUtil.isBlank(storeJoinVo.getBusinessLicenceNumber())) {
				throw new MyException(400, "营业执照号不能为空", null);
			}
			if (StringUtil.isBlank(storeJoinVo.getBusinessLicenceAddress())) {
				throw new MyException(400, "营业执所在地不能为空", null);
			}
			if (StringUtil.isBlank(storeJoinVo.getBusinessLicenceStart())) {
				throw new MyException(400, "营业执照有效期开始时间不能为空", null);
			}
			if (StringUtil.isBlank(storeJoinVo.getBusinessLicenceEnd())) {
				throw new MyException(400, "营业执照有效期结束时间不能为空", null);
			}
			if (StringUtil.isBlank(storeJoinVo.getBusinessLicenceNumberElectronic())) {
				throw new MyException(400, "营业执照电子版不能为空", null);
			}
			if (StringUtil.isBlank(storeJoinVo.getBusinessSphere())) {
				throw new MyException(400, "经营范围不能为空", null);
			}
			if (null == storeJoinVo.getCompanyRegisteredCapital()) {
				throw new MyException(400, "注册资金不能为空", null);
			}
			if (StringUtil.isBlank(storeJoinVo.getTaxRegistrationCertificate())) {
				throw new MyException(400, "税务登记号不能为空", null);
			}
			if (StringUtil.isBlank(storeJoinVo.getOrganizationCode())) {
				throw new MyException(400, "组织机构代码不能为空", null);
			}
		}
		Map<String, Object> attrsStoreJoin = new HashMap<String, Object>();
		attrsStoreJoin.put("storeId.id", gjfStoreInfo.getId());
		GjfStoreJoinin gjfStoreJoinin = gjfStoreInfoDao.query(GjfStoreJoinin.class, attrsStoreJoin);
		gjfStoreInfo.setStoreName(storeInfoVo.getStoreName());
		gjfStoreInfo.setStoreNum(DateHelper.dataToString(new Date(), "yyyyMMddHHmmss") + RandUtil.getRandomStr(2));
		gjfStoreInfo.setMemberId(gjfMemberInfo);
		gjfStoreInfo.setSellerName(storeInfoVo.getSellerName());
		gjfStoreInfo.setSellerMobile(storeInfoVo.getSellerMobile());
		gjfStoreInfo.setSellerEmail(storeInfoVo.getSellerEmail());

		/*
		 * gjfStoreInfo.setLatitude(23.1357768997); //TODO 先默认经纬度
		 * gjfStoreInfo.setLongitude(113.2416165593);
		 */

		String[] addressId = storeInfoVo.getStoreCitys().split(",");
		Map<String, Object> proAttrs = new HashMap<String, Object>();
		Map<String, Object> cityAttrs = new HashMap<String, Object>();
		Map<String, Object> areaAttrs = new HashMap<String, Object>();
		Object proObj = null;
		Object cityObj = null;
		Object areaObj = null;
		if (addressId.length > 0) {
			proAttrs.put("provinceId", Long.valueOf(addressId[0]));
			proObj = gjfStoreInfoDao.query(GjfAddressProvince.class, proAttrs);
			if (ObjValid.isNotValid(proObj)) {
				throw new MyException(400, "省份不能为空", null);
			}
		}
		if (addressId.length > 1) {
			cityAttrs.put("cityId", Long.valueOf(addressId[1]));
			cityObj = gjfStoreInfoDao.query(GjfAddressCity.class, cityAttrs);
			if (ObjValid.isNotValid(cityObj)) {
				throw new MyException(400, "城市不能为空", null);
			}
		}
		if (addressId.length > 2) {
			areaAttrs.put("areaId", Long.valueOf(addressId[2]));
			areaObj = gjfStoreInfoDao.query(GjfAddressArea.class, areaAttrs);
		}
		gjfStoreInfo.setProvinceId((GjfAddressProvince) proObj);
		gjfStoreInfo.setCityId((GjfAddressCity) cityObj);
		if (ObjValid.isValid(areaObj)) {
			gjfStoreInfo.setAreaId((GjfAddressArea) areaObj);
		}
		gjfStoreInfo.setAddressDetail(storeInfoVo.getAddressDetail());
		gjfStoreInfo.setStoreAddTime(new Date());
		if (StringUtil.isNotBlank(storeInfoVo.getStoreLogoUrl())) {
			gjfStoreInfo.setStoreLogoUrl(storeInfoVo.getStoreLogoUrl());
		}
		if (StringUtil.isNotBlank(storeInfoVo.getStoreBanner())) {
			gjfStoreInfo.setStoreBanner(storeInfoVo.getStoreBanner());
		}
		if (StringUtil.isNotBlank(storeInfoVo.getStoreKeywords())) {
			gjfStoreInfo.setStoreKeywords(storeInfoVo.getStoreKeywords());
		}
		if (StringUtil.isNotBlank(storeInfoVo.getStoreDescription())) {
			gjfStoreInfo.setStoreDescription(storeInfoVo.getStoreDescription());
		}

		gjfStoreInfo.setStoreDividendsTotalMoney(new BigDecimal(0.00));
		gjfStoreInfo.setStoreDividendsTotalMoneyBla(new BigDecimal(0.00));
		gjfStoreInfo.setStoreDividendsNum(new BigDecimal(0.00));
		gjfStoreInfo.setStoreDividendsMoneyBla(new BigDecimal(0.00));
		gjfStoreInfo.setStoreSaleTotalMoney(new BigDecimal(0.00));
		gjfStoreInfo.setStoreBenefitTotalMoney(new BigDecimal(0.00));

		gjfStoreInfo.setStoreRecommend("0");
		gjfStoreInfo.setStoreCreditScore(0L);
		gjfStoreInfo.setStoreDesccreditScore(new BigDecimal(5.00));
		gjfStoreInfo.setStoreServiceCreditScore(new BigDecimal(5.00));
		gjfStoreInfo.setStoreDeliveryCreditScore(new BigDecimal(5.00));
		gjfStoreInfo.setStoreCollectNum(0L);
		gjfStoreInfo.setStoreSalesNum(0L);
		gjfStoreInfo.setStoreBefCustomer(null);
		gjfStoreInfo.setStoreAftCustomer(null);
		gjfStoreInfo.setStoreWorkingTime("7X24");
		gjfStoreInfo.setStoreFreePrice(new BigDecimal(0.00));
		gjfStoreInfo.setStoreIsOwnShop(storeInfoVo.getStoreIsOwnShop());
		gjfStoreInfo.setStoreMargin(new BigDecimal(0.00));
		gjfStoreInfo.setStoreRealGiftRatio(new BigDecimal(88));
		gjfStoreInfo.setStoreRealIncomeRatio(new BigDecimal(12));
		gjfStoreInfo.setStorePro(storeInfoVo.getStorePro());
		gjfStoreInfo.setStoreType(storeInfoVo.getStoreType());
		gjfStoreInfo.setStoreStatus("2");
		gjfStoreInfo.setIsDel("1");
		gjfStoreInfo
			.setToken(Sha256.getSha256Hash(gjfStoreInfo.getStoreNum(), gjfStoreInfo.getStoreName(), CommonStatus.SIGN_KEY_NUM));
		// 根据选择的地址调用地图API获取详细的经纬度 TODO
		// gjfStoreInfo.setLongitude(longitude);
		// gjfStoreInfo.setDimension(dimension);
		gjfStoreInfo.setStoreIsOwnShop("0");
		gjfStoreInfoDao.update(gjfStoreInfo);

		gjfStoreJoinin.setBankAccountName(storeJoinVo.getBankAccountName());
		gjfStoreJoinin.setBankAccountNumber(storeJoinVo.getBankAccountNumber());
		gjfStoreJoinin.setStoreId(gjfStoreInfo);

		gjfStoreJoinin.setBusinessLicenceNumber(storeJoinVo.getBusinessLicenceNumber());
		gjfStoreJoinin.setBusinessLicenceAddress(storeJoinVo.getBusinessLicenceAddress());
		gjfStoreJoinin.setBusinessLicenceNumberElectronic(storeJoinVo.getBusinessLicenceNumberElectronic());
		gjfStoreJoinin.setBusinessSphere(storeJoinVo.getBusinessSphere());
		gjfStoreJoinin.setBusinessLicenceStart(storeJoinVo.getBusinessLicenceStart());
		gjfStoreJoinin.setBusinessLicenceEnd(storeJoinVo.getBusinessLicenceEnd());
		gjfStoreJoinin.setBusinessLicenceNumber(storeJoinVo.getBusinessLicenceNumber());
		// 个体入驻
		if ("0".equals(storeInfoVo.getStoreType())) {
			gjfStoreJoinin.setIsSettlementAccount("1");
		} else {
			// 判断是否作为结算账户
			String isSettlementAccount = storeJoinVo.getIsSettlementAccount();
			if (StringUtil.isBlank(isSettlementAccount) || (!"0".equals(isSettlementAccount) && !"1".equals(isSettlementAccount))) {
				gjfStoreJoinin.setIsSettlementAccount("0");
			} else {
				gjfStoreJoinin.setIsSettlementAccount(isSettlementAccount);
			}
			gjfStoreJoinin.setBankName(storeJoinVo.getBankName());
			gjfStoreJoinin.setBankAddress(storeJoinVo.getBankAddress());
			gjfStoreJoinin.setSettlementBankAccountName(storeJoinVo.getSettlementBankAccountName());
			gjfStoreJoinin.setSettlementBankAccountNumber(storeJoinVo.getSettlementBankAccountNumber());
			gjfStoreJoinin.setSettlementBankName(storeJoinVo.getSettlementBankName());
			gjfStoreJoinin.setSettlementBankAddress(storeJoinVo.getSettlementBankAddress());
			gjfStoreJoinin.setCompanyRegisteredCapital(storeJoinVo.getCompanyRegisteredCapital());
			gjfStoreJoinin.setTaxRegistrationCertificate(storeJoinVo.getTaxRegistrationCertificate());
			gjfStoreJoinin.setOrganizationCode(storeJoinVo.getOrganizationCode());
		}
		gjfStoreInfoDao.update(gjfStoreJoinin);

		// sendMessage(gjfStoreInfo);
		return new ResultVo(200, "提交成功，请等待审核", null);
	}

	/*
	 * 修改店铺信息
	 * 
	 * @see cc.messcat.gjfeng.service.store.GjfStoreInfoService#updateStore(cc.
	 * messcat.gjfeng.entity.GjfStoreInfo)
	 */
	@Override
	public ResultVo updateStore(GjfStoreInfo gjfStoreInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", gjfStoreInfo.getId());
		GjfStoreInfo gjfStoreInfo0 = gjfStoreInfoDao.query(GjfStoreInfo.class, map);
		if (!BeanUtil.isValid(gjfStoreInfo0)) {
			throw new MyException(400, "店铺不存在", null);
		}

		// 调整经纬度
		if (!BeanUtil.isValid(gjfStoreInfo0.getProvinceId()) || !BeanUtil.isValid(gjfStoreInfo0.getCityId())
			|| !BeanUtil.isValid(gjfStoreInfo0.getAreaId())) {
			Map<String, Object> map0 = new HashMap<String, Object>();
			GjfAddressProvince provice = gjfStoreInfoDao.query(GjfAddressProvince.class, map0);

			map.put("id", gjfStoreInfo.getCityId().getId());
			GjfAddressCity city = gjfStoreInfoDao.query(GjfAddressCity.class, map0);

			map.put("id", gjfStoreInfo.getAreaId().getId());
			GjfAddressArea area = gjfStoreInfoDao.query(GjfAddressArea.class, map0);

			// 根据地址获取经纬度
			String address = "";
			if (ObjValid.isValid(provice)) {
				address += provice.getProvince();
			}
			if (ObjValid.isValid(city)) {
				address += city.getCity();
			}
			if (ObjValid.isValid(area)) {
				address += area.getArea();
			}
			if (StringUtil.isBlank(gjfStoreInfo.getAddressDetail())) {
				address += gjfStoreInfo.getAddressDetail();
			}
			Map<String, BigDecimal> map1 = BaiduApi.getGeocoderLatitude(address);
			gjfStoreInfo.setLongitude(map1.get("lng").doubleValue());
			gjfStoreInfo.setLatitude(map1.get("lat").doubleValue());
		} else {
			if (gjfStoreInfo.getProvinceId().getId() != gjfStoreInfo0.getProvinceId().getId()
				|| gjfStoreInfo.getCityId().getId() != gjfStoreInfo0.getCityId().getId()
				|| gjfStoreInfo.getAreaId().getId() != gjfStoreInfo0.getAreaId().getId()
				|| gjfStoreInfo.getAddressDetail() != gjfStoreInfo0.getAddressDetail()) {
				Map<String, Object> map0 = new HashMap<String, Object>();
				map.put("id", gjfStoreInfo.getProvinceId().getId());
				GjfAddressProvince provice = gjfStoreInfoDao.query(GjfAddressProvince.class, map0);

				map.put("id", gjfStoreInfo.getCityId().getId());
				GjfAddressCity city = gjfStoreInfoDao.query(GjfAddressCity.class, map0);

				map.put("id", gjfStoreInfo.getAreaId().getId());
				GjfAddressArea area = gjfStoreInfoDao.query(GjfAddressArea.class, map0);

				// 根据地址获取经纬度
				String address = "";
				if (ObjValid.isValid(provice)) {
					address += provice.getProvince();
				}
				if (ObjValid.isValid(city)) {
					address += city.getCity();
				}
				if (ObjValid.isValid(area)) {
					address += area.getArea();
				}
				if (StringUtil.isBlank(gjfStoreInfo.getAddressDetail())) {
					address += gjfStoreInfo.getAddressDetail();
				}
				Map<String, BigDecimal> map1 = BaiduApi.getGeocoderLatitude(address);
				gjfStoreInfo.setLongitude(map1.get("lng").doubleValue());
				gjfStoreInfo.setLatitude(map1.get("lat").doubleValue());
			}

		}
		try {
			gjfStoreInfo0 = BeanUtil.setBeanByOtherBeanWithoutNull(gjfStoreInfo0, gjfStoreInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		gjfStoreInfoDao.updateObj(gjfStoreInfo0);

		return new ResultVo(200, "修改成功", null);
	}

	/*
	 * 修改店铺的banner
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.store.GjfStoreInfoService#updateStoreBanner(
	 * java.lang.Long, java.lang.String)
	 */
	public ResultVo updateStoreBanner(Long storeId, String bannerImgUrl) {
		if (ObjValid.isNotValid(storeId)) {
			throw new MyException(400, "店铺不存在", null);
		}
		if (StringUtil.isBlank(bannerImgUrl)) {
			throw new MyException(400, "banner图不能为空", null);
		}
		Object o = gjfStoreInfoDao.get(storeId, GjfStoreInfo.class.getName());
		if (ObjValid.isNotValid(o)) {
			throw new MyException(400, "店铺不存在", null);
		}
		GjfStoreInfo gjfStoreInfo = (GjfStoreInfo) o;
		gjfStoreInfo.setStoreBanner(bannerImgUrl);
		gjfStoreInfoDao.update(gjfStoreInfo);
		return new ResultVo(200, "修改成功", null);

	}

	/*
	 * 修改店铺的简介
	 * 
	 * @see cc.messcat.gjfeng.service.store.GjfStoreInfoService#
	 * updateStoreDescription(java.lang.Long, java.lang.String)
	 */
	public ResultVo updateStoreDescription(Long storeId, String description) {
		if (ObjValid.isNotValid(storeId)) {
			throw new MyException(400, "店铺不存在", null);
		}
		if (StringUtil.isBlank(description)) {
			throw new MyException(400, "简介不能为空", null);
		}
		Object o = gjfStoreInfoDao.get(storeId, GjfStoreInfo.class.getName());
		if (ObjValid.isNotValid(o)) {
			throw new MyException(400, "店铺不存在", null);
		}
		GjfStoreInfo gjfStoreInfo = (GjfStoreInfo) o;
		gjfStoreInfo.setStoreDescription(description);
		gjfStoreInfoDao.update(gjfStoreInfo);
		return new ResultVo(200, "修改成功", null);
	}

	/*
	 * 修改店铺审核状态
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.store.GjfStoreInfoService#updateAduitStatus(
	 * java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo updateAduitStatus(Long id, String aduitStatus, String token, String auditStatusReason) {
		if (StringUtil.isBlank(aduitStatus)
			|| (!"0".equals(aduitStatus) && !"1".equals(aduitStatus) && !"2".equals(aduitStatus) && !"3".equals(aduitStatus))) {
			return new ResultVo(400, "修改状态有误", null);
		}
		Object o = findStoreById(id, token).getResult();
		if (ObjValid.isNotValid(o)) {
			return new ResultVo(400, "店铺不存在", null);
		}
		GjfStoreInfo gjfStoreInfo = (GjfStoreInfo) o;
		gjfStoreInfo.setStoreStatus(aduitStatus);
		gjfStoreInfo.setStoreAduitTime(new Date());
		if (aduitStatus.equals("1")) {

			Map<String, Object> attrs = new HashMap<String, Object>();
			Map<String, Object> props = new HashMap<String, Object>();
			attrs.put("id", gjfStoreInfo.getMemberId().getId());
			props.put("type", "1");
			gjfStoreInfoDao.update(GjfMemberInfo.class, props, attrs);

			// 根据地址获取经纬度
			String address = "";
			if (ObjValid.isValid(gjfStoreInfo.getProvinceId())) {
				address += gjfStoreInfo.getProvinceId().getProvince();
			}
			if (ObjValid.isValid(gjfStoreInfo.getCityId())) {
				address += gjfStoreInfo.getCityId().getCity();
			}
			if (ObjValid.isValid(gjfStoreInfo.getProvinceId())) {
				address += gjfStoreInfo.getProvinceId().getProvince();
			}
			if (ObjValid.isValid(gjfStoreInfo.getAreaId())) {
				address += gjfStoreInfo.getAreaId().getArea();
			}
			if (StringUtil.isBlank(gjfStoreInfo.getAddressDetail())) {
				address += gjfStoreInfo.getAddressDetail();
			}
			Map<String, BigDecimal> map = BaiduApi.getGeocoderLatitude(address);
			gjfStoreInfo.setLongitude(map.get("lng").doubleValue());
			gjfStoreInfo.setLatitude(map.get("lat").doubleValue());

		}
		gjfStoreInfo.setAuditStatusReason(auditStatusReason);
		gjfStoreInfoDao.update(gjfStoreInfo);
		if (aduitStatus.equals("1")) {
			sendMessage(gjfStoreInfo);
		}
		return new ResultVo(200, "修改成功", null);
	}

	/*
	 * 删除店铺
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.store.GjfStoreInfoService#delStore(java.lang.
	 * Long, java.lang.String)
	 */
	@Override
	public ResultVo modifyStoreStatus(Long id, String token) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("id", id);
		attrs.put("token", token);
		GjfStoreInfo gjfStoreInfo = gjfStoreInfoDao.query(GjfStoreInfo.class, attrs);
		gjfStoreInfo.getMemberId().setType("0");
		gjfStoreInfo.setIsDel("0");
		gjfStoreInfoDao.updateObj(gjfStoreInfo);
		gjfStoreInfoDao.update(gjfStoreInfo.getMemberId());
		return new ResultVo(200, "删除成功", null);
	}

	/*
	 * 根据账户查询店铺
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.store.GjfStoreInfoService#findStoreByAccount(
	 * java.lang.String)
	 */
	@Override
	public ResultVo findStoreByAccount(String account) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("memberId.mobile", account);
		attrs.put("isDel", "1");
		attrs.put("storeStatus", "1");
		GjfStoreInfo gjfStoreInfo=gjfStoreInfoDao.query(GjfStoreInfo.class, attrs);
		return new ResultVo(200, "查询成功", ObjValid.isNotValid(gjfStoreInfo) ? null : BeanUtilsEx.copyBean(StoreInfoVo.class, gjfStoreInfo));
	}

	/*
	 * 根据id查询店铺
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.store.GjfStoreInfoService#findStoreById(java.
	 * lang.Long, java.lang.String)
	 */
	@Override
	public ResultVo findStoreById(Long id, String token) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("id", id);
		attrs.put("token", token);
		return new ResultVo(200, "查询成功", gjfStoreInfoDao.query(GjfStoreInfo.class, attrs));
	}

	/*
	 * 根据商家Id查询店铺
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.store.GjfStoreInfoService#findStoreByMemberId(
	 * java.lang.Long)
	 */
	@Override
	public ResultVo findStoreByMemberId(Long id) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("memberId.id", id);
		return new ResultVo(200, "查询成功", gjfStoreInfoDao.query(GjfStoreInfo.class, attrs));
	}

	/*
	 * 分页查询店铺
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.store.GjfStoreInfoService#findStoreByPager(int,
	 * int, java.lang.String)
	 */
	@Override
	public ResultVo findStoreByPager(int pageNo, int pageSize, String likeValue, String storePro, String storeType,
		String storeStatus,String isActivityStore) {
		return new ResultVo(200, "查询成功",
			gjfStoreInfoDao.findStoreByPager(pageNo, pageSize, likeValue, storePro, storeType, storeStatus,isActivityStore));
	}

	/*
	 * 根据地区查找店铺
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.store.GjfStoreInfoService#findStoreByCondition(
	 * int, int, java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findStoreByCondition(int pageNo, int pageSize, Long provinceId, Long cityId, Long areaId) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		if (ObjValid.isValid(provinceId)) {
			attrs.put("provinceId.provinceId", provinceId);
		}
		if (ObjValid.isValid(cityId)) {
			attrs.put("cityId.cityId", cityId);
		}
		if (ObjValid.isValid(areaId)) {
			attrs.put("areaId.areaId", areaId);
		}
		Pager pager = new Pager(pageSize, pageNo,
			Integer.parseInt(String.valueOf(gjfStoreInfoDao.queryTotalRecord(GjfStoreInfo.class, attrs))),
			gjfStoreInfoDao.queryList(GjfStoreInfo.class, (pageNo - 1) * pageSize, pageSize, "id", "asc", attrs));
		return new ResultVo(200, "查询成功", pager);
	}

	@Override
	public ResultVo modifyStoreAddress(Long storeId, String sellerMobile, String cityValue, String addressDetail) {
		Map<String, Object> attr2 = new HashMap<String, Object>();
		attr2.put("id", storeId);
		GjfStoreInfo gjfStoreInfo = gjfStoreInfoDao.query(GjfStoreInfo.class, attr2);
		if (!BeanUtil.isValid(gjfStoreInfo)) {
			throw new MyException(400, "店铺不存在", null);
		}

		if (BeanUtil.isValid(cityValue)) {
			String[] code = cityValue.split(",");
			Map<String, Object> attrs = new HashMap<String, Object>();
			attrs.put("provinceId", Long.valueOf(code[0]));
			GjfAddressProvince gjfAddressProvince = gjfStoreInfoDao.query(GjfAddressProvince.class, attrs);

			Map<String, Object> attrs0 = new HashMap<String, Object>();
			attrs0.put("cityId", Long.valueOf(code[1]));
			GjfAddressCity gjfAddressCity = gjfStoreInfoDao.query(GjfAddressCity.class, attrs0);

			Map<String, Object> attrs1 = new HashMap<String, Object>();
			attrs1.put("areaId", Long.valueOf(code[2]));
			GjfAddressArea gjfAddressArea = gjfStoreInfoDao.query(GjfAddressArea.class, attrs1);

			gjfStoreInfo.setProvinceId(gjfAddressProvince);
			gjfStoreInfo.setCityId(gjfAddressCity);
			gjfStoreInfo.setAreaId(gjfAddressArea);
		}

		gjfStoreInfo.setAddressDetail(addressDetail);
		gjfStoreInfo.setSellerMobile(sellerMobile);
		gjfStoreInfoDao.update(gjfStoreInfo);
		return new ResultVo(200, "修改成功", null);
	}

	/**
	 * 后台充值消费授信额度
	 * 
	 * @param id
	 * @param token
	 * @param account
	 * @param tradeMoney
	 * @param type
	 * @return
	 */
	@Override
	public ResultVo updateLineOfCreadit(Long id, String token, String account, Double tradeMoney, String type) {
		if (StringUtil.isBlank(account)) {
			return new ResultVo(400, "账户为空", null);
		}
		if (tradeMoney == 0.00) {
			return new ResultVo(400, "消费金额不能为零", null);
		}
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("id", id);
		attrs.put("token", token);
		GjfStoreInfo gjfStoreInfo = gjfStoreInfoDao.query(GjfStoreInfo.class, attrs);
		if (ObjValid.isNotValid(gjfStoreInfo)) {
			return new ResultVo(400, "店铺数据有误", null);
		}
		GjfMemberInfo gjfMemberInfo = gjfStoreInfo.getMemberId();
		BigDecimal temp = new BigDecimal("0");
		if ("0".equals(type)) {
			ResultVo resultVo = gjfTradeService.addShouXin("5", account, -tradeMoney,"0","");
			if (resultVo.getCode() == 200) {
				temp = new BigDecimal(-tradeMoney);
				gjfMemberInfo.setLineOfCrade(gjfMemberInfo.getLineOfCrade().add(temp));
				gjfMemberInfoService.updateMember(gjfMemberInfo);
				return new ResultVo(200, "操作成功", null);
			} else {
				return resultVo;
			}
		} else if ("1".equals(type)) {
			ResultVo resultVo = gjfTradeService.addShouXin("5", account, tradeMoney,"0","");
			if (resultVo.getCode() == 200) {
				temp = new BigDecimal(tradeMoney);
				gjfMemberInfo.setLineOfCrade(gjfMemberInfo.getLineOfCrade().add(temp));
				gjfMemberInfoService.updateMember(gjfMemberInfo);
			}
			return resultVo;
		}
		return null;
	}

	// 发送mq消息
	public void sendMessage(GjfStoreInfo gjfStoreInfo) {
		// 发送短信消息通知
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("mobile", gjfStoreInfo.getMemberId().getMobile());
			dataMap.put("templateCode", CommonProperties.MNS_TEMPLATE_ADUIT);
			if (gjfStoreInfo.getStoreStatus().equals("1")) {
				dataMap.put("variable0", gjfStoreInfo.getMemberId().getName());
				dataMap.put("variable1", gjfStoreInfo.getStoreName());
			}
			Object toJSON = JSONObject.fromObject(dataMap);
			final String str = toJSON.toString();
			notifyJmsTemplate.send("SmsSendNotify", new MessageCreator() {
				public Message createMessage(Session session) throws JMSException {
					Message obj = session.createTextMessage(str);
					return obj;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 根据Id和token查询商家入驻
	 * @see cc.messcat.gjfeng.service.store.GjfStoreInfoService#findStoreJoin(java.lang.Long, java.lang.String)
	 */
	@Override
	public ResultVo findStoreJoin(Long id, String token) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("storeId.id", id);
		attrs.put("storeId.token", token);
		return new ResultVo(200, "查询成功", gjfStoreInfoDao.query(GjfStoreJoinin.class, attrs));
	}

	/*
	 * 查询授信充值记录
	 * @see cc.messcat.gjfeng.service.store.GjfStoreInfoService#findRechargeLineCreditByPage(int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo findRechargeLineCreditByPage(int pageNo, int pageSize, String tradeNo, String name, String addTime,String endTime,
		String payType, String tradeStatus,String ste){
		return new ResultVo(200, "查询成功",
			gjfStoreInfoDao.findRechargeLineCreditByPage(pageNo, pageSize, tradeNo, name, addTime,endTime, payType, tradeStatus,ste));
	}
	
	/*
	 * 汇总当前查询条件授信充值列表
	 * @see cc.messcat.gjfeng.service.store.GjfStoreInfoService#findRechargeLineCredit(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ResultVo findRechargeLineCredit(String tradeNo,String  name,String addTime,String endTime,String payType,String tradeStatus){
		return new ResultVo(200, "查询成功",
				gjfStoreInfoDao.findRechargeLineCredit( tradeNo, name, addTime,endTime, payType, tradeStatus));
	}

	/*
	 * 后台重新定位店铺位置
	 * @see cc.messcat.gjfeng.service.store.GjfStoreInfoService#updateLocation(java.lang.Long, java.lang.String)
	 */
	@Override
	public ResultVo updateLocation(Long id, String token) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("id", id);
		attrs.put("token", token);
		GjfStoreInfo gjfStoreInfo = gjfStoreInfoDao.query(GjfStoreInfo.class, attrs);
		if (ObjValid.isNotValid(gjfStoreInfo)) {
			return new ResultVo(400, "店铺数据有误", null);
		}

		// 根据地址获取经纬度
		String address = "";
		if (ObjValid.isValid(gjfStoreInfo.getProvinceId().getProvince())) {
			address += gjfStoreInfo.getProvinceId().getProvince();
		}
		if (ObjValid.isValid(gjfStoreInfo.getCityId().getCity())) {
			address += gjfStoreInfo.getCityId().getCity();
		}
		if (ObjValid.isValid(gjfStoreInfo.getAreaId().getArea())) {
			address += gjfStoreInfo.getAreaId().getArea();
		}
		if (StringUtil.isBlank(gjfStoreInfo.getAddressDetail())) {
			address += gjfStoreInfo.getAddressDetail();
		}
		Map<String, BigDecimal> map1 = BaiduApi.getGeocoderLatitude(address);
		gjfStoreInfo.setLongitude(map1.get("lng").doubleValue());
		gjfStoreInfo.setLatitude(map1.get("lat").doubleValue());
		gjfStoreInfoDao.update(gjfStoreInfo);
		return new ResultVo(200, "操作成功!", null);
	}

	/*
	 * 查询代理下店铺
	 * @see cc.messcat.gjfeng.service.store.GjfStoreInfoService#findStoreByAgent(int, int, java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo findStoreByAgent(int pageNo, int pageSize, Long id, String token, String identity) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("id", id);
		attrs.put("token", token);
		GjfMemberInfo memberInfo = gjfStoreInfoDao.query(GjfMemberInfo.class, attrs);
		return new ResultVo(200, "操作成功", gjfStoreInfoDao.findStoreByAgent(pageNo, pageSize, memberInfo, identity));
	}

	/*
	 * 查询代理下商家让利流水
	 * @see cc.messcat.gjfeng.service.store.GjfStoreInfoService#findStoreBenefitByAgent(int, int, java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo findStoreBenefitByAgent(int pageNo, int pageSize, Long id, String token, String identity) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("id", id);
		attrs.put("token", token);
		GjfMemberInfo memberInfo = gjfStoreInfoDao.query(GjfMemberInfo.class, attrs);
		return new ResultVo(200, "操作成功", gjfStoreInfoDao.findStoreBenefitByAgent(pageNo, pageSize, memberInfo, identity));
	}

	@Override
	public ResultVo findStoreByStoreId(Long storeId) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("id", storeId);		
		return new ResultVo(200, "查询成功", gjfStoreInfoDao.query(GjfStoreInfo.class, attrs));
	}

	@Override
	public ResultVo findStoreByColumn(Long columnId, Map<String, Object> param) {
		return new ResultVo(200, "操作成功", gjfStoreInfoDao.findStoreByColumn(columnId, param));
	}

	@Override
	public ResultVo findStores(Map<String, Object> param) {
		return new ResultVo(200, "操作成功", gjfStoreInfoDao.findStores(param));
	}
	
}
