<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<style>
			.headbg1 {
				background: #c9e6f5;
				font-weight: bold;
				padding-left: 15px;
			}
			
			.headbg1 td {
				font-weight: bold;
				text-align: center;
			}
		</style>

	
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 资金池分红信息
			</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="history.back();"/></div>
			<div class="clear"></div>
		</div>					
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>当次资金池分红金额</td>
				<td>当次资金池分红前金额</td>
				<td>当次资金池分红后金额</td>
				<td>当次资金池分红时间</td>
				<td>当次资金池分红类型</td>
				<td>当次让利分红的日期</td>
				<td>交易时间</td>
				<td>交易类型</td>
			</tr>
			
			 
			 	 <c:forEach var="bean" items="${gjfBenefitHistories}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.benefitMoney }</td>
					<td>${bean.benefitMoneyBf }</td>
					<td>${bean.benefitMoneyAf }</td>
					<td><fmt:formatDate value="${bean.addTime}" pattern="yyyy-MM-dd"/></td>
					<td>
						<c:choose>
							<c:when test="${bean.benefitType eq 0}">抽出会员分红池</c:when>
							<c:when test="${bean.benefitType eq 1}">抽出商家分红池</c:when>
						</c:choose>
					</td>
					<td>
						<fmt:formatDate value="${bean.benefitTime}" pattern="yyyy-MM-dd"/>
					</td>
					<td><fmt:formatDate value="${bean.tradeTime}" pattern="yyyy-MM-dd"/></td>
					<td>
						<c:choose>
							<c:when test="${bean.tradeStatus eq 0}">提交中</c:when>
							<c:when test="${bean.tradeStatus eq 1}">交易成功</c:when>
							<c:when test="${bean.tradeStatus eq 2}">交易失败</c:when>
						</c:choose>
					</td>
				</tr>
				</c:forEach>
		</table>

	</body>
</html>