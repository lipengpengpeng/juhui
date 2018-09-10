<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
	<div class="container">
		<div class="shouxin-top">
			<div class="s-top-1">我的授信额度</div>
			<div class="s-top-2">${resultVo.result.lineOfCrade}</div>
		</div>
		<ul class="sx-ul">
			<li class="sx-li">
                <a href="${ctx}/wx/member/goBuyShouXin?shouXinMenoy=${resultVo.result.lineOfCrade}" class="sx-link clearfix">
                    <span class="list-text left"><i class="my-icon icon-sx-1"></i>线上购买授信额度</span>
                    <span class="list-more right"><i class="my-icon icon-more"></i></span>    
                </a>
            </li>
            <li class="sx-li">
                <a href="${ctx}/wx/member/goOfflineBuyShouXin?shouXinMenoy=${resultVo.result.lineOfCrade}" class="sx-link clearfix">
                    <span class="list-text left"><i class="my-icon icon-sx-1"></i>线下购买授信额度</span>
                    <span class="list-more right"><i class="my-icon icon-more"></i></span>    
                </a>
            </li>
			<li class="sx-li">
				<a href="${ctx}/wx/trade/toBenefit" class="sx-link clearfix">
                    <span class="list-text left"><i class="my-icon icon-sx-2"></i>销售录入</span>
                    <span class="list-more right"><i class="my-icon icon-more"></i></span>    
                </a>
			</li>
		</ul>
		<ul class="sx-ul sx-ul-2">
			<li class="sx-li">
                <a href="${ctx}/wx/member/goAllShouXin?pageNo=1&pageSize=10" class="sx-link clearfix">
                    <span class="list-text left"><i class="my-icon icon-sx-3"></i>授信记录查询</span>
                    <span class="list-more right"><i class="my-icon icon-more"></i></span>    
                </a>
            </li>
        </ul>
	</div>
</body>
<script>
document.title = "授信额度";
</script>
</html>