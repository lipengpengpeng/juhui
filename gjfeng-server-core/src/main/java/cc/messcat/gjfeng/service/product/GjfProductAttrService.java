package cc.messcat.gjfeng.service.product;

import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.entity.GjfAttrType;
import cc.messcat.gjfeng.entity.GjfAttrValue;
import cc.messcat.gjfeng.entity.GjfProductAttr;
import cc.messcat.gjfeng.entity.GjfProductAttrStock;

public interface GjfProductAttrService {

	/**
	 * @描述 添加属性类型
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param gjfAttrType
	 * @return
	 */
	public ResultVo addAttrType(GjfAttrType gjfAttrType);
	
	/**
	 * @描述 修改属性类型
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param gjfAttrType
	 * @return
	 */
	public ResultVo updateAttrType(GjfAttrType gjfAttrType);
	
	/**
	 * @描述 修改属性类型状态
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param id
	 * @param status
	 * @param token
	 * @return
	 */
	public ResultVo updateAttrTypeStatus(Long id,String status,String token);
	
	/**
	 * @描述 根据id查询属性类型
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param id
	 * @return
	 */
	public ResultVo findAttrTypeById(Long id);
	
	/**
	 * @描述 根据id、token查询属性类型
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param id
	 * @param token
	 * @return
	 */
	public ResultVo findAttrTypeById(Long id,String token);
	
	/**
	 * @描述 根据状态查询所有的属性类型
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param status
	 * @return
	 */
	public ResultVo findAllAttrTypeByStatus(String status);
	
	/**
	 * 查询所有的属性类型
	 * @return
	 */
	public ResultVo findAllAttrType();
	
	/**
	 * @描述 分页查询所有的属性类型
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public ResultVo findAttrTypeByPage(int pageNo,int pageSize);
	
	/**
	 * @描述 添加属性值
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param gjfAttrType
	 * @return
	 */
	public ResultVo addAttrValue(GjfAttrValue gjfAttrValue);
	
	/**
	 * @描述 修改属性值
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param gjfAttrType
	 * @return
	 */
	public ResultVo updateAttrValue(GjfAttrValue gjfAttrValue);
	
	/**
	 * @描述 修改属性值状态
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param id
	 * @param status
	 * @param token
	 * @return
	 */
	public ResultVo updateAttrValueStatus(Long id,String status,String token);
	
	/**
	 * @描述 根据id查询属性值
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param id
	 * @return
	 */
	public ResultVo findAttrValueById(Long id);
	
	/**
	 * @描述 根据id、token查询属性值
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param id
	 * @param token
	 * @return
	 */
	public ResultVo findAttrValueById(Long id,String token);
	
	/**
	 * @描述 根据属性类型id、token查询属性值
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param attrTypeId
	 * @param attrTypeToken
	 * @return
	 */
	public ResultVo findAttrValueByattrTypeId(Long attrTypeId,String attrTypeToken);
	
	/**
	 * 根据栏目Id查找属性值
	 * @param columnId
	 * @return
	 */
	public ResultVo findAttrValueByColumnId(Long columnId);
	
	/**
	 * @描述 根据状态查询所有的属性值
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param status
	 * @return
	 */
	public ResultVo findAllAttrValueByStatus(String status);
	
	/**
	 * 查询所有的属性值
	 * @return
	 */
	public ResultVo findAllAttrValue();
	
	/**
	 * @描述 分页查询属性值
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public ResultVo findAttrValueByPage(int pageNo,int pageSize);
	
	
	/**
	 * @描述 添加商品属性
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param gjfProductAttr
	 * @return
	 */
	public ResultVo addProductAttr(GjfProductAttr gjfProductAttr);
	
	/**
	 * 查询商品属性
	 * @param id
	 * @return
	 */
	public ResultVo findProAttrById(Long id);
	
	/**
	 * @描述 查询商品属性（分页）
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param proId
	 * @return
	 */
	public ResultVo findProAttrByProId(Long proId,int pageNo, int pageSize);
	
	/**
	 * @描述 查询商品属性
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param proId
	 * @return
	 */
	public ResultVo updateProAttrByProId(Long proId);
	
	/**
	 * @描述 根据商品属性值组合查询商品库存和价格
	 * @author Karhs
	 * @date 2017年1月17日
	 * @param proId
	 * @param attrIds
	 * @return
	 */
	public ResultVo findProAttrStockByAttrId(Long proId,String attrIds);
	
	/**
	 * 删除商品属性
	 * @param gjfProductAttr
	 * @return
	 */
	public ResultVo delProductAttr(GjfProductAttr gjfProductAttr);

	/**
	 * @描述 根据商品库存属性获取商品的属性和属性值
	 * @author Karhs
	 * @date 2017年1月17日
	 * @param attrStock
	 * @return
	 */
	public ResultVo findProAttrByProAttrStock(GjfProductAttrStock attrStock);

	
	/**
	 * @描述 查询商品属性--app
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param proId
	 * @return
	 */
	public ResultVo findProAttrByProIdInApp(Long proId);

	
	
}
