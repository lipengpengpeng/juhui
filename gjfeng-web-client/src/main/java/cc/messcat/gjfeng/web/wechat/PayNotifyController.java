package cc.messcat.gjfeng.web.wechat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.direct.util.AlipayNotify;
import com.h5pay.api.H5PayUtil;
import com.h5pay.api.XmlUtils;
import com.wechat.popular.bean.pay.PayResult;
import com.wechat.popular.util.XMLConverUtil;

import cc.messcat.gjfeng.common.exception.LoggerPrint;
import cc.messcat.gjfeng.common.fastpay.util.NetXmlUtils;
import cc.messcat.gjfeng.common.netFriends.HttpUtils;
import cc.messcat.gjfeng.common.proprietary.bean.ProductInfo;
import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.web.BaseController;
import cc.messcat.gjfeng.dubbo.core.GjfOrderInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfProductInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfTradeDubbo;
import cc.messcat.gjfeng.entity.GjfMemberTrade;

@Controller
@RequestMapping("wx/notify/")
public class PayNotifyController extends BaseController {

	@Autowired
	@Qualifier("request")
	private HttpServletRequest request;

	@Autowired
	@Qualifier("response")
	private HttpServletResponse response;

	@Autowired
	@Qualifier("tradeDubbo")
	private GjfTradeDubbo tradeDubbo;

	@Autowired
	@Qualifier("orderInfoDubbo")
	private GjfOrderInfoDubbo orderInfoDubbo;

	@Autowired
	@Qualifier("productInfoDubbo")
	private GjfProductInfoDubbo productInfoDubbo;

	/**
	 * @描述 订单支付回调通知
	 * @author Karhs
	 * @date 2017年1月11日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "payOrderNotify", method = RequestMethod.POST)
	@ResponseBody
	public Object payOrderNotify(HttpServletRequest request) {
		try {

			try {
				// 获取请求数据
				PayResult payResult = XMLConverUtil.convertToObject(PayResult.class, request.getInputStream());
				// 签名验证
				String out_trade_no = payResult.getOut_trade_no();
				if ("0".equals(payResult.getResult_code())) {
					System.out.println("支付回调地址调用");
					// 执行支付成功后的逻辑代码
					// orderInfoDubbo.midifyOrderPayStatus(out_trade_no,
					// payResult.getTransaction_id());
					orderInfoDubbo.updateOrderStatus(out_trade_no, payResult.getTransaction_id(), "1", null, null, "1");

				} else {
					orderInfoDubbo.updateOrderStatus(out_trade_no, payResult.getTransaction_id(), "0", null, null, "1");
					response.getOutputStream().write("error".getBytes());
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("支付回调异常");
			}
			response.getOutputStream().write("success".getBytes());
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, PayNotifyController.class);
		}
		return null;
	}

	/**
	 * @描述 购买分红权支付回调通知
	 * @author Karhs
	 * @date 2017年1月11日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "payBuyDiviNotify", method = RequestMethod.POST)
	@ResponseBody
	public Object payBuyDiviNotify(HttpServletRequest request) {
		try {

		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, PayNotifyController.class);
		}
		return resultVo;
	}

	/**
	 * @描述 商家让利支付回调通知
	 * @author Karhs
	 * @date 2017年1月11日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "payBenefitNotify", method = RequestMethod.POST)
	@ResponseBody
	public Object payBenefitNotify(HttpServletRequest request) {
		try {
			try {
				// 获取请求数据
				PayResult payResult = XMLConverUtil.convertToObject(PayResult.class, request.getInputStream());
				// 签名验证
				String out_trade_no = payResult.getOut_trade_no();
				System.out.println("---让利回调---" + payResult.getResult_code() + "---" + payResult.getReturn_code()
						+ "----" + payResult.getTransaction_id());
				if ("0".equals(payResult.getResult_code())) {
					System.out.println("商家让利支付回调地址调用");
					// 执行支付成功后的逻辑代码
					tradeDubbo.updateStoreBenefitPayStatus(out_trade_no, payResult.getTransaction_id(), "1");
				} else {
					response.getOutputStream().write("error".getBytes());
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("支付回调异常");
			}
			response.getOutputStream().write("success".getBytes());
			return null;
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, PayNotifyController.class);
		}
		return null;
	}

	/**
	 * @描述 充值授信额度回调通知
	 * @author Karhs
	 * @date 2017年2月18日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "payTradeNotify", method = RequestMethod.POST)
	@ResponseBody
	public Object payTradeNotify(HttpServletRequest request) {
		try {

			try {
				// 获取请求数据
				PayResult payResult = XMLConverUtil.convertToObject(PayResult.class, request.getInputStream());

				String out_trade_no = payResult.getOut_trade_no();
				// 签名验证
				System.out.println("---充值授信回调---" + payResult.getResult_code() + "---" + payResult.getReturn_code()
						+ "---" + payResult.getTransaction_id());
				if ("0".equals(payResult.getResult_code())) {
					System.out.println("充值支付回调地址调用");
					// 执行支付成功后的逻辑代码
					tradeDubbo.updateShouXinPayStatus(out_trade_no, payResult.getTransaction_id(), "1");

				} else {
					response.getOutputStream().write("error".getBytes());
					return null;
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("支付回调异常");
			}
			response.getOutputStream().write("success".getBytes());
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, PayNotifyController.class);
		}
		return null;
	}

	/**
	 * h5支付充值授信回调
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "payByH5TradeNotify", method = RequestMethod.POST)
	@ResponseBody
	public Object payByH5TradeNotify(HttpServletRequest request) {
		try {
			try {
				// 获取请求数据
				java.io.ByteArrayOutputStream inBuffer = new java.io.ByteArrayOutputStream();
				java.io.InputStream input = request.getInputStream();
				byte[] tmp = new byte[1024];
				int len = 0;
				while ((len = input.read(tmp)) > 0) {
					inBuffer.write(tmp, 0, len);
				}
				byte[] requestData = inBuffer.toByteArray();
				String requestJsonStr = new String(requestData, "UTF-8");

				JSONObject requestJson = JSON.parseObject(requestJsonStr);

				String out_trade_no = requestJson.getString("merchOrderId");
				String trade_no = requestJson.getString("orderId");

				System.out.println(out_trade_no + ":授信回调JSON:" + requestJson);
				// 签名验证
				/* "000000".equals(requestJson.get("retCode"))&& */
				if ("0".equals(requestJson.get("status"))) {
					tradeDubbo.updateShouXinPayStatus(out_trade_no, trade_no, "1");
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("支付回调异常");
			}
			response.getOutputStream().write("success".getBytes());
			return null;
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, PayNotifyController.class);
		}
		return null;
	}

	/**
	 * @描述 商家让利H5支付回调通知
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "payByH5BenefitNotify", method = RequestMethod.POST)
	@ResponseBody
	public Object payByH5BenefitNotify(HttpServletRequest request) {
		try {

			try {
				// 获取请求数据
				java.io.ByteArrayOutputStream inBuffer = new java.io.ByteArrayOutputStream();
				java.io.InputStream input = request.getInputStream();
				byte[] tmp = new byte[1024];
				int len = 0;
				while ((len = input.read(tmp)) > 0) {
					inBuffer.write(tmp, 0, len);
				}
				byte[] requestData = inBuffer.toByteArray();
				String requestJsonStr = new String(requestData, "UTF-8");

				JSONObject requestJson = JSON.parseObject(requestJsonStr);

				String out_trade_no = requestJson.getString("merchOrderId");
				String trade_no = requestJson.getString("orderId");
				System.out.println(out_trade_no + ":让利回调JSON:" + requestJson);
				/* "000000".equals(requestJson.get("retCode"))&& */
				if ("0".equals(requestJson.get("status"))) {
					tradeDubbo.updateStoreBenefitPayStatus(out_trade_no, trade_no, "1");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("支付回调异常");
			}
			response.getOutputStream().write("success".getBytes());
			return null;
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, PayNotifyController.class);
		}
		return resultVo;
	}

	/**
	 * @描述 订单H5支付回调通知
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "payByH5OrderNotify", method = RequestMethod.POST)
	@ResponseBody
	public Object payByH5OrderNotify(HttpServletRequest request) {
		try {
			System.out.println("订单H5支付回调地址调用");
			try {
				// 获取请求数据
				// 获取请求数据
				java.io.ByteArrayOutputStream inBuffer = new java.io.ByteArrayOutputStream();
				java.io.InputStream input = request.getInputStream();
				byte[] tmp = new byte[1024];
				int len = 0;
				while ((len = input.read(tmp)) > 0) {
					inBuffer.write(tmp, 0, len);
				}
				byte[] requestData = inBuffer.toByteArray();
				String requestJsonStr = new String(requestData, "UTF-8");

				JSONObject requestJson = JSON.parseObject(requestJsonStr);
				String out_trade_no = requestJson.getString("merchOrderId");
				String trade_no = requestJson.getString("orderId");
				System.out.println(out_trade_no + ":让利回调JSON:" + requestJson);
				/* "000000".equals(requestJson.get("retCode"))&& */
				if ("0".equals(requestJson.get("status"))) {
					orderInfoDubbo.updateOrderStatus(out_trade_no, trade_no, "1", null, null, "1");
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("支付回调异常");
			}
			response.getOutputStream().write("success".getBytes());
			return null;
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, PayNotifyController.class);
		}
		return null;
	}

	/**
	 * h5支付充值授信成功后查询
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "payByH5TradeQeury", method = RequestMethod.POST)
	@ResponseBody
	public Object payByH5TradeQeury(HttpServletRequest request, String out_trade_no) {
		try {
			System.out.println("h5支付充值授信成功后查询订单");
			try {
				// 获取请求数据
				String xml = H5PayUtil.queryH5Order(request, out_trade_no);
				String[] str = xml.split("&");
				Map<String, String> map = new HashMap<String, String>();
				for (int i = 0; i < str.length; i++) {
					String str0 = str[i];
					String[] str1 = str0.split("=");
					if (str1.length == 1) {
						map.put(str1[0], "");
					} else {
						for (int j = 0; j < str1.length; j++) {
							map.put(str1[0], str1[1]);
						}
					}
				}
				if (map.get("retCode").equals("000000")) {
					String out_trade_no1 = map.get("merchOrderId");
					String trade_no = map.get("orderId");
					tradeDubbo.updateShouXinPayStatus(out_trade_no1, trade_no, "1");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("支付回调异常");
			}
			response.getOutputStream().write("success".getBytes());
			return null;
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, PayNotifyController.class);
		}
		return resultVo;
	}

	/**
	 * @描述 订单扫码支付回调
	 * @author Karhs
	 * @date 2017年1月11日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "sweepPayOrderNotify", method = RequestMethod.POST)
	@ResponseBody
	public Object sweepPayOrderNotify(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			String resString = XmlUtils.parseRequst(request);
			// System.out.println("通知内容：" + resString);

			String respString = "fail";
			if (resString != null && !"".equals(resString)) {
				Map<String, String> map = XmlUtils.toMap(resString.getBytes(), "utf-8");
				String res = XmlUtils.toXml(map);
				// System.out.println("通知内容：" + res);
				// GjfWeiXinPayInfo payInfo=(GjfWeiXinPayInfo)
				// tradeDubbo.findWeiXinBaseInfo("0").getResult();
				if (map.containsKey("sign")) {
					// if(!SignUtils.checkParam(map, payInfo.getPayKey())){
					// res = "验证签名不通过";
					// respString = "fail";
					// }else{
					String status = map.get("status");
					if (status != null && "0".equals(status)) {
						String result_code = map.get("result_code");
						if (result_code != null && "0".equals(result_code)) {

							String out_trade_no = map.get("out_trade_no");
							String out_transaction_id = map.get("out_transaction_id");
							orderInfoDubbo.updateOrderStatus(out_trade_no, out_transaction_id, "1", null, null, "1");
						}
					}
					respString = "success";
					// }
				}
			}
			response.getWriter().write(respString);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, PayNotifyController.class);
		}
		return null;
	}

	/**
	 * @描述 利楚微信支付充值授信额度回调通知
	 * @author Karhs
	 * @date 2017年2月18日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "payTradeNotifyLiChu", method = RequestMethod.POST)
	@ResponseBody
	public Object payTradeNotifyLiChu(HttpServletRequest request) {
		try {
			// 获取请求数据
			java.io.ByteArrayOutputStream inBuffer = new java.io.ByteArrayOutputStream();
			java.io.InputStream input = request.getInputStream();
			byte[] tmp = new byte[1024];
			int len = 0;
			while ((len = input.read(tmp)) > 0) {
				inBuffer.write(tmp, 0, len);
			}
			byte[] requestData = inBuffer.toByteArray();
			String requestJsonStr = new String(requestData, "UTF-8");

			JSONObject requestJson = JSON.parseObject(requestJsonStr);
			String out_trade_no = requestJson.getString("terminal_trace");
			// 签名验证
			System.out.println("---充值授信回调---" + requestJson.getString("result_code") + "---"
					+ requestJson.getString("return_code") + "---" + requestJson.getString("out_trade_no"));
			if ("01".equals(requestJson.getString("result_code"))) {
				System.out.println("充值支付回调地址调用");
				// 执行支付成功后的逻辑代码
				tradeDubbo.updateShouXinPayStatus(out_trade_no, requestJson.getString("out_trade_no"), "1");
				JSONObject jsonParam = new JSONObject();
				jsonParam.put("return_code", requestJson.getString("result_code"));
				jsonParam.put("return_msg", "success");
				return jsonParam.toJSONString();
			} else {
				JSONObject jsonParam = new JSONObject();
				jsonParam.put("return_code", requestJson.getString("result_code"));
				jsonParam.put("return_msg", "fail");
				return jsonParam.toJSONString();
			}

		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, PayNotifyController.class);
		}
		return null;
	}

	/**
	 * @描述 商家让利支付回调通知
	 * @author Karhs
	 * @date 2017年1月11日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "payBenefitNotifyLiChu", method = RequestMethod.POST)
	@ResponseBody
	public Object payBenefitNotifyLiChu(HttpServletRequest request) {

		try {
			// 获取请求数据
			java.io.ByteArrayOutputStream inBuffer = new java.io.ByteArrayOutputStream();
			java.io.InputStream input = request.getInputStream();
			byte[] tmp = new byte[1024];
			int len = 0;
			while ((len = input.read(tmp)) > 0) {
				inBuffer.write(tmp, 0, len);
			}
			byte[] requestData = inBuffer.toByteArray();
			String requestJsonStr = new String(requestData, "UTF-8");

			JSONObject requestJson = JSON.parseObject(requestJsonStr);
			String out_trade_no = requestJson.getString("terminal_trace");
			System.out.println("---充值授信回调---" + requestJson.getString("result_code") + "---"
					+ requestJson.getString("return_code") + "---" + requestJson.getString("out_trade_no"));
			if ("01".equals(requestJson.getString("result_code"))) {
				System.out.println("商家让利支付回调地址调用");
				// 执行支付成功后的逻辑代码
				tradeDubbo.updateStoreBenefitPayStatus(out_trade_no, requestJson.getString("out_trade_no"), "1");
				JSONObject jsonParam = new JSONObject();
				jsonParam.put("return_code", requestJson.getString("result_code"));
				jsonParam.put("return_msg", "success");
				return jsonParam.toJSONString();
			} else {
				JSONObject jsonParam = new JSONObject();
				jsonParam.put("return_code", requestJson.getString("result_code"));
				jsonParam.put("return_msg", "fail");
				return jsonParam.toJSONString();
			}

		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, PayNotifyController.class);
		}
		return null;
	}

	/**
	 * @描述 订单支付回调通知
	 * @author Karhs
	 * @date 2017年1月11日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "payOrderNotifyLiChu", method = RequestMethod.POST)
	@ResponseBody
	public Object payOrderNotifyLiChu(HttpServletRequest request) {
		try {
			// 获取请求数据
			java.io.ByteArrayOutputStream inBuffer = new java.io.ByteArrayOutputStream();
			java.io.InputStream input = request.getInputStream();
			byte[] tmp = new byte[1024];
			int len = 0;
			while ((len = input.read(tmp)) > 0) {
				inBuffer.write(tmp, 0, len);
			}
			byte[] requestData = inBuffer.toByteArray();
			String requestJsonStr = new String(requestData, "UTF-8");

			JSONObject requestJson = JSON.parseObject(requestJsonStr);
			String out_trade_no = requestJson.getString("terminal_trace");
			System.out.println("---充值授信回调---" + requestJson.getString("result_code") + "---"
					+ requestJson.getString("return_code") + "---" + requestJson.getString("out_trade_no"));
			if ("01".equals(requestJson.getString("result_code"))) {
				System.out.println("支付回调地址调用");
				// 执行支付成功后的逻辑代码
				// orderInfoDubbo.midifyOrderPayStatus(out_trade_no,
				// payResult.getTransaction_id());
				orderInfoDubbo.updateOrderStatus(out_trade_no, requestJson.getString("out_trade_no"), "1", null, null,
						"1");
				JSONObject jsonParam = new JSONObject();
				jsonParam.put("return_code", requestJson.getString("result_code"));
				jsonParam.put("return_msg", "success");
				return jsonParam.toJSONString();

			} else {
				orderInfoDubbo.updateOrderStatus(out_trade_no, requestJson.getString("out_trade_no"), "0", null, null,
						"1");
				JSONObject jsonParam = new JSONObject();
				jsonParam.put("return_code", requestJson.getString("result_code"));
				jsonParam.put("return_msg", "fail");
				return jsonParam.toJSONString();
			}

		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, PayNotifyController.class);
		}
		return null;
	}

	/**
	 * @描述 订单支付回调通知
	 * @author Karhs
	 * @date 2017年1月11日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "payOrderNotifyWeiXin", method = RequestMethod.POST)
	@ResponseBody
	public Object payOrderNotifyWeiXin(HttpServletRequest request) {
		try {

			try {
				// 获取请求数据
				PayResult payResult = XMLConverUtil.convertToObject(PayResult.class, request.getInputStream());
				// 签名验证
				String out_trade_no = payResult.getOut_trade_no();
				if ("SUCCESS".equals(payResult.getResult_code()) && "SUCCESS".equals(payResult.getReturn_code())) {
					System.out.println("支付回调地址调用");
					// 执行支付成功后的逻辑代码
					// orderInfoDubbo.midifyOrderPayStatus(out_trade_no,
					// payResult.getTransaction_id());
					orderInfoDubbo.updateOrderStatus(out_trade_no, payResult.getTransaction_id(), "1", null, null, "1");

				} else {
					orderInfoDubbo.updateOrderStatus(out_trade_no, payResult.getTransaction_id(), "0", null, null, "1");
					response.getOutputStream().write("error".getBytes());
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("支付回调异常");
			}
			response.getOutputStream().write("success".getBytes());
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, PayNotifyController.class);
		}
		return null;
	}

	/**
	 * @描述 商家让利支付回调通知
	 * @author Karhs
	 * @date 2017年1月11日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "payBenefitNotifyWeiXin", method = RequestMethod.POST)
	@ResponseBody
	public Object payBenefitNotifyWeiXin(HttpServletRequest request) {
		try {
			try {
				// 获取请求数据
				PayResult payResult = XMLConverUtil.convertToObject(PayResult.class, request.getInputStream());
				// 签名验证
				String out_trade_no = payResult.getOut_trade_no();
				System.out.println("---让利回调---" + payResult.getResult_code() + "---" + payResult.getReturn_code()
						+ "----" + payResult.getTransaction_id());
				if ("SUCCESS".equals(payResult.getResult_code()) && "SUCCESS".equals(payResult.getReturn_code())) {
					System.out.println("商家让利支付回调地址调用");
					// 执行支付成功后的逻辑代码
					tradeDubbo.updateStoreBenefitPayStatus(out_trade_no, payResult.getTransaction_id(), "1");
				} else {
					response.getOutputStream().write("error".getBytes());
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("支付回调异常");
			}
			response.getOutputStream().write("success".getBytes());
			return null;
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, PayNotifyController.class);
		}
		return null;
	}

	/**
	 * @描述 充值授信额度回调通知
	 * @author Karhs
	 * @date 2017年2月18日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "payTradeNotifyWeiXin", method = RequestMethod.POST)
	@ResponseBody
	public Object payTradeNotifyWeiXin(HttpServletRequest request) {
		try {

			try {
				// 获取请求数据
				PayResult payResult = XMLConverUtil.convertToObject(PayResult.class, request.getInputStream());

				String out_trade_no = payResult.getOut_trade_no();
				// 签名验证
				System.out.println("---充值授信回调---" + payResult.getResult_code() + "---" + payResult.getReturn_code()
						+ "---" + payResult.getTransaction_id());
				if ("SUCCESS".equals(payResult.getResult_code()) && "SUCCESS".equals(payResult.getReturn_code())) {
					System.out.println("充值支付回调地址调用");
					// 执行支付成功后的逻辑代码
					tradeDubbo.updateShouXinPayStatus(out_trade_no, payResult.getTransaction_id(), "1");

				} else {
					response.getOutputStream().write("error".getBytes());
					return null;
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("支付回调异常");
			}
			response.getOutputStream().write("success".getBytes());
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, PayNotifyController.class);
		}
		return null;
	}

	/**
	 * 支付宝获取请求参数
	 * 
	 * @param requestParams
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	private Map<String, String> getParams(Map requestParams) throws UnsupportedEncodingException {
		Map<String, String> params = new HashMap<String, String>();
		if (requestParams != null && requestParams.size() > 0) {
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				// valueStr = new String(valueStr.getBytes("ISO-8859-1"),
				// "utf-8");
				params.put(name, valueStr);
			}
		}
		return params;
	}

	/**
	 * 支付宝app支付让利回调
	 * 
	 * @return
	 */
	@RequestMapping(value = "payBenefitNotifyAply", method = RequestMethod.POST)
	@ResponseBody
	public Object payBenefitNotifyAply() {
		try {
			// 获取支付宝请求参数
			Map<String, String> params = getParams(request.getParameterMap());
			System.out.println("回调参数：" + params);
			if (params.get("notify_id") == null || params.get("notify_id") == "") {//// 判断接受的post通知中有无notify_id，如果有则是异步通知。
				return "no notify message";
			}
			if (!AlipayNotify.verifyResponse(params.get("notify_id")).equals("true")) // 判断成功之后使用getResponse方法判断是否是支付宝发来的异步通知。
			{
				return "fail";
			}
			// if (!AlipayNotify.verifyAppAlipay(params, params.get("sign"))) //
			// 使用支付宝公钥验签
			// {
			// return "fail";
			// }
			if ("TRADE_SUCCESS".equals(params.get("trade_status"))) {
				String outTradeNo = params.get("out_trade_no");
				String payTradeNo = params.get("trade_no");
				System.out.println("支付宝让利回调：" + outTradeNo);
				tradeDubbo.updateStoreBenefitPayStatus(outTradeNo, payTradeNo, "1");
				return "success";// 请不要修改或删除
			}

		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * 支付宝app支付授信回调
	 * 
	 * @return
	 */
	@RequestMapping(value = "payTradeNotifyAply", method = RequestMethod.POST)
	@ResponseBody
	public Object payTradeNotifyAply() {
		try {
			// 获取支付宝请求参数
			Map<String, String> params = getParams(request.getParameterMap());
			if (params.get("notify_id") == null || params.get("notify_id") == "") {//// 判断接受的post通知中有无notify_id，如果有则是异步通知。
				return "no notify message";
			}
			if (!AlipayNotify.verifyResponse(params.get("notify_id")).equals("true")) // 判断成功之后使用getResponse方法判断是否是支付宝发来的异步通知。
			{
				return "fail";
			}
			// if (!AlipayNotify.verifyAppAlipay(params, params.get("sign"))) //
			// 使用支付宝公钥验签
			// {
			// return "fail";
			// }
			if ("TRADE_SUCCESS".equals(params.get("trade_status"))) {
				String outTradeNo = params.get("out_trade_no");
				String payTradeNo = params.get("trade_no");
				System.out.println("支付宝授信回调：" + outTradeNo);
				tradeDubbo.updateShouXinPayStatus(outTradeNo, payTradeNo, "1");
				return "success";// 请不要修改或删除
			}

		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * 支付宝app支付订单回调
	 * 
	 * @return
	 */
	@RequestMapping(value = "payOrderNotifyAply", method = RequestMethod.POST)
	@ResponseBody
	public Object payOrderNotifyAply() {
		try {
			// 获取支付宝请求参数
			Map<String, String> params = getParams(request.getParameterMap());
			if (params.get("notify_id") == null || params.get("notify_id") == "") {//// 判断接受的post通知中有无notify_id，如果有则是异步通知。
				return "no notify message";
			}
			if (!AlipayNotify.verifyResponse(params.get("notify_id")).equals("true")) // 判断成功之后使用getResponse方法判断是否是支付宝发来的异步通知。
			{
				return "fail";
			}
			// if (!AlipayNotify.verifyAppAlipay(params, params.get("sign"))) //
			// 使用支付宝公钥验签
			// {
			// return "fail";
			// }
			if ("TRADE_SUCCESS".equals(params.get("trade_status"))) {
				String outTradeNo = params.get("out_trade_no");
				String payTradeNo = params.get("trade_no");
				System.out.println("支付宝订单回调：" + outTradeNo);
				orderInfoDubbo.updateOrderStatus(outTradeNo, payTradeNo, "1", null, null, "1");
				return "success";// 请不要修改或删除
			}

		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * 授信网银支付回调
	 * 
	 * @return
	 */
	@RequestMapping(value = "payShouxinWangYiLNotify", method = RequestMethod.POST)
	@ResponseBody
	public Object payShouxinWangYiLNotify() {
		try {
			// 获取请求数据
			java.io.ByteArrayOutputStream inBuffer = new java.io.ByteArrayOutputStream();
			java.io.InputStream input = request.getInputStream();
			byte[] tmp = new byte[1024];
			int len = 0;
			while ((len = input.read(tmp)) > 0) {
				inBuffer.write(tmp, 0, len);
			}
			byte[] requestData = inBuffer.toByteArray();
			String requestJsonStr = new String(requestData, "UTF-8");
			Map<String, String> decryptMap = NetXmlUtils.Xml2Map(requestJsonStr);
			String out_trade_no = decryptMap.get("orderId");
			System.out.println("---快捷支付充值授信回调---" + decryptMap.get("resultCode") + "---" + decryptMap.get("resultDesc")
					+ "---" + out_trade_no);
			if ("00".equals(decryptMap.get("resultCode"))) {
				System.out.println("快捷支付回调地址调用");
				// 执行支付成功后的逻辑代码
				// orderInfoDubbo.midifyOrderPayStatus(out_trade_no,
				// payResult.getTransaction_id());
				tradeDubbo.updateShouXinPayStatus(out_trade_no, "", "1");
				return "SUCCESS";

			} else {
				return "FAIL";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 让利网银支付回调
	 * 
	 * @return
	 */
	@RequestMapping(value = "payBenerfitWangYiLNotify", method = RequestMethod.POST)
	@ResponseBody
	public Object payBenerfitWangYiLNotify() {
		try {
			// 获取请求数据
			java.io.ByteArrayOutputStream inBuffer = new java.io.ByteArrayOutputStream();
			java.io.InputStream input = request.getInputStream();
			byte[] tmp = new byte[1024];
			int len = 0;
			while ((len = input.read(tmp)) > 0) {
				inBuffer.write(tmp, 0, len);
			}
			byte[] requestData = inBuffer.toByteArray();
			String requestJsonStr = new String(requestData, "UTF-8");
			Map<String, String> decryptMap = NetXmlUtils.Xml2Map(requestJsonStr);
			String out_trade_no = decryptMap.get("orderId");
			System.out.println("---快捷支付充值让利回调---" + decryptMap.get("resultCode") + "---" + decryptMap.get("resultDesc")
					+ "---" + out_trade_no);
			if ("00".equals(decryptMap.get("resultCode"))) {
				System.out.println("快捷支付让利回调地址调用");
				// 执行支付成功后的逻辑代码
				// orderInfoDubbo.midifyOrderPayStatus(out_trade_no,
				// payResult.getTransaction_id());
				tradeDubbo.updateStoreBenefitPayStatus(out_trade_no, "", "1");
				return "SUCCESS";

			} else {
				return "FAIL";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 让利网银支付回调
	 * 
	 * @return
	 */
	@RequestMapping(value = "payOrderWangYiLNotify", method = RequestMethod.POST)
	@ResponseBody
	public Object payOrderWangYiLNotify() {
		try {
			// 获取请求数据
			java.io.ByteArrayOutputStream inBuffer = new java.io.ByteArrayOutputStream();
			java.io.InputStream input = request.getInputStream();
			byte[] tmp = new byte[1024];
			int len = 0;
			while ((len = input.read(tmp)) > 0) {
				inBuffer.write(tmp, 0, len);
			}
			byte[] requestData = inBuffer.toByteArray();
			String requestJsonStr = new String(requestData, "UTF-8");
			Map<String, String> decryptMap = NetXmlUtils.Xml2Map(requestJsonStr);
			String out_trade_no = decryptMap.get("orderId");
			System.out.println("---快捷支付充值订单回调---" + decryptMap.get("resultCode") + "---" + decryptMap.get("resultDesc")
					+ "---" + out_trade_no);
			if ("00".equals(decryptMap.get("resultCode"))) {
				System.out.println("快捷支付订单回调地址调用");
				// 执行支付成功后的逻辑代码
				// orderInfoDubbo.midifyOrderPayStatus(out_trade_no,
				// payResult.getTransaction_id());
				orderInfoDubbo.updateOrderStatus(out_trade_no, "", "1", null, null, "1");
				return "SUCCESS";

			} else {
				return "FAIL";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 代付回调
	 * 
	 * @return
	 */
	@RequestMapping(value = "paidNotify", method = RequestMethod.POST)
	@ResponseBody
	public synchronized Object paidNotify() {
		try {
			// 获取请求数据
			java.io.ByteArrayOutputStream inBuffer = new java.io.ByteArrayOutputStream();
			java.io.InputStream input = request.getInputStream();
			byte[] tmp = new byte[1024];
			int len = 0;
			while ((len = input.read(tmp)) > 0) {
				inBuffer.write(tmp, 0, len);
			}
			byte[] requestData = inBuffer.toByteArray();
			String requestJsonStr = new String(requestData, "UTF-8");
			Map<String, String> decryptMap = NetXmlUtils.Xml2Map(requestJsonStr);
			String out_trade_no = decryptMap.get("orderId");
			System.out.println("---代付回调---" + decryptMap.get("resultCode") + "---" + decryptMap.get("resultDesc")
					+ "---" + out_trade_no);
			GjfMemberTrade trade = (GjfMemberTrade) tradeDubbo.findMemberTradeByOrderSn(out_trade_no).getResult();
			if ("00".equals(decryptMap.get("resultCode"))) {
				System.out.println("代付回调地址调用");
				// 执行支付成功后的逻辑代码

				if ("0".equals(trade.getTradeStatus())) {
					tradeDubbo.updateDrawCashStatus(trade.getId(), trade.getToken(), "1", "");
				}
				return "SUCCESS";

			} else if (!"00".equals(decryptMap.get("resultCode"))) {
				if (!"2".equals(trade.getTradeStatus())) {
					tradeDubbo.updateDrawCashStatus(trade.getId(), trade.getToken(), "2", "");
				}
				return "SUCCESS";
			} else {
				return "FAIL";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 天猫订单回调
	 */

	@RequestMapping(value = "tmPayOrderNotify", method = RequestMethod.GET)
	@ResponseBody
	public Object tmPayOrderNotify(String item_title, String pay_price, String commission, String trade_id, String uid,
			String status, String apitype) {
		try {
			System.out.println("进入天猫回调接口:参数item_title=" + item_title + "--pay_price=" + pay_price + "--commission"
					+ commission + "--trade_id=" + trade_id + "--uid=" + uid + "--status=" + status + "--apitype="
					+ apitype);

			if (Long.valueOf(uid) != 0 && uid.length() > 4) {
				String subUid = uid.substring(0, 4);
				if ("1001".equals(subUid)) {
					resultVo = orderInfoDubbo.addTianmaoOrder(item_title, pay_price, commission, trade_id, uid, status,
							apitype);
				} else if ("1002".equals(subUid)) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("item_title", item_title);
					map.put("pay_price", pay_price);
					map.put("commission", commission);
					map.put("trade_id", trade_id);
					map.put("uid", uid);
					map.put("status", status);
					map.put("apitype", apitype);
					// 请求域名
					String host = "http://yn.gzfzsw.top";
					// 请求后缀
					String path = "/gjfeng-web-client/wx/notify/tmPayOrderNotify";
					// 请求类型
					String method = "GET";
					// 设置请求头
					Map<String, String> headers = new HashMap<String, String>();
					headers.put("Content-Type", "text/xml;charset=utf-8");

					HttpResponse response = HttpUtils.doGet(host, path, method, headers, map);

					// 获取response的body
					String str = EntityUtils.toString(response.getEntity());

				} else if ("1003".equals(subUid)) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("item_title", item_title);
					map.put("pay_price", pay_price);
					map.put("commission", commission);
					map.put("trade_id", trade_id);
					map.put("uid", uid);
					map.put("status", status);
					map.put("apitype", apitype);
					// 请求域名
					String host = "http://jfh.jfeimao.com";
					// 请求后缀
					String path = "/gjfeng-web-client/wx/notify/tmPayOrderNotify";
					// 请求类型
					String method = "GET";
					// 设置请求头
					Map<String, String> headers = new HashMap<String, String>();
					headers.put("Content-Type", "text/xml;charset=utf-8");

					HttpResponse response = HttpUtils.doGet(host, path, method, headers, map);

					// 获取response的body
					String str = EntityUtils.toString(response.getEntity());

				} else if ("1004".equals(subUid)) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("item_title", item_title);
					map.put("pay_price", pay_price);
					map.put("commission", commission);
					map.put("trade_id", trade_id);
					map.put("uid", uid);
					map.put("status", status);
					map.put("apitype", apitype);
					// 请求域名
					String host = "http://gz.gjfeng.net";
					// 请求后缀
					String path = "/gjfeng-web-client/wx/notify/tmPayOrderNotify";
					// 请求类型
					String method = "GET";
					// 设置请求头
					Map<String, String> headers = new HashMap<String, String>();
					headers.put("Content-Type", "text/xml;charset=utf-8");

					HttpResponse response = HttpUtils.doGet(host, path, method, headers, map);

					// 获取response的body
					String str = EntityUtils.toString(response.getEntity());
				} else if ("1005".equals(subUid)) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("item_title", item_title);
					map.put("pay_price", pay_price);
					map.put("commission", commission);
					map.put("trade_id", trade_id);
					map.put("uid", uid);
					map.put("status", status);
					map.put("apitype", apitype);
					// 请求域名
					String host = "http://xj.gjfeng.net";
					// 请求后缀
					String path = "/gjfeng-web-client/wx/notify/tmPayOrderNotify";
					// 请求类型
					String method = "GET";
					// 设置请求头
					Map<String, String> headers = new HashMap<String, String>();
					headers.put("Content-Type", "text/xml;charset=utf-8");

					HttpResponse response = HttpUtils.doGet(host, path, method, headers, map);

					// 获取response的body
					String str = EntityUtils.toString(response.getEntity());
				} else if ("1006".equals(subUid)) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("item_title", item_title);
					map.put("pay_price", pay_price);
					map.put("commission", commission);
					map.put("trade_id", trade_id);
					map.put("uid", uid);
					map.put("status", status);
					map.put("apitype", apitype);
					// 请求域名
					String host = "http://yg.gjfeng.com";
					// 请求后缀
					String path = "/gjfeng-web-client/wx/notify/tmPayOrderNotify";
					// 请求类型
					String method = "GET";
					// 设置请求头
					Map<String, String> headers = new HashMap<String, String>();
					headers.put("Content-Type", "text/xml;charset=utf-8");

					HttpResponse response = HttpUtils.doGet(host, path, method, headers, map);

					// 获取response的body
					String str = EntityUtils.toString(response.getEntity());
				} else if ("1007".equals(subUid)) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("item_title", item_title);
					map.put("pay_price", pay_price);
					map.put("commission", commission);
					map.put("trade_id", trade_id);
					map.put("uid", uid);
					map.put("status", status);
					map.put("apitype", apitype);
					// 请求域名
					String host = "http://jin.gjfeng.com";
					// 请求后缀
					String path = "/gjfeng-web-client/wx/notify/tmPayOrderNotify";
					// 请求类型
					String method = "GET";
					// 设置请求头
					Map<String, String> headers = new HashMap<String, String>();
					headers.put("Content-Type", "text/xml;charset=utf-8");

					HttpResponse response = HttpUtils.doGet(host, path, method, headers, map);

					// 获取response的body
					String str = EntityUtils.toString(response.getEntity());
				}

			}
			if (BeanUtil.isValid(item_title) && BeanUtil.isValid(pay_price) && BeanUtil.isValid(commission)
					&& BeanUtil.isValid(trade_id) && BeanUtil.isValid(uid) && BeanUtil.isValid(status)) {
				System.out.println("SUCCESS");
				return "SUCCESS";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "FAIL";
	}

	/**
	 * @描述 商家充值微信支付回调通知
	 * @author Karhs
	 * @date 2017年1月11日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "payMerchantRechargeNotifyWeiXin", method = RequestMethod.POST)
	@ResponseBody
	public Object payMerchantRechargeNotifyWeiXin(HttpServletRequest request) {
		try {

			try {
				// 获取请求数据
				PayResult payResult = XMLConverUtil.convertToObject(PayResult.class, request.getInputStream());
				// 签名验证
				String out_trade_no = payResult.getOut_trade_no();
				if ("SUCCESS".equals(payResult.getResult_code()) && "SUCCESS".equals(payResult.getReturn_code())) {
					System.out.println("商家充值微信支付回调地址调用:" + out_trade_no);

					tradeDubbo.updateRechargeHistoryStatus("1", out_trade_no);

				} else {
					tradeDubbo.updateRechargeHistoryStatus("0", out_trade_no);
					response.getOutputStream().write("error".getBytes());
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("支付回调异常");
			}
			response.getOutputStream().write("success".getBytes());
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, PayNotifyController.class);
		}
		return null;
	}

	/**
	 * @描述 商家赠送代金券微信支付回调通知
	 * @author Karhs
	 * @date 2017年1月11日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "payVouchersNotifyWeiXin", method = RequestMethod.POST)
	@ResponseBody
	public Object payVouchersNotifyWeiXin(HttpServletRequest request) {
		try {

			try {
				// 获取请求数据
				PayResult payResult = XMLConverUtil.convertToObject(PayResult.class, request.getInputStream());
				// 签名验证
				String out_trade_no = payResult.getOut_trade_no();
				if ("SUCCESS".equals(payResult.getResult_code()) && "SUCCESS".equals(payResult.getReturn_code())) {
					System.out.println("商家赠送代金券微信支付回调通知:" + out_trade_no);
					tradeDubbo.updateVoucherHistoryStatus("1", out_trade_no);

				} else {
					tradeDubbo.updateVoucherHistoryStatus("0", out_trade_no);
					response.getOutputStream().write("error".getBytes());
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("支付回调异常");
			}
			response.getOutputStream().write("success".getBytes());
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, PayNotifyController.class);
		}
		return null;
	}

	/**
	 * @描述 商家赠送代金券微信支付回调通知
	 * @author Karhs
	 * @date 2017年1月11日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "payMechantGivetifyWeiXin", method = RequestMethod.POST)
	@ResponseBody
	public Object payMechantGivetifyWeiXin(HttpServletRequest request) {
		try {

			try {
				// 获取请求数据
				PayResult payResult = XMLConverUtil.convertToObject(PayResult.class, request.getInputStream());
				// 签名验证
				String out_trade_no = payResult.getOut_trade_no();
				if ("SUCCESS".equals(payResult.getResult_code()) && "SUCCESS".equals(payResult.getReturn_code())) {
					System.out.println("会员升级赠送微信支付回调通知:" + out_trade_no);

					tradeDubbo.updateMerchantRechargeToMemberHistory("1", out_trade_no);

				} else {
					tradeDubbo.updateMerchantRechargeToMemberHistory("0", out_trade_no);
					response.getOutputStream().write("error".getBytes());
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("支付回调异常");
			}
			response.getOutputStream().write("success".getBytes());
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, PayNotifyController.class);
		}
		return null;
	}

	/**
	 * 商家赠送代金券微信支付回调通知
	 * 
	 * @return
	 */
	@RequestMapping(value = "payMerchantRechargeNotifyAply", method = RequestMethod.POST)
	@ResponseBody
	public Object payMerchantRechargeNotifyAply() {
		try {
			// 获取支付宝请求参数
			Map<String, String> params = getParams(request.getParameterMap());
			if (params.get("notify_id") == null || params.get("notify_id") == "") {//// 判断接受的post通知中有无notify_id，如果有则是异步通知。
				return "no notify message";
			}
			if (!AlipayNotify.verifyResponse(params.get("notify_id")).equals("true")) // 判断成功之后使用getResponse方法判断是否是支付宝发来的异步通知。
			{
				return "fail";
			}
			// if (!AlipayNotify.verifyAppAlipay(params, params.get("sign"))) //
			// 使用支付宝公钥验签
			// {
			// return "fail";
			// }
			if ("TRADE_SUCCESS".equals(params.get("trade_status"))) {
				String outTradeNo = params.get("out_trade_no");
				String payTradeNo = params.get("trade_no");
				System.out.println("支付宝订单回调：" + outTradeNo);
				tradeDubbo.updateRechargeHistoryStatus("1", outTradeNo);
				return "success";// 请不要修改或删除
			}

		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * 商家充值支付宝支付回调通知
	 * 
	 * @return
	 */
	@RequestMapping(value = "payVouchersNotifyAply", method = RequestMethod.POST)
	@ResponseBody
	public Object payVouchersNotifyAply() {
		try {
			// 获取支付宝请求参数
			Map<String, String> params = getParams(request.getParameterMap());
			if (params.get("notify_id") == null || params.get("notify_id") == "") {//// 判断接受的post通知中有无notify_id，如果有则是异步通知。
				return "no notify message";
			}
			if (!AlipayNotify.verifyResponse(params.get("notify_id")).equals("true")) // 判断成功之后使用getResponse方法判断是否是支付宝发来的异步通知。
			{
				return "fail";
			}
			// if (!AlipayNotify.verifyAppAlipay(params, params.get("sign"))) //
			// 使用支付宝公钥验签
			// {
			// return "fail";
			// }
			if ("TRADE_SUCCESS".equals(params.get("trade_status"))) {
				String outTradeNo = params.get("out_trade_no");
				String payTradeNo = params.get("trade_no");
				System.out.println("支付宝订单回调：" + outTradeNo);
				tradeDubbo.updateVoucherHistoryStatus("1", outTradeNo);
				return "success";// 请不要修改或删除
			}

		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * 商家充值支付宝支付回调通知
	 * 
	 * @return
	 */
	@RequestMapping(value = "payMechantGivetifyAply", method = RequestMethod.POST)
	@ResponseBody
	public Object payMechantGivetifyAply() {
		try {
			// 获取支付宝请求参数
			Map<String, String> params = getParams(request.getParameterMap());
			if (params.get("notify_id") == null || params.get("notify_id") == "") {//// 判断接受的post通知中有无notify_id，如果有则是异步通知。
				return "no notify message";
			}
			if (!AlipayNotify.verifyResponse(params.get("notify_id")).equals("true")) // 判断成功之后使用getResponse方法判断是否是支付宝发来的异步通知。
			{
				return "fail";
			}
			// if (!AlipayNotify.verifyAppAlipay(params, params.get("sign"))) //
			// 使用支付宝公钥验签
			// {
			// return "fail";
			// }
			if ("TRADE_SUCCESS".equals(params.get("trade_status"))) {
				String outTradeNo = params.get("out_trade_no");
				String payTradeNo = params.get("trade_no");
				System.out.println("支付宝订单回调：" + outTradeNo);
				tradeDubbo.updateMerchantRechargeToMemberHistory("1", outTradeNo);
				return "success";// 请不要修改或删除
			}

		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * 更新成品池商品信息
	 * 
	 * @param pro
	 * @return
	 */
	@RequestMapping(value = "updateProInfoNotify", method = RequestMethod.POST)
	@ResponseBody
	public Object updateProInfoNotify() {
		try {
			/*java.io.ByteArrayOutputStream inBuffer = new java.io.ByteArrayOutputStream();
			java.io.InputStream input = request.getInputStream();
			byte[] tmp = new byte[1024];
			int len = 0;
			while ((len = input.read(tmp)) > 0) {
				inBuffer.write(tmp, 0, len);
			}
			byte[] requestData = inBuffer.toByteArray();
			String requestJsonStr = new String(requestData, "UTF-8");*/
			StringBuffer sb = new StringBuffer() ; 
			request.setCharacterEncoding("utf-8");
			InputStream is = request.getInputStream();
			BufferedReader reader=null;
			reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));						
			String s = "" ;
			while((s=reader.readLine())!=null){ 
			  sb.append(s) ; 
			} 
			
			String str =sb.toString();
			//String str="{\"goods_id\":4090,\"cat_id\":2,\"goods_sn\":\"284001\",\"goods_name\":\"嘉炖 多功能用途家用美厨陶瓷不含铅镉耐热装冷热交替不开裂健康环保养生煲JDMC25 内蒙古，新疆不发货\",\"brand_id\":0,\"store_count\":9956,\"weight\":0,\"market_price\":\"799.00\",\"shop_price\":\"171.89\",\"vip_price\":\"156.98\",\"keywords\":\"\",\"goods_remark\":\"\",\"goods_content\":\"< img style=\"vertical-align:top;\" src=\" alt=}";

			ProductInfo pro=com.alibaba.fastjson.JSONObject.parseObject(str,ProductInfo.class);
			resultVo = productInfoDubbo.updateProprietaryPro(pro);
			if (resultVo.getCode() == 200) {
				String result = "{\"code\": 200, \"message\": \"success\"}";
				return result;
			} else {
				String result = "{\"code\": 400, \"message\": \"fail\"}";
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
