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
				/* $("#gjfAttrTypeForm").validate({
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
				 */
			});	
			
			
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">当前位置: 微信设置 - 修改微信信息</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="history.back();"/></div>
		</div>

		<form id="gjfAttrTypeForm" name="gjfAttrTypeForm" method="post" action="${ctx}/subsystem/tradeInfoAction!updateWeiXinInfo.action" >
			<table  align="center" class="listTable3">
			    <input type="hidden" value="${resultVo.result.id}" name="weixinInfo.id"/>				
				<tr>
					<td class="pn-flabel" width="100px">微信商户号或appId</td>
					<td><input type="text"  name="weixinInfo.mchId" value="${resultVo.result.mchId}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">微信秘钥</td>
					<td><input type="text"  name="weixinInfo.payKey" value="${resultVo.result.payKey}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">微信公众号partner</td>
					<td><input type="text"  name="weixinInfo.partner" value="${resultVo.result.partner}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">微信公众号partnerKey</td>
					<td><input type="text"  name="weixinInfo.partnerKey" value="${resultVo.result.partnerKey}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="150px">状态</td>
					<td><select name="weixinInfo.type" >
							<option value="0" <c:if test="${resultVo.result.type eq '0'}"> selected="selected" </c:if>>微信支付</option>
							<option value="1" <c:if test="${resultVo.result.type eq '1'}"> selected="selected" </c:if>>微信公众号</option>
							<option value="2" <c:if test="${resultVo.result.type eq '2'}"> selected="selected" </c:if>>h5</option>
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