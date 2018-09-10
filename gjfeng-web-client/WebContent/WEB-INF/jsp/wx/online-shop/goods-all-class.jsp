<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-shop-top.jsp" %>
<body>
	<!-- <div class="all-class-wrap">
		<div class="all-class-input clearfix">
			<input type="text" placeholder="请输入要搜索的商品">
			<a href="javascript:" class="all-search"><i class="all-ico xicon"></i></a>
		</div>
	</div> -->
	<section class="all-sec">
	<c:forEach var="mapBean" items="${resultVo.result}" varStatus="status">
		<div>
			<h4 class="all-snacks">
				<img src="${fn:substringBefore(mapBean.key,'~')}">
				<span>${fn:substringAfter(mapBean.key,'~')}</span>
			</h4>
			<ul class="all-common clearfix">
			<c:forEach var="bean" items="${mapBean.value}" varStatus="status">
				<li><a href="${ctx}/wx/product/online/products/1/${bean.id}">${bean.shortName}</a></li>
			</c:forEach>
			</ul>
		</div>
	</c:forEach>
	</section>
	<!-- 底部 -->
<%@include file="/common/wx-footer.jsp" %>
</body>
<script>
$(function(){
	document.title = "所有栏目";
	$(".icon-nav-3").siblings().removeClass("nav-link-active");
	$(".icon-nav-3").addClass("nav-link-active");
	$(".icon-nav-3").parent().siblings().removeClass("nav-link-active");
	$(".icon-nav-3").parent().addClass("nav-link-active");
});
</script>
</html>