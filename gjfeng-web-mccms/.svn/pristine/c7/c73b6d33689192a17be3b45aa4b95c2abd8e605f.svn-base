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
			$("#submitButton").click(function(){
				$.ajax({
	        		url : 'storeInfoAction!refreshLocation.action',
	        		type : 'post',
	        		data : {
	        			id:$("#id").val(),
	        			type:$("#type").val(),
	        			mobile:$("#mobile").val()
	        		},
	        		dataType : "text",
	        		success : function(data){
	        			var jsondata = eval("("+data+")");  
	        			alert(jsondata.msg);
	        		},
	        		error : function(data){
	        			 alert("操作出错!请稍后再试");
	        		}
	        	});
			})
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">当前位置: 分红权个数设置- 添加分红权个数信息</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="history.back();"/></div>
		</div>

		<form id="gjfAttrValueForm" >
			<table  align="center" class="listTable3">
				<tr>
					<td class="pn-flabel" width="100px">用户电话号码</td>
					<td><input type="text"  name="mobile" /></td>
				</tr>
				
				<input type="hidden"  name="type" value="${type }"/>
				<input type="hidden"  name="id" value="${id }"/>
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