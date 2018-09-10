<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
	<div class="container">
		<ul class="h-detail-ul">
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">流水账单号：</div>
				<div class="h-detail-right right">${resultVo.result.orderSn}</div>
			</li>
			<c:forEach var="goods" items="${resultVo.result.goodsVos}">
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">商品名称</div>
				<div class="h-detail-right right">${goods.goodsName}</div>
			</li>
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">商品属性</div>
				<div class="h-detail-right right">${goods.goodsAttr}</div>
			</li>
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">商品数量</div>
				<div class="h-detail-right right">${goods.goodsNum}</div>
			</li>
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">商品价格</div>
				<div class="h-detail-right right">${goods.goodsAmount}</div>
			</li>
			</c:forEach>
			
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">购买人姓名</div>
				<div class="h-detail-right right">${resultVo.result.memberId.name}</div>
			</li>
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">购买人电话</div>
				<div class="h-detail-right right">${resultVo.result.memberId.mobile}</div>
			</li>
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">线上支付金额</div>
				<div class="h-detail-right right">${resultVo.result.onlinePayAmount}</div>
			</li>
			
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">线下支付金额</div>
				<div class="h-detail-right right">${resultVo.result.offlinePayAmount}</div>
			</li>
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">收货人姓名</div>
				<div class="h-detail-right right">${resultVo.result.gjfOrderAddress.reciverName}</div>
			</li>
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">收货人电话</div>
				<div class="h-detail-right right">${resultVo.result.gjfOrderAddress.reciverMobile}</div>
			</li>
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">收货人地址</div>
				<div class="h-detail-right right">${resultVo.result.gjfOrderAddress.reciverProvinceId.province}${resultVo.result.gjfOrderAddress.reciverCityId.city}${resultVo.result.gjfOrderAddress.reciverAreaId.area}${resultVo.result.gjfOrderAddress.reciverDetailAddress}</div>
			</li>
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">快递类型</div>
				<div class="h-detail-right right">${resultVo.result.gjfOrderAddress.courierName}</div>
			</li>
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">快递单号</div>
				<div class="h-detail-right right">${resultVo.result.gjfOrderAddress.shippingCode}</div>
			</li>
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">下单时间</div>
				<div class="h-detail-right right"><fmt:formatDate value="${resultVo.result.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
			</li>
			
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">订单付款时间</div>
				<div class="h-detail-right right"><fmt:formatDate value="${resultVo.result.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
			</li>
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">订单发货时间</div>
				<div class="h-detail-right right"><fmt:formatDate value="${resultVo.result.deliveryTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
			</li>
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">订单完成时间</div>
				<div class="h-detail-right right"><fmt:formatDate value="${resultVo.result.finishedTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
			</li>
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">支付类型</div>
				<c:if test="${resultVo.result.payType==0}">
				   <div class="h-detail-right right">线上余额</div>
				</c:if>
				<c:if test="${resultVo.result.payType==1}">
				   <div class="h-detail-right right">微信支付</div>
				</c:if>
				<c:if test="${resultVo.result.payType==2}">
				   <div class="h-detail-right right">支付宝</div>
				</c:if>
				<c:if test="${resultVo.result.payType==3}">
				   <div class="h-detail-right right">银联支付</div>
				</c:if>
				<c:if test="${resultVo.result.payType==4}">
				   <div class="h-detail-right right">余额+微信</div>
				</c:if>
				<c:if test="${resultVo.result.payType==5}">
				   <div class="h-detail-right right">余额+支付宝</div>
				</c:if>
				<c:if test="${resultVo.result.payType==6}">
				   <div class="h-detail-right right">余额+银联</div>
				</c:if>	
			</li>
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">订单类型</div>
				<c:if test="${resultVo.result.orderType==0}">
				   <div class="h-detail-right right">020</div>
				</c:if>	
				<c:if test="${resultVo.result.orderType==1}">
				   <div class="h-detail-right right">网上商城</div>
				</c:if>	
			</li>
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">订单状态</div>
				<c:if test="${resultVo.result.orderStatus==0}">
				   <div class="h-detail-right right">未付款</div>
				</c:if>
				<c:if test="${resultVo.result.orderStatus==1}">
				   <div class="h-detail-right right">待发货</div>
				</c:if>
				<c:if test="${resultVo.result.orderStatus==2}">
				   <div class="h-detail-right right">待收货</div>
				</c:if>
				<c:if test="${resultVo.result.orderStatus==3}">
				   <div class="h-detail-right right">已收货</div>
				</c:if>
				<c:if test="${resultVo.result.orderStatus==4}">
				   <div class="h-detail-right right">已过期</div>
				</c:if>
				<c:if test="${resultVo.result.orderStatus==5}">
				   <div class="h-detail-right right">已取消</div>
				</c:if>
				<c:if test="${resultVo.result.orderStatus==6}">
				   <div class="h-detail-right right">已退款</div>
				</c:if>	
			</li>
		</ul>
	</div>
</body>
<script type="text/javascript">
document.title = "订单详情";
</script>
</html>