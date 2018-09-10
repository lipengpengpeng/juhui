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
		jQuery.validator.addMethod("num", function(value,element){
            var tel =/\d+\.?\d*/g;
             return this .optional(element) || (tel.test(value));
         }, "只能输入数字" );
		   
		   jQuery.validator.addMethod("negativeNumber", function(value,element){
            var tel =/^\d+(\.{0,1}\d+){0,1}$/;
             return this .optional(element) || (tel.test(value));
         }, "输入的数字不能是负数" );  
		
			$(document).ready(function(){
				 $("#gjfAttrValueForm").validate({
					 rules: { 
						 "setDivi.consumptionMin" : {
							 required:true,
							 num:true,
							 negativeNumber:true,
						 },
 						 "setDivi.consumptionMax" : {
 							 required:true,
 							 num:true,
 							negativeNumber:true,
						 },
						 "setDivi.consumption" : {
							 required:true,
							 num:true,
							 negativeNumber:true,
						 },						
			
					},
					messages:{  
						"setDivi.consumptionMin" : {
							 required:"请输入累计消费最小金额",							
						 },
						 "setDivi.consumptionMax" : {
							 required:"请输入累计消费最大金额",							
						 },
						 "setDivi.consumption" : {
							 required:"请输入分红权个数比例金额",						    
						 },						
					},
					success: function(label) {
						label.html("&nbsp;").addClass("checked");
			        },
			       
				}); 
				
			});		
			
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">当前位置: 分红权个数设置- 添加分红权个数信息</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="history.back();"/></div>
		</div>

		<form id="gjfAttrValueForm" action="${ctx}/subsystem/tradeInfoAction!newSetDivi.action" method="post" name="gjfAttrValueForm" >
			<table  align="center" class="listTable3">
				<tr>
					<td class="pn-flabel" width="100px">累计消费最小金额</td>
					<td><input type="text"  name="setDivi.consumptionMin" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">累计消费最大金额</td>
					<td><input type="text"  name="setDivi.consumptionMax" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">分红权个数比例金额</td>
					<td><input type="text"  name="setDivi.consumption" /></td>
				</tr>
				
				<input type="hidden"  name="setDivi.status" value="1"/>
				
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