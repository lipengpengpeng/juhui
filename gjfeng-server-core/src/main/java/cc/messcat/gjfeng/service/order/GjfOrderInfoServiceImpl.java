package cc.messcat.gjfeng.service.order;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;

import cc.messcat.gjfeng.common.bean.Pager;
import cc.messcat.gjfeng.common.constant.CommonProperties;
import cc.messcat.gjfeng.common.constant.CommonStatus;
import cc.messcat.gjfeng.common.exception.MyException;
import cc.messcat.gjfeng.common.jd.JdNewUtil;
import cc.messcat.gjfeng.common.jd.bean.GoodList;
import cc.messcat.gjfeng.common.jd.bean.OrderResult;
import cc.messcat.gjfeng.common.jd.bean.OrderSuccessResult;
import cc.messcat.gjfeng.common.jd.bean.ProductStock;
import cc.messcat.gjfeng.common.netFriends.NetFriendUtil;
import cc.messcat.gjfeng.common.pay.alipay.util.AlipayRefundUtil;
import cc.messcat.gjfeng.common.pay.wechat.util.RefundUtil;
import cc.messcat.gjfeng.common.pay.wechat.weixin.popular.api.TokenAPI;
import cc.messcat.gjfeng.common.pay.wechat.weixin.popular.bean.Token;
import cc.messcat.gjfeng.common.proprietary.util.ProUtil;
import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.util.BeanUtilsEx;
import cc.messcat.gjfeng.common.util.DateHelper;
import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.util.RandUtil;
import cc.messcat.gjfeng.common.util.Sha256;
import cc.messcat.gjfeng.common.util.StringUtil;
import cc.messcat.gjfeng.common.vo.app.OrderAddVo;
import cc.messcat.gjfeng.common.vo.app.OrderGoodsVo;
import cc.messcat.gjfeng.common.vo.app.OrderInfoVo;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.common.wechat.popular.support.MessageManager;
import cc.messcat.gjfeng.dao.benefit.GjfBenefitInfoDao;
import cc.messcat.gjfeng.dao.order.GjfOrderInfoDao;
import cc.messcat.gjfeng.entity.GjfAccessToken;
import cc.messcat.gjfeng.entity.GjfFhTreasureInfo;
import cc.messcat.gjfeng.entity.GjfMemberAddress;
import cc.messcat.gjfeng.entity.GjfMemberConsumptiomNum;
import cc.messcat.gjfeng.entity.GjfMemberDiviNumHistory;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.entity.GjfMemberTradeDetail;
import cc.messcat.gjfeng.entity.GjfMemberTradeLog;
import cc.messcat.gjfeng.entity.GjfMemberTreasureTrade;
import cc.messcat.gjfeng.entity.GjfMemberUpgradeVipDirectMoney;
import cc.messcat.gjfeng.entity.GjfMerchantModelAgentTradeHistory;
import cc.messcat.gjfeng.entity.GjfMerchantUpgradeHistory;
import cc.messcat.gjfeng.entity.GjfOrderAddress;
import cc.messcat.gjfeng.entity.GjfOrderGoods;
import cc.messcat.gjfeng.entity.GjfOrderInfo;
import cc.messcat.gjfeng.entity.GjfProductAttrStock;
import cc.messcat.gjfeng.entity.GjfProductInfo;
import cc.messcat.gjfeng.entity.GjfSetBaseInfo;
import cc.messcat.gjfeng.entity.GjfSetDividends;
import cc.messcat.gjfeng.entity.GjfStoreInfo;
import cc.messcat.gjfeng.service.benefit.GjfBenefitInfoService;
import cc.messcat.gjfeng.service.member.GjfAddressService;
import cc.messcat.gjfeng.service.member.GjfMemberInfoService;
import cc.messcat.gjfeng.service.product.GjfProductAttrService;
import net.sf.json.JSONObject;

@Service("gjfOrderInfoService")
public class GjfOrderInfoServiceImpl implements GjfOrderInfoService {

	@Autowired
	@Qualifier("gjfOrderInfoDao")
	private GjfOrderInfoDao gjfOrderInfoDao;

	@Autowired
	@Qualifier("gjfBenefitInfoDao")
	private GjfBenefitInfoDao gjfBenefitInfoDao;

	@Autowired
	@Qualifier("gjfMemberInfoService")
	private GjfMemberInfoService gjfMemberInfoService;

	@Autowired
	@Qualifier("gjfProductAttrService")
	private GjfProductAttrService gjfProductAttrService;

	@Autowired
	@Qualifier("gjfAddressService")
	private GjfAddressService gjfAddressService;

	@Autowired
	@Qualifier("gjfBenefitInfoService")
	private GjfBenefitInfoService gjfBenefitInfoService;

	@Autowired
	@Qualifier("notifyJmsTemplate")
	private JmsTemplate notifyJmsTemplate;

	@Value("${gjfeng.client.project.url}")
	private String projectName;

	/*
	 * 跳转到下单
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.order.GjfOrderInfoService#toAddOrder(java.util.
	 * List)
	 */
	public ResultVo toAddOrder(List<OrderAddVo> orderAddVos, String account) {
		// 创建基本信息map
		Map<String, Object> dataMap = new HashMap<String, Object>();
		// 创建商品信息集合
		List<OrderGoodsVo> goodsVos = new ArrayList<OrderGoodsVo>();
		// 用于记录总金额
		BigDecimal totalAmount = new BigDecimal(0.00);
		// 记录积分需要金额
		BigDecimal pointNiceAmount = new BigDecimal(0.00);
		// 是否能使用积分的状态 0 否 1是
		int isCanUseCou = 0;
		// 是否为采购商品
		String isWholesale = "0";
		// 是否升级vip产品
		String isUpgradePro = "0";

		Long proId = 0L;
		// 用于记录邮费
		BigDecimal pos = new BigDecimal(0.00);
		// 标准版总金额
		BigDecimal standardTotalAmount = new BigDecimal(0.00);
		// 尊享版金额
		BigDecimal honourTotalAmount = new BigDecimal(0.00);
		// 赠的积分
		BigDecimal pointMoney = new BigDecimal(0.00);
		// 赠的代金券
		BigDecimal vocMoney = new BigDecimal(0.00);
		// 产品来源
		String sourceGood = "0";
		// 如果是产品池产品商品信息数组
		List<GjfOrderGoods> orderGoodList = new ArrayList<>();

		// 创建查询用户信息map
		Map<String, Object> memMap = new HashMap<>();
		// 用户电话
		memMap.put("mobile", account);
		// 获取用户信息
		GjfMemberInfo memberInfo = gjfOrderInfoDao.query(GjfMemberInfo.class, memMap);

		// 获取平台信息
		String platform = "0";
		Map<String, Object> plAttrs = new HashMap<>();
		plAttrs.put("key", "PLATFORM");
		plAttrs.put("status", "1");
		GjfSetBaseInfo plaBase = gjfOrderInfoDao.query(GjfSetBaseInfo.class, plAttrs);
		if (BeanUtil.isValid(plaBase)) {
			platform = plaBase.getValue();
		}

		// 迭代商品信息
		for (OrderAddVo orderAddVo : orderAddVos) {
			// 创建订单信息对象
			GjfOrderGoods orderGood = new GjfOrderGoods();

			// 创建商品vo对象
			OrderGoodsVo goodsVo = new OrderGoodsVo();
			// 创建查询map
			Map<String, Object> attrs = new HashMap<>();
			// 查询商品信息
			attrs.put("id", orderAddVo.getGoodsId());
			GjfProductInfo proInfo = gjfOrderInfoDao.query(GjfProductInfo.class, attrs);
			if (!BeanUtil.isValid(proInfo)) {
				attrs.remove("id");
				attrs.put("netProId", orderAddVo.getGoodsId());
				proInfo = gjfOrderInfoDao.query(GjfProductInfo.class, attrs);
			}
			// 如果商品来源是本平台
			if ("0".equals(proInfo.getSuorceGoods())) {
				// 移除attrs里面id
				attrs.remove("id");
				// 设置查询商品属性的参数
				attrs.put("productId.id", orderAddVo.getGoodsId());
				attrs.put("productAttrIds",
						orderAddVo.getGoodsAttrIds().substring(0, orderAddVo.getGoodsAttrIds().length()));
				// 查询商品的库存属性
				GjfProductAttrStock gjfProductAttrStock = gjfOrderInfoDao.query(GjfProductAttrStock.class, attrs);
				// 获取对于属性的商品库存
				goodsVo.setGoodsAmount(gjfProductAttrStock.getPrice());
				// 标准版单价
				goodsVo.setStandardPrice(gjfProductAttrStock.getStandardPrice());
				// 尊享版单价
				goodsVo.setHonourPrice(gjfProductAttrStock.getHonourPrice());

				// 获取商品属性
				goodsVo.setGoodsAttr(String
						.valueOf(gjfProductAttrService.findProAttrByProAttrStock(gjfProductAttrStock).getResult()));
				// 统计购买商品的总金额
				BigDecimal bg = new BigDecimal(orderAddVo.getGoodsNum());

				// 如果为比特
				totalAmount = totalAmount.add(gjfProductAttrStock.getPrice().multiply(bg));

				// 统计标准版金额
				standardTotalAmount = standardTotalAmount.add(gjfProductAttrStock.getStandardPrice().multiply(bg));
				// 统计尊享版金额
				honourTotalAmount = honourTotalAmount.add(gjfProductAttrStock.getHonourPrice().multiply(bg));
				// 如果为积分兑换商品
				if ("1".equals(proInfo.getIsCanUserCou()) && proInfo.getPointNicePrice() != null) {
					pointNiceAmount = pointNiceAmount.add(proInfo.getPointNicePrice().multiply(bg));
				}
				// 如果为责任消费兑换商品
				if ("2".equals(proInfo.getIsCanUserCou()) && proInfo.getPointNicePrice() != null) {
					pointNiceAmount = pointNiceAmount.add(proInfo.getPointNicePrice().multiply(bg));
				}
				if ("3".equals(proInfo.getIsCanUserCou()) && proInfo.getPointNicePrice() != null) {
					pointNiceAmount = pointNiceAmount.add(proInfo.getPointNicePrice().multiply(bg));
				}
			} else if ("1".equals(proInfo.getSuorceGoods()) || "5".equals(proInfo.getSuorceGoods())) {
				goodsVo.setGoodsAmount(proInfo.getPrice());// 友品集
				// 统计购买商品的总金额
				BigDecimal bg = new BigDecimal(orderAddVo.getGoodsNum());

				totalAmount = totalAmount.add(proInfo.getPrice().multiply(bg));

				goodsVo.setGoodsAmount(proInfo.getPrice());
				// 标准版单价
				goodsVo.setStandardPrice(proInfo.getStandardPrice());
				// 尊享版单价
				goodsVo.setHonourPrice(proInfo.getHonourPrice());

				if ("1".equals(proInfo.getIsCanUserCou()) && proInfo.getPointNicePrice() != null) {
					pointNiceAmount = pointNiceAmount.add(proInfo.getPointNicePrice().multiply(bg));
				}
				
				if ("3".equals(proInfo.getIsCanUserCou()) && proInfo.getPointNicePrice() != null) {
					pointNiceAmount = pointNiceAmount.add(proInfo.getPointNicePrice().multiply(bg));
				}

				orderGood.setGoodsId(proInfo);
				orderGood.setGoodsNum(orderAddVo.getGoodsNum());
				sourceGood = proInfo.getSuorceGoods();
				orderGoodList.add(orderGood);
				// 统计标准版金额
				standardTotalAmount = standardTotalAmount.add(proInfo.getStandardPrice().multiply(bg));
				// 统计尊享版金额
				honourTotalAmount = honourTotalAmount.add(proInfo.getHonourPrice().multiply(bg));
			} else if ("2".equals(proInfo.getSuorceGoods())) {
				goodsVo.setGoodsAmount(proInfo.getPrice());// 友品集
				// 统计购买商品的总金额
				BigDecimal bg = new BigDecimal(orderAddVo.getGoodsNum());
				totalAmount = totalAmount.add(proInfo.getPrice().multiply(bg));
				BigDecimal profits = new BigDecimal(0.00);
				// 赠送金额
				// 计算利润
				BigDecimal chajia = proInfo.getMarketPrice().subtract(proInfo.getGcostPrice());
				chajia = chajia.multiply(bg).setScale(2, BigDecimal.ROUND_DOWN);
				if (chajia.doubleValue() > 0) {
					// 获取通道费率比例
					BigDecimal chcRate = new BigDecimal(0.01);
					Map<String, Object> baeseAttrs = new HashMap<>();
					baeseAttrs.put("key", "CAHNELCOST");
					GjfSetBaseInfo chcBase = gjfOrderInfoDao.query(GjfSetBaseInfo.class, baeseAttrs);
					if (BeanUtil.isValid(chcBase)) {
						chcRate = new BigDecimal(chcBase.getValue());
					}
					// 通道费用
					BigDecimal channelCost = proInfo.getMarketPrice().multiply(bg).multiply(chcRate).setScale(2,
							BigDecimal.ROUND_DOWN);
					// 获取增值税加卡通通道费率
					BigDecimal vatRate = new BigDecimal("0.38");
					baeseAttrs.put("key", "VAT");
					GjfSetBaseInfo vatBase = gjfOrderInfoDao.query(GjfSetBaseInfo.class, baeseAttrs);
					if (BeanUtil.isValid(vatBase)) {
						vatRate = new BigDecimal(vatBase.getValue());
					}
					// 增值税加卡通通道费
					BigDecimal vat = chajia.multiply(vatRate).setScale(2, BigDecimal.ROUND_DOWN);
					// 净利润
					BigDecimal netProfit = chajia.subtract(channelCost).subtract(vat).setScale(2,
							BigDecimal.ROUND_DOWN);
					// 分出比例
					BigDecimal giveRate = new BigDecimal(0.5);
					baeseAttrs.put("key", "VOGIVERATE");
					GjfSetBaseInfo giveBase = gjfOrderInfoDao.query(GjfSetBaseInfo.class, baeseAttrs);
					if (BeanUtil.isValid(giveBase)) {
						giveRate = new BigDecimal(giveBase.getValue());
					}
					// 送出部分
					netProfit = netProfit.multiply(giveRate).setScale(2, BigDecimal.ROUND_DOWN);
					// 积分
					profits = netProfit.divide(new BigDecimal(0.12), 2, BigDecimal.ROUND_DOWN);
					if (profits.doubleValue() > proInfo.getMarketPrice().multiply(bg).doubleValue()) {
						pointMoney = pointMoney.add(proInfo.getMarketPrice().multiply(bg)).setScale(2,
								BigDecimal.ROUND_DOWN);
					} else {
						pointMoney = pointMoney.add(profits).setScale(2, BigDecimal.ROUND_DOWN);
					}
					// 代金券
					profits = netProfit.divide(new BigDecimal(0.1), 2, BigDecimal.ROUND_DOWN);
					if (profits.doubleValue() > proInfo.getMarketPrice().multiply(bg).doubleValue()) {
						vocMoney = vocMoney.add(proInfo.getMarketPrice().multiply(bg)).setScale(2,
								BigDecimal.ROUND_DOWN);
					} else {
						vocMoney = vocMoney.add(profits).setScale(2, BigDecimal.ROUND_DOWN);
					}
				}

			}

			// 是否为采购
			isWholesale = proInfo.getIsWholesale();
			// 获取商品图片
			goodsVo.setGoodsImg(proInfo.getImgUrl());
			// 商品名称
			goodsVo.setGoodsName(proInfo.getName());
			// 购买商品数量
			goodsVo.setGoodsNum(orderAddVo.getGoodsNum());
			// 添加到商品集合
			goodsVos.add(goodsVo);

			// 是否为升级vip产品
			isUpgradePro = proInfo.getIsUpgradePro();

			if ("1".equals(proInfo.getIsCanUserCou())) {
				isCanUseCou = 1;
			}
			if ("2".equals(proInfo.getIsCanUserCou())) {
				isCanUseCou = 2;
			}
			if ("3".equals(proInfo.getIsCanUserCou())) {
				isCanUseCou = 3;
			}
			if (proInfo.getPostage() != null) {
				pos = proInfo.getPostage();
			}
			if ("2".equals(proInfo.getSuorceGoods())) {
				proId = proInfo.getId();
			}
		}

		// 如果为产品池产品
		String orderSn = DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6));
		String customerSn = "";
		if ("5".equals(sourceGood)) {
			GjfOrderInfo orderInfo = new GjfOrderInfo();
			orderInfo.setOrderSn(orderSn);
			Map<String, Object> attrs = new HashMap<String, Object>();
			attrs.put("memberId.mobile", account);
			attrs.put("isDefault", "1");
			if (BeanUtil.isValid(sourceGood)) {
				attrs.put("addressSouce", sourceGood);
			}
			GjfMemberAddress address = gjfOrderInfoDao.query(GjfMemberAddress.class, attrs);
			if (!BeanUtil.isValid(address)) {
				attrs.remove("isDefault");

				List<GjfMemberAddress> addresses = gjfOrderInfoDao.queryList(GjfMemberAddress.class, "id", "asc",
						attrs);
				if (BeanUtil.isValid(addresses)) {
					address = addresses.get(0);
				}

			}

			// 请求接口
			if (BeanUtil.isValid(address)) {
				String resutlStr = ProUtil.createOrder(address, orderInfo, orderGoodList);
				com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(resutlStr);
				if (BeanUtil.isValid(jsonObject)) {
					String shipPrice = jsonObject.getString("shipPrice");
					pos = pos.add(new BigDecimal(shipPrice));
					customerSn = jsonObject.getString("order_sn");
				}
			}

		}

		dataMap.put("orderSn", orderSn);
		dataMap.put("customerSn", customerSn);
		dataMap.put("isUpgradePro", isUpgradePro);
		dataMap.put("isCanUseCou", isCanUseCou);
		dataMap.put("pointMoney", pointMoney);
		dataMap.put("vocMoney", vocMoney);
		dataMap.put("proId", proId);
		dataMap.put("goodsVos", goodsVos);
		dataMap.put("totalAmount", totalAmount);
		dataMap.put("standardTotalAmount", standardTotalAmount);
		dataMap.put("honourTotalAmount", honourTotalAmount);
		dataMap.put("pos", pos);
		dataMap.put("isWholesale", isWholesale);
		dataMap.put("pointNiceAmount", pointNiceAmount.add(pos));
		dataMap.put("honourPreferentialMoney", standardTotalAmount.subtract(honourTotalAmount));
		dataMap.put("standardPreferentialMoney", totalAmount.subtract(standardTotalAmount));
		return new ResultVo(200, "操作成功", dataMap);
	}

	/*
	 * 用户下单
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.order.GjfOrderInfoService#addOrder(java.lang.
	 * String, java.util.List, java.lang.String, java.lang.String,
	 * java.lang.Double, java.lang.Long, java.lang.Long)
	 */
	@Override
	public ResultVo addOrder(String account, List<OrderAddVo> orderAddVos, String orderType, String payType,
			String remark, Long couponsId, Long orderAddressId, String logist, String commissionType, String orderSn,
			String customerSn, BigDecimal postage) {
		if ("1".equals(orderType)) {
			if (StringUtil.isBlank(account) || null == orderAddVos || 0 == orderAddVos.size()
					|| StringUtil.isBlank(orderType) || (!"0".equals(orderType) && !"1".equals(orderType))) {
				throw new MyException(400, "下单失败", null);
			}
		}

		GjfMemberAddress gjfMemberAddress;
		if (orderType.equals("1") && ObjValid.isNotValid(orderAddressId)) {
			throw new MyException(400, "下单失败", null);
		} else {
			// 获取收货地址
			Map<String, Object> attrs = new HashMap<String, Object>();
			attrs.put("id", orderAddressId);
			attrs.put("memberId.mobile", account);
			gjfMemberAddress = gjfOrderInfoDao.query(GjfMemberAddress.class, attrs);
		}

		GjfMemberInfo gjfMemberInfo = gjfMemberInfoService.findMember(account);
		if (ObjValid.isNotValid(gjfMemberInfo)) {
			throw new MyException(400, "用户不存在", null);
		}

		if ("0".equals(gjfMemberInfo.getStatus())) {
			throw new MyException(400, "用户已停用", null);
		}

		if (StringUtil.isBlank(payType) || (!payType.equals("0") && !payType.equals("1") && !payType.equals("2")
				&& !payType.equals("3") && !payType.equals("7") && !payType.equals("8") && !payType.equals("9")
				&& !payType.equals("10"))) {
			throw new MyException(400, "支付方式有误", null);
		}
		if (ObjValid.isValid(couponsId)) {
			// 优惠券 TODO
		}

		BigDecimal totalAmount = new BigDecimal(0.00).setScale(2, BigDecimal.ROUND_UP);
		BigDecimal totalStoreBenefitAmount = new BigDecimal(0.00).setScale(2, BigDecimal.ROUND_UP);
		// 记录邮费
		BigDecimal pos = new BigDecimal(0.00).setScale(2, BigDecimal.ROUND_UP);

		// 记录积分商品需要的金额
		BigDecimal pointAmount = new BigDecimal(0.00).setScale(2, BigDecimal.ROUND_UP);

		// 记录商品销售价格金额
		BigDecimal salseAmount = new BigDecimal(0.00);

		// 记录扣减金额
		BigDecimal dicTotalMoney = new BigDecimal(0.00);

		// 记录商品来源
		String suoceGood = "0";

		// 记录商品id
		String goodsId = "";
		// 记录参与分红金额
		BigDecimal benerfitMoney = new BigDecimal(0.00).setScale(2, BigDecimal.ROUND_UP);

		// 记录商品是否采购
		String isWholesale = "0";

		// 记录商品是否为升级vip商品
		String isUpgradePro = "0";

		// 友品集商品信息
		JSONArray array = new JSONArray();

		// 获取平台信息
		String platform = "0";
		Map<String, Object> plAttrs = new HashMap<>();
		plAttrs.put("key", "PLATFORM");
		plAttrs.put("status", "1");
		GjfSetBaseInfo plaBase = gjfOrderInfoDao.query(GjfSetBaseInfo.class, plAttrs);
		if (BeanUtil.isValid(plaBase)) {
			platform = plaBase.getValue();
		}

		List<GjfOrderGoods> gjfOrderGoods = new ArrayList<GjfOrderGoods>();
		if (orderAddVos != null && orderAddVos.size() != 0) {
			for (OrderAddVo orderAddVo : orderAddVos) {
				/*
				 * if (ObjValid.isNotValid(orderAddVo.getGoodsId())) { throw new
				 * MyException(400, "商品不存在", null); }
				 */
				if (ObjValid.isNotValid(orderAddVo.getGoodsNum())) {
					throw new MyException(400, "商品数量要大于1", null);
				}
				GjfOrderGoods orderGoods = new GjfOrderGoods();

				GjfProductInfo gjfProductInfo = null;
				if (ObjValid.isValid(orderAddVo.getGoodsId())) {
					Object o1 = gjfOrderInfoDao.get(orderAddVo.getGoodsId(), GjfProductInfo.class.getName());
					if (ObjValid.isNotValid(orderAddVo.getGoodsId())) {
						throw new MyException(400, "商品不存在", null);
					}

					gjfProductInfo = (GjfProductInfo) o1;
					orderGoods.setGoodsId(gjfProductInfo);
					// 如果支付方式是积分支付但支付商品不是积分支付商品返回提示
					if ("7".equals(payType) && "0".equals(gjfProductInfo.getIsCanUserCou())) {
						throw new MyException(400, gjfProductInfo.getName() + "不能使用积分换购", null);
					}
					// 统计用户购买商品的数量
					int count = gjfOrderInfoDao.countMemberOrder(gjfMemberInfo.getId(), gjfProductInfo.getId());
					// 如果商品有限定积分兑换次数并且用户的积分兑换次数大于可兑换次数则返回提示
					if (gjfProductInfo.getPointNum() != null && count >= gjfProductInfo.getPointNum()
							&& gjfProductInfo.getPointNum() != 0) {
						throw new MyException(400, "您兑换商品的次数已经达到最大次数", null);
					}
					// 记录是否采购商品
					isWholesale = gjfProductInfo.getIsWholesale();
					// 记录邮费
					if ("0".equals(logist)) {
						pos = pos.add(gjfProductInfo.getPostage());
					}

					// 商品来源
					suoceGood = gjfProductInfo.getSuorceGoods();

					// 记录商品
					isUpgradePro = gjfProductInfo.getIsUpgradePro();

					// 记录商品销售价格
					if (!"2".equals(gjfProductInfo.getSuorceGoods())) {
						salseAmount = gjfProductInfo.getGcostPrice().multiply(new BigDecimal(orderAddVo.getGoodsNum()))
								.setScale(2, BigDecimal.ROUND_DOWN);
					}

					if ("2".equals(suoceGood)) {
						goodsId += gjfProductInfo.getNetProId().toString() + ",";
						if (gjfProductInfo.getPostage().doubleValue() != 0) {
							pos = pos.subtract(gjfProductInfo.getPostage());
						}
					}
				}
				// 判断商品属性是否存在
				GjfProductAttrStock attrStock = null;
				if (ObjValid.isValid(orderAddVo.getGoodsAttrStockId())
						|| StringUtil.isNotBlank(orderAddVo.getGoodsAttrIds())) {
					Object o2 = null;
					if (ObjValid.isValid(orderAddVo.getGoodsAttrStockId())) {
						o2 = gjfOrderInfoDao.get(orderAddVo.getGoodsAttrStockId(), GjfProductAttrStock.class.getName());
					} else {
						Map<String, Object> attrs = new HashMap<String, Object>();
						attrs.put("productAttrIds",
								orderAddVo.getGoodsAttrIds().substring(0, orderAddVo.getGoodsAttrIds().length()));
						attrs.put("productId.id", gjfProductInfo.getId());
						o2 = gjfOrderInfoDao.query(GjfProductAttrStock.class, attrs);
					}
					if (ObjValid.isNotValid(o2)) {
						throw new MyException(400, "商品属性不存在", null);
					}
					attrStock = (GjfProductAttrStock) o2;
					// 判断下单数量是否大于库存
					if (orderAddVo.getGoodsNum().longValue() > attrStock.getRepertory().longValue()) {
						throw new MyException(400, "该商品库存不足", null);
					}
					attrStock.setRepertory(attrStock.getRepertory() - orderAddVo.getGoodsNum());
					orderGoods.setGoodsAttr(
							String.valueOf(gjfProductAttrService.findProAttrByProAttrStock(attrStock).getResult()));
				}
				// 普通商品商品金额
				BigDecimal totalGoodsAmount = new BigDecimal(0.00);
				// 标准版商品金额
				BigDecimal standardTotalGoodsAmount = new BigDecimal(0.00);
				// 尊享版商品金额
				BigDecimal honourTotalGoodsAmount = new BigDecimal(0.00);
				// 判断下单类型 1 网上商城
				if ("1".equals(orderType)) {
					// 判断商品的来源 0 平台自营 1友集网
					if ("0".equals(gjfProductInfo.getSuorceGoods())) {
						// 商品库存
						orderGoods.setGoodsAttrStockId(attrStock.getId());
						// 商品单价
						orderGoods.setGoodsAmount(attrStock.getPrice());
						// 商品支付金额
						orderGoods.setGoodsPayAmount(attrStock.getPrice());

						// 商品总金额
						totalGoodsAmount = new BigDecimal(attrStock.getPrice().doubleValue() * orderAddVo.getGoodsNum())
								.setScale(2, BigDecimal.ROUND_UP);

						// 标准版总金额
						standardTotalGoodsAmount = new BigDecimal(
								attrStock.getStandardPrice().doubleValue() * orderAddVo.getGoodsNum()).setScale(2,
										BigDecimal.ROUND_UP);
						// 尊享版总金额
						honourTotalGoodsAmount = new BigDecimal(
								attrStock.getHonourPrice().doubleValue() * orderAddVo.getGoodsNum()).setScale(2,
										BigDecimal.ROUND_UP);
						// 如果为积分的商品
						if ("1".equals(attrStock.getProductId().getIsCanUserCou())
								&& attrStock.getProductId().getPointNicePrice() != null) {
							pointAmount = pointAmount
									.add(new BigDecimal(attrStock.getProductId().getPointNicePrice().doubleValue()
											* orderAddVo.getGoodsNum()).setScale(2, BigDecimal.ROUND_UP));
						}
						if ("2".equals(attrStock.getProductId().getIsCanUserCou())
								&& attrStock.getProductId().getPointNicePrice() != null) {
							pointAmount = pointAmount
									.add(new BigDecimal(attrStock.getProductId().getPointNicePrice().doubleValue()
											* orderAddVo.getGoodsNum()).setScale(2, BigDecimal.ROUND_UP));
						}

						if ("3".equals(attrStock.getProductId().getIsCanUserCou())
								&& attrStock.getProductId().getPointNicePrice() != null) {
							pointAmount = pointAmount
									.add(new BigDecimal(attrStock.getProductId().getPointNicePrice().doubleValue()
											* orderAddVo.getGoodsNum()).setScale(2, BigDecimal.ROUND_UP));
						}
						// 记录商品让利金额
						if (BeanUtil.isValid(gjfProductInfo.getBenerfitMoney())) {
							benerfitMoney = benerfitMoney.add(new BigDecimal(
									gjfProductInfo.getBenerfitMoney().doubleValue() * orderAddVo.getGoodsNum())
											.setScale(2, BigDecimal.ROUND_UP));
						}

						// 更新商品库存数量
						gjfOrderInfoDao.update(attrStock);

					} else if ("1".equals(gjfProductInfo.getSuorceGoods())) {// 有品集
						JSONObject good = new JSONObject();
						good.put("gid", gjfProductInfo.getNetProId());
						good.put("quantity", orderAddVo.getGoodsNum());
						good.put("oid", 0);
						good.put("shop", 0);
						array.add(good);

						// 记录商品让利金额
						if (BeanUtil.isValid(gjfProductInfo.getBenerfitMoney())
								&& gjfProductInfo.getBenerfitMoney().doubleValue() != 0) {
							benerfitMoney = benerfitMoney.add(new BigDecimal(
									gjfProductInfo.getBenerfitMoney().doubleValue() * orderAddVo.getGoodsNum())
											.setScale(2, BigDecimal.ROUND_UP));
						} else {
							benerfitMoney = benerfitMoney.add(new BigDecimal(
									gjfProductInfo.getGcostPrice().doubleValue() * orderAddVo.getGoodsNum()).setScale(2,
											BigDecimal.ROUND_UP));
						}

						orderGoods.setGoodsAmount(gjfProductInfo.getGcostPrice());
						orderGoods.setGoodsPayAmount(gjfProductInfo.getGcostPrice());
						totalGoodsAmount = new BigDecimal(
								gjfProductInfo.getPrice().doubleValue() * orderAddVo.getGoodsNum()).setScale(2,
										BigDecimal.ROUND_UP);
					} else if ("2".equals(gjfProductInfo.getSuorceGoods())) {
						if ("0".equals(gjfProductInfo.getStatus())) {
							throw new MyException(400, "商品已下架", null);
						}
						// 记录商品让利金额
						if (BeanUtil.isValid(gjfProductInfo.getBenerfitMoney())
								&& gjfProductInfo.getBenerfitMoney().doubleValue() != 0) {
							benerfitMoney = benerfitMoney.add(new BigDecimal(
									gjfProductInfo.getBenerfitMoney().doubleValue() * orderAddVo.getGoodsNum())
											.setScale(2, BigDecimal.ROUND_UP));
						} else {
							benerfitMoney = benerfitMoney.add(new BigDecimal(
									gjfProductInfo.getGcostPrice().doubleValue() * orderAddVo.getGoodsNum()).setScale(2,
											BigDecimal.ROUND_UP));
						}
						orderGoods.setGoodsAmount(gjfProductInfo.getGcostPrice());
						orderGoods.setGoodsPayAmount(gjfProductInfo.getGcostPrice());
						totalGoodsAmount = new BigDecimal(
								gjfProductInfo.getPrice().doubleValue() * orderAddVo.getGoodsNum()).setScale(2,
										BigDecimal.ROUND_UP);
					} else if ("5".equals(gjfProductInfo.getSuorceGoods())) {
						if ("0".equals(gjfProductInfo.getStatus())) {
							throw new MyException(400, "商品已下架", null);
						}
						// 记录商品让利金额
						if (BeanUtil.isValid(gjfProductInfo.getBenerfitMoney())
								&& gjfProductInfo.getBenerfitMoney().doubleValue() != 0) {
							benerfitMoney = benerfitMoney.add(new BigDecimal(
									gjfProductInfo.getBenerfitMoney().doubleValue() * orderAddVo.getGoodsNum())
											.setScale(2, BigDecimal.ROUND_UP));
						} else {
							benerfitMoney = benerfitMoney.add(
									new BigDecimal(gjfProductInfo.getPrice().doubleValue() * orderAddVo.getGoodsNum())
											.setScale(2, BigDecimal.ROUND_UP));
						}
						orderGoods.setGoodsAmount(gjfProductInfo.getPrice());
						orderGoods.setGoodsPayAmount(gjfProductInfo.getPrice());
						totalGoodsAmount = new BigDecimal(
								gjfProductInfo.getPrice().doubleValue() * orderAddVo.getGoodsNum()).setScale(2,
										BigDecimal.ROUND_UP);

						// 如果为积分的商品
						if ("1".equals(gjfProductInfo.getIsCanUserCou())
								&& gjfProductInfo.getPointNicePrice() != null) {
							pointAmount = pointAmount.add(new BigDecimal(
									gjfProductInfo.getPointNicePrice().doubleValue() * orderAddVo.getGoodsNum())
											.setScale(2, BigDecimal.ROUND_UP));
						}
						// 如果为代金券
						if ("3".equals(gjfProductInfo.getIsCanUserCou())
								&& gjfProductInfo.getPointNicePrice() != null) {
							pointAmount = pointAmount.add(new BigDecimal(
									gjfProductInfo.getPointNicePrice().doubleValue() * orderAddVo.getGoodsNum())
											.setScale(2, BigDecimal.ROUND_UP));
						}
						// 标准版总金额
						standardTotalGoodsAmount = new BigDecimal(
								gjfProductInfo.getStandardPrice().doubleValue() * orderAddVo.getGoodsNum()).setScale(2,
										BigDecimal.ROUND_UP);
						// 尊享版总金额
						honourTotalGoodsAmount = new BigDecimal(
								gjfProductInfo.getHonourPrice().doubleValue() * orderAddVo.getGoodsNum()).setScale(2,
										BigDecimal.ROUND_UP);
					}

					orderGoods.setGoodsName(gjfProductInfo.getName());
					orderGoods.setGoodsNum(orderAddVo.getGoodsNum());
					orderGoods.setGoodsImageUrl(gjfProductInfo.getImgUrl());
					orderGoods.setGoodsType("1");
					// orderGoods.setPromotionsId(promotionsId);

				} else {

					orderGoods.setGoodsName("店铺支付");
					orderGoods.setGoodsNum(orderAddVo.getGoodsNum());
					// orderGoods.setGoodsImageUrl(gjfProductInfo.getImgUrl());
					orderGoods.setGoodsType("1");
					orderGoods.setGoodsAmount(new BigDecimal(remark).setScale(2, BigDecimal.ROUND_UP));
					orderGoods.setGoodsNum(1L);
					orderGoods.setGoodsPayAmount(new BigDecimal(remark).setScale(2, BigDecimal.ROUND_UP));
					// orderGoods.setPromotionsId(promotionsId);
					totalGoodsAmount = new BigDecimal(remark).setScale(2, BigDecimal.ROUND_UP);
				}
				orderGoods.setStoreBenefitAmount(
						totalGoodsAmount.multiply(new BigDecimal(0.12)).setScale(2, BigDecimal.ROUND_UP));
				orderGoods.setStoreRecAmount(
						totalGoodsAmount.subtract(orderGoods.getStoreBenefitAmount()).setScale(2, BigDecimal.ROUND_UP));
				gjfOrderGoods.add(orderGoods);
				if ("0".equals(isWholesale)) {
					totalAmount = totalAmount.add(totalGoodsAmount);
				} else {
					if ("0".equals(gjfMemberInfo.getMerchantType())) {
						totalAmount = totalAmount.add(totalGoodsAmount);
					} else if ("1".equals(gjfMemberInfo.getMerchantType())) {
						totalAmount = totalAmount.add(standardTotalGoodsAmount);
					} else {
						totalAmount = totalAmount.add(honourTotalGoodsAmount);
					}
				}

				totalStoreBenefitAmount = totalStoreBenefitAmount.add(orderGoods.getStoreBenefitAmount());
			}
		} else {
			totalAmount = new BigDecimal(remark).setScale(2, BigDecimal.ROUND_UP);
		}

		// 如果是产品池产品
		if ("5".equals(suoceGood)) {
			pos = postage;
		}

		// 如果是京东商品则查询商品是否还有库存
		if ("2".equals(suoceGood)) {
			// 处理收货地址省市区
			String areaStr = "";
			// 如果省份不为空
			if (BeanUtil.isValid(gjfMemberAddress.getProviceId())) {
				areaStr += gjfMemberAddress.getProviceId().getProvinceId().toString();
			}
			// 如果城市不为空
			if (BeanUtil.isValid(gjfMemberAddress.getCityId())) {
				areaStr += "," + gjfMemberAddress.getCityId().getCityId();
			}
			// 如果区域不为空
			if (BeanUtil.isValid(gjfMemberAddress.getAreaId())) {
				areaStr += "," + gjfMemberAddress.getAreaId().getAreaId();
			}
			// 如果镇不为空
			if (BeanUtil.isValid(gjfMemberAddress.getTownId())) {
				areaStr += "," + gjfMemberAddress.getTownId().getTownId();
			} else {
				areaStr += ",0";
			}
			if (BeanUtil.isValid(goodsId)) {
				goodsId = goodsId.substring(0, goodsId.length() - 1);
			}
			// 查询商品库存信息
			List<ProductStock> stockList = JdNewUtil.getProductStore(goodsId, areaStr);
			if (!BeanUtil.isValid(stockList)) {
				throw new MyException(400, "商品无库存", null);
			}
			Iterator<ProductStock> it = stockList.iterator();
			while (it.hasNext()) {
				ProductStock productStock = it.next();
				if (productStock.getGoodsNumber() == 2) {
					throw new MyException(400, "商品无库存", null);
				}
				if (productStock.getIsOnSale() == 0) {
					throw new MyException(400, "商品已下架", null);
				}
			}
		}

		GjfOrderInfo gjfOrderInfo = new GjfOrderInfo();
		gjfOrderInfo.setOrderSn(DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)));
		gjfOrderInfo.setPickupCode(RandUtil.getRandomStr(6));
		gjfOrderInfo.setMemberId(gjfMemberInfo);
		gjfOrderInfo.setSuoceGood(suoceGood);
		// 添加店铺
		if ("0".equals(orderType) && couponsId != 0) {
			Map<String, Object> storeMap = new HashMap<>();
			storeMap.put("id", couponsId);
			GjfStoreInfo storeInfo = gjfOrderInfoDao.query(GjfStoreInfo.class, storeMap);
			gjfOrderInfo.setStoreId(storeInfo);
		} else {
			gjfOrderInfo.setStoreId(gjfOrderGoods.get(0).getGoodsId().getStoreId());
		}

		// 支付明细流水
		GjfMemberTradeLog gjfMemberTradeLog = new GjfMemberTradeLog();
		gjfMemberTradeLog.setTradeNo(DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)));
		gjfMemberTradeLog.setLinkTradeNo(gjfOrderInfo.getOrderSn());
		gjfMemberTradeLog.setStoreId(gjfOrderInfo.getStoreId());
		gjfMemberTradeLog.setMemberId(gjfOrderInfo.getMemberId());
		gjfMemberTradeLog.setAddTime(new Date());

		// 如果是京东商品
		if ("2".equals(suoceGood)) {
			// 添加京东订单信息
			OrderResult orderResult = JdNewUtil.addJdOrder(gjfMemberAddress, gjfOrderInfo, gjfOrderGoods,
					totalAmount.doubleValue());
			// 如果京东订单信息添加失败，则返回提示
			if (!BeanUtil.isValid(orderResult)) {
				return new ResultVo(400, "数据对接失败", null);
			}
			// 记录京东订单信息
			gjfOrderInfo.setJdOrderSn(orderResult.getKxzNo());
			String totalDeliveryFee = orderResult.getTotalDeliveryFee();
			com.alibaba.fastjson.JSONObject jsonObject = (com.alibaba.fastjson.JSONObject) com.alibaba.fastjson.JSONObject
					.parse(totalDeliveryFee);
			// 京东邮费
			String jdFee = jsonObject.getString("JD");
			if (BeanUtil.isValid(jdFee)) {
				com.alibaba.fastjson.JSONObject jdJsonObject = (com.alibaba.fastjson.JSONObject) com.alibaba.fastjson.JSONObject
						.parse(jdFee);
				pos = pos.add(new BigDecimal(jdJsonObject.getDoubleValue("deliveryFee")));
			}
			// 苏宁
			String snFee = jsonObject.getString("SN");
			if (BeanUtil.isValid(snFee)) {
				com.alibaba.fastjson.JSONObject jdJsonObject = (com.alibaba.fastjson.JSONObject) com.alibaba.fastjson.JSONObject
						.parse(snFee);
				pos = pos.add(new BigDecimal(jdJsonObject.getDoubleValue("deliveryFee")));
			}
			// 一号店
			String yhdFee = jsonObject.getString("YHD");
			if (BeanUtil.isValid(yhdFee)) {
				com.alibaba.fastjson.JSONObject jdJsonObject = (com.alibaba.fastjson.JSONObject) com.alibaba.fastjson.JSONObject
						.parse(yhdFee);
				pos = pos.add(new BigDecimal(jdJsonObject.getDoubleValue("deliveryFee")));
			}

			// 赠送金额
			GoodList goodsList = orderResult.getGoodsList();

			List<OrderSuccessResult> successList = goodsList.getSuccessList();
			BigDecimal profits = new BigDecimal(0.00);
			if (BeanUtil.isValid(successList)) {
				for (int k = 0; k < successList.size(); k++) {
					// 获取返回订单信息
					OrderSuccessResult orderSuccess = successList.get(k);
					// 如果回的金额不为空
					if (BeanUtil.isValid(orderSuccess.getGoodsPrice())
							&& BeanUtil.isValid(orderSuccess.getTradePrice())) {
						salseAmount = salseAmount.add(orderSuccess.getGoodsAmount());
						// 计算利润
						BigDecimal chajia = orderSuccess.getTradePrice().subtract(orderSuccess.getGoodsAmount());
						if (chajia.doubleValue() > 0) {
							// 获取通道费率比例
							BigDecimal chcRate = new BigDecimal(0.01);
							Map<String, Object> attrs = new HashMap<>();
							attrs.put("key", "CAHNELCOST");
							attrs.put("status", "1");
							GjfSetBaseInfo chcBase = gjfOrderInfoDao.query(GjfSetBaseInfo.class, attrs);
							if (BeanUtil.isValid(chcBase)) {
								chcRate = new BigDecimal(chcBase.getValue());
							}
							// 通道费用
							BigDecimal channelCost = orderSuccess.getTradePrice().multiply(chcRate).setScale(2,
									BigDecimal.ROUND_DOWN);
							// 获取增值税加卡通通道费率
							BigDecimal vatRate = new BigDecimal("0.38");
							attrs.put("key", "VAT");
							attrs.put("status", "1");
							GjfSetBaseInfo vatBase = gjfOrderInfoDao.query(GjfSetBaseInfo.class, attrs);
							if (BeanUtil.isValid(vatBase)) {
								vatRate = new BigDecimal(vatBase.getValue());
							}
							// 增值税加卡通通道费
							BigDecimal vat = chajia.multiply(vatRate).setScale(2, BigDecimal.ROUND_DOWN);
							// 净利润
							BigDecimal netProfit = chajia.subtract(channelCost).subtract(vat).setScale(2,
									BigDecimal.ROUND_DOWN);
							// 分出比例
							BigDecimal giveRate = new BigDecimal(0.5);
							attrs.put("key", "VOGIVERATE");
							attrs.put("status", "1");
							GjfSetBaseInfo giveBase = gjfOrderInfoDao.query(GjfSetBaseInfo.class, attrs);
							if (BeanUtil.isValid(giveBase)) {
								giveRate = new BigDecimal(giveBase.getValue());
							}
							// 送出部分
							netProfit = netProfit.multiply(giveRate).setScale(2, BigDecimal.ROUND_DOWN);
							benerfitMoney = new BigDecimal(0.00);
							if ("0".equals(commissionType)) {
								profits = netProfit.divide(new BigDecimal(0.12), 2, BigDecimal.ROUND_DOWN);
								if (profits.doubleValue() > orderSuccess.getTradePrice().doubleValue()) {
									benerfitMoney = benerfitMoney.add(orderSuccess.getTradePrice()).setScale(2,
											BigDecimal.ROUND_DOWN);
								} else {
									benerfitMoney = benerfitMoney.add(profits).setScale(2, BigDecimal.ROUND_DOWN);
								}
							} else {
								profits = netProfit.divide(new BigDecimal(0.1), 2, BigDecimal.ROUND_DOWN);
								if (profits.doubleValue() > orderSuccess.getTradePrice().doubleValue()) {
									benerfitMoney = benerfitMoney.add(orderSuccess.getTradePrice()).setScale(2,
											BigDecimal.ROUND_DOWN);
								} else {
									benerfitMoney = benerfitMoney.add(profits).setScale(2, BigDecimal.ROUND_DOWN);
								}
							}
							gjfOrderInfo.setBenerfitMoney(benerfitMoney);
							gjfOrderInfo.setJdCostPrice(orderSuccess.getGoodsPrice());
						}
					}
				}
			}

			// 如果京东下单返回的需要支付总额不等于平台商品支付总额
			/*
			 * if(totalAmount.doubleValue()!=orderResult.getOrderAmount().
			 * doubleValue()){ totalAmount=orderResult.getOrderAmount();
			 * gjfOrderInfo.setGoodsTotalAmount(orderResult.getOrderAmount());
			 * gjfOrderInfo.setOrderTotalAmount(orderResult.getOrderAmount());
			 * gjfOrderInfo.setRealPayAmount(orderResult.getOrderAmount()); }
			 */
			// 如果总运费不一致
			/*
			 * if(pos.doubleValue()!=orderResult.getTotalDeliveryFee().
			 * doubleValue()){ pos=orderResult.getTotalDeliveryFee(); }
			 */

		}
		// 如果是友品集商品
		if ("1".equals(suoceGood)) {
			JSONObject netJson = NetFriendUtil.addNetFriendOrder(gjfMemberInfo.getToken().substring(0, 50),
					String.valueOf(totalAmount.doubleValue()), "0", array, "", "2", remark,
					gjfMemberAddress.getNetAddressId(), "");
			System.out.println(netJson);
			int errcode = (int) netJson.get("errcode");
			String errmsg = (String) netJson.get("errmsg");
			if (errcode != 0) {
				return new ResultVo(400, errmsg, null);
			}
			JSONObject data = netJson.getJSONObject("data");
			gjfOrderInfo.setJdOrderSn(data.getString("ordersn"));
			pos = pos.add(new BigDecimal(data.getDouble("freight")));
		}

		// 如果是平台商品池产品
		/*
		 * if("5".equals(suoceGood)){ String
		 * resutlStr=ProUtil.createOrder(gjfMemberAddress, gjfOrderInfo,
		 * gjfOrderGoods); com.alibaba.fastjson.JSONObject
		 * jsonObject=com.alibaba.fastjson.JSONObject.parseObject(resutlStr);
		 * if(BeanUtil.isValid(jsonObject)){ String
		 * shipPrice=jsonObject.getString("shipPrice"); pos=pos.add(new
		 * BigDecimal(shipPrice));
		 * gjfOrderInfo.setJdOrderSn(jsonObject.getString("order_sn")); } }
		 */

		gjfOrderInfo.setOrderTotalAmount(totalAmount.add(pos).add(pointAmount));
		// 如果为比特
		if ("3".equals(platform)) {
			gjfOrderInfo.setGoodsTotalAmount(totalAmount.add(pos).add(pointAmount).subtract(dicTotalMoney));
			gjfOrderInfo.setRealPayAmount(totalAmount.add(pos).add(pointAmount).subtract(dicTotalMoney));
		} else {
			gjfOrderInfo.setGoodsTotalAmount(totalAmount.add(pointAmount));
			gjfOrderInfo.setRealPayAmount(totalAmount.add(pos).add(pointAmount));
		}

		gjfOrderInfo.setBenerfitMoney(benerfitMoney);

		// 当选择支付方式为待领消费金额
		if (payType.equals("7")) {
			// 如果总积分数小于需要支付金额返回提示
			if (gjfMemberInfo.getConsumptionMoney().doubleValue() < totalAmount.doubleValue()) {
				return new ResultVo(400, "积分不足，请选择其他支付方式", null);
			}
			// 线上支付金额
			gjfOrderInfo.setOnlinePayAmount(totalAmount);
			// 线下支付金额
			gjfOrderInfo.setOfflinePayAmount(pos.add(pointAmount));
			// 支付类型
			gjfOrderInfo.setPayType("7");
			// 如果线下支付金额为0，则直接扣减积分并且订单状态为已支付
			if (gjfOrderInfo.getOfflinePayAmount().doubleValue() == 0) {
				// 订单状态
				gjfOrderInfo.setOrderStatus("1");
				// 交易记录状态
				gjfMemberTradeLog.setTradeStatus("1");

				// 获取产品池确认订单

				if ("5".equals(suoceGood)) {
					String str = ProUtil.confirmorder(customerSn, orderSn);
					if (!BeanUtil.isValid(str)) {
						return new ResultVo(400, "下单失败", null);
					}
					gjfOrderInfo.setOrderStatus("2");
					gjfMemberTradeLog.setTradeStatus("2");
				}

				// 记录剩余需要支付的积分
				BigDecimal rewardConsumption = totalAmount;
				// 如果总金额大于一类积分金额
				if (gjfMemberInfo.getMerchantFirstCousumptionMoney().doubleValue() < totalAmount.doubleValue()) {

					// 如果一类积分大于零
					if (gjfMemberInfo.getMerchantFirstCousumptionMoney().doubleValue() > 0) {
						// 记录减去扣除部分剩余的
						rewardConsumption = totalAmount.subtract(gjfMemberInfo.getMerchantFirstCousumptionMoney());
						// 扣减一类分红权
						updateDeductionDivi(gjfMemberInfo.getMobile(), "0",
								gjfMemberInfo.getMerchantFirstCousumptionMoney().doubleValue(), "1");
						// 清除积分
						gjfMemberInfo.setMerchantFirstCousumptionMoney(new BigDecimal(0));
					}

					// 如果剩余金额大于二类积分
					if (gjfMemberInfo.getMerchantSecondCousumptionMoney().doubleValue() < rewardConsumption
							.doubleValue()) {
						// 如果二类积分大于零
						if (gjfMemberInfo.getMerchantSecondCousumptionMoney().doubleValue() > 0) {
							// 记录减去扣除部分剩余的
							rewardConsumption = rewardConsumption
									.subtract(gjfMemberInfo.getMerchantSecondCousumptionMoney());
							// 扣减一类分红权
							updateDeductionDivi(gjfMemberInfo.getMobile(), "0",
									gjfMemberInfo.getMerchantSecondCousumptionMoney().doubleValue(), "2");
							// 清除积分
							gjfMemberInfo.setMerchantSecondCousumptionMoney(new BigDecimal(0));
						}

						// 如果剩余的还大于零
						if (rewardConsumption.doubleValue() >= 0
								&& gjfMemberInfo.getMerchantThreeCousumptionMoney().doubleValue() > 0) {
							// 三类积分
							gjfMemberInfo.setMerchantThreeCousumptionMoney(
									gjfMemberInfo.getMerchantThreeCousumptionMoney().subtract(rewardConsumption));
							// 扣减一类分红权
							updateDeductionDivi(gjfMemberInfo.getMobile(), "0", rewardConsumption.doubleValue(), "3");
						}

					} else {
						gjfMemberInfo.setMerchantSecondCousumptionMoney(
								gjfMemberInfo.getMerchantSecondCousumptionMoney().subtract(rewardConsumption));
						// 扣减一类分红权
						updateDeductionDivi(gjfMemberInfo.getMobile(), "0", rewardConsumption.doubleValue(), "2");
					}
					// 如果大于等于直接减
				} else {
					gjfMemberInfo.setMerchantFirstCousumptionMoney(
							gjfMemberInfo.getMerchantFirstCousumptionMoney().subtract(totalAmount));
					// 扣减分红权
					updateDeductionDivi(gjfMemberInfo.getMobile(), "0", totalAmount.doubleValue(), "1");
				}
				// 总积分
				gjfMemberInfo.setConsumptionMoney(gjfMemberInfo.getConsumptionMoney().subtract(totalAmount));
				gjfMemberInfo.setDividendsTotalMoney(gjfMemberInfo.getDividendsTotalMoney().add(totalAmount));
				// 查詢用戶消費記錄
				List<GjfMemberConsumptiomNum> list = gjfBenefitInfoDao
						.findMemberCousumptionNum(gjfOrderInfo.getMemberId().getId());
				// 如果不為空則直接加一
				if (BeanUtil.isValid(list)) {
					GjfMemberConsumptiomNum gjfMemberConsumptiomNum = list.get(0);
					gjfMemberConsumptiomNum.setShopConsumptionNum(gjfMemberConsumptiomNum.getShopConsumptionNum() + 1);
					gjfOrderInfoDao.update(gjfMemberConsumptiomNum);
				} else {
					GjfMemberConsumptiomNum consumptiomNum = new GjfMemberConsumptiomNum();
					consumptiomNum.setBenefitNum(0);
					consumptiomNum.setShopConsumptionNum(1);
					consumptiomNum.setMemberId(gjfOrderInfo.getMemberId().getId());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(new Date());
					ParsePosition ps = new ParsePosition(0);
					consumptiomNum.setAddTime(sdf.parse(date, ps));
					gjfOrderInfoDao.save(consumptiomNum);
				}

				// 判断用户最早消费时间是否为空
				if (!BeanUtil.isValid(gjfOrderInfo.getMemberId().getFirstConsumptionTime())) {
					GjfMemberInfo member = gjfOrderInfo.getMemberId();
					member.setFirstConsumptionTime(new Date());
				}
			} else {
				gjfOrderInfo.setOrderStatus("0");
				gjfMemberTradeLog.setTradeStatus("0");
			}

			gjfOrderInfo.setPayTime(new Date());
			gjfMemberTradeLog.setTradeType("1");
			gjfMemberTradeLog.setTradeTime(new Date());
			gjfMemberTradeLog.setTradeMoney(totalAmount);
			gjfMemberTradeLog.setTradeTrmo("待领消费金额支付--网上商城下单");

		}

		// 当选择支付方式为责任消费金额
		if (payType.equals("8")) {
			if (gjfMemberInfo.getInsuranceMoney().doubleValue() < totalAmount.doubleValue()) {
				return new ResultVo(400, "责任消费险金额不足,请选择其他支付方式", null);
			}
			gjfOrderInfo.setOnlinePayAmount(totalAmount);
			gjfOrderInfo.setOfflinePayAmount(pos.add(pointAmount));
			gjfOrderInfo.setPayType("8");

			// 如果线下支付金额为0，则直接扣减积分并且订单状态为已支付
			if (gjfOrderInfo.getOfflinePayAmount().doubleValue() == 0) {
				gjfMemberInfo.setInsuranceMoney(gjfMemberInfo.getInsuranceMoney().subtract(totalAmount));
				gjfOrderInfo.setOrderStatus("1");
				gjfMemberTradeLog.setTradeStatus("1");
			} else {
				gjfOrderInfo.setOrderStatus("0");
				gjfMemberTradeLog.setTradeStatus("0");
			}

			gjfOrderInfo.setOrderStatus("0");
			gjfMemberTradeLog.setTradeStatus("0");
			gjfMemberTradeLog.setTradeType("2");
			gjfOrderInfo.setPayTime(new Date());
			gjfMemberTradeLog.setTradeTime(new Date());
			gjfMemberTradeLog.setTradeMoney(totalAmount);
			gjfMemberTradeLog.setTradeTrmo("责任消费金额支付--网上商城下单");
			gjfMemberTradeLog.setTradeStatus("1");

			GjfMemberTradeDetail detail1 = new GjfMemberTradeDetail(null, gjfOrderInfo.getMemberId(),
					gjfOrderInfo.getOrderSn() + "-3", totalAmount, new Date(), new Date(), "3", "0", "网上商城下单");
			gjfOrderInfoDao.save(detail1);
		}

		// 当选择支付方式为代金券金额
		if (payType.equals("10")) {
			if (gjfMemberInfo.getMemberVoucherMoney().doubleValue() < totalAmount.doubleValue()) {
				return new ResultVo(400, "代金券金额不足,请选择其他支付方式", null);
			}
			gjfOrderInfo.setOnlinePayAmount(totalAmount);
			gjfOrderInfo.setOfflinePayAmount(pos.add(pointAmount));
			gjfOrderInfo.setPayType("10");

			// 如果线下支付金额为0，则直接扣减积分并且订单状态为已支付
			if (gjfOrderInfo.getOfflinePayAmount().doubleValue() == 0) {

				gjfOrderInfo.setOrderStatus("1");
				gjfMemberTradeLog.setTradeStatus("1");

				// 获取产品池确认订单

				if ("5".equals(suoceGood)) {
					String str = ProUtil.confirmorder(customerSn, orderSn);
					if (!BeanUtil.isValid(str)) {
						return new ResultVo(400, "下单失败", null);
					}
					gjfOrderInfo.setOrderStatus("2");
					gjfMemberTradeLog.setTradeStatus("2");
				}

				// 修改用户代金券
				gjfMemberInfo.setMemberVoucherMoney(gjfMemberInfo.getMemberVoucherMoney().subtract(totalAmount));
				// 如果用户是非活跃用户
				if ("0".equals(gjfMemberInfo.getIsActiveMember())) {
					// 获取用户上个月获取的代金券金额
					BigDecimal voucherMoney = gjfOrderInfoDao.findMemberTotalVoucherLastMonth(gjfMemberInfo.getId());
					// 计算上个月50%的代金券
					BigDecimal dicVoucherMoney = voucherMoney.multiply(new BigDecimal(0.5)).setScale(2,
							BigDecimal.ROUND_DOWN);
					// 如果用户的代金券小于上个月50%的代金券
					if (gjfMemberInfo.getMemberVoucherMoney().doubleValue() < dicVoucherMoney.doubleValue()) {
						// 设置成为活跃会员
						gjfMemberInfo.setIsActiveMember("1");
						// 创建查询店铺的map
						Map<String, Object> storeMap = new HashMap<>();
						// 用户id
						storeMap.put("memberId.id", gjfMemberInfo.getId());
						// 获取店铺信息
						GjfStoreInfo storeInfo = gjfOrderInfoDao.query(GjfStoreInfo.class, storeMap);
						// 如果店铺不为空
						if (BeanUtil.isValid(storeInfo)) {
							// 店铺设置为活跃
							storeInfo.setIsActivityStore("1");
							// 更新店铺信息
							gjfOrderInfoDao.update(storeInfo);
						}
					}
				}

			} else {
				gjfOrderInfo.setOrderStatus("0");
				gjfMemberTradeLog.setTradeStatus("0");
			}

			gjfMemberTradeLog.setTradeType("9");
			gjfOrderInfo.setPayTime(new Date());
			gjfMemberTradeLog.setTradeTime(new Date());
			gjfMemberTradeLog.setTradeMoney(totalAmount);
			gjfMemberTradeLog.setTradeTrmo("代金券金额支付--网上商城下单");

			GjfMemberTradeDetail detail1 = new GjfMemberTradeDetail(null, gjfOrderInfo.getMemberId(),
					gjfOrderInfo.getOrderSn() + "-3", totalAmount, new Date(), new Date(), "3", "0", "网上商城下单");
			gjfOrderInfoDao.save(detail1);
		}

		// 当选择支付方式为巨惠宝
		if (payType.equals("9")) {

			// 查询用户巨惠宝信息
			Map<String, Object> fhAttrs = new HashMap<>();
			fhAttrs.put("mobile", gjfMemberInfo.getMobile());
			GjfFhTreasureInfo fhInfo = gjfOrderInfoDao.query(GjfFhTreasureInfo.class, fhAttrs);

			if (!BeanUtil.isValid(fhInfo)) {
				// return new ResultVo(400, "巨惠宝金额不足,请选择其他支付方式", null);
				return new ResultVo(400, "巨惠宝金额不足,请选择其他支付方式", null);
			}
			if (fhInfo.getFhTreasureMoney().doubleValue() < totalAmount.add(pos).doubleValue()) {
				// return new ResultVo(400, "巨惠宝宝金额不足,请选择其他支付方式", null);
				return new ResultVo(400, "巨惠宝金额不足,请选择其他支付方式", null);
			}
			gjfOrderInfo.setOfflinePayAmount(new BigDecimal(0.00));
			gjfOrderInfo.setPayType("9");
			gjfOrderInfo.setOrderStatus("1");

			// 如果为京东商品
			if ("2".equals(suoceGood)) {

				OrderResult orderResult = JdNewUtil.cofirmOrder(gjfOrderInfo.getOrderSn(), gjfOrderInfo.getJdOrderSn());
				if (!BeanUtil.isValid(orderResult)) {
					return new ResultVo(400, "数据对接失败", null);
				}
				gjfOrderInfo.setOrderStatus("2");
				gjfMemberTradeLog.setTradeStatus("2");
			}

			// 获取产品池确认订单

			if ("5".equals(suoceGood)) {
				String str = ProUtil.confirmorder(customerSn, orderSn);
				if (!BeanUtil.isValid(str)) {
					return new ResultVo(400, "下单失败", null);
				}
				gjfOrderInfo.setOrderStatus("2");
				gjfMemberTradeLog.setTradeStatus("2");
			}

			// 余额支付计算税费
			// 如果为比特
			if ("3".equals(platform)) {
				gjfOrderInfo.setOnlinePayAmount(totalAmount.add(pos).subtract(dicTotalMoney));
			} else {
				gjfOrderInfo.setOnlinePayAmount(totalAmount.add(pos));
			}

			gjfOrderInfo.setPayTime(new Date());
			gjfMemberTradeLog.setTradeType("8");
			gjfMemberTradeLog.setTradeTime(new Date());
			gjfMemberTradeLog.setTradeMoney(totalAmount);
			gjfMemberTradeLog.setTradeTrmo("巨惠宝支付--网上商城下单");
			gjfMemberTradeLog.setTradeStatus("1");

			// 查詢用戶消費記錄
			List<GjfMemberConsumptiomNum> list = gjfBenefitInfoDao
					.findMemberCousumptionNum(gjfOrderInfo.getMemberId().getId());
			// 如果不為空則直接加一
			if (BeanUtil.isValid(list)) {
				GjfMemberConsumptiomNum gjfMemberConsumptiomNum = list.get(0);
				gjfMemberConsumptiomNum.setShopConsumptionNum(gjfMemberConsumptiomNum.getShopConsumptionNum() + 1);
				gjfOrderInfoDao.update(gjfMemberConsumptiomNum);
			} else {
				GjfMemberConsumptiomNum consumptiomNum = new GjfMemberConsumptiomNum();
				consumptiomNum.setBenefitNum(0);
				consumptiomNum.setShopConsumptionNum(1);
				consumptiomNum.setMemberId(gjfOrderInfo.getMemberId().getId());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(new Date());
				ParsePosition ps = new ParsePosition(0);
				consumptiomNum.setAddTime(sdf.parse(date, ps));
				gjfOrderInfoDao.save(consumptiomNum);
			}

			// 判断用户最早消费时间是否为空
			GjfMemberInfo member = gjfOrderInfo.getMemberId();
			if (!BeanUtil.isValid(gjfOrderInfo.getMemberId().getFirstConsumptionTime())) {
				member.setFirstConsumptionTime(new Date());
			}

			GjfMemberTradeDetail detail1 = new GjfMemberTradeDetail(null, gjfOrderInfo.getMemberId(),
					gjfOrderInfo.getOrderSn() + "-1", totalAmount, new Date(), new Date(), "0", "0", "网上商城下单");
			GjfMemberTradeDetail detail2 = new GjfMemberTradeDetail(null, gjfOrderInfo.getMemberId(),
					gjfOrderInfo.getOrderSn() + "-2", totalAmount, new Date(), new Date(), "1", "0", "网上商城下单");
			gjfOrderInfoDao.save(detail1);
			gjfOrderInfoDao.save(detail2);

			// 添加巨惠宝交易记录
			GjfMemberTreasureTrade treasuer = new GjfMemberTreasureTrade();
			treasuer.setMemberId(gjfMemberInfo.getId());
			treasuer.setMemberName(gjfMemberInfo.getName());
			treasuer.setMemebrMobile(gjfMemberInfo.getMobile());
			treasuer.setMemberTreasureMoneyBf(fhInfo.getFhTreasureMoney());
			treasuer.setMemberTreasureMoneyAf(fhInfo.getFhTreasureMoney().subtract(totalAmount.add(pos)));
			treasuer.setMemberTreasureTradeMoney(totalAmount.add(pos));
			treasuer.setAddTime(new Date());
			treasuer.setTradeNo(gjfOrderInfo.getOrderSn());
			treasuer.setTradeType("5");
			treasuer.setTradeStatus("1");
			gjfOrderInfoDao.save(treasuer);
			// 扣减用户巨惠宝金额
			fhInfo.setFhTreasureMoney(fhInfo.getFhTreasureMoney().subtract(totalAmount.add(pos)));
			gjfOrderInfoDao.update(fhInfo);
		}

		if (payType.equals("0")) {
			if (gjfMemberInfo.getBalanceMoney().doubleValue() >= totalAmount.add(pos).doubleValue()) {

				gjfOrderInfo.setOfflinePayAmount(new BigDecimal(0.00));
				gjfOrderInfo.setPayType("0");

				// 余额支付计算税费
				BigDecimal taxMoney = totalAmount.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_DOWN);
				gjfOrderInfo.setOnlinePayAmount(totalAmount.add(pos).add(taxMoney));
				gjfOrderInfo.setTaxMoney(taxMoney);

				gjfOrderInfo.setOrderStatus("1");
				gjfOrderInfo.setPayTime(new Date());

				gjfMemberTradeLog.setTradeType("0");
				gjfMemberTradeLog.setTradeTime(new Date());
				gjfMemberTradeLog.setTradeMoney(totalAmount);
				gjfMemberTradeLog.setTradeTrmo("余额支付--网上商城下单");
				gjfMemberTradeLog.setTradeStatus("1");

				// 如果为京东商品
				if ("2".equals(suoceGood)) {

					OrderResult orderResult = JdNewUtil.cofirmOrder(gjfOrderInfo.getOrderSn(),
							gjfOrderInfo.getJdOrderSn());
					if (!BeanUtil.isValid(orderResult)) {
						return new ResultVo(400, "数据对接失败", null);
					}
					gjfOrderInfo.setOrderStatus("2");
					gjfMemberTradeLog.setTradeStatus("2");
				}

				// 是否为采购产品
				if ("1".equals(isWholesale)) {
					return new ResultVo(400, "采购产品不支持余额支付", null);
				}

				// 查詢用戶消費記錄
				List<GjfMemberConsumptiomNum> list = gjfBenefitInfoDao
						.findMemberCousumptionNum(gjfOrderInfo.getMemberId().getId());
				// 如果不為空則直接加一
				if (BeanUtil.isValid(list)) {
					GjfMemberConsumptiomNum gjfMemberConsumptiomNum = list.get(0);
					gjfMemberConsumptiomNum.setShopConsumptionNum(gjfMemberConsumptiomNum.getShopConsumptionNum() + 1);
					gjfOrderInfoDao.update(gjfMemberConsumptiomNum);
				} else {
					GjfMemberConsumptiomNum consumptiomNum = new GjfMemberConsumptiomNum();
					consumptiomNum.setBenefitNum(0);
					consumptiomNum.setShopConsumptionNum(1);
					consumptiomNum.setMemberId(gjfOrderInfo.getMemberId().getId());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(new Date());
					ParsePosition ps = new ParsePosition(0);
					consumptiomNum.setAddTime(sdf.parse(date, ps));
					gjfOrderInfoDao.save(consumptiomNum);
				}
				GjfMemberInfo member = gjfOrderInfo.getMemberId();
				// 判断用户最早消费时间是否为空
				if (!BeanUtil.isValid(gjfOrderInfo.getMemberId().getFirstConsumptionTime())) {

					member.setFirstConsumptionTime(new Date());
				}

				// 扣减用户余额
				gjfMemberInfo.setBalanceMoney(
						gjfMemberInfo.getBalanceMoney().subtract(totalAmount).subtract(pos).subtract(taxMoney));
				gjfMemberInfo.setWithdrawalMoney(
						gjfMemberInfo.getWithdrawalMoney().subtract(totalAmount).subtract(pos).subtract(taxMoney));

				GjfMemberTradeDetail detail1 = new GjfMemberTradeDetail(null, gjfOrderInfo.getMemberId(),
						gjfOrderInfo.getOrderSn() + "-1", totalAmount, new Date(), new Date(), "0", "0", "网上商城下单");
				GjfMemberTradeDetail detail2 = new GjfMemberTradeDetail(null, gjfOrderInfo.getMemberId(),
						gjfOrderInfo.getOrderSn() + "-2", totalAmount, new Date(), new Date(), "1", "0", "网上商城下单");
				gjfOrderInfoDao.save(detail1);
				gjfOrderInfoDao.save(detail2);
			} else {
				return new ResultVo(400, "余额不足,请选择其他支付方式", null);
			}
		} else if (Integer.parseInt(payType) != 7 && Integer.parseInt(payType) != 8 && Integer.parseInt(payType) != 9
				&& Integer.parseInt(payType) != 10) {
			if (gjfMemberInfo.getBalanceMoney().doubleValue() > 0) {
				/*
				 * if (orderType.equals("1")) {
				 * gjfOrderInfo.setOnlinePayAmount(gjfMemberInfo.getBalanceMoney
				 * ()); if (payType.equals("1")) { gjfOrderInfo.setPayType("4");
				 * } else if (payType.equals("2")) {
				 * gjfOrderInfo.setPayType("5"); } else if (payType.equals("3"))
				 * { gjfOrderInfo.setPayType("6"); }
				 * gjfOrderInfo.setOfflinePayAmount(totalAmount.subtract(
				 * gjfOrderInfo.getOnlinePayAmount()).add(pos));
				 * 
				 * } else {
				 */

				gjfOrderInfo.setPayType(payType);
				gjfOrderInfo.setOfflinePayAmount(totalAmount.add(pos));
				gjfOrderInfo.setOnlinePayAmount(new BigDecimal(0.00));
				/*
				 * gjfMemberInfo.setBalanceMoney(new BigDecimal(0.00));
				 * gjfMemberInfo.setWithdrawalMoney(new BigDecimal(0.00));
				 */
				/* } */

				// 回调时候再修改
				// gjfMemberInfo.setBalanceMoney(new BigDecimal(0.00));
				// gjfMemberInfo.setWithdrawalMoney(new BigDecimal(0.00));
			} else {
				gjfOrderInfo.setOnlinePayAmount(new BigDecimal(0.00));
				gjfOrderInfo.setPayType(payType);
				// 如果为比特
				if ("3".equals(platform)) {
					gjfOrderInfo.setOfflinePayAmount(totalAmount.add(pos).subtract(dicTotalMoney));
				} else {
					gjfOrderInfo.setOfflinePayAmount(totalAmount.add(pos));
				}

				gjfOrderInfo.setOnlinePayAmount(new BigDecimal(0.00));
				gjfMemberInfo.setBalanceMoney(new BigDecimal(0.00));
				gjfMemberInfo.setWithdrawalMoney(new BigDecimal(0.00));
			}
			gjfOrderInfo.setOrderStatus("0");

			if (payType.equals("1")) {
				gjfMemberTradeLog.setTradeType("3");
				gjfMemberTradeLog.setTradeTrmo("微信支付--网上商城下单");
			} else if (payType.equals("2")) {
				gjfMemberTradeLog.setTradeType("4");
				gjfMemberTradeLog.setTradeTrmo("支付宝支付--网上商城下单");
			} else if (payType.equals("3")) {
				gjfMemberTradeLog.setTradeType("5");
				gjfMemberTradeLog.setTradeTrmo("银联支付--网上商城下单");
			}
			gjfMemberTradeLog.setTradeStatus("0");
			gjfMemberTradeLog.setTradeMoney(gjfOrderInfo.getOfflinePayAmount());
		}
		gjfOrderInfo.setIsUpgradePro(isUpgradePro);
		gjfOrderInfo.setSalseAmount(salseAmount);
		gjfOrderInfo.setLogist(logist);
		gjfOrderInfo.setBenerfitMoney(benerfitMoney);
		gjfOrderInfo.setStoreBenefitAmount(totalStoreBenefitAmount);
		gjfOrderInfo.setStoreRecAmount(totalAmount.subtract(totalStoreBenefitAmount));
		gjfOrderInfo.setRefundAmount(new BigDecimal(0.00));
		// gjfOrderInfo.setCouponsId(couponsId);
		gjfOrderInfo.setRemark(remark);
		gjfOrderInfo.setIsWholesalse(isWholesale);
		gjfOrderInfo.setAddTime(new Date());
		gjfOrderInfo.setOrderType(orderType);
		gjfOrderInfo.setEvaluationStatus("0");
		gjfOrderInfo.setRefundStatus("0");
		gjfOrderInfo.setIsDel("1");
		gjfOrderInfo.setOrderPostage(pos);
		gjfOrderInfo.setCommisionType(commissionType);
		// 如果为比特
		if ("3".equals(platform)) {
			gjfOrderInfo.setDirectMemberMoney(dicTotalMoney);
		}
		gjfOrderInfo.setToken(
				Sha256.getSha256Hash(gjfOrderInfo.getOrderSn(), gjfOrderInfo.getPayType(), CommonStatus.SIGN_KEY_NUM));
		if (BeanUtil.isValid(orderSn)) {
			gjfOrderInfo.setOrderSn(orderSn);
		}
		if (BeanUtil.isValid(customerSn)) {
			gjfOrderInfo.setJdOrderSn(customerSn);
		}
		gjfOrderInfoDao.save(gjfOrderInfo);

		for (GjfOrderGoods orderGoods : gjfOrderGoods) {
			orderGoods.setOrderId(gjfOrderInfo);
			gjfOrderInfoDao.save(orderGoods);
		}

		gjfOrderInfoDao.update(gjfMemberInfo);

		// 巨惠易购vip升级处理
		// updateMemerUpgradeVip(gjfMemberInfo.getId());

		// 添加订单发货信息
		if (orderType.equals("1")) {
			GjfOrderAddress gjfOrderAddress = new GjfOrderAddress();
			gjfOrderAddress.setReciverAreaId(gjfMemberAddress.getAreaId());
			gjfOrderAddress.setReciverCityId(gjfMemberAddress.getCityId());
			gjfOrderAddress.setReciverProvinceId(gjfMemberAddress.getProviceId());
			gjfOrderAddress.setReciverName(gjfMemberAddress.getConsigneeName());
			gjfOrderAddress.setReciverMobile(gjfMemberAddress.getMobile());
			gjfOrderAddress.setReciverDetailAddress(gjfMemberAddress.getAddressDetail());
			gjfOrderAddress.setOrderId(gjfOrderInfo);
			gjfOrderInfoDao.save(gjfOrderAddress);
		}

		// 添加保存支付明细日志
		gjfMemberTradeLog.setToken(Sha256.getSha256Hash(gjfMemberTradeLog.getTradeNo(),
				gjfMemberTradeLog.getTradeType(), CommonStatus.SIGN_KEY_NUM));
		gjfOrderInfoDao.save(gjfMemberTradeLog);

		// 订单加入延迟队列
		/*
		 * OrderDelayQueue orderDelayQueue = new OrderDelayQueue();
		 * orderDelayQueue.addItem(gjfOrderInfo.getOrderSn(), 1380, 1);// 提醒用户付款
		 * orderDelayQueue.addItem(gjfOrderInfo.getOrderSn(), 1440, 2);// 取消订单
		 * orderDelayQueue.start();
		 */

		// 短信或站内信推送 TODO

		return new ResultVo(200, "下单成功", gjfOrderInfo);

	}

	/*
	 * 后台--修改订单的状态
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.order.GjfOrderInfoService#updateOrderStatus(
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo updateOrderStatus(String orderSn, String payOrderSn, String orderStatus, String account,
			String token, String client) {
		if (StringUtil.isBlank(orderSn)) {
			throw new MyException(400, "订单不存在", null);
		}
		/*
		 * if (StringUtil.isBlank(orderStatus) || (!"1".equals(orderStatus) ||
		 * !"2".equals(orderStatus) || !"3".equals(orderStatus)
		 * !"4".equals(orderStatus) && !"5".equals(orderStatus) &&
		 * !"6".equals(orderStatus))) { throw new MyException(400, "订单状态有误",
		 * null); }
		 */
		System.out.println("----订单状态----" + orderStatus);
		GjfOrderInfo gjfOrderInfo = null;
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("orderSn", orderSn);
		if (client.equals("0")) {
			attrs.put("token", token);
		}
		gjfOrderInfo = gjfOrderInfoDao.query(GjfOrderInfo.class, attrs);

		if (ObjValid.isNotValid(gjfOrderInfo)) {
			throw new MyException(400, "订单不存在", null);
		}

		if ("1".equals(orderStatus)) {// 已支付
			if (gjfOrderInfo.getOrderStatus().equals("1")) {
				throw new MyException(400, "订单已经付款，无需重复", null);
			} else if (!gjfOrderInfo.getOrderStatus().equals("0")) {
				throw new MyException(400, "订单不是待付款状态，不能进行支付", null);
			}
		} else if ("2".equals(orderStatus)) {// 已发货
			if (!gjfOrderInfo.getOrderStatus().equals("1")) {
				throw new MyException(400, "订单未付款，请先支付", null);
			}
		} else if ("3".equals(orderStatus)) {// 已收货
			if (!gjfOrderInfo.getOrderStatus().equals("2") && gjfOrderInfo.getOrderType().equals("1")) {
				throw new MyException(400, "订单未发货，请耐心等待", null);
			}
		} else if ("4".equals(orderStatus)) {// 已过期--目前有定时器进行操作，该判断不进行处理
			if (!gjfOrderInfo.getOrderStatus().equals("1")) {
				throw new MyException(400, "不能进行此操作", null);
			}
		} else if ("5".equals(orderStatus)) {// 已取消
			if (!gjfOrderInfo.getOrderStatus().equals("0")) {
				throw new MyException(400, "不能取消该订单", null);
			}
		} else if ("6".equals(orderStatus)) {// 已退款
			if (!gjfOrderInfo.getOrderStatus().equals("3")) {
				throw new MyException(400, "订单未确认收货，请先确认收货", null);
			}
		}
		if ("3".equals(orderStatus)) {
			gjfOrderInfo.setFinishedTime(new Date());
		}
		String payType = gjfOrderInfo.getPayType();
		if (orderStatus.equals("1") && payType.equals("4") || payType.equals("5") || payType.equals("6")) {
			GjfMemberInfo memberInfo = gjfOrderInfo.getMemberId();
			memberInfo.setBalanceMoney(memberInfo.getBalanceMoney().subtract(gjfOrderInfo.getOnlinePayAmount())
					.setScale(2, BigDecimal.ROUND_DOWN));
			memberInfo.setWithdrawalMoney(memberInfo.getWithdrawalMoney().subtract(gjfOrderInfo.getOnlinePayAmount())
					.setScale(2, BigDecimal.ROUND_DOWN));
			gjfOrderInfoDao.update(memberInfo);

			GjfMemberTradeDetail detail1 = new GjfMemberTradeDetail(null, gjfOrderInfo.getMemberId(),
					gjfOrderInfo.getOrderSn() + "-1", gjfOrderInfo.getOnlinePayAmount(), new Date(), new Date(), "0",
					"0", "网上商城下单");
			GjfMemberTradeDetail detail2 = new GjfMemberTradeDetail(null, gjfOrderInfo.getMemberId(),
					gjfOrderInfo.getOrderSn() + "-2", gjfOrderInfo.getOnlinePayAmount(), new Date(), new Date(), "1",
					"0", "网上商城下单");
			gjfOrderInfoDao.save(detail1);
			gjfOrderInfoDao.save(detail2);
		}

		// 查询订单商品详情信息
		attrs.remove("orderSn");
		attrs.put("orderId.id", gjfOrderInfo.getId());
		List<GjfOrderGoods> goods = gjfOrderInfoDao.queryList(GjfOrderGoods.class, "id", "asc", attrs);
		String goodSource = "0";
		String isCanUserCou = "0";
		for (GjfOrderGoods orderGood : goods) {
			goodSource = orderGood.getGoodsId().getSuorceGoods();
			isCanUserCou = orderGood.getGoodsId().getIsCanUserCou();
		}

		// 如果支付方式为积分支付
		if (orderStatus.equals("1") && "7".equals(payType)) {
			GjfMemberInfo memberInfo = gjfOrderInfo.getMemberId();

			if ("5".equals(goodSource)) {
				String str = ProUtil.confirmorder(gjfOrderInfo.getJdOrderSn(), gjfOrderInfo.getOrderSn());
				if (!BeanUtil.isValid(str)) {
					ResultVo rseultVo = RefundUtil.refundWeixinPay(gjfOrderInfo.getOrderSn(),
							String.valueOf(gjfOrderInfo.getOfflinePayAmount().doubleValue()));
					if (rseultVo.getCode() == 200) {
						gjfOrderInfo.setOrderStatus("6");
					} else {
						rseultVo = AlipayRefundUtil.aliRefund(gjfOrderInfo.getOrderSn(),
								String.valueOf(gjfOrderInfo.getOfflinePayAmount().doubleValue()), "商品无库存");
						if (rseultVo.getCode() == 200) {
							gjfOrderInfo.setOrderStatus("6");
						}
					}
				} else {
					gjfOrderInfo.setOrderStatus("2");
				}

			} else {

				gjfOrderInfo.setOrderStatus("1");
			}

			// 记录剩余需要支付的积分
			BigDecimal rewardConsumption = gjfOrderInfo.getOnlinePayAmount();
			// 如果总金额大于一类积分金额
			if (memberInfo.getMerchantFirstCousumptionMoney().doubleValue() < rewardConsumption.doubleValue()) {
				// 如果一类积分大于零
				if (memberInfo.getMerchantFirstCousumptionMoney().doubleValue() > 0) {
					// 记录减去扣除部分剩余的
					rewardConsumption = rewardConsumption.subtract(memberInfo.getMerchantFirstCousumptionMoney());
					// 扣减分红权
					updateDeductionDivi(memberInfo.getMobile(), "0",
							memberInfo.getMerchantFirstCousumptionMoney().doubleValue(), "1");
					// 清除积分
					memberInfo.setMerchantFirstCousumptionMoney(new BigDecimal(0));
				}

				// 如果剩余金额大于二类积分
				if (memberInfo.getMerchantSecondCousumptionMoney().doubleValue() < rewardConsumption.doubleValue()) {
					// 如果二类积分大于零
					if (memberInfo.getMerchantSecondCousumptionMoney().doubleValue() > 0) {
						// 记录减去扣除部分剩余的
						rewardConsumption = rewardConsumption.subtract(memberInfo.getMerchantSecondCousumptionMoney());
						// 扣减分红权
						updateDeductionDivi(memberInfo.getMobile(), "0",
								memberInfo.getMerchantSecondCousumptionMoney().doubleValue(), "2");
						// 清除积分
						memberInfo.setMerchantSecondCousumptionMoney(new BigDecimal(0));
					}

					// 如果还有剩余
					if (rewardConsumption.doubleValue() > 0
							&& memberInfo.getMerchantThreeCousumptionMoney().doubleValue() > 0) {
						// 三类积分
						memberInfo.setMerchantThreeCousumptionMoney(
								memberInfo.getMerchantThreeCousumptionMoney().subtract(rewardConsumption));
						// 扣减分红权
						updateDeductionDivi(memberInfo.getMobile(), "0", rewardConsumption.doubleValue(), "3");

					}

				} else {
					memberInfo.setMerchantSecondCousumptionMoney(
							memberInfo.getMerchantSecondCousumptionMoney().subtract(rewardConsumption));
					// 扣减一类分红权
					updateDeductionDivi(memberInfo.getMobile(), "0", rewardConsumption.doubleValue(), "2");
				}
				// 如果大于等于直接减
			} else {
				memberInfo.setMerchantFirstCousumptionMoney(
						memberInfo.getMerchantFirstCousumptionMoney().subtract(rewardConsumption));
				// 扣减分红权
				updateDeductionDivi(memberInfo.getMobile(), "0", rewardConsumption.doubleValue(), "1");
			}

			memberInfo
					.setConsumptionMoney(memberInfo.getConsumptionMoney().subtract(gjfOrderInfo.getOnlinePayAmount()));
			memberInfo
					.setDividendsTotalMoney(memberInfo.getDividendsTotalMoney().add(gjfOrderInfo.getOnlinePayAmount()));
			gjfOrderInfo.setOrderStatus("1");
		}
		// 如果支付方式为责任消费
		if (orderStatus.equals("1") && "8".equals(payType)) {
			GjfMemberInfo memberInfo = gjfOrderInfo.getMemberId();
			memberInfo.setInsuranceMoney(memberInfo.getInsuranceMoney().subtract(gjfOrderInfo.getOnlinePayAmount()));
			gjfOrderInfo.setOrderStatus("1");
		}

		if ("0".equals(gjfOrderInfo.getOrderType())) {
			gjfOrderInfo.setOrderStatus("3");
			gjfOrderInfo.setFinishedTime(new Date());
		} else {
			gjfOrderInfo.setOrderStatus(orderStatus);
		}

		// 如果支付方式为代金券
		if (orderStatus.equals("1") && "10".equals(payType)) {
			GjfMemberInfo memberInfo = gjfOrderInfo.getMemberId();

			if ("5".equals(goodSource)) {
				String str = ProUtil.confirmorder(gjfOrderInfo.getJdOrderSn(), gjfOrderInfo.getOrderSn());
				if (!BeanUtil.isValid(str)) {
					ResultVo rseultVo = RefundUtil.refundWeixinPay(gjfOrderInfo.getOrderSn(),
							String.valueOf(gjfOrderInfo.getOfflinePayAmount().doubleValue()));
					if (rseultVo.getCode() == 200) {
						gjfOrderInfo.setOrderStatus("6");
					} else {
						rseultVo = AlipayRefundUtil.aliRefund(gjfOrderInfo.getOrderSn(),
								String.valueOf(gjfOrderInfo.getOfflinePayAmount().doubleValue()), "商品无库存");
						if (rseultVo.getCode() == 200) {
							gjfOrderInfo.setOrderStatus("6");
						}
					}
				} else {
					gjfOrderInfo.setOrderStatus("2");
				}

			} else {

				gjfOrderInfo.setOrderStatus("1");
			}
			memberInfo.setMemberVoucherMoney(
					memberInfo.getMemberVoucherMoney().subtract(gjfOrderInfo.getOnlinePayAmount()));

			// 如果用户是非活跃用户
			if ("0".equals(memberInfo.getIsActiveMember())) {
				// 获取用户上个月获取的代金券金额
				BigDecimal voucherMoney = gjfOrderInfoDao.findMemberTotalVoucherLastMonth(memberInfo.getId());
				// 计算上个月50%的代金券
				BigDecimal dicVoucherMoney = voucherMoney.multiply(new BigDecimal(0.5)).setScale(2,
						BigDecimal.ROUND_DOWN);
				// 如果用户的代金券小于上个月50%的代金券
				if (memberInfo.getMemberVoucherMoney().doubleValue() < dicVoucherMoney.doubleValue()) {
					// 设置成为活跃会员
					memberInfo.setIsActiveMember("1");
					// 创建查询店铺的map
					Map<String, Object> storeMap = new HashMap<>();
					// 用户id
					storeMap.put("memberId.id", memberInfo.getId());
					// 获取店铺信息
					GjfStoreInfo storeInfo = gjfOrderInfoDao.query(GjfStoreInfo.class, storeMap);
					// 如果店铺不为空
					if (BeanUtil.isValid(storeInfo)) {
						// 店铺设置为活跃
						storeInfo.setIsActivityStore("1");
						// 更新店铺信息
						gjfOrderInfoDao.update(storeInfo);
					}
				}
			}

		}
		// 如果为京东商品
		if ("2".equals(goodSource) && "1".equals(orderStatus) && (payType.equals("1") || payType.equals("2")
				|| payType.equals("3") || payType.equals("4") || payType.equals("5") || payType.equals("6"))) {
			OrderResult result = JdNewUtil.cofirmOrder(gjfOrderInfo.getOrderSn(), gjfOrderInfo.getJdOrderSn());
			if (BeanUtil.isValid(result)) {
				gjfOrderInfo.setOrderStatus("2");
			} else {
				if ("1".equals(payType)) {
					ResultVo rseultVo = RefundUtil.refundWeixinPay(gjfOrderInfo.getOrderSn(),
							String.valueOf(gjfOrderInfo.getOfflinePayAmount().doubleValue()));
					if (rseultVo.getCode() == 200) {
						gjfOrderInfo.setOrderStatus("6");
					}
				}
				if ("2".equals(payType)) {
					ResultVo rseultVo = AlipayRefundUtil.aliRefund(gjfOrderInfo.getOrderSn(),
							String.valueOf(gjfOrderInfo.getOfflinePayAmount().doubleValue()), "商品无库存");
					if (rseultVo.getCode() == 200) {
						gjfOrderInfo.setOrderStatus("6");
					}
				}
			}

		}

		// 商品池
		if ("5".equals(goodSource) && "1".equals(orderStatus) && (payType.equals("1") || payType.equals("2")
				|| payType.equals("3") || payType.equals("4") || payType.equals("5") || payType.equals("6"))) {
			String str = ProUtil.confirmorder(gjfOrderInfo.getJdOrderSn(), gjfOrderInfo.getOrderSn());

			if (BeanUtil.isValid(str)) {
				gjfOrderInfo.setOrderStatus("2");
			} else {
				if ("1".equals(payType)) {
					ResultVo rseultVo = RefundUtil.refundWeixinPay(gjfOrderInfo.getOrderSn(),
							String.valueOf(gjfOrderInfo.getOfflinePayAmount().doubleValue()));
					if (rseultVo.getCode() == 200) {
						gjfOrderInfo.setOrderStatus("6");
					}
				}
				if ("2".equals(payType)) {
					ResultVo rseultVo = AlipayRefundUtil.aliRefund(gjfOrderInfo.getOrderSn(),
							String.valueOf(gjfOrderInfo.getOfflinePayAmount().doubleValue()), "商品无库存");
					if (rseultVo.getCode() == 200) {
						gjfOrderInfo.setOrderStatus("6");
					}
				}
			}

			//gjfOrderInfo.setOrderStatus("1");
		}

		gjfOrderInfoDao.update(gjfOrderInfo);

		// 获取平台信息
		String platform = "0";
		Map<String, Object> plAttrs = new HashMap<>();
		plAttrs.put("key", "PLATFORM");
		plAttrs.put("status", "1");
		GjfSetBaseInfo plaBase = gjfOrderInfoDao.query(GjfSetBaseInfo.class, plAttrs);
		if (BeanUtil.isValid(plaBase)) {
			platform = plaBase.getValue();
		}

		if (client.equals("1")) {
			Map<String, Object> attrsTradeLog = new HashMap<String, Object>();
			Map<String, Object> propsTradeLog = new HashMap<String, Object>();
			attrsTradeLog.put("linkTradeNo", orderSn);
			propsTradeLog.put("payTradeNo", payOrderSn);
			propsTradeLog.put("tradeTime", new Date());
			propsTradeLog.put("tradeStatus", "1".equals(orderStatus) ? "1" : "0");
			gjfOrderInfoDao.update(GjfMemberTradeLog.class, propsTradeLog, attrsTradeLog);
		}

		// 記錄用戶消費次數
		if (gjfOrderInfo.getOrderStatus().equals("1")
				&& (payType.equals("0") || payType.equals("1") || payType.equals("2") || payType.equals("3")
						|| payType.equals("4") || payType.equals("5") || payType.equals("6") || payType.equals("7"))) {

			// 查詢用戶消費記錄
			List<GjfMemberConsumptiomNum> list = gjfBenefitInfoDao
					.findMemberCousumptionNum(gjfOrderInfo.getMemberId().getId());
			// 如果不為空則直接加一
			if (BeanUtil.isValid(list)) {
				GjfMemberConsumptiomNum gjfMemberConsumptiomNum = list.get(0);
				gjfMemberConsumptiomNum.setShopConsumptionNum(gjfMemberConsumptiomNum.getShopConsumptionNum() + 1);
				gjfOrderInfoDao.update(gjfMemberConsumptiomNum);
			} else {
				GjfMemberConsumptiomNum consumptiomNum = new GjfMemberConsumptiomNum();
				consumptiomNum.setBenefitNum(0);
				consumptiomNum.setShopConsumptionNum(1);
				consumptiomNum.setMemberId(gjfOrderInfo.getMemberId().getId());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(new Date());
				ParsePosition ps = new ParsePosition(0);
				consumptiomNum.setAddTime(sdf.parse(date, ps));
				gjfOrderInfoDao.save(consumptiomNum);
			}

			// 判断用户最早消费时间是否为空
			if (!BeanUtil.isValid(gjfOrderInfo.getMemberId().getFirstConsumptionTime())) {
				GjfMemberInfo member = gjfOrderInfo.getMemberId();
				member.setFirstConsumptionTime(new Date());
			}

		}

		// 確認訂單
		if (gjfOrderInfo.getOrderStatus().equals("3")
				&& (payType.equals("0") || payType.equals("1") || payType.equals("2") || payType.equals("3")
						|| payType.equals("4") || payType.equals("5") || payType.equals("6") || payType.equals("9"))) {

			// 采购产品奖励
			if ("1".equals(gjfOrderInfo.getIsWholesalse())) {
				updateSuperMemberDirectMoney(gjfOrderInfo.getMemberId(), gjfOrderInfo);
			}

			// 凤凰云商
			if ("1".equals(platform)) {
				// 积分奖励
				if ("0".equals(isCanUserCou)) {

					if ("9".equals(payType)) {
						// 确认收货，则计算用户分红权
						gjfBenefitInfoService.updateMemberDividendsNumNotify(gjfOrderInfo.getMemberId().getMobile(),
								gjfOrderInfo.getStoreId().getMemberId().getMobile(),
								gjfOrderInfo.getOnlinePayAmount().doubleValue(), orderSn, "3");
					} else {
						// 确认收货，则计算用户分红权
						gjfBenefitInfoService.updateMemberDividendsNumNotify(gjfOrderInfo.getMemberId().getMobile(),
								gjfOrderInfo.getStoreId().getMemberId().getMobile(),
								gjfOrderInfo.getOfflinePayAmount().doubleValue(), orderSn, "3");
					}

				}
			}

		}

		return new ResultVo(200, "修改成功", null);
	}

	// 采购商品返佣
	@SuppressWarnings("unchecked")
	public void updateSuperMemberDirectMoney(GjfMemberInfo member, GjfOrderInfo gjfOrderInfo) {
		// 查询是那个平台
		String platform = "0";
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("key", "PLATFORM");
		attrs.put("status", "1");
		GjfSetBaseInfo plaBase = gjfOrderInfoDao.query(GjfSetBaseInfo.class, attrs);
		if (BeanUtil.isValid(plaBase)) {
			platform = plaBase.getValue();
		}
		// 如果是巨惠易购
		if ("0".equals(platform)) {

			// 如果为1.8万代理
			if ("3".equals(member.getMerchantType())) {
				GjfMemberUpgradeVipDirectMoney memDirectMoney = new GjfMemberUpgradeVipDirectMoney();
				memDirectMoney.setAddTime(new Date());
				memDirectMoney.setMemberId(member.getId());
				memDirectMoney.setMemberMobile(member.getMobile());
				memDirectMoney.setMemberName(member.getName());
				memDirectMoney.setMemberCou(gjfOrderInfo.getGoodsTotalAmount());
				memDirectMoney.setStatus("1");
				memDirectMoney.setTradeType("4");
				// 计算返佣金额
				BigDecimal memCou = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.02)).setScale(2,
						BigDecimal.ROUND_DOWN);
				memDirectMoney.setDirectMoney(memCou);
				member.setDirectMemberMoney(member.getDirectMemberMoney().add(memCou));
				gjfOrderInfoDao.save(memDirectMoney);

			} else if ("4".equals(member.getMerchantType())) {
				GjfMemberUpgradeVipDirectMoney memDirectMoney = new GjfMemberUpgradeVipDirectMoney();
				memDirectMoney.setAddTime(new Date());
				memDirectMoney.setMemberId(member.getId());
				memDirectMoney.setMemberMobile(member.getMobile());
				memDirectMoney.setMemberName(member.getName());
				memDirectMoney.setMemberCou(gjfOrderInfo.getGoodsTotalAmount());
				memDirectMoney.setStatus("1");
				memDirectMoney.setTradeType("4");
				// 计算返佣金额
				BigDecimal memCou = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.03)).setScale(2,
						BigDecimal.ROUND_DOWN);
				memDirectMoney.setDirectMoney(memCou);
				member.setDirectMemberMoney(member.getDirectMemberMoney().add(memCou));
				gjfOrderInfoDao.save(memDirectMoney);
			} else if ("5".equals(member.getMerchantType()) || "6".equals(member.getMerchantType())
					|| "7".equals(member.getMerchantType())) {
				GjfMemberUpgradeVipDirectMoney memDirectMoney = new GjfMemberUpgradeVipDirectMoney();
				memDirectMoney.setAddTime(new Date());
				memDirectMoney.setMemberId(member.getId());
				memDirectMoney.setMemberMobile(member.getMobile());
				memDirectMoney.setMemberName(member.getName());
				memDirectMoney.setMemberCou(gjfOrderInfo.getGoodsTotalAmount());
				memDirectMoney.setStatus("1");
				memDirectMoney.setTradeType("4");
				// 计算返佣金额
				BigDecimal memCou = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.04)).setScale(2,
						BigDecimal.ROUND_DOWN);
				memDirectMoney.setDirectMoney(memCou);
				member.setDirectMemberMoney(member.getDirectMemberMoney().add(memCou));
				gjfOrderInfoDao.save(memDirectMoney);
			}

			// 查询用户的推荐人
			Map<String, Object> superMap = new HashMap<>();
			superMap.put("id", member.getSuperId());
			GjfMemberInfo supreInfo = gjfOrderInfoDao.query(GjfMemberInfo.class, superMap);
			if (BeanUtil.isValid(supreInfo)) {
				BigDecimal deretMoney = new BigDecimal(0.00);
				/*
				 * if ("3".equals(supreInfo.getMerchantType())) { deretMoney =
				 * gjfOrderInfo.getGoodsTotalAmount().multiply(new
				 * BigDecimal(0.02)).setScale(2, BigDecimal.ROUND_DOWN); } else
				 * if ("4".equals(supreInfo.getMerchantType()) ||
				 * "5".equals(supreInfo.getMerchantType()) ||
				 * "6".equals(supreInfo.getMerchantType()) ||
				 * "7".equals(supreInfo.getMerchantType())) { deretMoney =
				 * gjfOrderInfo.getGoodsTotalAmount().multiply(new
				 * BigDecimal(0.03)).setScale(2, BigDecimal.ROUND_DOWN); }
				 */
				// 如果推荐人为普通商家或vip商家
				if ("0".equals(supreInfo.getMerchantType()) || "1".equals(supreInfo.getMerchantType())
						|| "2".equals(supreInfo.getMerchantType())) {
					GjfMemberUpgradeVipDirectMoney memDirectMoney = new GjfMemberUpgradeVipDirectMoney();
					memDirectMoney.setAddTime(new Date());
					memDirectMoney.setMemberId(member.getId());
					memDirectMoney.setMemberMobile(member.getMobile());
					memDirectMoney.setMemberName(member.getName());
					memDirectMoney.setMemberCou(gjfOrderInfo.getGoodsTotalAmount());
					memDirectMoney.setStatus("1");
					memDirectMoney.setTradeType("4");
					memDirectMoney.setDirectMemberId(supreInfo.getId());

					deretMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.02)).setScale(2,
							BigDecimal.ROUND_DOWN);
					memDirectMoney.setDirectMoney(deretMoney);
					gjfOrderInfoDao.save(memDirectMoney);
				} else {
					GjfMemberUpgradeVipDirectMoney memDirectMoney = new GjfMemberUpgradeVipDirectMoney();
					memDirectMoney.setAddTime(new Date());
					memDirectMoney.setMemberId(member.getId());
					memDirectMoney.setMemberMobile(member.getMobile());
					memDirectMoney.setMemberName(member.getName());
					memDirectMoney.setMemberCou(gjfOrderInfo.getGoodsTotalAmount());
					memDirectMoney.setStatus("1");
					memDirectMoney.setTradeType("4");
					memDirectMoney.setDirectMemberId(supreInfo.getId());
					deretMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.01)).setScale(2,
							BigDecimal.ROUND_DOWN);
					memDirectMoney.setDirectMoney(deretMoney);
					gjfOrderInfoDao.save(memDirectMoney);
				}

				// 如果为市代理
				if ("5".equals(supreInfo.getMerchantType()) || "6".equals(supreInfo.getMerchantType())
						|| "7".equals(supreInfo.getMerchantType())) {
					// 查询推荐人店铺信息
					Map<String, Object> storeAttrs = new HashMap<>();
					storeAttrs.put("memberId.id", supreInfo.getId());
					GjfStoreInfo store = gjfOrderInfoDao.query(GjfStoreInfo.class, storeAttrs);
					if (BeanUtil.isValid(store)) {
						Map<String, Object> agAttrs = new HashMap<>();
						agAttrs.put("memberId", member.getId());
						agAttrs.put("tradeType", member.getMerchantType());
						agAttrs.put("tradeStatus", "1");
						List<GjfMerchantUpgradeHistory> agList = gjfOrderInfoDao
								.queryList(GjfMerchantUpgradeHistory.class, "tradeTime", "desc", agAttrs);
						if (BeanUtil.isValid(agList)) {
							GjfMerchantUpgradeHistory up = agList.get(0);
							if ("5".equals(supreInfo.getMerchantType())) {
								if (up.getAreaId().getId() == store.getAreaId().getId()) {
									GjfMemberUpgradeVipDirectMoney memDirectMoney = new GjfMemberUpgradeVipDirectMoney();
									memDirectMoney.setAddTime(new Date());
									memDirectMoney.setMemberId(member.getId());
									memDirectMoney.setMemberMobile(member.getMobile());
									memDirectMoney.setMemberName(member.getName());
									memDirectMoney.setMemberCou(gjfOrderInfo.getGoodsTotalAmount());
									memDirectMoney.setStatus("1");
									memDirectMoney.setTradeType("4");
									memDirectMoney.setDirectMemberId(supreInfo.getId());
									BigDecimal realDeretMoney = gjfOrderInfo.getGoodsTotalAmount()
											.multiply(new BigDecimal(0.01)).setScale(2, BigDecimal.ROUND_DOWN);
									deretMoney = deretMoney.add(gjfOrderInfo.getGoodsTotalAmount()
											.multiply(new BigDecimal(0.01)).setScale(2, BigDecimal.ROUND_DOWN));
									gjfOrderInfo.setAgentDeirectMoney(gjfOrderInfo.getGoodsTotalAmount()
											.multiply(new BigDecimal(0.01)).setScale(2, BigDecimal.ROUND_DOWN));
									memDirectMoney.setDirectMoney(realDeretMoney);
									gjfOrderInfoDao.save(memDirectMoney);
								}
							} else {
								if (up.getCityId().getId() == store.getCityId().getId()) {
									GjfMemberUpgradeVipDirectMoney memDirectMoney = new GjfMemberUpgradeVipDirectMoney();
									memDirectMoney.setAddTime(new Date());
									memDirectMoney.setMemberId(member.getId());
									memDirectMoney.setMemberMobile(member.getMobile());
									memDirectMoney.setMemberName(member.getName());
									memDirectMoney.setMemberCou(gjfOrderInfo.getGoodsTotalAmount());
									memDirectMoney.setStatus("1");
									memDirectMoney.setTradeType("4");
									memDirectMoney.setDirectMemberId(supreInfo.getId());
									BigDecimal realDeretMoney = gjfOrderInfo.getGoodsTotalAmount()
											.multiply(new BigDecimal(0.01)).setScale(2, BigDecimal.ROUND_DOWN);
									deretMoney = deretMoney.add(gjfOrderInfo.getGoodsTotalAmount()
											.multiply(new BigDecimal(0.01)).setScale(2, BigDecimal.ROUND_DOWN));
									gjfOrderInfo.setAgentDeirectMoney(gjfOrderInfo.getGoodsTotalAmount()
											.multiply(new BigDecimal(0.01)).setScale(2, BigDecimal.ROUND_DOWN));
									memDirectMoney.setDirectMoney(realDeretMoney);
									gjfOrderInfoDao.save(memDirectMoney);
								}
							}
						}
					}

				}
				supreInfo.setDirectMemberMoney(supreInfo.getDirectMemberMoney().add(deretMoney));
				gjfOrderInfoDao.update(supreInfo);
				gjfOrderInfo.setDirectMemberId(supreInfo.getId());
				gjfOrderInfo.setDirectMemberMoney(deretMoney);
				gjfOrderInfoDao.update(gjfOrderInfo);
				gjfOrderInfoDao.update(member);
			}

		} else {// 湛江和云南
			// 查询用户的推荐人
			Map<String, Object> superMap = new HashMap<>();
			superMap.put("id", member.getSuperId());
			GjfMemberInfo supreInfo = gjfOrderInfoDao.query(GjfMemberInfo.class, superMap);
			if (BeanUtil.isValid(supreInfo)) {
				// 记录奖励记录
				BigDecimal deretMoney = new BigDecimal(0.00);
				if ("3".equals(supreInfo.getMerchantType())) {
					deretMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.02)).setScale(2,
							BigDecimal.ROUND_DOWN);
				} else if ("4".equals(supreInfo.getMerchantType()) || "5".equals(supreInfo.getMerchantType())
						|| "6".equals(supreInfo.getMerchantType()) || "7".equals(supreInfo.getMerchantType())) {
					deretMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.03)).setScale(2,
							BigDecimal.ROUND_DOWN);
				}

				// 如果为市代理
				if ("5".equals(supreInfo.getMerchantType()) || "6".equals(supreInfo.getMerchantType())
						|| "7".equals(supreInfo.getMerchantType())) {
					// 查询推荐人店铺信息
					Map<String, Object> storeAttrs = new HashMap<>();
					storeAttrs.put("memberId.id", supreInfo.getId());
					GjfStoreInfo store = gjfOrderInfoDao.query(GjfStoreInfo.class, storeAttrs);
					if (BeanUtil.isValid(store)) {
						Map<String, Object> agAttrs = new HashMap<>();
						agAttrs.put("memberId", member.getId());
						agAttrs.put("tradeType", member.getMerchantType());
						agAttrs.put("tradeStatus", "1");
						List<GjfMerchantUpgradeHistory> agList = gjfOrderInfoDao
								.queryList(GjfMerchantUpgradeHistory.class, "tradeTime", "desc", agAttrs);
						if (BeanUtil.isValid(agList)) {
							GjfMerchantUpgradeHistory up = agList.get(0);
							if ("5".equals(supreInfo.getMerchantType())) {
								if (up.getAreaId().getId() == store.getAreaId().getId()) {
									deretMoney = deretMoney.add(gjfOrderInfo.getGoodsTotalAmount()
											.multiply(new BigDecimal(0.01)).setScale(2, BigDecimal.ROUND_DOWN));
									gjfOrderInfo.setAgentDeirectMoney(gjfOrderInfo.getGoodsTotalAmount()
											.multiply(new BigDecimal(0.01)).setScale(2, BigDecimal.ROUND_DOWN));
								}
							} else {
								if (up.getCityId().getId() == store.getCityId().getId()) {
									deretMoney = deretMoney.add(gjfOrderInfo.getGoodsTotalAmount()
											.multiply(new BigDecimal(0.01)).setScale(2, BigDecimal.ROUND_DOWN));
									gjfOrderInfo.setAgentDeirectMoney(gjfOrderInfo.getGoodsTotalAmount()
											.multiply(new BigDecimal(0.01)).setScale(2, BigDecimal.ROUND_DOWN));
								}
							}
						}
					}

				}
				supreInfo.setDirectMemberMoney(supreInfo.getDirectMemberMoney().add(deretMoney));
				gjfOrderInfoDao.update(supreInfo);
				gjfOrderInfo.setDirectMemberId(supreInfo.getId());
				gjfOrderInfo.setDirectMemberMoney(deretMoney);
				gjfOrderInfoDao.update(gjfOrderInfo);
			}
		}

	}

	// 巨惠易购升级vip处理
	@SuppressWarnings("unchecked")
	public void updateMemerUpgradeVip(Long memberId, GjfOrderInfo gjfOrderInfo, String type) {
		// 查询用户信息
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("id", memberId);
		// 查询用户信息
		GjfMemberInfo memberInfo = gjfOrderInfoDao.query(GjfMemberInfo.class, attrs);
		// 如果用户信息不为空
		if (BeanUtil.isValid(memberInfo) && "0".equals(memberInfo.getIsActiveMember())
				&& "1".equals(gjfOrderInfo.getIsUpgradePro()) && "1".equals(type)) {
			// 查询用户消费金额
			BigDecimal comMoney = gjfOrderInfo.getGoodsTotalAmount().subtract(gjfOrderInfo.getSalseAmount());
			// comMoney = comMoney.add(gjfOrderInfo.getGoodsTotalAmount());
			// 获取升级的金额
			Map<String, Object> baseMap = new HashMap<>();
			baseMap.put("key", "VIP_UPGRADE_MONEY");
			baseMap.put("status", "1");
			/*
			 * GjfSetBaseInfo bseInfo =
			 * gjfOrderInfoDao.query(GjfSetBaseInfo.class, baseMap); double
			 * couMoney = 299; if (BeanUtil.isValid(bseInfo)) { couMoney =
			 * Double.valueOf(bseInfo.getValue()); }
			 */
			// 如果用户消费金额大于298
			if (comMoney.doubleValue() >= 0) {
				memberInfo.setIsActiveMember("1");
				// 查询用户推荐人
				attrs.put("id", memberInfo.getSuperId());
				// 获取推荐人
				GjfMemberInfo superInfo = gjfOrderInfoDao.query(GjfMemberInfo.class, attrs);
				// 如果推荐人不为空
				if (BeanUtil.isValid(superInfo)) {

					Token tokens = new Token();
					// 微信消息推送
					try {
						if (BeanUtil.isValid(superInfo.getOpenId())) {

							// 获取token信息
							Map<String, Object> tokenAttr = new HashMap<>();
							tokenAttr.put("type", "1");
							List<GjfAccessToken> list = gjfOrderInfoDao.queryList(GjfAccessToken.class,
									"expirationTime", "desc", tokenAttr);
							GjfAccessToken accessTokens = null;
							if (list.size() > 0) {
								accessTokens = list.get(0);
							}
							if (BeanUtil.isValid(accessTokens)) {// 如果tokentoken信息不为空
								Date time = new Date();
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								// 判断token是否失效
								if (sdf.parse(sdf.format(accessTokens.getExpirationTime()))
										.compareTo(sdf.parse(sdf.format(time))) == 1) {

									tokens.setAccess_token(accessTokens.getToken());
								} else {// 如果token失效
									tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID, // 吐过token信息
											CommonProperties.GJFENG_APPSECRET);
									int i = 0;
									while (!ObjValid.isValid(tokens.getAccess_token()) && i < 5) { // 如果请求失败重新获取token
										tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID,
												CommonProperties.GJFENG_APPSECRET);
										if (!ObjValid.isValid(tokens.getAccess_token())) { // 若取不到snsToken、则不获取openid;
											Thread.sleep(500); // 睡眠0.5 再取
										}
										i++;
									}

									// 保存token信息
									GjfAccessToken token = new GjfAccessToken();
									token.setAppId(CommonProperties.GJFENG_APP_ID);
									token.setAppsecret(CommonProperties.GJFENG_APPSECRET);
									token.setType("1");
									token.setExpirationTime(new Date());
									token.setToken(tokens.getAccess_token());
									gjfOrderInfoDao.save(token);
								}
							} else {
								tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID,
										CommonProperties.GJFENG_APPSECRET);
								// 如果请求失败重新获取token
								int i = 0;
								while (!ObjValid.isValid(tokens.getAccess_token()) && i < 5) { //
									tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID,
											CommonProperties.GJFENG_APPSECRET);
									if (!ObjValid.isValid(tokens.getAccess_token())) { // 若取不到snsToken、则不获取openid;
										Thread.sleep(500); // 睡眠0.5 再取
									}
									i++;
								}
								// 保存token信息
								GjfAccessToken token = new GjfAccessToken();
								token.setAppId(CommonProperties.GJFENG_APP_ID);
								token.setAppsecret(CommonProperties.GJFENG_APPSECRET);
								token.setType("1");
								token.setExpirationTime(new Date());
								token.setToken(tokens.getAccess_token());
								gjfOrderInfoDao.save(token);
							}

						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					// 创建推荐奖励奖励对象
					GjfMemberUpgradeVipDirectMoney upgradeVip = new GjfMemberUpgradeVipDirectMoney();
					upgradeVip.setAddTime(new Date());
					upgradeVip.setMemberId(memberInfo.getId());
					upgradeVip.setMemberMobile(memberInfo.getMobile());
					upgradeVip.setMemberName(memberInfo.getName());
					upgradeVip.setMemberCou(gjfOrderInfo.getGoodsTotalAmount());
					upgradeVip.setStatus("1");
					upgradeVip.setTradeType("0");
					// 给推荐人添加奖励
					baseMap.put("key", "UGRADEVIPDIRECTMONEY");
					baseMap.put("status", "1");
					GjfSetBaseInfo directBase = gjfOrderInfoDao.query(GjfSetBaseInfo.class, baseMap);
					String ugradeVipDirectMoney = "0.4";
					if (BeanUtil.isValid(directBase)) {
						ugradeVipDirectMoney = directBase.getValue();
					}

					BigDecimal directMoeny = comMoney.multiply(new BigDecimal(ugradeVipDirectMoney)).setScale(2,
							BigDecimal.ROUND_DOWN);
					superInfo.setDirectMemberMoney(superInfo.getDirectMemberMoney().add(directMoeny));
					// 记录奖励金额
					upgradeVip.setDirectMemberId(superInfo.getId());
					upgradeVip.setDirectMoney(directMoeny);

					// 进行消息推送
					MessageManager.sendUpgradeVipDirectMoney(memberInfo.getNickName(), "无", "会员升级",
							upgradeVip.getMemberCou().toString(), upgradeVip.getDirectMoney().toString(),
							superInfo.getOpenId(), tokens.getAccess_token());

					// 查询推荐人的上级
					attrs.put("id", superInfo.getSuperId());
					GjfMemberInfo firstSuperInfo = gjfOrderInfoDao.query(GjfMemberInfo.class, attrs);
					if (BeanUtil.isValid(firstSuperInfo) && "1".equals(firstSuperInfo.getIsActiveMember())) {// 如果上级存在
						// 查询第一层用户底下vip会员个数
						attrs.clear();
						attrs.put("superId", firstSuperInfo.getId());
						attrs.put("status", "1");
						attrs.put("isActiveMember", "1");
						Long memCount = gjfOrderInfoDao.queryTotalRecord(GjfMemberInfo.class, attrs);
						// 一级推荐人奖励
						BigDecimal firstDirectMoney = new BigDecimal(0);

						String memberCou = "2";
						baseMap.put("key", "DIRECTCOUNTMEMBER");
						baseMap.put("status", "1");
						GjfSetBaseInfo mmeCBase = gjfOrderInfoDao.query(GjfSetBaseInfo.class, baseMap);
						if (BeanUtil.isValid(mmeCBase)) {
							memberCou = mmeCBase.getValue();
							if (memCount > Long.valueOf(memberCou)) {
								baseMap.put("key", "FIRSTDIRECTMONEY2");
								baseMap.put("status", "1");
								GjfSetBaseInfo fistBaseInfo1 = gjfOrderInfoDao.query(GjfSetBaseInfo.class, baseMap);
								String firstDirect1 = "0.2";
								if (BeanUtil.isValid(fistBaseInfo1)) {
									firstDirect1 = fistBaseInfo1.getValue();
								}
								firstDirectMoney = comMoney.multiply(new BigDecimal(firstDirect1)).setScale(2,
										BigDecimal.ROUND_DOWN);
								firstSuperInfo.setDirectMemberMoney(firstSuperInfo.getDirectMemberMoney()
										.add(comMoney.multiply(new BigDecimal(firstDirect1)))
										.setScale(2, BigDecimal.ROUND_DOWN));

								// 进行消息推送
								MessageManager.sendUpgradeVipDirectMoney(memberInfo.getNickName(), "无", "会员升级vip",
										upgradeVip.getMemberCou().toString(),
										upgradeVip.getFirstDirectMoney().toString(), firstSuperInfo.getOpenId(),
										tokens.getAccess_token());
							}
							upgradeVip.setFirstDirectMember(firstSuperInfo.getId());
							upgradeVip.setFirstDirectMoney(firstDirectMoney);
							gjfOrderInfoDao.update(firstSuperInfo);

						}

					}
					gjfOrderInfoDao.save(upgradeVip);
					gjfOrderInfoDao.update(superInfo);
				}
			}
		}

		// 购买返佣
		if (BeanUtil.isValid(memberInfo) && "0".equals(gjfOrderInfo.getIsWholesalse())
				&& "0".equals(gjfOrderInfo.getIsUpgradePro()) && (!"10".equals(gjfOrderInfo.getPayType()))
				&& "3".equals(gjfOrderInfo.getSuoceGood()) && "0".equals(type)) {
			// 查询推荐人信息
			Map<String, Object> superMap = new HashMap<>();
			superMap.put("id", memberInfo.getSuperId());
			GjfMemberInfo supreInfo = gjfOrderInfoDao.query(GjfMemberInfo.class, superMap);
			// 如果推荐人信息不为空
			if (BeanUtil.isValid(supreInfo)) {
				// 计算利润
				BigDecimal chajia = gjfOrderInfo.getBenerfitMoney();
				// 创建历史记录对象
				GjfMemberUpgradeVipDirectMoney upgradeVip = new GjfMemberUpgradeVipDirectMoney();
				upgradeVip.setAddTime(new Date());
				upgradeVip.setMemberId(memberInfo.getId());
				upgradeVip.setMemberMobile(memberInfo.getMobile());
				upgradeVip.setMemberName(memberInfo.getName());
				upgradeVip.setMemberCou(chajia);
				upgradeVip.setStatus("1");
				upgradeVip.setTradeType("1");
				// 计算奖励金额
				Map<String, Object> baseMap = new HashMap<>();
				baseMap.put("key", "COMMISSIONMONEY");
				baseMap.put("status", "1");
				GjfSetBaseInfo directBase = gjfOrderInfoDao.query(GjfSetBaseInfo.class, baseMap);
				String ugradeVipDirectMoney = "0.4";
				if (BeanUtil.isValid(directBase)) {
					ugradeVipDirectMoney = directBase.getValue();
				}
				BigDecimal deretMoney = chajia.multiply(new BigDecimal(ugradeVipDirectMoney)).setScale(2,
						BigDecimal.ROUND_DOWN);
				supreInfo.setDirectMemberMoney(supreInfo.getDirectMemberMoney().add(deretMoney));
				// 记录奖励信息
				upgradeVip.setDirectMemberId(supreInfo.getId());
				upgradeVip.setDirectMoney(deretMoney);
				// 查询推荐人的上级
				superMap.put("id", supreInfo.getSuperId());
				GjfMemberInfo firstSuperMember = gjfOrderInfoDao.query(GjfMemberInfo.class, superMap);
				// 如果推荐人上级不为空
				if (BeanUtil.isValid(firstSuperMember) && "1".equals(firstSuperMember.getIsActiveMember())) {
					superMap.clear();
					superMap.put("superId", firstSuperMember.getId());
					superMap.put("status", "1");
					superMap.put("isActiveMember", "1");
					Long memCount = gjfOrderInfoDao.queryTotalRecord(GjfMemberInfo.class, superMap);
					// 记录推荐人上级奖励金额
					BigDecimal firstDirectMoney = new BigDecimal(0);
					String memberCou = "2";
					baseMap.put("key", "DIRECTCOUNTMEMBER");
					baseMap.put("status", "1");
					GjfSetBaseInfo mmeCBase = gjfOrderInfoDao.query(GjfSetBaseInfo.class, baseMap);
					if (BeanUtil.isValid(mmeCBase)) {
						memberCou = mmeCBase.getValue();
						if (memCount > Long.valueOf(memberCou)) {
							baseMap.put("key", "FISTCOMMISSIONMONEY2");
							baseMap.put("status", "1");
							GjfSetBaseInfo firstCommiss1 = gjfOrderInfoDao.query(GjfSetBaseInfo.class, baseMap);
							String firstComm2 = "0.2";
							if (BeanUtil.isValid(firstCommiss1)) {
								firstComm2 = firstCommiss1.getValue();
							}
							firstDirectMoney = chajia.multiply(new BigDecimal(firstComm2)).setScale(2,
									BigDecimal.ROUND_DOWN);
							firstSuperMember.setDirectMemberMoney(firstSuperMember.getDirectMemberMoney()
									.add(chajia.multiply(new BigDecimal(firstComm2)))
									.setScale(2, BigDecimal.ROUND_DOWN));
						}
						upgradeVip.setFirstDirectMember(firstSuperMember.getId());
						upgradeVip.setFirstDirectMoney(firstDirectMoney);
						gjfOrderInfoDao.update(firstSuperMember);

					}
				}
				gjfOrderInfoDao.save(upgradeVip);
				gjfOrderInfoDao.update(supreInfo);

			}
		} else if (BeanUtil.isValid(memberInfo) && "0".equals(gjfOrderInfo.getIsWholesalse())
				&& "0".equals(gjfOrderInfo.getIsUpgradePro()) && (!"10".equals(gjfOrderInfo.getPayType()))
				&& ("0".equals(gjfOrderInfo.getSuoceGood()) || "2".equals(gjfOrderInfo.getSuoceGood()))
				&& "0".equals(type)) {
			// 计算利润
			BigDecimal chajia = new BigDecimal(0.00);
			chajia = gjfOrderInfo.getGoodsTotalAmount().subtract(gjfOrderInfo.getSalseAmount());
			if ("2".equals(gjfOrderInfo.getSuoceGood())) {
				BigDecimal chcRate = new BigDecimal(0.01);
				Map<String, Object> baeseAttrs = new HashMap<>();
				baeseAttrs.put("key", "CAHNELCOST");
				GjfSetBaseInfo chcBase = gjfOrderInfoDao.query(GjfSetBaseInfo.class, baeseAttrs);
				if (BeanUtil.isValid(chcBase)) {
					chcRate = new BigDecimal(chcBase.getValue());
				}
				// 通道费用
				BigDecimal channelCost = gjfOrderInfo.getGoodsTotalAmount().multiply(chcRate).setScale(2,
						BigDecimal.ROUND_DOWN);
				// 获取增值税加卡通通道费率
				BigDecimal vatRate = new BigDecimal("0.38");
				baeseAttrs.put("key", "VAT");
				GjfSetBaseInfo vatBase = gjfOrderInfoDao.query(GjfSetBaseInfo.class, baeseAttrs);
				if (BeanUtil.isValid(vatBase)) {
					vatRate = new BigDecimal(vatBase.getValue());
				}
				// 增值税加卡通通道费
				BigDecimal vat = chajia.multiply(vatRate).setScale(2, BigDecimal.ROUND_DOWN);
				// 净利润
				chajia = chajia.subtract(channelCost).subtract(vat).setScale(2, BigDecimal.ROUND_DOWN);
			}
			// 创建历史记录对象
			GjfMemberUpgradeVipDirectMoney upgradeVip = new GjfMemberUpgradeVipDirectMoney();
			upgradeVip.setAddTime(new Date());
			upgradeVip.setMemberId(memberInfo.getId());
			upgradeVip.setMemberMobile(memberInfo.getMobile());
			upgradeVip.setMemberName(memberInfo.getName());
			upgradeVip.setMemberCou(chajia);
			upgradeVip.setStatus("1");
			BigDecimal reward = chajia.multiply(new BigDecimal(0.2)).divide(new BigDecimal(0.1), 2,
					BigDecimal.ROUND_DOWN);
			upgradeVip.setDirectMoney(reward);
			upgradeVip.setDirectMemberId(memberInfo.getId());
			upgradeVip.setTradeType("3");
			gjfOrderInfoDao.save(upgradeVip);
			// 给用户添加代金券金额
			memberInfo.setMemberVoucherMoney(memberInfo.getMemberVoucherMoney().add(reward));
			gjfOrderInfoDao.update(memberInfo);

			// 查询推荐人信息
			Map<String, Object> superMap = new HashMap<>();
			superMap.put("id", memberInfo.getSuperId());
			GjfMemberInfo supreInfo = gjfOrderInfoDao.query(GjfMemberInfo.class, superMap);
			if (BeanUtil.isValid(supreInfo)) {

				Token tokens = new Token();
				// 微信消息推送
				try {
					if (BeanUtil.isValid(supreInfo.getOpenId())) {

						// 获取token信息
						Map<String, Object> tokenAttr = new HashMap<>();
						tokenAttr.put("type", "1");
						List<GjfAccessToken> list = gjfOrderInfoDao.queryList(GjfAccessToken.class, "expirationTime",
								"desc", tokenAttr);
						GjfAccessToken accessTokens = null;
						if (list.size() > 0) {
							accessTokens = list.get(0);
						}
						if (BeanUtil.isValid(accessTokens)) {// 如果tokentoken信息不为空
							Date time = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							// 判断token是否失效
							if (sdf.parse(sdf.format(accessTokens.getExpirationTime()))
									.compareTo(sdf.parse(sdf.format(time))) == 1) {

								tokens.setAccess_token(accessTokens.getToken());
							} else {// 如果token失效
								tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID, // 吐过token信息
										CommonProperties.GJFENG_APPSECRET);
								int i = 0;
								while (!ObjValid.isValid(tokens.getAccess_token()) && i < 5) { // 如果请求失败重新获取token
									tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID,
											CommonProperties.GJFENG_APPSECRET);
									if (!ObjValid.isValid(tokens.getAccess_token())) { // 若取不到snsToken、则不获取openid;
										Thread.sleep(500); // 睡眠0.5 再取
									}
									i++;
								}

								// 保存token信息
								GjfAccessToken token = new GjfAccessToken();
								token.setAppId(CommonProperties.GJFENG_APP_ID);
								token.setAppsecret(CommonProperties.GJFENG_APPSECRET);
								token.setType("1");
								token.setExpirationTime(new Date());
								token.setToken(tokens.getAccess_token());
								gjfOrderInfoDao.save(token);
							}
						} else {
							tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID, CommonProperties.GJFENG_APPSECRET);
							// 如果请求失败重新获取token
							int i = 0;
							while (!ObjValid.isValid(tokens.getAccess_token()) && i < 5) { //
								tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID,
										CommonProperties.GJFENG_APPSECRET);
								if (!ObjValid.isValid(tokens.getAccess_token())) { // 若取不到snsToken、则不获取openid;
									Thread.sleep(500); // 睡眠0.5 再取
								}
								i++;
							}
							// 保存token信息
							GjfAccessToken token = new GjfAccessToken();
							token.setAppId(CommonProperties.GJFENG_APP_ID);
							token.setAppsecret(CommonProperties.GJFENG_APPSECRET);
							token.setType("1");
							token.setExpirationTime(new Date());
							token.setToken(tokens.getAccess_token());
							gjfOrderInfoDao.save(token);
						}

					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				// 如果推荐人为普通会员
				if ("0".equals(supreInfo.getIsActiveMember())) {
					// 计算代金券
				} else if ("1".equals(supreInfo.getIsActiveMember())) {
					// 创建记录现金记录
					GjfMemberUpgradeVipDirectMoney upgradeVipReward = new GjfMemberUpgradeVipDirectMoney();
					upgradeVipReward.setAddTime(new Date());
					upgradeVipReward.setMemberId(memberInfo.getId());
					upgradeVipReward.setMemberMobile(memberInfo.getMobile());
					upgradeVipReward.setMemberName(memberInfo.getName());
					upgradeVipReward.setMemberCou(chajia);
					upgradeVipReward.setStatus("1");

					// 记录推荐人代金券
					GjfMemberUpgradeVipDirectMoney upgradeVipSuper = new GjfMemberUpgradeVipDirectMoney();
					upgradeVipSuper.setAddTime(new Date());
					upgradeVipSuper.setMemberId(memberInfo.getId());
					upgradeVipSuper.setMemberMobile(memberInfo.getMobile());
					upgradeVipSuper.setMemberName(memberInfo.getName());
					upgradeVipSuper.setMemberCou(chajia);
					upgradeVipSuper.setStatus("1");

					// 计算赠送现金
					BigDecimal reward1 = chajia.multiply(new BigDecimal(0.16)).setScale(2, BigDecimal.ROUND_DOWN);
					// 计算赠送代金券
					BigDecimal vorMoney = chajia.multiply(new BigDecimal(0.4)).setScale(2, BigDecimal.ROUND_DOWN);
					// 记录现金记录
					upgradeVipReward.setDirectMoney(reward1);
					upgradeVipReward.setDirectMemberId(supreInfo.getId());
					upgradeVipReward.setTradeType("1");
					supreInfo.setDirectMemberMoney(supreInfo.getDirectMemberMoney().add(reward1));
					// 消息推送
					MessageManager.sendOrderDirectMoney(memberInfo.getNickName(), "订单返佣", gjfOrderInfo.getOrderSn(),
							gjfOrderInfo.getGoodsTotalAmount().toString(), upgradeVipReward.getDirectMoney().toString(),
							supreInfo.getOpenId(), tokens.getAccess_token());
					// 代金券记录
					upgradeVipSuper.setDirectMoney(vorMoney);
					upgradeVipSuper.setDirectMemberId(supreInfo.getId());
					upgradeVipSuper.setTradeType("3");
					supreInfo.setMemberVoucherMoney(supreInfo.getMemberVoucherMoney().add(vorMoney));
					// 查询推荐人的上级
					superMap.put("id", supreInfo.getSuperId());
					GjfMemberInfo firstSuperMember = gjfOrderInfoDao.query(GjfMemberInfo.class, superMap);
					// 如果推荐人上级不为空
					if (BeanUtil.isValid(firstSuperMember) && "1".equals(firstSuperMember.getIsActiveMember())) {
						superMap.clear();
						superMap.put("superId", firstSuperMember.getId());
						superMap.put("status", "1");
						superMap.put("isActiveMember", "1");
						Long memCount = gjfOrderInfoDao.queryTotalRecord(GjfMemberInfo.class, superMap);
						// 记录推荐人上级奖励金额
						Map<String, Object> baseMap = new HashMap<>();
						String memberCou = "2";
						baseMap.put("key", "DIRECTCOUNTMEMBER");
						baseMap.put("status", "1");
						GjfSetBaseInfo mmeCBase = gjfOrderInfoDao.query(GjfSetBaseInfo.class, baseMap);
						if (BeanUtil.isValid(mmeCBase)) {
							memberCou = mmeCBase.getValue();
							if (memCount > Long.valueOf(memberCou)) {
								// 计算第一层推荐人的金额
								BigDecimal firstReward = chajia.multiply(new BigDecimal(0.16)).setScale(2,
										BigDecimal.ROUND_DOWN);
								upgradeVipReward.setFirstDirectMember(firstSuperMember.getId());
								upgradeVipReward.setFirstDirectMoney(firstReward);
								firstSuperMember
										.setDirectMemberMoney(firstSuperMember.getDirectMemberMoney().add(firstReward));
								// 计算第一层推荐人的代金券
								BigDecimal firstVorMoney = chajia.multiply(new BigDecimal(0.4)).setScale(2,
										BigDecimal.ROUND_DOWN);
								upgradeVipSuper.setFirstDirectMember(firstSuperMember.getId());
								upgradeVipSuper.setFirstDirectMoney(firstVorMoney);
								firstSuperMember.setMemberVoucherMoney(
										firstSuperMember.getMemberVoucherMoney().add(firstVorMoney));
							}
							gjfOrderInfoDao.update(firstSuperMember);
							// 消息推送
							if (BeanUtil.isValid(firstSuperMember.getOpenId())) {
								MessageManager.sendOrderDirectMoney(memberInfo.getNickName(), "订单返佣",
										gjfOrderInfo.getOrderSn(), gjfOrderInfo.getGoodsTotalAmount().toString(),
										upgradeVipSuper.getDirectMoney().toString(), supreInfo.getOpenId(),
										tokens.getAccess_token());
							}
						}
					}
					gjfOrderInfoDao.save(upgradeVipSuper);
					gjfOrderInfoDao.save(upgradeVipReward);
					gjfOrderInfoDao.update(supreInfo);
				}

			}
		}

		gjfOrderInfoDao.update(memberInfo);
	}

	// 巨惠易购新升级返佣
	public void updateNewTtygMemerUpgradeVip(Long mId, GjfOrderInfo gjfOrderInfo, String type) {
		// 判断商品是否为升级产品
		if ("0".equals(gjfOrderInfo.getIsUpgradePro())) {
			// 如果是普通产品结束方法
			return;
		}

		// 查询用户信息
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("id", mId);
		attrs.put("status", "1");
		attrs.put("isDel", "1");
		GjfMemberInfo memberInfo = gjfOrderInfoDao.query(GjfMemberInfo.class, attrs);
		// 如果用户等级大于等于升级购买等级
		if (Integer.valueOf(memberInfo.getIsActiveMember()) >= Integer.valueOf(gjfOrderInfo.getIsUpgradePro())) {
			return;
		}

		if (BeanUtil.isValid(memberInfo)) {// 如果用户不为空
			// 如果参与奖励金额不为零,分配奖励金额
			if (gjfOrderInfo.getGoodsTotalAmount().doubleValue() > 0) {

				/*
				 * // 创建记录对象 GjfMemberUpgradeVipDirectMoney direct = new
				 * GjfMemberUpgradeVipDirectMoney(); // 添加时间
				 * direct.setAddTime(new Date()); // 用户id
				 * direct.setMemberId(memberInfo.getId()); // 电话号码
				 * direct.setMemberMobile(memberInfo.getMobile()); // 用户姓名
				 * direct.setMemberName(memberInfo.getName()); // 用户参与奖励金额
				 * direct.setMemberCou(gjfOrderInfo.getGoodsTotalAmount()); //
				 * 交易单号 direct.setTradeNo(gjfOrderInfo.getOrderSn()); // 奖励状态
				 * direct.setStatus("1"); // 交易类型 direct.setTradeType("0"); //
				 * 升级类型 direct.setUpgradeType(gjfOrderInfo.getIsUpgradePro());
				 * // 奖励当次 direct.setBlockNumber("0");
				 * 
				 * // 如果用户为二星用户 if ("1".equals(memberInfo.getIsActiveMember()))
				 * { // 计算奖励金额 BigDecimal directMoney =
				 * gjfOrderInfo.getGoodsTotalAmount().multiply(new
				 * BigDecimal(0.35)).setScale(2, BigDecimal.ROUND_DOWN); //
				 * 添加奖励金额 memberInfo.setDirectMemberMoney(memberInfo.
				 * getDirectMemberMoney().add(directMoney)); // 添加积分
				 * memberInfo.setConsumptionMoney(memberInfo.getConsumptionMoney
				 * ().add(directMoney)); // 更新用户信息
				 * gjfOrderInfoDao.update(memberInfo); // 记录奖励积分
				 * direct.setDirectPoint(directMoney); // 奖励金额
				 * direct.setDirectMoney(directMoney); // 保存数据
				 * gjfOrderInfoDao.save(direct); } // 如果用户为三星用户 if
				 * ("2".equals(memberInfo.getIsActiveMember())) { // 计算奖励金额
				 * BigDecimal directMoney =
				 * gjfOrderInfo.getGoodsTotalAmount().multiply(new
				 * BigDecimal(0.41)).setScale(2, BigDecimal.ROUND_DOWN); //
				 * 添加奖励金额 memberInfo.setDirectMemberMoney(memberInfo.
				 * getDirectMemberMoney().add(directMoney)); // 添加积分
				 * memberInfo.setConsumptionMoney(memberInfo.getConsumptionMoney
				 * ().add(directMoney)); // 更新用户信息
				 * gjfOrderInfoDao.update(memberInfo); // 记录奖励积分
				 * direct.setDirectPoint(directMoney); // 奖励金额
				 * direct.setDirectMoney(directMoney); // 保存数据
				 * gjfOrderInfoDao.save(direct); }
				 * 
				 * // 如果用户为三星用户 if ("3".equals(memberInfo.getIsActiveMember()))
				 * { // 计算奖励金额 BigDecimal directMoney =
				 * gjfOrderInfo.getGoodsTotalAmount().multiply(new
				 * BigDecimal(0.45)).setScale(2, BigDecimal.ROUND_DOWN); //
				 * 添加奖励金额 memberInfo.setDirectMemberMoney(memberInfo.
				 * getDirectMemberMoney().add(directMoney)); // 添加积分
				 * memberInfo.setConsumptionMoney(memberInfo.getConsumptionMoney
				 * ().add(directMoney)); // 更新用户信息
				 * gjfOrderInfoDao.update(memberInfo); // 记录奖励积分
				 * direct.setDirectPoint(directMoney); // 奖励金额
				 * direct.setDirectMoney(directMoney); // 保存数据
				 * gjfOrderInfoDao.save(direct); }
				 */

				// 创建接收推荐人集合
				List<GjfMemberInfo> directList = new ArrayList<>();
				// 获取推荐人
				List<GjfMemberInfo> memList = findDirectMember(memberInfo.getSuperId(), directList);
				// 如果返回集合不为空
				if (BeanUtil.isValid(memList)) {

					// 获取所有用户信息
					List<String> listArr = gjfBenefitInfoDao.findAllMemberType();
					// 创建接用户数据map
					Map<String, String[]> dataMap = new HashMap<String, String[]>();
					// 如果用户不为空
					if (null != listArr && listArr.size() > 0) {
						// 遍历用户数据
						for (String str : listArr) {
							if (StringUtil.isNotBlank(str)) {
								String[] strArr = str.split(",");
								String memberId = strArr[0];
								dataMap.put(memberId, strArr);
							}
						}
					}
					// 遍历集合
					for (int i = 0; i < memList.size(); i++) {
						// 获取推荐人信息
						GjfMemberInfo sMemberInfo = memList.get(i);

						// 如果集合不为空
						if (dataMap.size() > 0) {
							// 创建总集合list
							List<String> allGoldPersonal = new ArrayList<>();
							// 记录推荐者本身
							allGoldPersonal.add(sMemberInfo.getId().toString());
							// 查询用户上级星级用户
							List<String> goldPersonal = findPersonalIdsUpgradeVip(sMemberInfo.getId().toString(),
									dataMap);
							// 整合数据
							allGoldPersonal.addAll(goldPersonal);
							// 如果用户不为空
							if (BeanUtil.isValid(goldPersonal)) {
								// 分配奖励
								addDirectPointInfo(allGoldPersonal, i, memberInfo, gjfOrderInfo);
							}
						}
					}
				}
			}

			// 如果购买产品为升级产品
			if (Integer.valueOf(gjfOrderInfo.getIsUpgradePro()) > 0) {
				// 记录等级
				String upgradeVipPro = "0";
				// 如果为一星用户
				if ("0".equals(memberInfo.getIsActiveMember())) {
					memberInfo.setIsActiveMember(gjfOrderInfo.getIsUpgradePro());
					// 记录等级
					upgradeVipPro = gjfOrderInfo.getIsUpgradePro();
				}
				// 如果用户为二星用户并且升级等级大于用户本身等级
				if ("1".equals(memberInfo.getIsActiveMember()) && Integer
						.valueOf(gjfOrderInfo.getIsUpgradePro()) > Integer.valueOf(memberInfo.getIsActiveMember())) {
					memberInfo.setIsActiveMember(gjfOrderInfo.getIsUpgradePro());
					// 记录等级
					upgradeVipPro = gjfOrderInfo.getIsUpgradePro();
				}

				// 如果用户为三星用户并且升级等级大于用户本身等级
				if ("2".equals(memberInfo.getIsActiveMember()) && Integer
						.valueOf(gjfOrderInfo.getIsUpgradePro()) > Integer.valueOf(memberInfo.getIsActiveMember())) {
					memberInfo.setIsActiveMember(gjfOrderInfo.getIsUpgradePro());
					// 记录等级
					upgradeVipPro = gjfOrderInfo.getIsUpgradePro();
				}
				// 如果用户等级大于2
				if ("1".equals(upgradeVipPro) || "2".equals(upgradeVipPro)) {
					// 创建查询推荐人map
					Map<String, Object> supAttr = new HashMap<>();
					// 用户id
					supAttr.put("id", memberInfo.getSuperId());
					// 用户状态
					supAttr.put("status", "1");
					// 获取推荐人
					GjfMemberInfo superMem = gjfBenefitInfoDao.query(GjfMemberInfo.class, supAttr);
					// 如果推荐人不为空
					if (BeanUtil.isValid(superMem)) {
						// 如果升级等级二星
						if ("1".equals(upgradeVipPro)) {
							// 给推荐人添加二星用户数量
							superMem.setTwoStarMemberNum(superMem.getTwoStarMemberNum() + 1);
							// 如果二星用户大于5
							if (superMem.getTwoStarMemberNum() >= 5
									&& Integer.valueOf(superMem.getIsActiveMember()) < 2) {
								// 推荐人升级
								superMem.setIsActiveMember("2");
							}
							// 更新推荐人信息
							gjfBenefitInfoDao.update(superMem);
						}

						// 如果升级等级三星星
						if ("2".equals(upgradeVipPro)) {
							// 给推荐人添加二星用户数量
							superMem.setThreeStarMemberNum(superMem.getThreeStarMemberNum() + 1);
							// 如果二星用户大于5
							if (superMem.getThreeStarMemberNum() >= 5
									&& Integer.valueOf(superMem.getIsActiveMember()) < 3) {
								// 推荐人升级
								superMem.setIsActiveMember("3");
							}
							// 更新推荐人信息
							gjfBenefitInfoDao.update(superMem);
						}

					}
				}

			}

			// 更新用户信息
			gjfBenefitInfoDao.update(memberInfo);

		}
	}

	/**
	 * 极差计算奖励
	 * 
	 * @param goldPersonal
	 * @param layer
	 */
	public void addDirectPointInfo(List<String> goldPersonal, int layer, GjfMemberInfo memberInfo,
			GjfOrderInfo gjfOrderInfo) {

		// 记录用户登录
		String memberUpgradeType = "0";
		for (String goldMid : goldPersonal) {
			// 获取用户信息
			Map<String, Object> goldMap = new HashMap<>();
			goldMap.put("id", Long.valueOf(goldMid));
			GjfMemberInfo goldMember = gjfBenefitInfoDao.query(GjfMemberInfo.class, goldMap);
			// 创建记录对象
			GjfMemberUpgradeVipDirectMoney direct = new GjfMemberUpgradeVipDirectMoney();
			direct.setAddTime(new Date());
			direct.setMemberId(memberInfo.getId());
			direct.setMemberMobile(memberInfo.getMobile());
			direct.setMemberName(memberInfo.getName());
			direct.setMemberCou(gjfOrderInfo.getGoodsTotalAmount());
			direct.setTradeNo(gjfOrderInfo.getOrderSn());
			direct.setStatus("1");
			direct.setTradeType("0");
			direct.setUpgradeType(gjfOrderInfo.getIsUpgradePro());

			if (BeanUtil.isValid(goldMember)) {
				direct.setDirectMemberId(goldMember.getId());
				direct.setDirectMemberMobile(goldMember.getMobile());
				direct.setDirectMemberName(goldMember.getName());
				direct.setDirectMemberUpgradeType(goldMember.getIsActiveMember());
			}
			if (BeanUtil.isValid(goldMember) && !memberUpgradeType.equals(goldMember.getIsActiveMember())
					&& Integer.valueOf(memberUpgradeType) < Integer.valueOf(goldMember.getIsActiveMember())) {
				// 是否经过区代
				String isAfterAreaAgent = "0";
				// 获取配置信息
				Map<String, Object> baseMap = new HashMap<>();
				// 黄金会员控制
				String goldControl = "1";
				baseMap.put("key", "GOLDCONTROL");
				GjfSetBaseInfo goldInfo = gjfBenefitInfoDao.query(GjfSetBaseInfo.class, baseMap);
				if (BeanUtil.isValid(goldInfo)) {
					goldControl = goldInfo.getValue();
				}

				// 铂金控制
				String platinumControl = "1";
				baseMap.put("key", "PLATINUMCONTROL");
				GjfSetBaseInfo platinumInfo = gjfBenefitInfoDao.query(GjfSetBaseInfo.class, baseMap);
				if (BeanUtil.isValid(platinumInfo)) {
					platinumControl = platinumInfo.getValue();
				}

				// 钻石控制
				String masonryControl = "1";
				baseMap.put("key", "MASONRYCONTROL");
				GjfSetBaseInfo masonryInfo = gjfBenefitInfoDao.query(GjfSetBaseInfo.class, baseMap);
				if (BeanUtil.isValid(masonryInfo)) {
					masonryControl = masonryInfo.getValue();
				}

				// 黄金奖励
				if ("1".equals(goldMember.getIsActiveMember()) && "1".equals(goldControl)) {
					// 计算奖励金额
					BigDecimal directMoney = new BigDecimal(0);
					// 如果为第一层
					if (layer == 0) {
						directMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.2)).setScale(2,
								BigDecimal.ROUND_DOWN);
						// 记录层数
						direct.setBlockNumber("1");
					} else if (layer == 1) {// 第二层
						directMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.15)).setScale(2,
								BigDecimal.ROUND_DOWN);
						// 记录层数
						direct.setBlockNumber("2");
					} else if (layer == 2) {// 第三层
						directMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.1)).setScale(2,
								BigDecimal.ROUND_DOWN);
						// 记录层数
						direct.setBlockNumber("3");
					}

					// 添加奖励金额
					goldMember.setDirectMemberMoney(goldMember.getDirectMemberMoney().add(directMoney));
					// 添加积分
					goldMember.setConsumptionMoney(goldMember.getConsumptionMoney().add(directMoney));
					// 更新用户信息
					gjfOrderInfoDao.update(goldMember);
					// 记录奖励
					direct.setDirectPoint(directMoney);
					direct.setDirectMoney(directMoney);
					gjfOrderInfoDao.save(direct);
					// 记录类型
					memberUpgradeType = goldMember.getIsActiveMember();
					// 经过黄金奖励
					isAfterAreaAgent = "1";
				}
				// 铂金奖励
				if ("2".equals(goldMember.getIsActiveMember()) && "0".equals(memberUpgradeType)
						&& "1".equals(platinumControl)) {
					// 计算奖励金额
					BigDecimal directMoney = new BigDecimal(0);
					// 如果为第一层
					if (layer == 0) {
						directMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.23)).setScale(2,
								BigDecimal.ROUND_HALF_DOWN);
						// 记录层数
						direct.setBlockNumber("1");
					} else if (layer == 1) {// 第二层
						directMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.18)).setScale(2,
								BigDecimal.ROUND_HALF_DOWN);
						// 记录层数
						direct.setBlockNumber("2");
					} else if (layer == 2) {// 第三层
						directMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.15)).setScale(2,
								BigDecimal.ROUND_DOWN);
						// 记录层数
						direct.setBlockNumber("3");
					}

					// 添加奖励金额
					goldMember.setDirectMemberMoney(goldMember.getDirectMemberMoney().add(directMoney));
					// 添加积分
					goldMember.setConsumptionMoney(goldMember.getConsumptionMoney().add(directMoney));
					// 更新用户信息
					gjfOrderInfoDao.update(goldMember);

					// 记录奖励
					direct.setDirectPoint(directMoney);
					direct.setDirectMoney(directMoney);
					gjfOrderInfoDao.save(direct);
					// 记录类型
					memberUpgradeType = goldMember.getIsActiveMember();
				}
				// 铂金奖励
				if ("2".equals(goldMember.getIsActiveMember()) && "1".equals(memberUpgradeType)
						&& "1".equals(platinumControl)) {
					// 计算奖励金额
					BigDecimal directMoney = new BigDecimal(0);
					// 如果为第一层
					if (layer == 0) {
						directMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.03)).setScale(2,
								BigDecimal.ROUND_HALF_DOWN);
						// 记录层数
						direct.setBlockNumber("1");
					} else if (layer == 1) {// 第二层
						directMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.05)).setScale(2,
								BigDecimal.ROUND_DOWN);
						// 记录层数
						direct.setBlockNumber("2");
					} else if (layer == 2) {// 第三层
						directMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.02)).setScale(2,
								BigDecimal.ROUND_DOWN);
						// 记录层数
						direct.setBlockNumber("3");
					}
					// 添加奖励金额
					goldMember.setDirectMemberMoney(goldMember.getDirectMemberMoney().add(directMoney));
					// 添加积分
					goldMember.setConsumptionMoney(goldMember.getConsumptionMoney().add(directMoney));
					// 更新用户信息
					gjfOrderInfoDao.update(goldMember);
					// 记录奖励
					direct.setDirectPoint(directMoney);
					direct.setDirectMoney(directMoney);
					gjfOrderInfoDao.save(direct);
					// 记录类型
					memberUpgradeType = goldMember.getIsActiveMember();
				}

				// 钻石会员
				if (("3".equals(goldMember.getIsActiveMember()) || "4".equals(goldMember.getIsActiveMember())
						|| "5".equals(goldMember.getIsActiveMember()) || "6".equals(goldMember.getIsActiveMember()))
						&& "0".equals(memberUpgradeType) && "1".equals(masonryControl)) {
					// 计算奖励金额
					BigDecimal directMoney = new BigDecimal(0);
					// 如果为第一层
					if (layer == 0) {
						directMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.25)).setScale(2,
								BigDecimal.ROUND_DOWN);
						// 记录层数
						direct.setBlockNumber("1");
					} else if (layer == 1) {// 第二层
						directMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.2)).setScale(2,
								BigDecimal.ROUND_DOWN);
						// 记录层数
						direct.setBlockNumber("2");
					} else if (layer == 2) {// 第三层
						directMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.2)).setScale(2,
								BigDecimal.ROUND_DOWN);
						// 记录层数
						direct.setBlockNumber("3");
					}
					// 添加奖励金额
					goldMember.setDirectMemberMoney(goldMember.getDirectMemberMoney().add(directMoney));
					// 添加积分
					goldMember.setConsumptionMoney(goldMember.getConsumptionMoney().add(directMoney));
					// 更新用户信息
					gjfOrderInfoDao.update(goldMember);

					// 记录奖励
					direct.setDirectPoint(directMoney);
					direct.setDirectMoney(directMoney);
					gjfOrderInfoDao.save(direct);
					// 记录类型
					memberUpgradeType = goldMember.getIsActiveMember();
				}

				// 钻石会员
				if (("3".equals(goldMember.getIsActiveMember()) || "4".equals(goldMember.getIsActiveMember())
						|| "5".equals(goldMember.getIsActiveMember()) || "6".equals(goldMember.getIsActiveMember()))
						&& "1".equals(memberUpgradeType) && "1".equals(masonryControl)) {
					// 计算奖励金额
					BigDecimal directMoney = new BigDecimal(0);
					// 如果为第一层
					if (layer == 0) {
						directMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.05)).setScale(2,
								BigDecimal.ROUND_DOWN);
						// 记录层数
						direct.setBlockNumber("1");
					} else if (layer == 1) {// 第二层
						directMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.05)).setScale(2,
								BigDecimal.ROUND_DOWN);
						// 记录层数
						direct.setBlockNumber("2");
					} else if (layer == 2) {// 第三层
						directMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.05)).setScale(2,
								BigDecimal.ROUND_DOWN);
						// 记录层数
						direct.setBlockNumber("3");
					}
					// 添加奖励金额
					goldMember.setDirectMemberMoney(goldMember.getDirectMemberMoney().add(directMoney));
					// 添加积分
					goldMember.setConsumptionMoney(goldMember.getConsumptionMoney().add(directMoney));
					// 更新用户信息
					gjfOrderInfoDao.update(goldMember);
					// 记录奖励
					direct.setDirectPoint(directMoney);
					direct.setDirectMoney(directMoney);
					gjfOrderInfoDao.save(direct);
					// 记录类型
					memberUpgradeType = goldMember.getIsActiveMember();
				}
				// 钻石会员
				if (("3".equals(goldMember.getIsActiveMember()) || "4".equals(goldMember.getIsActiveMember())
						|| "5".equals(goldMember.getIsActiveMember()) || "6".equals(goldMember.getIsActiveMember()))
						&& "2".equals(memberUpgradeType) && "1".equals(masonryControl)
						&& "1".equals(isAfterAreaAgent)) {
					// 计算奖励金额
					BigDecimal directMoney = new BigDecimal(0);
					// 如果为第一层
					if (layer == 0) {
						directMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.02)).setScale(2,
								BigDecimal.ROUND_DOWN);
						// 记录层数
						direct.setBlockNumber("1");
					} else if (layer == 1) {// 第二层
						directMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.02)).setScale(2,
								BigDecimal.ROUND_DOWN);
						// 记录层数
						direct.setBlockNumber("2");
					} else if (layer == 2) {// 第三层
						directMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.02)).setScale(2,
								BigDecimal.ROUND_DOWN);
						// 记录层数
						direct.setBlockNumber("3");
					}
					// 添加奖励金额
					goldMember.setDirectMemberMoney(goldMember.getDirectMemberMoney().add(directMoney));
					// 添加积分
					goldMember.setConsumptionMoney(goldMember.getConsumptionMoney().add(directMoney));
					// 更新用户信息
					gjfOrderInfoDao.update(goldMember);
					// 记录奖励
					direct.setDirectPoint(directMoney);
					direct.setDirectMoney(directMoney);
					gjfOrderInfoDao.save(direct);
					// 记录类型
					memberUpgradeType = goldMember.getIsActiveMember();
				}

				// 钻石会员
				if (("3".equals(goldMember.getIsActiveMember()) || "4".equals(goldMember.getIsActiveMember())
						|| "5".equals(goldMember.getIsActiveMember()) || "6".equals(goldMember.getIsActiveMember()))
						&& "2".equals(memberUpgradeType) && "1".equals(masonryControl)
						&& "0".equals(isAfterAreaAgent)) {
					// 计算奖励金额
					BigDecimal directMoney = new BigDecimal(0);
					// 如果为第一层
					if (layer == 0) {
						directMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.02)).setScale(2,
								BigDecimal.ROUND_DOWN);
						// 记录层数
						direct.setBlockNumber("1");
					} else if (layer == 1) {// 第二层
						directMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.02)).setScale(2,
								BigDecimal.ROUND_DOWN);
						// 记录层数
						direct.setBlockNumber("2");
					} else if (layer == 2) {// 第三层
						directMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.02)).setScale(2,
								BigDecimal.ROUND_DOWN);
						// 记录层数
						direct.setBlockNumber("3");
					}
					// 添加奖励金额
					goldMember.setDirectMemberMoney(goldMember.getDirectMemberMoney().add(directMoney));
					// 添加积分
					goldMember.setConsumptionMoney(goldMember.getConsumptionMoney().add(directMoney));
					// 更新用户信息
					gjfOrderInfoDao.update(goldMember);
					// 记录奖励
					direct.setDirectPoint(directMoney);
					direct.setDirectMoney(directMoney);
					gjfOrderInfoDao.save(direct);
					// 记录类型
					memberUpgradeType = goldMember.getIsActiveMember();
				}

			}
		}

	}

	/**
	 * 获取三代推荐人
	 * 
	 * @param memId
	 * @param resultList
	 * @return
	 */
	public List<GjfMemberInfo> findDirectMember(Long memId, List<GjfMemberInfo> resultList) {
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("id", memId);
		attrs.put("status", "1");
		attrs.put("isDel", "1");
		GjfMemberInfo memberInfo = gjfOrderInfoDao.query(GjfMemberInfo.class, attrs);
		// 如果用户不为空
		if (BeanUtil.isValid(memberInfo)) {
			// 添加用户
			resultList.add(memberInfo);
			// 如果用户数量大于3
			if (resultList.size() >= 2) {
				return resultList;
			} else {
				findDirectMember(memberInfo.getSuperId(), resultList);
			}
		}

		return resultList;
	}

	/**
	 * 
	 * 放回会员上级id list，如果为空则找不到上级
	 * 
	 * @param memberId
	 * @param map
	 * @return
	 * @throws ParseException
	 */
	public static List<String> findPersonalIdsUpgradeVip(String memberId, Map<String, String[]> map) {

		// 会员不存在
		if (memberId == null || memberId.length() == 0) {
			return null;
		}

		// 列表为空，没得聊了
		if (map == null || map.isEmpty()) {
			return null;
		}

		// 该会员找不到则返回
		String[] theMember = map.get(memberId);
		if (theMember == null) {
			return null;
		}

		// 默认加入已加载队列
		Map<String, String[]> foundMap = new HashMap<String, String[]>();
		foundMap.put(memberId, theMember);

		// 该会员上级id
		String fatherId = theMember[1];
		if (fatherId.equals("0")) {
			return null;
		}
		List<String> personal = findPersonalIdUpgradeVip(fatherId, map, foundMap, null);
		return personal;
	}

	/**
	 * 获取用户上级id
	 * 
	 * @param memberId
	 * @param map
	 * @param foundMap
	 * @param personal
	 * @return
	 * @throws ParseException
	 */
	private static List<String> findPersonalIdUpgradeVip(String memberId, Map<String, String[]> map,
			Map<String, String[]> foundMap, List<String> personal) {

		// 该会员找不到则返回
		String[] theMember = map.get(memberId);
		if (theMember == null) {
			return personal;
		}

		// 已经找到个代们了
		/*
		 * if (personal != null && personal.size() == 2) { return personal; }
		 */

		// 如果已加载队列里已经有这个会员了，说明已经循环了
		if (foundMap.containsKey(memberId)) {
			return personal;
		}

		// 默认加入已加载队列
		foundMap.put(memberId, theMember);

		// 如果是个贷，1代表个贷
		String isPersonal = theMember[9];

		if (!"0".equals(isPersonal)) {
			String status = theMember[5];
			String isDel = theMember[6];
			if (status.equals("1") && isDel.equals("1")) {
				if (personal == null) {
					personal = new ArrayList<String>();
				}
				personal.add(memberId);
			}
		}
		// 该会员上级id
		String fatherId = theMember[1];
		if (fatherId != null && fatherId.length() > 0 && !fatherId.equals("0")) {
			personal = findPersonalIdUpgradeVip(fatherId, map, foundMap, personal);
		}
		return personal;
	}

	/**
	 * 比特普通产品推荐奖励金额
	 * 
	 * @param memId
	 * @param gjfOrderInfo
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateMemberDirect(Long memId, GjfOrderInfo gjfOrderInfo) {
		// 判断是否为普通产品
		if (!"0".equals(gjfOrderInfo.getIsUpgradePro())) {
			// 如果不是普通产品结束退出方法
			return;
		}
		// 创建查询用户信息map
		Map<String, Object> attrs = new HashMap<>();
		// 用户id
		attrs.put("id", memId);
		// 用户状态
		attrs.put("status", "1");
		// 获取用户信息
		GjfMemberInfo member = gjfOrderInfoDao.query(GjfMemberInfo.class, attrs);
		// 如果用户为空直接退出方法
		if (!BeanUtil.isValid(member)) {
			return;
		}
		// 创建推荐人集合
		List<GjfMemberInfo> memList = new ArrayList();
		// 获取推荐人
		memList = findDirectMember(member.getSuperId(), memList);
		// 如果返回结果不为空
		if (BeanUtil.isValid(memList)) {

			// 遍历结果集
			for (int i = 0; i < memList.size(); i++) {
				// 创建记录奖励历史记录对象
				GjfMemberUpgradeVipDirectMoney direct = new GjfMemberUpgradeVipDirectMoney();
				// 购买用户id
				direct.setMemberId(member.getId());
				// 购买用户电话
				direct.setMemberMobile(member.getMobile());
				// 购买用户姓名
				direct.setMemberName(member.getName());
				// 用户消费金额
				direct.setMemberCou(gjfOrderInfo.getGoodsTotalAmount());
				// 交易状态
				direct.setStatus("1");
				// 交易类型
				direct.setTradeType("1");
				// 交易单号
				direct.setTradeNo(gjfOrderInfo.getOrderSn());
				// 添加时间
				direct.setAddTime(new Date());

				// 获取推荐人信息
				GjfMemberInfo superMem = memList.get(i);
				// 如果推荐人为空执行下次循环
				if (!BeanUtil.isValid(superMem)) {
					continue;
				}
				// 如果推荐人等级为普通用户,结束当次循环
				if ("0".equals(superMem.getIsActiveMember())) {
					continue;
				}
				// 推荐人id
				direct.setDirectMemberId(superMem.getId());
				// 推荐人姓名
				direct.setDirectMemberName(superMem.getName());
				// 推荐人电话
				direct.setDirectMemberMobile(superMem.getMobile());
				// 推荐人等级
				direct.setDirectMemberUpgradeType(superMem.getIsActiveMember());
				// 奖励金额
				BigDecimal directMoney = new BigDecimal(0);
				// 如果为直接推荐人
				if (i == 0) {
					// 如果推荐人为二星星用户
					if ("1".equals(superMem.getIsActiveMember())) {
						// 计算奖励金额
						directMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.05)).setScale(2,
								BigDecimal.ROUND_DOWN);
					}
					// 如果推荐人为三星星用户
					if ("2".equals(superMem.getIsActiveMember())) {
						// 计算奖励金额
						directMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.06)).setScale(2,
								BigDecimal.ROUND_DOWN);
					}
					// 如果推荐人为四星星用户
					if ("3".equals(superMem.getIsActiveMember())) {
						// 计算奖励金额
						directMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.07)).setScale(2,
								BigDecimal.ROUND_DOWN);
					}
					// 如果推荐人为五星星用户
					if ("4".equals(superMem.getIsActiveMember())) {
						// 计算奖励金额
						directMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.08)).setScale(2,
								BigDecimal.ROUND_DOWN);
					}
					// 如果推荐人为六星星用户
					if ("5".equals(superMem.getIsActiveMember())) {
						// 计算奖励金额
						directMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.09)).setScale(2,
								BigDecimal.ROUND_DOWN);
					}
					// 如果推荐人为七星星用户
					if ("6".equals(superMem.getIsActiveMember())) {
						// 计算奖励金额
						directMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.1)).setScale(2,
								BigDecimal.ROUND_DOWN);
					}
					// 记录奖励金额
					direct.setDirectMoney(directMoney);
					// 给用户添加奖励金额
					superMem.setDirectMemberMoney(superMem.getDirectMemberMoney().add(directMoney));
					// 记录用户类型
					direct.setBlockNumber("1");
					// 保存奖励记录
					gjfOrderInfoDao.save(direct);
					// 更新用户
					gjfOrderInfoDao.update(superMem);
				} else if (i == 1) {// 如果为间接推荐人
					// 如果推荐人为二星星用户
					if ("1".equals(superMem.getIsActiveMember())) {
						// 计算奖励金额
						directMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.01)).setScale(2,
								BigDecimal.ROUND_DOWN);
					}
					// 如果推荐人为三星星用户
					if ("2".equals(superMem.getIsActiveMember())) {
						// 计算奖励金额
						directMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.02)).setScale(2,
								BigDecimal.ROUND_DOWN);
					}
					// 如果推荐人为四星星用户
					if ("3".equals(superMem.getIsActiveMember())) {
						// 计算奖励金额
						directMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.03)).setScale(2,
								BigDecimal.ROUND_DOWN);
					}
					// 如果推荐人为五星星用户
					if ("4".equals(superMem.getIsActiveMember())) {
						// 计算奖励金额
						directMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.04)).setScale(2,
								BigDecimal.ROUND_DOWN);
					}
					// 如果推荐人为六星星用户
					if ("5".equals(superMem.getIsActiveMember())) {
						// 计算奖励金额
						directMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.05)).setScale(2,
								BigDecimal.ROUND_DOWN);
					}
					// 如果推荐人为七星星用户
					if ("6".equals(superMem.getIsActiveMember())) {
						// 计算奖励金额
						directMoney = gjfOrderInfo.getGoodsTotalAmount().multiply(new BigDecimal(0.06)).setScale(2,
								BigDecimal.ROUND_DOWN);
					}
					// 记录奖励金额
					direct.setDirectMoney(directMoney);
					// 给用户添加奖励金额
					superMem.setDirectMemberMoney(superMem.getDirectMemberMoney().add(directMoney));
					// 记录用户类型
					direct.setBlockNumber("1");
					// 保存奖励记录
					gjfOrderInfoDao.save(direct);
					// 更新用户数据
					gjfOrderInfoDao.update(superMem);

				}
			}
		}
	}

	// 确认订单代理处理
	public void updateDealAgentReward(Long memId, GjfOrderInfo gjfOrderInfo) {
		// 如果订单金额小于零
		if (gjfOrderInfo.getGoodsTotalAmount().doubleValue() <= 0) {
			return;
		}
		// 创建查询map集合
		Map<String, Object> attrs = new HashMap<>();
		// 用户id
		attrs.put("id", memId);
		// 用户状态
		attrs.put("status", "1");
		// 用户删除状态
		attrs.put("isDel", "1");
		// 用户信息
		GjfMemberInfo memberInfo = gjfOrderInfoDao.query(GjfMemberInfo.class, attrs);
		// 如果用户信息为空结束方法
		if (!BeanUtil.isValid(memberInfo)) {
			return;
		}
		// 获取用户信息
		List<String> listArr = gjfBenefitInfoDao.findAllMemberType();
		// 如果数据不为空
		if (null != listArr && listArr.size() > 0) {
			// 创建接收查询数据集合
			Map<String, String[]> dataMap = new HashMap<String, String[]>();
			// 遍历集合
			for (String str : listArr) {
				// 如果字符串不为空
				if (StringUtil.isNotBlank(str)) {
					// 对字符串进行截取
					String[] strArr = str.split(",");
					// 获取用户id
					String memberId = strArr[0];
					// 把用户id放到集合里面
					dataMap.put(memberId, strArr);
				}
			}
			// 获取整条线代理id
			List<String> goldPersonal = findPersonalIdsAgent(memId.toString(), dataMap);
			// 如果代理不为空
			if (BeanUtil.isValid(goldPersonal)) {
				// 记录代理等级
				String memberUpgradeType = "0";
				// 遍历代理id集合
				for (String goldMid : goldPersonal) {
					// 清除
					attrs.clear();
					// 用户id
					attrs.put("id", Long.valueOf(goldMid));
					// 用户状态
					attrs.put("status", "1");
					// 用户删除状态
					attrs.put("isDel", "1");
					// 获取代理信息
					GjfMemberInfo agentInfo = gjfBenefitInfoDao.query(GjfMemberInfo.class, attrs);
					// 如果代理为空继续下一次循环
					if (!BeanUtil.isValid(agentInfo)) {
						continue;
					}
					// 格式化时间
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					// 当前时间
					String crrDate = sdf.format(new Date());
					// 代理结束时间
					String agentEndDate = sdf.format(agentInfo.getAgentEndDate());
					// 转为时间类型
					ParsePosition pos = new ParsePosition(0);
					// 当前时间
					Date crrTime = sdf.parse(crrDate, pos);
					// 转为时间类型
					ParsePosition pos1 = new ParsePosition(0);
					// 代理结束时间
					Date agentEndTime = sdf.parse(agentEndDate, pos1);
					// 如果代理时间到期继续下一次循环
					if (crrTime.getTime() > agentEndTime.getTime()) {
						continue;
					}

					// 创建记录奖励历史记录对象
					GjfMerchantModelAgentTradeHistory agentHistory = new GjfMerchantModelAgentTradeHistory();
					// 记录购买用户id
					agentHistory.setMemberId(memberInfo.getId());
					// 记录购买用户姓名
					agentHistory.setMemberMobile(memberInfo.getMobile());
					// 记录购买用户电话
					agentHistory.setMemberName(memberInfo.getName());
					// 添加时间
					agentHistory.setAddTime(new Date());
					// 订单号
					agentHistory.setTradeNo(gjfOrderInfo.getOrderSn());
					// 交易金额
					agentHistory.setTradeMoney(gjfOrderInfo.getGoodsTotalAmount());
					// 交易状态
					agentHistory.setTradeStatus("1");
					// 如果代理级别比记录的状态高一级
					if (!memberUpgradeType.equals(agentInfo.getIdentity())
							&& Integer.valueOf(memberUpgradeType) < Integer.valueOf(agentInfo.getIdentity())) {
						// 是否经过区代
						String isAfterAreaAgent = "0";

						// 清除attrs集合
						attrs.clear();
						// 订单id
						attrs.put("orderId.id", gjfOrderInfo.getId());
						// 获取订单收货地址
						GjfOrderAddress address = gjfBenefitInfoDao.query(GjfOrderAddress.class, attrs);
						// 如果为空则结束
						if (!BeanUtil.isValid(address)) {
							return;
						}

						// 创建获取区代设置比例map
						Map<String, Object> areaAttrs = new HashMap<>();
						// 关键字
						areaAttrs.put("key", "AREAIDENTITY");
						// 状态
						areaAttrs.put("status", "1");
						// 区代比例
						String areaProtist = "1";
						// 获取区代配置比例
						GjfSetBaseInfo areaProtistInfo = gjfBenefitInfoDao.query(GjfSetBaseInfo.class, areaAttrs);
						// 如果配置信息不为空
						if (BeanUtil.isValid(areaProtistInfo)) {
							areaProtist = areaProtistInfo.getValue();
						}

						// 市代关键字
						areaAttrs.put("key", "CITYIDENTITY");
						// 市代比例
						String cityProtist = "2";
						// 获取市代比例
						GjfSetBaseInfo cityProtistInfo = gjfBenefitInfoDao.query(GjfSetBaseInfo.class, areaAttrs);
						// 如果配置信息不为空
						if (BeanUtil.isValid(cityProtistInfo)) {
							cityProtist = cityProtistInfo.getValue();
						}

						// 省代关键字
						areaAttrs.put("key", "PROVINCEIDENTITY");
						// 省代比例
						String provinceprotist = "3";
						// 获取省代比例
						GjfSetBaseInfo provinceProtistInfo = gjfBenefitInfoDao.query(GjfSetBaseInfo.class, areaAttrs);
						// 如果配置信息不为空
						if (BeanUtil.isValid(provinceProtistInfo)) {
							provinceprotist = provinceProtistInfo.getValue();
						}

						// 如果地址区域不为空
						if (BeanUtil.isValid(address.getReciverAreaId()) && "1".equals(agentInfo.getIdentity())
								&& address.getReciverAreaId().getAreaId().equals(agentInfo.getAreaId().getAreaId())) {

							// 计算奖励金额
							BigDecimal areaDirectMoney = gjfOrderInfo.getGoodsTotalAmount()
									.multiply(new BigDecimal(areaProtist)).setScale(2, BigDecimal.ROUND_DOWN);
							// 记录区代用户id
							agentHistory.setAgentMemberId(agentInfo.getId());
							// 记录区代用户名
							agentHistory.setAgentMemberName(agentInfo.getName());
							// 记录区代电话号码
							agentHistory.setAgentMemberMobile(agentInfo.getMobile());
							// 记录奖励金额
							agentHistory.setAgentDirectMoney(areaDirectMoney);
							// 记录代理类型
							agentHistory.setAgentType("1");
							// 给代理添加奖励
							agentInfo.setDirectMemberMoney(agentInfo.getDirectMemberMoney().add(areaDirectMoney));
							// 更新用户信息
							gjfBenefitInfoDao.update(agentInfo);
							// 保存记录
							gjfBenefitInfoDao.save(agentHistory);
							// 改变代理类型
							memberUpgradeType = "1";

							// 经过区代
							isAfterAreaAgent = "1";
						}

						// 如果地址区域不为空,代理类型为市代,并且市前面没有区代
						if (BeanUtil.isValid(address.getReciverCityId()) && "2".equals(agentInfo.getIdentity())
								&& address.getReciverCityId().getCityId().equals(agentInfo.getCityId().getCityId())
								&& "0".equals(memberUpgradeType)) {

							// 计算奖励金额
							BigDecimal cityDirectMoney = gjfOrderInfo.getGoodsTotalAmount()
									.multiply(new BigDecimal(cityProtist)).setScale(2, BigDecimal.ROUND_DOWN);

							// 记录区代用户id
							agentHistory.setAgentMemberId(agentInfo.getId());
							// 记录区代用户名
							agentHistory.setAgentMemberName(agentInfo.getName());
							// 记录区代电话号码
							agentHistory.setAgentMemberMobile(agentInfo.getMobile());
							// 记录奖励金额
							agentHistory.setAgentDirectMoney(cityDirectMoney);
							// 记录代理类型
							agentHistory.setAgentType("2");
							// 给代理添加奖励
							agentInfo.setDirectMemberMoney(agentInfo.getDirectMemberMoney().add(cityDirectMoney));
							// 更新用户信息
							gjfBenefitInfoDao.update(agentInfo);
							// 保存记录
							gjfBenefitInfoDao.save(agentHistory);
							// 改变代理类型
							memberUpgradeType = "2";
						}

						// 如果地址区域不为空,代理类型为市代,并且市前面有区代
						if (BeanUtil.isValid(address.getReciverCityId()) && "2".equals(agentInfo.getIdentity())
								&& address.getReciverCityId().getCityId().equals(agentInfo.getCityId().getCityId())
								&& "1".equals(memberUpgradeType)) {

							// 计算奖励金额
							BigDecimal cityDirectMoney = gjfOrderInfo.getGoodsTotalAmount()
									.multiply(new BigDecimal(cityProtist).subtract(new BigDecimal(areaProtist)))
									.setScale(2, BigDecimal.ROUND_DOWN);

							// 记录区代用户id
							agentHistory.setAgentMemberId(agentInfo.getId());
							// 记录区代用户名
							agentHistory.setAgentMemberName(agentInfo.getName());
							// 记录区代电话号码
							agentHistory.setAgentMemberMobile(agentInfo.getMobile());
							// 记录奖励金额
							agentHistory.setAgentDirectMoney(cityDirectMoney);
							// 记录代理类型
							agentHistory.setAgentType("2");
							// 给代理添加奖励
							agentInfo.setDirectMemberMoney(agentInfo.getDirectMemberMoney().add(cityDirectMoney));
							// 更新用户信息
							gjfBenefitInfoDao.update(agentInfo);
							// 保存记录
							gjfBenefitInfoDao.save(agentHistory);
							// 改变代理类型
							memberUpgradeType = "2";
						}

						// 如果地址省不为空,代理类型为省代,并且市前面没有区代市代
						if (BeanUtil.isValid(address.getReciverProvinceId())
								&& "3".equals(agentInfo.getIdentity()) && address.getReciverProvinceId().getProvinceId()
										.equals(agentInfo.getProviceId().getProvinceId())
								&& "0".equals(memberUpgradeType)) {

							// 计算奖励金额
							BigDecimal provinceDirectMoney = gjfOrderInfo.getGoodsTotalAmount()
									.multiply(new BigDecimal(provinceprotist)).setScale(2, BigDecimal.ROUND_DOWN);

							// 记录区代用户id
							agentHistory.setAgentMemberId(agentInfo.getId());
							// 记录区代用户名
							agentHistory.setAgentMemberName(agentInfo.getName());
							// 记录区代电话号码
							agentHistory.setAgentMemberMobile(agentInfo.getMobile());
							// 记录奖励金额
							agentHistory.setAgentDirectMoney(provinceDirectMoney);
							// 记录代理类型
							agentHistory.setAgentType("3");
							// 给代理添加奖励
							agentInfo.setDirectMemberMoney(agentInfo.getDirectMemberMoney().add(provinceDirectMoney));
							// 更新用户信息
							gjfBenefitInfoDao.update(agentInfo);
							// 保存记录
							gjfBenefitInfoDao.save(agentHistory);
							// 改变代理类型
							memberUpgradeType = "3";
						}

						// 如果地址省不为空,代理类型为省代,并且市前面有区代没市代
						if (BeanUtil.isValid(address.getReciverProvinceId())
								&& "3".equals(agentInfo.getIdentity()) && address.getReciverProvinceId().getProvinceId()
										.equals(agentInfo.getProviceId().getProvinceId())
								&& "1".equals(memberUpgradeType)) {

							// 计算奖励比例
							BigDecimal provinceDirectMoney = gjfOrderInfo.getGoodsTotalAmount()
									.multiply(new BigDecimal(provinceprotist).subtract(new BigDecimal(areaProtist)))
									.setScale(2, BigDecimal.ROUND_DOWN);

							// 记录区代用户id
							agentHistory.setAgentMemberId(agentInfo.getId());
							// 记录区代用户名
							agentHistory.setAgentMemberName(agentInfo.getName());
							// 记录区代电话号码
							agentHistory.setAgentMemberMobile(agentInfo.getMobile());
							// 记录奖励金额
							agentHistory.setAgentDirectMoney(provinceDirectMoney);
							// 记录代理类型
							agentHistory.setAgentType("3");
							// 给代理添加奖励
							agentInfo.setDirectMemberMoney(agentInfo.getDirectMemberMoney().add(provinceDirectMoney));
							// 更新用户信息
							gjfBenefitInfoDao.update(agentInfo);
							// 保存记录
							gjfBenefitInfoDao.save(agentHistory);
							// 改变代理类型
							memberUpgradeType = "3";
						}

						// 如果地址省不为空,代理类型为省代,并且市前面有区代没市代,经过区代
						if (BeanUtil.isValid(address.getReciverProvinceId()) && "3".equals(agentInfo.getIdentity())
								&& address.getReciverProvinceId().getProvinceId()
										.equals(agentInfo.getProviceId().getProvinceId())
								&& "2".equals(memberUpgradeType) && "1".equals(isAfterAreaAgent)) {

							// 计算奖励金额
							BigDecimal provinceDirectMoney = gjfOrderInfo.getGoodsTotalAmount()
									.multiply(new BigDecimal(provinceprotist).subtract(new BigDecimal(cityProtist)))
									.setScale(2, BigDecimal.ROUND_DOWN);

							// 记录区代用户id
							agentHistory.setAgentMemberId(agentInfo.getId());
							// 记录区代用户名
							agentHistory.setAgentMemberName(agentInfo.getName());
							// 记录区代电话号码
							agentHistory.setAgentMemberMobile(agentInfo.getMobile());
							// 记录奖励金额
							agentHistory.setAgentDirectMoney(provinceDirectMoney);
							// 记录代理类型
							agentHistory.setAgentType("3");
							// 给代理添加奖励
							agentInfo.setDirectMemberMoney(agentInfo.getDirectMemberMoney().add(provinceDirectMoney));
							// 更新用户信息
							gjfBenefitInfoDao.update(agentInfo);
							// 保存记录
							gjfBenefitInfoDao.save(agentHistory);
							// 改变代理类型
							memberUpgradeType = "3";
						}

						// 如果地址省不为空,代理类型为省代,并且市前面有区代没市代，不经过区代
						if (BeanUtil.isValid(address.getReciverProvinceId()) && "3".equals(agentInfo.getIdentity())
								&& address.getReciverProvinceId().getProvinceId()
										.equals(agentInfo.getProviceId().getProvinceId())
								&& "2".equals(memberUpgradeType) && "0".equals(isAfterAreaAgent)) {

							// 计算奖励比例
							BigDecimal provinceDirectMoney = gjfOrderInfo.getGoodsTotalAmount()
									.multiply(new BigDecimal(provinceprotist).subtract(new BigDecimal(cityProtist)))
									.setScale(2, BigDecimal.ROUND_DOWN);

							// 记录区代用户id
							agentHistory.setAgentMemberId(agentInfo.getId());
							// 记录区代用户名
							agentHistory.setAgentMemberName(agentInfo.getName());
							// 记录区代电话号码
							agentHistory.setAgentMemberMobile(agentInfo.getMobile());
							// 记录奖励金额
							agentHistory.setAgentDirectMoney(provinceDirectMoney);
							// 记录代理类型
							agentHistory.setAgentType("3");
							// 给代理添加奖励
							agentInfo.setDirectMemberMoney(agentInfo.getDirectMemberMoney().add(provinceDirectMoney));
							// 更新用户信息
							gjfBenefitInfoDao.update(agentInfo);
							// 保存记录
							gjfBenefitInfoDao.save(agentHistory);
							// 改变代理类型
							memberUpgradeType = "3";
						}
					}

				}
			}
		}
	}

	/**
	 * 
	 * 放回会员上级id list，如果为空则找不到上级
	 * 
	 * @param memberId
	 * @param map
	 * @return
	 * @throws ParseException
	 */
	public static List<String> findPersonalIdsAgent(String memberId, Map<String, String[]> map) {

		// 会员不存在
		if (memberId == null || memberId.length() == 0) {
			return null;
		}

		// 列表为空，没得聊了
		if (map == null || map.isEmpty()) {
			return null;
		}

		// 该会员找不到则返回
		String[] theMember = map.get(memberId);
		if (theMember == null) {
			return null;
		}

		// 默认加入已加载队列
		Map<String, String[]> foundMap = new HashMap<String, String[]>();
		foundMap.put(memberId, theMember);

		// 该会员上级id
		String fatherId = theMember[1];
		if (fatherId.equals("0")) {
			return null;
		}
		List<String> personal = findPersonalIdAgent(fatherId, map, foundMap, null);
		return personal;
	}

	/**
	 * 获取用户上级id
	 * 
	 * @param memberId
	 * @param map
	 * @param foundMap
	 * @param personal
	 * @return
	 * @throws ParseException
	 */
	private static List<String> findPersonalIdAgent(String memberId, Map<String, String[]> map,
			Map<String, String[]> foundMap, List<String> personal) {

		// 该会员找不到则返回
		String[] theMember = map.get(memberId);
		if (theMember == null) {
			return personal;
		}

		// 已经找到个代们了
		/*
		 * if (personal != null && personal.size() == 2) { return personal; }
		 */

		// 如果已加载队列里已经有这个会员了，说明已经循环了
		if (foundMap.containsKey(memberId)) {
			return personal;
		}

		// 默认加入已加载队列
		foundMap.put(memberId, theMember);

		// 如果是个贷，1代表个贷
		String isPersonal = theMember[2];

		if (!"0".equals(isPersonal)) {
			String status = theMember[5];
			String isDel = theMember[6];
			if (status.equals("1") && isDel.equals("1")) {
				if (personal == null) {
					personal = new ArrayList<String>();
				}
				personal.add(memberId);
			}
		}
		// 该会员上级id
		String fatherId = theMember[1];
		if (fatherId != null && fatherId.length() > 0 && !fatherId.equals("0")) {
			personal = findPersonalIdAgent(fatherId, map, foundMap, personal);
		}
		return personal;
	}

	/*
	 * 删除订单
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.order.GjfOrderInfoService#delOrder(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public ResultVo delOrder(String orderSn, String account, String token, int isNeedMember) {
		GjfOrderInfo gjfOrderInfo = null;
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("orderSn", orderSn);
		attrs.put("token", token);
		if (isNeedMember == 1) {
			attrs.put("memberId.mobile", account);
		}
		gjfOrderInfo = gjfOrderInfoDao.query(GjfOrderInfo.class, attrs);
		if (ObjValid.isNotValid(gjfOrderInfo)) {
			throw new MyException(400, "订单不存在", null);
		}
		gjfOrderInfo.setIsDel("0");
		gjfOrderInfoDao.update(gjfOrderInfo);
		return new ResultVo(200, "删除成功", null);
	}

	/*
	 * 根据订单号查询订单
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.order.GjfOrderInfoService#findOrderBySn(java.
	 * lang.String)
	 */
	@Override
	public ResultVo findOrderBySn(String orderSn, String account, int isNeedMember) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("orderSn", orderSn);
		if (isNeedMember == 1) {
			attrs.put("memberId.mobile", account);
		}
		attrs.put("isDel", "1");
		GjfOrderInfo gjfOrderInfo = gjfOrderInfoDao.query(GjfOrderInfo.class, attrs);
		return new ResultVo(200, "查询成功", gjfOrderInfo);
	}

	/*
	 * 查询用户订单详情
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.order.GjfOrderInfoService#findOrderDetail(java.
	 * lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public ResultVo findOrderDetail(String orderSn, String account) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("orderSn", orderSn);
		attrs.put("memberId.mobile", account);
		attrs.put("isDel", "1");
		GjfOrderInfo gjfOrderInfo = gjfOrderInfoDao.query(GjfOrderInfo.class, attrs);
		if (ObjValid.isNotValid(gjfOrderInfo)) {
			return new ResultVo(400, "查询失败", null);
		}
		OrderInfoVo orderInfoVo = BeanUtilsEx.copyBean(OrderInfoVo.class, gjfOrderInfo);
		attrs.remove("orderSn");
		attrs.remove("memberId.mobile");
		attrs.remove("isDel");
		attrs.put("orderId.id", gjfOrderInfo.getId());
		List<GjfOrderGoods> gjfOrderGoods = gjfOrderInfoDao.queryList(GjfOrderGoods.class, "id", "asc", attrs);
		GjfOrderAddress gjfOrderAddress = gjfOrderInfoDao.query(GjfOrderAddress.class, attrs);
		List<OrderGoodsVo> goodsVos = new ArrayList<OrderGoodsVo>();
		for (GjfOrderGoods goods : gjfOrderGoods) {
			OrderGoodsVo orderGoodsVo = new OrderGoodsVo();
			if ("1".equals(gjfOrderInfo.getOrderType())) {
				orderGoodsVo.setGoodsId(goods.getGoodsId().getId());
			}
			orderGoodsVo.setGoodsAmount(goods.getGoodsAmount());
			orderGoodsVo.setGoodsName(goods.getGoodsName());
			orderGoodsVo.setGoodsAttr(goods.getGoodsAttr());
			orderGoodsVo.setGoodsNum(goods.getGoodsNum());
			goodsVos.add(orderGoodsVo);
		}
		orderInfoVo.setGoodsVos(goodsVos);
		orderInfoVo.setGjfOrderAddress(gjfOrderAddress);
		return new ResultVo(200, "查询成功", orderInfoVo);
	}

	/*
	 * 根据订单号和token查询订单
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.order.GjfOrderInfoService#findOrderBySn(java.
	 * lang.String, java.lang.String)
	 */
	@Override
	public ResultVo findOrderBySn(String orderSn, String token) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("orderSn", orderSn);
		attrs.put("token", token);
		attrs.put("isDel", "1");
		GjfOrderInfo gjfOrderInfo = gjfOrderInfoDao.query(GjfOrderInfo.class, attrs);
		return new ResultVo(200, "查询成功", gjfOrderInfo);
	}

	/*
	 * 根据账户和订单状态查询订单
	 * 
	 * @see cc.messcat.gjfeng.service.order.GjfOrderInfoService#findMyOrder(int,
	 * int, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ResultVo findMyOrder(int pageNo, int pageSize, String account, String status) {
		// 分页查询所有订单
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("memberId.mobile", account);
		if (StringUtil.isNotBlank(status) && Integer.parseInt(status) != 7) {
			attrs.put("orderStatus", status);
		}
		List<GjfOrderInfo> list = gjfOrderInfoDao.queryList(GjfOrderInfo.class, (pageNo - 1) * pageSize, pageSize,
				"addTime", "desc", attrs);
		List orderList = new ArrayList<>();
		for (GjfOrderInfo order : list) {
			Map orderMap = new HashMap<>();
			orderMap.put("payType", order.getPayType());
			orderMap.put("evaluationStatus", order.getEvaluationStatus());
			orderMap.put("orderStatus", order.getOrderStatus());
			orderMap.put("orderSn", order.getOrderSn());
			orderMap.put("orderType", order.getOrderType());
			orderMap.put("storeName", order.getStoreId().getStoreName());
			orderMap.put("goodsTotalAmount", order.getGoodsTotalAmount());
			orderMap.put("token", order.getToken());
			orderMap.put("goodSoure", order.getSuoceGood());
			orderMap.put("benefitMoney", order.getBenerfitMoney());
			orderMap.put("addTime", order.getAddTime());
			orderMap.put("id", order.getId());
			orderMap.put("orderPos", order.getOrderPostage());

			// 如果是友品集商品
			if ("1".equals(order.getSuoceGood()) && "0".equals(order.getOrderStatus())
					&& BeanUtil.isValid(order.getJdOrderSn())) {
				// 获取用户信息
				Map<String, Object> memMap = new HashMap<>();
				memMap.put("mobile", account);
				GjfMemberInfo mem = gjfOrderInfoDao.query(GjfMemberInfo.class, memMap);
				// 查询订单信息
				JSONObject json = NetFriendUtil.findOrderDetail(mem.getToken().substring(0, 50), order.getJdOrderSn());
				System.out.println(json);
				if (Integer.valueOf(json.get("errcode").toString()) == 0) {
					JSONObject object = json.getJSONObject("data");
					if ("1".equals(object.getString("status"))) {
						orderMap.put("orderStatus", "2");
						order.setOrderSn("2");
						gjfOrderInfoDao.update(order);
					}
				}
			}

			orderMap.put("jdOrderSn", order.getJdOrderSn());
			Map<String, Object> attr = new HashMap<>();
			attr.put("orderId.id", order.getId());
			List<GjfOrderGoods> goodslist = gjfOrderInfoDao.queryList(GjfOrderGoods.class, "id", "asc", attr);
			List goodList = new ArrayList<>();
			for (GjfOrderGoods goods : goodslist) {
				Map goodMap = new HashMap<>();
				if (!ObjValid.isNotValid(goods.getGoodsId())) {
					goodMap.put("goodsId", goods.getGoodsId().getId());
					if (!"5".equals(goods.getGoodsId().getSuorceGoods())) {
						goodMap.put("goodsPrice", goods.getGoodsId().getMarketPrice());
					} else {
						goodMap.put("goodsPrice", goods.getGoodsId().getPrice());
					}

				}

				goodMap.put("goodsName", goods.getGoodsName());
				goodMap.put("goodsAmount", goods.getGoodsAmount());
				goodMap.put("goodsPayAcmount", goods.getGoodsPayAmount());
				goodMap.put("goodNum", goods.getGoodsNum());
				if ("1".equals(order.getOrderType())) {
					goodMap.put("goodsImg", goods.getGoodsImageUrl());
				} else {
					goodMap.put("goodsImg", order.getStoreId().getStoreBanner());
				}

				goodList.add(goodMap);
			}
			orderMap.put("goods", goodList);
			// 查询快递信息
			GjfOrderAddress orederAddress = gjfOrderInfoDao.query(GjfOrderAddress.class, attr);
			if (BeanUtil.isValid(orederAddress)) {
				orderMap.put("recvierName", orederAddress.getReciverName());
				orderMap.put("recvierMobile", orederAddress.getReciverMobile());

				String addressDetail = "";
				addressDetail += orederAddress.getReciverProvinceId().getProvince();
				addressDetail += orederAddress.getReciverCityId().getCity();
				if (BeanUtil.isValid(orederAddress.getReciverAreaId())) {
					addressDetail += orederAddress.getReciverAreaId().getArea();
				}
				addressDetail += orederAddress.getReciverDetailAddress();
				orderMap.put("addressDetail", addressDetail);
			} else {
				orderMap.put("recvierName", "");
				orderMap.put("recvierMobile", "");
				orderMap.put("addressDetail", "");
			}

			orderList.add(orderMap);
		}
		return new ResultVo(200, "查询成功", orderList);

		// return new ResultVo(200, "查询成功",
		// gjfOrderInfoDao.findOrderByStatus(pageNo, pageSize, account,
		// status));
	}

	/*
	 * 查询店铺的订单
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.order.GjfOrderInfoService#findStoreOrder(int,
	 * int, java.lang.String, java.lang.String)
	 */
	public ResultVo findStoreOrder(int pageNo, int pageSize, String account, String status) {
		return null;
	}

	/*
	 * 分页查询用户订单
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.order.GjfOrderInfoService#findAllOrder(int,
	 * int, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo findAllOrder(int pageNo, int pageSize, String orderSn, String storeName, String goodsName,
			String name, String nickName, String orderStatus, String payType, String orderType, String startDate,
			String endDate, String ste, String jdOrderSn, String goodSource) {
		return new ResultVo(200, "查询成功", gjfOrderInfoDao.findAllOrder(pageNo, pageSize, orderSn, storeName, goodsName,
				name, nickName, orderStatus, payType, orderType, startDate, endDate, ste, jdOrderSn, goodSource));

	}

	/*
	 * 根据用户Id查询用户订单
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.order.GjfOrderInfoService#findOrderByMemberId(
	 * int, int, java.lang.Long)
	 */
	@Override
	public ResultVo findOrderByMemberId(int pageNo, int pageSize, Long memberId, String startTime, String endTime) {
		return new ResultVo(200, "查询成功",
				gjfOrderInfoDao.findOrderByMemberId(pageNo, pageSize, memberId, startTime, endTime));
	}

	/*
	 * 添加订单地址
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.order.GjfOrderInfoService#addOrderAddress(cc.
	 * messcat.gjfeng.entity.GjfOrderAddress, java.lang.Long)
	 */
	@Override
	public ResultVo addOrderAddress(GjfOrderAddress address, Long orderId) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("id", orderId);
		GjfOrderInfo gjfOrderInfo = gjfOrderInfoDao.query(GjfOrderInfo.class, attrs);
		gjfOrderInfo.setOrderStatus("2");
		gjfOrderInfo.setDeliveryTime(new Date());
		gjfOrderInfoDao.update(gjfOrderInfo);
		Map<String, Object> attrs0 = new HashMap<String, Object>();
		attrs0.put("id", address.getId());
		GjfOrderAddress gjfOrderAddress = gjfOrderInfoDao.query(GjfOrderAddress.class, attrs0);
		try {
			gjfOrderAddress = BeanUtil.setBeanByOtherBeanWithoutNull(gjfOrderAddress, address);
		} catch (Exception e) {
			e.printStackTrace();
		}
		gjfOrderInfoDao.update(gjfOrderAddress);
		return new ResultVo(200, "查询成功", null);
	}

	/*
	 * 查询订单明细
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.order.GjfOrderInfoService#findOrderDetailInBack
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo findOrderDetailInBack(String orderSn, String token) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("orderSn", orderSn);
		attrs.put("token", token);
		attrs.put("isDel", "1");
		GjfOrderInfo gjfOrderInfo = gjfOrderInfoDao.query(GjfOrderInfo.class, attrs);
		Map<String, Object> attrs0 = new HashMap<String, Object>();
		attrs0.put("orderId.id", gjfOrderInfo.getId());
		GjfOrderGoods gjfOrderGoods = gjfOrderInfoDao.query(GjfOrderGoods.class, attrs0);

		GjfOrderAddress gjfOrderAddress = gjfOrderInfoDao.query(GjfOrderAddress.class, attrs0);
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("gjfOrderInfo", gjfOrderInfo);
		info.put("gjfOrderGoods", gjfOrderGoods);
		info.put("gjfOrderAddress", gjfOrderAddress);
		return new ResultVo(200, "查询成功", info);
	}

	/*
	 * 查询订单地址明细
	 * 
	 * @see cc.messcat.gjfeng.service.order.GjfOrderInfoService#
	 * findOrderAddressDetail(java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo findOrderAddressDetail(String orderSn, String token) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("orderId.orderSn", orderSn);
		attrs.put("orderId.token", token);
		attrs.put("orderId.isDel", "1");
		GjfOrderAddress gjfOrderAddress = gjfOrderInfoDao.query(GjfOrderAddress.class, attrs);
		return new ResultVo(200, "获取成功", gjfOrderAddress);
	}

	/*
	 * 查找分销商品
	 * 
	 * @see cc.messcat.gjfeng.service.order.GjfOrderInfoService#
	 * findLowersOrderGoodsById(int, int, java.lang.Long)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ResultVo findLowersOrderGoodsById(int pageNo, int pageSize, Long id) {
		if (ObjValid.isNotValid(id)) {
			return new ResultVo(400, "查询失败", null);
		}
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("superId", id);
		List result = new ArrayList<>();
		// 下级
		List<GjfMemberInfo> gjfMemberInfos = gjfOrderInfoDao.queryList(GjfMemberInfo.class, "id", "asc", attrs);
		for (GjfMemberInfo gjfMemberInfo : gjfMemberInfos) {
			result.addAll(gjfOrderInfoDao.findLowersOrderGoodsById(gjfMemberInfo.getId()));
		}
		Pager pager = new Pager(pageSize, pageNo, result.size(), result);
		return new ResultVo(200, "查询成功", pager);
	}

	/*
	 * 根据订单Id查找订单的商品
	 * 
	 * @see cc.messcat.gjfeng.service.order.GjfOrderInfoService#
	 * findOrderGoodsByOrderId(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findOrderGoodsByOrderId(Long id) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("orderId", id);
		return new ResultVo(200, "查询成功", gjfOrderInfoDao.queryList(GjfOrderGoods.class, "id", "asc", attrs));
	}

	/*
	 * 查询商家O2O订单
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.order.GjfOrderInfoService#findOrderByCondition(
	 * int, int, java.lang.Long, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public ResultVo findOrderByCondition(int pageNo, int pageSize, Long id, String token, String startTime,
			String endTime) {
		return new ResultVo(200, "查询成功",
				gjfOrderInfoDao.findOrderByCondition(pageNo, pageSize, id, token, startTime, endTime));
	}

	/*
	 * 查询商家O2O订单:当前条件统计
	 * 
	 * @see cc.messcat.gjfeng.service.order.GjfOrderInfoService#
	 * findCountOrderByCondition(java.lang.Long, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo findCountOrderByCondition(Long id, String token, String startTime, String endTime) {
		return new ResultVo(200, "查询成功", gjfOrderInfoDao.findCountOrderByCondition(id, token, startTime, endTime));
	}

	@Override
	public ResultVo updateOrderPayMoney(String orderSn, Double onlinePay, Double offlinePay, String newOrderSn) {

		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("orderSn", orderSn);
		GjfOrderInfo gjfOrderInfo = gjfOrderInfoDao.query(GjfOrderInfo.class, attrs);
		if (ObjValid.isNotValid(gjfOrderInfo)) {
			return new ResultVo(400, "订单不存在", null);
		}
		gjfOrderInfo.setOnlinePayAmount(new BigDecimal(onlinePay));
		gjfOrderInfo.setOfflinePayAmount(new BigDecimal(offlinePay));
		gjfOrderInfo.setOrderSn(newOrderSn);
		return new ResultVo(200, "修改成功", gjfOrderInfoDao.update(gjfOrderInfo));
	}

	@Override
	public ResultVo addO2oOrderInfo(String account, Double payMoney, Long storeId, String orderType, String payType,
			String remark, Long couponsId) {
		// 获取用户信息
		GjfMemberInfo gjfMemberInfo = gjfMemberInfoService.findMember(account);
		if (ObjValid.isNotValid(gjfMemberInfo)) {
			throw new MyException(400, "用户不存在", null);
		}
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("id", storeId);
		GjfStoreInfo gjfStoreInfo = gjfOrderInfoDao.query(GjfStoreInfo.class, attrs);
		if (ObjValid.isNotValid(gjfStoreInfo)) {
			throw new MyException(400, "店铺不存在", null);
		}
		// 添加订单信息
		GjfOrderInfo gjfOrderInfo = new GjfOrderInfo();
		gjfOrderInfo.setOrderSn(DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)));
		gjfOrderInfo.setPickupCode(RandUtil.getRandomStr(6));
		gjfOrderInfo.setMemberId(gjfMemberInfo);
		gjfOrderInfo.setStoreId(gjfStoreInfo);
		gjfOrderInfo.setGoodsTotalAmount(new BigDecimal(payMoney));
		gjfOrderInfo.setOrderTotalAmount(new BigDecimal(payMoney));
		gjfOrderInfo.setRealPayAmount(new BigDecimal(payMoney));
		gjfOrderInfo.setOnlinePayAmount(new BigDecimal(0.00));
		gjfOrderInfo.setOfflinePayAmount(new BigDecimal(payMoney));
		gjfOrderInfo.setPayType("1");
		gjfOrderInfo.setOrderStatus("0");
		gjfOrderInfo.setPayTime(new Date());
		gjfOrderInfo.setStoreBenefitAmount(
				new BigDecimal(payMoney).multiply(new BigDecimal(0.12)).setScale(2, BigDecimal.ROUND_UP));
		gjfOrderInfo.setStoreRecAmount(new BigDecimal(payMoney).subtract(gjfOrderInfo.getStoreBenefitAmount())
				.setScale(2, BigDecimal.ROUND_UP));
		gjfOrderInfo.setRefundAmount(new BigDecimal(0.00));
		// gjfOrderInfo.setCouponsId(couponsId);
		gjfOrderInfo.setRemark(remark);
		gjfOrderInfo.setAddTime(new Date());
		gjfOrderInfo.setOrderType(orderType);
		gjfOrderInfo.setEvaluationStatus("0");
		gjfOrderInfo.setRefundStatus("0");
		gjfOrderInfo.setIsDel("1");
		gjfOrderInfo.setToken(
				Sha256.getSha256Hash(gjfOrderInfo.getOrderSn(), gjfOrderInfo.getPayType(), CommonStatus.SIGN_KEY_NUM));

		// 支付明细流水
		GjfMemberTradeLog gjfMemberTradeLog = new GjfMemberTradeLog();
		gjfMemberTradeLog.setTradeNo(DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)));
		gjfMemberTradeLog.setLinkTradeNo(gjfOrderInfo.getOrderSn());
		gjfMemberTradeLog.setStoreId(gjfOrderInfo.getStoreId());
		gjfMemberTradeLog.setMemberId(gjfOrderInfo.getMemberId());
		gjfMemberTradeLog.setAddTime(new Date());
		gjfMemberTradeLog.setTradeType("3");
		gjfMemberTradeLog.setTradeTime(new Date());
		gjfMemberTradeLog.setTradeMoney(new BigDecimal(payMoney));
		gjfMemberTradeLog.setTradeTrmo("微信扫码支付");
		gjfMemberTradeLog.setTradeStatus("0");
		gjfMemberTradeLog.setToken(Sha256.getSha256Hash(gjfMemberTradeLog.getTradeNo(),
				gjfMemberTradeLog.getTradeType(), CommonStatus.SIGN_KEY_NUM));
		gjfOrderInfoDao.save(gjfOrderInfo);
		gjfOrderInfoDao.save(gjfMemberTradeLog);
		return new ResultVo(200, "添加成功", gjfOrderInfo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findO2oOrderInfo(String status, Long storeId, String beginTime, String endTime, Integer pageNo,
			Integer pageSize) {
		Map<String, Object> attr = new HashMap<>();
		attr.put("storeId.id", storeId);
		attr.put("orderType", "0");
		if (Integer.parseInt(status) != 7) {
			attr.put("orderStatus", status);
		}
		return new ResultVo(200, "添加成功", gjfOrderInfoDao.queryList(GjfOrderInfo.class, (pageNo - 1) * pageSize,
				pageSize, "addTime", "desc", attr));
	}

	// @Override
	// public ResultVo backOrderByOrderId(Long orderId,String orderSn,String
	// token){
	// Map<String,Object> attrs = new HashMap<String,Object>();
	// attrs.put("id", orderId);
	// attrs.put("orderSn", orderSn);
	// attrs.put("token", token);
	// GjfOrderInfo orderInfo = gjfOrderInfoDao.query(GjfOrderInfo.class,
	// attrs);
	//
	// if(ObjValid.isNotValid(orderInfo)){
	// return new ResultVo(404,"订单不存在！");
	// }
	// String orderStatus = orderInfo.getOrderStatus();
	// if(ObjValid.isNotValid(orderStatus) || !"1".equals(orderStatus)){
	// if("6".equals(orderStatus))
	// return new ResultVo(404,"订单状态已退款");
	// return new ResultVo(404,"订单状态不为已付款");
	// }
	// String orderType = orderInfo.getOrderType();
	// if("0".equals(orderType)){
	// //余额支付
	// refundBalanceMoney(orderInfo);
	// }else if ("1".equals(orderType)) {
	// refundWeixinPay(orderInfo);
	// }
	//
	// return new ResultVo();
	// }

	@Override
	public ResultVo refundOnlineMoney(GjfOrderInfo orderInfo) {

		GjfMemberInfo memberInfo = orderInfo.getMemberId();
		BigDecimal onlineMoney = orderInfo.getOnlinePayAmount();
		String payType = orderInfo.getPayType();
		if ("0".equals(payType)) {
			// 余额
			memberInfo.setBalanceMoney(memberInfo.getBalanceMoney().add(onlineMoney));
		} else if ("7".equals(payType)) {
			// 待领消费金额
			memberInfo.setConsumptionMoney(memberInfo.getConsumptionMoney().add(onlineMoney));
		} else if ("8".equals(payType)) {
			// 责任消费金额
			memberInfo.setInsuranceMoney(memberInfo.getInsuranceMoney().add(onlineMoney));
		} else if ("10".equals(payType)) {
			// 代金券
			memberInfo.setMemberVoucherMoney(memberInfo.getMemberVoucherMoney().add(onlineMoney));
		} else if ("9".equals(payType)) {
			// 查询凤凰宝信息
			Map<String, Object> fhMap = new HashMap<>();
			fhMap.put("mobile", memberInfo.getMobile());
			GjfFhTreasureInfo fh = gjfOrderInfoDao.query(GjfFhTreasureInfo.class, fhMap);
			if (BeanUtil.isValid(fh)) {

				// 凤凰宝交易记录
				GjfMemberTreasureTrade trade = new GjfMemberTreasureTrade();
				trade.setAddTime(new Date());
				trade.setMemberId(memberInfo.getId());
				trade.setMemberTreasureMoneyBf(fh.getFhTreasureMoney());
				trade.setMemberName(memberInfo.getName());
				trade.setMemebrMobile(memberInfo.getMobile());
				trade.setTradeStatus("1");
				trade.setTradeType("11");
				trade.setMemberTreasureTradeMoney(onlineMoney);
				fh.setFhTreasureMoney(fh.getFhTreasureMoney().add(onlineMoney));
				trade.setMemberTreasureMoneyAf(fh.getFhTreasureMoney());
				trade.setTradeNo(orderInfo.getOrderSn());
				gjfOrderInfoDao.update(fh);
				gjfOrderInfoDao.save(trade);
			} else {
				return new ResultVo(400, "凤凰宝不存在");
			}
		}

		// 更新订单状态
		BigDecimal refundAmount = new BigDecimal(0.00).setScale(2, BigDecimal.ROUND_UP);
		if ("0".equals(payType) || "7".equals(payType) || "8".equals(payType) || "9".equals(payType)
				|| "10".equals(payType)) {
			// 余额 待领消费金额 责任消费金额
			refundAmount = orderInfo.getOnlinePayAmount();
		} else if ("1".equals(payType) || "2".equals(payType) || "3".equals(payType)) {
			// 微信支付宝银联
			refundAmount = orderInfo.getOfflinePayAmount();
		}
		orderInfo.setOrderStatus("6");
		orderInfo.setRefundStatus("2");
		orderInfo.setRefundTime(new Date());
		orderInfo.setRefundAmount(refundAmount);
		gjfOrderInfoDao.update(memberInfo);
		gjfOrderInfoDao.update(orderInfo);
		return new ResultVo(200, "操作成功");
	}

	@Override
	public ResultVo updateRefundStatus(GjfOrderInfo orderInfo) {
		String payType = orderInfo.getOrderType();
		BigDecimal refundAmount = new BigDecimal(0.00).setScale(2, BigDecimal.ROUND_UP);
		if ("0".equals(payType) || "7".equals(payType) || "8".equals(payType)) {
			// 余额 待领消费金额 责任消费金额
			refundAmount = orderInfo.getOnlinePayAmount();
		} else if ("1".equals(payType) || "2".equals(payType) || "3".equals(payType)) {
			// 微信支付宝银联
			refundAmount = orderInfo.getOfflinePayAmount();
		}
		orderInfo.setOrderStatus("6");
		orderInfo.setRefundStatus("2");
		orderInfo.setRefundTime(new Date());
		orderInfo.setRefundAmount(refundAmount);
		gjfOrderInfoDao.update(orderInfo);
		return new ResultVo(200, "操作成功");
	}

	/**
	 * 天猫订单处理
	 */
	@Override
	public ResultVo addTianmaoOrder(String item_title, String pay_price, String commission, String trade_id, String uid,
			String status, String apitype) {
		// 查询订单是否存在
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("jdOrderSn", trade_id);
		GjfOrderInfo orderInfo = gjfOrderInfoDao.query(GjfOrderInfo.class, attrs);
		if (!BeanUtil.isValid(orderInfo)) {// 如果订单不存在，则添加订单
			// 查询用户信息
			attrs.remove("jdOrderSn");
			String str = uid.substring(4, uid.length());
			attrs.put("id", Long.valueOf(str));
			GjfMemberInfo memberInfo = gjfOrderInfoDao.query(GjfMemberInfo.class, attrs);
			if (!BeanUtil.isValid(memberInfo)) {
				return new ResultVo(400, "用户不存在", null);
			}

			attrs.remove("id");
			attrs.put("storeIsOwnShop", "1");
			GjfStoreInfo storeInfo = gjfOrderInfoDao.query(GjfStoreInfo.class, attrs);

			// 订单金额
			BigDecimal orderMoney = new BigDecimal(pay_price);
			BigDecimal zore = new BigDecimal(0);
			// 创建订单信息
			GjfOrderInfo order = new GjfOrderInfo();
			order.setAddTime(new Date());
			order.setBenerfitMoney(new BigDecimal(commission));
			order.setCancelReason("");
			order.setCancelTime(null);
			order.setCouponsId(0L);
			order.setDeliveryTime(null);
			order.setEvaluationStatus("0");
			order.setFinishedTime(null);
			order.setGoodsTotalAmount(orderMoney);
			order.setIsDel("1");
			order.setJdOrderSn(trade_id);
			order.setMemberId(memberInfo);
			order.setOfflinePayAmount(zore);
			order.setOnlinePayAmount(orderMoney);
			order.setOrderPostage(zore);

			order.setRemark("积分会在交易成功16天后进行发放");
			order.setOrderSn(DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)));
			if ("taobao".equals(apitype) || apitype == null) {
				if ("1".equals(status)) {
					order.setOrderStatus("0");
				} else if ("4".equals(status)) {
					order.setOrderStatus("5");
				} else if ("3".equals(status)) {
					order.setOrderStatus("3");
				} else if ("2".equals(status)) {
					order.setOrderStatus("2");
				} else {
					order.setOrderStatus("7");
				}
			} else {
				if ("1".equals(status)) {
					order.setOrderStatus("7");
				} else {
					order.setOrderStatus("8");
				}
			}

			order.setOrderTotalAmount(orderMoney);
			order.setOrderType("1");
			order.setPayTime(new Date());
			order.setPayType("9");
			order.setRealPayAmount(orderMoney);
			order.setRefundAmount(zore);
			order.setRefundStatus("0");
			order.setStoreBenefitAmount(orderMoney.multiply(new BigDecimal(0.12)).setScale(2, BigDecimal.ROUND_DOWN));
			order.setStoreRecAmount(orderMoney.multiply(new BigDecimal(0.12)).setScale(2, BigDecimal.ROUND_DOWN));
			order.setSuoceGood("3");
			order.setStoreId(storeInfo);
			order.setTaxMoney(zore);
			order.setToken(Sha256.getSha256Hash(order.getOrderSn(), order.getOrderType(), CommonStatus.SIGN_KEY_NUM));
			gjfOrderInfoDao.save(order);
			// 订单商品信息
			GjfOrderGoods orderGoods = new GjfOrderGoods();
			orderGoods.setGoodsAmount(orderMoney);
			orderGoods.setGoodsName(item_title);
			orderGoods.setOrderId(order);
			orderGoods.setGoodsType("1");
			orderGoods.setGoodsPayAmount(orderMoney);
			orderGoods.setGoodsNum(1L);
			orderGoods.setPromotionsId(0L);
			orderGoods.setStoreBenefitAmount(zore);
			orderGoods.setStoreRecAmount(zore);
			orderGoods.setGoodsImageUrl(projectName + "/common/image/wx/online-shop/proImageNo.jpg");
			gjfOrderInfoDao.save(orderGoods);

			// 查詢用戶消費記錄
			List<GjfMemberConsumptiomNum> list = gjfBenefitInfoDao.findMemberCousumptionNum(Long.valueOf(str));
			// 如果不為空則直接加一
			if (BeanUtil.isValid(list)) {
				GjfMemberConsumptiomNum gjfMemberConsumptiomNum = list.get(0);
				gjfMemberConsumptiomNum.setShopConsumptionNum(gjfMemberConsumptiomNum.getShopConsumptionNum() + 1);
				gjfOrderInfoDao.update(gjfMemberConsumptiomNum);
			} else {
				GjfMemberConsumptiomNum consumptiomNum = new GjfMemberConsumptiomNum();
				consumptiomNum.setBenefitNum(0);
				consumptiomNum.setShopConsumptionNum(1);
				consumptiomNum.setMemberId(Long.valueOf(str));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(new Date());
				ParsePosition ps = new ParsePosition(0);
				consumptiomNum.setAddTime(sdf.parse(date, ps));
				gjfOrderInfoDao.save(consumptiomNum);
			}

		} else {
			if ("taobao".equals(apitype) || apitype == null) {
				if ("1".equals(status)) {
					orderInfo.setOrderStatus("0");
				} else if ("4".equals(status)) {
					String str = uid.substring(4, uid.length());

					// 查詢用戶消費記錄
					List<GjfMemberConsumptiomNum> list = gjfBenefitInfoDao.findMemberCousumptionNum(Long.valueOf(str));
					// 如果不為空則直接加一
					if (BeanUtil.isValid(list)) {
						GjfMemberConsumptiomNum gjfMemberConsumptiomNum = list.get(0);
						gjfMemberConsumptiomNum
								.setShopConsumptionNum(gjfMemberConsumptiomNum.getShopConsumptionNum() - 1);
						gjfOrderInfoDao.update(gjfMemberConsumptiomNum);
					}

					orderInfo.setOrderStatus("5");
				} else if ("3".equals(status)) {
					orderInfo.setOrderStatus("3");
				} else if ("2".equals(status)) {
					orderInfo.setOrderStatus("2");
				} else {
					orderInfo.setOrderStatus("7");
					orderInfo.setFinishedTime(new Date());
				}
			} else {
				if ("1".equals(status)) {
					orderInfo.setOrderStatus("7");
				} else {
					orderInfo.setOrderStatus("8");
				}
			}
			gjfOrderInfoDao.update(orderInfo);
		}
		return new ResultVo(200, "回调成功", null);
	}

	/**
	 * 结算订单积分
	 */
	@Override
	public ResultVo updateOrderBenefit() {
		List<GjfOrderInfo> orList = gjfOrderInfoDao.findSettlementOrder();
		if (BeanUtil.isValid(orList)) {
			for (GjfOrderInfo order : orList) {
				if (order.getBenerfitMoney().doubleValue() > 0) {
					// 确认收货，则计算用户分红权
					gjfBenefitInfoService.updateMemberDividendsNumNotify(order.getMemberId().getMobile(),
							order.getStoreId().getMemberId().getMobile(), order.getBenerfitMoney().doubleValue(),
							order.getOrderSn(), "3");
				}
			}
		}
		return new ResultVo(200, "结算成功", null);
	}

	/**
	 * 根据地址获取商品邮费
	 */
	@Override
	public ResultVo findOrderPos(String goodIds, String goodNums, String addessId) {
		Map<String, Object> resultMap = new HashMap<>();
		BigDecimal pos = new BigDecimal(0);
		List<GjfOrderGoods> orderGoodList = new ArrayList<>();
		if (BeanUtil.isValid(goodIds) && BeanUtil.isValid(goodNums)) {
			String[] goodId = goodIds.split(",");
			String[] goodNum = goodNums.split(",");

			for (int i = 0; i < goodId.length; i++) {
				GjfOrderGoods orderGood = new GjfOrderGoods();
				// 获取商品信息
				Map<String, Object> attrs = new HashMap<>();
				attrs.put("id", Long.valueOf(goodId[i]));
				GjfProductInfo proId = gjfOrderInfoDao.query(GjfProductInfo.class, attrs);
				orderGood.setGoodsId(proId);
				orderGood.setGoodsNum(Long.valueOf(goodNum[i]));
				orderGoodList.add(orderGood);
			}

		}

		String orderSn = DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6));
		String customerSn = "";

		GjfOrderInfo orderInfo = new GjfOrderInfo();
		orderInfo.setOrderSn(orderSn);
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("id", Long.valueOf(addessId));

		GjfMemberAddress address = gjfOrderInfoDao.query(GjfMemberAddress.class, attrs);
		// 请求接口
		if (BeanUtil.isValid(address)) {
			String resutlStr = ProUtil.createOrder(address, orderInfo, orderGoodList);
			com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(resutlStr);
			if (BeanUtil.isValid(jsonObject)) {
				String shipPrice = jsonObject.getString("shipPrice");
				pos = pos.add(new BigDecimal(shipPrice));
				customerSn = jsonObject.getString("order_sn");
			}
		}
		resultMap.put("orderSn", orderSn);
		resultMap.put("customerSn", customerSn);
		resultMap.put("pos", pos);
		return new ResultVo(200, "查询成功", resultMap);
	}

	/**
	 * 扣减分红权
	 * 
	 * @param mobile
	 * @param type
	 * @param consumption
	 */
	public void updateDeductionDivi(String mobile, String type, double consumption, String merchantGrade) {
		// 创建查询条件map
		Map<String, Object> attrs = new HashMap<>();
		// 如果是会员
		if ("0".equals(type)) {
			// 用户电话号码
			attrs.put("mobile", mobile);
			// 获取用户信息
			GjfMemberInfo member = gjfBenefitInfoDao.query(GjfMemberInfo.class, attrs);
			// 如果用户为空返回
			if (!BeanUtil.isValid(member)) {
				return;
			}
			// 用户消费总积分
			BigDecimal totalCumulative = new BigDecimal(0);
			// 如果为一类
			if ("1".equals(merchantGrade)) {
				// 记录一类消费总积分
				totalCumulative = member.getMerchantFirstCumulativeMoney();
			}
			// 如果是二类
			if ("2".equals(merchantGrade)) {
				// 记录二类消费积分
				totalCumulative = member.getMerchantSecondCumulativeMoney();
			}
			// 如果是三类
			if ("3".equals(merchantGrade)) {
				// 记录三类消费积分
				totalCumulative = member.getMerchantThreeCumulativeMoney();
			}

			// 根据用户消费前累计消费金额获取分分红权设置信息
			List<GjfSetDividends> diviList = gjfBenefitInfoDao.findDividendsDate(totalCumulative.doubleValue());
			// 如果数据大于零
			if (diviList.size() > 0) {
				// 记录剩余数
				BigDecimal rewardConmuption = new BigDecimal(consumption);
				// 记录扣减分红权
				BigDecimal diviNum = new BigDecimal(0);
				// 获取设置信息
				GjfSetDividends dividends = diviList.get(0);
				// 如果领回的金额小于设置的最大金额
				if (rewardConmuption.doubleValue() <= dividends.getConsumptionMax().doubleValue()) {
					// 计算分红权
					BigDecimal num = rewardConmuption.divide(dividends.getConsumption()).setScale(6,
							BigDecimal.ROUND_DOWN);
					// 记录分红权
					diviNum = diviNum.add(num);
				} else {
					// 查看设置的区间的最大消费金额小于消费金额的数据个数
					List<GjfSetDividends> divDateSize = gjfBenefitInfoDao
							.findDividendiByCumulativeMoney(totalCumulative.doubleValue(), "1");
					// 如果数据大于零
					if (divDateSize.size() > 0) {
						// 遍历数据
						for (int i = 0; i < divDateSize.size(); i++) {
							// 如果领回的金额大于记录的最大金额
							if (rewardConmuption.doubleValue() > divDateSize.get(i).getConsumptionMax().doubleValue()) {
								// 计算分红权
								BigDecimal num = divDateSize.get(i).getConsumptionMax()
										.divide(divDateSize.get(i).getConsumption()).setScale(6, BigDecimal.ROUND_DOWN);
								// 记录扣减分红权
								diviNum = diviNum.add(num);
								// 减去扣减积分
								rewardConmuption = rewardConmuption.subtract(divDateSize.get(i).getConsumptionMax());
							} else {
								// 计算分红权
								BigDecimal num = rewardConmuption.divide(dividends.getConsumption()).setScale(6,
										BigDecimal.ROUND_DOWN);
								// 记录分红权
								diviNum = diviNum.add(num);
							}
						}
					}
				}

				// 创建扣减记录
				GjfMemberDiviNumHistory deDivi = new GjfMemberDiviNumHistory();
				// 用户id
				deDivi.setMemberId(member.getId());
				// 扣减积分
				deDivi.setDedcutAmount(new BigDecimal(consumption));
				// 扣减分红权
				deDivi.setDeductDiviNum(diviNum);
				// 添加时间
				deDivi.setAddTime(new Date());
				// 用户电话
				deDivi.setMemberName(member.getName());
				// 用户电话号码
				deDivi.setMemberMobile(member.getMobile());
				// 交易状态
				deDivi.setTradeStatus("1");
				// 交易类型
				deDivi.setTradeType(merchantGrade);

				// 如果为一类
				if ("1".equals(merchantGrade)) {
					// 用户扣减前分红权
					deDivi.setMemberDiviNumBf(member.getMerchantFirstDiviNum());
					// 用户扣减分红权
					member.setMerchantFirstDiviNum(member.getMerchantFirstDiviNum().subtract(diviNum));
					// 用户扣减前的分红权
					deDivi.setMemberDiviNumAf(member.getMerchantFirstDiviNum());
					// 记录扣减金
					deDivi.setDedcutDiviTotalMoneyBf(member.getMerchantFirstCousumptionMoney());

				}
				// 如果为一类
				if ("2".equals(merchantGrade)) {
					// 用户扣减前分红权
					deDivi.setMemberDiviNumBf(member.getMerchantSecondDiviNum());
					// 用户扣减分红权
					member.setMerchantSecondDiviNum(member.getMerchantSecondDiviNum().subtract(diviNum));
					// 用户扣减前的分红权
					deDivi.setMemberDiviNumAf(member.getMerchantSecondDiviNum());
					// 记录扣减金
					deDivi.setDedcutDiviTotalMoneyBf(member.getMerchantSecondCousumptionMoney());

				}
				// 如果为一类
				if ("3".equals(merchantGrade)) {
					// 用户扣减前分红权
					deDivi.setMemberDiviNumBf(member.getMerchantThreeDiviNum());
					// 用户扣减分红权
					member.setMerchantThreeDiviNum(member.getMerchantThreeDiviNum().subtract(diviNum));
					// 用户扣减前的分红权
					deDivi.setMemberDiviNumAf(member.getMerchantThreeDiviNum());
					// 记录扣减金
					deDivi.setDedcutDiviTotalMoneyBf(member.getMerchantThreeCousumptionMoney());

				}
				// 保存记录
				gjfBenefitInfoDao.save(deDivi);
				// 更新用户信息
				gjfBenefitInfoDao.update(member);

			}

			// 如果是商户
		} else {
			// 用户电话号码
			attrs.put("mobile", mobile);
			// 获取用户信息
			GjfMemberInfo member = gjfBenefitInfoDao.query(GjfMemberInfo.class, attrs);
			// 如果用户为空返回
			if (!BeanUtil.isValid(member)) {
				return;
			}
			// 清除attrs
			attrs.clear();
			// 用户id
			attrs.put("memberId.id", member.getId());
			// 获取店铺信息
			GjfStoreInfo store = gjfBenefitInfoDao.query(GjfStoreInfo.class, attrs);
			// 如果店铺不存在返回
			if (!BeanUtil.isValid(store)) {
				return;
			}
			// 用户消费总积分
			BigDecimal totalCumulative = store.getStoreDividendsTotalMoney();

			// 根据用户消费前累计消费金额获取分分红权设置信息
			List<GjfSetDividends> diviList = gjfBenefitInfoDao.findDividendsDate(totalCumulative.doubleValue());
			// 如果数据大于零
			if (diviList.size() > 0) {
				// 记录剩余数
				BigDecimal rewardConmuption = new BigDecimal(consumption);
				// 记录扣减分红权
				BigDecimal diviNum = new BigDecimal(0);
				// 获取设置信息
				GjfSetDividends dividends = diviList.get(0);
				// 如果领回的金额小于设置的最大金额
				if (rewardConmuption.doubleValue() <= dividends.getConsumptionMax().doubleValue()) {
					// 计算分红权
					BigDecimal num = rewardConmuption.divide(dividends.getConsumption()).setScale(6,
							BigDecimal.ROUND_DOWN);
					// 记录分红权
					diviNum = diviNum.add(num);
				} else {
					// 查看设置的区间的最大消费金额小于消费金额的数据个数
					List<GjfSetDividends> divDateSize = gjfBenefitInfoDao
							.findDividendiByCumulativeMoney(totalCumulative.doubleValue(), "1");
					// 如果数据大于零
					if (divDateSize.size() > 0) {
						// 遍历数据
						for (int i = 0; i < divDateSize.size(); i++) {
							// 如果领回的金额大于记录的最大金额
							if (rewardConmuption.doubleValue() > divDateSize.get(i).getConsumptionMax().doubleValue()) {
								// 计算分红权
								BigDecimal num = divDateSize.get(i).getConsumptionMax()
										.divide(divDateSize.get(i).getConsumption()).setScale(6, BigDecimal.ROUND_DOWN);
								// 记录扣减分红权
								diviNum = diviNum.add(num);
								// 减去扣减积分
								rewardConmuption = rewardConmuption.subtract(divDateSize.get(i).getConsumptionMax());
							} else {
								// 计算分红权
								BigDecimal num = rewardConmuption.divide(dividends.getConsumption()).setScale(6,
										BigDecimal.ROUND_DOWN);
								// 记录分红权
								diviNum = diviNum.add(num);
							}
						}
					}
				}

				// 创建扣减记录
				GjfMemberDiviNumHistory deDivi = new GjfMemberDiviNumHistory();
				// 用户id
				deDivi.setMemberId(member.getId());
				// 扣减积分
				deDivi.setDedcutAmount(totalCumulative);
				// 扣减分红权
				deDivi.setDeductDiviNum(diviNum);
				// 添加时间
				deDivi.setAddTime(new Date());
				// 用户电话
				deDivi.setMemberName(member.getName());
				// 用户电话号码
				deDivi.setMemberMobile(member.getMobile());
				// 交易状态
				deDivi.setTradeStatus("1");
				// 交易类型
				deDivi.setTradeType(merchantGrade);

				// 用户扣减前分红权
				deDivi.setMemberDiviNumBf(store.getStoreDividendsNum());
				// 用户扣减分红权
				store.setStoreDividendsNum(store.getStoreDividendsNum().subtract(diviNum));
				// 用户扣减前的分红权
				deDivi.setMemberDiviNumAf(store.getStoreDividendsNum());
				// 保存记录
				gjfBenefitInfoDao.save(deDivi);
				// 更新店铺信息
				gjfBenefitInfoDao.update(store);
			}
		}
	}
}
