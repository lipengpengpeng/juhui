<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<c:forEach items="${stores.result}" var="bean" varStatus="status">
 <li class="like-item">
    <a href="${ctx}/wx/store/o2o/store/${bean.id}" class="like-link clearfix">
        <div class="shop-item-1 left">
            <img src="${bean.storeBanner}" class="shop-img">
        </div>
        <div class="shop-item-2 left">
            <h3 class="shop-title clearfix">
                <div class="shopTitle-left left">${bean.storeName}</div>
                <div class="shop-near right"><i class="main-sprite icon-near"></i>
                 <c:if test="${bean.distance >= 1000 || bean.distance <= -1000}">${bean.distance/1000}km</c:if>
                 <c:if test="${-1000 < bean.distance && bean.distance < 1000}">${bean.distance}m</c:if>
                </div>
            </h3>
            <div class="shop-money clearfix">
                <div class="shpInfo-cont">${bean.addressDetail}</div>
            </div>
        </div>
    </a>
</li>
</c:forEach>