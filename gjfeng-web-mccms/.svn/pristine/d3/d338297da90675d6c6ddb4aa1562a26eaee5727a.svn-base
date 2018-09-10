<%--

	gjfAttrValue_edit.jsp

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
				$("#gjfAttrValueForm").validate({
					 rules: { 
						 "attrTypeId" : {
							 required:true,
						 },
 						 "gjfAttrValue.columnId" : {
 							 required:true,
						 },
						 "gjfAttrValue.attrValue" : {
							 required:true
						 },
						 "gjfAttrValue.sortOrder" : {
							 required:true
						 },
						 "gjfAttrValue.status" : {
							 required:true
						 }
			
					},
					messages:{  
						"attrTypeId" : {
							 required:"请选择"
						 },
						 "gjfAttrValue.columnId" : {
							 required:"请选择"
						 },
						 "gjfAttrValue.attrValue" : {
							 required:"请输入"
						 },
						 "gjfAttrValue.sortOrder" : {
 							 required:"请输入",
						 },
						 "gjfAttrValue.status" : {
							 required:"请选择"
						 }
					},
					success: function(label) {
						label.html("&nbsp;").addClass("checked");
			        },
			        submitHandler:function(form){
			        	$.ajax({
			        		url : 'attrValueAction!modifyAttrValue.action',
			        		type : 'post',
			        		data : $("#gjfAttrValueForm").serialize(),
			        		dataType : "text",
			        		success : function(data){
			        			var jsondata = eval("("+data+")");  
			        			alert(jsondata.msg);
			        			window.location.href="attrValueAction!retrieveAttrValueByPage.action";
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
			<div class="rpos">当前位置: 商品管理 - 属性值编辑</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="history.back();"/></div>
		</div>

		<form action="attrValueAction!modifyAttrValue.action" method="post" id="gjfAttrValueForm" name="gjfAttrValueForm" enctype="multipart/form-data">
			<table  align="center" class="listTable3">
				<input type="hidden" name="gjfAttrValue.id" value="${gjfAttrValue.id}"/>
				<input type="hidden" name="gjfAttrValue.addTime" value="<fmt:formatDate value="${gjfAttrValue.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				<input type="hidden" name="gjfAttrValue.token" value="${gjfAttrValue.token}"/>
				<tr>
					<td class="pn-flabel" width="100px">属性类型</td>
					<td>
					<select name="attrTypeId" id="attrTypeId" >
						<option value="${gjfAttrType.id }">${gjfAttrType.attrName }</option>
							<c:if test="${not empty gjfAttrTypes }">
								<c:forEach var="attrType" items="${gjfAttrTypes }">
									<option value="${attrType.id }">${attrType.attrName }</option>
								</c:forEach>
							</c:if>
						</select>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">栏目</td>
					<td>
					<select name="gjfAttrValue.columnId" id="gjfAttrValue.columnId" >
						<option value="${gjfAttrValue.columnId }">${gjfAttrValue.columnId }</option>
							<c:if test="${not empty enterpriseColumns }">
								<c:forEach var="enterpriseColumn" items="${enterpriseColumns }">
									<option value="${enterpriseColumn.id }">${enterpriseColumn.names }（${enterpriseColumn.id }）</option>
								</c:forEach>
							</c:if>
						</select>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">属性值</td>
					<td><input type="text" id="attrValue" name="gjfAttrValue.attrValue" value="${gjfAttrValue.attrValue}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">属性排序</td>
					<td><input type="text" id="sortOrder" name="gjfAttrValue.sortOrder" value="${gjfAttrValue.sortOrder}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="150px">状态</td>
					<td>
						<select name="gjfAttrValue.status" >
							<option value="0" <c:if test="${gjfAttrValue.status eq '0'}"> selected="selected" </c:if>>停用</option>
							<option value="1" <c:if test="${gjfAttrValue.status eq '1'}"> selected="selected" </c:if>>启用</option>
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