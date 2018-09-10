<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body style="background-color: #efefef;">
    <div class="container">
        <div class="wrap-top">
            <div class="wrap-item-1">
                <img src="${resultVo.result.imgUrl1}" class="gd-img">
                <div class="gd-title">${resultVo.result.name}</div>
            </div>
            <div class="wrap-item-2 clearfix">
                <div class="wrap-m-left left">
                    <span class="gd-money">${resultVo.result.price}</span>
                    <span class="gd-out-money">门市价:¥${resultVo.result.marketPrice}</span>
                </div>
               <div class="wrap-m-right right">
                    <%-- <a  class="wrap-pay" onclick="payOrder(${resultVo.result.id})">买单</a> --%>
                </div> 
            </div>
        </div>
        <div class="wrap-shopInfo">
            <div class="shopInfo-title">商家信息</div>
            <ul class="business-list" style="position: relative;">
                <li class="business-li clearfix">
                    <i class="main-sprite icon-shop left"></i>
                    <div class="b-li-left left">
                        <span class="business-text">${resultVo.result.storeName}</span>
                    </div>
                </li>
                <li class="business-li clearfix">
                    <i class="main-sprite icon-address left"></i>
                    <div class="b-li-left left">
                        <span class="business-text">${resultVo.result.storeAddress}</span>
                        <p class="near-distance">距离我最近
                        <c:if test="${resultVo.result.distance >= 1000 || resultVo.result.distance <= -1000}">${resultVo.result.distance/1000}km</c:if>
                 		<c:if test="${-1000 < resultVo.result.distance && resultVo.result.distance < 1000}">${resultVo.result.distance}m</c:if>
                        </p>
                    </div>
                </li>
                <div class="call-business">
                    <a href="tel:${resultVo.result.storeMobile}"><i class="main-sprite icon-call"></i></a>
                </div>
            </ul>
        </div>
        <div class="wrap-shopItem">
            <div class="wrap-left-title">购买须知</div>
            <div class="wrap-item-box">
                <div class="wrap-box">
                    <div class="wrap-tit-1">有效期:</div>
                    <div class="wrap-content">${resultVo.result.indate}</div>
                </div>
                <div class="wrap-box">
                    <div class="wrap-tit-1">使用规则:</div>
                    <div class="wrap-content">${resultVo.result.notice}</div>
                </div>
            </div>
        </div>
        <div class="wrap-shopItem">
            <div class="wrap-left-title clearfix">
            	<fmt:parseNumber value="${resultVo.result.score}" var="score"/>
                <span class="user-evaluation">用户评价(${score}分)</span>
                <div class="shopInfo-box user-points">
                    <div class="shopInfo-points-box">
                       <c:if test="${score==0}">
                         <i class="contMenu-icon icon-crown "></i>
                         <i class="contMenu-icon icon-crown "></i>
                         <i class="contMenu-icon icon-crown "></i>
                         <i class="contMenu-icon icon-crown "></i>
                         <i class="contMenu-icon icon-crown"></i>
                       </c:if>
                       <c:if test="${score>0 && score<1}">
                         <i class="contMenu-icon icon-crown icon-crown-middle"></i>
                         <i class="contMenu-icon icon-crown "></i>
                         <i class="contMenu-icon icon-crown "></i>
                         <i class="contMenu-icon icon-crown "></i>
                         <i class="contMenu-icon icon-crown "></i>
                       </c:if>
                       <c:if test="${score==1}">
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown "></i>
                         <i class="contMenu-icon icon-crown "></i>
                         <i class="contMenu-icon icon-crown "></i>
                         <i class="contMenu-icon icon-crown "></i>
                       </c:if>
                        <c:if test="${score>1 && score<2}">
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-middle"></i>
                         <i class="contMenu-icon icon-crown "></i>
                         <i class="contMenu-icon icon-crown "></i>
                         <i class="contMenu-icon icon-crown "></i>
                       </c:if>
                        <c:if test="${score==2}">
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown "></i>
                         <i class="contMenu-icon icon-crown "></i>
                         <i class="contMenu-icon icon-crown "></i>
                       </c:if>
                       
                        <c:if test="${score>2 && score<3}">
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-middle"></i>
                         <i class="contMenu-icon icon-crown "></i>
                         <i class="contMenu-icon icon-crown "></i>
                       </c:if>
                       
                       <c:if test="${score==3}">
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown "></i>
                         <i class="contMenu-icon icon-crown "></i>
                       </c:if>
                       
                       <c:if test="${score>3 && score<4}">
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-middle"></i>
                         <i class="contMenu-icon icon-crown "></i>
                       </c:if>
                       
                           
                       <c:if test="${score==4}">
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown "></i>
                       </c:if>
                       
                       <c:if test="${score>4 && score<5}">
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-middle"></i>
                       </c:if>
                       
                       <c:if test="${score==5}">
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                         <i class="contMenu-icon icon-crown icon-crown-active"></i>
                       </c:if>
                        
                    </div>
                </div>
                <span class="user-points-num right">
                    <a href="${ctx}/wx/comment/getProCommetByPage?pageNo=1&pageSize=4&proId=${id}&status=0&type=1" class="user-num-link">
                        <i class="users-num" id="users-num"></i>
                        <i class="my-icon icon-more"></i>
                    </a>
                </span>
            </div>
            <div class="feedback-list" id="allInfo">
                <!-- <div class="feedback-item">
                    <div class="feedback-item-top clearfix">
                        <div class="feedback-head left">
                            <img src="images/head.jpg" class="fh-img">
                        </div>
                        <div class="feedback-info left">
                            <div class="feedback-name">田馥甄_Hebe</div>
                            <div class="feedback-time">7分钟前</div>
                        </div>
                        <div class="feedback-points right">
                            <div class="shopInfo-box">
                                <i class="contMenu-icon icon-crown icon-crown-active"></i>
                                <i class="contMenu-icon icon-crown icon-crown-active"></i>
                                <i class="contMenu-icon icon-crown icon-crown-active"></i>
                                <i class="contMenu-icon icon-crown"></i>
                                <i class="contMenu-icon icon-crown"></i>
                            </div>
                        </div>
                    </div>
                    <div class="feedback-item-cont">
                        看到评价很高，刚好今天休息就过去试下，牛扒的份量特别足，也很鲜嫩，味道确实不错。环境也很好，挺有情调的~服务员态度也很好哦~
                    </div>
                    <div class="feedback-item-photo">
                        <img src="images/instruct-1.png" class="feedback-photo">
                        <img src="images/instruct-1.png" class="feedback-photo">
                        <img src="images/instruct-1.png" class="feedback-photo">
                    </div>
                </div> -->
                <!-- <div class="feedback-item">
                    <div class="feedback-item-top clearfix">
                        <div class="feedback-head left">
                            <img src="images/head.jpg" class="fh-img">
                        </div>
                        <div class="feedback-info left">
                            <div class="feedback-name">田馥甄_Hebe</div>
                            <div class="feedback-time">7分钟前</div>
                        </div>
                        <div class="feedback-points right">
                            <div class="shopInfo-box">
                                <i class="contMenu-icon icon-crown icon-crown-active"></i>
                                <i class="contMenu-icon icon-crown icon-crown-active"></i>
                                <i class="contMenu-icon icon-crown icon-crown-active"></i>
                                <i class="contMenu-icon icon-crown"></i>
                                <i class="contMenu-icon icon-crown"></i>
                            </div>
                        </div>
                    </div>
                    <div class="feedback-item-cont">
                        看到评价很高，刚好今天休息就过去试下，牛扒的份量特别足，也很鲜嫩，味道确实不错。环境也很好，挺有情调的~服务员态度也很好哦~
                    </div>
                    <div class="feedback-item-photo"></div>
                </div> -->
            </div>
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
    
    <div class="bg-cover hidden"></div>
<div class="popup-box hidden">
    <div class="popup-title">付款二维码</div>
    <div class="popup-cont clearfix" id="showImg">
          
    </div>  
    
    <i class="main-sprite icon-close abs-icon" id="close-popup"></i>
</div>
    
    <script type="text/javascript">
        $(function(){
        	document.title = "商品详情";
        	var pageNo=1;
    		var pageSize=10;
    		var lockStatus=true;
            $(window).scroll(function() {
                var totalHeight = $(window).height()+$(window).scrollTop();
                var docHeight = $(document).height();
                if(totalHeight>=docHeight){//拉到底部触发
                    $(".loading-bottom").removeClass('hidden');
                		if(!lockStatus){
                			return false;
                		}
                		lockStatus=false;
                    	$.ajax({
                            url: '${ctx}/wx/product/o2o/hotProduct',
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
            });
        })
        
         $("#close-popup").on("click",function(e){
            e.stopPropagation();
            $(".bg-cover").addClass("hidden");
            $(".popup-box").addClass("hidden");           
           /*  $("#wallet-tab-1").addClass('wb-tab-active').siblings().removeClass('wb-tab-active'); */
            location.replace(location)      
        })
        
        function payOrder(proId){
        	 $.ajax({
        		url:"${ctx}/wx/store/payStoreOrder",
        		method:"GET",
        		data:{
        			"orderAddVos[0].goodsId":proId,
        			"orderAddVos[0].goodsAttrStockId":"",
        			"orderAddVos[0].goodsAttrIds":"",
        			"orderAddVos[0].goodsAttr":"",
        			"orderAddVos[0].goodsNum":1,
        			storeId:"${resultVo.result.storeId}",
        			payType:2,
        			couponsId:0,
        			remark:$(".gd-money").html(),
        			type:"1"
        		},
        		success:function(data){
        			if(data.code==200){        			           			
        				$(".bg-cover").removeClass("hidden");
        	            $(".popup-box").removeClass("hidden");
        	            $("#showImg").html("<img style='margin-top:-30px' src='"+data.result+"'><div class='popup-text' style='padding-left:19px'>长按二维码或使用支付宝进行扫码</div>")
        		   }else{
        			   layer.open({
                           content:data.msg,
                           btn:'我知道了'
                       })
        		   }
        		}
        	}) 
        }
        
        
        $.ajax({
      		  url:"${ctx}/wx/comment/countProComment",
      		  method:"GET",
      		  data:{
      			  proId:"${id}",
      			  state:1
      		  },
      		  success:function(data){
      			  if(null == data.result || "" == data.result || undefined==data.result){
      				$("#users-num").html("共0条");
      			  }else{
      				$("#users-num").html("共"+data.result+"条");
      			  }
      		  }  	
        })
        
               $.ajax({
                        url: '${ctx}/wx/comment/getAllProCommetByPage',
                        type: 'GET',                 
                        data: {
                        	pageNo:1,
                        	pageSize:2,
                        	proId:"${id}",
                        	state:3,
                        	type:1
                          },
                          success:function(data){
                        	  var str=""
                        	  for(var i=0;i<data.result.length;i++){
                        		  str+="<div class='feedback-item'>"
                                       +"<div class='feedback-item-top clearfix'>"
                                      +"<div class='feedback-head left'>"
                                     +"<img src='"+data.result[i].imgHeadUrl+"' class='fh-img'>"
                                  +"</div><div class='feedback-info left'>"
                                      +"<div class='feedback-name'>"+data.result[i].nickName+"</div>"
                                      +"<div class='feedback-time'>"+format(data.result[i].comTime,"yyyy-MM-dd HH:mm:ss")+"</div>"
                                  +"</div><div class='feedback-points right'>"
                                      +"<div class='shopInfo-box'>"
                                           if(data.result[i].comScore==0){
                                        	   str+="<i class='contMenu-icon icon-crown'></i>"
                                               +"<i class='contMenu-icon icon-crown'></i>"
                                               +"<i class='contMenu-icon icon-crown'></i>"
                                               +"<i class='contMenu-icon icon-crown'></i>"
                                               +"<i class='contMenu-icon icon-crown'></i>"
                                           }
                                       
                        		            if(data.result[i].comScore==1){
                        		            	 str+="<i class='contMenu-icon icon-crown icon-crown-active'></i>"
                                                 +"<i class='contMenu-icon icon-crown'></i>"
                                                 +"<i class='contMenu-icon icon-crown'></i>"
                                                 +"<i class='contMenu-icon icon-crown'></i>"
                                                 +"<i class='contMenu-icon icon-crown'></i>"
                        		            }
                                             
                        		            if(data.result[i].comScore==2){
                        		            	 str+="<i class='contMenu-icon icon-crown icon-crown-active'></i>"
                                                 +"<i class='contMenu-icon icon-crown icon-crown-active'></i>"
                                                 +"<i class='contMenu-icon icon-crown'></i>"
                                                 +"<i class='contMenu-icon icon-crown'></i>"
                                                 +"<i class='contMenu-icon icon-crown'></i>"
                        		            }
                                          
                                          if(data.result[i].comScore==3){
                                        	  str+="<i class='contMenu-icon icon-crown icon-crown-active'></i>"
                                              +"<i class='contMenu-icon icon-crown icon-crown-active'></i>"
                                              +"<i class='contMenu-icon icon-crown icon-crown-active'></i>"
                                              +"<i class='contMenu-icon icon-crown'></i>"
                                              +"<i class='contMenu-icon icon-crown'></i>" 
                                          }
                                          
                                          if(data.result[i].comScore==4){
                                        	  str+="<i class='contMenu-icon icon-crown icon-crown-active'></i>"
                                              +"<i class='contMenu-icon icon-crown icon-crown-active'></i>"
                                              +"<i class='contMenu-icon icon-crown icon-crown-active'></i>"
                                              +"<i class='contMenu-icon icon-crown icon-crown-active'></i>"
                                              +"<i class='contMenu-icon icon-crown'></i>"
                                          }                                  
                                         
                                          if(data.result[i].comScore==5){
                                        	  str+="<i class='contMenu-icon icon-crown icon-crown-active'></i>"
                                              +"<i class='contMenu-icon icon-crown icon-crown-active'></i>"
                                              +"<i class='contMenu-icon icon-crown icon-crown-active'></i>"
                                              +"<i class='contMenu-icon icon-crown icon-crown-active'></i>"
                                              +"<i class='contMenu-icon icon-crown icon-crown-active'></i>"
                                          }
                                      
                                      str+="</div></div></div>"
                              +"<div class='feedback-item-cont'>"+data.result[i].content
                                  //
                              +"</div><div class='feedback-item-photo'>"
                                  +"<img src="+data.result[i].comImg+" class='feedback-photo'>"
                                  
                              +"</div></div>"
                        	  }
                        	  
                        	  $("#allInfo").html(str) 
                        	  
                          }
                    })
                    
 function format(time, format){
    var t = new Date(time);
    var tf = function(i){return (i < 10 ? '0' : '') + i};
    return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
        switch(a){
            case 'yyyy':
                return tf(t.getFullYear());
                break;
            case 'MM':
                return tf(t.getMonth() + 1);
                break;
            case 'mm':
                return tf(t.getMinutes());
                break;
            case 'dd':
                return tf(t.getDate());
                break;
            case 'HH':
                return tf(t.getHours());
                break;
            case 'ss':
                return tf(t.getSeconds());
                break;
        }
    })
}
    </script>
</body>
</html>