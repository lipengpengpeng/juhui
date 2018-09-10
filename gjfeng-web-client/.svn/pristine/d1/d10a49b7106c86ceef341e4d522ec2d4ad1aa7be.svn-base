<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
    <div class="container">
        <div class="withdraw-top clearfix" bkId="${bankId}">
             <span class="withdraw-left left">
                可转移金额<i class="withdraw-money" id="allMoney">
                 
                ${resultVo.result.dividendsRewardMoney+resultVo.result.recommendRewardMoney+resultVo.result.indiRewardMoney}
               
                </i>元(<label style="color: red">提取福利扣10%手续费</label>)
            </span>
            <!--  <span class="withdraw-right right" id="allWith">全部提现</span> -->
        </div>
        <!-- <form class="lock-form" action=""> -->            
            <div class="form-list">
                <div class="form-row">
                    <label class="form-label">转移金额</label>
                    <input type="text" name="money" id="money" placeholder="请输入提现金额" class="row-input">
                </div>              
            </div>
            <div class="withdraw-tip">
                *提现说明：申请提现后会有专门的人员后台进行审核，审核不通过将自动返还到提现账户，请耐心等待。
                <div  style="color: red;display: none" id="msg">ddd</div>
            </div>
            <div class="btn-box">
                <button type="submit" class="btn" id="addtrade">立即转移</button>
            </div>
       <!--  </form> -->
    </div>
    <script type="text/javascript">
    document.title = "分红金额转移";
               
        $("#money").focus(function(){
        	 $("#msg").html("")
			 $("#msg")[0].style.display = "none"
        })
        
         $("#money").blur(function(){
        	 if($("#money").val()==''){
          	   	   $("#msg").html("提现金额不能为空")
    			   $("#msg")[0].style.display = "block"
          	}else{
          		var reg = /^-?\d+\.?\d{0,1}$/;
          		if(!reg.test($("#money").val())){
          			$("#msg").html("输入数字且数字只能有一位小数")
          			$("#msg")[0].style.display = "block"
          		}
          		
          		if(parseInt($("#money").val())<200){
        			$("#msg").html("每次提现金额不能少于200")
        			$("#msg")[0].style.display = "block"
        		}          		
          		if(parseFloat($("#money").val())>parseInt(50000)){
        			$("#msg").html("每次提现金额不能大于50000")
        			$("#msg")[0].style.display = "block"
        		}
          	}
        })
        
        
        
        function vilade(){
        	
        	if($("#bk").attr("bankId")==''||$("#bk").attr("bankId")==undefined){
        		$("#msg").html("提现银行卡不能为空")
   			   $("#msg")[0].style.display = "block"
   			   return false
        	}
        	
        	if($("#money").val()==''){
        	   $("#msg").html("提现金额不能为空")
  			   $("#msg")[0].style.display = "block"
  			   return false
        	}else{
        		var reg =/^-?\d+\.?\d{0,1}$/;
        		if(!reg.test($("#money").val())){
        			$("#msg").html("输入数字且数字只能有一位小数")
        			$("#msg")[0].style.display = "block"
        			return false
        		}
        		      		
        		if(parseInt($("#money").val())<200){
        			$("#msg").html("每次提现金额不能少于200")
        			$("#msg")[0].style.display = "block"
        		    return false
        		}       		
        		if(parseFloat($("#money").val())>parseInt(50000)){
        			$("#msg").html("每次提现金额不能大于50000")
        			$("#msg")[0].style.display = "block"
        			return false
        		}
        	}
        	
        	var remark=$("#remark").val()
        	if(remark.length>20){
        		$("#msg").html("备注不能大于20个字符")
    			$("#msg")[0].style.display = "block"
    		    return false
        	}
        	
        	return true
        }
        
        $("#addtrade").click(function(){
        	/* layer.open({
                content:"尊敬的各位家人们，因为2.0系统升级到3.0系统对接的数据有误，需要逐笔核对，由于数据庞大，核对工作需要大量时间，为避免出现错误，在数据没有理清之前，系统暂停提现功能，在此期间交易、“分红”正常，4月17日中午12点后恢复提现。",
                 btn:'我知道了'
            })*/
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
        		var mem=$("#money").val();
        		if(mem < 200){
            		layer.open({
                        content: "提现金额不能小于200元" ,
                        btn: ['确定'],
                        yes: function(index){
                            layer.close(index);
                        },
                    });  
            		return false;
            	}
        		
        		if(parseFloat(mem) > parseInt(50000)){
            		layer.open({
                        content: "提现金额不能大于50000元" ,
                        btn: ['确定'],
                        yes: function(index){
                            layer.close(index);
                        },
                    });  
            		return false;
            	}
        		
        		$("#addtrade").attr("disabled","disabled");
        		 $.ajax({
              	   url:"${ctx}/wx/trade/addDrawCash",
              	   method:"POST",
              	   data:{
              		myBankId:$("#bk").attr("bankId"),
              		money:$("#money").val(),
              	    remark:$("#remark").val()
              	  },
              	  success:function(data){
              		if(data.code==200){
              			layer.open({
                            content: data.msg ,
                            btn: ['确定'],
                            yes: function(index){
                                location.href='${ctx}/wx/trade/toDrawHistory';
                                layer.close(index);
                            },
                           
                        });  
              			
              		}else{
              			layer.open({
                            content:data.msg ,
                            btn: ['确定'],
                            yes: function(index){//点击去认证页面，index为该特定层的索引
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