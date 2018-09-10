<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
    <div class="container">
        <div class="index-header clearfix sub-header">
            <div class="header-1 left">
                <a href="${ctx}/wx/address/toLocal" class="header-location">
                    <span class="location-text">
                    		<c:if test="${not empty sessionScope.cityName}">${sessionScope.cityName}</c:if>
							<c:if test="${empty sessionScope.cityName}">广州市</c:if>
                    </span>
                    <span class="location-icon-box"><i class="main-sprite icon-to-bottom icon-black-down"></i></span>
                </a>
            </div>
            <div class="header-2 right">
                <a href="${ctx}/wx/index/o2o/toSearch?columnId=${columnId}&columnType=1" class="header-search">
                    <i class="main-sprite icon-magnifying-4"></i>
                    <span class="index-search-text">搜索商家或商品</span>
                </a>
            </div>
        </div>
        <c:if test="${not empty subAds.result}">
        <div class="swiper-container-banner">
            <div class="swiper-wrapper">
            <c:forEach items="${subAds.result}" var="bean" varStatus="status">
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
        <div class="swiper-container-type">
            <div class="swiper-wrapper stop-swiping">
            <c:forEach items="${subColumns.result}" var="bean" varStatus="status">
                <div class="swiper-slide">
                    <a href="${ctx}/wx/product/o2o/subColumn/${bean.id}" class="type-link">
                        <img src="${bean.pic2}" class="type-img sub-img">
                        <div class="type-text">${bean.shortName}</div>
                    </a>
                </div>
               </c:forEach>
            </div>
        </div>
        <div class="yourlike-box sub-item-box">
            <div class="sub-nav">
                <ul class="sub-nav-ul clearfix">
                    <li class="sub-nav-li left sub-nav-active" data-value="1">距离近</li>
                    <li class="sub-nav-li left" data-value="2">人气高</li>
                    <li class="sub-nav-li left" data-value="3">价格低</li>
                    <li class="sub-nav-li left" data-value="4">最新</li>
                </ul>
            </div>
            <ul class="like-list">
                 <div class="loading-middle hidden">
                    <img src="${imagePath}/wx/o2o-shop/load.gif" class="loadImg">
                </div>
                <div class="loading-bottom hidden">
                    <img src="${imagePath}/wx/o2o-shop/load.gif" class="loadImg">
                    <span class="loadBottom-text">加载中...</span>
                </div>
            </ul>
        </div>
    </div>
    <script type="text/javascript">
    	document.title = "栏目";
    	var pageNo=1;
    	var pageSize=10;
        $(function(){
            var swiper1 = new Swiper('.swiper-container-type', {
                slidesPerView: 5,
                slidesPerColumn: 2,
                slidesPerColumnFill : 'row',
                noSwiping:true,
                noSwipingClass:'stop-swiping',
            });
            var swiper2 = new Swiper('.swiper-container-banner', {
                pagination: '.swiper-pagination',
                paginationClickable: true,
            });
                             
            
            ajaxLoad("1",1);
            
            $(".sub-nav-li").on("touchend",function(){
                $(this).addClass("sub-nav-active").siblings().removeClass('sub-nav-active');
                var value = $(this).attr("data-value");
                $(".loading-middle").removeClass("hidden");
                pageNo=1;
                if(value=="1"){//距离近
                	ajaxLoad(value,1);
                }else if(value== "2"){//人气高
                	ajaxLoad(value,1);
                }else if(value =="3"){//价格高
                	ajaxLoad(value,1);
                }else if(value =="4"){//最新
                	ajaxLoad(value,1);
                }
            })

            $(window).scroll(function() {
                var totalHeight = $(window).height()+$(window).scrollTop();
                var docHeight = $(document).height();
                if(totalHeight>=docHeight){//拉到底部触发
                    $(".loading-bottom").removeClass('hidden');
                    var value = $(".sub-nav-active").attr("data-value");
                    ajaxLoad(value,0);
                }
            });
        })
        
        function ajaxLoad(orderType,status){
        	$.ajax({
                url: '${ctx}/wx/product/o2o/products',
                type: 'POST',
                dataType: 'html',
                data: {
                	"pageNo":pageNo,
                	"pageSize":pageSize,
                	"columnId":"${columnId}",
                	"orderType":orderType,
                	"columnType":'1',
                	"likeValue":""
                },
                success:function(data){
                	 if(null != data && "" != data){
                     	++pageNo;
                     	if(status==1){
                     		$(".like-list").empty();
                     	}
                     	$(".like-list").append(data);
                     }
                    $(".loading-bottom").addClass('hidden');
                },
                error:function(){
                	$(".loading-bottom").addClass('hidden');
                }
            });
        }
    </script>
</body>
</html>