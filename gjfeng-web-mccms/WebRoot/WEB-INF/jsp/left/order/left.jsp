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
		<security:authorize ifAnyGranted="EP_ORDER">
			
			<security:authorize ifAnyGranted="ORDER_ORDER">
			<div class="father">
					<a href="${ctx}/order/orderAction!retrieveAllOrders.action" target="mainFrame">订单信息管理</a>
			</div> 
			</security:authorize>
			
		</security:authorize>
	</body>

</html>

