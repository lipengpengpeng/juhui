<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>MCCMS</title>
	</head>
	<body>
		<div id="title">
  		<div class="text"><!-- 当前位置: 界面风格 --><s:text name="enterprise.style.current.location" /> - 
					<!-- 模板管理 --><s:text name="enterprise.style.current.location.template" /></div>
 		 <div class="help"><!-- 查看模板信息 --><s:text name="enterprise.style.current.location.template.view" /></div>
 		 </div>
 		 <br/>
		<table align="center" class="listView" width="90%">
			<tr>
				<td><!-- 模板名称 --><s:text name="enterprise.style.template.name" /></td><td>${webSkin.names}</td>
			</tr>
			<tr>
				<td><!-- 模板文件夹名称 --><s:text name="enterprise.style.template.folder.name" /></td><td>${webSkin.filename}</td>
			</tr>
			<tr>
				<td><!-- 模板描述 --><s:text name="enterprise.style.template.description" /></td><td>${webSkin.content}</td>
			</tr>
			<tr>
				<td><!-- 状态--><s:text name="common.word.status" /></td>
				<td>
					<c:if test="${webSkin.state == 1}"><!-- 已启用 --><s:text name="common.word.has.opened" /></c:if>
					<c:if test="${webSkin.state == 0}"><font color="red"><!-- 已停用 --><s:text name="common.word.has.closed" /></font></c:if>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value='<s:text name="common.word.return.back" />' onclick="history.back()"/>
				</td>
			</tr>
	 	</table>
		<br/>
	
	</body>
</html>

