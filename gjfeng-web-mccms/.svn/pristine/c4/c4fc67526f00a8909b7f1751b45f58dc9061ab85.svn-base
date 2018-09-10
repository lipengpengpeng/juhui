<%--

	gjfProductAttr_edit.jsp

	Create by MCGT

	Create time 2017-01-10

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
				$("#gjfProductAttrForm").validate({
					rules: { 
						"gjfProductAttr.productId" : {
							 required:true
						},
						"attrValueId" : {
							 required:true
						},
						"gjfProductAttr.orderBy" : {
							 required:true
						}
					},
					messages : {
						"gjfProductAttr.productId" : {
							 required:"请选择"
						},
						"attrValueId" : {
							 required:"请选择"
						},
						"gjfProductAttr.orderBy" : {
							 required:"请输入"
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
			<div class="rpos">当前位置: 模块 - 子模块</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="history.back();"/></div>
		</div>

		<form action="gjfProductAttrAction!editGjfProductAttr.action" method="post" id="gjfProductAttrForm" name="gjfProductAttrForm" enctype="multipart/form-data">
			<table  align="center" class="listTable3">
				<input type="hidden" name="id" value="${gjfProductAttr.id}"/>
				<tr>
					<td class="pn-flabel" width="100px">商品ID</td>
					<td><input type="text" id="productId" name="gjfProductAttr.productId" value="${gjfProductAttr.productId}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">属性值ID</td>
					<td>
						<select name="attrValueId" id="attrValueId" >
							<c:if test="${not empty gjfAttrValues }">
								<c:forEach var="attrValue" items="${gjfAttrValues }">
									<option value="${attrValue.id }">${attrValue.attrValue }（${attrValue.id }）</option>
								</c:forEach>
							</c:if>
						</select>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">排序</td>
					<td><input type="text" id="orderBy" name="gjfProductAttr.orderBy" value="${gjfProductAttr.orderBy}" /></td>
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