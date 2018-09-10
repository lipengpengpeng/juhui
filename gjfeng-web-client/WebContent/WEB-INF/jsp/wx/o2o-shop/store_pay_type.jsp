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
                
                   <li class="bonusPay-li clearfix" data-id="2">
                        <label class="bonusPay-left left">
                            <img src="${imagePath}/wx/o2o-shop/支付宝.png" class="bonusPay-img">
                            <span class="bonusPay-words">支付宝</span>
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
                </ul>
            </div>
            <div class="btn-box">
                <button type="button" class="btn" id="saveToPay">立即支付</button>
            </div>
            <%-- <div class="bonus-bottom" style="margin-bottom:2rem;">
                <a href="${ctx}/wx/trade/benefits" class="bonus-link">
                    查看销售录入记录<i class="main-sprite icon-more"></i>
                </a>
            </div> --%>
            <div class="loading-middle hidden">
              <img src="${imagePath}/wx/o2o-shop/load.gif" class="loadImg">
           </div>
        </form>
    </div>
    
  <div class="bg-cover hidden"></div>
<div class="popup-box hidden">
    <div class="popup-title">付款二维码</div>
    <div class="popup-cont clearfix" id="showImg">
        
    </div>    
    <i class="main-sprite icon-close abs-icon" id="close-popup"></i>
</div>
    
    <script type="text/javascript">
        $(function(){
        	document.title = "";
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
                        content:'请填写消费金额',
                        btn:'我知道了'
                    })
                }else{
                	//var intBonus = parseInt(bonusNum);//转整型
                    //var reg = /^(([1-9]\d*)|([0-9]+\.[0-9]{1,2}))$/;
                	var reg=/^([0-9])+(\.[0-9]+)?$/
                    var mobile = /^13\d{9}$|15\d{9}$|17\d{9}$|18\d{9}$|13\d{1}-\d{4}-\d{4}$|15\d{1}-\d{4}-\d{4}$|17\d{1}-\d{4}-\d{4}$|18\d{1}-\d{4}-\d{4}$/; 
                    ////支持13 15 17 18开头,同时支持xxx-xxxx-xxxx格式手机输入
                    var phone=$(".profits-input").val();
                    if(!reg.test(bonusNum)||bonusNum==0){
                        layer.open({
                            content:'消费金额为非负数',
                            btn:'我知道了'
                        })
                    }else{
                    	if(phone == "" || phone == undefined){
                            layer.open({
                                content:'请填写会员手机号码',
                                btn:'我知道了'
                         })
                        }else{
                        	 if(!mobile.test(phone)){
                                 layer.open({
                                     content:'请填写正确的会员手机号码',
                                     btn:'我知道了'
                                 })
                             }else{
                            	 if(num=="0"){                            		 
                                     //微信支付
                                 	ajaxLoad("1");
                                 }else if(num=="2"){
                                     //支付宝                                     
                                   ajaxLoad("2")                                
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
        
        
         $("#close-popup").on("click",function(e){
            e.stopPropagation();
            $(".bg-cover").addClass("hidden");
            $(".popup-box").addClass("hidden");           
           /*  $("#wallet-tab-1").addClass('wb-tab-active').siblings().removeClass('wb-tab-active'); */
            location.replace(location)      
        })
        
        function ajaxLoad(payType){
        	 $.ajax({
         		url:"${ctx}/wx/store/payStoreOrder",
         		method:"GET",
         		data:{ 
         			"orderAddVos[0].goodsId":"",
        			"orderAddVos[0].goodsAttrStockId":"",
        			"orderAddVos[0].goodsAttrIds":"",
        			"orderAddVos[0].goodsAttr":"",
        			"orderAddVos[0].goodsNum":1,
        			storeId:"${storeId}",
         			payType:payType,
         			couponsId:"${storeId}",
         			remark: $("#bonus-num").val(),
         			type:"0",
         			mobile:$("#phone").val()
         		},
         		success:function(data){
         			if(data.code==200){        			           			
         				$(".bg-cover").removeClass("hidden");
         	            $(".popup-box").removeClass("hidden");
         	            $("#showImg").html("<img style='margin-top:-30px' src='"+data.result+"'>")
         		   }else{
         			   layer.open({
                            content:data.msg,
                            btn:'我知道了'
                        })
         		   }
         		}
         	}) 
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
            if(bonusNum != "" && bonusNum != undefined ){
            	 $(".bonusPay-money").text(bonusNum);
            	
            	 $("#ninePay").val(bonusNum)          	
            }
        }
    </script>
</body>
</html>