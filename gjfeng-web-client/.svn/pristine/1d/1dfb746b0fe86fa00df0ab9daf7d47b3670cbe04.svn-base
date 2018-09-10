<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<c:forEach var="bean" items="${products.result}" varStatus="status">
	<li>
		<a href="${ctx}/wx/product/online/product/${bean.id}?type=${merchantType}" class="clearfix">
			<div class="foods-font">
				<p class="foods-reg">${bean.name}</p>
				<c:if test="${bean.isCanUserCou==0 }">
				    <c:if test="${merchantType==0}">
					    <p class="foods-money">${bean.price}</p>
				    </c:if>				   
				    <c:if test="${merchantType==1}">
				       <p class="foods-money">${bean.price}</p>
					   <%--  <p class="foods-money">${bean.standardPrice}</p>
					    <p class="foods-money" style="text-decoration:line-through;color:#9e9e9e">${bean.price}</p> --%>
				    </c:if>
					<c:if test="${merchantType==2}">
					     <p class="foods-money">${bean.price}</p>
						  <%-- <p class="foods-money">${bean.honourPrice}</p>
						  <p class="foods-money" style="text-decoration:line-through;color:#9e9e9e">${bean.price}</p> --%>
					</c:if>
				</c:if>
				<c:if test="${bean.isCanUserCou==1}">
					<p class="foods-money">${bean.pointNicePrice}+${bean.price}积分</p>
				</c:if>
				<c:if test="${bean.isCanUserCou==2}">
					<p class="foods-money">${bean.pointNicePrice}+${bean.price}责任金额</p>
				</c:if>
				<span class="foods-buy">立即购买</span>
			</div>
			<div class="foods-img">
				<img src="${bean.imgUrl}">
			</div>
		</a>
	</li>
</c:forEach>
