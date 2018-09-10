package cc.messcat.gjfeng.util;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cc.messcat.gjfeng.common.constant.CommonProperties;
import cc.messcat.gjfeng.common.vo.app.SmsVo;

public class SendSMSUtil {

	/**
	 * 发送验证码短信
	 * 
	 * @param mobile
	 * @param randStr
	 * @return
	 */
	public static SmsVo sendMSG(String mobile, String randStr) {
		HttpClient client = new HttpClient();
		String host = CommonProperties.SMS_HOST;
		String account = CommonProperties.SMS_ACCOUNT;
		String password = CommonProperties.SMS_PWD;
		String charset = CommonProperties.SMS_CHARSET;
		String contentType = CommonProperties.SMS_CONTENTTYPE;

		/*
		 * String host="http://121.199.16.178/webservice/sms.php?method=";
		 * String account="cf_kdzc"; String password="zk34172599"; String
		 * charset="UTF-8"; String
		 * contentType="application/x-www-form-urlencoded;charset=UTF-8";
		 */

		PostMethod method = new PostMethod(host + "Submit");
		String code = null;
		String msg = null;
		client.getParams().setContentCharset(charset);
		method.setRequestHeader("ContentType", contentType);

		String content = new String("您的验证码是：" + randStr + "。请不要把验证码泄露给其他人。");
		// 提交短信 ， 密码可以使用明文密码或使用32位MD5加密
		NameValuePair[] data = { new NameValuePair("account", account), new NameValuePair("password", password),
			new NameValuePair("mobile", mobile), new NameValuePair("content", content) };
		method.setRequestBody(data);

		try {
			client.executeMethod(method);

			String SubmitResult = method.getResponseBodyAsString();

			Document doc = DocumentHelper.parseText(SubmitResult);
			Element root = doc.getRootElement();

			code = root.elementText("code");
			msg = root.elementText("msg");
			String smsid = root.elementText("smsid");
			System.out.println("返回值:" + code + "-----------查询结果描述:" + msg + "------------消息id:" + smsid);

			if (code == "2")
				System.out.println("短信提交成功：" + content);

		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		System.out.println("短信提交成功：" + randStr);
		return new SmsVo(mobile, new Date(), randStr, code, msg);
	}
	
	/**
	 * 发送验证码短信
	 * 
	 * @param mobile
	 * @param randStr
	 * @return
	 */
	public static SmsVo sendMsgContent(String mobile, String content) {
		HttpClient client = new HttpClient();
		String host = CommonProperties.SMS_HOST;
		String account = CommonProperties.SMS_ACCOUNT;
		String password = CommonProperties.SMS_PWD;
		String charset = CommonProperties.SMS_CHARSET;
		String contentType = CommonProperties.SMS_CONTENTTYPE;
		PostMethod method = new PostMethod(host + "Submit");
		String code = null;
		String msg = null;
		client.getParams().setContentCharset(charset);
		method.setRequestHeader("ContentType", contentType);

		// 提交短信 ， 密码可以使用明文密码或使用32位MD5加密
		NameValuePair[] data = { new NameValuePair("account", account), new NameValuePair("password", password),
			new NameValuePair("mobile", mobile), new NameValuePair("content", content) };
		method.setRequestBody(data);

		try {
			client.executeMethod(method);
			String SubmitResult = method.getResponseBodyAsString();
			Document doc = DocumentHelper.parseText(SubmitResult);
			Element root = doc.getRootElement();
			code = root.elementText("code");
			msg = root.elementText("msg");
			String smsid = root.elementText("smsid");
			System.out.println("返回值:" + code + "-----------查询结果描述:" + msg + "------------消息id:" + smsid);

		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return new SmsVo(mobile, new Date(), content, "2", "发送成功");
	}

	/**
	 * 获取剩余可发信息数量
	 * 
	 * @return
	 * @throws DocumentException
	 */
	public static String GetNum() throws DocumentException {
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(CommonProperties.SMS_HOST + "GetNum");
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");

		// 多个手机号码请用英文,号隔开
		// 提交短信
		NameValuePair[] data = { new NameValuePair("account", CommonProperties.SMS_ACCOUNT),
			new NameValuePair("password", CommonProperties.SMS_PWD) };

		method.setRequestBody(data);
		String num = "";
		try {
			client.executeMethod(method);

			String GetNumResult = method.getResponseBodyAsString();

			Document doc = DocumentHelper.parseText(GetNumResult);
			Element root = doc.getRootElement();

			String code = root.elementText("code");
			String msg = root.elementText("msg");
			num = root.elementText("num");

			System.out.println("返回值:" + code);
			System.out.println("查询结果描述:" + msg);
			System.out.println("剩余短信数量:" + num);

		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return num;
	}
}