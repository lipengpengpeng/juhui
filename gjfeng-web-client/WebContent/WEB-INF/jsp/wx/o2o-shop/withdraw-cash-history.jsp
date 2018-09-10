<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
	<div class="container">
		<div class="cash-item">
			<!-- <div class="cash-month">本月</div> -->
			<c:if test="${not empty resultVo.result}">
			<ul class="cash-list">
			   <c:forEach var="list" items="${resultVo.result}">
			    <li class="cash-li clearfix" style="margin: 0 1rem;">
			    <a href="${ctx}/wx/trade/toDrawHistoryDetail?tradeNo=${list.tradeNo}">
					<div class="cash-box left">
						<div class="cash-time"><mtag:longToDate value="${list.addTime}" pattern="yyyy-MM-dd"/></div>
						<div class="cash-time"><mtag:longToDate value="${list.addTime}" pattern="HH:mm"/></div>
					</div>
					<div class="cash-box left">
						<i class="cash-icon" style="margin: 0.5rem 1rem;"></i>
					</div>
					<div class="cash-box left">
						<div class="cash-text">提现${list.tradeMoney}元</div>
						<c:if test="${list.tradeStatus==0}">
						  <div class="cash-state cash-red">审核中...</div>
						</c:if>	
						<c:if test="${list.tradeStatus==1}">
						  <div class="cash-state cash-red">交易成功</div>
						</c:if>	
						<c:if test="${list.tradeStatus==2}">
						  <div class="cash-state cash-red">交易失败</div>
						</c:if>	
					</div></a>
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
		<!-- <div class="cash-item">
			<div class="cash-month">10月份</div>
			<ul class="cash-list">
				<li class="cash-li clearfix">
					<div class="cash-box left">
						<div class="cash-time">周二</div>
						<div class="cash-time">10-21</div>
					</div>
					<div class="cash-box left">
						<i class="cash-icon"></i>
					</div>
					<div class="cash-box left">
						<div class="cash-text">提现100元</div>
						<div class="cash-state cash-red">审核中...</div>
					</div>
				</li>
				<li class="cash-li clearfix">
					<div class="cash-box left">
						<div class="cash-time">周一</div>
						<div class="cash-time">10-20</div>
					</div>
					<div class="cash-box left">
						<i class="cash-icon"></i>
					</div>
					<div class="cash-box left">
						<div class="cash-text">提现100元</div>
						<div class="cash-state cash-grey">审核通过</div>
					</div>
				</li>
				<li class="cash-li clearfix">
					<div class="cash-box left">
						<div class="cash-time">周四</div>
						<div class="cash-time">10-15</div>
					</div>
					<div class="cash-box left">
						<i class="cash-icon"></i>
					</div>
					<div class="cash-box left">
						<div class="cash-text">提现100元</div>
						<div class="cash-state cash-green">审核失败</div>
					</div>
				</li>
			</ul>
		</div> -->
	</div>
</body>
<script type="text/javascript">
    $(function(){
    	document.title = "提取历史";
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
    
       function ajaxLoad(value){
        $.ajax({
            url: '${ctx}/wx/trade/toDrawHistory',
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