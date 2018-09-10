package cc.messcat.gjfeng.dubbo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cc.messcat.gjfeng.common.vo.app.OrderAddVo;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dubbo.core.GjfCartInfoDubbo;
import cc.messcat.gjfeng.service.order.GjfCartInfoService;

public class GjfCartInfoDubboImpl implements GjfCartInfoDubbo {

	@Autowired
	@Qualifier("gjfCartInfoService")
	private GjfCartInfoService gjfCartInfoService;

	/*
	 * 添加购物车
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfCartInfoDubbo#addCart(cc.messcat.gjfeng.
	 * common.vo.app.OrderAddVo, java.lang.String)
	 */
	@Override
	public ResultVo addCart(OrderAddVo orderAddVo, String account) {
		return gjfCartInfoService.addCart(orderAddVo, account);
	}
	
	/*
	 * 修改购物车商品数量
	 * @see cc.messcat.gjfeng.dubbo.core.GjfCartInfoDubbo#updateCartNum(java.lang.Long, java.lang.Long)
	 */
	@Override
	public ResultVo updateCartNum(Long id, Long goodsNum,String account) {
		return gjfCartInfoService.updateCartNum(id, goodsNum,account);
	}

	/*
	 * 删除购物车
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfCartInfoDubbo#delCart(java.lang.Long,
	 * java.lang.String)
	 */
	@Override
	public ResultVo delCart(Long cartId, String account) {
		return gjfCartInfoService.delCart(cartId, account);
	}

	/*
	 * 查询我的购物车
	 * 
	 * @see cc.messcat.gjfeng.dubbo.core.GjfCartInfoDubbo#findMyCart(int, int,
	 * java.lang.String)
	 */
	@Override
	public ResultVo findMyCart(String account) {
		return gjfCartInfoService.findMyCart(account);
	}

	/*
	 * 结算我的购物车
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfCartInfoDubbo#caculateCart(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public ResultVo caculateCart(String account, String cartIds) {
		return gjfCartInfoService.caculateCart(account, cartIds);
	}


}
