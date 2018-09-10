<%--

	productBrand.jsp

	Create by MCGT

	Create time 2016-05-20

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
				当前位置: 商品管理 - 商品品牌
			</div>
			<div class="ropt">
				<a href="productBrandAction!newPage.action">新增</a>
			</div>
			<div class="clear"></div>
		</div>
		
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>排序</td>
				<td>品牌名称</td>
				<td>品牌首字母</td>
				<td>图片</td>
				<td>推荐</td>
				<td>品牌展示类型</td>

				<td>操作</td>
			</tr>

			<c:forEach var="bean" items="${productBrands}"
				varStatus="status">
				<tr>
					<td>${bean.brandSort}</td>
					<td>${bean.brandName}</td>
					<td>${bean.brandInitial}</td>
					<td>${bean.brandPic}</td>
					<td>
						<c:if test="${bean.brandRecommend ==0}">否</c:if>
						<c:if test="${bean.brandRecommend ==1}">是</c:if>
					</td>
					<td>
						<c:if test="${bean.showType ==0}">图片</c:if>
						<c:if test="${bean.showType ==1}">文字</c:if>
					</td>

					<td>
						<a href="productBrandAction!retrieveProductBrandById.action?id=${bean.id}">编辑</a>
						&nbsp; &nbsp;
						<a href="productBrandAction!delProductBrand.action?id=${bean.id}"
								onclick="{if(confirm('你真的要删除吗?')){return true;}return false;}">删除</a>
					</td>
				</tr>
			</c:forEach>

		</table>

		<c:if test="${not empty productBrands}">
			<s:form action="productBrandAction!retrieveAllProductBrands.action"
					namespace="/collection" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>