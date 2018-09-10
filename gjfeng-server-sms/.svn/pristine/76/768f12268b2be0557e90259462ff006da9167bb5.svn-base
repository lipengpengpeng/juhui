package cc.messcat.gjfeng.service.sms;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cc.messcat.gjfeng.common.constant.CommonProperties;
import cc.messcat.gjfeng.common.vo.app.SmsVo;
import cc.messcat.gjfeng.dao.sms.SmsDao;
import cc.messcat.gjfeng.entity.GjfMessageHistory;
import cc.messcat.gjfeng.util.SMSUtil;
import cc.messcat.gjfeng.util.SendMNSUtil;
import cc.messcat.gjfeng.util.SendSMSUtil;

/**
 * @author Administrator
 *
 */
@Service("smsService")
public class SmsServiceImpl implements SmsService {

	@Autowired
	@Qualifier("smaDao")
	private SmsDao smaDao;

	/*
	 * 发送手机验证码
	 * 
	 * @see
	 * cc.messcat.jfeimao.service.sms.SmsService#sendMobileSms(java.lang.String)
	 */
	@Override
	public SmsVo sendMobileSms(String mobile, String randomCode) {
		Map<String, String> contents = new HashMap<String, String>();
		String cotent = null;
		contents.put("variable0", randomCode);
		boolean result = SMSUtil.sendMessage(mobile, contents, CommonProperties.MNS_TEMPLATE_CODE);
		if (result) {
			cotent = contents.get("variable0");
			return new SmsVo(mobile, new Date(), cotent, "2", "");
		}
		return new SmsVo(mobile, new Date(), cotent, "1", "发送失败");

	}

	/*
	 * 发送内容短信
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.sms.SmsService#sendContentSms(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public SmsVo sendContentSms(String mobile, String content) {
		Map<String, String> contents = new HashMap<String, String>();
		String code = null;
		contents.put("variable0", content);
		boolean result = SMSUtil.sendMessage(mobile, contents, CommonProperties.MNS_TEMPLATE_CODE);
		if (result) {
			code = contents.get("variable0");
			return new SmsVo(mobile, new Date(), code, "2", "发送成功");
		}
		return new SmsVo(mobile, new Date(), code, "1", "发送失败");
	}

	@Override
	public SmsVo sendContentSms(Map<String, Object> map) {
		//return SendMNSUtil.sendMSG(map);
		Map<String, String> contents = new HashMap<String, String>();
		String code = null;
		contents.put("variable0", String.valueOf(map.get("variable0")));
		contents.put("variable1", String.valueOf(map.get("variable1")));
		System.out.println("电话号码："+String.valueOf(map.get("mobile"))+"***variable0:"+String.valueOf(map.get("variable0"))+"****temp:"+String.valueOf(map.get("templateCode")));
		boolean result =SMSUtil.sendMessage(String.valueOf(map.get("mobile")), contents, String.valueOf(map.get("templateCode")));
		if (result) {
			code = contents.get("variable0");
			return new SmsVo(String.valueOf(map.get("mobile")), new Date(), code, "2", "发送成功");
		}
		return new SmsVo(String.valueOf(map.get("mobile")), new Date(), code, "1", "发送失败");
		// TODO Auto-generated method stub
	}

	/*
	 * 添加短信发送记录
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.sms.SmsService#addMessageHistory(cc.messcat.
	 * gjfeng.entity.GjfMessageHistory)
	 */
	@Override
	public void addMessageHistory(GjfMessageHistory gjfMessageHistory) {
		smaDao.save(gjfMessageHistory);
	}

	@Override
	public void updateMnsSmsStatus() {
		try {
			List<Map<String, String>> list = SendMNSUtil.getQueueMessage();
			for (Map<String, String> map : list) {
				final GjfMessageHistory messageHistory = new GjfMessageHistory();
				messageHistory.setAccepter(map.get("receiver"));
				messageHistory.setStatus("2".equals(map.get("state")) ? "0" : "1");
				messageHistory.setContent(map.get("err_code") != null ? map.get("err_code") : map.get("template_code"));
				messageHistory.setSendTime(new Date());
				// System.out.println("messageID:"+map.get("messageID"));
				addMessageHistory(messageHistory);
				// System.out.println("smsService after");
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
