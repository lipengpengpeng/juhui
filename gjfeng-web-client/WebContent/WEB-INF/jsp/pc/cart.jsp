<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>购物车</title>
  <link rel="stylesheet" href="${commonpc}/css/style.css?ver=1.0" type="text/css" media="all" charset="utf-8" />
</head>

<body>
  <main class="cart-wrapper main-wrapper" style="margin-top:100px">
    <h2 class="danger-color">购物车商品</h2>
    <table class="cart-list">
      <thead class="cart-head">
        <tr>
          <th>
            <label for="checkall">全选</label>
            <input type="checkbox" name="checkall" id="checkall">
          </th>
          <th>商品信息</th>
          <th>单价</th>
          <th>数量</th>
          
         <!--  <th>地址</th> -->
          <th>配送方式</th>
          <th>总价</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="cart" items="${cart}">
           
          <tr class="cart-item">
            
          <td class="check-td">
            <label for="1">
              <input type="checkbox" name="goodscheck" id="1" cart-id="${cart.id}">
            </label>
          </td>
          <td class="img-td clearfix">
            <img src="${cart.goodsId.imgUrl}" alt="" class="fl">
            <p class="fl">${cart.goodsId.name}</p>
          </td>
          <td>￥<span class="price">${cart.goodsId.price}</span></td>
          <td class="num-td">
            <div class="count-box">
              <span class="count-btn minus">-</span>
              <input id="1" data-id="${cart.id}" data-step="${ cart.goodsId.multipleNumber}" data-min="1" data-max="50" value="${cart.goodsNum}" readonly="readonly" />
              <span class="count-btn plus">+</span>
            </div>
          </td>
          
          <!-- <td>快递
            <br>￥<span class="postage">19.5</span>
          </td> -->
          <td>
              <c:if test="${cart.logist==0}">快递</c:if>
              <c:if test="${cart.logist==1}">物流</c:if>
          </td> 
          <td>￥<span class="total">${ cart.goodsId.price*cart.goodsNum}</span></td>
          <td class="danger-color" onclick="delCart(${cart.id})">删除</td>
         </tr>
        </c:forEach>             
      </tbody>
      <tfoot class="cart-foot">
        <tr>
          <td colspan="2">
            <a class="danger-color mg20" href="${ctx}/pc/merchant/goSpecifiedMerchantOnilne">继续选货</a>
          </td>
          <td colspan="6">
            <div class="total-area">
              共计&nbsp;<b class="danger-color"><span class="gongji">0</span></b>&nbsp;件商品&nbsp;&nbsp;
              <em class="danger-color">总价：￥<span class="zongjia">0</span></em>&nbsp;&nbsp; 运费：￥
              <span class="yunfei">0</span>&nbsp;&nbsp; 合计：
              <b class="danger-color">￥<span class="heji">0</span></b>
            </div>
          </td>
          <td>
           <form action="${ctx}/pc/merchant/actCart" method="POST" id="cartForm">
              <input type="hidden" name="cartIds" value="" id="cartIds"/>
              <button class="submit-btn cartSubmit" >提交</button>
           </form>
           
          </td>
         
        </tr>
      </tfoot>
    </table>
  </main>
  <script src="${commonpc}/js/jquery-1.11.0.min.js"></script>
  <script src="${plugInPath}/layer_mobile/layer.js?ver=1.1"></script>
  <script>
  $(function() {
    var checkboxs = $("input[name='goodscheck']:checkbox"),
      $gongji = $('.gongji'),
      $zongjia = $('.zongjia'),
      $yunfei = $('.yunfei'),
      $heji = $('.heji');
    $("#checkall").click(function() {
      if (this.checked) {
        checkboxs.each(function() {
          $(this).prop("checked", true);
        });
      } else {
        checkboxs.each(function() {
          $(this).prop("checked", false);
        });
      }
      calc();
    });
    checkboxs.on('click', function() {
      if ($("input[name='goodscheck']:checked").length == checkboxs.length) {
        $("#checkall").prop("checked", true);
      } else {
        $("#checkall").prop("checked", false);
      }
      calc();
    });
    var calc = function (){
    	var gongji = 0,
        zongjia = 0,
        yunfei = 0,
        heji = 0;
    	var cartId="";
      if ($("input[name='goodscheck']:checked").length > 0) {
        $("input[name='goodscheck']:checked").each(function() {
        	cartId+=$(this).attr("cart-id")+",";
        	
          var $tr = $(this).parents('.cart-item');
          var price = +$tr.find('.price').text(),
            num = +$tr.find('.count-box input').val(),
            postage = +$tr.find('.postage').text();
          gongji += num;
          zongjia += price * num;
          yunfei += postage;
          heji = zongjia + yunfei;
        });
          $('.gongji').text(gongji);
          $('.zongjia').text((zongjia).toFixed(2));
          $('.yunfei').text((yunfei).toFixed(2));
          $('.heji').text((heji).toFixed(2));
          $("#cartIds").val(cartId)
      } else {
        $('.gongji').text(0);
        $('.zongjia').text(0);
        $('.yunfei').text(0);
        $('.heji').text(0);
      }
    };
    $('.count-box').each(function() {
      var $tr = $(this).parents('.cart-item');
      var price = +$tr.find('.price').text();
      var $minus = $(this).find('.minus'),
        $plus = $(this).find('.plus'),
        $input = $(this).find('input');
      var min = +$input.data('min') || 1,
        max = +$input.data('max') || 1,
        step = +$input.data('step') || 1;
      var val = +$input.val() || 1;
      var cartId=$input.data('id')
      
      $minus.click(function() {
        if (val == step) { val = 1; } else { val -= step; }
        if (val < min) {
          val = min;
        }
        $input.val(val);
        $tr.find('.total').text((val * price).toFixed(2));
        //更改数量获取邮费，然后重新计算
        ajaxUpdateNum(cartId,val)
        calc();
      });
      $plus.click(function() {
        if (val == 1 && step !== 1) { val = step; } else if (val == 1 && step === 1) { val += step; } else { val += step; }
        if (val > max) {
          val = Math.floor(max / step) * step;
        }
        $input.val(val);
        $tr.find('.total').text((val * price).toFixed(2));
        //更改数量获取邮费，然后重新计算
        ajaxUpdateNum(cartId,val)
        calc();
      });

    });
  });
  
  //更改购物车数量
  function ajaxUpdateNum(id,num){
       $.ajax({
           url: '${ctx}/pc/merchant/updateCartNum',
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
  
  
  //删除购物车
  function delCart(id){	  
	  layer.open({
          content: '是否移除购物车商品',
          btn: ['是', '否'],
          yes: function(index){//点击去认证页面，index为该特定层的索引
        	  $.ajax({
        		  url:"${ctx}/pc/merchant/delCart/"+id,
        		  method:"POST",
        		  data:{
        			  id:id
        		  },
        		  success:function(data){
        			 if(data.code==200){      				  
                          location.reload() 
        			  }else{
        				  layer.open({
                              content:"删除失败",
                              btn:'我知道了'
                          })
        			  }
        		  }
        	  
        	  })
   	       
             layer.close(index);
          },
          no:function(index){
              layer.close(index);
          }
      });
	  	  
  }
  
  //购物车结算
  $(".cartSubmit").click(function(){
	  var cartIds=$("#cartIds").val();	
	  if(cartIds!=""){
		 
		$("#cartForm").submit();
		$(this).removeClass("cartSubmit")
	  }else{
		  layer.open({
              content:"请选择需要支付订单商品",
              btn:'我知道了'
          }) 
          return false;
	  }
	 
  }) 
  </script>
</body>

</html>