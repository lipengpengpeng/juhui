<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
	<div class="container">
		<div class="cash-item">
			<!-- <div class="cash-month">本月</div> -->
			<c:if test="${not empty resultVo.result}">
			<ul class="cash-list">
			<c:forEach var="bean" items="${resultVo.result}" varStatus="status">
				<li class="cash-li clearfix" style="height: 12.5rem">
					<div class="cash-box left">
						<div class="cash-time"><fmt:formatDate value="${bean.addTime}" pattern="yyyy-MM-dd"/></div>
						<div class="cash-time"><fmt:formatDate value="${bean.addTime}" pattern="HH:mm"/></div>
					</div>
					<div class="cash-box left">
						<!-- <i class="main-sprite icon-profits"></i> -->
					</div> 
					<div class="cash-box left" style="margin-left: 87px;">
						<div class="cash-text">${bean.memberName}</div>
						<div class="cash-state profits-phone">${bean.memberMobile}</div>
					</div>
					<div class="bonus-state right">
						<span class="bonusState-stand">
						  <c:if test="${bean.blockNumber eq '1'}">一档</c:if>
						  <c:if test="${bean.blockNumber eq '2'}">二挡</c:if>
						 
						</span>
					</div>
					<c:if test="${type==1}">
					  <div class="cash-box left" style="margin-left: 26px;margin-top: 14px;">
						<div class="cash-text">奖励金额：</div>						
					  </div>
					</c:if>
					<c:if test="${type==2}">
					  <div class="cash-box left" style="margin-left: 26px;margin-top: 14px;">
						<div class="cash-text">奖励积分：</div>						
					  </div>
					</c:if>
					
					<div class="cash-box left" style="margin-left: 87px;margin-top: 14px;">
						<div class="cash-text" >${bean.directMoney}</div>				
					</div>
				
				</li>
				
			</c:forEach>
			</ul>
			</c:if>
			<c:if test="${empty resultVo.result}">
				<div class="data-state-box">
			       <img src="${imagePath}/wx/o2o-shop/data-null.png" class="data-state-img">
			       <p class="data-state-text">没有数据</p>
			   </div>
			</c:if>
		</div>
	</div>
</body>
<script type="text/javascript">
var pageNo=2;
var pageSize=10;
var reqType=1;
document.title = "记录";
$(function(){
    $(window).scroll(function() {
        var totalHeight = $(window).height()+$(window).scrollTop();
        var docHeight = $(document).height();
        if(totalHeight>=docHeight){//拉到底部触发
        	//ajaxLoad();
        }
    });
})


</script>
</html>