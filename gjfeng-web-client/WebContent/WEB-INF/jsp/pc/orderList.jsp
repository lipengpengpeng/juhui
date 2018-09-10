<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <title>采购订单</title>
  <link rel="stylesheet" type="text/css" href="${commonpc}/css/iconfont.css" />
  <link rel="stylesheet" href="${commonpc}/css/style.css" type="text/css" media="all" charset="utf-8" />
  <script src="${commonpc}/js/jquery-1.11.0.min.js"></script>
</head>

<body>
  <main class="choice-wrapper main-wrapper">
    <section class="clearfix filter-container">
      <!-- <div class="fl time-box">
        <span>选货时间：</span>
        <input type="date" name="start" id="">
        <em>-</em>
        <input type="date" name="end" id="">
      </div> -->
      <!-- <div class="fl status-box">
        <span>订单状态：</span>
        <select name="status" id="">
          <option value="0" selected="selected">全部</option>
          <option value="1">已取消</option>
          <option value="2">待付款</option>
          <option value="3">待发货</option>
          <option value="4">待收货</option>
          <option value="5">已完成</option>
        </select>
      </div>
      <div class="fl">
        <button class="search-btn">查询</button>
      </div> -->
    </section>
    <table class="choice-table">
      <thead>
        <tr>
          <td width="350px">商品信息</td>
          <td width="85px">单价</td>
          <td width="70px">数量</td>
          <td width="70px">运费</td>
          <td width="100px">代收货款</td>
          <td width="225px">收货人信息</td>
          <td width="100px">订单状态</td>
          <td width="70px">操作</td> 
        </tr>
      </thead>
      <c:forEach var="order" items="${orderInfo}">
        <tbody>
      
           <tr class="top clearfix">
              <td colspan="3">
                <span>订单号：<span class="blue-color">${order.orderSn }</span></span>
                <span class="mgl-20">选货时间：<span><fmt:formatDate value="${order.addTime}" pattern="yyyy-mm-dd HH:MM:SS"/></span></span>
              </td>
              <td colspan="4" class="danger-color">
                 <span>总支付：<span>${order.goodsTotalAmount}</span></span>
              </td>
             <td></td> 
          </tr>     
       
        
        <tr class="middle">
          <td colspan="8"><span>日志ID：<span class="blue-color">${order.id}</span></span>
          </td>
        </tr>
        <c:forEach var="goods" items="${order.goods }">
         <tr class="bottom">
          <td class="img-td clearfix">
            <img src="${ goods.goodsImg}" alt="" class="fl">
            <p class="fl">${goods.goodsName }</p>
          </td>
          <td>￥<span>${goods.goodsPrice }</span></td>
          <td><span>${goods.goodNum }</span></td>
          <td>快递
            <br>￥<span>${order.orderPos}</span></td>
          <td>￥<span>${goods.goodsPayAcmount}</span></td>
          <td>
            <div class="clearfix">
              <p class="fl info-people"><span class="iconfont icon-people"></span><span>${order.recvierName }</span></p>
              <p class="fl"><span class="iconfont icon-mobile"></span><span>${ order.recvierMobile}</span></p>
            </div>
            <div>
              <span class="iconfont icon-location"></span>
              <span>${order.addressDetail}</span>
            </div>
          </td>
          <td>
            <c:if test="${order.orderStatus==0 }">
                <p class="blue-color">待付款</p>
            </c:if>
            <c:if test="${order.orderStatus==1 }">
                <p class="blue-color">已付款</p>
            </c:if>
            <c:if test="${order.orderStatus==2 }">
                <p class="blue-color">已发货</p>
            </c:if>
            <c:if test="${order.orderStatus==3 }">
                <p class="blue-color">已收货</p>
            </c:if>
            <c:if test="${order.orderStatus==4 }">
                <p class="blue-color">已过期</p>
            </c:if>
             <c:if test="${order.orderStatus==5 }">
                <p class="blue-color">已取消</p>
            </c:if>
            <c:if test="${order.orderStatus==6 }">
                <p class="blue-color">已退款</p>
            </c:if>
           
            <!-- <a href="">查看详情</a> -->
          </td>
          
          <td class="operate-td">
             <c:if test="${ order.orderStatus==2}">
               <button class="confirm-btn">确认收货</button>
             </c:if>
          </td> 
        </tr>
        </c:forEach>

      </tbody>
      </c:forEach>
      
    </table>
  </main>
</body>

</html>