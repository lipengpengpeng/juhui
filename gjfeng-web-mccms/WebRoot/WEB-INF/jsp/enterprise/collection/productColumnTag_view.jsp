﻿<%--

	productColumnTag_view.jsp

	Create by MCGT

	Create time 2014-08-28

	Version 1.0

	Copyright 2013 Messcat, Inc. All rights reserved.

 --%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>查看模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<script>
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">当前位置: 模块 - 子模块</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="location.href='${ctx}/collection/productColumnTagAction!retrieveAllProductColumnTags.action'"/></div>
		</div>

		<table  align="center" class="listTable3">
				<tr>
					<td class="pn-flabel" width="100px">栏目ID</td>
					<td>${productColumnTag.productColumnId}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">标签id FK: TAG_LIB</td>
					<td>${productColumnTag.tagId}</td>
				</tr>

		 </table>
	</body>
</html>