<%--

	gjfMemberAddress_edit.jsp

	Create by MCGT

	Create time 2017-01-10

	Version 1.0

	Copyright 2013 Messcat, Inc. All rights reserved.

 --%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>更新模板</title>
		<script>
			$(document).ready(function(){
				$("#gjfMemberAddressForm").validate({
					 rules: { 
			
					},
					success: function(label) {
						label.html("&nbsp;").addClass("checked");
			        }	
				});
				
			});		
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">当前位置: 模块 - 子模块</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="location.href='${ctx}/subsystem/gjfMemberAddressAction!retrieveAllGjfMemberAddresss.action'"/></div>
		</div>

		<form action="gjfMemberAddressAction!editGjfMemberAddress.action" method="post" id="gjfMemberAddressForm" name="gjfMemberAddressForm" enctype="multipart/form-data">
			<table  align="center" class="listTable3">
				<input type="hidden" name="id" value="${gjfMemberAddress.id}"/>
				<tr>
					<td class="pn-flabel" width="100px">FK:用户ID</td>
					<td><input type="text" id="memberId" name="gjfMemberAddress.memberId" value="${gjfMemberAddress.memberId}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">收货人姓名</td>
					<td><input type="text" id="consigneeName" name="gjfMemberAddress.consigneeName" value="${gjfMemberAddress.consigneeName}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">收货人性别(1:男 2:女)</td>
					<td><input type="text" id="consigneeSex" name="gjfMemberAddress.consigneeSex" value="${gjfMemberAddress.consigneeSex}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">联系号码</td>
					<td><input type="text" id="mobile" name="gjfMemberAddress.mobile" value="${gjfMemberAddress.mobile}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">FK:省份id</td>
					<td><input type="text" id="proviceId" name="gjfMemberAddress.proviceId" value="${gjfMemberAddress.proviceId}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">FK:城市id</td>
					<td><input type="text" id="cityId" name="gjfMemberAddress.cityId" value="${gjfMemberAddress.cityId}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">FK:区县id</td>
					<td><input type="text" id="areaId" name="gjfMemberAddress.areaId" value="${gjfMemberAddress.areaId}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">详细街道地址</td>
					<td><input type="text" id="addressDetail" name="gjfMemberAddress.addressDetail" value="${gjfMemberAddress.addressDetail}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">邮编</td>
					<td><input type="text" id="zipCode" name="gjfMemberAddress.zipCode" value="${gjfMemberAddress.zipCode}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">是否默认收货地址（0:否 1:是）</td>
					<td><input type="text" id="isDefault" name="gjfMemberAddress.isDefault" value="${gjfMemberAddress.isDefault}" /></td>
				</tr>

				<tr>
					<td></td>
					<td>
						<input type="submit" class="defaultButton" value=" 提 交 "/>
					</td>
				</tr>
		 	</table>
		</form>
	</body>
</html>