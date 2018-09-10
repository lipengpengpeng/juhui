<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
    <div class="container">
        <form class="buy-bonus-form">
            <div class="bonus-wrap">
                <div class="bonus-item clearfix">
                    <label class="bonus-label left">分红权数：</label>
                    <input type="text" name="" placeholder="请输入您要购买的分红权数" class="bonus-input right" id="bonus-num">
                </div>
            </div>
            <div class="bonus-pay">
                <div class="bonusPay-top clearfix">
                    <label class="bonusPay-label left">应付金额</label>
                    <div class="bonusPay-money right">0.00</div>
                </div>
                <ul class="bonusPay-ul">
                    <li class="bonusPay-li clearfix" data-id="1">
                        <label class="bonusPay-left left">
                            <img src="${imagePath}/wx/o2o-shop/weixin.png" class="bonusPay-img">
                            <span class="bonusPay-words">微信支付</span>
                        </label>
                        <div class="bonusPay-right right">
                            <i class="icon-circle icon-circle-active"></i>
                        </div>
                    </li>
                    <!-- <li class="bonusPay-li clearfix" data-id="2">
                        <label class="bonusPay-left left">
                            <img src="${imagePath}/wx/o2o-shop/yinglian.png" class="bonusPay-img">
                            <span class="bonusPay-words">银联在线支付</span>
                        </label>
                        <div class="bonusPay-right right">
                            <i class="icon-circle"></i>
                        </div>
                    </li> -->
                </ul>
            </div>
            <div class="btn-box">
                <button type="button" class="btn" id="saveToPay">确认购买</button>
            </div>
            <div class="bonus-bottom">
                <a href="${ctx}/wx/trade/buyDiviHistory" class="bonus-link">
                    查看购买分红权记录<i class="main-sprite icon-more"></i>
                </a>
            </div>
        </form>
    </div>
    <script type="text/javascript">
        $(function(){
            $(".icon-circle").on("click",function(){
                var that = $(this);
                $(".icon-circle").removeClass('icon-circle-active');
                that.addClass("icon-circle-active");
            });

            $("#saveToPay").on("touchend",function(){
                var num = $(".icon-circle-active").parents("li").attr("data-id");
                var bonusNum = $("#bonus-num").val();//分权数值，字符串类型
                var intBonus = parseInt(bonusNum);//转整型
                var reg = /^(([1-9]\d*)|([0-9]+\.[0-9]{1,2}))$/;
                if(bonusNum == "" || bonusNum == undefined){
                    layer.open({
                        content:'请填写分红权个数',
                        btn:'我知道了'
                    })
                }else{
                    if(!reg.test(bonusNum) || intBonus===0){
                        layer.open({
                            content:'分红权个数为非负非零数字,小数点后面最多两位',
                            btn:'我知道了'
                        })
                    }else{
                        if(num=="1"){
                            //微信支付
                        	ajaxLoad(bonusNum,"0","0");
                        }else if(num=="2"){
                            //银联
                        	ajaxLoad(bonusNum,"2","1");
                        }else{
                            layer.open({
                                content:'请选择支付方式',
                                btn:'我知道了'
                            })
                        }
                    }
                    
                }
                
            })
            
            $("#bonus-num").on("blur",function(){
            	var num = $(".icon-circle-active").parents("li").attr("data-id");
                var bonusNum = $("#bonus-num").val();//分权数值，字符串类型
                var intBonus = parseInt(bonusNum);//转整型
                var reg = /^(([1-9]\d*)|([0-9]+\.[0-9]{1,2}))$/;
                if(bonusNum == "" || bonusNum == undefined){
                	$(".bonusPay-money").text(0.00);
                }else{
                    if(!reg.test(bonusNum) || intBonus===0){
                    	$(".bonusPay-money").text(0.00);
                    }
                }
            	ajaxLoad(bonusNum,"0","0");
            });
        })
        
        function ajaxLoad(diviNum,payType,reqStatus){
	        $.ajax({
	            url: 'wx/trade/buyDivi',
	            type: 'post',
	            dataType: 'json',
	            data: {
	            	"diviNum":pageNo,
	            	"payType":payType,
	            	"reqStatus":reqStatus
	            	},
	            success:function(data){
	               if(data.code==200){
	            	   if(reqStatus=='0'){
	            		   $(".bonusPay-money").text(data.result);
	            	   }else if(reqStatus=='1'){
	            		   if(payType=='0'){
	            			   //微信支付
	            		   }else if(payType=='1'){
	            			   //支付宝支付
	            		   }else if(payType=='2'){
	            			   //银联支付
	            		   }
	            	   }
	               }else{
	            	   alert(data.msg);
	               }
	            },
	            error:function(){
					alert("网络异常");
	            }
	        })
       }
    </script>
</body>
</html>