<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body style="background-color: #fff;">
    <div class="container">
    	<c:if test="${not empty resultVo.result && not empty resultVo.result.contents}">
        <div class="about-us-box">
            <div class="aboutUs-text" style="width: 100%">
             ${resultVo.result.contents}   
            </div>
        </div>
        </c:if>
        <c:if test="${empty resultVo.result || empty resultVo.result.contents}">
			<div class="data-state-box">
		       <img src="${imagePath}/wx/o2o-shop/data-null.png" class="data-state-img">
		       <p class="data-state-text">没有数据</p>
		   </div>
		</c:if>
    </div>
</body>
<script>
document.title = "关于巨惠云商O2O";
</script>
</html>