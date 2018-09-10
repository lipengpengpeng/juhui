<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>查看</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<script>
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">当前位置: 商家让利记录 - 查看明细</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="history.back();"/></div>
		</div>

		<table  align="center" class="listTable3">
				<tr>
					<td class="pn-flabel" width="100px">交易流水号</td>
					<td>${gjfMemberTradeBenefit.tradeNo}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">购买的用户</td>
					<td>${gjfMemberTradeBenefit.memberId.name}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">让利的商家</td>
					<td>${gjfMemberTradeBenefit.storeId.storeName}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">消费金额</td>
					<td>${gjfMemberTradeBenefit.tradeMoney}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">让利金额</td>
					<td>${gjfMemberTradeBenefit.benefitMoney}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">产生会员分红权</td>
					<td>${gjfMemberTradeBenefit.memberDividendsNum}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">产生商家分红权</td>
					<td>${gjfMemberTradeBenefit.merchantsDividendsNum}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">直推会员推荐奖励</td>
					<td>${gjfMemberTradeBenefit.directMemberMoney}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">直推商家推荐奖励</td>
					<td>${gjfMemberTradeBenefit.directMerchantsMoney}</td>
				</tr>
				
				<tr>
					<td class="pn-flabel" width="100px">奖励会员</td>
					<td>${gjfMemberTradeBenefit.directMember.name}</td>
				</tr>
			
				<tr>
					<td class="pn-flabel" width="100px">奖励商家</td>
					<td>${gjfMemberTradeBenefit.directMerchants.name}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">添加时间</td>
					<td>
						<fmt:formatDate value="${gjfMemberTradeBenefit.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">支付类型</td>
					<td>
						<c:choose>
							<c:when test="${gjfMemberTradeBenefit.payType eq '0'}">微信支付</c:when>
							<c:when test="${gjfMemberTradeBenefit.payType eq '1'}">支付宝支付</c:when>
							<c:when test="${gjfMemberTradeBenefit.payType eq '2'}">银联支付</c:when>
							<c:when test="${gjfMemberTradeBenefit.payType eq '3'}">H5支付</c:when>
							<c:when test="${gjfMemberTradeBenefit.payType eq '4'}">授信金额支付</c:when>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">交易状态</td>
					<td>
						<c:choose>
							<c:when test="${gjfMemberTradeBenefit.tradeStatus eq '0'}">待支付</c:when>
							<c:when test="${gjfMemberTradeBenefit.tradeStatus eq '1'}">已支付</c:when>
							<c:when test="${gjfMemberTradeBenefit.tradeStatus eq '2'}">支付失败</c:when>
							<c:when test="${gjfMemberTradeBenefit.tradeStatus eq '3'}">取消</c:when>
						</c:choose>
					</td>
				</tr>
		 </table>
	</body>
</html>