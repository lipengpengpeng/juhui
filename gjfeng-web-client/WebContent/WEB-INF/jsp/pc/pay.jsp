<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>pay</title>
    <style>
        #all{width: 900px;height: 800px;margin-left:500px;margin-top: 100px }
    	.clear {clear:both}
		.clearfix:after { content:"."; display:block; height:0; clear:both; visibility:hidden}
		.clearfix { display:inline-block}
		/* Hide from IE Mac */
		.clearfix {display:block;}
		/* End hide from IE Mac */ /* end of clearfix */
      	.logo{ width: 140px; height: 130px; }
      	.logo img{ width: 100%; max-width: 100% }
      	.pay-box{ width: 100%; margin: 0 auto; }
      	.pay-order{ background: #eee; padding: 15px 30px; border-top: 1px solid #ddd; border-bottom: 1px solid #ddd;}
      	.pay-order .order-list{ float: left; }
      	.pay-order span{ float: right; margin-top: 30px;}
      	.pay-order span i{ font-weight: bold; font-style: normal; font-size: 18px;}
      	.pay-info{ border: 1px solid #ddd; padding: 30px 20px;}
      	.pay-info span img{ width: 100%; max-width: 100%; margin-bottom: 10px;}
      	.pay-info span{ display: inline-block; vertical-align: top; margin-left: 30px;}
      	.pay-info span:first-child{ width: 143px; height: 43px;}
      	.pay-info .scan{ width: 171px; height: 230px;}
      	.pay-way h3{ margin: 50px 0 -1px 0;}
      	.pay-way h3 span{ display: inline-block; vertical-align: top; width: 120px; height: 30px; line-height: 30px; border-top: 1px solid #ddd; border-left: 1px solid #ddd; border-right: 1px solid #ddd; border-bottom: 1px solid #fff; text-align: center; position: relative; font-size: 14px; border-top-left-radius: 5px; border-top-right-radius: 5px;}
      	.pay-way h3 span i{ position: absolute; top: -25px; left: 35px; z-index: 2; width: 60px; height: 30px; background: url(recommend-icon.png);}
    </style>
</head>
<script src="${commonpc}/js/jquery-1.11.0.min.js"></script>
<body id="all">
    <div class="pay-box">
	    <div class="logo">
	    	<img src="${commonpc}/img/green-logo.png">
	    </div>
	    <div class="pay-order clearfix">
	    	<div class="order-list">
	    	    <input id="orderNum" type="hidden" value="${orderInfo.orderSn}"/>
	    		<p>订单编号：${ orderInfo.orderSn}</p>
	    		<%-- <p>购买提示：${ orderInfo.body}</p> --%>
	    	</div>
	    	<span>应付金额￥<i>${orderInfo.offlinePayAmount}元</i></span>
	    </div>
	    <div class="pay-way">
	    	<h3 class="clearfix"><span><i class="active"></i>微信支付</span></h3>
	    	<div class="pay-info">
	    		<span><img src="${commonpc}/img/WePayLogo.png"></span>
	    		<span class="scan" style="margin-left:200px;">
	    			<img src="${payInfo}">
	    			<img src="${commonpc}/img/state-word.png">
	    		</span>
	    	</div>
	    </div>
    </div>
</body>

</html>