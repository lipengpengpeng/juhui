<%--

	pageType.jsp

	Create by MCGT

	Create time 2013-08-20

	Version 1.0

	Copyright 2013 Messcat, Inc. All rights reserved.

 --%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>模板</title>
		<link href="${ctx}/js/AsyncBox/skins/ZCMS/asyncbox.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${ctx}/js/AsyncBox/AsyncBox.v1.4.js"></script>
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
			function doYouWantToDel() {
				if(confirm('<s:text name="common.word.want.to.delete" />')) {
					return true;
				}
				return false;
			}
		</script>
	</head>
	<body style="padding:8px;">
		<c:if test="${not empty message}">
			<script>
				asyncbox.tips('${message}', 'error');
			</script>
		</c:if>
		<div class="rhead">
			<div class="rpos"><!-- 当前位置: 系统配置 - 页面类型管理 --><s:text name="enterprise.system.page.type.current.location" /></div>
			<div class="ropt"><a href="pageTypeAction!newPage.action"><!-- 新增 --><s:text name="common.word.new" /></a></div>
			<div class="clear"></div>
		</div>
		
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td><!-- 序号 --><s:text name="common.word.id" /></td>
				<td><!-- 类型名称 --><s:text name="enterprise.system.page.type.name" /></td>
				<td><!-- 模板类型 --><s:text name="enterprise.system.page.type.template.type" /></td>
				<td><!-- URL生成模板 --><s:text name="enterprise.system.page.type.template.url" /></td>
				<td><!-- 简介 --><s:text name="enterprise.system.page.type.intro" /></td>

				<td><!-- 操作 --><s:text name="common.word.handle" /></td>
			</tr>

			<c:forEach var="bean" items="${pageTypes}"
				varStatus="status">
				<tr>
					<td style="text-align:center;">${status.count}</td>
					<td style="text-align:center;">${bean.name}</td>
					<td style="text-align:center;">${bean.templateType}</td>
					<td style="text-align:center;">${bean.templateUrl}</td>
					<td style="text-align:center;">${bean.intro}</td>

					<td style="text-align:center;">
						<a href="pageTypeAction!viewPage.action?id=${bean.id}">
							<!-- 查看  --><s:text name="common.word.view" />
						</a>
						&nbsp; &nbsp;
						<a href="pageTypeAction!retrievePageTypeById.action?id=${bean.id}">
							<!-- 编辑  --><s:text name="common.word.edit" />
						</a>
						&nbsp; &nbsp;
						<a href="pageTypeAction!delPageType.action?id=${bean.id}" onclick="return doYouWantToDel();">
							<!-- 删除  --><s:text name="common.word.delete" />
						</a>
					</td>
				</tr>
			</c:forEach>

		</table>

		<c:if test="${not empty pageTypes}">
			<s:form action="pageTypeAction!retrieveAllPageTypes.action"
					method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>