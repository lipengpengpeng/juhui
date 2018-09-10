<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
<body>
    <div class="container">
        <form class="buy-bonus-form" action="">
            <div class="bonus-wrap">
                <div class="bonus-item clearfix">
                    <label class="bonus-label left">消费金额：</label>
                    <input type="number" placeholder="输入金额（单位：元）" class="bonus-input right" id="bonus-num">
                </div>
            </div>
            <div class="profits-item clearfix">
                <label class="profits-label left">消费会员:</label>
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
            <div class="bonus-pay">
                <div class="bonusPay-top clearfix">
                    <label class="bonusPay-label left">应付金额</label>
                    <div class="bonusPay-money right">0.00</div>
                    <input type="hidden" id="ninePay" name="amount"/>
                    <input type="hidden" id="payMoney" />
                </div>
                <ul class="bonusPay-ul">
                
                   <li class="bonusPay-li clearfix" data-id="4">
                        <label class="bonusPay-left left">
                            <img src="${imagePath}/wx/o2o-shop/shouxin_pay.png" class="bonusPay-img">
                            <span class="bonusPay-words">授信额度</span>
                        </label>
                        <div class="bonusPay-right right">
                            <i class="icon-circle"></i>
                        </div>
                    </li>
                    
                    <li class="bonusPay-li clearfix" data-id="6">
                        <label class="bonusPay-left left">
                            <img src="${imagePath}/wx/o2o-shop/微信图片_20171215180110.png" class="bonusPay-img">
                            <span class="bonusPay-words">巨惠宝</span>
                        </label>
                        <div class="bonusPay-right right">
                            <i class="icon-circle"></i>
                        </div>
                    </li>
                    
                    <li class="bonusPay-li clearfix" data-id="0">
                        <label class="bonusPay-left left">
                            <img src="${imagePath}/wx/o2o-shop/weixin.png" class="bonusPay-img">
                            <span class="bonusPay-words">微信支付</span>
                        </label>
                        <div class="bonusPay-right right">
                            <i class="icon-circle"></i>
                        </div>
                    </li>                   
                    
                  <%-- <li class="bonusPay-li clearfix" data-id="2">
                        <label class="bonusPay-left left">
                            <img src="${imagePath}/wx/o2o-shop/yinglian.png" class="bonusPay-img">
                            <span class="bonusPay-words">银联在线支付</span>
                        </label>
                        <div class="bonusPay-right right">
                            <i class="icon-circle"></i>
                        </div>
                    </li> --%>
                    
                    <%-- <li class="bonusPay-li clearfix" data-id="5">
                        <label class="bonusPay-left left">
                            <img src="${imagePath}/wx/o2o-shop/授信额度支付.png" class="bonusPay-img">
                            <span class="bonusPay-words">余额支付</span>
                        </label>
                        <div class="bonusPay-right right">
                            <i class="icon-circle"></i>
                        </div>
                    </li>    --%>
                </ul>
            </div>
            <div class="btn-box">
                <button type="button" class="btn" id="saveToPay">确认赠送</button>
            </div>
            <div class="bonus-bottom" style="margin-bottom:2rem;">
                <a href="${ctx}/wx/trade/findMemberVoucherHistory" class="bonus-link">
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
        	document.title = "赠送代金券";
            $(".icon-circle").on("click",function(){
                var that = $(this);
                $(".icon-circle").removeClass('icon-circle-active');
                that.addClass("icon-circle-active");
                actMoney(that.parent().parent().attr("data-id"));
            });
            
            $("#saveToPay").on("click",function(){
                var num = $(".icon-circle-active").parents("li").attr("data-id");
                var bonusNum = $("#bonus-num").val();//分权数值，字符串类型
                if(bonusNum == "" || bonusNum == undefined){
                    layer.open({
                        content:'请填写赠送金额',
                        btn:'我知道了'
                    })
                }else{
                	var intBonus = parseInt(bonusNum);//转整型
                    var reg = /^(([1-9]\d*)|([0-9]+\.[0-9]{1,2}))$/;
                    //var mobile = /^13\d{9}$|15\d{9}$|17\d{9}$|18\d{9}$|13\d{1}-\d{4}-\d{4}$|15\d{1}-\d{4}-\d{4}$|17\d{1}-\d{4}-\d{4}$|18\d{1}-\d{4}-\d{4}$/; 
                    ////支持13 15 17 18开头,同时支持xxx-xxxx-xxxx格式手机输入
                    var phone=$(".profits-input").val();
                    if(!reg.test(bonusNum) || intBonus==0){
                        layer.open({
                            content:'赠送金额为非负非零数字,小数点后面最多两位',
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
                            	 if(num=="0"){
                            		 /*  layer.open({
                                         content:'微信支付功能暂未开放，敬请期待',
                                         btn:'我知道了'
                                     })   */   
                                     //微信支付
                                 	ajaxLoad("0");
                                 }else if(num=="2"){
                                     //银联
                                     /*  if($("#payMoney").val()<600){
                                    	 layer.open({
                                             content:'网银支付金额不能少于600元',
                                             btn:'我知道了'
                                         })
                                     }else{  */  
                                    	 ajaxLoadByH5("2") 
                                       /* }   */ 
                                	 /* layer.open({
                                         content:'网银支付功能暂未开放，敬请期待',
                                         btn:'我知道了'
                                     }) */ 
                                	
                                 }else if(num=="4"){
                                	 //授信额度
                                	 ajaxLoad("4");
                                 }else if(num=="6"){
                                	 //授信额度
                                	 ajaxLoad("6");
                                 }else if(num=="5"){
                                	 ajaxLoad("5");
                                }else{
                                	layer.open({
                                        content:'请选择支付方式',
                                        btn:'我知道了'
                                    })	
                                } 
                             }
                         }
                    }
                }
                
            })
        })
        
        function ajaxLoad(payType){       	
        	layer.open({
                content: '是否确认赠送',
                btn: ['确定', '取消'],
                yes: function(index){//点击去认证页面，index为该特定层的索引
                	$.ajax({
        	            url: '${ctx}/wx/trade/addMemberVonchersHistory',
        	            type: 'post',
        	            dataType: 'json',
        	            data: {
        	            	"amount":$("#bonus-num").val(),
        	            	"mobile":$(".profits-input").val(),
        	            	"payType":payType
        	            	},
        	            success:function(data){
        	            	if(data.code==200){
                        		if(payType==0){
        	            			//window.location.href="https://pay.swiftpass.cn/pay/jspay?token_id="+data.result;
                        			//利楚  
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
                        				    // package: data.result.package_str, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
                        				    signType: "MD5", // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
                        				    paySign: data.result.paySign, // 支付签名
                        				    success: function (res) {
                        				        // 支付成功后的回调函数   
                        				    	if(res.errMsg == "chooseWXPay:ok" ) {
                           				    	 layer.open({
                           				                content: '支付成功',
                           				                btn: ['我知道了'],
                           				                yes: function(index){//点击去认证页面，index为该特定层的索引                  				                	
                           				                	window.location.href="${ctx}/wx/trade/findMemberVoucherHistory"
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
        	            		if(payType==4){
        	            			layer.open({
                                        content:data.msg,
                                        btn:['确定', '取消'],
                                        yes: function(index){
                                        	window.location.href="${ctx}/wx/trade/findMemberVoucherHistory"
                                        },
                                        no:function(index){
                                            layer.close(index);
                                        }
                                   });
        	            		}
        	            		if(payType=="5"){
        	            			layer.open({
                                        content:data.msg,
                                        btn:['确定', '取消'],
                                        yes: function(index){
                                        	window.location.href="${ctx}/wx/trade/findMemberVoucherHistory"
                                        },
                                        no:function(index){
                                            layer.close(index);
                                        }
                                   });
        	            		}
        	            		
        	            		if(payType=="6"){
        	            			layer.open({
                                        content:data.msg,
                                        btn:['确定', '取消'],
                                        yes: function(index){
                                        	window.location.href="${ctx}/wx/trade/findMemberVoucherHistory"
                                        },
                                        no:function(index){
                                            layer.close(index);
                                        }
                                   });
        	            		}
                  			}else{
                  				layer.open({
                                    content:data.msg,
                                    btn:['确定', '取消'],
                                    yes: function(index){
                                    	 layer.close(index);
                                    },
                                    no:function(index){
                                        layer.close(index);
                                    }
                               });
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
        
        function ajaxLoadByH5(payType){       	
        	layer.open({
                content: '是否确认录入',
                btn: ['确定', '取消'],
                yes: function(index){//点击去认证页面，index为该特定层的索引
                	$(".loading-middle").removeClass("hidden");
                    $(".btn").attr("disabled","disabled");
                	window.location.href="${ctx}/wx/trade/addBenefitByH5?payType="+payType+"&amount="+$("#bonus-num").val()+"&mobile="+$(".profits-input").val()
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
            var merchantType="${merchantType}";
            if(bonusNum != "" && bonusNum != undefined && reg.test(bonusNum) && intBonus!=0){
            	if(status=='4'){
            		$(".bonusPay-money").text(bonusNum);
            	}else{
            		alert(merchantType)
            		if(merchantType==1){
            			var payMoney=(bonusNum*0.1).toFixed(2)-(bonusNum*0.1*0.03).toFixed(2)
            			$(".bonusPay-money").text(payMoney);
            		}else if(merchantType==2){
            			var payMoney=(bonusNum*0.1).toFixed(2)-(bonusNum*0.1*0.05).toFixed(2)
            			$(".bonusPay-money").text(payMoney);
            		}else if(merchantType==3){
            			var payMoney=(bonusNum*0.1).toFixed(2)-(bonusNum*0.1*0.08).toFixed(2)
            			$(".bonusPay-money").text(payMoney);
            		}else{
            			$(".bonusPay-money").text((bonusNum*0.1).toFixed(2));
            		}
            		
            	}
            	 $("#ninePay").val(bonusNum)
            	 $("#payMoney").val((bonusNum*0.1).toFixed(2))
            }
        }
    </script>
</body>
</html>