<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<style>
.loading-middle{position:fixed;top:50%;left: 50%;background-color:rgba(0,0,0,.8);padding:1rem;border-radius: 5px;margin-top: -2.5rem;}
.loading-middle .loadImg{width: 4rem;height: 4rem;}
</style>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
	<title>京东自营产品</title>
	<link rel="stylesheet" href="${cssPath}/wx/online-shop/tianmao_style.css">
	
</head>
<body>
	<header>京东自营产品</header>
	<div class="search-bar">
		<input type="search" name="" id="keyWord" placeholder="请输入搜索关键词" name="keyWord">
		<button type="button" class="search-btn">搜索</button>
	</div> 

	<div class="container">
		<aside>
		<ul class="tab-list">
		    <c:forEach var="cat" items="${cat.firstLevel}"  end="0">
		       
		           <li class="tab-item tab-active" data-id="${cat.catId}" >${ cat.catName}</li>
			          	       
		    </c:forEach>
		    
		     <c:forEach var="cat" items="${cat.firstLevel}" begin="1" >
		       
		           <li class="tab-item " data-id="${cat.catId}" >${ cat.catName}</li>
	        
		    </c:forEach>					
		</ul>
		<%-- <div class="sub-container toggle-close">
		        <input type="hidden" id="catType" value="0">
				<input type="hidden" id="secId" value=""/>
				<ul class="sub-list" id="second-list">
				  
				   <c:forEach var="secondCat" items="${cat.secondLevel}">
				     <c:if test="${cat.firstLevel[0].catId==secondCat.parentId}">
				         <li class="tab-item second-item" data-id="${secondCat.catId}" onclick="secondLev('${secondCat.catId}','1')">${secondCat.catName}</li>
				     </c:if>				     				     
				   </c:forEach>				  
				</ul>
				</div>
				<div class="sub-toggle open">
				
		</div> --%>
	</aside>
	<main class="main-list" id="wrapper" page="2">
	    <div>
		<%-- <h3  id="list-title">${cat[0].catName}</h3>--%>

		<ul class="product-list">
		
		    <c:forEach var="product" items="${resultVo.result}">
		        <li class="product-item">
				  <a href="${ctx}/wx/product/findJdProprietaryProDetail?goodsId=${product.goodsId}">
					<div class="product-img">
						<img src="${product.goodsThumb}" alt="">
					</div>
					<div class="product-info">
						<div class="product-name">${ product.goodsName}</div>
					</div>
					<div class="product-price">
						<span>￥</span>
						<span>${product.shopPrice}</span>
					</div>
				 </a>
			   </li>
		    </c:forEach>			
		</ul>
		</div>
		  <div class="loading-middle hidden" style="display: none">
              <img src="${imagePath}/wx/o2o-shop/load.gif" class="loadImg">
          </div>
	</main>
	</div>
	 <script src="${jsPath}/wx/online-shop/jquery-2.1.4.min.js?ver=1.1"></script>
     <script src="${jsPath}/wx/online-shop/jroll.min.js?ver=1.1"></script>
	<script>
	

	var jroll = new JRoll("#wrapper");
	
	jroll.on("scrollEnd",function(){
		if (this.y === this.maxScrollY) {
			var key=$("#keyWord").val()
			if(key==""){
				var catType=$("#catType").val()
				//if(catType==0){
					ajaxLoad($("#wrapper").attr("page"))	
				//}else{
					//secondLev($("#secId").val(),$("#wrapper").attr("page"))
				//}
				
			}else{
				ajaxSerchLoad($("#wrapper").attr("page"))
			}
			//jroll.refresh();
          }
	 })
	 
	
		
	 //一级栏目栏目分页
	 function ajaxLoad(page){
		var type=$(".tab-list .tab-active").data("id")
		$("#second-list").html("")
		$.ajax({
			url:"${ctx}/wx/product/findJdProprietaryProByCatId",
		    method:"get",
		    data:{
		    	catId:type,
		    	page:page,
		    	sup:"",
		    	rateBegin:"0.15"
		    },
		    success:function(data){
		    	var str=""
		    	var secLvStr=""
				//获取产品
				for(var i=0;i<data.result.product.length;i++){
				     str+="<li class='product-item'><a href='${ctx}/wx/product/findJdProprietaryProDetail?goodsId="+data.result.product[i].goodsId+"'><div class='product-img'>"
							 +"<img src='"+data.result.product[i].goodsThumb+"' alt=''></div>"
							 +"<div class='product-info'><div class='product-name'>"+data.result.product[i].goodsName+"</div></div>"
							 +"<div class='product-price'><span>￥</span><span>"+data.result.product[i].shopPrice+"</span></div></a></li>" 
				 }
				 //获取二级栏目		    	
				/*  for(var j=0;j<data.result.secondLevel.length;j++){
				     secLvStr+=" <li class='tab-item second-item' data-id='"+data.result.secondLevel[j].catId+"' onclick=secondLev('"+data.result.secondLevel[j].catId+"','1')>"+data.result.secondLevel[j].catName+"</li>"
				 }
				$("#second-list").html(secLvStr) */
		    	$(".product-list").append(str);
		    	var page=$("#wrapper").attr("page")
		    	$("#wrapper").attr("page",parseInt(page)+1);
		    	jroll.refresh()
		    	
		    }
		})
	}
	
	 //搜索分页
	 function ajaxSerchLoad(page){
		 $.ajax({
				url:"${ctx}/wx/product/findJdProductListBySerch",
				method:"get",
				data:{
					keyWord:$("#keyWord").val(),
					page:page
				},
				success:function(data){
					var str=""
				    	for(var i=0;i<data.length;i++){
				    		str+="<li class='product-item'><a href='http://aihua.likecs.com/index.php?mod=aihua&act=jdfh&param=detail&id="+data[i].id+"&userid="+uid+"&phone="+mobile+"&email=&kh=fenghuang&tbnum='><div class='product-img'>"
								 +"<img src='"+data[i].img+"' alt=''></div>"
								 +"<div class='product-info'><div class='product-name'>"+data[i].title+"</div></div>"
							     +"<div class='product-price'><span>优惠券：￥</span><span>"+data[i].yhqmoney+"</span></div></a></li>" 
				    	}
					  //console.log(str)
					$("#list-title").html("关键字"+$("#keyWord").val());
				    $(".product-list").append(str);
					var page=$("#wrapper").attr("page")
			    	$("#wrapper").attr("page",parseInt(page)+1);
					  jroll.refresh()
				}
			})
	}
	
	 
	 
	 
	    //点击一级栏目
		$(".tab-item").click(function(event) {
			$(".product-list").html("");	
			$(".loading-middle").css('display','block');
			
			$(this).addClass('tab-active').siblings('.tab-item').removeClass('tab-active');
			//获取当前选项的id再ajax，然后渲染到main
			$("#list-title").html($(this).html());
									
			//$(".product-list").html("")
			$("#wrapper").attr("page",2)
			var type=$(this).data("id")
					
			$("#second-list").html("")
			$.ajax({
				url:"${ctx}/wx/product/findJdProprietaryProByCatId",
			    method:"get",
			    data:{
			    	catId:type,
			    	page:"1",
			    	sup:"",
			    	rateBegin:"0.15"
			    },
			    success:function(data){
			    	var str=""
			    	var secLvStr=""
			    	//获取产品
			    	for(var i=0;i<data.result.product.length;i++){
			    		str+="<li class='product-item'><a href='${ctx}/wx/product/findJdProprietaryProDetail?goodsId="+data.result.product[i].goodsId+"'><div class='product-img'>"
							 +"<img src='"+data.result.product[i].goodsThumb+"' alt=''></div>"
							 +"<div class='product-info'><div class='product-name'>"+data.result.product[i].goodsName+"</div></div>"
						     +"<div class='product-price'><span>￥</span><span>"+data.result.product[i].shopPrice+"</span></div></a></li>" 
			    	}
			    	//获取二级栏目		    	
			    	/* for(var j=0;j<data.result.secondLevel.length;j++){
			    		secLvStr+=" <li class='tab-item second-item' data-id='"+data.result.secondLevel[j].catId+"' onclick=secondLev('"+data.result.secondLevel[j].catId+"','1')>"+data.result.secondLevel[j].catName+"</li>"
			    	}
			    	$("#second-list").html(secLvStr) */
			    	$(".product-list").html(str);	
			    	$(".loading-middle").css('display','none');
			    	jroll.scrollTo(0,0);
			    	jroll.refresh()
			    	
			    }
			})
			
		});
	    
		function secondLev(type,page){
			//获取当前选项的id再ajax，然后渲染到main						
			$("#catType").val("1")
			$("#secId").val(type)
			$.ajax({
				url:"${ctx}/wx/product/findJdProprietaryProByCatId",
			    method:"get",
			    data:{
			    	catId:type,
			    	page:page,
			    	sup:"",
			    	rateBegin:""
			    },
			    success:function(data){
			    	var str=""			    	
			    	//获取产品
			    	for(var i=0;i<data.result.product.length;i++){
			    		str+="<li class='product-item'><a href='${ctx}/wx/product/findJdProprietaryProDetail?goodsId="+data.result.product[i].goodsId+"'><div class='product-img'>"
							 +"<img src='"+data.result.product[i].goodsThumb+"' alt=''></div>"
							 +"<div class='product-info'><div class='product-name'>"+data.result.product[i].goodsName+"</div></div>"
						     +"<div class='product-price'><span>￥</span><span>"+data.result.product[i].shopPrice+"</span></div></a></li>" 
			    	}
			    	//获取二级栏目		    	
			    	$(".sub-toggle").removeClass('close').addClass('open');
			    	$('.sub-container').removeClass('toggle-open').addClass('toggle-close');
			    	$(".product-list").append(str);
			    	if(parseInt(page)>1){
			    		var page=$("#wrapper").attr("page")
				    	$("#wrapper").attr("page",parseInt(page)+1);
			    	}			    	
			    	jroll.scrollTo(0,0);
			    	jroll.refresh()
			    	
			    }
			})
		}
	   				   
		
		//搜索
		$(".search-btn").click(function(){
			$("#wrapper").attr("page",2)
			$(".product-list").html("")
			jroll.scrollTo(0,0);
			jroll.refresh()
			$.ajax({
				url:"${ctx}/wx/product/findJdProductListBySerch",
				method:"get",
				data:{
					keyWord:$("#keyWord").val(),
					page:"1"
				},
				success:function(data){
					var str=""
				    	for(var i=0;i<data.length;i++){
				    		str+="<li class='product-item'><a href='http://aihua.likecs.com/index.php?mod=aihua&act=jdfh&param=detail&id="+data[i].id+"&userid="+uid+"&phone="+mobile+"&email=&kh=fenghuang&tbnum='><div class='product-img'>"
								 +"<img src='"+data[i].img+"' alt=''></div>"
								 +"<div class='product-info'><div class='product-name'>"+data[i].title+"</div></div>"
							     +"<div class='product-price'><span>优惠券：￥</span><span>"+data[i].yhqmoney+"</span></div></a></li>" 
				    	}
			
					  $("#list-title").html("关键字"+$("#keyWord").val());
				    $(".product-list").html(str);
					//var page=$("#wrapper").attr("page")
			    	//$("#wrapper").attr("page",parseInt(page)+1)
					 jroll.refresh()
				}
			})
		})
		
		$(".sub-toggle").click(function(){
			if($(this).hasClass('open')){
				$(this).removeClass('open').addClass('close');
				$('.sub-container').removeClass('toggle-close').addClass('toggle-open');
			}
			else{
				$(this).removeClass('close').addClass('open');
				$('.sub-container').removeClass('toggle-open').addClass('toggle-close');
			}
		})
			
	</script>
</body>
</html>