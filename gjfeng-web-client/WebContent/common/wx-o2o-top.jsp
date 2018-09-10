<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head class ="header">
    <meta charset="utf-8">
    <meta name="description" content=""/>
    <meta name="keywords" content=""/>
    <meta name="author" content="Lucky"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/><!-- 是否启用 WebApp 全屏模式 -->
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/><!-- 设置状态栏的背景颜色 -->
    <meta name="viewport" content="initial-scale=1.0,user-scalable=no,width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no,email=no"/><!-- 禁止数字识自动别为电话号码 --><!-- 不让android识别邮箱 -->
    <title>巨惠云商</title>
    
    <!-- css -->
    <link rel="stylesheet" type="text/css" href="${cssPath}/wx/o2o-shop/common.css?ver=1.1">
    <link rel="stylesheet" type="text/css" href="${cssPath}/wx/o2o-shop/style.css?ver=1.3.2">
    <link rel="stylesheet" type="text/css" href="${cssPath}/wx/o2o-shop/swiper.min.css?ver=1.1">
    <link rel="stylesheet" type="text/css" href="${plugInPath}/layer_mobile/need/layer.css?ver=1.1">
    <link rel="stylesheet" type="text/css" href="${plugInPath}/larea/LArea.min.css?ver=1.1">
    
    <link rel="icon" href="${imagePath}/wx/o2o-shop/favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="${imagePath}/wx/o2o-shop/favicon.ico" type="image/x-icon" />
    <link rel="bookmark" href="${imagePath}/wx/o2o-shop/favicon.ico" type="image/x-icon" />
    
    <!-- js -->
    <script src="${jsPath}/wx/o2o-shop/jquery-1.11.3.min.js?ver=1.1"></script>
    <script src="${jsPath}/wx/o2o-shop/common.js?ver=1.1"></script>
    <script src="${jsPath}/wx/o2o-shop/jquery.validate.min.js?ver=1.1"></script>
    <script src="${jsPath}/wx/o2o-shop/swiper.jquery.min.js?ver=1.1"></script>
    <script src="${plugInPath}/layer_mobile/layer.js?ver=1.1"></script>
    <script src="${jsPath}/utils/jsUtils.js?ver=1.1"></script>
    <script src="${jsPath}/utils/ajax-common.js?ver=1.1"></script>

	<script type="text/javascript">
		var ctx="${ctx}";
		var jsPath="${jsPath}";
		var cssPath="${cssPath}";
		var imagePath="${imagePath}";
	</script>	
	
	<script src="https://s13.cnzz.com/z_stat.php?id=1264521199&web_id=1264521199" language="JavaScript"></script>
	<script type="text/javascript">
	   $('body').find('a:first').css("display","none")
	</script>
	<!-- 字体 -->
	<%-- <link rel="stylesheet" type="text/css" href="${fontPath}/iconfont.css"> --%>
</head>
<!-- div class="topTitle">
        <a href="javascript:history.go(-1);"></a>
        <span>凤凰云商</span>
 </div> -->
 


