<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
<body>
    <div class="container">
        <div id="wrapper" class="wrapper-nav">           
               <ul class="nav-list clearfix">          
                <c:if test="${status==7}">
                   <li class="order-nav left  nav-active" data-value="7">全部订单</li>
                   <li class="order-nav left" data-value="0">待支付</li>
                  
                   <li class="order-nav left" data-value="3">交易成功</li>
                </c:if>
                 <c:if test="${status==0}">
                   <li class="order-nav left  " data-value="7">全部订单</li>
                   <li class="order-nav left nav-active" data-value="0">待支付</li>
                   
                   <li class="order-nav left" data-value="3">交易成功</li>
                </c:if>
                 <c:if test="${status==1}">
                   <li class="order-nav left" data-value="7">全部订单</li>
                   <li class="order-nav left" data-value="0">待支付</li>                
                   <li class="order-nav left" data-value="3">交易成功</li>
                </c:if>
                 <c:if test="${status==2}">
                   <li class="order-nav left  " data-value="7">全部订单</li>
                   <li class="order-nav left" data-value="0">待支付</li>
                  
                   <li class="order-nav left" data-value="3">交易成功</li>
                </c:if>
                
                 <c:if test="${status==3}">
                   <li class="order-nav left  " data-value="7">全部订单</li>
                   <li class="order-nav left" data-value="0">待支付</li>                   
                   <li class="order-nav left nav-active" data-value="3">交易成功</li>
                </c:if>
               
            </ul>
        </div>
        <div class="slide-box" id="wrapper2">
            <!-- <div class="slide-up">
                <span class="slideUp-icon"></span>
                <span class="slideUp-label">下拉刷新...</span>
            </div> -->
            <ul class="order-box" id="allInfo" pageNo="2">
             <c:if test="${not empty resultVo.result}">
                <c:forEach var="list" items="${resultVo.result}">
                   <li class="order-item">
                    <div class="order-top clearfix">
                        <h4 class="order-shop left">${list.storeId.storeName}</h4>   
                    </div>
                    <a href="" class="order-link">
                        <div class="order-id">订单编号：${list.orderSn}</div>                       
                         <div class="order-detail clearfix" >
                            <img src="${list.storeId.storeBanner}" class="order-img left"/>
                            <div class="order-words left">                              
                                <div class="order-info">
                                    <i class="order-num">数量1</i>
                                    <i class="order-money">¥ ${list.realPayAmount}</i>
                                </div>
                            </div>
                         </div>
                        
                    </a>
                    <div class="order-bottom clearfix">
                        <div class="order-label left">
                            <label class="label-left">应付金额</label>
                            <span class="label-right">¥ ${list.realPayAmount}</span>
                        </div>
                        <div class="order-btn right">
                        <c:if test="${list.orderStatus==0}">
                           <a style="color: red;margin-right: 36px">未付款</a>                          
                        </c:if>
                        <c:if test="${list.orderStatus==1}">
                            <a  style="color: red;margin-right: 36px">待发货</a>                           
                        </c:if>
                        <c:if test="${list.orderStatus==2}">
                           <a style="color: red;margin-right: 36px">确认收货</a>                                   
                        </c:if>
                        <c:if test="${list.orderStatus==3}">
                           <a  style='color: red;margin-right: 36px'>已收货</a>   
                        </c:if>
                        <c:if test="${list.orderStatus==4}">
                           <a  class="pay-btn"></a> 
                        </c:if>
                        </div>
                    </div>
                </li>
                </c:forEach>
                </c:if>
                <c:if test="${empty resultVo.result}">
                <div class="data-state-box">
                    <img src="${imagePath}/wx/o2o-shop/data-null.png" class="data-state-img">
                    <p class="data-state-text">没有数据</p>
                </div>
                </c:if>
            </ul>
            <!-- <div class="slide-down">
                <span class="slideUp-icon"></span>
                <span class="slideUp-label">上拉加载...</span>
            </div> -->
            <div class="loading">
                <img src="${imagePath}/wx/o2o-shop/load.gif" class="loadImg">
            </div>
        </div>
    </div>
   <div class="bg-cover hidden"></div>
<div class="popup-box hidden">
    <div class="popup-title">付款二维码</div>
    <div class="popup-cont clearfix" id="showImg">
        
    </div>    
    <i class="main-sprite icon-close abs-icon" id="close-popup"></i>
</div>
    
    <script type="text/javascript" src="${ctx}/common/js/wx/o2o-shop/iscroll.js"></script>
    <script type="text/javascript">
    document.title = "店铺的订单";
    function ajaxLoad(value){
    	$("#allInfo").html("")
    	 $("#allInfo").attr("pageNo",2);
        $.ajax({
            url: "${ctx}/wx/order/findO2oOrder?pageNo=1&pageSize=6&status="+value+"&storeId=${storeId}",
            type: 'GET',
            dataType: 'json',
           /*  data: {param1:value}, */
            success:function(json){
                var str=""
                if(null == json || null == json.result || "" == json.result){
                	str+='<div class="data-state-box">';
                	str+='<img src="${imagePath}/wx/o2o-shop/data-null.png" class="data-state-img">';
                	str+='<p class="data-state-text">没有数据</p>';
                	str+='</div>';
                	$("#allInfo").html(str);
                	return false;
                }
                for(var i=0;i<json.result.length;i++){
                	str+="<li class='order-item'>"
                        +"<div class='order-top clearfix'>"
                        +"<h4 class='order-shop left'>"+json.result[i].storeId.storeName+"</h4>"                                                   
                str+="</div>"
                +"<a href='' class='order-link'>"
                    +"<div class='order-id'>订单编号："+json.result[i].orderSn+"</div>";              
                    	 str+="<div class='order-detail clearfix' >"
                         +"<img src='"+json.result[i].storeId.storeBanner+"' class='order-img left'>"
                         +"<div class='order-words left'>"                            
                             +"<div class='order-info'>"
                                 +"<i class='order-num'>数量"+1+"</i>"
                                +"<i class='order-money'>¥ "+json.result[i].realPayAmount+"</i>"
                             +"</div>"
                         +"</div>"
                     +"</div>"
                   
                str+="</a>"
                +"<div class='order-bottom clearfix'>"
                    +"<div class='order-label left'>"
                        +"<label class='label-left'>应付金额</label>"
                        +"<span class='label-right'>¥ "+json.result[i].realPayAmount+"</span>"
                    +"</div>"
                    +"<div class='order-btn right'>"
                       
                    if(json.result[i].orderStatus==0){
                    	 str+="<a  style='color: red;margin-right: 36px')>未付款</a>"   
                    }
                    
                    if(json.result[i].orderStatus==1){
                    	str+="<a  style='color: red;margin-right: 36px'>待发货</a>"
                    }
                    
                    if(json.result[i].orderStatus==2){
                    	str+="<a  style='color: red;margin-right: 36px') class='pay-btn'>确认收货</a>"
                    }
                    
                    if(json.result[i].orderStatus==3){
                    	str+="<a  style='color: red;margin-right: 36px'>已收货</a>"
                    }
                     
                    if(json.result[i].orderStatus==4){
                    	str+="<a  class=''pay-btn'></a>"
                    }                   
                    str+="</div>"
                +"</div></li>"               	
                }
                $("#allInfo").html(str)
            },
            error:function(){

            }
        })
       
    }

    $(function(){
    	var num;
    	var sta="${status}";
        /* if(sta==3){
            var width = $("#wrapper").outerWidth();
            var inWidth = $("#wrapper").find('ul').outerWidth();
            num = inWidth - width;
        }else{
            num = 0;
        } */
        var myScroll = new IScroll('#wrapper', { 
            scrollX: true, 
            scrollY: false, 
            startX: 0,
            mouseWheel: true 
        });
        $(".order-nav").on("touchstart",function(){
            $(this).addClass('nav-active').siblings().removeClass("nav-active");
            
            var attr = $(this).attr("data-value");
            /*$(".loading").show();*/
            ajaxLoad(attr);
        });

        $(window).scroll(function() {       
            var totalHeight = $(window).height()+$(window).scrollTop();
            var docHeight = $(document).height();
            
            if(totalHeight>=docHeight){//拉到底部触发        
                var attr = $(".nav-active").attr("data-value");    
                $.ajax({
                    url: '${ctx}/wx/order/findO2oOrder',
                    type: 'GET',
                    dataType: 'json',
                    data: {
                    	 storeId:"${storeId}",
                         pageNo:$("#allInfo").attr("pageNo"),
                         pageSize:6,
                         status:attr,
                         reqType:0
                     },
                    success:function(json){
                    	var str=""
                            for(var i=0;i<json.result.length;i++){
                            	
                            	str+="<li class='order-item'>"
                                    +"<div class='order-top clearfix'>"
                                    +"<h4 class='order-shop left'>"+json.result[i].storeId.storeName+"</h4>"
                                                                                 
                            str+="</div>"
                            +"<a href='' class='order-link'>"
                                +"<div class='order-id'>订单编号："+json.result[i].orderSn+"</div>"
                              
                               	 str+="<div class='order-detail clearfix'>"
                                    +"<img src='"+json.result[i].storeIdBanner+"' class='order-img left'>"
                                    +"<div class='order-words left'>"                                       
                                        +"<div class='order-info'>"
                                            +"<i class='order-num'>数量"+1+"</i>"
                                           +"<i class='order-money'>¥ "+json.result[i].realPayAmount+"</i>"
                                        +"</div>"
                                    +"</div>"
                                +"</div>"                              
                            str+="</a>"
                            +"<div class='order-bottom clearfix'>"
                                +"<div class='order-label left'>"
                                    +"<label class='label-left'>应付金额</label>"
                                    +"<span class='label-right'>¥ "+json.result[i].realPayAmount+"</span>"
                                +"</div>"
                                +"<div class='order-btn right'>"
                                   
                                if(json.result[i].orderStatus==0){
                                	 str+="<a   style='color: red;margin-right: 36px')>未付款</a>"   
                                }
                                
                                if(json.result[i].orderStatus==1){
                                	str+="<a  style='color: red;margin-right: 36px'>待发货</a>"
                                }
                                
                                if(json.result[i].orderStatus==2){
                                	str+="<a  style='color: red;margin-right: 36px'>确认收货</a>"
                                }
                                
                                if(json.result[i].orderStatus==3){
                                	str+="<a  style='color: red;margin-right: 36px'>已收货</a>"
                                }
                                 
                                if(json.result[i].orderStatus==4){
                                	str+="<a  class=''pay-btn'></a>"
                                }                   
                                str+="</div>"
                            +"</div></li>"               	
                            }
                    	    var pageNo=parseInt($("#allInfo").attr("pageNo"))+1
                   	        $("#allInfo").attr("pageNo",pageNo);
                            $("#allInfo").append(str)
                                                      
                    },
                    
                });
            }
        });
    })
    
    //确认订单
    function confirmGood(orderNum){   	
    	$.ajax({
    		url:"${ctx}/wx/order/updateStatus",
    		method:"POST",
    		data:{
    			orderSn:orderNum,
    			status:"3"
    		},
    	   success:function(data){
    		   
    		   if(data.code==200){
    			   layer.open({
                       content:"已确认收货",
                       btn:'确定',
                       yes: function(index){//点击去认证页面，index为该特定层的索引
                         location.href='${ctx}/wx/order/my?pageNo=1&pageSize=6&status=7&reqType=0';
                         layer.close(index);
                       }
                   })
                  
    		   }else{
    			   layer.open({
                       content:data.msg,
                       btn:'确定',
                       yes: function(index){//点击去认证页面，index为该特定层的索引
                           location.href='${ctx}/wx/order/my?pageNo=1&pageSize=6&status=7&reqType=0';
                           layer.close(index);
                         }
                   })
                  
    		   }   		  		   
    	   }
    	})
    }
    
    $("#close-popup").on("click",function(e){
        e.stopPropagation();
        $(".bg-cover").addClass("hidden");
        $(".popup-box").addClass("hidden");           
       /*  $("#wallet-tab-1").addClass('wb-tab-active').siblings().removeClass('wb-tab-active'); */
        location.replace(location)      
    })
    
    //调用订单支付
    function payGood(orderNum,payType,orderType){
    	if(payType==1||payType==4||payType==2){
    		payWinXin(orderNum,orderType)
    	}else if(payType==3){
    		payH5(orderNum,orderType)
    	}
    }
    
    //微信支付
    function payWinXin(orderNum,orderType){
    	$.ajax({
    		url:"${ctx}/wx/order/payOrderSign",
    		method:"POST",
    		data:{
    			"orderSn":orderNum,
    		},
    	   success:function(data){
    		   if(data.code==200){
    			   if(orderType==1){
    				   window.location.href="https://pay.swiftpass.cn/pay/jspay?token_id="+data.result; 
    			   }else{
    				 $(".bg-cover").removeClass("hidden");
       	             $(".popup-box").removeClass("hidden");
       	             $("#showImg").html("<img style='margin-top:-30px' src='"+data.result+"'>")
    			   }
    			   
    		   }else{
    			   layer.open({
                       content:data.msg,
                       btn:'我知道了'
                   })
    		   }   		   
    	   }
    	})
    }
    
    //H5支付
    function payH5(orderNum,orderType){
    	window.location.href="${ctx}/wx/order/payOrderSignByH5?orderSn="+orderNum
    }
    
    
    //跳转到评论页面
     
   /*  $(".comm").each(function(){
    	alert($(this).find(".goodIds").val())
    	
    }) */
    function goCommentPage(orderNum,token,a){
    	var goodId="";
    	$(a).find(".goodIds").each(function(){
    		goodId+=$(this).val()+",";
    	}) 
    	var good=goodId.substring(0, goodId.length-1)
    	window.location.href="${ctx}/wx/comment/goCommentPage?orderSn="+orderNum+"&token="+token+"&proId="+good;
    } 
    </script>
</body>
</html>