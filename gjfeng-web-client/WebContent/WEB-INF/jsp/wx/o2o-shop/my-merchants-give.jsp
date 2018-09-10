<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
<body>
    <div class="container">
        <form class="buy-bonus-form" action="">
            
            <div class="profits-item clearfix">
                <label class="profits-label left">赠送会员:</label>
                <span class="profits-span right">
                    <input type="tel" name="mobile" placeholder="请输入会员手机号" class="profits-input" id="phone" value="">
                </span>
            </div>
            <div class="profits-item clearfix">
                <label class="profits-label left">会员名称:</label>
                <span class="profits-span right" id="memName" style="margin-right:-90px">
                    <!-- 会员名称 -->
                </span>
            </div>
            
             <div class="bonus-pay" style="margin-top: 47px;">               
                <ul class="bonusPay-ul">
                  <c:if test="${type==3}">
                      <li class="bonusPay-li clearfix" data-id="1">
                        <label class="bonusPay-left left">
                            <img src="${imagePath}/wx/o2o-shop/shouxin_pay.png" class="bonusPay-img">
                            <span class="bonusPay-words">商家版</span>
                        </label>
                        
                        <div class="bonusPay-right right">
                            <i class="icon-circle"></i>
                        </div>
                      </li>
                      
                       <li class="bonusPay-li clearfix" data-id="2">
                        <label class="bonusPay-left left">
                            <img src="${imagePath}/wx/o2o-shop/shouxin_pay.png" class="bonusPay-img">
                            <span class="bonusPay-words">企业版</span>
                        </label>                      
                        <div class="bonusPay-right right">
                            <i class="icon-circle"></i>
                        </div>
                      </li>  
                  </c:if>               
                  
                   <c:if test="${type==4}">
                      <li class="bonusPay-li clearfix" data-id="1">
                        <label class="bonusPay-left left">
                            <img src="${imagePath}/wx/o2o-shop/shouxin_pay.png" class="bonusPay-img">
                            <span class="bonusPay-words">商家版</span>
                        </label>
                        
                        <div class="bonusPay-right right">
                            <i class="icon-circle"></i>
                        </div>
                      </li>
                      <li class="bonusPay-li clearfix" data-id="3">
                        <label class="bonusPay-left left">
                            <img src="${imagePath}/wx/o2o-shop/shouxin_pay.png" class="bonusPay-img">
                            <span class="bonusPay-words">商家代理版</span>
                        </label>                    
                        <div class="bonusPay-right right">
                            <i class="icon-circle"></i>
                        </div>
                      </li> 
                   </c:if>
                                    
                   <c:if test="${type==5||type==6||type==7}">
                     
                     <li class="bonusPay-li clearfix" data-id="1">
                        <label class="bonusPay-left left">
                            <img src="${imagePath}/wx/o2o-shop/shouxin_pay.png" class="bonusPay-img">
                            <span class="bonusPay-words">商家版</span>
                        </label>
                        
                        <div class="bonusPay-right right">
                            <i class="icon-circle"></i>
                        </div>
                      </li>
                   
                     <li class="bonusPay-li clearfix" data-id="3">
                        <label class="bonusPay-left left">
                            <img src="${imagePath}/wx/o2o-shop/shouxin_pay.png" class="bonusPay-img">
                            <span class="bonusPay-words">商家代理版</span>
                        </label>                    
                        <div class="bonusPay-right right">
                            <i class="icon-circle"></i>
                        </div>
                     </li> 
                      
                     <li class="bonusPay-li clearfix" data-id="4">
                        <label class="bonusPay-left left">
                            <img src="${imagePath}/wx/o2o-shop/shouxin_pay.png" class="bonusPay-img">
                            <span class="bonusPay-words">企业代理版</span>
                        </label>                    
                        <div class="bonusPay-right right">
                            <i class="icon-circle"></i>
                        </div>
                     </li> 
                   </c:if>  
                           
                </ul>
            </div>
              
                     
            <div class="btn-box">
                <button type="button" class="btn" id="saveToPay">确认赠送</button>
            </div>
            <div class="bonus-bottom" style="margin-bottom:2rem;">
                <a href="${ctx}/wx/trade/findMerchantGiveHistory" class="bonus-link">
                    查看赠送记录<i class="main-sprite icon-more"></i>
                </a>
            </div>
            <div class="loading-middle hidden">
              <img src="${imagePath}/wx/o2o-shop/load.gif" class="loadImg">
           </div>
        </form>
    </div>
    <script type="text/javascript">
        $(function(){
        	document.title = "赠送尊享版商家";
            $(".icon-circle").on("click",function(){
                var that = $(this);
                $(".icon-circle").removeClass('icon-circle-active');
                that.addClass("icon-circle-active");
                actMoney(that.parent().parent().attr("data-id"));
            });
            
            $("#saveToPay").on("click",function(){
            	 var num = $(".icon-circle-active").parents("li").attr("data-id");
            	 var phone=$(".profits-input").val(); 
            	 if(phone == "" || phone == undefined){
                     layer.open({
                         content:'请填写会员手机号码',
                         btn:'我知道了'
                  })
                }else{
                    var reg = /^(([1-9]\d*)|([0-9]+\.[0-9]{1,2}))$/;
                	if(!reg.test(phone)){
                        layer.open({
                            content:'请填写正确的会员手机号码',
                            btn:'我知道了'
                        })
                    }else if(num==1){
                    	ajaxLoad("1")
                    }else if(num==2){
                    	ajaxLoad("2")
                    }else if(num==3){
                    	ajaxLoad("3")
                    }else if(num==4){
                    	ajaxLoad("4")
                    }else{
                    	layer.open({
                            content:'请选择赠送类型',
                            btn:'我知道了'
                        })
                    }
                }
            })
        })
        
        function ajaxLoad(type){       	
        	layer.open({
                content: '是否确认赠送',
                btn: ['确定', '取消'],
                yes: function(index){//点击去认证页面，index为该特定层的索引
                	$.ajax({
                		url:"${ctx}/wx/trade/addMerchantGiveHistory",
                		method:"POST",
                		data:{
                			mobile:$(".profits-input").val(),
                		    type:type
                		},
                		success:function(data){
                			if(data.code==200){
                				window.location.href="${ctx}/wx/trade/findMerchantGiveHistory"
                			}else{
                				layer.open({
                                    content:data.msg,
                                    btn:'我知道了'
                                })
                			}
                		},
                		error:function(){
         	            	layer.open({
                                 content:"网络异常",
                                 btn:'我知道了'
                             })
         	            }
                	})
                    layer.close(index);
                },
                no:function(index){
                    layer.close(index);
                }
            });	        
        }
        
      
        
        //根据手机号码获取用户信息
        $("#phone").blur(function(){
        	$.ajax({
        		url:"${ctx}/wx/member/findMemberByMoblie",
        		method:"GET",
        		data:{mobile:$("#phone").val()},
        		success:function(data){
        			if(data.code==200){
        				if(data.result.name){
        					$("#memName").html(data.result.name);
        				}else{
        					$("#memName").html(data.result.nickName);
        				}
        				
        			}else{
        				layer.open({
                            content:data.msg,
                            btn:'我知道了'
                        })
        			}       			      			
        		}
        	})
        })
        
        function actMoney(status){
        	var bonusNum = $("#bonus-num").val();//分权数值，字符串类型
            var intBonus = parseInt(bonusNum);//转整型
            var reg = /^(([1-9]\d*)|([0-9]+\.[0-9]{1,2}))$/;
            if(bonusNum != "" && bonusNum != undefined && reg.test(bonusNum) && intBonus!=0){
            	if(status=='4'){
            		$(".bonusPay-money").text(bonusNum);
            	}else{
            		$(".bonusPay-money").text((bonusNum*0.1).toFixed(2));
            	}
            	 $("#ninePay").val(bonusNum)
            	 $("#payMoney").val((bonusNum*0.1).toFixed(2))
            }
        }
    </script>
</body>
</html>