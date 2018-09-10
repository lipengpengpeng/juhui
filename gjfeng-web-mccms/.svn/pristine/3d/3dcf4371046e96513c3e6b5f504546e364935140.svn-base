<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
		<script>
			$(document).ready(function(){
				$("#mcParameterForm").validate({
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
			<div class="rpos">
				<!-- 当前位置: 系统配置 - 产品参数管理 --><s:text name="enterprise.system.parameter.current.location" />
			</div>
			<div class="ropt"><input type="button" value='<s:text name="common.word.return.back" />' onclick="history.back();"/></div>
		</div>

		<form action="mcParameterAction!editMcParameter.action" method="post" id="mcParameterForm" name="mcParameterForm" enctype="multipart/form-data">
			<table  align="center" class="listTable3">
				<input type="hidden" name="id" value="${mcParameter.id}"/> 
				<tr>
					<td class="pn-flabel" width="100px"><!-- 中文字段名称 --><s:text name="enterprise.system.parameter.name" /></td>
					<td><input type="text" id="name" name="mcParameter.name" value="${mcParameter.name}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px"><!-- 调用代码 --><s:text name="enterprise.system.parameter.invoke.code" /></td>
					<td>${mcParameter.mark}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px"><!-- 字段排序 --><s:text name="enterprise.system.parameter.order" /></td>
					<td><input type="text" id="noOrder" name="mcParameter.noOrder" value="${mcParameter.noOrder}" class="{required:true,digits:true}"/></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px"><!-- 字段类型 --><s:text name="enterprise.system.parameter.type" /></td>
					<td>
						<c:if test="${mcParameter.type==0}"><!-- 简短字段 --><s:text name="enterprise.system.parameter.short.field" /></c:if>
						<c:if test="${mcParameter.type==1}"><!-- 备用字段 --><s:text name="enterprise.system.parameter.backup.field" /></c:if>
						<c:if test="${mcParameter.type==2}"><!-- 上传字段 --><s:text name="enterprise.system.parameter.upload.field" /></c:if>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px"><!-- 最大可输入字符串 --><s:text name="enterprise.system.parameter.max.string" /></td>
					<td>${mcParameter.maxsize}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px"><!-- 是否开启该字段 --><s:text name="enterprise.system.parameter.status" /></td>
					<td>
						<select name="mcParameter.wrOk">
							<option value="0"><!-- 否 --><s:text name="common.word.no" /></option>
							<option value="1" <c:if test="${mcParameter.wrOk==1}">selected</c:if>><!-- 是 --><s:text name="common.word.yes" /></option>
						</select>
					</td>
				</tr>

				<tr>
					<td colspan="2" style="text-align:center;">
						<input type="submit" value='<s:text name="common.word.submit" />' />
					</td>
				</tr>
		 	</table>
		</form>
	</body>
</html>