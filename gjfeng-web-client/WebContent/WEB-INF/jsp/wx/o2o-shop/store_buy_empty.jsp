<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
    <div class="container">
      
    </div>
    <script type="text/javascript">  
    alert("进判断")
       
        var ua = window.navigator.userAgent.toLowerCase(); 
       //判断是不是微信
       if(ua.match(/MicroMessenger/i) == 'micromessenger'){
    	   alert("微信")
    	   window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=${appId}&redirect_uri=${url}&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect" 
        }else if(ua.match(/AlipayClient/i) == 'AlipayClient'){
        	alert("支付宝")
       	 window.location.href="${ctx}/wx/store/goInputMoneyPage?store=${storeId}"
        }else{
        	alert("其他")
       	 layer.open({
                content:'请使用微信或支付宝进行扫码',
                btn:'我知道了'
            }) 
        }       
  
    </script>
</body>
</html>