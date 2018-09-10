<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
	<div class="container">
		<div class="cash-item">
			<!-- <div class="cash-month clearfix">
				<div class="month-left left">2016-12</div>
				<div class="month-right right">12月累计：+5.20</div>
			</div> -->
			<c:if test="${not empty resultVo.result}">
			<ul class="cash-list">
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
					<div class="cash-time red-date acc-day"><mtag:longToDate value="${bean.addTime}" pattern='yyyy-MM-dd'/></div>
					<%-- <div class="cash-time day-time-${status.count}">00:00</div>
					<script type="text/javascript">
						$(function(){
							if("今天" == $(".acc-day").html() || "昨天" == $(".acc-day").html()){
								$(".day-time-"+"${status.count}").html("<mtag:longToDate value="${bean.tradeTime}" pattern='HH:mm'/>")
							}
						});
						</script> --%>
				</div>
				<div class="cash-box right">
				  <c:if test="${not empty bean.tradeSecondMoney && not empty bean.tradeThreeMoney}">
				       <div class="red-num">${bean.tradeMoney+bean.tradeSecondMoney+bean.tradeThreeMoney}</div>
				  </c:if>
				 <c:if test="${empty bean.tradeSecondMoney && empty bean.tradeThreeMoney}">
				       <div class="red-num">${bean.tradeMoney}</div>
				  </c:if>
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
    $(function(){
    	document.title = "福利记录";
        $(window).scroll(function() {
            var totalHeight = $(window).height()+$(window).scrollTop();
            var docHeight = $(document).height();
            if(totalHeight>=docHeight){//拉到底部触发
            	ajaxLoad();
            }
        });
    })
    
   	var pageNo=2;
   	var pageSize=10;
   	var reqType=1;
   	var lockStatus=true;
      function ajaxLoad(value){
   	   if(!lockStatus){
   		   return false;
   	   }
   		lockStatus=false;
        $.ajax({
            url: '${ctx}/wx/trade/diviHis',
            type: 'get',
            dataType: 'html',
            data: {
            	"pageNo":pageNo,
            	"pageSize":pageSize,
            	"reqType":reqType
            	},
            success:function(data){
                if(null != data && "" != data){
                	++pageNo;
                	lockStatus=true;
                	$(".cash-list").append(data);
                }else{
                	lockStatus=false;
                }
            },
            error:function(){
            	lockStatus=true;
				//alert("网络异常");
            }
        })
    }
    </script>
</html>