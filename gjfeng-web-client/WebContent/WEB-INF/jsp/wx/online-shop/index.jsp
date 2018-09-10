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
		/* .today-i{
			display: -webkit-box;
			-webkit-line-clamp: 2;
			-webkit-box-orient: vertical;
			overflow: hidden;
		} */
		.imgDiv{
		   height: 2.5rem;
		   background-color: white;
		   position: relative;
		}
		.font-info{
		   height: 2rem;
		   width: 22%;
		   display: inline-block;
		      margin-top: 0.2rem;
		}
		.font-h-1{
		  font-family:Georgia;
		  padding-left: 0.5rem;
		  padding-top: 0.5rem;
		  color: red;
		  font-size: 0.4rem;
		  font-style:italic;
		  font-weight:bold;
		}
		.font-h-2{
		  font-family:Georgia;
		  padding-left: 0.5rem;
		  padding-top: 0.3rem;
		  color: red;
		  font-size: 0.4rem;
		  font-style:italic;
		  font-weight:bold;
		}
		.newlist{
		   display: inline-block;
		   margin-top: 0.8rem;
		}
		.listInfo{		
		  margin-bottom: 0.3rem;
         /* margin-top: 0.4rem;*/
		}
		.keywords{
		   font-size: 0.3rem;
		   color:red;
		   border: red solid 0.01rem; 
		   border-radius: 0.3rem;
		   padding: 0.08rem 0.4rem;
		}
		.newsTitle{
		   margin-left: 0.2rem;
           font-size: 0.3rem;
           color: black;
		}
		.moreNews{
		   float: right;
           margin-right: 0.3rem;
           margin-top: 0.3rem;
           color: red;
           font-size: 0.3rem;
		}
	    .moreNews a{
	        color: red; 
	    }
</style>
<body>
	<c:if test="${not empty indexAds.result}">
		<div class="swiper-container" >
		    <div class="swiper-wrapper">
		     <c:if test="${indexType==0}">
		       <c:forEach var="bean" items="${indexAds.result}" varStatus="status">
		    	 <c:if test="${not empty bean.address}">
		    		<a href="${bean.address}" class="swiper-slide"><img src="${bean.photo}"></a>
		    	 </c:if>
		    	 <c:if test="${empty bean.address}">
		    		<a href="javascript:void(0)" class="swiper-slide"><img src="${bean.photo}"></a>
		    	 </c:if>
		       </c:forEach>
		     </c:if>
		      <c:if test="${indexType==1}">
		       <c:forEach var="bean" items="${indexAds.result}" varStatus="status">
		    	 <c:if test="${not empty bean.address}">
		    		<a href="${bean.address}" class="swiper-slide"><img src="${bean.photo}"></a>
		    	 </c:if>
		    	 <c:if test="${empty bean.address}">
		    		<a href="javascript:void(0)" class="swiper-slide"><img src="${bean.photo}"></a>
		    	 </c:if>
		       </c:forEach>
		     </c:if>
		   
		    </div>
		    <div class="pagination"></div>
		</div>
	</c:if>
	<div><img src="${imagePath}/wx/online-shop/fast.jpg"/></div>
	
	 <!--  <div class="imgDiv">
	     <div class="moreNews"><a  href="${ctx}/wx/index/findNewsList?pageNo=1&pageSize=50">更多>></a></div>
	     <div class="font-info">
	         <h1 class="font-h-1">天天</h1>
	         <h1 class="font-h-2">资讯</h1>
	      </div>
	     
	     <ul class="newlist">
	      <c:if test="${not empty newList.result }">
	         <c:forEach var="news" items="${newList.result}" varStatus="status" begin="0" end="3">
	            <li class="listInfo"><label class="keywords">${news.source}</label><a href="${ctx}/wx/index/findNewsById?id=${news.id}" class="newsTitle">${fn:substring(news.title,0,6)}</a></li>
	         </c:forEach>
	      </c:if>	      
	     </ul>
	 </div>-->
	
	<section class="content">
	<c:if test="${not empty indexColumns.result}">
	   <ul class="con-icon clearfix">
		<c:forEach var="bean" items="${indexColumns.result}" varStatus="status" begin="0" end="6">
			<li>
				<a href="${ctx}/wx/product/online/products/0/${bean.id}">
					<i><img src="${bean.pic2}"></i>
					<span>${bean.shortName}</span>
				</a>
			</li>
		</c:forEach>
		<li>
			<a href="${ctx}/wx/column/online/allColumn">
				<i><img src="${imagePath}/wx/online-shop/uploadimg/index-iocn8.png"></i>
				<span>全部分类</span>
			</a>
		</li>
	</ul>
		</c:if>
		<%-- <div class="con-snacks com-bg">
			<h4 class="today-title clearfix"> 
				<!-- <p class="today-icon"><img src="uploadimg/index-pollex.png"><span>待修改标题</span></p> -->
			</h4> 
			<ul class="common-ul common-Img clearfix">
				<li class="lucky-item" style="float:left:;width: 50%">
					<!--  <a href="${ctx}/wx/index/jdIndex?conlumId=1401">-->
					<a onclick="goTianMao()">
						<img style="width: 90%;height: 1.5rem;margin-right:5px" src="${imagePath}/wx/o2o-shop/微信图片_20171120095658.jpg">
					</a>
				</li>
				<li class="lucky-item" style="float:right;width: 50%">
					<a onclick="goTianMao()">
						<img style="width: 90%;height: 1.5rem;margin-left:5px" src="${imagePath}/wx/o2o-shop/微信图片_20171120100226.jpg">
					</a>
				</li>
			</ul>
			
			<ul class="common-ul common-Img clearfix" style="margin-top: 15px">
				<li class="lucky-item" style="width: 50%;float:left">
					<a href="${ctx}/wx/product/goTianTianYiGo">
						<img style="width: 90%;height: 1.5rem;margin-right:5px" src="${imagePath}/wx/o2o-shop/微信图片_20171117172544.jpg">
					</a>
				</li>
				<li class="lucky-item" style="width: 50%;float:right;">
					<a href="${ctx}/wx/product/goJDProductListPage">
						<img style="width: 90%;height: 1.5rem;margin-left:5px" src="${imagePath}/wx/o2o-shop/微信图片_20171226135827.png">
					</a>
				</li>
			</ul>
			
			
		</div>			 --%>
		
		 <%-- <div class="con-snacks com-bg">
			<h4 class="today-title clearfix">
				 <p class="today-icon"><img src="${imagePath}/wx/o2o-shop/商城-(1).png"><span>今天推荐</span></p> 
			</h4>
			<ul class="common-ul common-Img clearfix" >
			   <li class="lucky-item " onclick="goJdProProduct()">
					<a>
						<img src="${imagePath}/wx/online-shop/京东自营入口.jpg">
					</a>
				</li>
			    
			    <li class="lucky-item" onclick="goMerchantProcurement()" style="margin-top: 5px">
					<a >
						<img src="${imagePath}/wx/online-shop/微信图片_20180212144506.jpg">
					</a>
				</li>
				
				 <li class="lucky-item adv-img" >
					<a>
						<img src="${imagePath}/wx/online-shop/adv-2.jpg">
					</a>
				</li>
				<li class="lucky-item adv-img">
					<a>
						<img src="${imagePath}/wx/online-shop/adv-3.jpg" class="longImg" >
					</a>
				</li> 
			</ul>
		</div>--%>
				
		<c:if test="${not empty indexProducts.result}">
		<c:forEach var="mapBean" items="${indexProducts.result}" varStatus="status">
		<div class="con-today com-bg">
			<h4 class="today-title clearfix">
				<c:set value="${fn:substringBefore(mapBean.key,'~')}" var="columnPic" />
				<p class="today-icon"><img src="${fn:substringAfter(columnPic,';')}"><span>${fn:substringBefore(columnPic,';')}</span></p>
				<c:if test="${fn:substringBefore(columnPic,';')=='京东'}">
				    <a href="${ctx}/wx/index/jdIndex?conlumId=${fn:substringAfter(mapBean.key,'~')}" class="today-a">更多></a>
				</c:if>
				
				<c:if test="${fn:substringBefore(columnPic,';')!='京东'}">
				    <a href="${ctx}/wx/product/online/products/1/${fn:substringAfter(mapBean.key,'~')}" class="today-a">更多></a>
				</c:if>
				
			</h4>
			<ul class="today-wrap clearfix">
			<c:forEach var="bean" items="${mapBean.value}" varStatus="status">
				<li>
					<a href="${ctx}/wx/product/online/product/${bean.id}">
						<img src="${bean.imgUrl}">
						<p class="today-i">${bean.name}</p>
						<c:if test="${bean.isCanUserCou==0}">
						   <p class="today-p">${bean.price}</p>
						</c:if>
						<c:if test="${bean.isCanUserCou==1}">
						   <p class="today-p">${bean.pointNicePrice}+${bean.price}积分</p>
						</c:if>
						<c:if test="${bean.isCanUserCou==2}">
						   <p class="today-p">${bean.pointNicePrice}+${bean.price}责任金额</p>
						</c:if>
						<c:if test="${bean.isCanUserCou==3}">
						   <p class="today-p">${bean.pointNicePrice}+${bean.price}代金券金额</p>
						</c:if>
						
					</a>
				</li>
			</c:forEach>
			</ul>
		</div>
		</c:forEach>
		</c:if>
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
   			var dis="${sessionScope.gjfMemberInfo.merchantModel}";
   			window.location.href="${ctx}/wx/trade/goMerchantProcurementPage?discount="+dis
   			 /*$.ajax({
   				url:"${ctx}/wx/trade/findMemberInfo",
   			    method:"GET",
   			    success:function(data){
   			    	if(data.code==200){
   			    		if(data.result.type==0){
   			    		  layer.open({
   		   			         content:'你不是商家,请在个人中心-我是商家里面进行商家申请后再进行相应的操作',
   		   			         btn:'我知道了'
   		   			       })  
   			    		}else{
   			    			window.location.href="${ctx}/wx/trade/goMerchantProcurementPage"
   			    		}
   			    	}else{
   			    		layer.open({
  		   			         content:'用户信息不存在',
  		   			         btn:'我知道了'
  		   			       })
   			    	}
   			    	
   			    	
   			    	
   			    }
   			}) */
   		}
   		
   		$(".popup-btn-link").on("touchend",function(e){
            e.stopPropagation();
            e.preventDefault();
            var num = $(".into-sprite-active").attr("data-value");
            if(num == "1"){//企业入驻
                window.location.href = "${ctx}/wx/store/toAdd/1";
            }else if(num == "2"){//个体入驻
                window.location.href = "${ctx}/wx/store/toAdd/0";
            }
        })

        $("#close-popup").on("click",function(e){
            e.stopPropagation();
            $(".bg-cover").addClass("hidden");
            $(".popup-box").addClass("hidden");
            $(".popup-item").removeClass("into-sprite-active");
        })
   	    
   		//跳转到友集网
   		$(".adv-img").each(function(){
   			$(this).click(function(){
   				layer.open({
	                content: "点击将跳转到友集网，在友集网购买的商品将统一在中午12点和晚上12点进行结算",                       	                       
	                btn: ['确认', '取消'],
	                yes: function(index){//点击去认证页面，index为该特定层的索引
	                	   //获取用户id
	        		  	   var memberId="${sessionScope.gjfMemberInfo.id}";
	        		  	   //获取用户昵称
	        		  	   var nickName="${sessionScope.gjfMemberInfo.nickName}";
	        		  	   //获取用户头像
	        		  	   var img ="${sessionScope.gjfMemberInfo.imgHeadUrl}";
	        		  	   //获取用户手机号码
	        		  	   var mobile="${sessionScope.gjfMemberInfo.mobile}";
	        	   			
	        	   		   //请求url
	        	   		   window.location.href="http://m.upinkji.com/wap/myshop/shop_bind.html?weid=2"
	        	   						+"&domain=baf46d31fff31a7587df12dea5bfe&share_id=b1d41MSSuxRYJH_YfA05z5JZbpya2q4XZn5d5-6VUiGaF7zVdjw"
	        	   						+"&phoenix_id="+memberId+"&phoenix_name="+nickName+"&phoenix_mobile="+mobile+"&phoenix_avatar="+mobile
	                },
	                no:function(index){
	                    layer.close(index);	                    
	                }
	            });	
   			     			   
   			})
   		})
   		
   		
   		  		  		
   		
	</script>
</body>
</html>