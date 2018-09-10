<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp"%>
<body>
    <!--  <img src="${imagePath}/wx/o2o-shop/wait.png" style="width: 100vw;height:100vh;">-->
</body>
</html>
<script>
var id="${sessionScope.gjfMemberInfo.id}";
var token="${sessionScope.gjfMemberInfo.token}";
if(id==""&&token==""){
	 layer.open({
         content:'请先登录',
         btn:'我知道了'
       })  
}else{
	window.location.href="${ctx}/wx/product/goTianMaoProductListPage";
}

</script>