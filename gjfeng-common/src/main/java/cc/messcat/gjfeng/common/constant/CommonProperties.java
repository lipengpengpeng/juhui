package cc.messcat.gjfeng.common.constant;

import cc.messcat.gjfeng.common.util.PropertiesFileReader;

public class CommonProperties {
	
	public static final String CONF_PROPERTIES= "/conf.properties";
	public static final String UPLOAD_PROPERTIES= "/upload.properties";
	
	public static final String MNS_PROPERTIES= "/aliyun-mns.properties";

	/**Email*/
	//email host
	public static String EMAIL_HOST = PropertiesFileReader.get("gjfeng.mail.host", CONF_PROPERTIES);
	
	//email port
	public static String EMAIL_PORT = PropertiesFileReader.get("gjfeng.mail.port", CONF_PROPERTIES);
	
	//email account
	public static String EMAIL_ACCOUNT = PropertiesFileReader.get("gjfeng.mail.account", CONF_PROPERTIES);
	
	//email password
	public static String EMAIL_PWD = PropertiesFileReader.get("gjfeng.mail.pwd", CONF_PROPERTIES);
	
	
	
	/**SMS*/
	//sms host
	public static String SMS_HOST = PropertiesFileReader.get("gjfeng.sms.host", CONF_PROPERTIES);
	
	//sms account
	public static String SMS_ACCOUNT = PropertiesFileReader.get("gjfeng.sms.account", CONF_PROPERTIES);
	
	//sms port
	public static String SMS_PWD = PropertiesFileReader.get("gjfeng.sms.pwd", CONF_PROPERTIES);
	
	//sms charset
	public static String SMS_CHARSET = PropertiesFileReader.get("gjfeng.sms.charset", CONF_PROPERTIES);
	
	//sms contentType
	public static String SMS_CONTENTTYPE = PropertiesFileReader.get("gjfeng.sms.contentType", CONF_PROPERTIES);
	
	/**
	 * 阿里大于
	 */
	
	public static final String DY_SMS_ACCESSKEYID = PropertiesFileReader.get("gjfeng.dysms.accessKeyId", CONF_PROPERTIES);
	
	public static final String DY_SMS_ACCESSKEYSECRET = PropertiesFileReader.get("gjfeng.dysms.accessKeySecret", CONF_PROPERTIES);
	
	public static final String DY_SMS_SIGNNAME = PropertiesFileReader.get("gjfeng.dysms.signName", CONF_PROPERTIES);
	
	//验证码模板code
	public static final String DY_SMS_CODETEMPLATE = PropertiesFileReader.get("gjfeng.dysms.codeTemplate", CONF_PROPERTIES);
	
	/**
	 * MNS
	 */
	public static final String MNS_ACCESSKEYID = PropertiesFileReader.get("aliyun.mns.AccessKeyID", MNS_PROPERTIES);
	
	public static final String MNS_ACCESSKEYSECRET = PropertiesFileReader.get("aliyun.mns.AccessKeySecret", MNS_PROPERTIES);
	
	public static final String MNS_ENDPOINT = PropertiesFileReader.get("aliyun.mns.Endpoint", MNS_PROPERTIES);
	
	public static final String MNS_SMSTOPIC = PropertiesFileReader.get("aliyun.mns.smsTopic", MNS_PROPERTIES);
	
	public static final String MNS_SIGNNAME = PropertiesFileReader.get("aliyun.mns.signName", MNS_PROPERTIES);
	
	/**
	 * #凤凰云商短信模板
	 */
	public static final String MNS_TEMPLATE_CODE = PropertiesFileReader.get("aliyun.mns.template.code", MNS_PROPERTIES);
	
	public static final String MNS_TEMPLATE_ADUIT = PropertiesFileReader.get("aliyun.mns.template.aduitStatus", MNS_PROPERTIES);
	
	public static final String MNS_TEMPLATE_DRAWCASH_FAIL = PropertiesFileReader.get("aliyun.mns.template.drawCash.fail", MNS_PROPERTIES);
	
	public static final String MNS_TEMPLATE_CUSTOMER_SERVICE = PropertiesFileReader.get("aliyun.mns.template.customer.service", MNS_PROPERTIES);
	
	public static final String MNS_TEMPLATE_DRAWCASH_SUCC = PropertiesFileReader.get("aliyun.mns.template.drawCash.success", MNS_PROPERTIES);
	
	public static final String MNS_TEMPLATE_IDCARD_SUCC = PropertiesFileReader.get("aliyun.mns.template.idCard.success", MNS_PROPERTIES);
	
	public static final String MNS_TEMPLATE_IDCARD_FAIL = PropertiesFileReader.get("aliyun.mns.template.idCard.fail", MNS_PROPERTIES);
	
	public static final String MNS_QUEUE_SMS = PropertiesFileReader.get("aliyun.mns.queue.sms", MNS_PROPERTIES);
	
	/**
	 * 微信配置
	 */
	//server url
	public static String CLIENT_PROJECT_URL = PropertiesFileReader.get("gjfeng.client.project.url", CONF_PROPERTIES);
	public static String GJFENG_APP_ID = PropertiesFileReader.get("gjfeng.wechat.appId", CONF_PROPERTIES);
	public static String GJFENG_APPSECRET = PropertiesFileReader.get("gjfeng.wechat.appsecret", CONF_PROPERTIES);
	public static String GJFENG_PARTNER = PropertiesFileReader.get("gjfeng.wechat.partner", CONF_PROPERTIES);
	public static String GJFENG_PARTNER_KEY = PropertiesFileReader.get("gjfeng.wechat.partnerkey", CONF_PROPERTIES);
	public static String GJFENG_PAY_ORDER_NOTIFY = PropertiesFileReader.get("gjfeng.wechat.pay.order.notify.url", CONF_PROPERTIES);					//下单支付回调链接
	public static String GJFENG_PAY_BUY_DIVI_NOTIFY = PropertiesFileReader.get("gjfeng.wechat.pay.buy.divi.notify.url", CONF_PROPERTIES);			//商家购买分红权支付回调链接
	public static String GJFENG_PAY_BENEFIT_NOTIFY = PropertiesFileReader.get("gjfeng.wechat.pay.benefit.notify.url", CONF_PROPERTIES);				//商家让利支付回调链接
	public static String GJFENG_PAY_TRADE_NOTIFY = PropertiesFileReader.get("gjfeng.wechat.pay.trade.notify.url", CONF_PROPERTIES);				//商家让利支付回调链接
	
	public static String GJFENG_NOTICE_TEMPLATE = PropertiesFileReader.get("gjfeng.wechat.template.notice", CONF_PROPERTIES);						//消息通知模板
	
	
	
	
	/**上传*/
	//APP用户头像
	public static String UPLOAD_HEAD_PATH = PropertiesFileReader.get("gjfeng.upload.head.path", UPLOAD_PROPERTIES);
	
	//APP反馈图片
	public static String UPLOAD_FEEDBACK_PATH = PropertiesFileReader.get("gjfeng.upload.feedback.path", UPLOAD_PROPERTIES);

	/**
	 * 项目域名
	 */
	public static String GJFENG_MCCMS_PROJECT_URL =  PropertiesFileReader.get("gjfeng.mccms.project.url", CONF_PROPERTIES);
	
	public static String GJFENG_CLIENT_PROJECT_URL =  PropertiesFileReader.get("gjfeng.client.project.url", CONF_PROPERTIES);
	
	public static String GJFENG_CORE_PROJECT_URL =  PropertiesFileReader.get("gjfeng.core.project.url", CONF_PROPERTIES);
	
	public static String GJFENG_BENEFIT_PROJECT_URL =  PropertiesFileReader.get("gjfeng.benefit.project.url", CONF_PROPERTIES);
	
	public static String GJFENG_PRODUCT_PROJECT_URL =  PropertiesFileReader.get("gjfeng.product.project.url", CONF_PROPERTIES);

	/**
	 * H5支付配置
	 */
	//server url
	public static String GJFENG_H5_MERID = PropertiesFileReader.get("gjfeng.H5.pay.merId", CONF_PROPERTIES);
	public static String GJFENG_H5_MERURL = PropertiesFileReader.get("gjfeng.H5.pay.merUrl", CONF_PROPERTIES);	
	public static String GJFENG_H5_KEY = PropertiesFileReader.get("gjfeng.H5.pay.key", CONF_PROPERTIES);
	
	/**
	 * w微信
	 */
	
	public static String GJFENG_WEIXIN_MCH_ID = PropertiesFileReader.get("gjfeng.weiXin.pay.mch_id", CONF_PROPERTIES);
	public static String GJFENG_WEIXIN_KEY = PropertiesFileReader.get("gjfeng.weiXin.pay.key", CONF_PROPERTIES);
	
	/**
	 * 回调
	 */
	public static String GJFENG_WEIXIN_NOTIFY_ORDER = PropertiesFileReader.get("gjfeng.weiXin.pay.notify_url_order", CONF_PROPERTIES);
	public static String GJFENG_WEIXIN_NOTIFY_SHOUXIN= PropertiesFileReader.get("gjfeng.weiXin.pay.notify_url_shouxin", CONF_PROPERTIES);
	public static String GJFENG_WEIXIN_NOTIFY_BENETI = PropertiesFileReader.get("gjfeng.weiXin.pay.notify_url_benetin", CONF_PROPERTIES);
	
	/**
	 * 支付后跳转
	 */
	public static String GJFENG_WEIXIN_CALLBACK_URL_ORDER = PropertiesFileReader.get("gjfeng.weiXin.pay.callback_url_order", CONF_PROPERTIES);
	public static String GJFENG_WEIXIN_CALLBACK_URL_SHOUXIN= PropertiesFileReader.get("gjfeng.weiXin.pay.callback_url_shouxin", CONF_PROPERTIES);
	public static String GJFENG_WEIXIN_CALLBACK_URL_BENETI = PropertiesFileReader.get("gjfeng.weiXin.pay.callback_url_benetin", CONF_PROPERTIES);
	
	/**
	 * h5回调
	 */
	public static String GJFENG_H5_NOTIFY_ORDER = PropertiesFileReader.get("gjfeng.H5.pay.retUrl_order", CONF_PROPERTIES);
	public static String GJFENG_H5_NOTIFY_SHOUXIN= PropertiesFileReader.get("gjfeng.H5.pay.retUrl_shouxin", CONF_PROPERTIES);
	public static String GJFENG_H5_NOTIFY_BENETI = PropertiesFileReader.get("gjfeng.H5.pay.retUrl_benetin", CONF_PROPERTIES);
	
	/**
	 * 微信扫码支付
	 */
	public static String GJFENG_SWEEP_NOTIFY_ORDER = PropertiesFileReader.get("gjfeng.sweep.pay.notify_url_order", CONF_PROPERTIES);
	
	/**
	 * 微信原声js支付回调
	 */
	public static String GJFENG_WEIXIN_JSPAY_NOTIFY_URL_ORDER=PropertiesFileReader.get("gjfneg.weiXin.JSpay.notify_url_order", CONF_PROPERTIES);
	public static String GJFENG_WEIXIN_JSPAY_NOTIFY_URL_SHOUXIN=PropertiesFileReader.get("gjfneg.weiXin.JSpay.notify_url_shouxin", CONF_PROPERTIES);
	public static String GJFENG_WEIXIN_JSPAY_NOTIFY_URL_BENEFIT=PropertiesFileReader.get("gjfneg.weiXin.JSpay.notify_url_benetin", CONF_PROPERTIES);
	public static String GJFENG_WEIXIN_JSPAY_NOTIFY_URL_MERCAHNET_RECHARGE=PropertiesFileReader.get("gjfneg.weiXin.JSpay.notify_url_merchantRecharge", CONF_PROPERTIES);
	public static String GJFENG_WEIXIN_JSPAY_NOTIFY_URL_VOUCHERS=PropertiesFileReader.get("gjfneg.weiXin.JSpay.notify_url_vouchers", CONF_PROPERTIES);
	public static String GJFENG_WEIXIN_JSPAY_NOTIFY_URL_MERCHANT_GIVE=PropertiesFileReader.get("gjfneg.weiXin.JSpay.notify_url_merchantGive", CONF_PROPERTIES);

	/**
	 * 支付宝支付回调
	 */
	public static String GJFENG_APLY_PAY_NOTIFY_URL_ORDER=PropertiesFileReader.get("gjfeng.aply.pay.notify_url_order", CONF_PROPERTIES);
	public static String GJFENG_APLY_PAY_NOTIFY_URL_SHOUXIN=PropertiesFileReader.get("gjfeng.aply.pay.notify_url_shouxin", CONF_PROPERTIES);
	public static String GJFENG_APLY_PAY_NOTIFY_URL_BENEFIT=PropertiesFileReader.get("gjfeng.aply.pay.notify_url_benetin", CONF_PROPERTIES);
	public static String GJFENG_APLY_PAY_NOTIFY_URL_MERCAHNET_RECHARGE=PropertiesFileReader.get("gjfeng.aply.pay.notify_url_merchantRecharge", CONF_PROPERTIES);
	public static String GJFENG_APLY_PAY_NOTIFY_URL_VOUCHERS=PropertiesFileReader.get("gjfeng.aply.pay.notify_url_vouchers", CONF_PROPERTIES);
	public static String GJFENG_APLY_PAY_NOTIFY_URL_MERCHANT_GIVE=PropertiesFileReader.get("gjfeng.aply.pay.notify_url_merchantGive", CONF_PROPERTIES);
	
	/**
	 * 网银快捷支付
	 */
	public static String GJFENG_WANGYIN_PAY_NOTIFY_URL_SHOUXIN=PropertiesFileReader.get("gjfeng.wangli.pay.notify_url_shouxin", CONF_PROPERTIES);
	public static String GJFENG_WANGYIN_PAY_NOTIFY_URL_BENERFIT=PropertiesFileReader.get("gjfeng.wangli.pay.notify_url_benerfit", CONF_PROPERTIES);
	public static String GJFENG_WANGYIN_PAY_NOTIFY_URL_ORDER=PropertiesFileReader.get("gjfeng.wangli.pay.notify_url_order", CONF_PROPERTIES);
	
	/**
	 * 代付
	 */
	public static String GJFENG_PAID_PAY_NOTIFY_URL=PropertiesFileReader.get("gjfeng.paid.pay.notify_url", CONF_PROPERTIES);
	

	/**
	 * 微信证书
	 */
	public static String GJFENG_WEIXIN_REFUND_KEY_STORE_NAME=PropertiesFileReader.get("gjfeng.weixin.refund.key_store_name", CONF_PROPERTIES);
	public static String GJFENG_WEIXIN_REFUND_KEY_STORE_FILEPATH=PropertiesFileReader.get("gjfeng.weixin.refund.key_store_filepath", CONF_PROPERTIES);
	

}
