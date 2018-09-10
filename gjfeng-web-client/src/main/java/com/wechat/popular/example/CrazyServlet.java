package com.wechat.popular.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import cc.messcat.gjfeng.common.util.SignUtil;

public class CrazyServlet{
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	
	public String isToken(){
		/*HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();*/
		// 微信加密签名  
		String signature = request.getParameter("signature");  
		// 时间戳  
		String timestamp = request.getParameter("timestamp");  
		// 随机数  
		String nonce = request.getParameter("nonce");  
		// 随机字符串  
		String echostr = request.getParameter("echostr");  
		
		//this.LOG.info("inToken", signature + ":" + timestamp +":"+ nonce + ":" + echostr);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {  
		    out.print(echostr);  
		}  
		out.close();
		out = null;
		
		return "";
	}
}
