<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body style="background-color: #fff;">
    <div class="container">
    	<c:if test="${not empty resultVo.result && not empty resultVo.result.contents}">
        <div class="agree-content" style="padding:1rem;font-size: 1.2rem;line-height: 1.2;">
            ${resultVo.result.contents}
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
document.title = "消费规则";
</script>
</html>