<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp"%>
<body>
	<div class="container">
		<div class="wait-box">
			<img src="${imagePath}/wx/o2o-shop/under-review.png" class="wait-img">
			<c:if test="${resultVo.code == 200}">
				<p class="wait-text-1">申请成功</p>
				<!-- <p class="wait-text-2">您的申请将在1-3个工作日内审核,请您耐心等待</p>-->
			</c:if>
			<c:if test="${resultVo.code != 200}">
				<p class="wait-text-1">信息提示</p>
				<p class="wait-text-2">${resultVo.msg}</p>
			</c:if>
			<c:if test="${empty resultStatus}">
				<p id="jumpTo" class="wait-text-1" style="margin-top: 63px">3秒后自动跳转</p>
			</c:if>
		</div>
	</div>
</body>
<script>
	document.title = "信息提示";
	var code="${resultVo.code}";
	var status="${resultStatus}";
	countDown(3);
    function countDown(secs){
    	if(null == status || status!='1'){
    		var jumpTo = document.getElementById('jumpTo');
        	jumpTo.innerHTML=secs+"秒后自动跳转";  
       		if(--secs>0){     
            	setTimeout("countDown("+secs+")",1000);     
            }     
        	else{
        		if(code==200){
        			location.href='${ctx}/wx/member/my';
       		    }else{
       		    	history.back(-1);
       		    }
         	} 
    	}
   }  
</script>
</html>