<%--

	gjfOrderLog_view.jsp

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
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="location.href='${ctx}/subsystem/gjfOrderLogAction!retrieveAllGjfOrderLogs.action'"/></div>
		</div>

		<table  align="center" class="listTable3">
				<tr>
					<td class="pn-flabel" width="100px">FK:订单id</td>
					<td>${gjfOrderLog.orderId}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">文字描述</td>
					<td>${gjfOrderLog.logMsg}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">处理时间</td>
					<td>${gjfOrderLog.logTime}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">操作角色</td>
					<td>${gjfOrderLog.logRole}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">操作人</td>
					<td>${gjfOrderLog.logUserName}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">订单状态（0:未付款 1:已付款 2:已发货 3:已收货 4:已过期 5:已取消 6:已退款）</td>
					<td>${gjfOrderLog.logOrderStatus}</td>
				</tr>

		 </table>
	</body>
</html>