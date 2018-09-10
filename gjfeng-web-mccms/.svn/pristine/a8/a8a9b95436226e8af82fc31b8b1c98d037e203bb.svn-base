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
		<security:authorize ifAnyGranted="EP_BASEINFO">
			<security:authorize ifAnyGranted="BASEINFO_CARBRAND">
				<div class="father">
						<a href="${ctx}/baseinfo/carBrandAction!retrieveAllCarBrands.action" target="mainFrame">汽车品牌</a>
				</div> 
			</security:authorize>
			
			<security:authorize ifAnyGranted="BASEINFO_CARSERIES">
				<div class="father">
						<a href="${ctx}/baseinfo/carSeriesAction!retrieveAllCarSeriess.action" target="mainFrame">汽车车系</a>
				</div> 
			</security:authorize>
			
			<security:authorize ifAnyGranted="BASEINFO_CARVOLUME">
				<div class="father">
						<a href="${ctx}/baseinfo/carVolumeAction!retrieveAllCarVolumes.action" target="mainFrame">汽车排量</a>
				</div> 
			</security:authorize>
			
			<security:authorize ifAnyGranted="BASEINFO_CARPRODUCTIVE">
				<div class="father">
						<a href="${ctx}/baseinfo/carProductiveAction!retrieveAllCarProductives.action" target="mainFrame">汽车生产年份</a>
				</div> 
			</security:authorize>
			
			<security:authorize ifAnyGranted="BASEINFO_SERVICECLASSIFY">
				<div class="father">
						<a href="${ctx}/baseinfo/serviceClassifyAction!retrieveAllServiceClassifys.action" target="mainFrame">汽车服务分类</a>
				</div> 
			</security:authorize>
			
			<security:authorize ifAnyGranted="BASEINFO_SERVICETYPE">
				<div class="father">
						<a href="${ctx}/baseinfo/serviceTypeAction!retrieveAllServiceTypes.action" target="mainFrame">汽车服务种类</a>
				</div> 
			</security:authorize>
				<div class="father">
						<a href="${ctx}/baseinfo/tagLibAction!retrieveAllTagLibs.action" target="mainFrame">品牌信息管理</a>
				</div>
			
		</security:authorize>
	</body>

</html>

