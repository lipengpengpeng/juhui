<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-shop-top.jsp" %>

<style>
*{margin:0;padding:0;box-sizing:border-box;}
.clearfix:after{font-size:0;display:block;visibility:hidden;clear:both;height:0;content:' ';}
.fl{float:left;}
.fr{float:right;}
ul{list-style:none;}
input{border:none;outline:none;}
body{background-color:rgb(246,246,246);}
.search-container{position:fixed;left:0;top:0;width:100%;line-height:36px;padding:5px 8px;background-color:transparent;z-index:100;}
.search-container .search-box{width:88%;padding:0 15px;background-color:#fff;border-radius:20px;}
.search-container .search-box .search-icon{display:inline-block;width:20px;height:20px;vertical-align:middle;}
.search-container .search-box input{padding-left:8px;font-size:15px;height:36px;line-height:36px;width:80%;}
.search-container .message-icon{width:25px;height:25px;margin-top:7px;}
.swiper-container img{width:100%;}
.nav-container,
.nav-container,
.activity-container{background-color:#fff;}
.nav-container .nav-item{float:left;width:19%;margin:10px 0;text-align:center;margin-left: 20px;}
.nav-container .nav-item.large{width:22%;}
.nav-container .nav-item img{width:50px;height:50px;}
.nav-container .nav-item p{font-size:12px;color:#9a9595;}
.high-container{background-color:#fff;margin-top:3px;}
.high-container img{width:100%;}
.merchant-container{padding:10px;}
.merchant-container img{width:100%;}
.hot-container{padding-bottom:10px;background-color:#fe1e36;}
.hot-container .hot-box .hot-header{position:relative;color:#fff;font-weight:bold;text-align:center;}
.hot-container .hot-box .hot-header img{width:100%;height: 40px}
.hot-container .hot-box .hot-header span{position:absolute;left:50%;top:50%;-webkit-transform:translate(-50%,-50%);transform:translate(-50%,-50%);font-size:15px;z-index:5;}
.hot-container .pd-item{width:30.6%;margin-left:2%;padding:3px;background-color:#fff;}
.hot-container .pd-item .img-cover{position:relative;width:100%;overflow:hidden;padding:50% 0;background-color:#fff;border-radius:2px;}
.hot-container .pd-item .img-cover img{width:100%;position:absolute;left:50%;top:50%;-webkit-transform:translate(-50%,-50%);transform:translate(-50%,-50%);border-radius:2px;}
.hot-container .type-item .type-title{color:#fff;font-size:14px;font-weight:700;text-align:center;border-radius:20px;margin:10px;padding:3px 0;}
.hot-container .pd-item .title{height:40px;line-height:20px;}
.hot-container .pd-item .title h3{display:-webkit-box;-webkit-box-orient:vertical;-webkit-line-clamp:2;text-overflow:ellipsis;overflow:hidden;padding:0 2px;font-size:13px;font-weight:normal;word-wrap:break-word;word-break:break-all;color:#333;}
.hot-container .pd-item .price{margin-top:5px;font-size:10px;text-align:right;}
.hot-container .pd-item .price span{color:#ff7625;font-size:15px;}
.activity-container{background-color:rgb(242,242,242);}
.activity-container .activity-header{padding:10px 0;font-size:18px;font-weight:bold;letter-spacing:5px;text-align:center;background:url(/gjfeng-web-client/common/image/wx/online-shop/20180529_2.png) no-repeat;background-size:100%;}
.activity-container .activity-title{float:left;width:100%;font-size:16px;font-weight:400;text-align:center;padding:12px;letter-spacing:2px;border-bottom:1px solid #eee;}
.activity-container .activity-item{width:50%;min-height:100px;padding:10px 0;border-right:1px solid #eee;border-bottom:1px solid #eee;background-color:white;box-shadow:0 0 1px rgba(120,120,120,0.3);}
.activity-container .activity-item:nth-of-type(2n+1){border-right:none;}
.activity-item .activity-text{width:60%;padding-left:8px;}
.activity-item .activity-thumb{width:40%;}
.activity-item .activity-thumb img{width:100%;padding-right:8px;}
.activity-item .activity-name{font-size:16px;}
.activity-item .activity-text p{font-size:10px;color:rgb(153,153,153);margin:3px 0;}
.activity-item .activity-text .activity-desc{color:#fff;font-size:8px;padding:3px 5px;border-radius:3px;}
.jianbian-bg1{background:-webkit-linear-gradient(left,rgb(252,10,34),rgb(242,134,0));}
.jianbian-bg2{background:-webkit-linear-gradient(left,rgb(248,44,131),rgb(250,92,65));}
.jianbian-text1{display:inline-block;background:-webkit-linear-gradient(left,rgb(73,38,180),rgb(7,158,227));-webkit-background-clip:text;-webkit-text-fill-color:transparent;}
.tupiaowenzi{margin-right: 0.5rem;margin-top: 0.1rem;}
</style>


<body>
     <%-- <header class="search-container clearfix">
       <div class="search-box fl">
            <img src="${imagePath}/wx/online-shop/search.png" alt="" class="search-icon">
            <input type="text" placeholder="搜索自营商品...">
        </div>
        <img src="${imagePath}/wx/online-shop/message.png" alt="消息" class="fr message-icon"> 
    </header>--%>
    <main>
       
           <div class="swiper-container" >
		    <div class="swiper-wrapper">		    
		     <c:forEach var="bean" items="${indexAds}" varStatus="status">
		    	 <c:if test="${not empty bean.address}">
		    		<a href="${bean.address}" class="swiper-slide"><img src="${bean.photo}"></a>
		    	 </c:if>
		    	 <c:if test="${empty bean.address}">
		    		<a href="javascript:void(0)" class="swiper-slide"><img src="${bean.photo}"></a>
		    	 </c:if>
		       </c:forEach>
		      <%--  <a href="" class="swiper-slide"><img src="${imagePath}/wx/online-shop/微信图片_20180212144506.jpg"></a>
		       <a href="" class="swiper-slide"><img src="${imagePath}/wx/online-shop/微信图片_20180212144506.jpg"></a> --%>
		    </div>
		    <div class="pagination"></div>
		  </div> 
       
        <nav class="nav-container clearfix">
            <div onclick="goTianMao()"  class="nav-item">
                <img src="${imagePath}/wx/online-shop/taobao.jpg" alt="">
                <p class="tupiaowenzi">淘宝</p>
            </div>
            <div onclick="goTianMao()" class="nav-item">
                <img src="${imagePath}/wx/online-shop/tianmao.png" alt="">
                <p class="tupiaowenzi">天猫</p>
            </div>
            <%-- <div href="" class="nav-item large">
                <img src="${imagePath}/wx/online-shop/20180529_8.png" alt="">
                <p>京东</p>
            </div> --%>
            <div onclick="getProd()" class="nav-item">
                <img src="${imagePath}/wx/online-shop/ziying.png" alt="">
                <p class="tupiaowenzi">自营商城</p>
            </div>
            <div onclick="getAllColumns()" class="nav-item">
                <img src="${imagePath}/wx/online-shop/column.png" alt="">
                <p class="tupiaowenzi">所有分类</p>
            </div>
        </nav>
        <section class="high-container">
            <a href="${ctx}/wx/product/online/products/1/1415"><img src="${imagePath}/wx/online-shop/20180529_1.png" alt=""></a>
        </section>
        <section class="hot-container">
            <div class="hot-box">        
               <c:if test="${not empty  indexSupColumns}">
               
                 <c:forEach var="subColumns" items="${indexSupColumns}">
                 
                   <c:if test="${subColumns.faterColumnFrontNum eq 'TODAYWILLROB'}">
                      <div class="hot-header">
                         <img src="${imagePath}/wx/online-shop/20180529_4.png" alt="">
                         <span>${subColumns.faterColumnName }</span>
                      </div>
                      <ul class="type-list clearfix">
                          <c:forEach var="subColunm" items="${subColumns.supColumn }">
                              <li class="type-item pd-item fl" onclick="getColumnPro(${subColunm.id})">
                                 <div class="img-cover">
                                     <img src="${subColunm.pic2 }">
                                 </div>
                                 <div class="type-title jianbian-bg1">${subColunm.names}</div>
                              </li>
                          </c:forEach>
                       </ul>                  
                   </c:if>                                  
                  </c:forEach>
               </c:if> 
            </div>
            
            <c:if test="${not empty  indexProducts}">
               <c:forEach var="indexPro" items="${indexProducts}">
               <div class="hot-box">
                <div class="hot-header">
                    <img src="${imagePath}/wx/online-shop/20180529_4.png" alt="">
                    <span>${indexPro.columnName }</span>
                </div>
                <ul class="pd-list clearfix" >
                    <c:forEach var="pros" items="${indexPro.product }">
                      <li class="pd-item fl" onclick="goProDetail(${pros.id})">
                        <div class="img-cover">
                            <img src="${pros.imgUrl }" alt="">
                        </div>
                        <div class="title">
                            <h3>${pros.name }</h3>
                        </div>
                        <div class="price">
                                                                          零售价:<span class="orange-color">￥${ pros.price}</span>
                        </div>
                       </li>
                    </c:forEach>                                    
                </ul>
              </div>
            
            </c:forEach>
           </c:if>
   
        </section>
        
        <c:if test="${not empty  indexSupColumns}">
           <c:forEach var="subCol" items="${indexSupColumns}">         
            <c:if test="${subCol.faterColumnFrontNum ne 'TODAYWILLROB'}">
            <section class="activity-container clearfix">
            <div class="activity-header" :style="{'background-image':formatBg('${imagePath}/wx/online-shop/20180529_2.png')}">
                <span class="jianbian-text1">${subCol.faterColumnName }</span>
            </div>
            <c:forEach var="subColumn" items="${subCol.supColumn }">
               <div class="activity-item fl" onclick="getColumnPro(${subColumn.id})">
                <div class="activity-text fl">
                    <h3 class="activity-name">${subColumn.names }</h3>
                    <p>${subColumn.intro }</p>
                    <!-- <span class="activity-desc jianbian-bg2">领券购物更优惠</span> -->
                </div>
                <div class="activity-thumb fr">
                    <img src="${subColumn.pic2 }" alt="">
                </div>
               </div>
            </c:forEach>       
           </section>
           </c:if>
          </c:forEach>
        </c:if>
        
        
        <section class="merchant-container" style="margin-bottom: 50px">
            <img src="${imagePath}/wx/online-shop/20180529_3.jpg" alt="开通跨行业盈利系统">
        </section>
    </main>
    <%@include file="/common/wx-footer.jsp" %>
    <script src="${jsPath}/wx/online-shop/idangerous.swiper.min.js"></script>
	<script src="${jsPath}/wx/online-shop/jquery.font.js"></script>
    <script>
    /* var imgHeight = document.querySelector('.swiper-container').offsetHeight,
        a = 0,
        cssText = '';
    var h = document.querySelector('.search-container');
    window.addEventListener('scroll', function() {
        var t = document.documentElement.scrollTop || document.body.scrollTop;
        a = t / imgHeight < 1 ? +(t / imgHeight).toFixed(1) : 1;
        if (t > imgHeight) { cssText = 'rgba(240,85,108,' + a + ')';
            h.style.backgroundColor = cssText; } else { cssText = 'transparent';
            h.style.backgroundColor = cssText; }
    }, false); */
    
    
	$(function(){
   			document.title = "网上商城";
   			/* $(".icon-nav-3").siblings().removeClass("nav-link-active");
   			$(".icon-nav-3").addClass("nav-link-active");
   			$(".icon-nav-3").parent().siblings().removeClass("nav-link-active");
   			$(".icon-nav-3").parent().addClass("nav-link-active"); */
   			
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
   	  
   	  //点击商品
   	  function goProDetail(id){
    	window.location.href="${ctx}/wx/product/online/product/"+id
      }
    
      //点击栏目
      function getColumnPro(colId){
    	  window.location.href="${ctx}/wx/product/online/products/0/"+colId
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
    
    //自营商城
    function getProd(){
       window.location.href="${ctx}/wx/product/online/products/1/1398"
    }
    
    //获取全部栏目
    function getAllColumns(){
    	window.location.href="${ctx}/wx/column/online/allColumn"
    }
   
    </script>
</body>

</html>