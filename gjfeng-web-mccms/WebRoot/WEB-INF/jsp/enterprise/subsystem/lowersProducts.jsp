
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<style>
			.headbg1 {
				background: #c9e6f5;
				font-weight: bold;
				padding-left: 15px;
			}
			
			.headbg1 td {
				font-weight: bold;
				text-align: center;
			}
		</style>

		<script>
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 会员管理- 分销商品
			</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="history.back();"/></div>
		</div>					
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>订单</td>
				<td>商品</td>
				<td>商品名称</td>
				<td>商品价格</td>
				<td>支付价格</td>
				<td>数量</td>
			</tr>
			
			 <c:if test="${not empty pager.resultList}">
			 	 <c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.orderId }</td>
					<td>${bean.goodsId }</td>
					<td>${bean.goodsName }</td>
					<td>${bean.goodsAmount }</td>
					<td>${bean.goodsPayAmount }</td>
					<td>${bean.goodsNum }</td>
				</tr>
				</c:forEach>
			 
			 </c:if>
			

		</table>

		<c:if test="${not empty pager.resultList}">
			<s:form action="memberInfoAction!findLowersProducts.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
					<input type="hidden" name="id" value="${id }"/>
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>