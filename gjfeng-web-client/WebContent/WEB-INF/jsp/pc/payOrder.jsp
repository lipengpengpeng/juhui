<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <title>支付</title>
  <link rel="stylesheet" type="text/css" href="${commonpc}/css/iconfont.css" />
  <link rel="stylesheet" href="${commonpc}/css/style.css?ver=1.0" type="text/css" media="all" charset="utf-8" />
</head>

<body>
  <main class="pay-wrapper main-wrapper">
    <div class="clearfix tips1">
      <h3 class="fl">订单提交成功，请您尽快完成付款哦！</h3>
       <span class="fl blue-color">订单号：${orderSn}</span> 
    </div>
    <div class="clearfix tips2">
      <p class="fl">您的订单我们将在付款完成后的48小时内发出 请耐心等待</p>
      <p class="fl blue-color toggle ">
        <span>订单详情</span>
        <span class="iconfont icon-arrowup" style="font-size: 12px;"></span>
      </p>
      <div class="order-detail fl " style="display: block;">
        <h4>收货信息：</h4>
        <p>收货地址：<span class="mgr-20" id="addess_con">${memberDefAddress.result.consigneeName }</span><span class="mgr-20" id="addres_detail">${memberDefAddress.result.proviceId.province }${memberDefAddress.result.cityId.city }${memberDefAddress.result.areaId.area }${memberDefAddress.result.townId.townName }${memberDefAddress.result.addressDetail }</span><span class="mgr-20" id="addres_mobile">${memberDefAddress.result.mobile }</span><span class="mgr-20 toAddress" style="color: red">更换收货地址</span></p>
        <h4>物流信息：</h4>
        <p>
           <c:if test="${logist==0 }">快递费：<span class="mgr-20" id="posInfo">￥${pos}</span></c:if>
           <c:if test="${logist==1 }">物流：<span class="mgr-20">到付</span></c:if>
        </p>
        
        <h4>订单信息：</h4>
        <c:forEach var="goodInfo" items="${goodsVos}">

          <p>
             <span>${goodInfo.goodsName }*${ goodInfo.goodsNum}</span>
             <c:if test="${merchantType==1}">
                <span class="mgr-20">￥${goodInfo.standardPrice}</span>
             </c:if>
             <c:if test="${merchantType==2}">
                <span class="mgr-20">￥${goodInfo.honourPrice}</span>
             </c:if>
          </p>
        </c:forEach>
 
      </div>
    </div>
    
    
    <form action="${ctx}/pc/merchant/add" class="pay-form" id="order-add-form" method="post">
      <div class="tips3">还需网银支付：
            <c:if test="${merchantType==1}">
               <span class="danger-color" >￥${standardTotalAmount+pointNiceAmount }</span></div>
            </c:if>
            <c:if test="${merchantType==2}">
               <span class="danger-color" >￥${honourTotalAmount+pointNiceAmount}</span></div>
            </c:if>
      <ul class="clearfix">
        <li class="pay-item fl mgr-20">
          <label for="yinlian">
            <input type="radio" id="yinlian"  checked="checked" class="fl" name="pay" data-value="1">
            <img src="http://statics.76sd.com/static/b2bpc/image/pay.png" alt="">
          </label>
        </li>
        <li class="pay-item fl">
          <label for="zhifubao">
            <input type="radio" id="zhifubao"  class="fl" data-value="2" name="pay">
            <img src="http://statics.76sd.com/static/b2bpc/image/pay4.jpg" alt="">
          </label>
        </li>
      </ul>
                       
      <c:forEach var="bean" items="${orderAddVos}" varStatus="status">
		   <c:if test="${goodSource==1}">
		     <input type="hidden" name="orderAddVos[${status.count-1}].goodsId" value="${proId}"/>
		   </c:if>
		   <c:if test="${goodSource==0||goodSource==2||goodSource==5}">
		     <input type="hidden" name="orderAddVos[${status.count-1}].goodsId" class="goodIds" value="${bean.goodsId}"/>
		   </c:if>
			
			<input type="hidden" name="orderAddVos[${status.count-1}].goodsAttrStockId" value="${bean.goodsAttrStockId}"/>
			<input type="hidden" name="orderAddVos[${status.count-1}].goodsAttrIds" value="${bean.goodsAttrIds}"/>
			<input type="hidden" name="orderAddVos[${status.count-1}].goodsAttr" value="${bean.goodsAttr}"/>
			<input type="hidden" name="orderAddVos[${status.count-1}].goodsNum" class="goodNums" value="${bean.goodsNum}"/>
		</c:forEach>
		<input type="hidden" class="payType-add" name="payType" value=""/>
		<input type="hidden" class="remark-add" name="remark" value=""/>
		<input type="hidden" class="couponsId-add" name="couponsId" value="0"/>
		<input type="hidden" class="orderAddressId-add" name="orderAddressId" value="${memberDefAddress.result.id}"/>
		<input type="hidden" class="payPassword" name="payPassword" value=""/>
		<input type="hidden" class="logist" name="logist" value="${logist}"/>
		<input type="hidden" id="commission" name="commissionType" value="1"/>
		<input type="hidden" class="pointNiceMoney" name="pointNiceMoney" value="${pointNiceAmount}"/>
		<input type="hidden" class="orderSn" name="orderSn" value="${orderSn}"/>
		<input type="hidden" class="customerSn" name="customerSn" value="${customerSn}"/>
		<c:if test="${logist==0}">
		   <input type="hidden" class="postage" name="postage" value="${pos}"/>
		</c:if>
		<c:if test="${logist==1}">
		   <input type="hidden" class="postage" name="postage" value="0"/>
		</c:if>
		
      <div>
      	<button class="pay-btn">立即支付</button>
      </div>
    </form>
               
  </main>
  <script src="${commonpc}/js/jquery-1.11.0.min.js"></script>
  <script>
  	$(function(){
  		$(".toggle").click(function(){
  		var t = $(this).find('.iconfont'),
  		p = $(this).siblings('.order-detail');
  			if($(this).hasClass('open')){
  				t.removeClass('icon-arrowdown').addClass('icon-arrowup');
  				p.fadeOut('400');
  			}
  			else {
  				t.removeClass('icon-arrowup').addClass('icon-arrowdown');
  				p.fadeIn(400);
  			}
  		$(this).toggleClass('open');
  		});
  	});
  	
  	var goodSource="${goodSource}";
  	//跳转到收货地址
  	$(".toAddress").click(function(){
  		
  		window.location.href="${ctx}/pc/merchant/toFind?type=0&goodSource="+goodSource
  	})
  	
  	//异步获取收货地址
  	
  	var addressId=sessionStorage.getItem("addreId")
    
  	if(addressId!=undefined&&addressId!=null){				
		 $.ajax({
			 url:"${ctx}/wx/order/getMemAddreById",
			 method:"GET",
			 data:{
				 orderAddressId:addressId,
				 goodSource:goodSource,
			 },
			 success:function(data){
				 $("#addess_con").html(data.result.consigneeName)
				   var str=data.result.proviceId.province
			       if(data.result.cityId){
			         str+=""+data.result.cityId.city
			       }
			       if(data.result.areaId){
		              str+=""+data.result.areaId.area
		           }
			       if(data.result.townId){
			              str+=""+data.result.townId.townName
			        }
			       str+=data.result.addressDetail	
				 $("#addres_detail").html(str)
				 $("#addres_mobile").html(data.result.mobile)
				 $(".orderAddressId-add").val(data.result.id)
				 sessionStorage.removeItem("addreId")
				  if(goodSource==5){
					  getPosMoney()
				  }
			 }
		 }) 
	 }
  	
  	
  	 //获取邮费
	
	 function getPosMoney(){
		 //获取商品id
		 var goodIds="";
		 $(".goodIds").each(function(){
			  goodIds=$(this).val()+",";
		    })
		 goodIds=goodIds.substring(0,goodIds.length-1);
		 
		 var goodNums=""
		 $(".goodNums").each(function(){
			 goodNums=$(this).val()+",";
		 })
		 goodNums=goodNums.substring(0,goodNums.length-1)
		
		 $.ajax({
			 url:"${ctx}/wx/order/findOrderPos",
			 method:"get",
			 data:{
				 goodIds:goodIds,
				 goodNums:goodNums,
				 addsId:addressId
			 },
			 success:function(data){
				 var totMoney="${totalAmount}";
				 var merchantType="${merchantType}"
				 var isWholesale="${isWholesale}"				
				 var pos="${pos}"								
				 $(".orderSn").val(data.result.orderSn)
				 $(".customerSn").val(data.result.customerSn);	
				 $("#posInfo").html("￥"+data.result.pos)
				 if(isWholesale==1){
					 if(merchantType==1){
						 var standardTotalAmount="${standardTotalAmount}" 
						 standardTotalAmount=parseFloat(standardTotalAmount)+parseFloat(data.result.pos);
						 $(".danger-color").html(standardTotalAmount);
					 }else if(merchantType==2){
						 var honourTotalAmount="${honourTotalAmount}"
							 honourTotalAmount=parseFloat(honourTotalAmount)+parseFloat(data.result.pos);
						$(".danger-color").html(honourTotalAmount);
					 }							 
				 }
												
			 }
		 })
		 
     }
  	 
  	 //点击支付
  	 $(".pay-btn").click(function(){
  		 var payType=$("input:radio:checked").attr("data-value")
  		 $(".payType-add").val(payType)
  		$("#order-add-form").submit();
  	 })
  	 
  	
  </script>
</body>

</html>