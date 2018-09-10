package cc.messcat.gjfeng.common.pay.alipay.util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayParser;
import com.alipay.api.AlipayRequest;
import com.alipay.api.AlipayResponse;
import com.alipay.api.AlipayUploadRequest;
import com.alipay.api.FileItem;
import com.alipay.api.ResponseEncryptItem;
import com.alipay.api.SignItem;
import com.alipay.api.internal.parser.json.ObjectJsonParser;
import com.alipay.api.internal.parser.xml.ObjectXmlParser;
import com.alipay.api.internal.util.AlipayEncrypt;
import com.alipay.api.internal.util.AlipayHashMap;
import com.alipay.api.internal.util.AlipayLogger;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.internal.util.AlipayUtils;
import com.alipay.api.internal.util.RequestParametersHolder;
import com.alipay.api.internal.util.StringUtils;
import com.alipay.api.internal.util.WebUtils;
import com.alipay.api.internal.util.json.JSONWriter;
import java.io.IOException;
import java.security.Security;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class DefaultAlipayClient implements AlipayClient {
	private String serverUrl;
	private String appId;
	private String privateKey;
	private String prodCode;
	private String format = "json";
	private String sign_type = "RSA";
	private String encryptType = "AES";
	private String encryptKey;
	private String alipayPublicKey;
	private String charset;
	private int connectTimeout = 3000;
	private int readTimeout = 15000;

	static {
		Security.setProperty("jdk.certpath.disabledAlgorithms", "");
	}

	public DefaultAlipayClient(String serverUrl, String appId, String privateKey) {
		this.serverUrl = serverUrl;
		this.appId = appId;
		this.privateKey = privateKey;
		this.alipayPublicKey = null;
	}

	public DefaultAlipayClient(String serverUrl, String appId, String privateKey, String format) {
		this(serverUrl, appId, privateKey);
		this.format = format;
	}

	public DefaultAlipayClient(String serverUrl, String appId, String privateKey, String format, String charset) {
		this(serverUrl, appId, privateKey);
		this.format = format;
		this.charset = charset;
	}

	public DefaultAlipayClient(String serverUrl, String appId, String privateKey, String format, String charset,
			String alipayPulicKey) {
		this(serverUrl, appId, privateKey);
		this.format = format;
		this.charset = charset;
		this.alipayPublicKey = alipayPulicKey;
	}

	public DefaultAlipayClient(String serverUrl, String appId, String privateKey, String format, String charset,
			String alipayPulicKey, String signType) {
		this(serverUrl, appId, privateKey, format, charset, alipayPulicKey);

		this.sign_type = signType;
	}

	public DefaultAlipayClient(String serverUrl, String appId, String privateKey, String format, String charset,
			String alipayPulicKey, String signType, String encryptKey, String encryptType) {
		this(serverUrl, appId, privateKey, format, charset, alipayPulicKey, signType);

		this.encryptType = encryptType;
		this.encryptKey = encryptKey;
	}

	public <T extends AlipayResponse> T execute(AlipayRequest<T> request) throws AlipayApiException {
		return execute(request, null);
	}

	public <T extends AlipayResponse> T execute(AlipayRequest<T> request, String accessToken)
			throws AlipayApiException {
		return execute(request, accessToken, null);
	}

	public <T extends AlipayResponse> T execute(AlipayRequest<T> request, String accessToken, String appAuthToken)
			throws AlipayApiException {
		AlipayParser<T> parser = null;
		if ("xml".equals(this.format)) {
			parser = new ObjectXmlParser(request.getResponseClass());
		} else {
			parser = new ObjectJsonParser(request.getResponseClass());
		}
		return _execute(request, parser, accessToken, appAuthToken);
	}

	public <T extends AlipayResponse> T pageExecute(AlipayRequest<T> request) throws AlipayApiException {
		return pageExecute(request, "POST");
	}

	public <T extends AlipayResponse> T pageExecute(AlipayRequest<T> request, String httpMethod)
			throws AlipayApiException {
		RequestParametersHolder requestHolder = getRequestHolderWithSign(request, null, null);
		if (AlipayLogger.isBizDebugEnabled().booleanValue()) {
			AlipayLogger.logBizDebug(getRedirectUrl(requestHolder));
		}
		T rsp = null;
		try {
			Class<T> clazz = request.getResponseClass();
			rsp = (T) clazz.newInstance();
		} catch (InstantiationException e) {
			AlipayLogger.logBizError(e);
		} catch (IllegalAccessException e) {
			AlipayLogger.logBizError(e);
		}
		if ("GET".equalsIgnoreCase(httpMethod)) {
			rsp.setBody(getRedirectUrl(requestHolder));
		} else {
			String baseUrl = getRequestUrl(requestHolder);
			rsp.setBody(WebUtils.buildForm(baseUrl, requestHolder.getApplicationParams()));
		}
		return rsp;
	}

	public <T extends AlipayResponse> T sdkExecute(AlipayRequest<T> request) throws AlipayApiException {
		RequestParametersHolder requestHolder = getRequestHolderWithSign(request, null, null);
		if (AlipayLogger.isBizDebugEnabled().booleanValue()) {
			AlipayLogger.logBizDebug(getSdkParams(requestHolder));
		}
		T rsp = null;
		try {
			Class<T> clazz = request.getResponseClass();
			rsp = (T) clazz.newInstance();
		} catch (InstantiationException e) {
			AlipayLogger.logBizError(e);
		} catch (IllegalAccessException e) {
			AlipayLogger.logBizError(e);
		}
		rsp.setBody(getSdkParams(requestHolder));
		return rsp;
	}

	public <TR extends AlipayResponse, T extends AlipayRequest<TR>> TR parseAppSyncResult(Map<String, String> result,
			Class<T> requsetClazz) throws AlipayApiException {
		TR tRsp = null;
		String rsp = (String) result.get("result");
		try {
			T request = (T) requsetClazz.newInstance();
			Class<TR> responseClazz = request.getResponseClass();
			if (StringUtils.isEmpty(rsp)) {
				tRsp = (TR) responseClazz.newInstance();
				tRsp.setCode("20000");
				tRsp.setSubCode("ACQ.SYSTEM_ERROR");
				tRsp.setSubMsg((String) result.get("memo"));
			} else {
				AlipayParser<TR> parser = null;
				if ("xml".equals(this.format)) {
					parser = new ObjectXmlParser(responseClazz);
				} else {
					parser = new ObjectJsonParser(responseClazz);
				}
				tRsp = parser.parse(rsp);
				tRsp.setBody(rsp);

				checkResponseSign(request, parser, rsp, tRsp.isSuccess());
				if (!tRsp.isSuccess()) {
					AlipayLogger.logBizError(rsp);
				}
			}
		} catch (RuntimeException e) {
			AlipayLogger.logBizError(rsp);
			throw e;
		} catch (AlipayApiException e) {
			AlipayLogger.logBizError(rsp);
			throw new AlipayApiException(e);
		} catch (InstantiationException e) {
			AlipayLogger.logBizError(rsp);
			throw new AlipayApiException(e);
		} catch (IllegalAccessException e) {
			AlipayLogger.logBizError(rsp);
			throw new AlipayApiException(e);
		}
		return tRsp;
	}

	private <T extends AlipayResponse> RequestParametersHolder getRequestHolderWithSign(AlipayRequest<?> request,
			String accessToken, String appAuthToken) throws AlipayApiException {
		RequestParametersHolder requestHolder = new RequestParametersHolder();
		AlipayHashMap appParams = new AlipayHashMap(request.getTextParams());
		try {
			if ((request.getClass().getMethod("getBizContent", new Class[0]) != null)
					&& (StringUtils.isEmpty((String) appParams.get("biz_content")))
					&& (request.getBizModel() != null)) {
				appParams.put("biz_content", new JSONWriter().write(request.getBizModel(), true));
			}
		} catch (NoSuchMethodException e) {
		} catch (SecurityException e) {
			AlipayLogger.logBizError(e);
		}
		if (request.isNeedEncrypt()) {
			if (StringUtils.isEmpty((String) appParams.get("biz_content"))) {
				throw new AlipayApiException("当前API不支持加密请求");
			}
			if (!StringUtils.areNotEmpty(new String[] { this.encryptKey, this.encryptType })) {
				throw new AlipayApiException(
						"API请求要求加密，则必须设置密钥和密钥类型：encryptKey=" + this.encryptKey + ",encryptType=" + this.encryptType);
			}
			String encryptContent = AlipayEncrypt.encryptContent((String) appParams.get("biz_content"),
					this.encryptType, this.encryptKey, this.charset);

			appParams.put("biz_content", encryptContent);
		}
		if (!StringUtils.isEmpty(appAuthToken)) {
			appParams.put("app_auth_token", appAuthToken);
		}
		requestHolder.setApplicationParams(appParams);
		if (StringUtils.isEmpty(this.charset)) {
			this.charset = "UTF-8";
		}
		AlipayHashMap protocalMustParams = new AlipayHashMap();
		protocalMustParams.put("method", request.getApiMethodName());
		protocalMustParams.put("version", request.getApiVersion());
		protocalMustParams.put("app_id", this.appId);
		protocalMustParams.put("sign_type", this.sign_type);
		protocalMustParams.put("terminal_type", request.getTerminalType());
		protocalMustParams.put("terminal_info", request.getTerminalInfo());
		protocalMustParams.put("notify_url", request.getNotifyUrl());
		protocalMustParams.put("return_url", request.getReturnUrl());
		protocalMustParams.put("charset", this.charset);
		if (request.isNeedEncrypt()) {
			protocalMustParams.put("encrypt_type", this.encryptType);
		}
		Long timestamp = Long.valueOf(System.currentTimeMillis());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		protocalMustParams.put("timestamp", df.format(new Date(timestamp.longValue())));
		requestHolder.setProtocalMustParams(protocalMustParams);

		AlipayHashMap protocalOptParams = new AlipayHashMap();
		protocalOptParams.put("format", this.format);
		protocalOptParams.put("auth_token", accessToken);
		protocalOptParams.put("alipay_sdk", "alipay-sdk-java-dynamicVersionNo");
		protocalOptParams.put("prod_code", request.getProdCode());
		requestHolder.setProtocalOptParams(protocalOptParams);
		if (!StringUtils.isEmpty(this.sign_type)) {
			String signContent = AlipaySignature.getSignatureContent(requestHolder);
			protocalMustParams.put("sign",
					AlipaySignature.rsaSign(signContent, this.privateKey, this.charset, this.sign_type));
		} else {
			protocalMustParams.put("sign", "");
		}
		return requestHolder;
	}

	private String getRequestUrl(RequestParametersHolder requestHolder) throws AlipayApiException {
		StringBuffer urlSb = new StringBuffer(this.serverUrl);
		try {
			String sysMustQuery = WebUtils.buildQuery(requestHolder.getProtocalMustParams(), this.charset);

			String sysOptQuery = WebUtils.buildQuery(requestHolder.getProtocalOptParams(), this.charset);

			urlSb.append("?");
			urlSb.append(sysMustQuery);
			if (((sysOptQuery != null ? 1 : 0) & (sysOptQuery.length() > 0 ? 1 : 0)) != 0) {
				urlSb.append("&");
				urlSb.append(sysOptQuery);
			}
		} catch (IOException e) {
			throw new AlipayApiException(e);
		}
		return urlSb.toString();
	}

	private String getRedirectUrl(RequestParametersHolder requestHolder) throws AlipayApiException {
		StringBuffer urlSb = new StringBuffer(this.serverUrl);
		try {
			Map<String, String> sortedMap = AlipaySignature.getSortedMap(requestHolder);
			String sortedQuery = WebUtils.buildQuery(sortedMap, this.charset);
			String sign = (String) requestHolder.getProtocalMustParams().get("sign");
			urlSb.append("?");
			urlSb.append(sortedQuery);
			if (((sign != null ? 1 : 0) & (sign.length() > 0 ? 1 : 0)) != 0) {
				Map<String, String> signMap = new HashMap();
				signMap.put("sign", sign);
				String signQuery = WebUtils.buildQuery(signMap, this.charset);
				urlSb.append("&");
				urlSb.append(signQuery);
			}
		} catch (IOException e) {
			throw new AlipayApiException(e);
		}
		return urlSb.toString();
	}

	private String getSdkParams(RequestParametersHolder requestHolder) throws AlipayApiException {
		StringBuffer urlSb = new StringBuffer();
		try {
			Map<String, String> sortedMap = AlipaySignature.getSortedMap(requestHolder);
			String sortedQuery = WebUtils.buildQuery(sortedMap, this.charset);
			String sign = (String) requestHolder.getProtocalMustParams().get("sign");
			urlSb.append(sortedQuery);
			if (((sign != null ? 1 : 0) & (sign.length() > 0 ? 1 : 0)) != 0) {
				Map<String, String> signMap = new HashMap();
				signMap.put("sign", sign);
				String signQuery = WebUtils.buildQuery(signMap, this.charset);
				urlSb.append("&");
				urlSb.append(signQuery);
			}
		} catch (IOException e) {
			throw new AlipayApiException(e);
		}
		return urlSb.toString();
	}

	private <T extends AlipayResponse> T _execute(AlipayRequest<T> request, AlipayParser<T> parser, String authToken,
			String appAuthToken) throws AlipayApiException {
		Map<String, Object> rt = doPost(request, authToken, appAuthToken);
		if (rt == null) {
			return null;
		}
		T tRsp = null;
		try {
			ResponseEncryptItem responseItem = encryptResponse(request, rt, parser);

			tRsp = parser.parse(responseItem.getRealContent());
			tRsp.setBody(responseItem.getRealContent());

			//checkResponseSign(request, parser, responseItem.getRespContent(), tRsp.isSuccess());
		} catch (RuntimeException e) {
			AlipayLogger.logBizError((String) rt.get("rsp"));
			throw e;
		} catch (AlipayApiException e) {
			AlipayLogger.logBizError((String) rt.get("rsp"));
			throw new AlipayApiException(e);
		}
		tRsp.setParams((AlipayHashMap) rt.get("textParams"));
		if (!tRsp.isSuccess()) {
			AlipayLogger.logErrorScene(rt, tRsp, "");
		}
		return tRsp;
	}

	private <T extends AlipayResponse> Map<String, Object> doPost(AlipayRequest<T> request, String accessToken,
			String appAuthToken) throws AlipayApiException {
		Map<String, Object> result = new HashMap();
		RequestParametersHolder requestHolder = getRequestHolderWithSign(request, accessToken, appAuthToken);

		String url = getRequestUrl(requestHolder);
		if (AlipayLogger.isBizDebugEnabled().booleanValue()) {
			AlipayLogger.logBizDebug(getRedirectUrl(requestHolder));
		}
		String rsp = null;
		try {
			if ((request instanceof AlipayUploadRequest)) {
				AlipayUploadRequest<T> uRequest = (AlipayUploadRequest) request;
				Map<String, FileItem> fileParams = AlipayUtils.cleanupMap(uRequest.getFileParams());
				rsp = WebUtils.doPost(url, requestHolder.getApplicationParams(), fileParams, this.charset,
						this.connectTimeout, this.readTimeout);
			} else {
				rsp = WebUtils.doPost(url, requestHolder.getApplicationParams(), this.charset, this.connectTimeout,
						this.readTimeout);
			}
		} catch (IOException e) {
			throw new AlipayApiException(e);
		}
		result.put("rsp", rsp);
		result.put("textParams", requestHolder.getApplicationParams());
		result.put("protocalMustParams", requestHolder.getProtocalMustParams());
		result.put("protocalOptParams", requestHolder.getProtocalOptParams());
		result.put("url", url);
		return result;
	}

	private <T extends AlipayResponse> void checkResponseSign(AlipayRequest<T> request, AlipayParser<T> parser,
			String responseBody, boolean responseIsSucess) throws AlipayApiException {
		if (!StringUtils.isEmpty(this.alipayPublicKey)) {
			SignItem signItem = parser.getSignItem(request, responseBody);
			if (signItem == null) {
				throw new AlipayApiException("sign check fail: Body is Empty!");
			}
			if ((responseIsSucess) || ((!responseIsSucess) && (!StringUtils.isEmpty(signItem.getSign())))) {
				boolean rsaCheckContent = AlipaySignature.rsaCheck(signItem.getSignSourceDate(), signItem.getSign(),
						this.alipayPublicKey, this.charset, this.sign_type);
				if (!rsaCheckContent) {
					if ((!StringUtils.isEmpty(signItem.getSignSourceDate()))
							&& (signItem.getSignSourceDate().contains("\\/"))) {
						String srouceData = signItem.getSignSourceDate().replace("\\/", "/");

						boolean jsonCheck = AlipaySignature.rsaCheck(srouceData, signItem.getSign(),
								this.alipayPublicKey, this.charset, this.sign_type);
						if (!jsonCheck) {
							throw new AlipayApiException("sign check fail: check Sign and Data Fail！JSON also！");
						}
					} else {
						throw new AlipayApiException("sign check fail: check Sign and Data Fail!");
					}
				}
			}
		}
	}

	private <T extends AlipayResponse> ResponseEncryptItem encryptResponse(AlipayRequest<T> request,
			Map<String, Object> rt, AlipayParser<T> parser) throws AlipayApiException {
		String responseBody = (String) rt.get("rsp");

		String realBody = null;
		if (request.isNeedEncrypt()) {
			realBody = parser.encryptSourceData(request, responseBody, this.format, this.encryptType, this.encryptKey,
					this.charset);
		} else {
			realBody = (String) rt.get("rsp");
		}
		return new ResponseEncryptItem(responseBody, realBody);
	}
}
