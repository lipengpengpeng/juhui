<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
    <div class="container">
        <form class="buy-bonus-form" id="gives" action="${ctx}/wx/member/giveShouXintoMember" method="POST">
            <div class="bonus-wrap">
                <div class="shouxin-now">可用授信额度:${shouXinMenoy}</div>
                <div class="bonus-item clearfix">
                    <label class="bonus-label left">金额：</label>
                    <input type="text" name="giveMoney" placeholder="请输入与会员交易的金额" class="bonus-input right" id="bonus-num">
                </div>
            </div>
            <div class="bonus-pay">
                <ul class="cont-list shopInfo-list">
                    <li class="clearfix">
                        <label class="shopInfo-label left">赠送会员</label>
                        <span class="shopInfo-right right">
                            <input type="tel" name="toAccount" class="shopInfo-input" placeholder="请输入会员手机号">
                        </span>
                    </li>
                </ul>
            </div>
            <div class="btn-box">
                <button type="submit" class="btn" id="saveToPay">立即赠送</button>
            </div>
        </form>
    </div>
    <script type="text/javascript">
        $(function(){
        	document.title = "赠送额度";
            jQuery.validator.addMethod("isPhone",function(value,element){
                var length = value.length;
                //支持13 15 17 18(056789)开头,同时支持xxx-xxxx-xxxx格式手机输入
                var mobile = /^13\d{9}$|15\d{9}$|17\d{9}$|18\d{1}\d{8}|13\d{1}-\d{4}-\d{4}$|15\d{1}-\d{4}-\d{4}$|17\d{1}-\d{4}-\d{4}$|18\d{1}-\d{4}-\d{4}$/; 
                var result = this.optional(element) || (mobile.test(value));
                return result;
            },"请输入正确的手机号码");
            $(".buy-bonus-form").validate({
                rules:{
                	giveMoney:{
                        required:true,
                    },
                    toAccount:{
                        required:true,
                        isPhone:true,
                    },
                },
                messages:{
                	giveMoney:{
                        required:"请输入与会员交易的金额",
                    },
                    toAccount:{
                        required:"请输入会员手机号",
                        isPhone:"请输入正确的手机号码",
                    },
                },
                onfocusout:false,
                onkeyup:false,
                focusInvalid:false,
                showErrors: function(errorMap, errorList) {  
                    var msg = "";  
                    $.each(errorList, function(i,v){  
                      msg = (v.message);  
                      return false; 
                    });  
                    if(msg!=""){
                        layer.open({
                            content: msg,
                            skin: 'msg',
                            style:'bottom:0;',
                            time: 1 //2秒后自动关闭
                        }); 
                    } 
                },
                 submitHandler:function(form){
                   layer.open({
		                content: '是否确定赠送',
		                btn: ['确定', '取消'],
		                yes: function(index){//点击去认证页面，index为该特定层的索引
		                    $.ajax({
		                    	url:"${ctx}/wx/member/giveShouXintoMember",
		                    	method:"POST",
		                    	data:$("#gives").serialize(),
		                    	success:function(data){
		                    		if(data.code==200){
		                    			layer.open({
		                                    content:"赠送成功",
		                                    btn:'我知道了',
		                                    yes:function(index){
		                                    	window.location.href="${ctx}/wx/member/shouXin"
		                                    }
		                                })
		                    		}else{
		                    			layer.open({
		                                    content:data.msg,
		                                    btn:'我知道了',                                  
		                                })
		                    		}
		                    	}
                    })
                    layer.close(index);
                },
                no:function(index){
                    layer.close(index);
                }
            });	    
                }     
            })
        })
    </script>
</body>
</html>