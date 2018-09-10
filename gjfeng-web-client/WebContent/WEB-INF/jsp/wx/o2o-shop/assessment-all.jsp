<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
    <div class="container">
        <div class="all-assess-box">
            <div class="assess-nav">
                <ul class="assess-ul clearfix" id="getState">
                    <li class="assess-li left assess-li-active" id="allCom" state="0"></li>
                    <li class="assess-li left" id="lCom" state="1"></li>
                    <li class="assess-li left" state="2">最新</li>
                </ul>
            </div>
            <div class="wrap-points-box clearfix">
                <div class="wrap-real-point left" id="proScore"></div>
                <div class="shopInfo-box user-points left">
                    <div class="shopInfo-points-box" id="starList">
                       <!--  <i class="contMenu-icon icon-crown icon-crown-active"></i>
                        <i class="contMenu-icon icon-crown icon-crown-active"></i>
                        <i class="contMenu-icon icon-crown icon-crown-active"></i>
                        <i class="contMenu-icon icon-crown icon-crown-middle"></i>
                        <i class="contMenu-icon icon-crown"></i> -->
                    </div>
                </div>
                <div class="right">
                    <i class="users-num" id="users-num"></i>
                </div>
            </div>
            <div class="feedback-list" id="allInfo" pageNo="2" >
                 <c:forEach var="list" items="${resultVo.result}">
                     <div class="feedback-item" >
                    <div class="feedback-item-top clearfix">
                        <div class="feedback-head left">
                            <img src="${list.imgHeadUrl}" class="fh-img">
                        </div>
                        <div class="feedback-info left">
                            <div class="feedback-name">${list.nickName}</div>
                            <div class="feedback-time"><fmt:formatDate value="${list.comTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                        </div>
                        <div class="feedback-points right" >
                            <div class="shopInfo-box">
                                <c:if test="${list.comScore==0}">
                                    <i class="contMenu-icon icon-crown"></i>
                                    <i class="contMenu-icon icon-crown"></i>
                                    <i class="contMenu-icon icon-crown"></i>
                                    <i class="contMenu-icon icon-crown"></i>
                                    <i class="contMenu-icon icon-crown"></i>
                                </c:if>
                               
                                <c:if test="${list.comScore==1}">
                                    <i class="contMenu-icon icon-crown icon-crown-active"></i>
                                    <i class="contMenu-icon icon-crown"></i>
                                    <i class="contMenu-icon icon-crown"></i>
                                    <i class="contMenu-icon icon-crown"></i>
                                    <i class="contMenu-icon icon-crown"></i>
                                </c:if>
                                
                                
                                 <c:if test="${list.comScore==2}">
                                    <i class="contMenu-icon icon-crown icon-crown-active"></i>
                                    <i class="contMenu-icon icon-crown icon-crown-active"></i>
                                    <i class="contMenu-icon icon-crown"></i>
                                    <i class="contMenu-icon icon-crown"></i>
                                    <i class="contMenu-icon icon-crown"></i>
                                </c:if>
                                
                                
                                <c:if test="${list.comScore==3}">
                                    <i class="contMenu-icon icon-crown icon-crown-active"></i>
                                    <i class="contMenu-icon icon-crown icon-crown-active"></i>
                                    <i class="contMenu-icon icon-crown icon-crown-active"></i>
                                    <i class="contMenu-icon icon-crown"></i>
                                    <i class="contMenu-icon icon-crown"></i>
                                </c:if>

                                <c:if test="${list.comScore==4}">
                                    <i class="contMenu-icon icon-crown icon-crown-active"></i>
                                    <i class="contMenu-icon icon-crown icon-crown-active"></i>
                                    <i class="contMenu-icon icon-crown icon-crown-active"></i>
                                    <i class="contMenu-icon icon-crown icon-crown-active"></i>
                                    <i class="contMenu-icon icon-crown"></i>
                                </c:if>
 
                                 <c:if test="${list.comScore==5}">
                                    <i class="contMenu-icon icon-crown icon-crown-active"></i>
                                    <i class="contMenu-icon icon-crown icon-crown-active"></i>
                                    <i class="contMenu-icon icon-crown icon-crown-active"></i>
                                    <i class="contMenu-icon icon-crown icon-crown-active"></i>
                                    <i class="contMenu-icon icon-crown icon-crown-active"></i>
                                </c:if>                                
                            </div>
                        </div>
                    </div>
                    <div class="feedback-item-cont">
                     ${list.content}
                    </div>
                    <div class="feedback-item-photo">
                        <img src="${list.comImg}" class="feedback-photo">
                        <!-- <img src="images/instruct-1.png" class="feedback-photo">
                        <img src="images/instruct-1.png" class="feedback-photo"> -->
                    </div>
                </div>
                 </c:forEach>
               
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
                <div class="loading-middle hidden">
                    <img src="${ctx}/common/image/wx/o2o-shop/load.gif" class="loadImg">
                </div>
                <div class="loading-bottom hidden">
                    <img src="${ctx}/common/image/wx/o2o-shop/load.gif" class="loadImg">
                    <span class="loadBottom-text">加载中...</span>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        $(function(){
        	document.title = "评论";
            $(".assess-li").on("click",function(){
                $(this).addClass("assess-li-active").siblings().removeClass('assess-li-active');
                $(".loading-middle").removeClass('hidden');
                var state=$(this).attr("state")
                $("#allInfo").html("") 
                $.ajax({
                    url: '${ctx}/wx/comment/getAllProCommetByPage',
                    type: 'GET',                 
                    data: {
                    	pageNo:1,
                    	pageSize:4,
                    	proId:"${proId}",
                    	state:state,
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
            })
            $(window).scroll(function() {
                var totalHeight = $(window).height()+$(window).scrollTop();
                var docHeight = $(document).height();
                var state=$("#getState .assess-li-active").attr("state")
                if(totalHeight>=docHeight){//拉到底部触发
                    $(".loading-bottom").removeClass('hidden');
                    $.ajax({
                        url: '${ctx}/wx/comment/getAllProCommetByPage',
                        type: 'GET',                 
                        data: {
                        	pageNo:$("#allInfo").attr("pageNo"),
                        	pageSize:4,
                        	proId:"${proId}",
                        	state:state,
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
                        	  $(".loading-bottom").addClass('hidden');
                        	  var pageNo=parseInt($("#allInfo").attr("pageNo"))+1
                        	  $("#allInfo").attr("pageNo",pageNo)
                        	  $("#allInfo").append(str)
                          }
                    })
                }
            });
        })
        
        //获取评论数
            var proIdss='${proId}'
        	$.ajax({
      		  url:"${ctx}/wx/comment/countProComment",
      		  method:"GET",
      		  data:{
      			  proId:proIdss,
      			  state:1
      		  },
      		  success:function(data){
      			  $("#allCom").html("全部("+data.result+")");
      			$("#users-num").html("共"+data.result+"条");
      		  }
      	
        })
        
     
    
        	 $.ajax({
         		  url:"${ctx}/wx/comment/countProComment",
         		  method:"GET",
         		  data:{
         			  proId:"${proId}",
         			  state:2
         		  },
         		  success:function(data){
         			  $("#lCom").html("低分("+data.result+")");
         		  }
         	
           })

           //总分
           $.ajax({
        	   url:"${ctx}/wx/comment/getProductById",
        	   method:"GET",
        	   data:{
        		   proId:"${proId}"
        	   },
        	   success:function(data){
        		   var str=data.result.score;
        		   $("#proScore").html(str+"分");
        		   var str0="";
        		   if(str==0){
        			   str0="<i class='contMenu-icon icon-crown'></i>"
        			         +"<i class='contMenu-icon icon-crown'></i>"
        			         +"<i class='contMenu-icon icon-crown'></i>"
        			         +"<i class='contMenu-icon icon-crown'></i>"
        			         +"<i class='contMenu-icon icon-crown'></i>"                     
        		   }
        		   if(str>=0&&str<1){
        			   str0="<i class='contMenu-icon icon-crown icon-crown-middle'></i>"
      			         +"<i class='contMenu-icon icon-crown'></i>"
      			         +"<i class='contMenu-icon icon-crown'></i>"
      			         +"<i class='contMenu-icon icon-crown'></i>"
      			         +"<i class='contMenu-icon icon-crown'></i>" 
        		   }
        		   if(str==1){
        			   str0="<i class='contMenu-icon icon-crown icon-crown-active'></i>"
      			         +"<i class='contMenu-icon icon-crown'></i>"
      			         +"<i class='contMenu-icon icon-crown'></i>"
      			         +"<i class='contMenu-icon icon-crown'></i>"
      			         +"<i class='contMenu-icon icon-crown'></i>" 
        		   }
        		   
        		   if(str>1&&str<2){
        			   str0="<i class='contMenu-icon icon-crown icon-crown-active'></i>"
      			         +"<i class='contMenu-icon icon-crown icon-crown-middle'></i>"
      			         +"<i class='contMenu-icon icon-crown'></i>"
      			         +"<i class='contMenu-icon icon-crown'></i>"
      			         +"<i class='contMenu-icon icon-crown'></i>" 
        		   }
        		   
        		   if(str==2){
        
        			   str0="<i class='contMenu-icon icon-crown icon-crown-active'></i>"
      			         +"<i class='contMenu-icon icon-crown icon-crown-active'></i>"
      			         +"<i class='contMenu-icon icon-crown'></i>"
      			         +"<i class='contMenu-icon icon-crown'></i>"
      			         +"<i class='contMenu-icon icon-crown'></i>" 
        		   }
        		   
        		   if(str>2&&str<3){
        	
        			   str0="<i class='contMenu-icon icon-crown icon-crown-active'></i>"
      			         +"<i class='contMenu-icon icon-crown icon-crown-active'></i>"
      			         +"<i class='contMenu-icon icon-crown icon-crown-middle'></i>"
      			         +"<i class='contMenu-icon icon-crown'></i>"
      			         +"<i class='contMenu-icon icon-crown'></i>" 
        		   }
        		   
        		   if(str==3){
        
        			   str0="<i class='contMenu-icon icon-crown icon-crown-active'></i>"
      			         +"<i class='contMenu-icon icon-crown icon-crown-active'></i>"
      			         +"<i class='contMenu-icon icon-crown icon-crown-active'></i>"
      			         +"<i class='contMenu-icon icon-crown'></i>"
      			         +"<i class='contMenu-icon icon-crown'></i>" 
        		   }
        		   
        		   if(str>3&&str<4){
        		
        			   str0="<i class='contMenu-icon icon-crown icon-crown-active'></i>"
      			         +"<i class='contMenu-icon icon-crown icon-crown-active'></i>"
      			         +"<i class='contMenu-icon icon-crown icon-crown-active'></i>"
      			         +"<i class='contMenu-icon icon-crown icon-crown-middle'></i>"
      			         +"<i class='contMenu-icon icon-crown'></i>" 
        		   }
        		   
        		   if(str==4){
        			   str0="<i class='contMenu-icon icon-crown icon-crown-active'></i>"
      			         +"<i class='contMenu-icon icon-crown icon-crown-active'></i>"
      			         +"<i class='contMenu-icon icon-crown icon-crown-active'></i>"
      			         +"<i class='contMenu-icon icon-crown icon-crown-active'></i>"
      			         +"<i class='contMenu-icon icon-crown'></i>" 
        		   }
        		   
        		   if(str>4&&str<5){
        			   str0="<i class='contMenu-icon icon-crown icon-crown-active'></i>"
      			         +"<i class='contMenu-icon icon-crown icon-crown-active'></i>"
      			         +"<i class='contMenu-icon icon-crown icon-crown-active'></i>"
      			         +"<i class='contMenu-icon icon-crown icon-crown-active'></i>"
      			         +"<i class='contMenu-icon icon-crown icon-crown-middle'></i>" 
        		   }
        		   
        		   if(str==4){
        			   str0="<i class='contMenu-icon icon-crown icon-crown-active'></i>"
      			         +"<i class='contMenu-icon icon-crown icon-crown-active'></i>"
      			         +"<i class='contMenu-icon icon-crown icon-crown-active'></i>"
      			         +"<i class='contMenu-icon icon-crown icon-crown-active'></i>"
      			         +"<i class='contMenu-icon icon-crown icon-crown-active'></i>" 
        		   }
        		   $("#starList").html(str0)
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