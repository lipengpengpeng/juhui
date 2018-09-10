<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
	<div class="container">
		<c:if test="${not empty trackResult }">
		<div class="cash-item">
		    <c:forEach items="${trackResult.jdTrack}" var="trackList">
		      <div class="cash-month">${trackList.order_sn}</div>
			  <ul class="cash-list">
			     <c:forEach items="${trackList.trackInfo}" var="list">
			       <li class="cash-li clearfix">
					  <div class="cash-box left">
						<div class="cash-time" style="margin-left: 0px"><fmt:formatDate value="${list.msgTime}" pattern="yyyy-MM-dd HH:mm"/></div>
					  </div>					 					  
					   <div class="cash-box left" >
						 <div class="cash-text " style="margin-left: 2px">${list.content}</div>
					   </div>					 
				   </li>
			     </c:forEach>
			</ul>
		    
		  </c:forEach>
			
		</div>
		</c:if>	
		<c:if test="${empty trackResult}">
			<div class="data-state-box">
		        <img src="${imagePath}/wx/o2o-shop/data-null.png" class="data-state-img">
		        <p class="data-state-text">暂无数据</p>
		    </div>
		</c:if>
	</div>
</body>
<script>
document.title = "记录列表";
</script>
</html>