<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
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

		<script type="text/javascript">
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 代理下商户流水
			</div>
			<div class="clear"></div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="history.back();"/></div>
		</div>	
				
					
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>交易流水号</td>
				<td>用户</td>
				<td>商家</td>
				<td>消费金额</td>
				<td>让利金额</td>
				<td>添加时间</td>
				<td>支付类型</td>
				<td>交易状态</td>
			</tr>
			
			 
			<c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.tradeNo }</td>
					<td>${bean.name }</td>
					<td>${bean.storeName }</td>
					<td>
						${bean.tradeMoney }
					</td>
					<td>
						${bean.benefitMoney }
					</td>
					<td>
						<fmt:formatDate value="${bean.addTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					
					<td>
						<c:choose>
							<c:when test="${bean.payType eq '0' }">微信支付</c:when>
							<c:when test="${bean.payType eq '1' }">支付宝支付</c:when>
							<c:when test="${bean.payType eq '2' }">银联支付</c:when>
							<c:when test="${bean.payType eq '4' }">授信金额支付</c:when>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${bean.tradeStatus eq '0' }">待支付</c:when>
							<c:when test="${bean.tradeStatus eq '1' }">已支付</c:when>
							<c:when test="${bean.storeStatus eq '2' }">审核中</c:when>
							<c:when test="${bean.storeStatus eq '3' }">取消</c:when>
						</c:choose>
					</td>
				</tr>
	</c:forEach>
		</table>

		<c:if test="${not empty pager.resultList}">
			<s:form action="storeInfoAction!findStoreBenefitByAgent.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
					<input type="hidden" name="id" value="${id }"/>
					<input type="hidden" name="token" value="${token }"/>
					<input type="hidden" name="identity" value="${identity }"/>
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>