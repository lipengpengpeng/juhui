<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
		
		<script src="${ctx}/js/jquery.ui/ui/jquery.ui.all.js"
			type="text/javascript"></script>
		<script src="${ctx}/js/layout/jquery.layout.js" type="text/javascript"></script>
		<script src="${ctx}/js/ddaccordion.js" type="text/javascript"></script>
		<link href="${ctx}/js/jquery.ui/themes/default/ui.all.css"
			rel="stylesheet" type="text/css" />

		<link href="${ctx}/image/style_blue5/style.css" rel="stylesheet"
			type="text/css" />
		<script src="${ctx}/image/style_blue5/style.js" type="text/javascript"></script>
		<script src="${ctx}/js/mainframe_nav.js" type="text/javascript"></script>


</head>
	<body>
		<security:authorize ifAnyGranted="GJF_SETTING_BENEFIT_RATIO">
			<div class="father">
				<a href="${ctx}/subsystem/benefitInfoAction.action" target="mainFrame">让利比例配置</a>
			</div> 
		</security:authorize>
		<security:authorize ifAnyGranted="GJF_SETTING_DIVI_AMOUNT">
			<div class="father">
				<a href="${ctx}/subsystem/benefitInfoAction!toDividentAmountSetting.action" target="mainFrame">分红金额设置</a>
			</div> 
		</security:authorize>
		
			<%-- <div class="father">
				<a href="${ctx}/subsystem/benefitInfoAction!toSpecialDividentAmountSetting.action" target="mainFrame">特殊人分红金额设置</a>
			</div>  --%>
		
		<%-- <security:authorize ifAnyGranted="GJF_SETTING_TEST_CONTROLLER"> --%>
			<%-- <div class="father">
				<a href="${ctx}/subsystem/benefitInfoAction!toTestControl.action" target="mainFrame">测试控制面板</a>
			</div> --%>
		<%--</security:authorize> --%>
		<%-- <security:authorize ifAnyGranted="GJF_SETTING_WECHAT_CONTROLLER">
		<div class="father">
			<a href="${ctx}/subsystem/tradeInfoAction!findAllWeiXinInfo.action" target="mainFrame">微信信息设置</a>
		</div>
		</security:authorize> --%>
		<div class="father">
			<a href="${ctx}/subsystem/tradeInfoAction!getAllSetDividens.action" target="mainFrame">分红权个数设置</a>
		</div>
		<security:authorize ifAnyGranted="GJF_SETTING_BASEINFO_CONTROLLER">
		<div class="father">
			<a href="${ctx}/subsystem/memberInfoAction!findSetBaseInfo.action" target="mainFrame">控制信息设置</a>
		</div>
		</security:authorize>
	</body>

</html>

