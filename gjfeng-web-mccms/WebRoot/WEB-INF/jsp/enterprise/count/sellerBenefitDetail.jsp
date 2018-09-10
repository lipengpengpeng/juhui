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
		<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
			function back(){
				window.location.href = "countInfoAction!countStoresBenefit.action";
			}
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 统计 - 商家让利明细
			</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="back()"/></div>
			<div class="clear"></div>
		</div>
		<form action="countInfoAction!findBenefitDetail.action" method="get">
			<input type="hidden" name="id" value="${id }"/>
			<table class="listTable2">
				<tr>
					<td>
						开始时间：<input type="text" readonly="readonly"  placeholder="请选择日期" value="${startTime }" name="startTime" id="startTime" onclick="WdatePicker()" style="width: 75px;"/>
						-
					  	结束时间：<input type="text" readonly="readonly"  placeholder="请选择日期" value="${endTime }" name="endTime" id="endTime" onclick="WdatePicker()" style="width: 75px;"/>
						&nbsp;&nbsp;&nbsp;&nbsp;
							
						
						 <input type="submit" style="margin-left:30px;" class="default_button" value='<s:text name="common.word.search" />' />
					</td>
				</tr>
			</table>
		</form>
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>交易流水号</td>
				<td>让利用户</td>
				<td>消费金额</td>
				<td>让利金额</td>
				<td>时间</td>
				<td>支付方式</td>
				<td>交易状态</td>
			</tr>

			<c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.tradeNo }</td>
					<td>${bean.memberName }</td>
					<td>${bean.tradeMoney }</td>
					<td>${bean.benefitMoney }</td>
					<td><fmt:formatDate value="${bean.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>
						<c:if test="${bean.payType eq 0}">微信支付</c:if>
						<c:if test="${bean.payType eq 1}">支付宝支付</c:if>
						<c:if test="${bean.payType eq 2}">银联支付</c:if>
						<c:if test="${bean.payType eq 3}">H5支付</c:if>
						<c:if test="${bean.payType eq 4}">授信金额支付</c:if>
						<c:if test="${bean.payType eq 5}">后台充值</c:if>
					
					</td>
					<td>
						<c:if test="${bean.tradeStatus eq 0}">待支付</c:if>
						<c:if test="${bean.tradeStatus eq 1}">已支付</c:if>
						<c:if test="${bean.tradeStatus eq 2}">支付失败</c:if>
						<c:if test="${bean.tradeStatus eq 3}">取消</c:if>
					</td>
				</tr>
			</c:forEach>

		</table>

		<c:if test="${not empty pager.resultList}">
			<s:form action="countInfoAction!findBenefitDetail.action"
				namespace="/subsystem"	 method="post" name="form1" theme="simple" id="form1">
				<input type="hidden" name="id"  value="${id }" readonly="readonly"/>
				<input type="hidden" readonly="readonly"   value="${startTime }" name="startTime" id="startTime"/>
				<input type="hidden" readonly="readonly"   value="${endTime }" name="endTime" id="endTime"/>
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>