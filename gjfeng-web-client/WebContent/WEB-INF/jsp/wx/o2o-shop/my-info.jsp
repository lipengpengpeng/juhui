<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
<div class="container" style="padding-bottom: 6rem;">
    <div class="cont-top">
        <div class="top-banner">
            <div class="img-cover">
            	<a href="${ctx}/wx/member/toUpdate">
            	  <c:if test="${empty resultVo.result.imgHeadUrl}">
            	      <img src="${imagePath}/wx/o2o-shop/head.jpg" class="img-head">
            	  </c:if>
            	  <c:if test="${not empty resultVo.result.imgHeadUrl}">
            	     <img src="${resultVo.result.imgHeadUrl}" class="img-head">
            	  </c:if>               	
                </a>
                <span class="img-sex">
                	<c:if test="${empty resultVo.result || resultVo.result.sex eq '0'}">无</c:if>
                	<c:if test="${resultVo.result.sex eq '1'}">男</c:if>
                	<c:if test="${resultVo.result.sex eq '2'}">女</c:if>
                </span>
                <c:if test="${not empty resultVo.result.superId && resultVo.result.superId != 0}">
	                <span class="recommend-name-box">
	                    <i class="recommend-name"><!-- 推荐的用户 --></i><i class="recommend-tip">推</i>
	                </span>
                </c:if>
            </div>
        </div>
        <div class="top-text">
            <div class="top-name">${resultVo.result.nickName}</div>
            <div class="top-abstract" >${resultVo.result.remark}<li class="recommend-tip" style="color:red;font-size: medium;" id="isActivity"></li></div>
           <!--  <div class="top-abstract" id="conMoney">数据加载中...</div> -->
        </div>
    </div>
    <div class="cont-order">
        <div class="my-order clearfix">
            <h3 class="index-order-title left">我的订单</h3>
            <div class="check-order right">
                <a href="${ctx}/wx/order/my?pageNo=1&pageSize=6&status=7&reqType=0">查看所有订单<i class="my-icon icon-more"></i></a>
            </div>
        </div>
        <div class="order-menu clearfix">
            <a href="${ctx}/wx/order/my?pageNo=1&pageSize=6&status=0&reqType=0" class="orderNav-link left">
                <i class="my-icon order-icon-1"></i>
                <div class="order-text">待付款</div>
            </a>
            <a href="${ctx}/wx/order/my?pageNo=1&pageSize=6&status=1&reqType=0" class="orderNav-link left">
                <i class="my-icon order-icon-2"></i>
                <div class="order-text">待发货</div>
            </a>
            <a href="${ctx}/wx/order/my?pageNo=1&pageSize=6&status=2&reqType=0" class="orderNav-link left">
                <i class="my-icon order-icon-3"></i>
                <div class="order-text">待收货</div>
            </a>
            <a href="${ctx}/wx/order/my?pageNo=1&pageSize=6&status=3&reqType=0" class="orderNav-link left">
                <i class="my-icon order-icon-4"></i>
                <div class="order-text">交易完成</div>
            </a>
        </div>
    </div>
    <div class="cont-menu">
        <ul class="contMenu-box">
            <li>
                <%-- <a href="${ctx}/wx/member/myQr">
                   <i class="contMenu-icon contMenu-icon-10"></i>
                   <span class="contMenu-text">我的二维码</span>
                </a> --%>
                <%-- <a href="${ctx}/wx/member/myQr"> --%>
                <a href="${ctx}/appNeed/appDownLoadGuide?superId=${resultVo.result.id}">
                   <i class="contMenu-icon contMenu-icon-10"></i>
                   <span class="contMenu-text">我的二维码</span>
                </a> 
            </li>
           <li>
                <a href="${ctx}/wx/member/myWallet" id="mywallet">
                   <i class="contMenu-icon contMenu-icon-2"></i>
                   <span class="contMenu-text">我的钱包</span>
                </a>
            </li>
            <li>
                <a href="${ctx}/wx/cart/my">
                   <i class="contMenu-icon contMenu-icon-3"></i>
                   <span class="contMenu-text">购物车</span>
                </a>
            </li>
            <li>
                <a href="${ctx}/wx/member/toSetPay">
                   <i class="contMenu-icon contMenu-icon-9"></i>
                   <span class="contMenu-text">设置支付密码</span>
                </a>
            </li>
        </ul>
        <ul class="contMenu-box">
           <li>
           		<c:if test="${resultVo.result.type eq '0'}">
           			<a href="javascript:void(0);" class="judge-role">
	                   <i class="contMenu-icon contMenu-icon-4"></i>
	                   <span class="contMenu-text">我是商家</span>
	                </a>
           		</c:if>
           		<c:if test="${resultVo.result.type eq '1'}">
           			<a href="${ctx}/wx/store/my">
	                   <i class="contMenu-icon contMenu-icon-4"></i>
	                   <span class="contMenu-text">我是商家</span>
	                </a>
           		</c:if>
            </li>
            
            <c:if test="${resultVo.result.merchantType eq '3' && fhTreasureInfo>=0}">
              <li>
                 <a href="${ctx}/wx/trade/toMerchantGive?type=3">
                   <i class="contMenu-icon-agent"></i>
                   <span class="contMenu-text">赠送企业版或商家版</span>
                 </a>
              </li>
            </c:if>
            <c:if test="${resultVo.result.merchantType eq '4' && fhTreasureInfo>=0}">
              <li>
                 <a href="${ctx}/wx/trade/toMerchantGive?type=4">
                   <i class="contMenu-icon-agent"></i>
                   <span class="contMenu-text">赠送商家版代理</span>
                 </a>
              </li>
            </c:if>
            <c:if test="${resultVo.result.merchantType eq '5' && fhTreasureInfo>=0}">
              <li>
                 <a href="${ctx}/wx/trade/toMerchantGive?type=5">
                   <i class="contMenu-icon-agent"></i>
                   <span class="contMenu-text">赠送商家版代理或企业代理</span>
                 </a>
              </li>
            </c:if>
            <c:if test="${resultVo.result.merchantType eq '6' && fhTreasureInfo>=0}">
              <li>
                 <a href="${ctx}/wx/trade/toMerchantGive?type=6">
                   <i class="contMenu-icon-agent"></i>
                   <span class="contMenu-text">赠送商家版代理或企业代理</span>
                 </a>
              </li>
            </c:if>
             <c:if test="${resultVo.result.merchantType eq '7' && fhTreasureInfo>=0}">
              <li>
                 <a href="${ctx}/wx/trade/toMerchantGive?type=7">
                   <i class="contMenu-icon-agent"></i>
                   <span class="contMenu-text">赠送商家版代理或企业代理</span>
                 </a>
              </li>
            </c:if>
            
            
            <%-- <c:if test="${resultVo.result.type eq '1'&&(resultVo.result.merchantType eq '0'||resultVo.result.merchantType eq '1'||resultVo.result.merchantType eq '2')}">
              <li>
                 <a href="${ctx}/wx/trade/goMerchantUpgrade">
                   <i class="contMenu-icon-agent"></i>
                   <span class="contMenu-text">联盟商家升级</span>
                 </a>
              </li>
            </c:if>  --%>
            
            <c:if test="${resultVo.result.identity eq '1' || resultVo.result.identity eq '2' || resultVo.result.identity eq '3'}">
            <li>
                <a href="${ctx}/wx/trade/agent">
                   <i class="contMenu-icon-agent"></i>
                   <span class="contMenu-text">我是代理</span>
                </a>
            </li>
            </c:if>
             <c:if test="${resultVo.result.isOpcenter eq '1'}">
               <li>
                 <a href="${ctx}/wx/member/opcenter">
                   <i class="contMenu-icon-agent"></i>
                   <span class="contMenu-text">运营中心</span>
                 </a>
               </li>
            </c:if>
            <li>
               <a href="${ctx}/wx/member/getMemberLowerLevel?superId=${resultVo.result.id}&pageNo=1&pageSize=15" >
                   <i class="contMenu-icon-people"></i>
                  <span class="contMenu-text">我推荐的人</span>
               </a>
            </li>
            <li>
                <a href="${ctx}/wx/address/toFind?type=1&goodSource=0">
                   <i class="contMenu-icon contMenu-icon-5"></i>
                   <span class="contMenu-text">地址管理</span>
                </a>
            </li>
            <li>
                <a href="${ctx}/wx/member/toMyCollect">
                   <i class="contMenu-icon contMenu-icon-6"></i>
                   <span class="contMenu-text">我的收藏</span>
                </a>
            </li>
            
           
        </ul>
        <ul class="contMenu-box">
            <li>
                <a href="tel:010-59070355">
                   <i class="contMenu-icon contMenu-icon-7"></i>
                   <span class="contMenu-text">联系客服:010-59070355</span>
                </a>
            </li> 
            <li>
                <a href="${ctx}/wx/rule/about">
                   <i class="contMenu-icon contMenu-icon-8"></i>
                   <span class="contMenu-text">关于巨惠云商O2O</span>
                </a>
            </li>  
            <li>
                <a href="${ctx}/wx/rule/about">
                   <i class="contMenu-icon contMenu-icon-8"></i>
                   <span class="contMenu-text">app下载</span>
                </a>
            </li>           
            <!-- <li>
                <a href="">
                   <i class="contMenu-icon contMenu-icon-9"></i>
                   <span class="contMenu-text">账户设置</span>
                </a>
            </li> -->
        </ul>
    </div>
</div>
<!-- 底部 -->
<%@include file="/common/wx-footer.jsp" %>

<div class="bg-cover hidden"></div>
<div class="popup-box hidden">
    <div class="popup-title">您还未入驻巨惠云商O2O</div>
    <div class="popup-cont clearfix">
        <div class="popup-item left" data-value="1">
            <div class="popup-img">
                <i class="into-sprite icon-enterprise"></i>
            </div>
            <div class="popup-text">企业入驻</div>
        </div>
        <div class="popup-item left" data-value="2">
            <div class="popup-img">
                <i class="into-sprite icon-individual"></i>
            </div>
            <div class="popup-text">个体入驻</div>
        </div>
    </div>
    <div class="popup-btn">
        <a href="#" class="popup-btn-link">确认</a>
    </div>
    <i class="main-sprite icon-close abs-icon" id="close-popup"></i>
</div>
<script type="text/javascript">
    $(function(){
    	document.title = "个人中心";
    	
    	findUperMember();
    	
    	//findMemberConMoney()
    	
    	$(".icon-nav-4").siblings().removeClass("nav-link-active");
		$(".icon-nav-4").addClass("nav-link-active");
		$(".icon-nav-4").parent().siblings().removeClass("nav-link-active");
		$(".icon-nav-4").parent().addClass("nav-link-active");
		
        $(".judge-role").on("click",function(e){
            e.preventDefault();
            $(".bg-cover").removeClass("hidden");
            $(".popup-box").removeClass("hidden");
        });

        $(".popup-item").on("touchend",function(){
            $(this).addClass("into-sprite-active").siblings().removeClass("into-sprite-active");
        });

        $(".popup-btn-link").on("touchend",function(e){
            e.stopPropagation();
            e.preventDefault();
            var num = $(".into-sprite-active").attr("data-value");
            if(num == "1"){//企业入驻
                window.location.href = "${ctx}/wx/store/toAdd/1";
            }else if(num == "2"){//个体入驻
                window.location.href = "${ctx}/wx/store/toAdd/0";
            }
        })

        $("#close-popup").on("click",function(e){
            e.stopPropagation();
            $(".bg-cover").addClass("hidden");
            $(".popup-box").addClass("hidden");
            $(".popup-item").removeClass("into-sprite-active");
        })
    })
    
    function findUperMember(){
    	var superId="${resultVo.result.superId}";
    	if(null == superId || "" == superId || undefined==superId || superId==0){
    		return false;
    	}
    	//获取父级
        $.ajax({
        	url:"${ctx}/wx/member/findSuperMember",
        	method:"GET",
        	data:{
        		"superId":superId
        	},
        	success:function(data){
        		if(data.result != null && data.code==200){
        			$(".recommend-name").html(data.result.nickName);
        		}	
        	}
        })
    }
    
    
    //获取用户复消金额
    function findMemberConMoney(){
    	   	
    	$.ajax({
    		url:"${ctx}/wx/trade/countMemberNiceConMoney",
    		method:"GET",
    		success:function(data){
    			if(data.result != null && data.code==200){
    				if((data.result.isActivity==1&&data.result.type==0)||(data.result.isActivity==1&&data.result.isActivityStore==1)){
    					if(data.result.activityMember!=0){
							$("#conMoney").html("预计还需消费"+data.result.activityMember +"以上维持vip")
    					    $("#isActivity").html("vip")
						}else{
							$("#conMoney").html("消费已达到维持vip")
    					    $("#isActivity").html("vip")
						}
    				}else if(data.result.isActivity==1&&data.result.isActivityStore==0){	
    					if(data.result.merchantMoney!=0){
    						$("#conMoney").html("商家预计还需消费"+data.result.merchantMoney +"以上升级vip")	
    						$("#isActivity").html("vip")
    					}else{
    						$("#conMoney").html("")
    						$("#isActivity").html("vip")
    					}
						
    				}else if(data.result.isActivity==0&&data.result.isActivityStore==1){
    					if(data.result.memberMoney!=0){
    						$("#conMoney").html("会员预计还需消费"+data.result.memberMoney +"以上升级vip")
    					}else{
    						$("#conMoney").html("")
    					}
    					
    				}else if(data.result.isActivity==0&&data.result.isActivityStore==0){
    					if(data.result.noActivityMember!=0){
    						$("#conMoney").html("预计还需消费"+data.result.noActivityMember +"以上升级vip")	
    					}else{
    						$("#conMoney").html("")
    					}
    					
    				}
    			}
    		}
    	})
    }
    
    
</script>
</body>
</html>