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
	
</style>
<body>
	
	<div><img src="${imagePath}/wx/online-shop/满减.jpg"/></div>
	<div><img src="${imagePath}/wx/online-shop/满减.jpg"/></div>
	<div><img src="${imagePath}/wx/online-shop/满减.jpg"/></div>
	<div><img src="${imagePath}/wx/online-shop/满减.jpg" /></div>
	
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
 		  	autoplay:3000,//每3秒走一次
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
   			//window.location.href="${ctx}/wx/trade/goMerchantProcurementPage"
   			 $.ajax({
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
   			}) 
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