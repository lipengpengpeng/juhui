<%@ include file="/common/h5pay-top.jsp" %>


<body> 
<div class="renzheng">
	<div style="margin-top: 15px">

	<FORM  method="POST" action="https://cashier.ronghexx.com/h5pay/" id="cashierForm" name="cashierForm">

	       <input id="svcName" name="svcName" type="hidden"  value="${resultVo.result.svcName}" >       
           <input id="merId" name="merId" type="hidden"  value="${resultVo.result.merId}" >                         
           <input  name="merchOrderId"   type="hidden" value="${resultVo.result.merchOrderId}"> 
           <input  name="amt" type="hidden"     value="${resultVo.result.amt}"> 
           <input  name="ccy"   type="hidden" value="${resultVo.result.ccy}">           
           <input  name="tranTime" type="hidden"   value="${resultVo.result.tranTime}">             
           <input  name="tranChannel"   type="hidden" value="${resultVo.result.tranChannel}">
           <input  name="merUrl"   type="hidden"    value="${resultVo.result.merUrl}">  
           <input  name="retUrl"  type="hidden" value="${resultVo.result.retUrl}">
           <input name="merData" type="hidden" value="${resultVo.result.merData}">
           <input  name="pName" type="hidden" value="${resultVo.result.pName}">	
           <input name="pCat" type="hidden" value="${resultVo.result.pCat}">
           <input  name="pDesc" type="hidden" value="${resultVo.result.pDesc}">                   
           <input   name="tranType" type="hidden" value="${resultVo.result.tranType}">                                      
		   <input  name="merUserId"  type="hidden" value="${resultVo.result.merUserId}">				
		   <input  name="terminalType" type="hidden" value="${resultVo.result.terminalType}">			
		   <input  name="terminalId" type="hidden" value="${resultVo.result.terminalId}">  
		   <input  name="productType" type="hidden" value="${resultVo.result.productType}">
		   <input  name="userIp" type="hidden" value="${resultVo.result.userIp}">				         
		   <input  name="md5value" type="hidden" value="${resultVo.result.md5value}">         
		   <input  name="mer_cust_id" type="hidden" value="${resultVo.result.mer_cust_id}"> 
		   <input  name="rcvMobile" type="hidden" value="${resultVo.result.rcvMobile}">
		   <input  name="rcvName" type="hidden" value="${resultVo.result.rcvName}"> 
		   <input  name="merData" type="hidden" value="${resultVo.result.merData}">  
		   <input  name="rcvAdress" type="hidden" value="${resultVo.result.rcvAdress}">
		   <input  name="regMail" type="hidden" value="${resultVo.result.regMail}">
		   <input  name="regTime" type="hidden" value="${resultVo.result.regTime}">   
            <input type="submit"  value="去支付" style="opacity:0;" id="btn-submit">
         </FORM>
	</div>  
</div>

</body>
</html>
<script>
$(function(){
	/* function sub(){
	    $("#cashierForm").submit();
	 }
	sub(); */
	$("#btn-submit").click();
})
</script> 

<!-- <script>
  $("form").attr("action","cashier.ronghexx.com/h5pay/banks")
  alert( $("form").attr("action"))
  $(".img-left").each(function(){
	  var img=$(this).attr("src")
	  img=img.replace(/../,"https://cashier.ronghexx.com")
	 $(this).attr("src",img)
  })
</script> -->