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
		
		<form action="tradeInfoAction!findMemberPointHistory.action" method="post">
			<table class="listTable2">
				<tr>
					<td>
						&nbsp;&nbsp;用户名称：<input type="text" placeholder="请输入用户名称" name="memberName" value="${memberName }"/>
						&nbsp;&nbsp;用户电话：<input type="text" placeholder="请输入用户电话" name="memberMobile" value="${memberMobile }"/>&nbsp;&nbsp;
						&nbsp;&nbsp;转移用户名称：<input type="text" placeholder="请输入转移用户名称" name="transferMemberName" value="${transferMemberName}"/>&nbsp;&nbsp;
						&nbsp;&nbsp;转移用户电话号码：<input type="text" placeholder="请输入转移用户电话号码" name="transferMemberMobile" value="${transferMemberMobile}"/>&nbsp;&nbsp;							                         
		                <input type="submit" style="margin-left:30px;" class="default_button" value='<s:text name="common.word.search" />' />
					</td>
				</tr>
			</table>
		</form>

		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>用户名称</td>
				<td>用户号码</td>
				<td>合并用户名称</td>
				<td>合并用户电话</td>
				<td>余额</td>
				<td>积分</td>
				<td>累计总额</td>
				<td>销售总额</td>
				<td>添加时间</td>	
				<td>操作</td>				
			</tr>

			<c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.memberName}</td>
					<td>${bean.memberMobile}</td>
					<td>${bean.mergeMemberName}</td>
					<td>${bean.mergeMemberMobile}</td>
					<td>${bean.memberBalanceBF}</td>
					<td>${bean.memberIntegralBf}</td>
					<td>${bean.memberCousumptionBf}</td>
					<td>${bean.merchantSaleBf}</td>
					<td><fmt:formatDate value="${bean.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										
					<td>
						<a href="tradeInfoAction!findMemberMergeDetailById.action?id=${bean.id}">查看详情</a>		
					</td> 
				</tr>
			</c:forEach>

		</table>

		<c:if test="${not empty pager.resultList}">
			<s:form action="tradeInfoAction!findMergeMemberHistory.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>