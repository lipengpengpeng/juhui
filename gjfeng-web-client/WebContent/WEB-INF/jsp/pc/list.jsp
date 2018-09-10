<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>采购列表</title>
  <link rel="stylesheet" type="text/css" href="${commonpc}/css/normalize.css" />
  <link rel="stylesheet" type="text/css" href="${commonpc}/css/iconfont.css" />
  <link type="text/css" rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.1.1/css/bootstrap.min.css"/>
  <link rel="stylesheet" href="${commonpc}/css/style.css?ver=1.0" type="text/css" media="all" charset="utf-8" />
  <script src="${commonpc}/js/jquery-1.11.0.min.js"></script>
  <script src="${commonpc}/js/jqPaginator.js"></script>
</head>
<style>
.pagination>.active>a, .pagination>.active>span, .pagination>.active>a:hover, .pagination>.active>span:hover, .pagination>.active>a:focus, .pagination>.active>span:focus {
    z-index: 2;
    color: #FF5722;
    background-color: #ffffff;
    border-color: #FF5722;
    cursor: default;
}
.pagination>li>a, .pagination>li>span {
    position: relative;
    float: left;
    padding: 6px 12px;
    line-height: 1.42857143;
    text-decoration: none;
    color: #e05817;
    background-color: #fff;
    border: 1px solid #ddd;
    margin-left: -1px;
}
</style>
<body> 
  <main class="testPay getClassName proName newFile"></main>
  <main class="list-wrapper main-wrapper">
    <section class="search-container clearfix">
      <form class="search-form"> 
        <input type="text" class="search-input fl" placeholder="输入搜索的商品关键词" value="">
        <button class="search-btn fl">搜索</button>
      </form> 
      <div>
        <span class="iconfont icon-gouwuche fl danger-color shopping-cart " onclick="findMyCart()"></span>
      </div>
    </section>
    <section class="sort-container clearfix">
      <div class="sort-box1 fl">
        <div>
          <span class="iconfont iconfont-large icon-fenlei sort-icon"></span>
          <span class="current-sort" data-value="">全部商品分类</span>
          <span class="iconfont iconfont-large icon-shang toggle-icon"></span>
        </div>
        <ul class="sort-list">
          <li class="sort-item1">全部</li>
          <c:forEach var="column" items="${column.result}">
            <li class="sort-item1" data-value="${column.id}">${column.names}</li>
          </c:forEach>         
        </ul>
      </div>
      <ul class="sort-box2 fl">
        <li class="sort-item2 fl active">全部</li>
        <li class="sort-item2 fl" onclick="getMyOrder()">我的订单</li>
         <!--<li class="sort-item2 fl">尊享采购</li>
        <li class="sort-item2 fl">特价采购</li> -->
      </ul>
    </section>
    <section class="filter-container">
      <ul class="filter-list clearfix">
        <li class="filter-item fl active" data-value="0">全部</li>
        <li class="filter-item fl" data-value="1">1折以下</li>
        <li class="filter-item fl" data-value="2">1-2折</li>
        <li class="filter-item fl" data-value="3">3-4折</li>
        <li class="filter-item fl" data-value="4">4折以上</li>
      </ul>
    </section>
    <section class="pdlist-container">
      <ul class="pd-list clearfix">
        <c:forEach var="pro" items="${products.result.resultList}">
          <li class="pd-item fl">
            <div class="pd-img">
          	  <a onclick="goProductDetail(${pro.id})">
              <img src="${pro.imgUrl}" alt=""></a>
            </div>
          <!--   <p class="danger-color">[精品采购]</p> -->
            <h3 class="pd-title ellipsis" style="font-size: 16px;"><a onclick="goProductDetail(${pro.id})">${pro.name} </a></h3>
            <div class="pd-price clearfix">
              <p class="pd-price1 danger-color fl">指导兑换：<span style="font-size: 14px;"><fmt:formatNumber value="${pro.standardPrice*10}" maxFractionDigits="2"/></span></p>
              <p class="fr"><span class="lightgray-color">库存：</span><span>${pro.repertory }</span></p>
            </div>
            <div class="pd-price clearfix">
              <p class="pd-price2 royal-color fl">尊享兑换：<span style="font-size: 14px;"><fmt:formatNumber value="${pro.honourPrice*10 }" maxFractionDigits="2"/></span></p>
              <p class="fr"><span class="lightgray-color">销量：</span><span>${pro.salesNum}</span></p>
            </div>
            <div class="clearfix">
              <p class="pd-price3 lightgray-color fl">零售价：￥<span>${pro.price}</span></p>
              <button class="add2cart fr" onclick="addCart(${pro.id})">加入购物车</button>
           </div>
          </li>
        </c:forEach>
      </ul>
    </section>
  </main>
  <!-- 分页 -->
  <p id="p1"></p>
  <ul class="pagination" id="pagination1" style="padding-left: 500px;"></ul>
  
  
  <script>
  $(document).ready(function() {
    $(".sort-box1").click(function() {
      $(this).toggleClass('open');
      var toggleIcon = $(this).find('.toggle-icon');
      if ($(this).hasClass('open')) {
        toggleIcon.removeClass('icon-shang').addClass('icon-xia')
      } else {
        toggleIcon.removeClass('icon-xia').addClass('icon-shang')
      }
    })
    $(".sort-item1").click(function() {
      $(this).addClass('active').siblings().removeClass('active');
      $('.current-sort').text($(this).text());
      $(".current-sort").attr("data-value",$(this).attr)
      var columnId=$(this).attr("data-value");
      if(columnId==undefined){
    	  columnId="";
      }
      $(".current-sort").attr("data-value",columnId)
      var likeName=$(".search-input").val()
      
      var discount=$(".filter-list .active").attr("data-value")
         
      getMerchantPro(columnId,"1","20",likeName,"","1");
    })
    $(".sort-item2").click(function() {
      $(this).addClass('active').siblings().removeClass('active');
    })
    $(".filter-item").click(function() {
      $(this).addClass('active').siblings().removeClass('active');
      var discount=$(this).attr("data-value");
      
      var columnId=$(".current-sort").attr("data-value")
      var likeName=$(".search-input").val()

      getMerchantPro(columnId,"1","20",likeName,discount,"1");
    })
    
    //搜索
    $(".search-btn").click(function(){
    	var likeName=$(".search-input").val()
    	
    	getMerchantPro("","1","20",likeName,"","1");
    })
    
    //根据条件获取商品信息
    function getMerchantPro(columnId,pageNo,pageSize,likeName,discount,type){
    	$.ajax({
    		url:"${ctx}/pc/merchant/getMechartProList",
    		method:"GET",
    		data:{
    			columnId:columnId,
    			pageNo:pageNo,
    			pageSize:pageSize,
    			likeName:likeName,
    			discount:discount,
    		},
    	   success:function(data){
    		   var str="";

    		   for(var i=0;i<data.result.resultList.length;i++){
    			    var standardPrice=Math.round(parseFloat(data.result.resultList[i].standardPrice*10)*100)/100
    			    var honourPrice=Math.round(parseFloat(data.result.resultList[i].honourPrice)*100)/100
    			    str+="<li class='pd-item fl'><div class='pd-img'>"
    	          	        +"<a onclick='goProductDetail("+data.result.resultList[i].id+")'>"
    	                    +"<img src='"+data.result.resultList[i].imgUrl+"' alt=''></a></div>"
    	                    +"<h3 class='pd-title ellipsis' style='font-size: 16px;'><a onclick='goProductDetail("+data.result.resultList[i].id+")'>"+data.result.resultList[i].name+" </a></h3>"
    	                    +"<div class='pd-price clearfix'><p class='pd-price1 danger-color fl'>指导兑换：<span style='font-size: 14px;''>"+standardPrice+"</span></p>"
    	                    +"<p class='fr'><span class='lightgray-color'>库存：</span><span>"+data.result.resultList[i].repertory+"</span></p></div>"
    	                    +"<div class='pd-price clearfix'><p class='pd-price2 royal-color fl'>尊享兑换：<span style='font-size: 14px;'>"+honourPrice+"</span></p>"
    	                    +"<p class='fr'><span class='lightgray-color'>销量：</span><span>"+data.result.resultList[i].salesNum+"</span></p></div><div class='clearfix'>"
    	                    +"<p class='pd-price3 lightgray-color fl'>零售价：￥<span>"+data.result.resultList[i].price+"</span></p><button class='add2cart fr' onclick='addCart("+data.result.resultList[i].id+")'>加入购物车</button>"
    	                    +"</div></li>";
    		   }
    		   $(".pd-list").html(str)
    		   if(type=="1"){
    			   var totalPages=data.result.pageCount
    			   var currentPage=data.result.prePageNo
    			   if(totalPages>=1){
    				   //分页
            		   $.jqPaginator('#pagination1', {
            		        totalPages: totalPages,
            		        visiblePages: 10,
            		        currentPage: currentPage,
            		        onPageChange: function (num, type) {
            		        	getMerchantPro(columnId,num,pageSize,likeName,discount);
            		        }
            		   });  
    			   }
    			  
    		   }
    		  
    	   }
    	})
    }
    
    //分页
    var totalPage=${products.result.pageCount};
    var currentPage=${products.result.prePageNo};
    
	$.jqPaginator('#pagination1', {
	       totalPages: totalPage,
	       visiblePages: 10,
	       currentPage: currentPage,
	       onPageChange: function (num, type) {
	    	   if(num!=1){
	    		   getMerchantPro("",num,20,"","","0");
	    	   }
	        
	       }
	  });
    
   
	
  })
  
   //添加购物车  
   function addCart(id){
	    var merchantType="${sessionScope.merchantType}";
	    if(merchantType==""){
			  window.location.href="${ctx}/pc/merchant/goLoginPage" 
		}
    	window.location.href="${ctx}/pc/merchant/online/product/"+id+"?type="+merchantType
    }
  
  //进入购物车
  function findMyCart(){
	  window.location.href="${ctx}/pc/merchant/myCart";
  }
  
  //点击跳到商品详情页面
  function goProductDetail(id){
	  var merchantType="${sessionScope.merchantType}";
	  if(merchantType==""){
		  window.location.href="${ctx}/pc/merchant/goLoginPage" 
	  }
	  window.location.href="${ctx}/pc/merchant/online/product/"+id+"?type="+merchantType
  }
  
  //我的订单
  function getMyOrder(){
	  window.location.href="${ctx}/pc/merchant/findMemberOrderInfo?pageNo=1&pageSize=20&status=0"
  }
  </script>
</body>

</html>