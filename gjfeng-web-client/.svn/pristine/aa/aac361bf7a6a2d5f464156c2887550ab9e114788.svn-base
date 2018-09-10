<%@ include file="/common/h5pay-top.jsp" %>


<body> 
<div class="renzheng">
	<div style="margin-top: 15px">

	<FORM  method="POST" action="https://cashier.ronghexx.com/h5pay/" id="cashierForm" name="cashierForm">
	       <input id="svcName" name="svcName" type="hidden"  value="${obj.svcName}" >       
           <input id="merId" name="merId" type="hidden"  value="${obj.merId}" >                         
           <input  name="merchOrderId"   type="hidden" value="${obj.merchOrderId}">            
           <input  name="mer_cust_id"   type="hidden" value="${obj.mer_cust_id}">
           <input  name="ccy"   type="hidden" value="${obj.ccy}">    
           <input  name="amt" type="hidden"     value="${obj.amt}">  
           <input  name="tranTime" type="hidden"   value="${obj.tranTime}">        
            <input   name="md5value" type="hidden" value="${obj.md5value}">               
            <input  name="merUrl"   type="hidden"    value="${obj.merUrl}">              
			<input  name="retUrl"  type="hidden" value="${obj.retUrl}">				
			<input  name="pName" type="hidden" value="${obj.pName}">				
			<input name="pCat" type="hidden" value="${obj.pCat}">            
			<input  name="pDesc" type="hidden" value="${obj.pDesc}">         
			<input name="merData" type="hidden" value="${obj.merData}">    
			<input  name="bankName" type="hidden" value="${obj.bankName}">				
			<input name="bankId" type="hidden" value="${obj.bankId}">                   	                        	
            <input  name="terminalType" type="hidden" value="${obj.terminalType}">			
			<input  name="terminalId" type="hidden" value="${obj.terminalId}">			
			<input  name="productType" type="hidden" value="${obj.productType}">
			<input  name="userIp" type="hidden" value="${obj.userIp}">
			<input  name="rcvName" type="hidden" value="${obj.rcvName}">
			<input  name="rcvMobile" type="hidden" value="${obj.rcvMobile}">
			<input  name="rcvAdress" type="hidden" value="${obj.rcvAdress}">
			<input  name="regMail" type="hidden" value="${obj.regMail}">
			<input  name="regTime" type="hidden" value="${obj.regTime}">
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