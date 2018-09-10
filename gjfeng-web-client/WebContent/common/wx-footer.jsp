<%@ page contentType="text/html;charset=utf-8"%>
<footer class="nav-footer">
    <nav class="clearfix">
        <a href="${ctx}/wx/index/o2o" class="nav-link nav-link-active">
            <i class="nav-icon icon-nav-1 icon-nav-4-active"></i>
            <div class="nav-text">首页</div>
        </a>
        <a href="${ctx}/wx/index/near" class="nav-link">
            <i class="nav-icon icon-nav-2"></i>
            <div class="nav-text">附近</div>
        </a> 
        <a href="${ctx}/wx/index/online" class="nav-link">
            <i class="nav-icon icon-nav-3"></i>
            <div class="nav-text">商城</div>
        </a>
        <a class="nav-link" id="my" href="${ctx}/wx/member/my">
            <i class="nav-icon icon-nav-4"></i>
            <div class="nav-text">我的</div>
        </a>      
    </nav>
</footer>
<script>
$("a").on("touchend",function(){
	$(this).siblings().removeClass("nav-link-active");
	$(this).addClass("nav-link-active");
	$(this).children(".nav-icon").addClass("nav-link-active");
});
/* $("#my").on("touchend",function(){
	window.location.href="${ctx}/wx/member/my?num="+Math.random()*10;
}); */
</script>