<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-shop-top.jsp" %>
<style type="text/css">
	.common-Img a{width: 100%;height:100%;}
	.common-Img img{width: 100%;height: 3.4rem;}
		.lucky-item{
			width: 100%;
		}
		.lucky-item img.longImg{
			height: 5rem;
		}			
</style>
<body>
	<c:if test="${not empty indexAds.result}">
		<div class="swiper-container" style="height: 6rem">
		    <div class="swiper-wrapper">
			<c:if test="${indexType==0}">
			    <c:forEach var="bean" items="${indexAds.result}" varStatus="status">
		    	<c:if test="${not empty bean.address}">
		    		<a href="${bean.address}" class="swiper-slide"><img src="${bean.photo}" style="height:6rem"></a>
		    	</c:if>
		    	<c:if test="${empty bean.address}">
		    		<a href="javascript:void(0)" class="swiper-slide"><img src="${bean.photo}" style="height:6rem"></a>
		    	</c:if>
		     </c:forEach>
			  <a onclick="goMerchantProcurement()" class="swiper-slide"><img src="${imagePath}/wx/online-shop/商家尊享.jpg" style="height:6rem"></a>
			
			</c:if>
			
			<c:if test="${indexType==1}">			
			   <a onclick="goMerchantProcurement()" class="swiper-slide"><img src="${imagePath}/wx/online-shop/商家尊享.jpg" style="height:6rem"></a>
			    <c:forEach var="bean" items="${indexAds.result}" varStatus="status">
		    	<c:if test="${not empty bean.address}">
		    		<a href="${bean.address}" class="swiper-slide"><img src="${bean.photo}" style="height:6rem"></a>
		    	</c:if>
		    	<c:if test="${empty bean.address}">
		    		<a href="javascript:void(0)" class="swiper-slide"><img src="${bean.photo}" style="height:6rem"></a>
		    	</c:if>
		     </c:forEach>			  			
			</c:if>		   
		    </div>
		    <div class="pagination"></div>
		</div>
	</c:if>
	<div><img src="${imagePath}/wx/online-shop/fast.jpg"/></div>
	<section class="content">
	<c:if test="${not empty indexColumns.result}">		
		</c:if>
		<div class="con-snacks com-bg">
			<h4 class="today-title clearfix"> 
				<!-- <p class="today-icon"><img src="uploadimg/index-pollex.png"><span>待修改标题</span></p> -->
			</h4> 
			<ul class="common-ul common-Img clearfix">
			
			     <li class="lucky-item" style="float:right;width: 50%">
					<a onclick="goTianMao()">
						<img style="width: 100%;height: 1.5rem;margin-left:5px" src="${imagePath}/wx/online-shop/天猫.jpg">
					</a>
				</li>
			     <li class="lucky-item" style="width: 50%;float:right;">
					<a href="${ctx}/wx/product/goJDProductListPage">
						<img style="width: 100%;height: 1.5rem;margin-right:5px" src="${imagePath}/wx/o2o-shop/微信图片_20171226135827.png">
					</a>
				</li>								
			</ul>			
			<ul class="common-ul common-Img clearfix" style="margin-top: 15px">
			     <li class="lucky-item" style="float:left:;width: 50%">
					<!--  <a href="${ctx}/wx/index/jdIndex?conlumId=1401">-->
					<a onclick="goTianMao()">
						<img style="width: 100%;height: 1.5rem;margin-right:5px" src="${imagePath}/wx/o2o-shop/微信图片_20171120095658.jpg">
					</a>
				</li>
				<li class="lucky-item" style="width: 50%;float:left">
					<a href="${ctx}/wx/product/goTianTianYiGo">
						<img style="width: 100%;height: 1.5rem;margin-left:5px" src="${imagePath}/wx/online-shop/ttyg-lo.jpg">
					</a>
				</li>				
			</ul>						
		</div>					
	</section>
	
	<%@include file="/common/wx-footer.jsp" %>
	<div class="goTop xicon"></div>
	<script src="${jsPath}/wx/online-shop/idangerous.swiper.min.js"></script>
	<script src="${jsPath}/wx/online-shop/jquery.font.js"></script>
	<script type="text/javascript">
   		$(function(){
   			document.title = "网上商城";
   			$(".icon-nav-3").siblings().removeClass("nav-link-active");
   			$(".icon-nav-3").addClass("nav-link-active");
   			$(".icon-nav-3").parent().siblings().removeClass("nav-link-active");
   			$(".icon-nav-3").parent().addClass("nav-link-active");
   			
   			var mySwiper = new Swiper('.swiper-container',{
 		  	autoplay:60000,//每3秒走一次
     	  	autoplayDisableOnInteraction : false,//轮播
   		  	loop:true,//是否无缝
   		 	pagination: '.pagination',
   		  	mousewheelControl:true,//值为true时，能够使用鼠标滑轮滑动swiper
   		  	paginationClickable: true,//点击按钮
   		  	calculateHeight:true,//Swiper根据slides内容计算容器高度。
   		})
	   		//今日推荐盒子的高度适配(有bug)
			function giveHeight(){
				var b = $('.today-i');
					for(i=0;i< b.length;i++){
					    if(0==i%3){
					    	var h1 = b.eq(i).height();
					    	var h2 =  b.eq(i+1).height();
					    	var h3 =  b.eq(i+2).height();
					    	if(i < b.length){
						    	h = Math.max(h1,h2,h3);
						    	b.eq(i).height(h);
						    	b.eq(i+1).height(h);
						    	b.eq(i+2).height(h);
					    	}
					    }
					}
			}	
			giveHeight();
   		})
   		// 多行省略小插件 控制长度
		$(".today-i").fonts({
            fontNum: 12
        });
   		
   		function jd(){
   		  layer.open({
            content:'产品优化升级中,敬请期待。',
            btn:'我知道了'
           })  
   		}
   		
   		//跳转到天猫
   		function goTianMao(){
   			var id="${sessionScope.gjfMemberInfo.id}";
   			var token="${sessionScope.gjfMemberInfo.mobile}";
   			if(id==""&&token==""){
   				 layer.open({
   			         content:'请先登录',
   			         btn:'我知道了'
   			       })  
   			}else{
   				window.location.href="${ctx}/wx/product/goTianMaoProductListPage";
   			}
   		}
   		
   		//跳转到京东自营产品
   		function goJdProProduct(){
   			window.location.href="${ctx}/wx/product/findProprietaryJdCategory";
   		}
   		
   		//跳转到商家采购
   		function goMerchantProcurement(){
   			window.location.href="${ctx}/wx/trade/goMerchantProcurementPage"  			 
   		}
   

        $("#close-popup").on("click",function(e){
            e.stopPropagation();
            $(".bg-cover").addClass("hidden");
            $(".popup-box").addClass("hidden");
            $(".popup-item").removeClass("into-sprite-active");
        })
   	    
   		
   		
   		
   		  		  		
   		
	</script>
</body>
</html>