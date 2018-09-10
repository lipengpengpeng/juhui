<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
 <c:if test="${not empty resultVo.result}">
  <c:forEach var="bean" items="${resultVo.result}" varStatus="status">
  	<li class="goods-li" data-id="${bean.id}">
          <div class="goods-item-choose" ontouchend="iconSelect(this)" data-value="0">
              <i class="main-sprite icon-no"></i>
          </div>
          <div class="goods-right-box clearfix">
              <div class="goods-item-1 left goods-rel">
                 <img src="${bean.imgUrl}" class="goods-img">
                 <span class="goods-state">
                   <c:if test="${bean.status eq '0' && bean.aduitStatus eq '0'}">审核不通过</c:if>
                   <c:if test="${bean.status eq '1' && bean.aduitStatus eq '2'}">待审核</c:if>
                   <c:if test="${bean.status eq '0'}">已下架</c:if>
                   <c:if test="${bean.status eq '1' && bean.aduitStatus eq '1'}">上架中</c:if>
                   <c:if test="${bean.status eq '2'}">违规下架</c:if>
                  </span>
              </div>
              <div class="goods-item-2 left">
                  <h3 class="goods-title">${bean.name}</h3>
                  <fmt:parseNumber value="${bean.score}" var="score"/>
                  <div class="goods-info clearfix">
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
                      <div class="goods-right right">已售${bean.salesNum}份</div>
                  </div>
                  <div class="goods-money clearfix">
                      <span class="goods-m-1 left"><i class="goods-front">¥</i>${bean.price}</span>
                      <span class="goods-m-2 left">门市价:¥${bean.marketPrice}</span>
                  </div>
              </div>
          </div>
      </li>
  </c:forEach>
  </c:if>
        