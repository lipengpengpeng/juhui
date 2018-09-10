<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
  <c:forEach var="list" items="${resultVo.result}">
   <li class="cash-li clearfix" style="margin: 0 1rem;">
	<div class="cash-box left">
		<div class="cash-time"><mtag:longToDate value="${list.addTime}" pattern="yyyy-MM-dd"/></div>
		<div class="cash-time"><mtag:longToDate value="${list.addTime}" pattern="HH:mm"/></div>
	</div>
	<div class="cash-box left">
		<i class="cash-icon" style="margin: 0.5rem 1rem;"></i>
	</div>
	<div class="cash-box left" >
		<div class="cash-text">提现${list.tradeMoney}元</div>
		<c:if test="${list.tradeStatus==0}">
		  <div class="cash-state cash-red">审核中...</div>
		</c:if>	
		<c:if test="${list.tradeStatus==1}">
		  <div class="cash-state cash-red">交易成功</div>
		</c:if>	
		<c:if test="${list.tradeStatus==2}">
		  <div class="cash-state cash-red">交易失败</div>
		</c:if>	
	</div>
 </li>
  </c:forEach> 
