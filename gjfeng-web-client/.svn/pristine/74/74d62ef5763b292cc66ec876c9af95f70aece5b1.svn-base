<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
    <div class="container">
        <div class="wait-box">
            <img src="${imagePath}/wx/o2o-shop/under-review.png" class="wait-img">
            <c:if test="${resultVo.code == 200}">
            	<p class="wait-text-1">已提交申请</p>
            	<p class="wait-text-2">${resultVo.msg}</p>
            </c:if>
            <c:if test="${resultVo.code != 200}">
            	<p class="wait-text-1">信息提示</p>
            	<p class="wait-text-2">${resultVo.msg}</p>
            </c:if>
            <p id="jumpTo" class="wait-text-1" style="margin-top: 63px">3秒后自动跳转</p>
        </div>
    </div>
</body>

<script type="text/JavaScript">
  document.title = "实名制申请";
   var code="${resultVo.code}"
   if(code==200){
	  countDown(3,'${ctx}/wx/member/myWallet')
  }else{
	  countDown(3,'${ctx}/wx/member/toRealName') 
  }
  function countDown(secs,surl){     
    var jumpTo = document.getElementById('jumpTo');
    jumpTo.innerHTML=secs+"秒后自动跳转";  
   if(--secs>0){     
        setTimeout("countDown("+secs+",'"+surl+"')",1000);     
        }     
    else{       
        location.href=surl;     
     }     
  }   
</script>  
</html>