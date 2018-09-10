<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp"%>

<style>
#wrapper {
    position: absolute;
    z-index: 1;
    overflow: hidden;
}

.foods-ul {
    background-color: #fff;
}

.foods-ul li {
    border-bottom: 1px solid #CCC;
}

.foods-ul a {
    font-size: 0.28rem;
    color: #5a5a5a;
    display: block;
    padding: 0.62rem;
}

.foods-font {
    float: left;
    line-height: 1.5;
    width: 70%;
    margin-right: 0.4rem;
    padding-top: 0.2rem;
}

.foods-reg {
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    font-size: 16px;
}

.foods-money {
    color: #f92c2c;
    margin: 0.1rem 0;
    font-size: 16px;
}

.foods-buy {
    display: block;
    font-size: 0.2rem;
    width: 5rem;
    height: 2rem;
    text-align: center;
    line-height: 2rem;
    background: #F92C2C;
    border-radius: 0.5rem;
    color: #fff;
}

.foods-img {
    float: right;
     width: 28%;
    height: 10rem;
}

</style>
<script type="text/javascript"
	src="https://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
<body>
	<div class="container">
		<div class="index-header clearfix">
			<div class="header-1 left">
				<%-- <a href="${ctx}/wx/address/toLocal" class="header-location">
					<span class="location-text">
							<c:if test="${not empty sessionScope.cityName}">${sessionScope.cityName}</c:if>
							<c:if test="${empty sessionScope.cityName}">广州市</c:if>
					</span>
					<span class="location-icon-box"><i class="main-sprite icon-to-bottom"></i></span>
				</a> --%>
			</div>
			<div class="header-2 right">
				<%-- <a href="${ctx}/wx/index/o2o/toSearch" class="header-search"> <i
					class="main-sprite icon-magnifying"></i> <span
					class="index-search-text">搜索商家或商品</span>
				</a> --%>
			</div>
		</div>
		<div class="swiper-container-type">
			<div class="swiper-wrapper">
				<c:forEach items="${indexColumn.result}" var="bean"
					varStatus="status">
					<div class="swiper-slide">
						<a href="${ctx}/wx/product/online/products/1/${bean.id}"
							class="type-link"> <img src="${bean.pic2}" class="type-img">
							<div class="type-text">${bean.shortName}</div>
						</a>
					</div>
				</c:forEach>
			</div>
			<div class="swiper-pagination"></div>
		</div>
		<c:if test="${not empty indexAd.result}">
			<div class="swiper-container-banner">
				<div class="swiper-wrapper">
					<c:forEach items="${indexAd.result}" var="bean" varStatus="status">
						<div class="swiper-slide">
						<c:if test="${not empty bean.address}">
							<a href="${bean.address}" class="banner-link">
								<img src="${bean.photo}" class="index-banner">
							</a>
						</c:if>
						<c:if test="${empty bean.address}">
							<a href="javascript:void(0)" class="banner-link">
								<img src="${bean.photo}" class="index-banner">
							</a>
						</c:if>
						</div>
					</c:forEach>
				</div>
				<div class="swiper-pagination"></div>
			</div>
		</c:if>
		<div class="yourlike-box">
			<div id="wrapper" pageNo="2">
	<c:if test="${not empty products.result}">
		<ul class="foods-ul">
		<!-- <div class="foods-banner">
			<img src="../uploadimg/foods-bannner.jpg">
		</div> -->
		<c:forEach var="bean" items="${products.result}" varStatus="status">
			<li>
				<a href="${ctx}/wx/product/online/product/${bean.id}" class="clearfix">
					<div class="foods-font">
						<p class="foods-reg">${bean.name}</p>
						<p class="foods-money">${bean.price}</p>
						<span class="foods-buy">立即购买</span>
					</div>
					<div class="foods-img">
						<img src="${bean.imgUrl}">
					</div>
				</a>
			</li>
		</c:forEach>
		</ul>
	</c:if>
	</div>
			<ul class="like-list">
				<div class="loading-bottom hidden">
					<img src="${imagePath}/wx/o2o-shop/load.gif" class="loadImg">
					<span class="loadBottom-text">加载中...</span>
				</div>
			</ul>
		</div>
		<%@include file="/common/wx-footer.jsp"%>
	</div>
	<script type="text/javascript">
	 
		document.title = "网上商城";
    	var pageNo=1;
		var pageSize=10;
        $(function(){
        	$(".icon-nav-1").siblings().removeClass("nav-link-active");
   			$(".icon-nav-1").addClass("nav-link-active");
   			$(".icon-nav-1").parent().siblings().removeClass("nav-link-active");
   			$(".icon-nav-1").parent().addClass("nav-link-active");
   			
   			
   			
            var swiper1 = new Swiper('.swiper-container-type', {
                pagination: '.swiper-pagination',
                slidesPerView: 5,
                slidesPerGroup:10,
                slidesPerColumn: 2,
                paginationClickable: true,
            });
            var swiper2 = new Swiper('.swiper-container-banner', {
                pagination: '.swiper-pagination',
                paginationClickable: true,
            });
            
            //$(".loading-bottom").removeClass('hidden');
            //ajaxLoad(1,pageSize,1,1,1401,1)
           // var columnId="${indexColumn.result[0].father}"

            $(window).scroll(function() {
            	
                var totalHeight = $(window).height()+$(window).scrollTop();
                var docHeight = $(document).height();
                if(totalHeight>=docHeight){//拉到底部触发
                    $(".loading-bottom").removeClass('hidden');
                    ajaxLoad($("#wrapper").attr("pageNo"),pageSize,1,1,1414,1)
                }
            });
        })
        
         function ajaxLoad(pageNo,pageSize,reqType,refreshType,columnId,type){
	       $.ajax({
	           url: '${ctx}/wx/product/online/products/'+type+'/'+columnId,
	           type: 'get',
	           dataType: 'html',
	           data: {
	           	"pageNo":pageNo,
	           	"pageSize":pageSize,
	           	"reqType":reqType,
	           	"likeValue":$("#likeValue").val()
	           	},
	           success:function(data){	        	   
	               if(null != data && "" != data){	            	   	            	   
		               	if(refreshType==0){
		               		pageNo=1;
		               		$(".foods-ul").empty();
		               	}else if(refreshType==1){		               		
		               		$(".columnId").val(columnId);
		        	    	$(".type").val(type);
		        	    	var pageNo0=parseInt($("#wrapper").attr("pageNo"))+1
                   	        $("#wrapper").attr("pageNo",pageNo0);
		               	}
		               
		               	$(".foods-ul").append(data);
	               }
	              // myScroll.refresh();/**完成的刷新！*/
	           },
	           error:function(){
	        	   myScroll.refresh();/**完成的刷新！*/
	        	   alert("网络异常");
	           }
	       })
	     }
        
       

        function GetQueryString(name){
            var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if(r!=null)
            	return  unescape(r[2]); 
            return null;
        }
    </script>
</body>
</html>