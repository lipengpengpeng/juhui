<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-shop-top.jsp" %>
<body style="background: #fff;">
	<section class="add-addressec">
		<%-- <form action="${ctx}/wx/address/newsAddress" method="post"> --%>
			<ul class="add-addresform">
				<li class="clearfix">
					<span>收货人</span>
					<input type="text" value="" name="consigneeName" id="consigneeName">
					<div style="margin-left: 165px;line-height: inherit;color: red; display:none" id="nameMsg">aaa</div>
				</li>
				<li class="clearfix">
					<span>手机号码</span>
					<input type="text" value="" name="mobile" id="mobile">
					<div style="margin-left: 165px;line-height: inherit;color: red;display:none" id="mobileMsg">aaa</div>
				</li>
				<li class="clearfix">
					<span>省</span>
					<select id="provice" name="proviceId">
						
					</select>
					
				</li>
				<li class="clearfix">
					<span>市</span>
					<select id="city" name="cityId">
						
					</select>
					
				</li>
				<li class="clearfix">
					<span>区</span>
					<select id="area" name="areaId">
						
					</select>
					
				</li>
				
				<c:if test="${goodSource==1||goodSource==5}">
				  <li class="clearfix">
					<span>镇</span>
					<select id="town" name="townId">
						
					</select>
					
				  </li>
				</c:if>
				
				<li class="clearfix">
					<span>详细地址</span>
					<textarea required name="addressDetail" id="addressDetail"></textarea>
					<div style="margin-left: 165px;line-height: inherit;color: red;display:none" id="detailMsg"></div>
				</li>
			</ul>
		<!-- </form> -->
	</section>
	<div class="add-mask"></div>
	<footer class="address-footer">
		<a id="newAddres" class="address-adda">保存</a>
	</footer>
</body>
 <script>
 		document.title = "收货地址添加";
 		
 		var goodSource="${goodSource}"
 		var source=""
 		if(goodSource=="2"){
 			goodSource=0
 		}
 		
    	//获取所有省份
    	  $.ajax({
    		   url:"${ctx}/wx/address/getAllProvince",
    		   method:"GET",
    		   data:{
    			   fatherId:"",
    			   addressType:1,
    			   goodSource:goodSource
    		   },
    		   success:function(data){
    			   var str=""
    			   for(var i=0;i<data.result.length;i++){
    				   if(i==0){
    					   str+="<option selected provinceId="+data.result[i].id+" value="+data.result[i].provinceId+">"+data.result[i].province+"</option>"
    				   }else{
    					   str+="<option provinceId="+data.result[i].id+" value="+data.result[i].provinceId+">"+data.result[i].province+"</option>"
    				   }
    				  
    			   }
   			     $("#provice").html(str)
   			      getcity($("#provice option:selected").val())
   			      
   			      $("#provice").change(function(){ 
   			    	getcity($("#provice option:selected").val())
    	               })
    		   }
    	  })
       
    	  
    	  //获取省份下的城市
    	    getcity()
    	    function getcity(proviceId){       		  
        		  $.ajax({
           		   url:"${ctx}/wx/address/getAllProvince",
           		   method:"GET",
           		   data:{
           			   fatherId:proviceId,
           			   addressType:2,
           			   goodSource:goodSource
           		   },
           		   success:function(data){
           			   var str=""          			
           			   if(data.result!=""){
           				  for(var i=0;i<data.result.length;i++){
              				   str+="<option cityId="+data.result[i].id+" value="+data.result[i].cityId+">"+data.result[i].city+"</option>"
              			   }
             			     $("#city").html(str)
 
             			     getarea($("#city option:selected").val())  
           			   }else{
           				   getarea($("#city option:selected").val()) 
           			   }
           			          			             			   
           		   }
           	     })      
    	  }
    	  
    	
    	  $("#city").change(function(){ 
			    	getcity($("#city option:selected").val())
			    	
			    	if($("#city option:selected").val()==29855||$("#city option:selected").val()==29890){
			    		
			    		getTown($("#city option:selected").val())
			    	}
	           })
    	  
    	  //获取城市下的区域
    	   
    	   
    	  function getarea(cityId){
    		
       		   $.ajax({
              		   url:"${ctx}/wx/address/getAllProvince",
              		   method:"GET",
              		   data:{
              			   fatherId:cityId,
              			   addressType:3,
              			   goodSource:goodSource
              		   },
              		   success:function(data){
              			   var str=""
              			   if(!data.result){
              				 $("#area").html("") 
              			   }else{
              				 for(var i=0;i<data.result.length;i++){
              				   str+="<option areaId="+data.result[i].id+" value="+data.result[i].areaId+">"+data.result[i].area+"</option>"
              			      }
             			     $("#area").html(str) 
             			     getTown($("#area option:selected").val())
              			   }    
             			 
              		   }
              	     }) 
       	 
    	 }
    	  
    	  $("#area").change(function(){ 
    		  getTown($("#area option:selected").val())
          })
    	  
    	  
    	  //获取区域下的镇
    	  function getTown(areaId){
    		  $.ajax({
         		   url:"${ctx}/wx/address/getAllProvince",
         		   method:"GET",
         		   data:{
         			   fatherId:areaId,
         			   addressType:4,
         			   goodSource:"${goodSource}"
         		   },
         		   success:function(data){
         			   var str=""
         			   if(!data.result){
         				 $("#town").html("") 
         			   }else{
         				 for(var i=0;i<data.result.length;i++){
         				   str+="<option townId="+data.result[i].id+" value="+data.result[i].townId+">"+data.result[i].townName+"</option>"
         			   }
        			     $("#town").html(str) 
         			   }    
        			 
         		   }
         	     }) 
    	  }
    	  
    	  
    	  //获取焦点事件
    	  $("#consigneeName").focus(function(){
    		  $("#nameMsg")[0].style.display = "none" 
    	  })
    	  
    	   $("#mobile").focus(function(){
    		  $("#mobileMsg")[0].style.display = "none" 
    	  })
    	     	     	  
    	    $("#addressDetail").focus(function(){
    		  $("#detailMsg")[0].style.display = "none" 
    	  })
    	  
    	  //失去焦点
    	   $("#consigneeName").blur(function(){
    		   if($("#consigneeName").val()==''){
    			   $("#nameMsg").html("用户名不能为空")
    			   $("#nameMsg")[0].style.display = "block"
    		   }
    	  })
    	  
    	   $("#mobile").blur(function(){
    		 //校验电话号码
    		   if($("#mobile").val()==''){
    			   $("#mobileMsg").html("电话号码不能为空")
    			   $("#mobileMsg")[0].style.display = "block"
    		   }else{
    			    var reg = new RegExp("^[1][34578][0-9]{9}$");
    				if (!reg.test($("#mobile").val())) {
    					$("#mobileMsg").html("输入的手机号码不正确")
    					$("#mobileMsg")[0].style.display = "block"
    				}
    		   }
    	  })
    	  
    	  
    	    $("#addressDetail").blur(function(){
    	    	 if($("#addressDetail").val()==''){
      			   $("#detailMsg").html("详细地址不能为空")
      			   $("#detailMsg")[0].style.display = "block"
      		   }
    	  })
    	  
    	  
    	  
    	  
    	   
    	   function vilade(){   		  
    		   //校验用户名
    		   if($("#consigneeName").val()==''){
    			   $("#nameMsg").html("用户名不能为空")
    			   $("#nameMsg")[0].style.display = "block"
    			   return false
    		   }else{
    			   $("#nameMsg").html("")
    			   $("#nameMsg")[0].style.display = "none"    			  
    		   }
    		   //校验电话号码
    		   if($("#mobile").val()==''){
    			   $("#mobileMsg").html("电话号码不能为空")
    			   $("#mobileMsg")[0].style.display = "block"
    			   return false
    		   }else{
    			    var reg = new RegExp("^[1][34578][0-9]{9}$");
    				if (!reg.test($("#mobile").val())) {
    					$("#mobileMsg").html("输入的手机号码不正确")
    					$("#mobileMsg")[0].style.display = "block"
    					return false
    				}else{
    					$("#mobileMsg").html("")
    					$("#mobileMsg")[0].style.display = "none"    					
    				}    				
    		   } 
    		   
    		     		    
    		    //校验详细地址
    		    if($("#addressDetail").val()==''){
    			   $("#detailMsg").html("详细地址不能为空")
    			   $("#detailMsg")[0].style.display = "block"
    			   return false
    		   }else{
    			   $("#detailMsg").html("")
    			   $("#detailMsg")[0].style.display = "none"    			  
    		   } 
    		        		    
    		   return true;
    	   }
    	   
    	
    	   //提交信息
    	   $("#newAddres").click(function(){
    		   var type="${type}"
    		   var townId=$("#town option:selected").attr("townId")
    		   if(vilade()){
    			   $.ajax({
               		   url:"${ctx}/wx/address/newsAddress",
               		   method:"POST",
               		   data:{
               			  consigneeName:$("#consigneeName").val(),
               			   mobile:$("#mobile").val(),
               			  proviceId:$("#provice option:selected").attr("provinceId"),
               			  cityId:$("#city option:selected").attr("cityId"),
               			  areaId:$("#area option:selected").attr("areaId"),
               			  addressDetail:$("#addressDetail").val(),
               			  consigneeSex:1,
               			  goodSource:"${goodSource}",
               			  townId:townId
               		   },
               		   success:function(data){
               			   if(data.code==200){
               				layer.open({
                                content: '添加成功',
                                btn: ['确定'],
                                yes: function(index){//点击去认证页面，index为该特定层的索引
                                	if(type==0){
                                		 location.href='${ctx}/wx/address/toFind?type=1&goodSource=${goodSource}';
                                	}else if(type==1){
                                		 location.href='${ctx}/wx/address/toFind?type=0&goodSource=${goodSource}';
                                	}else if(type==2){
                                		location.href='${ctx}/wx/address/toFind?type=2&goodSource=${goodSource}';
                                	}
                                   
                                    layer.close(index);
                                },
                               
                            });     
               			  }else{
               				layer.open({
                                content: data.msg,
                                btn: ['确定'],
                                yes: function(index){//点击去认证页面，index为该特定层的索引                                  
                                    layer.close(index);
                                },
                               
                            });   
               			  }
               			  
               		   }
               	     }) 
    		   }
    		 
    	   })
  
   
</script> 


</html>