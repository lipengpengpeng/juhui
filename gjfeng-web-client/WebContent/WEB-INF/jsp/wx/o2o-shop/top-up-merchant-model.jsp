<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>

<style>
  .bonus-label {
    color: #ff5350;
    padding-left: 13.2rem;
    width: 8.2rem;
    }
  .bonus-item {
    width: 100%;
    height: 4.5rem;
    line-height: 4.5rem;
    border: 1px solid #555;
    border-radius: 10px;
    margin-top: 16px;
   }
</style>

<body>
    <div class="container">
       <!--  <form class="buy-bonus-form"> -->
            <div class="bonus-wrap"> 
                <c:if test="${member.merchantType==0}">
                    <input name="merchantType" type="hidden" value="1" id="merchantType"/>
                </c:if>
                <c:if test="${member.merchantType==1}">
                    <input name="merchantType" type="hidden" value="2" id="merchantType"/>
                </c:if>
                <c:if test="${member.merchantType==2}">
                    <input name="merchantType" type="hidden" value="3" id="merchantType"/>
                </c:if>
               
                <c:if test="${member.merchantType==0}">
                   <div class="bonus-item clearfix type1" dataValue='1' style="border:1px solid #5ac715">
                     <label class="bonus-label " >商家版</label>                 
                   </div>
                </c:if>              
                
                
                   <c:if test="${member.merchantType==0}">
                      <div class="bonus-item clearfix type2" dataValue='2'>
                       <label class="bonus-label " >企业版</label>                 
                      </div>
                   </c:if>
                   <c:if test="${member.merchantType==1}">
                      <div class="bonus-item clearfix type2" dataValue='2' style="border:1px solid #5ac715">
                       <label class="bonus-label " >企业版</label>                 
                      </div>
                   </c:if>
                   
                   
                   <c:if test="${member.merchantType==0}">
                       <div class="bonus-item clearfix type3" dataValue='3'>
                        <label class="bonus-label " >商家代理</label>                 
                      </div>
                   </c:if>
                   <c:if test="${member.merchantType==1}">
                       <div class="bonus-item clearfix type3" dataValue='3'>
                        <label class="bonus-label " >商家代理</label>                 
                      </div>
                   </c:if>
                   <c:if test="${member.merchantType==2}">
                       <div class="bonus-item clearfix type3" dataValue='3'>
                        <label class="bonus-label " >商家代理</label>                 
                      </div>
                   </c:if>
                   
                   
             
               
            </div>
            <div class="bonus-pay">
                <div class="bonusPay-top clearfix">
                    <label class="bonusPay-label left">应付金额</label>
                    <c:if test="${member.merchantType==0}">
                       <div class="bonusPay-money right" id="nicePay">1200</div>
                    </c:if>
                    <c:if test="${member.merchantType==1}">
                       <div class="bonusPay-money right" id="nicePay">6000</div>
                    </c:if>
                    <c:if test="${member.merchantType==2}">
                       <div class="bonusPay-money right" id="nicePay">18000</div>
                    </c:if>
                    
                </div>
                <input type="hidden" name="type" id="type"/>
                <ul class="bonusPay-ul">
                    <li class="bonusPay-li clearfix" data-id="0" >
                        <label class="bonusPay-left left"  style="height: 100%;">
                            <img src="${imagePath}/wx/o2o-shop/weixin.png" class="bonusPay-img">
                            <span class="bonusPay-words">微信支付</span>
                        </label>
                        <div class="bonusPay-right right">
                            <i class="icon-circle"></i>
                        </div>
                    </li>
                    <li class="bonusPay-li clearfix" data-id="3">
                        <label class="bonusPay-left left">
                            <img src="${imagePath}/wx/o2o-shop/微信图片_20171215180110.png" class="bonusPay-img">
                            <span class="bonusPay-words">巨惠宝</span>
                        </label>
                        <div class="bonusPay-right right">
                            <i class="icon-circle"></i>
                        </div>
                    </li>
                   
                </ul>
            </div>
            <div style="font-size: 16px;">
                  <input type="checkbox" name="memService" id="memService" style="margin-left: -65px;box-shadow: none;margin-top: 20px;">
                  <c:if test="${member.merchantType==0}">
                     <a href="${ctx}/wx/rule/memberCooperationRule?key=merchant_1" id="merchant" style="margin-left:6px;font-size:16px">用户勾选即代表同意《服务条款》和《法律声明》</a>
                  </c:if>
                  <c:if test="${member.merchantType==1}">
                     <a href="${ctx}/wx/rule/memberCooperationRule?key=merchant_2" id="merchant" style="margin-left:6px;font-size:16px">用户勾选即代表同意《服务条款》和《法律声明》</a>
                  </c:if>
                  <c:if test="${member.merchantType==2}">
                     <a href="${ctx}/wx/rule/memberCooperationRule?key=merchant_3" id="merchant" style="margin-left:6px;font-size:16px">用户勾选即代表同意《服务条款》和《法律声明》</a>
                  </c:if>
                  
             </div>
            <div class="btn-box">
                <button type="button" class="btn" id="saveToPay">确认充值</button>
            </div>
            <div class="loading-middle hidden">
              <img src="${imagePath}/wx/o2o-shop/load.gif" class="loadImg">
           </div>
        <!-- </form> -->
    </div>
    <script type="text/javascript">
        $(function(){
        	document.title = "商家升级";
            $(".icon-circle").on("click",function(){
                var that = $(this);
                $(".icon-circle").removeClass('icon-circle-active');
                that.addClass("icon-circle-active");
            });
            
            
            $(".bonus-item").each(function(){
            	$(this).click(function(){
            		var dataValue=$(this).attr("dataValue");     
            		if(dataValue==1){
            			$(".type1").css({border:"1px solid #5ac715"});
            			$(".type2").css({border:"1px solid #555"});
            			$(".type3").css({border:"1px solid #555"});
            			$("#merchantType").val("1");
            			$("#nicePay").html("1200")
            			$("#merchant").attr("href","${ctx}/wx/rule/memberCooperationRule?key=merchant_1")
            		}else if(dataValue==2){           			
            			$(".type1").css({border:"1px solid #555"});
            			$(".type2").css({border:"1px solid #5ac715"});
            			$(".type3").css({border:"1px solid #555"});
            			$("#merchantType").val("2");
            			$("#nicePay").html("6000")
            			$("#merchant").attr("href","${ctx}/wx/rule/memberCooperationRule?key=merchant_2")
            		}else{
            			$(".type1").css({border:"1px solid #555"});
            			$(".type2").css({border:"1px solid #555"});
            			$(".type3").css({border:"1px solid #5ac715"});
            			$("#merchantType").val("3");
            			$("#nicePay").html("18000")
            			$("#merchant").attr("href","${ctx}/wx/rule/memberCooperationRule?key=merchant_3")
            		}
            	})
            })
            

            $("#saveToPay").on("click",function(){
            	
            	if($('#memService').is(':checked')==false) {
            		layer.open({
                        content:'请勾选协议',
                        btn:'我知道了'
                    })
                    return false;
            	 }
            	
                var num = $(".icon-circle-active").parents("li").attr("data-id");
                if(num=="0"){             	     
                	addShouXin("0")                
                }else if(num=="3"){
                	addShouXin("3")
                }else{
                    layer.open({
                        content:'请选择支付方式',
                        btn:'我知道了'
                    })
                 }
            })
                
        })
        
        //微信余额
        function addShouXin(type){
        	var merchantType=$("#merchantType").val()
        	var tradeMoney=0;
        	
        	if(merchantType==1){
        		tradeMoney=1200
        	}else if(merchantType==2){
        		tradeMoney=6000
        	}else if(merchantType==3){
        		tradeMoney=18000
        	}    
        	layer.open({
                content: '是否确认充值',
                btn: ['确定', '取消'],
                yes: function(index){//点击去认证页面，index为该特定层的索引
                	
                	$(".loading-middle").removeClass("hidden");
                    $(".btn").attr("disabled","disabled");
                    $.ajax({
                    	url:"${ctx}/wx/trade/addMerchantRechargeHistory",
                    	method:"POST",
                    	data:{
                    		payType:type,
                    		tradeMoney:tradeMoney,
                    		merchantType:merchantType                  			
                    	},
                    	success:function(data){
                    		if(data.code==200){
                    			if(type==0){//微信支付
                    		        wx.config({
                    				    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                    				    appId: data.result.appId, // 必填，公众号的唯一标识
                    				    timestamp: data.result.timeStamp, // 必填，生成签名的时间戳
                    				    nonceStr: data.result.nonceStr, // 必填，生成签名的随机串
                    				    signature:data.result.paySign,// 必填，签名，见附录1
                    				    jsApiList: ['chooseWXPay'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
                    				}); 
                    		    	
                    				wx.ready(function(){
                    					wx.chooseWXPay({
                    					    timestamp: data.result.timeStamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
                    					    nonceStr: data.result.nonceStr, // 支付签名随机串，不长于 32 位
                    					    package: data.result.package_,
                    					    //package: data.result.package_str, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
                    					    signType: "MD5", // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
                    					    paySign: data.result.paySign, // 支付签名
                    					    success: function (res) {
                    					        // 支付成功后的回调函数   
                    					    	if(res.errMsg == "chooseWXPay:ok" ) {
                    						    	 layer.open({
                    						                content: '支付成功',
                    						                btn: ['我知道了'],
                    						                yes: function(index){//点击去认证页面，index为该特定层的索引                  				                	
                    						                	window.location.href="${ctx}/wx/index/online"
                    						                    layer.close(index);
                    						                }                   				               
                    						           });	                       				     
                    						        } 
                    					    	if(res.errMsg == "chooseWXPay:cancel" ) {
                    						    	 layer.open({
                    						                content: '取消支付',
                    						                btn: ['我知道了'],
                    						                yes: function(index){//点击去认证页面，index为该特定层的索引                  				                	
                    						                   $(".loading-middle").addClass("hidden");
                    				                            $(".btn").removeAttr("disabled");
                    						                    layer.close(index);
                    						                }                   				               
                    						           });	                       				     
                    						     }
                    					    	if(res.errMsg == "chooseWXPay:fail" ) {
                    						    	 layer.open({
                    						                content: '支付失败',
                    						                btn: ['我知道了'],
                    						                yes: function(index){//点击去认证页面，index为该特定层的索引                  				                	
                    						                	$(".loading-middle").addClass("hidden");
                    				                            $(".btn").removeAttr("disabled");
                    						                    layer.close(index);
                    						                }                   				               
                    						           });	
                    					    	}
                    					    	}, 
                    					    
                    					});
                    				});  
                    			}
                    			//凤凰宝
                    			if(type==3){
                    				layer.open({
                                        content:data.msg,
                                        btn:['确定', '取消'],
                                        yes: function(index){
                                        	window.location.href="${ctx}/wx/index/online"
                                        },
                                        no:function(index){
                                        	$(".loading-middle").addClass("hidden");
				                            $(".btn").removeAttr("disabled");
                                            layer.close(index);
                                        }
                                   });
                    			}
                    		}else{
                    			layer.open({
                                    content:data.msg,
                                    btn:['确定', '取消'],
                                    yes: function(index){
                                    	$(".loading-middle").addClass("hidden");
			                            $(".btn").removeAttr("disabled");
                                    	 layer.close(index);
                                    },
                                    no:function(index){
                                    	$(".loading-middle").addClass("hidden");
			                            $(".btn").removeAttr("disabled");
                                        layer.close(index);
                                    }
                               });
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
        

       
        
    </script>
</body>
</html>