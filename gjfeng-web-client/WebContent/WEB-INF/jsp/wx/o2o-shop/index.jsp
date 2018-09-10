<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp"%>
<script type="text/javascript"
	src="https://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
<body>
	<div class="container">
		<div class="index-header clearfix">
			<div class="header-1 left">
				<a href="${ctx}/wx/address/toLocal" class="header-location">
					<span class="location-text">
							<c:if test="${not empty sessionScope.cityName}">${sessionScope.cityName}</c:if>
							<c:if test="${empty sessionScope.cityName}">广州市</c:if>
					</span>
					<span class="location-icon-box"><i class="main-sprite icon-to-bottom"></i></span>
				</a>
			</div>
			<div class="header-2 right">
				<a href="${ctx}/wx/index/o2o/toSearch" class="header-search"> <i
					class="main-sprite icon-magnifying"></i> <span
					class="index-search-text">搜索商家或商品</span>
				</a>
			</div>
		</div>
		<div class="swiper-container-type">
			<div class="swiper-wrapper">
				<c:forEach items="${indexColumn.result}" var="bean"
					varStatus="status">
					<div class="swiper-slide">
						<a href="${ctx}/wx/index/o2o/subColumn/${bean.id}"
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
			<div class="like-title">
				<div class="like-wrap">
					<i class="main-sprite icon-red-crown"></i> <span
						class="yourlike-text">猜你喜欢</span>
				</div>
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
		document.title = "首页";
    	var pageNo=1;
		var pageSize=10;
        $(function(){
        	$(".icon-nav-1").siblings().removeClass("nav-link-active");
   			$(".icon-nav-1").addClass("nav-link-active");
   			$(".icon-nav-1").parent().siblings().removeClass("nav-link-active");
   			$(".icon-nav-1").parent().addClass("nav-link-active");
   			
   			var localStatus="${sessionScope.localStatus}";
        	if(null == localStatus || "" == localStatus || localStatus != "1"){
	   			var code=GetQueryString("code")
	   			var state=GetQueryString("state")
	   			var url="/wx/index/o2o";
	   			if(code&&state){
	   				url+="?code="+code+"&state="+state
	   			}
	   			$.ajax({
					type: "POST", //用POST方式传输
					url: '${ctx}/wx/localSign', //目标地址
					data: {
						lastPath:url
					},
					success:function(data){
						//wxAddress(data);
	 					//alert(location.href.split('#')[0]) 
						var appId=data.appId;
						var timestamp=data.timestamp;
						var nonceStr=data.noncestr;
						var signature=data.signature
			        	
			        	wx.config({
			        		debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			        	    appId: appId, // 必填，公众号的唯一标识
			        	    timestamp:timestamp, // 必填，生成签名的时间戳
			        	    nonceStr: nonceStr, // 必填，生成签名的随机串
			        	    signature: signature,// 必填，签名，见附录1
			        	    jsApiList: ['getLocation'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
			        	});    		        	
				 		wx.ready(function () {
					 		wx.getLocation({
				   			    type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
				   			    success: function (res) {
				   			        var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
				   			        var longitude = res.longitude ; // 经度，浮点数，范围为180 ~ -180。
				   			        var speed = res.speed; // 速度，以米/每秒计
				   			        var accuracy = res.accuracy; // 位置精度
				   			        //sessionStorage.setItem("latitude", latitude);
				   			        //sessionStorage.setItem("longitude", longitude)
				   			     	ajaxGetCityName(latitude,longitude);
				   			    },		   			  
				   			  
				    		});
					 		
				     });
				      wx.error(function(res) {
				    	 //alert(res.errMsg);	    	
				     }); 
		         	},
		         	error: function(XMLHttpRequest, Status, errorMsg){
						
		            }
				});
        	}
   			
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
            
            $(".loading-bottom").removeClass('hidden');
            ajaxLoad();

            $(window).scroll(function() {
                var totalHeight = $(window).height()+$(window).scrollTop();
                var docHeight = $(document).height();
                if(totalHeight>=docHeight){//拉到底部触发
                    $(".loading-bottom").removeClass('hidden');
                    ajaxLoad();
                }
            });
        })
        
        function ajaxLoad(){
        	alert("ddd")
        	$.ajax({
                url: '${ctx}/wx/index/o2o/like',
                type: 'POST',
                dataType: 'html',
                data: {
                	"pageNo":pageNo,
                	"pageSize":pageSize
                },
                success:function(data){
                	 if(null != data && "" != data){
                     	++pageNo;
                     	$(".like-list").append(data);
                     }
                    $(".loading-bottom").addClass('hidden');
                },
                error:function(){
                	$(".loading-bottom").addClass('hidden');
                }
            });
        }
        
        function ajaxGetCityName(latitude,longitude){
        	alert(latitude+"dd"+longitude)
        	$.ajax({
                url: '${ctx}/wx/address/getCityName',
                type: 'post',
                dataType: 'json',
                data: {
                	"latitude":latitude,
                	"longitude":longitude
                },
                success:function(data){
                	if(null != data.result && "" != data.result){
                		$(".location-text").text(data.result);
                	}
                },
                error:function(){
                	
                }
            });
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