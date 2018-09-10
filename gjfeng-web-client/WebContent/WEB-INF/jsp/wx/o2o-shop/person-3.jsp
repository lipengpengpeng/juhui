<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
	<div class="container">
		<div class="cash-item">
			<!-- <div class="cash-month">2017年2月份</div> -->
			<ul class="cash-list1">
			<c:if test="${not empty resultVo.result}">
			   <c:forEach items="${resultVo.result}" var="result">
			   <li class="cash-li clearfix">
					<div class="cash-box left">
						<div class="cash-time"><fmt:formatDate type="date" dateStyle="long" value="${result.addTime}"/></div>
						<div class="cash-time"><fmt:formatDate pattern="HH:mm" value="${result.addTime}"/></div>
					</div>
					<div class="cash-box left">
						<i class="fuli-icon"></i>
					</div>
					<div class="cash-box left">
						<div class="cash-text">${result.tradeMoney}元</div>
						<div class="cash-state cash-grey">${result.nickName}</div>
					</div>
				</li>
			   </c:forEach>
			   </c:if>
			   <c:if test="${empty resultVo.result}">
					<div class="data-state-box">
				       <img src="${imagePath}/wx/o2o-shop/data-null.png" class="data-state-img">
				       <p class="data-state-text">没有数据</p>
				   </div>
				</c:if>				
			</ul>
		<!-- </div> -->
		<!-- <div class="cash-item">
			<div class="cash-month">10月份</div>
			<ul class="cash-list">
				<li class="cash-li clearfix">
					<div class="cash-box left">
						<div class="cash-time">周二</div>
						<div class="cash-time">10-21</div>
					</div>
					<div class="cash-box left">
						<i class="fuli-icon"></i>
					</div>
					<div class="cash-box left">
						<div class="cash-text">5000元</div>
						<div class="cash-state cash-grey">13525215480</div>
					</div>
				</li>
				<li class="cash-li clearfix">
					<div class="cash-box left">
						<div class="cash-time">周一</div>
						<div class="cash-time">10-20</div>
					</div>
					<div class="cash-box left">
						<i class="fuli-icon"></i>
					</div>
					<div class="cash-box left">
						<div class="cash-text">5000元</div>
						<div class="cash-state cash-grey">13525215480</div>
					</div>
				</li>
				<li class="cash-li clearfix">
					<div class="cash-box left">
						<div class="cash-time">周四</div>
						<div class="cash-time">10-15</div>
					</div>
					<div class="cash-box left">
						<i class="fuli-icon"></i>
					</div>
					<div class="cash-box left">
						<div class="cash-text">5000元</div>
						<div class="cash-state cash-grey">13525215480</div>
					</div>
				</li>
			</ul>
		</div> -->
	</div>
</body>
</html>