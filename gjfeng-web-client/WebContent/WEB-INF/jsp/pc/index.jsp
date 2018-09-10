<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html>	
<head>
<title>巨惠供应链</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<meta name="keywords" content="Flat Dark Web Login Form Responsive Templates, Iphone Widget Template, Smartphone login forms,Login form, Widget Template, Responsive Templates, a Ipad 404 Templates, Flat Responsive Templates" />
<link href="${commonpc}/css/login_index.css?ver=1.2" rel='stylesheet' type='text/css' />
<!--webfonts-->
<!-- <link href='http://fonts.useso.com/css?family=PT+Sans:400,700,400italic,700italic|Oswald:400,300,700' rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Exo+2' rel='stylesheet' type='text/css'> -->
<!--//webfonts-->
<script src="${commonpc}/js/jquery-1.11.0.min.js"></script>
</head>
<body>
<script>$(document).ready(function(c) {
	$('.close').on('click', function(c){
		$('.login-form').fadeOut('slow', function(c){
	  		$('.login-form').remove();
		});
	});	  
});
</script>
 <!--SIGN UP-->
 <h1>巨惠供应链登录界面</h1>
<div class="login-form">
	<!-- <div class="close"> </div> -->
	<div class="head-info">
			<label class="lbl-1"> </label>
			<label class="lbl-2"> </label>
			<label class="lbl-3"> </label>
		</div>
	<div class="clear"> </div>
	<div class="avtar">
		<%-- <img src="${commonpc}/img/avtar.png" /> --%>
	</div>
			<form id="set-form">
				   <input type="text" name="account" class="mobile" placeholder="请输入手机号码" value="" >
				<div > 
					<input type="text" class="code" value=""  placeholder="请输入验证码" >
					<button type="button" class="code-btn" style="margin-left: -108px;padding: 1em 0.5em 1em 2em;border-radius: 9px;background-color: #ea569a;">获取验证码</button>
					
				</div> 	
				<span style="margin-left: -7rem;font-size: 1.5em;color: red;display:none" id="msg"></span>			
			</form>
	<div class="signin">
		<input type="submit" value="登录" id="sub">
	</div>
</div>
    <div class="copy-rights">
		<p>Copyright &copy; 2018.Company name All rights </p>
	</div>
</body>
<script type="text/javascript">
var mobile_send = "";//手机号码储存 
var count = 60; //间隔函数，1秒执行
var curCount;//当前剩余秒数
var code = ""; //验证码
var codeLength = 4;//验证码长度
$(".code-btn").on("click",function (event){
    event.preventDefault();
    curCount = count;
    /* || !checkMobile($(".phone-input").val() )*/
    if($(".mobile").val() == null || $(".mobile").val() == ""|| !checkMobile($(".mobile").val() ) ){
    	var msg="手机号码格式错误";
    	if($(".mobile").val() == null||$(".mobile").val()==""){
    		msg="请输入手机号码";
    	}
    	$("#msg").html(msg)
		$("#msg")[0].style.display = "block"
    }
    //设置button效果，开始计时
    $(".code-input").val("");
    $(".code-btn").attr("disabled",true);
    $(".code-btn").html(curCount + "秒     ");
    $(".code-btn").addClass("code-grey-btn");
    semdSms();
    InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
});




$(".mobile").focus(function(){
	$("#msg")[0].style.display = "none"
})

$(".code").focus(function(){
	$("#msg")[0].style.display = "none"
})

$("#sub").click(function(){
	var mobile=$(".mobile").val()
	
	if(mobile==null||mobile==""){
		$("#msg").html("电话号码不能为空")
		$("#msg")[0].style.display = "block"
		return false;
	}
	
	var reg = new RegExp("^[1][3578][0-9]{9}$");
    if (!reg.test(mobile)) {
    	$("#msg").html("输入的电话号码不正确")
		$("#msg")[0].style.display = "block"
		return false;
	}
    
    var code=$(".code").val()
    if(code==null||code==""){
    	$("#msg").html("手机验证码不能为空")
		$("#msg")[0].style.display = "block"
		return false;
    }else{
    	/* $.ajax({
    		url:"${ctx}/sms/validate",
    		method:"get",
    		data:{
    			code:code,
    			mobile:mobile
    		},
    		success:function(data){
    			if(!data){
    				$("#msg").html("手机验证码不正确")
    				$("#msg")[0].style.display = "block"
    				return false;
    			}
    		}
    	}) */
    }
    
    $.ajax({
    	url:"${ctx}/pc/merchant/login",
        method:"POST",
        dataType: "json", //数据格式:JSON
        data:$('#set-form').serialize(),
        success:function(data){
        	if(data.code==200){
        		window.location.href="${ctx}/pc/merchant/goSpecifiedMerchantOnilne"
        	}else{
        		 layer.open({
                     content:data.msg,
                     btn:'我知道'
                });
        	}
        }
    	
    })
    
  
    
    
})



        //发送短信
       function semdSms(){
        	//向后台发送处理数据
            $.ajax({
                type: "post", //用POST方式传输
                dataType: "json", //数据格式:JSON
                url: '${ctx}/sms/send', //目标地址
                data: {
                    'mobile':$(".mobile").val(),
                   
                },
                success: function (data){
                	if(null == data || "" == data){
                		layer.open({
                            content:"发送失败",
                            btn:'我知道了'
                       });
                		curCount=0;
                        SetRemainTime();
                		return false;
                	}
                    if(data.code=="2"){
                        mobile_send = $(".mobile").val();
                    }else{
                    	curCount=0;
                        SetRemainTime();
                    }
                   	/* layer.open({
                           content:data.msg,
                           btn:'我知道了'
                      }); */
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                	curCount=0;
                    SetRemainTime();
                	layer.open({
                        content:"发送失败，请重新发送",
                        btn:'我知道了'
                   });
                }
            });
        }
function checkMobile(value) {
    //return (value.length == 11 && /^13\d{9}$|15\d{9}$|17\d{9}$|18\d{1}\d{8}|13\d{1}-\d{4}-\d{4}$|15\d{1}-\d{4}-\d{4}$|17\d{1}-\d{4}-\d{4}$|18\d{1}-\d{4}-\d{4}$/.test(value));
    return (value.length == 11 && /^1[34578]\d{9}$/.test(value));
}     
        
function SetRemainTime() {
    if (curCount == 0) {
        window.clearInterval(InterValObj);//停止计时器
        $(".code-btn").removeAttr("disabled");//启用按钮
        $(".code-btn").html("获取验证码");
        $(".code-btn").removeClass("code-grey-btn");
        code = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效
    }
    else {
        curCount--;
        $(".code-btn").html(curCount + "秒");
    }
};
</script>
</html>