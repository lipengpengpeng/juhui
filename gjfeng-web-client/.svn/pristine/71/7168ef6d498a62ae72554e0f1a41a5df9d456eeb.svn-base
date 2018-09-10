<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
	<div class="container">
		<div class="cash-item">
			<!-- <div class="cash-month clearfix">
				<div class="month-left left">2016-12</div>
			</div> -->
			<ul class="cash-list1">
				<c:if test="${not empty resultVo.result}">
			    <c:forEach items="${resultVo.result}" var="result">
			    <li class="cash-li clearfix">
					<div class="cash-box left">
						<div class="cash-time big-text">增加权益</div>
						<div class="cash-time sm-text"><fmt:formatDate pattern="yyyy-MM-dd" value="${result.addTime}"/></div>
					</div>
					<div class="cash-box right">
						<div class="person-number">${result.diviNum}</div>
						<c:if test="${result.diviType==1}">
						   <div class="person-tip">满500增加1个</div>
                        </c:if>
                        <c:if test="${result.diviType==2}">
						   <div class="person-tip">满1000增加1个</div>
                        </c:if>
                        <c:if test="${result.diviType==3}">
						   <div class="person-tip">满5000增加1个</div>
                        </c:if>
						
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
		</div>		
	</div>
</body>
</html>