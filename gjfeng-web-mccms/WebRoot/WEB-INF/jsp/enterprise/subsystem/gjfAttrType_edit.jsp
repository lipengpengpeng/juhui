<%--

	gjfAttrType_edit.jsp

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
				$("#gjfAttrTypeForm").validate({
					 rules: { 
						 "gjfAttrType.attrName" : {
							 required:true,
							 maxlength:100,
							 minlength:0
						 },
 						 "gjfAttrType.sortOrder" : {
 							 required:true,
 							 number:true,
 							 max:100000,
 							 min:0
						 },
						 "gjfAttrType.status" : {
							 required:true
						 }
					},
					messages:{  
						 "gjfAttrType.attrName" : {
							 required:"请输入属性类型名称"
						 },
						 "gjfAttrType.sortOrder" : {
							 required:"请输入",
							 number:"请输入数字",
							 max:"不能超过100000",
							 min:"最小为0"
						 },
						 "gjfAttrType.status" : {
							 required:"请选择"
						 }
					},
					success: function(label) {
						label.html("&nbsp;").addClass("checked");
			        },
			        submitHandler:function(form){
			        	$.ajax({
			        		url : 'attrTypeAction!modifyAttrType.action',
			        		type : 'post',
			        		data : $("#gjfAttrTypeForm").serialize(),
			        		dataType : "text",
			        		success : function(data){
			        			var jsondata = eval("("+data+")");  
			        			alert(jsondata.msg);
			        			window.location.href="attrTypeAction!retrieveAttrTypeByPage.action";
			        		},
			        		error : function(data){
			        			 alert("修改出错!请稍后再试");
			        		}
			        	});
			        }
				});
				
			});	
			
			
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">当前位置: 商品管理 - 属性类型编辑</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="history.back();"/></div>
		</div>

		<form id="gjfAttrTypeForm" name="gjfAttrTypeForm" enctype="multipart/form-data">
			<table  align="center" class="listTable3">
				<input type="hidden" name="gjfAttrType.id" value="${gjfAttrType.id}"/>
				<input type="hidden" name="gjfAttrType.token" value="${gjfAttrType.token }"/>
				<input type="hidden" name="gjfAttrType.addTime" value="<fmt:formatDate value="${gjfAttrType.addTime }" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
				<tr>
					<td class="pn-flabel" width="100px">属性名</td>
					<td><input type="text" id="attrName" name="gjfAttrType.attrName" value="${gjfAttrType.attrName}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">属性排序</td>
					<td><input type="text" id="sortOrder" name="gjfAttrType.sortOrder" value="${gjfAttrType.sortOrder}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="150px">状态</td>
					<td><select name="gjfAttrType.status" >
							<option value="0" <c:if test="${gjfAttrType.status eq '0'}"> selected="selected" </c:if>>停用</option>
							<option value="1" <c:if test="${gjfAttrType.status eq '1'}"> selected="selected" </c:if>>启用</option>
						</select>
					</td>
				</tr>

				<tr>
					<td></td>
					<td>
						<input type="submit" id="submitButton" class="defaultButton" value=" 提 交 "/>
					</td>
				</tr>
		 	</table>
		</form>
	</body>
</html>