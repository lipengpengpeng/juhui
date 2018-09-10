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
			<div class="father">
				<a href="${ctx}/subsystem/storeInfoAction!retrieveStoreByPager.action" target="mainFrame">店铺列表</a>
			</div>
			<div class="father">
				<a href="${ctx}/subsystem/storeInfoAction!findLineCreditRechargeByPage.action" target="mainFrame">授信充值记录</a>
			</div>
			<%-- <div class="father">
				<a href="${ctx}/store/storeJoininAction!findStoreJoinins4page.action" target="mainFrame">开店申请</a>
			</div>
			<div class="father">
				<a href="${ctx}/store/storeGradeAction!retrieveAllStoreGrades.action" target="mainFrame">店铺等级</a>
			</div> --%>
			
	</body>

</html>

