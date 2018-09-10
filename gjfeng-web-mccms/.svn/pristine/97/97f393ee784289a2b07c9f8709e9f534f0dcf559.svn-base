<%--

	productBrand_new.jsp

	Create by MCGT

	Create time 2016-05-20

	Version 1.0

	Copyright 2013 Messcat, Inc. All rights reserved.

 --%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>添加模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
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
			<div class="rpos">当前位置: 商品管理 - 商品品牌- 新增</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="location.href='${ctx}/collection/productBrandAction!retrieveAllProductBrands.action'"/></div>
		</div>

		<form action="productBrandAction!newProductBrand.action" method="post" id="productBrandForm" name="productBrandForm" enctype="multipart/form-data">
			<table  align="center" class="listTable3">
				<tr>
					<td class="pn-flabel" width="100px">品牌名称</td>
					<td><input type="text" id="brandName" name="productBrand.brandName" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">品牌首字母</td>
					<td><input type="text" id="brandInitial" name="productBrand.brandInitial" /></td>
				</tr>
				<!-- <tr>
					<td class="pn-flabel" width="100px">图片</td>
					<td><input type="text" id="brandPic" name="productBrand.brandPic" /></td>
				</tr> -->
				<tr>
					<td class="pn-flabel" width="100px">排序</td>
					<td><input type="text" id="brandSort" name="productBrand.brandSort"  />
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">推荐</td>
					<td>
						<select id="lineType" name="productBrand.brandRecommend" style="width: 153px;">
				    		<option value="0">否</option>
				    		<option value="1">是</option>
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