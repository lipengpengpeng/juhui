package cc.messcat.gjfeng.common.exception;

import cc.messcat.gjfeng.common.vo.app.ResultVo;
import net.sf.json.JSONObject;

public class MyException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private int code;
	private String msg;
	private Object result;
	
	public MyException() {
		super();
	}

	public MyException(int code) {
		super(JSONObject.fromObject(new ResultVo(code, null, null)).toString());
		this.code = code;
	}

	public MyException(int code, String msg) {
		super(JSONObject.fromObject(new ResultVo(code, msg, null)).toString());
		this.code = code;
		this.msg = msg;
	}

	public MyException(int code, String msg, Object result) {
		super(JSONObject.fromObject(new ResultVo(code, msg, result)).toString());
		this.code = code;
		this.msg = msg;
		this.result = result;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}
