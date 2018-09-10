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
				window.location.href = "storeInfoAction!retrieveStoreByPager.action";
			}
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 统计 - 店铺订单
			</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="back()"/></div>
			<div class="clear"></div>
		</div>
		<form action="countInfoAction!findOrderByCondition.action" method="get">
			<input type="hidden" name="id" value="${id }"/>
			<input type="hidden" name="token" value="${token }"/>
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
		<div>
			<table class="listTable3">
				<tr id="nc" class="headbg" style="background-color: #1F6D9E;height: 50px;">
		  	 	   <td colspan="4" style="cl;font-size: 18px">
		  	 	   		<font color="#FFFFFF">当前条件统计</font>
		 	  	   </td>
			   </tr>
		  		<tr id="nc" class="headbg" style="background-color: #1F6D9E;height: 50px;">
		  	 	   <td colspan="2" style="cl;font-size: 18px">
		  	 	   		<font color="#FFFFFF">消费金额：${object.totalTradeMoney }</font>
		 	  	   </td>
		 	  	     <td colspan="2" style="cl;font-size: 18px">
		  	 	   		<font color="#FFFFFF">让利金额：${object.totalBenefitMoney }</font>
		 	  	   </td>
			   </tr>			
			</table>
		</div>
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>交易流水号</td>
				<td>购买的用户</td>
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
					<td>${bean.tradeNo}</td>
					<td>${bean.memberId.name}</td>
					<td>${bean.tradeMoney}</td>
					<td>${bean.benefitMoney}</td>
					<td>
						<fmt:formatDate value="${bean.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
							<c:if test="${bean.payType eq '0'}">微信支付</c:if>
							<c:if test="${bean.payType eq '1'}">支付宝支付</c:if>
							<c:if test="${bean.payType eq '2'}">银联支付</c:if>
							<c:if test="${bean.payType eq '3'}">授信金额支付</c:if>
					</td>
					<td>
							<c:if test="${bean.tradeStatus eq '0'}">待支付</c:if>
							<c:if test="${bean.tradeStatus eq '1'}">已支付</c:if>
							<c:if test="${bean.tradeStatus eq '2'}">支付失败</c:if>
							<c:if test="${bean.tradeStatus eq '3'}">取消</c:if>
					</td>
				</tr>
			</c:forEach>

		</table>

		<c:if test="${not empty pager.resultList}">
			<s:form action="countInfoAction!findOrderByCondition.action"
				namespace="/subsystem"	 method="post" name="form1" theme="simple" id="form1">
				<input type="hidden" name="id" value="${id }"/>
				<input type="hidden" name="token" value="${token }"/>
				<input type="hidden" readonly="readonly" value="${startTime }" name="startTime" />
				<input type="hidden" readonly="readonly" value="${endTime }" name="endTime" />
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>