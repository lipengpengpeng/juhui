<%--

	gjfProductAttr_new.jsp

	Create by MCGT

	Create time 2017-01-10

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
			        },
			        submitHandler:function(form){
			        	var proId = $("#productId").val();
			        	$.ajax({
			        		url : 'productAttrAction!addProductAttr.action',
			        		type : 'post',
			        		data : $("#gjfProductAttrForm").serialize(),
			        		dataType : "text",
			        		success : function(data){
			        			var jsondata = eval("("+data+")");  
			        			alert(jsondata.msg);
			        			window.location.href="productAttrAction!retrieveProductAttrById.action?proId="+proId;
			        		},
			        		error : function(data){
			        			 alert("添加出错!请稍后再试");
			        		}
			        	});
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

		<form id="gjfProductAttrForm" name="gjfProductAttrForm" enctype="multipart/form-data">
			<table  align="center" class="listTable3">
				<tr>
					<td class="pn-flabel" width="100px">商品</td>
					<td>
						<input type="hidden" id="productId" readonly="readonly" value="${gjfProductInfo.id}" name="proId" />
						${gjfProductInfo.name}
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px"></td>
					<td>
						<select>
							<option value="">请选择</option>
							<c:forEach items="${beanMap.value}" var="bean">
								<option value="${bean[0]}">${bean[1]}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">属性值</td>
					<td>
						<select name="attrValueId" id="attrValueId" >
							<c:if test="${not empty gjfAttrValues }">
								<c:forEach var="attrValue" items="${gjfAttrValues }">
									<option value="${attrValue.id }">${attrValue.attrValue }</option>
								</c:forEach>
							</c:if>
						</select>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">排序</td>
					<td><input type="text" id="orderBy" name="gjfProductAttr.orderBy" /></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<input type="submit" id="submitButton"  class="defaultButton" value=" 提 交 "/>
					</td>
				</tr>
		 	</table>
		</form>
	</body>
</html>