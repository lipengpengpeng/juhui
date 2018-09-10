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
	<security:authorize ifAnyGranted="EP_BASE">
		<security:authorize ifAnyGranted="BASE_WELCOME">
			<div class="father">
				<a href="${ctx}/defaultAction.action" target="mainFrame"><!-- 欢迎信息 --><s:text name="left.base.left.welcome" /></a>
			</div>
		</security:authorize>
		<security:authorize ifAnyGranted="BASE_WEBSITE">
			<div class="father">
				<a href="${ctx}/webSiteAction.action" target="mainFrame"><!-- 站点配置 --><s:text name="left.base.left.site.config" /></a>
			</div>
		</security:authorize>
		<security:authorize ifAnyGranted="BASE_STANDBY">
			<div class="father">
				<a href="${ctx}/standbyAction.action" target="mainFrame"><!-- 公共信息管理 --><s:text name="left.base.left.public.information" /></a>
			</div>
		</security:authorize>
		<%-- <div class="father">
			<a href="${ctx}/config/paymentAction!retrieveAllPayments.action" target="mainFrame">支付方式</a>
		</div> --%>
	</security:authorize>
	</body>
</html>

