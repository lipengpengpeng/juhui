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
				/* $("#gjfAttrValueForm").validate({
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
			        		url : 'attrValueAction!addAttrValue.action',
			        		type : 'post',
			        		data : $("#gjfAttrValueForm").serialize(),
			        		dataType : "text",
			        		success : function(data){
			        			var jsondata = eval("("+data+")");  
			        			alert(jsondata.msg);
			        			window.location.href="attrValueAction!retrieveAttrValueByPage.action";
			        		},
			        		error : function(data){
			        			 alert("添加出错!请稍后再试");
			        		}
			        	}); 
			        }
				}); */
				
			});		
			
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">当前位置: 微信设置- 添加微信信息</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="history.back();"/></div>
		</div>

		<form id="gjfAttrValueForm" action="${ctx}/subsystem/tradeInfoAction!newWeiXinInfo.action" method="post" name="gjfAttrValueForm" >
			<table  align="center" class="listTable3">
				<tr>
					<td class="pn-flabel" width="100px">微信商户号或appId</td>
					<td><input type="text"  name="weixinInfo.mchId" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">微信秘钥</td>
					<td><input type="text"  name="weixinInfo.payKey" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">微信公众号partner值</td>
					<td><input type="text"  name="weixinInfo.partner" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">微信公众号partnerKey值</td>
					<td><input type="text"  name="weixinInfo.partnerKey" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="150px">类型</td>
					<td>
						<select name="weixinInfo.type">
							<option value="0">微信支付</option>
							<option value="1">微信公众号</option>
							<option value="2">H5</option>
						</select>
					</td>
				</tr>

				<input type="hidden"  name="weixinInfo.status" value="0"/>
				

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