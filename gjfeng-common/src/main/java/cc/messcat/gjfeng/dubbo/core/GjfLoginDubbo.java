package cc.messcat.gjfeng.dubbo.core;

import cc.messcat.gjfeng.common.vo.app.ResultVo;

public interface GjfLoginDubbo {

	/**
	 * @描述 注册 --微信快捷登陆需要绑定手机号
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param account
	 *            手机号码注册时是手机号，微信快捷登陆是union
	 * @param password
	 * @param nickName
	 *            输入昵称或微信昵称
	 * @param registerType
	 *            0:手机号码注册 1:微信快捷登陆
	 * @return
	 */
	public ResultVo register(String account, String password, String nickName, String sex, String imgHeadUrl, String registerType,
		Long superId, String url);

	/**
	 * @描述 登陆
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param account
	 * @param password
	 * @param loginType
	 *            0:手机号码登陆 1:微信快捷登陆
	 * @return
	 */
	public ResultVo login(String account, String password, String loginType,String type);

	/**
	 * @描述 忘记密码
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param account
	 * @param password
	 * @param rePassword
	 * @return
	 */
	public ResultVo forgetPwd(String account, String password, String rePassword);

	/**
	 * @描述 修改密码
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param account
	 * @param oldPassword
	 * @param newPassword
	 * @param rePassword
	 * @return
	 */
	public ResultVo updatePwd(String account, String oldPassword, String newPassword, String rePassword);

	/**
	 * @描述 微信快捷登陆绑定手机号码
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param unionId
	 * @param mobile
	 * @return
	 */
	public ResultVo bindMobie(String unionId, String mobile,String password);

	/**
	 * @描述 设置修改支付密码
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param account
	 * @param payPwd
	 * @param rePayPwd
	 * @return
	 */
	public ResultVo updatePayPwd(String account, String payPwd, String rePayPwd);
	
	/**
	 * app微信登录
	 * @param Mobile
	 * @param password
	 * @return
	 */
	public ResultVo upateBindMobieInApp(String uid,String Mobile,String password);
}
