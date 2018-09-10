<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
    <div class="container">
        <form class="set-form">
            <div class="form-item">
                <label for="" class="label-item"><i class="main-sprite lock-icon-mobile"></i></label>
                <input type="text" name="mobile" class="pwd-input phone-input" placeholder="请输入手机号码" value="" >
            </div>
            <div class="form-item">
                <label for="" class="label-item"><i class="main-sprite lock-icon"></i></label>
                <input type="password" name="pwd1" class="pwd-input" placeholder="请输入6位数字密码" id="pwd-1">
            </div>
            <div class="form-item">
                <label for="" class="label-item"><i class="main-sprite lock-icon"></i></label>
                <input type="password" name="pwd2" class="pwd-input" placeholder="请再次输入密码">
            </div>
            <div class="form-codeItem clearfix">
                <div class="form-item code-item-1 left">
                    <label for="" class="label-item"><i class="main-sprite lock-icon-code"></i></label>
                    <input type="password" name="code" class="pwd-input code-input" placeholder="请输入验证码">
                </div>
                <div class="form-item code-item-2 left">
                    <button type="button" class="code-btn">获取验证码</button>
                </div>
            </div>
            <div class="btn-box">
                <button type="submit" class="btn">完成</button>
            </div>
        </form>
    </div>
    <script type="text/javascript">
    	document.title = "设置支付密码";
	    var mobile_send = "";//手机号码储存 
	    var count = 60; //间隔函数，1秒执行
	    var curCount;//当前剩余秒数
	    var code = ""; //验证码
	    var codeLength = 4;//验证码长度
        function checkMobile(value) {
        	return (value.length == 11 && /^(([1-9]\d*)|([0-9]+\.[0-9]{1,2}))$/.test(value));       
	    	//return (value.length == 11 && /^13\d{9}$|15\d{9}$|17\d{9}$|18\d{1}\d{8}|13\d{1}-\d{4}-\d{4}$|15\d{1}-\d{4}-\d{4}$|17\d{1}-\d{4}-\d{4}$|18\d{1}-\d{4}-\d{4}$/.test(value));
        }

        $(function() {  
	        $(".code-btn").on("click",function (event){
	            event.preventDefault();
	            curCount = count;
	            if($(".phone-input").val() == null || !checkMobile($(".phone-input").val())){
	                var str = '<div for="phone" class="error">手机号码格式错误</div>';
	                var item = $(".set-form").find(".form-item").eq(0).hasClass('has-error');
	                if(!item){
	                    $(".set-form").find(".form-item").eq(0).next(".error").remove();
	                    $(".set-form").find(".form-item").eq(0).after(str);
	                    return false;
	                }else{
	                    return true;
	                }
	            }
	            //设置button效果，开始计时
	            $(".code-input").val("");
	            $(".code-btn").attr("disabled", "true");
	            $(".code-btn").html(curCount + "秒");
	            $(".code-btn").addClass("code-grey-btn");
	            semdSms();
	            InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
	        });
	        $(".phone-input").on("input",function(){
	            if($(".phone-input").val()!=""){
	                $(".form-item").eq(0).next(".error").remove();
	            }
	        })

            jQuery.validator.addMethod("isPhone",function(value,element){
                var length = value.length;
                //支持13 15 17 18(056789)开头,同时支持xxx-xxxx-xxxx格式手机输入
                //var mobile = /^13\d{9}$|15\d{9}$|17\d{9}$|18\d{1}\d{8}|13\d{1}-\d{4}-\d{4}$|15\d{1}-\d{4}-\d{4}$|17\d{1}-\d{4}-\d{4}$|18\d{1}-\d{4}-\d{4}$/; 
                var mobile= /^(([1-9]\d*)|([0-9]+\.[0-9]{1,2}))$/;
                var result = this.optional(element) || (mobile.test(value));
                if(result && mobile_send!=''){//发送过验证码之后
                    if(mobile_send!=value){//手机号码不等于之前的手机号码的话
                        //$("#pro-code").val("");
                        result = "false";
                    }
                }
                return result;
            },"请输入正确的手机号码");
            $(".set-form").validate({
                rules:{
                    phone:{
                        required:true,
                        isPhone:true,
                    },
                    pwd1:{
                        required:true,
                        number:true,
                        maxlength:6,
                        minlength:6
                    },
                    pwd2:{
                        required:true,
                        number:true,
                        maxlength:6,
                        minlength:6,
                        equalTo:'#pwd-1',
                    },
                    code:{
                        required:true,
                        remote:{
	                		async:false,
	    	        	    url: "${ctx}/sms/validate",     //后台处理程序
	    	        	    type: "get",               		//数据发送方式
	    	        	    dataType: "json",           		//接受数据格式   
	    	        	    data: {                     		//要传递的数据
	    	        	    	"code": function() {
	    		        			return $(".code-input").val();
	    		        		},					
	    		        		"mobile": function() {
	    	        	        	return $(".phone-input").val();
	    		        		}
	    	        	    }
	    	        	}
                     } 
                },
                messages:{
                    phone:{
                        required:"请输入手机号码",
                        isPhone:"请输入正确的手机号码",
                    },
                    pwd1:{
                        required:"请输入密码",
                        number:"必须为数字",
                        maxlength:"必须为6位",
                        minlength:"必须为6位"
                    },
                    pwd2:{
                        required:"请再次输入密码",
                        number:"必须为数字",
                        maxlength:"必须为6位",
                        minlength:"必须为6位",
                        equalTo:'两个密码不一致',
                    },
                    code:{
                        required:"请输入验证码",
                        remote:"验证码不正确"
                    }
                },
                highlight: function (e) {
                    $(e).parent().addClass('has-error');
                },
                success: function (e) {
                    $(e).parents(".form-item").removeClass('has-error');
                    $(e).remove();
                },
                submitHandler: function (e) {
                	setPayPwd();
                },
                errorElement:'div',
                errorPlacement:function(error,element) {  
                    if(element.hasClass("code-input")){
                        error.insertAfter(element.parents(".form-codeItem"));
                        error.addClass('error-code');
                    }else{
                        error.insertAfter(element.parent());
                    }
                },
                onfocusout:false,
                onkeyup:false,
                focusInvalid:false,
            });

        });
        
        
        //发送短信
        function semdSms(){
        	//向后台发送处理数据
            $.ajax({
                type: "post", //用POST方式传输
                dataType: "json", //数据格式:JSON
                url: '${ctx}/sms/send', //目标地址
                data: {
                    'mobile':$(".phone-input").val(),
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
                        mobile_send = $(".phone-input").val();
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
                        content:"发送失败，请重新获取",
                        btn:'我知道了'
                   });
                }
            });
        }
        
        //设置支付密码
        function setPayPwd(){
        	$.ajax({
    			url:"${ctx}/wx/member/setPay",
    			type: "post", //用POST方式传输
                dataType: "json", //数据格式:JSON
                data:$('.set-form').serialize(),
    			success:function(data){
  					layer.open({
                          content:data.msg,
                          btn:['确定', '取消'],
                          yes: function(index){
                          	if(data.code==200){
                          		window.location.href = document.referrer;
                   			}else{
                   				layer.close(index)
                   			}
                          },
                          no:function(index){
                              layer.close(index);
                          }
                     });
    			},
    			error:function(xhr,type ,e){
    				layer.open({
                        content:"设置失败，请重试",
                        btn:'我知道了'
                   });
    			}
    		})
        }
        
        function checkMobile(value) {        
        	return (value.length == 11 &&/^(([1-9]\d*)|([0-9]+\.[0-9]{1,2}))$/.test(value));
        
            //return (value.length == 11 && /^13\d{9}$|15\d{9}$|17\d{9}$|18\d{1}\d{8}|13\d{1}-\d{4}-\d{4}$|15\d{1}-\d{4}-\d{4}$|17\d{1}-\d{4}-\d{4}$|18\d{1}-\d{4}-\d{4}$/.test(value));
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
</body>
</html>