<%--

	gjfOrderInfo_new.jsp

	Create by MCGT

	Create time 2017-01-10

	Version 1.0

	Copyright 2013 Messcat, Inc. All rights reserved.

 --%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>添加模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<script>
			$(document).ready(function(){
				$("#gjfOrderInfoForm").validate({
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
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="location.href='${ctx}/subsystem/gjfOrderInfoAction!retrieveAllGjfOrderInfos.action'"/></div>
		</div>

		<form action="gjfOrderInfoAction!newGjfOrderInfo.action" method="post" id="gjfOrderInfoForm" name="gjfOrderInfoForm" enctype="multipart/form-data">
			<table  align="center" class="listTable3">
				<tr>
					<td class="pn-flabel" width="100px">订单编号</td>
					<td><input type="text" id="orderSn" name="gjfOrderInfo.orderSn" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">支付单号</td>
					<td><input type="text" id="paySn" name="gjfOrderInfo.paySn" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">提货码</td>
					<td><input type="text" id="pickupCode" name="gjfOrderInfo.pickupCode" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">FK:用户ID</td>
					<td><input type="text" id="memberId" name="gjfOrderInfo.memberId" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">FK:店铺ID</td>
					<td><input type="text" id="stordId" name="gjfOrderInfo.stordId" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品总金额</td>
					<td><input type="text" id="goodsTotalAmount" name="gjfOrderInfo.goodsTotalAmount" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">订单总金额</td>
					<td><input type="text" id="orderTotalAmount" name="gjfOrderInfo.orderTotalAmount" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">实际支付金额</td>
					<td><input type="text" id="realPayAmount" name="gjfOrderInfo.realPayAmount" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">线上支付金额</td>
					<td><input type="text" id="onlinePayAmount" name="gjfOrderInfo.onlinePayAmount" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">线下支付金额</td>
					<td><input type="text" id="offlinePayAmount" name="gjfOrderInfo.offlinePayAmount" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺获得金额</td>
					<td><input type="text" id="storeRecAmount" name="gjfOrderInfo.storeRecAmount" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺让利金额</td>
					<td><input type="text" id="storeBenefitAmount" name="gjfOrderInfo.storeBenefitAmount" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">退款金额</td>
					<td><input type="text" id="refundAmount" name="gjfOrderInfo.refundAmount" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">FK:优惠券ID</td>
					<td><input type="text" id="couponsId" name="gjfOrderInfo.couponsId" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">支付方式（0:线上余额 1:微信支付 2:支付宝 3:银联支付 4:余额+微信 5:余额+支付宝 6:余额+银联）</td>
					<td><input type="text" id="payType" name="gjfOrderInfo.payType" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">订单生成时间</td>
					<td><input type="text" id="addTime" name="gjfOrderInfo.addTime" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">订单付款时间</td>
					<td><input type="text" id="payTime" name="gjfOrderInfo.payTime" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">订单发货时间</td>
					<td><input type="text" id="deliveryTime" name="gjfOrderInfo.deliveryTime" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">订单完成时间</td>
					<td><input type="text" id="finishedTime" name="gjfOrderInfo.finishedTime" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">订单过期时间</td>
					<td><input type="text" id="overdueTime" name="gjfOrderInfo.overdueTime" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">订单退款时间</td>
					<td><input type="text" id="refundTime" name="gjfOrderInfo.refundTime" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">订单取消时间</td>
					<td><input type="text" id="cancelTime" name="gjfOrderInfo.cancelTime" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">订单下单备注</td>
					<td><input type="text" id="remark" name="gjfOrderInfo.remark" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">订单取消原因</td>
					<td><input type="text" id="cancelReason" name="gjfOrderInfo.cancelReason" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">订单类型（0:O2O 1:商城）</td>
					<td><input type="text" id="orderType" name="gjfOrderInfo.orderType" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">评价状态(0:未评价 1:已评价 2:已过期未评价)</td>
					<td><input type="text" id="evaluationStatus" name="gjfOrderInfo.evaluationStatus" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">订单状态（0:未付款 1:已付款 2:已发货 3:已收货 4:已过期 5:已取消 6:已退款)</td>
					<td><input type="text" id="orderStatus" name="gjfOrderInfo.orderStatus" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">退款状态（0:无退款 1:部分退款 2:全部退款)</td>
					<td><input type="text" id="refundStatus" name="gjfOrderInfo.refundStatus" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">删除状态(0:删除 1:正常）</td>
					<td><input type="text" id="isDel" name="gjfOrderInfo.isDel" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">安全token</td>
					<td><input type="text" id="token" name="gjfOrderInfo.token" /></td>
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