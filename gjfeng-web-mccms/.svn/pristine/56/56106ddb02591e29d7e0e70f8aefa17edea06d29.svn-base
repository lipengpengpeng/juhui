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
				<td>用户</td>
				<td>分红权交易号</td>
				<td>分红权数量</td>
				<td>分红权金额</td>
				<td>分红权额度</td>
				<td>单笔消费金额</td>
				<td>添加时间</td>
				<td>支付类型</td>
				<td>支付状态</td>
				<td>分红权类型</td>
			</tr>

			<c:forEach var="bean" items="${gjfMemberDiviHistorys}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.memberId.name}</td>
					<td>${bean.diviNo}</td>
					<td>${bean.diviNum}</td>
					<td>${bean.diviMoney}</td>
					<td>${bean.diviMoneyBla}</td>
					<td>${bean.consumptionMoney}</td>
					<td><fmt:formatDate value="${bean.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>
						<c:choose>
							<c:when test="${bean.payType eq 0}">微信支付</c:when>
							<c:when test="${bean.payType eq 1}">支付宝支付</c:when>
							<c:when test="${bean.payType eq 2}">银联支付</c:when>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${bean.payStatus eq 0}">待支付 </c:when>
							<c:when test="${bean.payStatus eq 1}">支付成功</c:when>
							<c:when test="${bean.payStatus eq 2}">支付失败</c:when>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${bean.diviStatus eq 0}">消费 </c:when>
							<c:when test="${bean.diviStatus eq 1}">充值</c:when>
						</c:choose>
					</td>

					<%-- <td>
						<a href="gjfMemberDiviHistoryAction!viewPage.action?id=${bean.id}">查看</a>
						&nbsp; &nbsp;
						<a href="gjfMemberDiviHistoryAction!retrieveGjfMemberDiviHistoryById.action?id=${bean.id}">编辑</a>
						&nbsp; &nbsp;
						<a href="gjfMemberDiviHistoryAction!delGjfMemberDiviHistory.action?id=${bean.id}"
								onclick="{if(confirm('你真的要删除吗?')){return true;}return false;}">删除</a>
					</td> --%>
				</tr>
			</c:forEach>

		</table>

		<c:if test="${not empty gjfMemberDiviHistorys}">
			<s:form action="tradeInfoAction!retrieveDiviHistoryByPager.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>