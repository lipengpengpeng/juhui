<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp"%>
<body>
    <div class="container">
        <div class="agentList-top clearfix">
            <div class="agentTop-item left">商家总数</div>
            <div class="agentTop-item right">
            <c:if test="${not empty resultVo.result.totalCount}">${resultVo.result.totalCount}</c:if>
      		<c:if test="${empty resultVo.result.totalCount}">0</c:if>
            个</div>
        </div>
        <ul class="agentList-ul">
        	<c:if test="${empty resultVo.result.agentList}">
        	<div class="data-state-box">
		       <img src="${imagePath}/wx/o2o-shop/data-null.png" class="data-state-img">
		       <p class="data-state-text">没有数据</p>
		   </div>
        	</c:if>
        	<c:if test="${not empty resultVo.result.agentList}">
        	<c:forEach items="${resultVo.result.agentList}" var="bean" >
            <li class="agentList-li">
                <div class="agentList-box cleafix">
                    <div class="agentList-item left">
                        <img src="${imagePath}/wx/o2o-shop/person-icon.png" class="agentList-icon">
                        <span class="shop-name"><mtag:cutString value="${bean.storeName}" size="2" mark="***"/></span>
                    </div>
                    <div class="agentList-item right">
                        <mtag:cutString value="${bean.sellerMobile}" size="2" mark="***"/>
                    </div>
                </div>
                <div class="agentList-box agentList-middle cleafix">
                    <div class="agentList-mbox left">
                        <p class="agentList-money">${bean.storeSaleTotalMoney}</p>
                        <p class="agentList-money agentList-small">总营业额</p>
                    </div>
                    <div class="agentList-mbox left">
                        <p class="agentList-money red-money">${bean.storeMonthSaleTotalMoney}</p>
                        <p class="agentList-money agentList-small">当月营业额</p>
                    </div>
                </div>
            </li>
            </c:forEach>
            </c:if>
        </ul>
        <c:if test="${not empty resultVo.result.agentList}">
        <div class="loading-more">上拉加载更多</div>
        </c:if>
    </div>
    <script type="text/javascript">
    var pageNo=2;
	var pageSize=10;
        $(function(){
        	document.title = "个代";
        	$(window).scroll(function() {
                var totalHeight = $(window).height()+$(window).scrollTop();
                var docHeight = $(document).height();
                if(totalHeight>=docHeight){//拉到底部触发
                	$(".loading-more").html('<img src="${imagePath}/wx/o2o-shop/sm-loading.gif" class="sm-loadImg">');
                    $.ajax({
                        url: '${ctx}/wx/trade/findNextAgent',
                        type: 'get',
                        dataType: 'html',
                        data: {
                        	"pageNo":pageNo,
                        	"pageSize":pageSize,
                        	"reqType":"1"
                        },
                        success:function(data){
                            if(data==null || data == ""){
                                $(".loading-more").html("数据已加载完！");
                            }else{
                            	++pageNo;
                                $(".agentList-ul").append(data);
                                $(".loading-more").html("上拉加载更多");
                            }
                        },
                        error:function(){
                        	$(".loading-more").html("上拉加载更多");
                        }
                    });
                }
            });
        })
    </script>
</body>
</html>