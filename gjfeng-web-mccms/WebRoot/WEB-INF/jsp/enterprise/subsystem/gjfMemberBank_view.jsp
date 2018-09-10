﻿<%--

	gjfMemberBank_view.jsp

	Create by MCGT

	Create time 2017-01-10

	Version 1.0

	Copyright 2013 Messcat, Inc. All rights reserved.

 --%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>查看模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<script>
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">当前位置: 模块 - 子模块</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="location.href='${ctx}/subsystem/gjfMemberBankAction!retrieveAllGjfMemberBanks.action'"/></div>
		</div>

		<table  align="center" class="listTable3">
				<tr>
					<td class="pn-flabel" width="100px">关联渠道商id</td>
					<td>${gjfMemberBank.memberId}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">关联银行id</td>
					<td>${gjfMemberBank.bankId}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">银行支行名称</td>
					<td>${gjfMemberBank.bankSub}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">银行卡账号</td>
					<td>${gjfMemberBank.bankCard}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">银行卡持有人</td>
					<td>${gjfMemberBank.holder}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">添加时间</td>
					<td>${gjfMemberBank.addTime}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">状态（0：删除 1：正常）</td>
					<td>${gjfMemberBank.status}</td>
				</tr>

		 </table>
	</body>
</html>