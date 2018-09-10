<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<c:forEach var="bean" items="${resultVo.result}" varStatus="status">
	<li class="cash-li clearfix">
		<div class="cash-box left">
			<div class="cash-time acc-day"><mtag:date value="${bean.addTime}" type="week" timeType="1"/></div>
			<div class="cash-time day-time-${status.count}"><mtag:longToDate value="${bean.addTime}" pattern='yyyy-MM-dd'/></div>
		</div>
		<div class="cash-box left">
			<i class="main-sprite icon-profits"></i>
		</div>
		<div class="cash-box left">
			<div class="cash-text">${bean.benefitMoney}元</div>
			<div class="cash-state profits-phone">${bean.mobile}</div>
		</div>
		<div class="bonus-state right">
			<span class="bonusState-stand">
			<c:if test="${bean.tradeStatus eq '0'}">待支付</c:if>
			<c:if test="${bean.tradeStatus eq '1'}">支付成功</c:if>
			<c:if test="${bean.tradeStatus eq '2'}">支付失败</c:if>
			<c:if test="${bean.tradeStatus eq '3'}">取消</c:if>
			</span>
		</div>
	</li>
</c:forEach>
