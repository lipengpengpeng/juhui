/*
 * Copyright (c) 2015 Messcat. All rights reserved.
 * 
 */
package cc.messcat.gjfeng.service.sms;

import java.util.Map;

import cc.messcat.gjfeng.common.vo.app.SmsVo;
import cc.messcat.gjfeng.entity.GjfMessageHistory;

public interface SmsService {

	
	/**
	 * 发送手机验证码
	 * @author Karhs
	 * @date 2016-01-28 18:23
	 * @param mobile
	 * @param randomCode
	 * @return
	 */
	public SmsVo sendMobileSms(String mobile,String randomCode);
	
	/**
	 * @描述 发送内容短信
	 * @author Karhs
	 * @date 2017年2月21日
	 * @param mobile
	 * @param content
	 * @return
	 */
	public SmsVo sendContentSms(String mobile,String content);
	
	/**
	 * @描述 发送模板短信
	 * @param map
	 * @return
	 */
	public SmsVo sendContentSms(Map<String,Object> map);

	/**
	 * 添加短信发送记录
	 * @param gjfMessageHistory
	 * @return
	 */
	public void addMessageHistory(GjfMessageHistory gjfMessageHistory);
	
	/**
	 * 下载短信状态
	 */
	public void updateMnsSmsStatus();
}
