<%--

	gjfMemberAddress.jsp

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
				<a href="gjfMemberAddressAction!newPage.action">新增</a>
			</div>
			<div class="clear"></div>
		</div>
		
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>FK:用户ID</td>
				<td>收货人姓名</td>
				<td>收货人性别(1:男 2:女)</td>
				<td>联系号码</td>
				<td>FK:省份id</td>
				<td>FK:城市id</td>
				<td>FK:区县id</td>
				<td>详细街道地址</td>
				<td>邮编</td>
				<td>是否默认收货地址（0:否 1:是）</td>

				<td>操作</td>
			</tr>

			<c:forEach var="bean" items="${gjfMemberAddresss}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.memberId}</td>
					<td>${bean.consigneeName}</td>
					<td>${bean.consigneeSex}</td>
					<td>${bean.mobile}</td>
					<td>${bean.proviceId}</td>
					<td>${bean.cityId}</td>
					<td>${bean.areaId}</td>
					<td>${bean.addressDetail}</td>
					<td>${bean.zipCode}</td>
					<td>${bean.isDefault}</td>

					<td>
						<a href="gjfMemberAddressAction!viewPage.action?id=${bean.id}">查看</a>
						&nbsp; &nbsp;
						<a href="gjfMemberAddressAction!retrieveGjfMemberAddressById.action?id=${bean.id}">编辑</a>
						&nbsp; &nbsp;
						<a href="gjfMemberAddressAction!delGjfMemberAddress.action?id=${bean.id}"
								onclick="{if(confirm('你真的要删除吗?')){return true;}return false;}">删除</a>
					</td>
				</tr>
			</c:forEach>

		</table>

		<c:if test="${not empty gjfMemberAddresss}">
			<s:form action="gjfMemberAddressAction!retrieveAllGjfMemberAddresss.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>