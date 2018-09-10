<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
	<div class="container">
		<div class="cash-item">
			<!-- <div class="cash-month">本月</div> -->
			<ul class="cash-list">
			<c:forEach var="bean" items="${resultVo.result}" varStatus="status">
				<li class="cash-li clearfix">
					<div class="cash-box left">
						<div class="cash-time acc-day"><mtag:date value="${bean.applyDate}" type="week"/></div>
						<div class="cash-time day-time-${status.count}">00:00</div>
						<script type="text/javascript">
						$(function(){
							if("今天" == $(".acc-day").html() || "昨天" == $(".acc-day").html()){
								$(".day-time-"+"${status.count}").html("<fmt:formatDate value="${bean.addTime}" pattern="HH:mm"/>")
							}
						});
						</script>
					</div>
					<div class="cash-box left">
						<i class="main-sprite icon-bonus"></i>
					</div>
					<div class="cash-box left">
						<div class="cash-text">${bean.diviNum}个分红权</div>
						<div class="cash-state bonnus-money"><fmt:formatNumber value="${bean.diviMoney}" type="number" pattern="#,#0.00#"/></div>
					</div>
					<div class="bonus-state right">
						<span class="bonusState-stand">
						<c:if test="${bean.payStatus eq '0'}">
							待支付
						</c:if>
						<c:if test="${bean.payStatus eq '1'}">
							支付成功
						</c:if>
						<c:if test="${bean.payStatus eq '2'}">
							支付失败
						</c:if>
						</span>
					</div>
				</li>
			</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>