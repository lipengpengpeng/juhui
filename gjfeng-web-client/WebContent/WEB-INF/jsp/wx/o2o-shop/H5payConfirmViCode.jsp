<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
    <div class="container">
        <div class="withdraw-top clearfix" >
            <input name="orderId" value="${orderId}"  id="orderId" type="hidden"/> 
            <input name="payMoney" value="${payMoney}" id="payMoney" type="hidden"/>          
            <input name="accNo" value="${accNo}" id="accNo" type="hidden"/>  
            <input name="mobile" value="${mobile}" id="mobile" type="hidden"/>                     
            <div class="form-list">                
                <div class="form-row">
                    <label class="form-label">验证码:</label>
                    <input type="text" name="verifyCode" style="margin-left: 1rem;" id="verifyCode" placeholder="请输入手机验证码" class="row-input">
                </div>
            </div>
            <div class="withdraw-tip">
               <!--  *支付说明：请确认支付信息正确，否则无法进行支付 -->
                <div  style="color: red;display: none" id="msg">ddd</div>
            </div>
            <div class="btn-box">
                <button type="submit" class="btn" id="addtrade">确认支付</button>
            </div>
            <div class="loading-middle hidden">
              <img src="${imagePath}/wx/o2o-shop/load.gif" class="loadImg">
            </div>
       <!--  </form> -->
    </div>
    <script type="text/javascript">
    document.title = "快捷支付确认";
        
        
        $("#verifyCode").focus(function(){
        	 $("#msg").html("")
			 $("#msg")[0].style.display = "none"
        })
        
         $("#verifyCode").blur(function(){
        	 if($("#verifyCode").val()==''){
          	   	   $("#msg").html("验证码不能为空")
    			   $("#msg")[0].style.display = "block"
          	}
        })
        
        
        
        function vilade(){      	        	      	
        	if($("#verifyCode").val()==''){
        	   $("#msg").html("验证码不能为空")
  			   $("#msg")[0].style.display = "block"
  			   return false
        	}    	        	       	
        	return true
        }
        
        $("#addtrade").click(function(){       	
        	var isReadName="${isReadName}";
        	if(null==isReadName || "" == isReadName || isReadName=='0'){
        		layer.open({
                    content: "请先实名认证" ,
                    btn: ['确定'],
                    yes: function(index){
                    	location.href='${ctx}/wx/member/toRealName';
                        layer.close(index);
                    },
                }); 
        		return false;
        	}
        	if(vilade()){   
        		$(".loading-middle").removeClass("hidden");
                $(".btn").attr("disabled","disabled");
        		$("#addtrade").attr("disabled","disabled");
        		 $.ajax({
              	   url:"${ctx}/wx/member/yinLPayCofirom",
              	   method:"POST",
              	   data:{
              		orderId:$("#orderId").val(),
              		payMoney:$("#payMoney").val(),
              		accNo:$("#accNo").val(),             		
              		mobile:$("#mobile").val(),
              		verifyCode:$("#verifyCode").val()
              	  },
              	  success:function(data){
              		if(data.code==200){
              			layer.open({
                            content: data.msg ,
                            btn: ['确定'],
                            yes: function(index){
                            	$(".loading-middle").addClass("hidden");
                                
                                location.href='${ctx}/wx/member/myWallet'
                                layer.close(index);
                            },
                           
                        });  
              			
              		}else{
              			layer.open({
                            content:data.msg ,
                            btn: ['确定'],
                            yes: function(index){//点击去认证页面，index为该特定层的索引
                            	$(".loading-middle").addClass("hidden");
                               
                            	$("#addtrade").removeAttr("disabled")
                                layer.close(index);
                            },
                           
                        });  
              		}
              	  }
                  }) 
        	} 
        	
        })
        
   
    </script>
</body>
</html>