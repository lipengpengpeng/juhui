<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
    <div class="container">
        <form class="buy-bonus-form">
            <div class="bonus-wrap">
                <div class="bonus-item clearfix">
                    <label class="bonus-label left">消费总额：</label>
                    <input type="number" name="" placeholder="输入 消费金额" class="bonus-input right" id="bonus-num">
                </div>
            </div>
            <div class="bonus-pay">
                <div class="bonusPay-top clearfix">
                    <label class="bonusPay-label left">应付金额</label>
                    <div class="bonusPay-money right">0</div>
                </div>
                <!-- <ul class="bonusPay-ul">
                    <li class="bonusPay-li clearfix" data-id="1">
                        <label class="bonusPay-left left">
                            <img src="images/weixin.png" class="bonusPay-img">
                            <span class="bonusPay-words">微信支付</span>
                        </label>
                        <div class="bonusPay-right right">
                            <i class="icon-circle"></i>
                        </div>
                    </li>
                    <li class="bonusPay-li clearfix" data-id="2">
                        <label class="bonusPay-left left">
                            <img src="images/yinglian.png" class="bonusPay-img">
                            <span class="bonusPay-words">银联在线支付</span>
                        </label>
                        <div class="bonusPay-right right">
                            <i class="icon-circle"></i>
                        </div>
                    </li>
                </ul> -->
            </div>
            <div class="btn-box">
                <button type="button" class="btn" id="saveToPay">立即支付</button>
            </div>
            <!-- <div class="bonus-bottom">
                <a href="buyBonus-history.html" class="bonus-link">
                    查看购买分红权记录<i class="main-sprite icon-more"></i>
                </a>
            </div> -->
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
                if(bonusNum == "" ||　bonusNum == undefined){
                    layer.open({
                        content:'消费金额不能为空',
                        btn:'我知道了'
                    })
                }else{
                	alert(bonusNum)
                    $(".bonusPay-money").html(bonusNum)
                $.ajax({
        		url:"${ctx}/wx/store/payStoreOrder",
        		method:"GET",
        		data:{        			
        			payType:1,
        			couponsId:"${storeId}",
        			remark:bonusNum
        		},
        		success:function(data){
        			if(data.code==200){        			           			
            			window.location.href="https://pay.swiftpass.cn/pay/jspay?token_id="+data.result;
        		   }else{
        			   layer.open({
                           content:data.msg,
                           btn:'我知道了'
                       })
        		   }
        		}
        	})
                }
                
            })
        })
    </script>
</body>
</html>