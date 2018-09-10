<%--

	gjfMemberTradeDivi.jsp

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
			<div class="clear"></div>
		</div>
		
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>用户</td>
				<td>分红交易流水号</td>
				<td>交易金额</td>
				<td>交易比例</td>
				<td>添加时间</td>
				<td>交易类型</td>
				<td>交易状态</td>
				<td>交易附言</td>
			</tr>

			<c:forEach var="bean" items="${gjfMemberTradeDivis}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.memberId.name}</td>
					<td>${bean.tradeNo}</td>
					<td>${bean.tradeMoney}</td>
					<td>${bean.tradeRatio}</td>
					<td><fmt:formatDate value="${bean.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>
						<c:choose>
							<c:when test="${bean.tradeType eq 0}">直推会员分红</c:when>
							<c:when test="${bean.tradeType eq 1}">直推商家分红权</c:when>
							<c:when test="${bean.tradeType eq 2}">普通用户分红权</c:when>
							<c:when test="${bean.tradeType eq 3}">普通商家分红权</c:when>
							<c:when test="${bean.tradeType eq 4}">市代分红权</c:when>
							<c:when test="${bean.tradeType eq 5}">区代分红权</c:when>
							<c:when test="${bean.tradeType eq 6}">个代分红权</c:when>
						</c:choose>
						
					</td>
					<td>
						<c:choose>
							<c:when test="${bean.tradeStatus eq 0}">提交中</c:when>
							<c:when test="${bean.tradeStatus eq 1}">交易成功</c:when>
							<c:when test="${bean.tradeStatus eq 2}">交易失败</c:when>
						</c:choose>
					</td>
					<td>${bean.tradeTrmo}</td>

					<%-- <td>
						<a href="gjfMemberTradeDiviAction!viewPage.action?id=${bean.id}">查看</a>
						&nbsp; &nbsp;
						<a href="gjfMemberTradeDiviAction!retrieveGjfMemberTradeDiviById.action?id=${bean.id}">编辑</a>
						&nbsp; &nbsp;
						<a href="gjfMemberTradeDiviAction!delGjfMemberTradeDivi.action?id=${bean.id}"
								onclick="{if(confirm('你真的要删除吗?')){return true;}return false;}">删除</a>
					</td> --%>
				</tr>
			</c:forEach>

		</table>

		<c:if test="${not empty gjfMemberTradeDivis}">
			<s:form action="tradeInfoAction!retrieveDiviByPager.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>