<%--

	gjfOrderAddress_edit.jsp

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
				$("#gjfOrderAddressForm").validate({
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
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="location.href='${ctx}/subsystem/gjfOrderAddressAction!retrieveAllGjfOrderAddresss.action'"/></div>
		</div>

		<form action="gjfOrderAddressAction!editGjfOrderAddress.action" method="post" id="gjfOrderAddressForm" name="gjfOrderAddressForm" enctype="multipart/form-data">
			<table  align="center" class="listTable3">
				<input type="hidden" name="id" value="${gjfOrderAddress.id}"/>
				<tr>
					<td class="pn-flabel" width="100px">FK:订单id</td>
					<td><input type="text" id="orderId" name="gjfOrderAddress.orderId" value="${gjfOrderAddress.orderId}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">收货人姓名</td>
					<td><input type="text" id="reciverName" name="gjfOrderAddress.reciverName" value="${gjfOrderAddress.reciverName}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">收货人联系号码</td>
					<td><input type="text" id="reciverMobile" name="gjfOrderAddress.reciverMobile" value="${gjfOrderAddress.reciverMobile}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">FK:收货省份</td>
					<td><input type="text" id="reciverProvinceId" name="gjfOrderAddress.reciverProvinceId" value="${gjfOrderAddress.reciverProvinceId}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">FK:收货城市</td>
					<td><input type="text" id="reciverCityId" name="gjfOrderAddress.reciverCityId" value="${gjfOrderAddress.reciverCityId}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">FK:收货区县</td>
					<td><input type="text" id="reciverAreaId" name="gjfOrderAddress.reciverAreaId" value="${gjfOrderAddress.reciverAreaId}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">运费</td>
					<td><input type="text" id="shippingFeeAmount" name="gjfOrderAddress.shippingFeeAmount" value="${gjfOrderAddress.shippingFeeAmount}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">快递名称</td>
					<td><input type="text" id="courierName" name="gjfOrderAddress.courierName" value="${gjfOrderAddress.courierName}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">物流单号</td>
					<td><input type="text" id="shippingCode" name="gjfOrderAddress.shippingCode" value="${gjfOrderAddress.shippingCode}" /></td>
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