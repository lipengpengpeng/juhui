<%--

	gjfMemberDiviHistory.jsp

	Create by MCGT

	Create time 2017-01-20

	Version 1.0

	Copyright 2013 Messcat, Inc. All rights reserved.

 --%>
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
				当前位置: 模块 - 子模块
			</div>
			<!-- <div class="ropt">
				<a href="gjfMemberDiviHistoryAction!newPage.action">新增</a>
			</div> -->
			<div class="clear"></div>
		</div>				
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>消费金额</td>
				<td>让利金额</td>
				<td>交易金额</td>
				<td>交易类型</td>
				<td>交易状态</td>
				<td>交易时间</td>											
			</tr>

			<c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.consumptionMoney}</td>
					<td>${bean.benefitMoney}</td>
					<td>${bean.tradeMoney}</td>
					<td>
					   <c:if test="${bean.tradeType==1}">
					                     增加
					   </c:if>
					    <c:if test="${bean.tradeType==0}">
					                     减少
					   </c:if>
					</td>
					<td>
					 <c:if test="${bean.tradeStatus==1}">
					                     成功
					   </c:if>
					    <c:if test="${bean.tradeStatus==0}">
					                    失败
					   </c:if>
					</td>
				
					<td><fmt:formatDate value="${bean.addTime}" pattern="yyyy-MM-dd mm:HH:ss"/></td>					
				</tr>
			</c:forEach>

		</table>

		<c:if test="${not empty pager.resultList}">
			<s:form action="memberInfoAction!findMemberOpcenterHistory.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>