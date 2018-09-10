<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
    <div class="container">
        <div class="shop-box">
            <c:if test="${not empty resultVo.result.storeBanner}">
        		<img src="${resultVo.result.storeBanner}" class="shop-show">
        	</c:if>
        	<c:if test="${empty resultVo.result.storeBanner}">
        		<img src="${imagePath}/wx/o2o-shop/business.png" class="shop-show">
        	</c:if>
            <div class="shop-text">${resultVo.result.storeDescription}</div>
        </div>
        <div class="shop-btn">
            <a href="${ctx}/wx/store/toUpdateIntro" class="edit-btn">编辑简介</a>
        </div>
    </div>
    <script type="text/javascript">
    document.title = "编辑简介";
    </script>
</body>
</html>