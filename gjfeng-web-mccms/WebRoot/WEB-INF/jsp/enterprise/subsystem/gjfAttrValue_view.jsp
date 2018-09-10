<%--

	gjfAttrValue_view.jsp

	Create by MCGT

	Create time 2017-01-10

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
			<div class="rpos">当前位置: 商品管理 - 查看属性值</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="history.back();"/></div>
		</div>

		<table  align="center" class="listTable3">
				<tr>
					<td class="pn-flabel" width="100px">FK:属性类型ID</td>
					<td>${gjfAttrValue.attrId}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">属性值</td>
					<td>${gjfAttrValue.attrValue}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">属性排序</td>
					<td>${gjfAttrValue.sortOrder}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">新增时间</td>
					<td>${gjfAttrValue.addTime}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">编辑时间</td>
					<td>${gjfAttrValue.editTime}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">状态（0:停用 1:启用）</td>
					<td>${gjfAttrValue.status}</td>
				</tr>

		 </table>
	</body>
</html>