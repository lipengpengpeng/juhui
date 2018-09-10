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

		<script>
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 流水记录
			</div>
			<div class="clear"></div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="history.back();"/></div>	
		</div>	
		<%-- <form action="countInfoAction!toBenefitReport.action" method="get">
			<table class="listTable2">
				<tr>
					<td>
				日期：<input type="text" readonly="readonly" placeholder="请选择日期" value="${addTime }" name="addTime" onclick="WdatePicker()" style="width: 75px;"/>&nbsp;&nbsp;&nbsp;				
					 <input type="submit" style="margin-left:30px;" class="default_button" value='<s:text name="common.word.search" />' />
					</td>
				</tr>
			</table>
		</form>	 --%>				
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>交易单号</td>
				<td>交易金额</td>
				<td>添加时间</td>
				<td>交易时间</td>
				<td>交易类型</td>
				<td>交易状态</td>
			</tr>
			
	    <c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.tradeNo }</td>
					<td>${bean.tradeMoney }</td>
					<td><fmt:formatDate value="${bean.addTime }" pattern="yyyy-MM-dd"/></td>
					<td><fmt:formatDate value="${bean.tradeTime }" pattern="yyyy-MM-dd"/></td>
					<td>
						<c:choose>
							<c:when test="${bean.tradeType eq '0'}">余额交易</c:when>
							<c:when test="${bean.tradeType eq '1'}">提现金额交易</c:when>
							<c:when test="${bean.tradeType eq '2'}">责任消费金额交易</c:when>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${bean.tradeStatus eq '0'}">减少</c:when>
							<c:when test="${bean.tradeStatus eq '1'}">增加</c:when>
						</c:choose>
					</td> 
				</tr>
			</c:forEach>
		</table>

		<c:if test="${not empty pager.resultList}">
			<s:form action="memberInfoAction!findMoneyHistoryByMemberId.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
					<input type="hidden" readonly="readonly" value="${id }" name="id"/>
					<input type="hidden" readonly="readonly" value="${token }" name="token"/>
					<input type="hidden" readonly="readonly" value="${tradeType }" name="tradeType"/>
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>