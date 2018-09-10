<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<c:if test="${not empty resultVo && not empty resultVo.result && not empty resultVo.result.resultList}">
<c:forEach var="bean" items="${resultVo.result.resultList}" varStatus="status">
	<li class="cash-li clearfix">
		<div class="cash-box left">
			<div class="cash-time red-text">代理收入</div>
			<div class="cash-time red-date"><mtag:longToDate value="${bean.addTime}" pattern='yyyy-MM-dd'/></div>
		</div>
		<div class="cash-box right">
			<div class="red-num">+${bean.tradeMoney}</div>
		</div>
	</li>
</c:forEach>
</c:if>
<c:if test="${empty resultVo || empty resultVo.result || empty resultVo.result.resultList}">
<c:if test="${resultVo.result.pageNo == 1}">
	<div class="data-state-box">
       <img src="${imagePath}/wx/o2o-shop/data-null.png" class="data-state-img">
       <p class="data-state-text">没有数据</p>
   </div>
</c:if>
</c:if>
