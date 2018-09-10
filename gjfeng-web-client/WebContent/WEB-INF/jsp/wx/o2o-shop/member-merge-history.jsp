<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
	<div class="container">
		<c:if test="${not empty resultVo.result }">
		<div class="cash-item">
			<!-- <div class="cash-month">2017年 2月份</div> -->
			<ul class="cash-list">
			     <c:forEach items="${resultVo.result}" var="list">
			       <li class="cash-li clearfix" style="height: 13.5rem;margin: 0 0.5rem;">
			         <div style="margin-left: -200px;font-size: 1.5rem;margin-bottom: 8px;"><span>用户名称:</span><span>${list.mergeMemberName}</span></div>			         
					 <div class="cash-box left">
						<div class="cash-time" style="margin-left: 43px;"><fmt:formatDate value="${list.addTime}" pattern="yyyy-MM-dd"/></div>
						<div class="cash-time"><fmt:formatDate value="${list.addTime}" pattern="HH:mm"/></div>
					  </div>
					  
					  
					    <div class="cash-box left">
						  <div class="cash-text shouxin-text" style="line-height: 2.5rem; margin: 0px 10px 0px 50px;">余额：${ list.memberBalanceBF}</div>
						  <div class="cash-text shouxin-text" style="line-height: 2.5rem; margin: 0px 10px 0px 50px;">积分：${list.memberIntegralBf}</div>
						  <div class="cash-text shouxin-text" style="line-height: 2.5rem; margin: 0px 10px 0px 50px;">消费总额：${list.memberCousumptionBf }</div>
						  <div class="cash-text shouxin-text" style="line-height: 2.5rem; margin: 0px 10px 0px 50px;">销售总额：${list.merchantSaleBf }</div>						  
						 
					    </div>
					    					  								 					  
				   </li>
			     </c:forEach>
			</ul>
		</div>
		</c:if>	
		<c:if test="${empty resultVo.result}">
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