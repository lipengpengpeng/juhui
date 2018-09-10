<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-shop-top.jsp" %>
<body>
	<div class="appraise-all"><a href="javascript:">全部评价（<span id="comCount"></span>）</a></div>
	<section class="appraise-set" id="wrapper">
		<ul class="appraise-wrap" id="page" pageNo="2" pageSize="10">
		    <c:forEach var="com" items="${resultVo.result}">
		       <ul class="appraise-comul" >
				<li class="clearfix">
					<div class="appraise-portrait"><img src="${com.imgHeadUrl}"></div>
					<div class="appraise-box clearfix">
						 <span class="appraise-num">${com.nickName}</span> 
						 <c:if test="${com.comScore==0}">
						   <ul class="appraise-xing clearfix">
	                          <li rate="1"></li>
	                          <li rate="2"></li>
	                          <li rate="3"></li>
	                          <li rate="4"></li>
	                          <li rate="5"></li>
						   </ul>
						 </c:if>
						 <c:if test="${com.comScore>0&&com.comScore<=1}">
						   <ul class="appraise-xing clearfix">
	                          <li rate="1" class="appraise-active"></li>
	                          <li rate="2"></li>
	                          <li rate="3"></li>
	                          <li rate="4"></li>
	                          <li rate="5"></li>
						   </ul>
						 </c:if>
						  <c:if test="${com.comScore>1&&com.comScore<=2}">
						   <ul class="appraise-xing clearfix">
	                          <li rate="1" class="appraise-active"></li>
	                          <li rate="2" class="appraise-active"></li>
	                          <li rate="3"></li>
	                          <li rate="4"></li>
	                          <li rate="5"></li>
						   </ul>
						 </c:if>
						  <c:if test="${com.comScore>2&&com.comScore<=3}">
						   <ul class="appraise-xing clearfix">
	                          <li rate="1" class="appraise-active"></li>
	                          <li rate="2" class="appraise-active"></li>
	                          <li rate="3" class="appraise-active"></li>
	                          <li rate="4"></li>
	                          <li rate="5"></li>
						   </ul>
						 </c:if>
						  <c:if test="${com.comScore>3&&com.comScore<=4}">
						   <ul class="appraise-xing clearfix">
	                          <li rate="1" class="appraise-active"></li>
	                          <li rate="2" class="appraise-active"></li>
	                          <li rate="3" class="appraise-active"></li>
	                          <li rate="4" class="appraise-active"></li>
	                          <li rate="5"></li>
						   </ul>
						 </c:if>
						 <c:if test="${com.comScore>4&&com.comScore<=5}">
						   <ul class="appraise-xing clearfix">
	                          <li rate="1" class="appraise-active"></li>
	                          <li rate="2" class="appraise-active"></li>
	                          <li rate="3" class="appraise-active"></li>
	                          <li rate="4" class="appraise-active"></li>
	                          <li rate="5" class="appraise-active"></li>
						   </ul>
						 </c:if>
						
					</div>
				</li>
				<li>
				    
					<p class="appraise-data">${com.content}</p>
					<p class="appraise-data"><img src="${com.comImg}" style="width: 80px;height: 80px"/></p>
					<p class="appraise-time"><span><fmt:formatDate value="${com.comTime}" pattern="yyyy-MM-dd"/></span><i><fmt:formatDate value="${com.comTime}" pattern="HH:mm:ss"/></i></p>
				</li>
				<!-- <li class="appraise-reply">
					<p><span>店家回复：</span>我们家的商品都是有质量保障的哦，欢迎下次继续购买哦！</p>
				</li> -->
			   </ul>
		    </c:forEach>						
		</ul>
	</section>
	<script src="${jsPath}/wx/online-shop/iscroll.js"></script>
	<script src="${jsPath}/wx/online-shop/pullToRefresh.js"></script>
	<script type="text/javascript">
		document.title = "评价详情";
		$(function(){
				//给加载盒子高度
		function wrapperHeight(){
			var headerHeight=$(".header").outerHeight();
			var appraiseAll=$(".appraise-all").innerHeight();
			var windowHeight =$(window).height();
			var wrapHeight =windowHeight-headerHeight-appraiseAll;
			$("#wrapper").height(wrapHeight);
		}wrapperHeight();
		//加载
		function loading(){
			refresher.init({
				id:"wrapper",
				pullDownAction:Refresh,
				pullUpAction:Load
			});
			// 下拉刷新
			function Refresh() {
			
					var el, li, i;
					el =document.querySelector("#wrapper .appraise-wrap");
					el.innerHTML='';
					$.ajax({
						url:"${ctx}/wx/comment/getAllProCommetByPage",
						method:"GET",
						data:{
							pageNo:1,
							pageSize:10,
							proId:"${proId}",
							state:0
						},
					    success:function(data){
					    	for(var i=0;i<data.result.length;i++){
					    		oUI = document.createElement('ul');
								oUI.className="appraise-comul";
								oUI.innerHTML="<li class='clearfix'>"+
												"<div class='appraise-portrait'><img src='"+data.result[i].imgHeadUrl+"'></div>"+
												"<div class='appraise-box clearfix'>"+
													"<span class='appraise-num'>"+data.result[i].nickName+"</span>"
													if(data.result[i].comScore==0){
													oUI.innerHTML+="<ul class='appraise-xing clearfix'>"+
								                        "<li rate='1' ></li>"+
								                        "<li rate='2' ></li>"+
								                        "<li rate='3' ></li>"+
								                        "<li rate='4' ></li>"+
								                        "<li rate='5'></li>"+
													     "</ul>"
													}
								if(data.result[i].comScore>0&&data.result[i].comScore<=1){
									oUI.innerHTML+="<ul class='appraise-xing clearfix'>"+
				                        "<li rate='1' class='appraise-active'></li>"+
				                        "<li rate='2' ></li>"+
				                        "<li rate='3' ></li>"+
				                        "<li rate='4' ></li>"+
				                        "<li rate='5'></li>"+
									     "</ul>"
									}
								if(data.result[i].comScore>1&&data.result[i].comScore<=2){
									oUI.innerHTML+="<ul class='appraise-xing clearfix'>"+
				                        "<li rate='1' class='appraise-active'></li>"+
				                        "<li rate='2' class=appraise-active'></li>"+
				                        "<li rate='3' ></li>"+
				                        "<li rate='4' ></li>"+
				                        "<li rate='5'></li>"+
									     "</ul>"
									}
								if(data.result[i].comScore>2&&data.result[i].comScore<=3){
									oUI.innerHTML+="<ul class='appraise-xing clearfix'>"+
				                        "<li rate='1' class='appraise-active'></li>"+
				                        "<li rate='2' class='appraise-active'></li>"+
				                        "<li rate='3' class='appraise-active'></li>"+
				                        "<li rate='4' ></li>"+
				                        "<li rate='5'></li>"+
									     "</ul>"
									}
								if(data.result[i].comScore>3&&data.result[i].comScore<=4){
									oUI.innerHTML+="<ul class='appraise-xing clearfix'>"+
				                        "<li rate='1' class='appraise-active'></li>"+
				                        "<li rate='2' class='appraise-active'></li>"+
				                        "<li rate='3' class='appraise-active'></li>"+
				                        "<li rate='4' class='appraise-active'></li>"+
				                        "<li rate='5'></li>"+
									     "</ul>"
									}
								if(data.result[i].comScore>4&&data.result[i].comScore<=5){
									oUI.innerHTML+="<ul class='appraise-xing clearfix'>"+
				                        "<li rate='1' class='appraise-active'></li>"+
				                        "<li rate='2' class='appraise-active'></li>"+
				                        "<li rate='3' class='appraise-active'></li>"+
				                        "<li rate='4' class='appraise-active'></li>"+
				                        "<li rate='5'  class='appraise-active'></li>"+
									     "</ul>"
									}
								
																		
											oUI.innerHTML+="</div>"+
											"</li>"+
											"<li>"+
												"<p class='appraise-data'>"+data.result[i].content+"</p>"+
												"<p class='appraise-data'><img src="+data.result[i].comImg+" style='width: 80px;height: 80px'/></p> "+
												"<p class='appraise-time'><span>"+format(data.result[i].comTime,"yyyy-MM-dd")+"</span><i>"+format(data.result[i].comTime,"HH:mm:ss")+"</i></p>"+
											"</li>";
							el.appendChild(oUI);
					    	}
					    }
					})
					
					myScroll.refresh();/**完成的刷新！*/
				}
			}
			//上拉加载更多
			function Load() {
					var el, li, i;
					el =document.querySelector("#wrapper .appraise-wrap");
					
					
					$.ajax({
						url:"${ctx}/wx/comment/getAllProCommetByPage",
						method:"GET",
						data:{
							pageNo:$("#page").attr("pageNo"),
							pageSize:$("#page").attr("pageSize"),
							proId:"${proId}",
							state:0
						},
					    success:function(data){
					    	for(var i=0;i<data.result.length;i++){
					    		oUI = document.createElement('ul');
								oUI.className="appraise-comul";
								oUI.innerHTML="<li class='clearfix'>"+
												"<div class='appraise-portrait'><img src='"+data.result[i].imgHeadUrl+"'></div>"+
												"<div class='appraise-box clearfix'>"+
												 "<span class='appraise-num'>"+data.result[i].nickName+"</span>"
												 if(data.result[i].comScore==0){
														oUI.innerHTML+="<ul class='appraise-xing clearfix'>"+
									                        "<li rate='1' ></li>"+
									                        "<li rate='2' ></li>"+
									                        "<li rate='3' ></li>"+
									                        "<li rate='4' ></li>"+
									                        "<li rate='5'></li>"+
														     "</ul>"
														}
									if(data.result[i].comScore>0&&data.result[i].comScore<=1){
										oUI.innerHTML+="<ul class='appraise-xing clearfix'>"+
					                        "<li rate='1' class='appraise-active'></li>"+
					                        "<li rate='2' ></li>"+
					                        "<li rate='3' ></li>"+
					                        "<li rate='4' ></li>"+
					                        "<li rate='5'></li>"+
										     "</ul>"
										}
									if(data.result[i].comScore>1&&data.result[i].comScore<=2){
										oUI.innerHTML+="<ul class='appraise-xing clearfix'>"+
					                        "<li rate='1' class='appraise-active'></li>"+
					                        "<li rate='2' class='appraise-active'></li>"+
					                        "<li rate='3' ></li>"+
					                        "<li rate='4' ></li>"+
					                        "<li rate='5'></li>"+
										     "</ul>"
										}
									if(data.result[i].comScore>2&&data.result[i].comScore<=3){
										oUI.innerHTML+="<ul class='appraise-xing clearfix'>"+
					                        "<li rate='1' class='appraise-active'></li>"+
					                        "<li rate='2' class='appraise-active'></li>"+
					                        "<li rate='3' class='appraise-active'></li>"+
					                        "<li rate='4' ></li>"+
					                        "<li rate='5'></li>"+
										     "</ul>"
										}
									if(data.result[i].comScore>3&&data.result[i].comScore<=4){
										oUI.innerHTML+="<ul class='appraise-xing clearfix'>"+
					                        "<li rate='1' class='appraise-active'></li>"+
					                        "<li rate='2' class='appraise-active'></li>"+
					                        "<li rate='3' class='appraise-active'></li>"+
					                        "<li rate='4' class='appraise-active'></li>"+
					                        "<li rate='5'></li>"+
										     "</ul>"
										}
									if(data.result[i].comScore>4&&data.result[i].comScore<=5){
										oUI.innerHTML+="<ul class='appraise-xing clearfix'>"+
					                        "<li rate='1' class='appraise-active'></li>"+
					                        "<li rate='2' class='appraise-active'></li>"+
					                        "<li rate='3' class='appraise-active'></li>"+
					                        "<li rate='4' class='appraise-active'></li>"+
					                        "<li rate='5'  class='appraise-active'></li>"+
										     "</ul>"
										}
									
									oUI.innerHTML+="</div>"+
											"</li>"+
											"<li>"+
												"<p class='appraise-data'>"+data.result[i].content+"</p>"+
												"<p class='appraise-data'><img src="+data.result[i].comImg+" style='width: 80px;height: 80px'/></p>"+
												"<p class='appraise-time'><span>"+format(data.result[i].comTime,"yyyy-MM-dd")+"</span><i>"+format(data.result[i].comTime,"HH:mm:ss")+"</i></p>"+
											"</li>";
							el.appendChild(oUI);
					    	}
					    	var pageNo=parseInt($("#page").attr("pageNo"))+1;
					    	$("#page").attr("pageNo",pageNo)
					    }
					})
					myScroll.refresh();
				}
			
		loading();
		})
		
		
	  //统计商品评论数
	   var proIds='${proId}'
	  $.ajax({
		  url:"${ctx}/wx/comment/countProComment",
		  method:"GET",
		  data:{
			  proId:proIds,
			  state:1
		  },
		  success:function(data){
			  $("#comCount").html(data.result)
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