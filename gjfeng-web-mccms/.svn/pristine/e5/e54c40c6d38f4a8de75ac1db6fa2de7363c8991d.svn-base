<%--

	gjfMemberBank.jsp

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
				<a href="gjfMemberBankAction!newPage.action">新增</a>
			</div>
			<div class="clear"></div>
		</div>
		
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>关联渠道商id</td>
				<td>关联银行id</td>
				<td>银行支行名称</td>
				<td>银行卡账号</td>
				<td>银行卡持有人</td>
				<td>添加时间</td>
				<td>状态（0：删除 1：正常）</td>

				<td>操作</td>
			</tr>

			<c:forEach var="bean" items="${gjfMemberBanks}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.memberId}</td>
					<td>${bean.bankId}</td>
					<td>${bean.bankSub}</td>
					<td>${bean.bankCard}</td>
					<td>${bean.holder}</td>
					<td>${bean.addTime}</td>
					<td>${bean.status}</td>

					<td>
						<a href="gjfMemberBankAction!viewPage.action?id=${bean.id}">查看</a>
						&nbsp; &nbsp;
						<a href="gjfMemberBankAction!retrieveGjfMemberBankById.action?id=${bean.id}">编辑</a>
						&nbsp; &nbsp;
						<a href="gjfMemberBankAction!delGjfMemberBank.action?id=${bean.id}"
								onclick="{if(confirm('你真的要删除吗?')){return true;}return false;}">删除</a>
					</td>
				</tr>
			</c:forEach>

		</table>

		<c:if test="${not empty gjfMemberBanks}">
			<s:form action="gjfMemberBankAction!retrieveAllGjfMemberBanks.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>