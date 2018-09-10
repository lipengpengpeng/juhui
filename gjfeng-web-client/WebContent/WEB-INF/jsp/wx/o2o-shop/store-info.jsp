<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
    <div class="container">
        <div class="business-banner">
        	<c:if test="${not empty resultVo.result.storeBanner}">
        		<img src="${resultVo.result.storeBanner}" class="business-img" id="storeImg">
        	</c:if>
        	<c:if test="${empty resultVo.result.storeBanner}">
        		<img src="${imagePath}/wx/o2o-shop/business.png" class="business-img" id="img">
        	</c:if> 
        	<form action="${ctx}/wx/store/updateBanner" id="addBanner" method="POST" enctype="multipart/form-data">
        	<input type="file" id="doc" capture="camera" accept="image/*" name="file"  onchange="previewStoceImage(this,750,460)" class="business-upload">
        	</form>
            
            <span class="business-upload-cover">更换封面</span>
        </div>
        <div class="business-box">
            <div class="business-title clearfix">
                <div class="b-title-left left">${resultVo.result.storeName}<c:if test="${resultVo.result.isActivityStore==1}"><li class="recommend-tip" style="color:red;font-size: medium;">(vip)</li></c:if><c:if test="${resultVo.result.isActivityStore==0}"><li class="recommend-tip" style="color:red"></li></c:if></div>
                <div class="b-title-right right">${resultVo.result.storeDesccreditScore}
                	<c:if test="${resultVo.result.storeDesccreditScore==0}">
                         <i class="contMenu-icon icon-crown"></i>
                         <i class="contMenu-icon icon-crown"></i>
                         <i class="contMenu-icon icon-crown"></i>
                         <i class="contMenu-icon icon-crown"></i>
                         <i class="contMenu-icon icon-crown"></i>
                       </c:if>
                       <c:if test="${resultVo.result.storeDesccreditScore>0 && resultVo.result.storeDesccreditScore<1}">
                         <i class="contMenu-icon icon-crown icon-crown-middle"></i>
                         <i class="contMenu-icon icon-crown "></i>
                         <i class="contMenu-icon icon-crown "></i>
                         <i class="contMenu-icon icon-crown "></i>
                         <i class="contMenu-icon icon-crown "></i>
                       </c:if>
                       <c:if test="${resultVo.result.storeDesccreditScore==1}">
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown "></i>
                         <i class="contMenu-icon icon-crown "></i>
                         <i class="contMenu-icon icon-crown "></i>
                         <i class="contMenu-icon icon-crown "></i>
                       </c:if>
                        <c:if test="${resultVo.result.storeDesccreditScore>1 && resultVo.result.storeDesccreditScore<2}">
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-middle"></i>
                         <i class="contMenu-icon icon-crown "></i>
                         <i class="contMenu-icon icon-crown "></i>
                         <i class="contMenu-icon icon-crown "></i>
                       </c:if>
                        <c:if test="${resultVo.result.storeDesccreditScore==2}">
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown "></i>
                         <i class="contMenu-icon icon-crown "></i>
                         <i class="contMenu-icon icon-crown "></i>
                       </c:if>
                       
                        <c:if test="${resultVo.result.storeDesccreditScore>2 && resultVo.result.storeDesccreditScore<3}">
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-middle"></i>
                         <i class="contMenu-icon icon-crown "></i>
                         <i class="contMenu-icon icon-crown "></i>
                       </c:if>
                       
                       <c:if test="${resultVo.result.storeDesccreditScore==3}">
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown "></i>
                         <i class="contMenu-icon icon-crown "></i>
                       </c:if>
                       
                       <c:if test="${resultVo.result.storeDesccreditScore>3 && resultVo.result.storeDesccreditScore<4}">
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-middle"></i>
                         <i class="contMenu-icon icon-crown "></i>
                       </c:if>
                       
                           
                       <c:if test="${resultVo.result.storeDesccreditScore==4}">
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown "></i>
                       </c:if>
                       
                       <c:if test="${resultVo.result.storeDesccreditScore>4 && resultVo.result.storeDesccreditScore<5}">
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-middle"></i>
                       </c:if>
                       
                       <c:if test="${resultVo.result.storeDesccreditScore==5}">
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                       </c:if>
                </div>
            </div>
            <ul class="business-list">
                <li class="business-li clearfix">
                    <i class="main-sprite icon-address left"></i>
                    <div class="b-li-left left">
                        <span class="business-text">${resultVo.result.areaId.area}${resultVo.result.addressDetail}</span>
                    </div>
                    <div class="b-li-right right">
                        <i class="main-sprite icon-edit"></i>
                    </div>
                </li>
                <li class="business-li clearfix">
                    <i class="main-sprite icon-tel left"></i>
                    <div class="left">
                        <span class="business-text">${resultVo.result.sellerMobile}</span>
                    </div>
                </li>
                <li class="business-li clearfix">
                    <i class="" style="margin-left: -150px">店铺审核状态:</i>                   
                    <c:if test="${resultVo.result.storeStatus==1}">
                      <div class="left" style="margin-left: 100px">
                        <span class="business-text">审核通过</span>
                     </div>
                    </c:if>
                
                </li>
            </ul>
        </div>
        <div class="business-row">
            <a href="${ctx}/wx/store/intro" class="business-link clearfix">
                <span class="b-row-left left">店铺简介</span>
                <span class="right"><i class="my-icon icon-more"></i></span>
            </a>
        </div>
        <c:if test="${resultVo.result.storePro eq '0'}">
        <div class="business-menu clearfix">
            <a href="${ctx}/wx/product/my" class="b-m-link left">
                <i class="main-sprite icon-b-1"></i>
                <p class="b-menu-text">商品管理</p>
            </a>
            <a href="${ctx}/wx/order/findO2oOrderByPage?pageNo=1&pageSize=6&storeId=${resultVo.result.id}&status=7" class="b-m-link left">
                <i class="main-sprite icon-b-2"></i>
                <p class="b-menu-text">订单管理</p>
            </a>
            <a href="${ctx}/wx/product/toAdd" class="b-m-link left">
                <i class="main-sprite icon-b-3"></i>
                <p class="b-menu-text">发布商品</p>
            </a>
            <a href="${ctx}/wx/store/goInputMoneyPage?storeId=${resultVo.result.id}" class="b-m-link left">
                <i class="main-sprite icon-b-3"></i>
                <p class="b-menu-text">收款</p>
            </a>
        </div>
        </c:if>
    </div>
</body>

<script type="text/javascript">
	document.title = "店铺信息";
   //店铺banner图片上传
   function previewStoceImage(file,maxWidth,maxHeight,state){//图片上传预览
    var MAXWIDTH  = maxWidth;
    var MAXHEIGHT = maxHeight;
    var img = $(file).siblings("img");
    if (file.files && file.files[0]){
        img.attr("src","");
        img.onload = function(){
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            img.width  =  rect.width;
            img.height =  rect.height;
        }
        var reader = new FileReader();
        reader.onload = function(evt){
        	img.attr("src",evt.target.result);
        	
        	var str=evt.target.result.split(",")
        	/* $.ajax({
         	   url:"${ctx}/wx/store/updateBanner",
         	   method:"POST",
         	   data:{
         		   fileName:"1123.png",
         		   fileContent:str[1]
         	   },
         	   success:function(data){
         		   
         	   }
            }) */
        }
       
       
        reader.readAsDataURL(file.files[0]);
        if(img.hasClass('hidden')){
            img.removeClass('hidden')
        }
        if(img.hasClass("business-img")){
            var uploadImg = file.files[0];
            console.log(file.files[0]);      
        } 
        
        $("#addBanner").submit()
       
    }
}
   
   //跳转到修改店铺地址页面
   $(".icon-edit").click(function(){
	   window.location.href="${ctx}/wx/store/toUpdateAddressInfo"
   })
</script>
</html>