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
				当前位置: 当前池明细
			</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="history.back();"/></div>
			<div class="clear"></div>
		</div>		
						
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>当次资金池分红金额</td>
				<td>当次资金池分红前金额</td>
				<td>当次资金池分红后金额</td>	
				<td>当次资金池分红单价</td>	
				<td>当次资金池分红时间</td>	
				<td>交易时间</td>	
			</tr>
			
			<c:forEach var="bean" items="${pager.resultList}"
					varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td>${bean.benefitMoney}</td>
						<td>${bean.benefitMoneyBF}</td>
						<td>${bean.benefitMoneyAF}</td>
						<td>${bean.unitPrice}</td>
						<td><fmt:formatDate value="${bean.addTime }" pattern="yyyy-MM-dd"/></td>
						<td><fmt:formatDate value="${bean.tradeTime }" pattern="yyyy-MM-dd"/></td>
					</tr>
			</c:forEach>
		</table>

		<c:if test="${not empty pager.resultList}">
			<s:form action="countInfoAction!findPoolInDetail.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
					<input type="hidden" name="addTime" value="${addTime }" />
					<input type="hidden" name="type" value="${type }" />
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>