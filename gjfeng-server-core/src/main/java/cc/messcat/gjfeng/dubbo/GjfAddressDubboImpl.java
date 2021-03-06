package cc.messcat.gjfeng.dubbo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dubbo.core.GjfAddressDubbo;
import cc.messcat.gjfeng.service.member.GjfAddressService;

public class GjfAddressDubboImpl implements GjfAddressDubbo {

	@Autowired
	@Qualifier("gjfAddressService")
	private GjfAddressService gjfAddressService;

	/*
	 * 添加收货地址
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfAddressDubbo#addDeliveryAddress(java.lang
	 * .Object[])
	 */
	@Override
	public ResultVo addDeliveryAddress(Object... objs) {
		return gjfAddressService.addDeliveryAddress(objs);
	}

	/*
	 * 修改地址为默认收货地址
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfAddressDubbo#updateAdressIsDefault(java.
	 * lang.Long, java.lang.String)
	 */
	@Override
	public ResultVo updateAdressIsDefault(Long id, String account,String goodSource) {
		return gjfAddressService.updateAdressIsDefault(id, account,goodSource);
	}

	/*
	 * 修改收货地址
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfAddressDubbo#updateAddress(java.lang.
	 * Object[])
	 */
	@Override
	public ResultVo updateAddress(Object... objs) {
		return gjfAddressService.updateAddress(objs);
	}

	/*
	 * 删除收货地址
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfAddressDubbo#delAddress(java.lang.Long,
	 * java.lang.String)
	 */
	@Override
	public ResultVo delAddress(Long id, String account) {
		return gjfAddressService.removeAddress(id, account);
	}

	/*
	 * 查询收货地址根据id
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfAddressDubbo#findAddressById(java.lang.
	 * Long, java.lang.String)
	 */
	@Override
	public ResultVo findAddressById(Long id, String account,String goodSource) {
		return gjfAddressService.findAddressById(id, account,goodSource);
	}

	/*
	 * 查询我的收货地址
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfAddressDubbo#findMyDeliveryAddress(java.
	 * lang.String)
	 */
	@Override
	public ResultVo findMyDeliveryAddress(String account,String goodSource) {
		return gjfAddressService.findMyDeliveryAddress(account,goodSource);
	}
	
	/* 
	 * 查询我的默认收货地址
	 * @see cc.messcat.gjfeng.dubbo.core.GjfAddressDubbo#findMyDefDeliveryAddress(java.lang.String)
	 */
	@Override
	public ResultVo findMyDefDeliveryAddress(String account,String goodSource) {
		return gjfAddressService.findMyDefDeliveryAddress(account, goodSource);
	}

	/*
	 * 查询省市区
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfAddressDubbo#findAddress(java.lang.Long,
	 * java.lang.String)
	 */
	@Override
	public ResultVo findAddress(Long fatherId, String addressType,String goodSource) {
		return gjfAddressService.findAddress(fatherId, addressType,goodSource);
	}

	@Override
	public ResultVo findAddressByLetter(String letter, String type) {
		return gjfAddressService.findAddressByLetter(letter, type);
	}

	/*
	 * 查询所有区域
	 * @see cc.messcat.gjfeng.dubbo.core.GjfAddressDubbo#findAllArea()
	 */
	@Override
	public ResultVo findAllArea() {
		return gjfAddressService.findAllArea();
	}

	/**
	 * ios添加收货地址
	 */
	@Override
	public ResultVo addDeliveryAddressInIos(Object... objs) {
		
		return gjfAddressService.addDeliveryAddressInIos(objs);
	}

	/**
	 * ios修改收货地址
	 */
	@Override
	public ResultVo updateAddressInIos(Object... objs) {
		
		return gjfAddressService.updateAddressInIos(objs);
	}
	
	/**
	 * 添加京东省份信息
	 * 
	 */
	public ResultVo addJdProvince(){
		return gjfAddressService.addJdProvice();
	}

	/**
	 * 添加京东省份下的城市区域信息
	 */
	@Override
	public ResultVo addProvinceLower() {
		
		return gjfAddressService.addJdProviceLowerLevel();
	}

	/**
	 * 添加商品池地址
	 * @return
	 */
	@Override
	public ResultVo addProAddress() {
		
		return gjfAddressService.addProAddress();
	}
}
