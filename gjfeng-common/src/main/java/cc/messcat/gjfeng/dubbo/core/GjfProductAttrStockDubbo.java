package cc.messcat.gjfeng.dubbo.core;

import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.entity.GjfProductAttrStock;

public interface GjfProductAttrStockDubbo {
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public ResultVo findProductAttrStockById(Long id);
	
	/**
	 * 根据商品Id查询
	 * @param proId
	 * @return
	 */
	public ResultVo findProductAttrStockByProId(Long proId,int pageNo, int pageSize);
	
	/**
	 * 添加
	 * @param gjfProductAttrStock
	 * @return
	 */
	public ResultVo addProductAttrStock(GjfProductAttrStock gjfProductAttrStock);
	
	/**
	 * 更新
	 * @param gjfProductAttrStock
	 * @return
	 */
	public ResultVo updateProductAttrStock(GjfProductAttrStock gjfProductAttrStock);

}
