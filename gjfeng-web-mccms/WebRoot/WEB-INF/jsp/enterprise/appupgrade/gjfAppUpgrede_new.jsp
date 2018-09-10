<%--

	gjfBankInfo_new.jsp

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
				$("#gjfBankInfoForm").validate({
					 rules: { 
			
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
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="location.href='${ctx}/subsystem/gjfBankInfoAction!retrieveAllGjfBankInfos.action'"/></div>
		</div>

		<form action="appUpgradeInfoAction!newAppUpgredeInfo.action" method="post" id="gjfBankInfoForm" name="gjfBankInfoForm" enctype="multipart/form-data">
			<table  align="center" class="listTable3">
				<tr>
					<td class="pn-flabel" width="100px">版本号</td>
					<td><input type="text" id="bankCode" name="appUpGradeInfo.version" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">更新链接</td>
					<td><input type="text" id="bankName" name="appUpGradeInfo.jumpUrl" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">描述</td>
					<td><textarea id="keywords" rows="10" cols="77" name="appUpGradeInfo.describe"></textarea></td>
				</tr>
				
				<tr>
					<td class="pn-flabel" width="150px">类型</td>
					<td>
						<select name="appUpGradeInfo.type">
							<option value="0">安卓</option>
							<option value="1">ios</option>							
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