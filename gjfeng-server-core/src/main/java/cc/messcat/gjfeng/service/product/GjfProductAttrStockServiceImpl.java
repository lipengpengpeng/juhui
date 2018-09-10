package cc.messcat.gjfeng.service.product;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cc.messcat.gjfeng.common.bean.Pager;
import cc.messcat.gjfeng.common.exception.MyException;
import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dao.product.GjfProductAttrStockDao;
import cc.messcat.gjfeng.entity.GjfProductAttrStock;

@Service("gjfProductAttrStockService")
public class GjfProductAttrStockServiceImpl implements GjfProductAttrStockService {

	@Autowired
	@Qualifier("gjfProductAttrStockDao")
	private GjfProductAttrStockDao gjfProductAttrStockDao;

	@Autowired
	@Qualifier("gjfProductInfoService")
	private GjfProductInfoService gjfProductInfoService;
	
	/*
	 * 根据Id查询
	 * @see cc.messcat.gjfeng.service.product.GjfProductAttrStockService#findProductAttrStockById(java.lang.Long)
	 */
	@Override
	public ResultVo findProductAttrStockById(Long id) {
		if (ObjValid.isNotValid(id)) {
			return new ResultVo(400, "查询失败", null);
		}
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("id", id);
		return new ResultVo(200,"查询成功",gjfProductAttrStockDao.query(GjfProductAttrStock.class, attrs));
	}
	
	/*
	 * 根据商品Id查询(分页)
	 * @see cc.messcat.gjfeng.service.product.GjfProductAttrStockService#findProductAttrStockByProId(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findProductAttrStockByProId(Long proId,int pageNo, int pageSize) {
		if (ObjValid.isNotValid(proId)) {
			return new ResultVo(400, "查询失败", null);
		}
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("productId.id", proId);
		Pager pager = new Pager(pageSize, pageNo,
				Integer.parseInt(String.valueOf(gjfProductAttrStockDao.queryTotalRecord(GjfProductAttrStock.class, attrs))),
				gjfProductAttrStockDao.queryList(GjfProductAttrStock.class,(pageNo-1)*pageSize,pageSize, "id", "asc", attrs));
		return new ResultVo(200,"查询成功",pager);
	}

	/*
	 * 添加
	 * @see cc.messcat.gjfeng.service.product.GjfProductAttrStockService#addProductAttrStock(cc.messcat.gjfeng.entity.GjfProductAttrStock)
	 */
	@Override
	public ResultVo addProductAttrStock(GjfProductAttrStock gjfProductAttrStock) {
		if (ObjValid.isNotValid(gjfProductAttrStock.getProductId()) 
			|| ObjValid.isNotValid(gjfProductAttrStock.getPrice())
			|| ObjValid.isNotValid(gjfProductAttrStock.getRepertory())
			|| ObjValid.isNotValid(gjfProductAttrStock.getAddTime())
			|| ObjValid.isNotValid(gjfProductAttrStock.getStatus())) {
			throw new MyException(400, "商品属性参数错误", null);
		}
		Object o  = gjfProductInfoService.findProductById(gjfProductAttrStock.getProductId().getId());
		if (ObjValid.isNotValid(o)) {
			throw new MyException(400, "商品不存在", null);
		}
		gjfProductAttrStockDao.save(gjfProductAttrStock);
		return new ResultVo(200,"保存成功",null);
	}
	
	/*
	 * 修改
	 */
	@Override
	public ResultVo updateProductAttrStock(GjfProductAttrStock gjfProductAttrStock) {
		if (ObjValid.isNotValid(gjfProductAttrStock)) {
				throw new MyException(400, "参数错误", null);
			}
		gjfProductAttrStockDao.updateObj(gjfProductAttrStock);
		return new ResultVo(200,"保存成功",null);
	}
	
	
}
