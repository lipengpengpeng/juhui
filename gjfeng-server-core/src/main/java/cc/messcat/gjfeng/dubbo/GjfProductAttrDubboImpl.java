package cc.messcat.gjfeng.dubbo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dubbo.core.GjfProductAttrDubbo;
import cc.messcat.gjfeng.entity.GjfAttrType;
import cc.messcat.gjfeng.entity.GjfAttrValue;
import cc.messcat.gjfeng.entity.GjfProductAttr;
import cc.messcat.gjfeng.service.product.GjfProductAttrService;

public class GjfProductAttrDubboImpl implements GjfProductAttrDubbo {

	@Autowired
	@Qualifier("gjfProductAttrService")
	private GjfProductAttrService gjfProductAttrService;
	

	@Override
	public ResultVo addProductAttr(GjfProductAttr gjfProductAttr) {
		return gjfProductAttrService.addProductAttr(gjfProductAttr);
	}


	@Override
	public ResultVo retrieveProductAttrByProId(Long proId,int pageNo, int pageSize) {
		return gjfProductAttrService.findProAttrByProId(proId,pageNo,pageSize);
	}
	
	@Override
	public ResultVo retrieveProductAttrById(Long id) {
		return gjfProductAttrService.findProAttrById(id);
	}

	@Override
	public ResultVo addAttrValue(GjfAttrValue gjfAttrValue) {
		return gjfProductAttrService.addAttrValue(gjfAttrValue);
	}


	@Override
	public ResultVo modifyAttrValue(GjfAttrValue gjfAttrValue) {
		return gjfProductAttrService.updateAttrValue(gjfAttrValue);
	}


	@Override
	public ResultVo modifyAttrValueStatus(Long id, String status, String token) {
		return gjfProductAttrService.updateAttrValueStatus(id, status, token);
	}


	@Override
	public ResultVo retrieveAttrValueById(Long id) {
		return gjfProductAttrService.findAttrValueById(id);
	}


	@Override
	public ResultVo retrieveAttrValueById(Long id, String token) {
		return gjfProductAttrService.findAttrValueById(id, token);
	}

	@Override
	public ResultVo retrieveAttrValueByColumnId(Long columnId) {
		return gjfProductAttrService.findAttrValueByColumnId(columnId);
	}

	@Override
	public ResultVo retrieveAttrValueByAttrTypeId(Long attrTypeId, String token) {
		return gjfProductAttrService.findAttrValueByattrTypeId(attrTypeId, token);
	}


	@Override
	public ResultVo retrieveAllAttrValue(String status) {
		return gjfProductAttrService.findAllAttrValueByStatus(status);
	}


	@Override
	public ResultVo retrieveAttrValueByPage(int pageNo, int pageSize) {
		return gjfProductAttrService.findAttrValueByPage(pageNo, pageSize);
	}


	@Override
	public ResultVo addAttrType(GjfAttrType gjfAttrType) {
		return gjfProductAttrService.addAttrType(gjfAttrType);
	}


	@Override
	public ResultVo modifyAttrType(GjfAttrType gjfAttrType) {
		return gjfProductAttrService.updateAttrType(gjfAttrType);
	}


	@Override
	public ResultVo modifyAttrTypeStatus(Long id, String status, String token) {
		return gjfProductAttrService.updateAttrTypeStatus(id, status, token);
	}


	@Override
	public ResultVo retrieveAttrTypeById(Long id) {
		return gjfProductAttrService.findAttrTypeById(id);
	}


	@Override
	public ResultVo retrieveAttrTypeById(Long id, String token) {
		return gjfProductAttrService.findAttrTypeById(id, token);
	}


	@Override
	public ResultVo retrieveAllAttrTypeByStatus(String status) {
		return gjfProductAttrService.findAllAttrTypeByStatus(status);
	}


	@Override
	public ResultVo retrieveAttrTypeByPage(int pageNo, int pageSize) {
		return gjfProductAttrService.findAttrTypeByPage(pageNo, pageSize);
	}


	@Override
	public ResultVo delProductAttr(GjfProductAttr gjfProductAttr) {
		return gjfProductAttrService.delProductAttr(gjfProductAttr);
	}

	/* 
	 *  查询商品属性--前端
	 * @see cc.messcat.gjfeng.dubbo.core.GjfProductAttrDubbo#findProAttrByProId(java.lang.Long)
	 */
	@Override
	public ResultVo findProAttrByProId(Long proId) {
		return gjfProductAttrService.updateProAttrByProId(proId);
	}
	
	@Override
	public ResultVo retrieveAllAttrType() {
		return gjfProductAttrService.findAllAttrType();
	}


	/* 
	 * 根据商品属性值组合查询商品库存和价格
	 * @see cc.messcat.gjfeng.dubbo.core.GjfProductAttrDubbo#findProAttrStockByAttrId(java.lang.Long, java.lang.String)
	 */
	@Override
	public ResultVo findProAttrStockByAttrId(Long proId, String attrIds) {
		return gjfProductAttrService.findProAttrStockByAttrId(proId, attrIds);
	}


	@Override
	public ResultVo findProAttrByProIdInApp(Long proId) {
		// TODO Auto-generated method stub
		return gjfProductAttrService.findProAttrByProIdInApp(proId);
	}


	

}
