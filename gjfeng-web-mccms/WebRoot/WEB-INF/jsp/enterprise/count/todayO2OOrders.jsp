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
			function back(){
				window.location.href = "countInfoAction!diviDataList.action"; 
			}
		
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 今日O2O订单列表
			</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="back();"/></div>
		</div>					
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>交易流水号</td>
				<td>用户</td>
				<td>商家</td>
				<td>金额</td>
				<td>添加时间</td>
				<td>支付类型</td>
				<td>交易状态</td>
				
			</tr>
			
			 
			 <c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.tradeNo }</td>
					<td>${bean.name }</td>
					<td>${bean.storeName }</td>
					<td>${bean.tradeMoney }</td>
					<td>
						<fmt:formatDate value="${bean.addTime }" pattern="yyyy-MM-dd"/>
					</td>
					<td>
						<c:choose>
							<c:when test="${bean.payType eq '0'}">微信支付</c:when>
							<c:when test="${bean.payType eq '1'}">支付宝支付</c:when>
							<c:when test="${bean.payType eq '2'}">银联支付</c:when>
							<c:when test="${bean.payType eq '4'}">授信金额支付</c:when>
							
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${bean.tradeStatus eq '0'}">待支付</c:when>
							<c:when test="${bean.tradeStatus eq '1'}">已支付</c:when>
							<c:when test="${bean.tradeStatus eq '2'}">支付失败</c:when>
							<c:when test="${bean.tradeStatus eq '3'}">取消</c:when>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</table>

		<c:if test="${not empty pager.resultList}">
			<s:form action="countInfoAction!findTodayO2OOrders.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>