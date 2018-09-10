<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
    <div class="container">
        <div class="wait-box">
            <img src="${imagePath}/wx/o2o-shop/under-review.png" class="wait-img">
           	<p class="wait-text-1">信息提示</p>
           	<p class="wait-text-2">${resultVo.msg}</p>
           	<p id="jumpTo" class="wait-text-1" style="margin-top: 63px">3秒后自动跳转</p>
        </div>
    </div>
</body>
<script>
	document.title = "信息提示";
	countDown(3);
    function countDown(secs){     
    	var jumpTo = document.getElementById('jumpTo');
    	jumpTo.innerHTML=secs+"秒后自动跳转";  
   		if(--secs>0){     
        	setTimeout("countDown("+secs+")",1000);     
        }else{
        	if("${resultVo.code}"==200){
    			location.href='${ctx}/wx/member/my';
   		    }else{
   		    	history.back(-1);
   		    }
     	}     
   }  
</script>
</html>