package com.alipay.direct.config;

import com.alipay.direct.util.UtilDate;

//import cc.messcat.entity.Payment;
//import cc.messcat.service.payment.PaymentManagerDao;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {

	// ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	//天天易购
	public static String partner = "2088031561215589";
	//凤凰云商
	//public static String partner = "2088721341409564";
	//楚鸿
	//public static String partner = "2088921923034117";
	
	// 商户的私钥
	public static String key = "DZS6Ws5aJ0hBsMiRJXMOhQ==";
	//凤凰云商
	//public static String key = "8go733porpep0lyr1f5r3zyq1cl0szyw";
	//楚鸿
	//public static String key = "CRUNEuCUVMqpMu6kg1UMTQ==";

	// ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\text\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";

	// 签名方式 不需修改
	public static String sign_type = "MD5";
	
	public static String sign_type_web = "RSA2";

	//天天易购
	public static String seller_mail = "gztiantianyigou@163.com";// public@mymiracle.com.cn
	//凤凰云商
	//public static String seller_mail = "fh4009019517@163.com";
	//楚鸿
	//public static String seller_mail = "fh4009019517@163.com";

	public static String notify_url = "http://app1.ys69.cn/return_url.jsp";

	public static String return_url = "http://app1.ys69.cn/return_url.jsp";

	public static String payment_type = "1";

	public static String alipay_services = "alipay.wap.create.direct.pay.by.user";// create_partner_trade_by_buyer
	

	public static String show_url = "http://app1.ys69.cn/return_url.jsp";

	//网页支付参数
	public static String notify_url_inweb = "http://app1.ys69.cn/alipay.notify.do";

	public static String return_url_inweb = "http://localhost:8180/gjfeng-web-client/pc/merchant/goSpecifiedMerchantOnilne";
	public static String alipay_services_inweb = "create_direct_pay_by_user";// 
//	public static String alipay_services_web = "create_direct_pay_by_user";
	// 手机app支付参数
	//凤凰云商
	//public static final String APPID = "2017062707581858";//2016070601586081
	//天天易购
	public static final String APPID = "2018032202424514";//2016070601586081
	//楚鸿
	//public static final String APPID = "2018041202543636";
	
	public static final String SERVICE = "mobile.securitypay.pay";
	public static final String METHOD = "alipay.trade.app.pay";
	public static final String TIMEOUT_EXPRESS = "30m";
    public static final String PRODUCT_CODE="QUICK_MSECURITY_PAY";
    public static final String APP_NOTITY_URL="http://app1.ys69.cn/alipay.notify.do";
    public static final String SING_TYPE="RSA2";
    public static final String VERSION="1.0";
    
    ///天天易购	
	public static final String RSA_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCaVGO3/bUk/b+L76w1CHwEtrXvQVD2Cf90q6FfCRM0sLEjsYuf1n8x2sqlZ3M0LpNtbXJDJ/tuKkoKpGm9r9SuVFPtwdxRlZvSYdaukfaPfTDBJxPSDUPG1nDB6ZDzdKaJiSDC8hwIghZLgKWSJonP/Yaukk3Nx0HS9UmdjwWeNDiUeVf1uD/xvjr56pU6iRi/1Q2RfKZ0tEGZP6TN0zCkRr26mGiDQVT3D8cBE/p/ST4if7wagvba5q1jUSo8xgUh8UM9kVTaTpdoYgA9da6B9XSh2jGBJuMOi87By3acAlzjM0HLRU1tumy3R/0z2hkmVs+glpexqnbNPv1NQKInAgMBAAECggEAW+7peDEqN17xO+sKKo1jU2uD9YJzOB28j0OuXW6Kgli/LQQWqKCeJJSxItb9SZh1Ojp4Voj6viVy9eP7GYQkrbSOSpOxKuh0al7eBB/vQIrTADzHEdnVBh24DcuVDXHvwv5tBbQz/t1/anq4JtfOQJhGIrgEoRCRpZl4wOLx9cs3OJT9RPJ803TOQpQX8aX9qAHTYSNBhZ65b6aIysJXTmZLpa/4/Cz/A6DRviYoRdv7Vp3FH9PC4oW8dnDgiMwv1jGfE95lztfkKKvJVa7TriK2ESdRpSeBhNDx9WsufsZk4GOky2k0k5xMLpUPLqUzNofAAFCvusrk61WMJhxgAQKBgQDhbCSYLbtp2PnE3oYy3N1fRp4cIwcSGD7ybWLXTdHL6ZvMIks/zE/mFu7jg5cJ6W1uVoSrnm4cO9n9G8IXaq49SVgk9ELVBEsec7Q/aYh6nZjhgo6SFzIgichgoA/dX4gNLjJcqJPOfosCaRZ0oyQam6818moy9PQjmCifRi4ORwKBgQCvQ4eYlOTnQ6sPWATx2yqTMQjPNXH+sJnTsYc9Oq/A8n/3ZEpwm9XEK3XxIuRYy4x8XrOJySNQnOUJRmwXDxJfir1HzELKPbAK/3xS3TX0VOWR8VvDARU9nJBKavv+GiM0l/2A8etpIdraSVvvXFP9GcvBE5/ocF38Zjms9FddIQKBgQDRrQRw5IDhmKfindUXtGbY+1eP9mjaROEwH90DRWgykq2O+AskYYekDorP5xNzbcOTETjAZVIoQuelzc8AdwNxECrD3MvZVfAFeDwdi60d2D9e61BvxNGkgGR4tAltsTFciS7hDxDEzuHAMT1o6mJnuY4E0OJFWaFJBnwqgl3OewKBgCjVXfExt4ceH+uqQTSsMGYsxXWpaQ7CzIQi13RviOU2WpQj/Kfx1sMmsQrmazc47VlIoyHWB/FPLJmVm7dfnpM9+s2QjHscFjjpv2h6+4gag4YWKp/rT6yUHpYICP/xzQQC6WAyfPt4lMnIz6+b3kGY0f0A+n+oNm2Iz6U5JGWhAoGAHdoqP6rggRVYkCbJqWw1w0EXZKF6xhrv8l4NiZZq7OouUPsD6JgFaZzzTDWQVlCkdAKSlSqLlFRCm/50JW/mQbgFupKSSkpYc4w14wBe1cTByKRtP8I0EpYHb/BGEBttZAuV3+UaDKnQTrJ3Ntb4QG1VPeg4myJSCoQgownoE1M=";
    
    //凤凰云商
    //public static final String RSA_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDEOx0cnN5jKzfB2fiOxqSwW71NMo+7LNACUcBok0c9d7q5OWp2gRK9c8UwxGdq3YpqZ+LfgLtCspGMfCkVTAEQ8rk05RGspbATHh96N8xyYxCeFb65cuN1vjyKCeKGpm3RKm3xOAHp8FSRWWa0kQhBGhR2ebau8QZ3Lsso56m95MEaDshFLPh8bvgfLucF9nmDqyJzzI1XwJEEkr1lf7aLMJjm5cpWnT8vvPBKV0JNQFzQIvzxrkaWZe6EJAnJ2yHAgWETfjZFgVmDzfvILwvJGF+rfETitYeyohx1BAf3FdzKTO8KJ/4Fr61Euj5DVPJDoJ5KnSVUXbfMevxcq6EHAgMBAAECggEANuCxS4czSliTx0rGEGfNNuTxE93IBHiL05o5TQWjLrWFKre75lhCfWkbBIBoy6IZS5iyAf9mX0jBG6C9fI883RiEYx6MytOrYi1ScEOakYLS6uL567MNmagSFiidiTqSC6TqCkO3hU0I2X0/E9BblplMj7WKOXJ+dGZJuq7O8aPCBlFFbJ0dzqWbDuRqouuEz8tSCZUAnslHye9YH+JuxzD8VZ9OqSDgiqFk3xqZRI8Womnb+MbSnPEJj7jAdNH3rFS0cWcTCSPbMD7rcoSsuW3ZBgf7MsqhYoKNV+/FOC2AeVRpbz7U34C70OYwaWP79lQWKcQqr06T/Dk2mM0pAQKBgQDxjKw+JKHSHt5gyShcj/aMlH9h18FgSiBKpxvFWd/7wfCXL+HPo4yvqgjvFFGfehMGmd2HXewrgvkIw5TvTRfra8otrD4QQlUqOr9r4y9xI1ul/31oQR3618DuDtZ1wq5lKssJDnrW7MQJzxcAkO3LsTPSiN0yz8y4dtx1HjPLkQKBgQDP+GMoYf0XrL3vNr7ICjHgtUlzN0QiKU+6pKf+piB5gaCAj6Gy1/IkwpNFempPXZ61uubWMmRrQPcH7zwTtHzdL1UxToGtxoMrw5uj4E242BjEHxNLGtyo8SxGDbgPBVKsmtuWmHI1Fpt5MsnGBwqqZQ6lkjohZpodPfgbDM1nFwKBgQDCB6IEeSCTzMANNwj48Zu26UI5sjpfwquVzA5+xOcrMGq2x3u4c0P7h7aNdeDrHgBSeR5+MnAXs6mf5Juqbl8ceQV7ErT4EpCez3oci+38D8761PRD3qraaJ1NnRWgmrXEBk/6AmuiYaZHytZLWh/qU3mxUMd+nXVxZ+lHGu+4QQKBgHpWiz3+Jnv+ydwmQCQ8mwqsmgCEyjeuRZBtu8U4xHG1p0z5MQ2bqn2lWCzOzirRz8Lnp1LpYdkV5jtsp9KPocQbS+otXGt1E0RzVOMZ156cwDTecuMDcjqPZJPxDg0H/JI8pl/4XXaNTfgFwFwVWFjUCcGsOzXavV3mTkTbSxkRAoGAc0XVpyLBZmuEroAHnHE7cEb58G9dYLxDL3gaXFqZSJgAo2DfrY4cLhmzbyaOkvGI/eGXLsDhAT7sphcGJ2BQuxJuKsZs70QmXN9vuOuyG+/DUPgmAvyhisDBLgsBLUbYsRXbEG/UirjqH/0osmi84q8igH1a7WL/UfKdWU1S7m4=";
    //楚鸿
    //public static final String RSA_PRIVATE = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCcKEGTDajnzIJfJDglexnETX6IJUMLpyBESOqo11wIddgIuCtnhr9LpBGgw3VGXXOQiLTWs32/52jd9jqjIecrOXSZjP/G5XxWbpHVt/TJ+2aupcuopQ2xG8KS/P7wB94JrJiotoFV47IFAiLnoLBcf3fa+gAw9ZPpQOvSDNycpnqwZTRCALwGyjwgkknutP8zeAgzn7cbr5KuNq4yWWmBXHTGM9MBc7IJ22Em9WzK2EoLxZ32auJ2SQZhoqGxNeb70zCfbO17QNjtcgZaTiVLn1Km4hwrQvgZ/dW4xeB6+hV3Hopme4Yh0Cr7s6xNxbgyDJlqSCN9jYYQS39cHGvlAgMBAAECggEAd+RcfFf3f4i/6+Y2CWjbHqFU7ZiWQkMdfe/x4pH+gixgIkmsYX7OLngYZLLMvqqPSL/8//6tJIK6O/iJiqhEASbKw/3Cf2XAMegQpeLn2Y/ghWSb2a5ntbwXhp0h6w5PRmQyjQ4afOQl0LyUKfxwi4P+dKyPKwfReTu0EwB2/PmHnGbpg+tksWpzJn5R5bKsQpCCugwbms4VQwLhZ3R7mc+PRvoDfltolDAJmR54YQQ09mcE+W+5qrfDoilGbeOcA0WmI/7odVccD5TauGTbEzsmWTs71tlGAFA+wJl2pp5kbuLSwjIarFmKMsra9pZKZMYht0KcDd+WtXtrgJh9vQKBgQDzqIAQbaiLtWkZ0G+PlughJwohLMa8rOq3+OwA9cSMQnPYxdRwpOcqPr3kTL3A81shoIggOFFaMth+JtTfpxyS2CKfvB8fhCC7hKYENoMqfRnw/9NxhuR06nWS5YQsfhjqdbTCvbpoH3t/pmSnp0AeIj9nhD97NrBwiBRDnZL5UwKBgQCkESMIZGJSBnD6vvWnUd7Bj6IZ6VmES7SwsrynwrHriSShCRyivrpGUPNCsZDoYihPR/TwI19v9k1Wo8zLGt9/LV+XqVgW8+1twa9wa6Tn3qSXgSANEGWMr8SBMlhxqyzeRp2KEmwjJodpYQ5VdkIc7FpBVTG78ttKTpDdPpSG5wKBgQCM6EkHNyNdiA+7yfp6Q38CjR0iik50Flu3ANEazQZZRqTQG7f7XKICoD4YpPineRSeocOXPnptezIP4k0A+hdgJHYBgj1NcI+tYczQauX30FkMMiYJZUUBCAXuaaTCRjoKfP+ID0iZpns5GvTir6o4wv1/088SZlIrAccWQy4X2QKBgCQauVDhdlmCnV4wo7J8a77AsvVsyow4dLzluGpah/jDJ1JgxPJzcl6C9eVZWgApiaFYvmgSALCMzRwh07FmaoR8IasoUwBKEZezpb3hklqMPkAw5DK7xv0cuOuNfrUU7DzF4Wk/AJkDo4znFNZtvcLLqFj0PIuVt/g0LeDiIqFFAoGBAJThtO2Rohuwkw+atdJ0Sy4zkOZ6W/ul01OIYgTP8P4Lz6Ip0c1Tp4/KjdR9mnk+wLUVUtY60hyq0jTvz+GGv2fglan4IhvsaRl2Y4HzufGRJD/5WdIOprgpVeG+WYOyq00L6mTUm585wwFChxRLrO9CQj353smqCtVrf8nG8dLw";
    
    
   
   
	//支付宝公钥
    //凤凰云商
	//public static final String ALIPAY_RSA_PUBLIC="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxDsdHJzeYys3wdn4jsaksFu9TTKPuyzQAlHAaJNHPXe6uTlqdoESvXPFMMRnat2Kamfi34C7QrKRjHwpFUwBEPK5NOURrKWwEx4fejfMcmMQnhW+uXLjdb48ignihqZt0Spt8TgB6fBUkVlmtJEIQRoUdnm2rvEGdy7LKOepveTBGg7IRSz4fG74Hy7nBfZ5g6sic8yNV8CRBJK9ZX+2izCY5uXKVp0/L7zwSldCTUBc0CL88a5GlmXuhCQJydshwIFhE342RYFZg837yC8LyRhfq3xE4rWHsqIcdQQH9xXcykzvCif+Ba+tRLo+Q1TyQ6CeSp0lVF23zHr8XKuhBwIDAQAB";
    //天天易购	
	public static final String ALIPAY_RSA_PUBLIC="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmlRjt/21JP2/i++sNQh8BLa170FQ9gn/dKuhXwkTNLCxI7GLn9Z/MdrKpWdzNC6TbW1yQyf7bipKCqRpva/UrlRT7cHcUZWb0mHWrpH2j30wwScT0g1DxtZwwemQ83SmiYkgwvIcCIIWS4ClkiaJz/2GrpJNzcdB0vVJnY8FnjQ4lHlX9bg/8b46+eqVOokYv9UNkXymdLRBmT+kzdMwpEa9uphog0FU9w/HARP6f0k+In+8GoL22uatY1EqPMYFIfFDPZFU2k6XaGIAPXWugfV0odoxgSbjDovOwct2nAJc4zNBy0VNbbpst0f9M9oZJlbPoJaXsap2zT79TUCiJwIDAQAB";
    //楚鸿
  	//public static final String ALIPAY_RSA_PUBLIC="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnChBkw2o58yCXyQ4JXsZxE1+iCVDC6cgREjqqNdcCHXYCLgrZ4a/S6QRoMN1Rl1zkIi01rN9v+do3fY6oyHnKzl0mYz/xuV8Vm6R1bf0yftmrqXLqKUNsRvCkvz+8AfeCayYqLaBVeOyBQIi56CwXH932voAMPWT6UDr0gzcnKZ6sGU0QgC8Bso8IJJJ7rT/M3gIM5+3G6+SrjauMllpgVx0xjPTAXOyCdthJvVsythKC8Wd9mridkkGYaKhsTXm+9Mwn2zte0DY7XIGWk4lS59SpuIcK0L4Gf3VuMXgevoVdx6KZnuGIdAq+7OsTcW4MgyZakgjfY2GEEt/XBxr5QIDAQAB";

    
	//退款申请
	public static final String refund_date = UtilDate.getDateFormatter();
	public static final String rufund_service = "refund_fastpay_by_platform_pwd";
	public static final String refund_notify_url = "http://app1.ys69.cn/notify_url.jsp";
	public static final String seller_user_id = partner;
	
	//public static final String order_notify="http://yn.gjfeng.net/gjfeng-web-client/wx/notify/payOrderNotifyAply";
	//public static final String trade_notify="http://yn.gjfeng.net/gjfeng-web-client/wx/notify/payTradeNotifyAply";
	//public static final String banefit_notify="http://yn.gjfeng.net/gjfeng-web-client/wx/notify/payBenefitNotifyAply";
	
}
