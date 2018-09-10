<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
<div class="container">
    <div class="collect-box">
        <div class="collect-nav-box clearfix">
            <div class="collect-nav left collect-nav-active" data-value="1">店铺</div>
            <div class="collect-nav left" data-value="2">商品</div>
        </div>
        <div class="collect-cont">
            <div class="collect-tab-1">
                <!-- 收藏的店铺 -->
            </div>
            <div class="collect-tab-2 hidden">
                    <!-- 收藏的商品 -->
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
	document.title = "我的收藏";
    $(function(){
        $(".collect-nav").on("touchend", function() {
            $(this).addClass("collect-nav-active").siblings().removeClass("collect-nav-active");
            var attr = $(this).attr("data-value");
            $(".collect-tab-"+attr).removeClass("hidden").siblings().addClass("hidden");
        })

        $(".icon-del").on("click",function() {
            var that = $(this);
            layer.open({
                content:'确定删除该收藏？',
                btn: ['删除', '取消'],
                yes:function(index){
                    var thisId = that.parents(".collect-item").attr('data-id');//需要删除的数据id
                    that.parents(".collect-item").remove();//执行前端页面删除操作
                    layer.close(index);//关闭弹窗
                }
            })
        })
    })
</script>
</body>
</html>