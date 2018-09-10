<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
<style>
.pay-set-password{
	float: left;
	line-height: 3.84rem;
	color: #f92c2c;
	font-size: 1.5rem;
	margin-left: 24.26rem;
}
</style>
<body>
    <div class="container">
        <form class="buy-bonus-form" action="">
        
           <div class="withdraw-top clearfix" style="font-size:1.6rem">
             <span class="withdraw-left left">
                                 可以转移分红权：<i class="withdraw-money">${member.dividendsNum}</i>
                   ,余额 ：<i class="withdraw-money">${member.balanceMoney}</i>
                   ,积分：<i class="withdraw-money">${member.consumptionMoney}</i>
                 (<label style="color: red">转移扣${baseInfo.value*100}%手续费</label>)
             </span>
           
           </div>
        
            <div class="bonus-wrap">
                <div class="bonus-item clearfix">
                    <label class="bonus-label left">转移数：</label>
                    <input type="number" placeholder="输入转移数额" class="bonus-input right" id="bonus-num">
                </div>
            </div>
            <div class="profits-item clearfix">
                <label class="profits-label left">转移会员:</label>
                <span class="profits-span right">
                    <input type="text" name="mobile" placeholder="请输入会员手机号" class="profits-input" id="phone" value="">
                </span>
            </div>
            <div class="profits-item clearfix">
                <label class="profits-label left">会员名称:</label>
                <span class="profits-span right" id="memName" style="margin-right:-90px">
                    <!-- 会员名称 -->
                </span>
            </div>
            
            <div class="profits-item clearfix">
                <label class="profits-label left">支付密码:</label>
                <span class="profits-span right">
                    <input type="password" name="payPassword" id="payPassword" placeholder="请输入支付密码" class="profits-input"  value="">
                </span>
            </div>
            
            <c:if test="${empty member.payPassword}">
				 <a href="${ctx}/wx/member/toSetPay" class="pay-set-password">设置支付密码</a>
			</c:if>
			<c:if test="${not empty member.payPassword}">
				 <a href="${ctx}/wx/member/toSetPay" class="pay-set-password">忘记密码</a>
			</c:if>
            
            <div class="bonus-pay" style="margin-top: 47px;">               
                <ul class="bonusPay-ul">
                
                   <li class="bonusPay-li clearfix" data-id="0">
                        <label class="bonusPay-left left">
                            <img src="${imagePath}/wx/o2o-shop/shouxin_pay.png" class="bonusPay-img">
                            <span class="bonusPay-words">分红权</span>
                        </label>
                        
                        <div class="bonusPay-right right">
                            <i class="icon-circle"></i>
                        </div>
                    </li>
                    
                    <li class="bonusPay-li clearfix" data-id="1">
                        <label class="bonusPay-left left">
                            <img src="${imagePath}/wx/o2o-shop/shouxin_pay.png" class="bonusPay-img">
                            <span class="bonusPay-words">余额</span>
                        </label>                      
                        <div class="bonusPay-right right">
                            <i class="icon-circle"></i>
                        </div>
                    </li>                   
                    
                   <li class="bonusPay-li clearfix" data-id="2">
                        <label class="bonusPay-left left">
                            <img src="${imagePath}/wx/o2o-shop/shouxin_pay.png" class="bonusPay-img">
                            <span class="bonusPay-words">积分</span>
                        </label>                    
                        <div class="bonusPay-right right">
                            <i class="icon-circle"></i>
                        </div>
                    </li> 
                                     
                </ul>
            </div>
                                       
            <div class="btn-box">
                <button type="button" class="btn" id="saveToPay">确认转移</button>
            </div>
            <div class="bonus-bottom" style="margin-bottom:2rem;">
                <a href="${ctx}/wx/trade/findTransferHistory?pageNo=1&pageSize=30&type=0" class="bonus-link">
                                        查看转移记录<i class="main-sprite icon-more"></i>
                </a>
            </div>
           
            <div class="loading-middle hidden">
              <img src="${imagePath}/wx/o2o-shop/load.gif" class="loadImg">
           </div>
        </form>
    </div>
    <script type="text/javascript">
        $(function(){
        	document.title = "积分转移";
            $(".icon-circle").on("click",function(){
                var that = $(this);
                $(".icon-circle").removeClass('icon-circle-active');
                that.addClass("icon-circle-active");
                actMoney(that.parent().parent().attr("data-id"));
            });
            
            $("#saveToPay").on("click",function(){
                var num = $(".icon-circle-active").parents("li").attr("data-id");
                var bonusNum = $("#bonus-num").val();//分权数值，字符串类型
                
                var payPassword=$("#payPassword").val();
                                   
                if(bonusNum == "" || bonusNum == undefined){
                    layer.open({
                        content:'请填写转移数量',
                        btn:'我知道了'
                    })
                }else{
                	var intBonus = parseInt(bonusNum);//转整型
                    var reg = /^(([1-9]\d*)|([0-9]+\.[0-9]{1,2}))$/;
                    //var mobile = /^13\d{9}$|15\d{9}$|17\d{9}$|18\d{9}$|13\d{1}-\d{4}-\d{4}$|15\d{1}-\d{4}-\d{4}$|17\d{1}-\d{4}-\d{4}$|18\d{1}-\d{4}-\d{4}$/; 
                    ////支持13 15 17 18开头,同时支持xxx-xxxx-xxxx格式手机输入
                    var phone=$(".profits-input").val();
                    if(intBonus==0){
                        layer.open({
                            content:'转移数量为非负非零数字',
                            btn:'我知道了'
                        })
                    }else{
                    	if(phone == "" || phone == undefined){
                            layer.open({
                                content:'请填写会员手机号码',
                                btn:'我知道了'
                             })
                        }else{
                        	 if(!reg.test(phone)){
                                 layer.open({
                                     content:'请填写正确的会员手机号码',
                                     btn:'我知道了'
                                 })
                             }else{
                            	 var rePas=/^[0-9]*$/;
                            	 if(payPassword == "" || payPassword == undefined){
                                 	layer.open({
                                         content:'请输入支付密码',
                                         btn:'我知道了'
                                     })
                                 }else{
                                	 
                                	 if(num=="0"){
                                		
                                     	ajaxLoad("0");
                                     }else if(num=="1"){
                                    	 
                                    	 ajaxLoad("1");
                                     }else if(num=="2"){ 
                                    	 
                                    	 ajaxLoad("2");
                                     
                                    }else{
                                    	layer.open({
                                            content:'请选择转移类型',
                                            btn:'我知道了'
                                        })	
                                    } 
                                 }                          	
                             }
                         }
                    }
                }
                
            })
        })
        
        function ajaxLoad(payType){        
        	layer.open({
                content: '是否确认转移',
                btn: ['确定', '取消'],
                yes: function(index){//点击去认证页面，index为该特定层的索引
                	$.ajax({
        	            url: '${ctx}/wx/trade/memberPointTransfer',
        	            type: 'post',
        	            dataType: 'json',
        	            data: {
        	            	"transferMoney":$("#bonus-num").val(),
        	            	"transferMemberMobile":$("#phone").val(),
        	            	"payPassword":$("#payPassword").val(),
        	            	"type":payType
        	            	},
        	            success:function(data){
        	            	if(data.code==200){
        	            		window.location.href="${ctx}/wx/member/myWallet"
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
            		$(".bonusPay-money").text((bonusNum*0.12).toFixed(2));
            	}
            	 $("#ninePay").val(bonusNum)
            	 $("#payMoney").val((bonusNum*0.12).toFixed(2))
            }
        }
    </script>
</body>
</html>