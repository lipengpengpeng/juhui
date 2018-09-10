<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
	<div class="container">
		<div class="wallet-top wallet-branch agent-top">
			<div class="wallet-tab-box" id="wallet-1-1">
				<div class="wallet-branch-2 clearfix agent-top-box">
					<div class="w2-left left">
						<p class="w2-text">代理总收入</p>
						<div class="w2-num">${resultVo.result.agentTotalMoney}</div>
					</div>
					<div class="w2-right left">
						<p class="w2-text">待派发收入</p>
						<div class="w2-money">${resultVo.result.agentMoney}</div>
					</div>
				</div>
			</div>
		</div>
		<div class="wallet-tab-box" id="wallet-1-2">
			<div class="wallet-middle clearfix">
				<div class="wm-item left agent-item">
					<p class="wm-text">今天商家让利</p>
					<div class="wm-money">${resultVo.result.noStoreTotalBenefit}</div>
				</div>
				<div class="wm-item left agent-item">
					<p class="wm-text">商家总让利</p>
					<div class="wm-money">${resultVo.result.storeTotalBenefit}</div>
				</div>
			</div>
		</div>
		<div class="wallet-menu clearfix">
			<a href="${ctx}/wx/trade/findNextAgent" class="wmenu-item left agent-item">
				<div class="wmenu-img">
					<img src="${ctx}/common/image/wx/o2o-shop/wallet_history.png">
				</div>
				<div class="wmenu-text">商家列表</div>
			</a>
			<a href="${ctx}/wx/trade/toAgentHistory" class="wmenu-item left agent-item">
				<div class="wmenu-img">
					<img src="${ctx}/common/image/wx/o2o-shop/wallet_history.png">
				</div>
				<div class="wmenu-text">收入历史</div>
			</a>
			<a href="${ctx}/wx/rule/agent" class="wmenu-item left agent-item">
				<div class="wmenu-img">
					<img src="${ctx}/common/image/wx/o2o-shop/agent_rule.png">
				</div>
				<div class="wmenu-text">代理规则</div>
			</a>
		</div>
	</div>
</body>
<script>
	document.title = "我是代理";
</script>
</html>