<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
	</head>
	<%		
		String sHtmlText = (String)request.getAttribute("html");
		out.println(sHtmlText);
	%>
	<body>
	</body>
</html>
