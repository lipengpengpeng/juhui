<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
		<%@ include file="/common/meta.jsp"%>
		<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />
		<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
		<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
		
		<link href="${ctx}/image/login5/style.css" rel="stylesheet" type="text/css" />
		<script src="${ctx}/image/login5/style.js" type="text/javascript"></script>
	</head>
	<body>
		<h4 align="center"><!-- 您的请求正在处理中，请不要重复操作！ --><s:text name="error.double.click.message" /></h4>
	</body>
</html>

