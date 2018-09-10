<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-shop-top.jsp" %>

<style>
.cf:before, .cf:after{
      content:"";
      display:table;
    }
    
    .cf:after{
      clear:both;
    }

    .cf{
      zoom:1;
    }

        
    
    .form-wrapper {
        width: 93%;
        padding: 10px;
        margin: 2px auto 4px auto;
        background: #444;
        background: rgba(0,0,0,.2);
        -moz-border-radius: 10px;
        -webkit-border-radius: 10px;
        border-radius: 10px;
        -moz-box-shadow: 0 1px 1px rgba(0,0,0,.4) inset, 0 1px 0 rgba(255,255,255,.2);
        -webkit-box-shadow: 0 1px 1px rgba(0,0,0,.4) inset, 0 1px 0 rgba(255,255,255,.2);
        box-shadow: 0 1px 1px rgba(0,0,0,.4) inset, 0 1px 0 rgba(255,255,255,.2);
    }
    
    #searchInput {
        width: 70%;
        height: 20px;
        padding: 10px 5px;
        float: left;    
        font: bold 15px 'lucida sans', 'trebuchet MS', 'Tahoma';
        border: 0;
        background: #eee;
        -moz-border-radius: 3px 0 0 3px;
        -webkit-border-radius: 3px 0 0 3px;
        border-radius: 3px 0 0 3px;      
    }
    
    #searchInput:focus {
        outline: 0;
        background: #fff;
        -moz-box-shadow: 0 0 2px rgba(0,0,0,.8) inset;
        -webkit-box-shadow: 0 0 2px rgba(0,0,0,.8) inset;
        box-shadow: 0 0 2px rgba(0,0,0,.8) inset;
    }
    
    #searchInput::-webkit-input-placeholder {
       color: #999;
       font-weight: normal;
       font-style: italic;
    }
    
    #searchInput:-moz-placeholder {
        color: #999;
        font-weight: normal;
        font-style: italic;
    }
    
    #searchInput:-ms-input-placeholder {
        color: #999;
        font-weight: normal;
        font-style: italic;
    }    
    
    #search {
        overflow: visible;
        position: relative;
        float: right;
        border: 0;
        padding: 0;
        cursor: pointer;
        height: 40px;
        width: 27%;
        font: bold 15px/40px 'lucida sans', 'trebuchet MS', 'Tahoma';
        color: #fff;
        text-transform: uppercase;
        background: #d83c3c;
        -moz-border-radius: 0 3px 3px 0;
        -webkit-border-radius: 0 3px 3px 0;
        border-radius: 0 3px 3px 0;      
        text-shadow: 0 -1px 0 rgba(0, 0 ,0, .3);
    }   
      
    #search:hover{        
        background: #e54040;
    }    
      
    #search:active,
    #search:focus{   
        background: #c42f2f;    
    }
    
    #search:before {
        content: '';
        position: absolute;
        border-width: 8px 8px 8px 0;
        border-style: solid solid solid none;
        border-color: transparent #d83c3c transparent;
        top: 12px;
        left: -6px;
    }
    
    #search:hover:before{
        border-right-color: #e54040;
    }
    
    #search:focus:before{
        border-right-color: #c42f2f;
    }    
    
    #search::-moz-focus-inner {
        border: 0;
        padding: 0;
    }
</style>

<body>
    <!-- <input type="text" name="likeValue" id="likeValue" value="" placeholder="输入商品名称" style="width: 65%;
    padding: 6px;border: solid 1px gray;"/> -->
   <!--  <input type="button"  value="搜索" id="seachProd" style="padding: 6px;width: 30%;color: #2f2f2f;
         font-size: 13px;background-color: red"/>  -->
  
   <div class="form-wrapper cf">
    <input type="text" placeholder="请输入商品名称..." required id="searchInput">
    <button  id="search">搜索</button>
   </div>
                       
          
	 <div id="wrapper" pageNo="2">	
	<c:if test="${not empty products.result}">
		<ul class="foods-ul">
		<!-- <div class="foods-banner">
			<img src="../uploadimg/foods-bannner.jpg">
		</div> -->
		<c:forEach var="bean" items="${products.result}" varStatus="status">
			<li>
				<a href="${ctx}/wx/product/online/product/${bean.id}" class="clearfix">
					<div class="foods-font">
						<p class="foods-reg">${bean.name}</p>
						<c:if test="${bean.isCanUserCou==0 }">
						   <p class="foods-money">${bean.price}</p>
						</c:if>
						<c:if test="${bean.isCanUserCou==1}">
						   <p class="foods-money">${bean.pointNicePrice}+${bean.price}积分</p>
						</c:if>
						<c:if test="${bean.isCanUserCou==2}">
						   <p class="foods-money">${bean.pointNicePrice}+${bean.price}责任金额</p>
						</c:if> 
						<c:if test="${bean.isCanUserCou==3}">
						   <p class="foods-money">${bean.pointNicePrice}+${bean.price}代金券金额</p>
						</c:if> 
						
						<span class="foods-buy">立即购买</span>
					</div>
					<div class="foods-img">
						<img src="${bean.imgUrl}">
					</div>
				</a>
			</li>
		</c:forEach>
		</ul>
	</c:if>
	</div>
	<input type="hidden" class="columnId" value="${columnId}">
	<input type="hidden" class="type" value="${type}">
	<script src="${jsPath}/wx/online-shop/iscroll.js"></script>
	<script src="${jsPath}/wx/online-shop/pullToRefresh.js"></script>
	<script src="${jsPath}/wx/online-shop/jquery.font.js"></script>
	<script type="text/javascript">
	document.title = "商品列表";
  	var pageSize=10;
  	var reqType=1;
	$(function(){
		$(".header-navActive li>a").on("touchend",function(){
			$(this).addClass("nav-active").parent().siblings().find("a").removeClass("nav-active");
				alert($(this).html());
			})
			//给加载盒子高度
		function wrapperHeight(){
			var headerHeight=$(".header").outerHeight();
			var windowHeight =$(window).height();
			var wrapHeight =windowHeight-headerHeight;
			$("#wrapper").height(wrapHeight);
		}wrapperHeight();

		function loading(){
			refresher.init({
				id:"wrapper",
				pullDownAction:Refresh,
				pullUpAction:Load
			});
			//上拉刷新
			function Refresh() {
				var columnId=$(".columnId").val();
		    	var type=$(".type").val();
				ajaxLoad(1,pageSize,reqType,0,columnId,type);
			}
			//加载更多
			function Load() {
				var columnId=$(".columnId").val();
		    	var type=$(".type").val();
				ajaxLoad($("#wrapper").attr("pageNo"),pageSize,reqType,1,columnId,type);
			}
		}loading();

		//控制字体个数
		$(".foods-reg").fonts({
            fontNum: 18
        });
		
		
	     function ajaxLoad(pageNo,pageSize,reqType,refreshType,columnId,type){
	       $.ajax({
	           url: '${ctx}/wx/product/online/products/'+type+'/'+columnId,
	           type: 'get',
	           dataType: 'html',
	           data: {
	           	"pageNo":pageNo,
	           	"pageSize":pageSize,
	           	"reqType":reqType,
	           	"likeValue":$("#searchInput").val()
	           	},
	           success:function(data){	        	   
	               if(null != data && "" != data){	            	   	            	   
		               	if(refreshType==0){
		               		pageNo=1;
		               		$(".foods-ul").empty();
		               	}else if(refreshType==1){		               		
		               		$(".columnId").val(columnId);
		        	    	$(".type").val(type);
		        	    	var pageNo0=parseInt($("#wrapper").attr("pageNo"))+1
                   	        $("#wrapper").attr("pageNo",pageNo0);
		               	}
		               	$(".foods-ul").append(data);
	               }
	               myScroll.refresh();/**完成的刷新！*/
	           },
	           error:function(){
	        	   myScroll.refresh();/**完成的刷新！*/
	        	   alert("网络异常");
	           }
	       })
	     }
	})
	
	//查询商品
	$("#search").click(function(){
		var columnId=$(".columnId").val();
    	var type=$(".type").val();
    	var refreshType=1;
    	$(".foods-ul").html("")
		$.ajax({
			url:"${ctx}/wx/product/online/products/"+type+"/"+columnId,
			method:"get",
			dataType: 'html',
			data: {
	           	"pageNo":1,
	           	"pageSize":pageSize,
	           	"reqType":reqType,
	           	"likeValue":$("#searchInput").val()
	           	},
	           	success:function(data){	 
		               if(null != data && "" != data){	            	   	            	   
			               	if(refreshType==0){
			               		pageNo=1;
			               		$(".foods-ul").empty();
			               	}else if(refreshType==1){		               		
			               		$(".columnId").val(columnId);
			        	    	$(".type").val(type);
			        	    	var pageNo0=parseInt($("#wrapper").attr("pageNo"))+1
	                   	        $("#wrapper").attr("pageNo",pageNo0);
			               	}
			               	$(".foods-ul").append(data);
		               }
		               myScroll.refresh();/**完成的刷新！*/
		           },
		           error:function(){
		        	   myScroll.refresh();/**完成的刷新！*/
		        	   alert("网络异常");
		           }
		})
	})
	
	</script>
</body>
</html>