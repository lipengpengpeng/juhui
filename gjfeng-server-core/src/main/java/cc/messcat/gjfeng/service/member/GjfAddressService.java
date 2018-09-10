package cc.messcat.gjfeng.service.member;

import cc.messcat.gjfeng.common.vo.app.ResultVo;

public interface GjfAddressService {

	/**
	 * @描述 添加收货地址
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param objs
	 * @return
	 */
	public ResultVo addDeliveryAddress(Object... objs);
	
	/**
	 * @描述 修改地址为默认收货地址
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param id
	 * @return
	 */
	public ResultVo updateAdressIsDefault(Long id,String account,String goodSource);
	
	/**
	 * @描述 修改收货地址
	 * @author Karhs
	 * @date 2017年1月9日
	 * @param objs
	 * @return
	 */
	public ResultVo updateAddress(Object... objs);
	
	/**
	 * @描述 删除收货地址
	 * @author Karhs
	 * @date 2017年1月9日
	 * @param id
	 * @param account
	 * @return
	 */
	public ResultVo removeAddress(Long id,String account);
	
	/**
	 * @描述 查询收货地址根据id
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param id
	 * @return
	 */
	public ResultVo findAddressById(Long id,String account,String goodSource);
	
	/**
	 * @描述 查询我的收货地址
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param account
	 * @return
	 */
	public ResultVo findMyDeliveryAddress(String account,String goodSource);
	
	/**
	 * @描述 查询我的默认收货地址
	 * @author Karhs
	 * @date 2017年1月14日
	 * @param account
	 * @return
	 */
	public ResultVo findMyDefDeliveryAddress(String account,String goodSource);
	
	/**
	 * @描述 查询省市区
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param fatherId 查询省份时为空
	 * @param addressType 1:查询省份 2:查询城市 3:查询区县
	 * @return
	 */
	public ResultVo findAddress(Long fatherId,String addressType,String goodSource);
	
	/**
	 * 根据字母获取省市区
	 * @param letter
	 * @param type
	 * @return
	 */
	public ResultVo findAddressByLetter(String letter,String type);
	
	/**
	 * @描述 根据区域编号获取城市id
	 * @author Karhs
	 * @date 2017年2月3日
	 * @param areaCode
	 * @return
	 */
	public ResultVo findCityIdByAreaCode(Long areaCode);
	
	/**
	 * 根据省市区的编号获取城市信息
	 * @param code
	 * @param type
	 * @return
	 */
	public ResultVo findAddressByCode(Long code,String type);
	
	/**
	 * 查询所有地区
	 * @return
	 */
	public ResultVo findAllArea();
	
	
	/**
	 * @描述 添加收货地址ios
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param objs
	 * @return
	 */
	public ResultVo addDeliveryAddressInIos(Object... objs);
	
	
	/**
	 * @描述 修改收货地址ios
	 * @author Karhs
	 * @date 2017年1月9日
	 * @param objs
	 * @return
	 */
	public ResultVo updateAddressInIos(Object... objs);
	
	/**
	 * 添加京东省信息
	 * @return
	 */
	public ResultVo addJdProvice();
	
	/**
	 * 
	 * @param proviceId
	 * @return
	 */
	public ResultVo addJdProviceLowerLevel();
	
	/**
	 * 添加商品池地址
	 * @return
	 */
	public ResultVo addProAddress();
}
