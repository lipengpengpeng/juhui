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
		<security:authorize ifAnyGranted="GJF_PRODUCT_LIST">
			<div class="father">
				<a href="${ctx}/collection/mcProductInfoAction!query.action" target="mainFrame">商品信息</a>
			</div>
		</security:authorize>
		<security:authorize ifAnyGranted="GJF_PRODUCT_TYPE">
			<div class="father">
				<a href="${ctx}/subsystem/attrTypeAction!retrieveAttrTypeByPage.action" target="mainFrame">属性类型</a>
			</div>
		</security:authorize>
		<security:authorize ifAnyGranted="GJF_PRODUCT_VALUE">
			<div class="father">
				<a href="${ctx}/subsystem/attrValueAction!retrieveAttrValueByPage.action" target="mainFrame">属性值</a>
			</div>
		</security:authorize>
		<security:authorize ifAnyGranted="GJF_PRODUCT_NUM">
			<div class="father">
				<a href="${ctx}/collection/mcProductInfoAction!queryforProNum.action" target="mainFrame">商品库存</a>
			</div>
		</security:authorize>
	</body>

</html>

