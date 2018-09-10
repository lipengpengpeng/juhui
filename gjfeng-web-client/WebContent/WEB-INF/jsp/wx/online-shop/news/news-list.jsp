<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="description" content="">
  <meta name="keywords" content="">
  <meta name="viewport"
        content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <title>新闻列表</title>
  <link rel="stylesheet" href="${ctx}/common/css/wx/online-shop/news/amazeui.min.css">
  <link rel="stylesheet" href="${ctx}/common/css/wx/online-shop/news/app.css">
</head>
<body>
<div class="am-g">
  <header data-am-widget="header"
          class="amazeMarchHead am-header am-header-default">    
      <h1 class="amazeMarchHeadTitle am-header-title">
      	       新闻列表      
      </h1>
  </header>
  <div class="newsList">
   <c:if test="${not empty newsInfo}">
      <c:forEach var="news" items="${newsInfo}">
         <div class="newsListBlock"  onclick="goNewsDetail(${news.id})">
  	         <div class="newsListTitle">
  		         <div class="newsListTitleLeft">
  			         <div class="userHead">
  				        <!-- <img src="assets/img/user04.png" > -->
  			         </div>
  			         <div class="userName">${news.source}</div>
  		         </div>
  		         <div class="newsListTitleRight">
  			       <div class="newsListTitleRightMap">
  				      <!-- <i class="am-icon-map-o"></i> --> 
  				      <fmt:formatDate value="${news.initTime}" type="both" pattern="yyyy-MM-dd" />
  			       </div>
  		         </div>
  	         </div>
  	         <div class="newsListBanner">
  		        <div class="newsListBannerImg">
  			       <img src="${news.photo}">
  		        </div>
  	         </div>

  	         <div class="newsListText">
  		        ${news.title}
  	         </div>	
  	    </div>      
      </c:forEach>
   </c:if> 
  </div>
</div>
</body>
<script type="text/javascript">
function goNewsDetail(newsId){
	window.location.href="${ctx}/wx/index/findNewsById?id="+newsId;
}

</script>
</html>
