<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=<%= cc.messcat.gjfeng.common.util.BaiduApi.KEY %>"></script>
<body>
    <div class="container">
    <c:if test="${not empty sessionScope.cityName}">
        <div class="index-header clearfix sub-header">
            <div class="header-1 left">
                <a href="${ctx}/wx/address/toLocal" class="header-location">
                    <!-- <span class="location-text location-near">未知位置</span> -->
                    <span class="location-text location-near">${sessionScope.cityName}</span>
                    <span class="location-icon-box"><i class="main-sprite icon-to-bottom icon-black-down"></i></span>
                </a>
            </div>
            <div class="header-2 right location-top-right">
                <a href="#" class="header-search">
                    <i class="main-sprite icon-magnifying-4"></i>
                    <span class="index-search-text">搜索商家或商品</span>
                </a>
            </div>
        </div>
       </c:if>
        <!-- start获取不到位置 -->
        <c:if test="${empty sessionScope.cityName}">
        <div class="location-box">
            <div class="location-null">
                <img src="${imagePath}/wx/o2o-shop/location-failure.png" class="location-null-img">
                <p class="location-row">无法获取您的位置</p>
            </div>
            <div class="location-choose-city">
                <a href="${ctx}/wx/address/toLocal" class="to-city-link">选择城市</a>
            </div>
        </div>
        </c:if>
        <!-- end获取不到位置 -->
        <c:if test="${not empty sessionScope.cityName}">
        <div class="yourlike-box sub-item-box near-shop-box">
            <div class="sub-nav">
                <ul class="sub-cont-ul clearfix">
                <c:forEach items="${nearColumns.result}" var="bean" varStatus="status">
                	<c:if test="${not empty bean}">
                    	<li class="sub-cont-li left<c:if test="${status.count == 1}"> sub-cont-active</c:if>" data-value="${status.count}">${bean.key}</li>
                    </c:if>
                </c:forEach>
                </ul>
            </div>
            <div>
            <c:forEach items="${nearColumns.result}" var="beanMap" varStatus="statusMap">
                <div class="near-type-${statusMap.count}<c:if test="${statusMap.count != 1}"> hidden</c:if>">
                    <div class="swiper-container-select ss-${statusMap.count}">
                        <div class="swiper-wrapper">
                        	 <c:forEach items="${beanMap.value}" var="bean" varStatus="status">
                        	 <c:if test="${not empty bean}">
                            	<div class="swiper-slide slide-select<c:if test="${status.count == 1}"> slide-select-active</c:if>" data-value="${status.count}" data-id="${bean.id}" data-count="${statusMap.count}">${bean.shortName}</div>
                            </c:if>
                            </c:forEach>
                        </div>
                    </div>
            
                    <ul class="like-list like-list-${statusMap.count}">
                        
                    </ul>
                </div>
               </c:forEach>
            </div>
            <div class="loading-bottom hidden">
                <img src="${imagePath}/wx/o2o-shop/load.gif" class="loadImg">
                <span class="loadBottom-text">加载中...</span>
            </div>
            <div class="loading-middle hidden">
                <img src="${imagePath}/wx/o2o-shop/load.gif" class="loadImg">
            </div>
        </div>
        </c:if>
         <%@include file="/common/wx-footer.jsp" %>
    </div>
    <script type="text/javascript">
    	document.title = "附近";
    
    	var count=1;
    	var tabClickNum1=0;
    	var tabClickNum2=0;
    	var tabClickNum3=0;
    	var tabClickNum4=0;
    	var pageNo1=1;
    	var pageNo2=1;
    	var pageNo3=1;
    	var pageNo4=1;
        var pageSize=10;
        
        $(function(){
        	$(".icon-nav-2").siblings().removeClass("nav-link-active");
   			$(".icon-nav-2").addClass("nav-link-active");
   			$(".icon-nav-2").parent().siblings().removeClass("nav-link-active");
   			$(".icon-nav-2").parent().addClass("nav-link-active");
   			
   			oneColumn(1);
        	
            var swiper1 = new Swiper('.ss-1', {
                slidesPerView:4,
                spaceBetween : 10,
                observer:true,
                observeParents: true,
                slideToClickedSlide:true,
            });
            var swiper2 = new Swiper('.ss-2', {
                slidesPerView:4,
                spaceBetween : 10,
                observer:true,
                observeParents: true,
                slideToClickedSlide:true,
            });
            var swiper3 = new Swiper('.ss-3', {
                slidesPerView:4,
                spaceBetween : 10,
                observer:true,
                observeParents: true,
                slideToClickedSlide:true,
            });
            var swiper4 = new Swiper('.ss-4', {
                slidesPerView:4,
                spaceBetween : 10,
                observer:true,
                observeParents: true,
                slideToClickedSlide:true,
            });

            $(".sub-cont-li").on("touchend",function(){//点击大类享美食，住酒店等
                $(this).addClass("sub-cont-active").siblings().removeClass("sub-cont-active");
                var attr = $(this).attr('data-value');
                $(".near-type-"+attr).removeClass("hidden").siblings().addClass("hidden");
                oneColumn(attr);
            });
            
            $(".slide-select").on("click",function(){//点击二级分类
                $(this).addClass("slide-select-active").siblings().removeClass("slide-select-active");
                var thisParentsID = $(".sub-cont-active").attr("data-value");
                var thisID = $(this).attr("data-value");
                var count=$(this).attr("data-count");
                if(count==1){
                	pageNo1=1;
                }else if(count==2){
                	pageNo2=1;
                }else if(count==3){
                	pageNo3=1;
                }else if(count==4){
                	pageNo4=1;
                }
                
                $(".like-list-"+count).empty();
                ajaxLoad($(this).attr("data-id"),count,"0");
            })

            $(window).scroll(function() {
                var totalHeight = $(window).height()+$(window).scrollTop();
                var docHeight = $(document).height();
                if(totalHeight>=docHeight){//拉到底部触发
                    $(".loading-bottom").removeClass('hidden');
                    var columnId=$(".near-type-"+count).find(".slide-select-active").attr("data-id");
                    ajaxLoad(columnId,count,"1");
                }
            });
            
            //定位
            var localStatus="${sessionScope.localStatus}";
        	if(null == localStatus || "" == localStatus || localStatus != "1"){
            	localAddress();
        	}
        })
        
        //点击一级栏目
        function oneColumn(count){
        	count=count;
        	var columnId=$(".near-type-"+count).find(".slide-select-active").attr("data-id");
        	if(null == columnId || "" == columnId || "undefined"==columnId){
        		return false;
        	}
        	if(count==1){
        		if(tabClickNum1==0){
        			tabClickNum1=1;
                	ajaxLoad(columnId,count,"0");
            	}
        	}else if(count==2){
        		if(tabClickNum2==0){
        			tabClickNum2=1;
                	ajaxLoad(columnId,count,"0");
            	}
        	}else if(count==3){
        		if(tabClickNum3==0){
        			tabClickNum3=1;
                	ajaxLoad(columnId,count,"0");
            	}
        	}else if(count==4){
        		if(tabClickNum4==0){
        			tabClickNum4=1;
                	ajaxLoad(columnId,count,"0");
            	}
        	}
        }
        function ajaxLoad(columnId,count,status){
        	var pageNo=1;
        	if(count==1){
   			 	pageNo=pageNo1;
            }else if(count==2){
            	pageNo=pageNo2;
            }else if(count==3){
            	pageNo=pageNo3;
            }else if(count==4){
            	pageNo=pageNo4;
            }
        	$(".header-search").attr("href","${ctx}/wx/index/o2o/toSearch?columnType=2&columnId="+columnId);
        	$.ajax({
                url: '${ctx}/wx/store/o2o/stores',
                type: 'POST',
                ansyc:false,
                dataType: 'html',
                data: {
                	"pageNo":pageNo,
                	"pageSize":pageSize,
                	"columnId":columnId,
                	"orderType":"1",
                	"columnType":'2',
                	"likeValue":""
                },
                success:function(data){
                	 if(null != data && "" != data){
                		 if(count==1){
                			 ++pageNo1;
                         }else if(count==2){
                        	 ++pageNo2;
                         }else if(count==3){
                        	 ++pageNo3;
                         }else if(count==4){
                        	 ++pageNo4;
                         }
                     	
                     	if(status=="0"){
                     		$(".like-list-"+count).empty();
                     	}
                     	$(".near-type-"+count).removeClass("hidden").siblings().addClass("hidden");
                     	$(".like-list-"+count).append(data);
                     }
                    $(".loading-bottom").addClass('hidden');
                },
                error:function(){
                	$(".loading-bottom").addClass('hidden');
                }
            });
        }
        
		function localAddress() {
			var geolocation = new BMap.Geolocation();
			geolocation.getCurrentPosition(
				function(r) {
					if (this.getStatus() == BMAP_STATUS_SUCCESS) {
						$.ajax({
							url : '${ctx}/wx/address/getCityName',
							type : 'post',
							dataType : 'json',
							data : {
								"latitude" : r.point.lat,
								"longitude" : r.point.lng
							},
							success : function(data) {
								window.location.reload();
							},
							error : function() {
								alert('定位城市失败');
							}
						});
					} else {
						alert('failed'
								+ this.getStatus());
					}
				}, {
					enableHighAccuracy : true
				});
		}
</script>
</body>
</html>