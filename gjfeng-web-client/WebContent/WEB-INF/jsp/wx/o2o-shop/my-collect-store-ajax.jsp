<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<c:forEach items="${products.result}" var="bean" varStatus="status">
<div class="collect-item" data-id="${bean.id}">
    <a href="#" class="collect-link clearfix">
        <div class="collect-left-1 left">
            <img src="images/instruct-1.png" class="collect-img">
        </div>
        <div class="collect-left-2 left">
            <h3 class="collect-title">${bean.name}</h3>
        </div>
        <i class="main-sprite icon-del"></i>
    </a>
</div>
</c:forEach>
