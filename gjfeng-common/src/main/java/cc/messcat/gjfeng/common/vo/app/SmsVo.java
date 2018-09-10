package cc.messcat.gjfeng.common.vo.app;

import java.io.Serializable;
import java.util.Date;

public class SmsVo implements Serializable{

	private static final long serialVersionUID = 1L;
	private String mobile;
	private Date sendDate;
	private String content;
	private String code;
	private String msg;
	
	public SmsVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SmsVo(String mobile, Date sendDate, String content, String code, String msg) {
		super();
		this.mobile = mobile;
		this.sendDate = sendDate;
		this.content = content;
		this.code = code;
		this.msg = msg;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
