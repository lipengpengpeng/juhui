﻿<%--

	gjfOrderAddress_view.jsp

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
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="location.href='${ctx}/subsystem/gjfOrderAddressAction!retrieveAllGjfOrderAddresss.action'"/></div>
		</div>

		<table  align="center" class="listTable3">
				<tr>
					<td class="pn-flabel" width="100px">FK:订单id</td>
					<td>${gjfOrderAddress.orderId}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">收货人姓名</td>
					<td>${gjfOrderAddress.reciverName}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">收货人联系号码</td>
					<td>${gjfOrderAddress.reciverMobile}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">FK:收货省份</td>
					<td>${gjfOrderAddress.reciverProvinceId}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">FK:收货城市</td>
					<td>${gjfOrderAddress.reciverCityId}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">FK:收货区县</td>
					<td>${gjfOrderAddress.reciverAreaId}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">运费</td>
					<td>${gjfOrderAddress.shippingFeeAmount}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">快递名称</td>
					<td>${gjfOrderAddress.courierName}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">物流单号</td>
					<td>${gjfOrderAddress.shippingCode}</td>
				</tr>

		 </table>
	</body>
</html>