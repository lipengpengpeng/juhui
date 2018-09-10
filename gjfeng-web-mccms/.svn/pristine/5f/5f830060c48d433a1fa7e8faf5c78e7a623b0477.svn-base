<%--

	gjfBankInfo.jsp

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
				<a href="gjfBankInfoAction!newPage.action">新增</a>
			</div>
			<div class="clear"></div>
		</div>
		
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>银行代码</td>
				<td>银行名称</td>
				<td>银行图片</td>
				<td>银行url</td>
				<td>银行颜色</td>
				<td>排序</td>
				<td>添加时间</td>
				<td>状态</td>

				<td>操作</td>
			</tr>

			<c:forEach var="bean" items="${gjfBankInfos}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.bankCode}</td>
					<td>${bean.bankName}</td>
					<td>${bean.bankPic}</td>
					<td>${bean.bankUrl}</td>
					<td>${bean.bankColor}</td>
					<td>${bean.orderBy}</td>
					<td>${bean.addTime}</td>
					<td>${bean.status}</td>

					<td>
						<a href="gjfBankInfoAction!viewPage.action?id=${bean.id}">查看</a>
						&nbsp; &nbsp;
						<a href="gjfBankInfoAction!retrieveGjfBankInfoById.action?id=${bean.id}">编辑</a>
						&nbsp; &nbsp;
						<a href="gjfBankInfoAction!delGjfBankInfo.action?id=${bean.id}"
								onclick="{if(confirm('你真的要删除吗?')){return true;}return false;}">删除</a>
					</td>
				</tr>
			</c:forEach>

		</table>

		<c:if test="${not empty gjfBankInfos}">
			<s:form action="gjfBankInfoAction!retrieveAllGjfBankInfos.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>