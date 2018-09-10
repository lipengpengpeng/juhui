<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<c:if test="${not empty products.result}">
<c:forEach items="${products.result}" var="bean" varStatus="status">
 <li class="like-item">
   <a href="${ctx}/wx/product/o2o/product/${bean.id}" class="like-link clearfix">
        <div class="shop-item-1 left">
            <img src="${bean.imgUrl}" class="shop-img">
        </div>
        <div class="shop-item-2 left">
            <h3 class="shop-title clearfix">
                <div class="shopTitle-left left">${bean.storeName}</div>
                <div class="shop-near right"><i class="main-sprite icon-near"></i>
                 <c:if test="${bean.distance >= 1000 || bean.distance <= -1000}">${bean.distance/1000}km</c:if>
                 <c:if test="${-1000 < bean.distance && bean.distance < 1000}">${bean.distance}m</c:if>
                </div>
            </h3>
            <div class="shopInfo-box clearfix">
            	<fmt:parseNumber value="${bean.score}" var="score"/>
                <div class="shopInfo-points-box left">
                    <c:if test="${score==0}">
                      <i class="contMenu-icon icon-crown "></i>
                      <i class="contMenu-icon icon-crown "></i>
                      <i class="contMenu-icon icon-crown "></i>
                      <i class="contMenu-icon icon-crown "></i>
                      <i class="contMenu-icon icon-crown"></i>
                    </c:if>
                    <c:if test="${score>0 && score<1}">
                      <i class="contMenu-icon icon-crown icon-crown-middle"></i>
                      <i class="contMenu-icon icon-crown "></i>
                      <i class="contMenu-icon icon-crown "></i>
                      <i class="contMenu-icon icon-crown "></i>
                      <i class="contMenu-icon icon-crown "></i>
                    </c:if>
                    <c:if test="${score==1}">
                      <i class="contMenu-icon icon-crown icon-crown-active"></i>
                      <i class="contMenu-icon icon-crown "></i>
                      <i class="contMenu-icon icon-crown "></i>
                      <i class="contMenu-icon icon-crown "></i>
                      <i class="contMenu-icon icon-crown "></i>
                    </c:if>
                     <c:if test="${score>1 && score<2}">
                      <i class="contMenu-icon icon-crown icon-crown-active"></i>
                      <i class="contMenu-icon icon-crown icon-crown-middle"></i>
                      <i class="contMenu-icon icon-crown "></i>
                      <i class="contMenu-icon icon-crown "></i>
                      <i class="contMenu-icon icon-crown "></i>
                    </c:if>
                     <c:if test="${score==2}">
                      <i class="contMenu-icon icon-crown icon-crown-active"></i>
                      <i class="contMenu-icon icon-crown icon-crown-active"></i>
                      <i class="contMenu-icon icon-crown "></i>
                      <i class="contMenu-icon icon-crown "></i>
                      <i class="contMenu-icon icon-crown "></i>
                    </c:if>
                    
                     <c:if test="${score>2 && score<3}">
                      <i class="contMenu-icon icon-crown icon-crown-active"></i>
                      <i class="contMenu-icon icon-crown icon-crown-active"></i>
                      <i class="contMenu-icon icon-crown icon-crown-middle"></i>
                      <i class="contMenu-icon icon-crown "></i>
                      <i class="contMenu-icon icon-crown "></i>
                    </c:if>
                    
                    <c:if test="${score==3}">
                      <i class="contMenu-icon icon-crown icon-crown-active"></i>
                      <i class="contMenu-icon icon-crown icon-crown-active"></i>
                      <i class="contMenu-icon icon-crown icon-crown-active"></i>
                      <i class="contMenu-icon icon-crown "></i>
                      <i class="contMenu-icon icon-crown "></i>
                    </c:if>
                    
                    <c:if test="${score>3 && score<4}">
                      <i class="contMenu-icon icon-crown icon-crown-active"></i>
                      <i class="contMenu-icon icon-crown icon-crown-active"></i>
                      <i class="contMenu-icon icon-crown icon-crown-active"></i>
                      <i class="contMenu-icon icon-crown icon-crown-middle"></i>
                      <i class="contMenu-icon icon-crown "></i>
                    </c:if>
                    
                        
                    <c:if test="${score==4}">
                      <i class="contMenu-icon icon-crown icon-crown-active"></i>
                      <i class="contMenu-icon icon-crown icon-crown-active"></i>
                      <i class="contMenu-icon icon-crown icon-crown-active"></i>
                      <i class="contMenu-icon icon-crown icon-crown-active"></i>
                      <i class="contMenu-icon icon-crown "></i>
                    </c:if>
                    
                    <c:if test="${score>4 && score<5}">
                      <i class="contMenu-icon icon-crown icon-crown-active"></i>
                      <i class="contMenu-icon icon-crown icon-crown-active"></i>
                      <i class="contMenu-icon icon-crown icon-crown-active"></i>
                      <i class="contMenu-icon icon-crown icon-crown-active"></i>
                      <i class="contMenu-icon icon-crown icon-crown-middle"></i>
                    </c:if>
                    
                    <c:if test="${score==5}">
                      <i class="contMenu-icon icon-crown icon-crown-active"></i>
                      <i class="contMenu-icon icon-crown icon-crown-active"></i>
                      <i class="contMenu-icon icon-crown icon-crown-active"></i>
                      <i class="contMenu-icon icon-crown icon-crown-active"></i>
                      <i class="contMenu-icon icon-crown icon-crown-active"></i>
                    </c:if>
                </div>
                <div class="shopInfo-points left">${score}分</div>
            </div>
            <div class="shpInfo-cont">${bean.name}</div>
            <div class="shop-money clearfix">
                <span class="shop-m-1 left"><i class="shop-front">¥</i>${bean.price}</span>
                <span class="shop-m-2 left">门市价:¥${bean.marketPrice}</span>
            </div>
        </div>
    </a>
</li>
</c:forEach>
</c:if>