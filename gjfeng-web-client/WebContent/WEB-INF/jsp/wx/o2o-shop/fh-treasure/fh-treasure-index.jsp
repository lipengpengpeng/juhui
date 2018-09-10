<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
	<div class="container">
		<div class="shouxin-top">
			<div class="s-top-1">巨惠宝余额</div>
			<div class="s-top-2">${resultVo.result.fhTreasureMoney}</div>
		</div>
		<ul class="sx-ul">
			<li class="sx-li">
                <a href="${ctx}/wx/trade/toBalanceTransferPage" class="sx-link clearfix">
                    <span class="list-text left"><i class="my-icon icon-sx-1"></i>余额转入巨惠宝</span>
                    <span class="list-more right"><i class="my-icon icon-more"></i></span>    
                </a>
            </li>
            <%-- <li class="sx-li">
                <a href="${ctx}/wx/trade/toTransferFhTreasurePage" class="sx-link clearfix">
                    <span class="list-text left"><i class="my-icon icon-sx-1"></i>巨惠宝金额转移</span>
                    <span class="list-more right"><i class="my-icon icon-more"></i></span>    
                </a>
            </li>--%>
			<li class="sx-li">
				<a href="${ctx}/wx/trade/toDrawCash" class="sx-link clearfix">
                    <span class="list-text left"><i class="my-icon icon-sx-2"></i>巨惠宝提现</span>
                    <span class="list-more right"><i class="my-icon icon-more"></i></span>    
                </a>
			</li> 
		</ul>
		<ul class="sx-ul sx-ul-2">
			<li class="sx-li">
                <a href="${ctx}/wx/trade/toFhTreasureTradeHistory?pageNo=1&pageSize=50" class="sx-link clearfix">
                    <span class="list-text left"><i class="my-icon icon-sx-3"></i>巨惠宝交易记录</span>
                    <span class="list-more right"><i class="my-icon icon-more"></i></span>    
                </a>
            </li>
        </ul>
	</div>
</body>
<script>
document.title = "巨惠宝";
</script>
</html>