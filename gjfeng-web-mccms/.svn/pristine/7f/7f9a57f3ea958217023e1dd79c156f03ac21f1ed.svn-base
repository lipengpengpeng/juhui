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
				<td>购买的用户</td>
				<td>购买的商家</td>
				<td>让利金额</td>
				<td>添加时间</td>

				<td>操作</td>
			</tr>

			<c:forEach var="bean" items="${gjfMemberBenefitRecords}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.memberId}</td>
					<td>${bean.storeId}</td>
					<td>${bean.benefitMoney}</td>
					<td>${bean.addTime}</td>

					<td>
						<a href="gjfMemberBenefitRecordAction!viewPage.action?id=${bean.id}">查看</a>
						&nbsp; &nbsp;
						<a href="gjfMemberBenefitRecordAction!retrieveGjfMemberBenefitRecordById.action?id=${bean.id}">编辑</a>
						&nbsp; &nbsp;
						<a href="gjfMemberBenefitRecordAction!delGjfMemberBenefitRecord.action?id=${bean.id}"
								onclick="{if(confirm('你真的要删除吗?')){return true;}return false;}">删除</a>
					</td>
				</tr>
			</c:forEach>

		</table>

		<c:if test="${not empty gjfMemberBenefitRecords}">
			<s:form action="gjfMemberBenefitRecordAction!retrieveAllGjfMemberBenefitRecords.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>