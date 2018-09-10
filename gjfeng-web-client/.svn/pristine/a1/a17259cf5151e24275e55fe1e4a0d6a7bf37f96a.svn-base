<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-shop-top.jsp" %>

<style>
   .par-li0:BEFORE{
    position: absolute;
    left: -0.2rem;
    top: 0;
    font-size: 0.56rem;
   }
   .partic-footer-a0{
    display: inline-block;
    width: 7.56rem;
    height: 0.78rem;
    line-height: 0.78rem;
    text-align: center;
    font-size: 0.305rem;
    color: #fff;
    background-color: #ff5350;
    border-radius: 0.08rem;
    text-indent: -1em;
   }
   #res{
       width: 45px;
       height: 33px;
       background-color: rgba(181, 181, 181, 0);
       text-align: center;
       font-size: 0.235rem;
   }
</style>
<body>
	<div class="swiper-container partic-banner">
	    <div class="swiper-wrapper">	      
	      <c:if test="${not empty goodDetail.gallery}">
	        <c:forEach var="goodImg" items="${goodDetail.gallery}">
	           	<a href="javascript:" class="swiper-slide "><img src="${goodImg.imgUrl}"></a>
	        </c:forEach>       
	      </c:if>	     	      		
	    </div>
	    <div class="pagination"></div>
	</div>
	<section class="meb-list partic-list">
		<div class="partic-ti">
		    <input id="pusNum" type="hidden" value=""/>
			<p>${goodDetail.goodsName}</p>
			<p class="partic-co">${goodDetail.shopPrice}</p>						
		</div>
		<ul class="list-common list-margin">
			<li>				
				<c:if test="${goodDetail.from=='JD'}">
				     <i class="partic-i"  style="display: block;width: 100%;line-height: 0.84rem;position: relative;font-size: 15px">商品来源：京东</i> 				    				
				  </c:if>
				  <c:if test="${goodDetail.from=='SN'}">
				     <i class="partic-i" style="display: block;width: 100%;line-height: 0.84rem;position: relative;font-size: 15px" >商品来源：苏宁</i>
				  </c:if>
				  <c:if test="${goodDetail.from=='YHD'}">
				     <i class="partic-i" style="display: block;width: 100%;line-height: 0.84rem;position: relative;font-size: 15px">商品来源：1号店</i>
				  </c:if>				
			</li>
			<li>
				<a href="javascript:">
					 <i class="partic-i">库存：</i>
					 <span class="pro-stock">999</span>				
				</a>
			</li>
					
		</ul>
		<ul class="list-common list-margin">
			<li>
				<a href="${ctx}/wx/product/findJdProprietaryGraphicDetail?goodsId=${goodDetail.goodsId}">
					<span>商品详情</span>
					<div class="tr"></div>
				</a>
			</li>
		</ul>				
	</section>
	<div class="partic-dustP">
		<div class="partic-box">
			<div class="partic-setmeal">
				<div class="partic-com-set clearfix">
					<div class="partic-seimg"><img src="${goodDetail.goodsThumb}"></div>
					<div class="partic-com-details">
						<p>${goodDetail.goodsName} </p>						
					</div>
					<a href="javascript:void(0)" class="partic-com-shut partic-shut-a"></a>
				</div>
				<div class="partic-com-count">								    				 				    					
					<p class="partic-com-co">请选择数量：</p>
					<p class="partic-count clearfix">
						<i class="partic-cut partic-cuti1"></i>
							<span class="partic-cutinput">
							   <input id="res" type="text" value="1">							  							   
							</span>
						<i class="partic-cut partic-cuti2"></i>
						<%-- <span class="partic-com-s">库存${productAttrStock.repertory}件(商品限购${productInfo.purchasNum}件)</span>
						<input type="hidden" id="getRepertory" value="${productAttrStock.repertory}"/> --%>
					</p>
					<div class="partic-com-gou clearfix" style="display: none;">
						<a href="javascript:void(0)" class="partic-comgouac" id="addOrder">立即购买</a>
						
					</div>
					<a href="javascript:" class="address-adda partic-ac" style="display: none;">立即购买</a>
					
					   <a href="javascript:" class="address-adda partic-shop" style="display: none;">加入购物车</a>
					
					
				</div>
			</div>
			<div class="partic-com-hint" style="display: none">
				<span>您已成功加入购物车</span>
				<a href="javascript:" class="partic-com-shut"></a>
			</div>
		</div>
		<div class="partic-dust dustclick"></div>
		</div>
		<form action="${ctx}/wx/order/toAdd" method="get" id="goods-order">
            <input type="hidden" class="" name="orderAddVos[0].goodsId" value="${goodDetail.goodsId}">
			<input type="hidden" class="order-goods-attr" name="orderAddVos[0].goodsAttrIds" value="">
			<input type="hidden" class="order-goods-num" name="orderAddVos[0].goodsNum" value="">
			<input type="hidden" class="order-goods-total-num" value="999">
			 
			<input type="hidden" name="goodSource" value="1">
			 <input type="hidden" name="logist" value="0" id="logist"/>
		</form>
	<footer class="partic-footer">					
		  <a href="javascript:" class="partic-footer-a0 par-li" >立即购买</a>		   		
	</footer>
	<script src="${jsPath}/wx/online-shop/idangerous.swiper.min.js"></script>
	<script type="text/javascript">
	$(function(){
		document.title = "商品详情";
		var mySwiper = new Swiper('.swiper-container',{
 		  	autoplay:3000,//每3秒走一次
     	  	autoplayDisableOnInteraction : false,//轮播
   		  	loop:true,//是否无缝
   		 	pagination: '.pagination',
   		  	mousewheelControl:true,//值为true时，能够使用鼠标滑轮滑动swiper
   		  	paginationClickable: true,//点击按钮
   		  	calculateHeight:true,//Swiper根据slides内容计算容器高度。
		})
   		//计算高度
   		function particHeight(){
			var b = $('.partic-list .today-i');
				for(i=0;i< b.length;i++){
				    if(0==i%2){
				    	var h1 = b.eq(i).height();
				    	var h2 =  b.eq(i+1).height();
				    	if(i < b.length){
					    	h = Math.max(h1,h2);
					    	b.eq(i).height(h);
					    	b.eq(i+1).height(h);
				    	}
				    }
				}
		}	
		particHeight();

		//选中套餐
		function particTao(){
			$(".partic-com-tao span").on("click",function(event){
				$(this).addClass("partic-com-taoactive").siblings().removeClass("partic-com-taoactive");
				var proId="${productInfo.id}";
				var attrIds="";
				$(".partic-com-taoactive").each(function(){
					attrIds+=$(this).attr("data-attr-id")+",";
				});
				console.log(proId+"---"+attrIds);
				ajaxGetStock(proId,attrIds);
				//$("").text("库存"+$(this).siblings(".pro-attr-repertory").val()+"件");
				event.preventDefault();//取消冒泡
			})
		}particTao();

		// 立即购买 加入购物车
		function  particShow(){
			sessionStorage.removeItem("addreId")
			// 立即购买
			$(".par-li").on('touchstart',function(e){
				$(".partic-ac").show();
				$(".partic-dustP").show();
				$(".partic-footer").hide();			
				$("#res").val("1")
				event.preventDefault();//取消冒泡
			})

			// 加入购物车
			$(".par-shop").on("touchstart",function(event){
				$(".partic-shop").show();
				$(".partic-dustP").show();
				$(".partic-footer").hide();
				$("#logist").val("0")
				$("#res").val("1")
				event.preventDefault();//取消冒泡
			})
			// 加入购物车
			$(".partic-shop").on("click",function(event){
				//异步添加到购物车
				ajaxAddCart();
				event.preventDefault();//取消冒泡
			})
			// 加入购物车
			$(".partic-ac").on("click",function(event){
				//下单
				addOrder();
				event.preventDefault();//取消冒泡
			})
			// 关闭
			$(".partic-com-shut").on("click",function(event){
				$(this).parent(".partic-com-hint").fadeOut();
				event.preventDefault();//取消冒泡
			})
			// 点击套餐
			$(".partic-com-li").on("click",function(event){
				$(".partic-dustP").fadeIn();
				$(".partic-footer").fadeOut();
				$(".partic-com-gou").fadeIn();
				event.preventDefault();//取消冒泡
			})
			$(".partic-shopcar").on("click",function(event){
				event.preventDefault();//取消冒泡
			})

			$(".partic-shut-a").on("click",function(event){
				$(this).parents(".partic-dustP").hide();
				$(".partic-footer").show();
				$(".partic-ac").hide();
				$(".partic-shop").hide();
				$(".partic-com-hint").hide();
				$(".partic-com-gou").hide();
				event.preventDefault();
			})

			
			
			
			$(".partic-com-gou a").on("click",function(event){
				$(this).addClass("partic-comgouac").siblings().removeClass("partic-comgouac");
				if($(this).attr("class")=="partic-shopcar partic-comgouac"){
					ajaxAddCart();
				}else{
					addOrder();
				}
				event.preventDefault();//取消冒泡
			})
		}particShow();
	})
	
	
	//点击加点击减
    function particCuti(){   	
        // 加
        $(".partic-cuti2").on("touchend",function(){
            var arr = [];
            var countVal=parseInt($("#res").val())+1
            var str="<input id='res' type='text' value='"+countVal+"'>"           		
       	    $(this).siblings(".partic-cutinput").html(str);                                                
           
        })
         // 减
       /*  $(".partic-cuti1").on("touchend",function(){  
        	  
        	var countVal=parseInt($("#res").val())-1        	
            if(countVal==0){
                return;
            }
        	
        	var conStr="<input id='res' type='text' value='"+countVal+"'>"

            $(this).siblings(".partic-cutinput").html(conStr);            
            
        }) */
    }particCuti();
      	
	//立即购物
	function addOrder(){
		//添加商品信息
		
		var attrIds="";
		$(".partic-com-taoactive").each(function(){
			attrIds+=$(this).attr("data-attr-id")+",";
		});
		var goodsNum=$("#res").val();
		var reg = new RegExp("^[1-9]\d*|0$");
		
		if(!reg.test(goodsNum)||goodsNum==0){
			layer.open({
                content:"购买商品数量是大于1的正整数",
                btn:'我知道了'
            })
            $("#res").val(1)
            return false;
		}
		var goodsTotalNum=$(".order-goods-total-num").val();
				
		$(".order-goods-num").val(goodsNum);
		$(".order-goods-attr").val(attrIds);
		$("#goods-order").submit();
	}
	
	//添加购物车
	function ajaxAddCart(){
		var proId="${productInfo.id}";
		var attrIds="";
		$(".partic-com-taoactive").each(function(){
			attrIds+=$(this).attr("data-attr-id")+",";
		});
		var goodsNum=$("#res").val();
		var reg = new RegExp("^[1-9]\d*|0$");
		if(!reg.test(goodsNum)||goodsNum==0){
			layer.open({
                content:"购买商品数量是大于1的正整数",
                btn:'我知道了'
            })
            $("#res").val(1)
            return false;
		}
		var goodsTotalNum=$(".order-goods-total-num").val();
		var gosStock=$(".pro-stock").text();
		var logist=$("#logist").val()
		if(gosStock <=0){
			layer.open({
                content:"库存不足，请选择其他属性或商品",
                btn:'我知道了'
            })
			return false;
		}
	       $.ajax({
	           url: '${ctx}/wx/cart/add',
	           type: 'post',
	           dataType: 'json',
	           data: {
	           	"goodsId":proId,
	           	"goodsAttr":attrIds,
	           	"goodsNum":goodsNum,
	           	"logist":logist
	           	},
	           success:function(data){
	               if(null != data && "" != data){
		              if(data.code != 200){
		            	  $(".partic-com-hint > span").text(data.msg);
		              }
	               }else{
	            	   $(".partic-com-hint > span").text('添加购物车失败');
	               }
	               $(".partic-com-hint").fadeIn();
	           },
	           error:function(){
	        	   //layer.alert("网络异常");
	           }
	       })
	     }
	
	
	//添加京东商品信息
	addJdPro('${goodDetail.goodsId}')
	function addJdPro(proId){
		$.ajax({
			url:"${ctx}/wx/product/addJdProprietaryProduct",
			method:"POST",
			data:{
				proId:proId
			},
			success:function(data){
				if(null != data && "" != data){
		              if(data.code != 200){
		            	  layer.open({
		                      content:data.msg,
		                      btn:'我知道了'
		                  })
		              }
	               }
			}
		})
	}
	
	//查找商品库存
	function ajaxGetStock(proId,attrIds){
	       $.ajax({
	           url: '${ctx}/wx/product/online/product/stock',
	           type: 'post',
	           dataType: 'json',
	           data: {
	           	"proId":proId,
	           	"attrIds":attrIds
	           	},
	           success:function(data){
	        	   console.log(data);
	               if(null != data && "" != data){
	            	   console.log(data);
		              if(data.code==200){
		            	 $(".partic-com-s").text('库存'+data.result.repertory+'件');
		            	 $(".pro-stock").text(data.result.repertory);
		            	 $(".partic-co").text(data.result.price);
		            	 $(".partic-com-price").text(data.result.price);
		            	 $(".order-goods-total-num").text(data.result.repertory);
		              }else{
		            	  $(".partic-com-s").text('库存0件');
		            	  $(".pro-stock").text(0);
		            	  //$(".partic-co").text(0);
		            	  //$(".partic-com-price").text(0);
		              }
	               }else{
	            	   $(".partic-com-s").text('库存0件');
	            	   $(".pro-stock").text(0);
	            	   //$(".partic-co").text(0);
	            	   //$(".partic-com-price").text(0);
	               }
	           },
	           error:function(){
	        	   //layer.opend("网络异常");
	           }
	       })
	     }
	
	
	  $.ajax({
		  url:"${ctx}/wx/comment/countProComment",
		  method:"GET",
		  data:{
			  proId:"${productInfo.id}",
			  state:1
		  },
		  success:function(data){
			  if(null == data.result || "" == data.result || undefined==data.result){
				$("#users-num").html("(0)");
			  }else{
				$("#users-num").html("("+data.result+")");
			  }
		  }  	
      })
      
      var isLoginNet= sessionStorage.getItem("isLoginNet");
        if("${productInfo.suorceGoods}"=="1"&&isLoginNet!="1"){
    	   var phoenix_id="${memberInfo.token}".substring(0,50);
    	   $.ajax({
    		  url:"${ctx}/wx/product/registerNetFriend",
    		  method:"POST",
    		  data:{
    			  phoenix_id:phoenix_id,
    			  phoenix_name:"${memberInfo.nickName}",
    			  phoenix_mobile:"${memberInfo.mobile}",
    			  phoenix_avatar:"${memberInfo.imgHeadUrl}"
    		  },
    		  success:function(data){
    			  sessionStorage.setItem("isLoginNet", "1")
    			  window.location.href="http://m.upinkji.com/wap/phoenix/shop_bind.html?appKey=FnHbJsK6yFFuihUSef&weid=2&domain=baf46d31fff31a7587df12dea5bfe&share_id=b1d41MSSuxRYJH_YfA05z5JZbpya2q4XZn5d5-6VUiGaF7zVdjw&appKey=FnHbJsK6yFFuihUSef&phoenix_id="+phoenix_id+"&phoenix_name=${memberInfo.nickName}&phoenix_mobile=${memberInfo.mobile}&phoenix_avatar=${memberInfo.imgHeadUrl}&sign="+data.result.sign;  
    		  }
    	  })
    	 
      }  
	  
	   
	  //更新商品库存
	 if("${productInfo.suorceGoods}"=="2"){
		 $.ajax({
			 url:"${ctx}/wx/product/updataJdProStock",
			 method:"POST",
			 data:{
				 proId:"${productInfo.id}",
			 },
			 success:function(data){
				 if(data.code==200){
					 $("#benefitMoney").html(data.result.benefitMoney);
					 $("#jdProMoney").html(data.result.proPrice);
				 }
			 }
		 })
	 }
	  
      		  
	</script>
</body>
</html>