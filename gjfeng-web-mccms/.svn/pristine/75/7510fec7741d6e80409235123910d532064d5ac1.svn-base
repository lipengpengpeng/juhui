<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/left.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
	</head>
	<body>
	<security:authorize ifAnyGranted="EP_SKIN">
		<security:authorize ifAnyGranted="SKIN_SKIN">
			<div class="father">
				<a href="${ctx}/style/webSkinAction.action" target="mainFrame"><!-- 模板管理 --><s:text name="left.style.left.template" /></a>
			</div>
		</security:authorize>
		<security:authorize ifAnyGranted="SKIN_COLOR">
			<div class="father">
				<a href="${ctx}/style/webSkinAction!style.action" target="mainFrame"><!-- 风格管理 --><s:text name="left.style.left.style" /></a>
			</div>
		</security:authorize>
	</security:authorize>
	</body>

</html>

