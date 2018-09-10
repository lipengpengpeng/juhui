<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body style="background-color: #fff;">
    <div class="container">
    	<form action="${ctx}/wx/index/o2o/search" method="post">
            <div class="search-header clearfix">
                <div class="sHead-item-1 left">
                    <i class="main-sprite icon-left-arrow"></i>
                </div>
                <div class="sHead-item-2 left">
                    <i class="main-sprite icon-magnifying-2"></i>
                    <input type="text" name="likeValue" class="sHead-input" placeholder="搜索商家或商品">
                </div>
                <input type="hidden" name="columnId" value="${columnId}">
                <input type="hidden" name="columnType" value="${columnType}">
                <div class="sHead-item-3 right">
                    <button type="submit" class="sHead-btn">搜索</button>
                </div>
            </div>
        </form>
        <div class="search-hot">
            <div class="search-list">
                <div class="sHot-title">
                    <span class="sHot-tit-left">
                        <i class="main-sprite icon-magnifying-3"></i>
                    </span>
                    <span class="sHot-tit-right">历史搜索</span>
                </div>
                <ul class="search-ul clearfix">
                 <c:if test="${not empty searchHistory.result}">
                 <c:forEach items="${searchHistory.result}" var="bean" varStatus="status">
                    <li class="search-li left">
                    <%-- <c:if test="${indexSearch eq '1'}">
                     	<a href="${ctx}/wx/index/o2o?likeValue=${bean.keyWord}" class="search-link">${bean.keyWord}</a>
                    </c:if>
                    <c:if test="${indexSearch eq '0'}">
                     	<a href="${ctx}/wx/index/o2o/subColumn/${clolumnId}?likeValue=${bean.keyWord}" class="search-link">${bean.keyWord}</a>
                    </c:if> --%>
                    <a href="javascript:void(0)" class="search-link">${bean.keyWord}</a>
                    </li>
                  </c:forEach>
                  </c:if>
                </ul>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        $(function(){
        	document.title = "搜索";
            $(".sHead-input").click();
            $(".search-link").on("click",function(){
                var text = $(this).text();
                $(".sHead-input").val(text);
            })
            $(".sHead-item-1").on("click",function(){
                window.history.go(-1);
            })
        })
    </script>
</body>
</html>