<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<script src="${jsPath}/wx/online-shop/md5.js?ver=1.1"></script>
<script src="${jsPath}/wx/online-shop/jquery.cookie.js?ver=1.1"></script>
<body>   
    <div class="container">
       <form class="set-form" id="set-form" >          
            <div class="form-item">
                <label for="" class="label-item"><i class="main-sprite lock-icon-mobile"></i></label>
                <input type="text" name="account" id="account" class="pwd-input phone-input" placeholder="请输入手机号码">
            </div>
            <div class="form-item">
                <label for="" class="label-item"><i class="main-sprite lock-icon-mobile"></i></label>
                <input type="password" name="password" id="password" class="pwd-input passw-input" placeholder="请输入密码">
            </div>
                               
            <div class="btn-box">
                <button type="submit" class="btn">登录</button>               
            </div>
         </form> 
    </div>   
    <script type="text/javascript">
	    var mobile_send = "";//手机号码储存 
	    var count = 60; //间隔函数，1秒执行
	    var curCount;//当前剩余秒数
	    var code = ""; //验证码
	    var codeLength = 4;//验证码长度
	    document.title = "绑定手机";
        $(function() {
	       
	        $(".phone-input").on("input",function(){
	            if($(".phone-input").val()!=""){
	                $(".form-item").eq(0).next(".error").remove();
	            }
	        })

            jQuery.validator.addMethod("isPhone",function(value,element){
                var length = value.length;
                //支持13 15 17 18(056789)开头,同时支持xxx-xxxx-xxxx格式手机输入
                var mobile = /^13\d{9}$|15\d{9}$|17\d{9}$|18\d{1}\d{8}|13\d{1}-\d{4}-\d{4}$|15\d{1}-\d{4}-\d{4}$|17\d{1}-\d{4}-\d{4}$|18\d{1}-\d{4}-\d{4}$/; 
                var result = this.optional(element) || (mobile.test(value));
                if(result && mobile_send != ''){//发送过验证码之后
                    if(mobile_send != value){//手机号码不等于之前的手机号码的话
                        //$("#pro-code").val("");
                    	result = "false";
                    }
                }
                return result;
            },"请输入正确的手机号码");
            $(".set-form").validate({
                rules:{
                	account:{
                        required:true,
                        isPhone:true,
                    },
                    password:{
                        required:true,                    
                    },  
	    	        
                },
                messages:{
                	account:{
                        required:"请输入手机号码",
                        isPhone:"请输入正确的手机号码",
                    },
                    password:{
                        required:"请输入密码",
                      
                    },
                   
                },
                highlight: function (e) {
                    $(e).parent().addClass('has-error');
                },
                success: function (e) {
                    $(e).parents(".form-item").removeClass('has-error');
                    $(e).remove();
                },
                submitHandler: function (e) {
                	bindMobile();
                },
                errorElement:'div',
                errorPlacement:function(error,element) {  
                    if(element.hasClass("code-input")){
                        error.insertAfter(element.parents(".form-codeItem"));
                        error.addClass('error-code');
                    }else{
                        error.insertAfter(element.parent());
                    }
                }
            });
        });
                          
        //用户登录
        function bindMobile(){
        	
        	$.ajax({
    			url:"${ctx}/wx/login/login",
    			type: "POST", //用POST方式传输
                dataType: "json", //数据格式:JSON
                data:$('#set-form').serialize(),
     			success:function(data){   				                 
    				layer.open({
                        content: data.msg,
                        btn: ['确定', '取消'],
                        yes: function(index){//跳转到个人中心
                        	if(data.code==200){                       		
                        		localStorage.setItem("account", data.result.mobile)
                        		localStorage.setItem("password", data.result.password)                       	
                        		location.reload()               		
                        	}
                            layer.close(index);
                        },
                        no:function(index){
                            layer.close(index);
                        }
                    }); 
    			},
    			error:function(xhr,type ,e){
    				layer.open({
                        content:"网络异常，请重试",
                        btn:'我知道了'
                   });
    			}
    		})
        }                       	
     
    </script>
</body>
</html>