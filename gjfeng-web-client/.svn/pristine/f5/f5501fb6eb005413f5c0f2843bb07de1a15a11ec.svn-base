<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
	<div class="container">
		<div class="cash-item">			
			<ul class="cash-list" style="background-color: transparent;">
			  <c:if test="${not empty resultVo.result.resultList }">
                  <c:forEach var="bean" items="${resultVo.result.resultList }" varStatus="status">
	                 <li class="cash-li clearfix">
		                 <div class="cash-box left">
		                   <c:if test="${bean.tradeType=='1'}">
		                     <div class="cash-time red-text">代理收入</div>
		                   </c:if>
		                   <c:if test="${bean.tradeType=='0'}">
		                     <div class="cash-time red-text">代理结算</div>
		                   </c:if>			               
			                <div class="cash-time red-date"><fmt:formatDate value="${bean.addTime}" pattern="yyyy-MM-dd"/></div>
		                 </div>
		                 <div class="cash-box right">
		                   <c:if test="${bean.tradeType=='1'}">
		                     <div class="red-num">+${bean.tradeMoney}</div>
		                   </c:if>
		                   <c:if test="${bean.tradeType=='0'}">
		                     <div class="red-num">-${bean.tradeMoney}</div>
		                   </c:if>
			               
		                  </div>
	                  </li>
                   </c:forEach>
               </c:if>
               <c:if test="${empty resultVo.result.resultList}">
	                 <div class="data-state-box">
                         <img src="${imagePath}/wx/o2o-shop/data-null.png" class="data-state-img">
                         <p class="data-state-text">没有数据</p>
                    </div> 
               </c:if>			
			</ul>
		</div>
	</div>
</body>
<script type="text/javascript">
document.title = "运营中心历史记录";
var pageNo=1;
var pageSize=10;

/* $(function(){
	ajaxLoad();
    $(window).scroll(function() {
        var totalHeight = $(window).height()+$(window).scrollTop();
        var docHeight = $(document).height();
        if(totalHeight>=docHeight){//拉到底部触发
        	ajaxLoad();
        }
    });
}) */

function ajaxLoad(){
$.ajax({
    url: '${ctx}/wx/trade/agentHistory',
    type: 'get',
    dataType: 'html',
    data: {
    	"pageNo":pageNo,
    	"pageSize":pageSize,
    	"reqType":1
    	},
    success:function(data){
        if(null != data && "" != data){
        	++pageNo;
        	$(".cash-list").append(data);
        }
    },
    error:function(){
    	layer.open({
            content: "请求异常，请稍后再试",
            skin: 'msg',
            style:'bottom:0;',
            time: 1 //2秒后自动关闭
        }); 
    }
})
}
</script>
</html>