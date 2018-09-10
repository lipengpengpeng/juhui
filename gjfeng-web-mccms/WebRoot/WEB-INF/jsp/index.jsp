<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<!--[if lt IE 7]>
<html class="ie6" lang="zh-cn"><![endif]-->
<!--[if IE 7]>
<html class="ie7" lang="zh-cn"><![endif]-->
<!--[if IE 8]>
<html class="ie8" lang="zh-cn"><![endif]-->
<!--[if IE 9]>
<html class="ie9" lang="zh-cn"><![endif]-->
<!--[if gt IE 9]>
<html lang="zh-cn"><![endif]-->
<!--[if !IE]>-->
<html lang="zh-cn"><!--<![endif]-->
<head>
	<script type="text/javascript">
		if(window.top!==window.self)
		{
			window.top.location=window.location
		};
	</script>	
    <!--meta.jsp-->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!--不使用IE兼容模式（杂项）-->
    <meta name="renderer" content="webkit">
    <!--默认使用极速模式，目前只有360支持，不过已经够了-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--平板或视网膜屏100%缩放显示网页-->
    <meta name="description" content=""/>
    <meta name="keywords" content=""/>
    <meta name="author" content=""/>
    <link rel="icon" href="#">
    <!--客户有需要时添加，ico图标-->
    <title>凤凰云商O2O</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/image/login/supersized.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/image/login/style.css">
    <script src="${ctx}/js/jquery-1.11.2.min.js"></script>
    <script src="${ctx}/js/login.js"></script>
    <script src="${ctx}/js/supersized.3.0.js"></script>
    <script src="${ctx}/js/jquery.validate.js"></script>
    <script src="${ctx}/js/messages_zh.js"></script>
    
    
    <link rel="icon" href="${ctx}/images/favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="${ctx}/imagesfavicon.ico" type="image/x-icon" />
    <link rel="bookmark" href="${ctx}/images/favicon.ico" type="image/x-icon" />
    <!--[if lt IE 9]>
    <script src="js/html5shiv.min.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
    <noscript>您的浏览器不支持JavaScript，请启动JavaScript或更换浏览器</noscript>
    <!--end of meta.jsp-->
</head>
<body>
<!--header-->
<div id="header">
    <div class="logo">
<!--         <a href="javascript:void(0)"> -->
<!--         <img src="${ctx}/image/login/logo.png" alt="" title=""> -->
<!--         </a> -->
    </div>
</div>
<!--header end-->
<!--container-->
<div id="container">
	
    <div class="form-group">
        <form id="form-group" name="loginForm" action="${ctx}/j_spring_security_check" method="post">
            <h3>授权登录</h3>
            <div id="login_error" style="text-align: center;">
				<%
					if (session.getAttribute(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY) != null) {
				%>
				<span style="color: #fff; font-size: 12px;">${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}</span>
				<%
					}
				%>
			</div>
            <div class="input-group">
                <span class="icon-common icon-username"></span>
                <input type="text" name="j_username" id="j_username" class="common-input" placeholder="用户名">
                <label class="error">请输入用户名</label><!--
                <div class="input-val">用户名</div>
            --></div>
            <div class="input-group">
                <span class="icon-common icon-pwd"></span>
                <input type="password" name="j_password" id="j_password" class="common-input" placeholder="密码"><label class="error">请输入密码</label><!--
                <div class="input-val">密码</div>
            --></div>
            <div class="">
                <input type="text" name="vercode" id="vertiCode" class="common-input " placeholder="验证码"><label class="error">请输入验证码</label>
                <input class="common-input" type="button" id="sendCode" value="获取手机验证码"/>
                <%-- <span class="vertiCode-img">
                	<img id="AuthImg" name="AuthImg" src="${ctx}/AuthImg" alt='<s:text name="login.check.change" />' />
                </span> --%>
            </div>
            <div class="input-group">
                <button type="submit" class="register-btn">立即登录</button>
            </div>
        </form>
    </div>
</div>
<!--container end-->
<div id="supersized"></div>
<script type="text/javascript">
    //背景切换
    $(function(){
        $.fn.supersized.options = {
            vertical_center: 1,
            slideshow: 1,
            navigation: 1,
            thumbnail_navigation: 1,
            transition: 1, //0-None, 1-Fade, 2-slide top, 3-slide right, 4-slide bottom, 5-slide left
            pause_hover: 0,
            slide_counter: 1,
            slide_captions: 1,
            slide_interval: 3000,
            slides : [{image : '${ctx}/image/login/bg1.jpg', title : ' ', url : 'javascript:void(0)'},
                {image : '${ctx}/image/login/bg2.jpg', title : ' ', url : 'javascript:void(0)'},
                {image : '${ctx}/image/login/bg3.jpg', title : ' ', url : 'javascript:void(0)'}
            ]
        };
        $('#supersized').supersized();

        $("#AuthImg").click(function(){
			var timenow = new Date().getTime();
				this.src="${ctx}/AuthImg?d="+timenow;
		});
    });
    var countdown = 60;
	var t;
	
    $("#sendCode").on("click", function() {
		var val =this;
		if($("#j_username").val()!=""){
			settime(val);
			$.ajax({
				type: "post",
				url: '${ctx}/loginAction!sendCode.action',
				dataType: "json",
				data: {"loginName": $('#j_username').val()},
				success: function(data) {
					if(data){
						alert("短信验证码已发送！！");
						
					}else{
						alert("帐号未绑定手机号码！！");
					}
					/* }else{
						$.alert(data.errorMsg, "警告！");
					} */
				},
				error: function() {alert("网络错误");}
			});
		}else{
			alert("用户名不能为空，请输入用户名！！");
		}
	})

	//60秒倒计时
	function settime(val) {
		t = setTimeout(function() {
			settime(val)
		}, 1000)
		if (countdown == 0) {
			val.removeAttribute("disabled");    
	        val.value="获取验证码"; 
	        countdown =60;
	        clearTimeout(t);
		} else { 
			val.setAttribute("disabled", true); 
			val.value="重新发送(" + countdown + ")"; 
			countdown--; 
		} 
	
	}
    //验证
    $(function(){
        $('#form-group').validate({
            rules: {
	        	j_username:
				{
					required: true,
					minlength: 4,
					maxlength: 32
				},
				j_password:
				{
					required: true,
					minlength: 4,
					maxlength: 64
				},
			 	vercode: 
			 	{ 
			 		required: true, 
			 		rangelength : [6,6],
					remote: {
						//远程地址只能输出 "true" 或 "false"，不能有其他输出。
					    url: "${ctx}/loginAction!checkCode.action",     //后台处理程序
					    type: "post",               //数据发送方式
					    dataType: "json",           //接受数据格式   
					    data: {                     //要传递的数据
					    	vercode: function() {
					            return $("#vertiCode").val();
					        }
					    }
					}
			 	}
            },
            messages: {
            	j_username:{
			 		minlength: '<s:text name="login.validate.name.minlength" />',
			 		maxlength: '<s:text name="login.validate.name.maxlength" />'
			 	},
			 	j_password:{
			 		minlength: '<s:text name="login.validate.password.minlength" />',
			 		maxlength: '<s:text name="login.validate.password.minlength" />'
			 	},
			 	vercode: {
			 		required : "请输入你的验证码",
					rangelength : "请输入6位验证码",
					remote: "验证码错误，请重新输入"
				}
            },
            /*submitHandler:function(form){
             alert("submitted");
             form.submit();
             },*/
            //debug: true
        });

        //ie8下的校验
        var ua = navigator.userAgent;
        if(ua.indexOf("MSIE")>0) {
            if(ua.indexOf("MSIE 8.0")>0) {
                $('.register-btn').click(function(){
                    $('.common-input').each(function(){
                        if($(this).val() == ''){
                            $(this).next().show().prev().focus(function(){
                                $(this).next().hide();
                            });
                        }else{
                            $(this).next().hide();
                        }
                    });
                });
            }
        }
    });
</script>
</body>
</html>
