package cc.messcat.gjfeng.dubbo.core;

import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.entity.GjfAttrType;
import cc.messcat.gjfeng.entity.GjfAttrValue;
import cc.messcat.gjfeng.entity.GjfProductAttr;

public interface GjfProductAttrDubbo {
	
	/**
	 * 添加商品属性
	 * @param gjfProductAttr
	 * @return
	 */
	public ResultVo addProductAttr(GjfProductAttr gjfProductAttr);

	/**
	 * 根据商品Id查找商品属性（分页）
	 * @param proId
	 * @return
	 */
	public ResultVo retrieveProductAttrByProId(Long proId,int pageNo, int pageSize);
	
	/**
	 * 根据商品属性Id查找商品属性
	 * @param proId
	 * @return
	 */
	public ResultVo retrieveProductAttrById(Long id);
	
	/**
	 * 删除商品属性
	 * @param gjfProductAttr
	 * @return
	 */
	public ResultVo delProductAttr(GjfProductAttr gjfProductAttr);

	/**
	 * 添加属性值
	 * @param gjfAttrValue
	 * @return
	 */
	public ResultVo addAttrValue(GjfAttrValue gjfAttrValue);

	/**
	 * 修改属性值
	 * @param gjfAttrValue
	 * @return
	 */
	public ResultVo modifyAttrValue(GjfAttrValue gjfAttrValue);

	/**
	 * 修改属性值状态
	 * @param id
	 * @param status
	 * @param token
	 * @return
	 */
	public ResultVo modifyAttrValueStatus(Long id, String status, String token);

	/**
	 * 根据id查找属性值
	 * @param id
	 * @return
	 */
	public ResultVo retrieveAttrValueById(Long id);

	/**
	 * 根据Id和Token查找属性值
	 * @param id
	 * @param token
	 * @return
	 */
	public ResultVo retrieveAttrValueById(Long id, String token);
	
	/**
	 * 根据栏目Id查找属性值
	 * @return
	 */
	public ResultVo retrieveAttrValueByColumnId(Long columnId);

	/**
	 * 根据属性类型id、token查询属性值
	 * @param attrTypeId
	 * @param token
	 * @return
	 */
	public ResultVo retrieveAttrValueByAttrTypeId(Long attrTypeId, String token);

	/**
	 * 查询所有属性值
	 * @return
	 */
	public ResultVo retrieveAllAttrValue(String status);

	/**
	 * 分页查询所有属性值
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public ResultVo retrieveAttrValueByPage(int pageNo, int pageSize);

	/**
	 * 添加属性类型
	 * @param gjfAttrType
	 * @return
	 */
	public ResultVo addAttrType(GjfAttrType gjfAttrType);

	/**
	 * 修改属性类型
	 * @param gjfAttrType
	 * @return
	 */
	public ResultVo modifyAttrType(GjfAttrType gjfAttrType);

	/**
	 * 修改属性类型状态
	 * @param id
	 * @param status
	 * @param token
	 * @return
	 */
	public ResultVo modifyAttrTypeStatus(Long id, String status, String token);

	/**
	 * 根据Id查找属性类型
	 * @param id
	 * @return
	 */
	public ResultVo retrieveAttrTypeById(Long id);

	/**
	 * 根据Id和Token查找属性类型
	 * @param id
	 * @param token
	 * @return
	 */
	public ResultVo retrieveAttrTypeById(Long id, String token);
	
	/**
	 * 查找所有的属性类型
	 * @return
	 */
	public ResultVo retrieveAllAttrType();

	/**
	 * 根据状态查找所有的属性类型
	 * @return
	 */
	public ResultVo retrieveAllAttrTypeByStatus(String status);

	/**
	 * 分页查找属性类型
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public ResultVo retrieveAttrTypeByPage(int pageNo, int pageSize);
	
	/**
	 * @描述 查询商品属性--前端
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param proId
	 * @return
	 */
	public ResultVo findProAttrByProId(Long proId);
	
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
	 * @描述 查询商品属性--app
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param proId
	 * @return
	 */
	public ResultVo findProAttrByProIdInApp(Long proId);
}
