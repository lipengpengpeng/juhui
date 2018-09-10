<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
  <title>商品采购</title>
  <link rel="stylesheet" type="text/css" href="${commonpc}/css/normalize.css" />
  <link rel="stylesheet" type="text/css" href="${commonpc}/css/iconfont.css" />
  <link rel="stylesheet" href="${commonpc}/css/style.css?ver=1.2" type="text/css" media="all" charset="utf-8" />
  <script src="${plugInPath}/layer_mobile/layer.js"></script>
</head>

<body>
  <!-- 缩放区域 -->
  <div id="zoom"></div>
  <main id="wrapper " class=" clearfix main-wrapper">
    <!-- 图片区域 -->
    <section id="preview" class="fl">
      <div id="view">
      <img src="${productInfo.imgUrl }" alt="" />
      </div>
      <div id="thumbs">
        <div id="nav-left-thumbs">&lt;
        </div>
        <div id="pics-thumbs">
        
        <c:if test="${productInfo.para1!='' }">
           <img src="${productInfo.para1 }" alt="" />
        </c:if>
         <c:if test="${productInfo.para2!='' }">
           <img src="${productInfo.para2 }" alt="" />
        </c:if>
         <c:if test="${productInfo.para3!='' }">
           <img src="${productInfo.para3 }" alt="" />
        </c:if>
         <c:if test="${productInfo.para4!='' }">
           <img src="${productInfo.para4 }" alt="" />
        </c:if>
         <c:if test="${productInfo.para5!='' }">
           <img src="${productInfo.para5 }" alt="" />
        </c:if>
           
          <!-- 获取轮播图 -->
        </div>
        <div id="nav-right-thumbs">&gt;</div>
      </div>
    </section>
    <!-- 文字信息 -->
    <section class="content fl">
      <h2 class="pd-title"></h2>
      <div class="pd-price clearfix">
        <p class="price-1 fl danger-color">指导兑换：${productInfo.standardPrice*10 }<strong></strong><span>兑换券</span></p>
        <p class="price-2 fl">建议零售价：￥${productInfo.price }<span></span></p>
        <p class="price-3">尊享兑换：${productInfo.honourPrice*10 }<span></span>兑换券</p>
      </div>
      <!-- <div class="right-text clearfix attrs">
        <span>规格：</span>
        <ul class="fl ways">
          <li data-type="1" class="active">规格1</li>
          <li data-type="2">规格2</li>
          <li data-type="3">规格3</li>
          <li data-type="4">规格4</li>
        </ul>
      </div> -->
      <div class="right-text clearfix address" style="padding: 1rem;font-size: 1rem;">
        <span>商品名称：</span>
        <!-- <span class="iconfont icon-location danger-color"></span> -->
        <span>${productInfo.name}</span>
        <!-- <span class="iconfont iconfont-large icon-bianji"></span> -->
      </div>
      <%-- <div class="right-text clearfix fee">
        <span>运输费用：</span>
        <span>${productInfo.postage }</span>元
      </div> --%>
      <div class="right-text clearfix transport">
        <span>运输方式：</span>
        <ul class="fl ways">
          <li data-type="0" class="transport-item active"><span>普通快递</span>
            <!-- <div class="transport-detail">
              <p>货品总重：<span class="danger-color">3.00kg</span></p>
              <p>运费费率：1公斤以内 14.50元 /首重1公斤14.50元，每增加1公斤，增加运费9.00元</p>
              <p>友情提示：物流运费标准为零担（小批量）物流价格，大宗订单请联系客服，我们会为您提供更为便宜、优质的物流服务商。</p>
            </div> -->
          </li>
          <li data-type="1" class="transport-item"><span>普通物流</span>
            <!-- <div class="transport-detail">
              <p>货品体积：<span class="danger-color">1.00立方米</span></p>
              <p>运费费率：起步价80.00元 ，每立方米450元</p>
              <p>友情提示：物流运费标准为零担（小批量）物流价格，大宗订单请联系客服，我们会为您提供更为便宜、优质的物流服务商。</p>
            </div> -->
          </li>
          
        </ul>
        <p class="fl tips">注：物流运费到付，如需物流送货上门请联系当地物流网点。
          <br>德邦物流每票货物需要+18元保险费用。快递费用太高，建议选用物流。</p>
      </div>
      <div class="right-text clearfix repertory">
        <span>库存：</span>
        <p class="danger-color"><span class="place"></span><span class="stock">${productInfo.repertory }</span></p>
      </div>
      <div class="right-text clearfix num">
        <span>选购数量：</span>
        <input type="text" value="1" placeholder="正整数" class="num-input">
        <span>注：样品采购数量为1，批量采购填写<span class="multiple-num">${productInfo.multipleNumber }</span>的倍数</span>
      </div>
      <div class="btn-group clearfix">
         <button class="primary-btn" onclick="addCart(${productInfo.id})">提交到预选</button>
         <button class="primary-btn" onclick="findMyCart()">进入预选区</button>
      </div>
      <c:forEach items="${productAttrs}" var="beanMap" varStatus="status">
		<div class="partic-com-tao clearfix" style="hidden">			
			<c:forEach items="${beanMap.value}" var="bean" varStatus="status1">
				<span data-attr-id="${bean.id}" <c:if test="${status1.count==1}"> class="partic-com-taoactive"</c:if>>${bean.attrValueId.attrValue}</span>
			</c:forEach>
		</div>
      </c:forEach>
    </section>
  </main>
  <div class="pd-detail" style="width: 90%;margin-top: 83px;">
    <h3>商品详情</h3>
    <div>${ productInfo.content}</div>
  </div>
  <a class="go-top" href="javascript:;" style="display: none;">
  	<img src="${commonpc}/img/top.png" alt="">
  	<p>回到顶部</p>
  </a>
  <script src="http://cdn.bootcss.com/jquery/1.11.0/jquery.min.js" type="text/javascript"></script>
  <script>
  window.jQuery || document.write('<script src="${commonpc}/js/jquery-1.11.0.min.js"><\/script>')
  </script>
  <script type="text/javascript" src="${commonpc}/js/prefixfree.min.js"></script>
  <script type="text/javascript" src="${commonpc}/js/zoom-slideshow.js"></script>
  <script type="text/javascript" src="${commonpc}/js/md5.min.js"></script>
  <script>
  $(document).ready(function() {
	  
	
	
	//选择物流方式
	$(".transport-item").click(function(){
		$(this).addClass('active').siblings().removeClass('active');
	})
	  
   /*  $.ajax({
        url: 'http://yg.gjfeng.com/gjfeng-web-client/app/product/v1_0/online/onlineProductsDetailInfoInH5',
        type: 'POST',
        dataType: 'json',
        data: { id: 133888, token: md5('onlineProductsDetailInfoInH5133888') },
        headers: { 'app-version': 'v1.0' }
      })
      .done(function(data) {
        var result = data.result;
        $("#view img").attr("src", result.proImg);
        var f = document.createDocumentFragment(),
          thumbs = document.getElementById('pics-thumbs');
        for (var i = 1; i <= 5; i++) {
          if (result['para' + i]) {
            var img = document.createElement("img");
            img.src = result['para' + i];
            f.append(img);
          }
        }
        thumbs.appendChild(f);
        $('.pd-title').text(result.proName);
        $('.price-1 strong').text(result.productAttrStock.standardTotalAmount);
        $('.price-2 span:first').text(result.productAttrStock.price);
        $(".price-3 span:first").text(result.productAttrStock.honourTotalAmount);
        $('.repertory .stock').text(result.productAttrStock.repertory);
        $('.multiple-num').text(result.multipleNumber);
        $('.pd-detail div').html(result.content);
      }); */
    $('#view').setZoomPicture({
      thumbsContainer: '#pics-thumbs',
      prevContainer: '#nav-left-thumbs',
      nextContainer: '#nav-right-thumbs',
      zoomContainer: '#zoom',
      zoomLevel: 2
    });
    $(window).scroll(function() {
      if ($(window).scrollTop() > 100) {
        $(".go-top").fadeIn(1000);
      } else {
        $(".go-top").fadeOut(1000);
      }
    });
    $('.go-top').click(function() {
      $('body,html').animate({
          scrollTop: 0
        },
        1000);
    })
  });
  
  
  
    //添加购物车
	function addCart(proId){
		
		var attrIds="";
		$(".partic-com-taoactive").each(function(){
			attrIds+=$(this).attr("data-attr-id")+",";
		});
		var goodsNum=$(".num-input").val();
		var reg = new RegExp("^[1-9]\d*|0$");
		if(!reg.test(goodsNum)||goodsNum==0){
			layer.open({
              content:"购买商品数量是大于1的正整数",
              btn:'我知道了'
          })
          $(".num-input").val(1)
          return false;
		}
		var mulNum="${productInfo.multipleNumber}";
		if(goodsNum!=1&&goodsNum%mulNum!=0){
			layer.open({
              content:"购买商品数量必须是"+mulNum+"倍数",
              btn:'我知道了'
          })
          $(".num-input").val(1)
          return false;
		}
		
		var gosStock=$(".stock").text();
		var logist=$(".ways .active").attr("data-type")
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
		            	  layer.open({
		                      content:data.msg,
		                      btn:'我知道了'
		                  })
		            	  
		              }else{
		            	  layer.open({
		                      content:"已添加到预选区",
		                      btn:'我知道了'
		                  }) 
		              }
	               }else{
	            	   layer.open({
		                      content:"添加失败",
		                      btn:'我知道了'
		                  })
	            	  
	               }
	              
	           },
	           error:function(){
	        	   //layer.alert("网络异常");
	           }
	     })
	}
  
    //进入预选区
    function findMyCart(){
    	window.location.href="${ctx}/pc/merchant/myCart"
    }
  </script>
</body>

</html>