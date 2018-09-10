package cc.messcat.gjfeng.common.pay.alipay.util;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;

import cc.messcat.gjfeng.common.pay.alipay.config.AlipayConfig;
import cc.messcat.gjfeng.common.vo.app.ResultVo;

public class AlipayRefundUtil {

	private AlipayRefundUtil() {
	};

	private static class Single {
		private final static AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID,
				AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,
				AlipayConfig.SIGNTYPE);
	}

	private static AlipayClient getClient() {
		return Single.client;
	}
	
	/**
	 * 支付宝退款
	 * @param orderSn	订单支付时传入的商户订单号,不能和 trade_no同时为空。
	 * @param refundAmount 需要退款的金额，该金额不能大于订单金额,单位为元，支持两位小数
	 * @param refundReason 退款的原因说明
	 * @return
	 */
	public static ResultVo aliRefund(String orderSn,String refundAmount,String refundReason){
		ResultVo resultVo = new ResultVo(400,"退款失败");
		AlipayTradeRefundModel model = new AlipayTradeRefundModel();
		model.setOutTradeNo(orderSn);
		model.setRefundAmount(refundAmount);
		model.setRefundReason(refundReason);
		AlipayTradeRefundResponse response = refund(model);
		if("Success".equalsIgnoreCase(response.getMsg())){
			resultVo.setResult(response.getTradeNo());
			resultVo.setCode(200);
		}
		resultVo.setMsg(response.getMsg());
		return resultVo;
	}

	public static void main(String[] args) {
//		aliRefund("201789113633640", "0.01", "无理由退款");
		// TODO Auto-generated method stub
		// 支付
//		AlipayTradeWapPayModel model0 = new AlipayTradeWapPayModel();
//		model0.setOutTradeNo("20170422145610263252");
//		model0.setSubject("Iphone6 16G");
//		model0.setTotalAmount("88.88");
//		model0.setBody("iphone 6");
//		model0.setTimeoutExpress("90m");
//		model0.setProductCode("FACE_TO_FACE_PAYMENT");
//		AlipayTradeWapPayResponse respone = AlipayTradePay(model0);
//		System.out.println("requset:" + respone.getMsg());

//		AlipayTradeRefundModel model = new AlipayTradeRefundModel();
//		model.setOutTradeNo("201788143435254");
//		model.setRefundAmount("0.01");
//		model.setRefundReason("88.88");
//		AlipayTradeRefundResponse response = refund(model);
//		System.out.println(response.getMsg());
	}

	@SuppressWarnings("finally")
	private static AlipayTradeRefundResponse refund(AlipayTradeRefundModel model) {
		AlipayClient client = getClient();
		AlipayTradeRefundRequest alipay_request = new AlipayTradeRefundRequest();

		alipay_request.setBizModel(model);

		AlipayTradeRefundResponse alipay_response = null;
		try {
			alipay_response = client.execute(alipay_request);
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(alipay_response.getBody());
		return alipay_response;
		
		
	}

}
