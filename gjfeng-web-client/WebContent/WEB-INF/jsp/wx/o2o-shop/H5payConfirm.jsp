<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
    <div class="container">
        <div class="withdraw-top clearfix" bkId="${bankId}">
            <input name="orderId" value="${orderId}" id="orderId" type="hidden"/>
        
            <input name="retUrl" value="${retUrl}" id="retUrl" type="hidden"/>
            
           
             <%-- <span class="withdraw-left left">
                可提取福利<i class="withdraw-money" id="allMoney">
                 <c:if test="${not empty resultVo.result['withdrawalMoney']}">
                	${resultVo.result['withdrawalMoney']}
                </c:if>
                <c:if test="${empty resultVo.result['withdrawalMoney']}">
                	0
                </c:if> 
                </i>元(<label style="color: red">提取福利扣10%手续费</label>)
            </span>
            <!--  <span class="withdraw-right right" id="allWith">全部提现</span> -->
        </div> --%>
        <!-- <form class="lock-form" action=""> -->
            <input type="hidden" name="myBankId" id="myBankId">
            <div class="form-row form-top-list">
                <a href="${ctx}/wx/trade/myBanksForPay?orderId=${orderId}&payMoney=${payMoney}&retUrl=${retUrl}&account=${mobile}" class="withdraw-link clearfix">
               
                </a>
            </div>
            <div class="form-list">
                 <div class="form-row">
                    <label class="form-label" style="width: 8.2rem;">身份证号码:</label>
                    <input type="text" name="idCard" style="margin-left: 1rem;" id="idCard" value="${idCard}" class="row-input" readOnly="true">
                </div>
                <div class="form-row">
                    <label class="form-label"  style="width: 8.2rem;">支付金额:</label>
                    <input type="text" style="margin-left: 1rem;" name="payMoney" id="payMoney" value="${payMoney}" class="row-input" readOnly="true">
                </div>
                <div class="form-row">
                    <label class="form-label"  style="width: 8.2rem;">电话号码:</label>
                    <input type="text" name="mobile" style="margin-left: 1rem;" id="mobile" value="${mobile}" placeholder="请输入电话号码" readOnly="true" class="row-input">
                </div>
            </div>
            <div class="withdraw-tip">
                *支付说明：请确认支付信息正确，否则无法进行支付
                <div  style="color: red;display: none" id="msg">ddd</div>
            </div>
            <div class="btn-box">
                <button type="submit" class="btn" id="addtrade">确认信息</button>
            </div>
       <!--  </form> -->
           <div class="loading-middle hidden">
              <img src="${imagePath}/wx/o2o-shop/load.gif" class="loadImg">
           </div>
    </div>
    <script type="text/javascript">
    document.title = "快捷支付申请";
        
        
        $("#mobile").focus(function(){
        	 $("#msg").html("")
			 $("#msg")[0].style.display = "none"
        })
        
         $("#mobile").blur(function(){
        	 if($("#mobile").val()==''){
          	   	   $("#msg").html("电话号码不能为空")
    			   $("#msg")[0].style.display = "block"
          	}else{
          		var reg = /^13\d{9}$|15\d{9}$|17\d{9}$|18\d{1}\d{8}|13\d{1}-\d{4}-\d{4}$|15\d{1}-\d{4}-\d{4}$|17\d{1}-\d{4}-\d{4}$|18\d{1}-\d{4}-\d{4}$/;
          		if(!reg.test($("#mobile").val())){
          			$("#msg").html("输入电话号码格式不正确")
          			$("#msg")[0].style.display = "block"
          		}         		        		
          	}
        })
        
        
        
        function vilade(){
        	
        	if($("#bk").attr("bankId")==''||$("#bk").attr("bankId")==undefined){
        		$("#msg").html("银行卡不能为空")
   			   $("#msg")[0].style.display = "block"
   			   return false
        	}
        	
        	if($("#mobile").val()==''){
        	   $("#msg").html("电话号码不能为空")
  			   $("#msg")[0].style.display = "block"
  			   return false
        	}else{
        		var reg =/^13\d{9}$|15\d{9}$|17\d{9}$|18\d{1}\d{8}|13\d{1}-\d{4}-\d{4}$|15\d{1}-\d{4}-\d{4}$|17\d{1}-\d{4}-\d{4}$|18\d{1}-\d{4}-\d{4}$/;
        		if(!reg.test($("#mobile").val())){
        			$("#msg").html("电话号码格式不正确")
        			$("#msg")[0].style.display = "block"
        			return false
        		}
      		
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
        		$(".loading-middle").removeClass("hidden");
                $(".btn").attr("disabled","disabled");
        		$("#addtrade").attr("disabled","disabled");
        		 $.ajax({
              	   url:"${ctx}/wx/member/yinLPay",
              	   method:"POST",
              	   data:{
              		orderId:$("#orderId").val(),
              		payMoney:$("#payMoney").val(),
              		accNo:$("#accNo").val(),
              		retUrl:$("#retUrl").val(),
              		mobile:$("#mobile").val()
              	  },
              	  success:function(data){
              		if(data.code==200){
              			layer.open({
                            content: data.msg ,
                            btn: ['确定'],
                            yes: function(index){
                            	$(".loading-middle").addClass("hidden");
                                location.href='${ctx}/wx/member/goConfirmPay?orderId='+$("#orderId").val()+"&payMoney="+$("#payMoney").val()+"&accNo="+$("#accNo").val()+"&mobile="+$("#mobile").val();
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
        
        
        
        $.ajax({
        	url:"${ctx}/wx/trade/getMyBank",
        	method:"GET",
        	data:{bankId:$(".withdraw-top").attr("bkId")},
        	success:function(data){
        		var str="";
        		if(data.result!=""&&data.result!=null){       		
        			if($(".withdraw-top").attr("bkId")==""){
        				str="<input name='accNo' id='accNo' value='"+data.result[0].bankCard+"' type='hidden'/><div class='withdraw-img-item left' id='bk' bankId='"+data.result[0].id+"'>"
                        +"<img src='${ctx}/common/image/bank/"+data.result[0].bankPic+"' class='withdraw-img'></div>"
                        +"<div class='withdraw-img-text left'>"
                        +"<p class='w-img-text1'>"+data.result[0].bankName+"</p>"
                        +"<p class='w-img-text2'>卡号"+data.result[0].bankCard+"</p>"
                        +"</div>";
        			}else{
        				str="<input name='accNo' id='accNo' value='"+data.result.bankCard+"' type='hidden'/><div class='withdraw-img-item left' id='bk' bankId='"+data.result.id+"'>"
                        +"<img src='${ctx}/common/image/bank/"+data.result.bankId.bankPic+"' class='withdraw-img'></div>"
                        +"<div class='withdraw-img-text left'>"
                        +"<p class='w-img-text1'>"+data.result.bankId.bankName+"</p>"
                        +"<p class='w-img-text2'>卡号"+data.result.bankCard+"</p>"
                        +"</div>";
        			}
        			
        		}else{
        			str="<div class='withdraw-img-text left' id='bk'>"
                        +"<p class='w-img-text1' style='line-height: none;''>请添加银行卡</p>"
                        +"</div>";
        		}
        		str+="<div class='withdraw-img-icon right'>"
                     +"<i class='my-icon icon-more'></i>"
                     +"</div>"
                     
                 $(".withdraw-link").html(str)
        	}
        })
        
        
        
    </script>
</body>
</html>