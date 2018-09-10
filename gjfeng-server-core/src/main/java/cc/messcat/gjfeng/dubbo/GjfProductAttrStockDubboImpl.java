package cc.messcat.gjfeng.dubbo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dubbo.core.GjfProductAttrStockDubbo;
import cc.messcat.gjfeng.entity.GjfProductAttrStock;
import cc.messcat.gjfeng.service.product.GjfProductAttrStockService;

public class GjfProductAttrStockDubboImpl implements GjfProductAttrStockDubbo {
	
	@Autowired
	@Qualifier("gjfProductAttrStockService")
	private GjfProductAttrStockService gjfProductAttrStockService;
	
	@Override
	public ResultVo findProductAttrStockById(Long id) {
		return gjfProductAttrStockService.findProductAttrStockById(id);
	}
	
	@Override
	public ResultVo findProductAttrStockByProId(Long proId,int pageNo, int pageSize) {
		return gjfProductAttrStockService.findProductAttrStockByProId(proId,pageNo,pageSize);
	}

	@Override
	public ResultVo addProductAttrStock(GjfProductAttrStock gjfProductAttrStock) {
		return gjfProductAttrStockService.addProductAttrStock(gjfProductAttrStock);
	}

	@Override
	public ResultVo updateProductAttrStock(GjfProductAttrStock gjfProductAttrStock) {
		return gjfProductAttrStockService.updateProductAttrStock(gjfProductAttrStock);
	}

	

}
