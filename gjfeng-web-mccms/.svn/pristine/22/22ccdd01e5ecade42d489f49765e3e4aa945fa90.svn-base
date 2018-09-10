<%--

	gjfMemberBenefitRecord.jsp

	Create by MCGT

	Create time 2017-01-10

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
			<div class="ropt">
				<a href="gjfMemberBenefitRecordAction!newPage.action">新增</a>
			</div>
			<div class="clear"></div>
		</div>
		
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>充值的用户</td>
				<td>申请金额</td>
				<td>交易金额 </td>
				<td>交易状态 </td>
				<td>支付类型 </td>
				<td>添加时间</td>
				<td>操作</td>
			</tr>

			<c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.memberId.name}</td>
					<td>${bean.applyMoney}</td>
					<td>${bean.tradeMoney}</td>
					<td>
					  <c:if test="${bean.shouxinType==0}">
						 <c:if test="${bean.tradeStatus==0}">待支付</c:if>
					  </c:if>
					  <c:if test="${bean.shouxinType==1}">
						 <c:if test="${bean.tradeStatus==0}">待审核</c:if>
					  </c:if>				
					  <c:if test="${bean.tradeStatus==1}">支付成功</c:if>
					  <c:if test="${bean.tradeStatus==2}">交易失败</c:if>
					</td>
					<td>
					  <c:if test="${bean.payType==0}">余额支付</c:if>
					  <c:if test="${bean.payType==1}">微信支付</c:if>
					  <c:if test="${bean.payType==2}">支付宝支付</c:if>
					  <c:if test="${bean.payType==3}">银联支付</c:if>
					  <c:if test="${bean.payType==5}">后台充值消费</c:if>
					  <c:if test="${bean.payType==6}">线下充值</c:if>
					</td>					
					<td><fmt:formatDate value="${bean.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>

					<td>	
					 <c:if test="${bean.shouxinType==1&&bean.tradeStatus==0}">					
						<a href="tradeInfoAction!findShouxinDetailById.action?id=${bean.id}">审核</a>		
					 </c:if>				
					</td>
				</tr>
			</c:forEach>

		</table>

		<c:if test="${not empty pager.resultList}">
			<s:form action="tradeInfoAction!findShouxinHistory.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>