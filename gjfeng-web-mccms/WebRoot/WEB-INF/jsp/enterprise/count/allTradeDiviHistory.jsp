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
				window.location.href="countInfoAction!diviDataList.action";
			}
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 分红发放记录
			</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="back();"/></div>
			<div class="clear"></div>
		</div>	
						
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>用户</td>
				<td>分红交易流水号</td>
				<td>交易金额</td>
				<td>分红权数</td>
				<td>分红单价</td>
				<td>添加时间</td>
				<td>交易类型</td>		
			</tr> 
		<c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.memberId.name}</td>
					<td>${bean.tradeNo}</td>
					<td>${bean.tradeMoney}</td>
					<td>${bean.tradeDiviNum}</td>
					<td>${bean.tradeUnit}</td>
					<td><fmt:formatDate value="${bean.addTime }" pattern="yyyy-MM-dd"/></td>
					<td>
						<c:choose>
							<c:when test="${bean.tradeType eq '0'}">直推会员分红</c:when>
							<c:when test="${bean.tradeType eq '1'}">直推商家分红</c:when>
							<c:when test="${bean.tradeType eq '2'}">普通用户分红</c:when>
							<c:when test="${bean.tradeType eq '3'}">普通商家分红</c:when>
							<c:when test="${bean.tradeType eq '4'}">市代分红</c:when>
							<c:when test="${bean.tradeType eq '5'}">区代分红</c:when>
							<c:when test="${bean.tradeType eq '6'}">个代分红</c:when>
						</c:choose>
					</td>
					
				</tr>
			</c:forEach>
		</table>

		<c:if test="${not empty pager.resultList}">
			<s:form action="tradeInfoAction!findAllTradeDiviHistory.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>