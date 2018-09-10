<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<c:forEach items="${resultVo.result}" var="bean" varStatus="status">
 <li class="like-item">
    <a href="${ctx}/wx/product/o2o/product/${bean.id}" class="like-link hot-goods-link">
        <div class="goods-right-box shop-hot-box clearfix">
            <div class="goods-item-1 left">
                <img src="${bean.imgUrl}" class="goods-img">
            </div>
            <div class="goods-item-2 left">
                <h3 class="goods-title">${bean.name}</h3>
                <div class="goods-info clearfix">
                <fmt:parseNumber value="${bean.score}" var="score"/>
                    <div class="goods-left left">
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
                    <div class="goods-left-point left">${score}分</div>
                    <div class="goods-right right">已售
                    	<c:if test="${not empty bean.salesNum}">${bean.salesNum}</c:if>
                    	<c:if test="${empty bean.salesNum}">0</c:if>份</div>
                </div>
                <div class="goods-money clearfix">
                    <span class="goods-m-1 left"><i class="goods-front">¥</i>${bean.price}</span>
                    <span class="goods-m-2 left">门市价:¥${bean.marketPrice}</span>
                </div>
            </div>
        </div>
    </a>
</li>
</c:forEach>