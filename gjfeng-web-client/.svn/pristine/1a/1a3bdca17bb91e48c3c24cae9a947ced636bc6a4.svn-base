<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
	<div class="container">
		<div class="cash-item">
			<!-- <div class="cash-month clearfix">
				<div class="month-left left">2016-12</div>
				<div class="month-right right">12月累计：+5.20</div>
			</div> -->
			<ul class="cash-list" style="background-color: transparent;"></ul>
		</div>
	</div>
</body>
<script type="text/javascript">
document.title = "代理分红记录";
var pageNo=1;
var pageSize=10;

$(function(){
	ajaxLoad();
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