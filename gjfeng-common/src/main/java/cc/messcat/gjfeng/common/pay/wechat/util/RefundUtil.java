package cc.messcat.gjfeng.common.pay.wechat.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import cc.messcat.gjfeng.common.constant.CommonProperties;
import cc.messcat.gjfeng.common.pay.wechat.weixin.popular.api.PayMchAPI;
import cc.messcat.gjfeng.common.pay.wechat.weixin.popular.bean.paymch.SecapiPayRefund;
import cc.messcat.gjfeng.common.pay.wechat.weixin.popular.bean.paymch.SecapiPayRefundResult;
import cc.messcat.gjfeng.common.pay.wechat.weixin.popular.client.LocalHttpClient;
import cc.messcat.gjfeng.common.pay.wechat.weixin.popular.util.ClientCustomSSL;
import cc.messcat.gjfeng.common.pay.wechat.weixin.popular.util.MapUtil;
import cc.messcat.gjfeng.common.pay.wechat.weixin.popular.util.SignatureUtil;
import cc.messcat.gjfeng.common.pay.wechat.weixin.popular.util.XMLConverUtil;
import cc.messcat.gjfeng.common.util.DateHelper;
import cc.messcat.gjfeng.common.util.RandUtil;
import cc.messcat.gjfeng.common.vo.app.ResultVo;

public class RefundUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// LocalHttpClient.initMchKeyStore("PKCS12",
		// "D:/cert/apiclient_cert.p12", "1481013522", 100, 10);
		ResultVo resultVo = refundWeixinPay("20170816102013888357", "0.12");
		System.out.println(resultVo);
		System.out.println("msg:" + resultVo.getMsg());
	}

	/**
	 * 微信退款
	 * 
	 * @param orderSn
	 *            商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
	 * @param free
	 *            订单总金额，单位为分，只能为整数
	 * @return 退款号
	 */
	public static ResultVo refundWeixinPay(String orderSn, String free) {
		ResultVo resultVo = new ResultVo(400, "操作失败");
		SecapiPayRefund secapiPayRefund = new SecapiPayRefund();
		secapiPayRefund.setAppid(CommonProperties.GJFENG_APP_ID);
		secapiPayRefund.setMch_id(CommonProperties.GJFENG_PARTNER);
		secapiPayRefund.setNonce_str(UUID.randomUUID().toString().replace("-", ""));
		float sessionmoney = Float.parseFloat(free)*100;
		secapiPayRefund.setOut_trade_no(orderSn);
		secapiPayRefund.setTotal_fee(String.format("%.0f", sessionmoney));
		secapiPayRefund.setRefund_fee(String.format("%.0f", sessionmoney));
		String out_refund_no = DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6));
		secapiPayRefund.setOut_refund_no(out_refund_no);
		//secapiPayRefund.setRefund_account("REFUND_SOURCE_RECHARGE_FUNDS");
		Map<String, String> map = MapUtil.objectToMap(secapiPayRefund);
		String sign = SignatureUtil.generateSign(map, CommonProperties.GJFENG_PARTNER_KEY);
		secapiPayRefund.setSign(sign);
		
		// SecapiPayRefundResult payRefundResult =
		// PayMchAPI.secapiPayRefund(secapiPayRefund,
		// CommonProperties.GJFENG_PARTNER_KEY);
		String data = XMLConverUtil.convertToXML(secapiPayRefund);
		String createOrderURL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
		try {
			String result = ClientCustomSSL.doRefund(createOrderURL, data);
			SecapiPayRefundResult payRefundResult = XMLConverUtil.convertToObject(SecapiPayRefundResult.class, result);
			if ("SUCCESS".equals(payRefundResult.getReturn_code())) {
				resultVo = "SUCCESS".equals(payRefundResult.getResult_code())
						? new ResultVo(200,"退款成功")
						: new ResultVo(400,"退款失败，原因为："+ payRefundResult.getErr_code_des());
				resultVo.setResult(out_refund_no);
			} else {
				resultVo.setMsg(payRefundResult.getReturn_msg());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultVo;
	}

}
