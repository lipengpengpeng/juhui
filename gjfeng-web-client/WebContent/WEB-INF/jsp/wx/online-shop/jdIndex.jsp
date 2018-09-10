
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
	<title>京东全球购</title>
	<link rel="stylesheet" href="${cssPath}/wx/online-shop/tianmao_style.css">
	
</head>
<body>
	<header>天猫</header>
	<div class="search-bar">
		<input type="search" name="" id="keyWord" placeholder="请输入搜索关键词" name="keyWord">
		<button type="button" class="search-btn">搜索</button>
	</div>

	<div class="container">
		<aside>
		<ul class="tab-list">
		    <c:forEach var="cat" items="${cat}"  end="0">
		       
		           <li class="tab-item tab-active" data-id="${cat.id}" >${ cat.catName}</li>
			          	       
		    </c:forEach>
		     <c:forEach var="cat" items="${cat}" begin="1" >
		       
		           <li class="tab-item " data-id="${cat.id}" >${ cat.catName}</li>
	        
		    </c:forEach>						
		</ul>
	</aside>
	<main class="main-list" id="wrapper" page="2">
	    <div>
		<h3  id="list-title">${cat[0].catName}</h3>

		<ul class="product-list">
		
		    <c:forEach var="product" items="${product}">
		        <li class="product-item">
				  <a onclick="goProductDetail(${product.id})">
					<div class="product-img">
						<img src="${product.img}" alt="">
					</div>
					<div class="product-info">
						<div class="product-name">${ product.title}</div>
					</div>
					<div class="product-price">
						<span>优惠券：￥</span>
						<span>${product.yhqmoney}</span>
					</div>
				 </a>
			   </li>
		    </c:forEach>			
		</ul>
		</div>
	</main>
	</div>
	 <script src="${jsPath}/wx/online-shop/jquery-2.1.4.min.js?ver=1.1"></script>
     <script src="${jsPath}/wx/online-shop/jroll.min.js?ver=1.1"></script>
	<script>
	
	var token="${sessionScope.gjfMemberInfo.token}";
	var id="${sessionScope.gjfMemberInfo.id}";
	 var mobile="${sessionScope.gjfMemberInfo.mobile}";
    var uid="1001"+id;
    //alert(uid)
	var jroll = new JRoll("#wrapper");
	
	jroll.on("scrollEnd",function(){
		if (this.y === this.maxScrollY) {
			var key=$("#keyWord").val()
			if(key==""){
				
				ajaxLoad($("#wrapper").attr("page"))
			}else{
				ajaxSerchLoad($("#wrapper").attr("page"))
			}
			//jroll.refresh();
          }
	 })
	 
	//跳转
	function goProductDetail(proId){
		window.location.href="http://aihua.likecs.com/index.php?mod=aihua&act=jdfh&param=detail&id="+proId+"&userid="+uid+"&phone="+mobile+"&email=&kh=fenghuang&tbnum="
	}
		
	 //栏目分页
	 function ajaxLoad(page){
		var type=$(".tab-list .tab-active").data("id")
		$.ajax({
			url:"${ctx}/wx/product/findJdProductList",
		    method:"get",
		    data:{
		    	type:type,
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
		    	$(".product-list").append(str);
		    	var page=$("#wrapper").attr("page")
		    	$("#wrapper").attr("page",parseInt(page)+1);
		    	jroll.refresh()
		    	
		    }
		})
	}
	
	 //栏目分页
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
	
	 
	 
	 
	    //点击栏目
		$(".tab-item").click(function(event) {
			$(this).addClass('tab-active').siblings('.tab-item').removeClass('tab-active');
			//获取当前选项的id再ajax，然后渲染到main
			$("#list-title").html($(this).html());
			
			//$(".product-list").html("")
			$("#wrapper").attr("page",2)
			var type=$(this).data("id")
			$.ajax({
				url:"${ctx}/wx/product/findJdProductList",
			    method:"get",
			    data:{
			    	type:type,
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
			    	$(".product-list").html(str);	
			    	jroll.scrollTo(0,0);
			    	jroll.refresh()
			    	
			    }
			})
			
		});
		
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
		
		
			
	</script>
</body>
</html>