package cc.messcat.gjfeng.web.sms;

import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cc.messcat.gjfeng.common.exception.LoggerPrint;
import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.util.RandUtil;
import cc.messcat.gjfeng.common.vo.app.SmsVo;
import cc.messcat.gjfeng.common.web.BaseController;
import cc.messcat.gjfeng.dubbo.sms.SmsDubbo;
import cc.messcat.gjfeng.entity.GjfMessageHistory;

@Controller
@RequestMapping("sms/")
public class SmsController extends BaseController {

	@Autowired
	@Qualifier("request")
	private HttpServletRequest request;

	@Autowired
	@Qualifier("smsDubbo")
	private SmsDubbo smsDubbo;

	/**
	 * @描述 发送验证码短信
	 * @author Karhs
	 * @date 2016年10月17日
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value = "send", method = RequestMethod.POST)
	@ResponseBody
	public Object sendCode(@RequestParam("mobile") String mobile) {
		try {
			SmsVo smsVo = smsDubbo.sendMsg(mobile, RandUtil.getRandomStr(6));
			final GjfMessageHistory gjfMessageHistory = new GjfMessageHistory();
			gjfMessageHistory.setAccepter(smsVo.getMobile());
			gjfMessageHistory.setContent(smsVo.getContent());
			gjfMessageHistory.setSendTime(new Date());
			if (smsVo.getCode().equals("2")) {
				request.getSession().setAttribute("SMSCODE", smsVo.getMobile() + smsVo.getContent());
				gjfMessageHistory.setStatus("1");
			} else {
				gjfMessageHistory.setStatus("0");
			}

			new Thread(new Thread() {
				@Override
				public void run() {
					smsDubbo.addMessageHistory(gjfMessageHistory);
				}
			}).start();
			return smsVo;
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, SmsController.class);
		}
		return null;
	}

	/**
	 * 校验手机验证码
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "validate", method = RequestMethod.GET)
	@ResponseBody
	public boolean validateCode(@RequestParam("code") String code, @RequestParam("mobile") String mobile) throws Exception {
		try {
			code = URLDecoder.decode(code, "UTF-8").trim();
			if (code != null) {
				Object smsCode = request.getSession().getAttribute("SMSCODE");
				if (ObjValid.isNotValid(smsCode)) {
					return false;
				}
				if (String.valueOf(smsCode).equals(mobile.trim() + code.trim())) {
					request.getSession().removeAttribute("SMSCODE");
					return true;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

}
