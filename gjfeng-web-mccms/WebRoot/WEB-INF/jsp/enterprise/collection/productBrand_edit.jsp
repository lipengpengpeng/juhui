<%--

	productBrand_edit.jsp

	Create by MCGT

	Create time 2016-05-20

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
		<title>更新模板</title>
		<script>
			$(document).ready(function(){
				$("#productBrandForm").validate({
					rules: { 
						"productBrand.brandSort" :{
						 	digits:true,
							min : 0,
							max : 255
						}
					},
					success: function(label) {
						label.html("&nbsp;").addClass("checked");
			        }	
				});
				
			});		
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">当前位置: 商品管理 - 商品品牌 - 编辑</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="location.href='${ctx}/collection/productBrandAction!retrieveAllProductBrands.action'"/></div>
		</div>

		<form action="productBrandAction!editProductBrand.action" method="post" id="productBrandForm" name="productBrandForm" enctype="multipart/form-data">
			<table  align="center" class="listTable3">
				<input type="hidden" name="id" value="${productBrand.id}"/>
				<tr>
					<td class="pn-flabel" width="100px">品牌名称</td>
					<td><input type="text" id="brandName" name="productBrand.brandName" value="${productBrand.brandName}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">品牌首字母</td>
					<td><input type="text" id="brandInitial" name="productBrand.brandInitial" value="${productBrand.brandInitial}" /></td>
				</tr>
				<%-- <tr>
					<td class="pn-flabel" width="100px">图片</td>
					<td><input type="text" id="brandPic" name="productBrand.brandPic" value="${productBrand.brandPic}" /></td>
				</tr> --%>
				<tr>
					<td class="pn-flabel" width="100px">排序</td>
					<td><input type="text" id="brandSort" name="productBrand.brandSort" value="${productBrand.brandSort}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">推荐</td>
					<td>
						<select id="lineType" name="productBrand.brandRecommend" style="width: 153px;">
				    		<option value="0">否</option>
				    		<option value="1" <c:if test="${productBrand.brandRecommend ==1}"></c:if>>是</option>
    				 	</select>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">品牌展示类型</td>
					<td>
						<select id="lineType" name="productBrand.showType" style="width: 153px;">
				    		<!-- <option value="0">图片</option> -->
				    		<option value="1">文字</option>
    				 	</select>
					</td>
				</tr>

				<tr>
					<td></td>
					<td>
						<input type="submit" class="defaultButton" value=" 提 交 "/>
					</td>
				</tr>
		 	</table>
		</form>
	</body>
</html>