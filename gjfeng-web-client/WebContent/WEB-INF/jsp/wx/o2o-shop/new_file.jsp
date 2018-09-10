<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
	<style>
	.backdrop{
		background-size:cover;
		background-image:url(../common/image/wx/o2o-shop/background.jpg);
		background-color:rgba(67, 198, 252, 1);
		text-align:center;
	}
	
	.all{ 
		margin:0px auto;  
		width:100%;
		height: 20px;
	}
	.apptitle{
		margin:50px auto;
		color: #fff;
	}	
	.imglogo{
		margin:0px auto;
		width: 70%;

	}
	.imgcode{
		margin:0px auto;
		width: 45%;
	}
	.client {
	    background: #fff;
	    color: #000;
	    font-size: 18px;
	    line-height: 40px;
	    width: 44%;
	    display: block;
	    border-radius: 6px;
	    -webkit-border-radius: 6px;
	    -moz-border-radius: 6px;
	    margin: 0 3%;
	    cursor: default;
	    float: left;
		text-decoration:none
	}
	</style>
	<body class="backdrop">
		<div class="all">
			<!--标题部分-->
			<div class='apptitle'>
				  <!--  <img class='imglogo' src="${imagePath}/wx/o2o-shop/logo_1.png"  alt="" />-->
			</div>
			<!--微信二维码部分-->
			<div class='apptitle'>
			   <c:if test="${not empty member.imgQrUrl}">
			      <img class='imgcode' src="${member.imgQrUrl}"  alt="" />
			   </c:if>
			   <c:if test="${empty member.imgQrUrl}">
			      <img class='imgcode' src="${imagePath}/wx/o2o-shop/"  alt="" />
			   </c:if>
				
				<h5 style="margin-top: 15px;font-size: 16px">微信识别二维码即可关注微信公众号</h5>
			</div>
			<!--下载按钮-->
			<div class='apptitle'>
				<a class="client" onclick="adDouwLaod()">下载安卓客户端</a>
				<a class="client" href="#">下载苹果客户端</a>
			</div>
		</div>
		<div class="guidePage" style="display:none;">
		   <img alt="" src="${imagePath}/wx/o2o-shop/guide.png">
		</div>
	</body>
</html>
<script type="text/javascript">
   var nvatTpye="0";
   //app浏览器
   if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)||/(Android)/i.test(navigator.userAgent)) {
	   nvatTpye="1"
   } 
  
   //微信浏览器
   if(/MicroMessenger/i.test(navigator.userAgent)){
	  nvatTpye="0"
   } 
  
   if(nvatTpye=="0"){//如果是微信浏览器
	   function adDouwLaod(){		  
		     $(".all").css("display","none");
		     $(".guidePage").css("display","block")
	    }  
   }else{//如果是在app浏览器	  	   
	   function adDouwLaod(){
		   window.location.href="https://pan.baidu.com/s/1ERkj4kqlXYwyW4lS06ULzA"
	    }	    
   }
</script>

