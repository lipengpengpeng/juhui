<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<style>
			.headbg1 {
				background: #c9e6f5;
				font-weight: bold;
				padding-left: 15px;
			}
			
			.headbg1 td {
				font-weight: bold;
				text-align: center;
			}
		</style>

		<script>
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 模块 - 子模块
			</div>
			<div class="ropt">
				<a href="gjfOrderInfoAction!newPage.action">新增</a>
			</div>
			<div class="clear"></div>
		</div>
		
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>订单编号</td>
				<td>支付单号</td>
				<td>提货码</td>
				<td>FK:用户ID</td>
				<td>FK:店铺ID</td>
				<td>商品总金额</td>
				<td>订单总金额</td>
				<td>实际支付金额</td>
				<td>线上支付金额</td>
				<td>线下支付金额</td>
				<td>店铺获得金额</td>
				<td>店铺让利金额</td>
				<td>退款金额</td>
				<td>FK:优惠券ID</td>
				<td>支付方式（0:线上余额 1:微信支付 2:支付宝 3:银联支付 4:余额+微信 5:余额+支付宝 6:余额+银联）</td>
				<td>订单生成时间</td>
				<td>订单付款时间</td>
				<td>订单发货时间</td>
				<td>订单完成时间</td>
				<td>订单过期时间</td>
				<td>订单退款时间</td>
				<td>订单取消时间</td>
				<td>订单下单备注</td>
				<td>订单取消原因</td>
				<td>订单类型（0:O2O 1:商城）</td>
				<td>评价状态(0:未评价 1:已评价 2:已过期未评价)</td>
				<td>订单状态（0:未付款 1:已付款 2:已发货 3:已收货 4:已过期 5:已取消 6:已退款)</td>
				<td>退款状态（0:无退款 1:部分退款 2:全部退款)</td>
				<td>删除状态(0:删除 1:正常）</td>
				<td>安全token</td>

				<td>操作</td>
			</tr>

			<c:forEach var="bean" items="${gjfOrderInfos}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.orderSn}</td>
					<td>${bean.paySn}</td>
					<td>${bean.pickupCode}</td>
					<td>${bean.memberId}</td>
					<td>${bean.stordId}</td>
					<td>${bean.goodsTotalAmount}</td>
					<td>${bean.orderTotalAmount}</td>
					<td>${bean.realPayAmount}</td>
					<td>${bean.onlinePayAmount}</td>
					<td>${bean.offlinePayAmount}</td>
					<td>${bean.storeRecAmount}</td>
					<td>${bean.storeBenefitAmount}</td>
					<td>${bean.refundAmount}</td>
					<td>${bean.couponsId}</td>
					<td>${bean.payType}</td>
					<td>${bean.addTime}</td>
					<td>${bean.payTime}</td>
					<td>${bean.deliveryTime}</td>
					<td>${bean.finishedTime}</td>
					<td>${bean.overdueTime}</td>
					<td>${bean.refundTime}</td>
					<td>${bean.cancelTime}</td>
					<td>${bean.remark}</td>
					<td>${bean.cancelReason}</td>
					<td>${bean.orderType}</td>
					<td>${bean.evaluationStatus}</td>
					<td>${bean.orderStatus}</td>
					<td>${bean.refundStatus}</td>
					<td>${bean.isDel}</td>
					<td>${bean.token}</td>

					<td>
						<a href="gjfOrderInfoAction!viewPage.action?id=${bean.id}">查看</a>
						&nbsp; &nbsp;
						<a href="gjfOrderInfoAction!retrieveGjfOrderInfoById.action?id=${bean.id}">编辑</a>
						&nbsp; &nbsp;
						<a href="gjfOrderInfoAction!delGjfOrderInfo.action?id=${bean.id}"
								onclick="{if(confirm('你真的要删除吗?')){return true;}return false;}">删除</a>
					</td>
				</tr>
			</c:forEach>

		</table>

		<c:if test="${not empty gjfOrderInfos}">
			<s:form action="gjfOrderInfoAction!retrieveAllGjfOrderInfos.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>