<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
	<div class="container">
		<c:if test="${not empty resultVo.result.resultList }">
		<div class="cash-item">
			<!-- <div class="cash-month">2017年 2月份</div> -->
			<ul class="cash-list">
			     <c:forEach items="${resultVo.result.resultList}" var="list">
			       <li class="cash-li clearfix">
					 <div class="cash-box left">
						<div class="cash-time"><fmt:formatDate value="${list.addTime}" pattern="yyyy-MM-dd"/></div>
						<div class="cash-time"><fmt:formatDate value="${list.addTime}" pattern="HH:mm"/></div>
					  </div>
					  <div class="cash-box left">
					     <c:if test="${list.payType==0}">
					        <i class="fuli-icon" style="background-image: url('${imagePath}/wx/o2o-shop/授信额度支付.png');background-size: cover;"></i>
					     </c:if>
					     <c:if test="${list.payType==1}">
					        <i class="fuli-icon" style="background-image: url('${imagePath}/wx/o2o-shop/微信支付.png');background-size: cover;"></i>
					     </c:if>
					      <c:if test="${list.payType==3}">
					        <i class="fuli-icon" style="background-image: url('${imagePath}/wx/o2o-shop/银联支付.png');background-size: cover;"></i>
					     </c:if>
					      <c:if test="${list.payType==5}">
					        <i class="fuli-icon" style="background-image: url('${imagePath}/wx/o2o-shop/授信额度支付.png');background-size: cover;"></i>
					     </c:if>
					      <c:if test="${list.payType==6}">
					        <i class="fuli-icon" style="background-image: url('${imagePath}/wx/o2o-shop/授信额度支付.png');background-size: cover;"></i>
					     </c:if>
						
					  </div>
					  <c:if test="${list.tradeType==0}">
					    <div class="cash-box left">
						  <div class="cash-text shouxin-text">充值${list.tradeMoney}</div>
					    </div>
					  </c:if>
					  <c:if test="${list.tradeType==2}">
					    <div class="cash-box left">
						  <div class="cash-text">消耗${list.tradeMoney}</div>
						  <div class="cash-state shouxin-state">${list.toMemberId.mobile}</div>
					     </div>					    
					  </c:if>
					  <div class="bonus-state right">
						<span class="bonusState-stand">
						    <c:if test="${list.shouxinType==0}">
						       <c:if test="${list.tradeStatus eq '0'}">待支付</c:if>
						    </c:if>
						    <c:if test="${list.shouxinType==1}">
						       <c:if test="${list.tradeStatus eq '0'}">待审核</c:if>
						    </c:if>
							
							<c:if test="${list.tradeStatus eq '1'}">支付成功</c:if>
							<c:if test="${list.tradeStatus eq '2'}">支付失败</c:if>
							<c:if test="${list.tradeStatus eq '3'}">取消</c:if>
						</span>
					  </div>
				   </li>
			     </c:forEach>
			</ul>
		</div>
		</c:if>	
		<c:if test="${empty resultVo.result.resultList}">
			<div class="data-state-box">
		        <img src="${imagePath}/wx/o2o-shop/data-null.png" class="data-state-img">
		        <p class="data-state-text">没有数据</p>
		    </div>
		</c:if>
	</div>
</body>
<script>
document.title = "记录列表";
</script>
</html>