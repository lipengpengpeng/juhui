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
					   <c:if test="${result.tradeType==0}">
					      <i class="fuli-icon" style="background:url('${imagePath}/wx/o2o-shop/直授福利.png') 0 0 no-repeat;background-size: cover;"></i>
					   </c:if>
					   <c:if test="${result.tradeType==1}">
					      <i class="fuli-icon" style="background:url('${imagePath}/wx/o2o-shop/商户福利.png') 0 0 no-repeat;background-size: cover;"></i>
					   </c:if>
						
					</div>
					<div class="cash-box left">
						<div class="cash-text">${result.tradeMoney}元</div>
					
						 <div class="cash-state cash-grey"  tradeType="${result.tradeType}"></div>
						 <c:if test="${result.tradeType==0}">
						    <input type="hidden" value="${result.memberId.nickName}" id="nickNa"/>
						 </c:if>
					</div>
				</li>
			   </c:forEach>	
			   <input type="hidden" value="" id="storeInfo"/>
			   </c:if>
			   <c:if test="${empty resultVo.result}">
					<div class="data-state-box">
				       <img src="${imagePath}/wx/o2o-shop/data-null.png" class="data-state-img">
				       <p class="data-state-text">没有数据</p>
				   </div>
				</c:if>							
			</ul>
		<!-- </div> -->		
	</div>
</body>
</html>
<script type="text/javascript">
  $.ajax({
	   url:"${ctx}/wx/store/getStoreInfoByMemId",
	   method:"POST",
	   success:function(data){
		   $("#storeInfo").val(data.result.storeName)
		   
		   $(".cash-grey").each(function(){        	
	    	   var state=$(this).attr("tradeType")
	    	   if(state==0){
	    		   var nickName=$("#nickNa").val()
	    		   $(this).html(nickName)
	    	   }else{
	    		   var storeInfo=$("#storeInfo").val()
	    		   $(this).html(storeInfo)
	    	   }
	    	   
	       }) 
	    }
  })
    </script>