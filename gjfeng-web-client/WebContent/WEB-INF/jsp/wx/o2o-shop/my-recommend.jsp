<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
    <div class="container">
        <ul class="people-ul" pageNo="2">
        <c:if test="${not empty resultVo.result}">
        <c:forEach var="member" items="${resultVo.result}">
         <li class="people-li clearfix">
                <div class="left people-item-left">
                   <c:if test="${not empty member.imgHeadUrl}">
                      <img src=" ${member.imgHeadUrl}" class="people-head">
                   </c:if>
                   <c:if test="${empty member.imgHeadUrl}">
                      <img src=" ${ctx}/common/image/wx/o2o-shop/default-people.png" class="people-head">
                   </c:if>                   
                </div>
                <div class="left people-item-right">
                <c:if test="${member.isReadName==0}">
                   <div class="people-name cut-word">${member.nickName}</div>
                </c:if>
                 <c:if test="${member.isReadName==1}">
                   <div class="people-name cut-word">${member.name}</div>
                </c:if>
                    
                    <div class="people-id cut-word">ID:${member.id}</div>
                </div>
            </li>
        </c:forEach>
        <div class="loading-more">上拉加载更多</div>
        </c:if>
        <c:if test="${empty resultVo.result}">
			<div class="data-state-box">
		       <img src="${imagePath}/wx/o2o-shop/data-null.png" class="data-state-img">
		       <p class="data-state-text">没有数据</p>
		   </div>
		</c:if>       
        </ul> 
    </div>
    <script type="text/javascript">
        $(function(){
            $(window).scroll(function() {
                var totalHeight = $(window).height()+$(window).scrollTop();
                var docHeight = $(document).height();
                var superId="${superId}"
                var pageNo=$(".people-ul").attr("pageNo")
                if(totalHeight>=docHeight){//拉到底部触发
                    $(".loading-more").html('<img src="${ctx}/common/image/wx/o2o-shop/sm-loading.gif" class="sm-loadImg">');
                   $.ajax({
                        url: "${ctx}/wx/member/getMemberLowerLevels?superId="+superId+"&pageNo="+pageNo+"&pageSize=15",
                        type: 'GET',
                        success:function(data){
                            if(data.result){
                            	var str=""
                                    for(var i=0;i<data.result.length;i++){
                                		str+="<li class='people-li clearfix'>"
                                		     +"<div class='left people-item-left'>"
                                		     if(data.result[i].imgHeadUrl!=""){
                                		    	str+="<img src="+data.result[i].imgHeadUrl+" class='people-head'>" 
                                		     }else{
                                		    	 str+="<img src='${ctx}/common/image/wx/o2o-shop/default-people.png' class='people-head'>"
                                		     }
                                		     str+="</div><div class='left people-item-right'>"
                                              
                                		     if(data.result[i].isReadName==0){
                                		    	 str+="<div class='people-name cut-word'>"+data.result[i].nickName+"</div>"
                                		     }
                                		     if(data.result[i].isReadName==1){
                                		    	 str+="<div class='people-name cut-word'>"+data.result[i].name+"</div>"
                                		     }
                                             str+="<div class='people-id cut-word'>ID:"+data.result[i].id+"</div></div></li>"
     
                                 
                                	}
                            	    pageNo=parseInt($(".people-ul").attr("pageNo"))+1
                            	    $(".people-ul").attr("pageNo",pageNo);
                                	$(".people-ul").append(str)
                                    $(".loading-more").html('上拉加载更多');
                               
                            }else{
                            	alert("dd")
                            	 $(".loading-more").html("数据已加载完！");
                            }
                        },                       
                    });
                }
            });
        })
    </script>
</body>
</html>