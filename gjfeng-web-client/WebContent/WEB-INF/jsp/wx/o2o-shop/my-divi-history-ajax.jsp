<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<c:forEach items="${resultVo.result}" var="bean" varStatus="status">
<li class="cash-li clearfix">
	<div class="cash-box left">
		<div class="cash-time red-text">
			<c:if test="${bean.tradeType eq '0'}">直推会员</c:if>
			<c:if test="${bean.tradeType eq '1'}">直推商家</c:if>
			<c:if test="${bean.tradeType eq '2'}">普通用户</c:if>
			<c:if test="${bean.tradeType eq '3'}">普通商家</c:if>
			<c:if test="${bean.tradeType eq '4'}">市代</c:if>
			<c:if test="${bean.tradeType eq '5'}">区代</c:if>
			<c:if test="${bean.tradeType eq '6'}">个代</c:if>
		</div>
		<div class="cash-time red-date"><mtag:longToDate value="${bean.addTime}" pattern='yyyy-MM-dd'/></div>
	</div>
	<div class="cash-box right">
		<div class="red-num">${bean.tradeMoney}</div>
	</div>
</li>
</c:forEach>
