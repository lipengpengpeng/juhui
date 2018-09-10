<%--

	gjfAttrValue_new.jsp

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
				$("#gjfAttrValueForm").validate({
					 rules: { 
						 "accNo" : {
							 required:true,
						 },
 						 "holerName" : {
 							 required:true,
						 },
						 "tanamt" : {
							 required:true
						 },						 
			
					},
					messages:{  
						"accNo" : {
							 required:"银行卡号不能为空"
						 },
						 "holerName" : {
							 required:"持卡人姓名不能为空"
						 },
						 "tanamt" : {
							 required:"充值金额不能为空"
						 },
						
					},
					success: function(label) {
						label.html("&nbsp;").addClass("checked");
			        },
			        submitHandler:function(form){
			        	$.ajax({
			        		url : '${ctx}/subsystem/tradeInfoAction!newRecharPaid.action',
			        		type : 'post',
			        		data : $("#gjfAttrValueForm").serialize(),
			        		dataType : "text",
			        		success : function(data){
			        			var jsondata = eval("("+data+")");  
			        			alert(jsondata.msg);
			        			window.location.href="${ctx}/subsystem/tradeInfoAction!goPaidPayPage.action";
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
			<div class="rpos">当前位置: 代付管理 - 代付充值</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="history.back();"/></div>
		</div>

		<form id="gjfAttrValueForm" name="gjfAttrValueForm" enctype="multipart/form-data">
			<table  align="center" class="listTable3">
				
				<tr>
					<td class="pn-flabel" width="100px">银行卡号</td>
					<td><input type="text" id="accNo" name="accNo" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">持卡人姓名</td>
					<td><input type="text" id="holerName" name="holerName" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">充值金额</td>
					<td><input type="text" id="tanAmt" name="tanamt" /></td>
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