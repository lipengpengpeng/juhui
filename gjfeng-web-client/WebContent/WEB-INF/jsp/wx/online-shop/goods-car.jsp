<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-shop-top.jsp" %>
<body>
	<!-- 公用头部导航 end-->
	<section class="shopcar-sec">
		<form id="car-form" action="${ctx}/wx/order/actCart" method="post">
			<ul class="shop-car">
			<c:forEach var="bean" items="${resultVo.result}" varStatus="status">
				<li>
					<div class="shopcar-head clearfix">
						<!-- <p class="shop-head-p1">店铺：<span>XXXX超市</span></p> -->
						<p class="shop-head-p2">
						<span class="shop-head-span">编辑</span>
						<span class="shop-delete" onclick="ajaxDel(this)">删除</span>
						<input type="hidden" class="hic-car-Id" value="${bean.id}">
						</p>
					</div>
					<div class="shopcar-box clearfix">
						<div class="shopcar-input">
		  					<input type="checkbox" value="" class="shop-chckinput">
		  					<label class="shopcar-checkbox"></label>
						</div>
						<div class="shopcar-wrap clearfix">
						    <c:if test="${not empty bean.goodsId.imgUrl}">
						       <img src="${bean.goodsId.imgUrl}">
						    </c:if>
						    <c:if test="${empty bean.goodsId.imgUrl}">
						       <img src="${imagePath}/wx/o2o-shop/shop-car.jpg">
						    </c:if>
							
							<a href="javascript:" class="shopcar-div">
								<div>
									<p class="shopcar-div-p1">${bean.goodsId.name}</p>
									<c:if test="${bean.goodsId.isWholesale==0 }">
									  <p class="shopcar-div-a">${bean.goodsAttr}</p>
									</c:if>
									
								</div>
							</a>
						</div>
						<div class="shopcar-redact">
						   <c:if test="${bean.goodsId.isWholesale==0 }">
						        <c:if test="${bean.goodsId.suorceGoods!=5}">
						           <c:if test="${bean.goodsId.isCanUserCou==0 }">
						              <p class="shopcar-money">${bean.goodsAttrStockId.price}</p>
						           </c:if>
						           <c:if test="${bean.goodsId.isCanUserCou==1 }">
						              <p class="" style="font-size: 0.30rem;color: #f92c2c;text-align: right;">${bean.goodsId.price}积分</p>
						           </c:if>
						           <c:if test="${bean.goodsId.isCanUserCou==3 }">
						              <p class="" style="font-size: 0.30rem;color: #f92c2c;text-align: right;">${bean.goodsId.price}代金券</p>
						           </c:if>
						          
						        </c:if>
						         <c:if test="${bean.goodsId.suorceGoods==5}">
						           <c:if test="${bean.goodsId.isCanUserCou==0 }">
						              <p class="shopcar-money">${bean.goodsId.price}</p>
						           </c:if>
						           <c:if test="${bean.goodsId.isCanUserCou==1 }">
						              <p class="" style="font-size: 0.30rem;color: #f92c2c;text-align: right;">${bean.goodsId.price}积分</p>
						             <%--  <p class="shopcar-money">${bean.goodsId.pointNicePrice}</p> --%>
						              
						           </c:if>
						           <c:if test="${bean.goodsId.isCanUserCou==3 }">
						              <%-- <p class="shopcar-money">${bean.goodsId.pointNicePrice}+${bean.goodsId.price}代金券</p> --%>
						              <p class="" style="font-size: 0.30rem;color: #f92c2c;text-align: right;">${bean.goodsId.price}代金券</p>
						           </c:if>
						           
						        </c:if>
									
							</c:if>
							<c:if test="${bean.goodsId.isWholesale==1 }">
							    <c:if test="${bean.goodsId.suorceGoods!=5}">
						          <c:if test="${merchantType==0 }">
						              <p class="shopcar-money">${bean.goodsAttrStockId.price}</p>
						            </c:if>
						            <c:if test="${merchantType==1 }">
						              <p class="shopcar-money">${bean.goodsAttrStockId.standardPrice}</p>
						            </c:if>
						            <c:if test="${merchantType==2 }">
						               <p class="shopcar-money">${bean.goodsAttrStockId.honourPrice}</p>
						            </c:if>
						        </c:if>
						        <c:if test="${bean.goodsId.suorceGoods==5}">
						          <c:if test="${merchantType==0 }">
						              <p class="shopcar-money">${bean.goodsId.price}</p>
						            </c:if>
						            <c:if test="${merchantType==1 }">
						              <p class="shopcar-money">${bean.goodsId.standardPrice}</p>
						            </c:if>
						            <c:if test="${merchantType==2 }">
						               <p class="shopcar-money">${bean.goodsId.honourPrice}</p>
						            </c:if>
						        </c:if>
									
							</c:if>
						    
							
							<p class="shopcar-count" id="shopcar-count">${bean.goodsNum}</p>
							<p class="shopcar-count-input clearfix">
								<i class="shopcar-cut shopcar-cuti1"></i>
									<span class="shopcar-cut-input">${bean.goodsNum}</span>
								<i class="shopcar-cut shopcar-cuti2"></i>
							</p>
							<input type="hidden" class="car-Id" value="${bean.id}">
							
							<input type="hidden" id="pusNum" value="${bean.goodsId.purchasNum}">
							<input type="hidden" id="mulNum" value="${bean.goodsId.multipleNumber}">
							<input type="hidden" id="logist" value="${bean.logist}">		
						</div>
					</div>
				</li>
			</c:forEach>
			</ul>
			<input type="hidden" class="cartIds" name="cartIds"  value="">
		</form>
	</section>
	<footer class="shopcar-footer clearfix">
		<div class="shopfooter-left">
			<input type="checkbox" class="check-all">
			<label class="checkbox-all"></label>
			<span>全选</span>
		</div>
		<div class="shopfooter-right">
			<div class="shopcar-pay">
				<p class="shopcar-pink">合计：<span class="shopcar-allmoney">0</span></p>
				<p class="shopcar-freight">不含运费</p>
			</div>
			<!-- <p class="shopcar-submit">
				结算(<span class="shop-submitcount">0</span>)
			</p> -->
			<button type="button" class="shopcar-submit" disabled="disabled">
				结算(<span class="shop-submitcount">0</span>)
			</button>
		</div>
	</footer>
	<script type="text/javascript">
	document.title = "购物车(0)";
	$("button").on('click',function(){
		$.ajax({
			url:"${ctx}/wx/order/isHasStock",
			method:"GET",
			data:{
				cartIds:$(".cartIds").val()
			},
			success:function(data){
				if(data.code==200){
					$('form').submit();
				}else{
					 layer.open({
		                  content:data.msg,
		                  btn:'我知道'
		              });
				}
			}
		})
			
	})
			
	function ajaxUpdateNum(id,num){
       $.ajax({
           url: '${ctx}/wx/cart/update',
           type: 'post',
           dataType: 'json',
           data: {
        	    "id":id,
           		"goodsNum":num
           	},
           success:function(data){
        	   if(data.code==200){
        		   layer.open({
                       content:data.msg,
                       btn:'我知道',
                       yes:function(index){
                    	   location.reload() 
                       }
                  });
        		 
        	   }else{
        		   layer.open({
                       content:data.msg,
                       btn:'我知道'
                  });  
        	   }
        	    
           },
           error:function(){
        	   //layer.alert("网络异常");
           }
       })
     }
	
	function ajaxDel(obj){
		layer.open({
            content: '是否移除购物车商品',
            btn: ['是', '否'],
            yes: function(index){//点击去认证页面，index为该特定层的索引
            	var id=$(obj).siblings(".hic-car-Id").val();
     	       $.ajax({
     	           url: '${ctx}/wx/cart/del/'+id,
     	           type: 'get',
     	           dataType: 'json',
     	           data: null,
     	           async:false,
     	           success:function(data){
     	               	if(data.code==200){
     	               		 $(obj).parents("li").remove();
     	               		moneyAll1();
                             //$(".head-count").html();
                             document.title = "购物车("+$('.shop-car li').length+")"
     	               	}
     	           		layer.open({
     	                       content:data.msg,
     	                       btn:'我知道'
     	                  });
     	           },
     	           error:function(){
     	        	   layer.open({
                            content:"删除失败",
                            btn:'我知道'
                       });
     	           }
     	       })
                layer.close(index);
            },
            no:function(index){
                layer.close(index);
            }
        });
		
	   }
	
	//选中
    function moneyAll1(){
        var money = 0;
        var cartIds="";
        for(i = 0;i < $('.check-after').length;i++){
            var that = $('.check-after').eq(i);
            var eq = $('.shopcar-checkbox').index(that);
            //计算金额
            money += $('.shopcar-money').eq(eq).text() * $('.shopcar-count').eq(eq).text();
            //购物车信息
            cartIds+=$('.car-Id').eq(eq).val()+",";
        }
        $(".cartIds").val(cartIds);
        $(".shopcar-allmoney").html(money);
        $(".shop-submitcount").html($('.check-after').length);
    }
	</script>
</body>
</html>