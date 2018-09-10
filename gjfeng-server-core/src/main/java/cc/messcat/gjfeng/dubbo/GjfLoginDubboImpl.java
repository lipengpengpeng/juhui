package cc.messcat.gjfeng.dubbo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dubbo.core.GjfLoginDubbo;
import cc.messcat.gjfeng.service.member.GjfLoginService;

public class GjfLoginDubboImpl implements GjfLoginDubbo {

	@Autowired
	@Qualifier("gjfLoginService")
	private GjfLoginService gjfLoginService;

	/*
	 * 注册 --微信快捷登陆需要绑定手机号
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfLoginDubbo#register(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.Long)
	 */
	@Override
	public ResultVo register(String account, String password, String nickName, String sex, String imgHeadUrl, String registerType,
		Long superId, String url) {
		return gjfLoginService.register(account, password, nickName, sex, imgHeadUrl, registerType, superId, url);
	}

	/*
	 * 登陆
	 * 
	 * @see cc.messcat.gjfeng.dubbo.core.GjfLoginDubbo#login(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo login(String account, String password, String loginType,String type) {
		return gjfLoginService.login(account, password, loginType,type);
	}

	/*
	 * 忘记密码
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfLoginDubbo#forgetPwd(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo forgetPwd(String account, String password, String rePassword) {
		return gjfLoginService.updateForgetPwd(account, password, rePassword);
	}

	/*
	 * 修改密码
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfLoginDubbo#updatePwd(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo updatePwd(String account, String oldPassword, String newPassword, String rePassword) {
		return gjfLoginService.updatePwd(account, oldPassword, newPassword, rePassword);
	}

	/*
	 * 微信快捷登陆绑定手机号码
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfLoginDubbo#bindMobie(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public ResultVo bindMobie(String unionId, String mobile,String password) {
		return gjfLoginService.updateBindMobie(unionId, mobile,password);
	}

	/*
	 * 设置修改支付密码
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfLoginDubbo#updatePayPwd(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo updatePayPwd(String account, String payPwd, String rePayPwd) {
		return gjfLoginService.updatePayPwd(account, payPwd, rePayPwd);
	}

	@Override
	public ResultVo upateBindMobieInApp(String uid,String Mobile, String password) {		
		return gjfLoginService.upateBindMobieInApp(uid,Mobile,password);
	}

}
