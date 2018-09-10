package cc.messcat.gjfeng.service.order;

import cc.messcat.gjfeng.common.vo.app.OrderAddVo;
import cc.messcat.gjfeng.common.vo.app.ResultVo;

public interface GjfCartInfoService {

	/**
	 * @描述 添加购物车
	 * @author Karhs
	 * @date 2017年1月5日
	 * @param orderAddVo
	 * @param account
	 * @return
	 */
	public ResultVo addCart(OrderAddVo orderAddVo,String account);
	
	/**
	 * @描述 修改购物车商品数量
	 * @author Karhs
	 * @date 2017年1月15日
	 * @param id
	 * @param goodsNum
	 * @return
	 */
	public ResultVo updateCartNum(Long id,Long goodsNum,String account);
	
	/**
	 * @描述 删除购物车
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param cartId
	 * @param account
	 * @return
	 */
	public ResultVo delCart(Long cartId,String account);
	
	/**
	 * @描述 查询我的购物车
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param pageNo
	 * @param pageSize
	 * @param account
	 * @return
	 */
	public ResultVo findMyCart(String account);
	
	/**
	 * @描述 结算我的购物车
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param account
	 * @param cartIds
	 * @return
	 */
	public ResultVo caculateCart(String account,String cartIds);
}
