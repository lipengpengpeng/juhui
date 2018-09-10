<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
    <div class="container">
        <div class="withdraw-top clearfix" bkId="${bankId}">
             <span class="withdraw-left left">
               当前可转余额<i class="withdraw-money" id="allMoney">
                 <c:if test="${not empty memberInfo.balanceMoney}">
                	${memberInfo.balanceMoney}
                </c:if>
                <c:if test="${empty memberInfo.balanceMoney}">
                	0
                </c:if> 
                </i>元<label style="color: red">（确认转入，根据国家税务要求代扣10%税费）</label>
            </span>
            <!--  <span class="withdraw-right right" id="allWith">全部提现</span> -->
        </div>
        <!-- <form class="lock-form" action=""> -->
            <%-- <input type="hidden" name="myBankId" id="myBankId">
            <div class="form-row form-top-list">
                <a href="${ctx}/wx/trade/myBanks?type=1" class="withdraw-link clearfix">
               
                </a>
            </div> --%>
            <div class="form-list">
                <div class="form-row">
                    <label class="form-label">提现金额</label>
                    <input type="text" name="money" id="money" placeholder="请输入提现金额" class="row-input">
                </div>
                <!-- <div class="form-row">
                    <label class="form-label">备注</label>
                    <input type="text" name="remark" id="remark" placeholder="20字以内" class="row-input">
                </div> -->
            </div>
            <div class="withdraw-tip">
                <!-- *提现说明：申请提现后会有专门的人员后台进行审核，审核不通过将自动返还到提现账户，请耐心等待。 -->
                <div  style="color: red;display: none" id="msg">ddd</div>
            </div> 
             
            <div class="btn-box">
                <button type="submit" class="btn" id="addtrade">转入</button>
            </div>
       <!--  </form> -->
    </div>
    <script type="text/javascript">
    document.title = "转入";              
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
              	   url:"${ctx}/wx/trade/transderBalanceToTreasure",
              	   method:"POST",
              	   data:{             		
              		money:$("#money").val(),
              	  },
              	  success:function(data){
              		if(data.code==200){
              			layer.open({
                            content: data.msg ,
                            btn: ['确定'],
                            yes: function(index){
                                location.href='${ctx}/wx/trade/toFhTreasurePage';
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