<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body style="background-color: #efefef;">
    <div class="container">
        <div class="wrap-top">
            <div class="wrap-item-1">
                <img src="${store.storeBanner}" class="gd-img">
            </div>
            <div class="wrap-item-2 clearfix">
                <div class="wrap-m-left left">
                    <span class="shopInfo-title">${store.storeName}</span>
                </div>
            </div>
        </div>
        <div class="wrap-shopInfo">
            <div class="shopInfo-title">商家信息</div>
            <ul class="business-list" style="position: relative;">
                <li class="business-li clearfix">
                    <i class="main-sprite icon-shop left"></i>
                    <div class="b-li-left left">
                        <span class="business-text">${store.storeName}</span>
                    </div>
                </li>
                <li class="business-li clearfix">
                    <i class="main-sprite icon-address left"></i>
                    <div class="b-li-left left">
                        <span class="business-text">${store.addressDetail}</span>
                        <p class="near-distance">距离我最近
                        <c:if test="${store.distance >= 1000 || store.distance <= -1000}">${store.distance/1000}km</c:if>
                 		<c:if test="${-1000 < store.distance && store.distance < 1000}">${store.distance}m</c:if>
                        </p>
                    </div>
                </li>
                <div class="call-business">
                    <a href="tel:${store.sellerMobile}"><i class="main-sprite icon-call"></i></a>
                </div>
            </ul>
        </div>
        <div class="yourlike-box shop-hot-goods">
            <div class="like-title">
                <div class="like-wrap">
                    <i class="main-sprite icon-red-crown"></i>
                    <span class="yourlike-text">本店热销</span>
                </div>
            </div>
            <ul class="like-list">
                <div class="loading-bottom hidden">
                    <img src="${imagePath}/wx/o2o-shop/load.gif" class="loadImg">
                    <span class="loadBottom-text">加载中...</span>
                </div>
            </ul>
        </div>
    </div>
    <script type="text/javascript">
        $(function(){
        	document.title = "商品详情";
        	var pageNo=1;
    		var pageSize=10;
    		var lockStatus=true;
    		var onScroll = function() {
                var totalHeight = $(window).height()+$(window).scrollTop();
                var docHeight = $(document).height();
                if(totalHeight>=docHeight){//拉到底部触发
                    $(".loading-bottom").removeClass('hidden');
                		if(!lockStatus){
                			return false;
                		}
                		lockStatus=false;
                    	$.ajax({
                            url: '${ctx}/wx/product/o2o/hotProduct2',
                            type: 'post',
                            dataType: 'html',
                            data: {
                            	"id":"${id}",
                            	"pageNo":pageNo,
                            	"pageSize":pageSize
                            },
                            success:function(data){
                            	 if(null != data && "" != data){
                                 	++pageNo;
                                 	lockStatus=true;
                                 	$(".like-list").append(data);
                                 }else{
                                	 lockStatus=false;
                                 }
                                $(".loading-bottom").addClass('hidden');
                            },
                            error:function(){
                            	lockStatus=true;
                            	$(".loading-bottom").addClass('hidden');
                            }
                        });
                    }
            };
            $(window).scroll(onScroll);
            onScroll();
        });
    </script>
</body>
</html>