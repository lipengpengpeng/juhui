<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
	<div class="container">
		<ul class="h-detail-ul">
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">交易流水号：</div>
				<div class="h-detail-right right">${resultVo.result.tradeNo}</div>
			</li>
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">用户名称：</div>
				<div class="h-detail-right right">${resultVo.result.memberName}</div>
			</li>
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">申请金额：</div>
				<div class="h-detail-right right">${resultVo.result.applyMoney}</div>
			</li>
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">交易金额：</div>
				<div class="h-detail-right right">${resultVo.result.tradeMoney}</div>
			</li>
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">手续费：</div>
				<div class="h-detail-right right">${resultVo.result.taxMoney}</div>
			</li>
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">责任消费：</div>
				<div class="h-detail-right right">${resultVo.result.insuranceMoney}</div>
			</li>
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">银行卡号：</div>
				<div class="h-detail-right right">${resultVo.result.bankCard}</div>
			</li>
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">持卡人：</div>
				<div class="h-detail-right right">${resultVo.result.holder}</div>
			</li>
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">银行名称：</div>
				<div class="h-detail-right right">${resultVo.result.bankName}</div>
			</li>
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">支行名称：</div>
				<div class="h-detail-right right">${resultVo.result.bankSub}</div>
			</li>
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">申请时间：</div>
				<div class="h-detail-right right"><mtag:longToDate value="${resultVo.result.addTime}" pattern="yyyy-MM-dd HH:mm"/></div>
			</li>
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">处理时间：</div>
				<div class="h-detail-right right"><mtag:longToDate value="${resultVo.result.dealTime}" pattern="yyyy-MM-dd HH:mm"/></div>
			</li>
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">交易时间：</div>
				<div class="h-detail-right right"><mtag:longToDate value="${resultVo.result.tradeTime}" pattern="yyyy-MM-dd HH:mm"/></div>
			</li>
			<li class="h-detail-li clearfix">
				<div class="h-detail-left left">交易状态：</div>
				<div class="h-detail-right right">
				<c:if test="${resultVo.result.tradeStatus==0}">
					审核中
				</c:if>	
				<c:if test="${resultVo.result.tradeStatus==1}">
					交易成功
				</c:if>	
				<c:if test="${resultVo.result.tradeStatus==2}">
					交易失败
				</c:if>	
				</div>
			</li>
		</ul>
	</div>
</body>
<script type="text/javascript">
    document.title = "提现详情";
</script>
</html>