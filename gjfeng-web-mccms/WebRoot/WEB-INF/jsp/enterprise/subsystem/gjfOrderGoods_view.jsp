<%--

	gjfOrderGoods_view.jsp

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
			<div class="rpos">当前位置:交易管理- 订单详情</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="location.href='${ctx}/subsystem/orderInfoAction!findAllOrderInfo.action'"/></div>
		</div>

		<table  align="center" class="listTable3">
		         <%-- <tr>
					<td class="pn-flabel" width="100px">商品名称</td>
					<td>${resultVo.result.gjfOrderGoods.goodsName}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品属性</td>
					<td>${resultVo.result.gjfOrderGoods.goodsAttr}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品价格</td>
					<td>${resultVo.result.gjfOrderGoods.goodsAmount}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品支付价格</td>
					<td>${resultVo.result.gjfOrderGoods.goodsPayAmount}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">购买商品数量</td>
					<td>${resultVo.result.gjfOrderGoods.goodsNum}</td>
				</tr>
				 --%>
				<tr>
					<td class="pn-flabel" width="100px">订单编号</td>
					<td>${resultVo.result.gjfOrderInfo.orderSn}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">支付单号</td>
					<td>${resultVo.result.gjfOrderInfo.paySn}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户名</td>
					<td>${resultVo.result.gjfOrderInfo.memberId.name}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户手机号码</td>
					<td>${resultVo.result.gjfOrderInfo.memberId.mobile}</td>
				</tr>
				<%-- <tr>
					<td class="pn-flabel" width="100px">店铺到账金额</td>
					<td>${resultVo.result.gjfOrderGoods.storeRecAmount}</td>
				</tr>
				
				<tr>
					<td class="pn-flabel" width="100px">店铺让利金额</td>
					<td>${resultVo.result.gjfOrderGoods.storeBenefitAmount}</td>
				</tr> --%>
				
				<%-- <tr>
					<td class="pn-flabel" width="100px">店铺名称</td>
					<td>${resultVo.result.gjfOrderInfo.storeId.stroeName}</td>
				</tr> --%>
				<tr>
					<td class="pn-flabel" width="100px">商品总金额</td>
					<td>${resultVo.result.gjfOrderInfo.goodsTotalAmount}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">订单总金额</td>
					<td>${resultVo.result.gjfOrderInfo.orderTotalAmount}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">实际支付金额</td>
					<td>${resultVo.result.gjfOrderInfo.realPayAmount}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">线上支付金额</td>
					<td>${resultVo.result.gjfOrderInfo.onlinePayAmount}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">线下支付金额</td>
					<td>${resultVo.result.gjfOrderInfo.offlinePayAmount}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺获得金额</td>
					<td>${resultVo.result.gjfOrderInfo.storeRecAmount}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺让利金额</td>
					<td>${resultVo.result.gjfOrderInfo.storeBenefitAmount}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">退款金额</td>
					<td>${resultVo.result.gjfOrderInfo.refundAmount}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">支付方式</td>
					<c:if test="${resultVo.result.gjfOrderInfo.payType==0}">
					   <td>线上余额</td>
					</c:if>
					<c:if test="${resultVo.result.gjfOrderInfo.payType==1}">
					   <td>微信支付</td>
					</c:if>
					<c:if test="${resultVo.result.gjfOrderInfo.payType==2}">
					   <td>支付宝</td>
					</c:if>
					<c:if test="${resultVo.result.gjfOrderInfo.payType==3}">
					   <td>银联支付</td>
					</c:if>
					<c:if test="${resultVo.result.gjfOrderInfo.payType==4}">
					   <td>余额+微信</td>
					</c:if>
					<c:if test="${resultVo.result.gjfOrderInfo.payType==5}">
					   <td>余额+支付宝</td>
					</c:if>
					<c:if test="${resultVo.result.gjfOrderInfo.payType==6}">
					   <td>线上+银联</td>
					</c:if>
					<c:if test="${resultVo.result.gjfOrderInfo.payType==7}">
					   <td>待领消费金额</td>
					</c:if>			
				</tr>

                 <tr>
					<td class="pn-flabel" width="100px">订单生成时间</td>
					<td><fmt:formatDate value="${resultVo.result.gjfOrderInfo.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">订单付款时间</td>
					<td><fmt:formatDate value="${resultVo.result.gjfOrderInfo.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">订单发货时间</td>
					<td><fmt:formatDate value="${resultVo.result.gjfOrderInfo.deliveryTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">订单完成时间</td>
					<td><fmt:formatDate value="${resultVo.result.gjfOrderInfo.finishedTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">订单过期时间</td>
					<td><fmt:formatDate value="${resultVo.result.gjfOrderInfo.overdueTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">订单退款时间</td>
					<td><fmt:formatDate value="${resultVo.result.gjfOrderInfo.refundTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">订单取消时间</td>
					<td><fmt:formatDate value="${resultVo.result.gjfOrderInfo.cancelTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">订单备注</td>
					<td>${resultVo.result.gjfOrderInfo.remark}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">订单取消原因</td>
					<td>${resultVo.result.gjfOrderInfo.cancelReason}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">订单类型</td>
					<c:if test="${resultVo.result.gjfOrderInfo.orderType==0}">
					  <td>o2o</td>
					</c:if>
					<c:if test="${resultVo.result.gjfOrderInfo.orderType==1}">
					  <td>商城</td>
					</c:if>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">评价状态</td>
					<c:if test="${resultVo.result.gjfOrderInfo.evaluationStatus==0}">
					  <td>未评价</td>
					</c:if>
					<c:if test="${resultVo.result.gjfOrderInfo.evaluationStatus==1}">
					  <td>已评价</td>
					</c:if>
					<c:if test="${resultVo.result.gjfOrderInfo.evaluationStatus==2}">
					  <td>已过期未评价</td>
					</c:if>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">订单状态</td>
					<c:if test="${resultVo.result.gjfOrderInfo.orderStatus==0}">
					  <td>未付款</td>
					</c:if>
					<c:if test="${resultVo.result.gjfOrderInfo.orderStatus==1}">
					  <td>待收货</td>
					</c:if>
					<c:if test="${resultVo.result.gjfOrderInfo.orderStatus==2}">
					  <td>已发货</td>
					</c:if>
					<c:if test="${resultVo.result.gjfOrderInfo.orderStatus==3}">
					  <td>已收货</td>
					</c:if>
					<c:if test="${resultVo.result.gjfOrderInfo.orderStatus==4}">
					  <td>已过期</td>
					</c:if>
					<c:if test="${resultVo.result.gjfOrderInfo.orderStatus==5}">
					  <td>已取消</td>
					</c:if>
					<c:if test="${resultVo.result.gjfOrderInfo.orderStatus==6}">
					  <td>已退款</td>
					</c:if>
				</tr>
				
				<tr>
					<td class="pn-flabel" width="100px">退款状态</td>
					<c:if test="${resultVo.result.gjfOrderInfo.refundStatus==0}">
					  <td>无退款</td>
					</c:if>
					<c:if test="${resultVo.result.gjfOrderInfo.refundStatus==1}">
					  <td>部分退款 </td>
					</c:if>
					<c:if test="${resultVo.result.gjfOrderInfo.refundStatus==2}">
					  <td>全部退款</td>
					</c:if>
				</tr>
				
				<tr>
					<td class="pn-flabel" width="100px">收货省份</td>
					<td>${resultVo.result.gjfOrderAddress.reciverProvinceId.province}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">收货城市</td>
					<td>${resultVo.result.gjfOrderAddress.reciverCityId.city}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">收货区域</td>
					<td>${resultVo.result.gjfOrderAddress.reciverAreaId.area}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">收货详细地址</td>
					<td>${resultVo.result.gjfOrderAddress.reciverDetailAddress}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">运费</td>
					<td>${resultVo.result.gjfOrderAddress.shippingFeeAmount}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">快递名称</td>
					<td>${resultVo.result.gjfOrderAddress.courierName}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">物流单号</td>
					<td>${resultVo.result.gjfOrderAddress.shippingCode}</td>
				</tr>
				<c:if test="${not empty gjfOrderGoods}">
					<tr>
						<td class="pn-flabel" width="100px">序号</td>
						<td>商品名称</td>
						<td>商品价格</td>
						<td>商品支付价格</td>
						<td>商品数量</td>
						<td>商品类型</td>
						<td>店铺到账金额</td>
						<td>店铺让利金额</td>
					</tr>
				<c:forEach items="${gjfOrderGoods }" var="bean" varStatus="status">
					<tr>
						<td class="pn-flabel" width="100px">商品${status.count}</td>
						<td>${bean.goodsName }</td>
						<td>${bean.goodsAmount }</td>
						<td>${bean.goodsPayAmount }</td>
						<td>${bean.goodsNum }</td>
						<td>
							<c:choose>
								<c:when test="${bean.goodsType eq 1}">默认</c:when>
								<c:when test="${bean.goodsType eq 2}">团购商品</c:when>
								<c:when test="${bean.goodsType eq 3}">限时折扣商品</c:when>
								<c:when test="${bean.goodsType eq 4}">组合套装</c:when>
								<c:when test="${bean.goodsType eq 5}">赠品</c:when>
							</c:choose>
						</td>
						<td>${bean.storeRecAmount }</td>
						<td>${bean.storeBenefitAmount }</td>
					</tr>
				</c:forEach>
				</c:if>
		 </table>
	</body>
</html>