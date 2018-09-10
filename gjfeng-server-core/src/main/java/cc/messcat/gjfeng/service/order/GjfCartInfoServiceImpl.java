package cc.messcat.gjfeng.service.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cc.messcat.gjfeng.common.exception.MyException;
import cc.messcat.gjfeng.common.proprietary.util.ProUtil;
import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.util.DateHelper;
import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.util.RandUtil;
import cc.messcat.gjfeng.common.util.StringUtil;
import cc.messcat.gjfeng.common.vo.app.OrderAddVo;
import cc.messcat.gjfeng.common.vo.app.OrderGoodsVo;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dao.order.GjfOrderInfoDao;
import cc.messcat.gjfeng.dao.product.GjfProductInfoDao;
import cc.messcat.gjfeng.entity.GjfCartInfo;
import cc.messcat.gjfeng.entity.GjfMemberAddress;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.entity.GjfOrderGoods;
import cc.messcat.gjfeng.entity.GjfOrderInfo;
import cc.messcat.gjfeng.entity.GjfProductAttr;
import cc.messcat.gjfeng.entity.GjfProductAttrStock;
import cc.messcat.gjfeng.entity.GjfProductInfo;
import cc.messcat.gjfeng.service.member.GjfMemberInfoService;

@Service("gjfCartInfoService")
public class GjfCartInfoServiceImpl implements GjfCartInfoService {

	@Autowired
	@Qualifier("gjfOrderInfoDao")
	private GjfOrderInfoDao gjfOrderInfoDao;
	
	@Autowired
	@Qualifier("gjfProductInfoDao")
	private GjfProductInfoDao gjfProductInfoDao;


	@Autowired
	@Qualifier("gjfMemberInfoService")
	private GjfMemberInfoService gjfMemberInfoService;

	/*
	 * 添加购物车
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.order.GjfCartInfoService#addCart(cc.messcat.
	 * gjfeng.common.vo.app.OrderAddVo, java.lang.String)
	 */
	@Override
	public ResultVo addCart(OrderAddVo orderAddVo, String account) {
		if (ObjValid.isNotValid(orderAddVo) || ObjValid.isNotValid(orderAddVo.getGoodsId(), orderAddVo.getGoodsNum())
			|| StringUtil.isBlank(account)) {
			throw new MyException(400, "添加购物车失败", null);
		}
		GjfMemberInfo gjfMemberInfo = gjfMemberInfoService.findMember(account);
		if (ObjValid.isNotValid(gjfMemberInfo)) {
			throw new MyException(400, "用户不存在", null);
		}
		GjfCartInfo gjfCartInfo = new GjfCartInfo();
		Object goodsObj = gjfOrderInfoDao.get(orderAddVo.getGoodsId(), GjfProductInfo.class.getName());
		if (ObjValid.isNotValid(gjfMemberInfo)) {
			throw new MyException(400, "商品不存在", null);
		}
		GjfProductInfo prodInfo=(GjfProductInfo) goodsObj;
		GjfProductAttrStock attrStock = null;
		if(!"5".equals(prodInfo.getSuorceGoods())){			
			if (StringUtil.isNotBlank(orderAddVo.getGoodsAttr())) {
				Map<String, Object> attrs = new HashMap<>();
				attrs.put("productId.id", orderAddVo.getGoodsId());
				attrs.put("productAttrIds", orderAddVo.getGoodsAttr().substring(0, orderAddVo.getGoodsAttr().length()));
				attrStock = gjfOrderInfoDao.query(GjfProductAttrStock.class, attrs);
				if (ObjValid.isNotValid(attrStock)) {
					throw new MyException(400, "商品属性不存在", null);
				}
				
				if(attrStock.getRepertory()<=0){
					throw new MyException(400, "商品库存不足", null);
				}
				
				String goodsAttr = "";
				for (String idStr : attrStock.getProductAttrIds().split(",")) {
					Object o = gjfOrderInfoDao.get(Long.valueOf(idStr), GjfProductAttr.class.getName());
					GjfProductAttr attr = (GjfProductAttr) o;
					goodsAttr += attr.getAttrValueId().getAttrId().getAttrName() + ":" + attr.getAttrValueId().getAttrValue() + " ";
				}
				gjfCartInfo.setGoodsAttr(goodsAttr);
				gjfCartInfo.setGoodsAttrStockId(attrStock);
			}
		}
		
		// 判断当前商品是否已经加入购物车，如果已经加入，则只添加购物数量
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("memberId.id", gjfMemberInfo.getId());
		attrs.put("goodsId.id", ((GjfProductInfo) goodsObj).getId());
		if(!"5".equals(prodInfo.getSuorceGoods())){
		   attrs.put("goodsAttrStockId.id", attrStock.getId());
		}
		GjfCartInfo cartInfo = gjfOrderInfoDao.query(GjfCartInfo.class, attrs);
		if (ObjValid.isValid(cartInfo)&&cartInfo.getLogist().equals(orderAddVo.getLogist())) {
			if(orderAddVo.getGoodsNum()==1){
				if(cartInfo.getGoodsNum()==1&&!"1".equals(cartInfo.getGoodsId().getMultipleNumber())){
					cartInfo.setGoodsNum(orderAddVo.getGoodsNum()*Integer.valueOf(cartInfo.getGoodsId().getMultipleNumber()));
				}else if(cartInfo.getGoodsNum()!=1){
					cartInfo.setGoodsNum(cartInfo.getGoodsNum() + orderAddVo.getGoodsNum()*Integer.valueOf(cartInfo.getGoodsId().getMultipleNumber()));
				}else if(cartInfo.getGoodsNum()==1&&"1".equals(cartInfo.getGoodsId().getMultipleNumber())){
					cartInfo.setGoodsNum(cartInfo.getGoodsNum() + orderAddVo.getGoodsNum());
				}
				
			}else{
				cartInfo.setGoodsNum(cartInfo.getGoodsNum() + orderAddVo.getGoodsNum());
			}
			
			return new ResultVo(200, "添加购物车成功", null);
		}
		gjfCartInfo.setGoodsId((GjfProductInfo) goodsObj);
		gjfCartInfo.setGoodsNum(orderAddVo.getGoodsNum());
		gjfCartInfo.setMemberId(gjfMemberInfo);
		gjfCartInfo.setLogist(orderAddVo.getLogist());
		gjfCartInfo.setAddTime(new Date());
		gjfOrderInfoDao.save(gjfCartInfo);
		return new ResultVo(200, "添加购物车成功", null);
	}

	/*
	 * 修改购物车商品数量
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.order.GjfCartInfoService#updateCartNum(java.
	 * lang.Long, java.lang.Long)
	 */
	@Override
	public ResultVo updateCartNum(Long id, Long goodsNum, String account) {
		if (ObjValid.isNotValid(id) || ObjValid.isNotValid(goodsNum) || StringUtil.isBlank(account)) {
			throw new MyException(400, "修改失败", null);
		}
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("id", id);
		attrs.put("memberId.mobile", account);
		GjfCartInfo cartInfo = gjfOrderInfoDao.query(GjfCartInfo.class, attrs);
		if (ObjValid.isNotValid(cartInfo)) {
			throw new MyException(400, "购物车不存在该商品", null);
		}
		//如果选择是快递并商品数量大于限购数量
		if("0".equals(cartInfo.getLogist())&&cartInfo.getGoodsId().getPurchasNum()<goodsNum){
			return new ResultVo(400, "商品数量不能大于商品限购数量", goodsNum);
		}
		//如果选择是快递并商品数量大于限购数量
		if("1".equals(cartInfo.getLogist())&&cartInfo.getGoodsAttrStockId().getRepertory()<goodsNum){
				return new ResultVo(400, "商品库存不足", goodsNum);
		}
		cartInfo.setGoodsNum(goodsNum);
		gjfOrderInfoDao.update(cartInfo);
		return new ResultVo(200, "修改商品数量成功", goodsNum);
	}

	/*
	 * 删除购物车
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.order.GjfCartInfoService#delCart(java.lang.
	 * Long, java.lang.String)
	 */
	@Override
	public ResultVo delCart(Long cartId, String account) {
		if (ObjValid.isNotValid(cartId)) {
			throw new MyException(400, "商品不存在", null);
		}
		if (StringUtil.isBlank(account)) {
			throw new MyException(400, "用户不存在", null);
		}
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("id", cartId);
		attrs.put("memberId.mobile", account);
		GjfCartInfo cartInfo=gjfOrderInfoDao.query(GjfCartInfo.class, attrs);
		gjfOrderInfoDao.delete(cartInfo);
		return new ResultVo(200, "删除成功", null);
	}

	/*
	 * 查询我的购物车
	 * 
	 * @see cc.messcat.gjfeng.service.order.GjfCartInfoService#findMyCart(int,
	 * int, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findMyCart(String account) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("mobile", account);
		GjfMemberInfo memberInfo=gjfOrderInfoDao.query(GjfMemberInfo.class, attrs);
		if(!BeanUtil.isValid(memberInfo)){
			throw new MyException(400, "用户不存在", null);
		}
		List list=gjfProductInfoDao.findAllCartMember(memberInfo.getId());
		List<GjfCartInfo> cartInfo=new ArrayList<>();
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				Map<String, Object> map=(Map<String, Object>) list.get(i);
				GjfCartInfo cart=new GjfCartInfo();				
				cart.setId( Long.valueOf(map.get("id").toString()));
				cart.setGoodsNum(Long.valueOf(map.get("goodsNum").toString()));
				cart.setLogist(map.get("logist").toString());
				
				Map<String, Object> attrGoods = new HashMap<String, Object>();
				attrGoods.put("id", Long.valueOf(map.get("goodsId").toString()));
				GjfProductInfo goodsId=gjfOrderInfoDao.query(GjfProductInfo.class, attrGoods);
				
				if(BeanUtil.isValid(map.get("goodsAttr"))){
					cart.setGoodsAttr(map.get("goodsAttr").toString());
				}else{
					cart.setGoodsAttr("");
				}
				
								
				if(BeanUtil.isValid(goodsId)){
					if(!"5".equals(goodsId.getSuorceGoods())){
						Map<String, Object> attrstock = new HashMap<String, Object>();
						attrstock.put("id", Long.valueOf(map.get("goodsAttrStockId").toString()));
						GjfProductAttrStock goodsAttrStockId=gjfOrderInfoDao.query(GjfProductAttrStock.class, attrstock);
						cart.setGoodsAttrStockId(goodsAttrStockId);	
					}					
					if("0".equals(goodsId.getStatus())||"2".equals(goodsId.getStatus())){
						goodsId.setName("商品已经下架");					
					}
					cart.setGoodsId(goodsId);
				}else{
					GjfProductAttrStock goodsAttrStockId=new GjfProductAttrStock();
					GjfProductInfo good=new GjfProductInfo();
					good.setName("商品已经下架");
					goodsAttrStockId.setPrice(new BigDecimal(0.00));
					cart.setGoodsId(good);
					cart.setGoodsAttrStockId(goodsAttrStockId);
				}
				cartInfo.add(cart);
			}
		}		
		return new ResultVo(200, "查询成功", cartInfo);
	}

	/*
	 * 结算我的购物车
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.order.GjfCartInfoService#caculateCart(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public ResultVo caculateCart(String account, String cartIds) {
		if (StringUtil.isBlank(cartIds) || StringUtil.isBlank(account)) {
			throw new MyException(400, "结算失败，购物车不存在该商品", null);
		}
		String[] stringArr = cartIds.split(",");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<OrderGoodsVo> goodsVos = new ArrayList<OrderGoodsVo>();
		List<OrderAddVo> orderAddVos = new ArrayList<OrderAddVo>();
		//创建商品信息集合
		List<GjfOrderGoods> orderGoodList=new ArrayList<>();
		BigDecimal totalAmount = new BigDecimal(0.00);
		BigDecimal pointNiceAmount = new BigDecimal(0.00);
		int isCanUseCou=0;
		String goodSource="0";
		
		String isWohsalse="0";
		
		//记录物流方式
		String logist="0";
		List<String> logistType=new ArrayList<>();
		
		// 标准版总金额
		BigDecimal standardTotalAmount = new BigDecimal(0.00);
		// 尊享版金额
		BigDecimal honourTotalAmount = new BigDecimal(0.00);
		//记录商品类型
		List<String> goodType=new ArrayList<>();
		//记录商品是否为采购
		List<String> voucherType=new ArrayList<>();
		BigDecimal pos=new BigDecimal(0.00);
		for (String str : stringArr) {
			Object o = gjfOrderInfoDao.get(Long.valueOf(str), GjfCartInfo.class.getName());
			if (ObjValid.isValid(o)) {
				OrderGoodsVo goodsVo = new OrderGoodsVo();
				OrderAddVo orderAddVo = new OrderAddVo();
				GjfCartInfo cartInfo = (GjfCartInfo) o;
				
				GjfOrderGoods orderGood=new GjfOrderGoods();
				//判断购物车商品库存
				if(!"5".equals(cartInfo.getGoodsId().getSuorceGoods())){
					GjfProductAttrStock stock=cartInfo.getGoodsAttrStockId();
					if(stock.getRepertory()<=0||cartInfo.getGoodsNum()>stock.getRepertory()){
						return new ResultVo(400, cartInfo.getGoodsId().getName()+"库存不足", null);
					}
				}
				
				
				if("0".equals(cartInfo.getLogist())&&cartInfo.getGoodsId().getPurchasNum()<cartInfo.getGoodsNum()){
					return new ResultVo(400, cartInfo.getGoodsId().getName()+"商品下单数额大于商品限购数量", null);
				}
				
				//判断商品是否下架
				if("0".equals(cartInfo.getGoodsId().getStatus())||"2".equals(cartInfo.getGoodsId().getStatus())){
					return new ResultVo(400, cartInfo.getGoodsId().getName()+"已经下架", null);
				}
				
				if("1".equals(cartInfo.getGoodsId().getIsCanUserCou())){
					isCanUseCou=1;
				}
				if("2".equals(cartInfo.getGoodsId().getIsCanUserCou())){
					isCanUseCou=2;
				}
				if("3".equals(cartInfo.getGoodsId().getIsCanUserCou())){
					isCanUseCou=3;
				}
				isWohsalse=cartInfo.getGoodsId().getIsWholesale();
				//记录商品类型
				goodType.add(cartInfo.getGoodsId().getIsCanUserCou());
				//记录商品采购状态
				voucherType.add(cartInfo.getGoodsId().getIsWholesale());
				//如果配送方式不同
				logistType.add(cartInfo.getLogist());
				logist=cartInfo.getLogist();
				//记录商品类型
				goodSource=cartInfo.getGoodsId().getSuorceGoods();
				//如果支付方式为快递计算邮费
				if("0".equals(cartInfo.getLogist())){
					pos=pos.add(cartInfo.getGoodsId().getPostage());
				}
				
				if ("1".equals(cartInfo.getGoodsId().getIsCanUserCou()) && cartInfo.getGoodsId().getPointNicePrice() != null) {
					pointNiceAmount = pointNiceAmount.add(cartInfo.getGoodsId().getPointNicePrice().multiply(new BigDecimal(cartInfo.getGoodsNum())));
				}
				if ("2".equals(cartInfo.getGoodsId().getIsCanUserCou()) && cartInfo.getGoodsId().getPointNicePrice() != null) {
					pointNiceAmount = pointNiceAmount.add(cartInfo.getGoodsId().getPointNicePrice().multiply(new BigDecimal(cartInfo.getGoodsNum())));
				}
				if ("3".equals(cartInfo.getGoodsId().getIsCanUserCou()) && cartInfo.getGoodsId().getPointNicePrice() != null) {
					pointNiceAmount = pointNiceAmount.add(cartInfo.getGoodsId().getPointNicePrice().multiply(new BigDecimal(cartInfo.getGoodsNum())));
				}
				//产品池产品
				if(!"5".equals(cartInfo.getGoodsId().getSuorceGoods())){
					goodsVo.setGoodsAmount(cartInfo.getGoodsAttrStockId().getPrice());
					goodsVo.setHonourPrice(cartInfo.getGoodsAttrStockId().getHonourPrice());
					goodsVo.setStandardPrice(cartInfo.getGoodsAttrStockId().getStandardPrice());
					totalAmount=totalAmount.add(cartInfo.getGoodsAttrStockId().getPrice().multiply(new BigDecimal(cartInfo.getGoodsNum())));
					//标准版
					standardTotalAmount=standardTotalAmount.add(cartInfo.getGoodsAttrStockId().getStandardPrice().multiply(new BigDecimal(cartInfo.getGoodsNum())));
					//尊享版
					honourTotalAmount=honourTotalAmount.add(cartInfo.getGoodsAttrStockId().getHonourPrice().multiply(new BigDecimal(cartInfo.getGoodsNum())));
					
					orderAddVo.setGoodsAttrIds(cartInfo.getGoodsAttrStockId().getProductAttrIds());
					orderAddVo.setGoodsAttrStockId(cartInfo.getGoodsAttrStockId().getId());
				}else{
					goodsVo.setGoodsAmount(cartInfo.getGoodsId().getPrice());
					goodsVo.setHonourPrice(cartInfo.getGoodsId().getHonourPrice());
					goodsVo.setStandardPrice(cartInfo.getGoodsId().getStandardPrice());
					totalAmount=totalAmount.add(cartInfo.getGoodsId().getPrice().multiply(new BigDecimal(cartInfo.getGoodsNum())));
					//标准版
					standardTotalAmount=standardTotalAmount.add(cartInfo.getGoodsId().getStandardPrice().multiply(new BigDecimal(cartInfo.getGoodsNum())));
					//尊享版
					honourTotalAmount=honourTotalAmount.add(cartInfo.getGoodsId().getHonourPrice().multiply(new BigDecimal(cartInfo.getGoodsNum())));
					
					orderAddVo.setGoodsAttrIds("");
					//orderAddVo.setGoodsAttrStockId(cartInfo.getGoodsAttrStockId().getId());
				}
				
				goodsVo.setGoodsAttr(cartInfo.getGoodsAttr());
				goodsVo.setGoodsImg(cartInfo.getGoodsId().getImgUrl());
				goodsVo.setGoodsName(cartInfo.getGoodsId().getName());
				goodsVo.setGoodsNum(cartInfo.getGoodsNum());
				goodsVos.add(goodsVo);
				
				orderGood.setGoodsId(cartInfo.getGoodsId());
				orderGood.setGoodsNum(cartInfo.getGoodsNum());
				orderGoodList.add(orderGood);
				// gjfOrderInfoDao.delete(cartInfo);
				
				orderAddVo.setGoodsAttr(cartInfo.getGoodsAttr());
				orderAddVo.setGoodsId(cartInfo.getGoodsId().getId());
				orderAddVo.setGoodsNum(cartInfo.getGoodsNum());
				
				orderAddVos.add(orderAddVo);
			}
		}

		if(goodType.size()>1){
			String type=goodType.get(0);
			for(int i=1;i<goodType.size();i++){
				if(!type.equals(goodType.get(i))){
					return new ResultVo(400, "不同类型商品不能同时结算", null);
				}
			}
		}
		
		if(voucherType.size()>1){
			String type=voucherType.get(0);
			for(int i=1;i<voucherType.size();i++){
				if(!type.equals(voucherType.get(i))){
					return new ResultVo(400, "不同类型商品不能同时结算", null);
				}
			}
		}
		
		if(logistType.size()>1){
			String type=logistType.get(0);
			for(int i=1;i<logistType.size();i++){
				if(!type.equals(logistType.get(i))){
					return new ResultVo(400, "不同配送方式不能同时下单", null);
				}
			}
		}
		
		//商品池产品结算
		String orderSn=DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6));
		String customerSn="";
		if("5".equals(goodSource)){
			GjfOrderInfo orderInfo=new GjfOrderInfo();
			orderInfo.setOrderSn(orderSn);
			Map<String, Object> attrs = new HashMap<String, Object>();
			attrs.put("memberId.mobile", account);
			attrs.put("isDefault", "1");
			if (BeanUtil.isValid(goodSource)) {
				attrs.put("addressSouce", goodSource);
			}
			GjfMemberAddress address = gjfOrderInfoDao.query(GjfMemberAddress.class, attrs);
			if (!BeanUtil.isValid(address)) {
				attrs.remove("isDefault");
				
				List<GjfMemberAddress> addresses=gjfOrderInfoDao.queryList(GjfMemberAddress.class, "id", "asc", attrs);
				if(BeanUtil.isValid(addresses)){
					address = addresses.get(0);
				}
			}
			
			//请求接口
			if(BeanUtil.isValid(address)){
				String resutlStr=ProUtil.createOrder(address, orderInfo, orderGoodList);
				com.alibaba.fastjson.JSONObject jsonObject=com.alibaba.fastjson.JSONObject.parseObject(resutlStr);
				if(BeanUtil.isValid(jsonObject)){
					String shipPrice=jsonObject.getString("shipPrice");				
					pos=pos.add(new BigDecimal(shipPrice));
					customerSn=jsonObject.getString("order_sn");
				}
			}
		}
		
		dataMap.put("orderSn", orderSn);
		dataMap.put("customerSn", customerSn);
		dataMap.put("goodsVos", goodsVos);
		dataMap.put("orderAddVos", orderAddVos);
		dataMap.put("totalAmount", totalAmount);
		dataMap.put("standardTotalAmount", standardTotalAmount);
		dataMap.put("honourTotalAmount", honourTotalAmount);
		dataMap.put("isCanUseCou", isCanUseCou);
		dataMap.put("goodSource", goodSource);
		dataMap.put("pos", pos);
		dataMap.put("logist", logist);
		dataMap.put("isWohsalse", isWohsalse);
		dataMap.put("pointNiceAmount", pointNiceAmount.add(pos));
		dataMap.put("honourPreferentialMoney", standardTotalAmount.subtract(honourTotalAmount));
		dataMap.put("standardPreferentialMoney", totalAmount.subtract(standardTotalAmount));
		return new ResultVo(200, "操作成功", dataMap);
	}

}
