<%--

	productBrand_view.jsp

	Create by MCGT

	Create time 2016-05-20

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
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="location.href='${ctx}/collection/productBrandAction!retrieveAllProductBrands.action'"/></div>
		</div>

		<table  align="center" class="listTable3">
				<tr>
					<td class="pn-flabel" width="100px">品牌名称</td>
					<td>${productBrand.brandName}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">品牌首字母</td>
					<td>${productBrand.brandInitial}</td>
				</tr>
				<%-- <tr>
					<td class="pn-flabel" width="100px">图片</td>
					<td>${productBrand.brandPic}</td>
				</tr> --%>
				<tr>
					<td class="pn-flabel" width="100px">排序</td>
					<td>${productBrand.brandSort}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">推荐，0为否，1为是，默认为0</td>
					<td>${productBrand.brandRecommend}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">品牌展示类型 0表示图片 1表示文字</td>
					<td>${productBrand.showType}</td>
				</tr>

		 </table>
	</body>
</html>