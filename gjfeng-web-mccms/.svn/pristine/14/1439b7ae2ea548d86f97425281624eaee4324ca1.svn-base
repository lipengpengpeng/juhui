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
	<security:authorize ifAnyGranted="EP_DYNAMIC">
			<security:authorize ifAnyGranted="DYNAMIC_LINKS">
				<div class="father">
					<a href="${ctx}/dynamic/enterpriseLinksAction.action"
						target="mainFrame"><!-- 友情链接管理 --><s:text name="left.dynamic.left.friend.link" /></a>
				</div>
			</security:authorize>
			<security:authorize ifAnyGranted="DYNAMIC_AD">
				<div class="father">
					<a href="${ctx}/dynamic/enterpriseAdAction.action"
						target="mainFrame"><!-- 广告信息管理 --><s:text name="left.dynamic.left.advertisement" /></a>
				</div>
			</security:authorize>
			
			
				<div class="father">
					<a href="${ctx}/subsystem/appUpgradeInfoAction!findAllAppUpgradeInfo.action"
						target="mainFrame">app版本信息</a>
				</div>
			
		</security:authorize>
	</body>

</html>

