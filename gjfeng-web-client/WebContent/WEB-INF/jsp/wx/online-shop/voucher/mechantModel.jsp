
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
	<title>产品供应链</title>
	<link rel="stylesheet" href="${cssPath}/wx/online-shop/tianmao_style.css">
	
</head>
<style>
  .dist{
      width: 100%;
      height: 2rem;
      color: #8AA8BD;
      border-radius: 0.5rem;
      color: #8AA8BD;
      font-size: 1rem;
   }
</style>
<body>
	<header>供应链</header>
	<div class="search-bar">
		<input type="search" name="" id="keyWord" placeholder="请输入搜索关键词" name="keyWord">
		<button type="button" class="search-btn">搜索</button>
	</div>

	<div class="container">
		<aside>
		<ul class="tab-list">
		     <li class="tab-item tab-active"  >全部</li>
		   
		     <c:forEach var="column" items="${column.result}"  >
		       
		           <li class="tab-item " data-id="${column.id}" >${column.names}</li>
	        
		    </c:forEach>						
		</ul>
	</aside>
	<main class="main-list" id="wrapper" page="2">
	    <div>
		<h3  id="list-title"> 
		   <c:if test="${discount==0}">
		      <select class="dist" onchange="getDistPro()">
		         <option value="" class="distValue">全部</option>
		         <option value="1" class="distValue">1折以下</option>
		         <option value="2" class="distValue">1~2折</option>
		         <option value="3" class="distValue">3~4折</option>
		         <option value="4" class="distValue">4折以上</option>
		      </select>
		   </c:if>
		   
		   <c:if test="${discount!=0}">
		      <input type="hidden" value="${discount}" id="dis"/>
		   </c:if>
		    		   
		</h3>

		<ul class="product-list">
		
		    <c:forEach var="product" items="${products.result}">
		        <li class="product-item">
				  <a href="${ctx}/wx/product/online/product/${product.id}?type=${merchantType}">
					<div class="product-img">
						<img src="${product.imgUrl}" alt="">
					</div>
					<div class="product-info">
						<div class="product-name">${ product.name}</div>
					</div>
					<div class="product-price">
						<span></span>
						<span>${product.price}</span>
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
	 
	
		
	 //栏目分页
	 function ajaxLoad(page){
		var type=$(".tab-list .tab-active").data("id")
		if(type==undefined){
			type=""
		}
		var merchantType="${merchantType}"
		
		
		var dis=$(".dist option:selected").val()
		if(dis==undefined){
			dis=""
		}
		var discount=${discount}
        if(discount!=0){
        	dis=$("#dis").val()
		}
		$.ajax({
			url:"${ctx}/wx/trade/findModelProduct",
		    method:"get",
		    data:{
		    	columnId:type,
		    	pageNo:page,
		    	pageSize:"20",
		    	likeName:"",
		    	discount:dis
		    },
		    success:function(data){
		    	var str=""
		    		for(var i=0;i<data.result.length;i++){
				    	str+="<li class='product-item'><a href='${ctx}/wx/product/online/product/"+data.result[i].id+"?type="+merchantType+"'><div class='product-img'>"
							 +"<img src='"+data.result[i].imgUrl+"' alt=''></div>"
							 +"<div class='product-info'><div class='product-name'>"+data.result[i].name+"</div></div>"
						     +"<div class='product-price'><span></span><span>"+data.result[i].price+"</span></div></a></li>" 
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
		 var type=$(".tab-list .tab-active").data("id")
		 if(type==undefined){
				type=""
			}
		 var merchantType="${merchantType}"
		 
		var dis=$(".dist option:selected").val()
		if(dis==undefined){
			dis=""
		}
		 var discount=${discount}
	        if(discount!=0){
	        	dis=$("#dis").val()
			}
		 $.ajax({
				url:"${ctx}/wx/trade/findModelProduct",
				method:"get",
				data:{
					likeName:$("#keyWord").val(),				
					columnId:type,
			    	pageNo:page,
			    	pageSize:"20",
			    	discount:dis
				},
				success:function(data){
					var str=""
						for(var i=0;i<data.result.length;i++){
					    	str+="<li class='product-item'><a href='${ctx}/wx/product/online/product/"+data.result[i].id+"?type="+merchantType+"'><div class='product-img'>"
								 +"<img src='"+data.result[i].imgUrl+"' alt=''></div>"
								 +"<div class='product-info'><div class='product-name'>"+data.result[i].name+"</div></div>"
							     +"<div class='product-price'><span></span><span>"+data.result[i].price+"</span></div></a></li>" 
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
		    var setStr="<select class='dist' onchange='getDistPro()'><option value='' class='distValue'>全部</option>"
		             +"<option value='1' class='distValue'>1折以下</option>"
		             +" <option value='2' class='distValue'>1~2折</option>"
	                 +" <option value='3' class='distValue'>3~4折</option>"
	                 +"<option value='4' class='distValue'>4折以上</option></select>"
			
			
			 var merchantType="${merchantType}"
			//$(".product-list").html("")
			$("#wrapper").attr("page",2)
			var type=$(this).data("id")
			if(type==undefined){
				type=""
			}
			var dis=$(".dist option:selected").val()
			if(dis==undefined){
				dis=""
			}
			var discount=${discount}
			
	        if(discount!=0){
	        	dis=$("#dis").val()
	        	
			}else{
				$("#list-title").html(setStr);
			}
			$.ajax({
				url:"${ctx}/wx/trade/findModelProduct",
			    method:"get",
			    data:{
			    	columnId:type,
			    	pageNo:"1",
			    	pageSize:"20",
			    	likeName:"",
			    	discount:dis
			    },
			    success:function(data){
			    	var str=""
			    	for(var i=0;i<data.result.length;i++){
			    	str+="<li class='product-item'><a href='${ctx}/wx/product/online/product/"+data.result[i].id+"?type="+merchantType+"'><div class='product-img'>"
						 +"<img src='"+data.result[i].imgUrl+"' alt=''></div>"
						 +"<div class='product-info'><div class='product-name'>"+data.result[i].name+"</div></div>"
					     +"<div class='product-price'><span></span><span>"+data.result[i].price+"</span></div></a></li>" 
			    	}
			    	$(".product-list").html(str);	
			    	jroll.scrollTo(0,0);
			    	jroll.refresh()
			    	
			    }
			})
			
		});
		
		//搜索
		$(".search-btn").click(function(){
			 var merchantType="${merchantType}"
			$("#wrapper").attr("page",2)
			$(".product-list").html("")
			 var type=$(".tab-list .tab-active").data("id")
			 if(type==undefined){
					type=""
				}
			var dis=$(".dist option:selected").val()
			if(dis==undefined){
				dis=""
			}
			var discount=${discount}
		    if(discount!=0){
		        dis=$("#dis").val()
			}
			jroll.scrollTo(0,0);
			jroll.refresh()
			$.ajax({
				url:"${ctx}/wx/trade/findModelProduct",
				method:"get",
				data:{
					likeName:$("#keyWord").val(),
					columnId:type,
			    	pageNo:"1",
			    	pageSize:"20",
			    	discount:dis
				},
				success:function(data){
					var str=""
						for(var i=0;i<data.result.length;i++){
					    	str+="<li class='product-item'><a href='${ctx}/wx/product/online/product/"+data.result[i].id+"?type="+merchantType+"'><div class='product-img'>"
								 +"<img src='"+data.result[i].imgUrl+"' alt=''></div>"
								 +"<div class='product-info'><div class='product-name'>"+data.result[i].name+"</div></div>"
							     +"<div class='product-price'><span></span><span>"+data.result[i].price+"</span></div></a></li>" 
					    	}
			
					  $("#list-title").html("关键字"+$("#keyWord").val());
				    $(".product-list").html(str);
					//var page=$("#wrapper").attr("page")
			    	//$("#wrapper").attr("page",parseInt(page)+1)
					 jroll.refresh()
				}
			})
		})
		
		
		//选择折数
		function getDistPro(){
			 var merchantType="${merchantType}"
					//$(".product-list").html("")
					$("#wrapper").attr("page",2)
					var type=$(".tab-list .tab-active").data("id")
					if(type==undefined){
						type=""
					}
					var dis=$(".dist option:selected").val()
					if(dis==undefined){
						dis=""
					}
					var discount=${discount}
			        if(discount!=0){
			        	dis=$("#dis").val()
					}
					
					$.ajax({
						url:"${ctx}/wx/trade/findModelProduct",
					    method:"get",
					    data:{
					    	columnId:type,
					    	pageNo:"1",
					    	pageSize:"20",
					    	likeName:"",
					    	discount:dis
					    },
					    success:function(data){
					    	var str=""
					    	for(var i=0;i<data.result.length;i++){
					    	str+="<li class='product-item'><a href='${ctx}/wx/product/online/product/"+data.result[i].id+"?type="+merchantType+"'><div class='product-img'>"
								 +"<img src='"+data.result[i].imgUrl+"' alt=''></div>"
								 +"<div class='product-info'><div class='product-name'>"+data.result[i].name+"</div></div>"
							     +"<div class='product-price'><span></span><span>"+data.result[i].price+"</span></div></a></li>" 
					    	}
					    	$(".product-list").html(str);	
					    	var page=$("#wrapper").attr("page")
					    	$("#wrapper").attr("page",parseInt(page)+1);
					    	jroll.scrollTo(0,0);
					    	jroll.refresh()
					    	
					    }
					})
		}
		/* $(".dist").change(function(){
			
			 var merchantType="${merchantType}"
			//$(".product-list").html("")
			$("#wrapper").attr("page",2)
			var type=$(this).data("id")
			if(type==undefined){
				type=""
			}
			var dis=$(".dist option:selected").val()
			if(dis==undefined){
				dis=""
			}
			$.ajax({
				url:"${ctx}/wx/trade/findModelProduct",
			    method:"get",
			    data:{
			    	columnId:type,
			    	pageNo:"1",
			    	pageSize:"20",
			    	likeName:"",
			    	discount:dis
			    },
			    success:function(data){
			    	var str=""
			    	for(var i=0;i<data.result.length;i++){
			    	str+="<li class='product-item'><a href='${ctx}/wx/product/online/product/"+data.result[i].id+"?type="+merchantType+"'><div class='product-img'>"
						 +"<img src='"+data.result[i].imgUrl+"' alt=''></div>"
						 +"<div class='product-info'><div class='product-name'>"+data.result[i].name+"</div></div>"
					     +"<div class='product-price'><span></span><span>"+data.result[i].price+"</span></div></a></li>" 
			    	}
			    	$(".product-list").html(str);	
			    	var page=$("#wrapper").attr("page")
			    	$("#wrapper").attr("page",parseInt(page)+1);
			    	jroll.scrollTo(0,0);
			    	jroll.refresh()
			    	
			    }
			})
		}) */
					
			
	</script>
</body>
</html>