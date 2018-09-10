<%--

	pageType_view.jsp

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
		<title>查看模板</title>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				<!-- 当前位置: 系统配置 - 页面类型管理 --><s:text name="enterprise.system.page.type.current.location" /> -
				<!-- 查看页面类型信息 --><s:text name="enterprise.system.page.type.current.location.view" />
			</div>
			<div class="ropt"><input type="button" value="<s:text name="common.word.return.back" />" onclick="location.href='${ctx}/pageTypeAction!retrieveAllPageTypes.action'"/></div>
		</div>

		<table  align="center" class="listTable3">
				<tr>
					<td class="pn-flabel" width="100px"><!-- 类型名称 --><s:text name="enterprise.system.page.type.name" /></td>
					<td>${pageType.name}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px"><!-- 模板类型 --><s:text name="enterprise.system.page.type.template.type" /></td>
					<td>${pageType.templateType}</td>
				</tr>
				<c:if test="${pageType.templateType != 'link'}">
					<tr>
						<td class="pn-flabel" width="100px"><!-- URL生成模板 --><s:text name="enterprise.system.page.type.template.url" /></td>
						<td>${pageType.templateUrl}</td>
					</tr>
				</c:if>
				<c:if test="${pageType.templateType == 'other'}">
					<tr>
						<td class="pn-flabel" width="100px"><!-- 新功能URL --><s:text name="enterprise.system.page.type.features.url" /></td>
						<td>${pageType.featuresUrl}</td>
					</tr>
				</c:if>
				<tr>
					<td class="pn-flabel" width="100px"><!-- 简介 --><s:text name="enterprise.system.page.type.intro" /></td>
					<td>${pageType.intro}</td>
				</tr>

		 </table>
	</body>
</html>