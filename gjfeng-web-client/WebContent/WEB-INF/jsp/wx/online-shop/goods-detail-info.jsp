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
	      
	      <c:if test="${not empty productInfo.para3}">
	       	<a href="javascript:" class="swiper-slide "><img src="${productInfo.para3}"></a>
	      </c:if>
	      <c:if test="${not empty productInfo.para4}">
	       	<a href="javascript:" class="swiper-slide"><img src="${productInfo.para4}"></a>
	      </c:if>
	      <c:if test="${not empty productInfo.para5}">
	       	<a href="javascript:" class="swiper-slide"><img src="${productInfo.para5}"></a>
	      </c:if>
	      <c:if test="${not empty productInfo.para1}">
	       	<a href="javascript:" class="swiper-slide"><img src="${productInfo.para1}"></a>
	      </c:if>
	      <c:if test="${not empty productInfo.para2}">
	       	<a href="javascript:" class="swiper-slide"><img src="${productInfo.para2}"></a>
	      </c:if>
	      <%-- <c:if test="${not empty productInfo.para6}">
	       	<a href="javascript:" class="swiper-slide"><img src="${productInfo.para6}"></a>
	      </c:if>
	      <c:if test="${not empty productInfo.para7}">
	       	<a href="javascript:" class="swiper-slide"><img src="${productInfo.para7}"></a>
	      </c:if>
	      <c:if test="${not empty productInfo.para8}">
	       	<a href="javascript:" class="swiper-slide"><img src="${productInfo.para8}"></a>
	      </c:if> --%>
		
	    </div>
	    <div class="pagination"></div>
	</div>
	<section class="meb-list partic-list">
		<div class="partic-ti">
		    <input id="pusNum" type="hidden" value="${productInfo.purchasNum}"/>
		    <input id="mulNum" type="hidden" value="${productInfo.multipleNumber}"/>
			<p>${productInfo.name}</p>
			<c:if test="${productInfo.suorceGoods==0}">
			  <c:if test="${productInfo.isCanUserCou==0}">
			     <c:if test="${empty type}">
			       <p class="partic-co">${productAttrStock.price}</p>
			     </c:if>
			     <c:if test="${type==1}"> 
			       <p style="color:red">${productAttrStock.price}</p>
			       <%-- <p class="partic-co">${productAttrStock.standardPrice}</p>
			       <p class="partic-co" style="text-decoration:line-through;color:#9e9e9e">${productAttrStock.price}</p> --%>
			     </c:if>
			     <c:if test="${type==2}">
			       <p style="color:red">${productAttrStock.price}</p>
			       <%-- <p class="partic-co">${productAttrStock.honourPrice}</p>
			       <p class="partic-co" style="text-decoration:line-through;color:#9e9e9e">${productAttrStock.price}</p> --%>
			     </c:if>
			     
			  </c:if>
			  <c:if test="${productInfo.isCanUserCou==1}">
			     <p style="color: red">${productAttrStock.price} 积分</p>
			  </c:if>	
			  <c:if test="${productInfo.isCanUserCou==2}">
			     <p style="color: red">${productAttrStock.price} 责任金额</p>
			  </c:if>
			  <c:if test="${productInfo.isCanUserCou==3}">
			     <p style="color: red">${productAttrStock.price} 代金券金额</p>
			  </c:if>		 
			   
			</c:if>
			<c:if test="${productInfo.suorceGoods==1||(productInfo.suorceGoods==5&&productInfo.isCanUserCou==0)}">
			   <p class="partic-co">${productInfo.price}</p>		
			</c:if>
			<c:if test="${productInfo.suorceGoods==5&&productInfo.isCanUserCou==3}">
			   <p style="color: red">${productInfo.price} 代金券金额</p>
			</c:if>
			<c:if test="${productInfo.suorceGoods==5&&productInfo.isCanUserCou==1}">
			   <p style="color: red">${productInfo.price} 积分</p>
			</c:if>
			<c:if test="${productInfo.suorceGoods==2}">
			   <p class="partic-co" id="jdProMoney">价格更新中....</p>		
			</c:if>
			<a href="${ctx}/wx/cart/my" style="float: right;margin-top: -25px;color:red">进入购物车</a>
		</div>
		<ul class="list-common list-margin">
			<li>				
				<c:if test="${productInfo.suorceGoods==0}">
				  <a href="javascript:" class="partic-com-li">
					 <i class="partic-i">选择属性</i>
					 <div class="tr"></div>
				  </a>
				</c:if>
				 <c:if test="${productInfo.suorceGoods==1||productInfo.suorceGoods==5}">				 
				   <i class="partic-i" style="font-size: 16px;line-height: 0.84rem;">${productInfo.remark}</i>				  
				 </c:if>  
				
				<c:if test="${productInfo.suorceGoods==2}">				  
				  <c:if test="${productInfo.remark=='JD'}">
				     <i class="partic-i"  style="display: block;width: 100%;line-height: 0.84rem;position: relative;font-size: 15px">商品来源：京东</i> 				    				
				  </c:if>
				  <c:if test="${productInfo.remark=='SN'}">
				     <i class="partic-i" style="display: block;width: 100%;line-height: 0.84rem;position: relative;font-size: 15px" >商品来源：苏宁</i>
				  </c:if>
				  <c:if test="${productInfo.remark=='YHD'}">
				     <i class="partic-i" style="display: block;width: 100%;line-height: 0.84rem;position: relative;font-size: 15px">商品来源：1号店</i>
				  </c:if>				 			  
				</c:if> 
				
			</li>
			<li>
				<a href="javascript:">
					
					<c:if test="${productInfo.suorceGoods==0}">
					   <i class="partic-i">剩余：</i>
					  <span class="pro-stock">${productAttrStock.repertory}</span>
					</c:if>
					<c:if test="${productInfo.suorceGoods==1||productInfo.suorceGoods==5}">
					  <i class="partic-i">剩余：</i>
					  <span class="pro-stock">${productInfo.repertory}</span>
					</c:if>
					<c:if test="${productInfo.suorceGoods==2}">
					  <i class="partic-i">库存：</i>
					  <span class="pro-stock">999</span>
					</c:if>
					<!-- <div class="tr"></div> -->
				</a>
			</li>
			
			<c:if test="${productInfo.suorceGoods==2}">
			 <li>
				<a href="javascript:">
					<i class="partic-i">消费积分：</i>
					<span id="benefitMoney">积分计算中...</span>
					<!-- <div class="tr"></div> -->
				</a>
			 </li>
			</c:if>
			<c:if test="${productInfo.suorceGoods==1}">
			 <li>
				<a href="javascript:">
					<i class="partic-i">消费积分：</i>
					<span id="benefitMoney">${productInfo.benerfitMoney}</span>
					<!-- <div class="tr"></div> -->
				</a>
			 </li>
			</c:if>
			
			
			<c:if test="${productInfo.isCanUserCou==0}">
			  <c:if test="${not empty type}">
			     <li>
				   <a href="javascript:" >
					<i class="partic-i" style='color:#206bdc'>商家兑换：</i>
					 <span>${productInfo.standardPrice*10}</span>					
				  </a>
				 
			    </li>
			  </c:if>			 
			</c:if>
			<c:if test="${productInfo.isCanUserCou==0}">
			  <c:if test="${not empty type}">
			     <li>
				   <a href="javascript:" >
					<i class="partic-i" style='color:#ae20dc'>企业兑换：</i>
					 <span>${productInfo.honourPrice*10}</span>					
				  </a>
				 
			    </li>
			  </c:if>
			 
			</c:if>
						
			<c:if test="${productInfo.isCanUserCou==1&&productInfo.suorceGoods==0}">
			  <li>
				<a href="javascript:">
					<i class="partic-i">积分使用说明：</i>
					<span>${productAttrStock.price}积分+￥${productInfo.pointNicePrice}</span>					
				</a>
			  </li>
			</c:if>
			<c:if test="${productInfo.isCanUserCou==1&&productInfo.suorceGoods==5}">
			  <li>
				<a href="javascript:">
					<i class="partic-i">积分使用说明：</i>
					<span>${productInfo.price}积分+￥${productInfo.pointNicePrice}</span>					
				</a>
			  </li>
			</c:if>
			
			<c:if test="${productInfo.isCanUserCou==2}">
			  <li>
				<a href="javascript:">
					<i class="partic-i">责任金额使用说明：</i>
					<span>${productAttrStock.price}责任金额+￥${productInfo.pointNicePrice}</span>
					
				</a>
			  </li>
			</c:if>
			<c:if test="${productInfo.isCanUserCou==3&&productInfo.suorceGoods==0}">
			  <li>
				<a href="javascript:">
					<i class="partic-i">代金券使用说明：</i>
					<span>${productAttrStock.price}代金券金额+￥${productInfo.pointNicePrice}</span>
					
				</a>
			  </li>
			</c:if>
			<c:if test="${productInfo.isCanUserCou==3&&productInfo.suorceGoods==5}">
			  <li>
				<a href="javascript:">
					<i class="partic-i">代金券使用说明：</i>
					<span>${productInfo.price}代金券金额+￥${productInfo.pointNicePrice}</span>
					
				</a>
			  </li>
			</c:if>
			
			
			
		</ul>
		<ul class="list-common list-margin">
			<li>
				<a href="${ctx}/wx/product/online/product/detail/${productInfo.id}">
					<span>商品详情</span>
					<div class="tr"></div>
				</a>
			</li>
		</ul>
		<ul class="list-common list-margin">
			<li>
				<a href="${ctx}/wx/comment/getProCommetByPage?pageNo=1&pageSize=10&type=0&state=0&proId=${productInfo.id}">
					<span>商品评价<i id="users-num">
						<%-- <c:if test="${empty commentsNum}">0</c:if>
						<c:if test="${not empty commentsNum}">${commentsNum}</c:if> --%></i></span>
					<div class="tr"></div>
				</a>
			</li>
		</ul>
		<!-- <div class="reset-tody list-margin">
			<a href="javascript:" class="partic-a">更多精选商品</a>
			<ul class="today-wrap clearfix">
				<li>
					<a href="javascript:">
						<img src="../uploadimg/in-today.jpg">
						<p class="today-i">脐橙之乡赣南脐橙</p>
						<p class="today-p">188.00</p>
					</a>
				</li>
			</ul>
		</div> -->
	</section>
	<div class="partic-dustP">
		<div class="partic-box">
			<div class="partic-setmeal">
				<div class="partic-com-set clearfix">
					<div class="partic-seimg"><img src="${productInfo.imgUrl}"></div>
					<div class="partic-com-details">
						<p>${productInfo.name} </p>
						<c:if test="${productInfo.suorceGoods==0}">
						  <c:if test="${empty type}">
						     <p class="partic-com-price">${productAttrStock.price}</p>
						  </c:if>
						  <c:if test="${type==1}">
			                 <p  style="color:red"><fmt:formatNumber value="${productInfo.standardPrice}" pattern="2"/></p>
			              </c:if>
			              <c:if test="${type==2}">
			                <p style="color:red"><fmt:formatNumber value="${productInfo.honourPrice}" pattern="2"/></p>
			              </c:if>
						</c:if>
						<c:if test="${productInfo.suorceGoods==1||productInfo.suorceGoods==5}">
						   <p class="partic-com-price">${productInfo.price}</p>
						</c:if>
						
					</div>
					<a href="javascript:void(0)" class="partic-com-shut partic-shut-a"></a>
				</div>
				<div class="partic-com-count">
				
				    <c:if test="${productInfo.suorceGoods==0}">
				      <c:if test="${productInfo.isWholesale==0}">
				        <p>请选择属性：</p>
					     <c:forEach items="${productAttrs}" var="beanMap" varStatus="status">
					     <div class="partic-com-tao clearfix">
					        <c:if test="${productInfo.isCanUserCou!=3}">
							  <label style="float: left;margin-right: 0.18rem; margin-bottom: 0.2rem;">${beanMap.key}:</label>
							</c:if>
							<c:if test="${productInfo.isCanUserCou==3}">
							  <label style="float: left;margin-right: 0.18rem; margin-bottom: 0.2rem;">代金券兑换:</label>
							</c:if>
							<c:forEach items="${beanMap.value}" var="bean" varStatus="status1">
							<span data-attr-id="${bean.id}" <c:if test="${status1.count==1}"> class="partic-com-taoactive"</c:if>>${bean.attrValueId.attrValue}</span>
							</c:forEach>
					     </div>
					    </c:forEach>
				      </c:if>
				      <c:if test="${productInfo.isWholesale==1&&type==1}">
				        <input type="hidden" value="${productInfo.standardPrice-productInfo.honourPrice}" id="hou"/>
				        <p style="color: red" id="saveMoney">升级企业版本次购物单件立省${productAttrStock.standardPrice-productAttrStock.honourPrice}元</p>
				         <div class=" clearfix" style="margin-top: 23px;">
							<label style="float: left;margin-right: 0.18rem; margin-bottom: 0.2rem;margin-left: 139px;"></label>							
							<a  href="${ctx}/wx/trade/goMerchantUpgrade">立即升级</a>							
					     </div>
					     
					     <c:forEach items="${productAttrs}" var="beanMap" varStatus="status">
					     <div class="partic-com-tao clearfix" style="display: none">
							<label style="float: left;margin-right: 0.18rem; margin-bottom: 0.2rem;">${beanMap.key}:</label>
							<c:forEach items="${beanMap.value}" var="bean" varStatus="status1">
							<span data-attr-id="${bean.id}" <c:if test="${status1.count==1}"> class="partic-com-taoactive"</c:if>>${bean.attrValueId.attrValue}</span>
							</c:forEach>
					     </div>
					    </c:forEach>
				      </c:if>
				      
				      <c:if test="${productInfo.isWholesale==1&&type==2}">				        					     
					     <c:forEach items="${productAttrs}" var="beanMap" varStatus="status">
					     <div class="partic-com-tao clearfix" style="display: none">
							<label style="float: left;margin-right: 0.18rem; margin-bottom: 0.2rem;">${beanMap.key}:</label>
							<c:forEach items="${beanMap.value}" var="bean" varStatus="status1">
							<span data-attr-id="${bean.id}" <c:if test="${status1.count==1}"> class="partic-com-taoactive"</c:if>>${bean.attrValueId.attrValue}</span>
							</c:forEach>
					     </div>
					    </c:forEach>
				      </c:if>
				      
				    </c:if>
				    
				   <c:if test="${productInfo.isWholesale==1}">
				       <div style="margin-top: 25px;margin-left: 7px;">				        
				          <span class="pos logist0" data-value="0" style="padding: 5px;border: solid 1px #00ff08;margin-left: 5px;">快递</span>  
				          <span class="pos logist1" data-value="1" style="padding: 5px;border: solid 1px #9E9E9E;margin-left: 30px;">物流</span>                        
				       </div>				    
				    </c:if>
				    
					
					<p class="partic-com-co">请选择数量：</p>
					<p class="partic-count clearfix">
						<i class="partic-cut partic-cuti1"></i>
							<span class="partic-cutinput">
							  <c:if test="${productInfo.purchasNum==1}">
							    <input id="res" type="text" value="1" disabled="disabled" >
							  </c:if>
							  <c:if test="${productInfo.purchasNum>1}">
							     <input id="res" type="text" value="1">
							  </c:if>
							   
							</span>
						<i class="partic-cut partic-cuti2"></i>
						<span class="partic-com-s">库存
						  <c:if test="${productInfo.suorceGoods!=5}">
						     ${productAttrStock.repertory}
						     
						  </c:if>
						  <c:if test="${productInfo.suorceGoods==5}">
						     ${productInfo.repertory}
						  </c:if>
						件(商品限购${productInfo.purchasNum}件)</span>
						<input type="hidden" id="getRepertory" value="${productAttrStock.repertory}"/>
					</p>
					<div class="partic-com-gou clearfix" style="display: none;">
						<a href="javascript:void(0)" class="partic-comgouac" id="addOrder">立即购买</a>
						<c:if test="${productInfo.suorceGoods==0}">
						   <a href="javascript:" class="partic-shopcar" id="addCart">加入购物车</a>
						</c:if>
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
			<input type="hidden" class="" name="orderAddVos[0].goodsId" value="${productInfo.id}">
			<input type="hidden" class="order-goods-attr" name="orderAddVos[0].goodsAttrIds" value="${productAttrStock.productAttrIds}">
			<input type="hidden" class="order-goods-num" name="orderAddVos[0].goodsNum" value="">
			<input type="hidden" class="order-goods-total-num" value="${productAttrStock.repertory}">
			<c:if test="${productInfo.suorceGoods==0}">
			      <input type="hidden" name="goodSource" value="0">
			</c:if>
			<c:if test="${productInfo.suorceGoods==1}">
			      <input type="hidden" name="goodSource" value="2">
			</c:if>
			<c:if test="${productInfo.suorceGoods==2}">
			      <input type="hidden" name="goodSource" value="1">
			</c:if>
			<c:if test="${productInfo.suorceGoods==5}">
			      <input type="hidden" name="goodSource" value="5">
			</c:if>
			<%-- <c:if test="${productInfo.isWholesale==1}">
			   <input type="hidden" name="logist" value="1" id="logist"/>
			</c:if>
			<c:if test="${productInfo.isWholesale==0}">
			   <input type="hidden" name="logist" value="0" id="logist"/>
			</c:if> --%>
			 <input type="hidden" name="logist" value="0" id="logist"/>
		</form>
	<footer class="partic-footer">
		
		<c:if test="${productInfo.suorceGoods==0||productInfo.suorceGoods==5}">
		   <a href="javascript:" class="partic-footer-a par-li">立即购买</a>
		   <a href="javascript:" class="partic-footer-a par-shop">加入购物车</a>
		</c:if>
		<c:if test="${productInfo.suorceGoods==2||productInfo.suorceGoods==1}">
		   <a href="javascript:" class="partic-footer-a0 par-li" >立即购买</a>		   
		</c:if>
	</footer>
	<script src="${jsPath}/wx/online-shop/idangerous.swiper.min.js"></script>
	<script type="text/javascript">
	$(function(){
		document.title = "商品详情";
		var mySwiper = new Swiper('.swiper-container',{
 		  	autoplay:3000,//每3秒走一次
     	  	autoplayDisableOnInteraction : false,//轮播
   		  	loop:true,//0是否无缝
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
				var log="${productInfo.isWholesale}";
				/* if(log==0){
					$("#logist").val("0")
				}else{
					$("#logist").val("1")
				} */
				$("#logist").val("0")
				$("#res").val("1")
				event.preventDefault();//取消冒泡
			})

			// 加入购物车
			$(".par-shop").on("touchstart",function(event){
				$(".partic-shop").show();
				$(".partic-dustP").show();
				$(".partic-footer").hide();
				var log="${productInfo.isWholesale}";
				/* if(log==0){
					$("#logist").val("0")
				}else{
					$("#logist").val("1")
				} */
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

			// 点击蒙尘关闭
			// $(".dustclick").on("click",function(){
			// 	$(".partic-dustP").fadeOut();
			// 	$(".partic-footer").fadeIn();
			// 	$(".partic-ac").fadeOut();
			// 	$(".partic-shop").fadeOut();
			// 	$(".partic-com-hint").fadeOut();
			// 	$(".partic-com-gou").fadeOut();
			// })
			
			
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
	
	
	//点击快递或这物流
    $(".pos").each(function(){
    	$(this).click(function(){
    		var dataValue=$(this).attr("data-value")   		
    		if(dataValue==0){
    			$(".logist0").css({border:"solid 1px #00ff08"});
    			$(".logist1").css({border:"solid 1px #9E9E9E"});
    			$("#logist").val(dataValue)
    			$(".partic-cutinput").text("1") 
    			if("${productInfo.purchasNum}"=="1"){
    				$(".partic-cutinput").html("<input id='res' type='text' value='1' disabled='disabled'>")
    			}else{
    				$(".partic-cutinput").html("<input id='res' type='text' value='1'>")
    			}
    			var suorceGoods="${productInfo.suorceGoods}";
    			if(suorceGoods==5){
    				$(".partic-com-s").text('库存${productInfo.repertory}件(商品限购${productInfo.purchasNum}件)')
    			}else{
    				$(".partic-com-s").text('库存${productAttrStock.repertory}件(商品限购${productInfo.purchasNum}件)')
    			}
    			
    		}else{
    			$(".logist0").css({border:"solid 1px #9E9E9E"});
    			$(".logist1").css({border:"solid 1px #00ff08"}); 
    			$("#logist").val(dataValue)
    			$(".partic-cutinput").text("1")
    			$(".partic-cutinput").html("<input id='res' type='text' value='1'>")
    			var suorceGoods="${productInfo.suorceGoods}";
    			if(suorceGoods==5){
    				$(".partic-com-s").text('库存${productInfo.repertory}件(商品限购${productInfo.repertory}件)')
    			}else{
    				$(".partic-com-s").text('库存${productAttrStock.repertory}件(商品限购${productAttrStock.repertory}件)')
    			}
    			
    		}
    	})
    })
    
   $("#res").blur(function(){   	
    	 var hou=$("#hou").val()
    	 if(hou!=undefined){
      		var saveMoney=Math.floor(countVal*hou*100) / 100 ;
      		 $("#saveMoney").text("升级企业版本次购物立省"+saveMoney+"元")
      	}
    }) 
	
	//立即购物
	function addOrder(){
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
		
		var mulNum="${productInfo.multipleNumber}";
		if(goodsNum!=1&&goodsNum%mulNum!=0){
			layer.open({
                content:"购买商品数量必须是"+mulNum+"倍数",
                btn:'我知道了'
            })
            $("#res").val(1)
            return false;
		}
		var goodsTotalNum=$(".order-goods-total-num").val();
		var gosStock=$(".pro-stock").text();
		/* if(gosStock > goodsTotalNum){
			alert("不能大于库存！");
			return false;
		} */
		if(gosStock<=0){
			layer.open({
                content:"库存不足，请选择其他属性或商品",
                btn:'我知道了'
            })
            return false;
		}
		
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
		var mulNum="${productInfo.multipleNumber}";
		if(goodsNum!=1&&goodsNum%mulNum!=0){
			layer.open({
                content:"购买商品数量必须是"+mulNum+"倍数",
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
	  
	  function goNetFrieds(phoenix_id,phoenix_name,phoenix_mobile,phoenix_avatar,sign){
		  $.ajax({
			  url:"http://m.upinkji.com/wap/phoenix/shop_bind.html",
			  method:"GET",
			  data:{
				  weid:"2",
				  domain:"baf46d31fff31a7587df12dea5bfe",
				  share_id:"b1d41MSSuxRYJH_YfA05z5JZbpya2q4XZn5d5-6VUiGaF7zVdjw",
				  appKey:"FnHbJsK6yFFuihUSef",
				  phoenix_id:phoenix_id,
				  phoenix_name:phoenix_name,
				  phoenix_mobile: phoenix_mobile,
				  phoenix_avatar:phoenix_avatar,
				  sign:sign
			  },
			  success:function(data){
				  var json=eval('('+data+')');
				  alert(json.msg);
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