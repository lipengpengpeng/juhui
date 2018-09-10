<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<c:forEach items="${products.result}" var="bean" varStatus="status">
<div class="collect-item collect-goods" data-id="${bean.id}">
    <div class="collect-shop clearfix">
        <div class="collect-shop-name left">${bean.storeName}</div>
        <div class="collect-state right">已收藏</div>
    </div>
    <a href="#" class="collect-link clearfix">
        <div class="collect-left-1 left">
            <img src="images/instruct-1.png" class="collect-img">
        </div>
        <div class="collect-left-2 left">
            <h4 class="collect-title">${bean.name}</h4>
        </div>
        <i class="main-sprite icon-del"></i>
    </a>
</div>
</c:forEach>
