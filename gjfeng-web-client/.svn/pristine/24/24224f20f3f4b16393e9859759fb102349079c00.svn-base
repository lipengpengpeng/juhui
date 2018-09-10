<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
	<div class="container">
		<div class="cash-item">
			<!-- <div class="cash-month">本月</div> -->
			<c:if test="${not empty resultVo.result}">
			<ul class="cash-list">
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
        	ajaxLoad();
        }
    });
})

function ajaxLoad(){
	$.ajax({
	    url: '${ctx}/wx/trade/benefits',
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
	        	$(".cash-list").append(data);
	        }
	    },
	    error:function(){
			alert("网络异常");
	    }
})
}
</script>
</html>