package cc.messcat.gjfeng.common.jd;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;

import cc.messcat.gjfeng.common.jd.bean.CatDetail;
import cc.messcat.gjfeng.common.jd.bean.Result;
import cc.messcat.gjfeng.common.netFriends.SignUtils;

public class CartoomJdUtil {

	private static String serverURL = "http://api.shanghai.com.cn";

	private static String agentUser = "52935701c32c45768c0ddb0b2b149d25";

	private static String agentKey = "";

	/**
	 * 請求接口獲取數據
	 * 
	 * @param url
	 * @param requestPath
	 * @param data
	 * @return
	 */
	public static String getData(String url, String requestPath, Map<String, String> data) {
		String method = "POST";
		HttpURLConnection conn = null;
		InputStream in = null;
		PrintWriter out = null;
		try {
			URL realURL = new URL(url);
			HttpURLConnection.setFollowRedirects(true);
			conn = (HttpURLConnection) realURL.openConnection();
			conn.setRequestMethod(method);
			conn.setDoOutput(true);
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			conn.setRequestProperty("Content-Type", "text/json;charset=UTF-8");
			// 传递业务参
			String JsonString = "";
			if (data != null) {
				JsonString = JSON.toJSONString(JSON.toJSON(data));
			}

			out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "utf-8"));
			IOUtils.copy(new ByteArrayInputStream(JsonString.getBytes("utf-8")), out, "utf-8");
			out.flush();

			int status = conn.getResponseCode();
			if (status >= 200 && status < 400) {
				in = conn.getInputStream();
				StringWriter w = new StringWriter();
				IOUtils.copy(new InputStreamReader(in, "UTF-8"), w);
				String jsonData = w.toString();
				System.out.println("成功返回" + jsonData);
				return jsonData;
			} else {
				in = conn.getErrorStream();
				StringWriter w = new StringWriter();
				IOUtils.copy(new InputStreamReader(in, "UTF-8"), w);
				String jsonData = w.toString();
				System.out.println("失败返回" + jsonData);
				return jsonData;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源和连接
			// IOUtils.close(conn);
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(out);
		}
		return null;
	}

	/**
	 *獲取簽名
	 * @param data
	 */
	public static String getSign(Map<String, String> data){
		//簽名
		Map<String, String> params = SignUtils.paraFilter(data);
		StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
		SignUtils.buildPayParams(buf, params, false);
		String preStr = buf.toString()+"&agentKey="+agentKey;
		String sign = MD5.sign(preStr.toUpperCase(), "utf-8");
		return sign;
	}

	/**
	 * 接入京东商品数据
	 */

	public static CatDetail addTestJdProductInfo() {
		// 获取大类数据
		String requestPath = "/api/v1.0/goods/category";
		String url = serverURL + requestPath;

		Map<String, String> data = new HashMap<>();
		data.put("agentUser", agentUser);
		String sign=getSign(data);
		data.put("sign", sign);
		String categoryJson = CartoomJdUtil.getData(url, requestPath, data);
		// 把json字符串转为json对象
		com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(categoryJson);

		Result result = jsonObject.getObject("result", Result.class);
		if ("0000".equals(result.getResultCode()) && "1".equals(result.getSuccess())) {
			// 获取全部分类
			CatDetail catDetail = jsonObject.getObject("detail", CatDetail.class);
			return catDetail;
		}
		return null;
	}

}
