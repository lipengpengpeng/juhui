<%--

	gjfMemberAddress_view.jsp

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
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="location.href='${ctx}/subsystem/gjfMemberAddressAction!retrieveAllGjfMemberAddresss.action'"/></div>
		</div>

		<table  align="center" class="listTable3">
				<tr>
					<td class="pn-flabel" width="100px">FK:用户ID</td>
					<td>${gjfMemberAddress.memberId}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">收货人姓名</td>
					<td>${gjfMemberAddress.consigneeName}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">收货人性别(1:男 2:女)</td>
					<td>${gjfMemberAddress.consigneeSex}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">联系号码</td>
					<td>${gjfMemberAddress.mobile}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">FK:省份id</td>
					<td>${gjfMemberAddress.proviceId}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">FK:城市id</td>
					<td>${gjfMemberAddress.cityId}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">FK:区县id</td>
					<td>${gjfMemberAddress.areaId}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">详细街道地址</td>
					<td>${gjfMemberAddress.addressDetail}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">邮编</td>
					<td>${gjfMemberAddress.zipCode}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">是否默认收货地址（0:否 1:是）</td>
					<td>${gjfMemberAddress.isDefault}</td>
				</tr>

		 </table>
	</body>
</html>