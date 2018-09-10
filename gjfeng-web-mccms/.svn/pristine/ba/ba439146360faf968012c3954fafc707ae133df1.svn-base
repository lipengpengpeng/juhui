<%--

	gjfMemberTradeDivi.jsp

	Create by MCGT

	Create time 2017-01-20

	Version 1.0

	Copyright 2013 Messcat, Inc. All rights reserved.

 --%>
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
				function doYouWantToExport() {
					if(confirm('确定要导出所有记录吗?')) {
						return true;
					}
					return false;
				}
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 交易管理 - 支付明细
			</div>
			<div class="ropt"><a href="tradeInfoAction!retrieveTradeLogByPage.action?name=${name }&storeName=${storeName }&addTime=${addTime }&tradeNo=${tradeNo }&payTradeNo=${payTradeNo }&tradeType=${tradeType }&tradeStatus=${tradeStatus }&ste=1" onclick="return doYouWantToExport();">导出</a></div>
		</div>
		<div>
			<table class="listTable3">
				<tr id="nc" class="headbg" style="background-color: #1F6D9E;height: 50px;">
		  	 	   <td colspan="4" style="cl;font-size: 18px">
		  	 	   		<font color="#FFFFFF">当前条件统计</font>
		 	  	   </td>
			   </tr>
		  		<tr id="nc" class="headbg" style="background-color: #1F6D9E;height: 50px;">
		  	 	   <td colspan="2" style="cl;font-size: 18px">
		  	 	   		<font color="#FFFFFF">交易金额：${totalTradeMoney }</font>
		 	  	   </td>
			   </tr>
			</table>
		</div>
		<form action="${ctx}/subsystem/tradeInfoAction!retrieveTradeLogByPage.action" method="get">
		<table class="listTable2">
			<tr>
				<td>
						会员名称：<input type="text" placeholder="请输入会员名称" name="name" value="${name }"/> &nbsp;&nbsp;&nbsp;
						店铺名称：<input type="text" placeholder="请输入店铺名称" name="storeName" value="${storeName }"/> &nbsp;&nbsp;&nbsp;
					        开始时间： <input type="text" placeholder="请选择时间" name="addTime" value="${addTime }" onclick="WdatePicker()" style="width: 75px;"/>&nbsp;&nbsp;&nbsp;
					    -&nbsp;&nbsp;&nbsp;
					        结束时间： <input type="text" placeholder="请选择时间" name="endTime" value="${endTime }" onclick="WdatePicker()" style="width: 75px;"/><br/>
					        支付单号：<input type="text" placeholder="请输入支付单号" name="tradeNo" value="${tradeNo }"/>&nbsp;&nbsp;&nbsp;
					        第三方支付单号：<input type="text" placeholder="请输入第三方支付单号" name="payTradeNo" value="${payTradeNo }"/>&nbsp;&nbsp;&nbsp;
					        支付类型：<select name="tradeType">
					        <option value="" <c:if test="${empty tradeType}">selected="selected"</c:if>>全部</option>
					        <option value="0" <c:if test="${tradeType eq '0'}">selected="selected"</c:if>>订单余额支付</option>
					        <option value="1" <c:if test="${tradeType eq '1'}">selected="selected"</c:if>>订单待领金额支付</option>
					        <option value="2" <c:if test="${tradeType eq '2'}">selected="selected"</c:if>>责任消费金额支付</option>
					        <option value="3" <c:if test="${tradeType eq '3'}">selected="selected"</c:if>>微信支付</option>
					        <option value="4" <c:if test="${tradeType eq '4'}">selected="selected"</c:if>>支付宝支付</option>
					        <option value="5" <c:if test="${tradeType eq '5'}">selected="selected"</c:if>>银联支付</option>
					        <option value="6" <c:if test="${tradeType eq '6'}">selected="selected"</c:if>>授信额度支付</option>
					        <option value="7" <c:if test="${tradeType eq '7'}">selected="selected"</c:if>>后台授信充值消费</option>
					     </select>
					     &nbsp;&nbsp;&nbsp;
					     交易状态：<select name="tradeStatus">
					        <option value="" <c:if test="${empty tradeStatus}">selected="selected"</c:if>>全部</option>
					        <option value="0" <c:if test="${tradeStatus eq '0'}">selected="selected"</c:if>>提交中</option>
					        <option value="1" <c:if test="${tradeStatus eq '1'}">selected="selected"</c:if>>交易成功</option>
					        <option value="2" <c:if test="${tradeStatus eq '2'}">selected="selected"</c:if>>交易失败</option>
					     </select>&nbsp;&nbsp;&nbsp;
					      &nbsp;&nbsp;&nbsp;
					     <input type="submit" value="查询"/>
				</td>
			</tr>
		</table>
	</form>
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>支付单号</td>
				<td>第三方支付单号</td>
				<td>交易金额</td>
				<!-- <td>申请金额</td> -->
				<td>添加时间</td>
				<td>支付会员</td>
				<td>交易店铺</td>
				<td>支付类型</td>
				<td>交易状态</td>
			</tr>

			<c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.tradeNo }</td>
					<td>${bean.payTradeNo}</td>
					<td>${bean.tradeMoney }</td>
					<%-- <td>${bean.applyMoney }</td> --%>
					<td><fmt:formatDate value="${bean.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${bean.name}</td>
					<td>${bean.storeName}</td>
					<td>
						<c:if test="${bean.tradeType eq 0}">订单余额支付</c:if>
						<c:if test="${bean.tradeType eq 1}">订单待领金额支付</c:if>
						<c:if test="${bean.tradeType eq 2}">责任消费金额支付</c:if>
						<c:if test="${bean.tradeType eq 3}">微信支付</c:if>
						<c:if test="${bean.tradeType eq 4}">支付宝支付</c:if>
						<c:if test="${bean.tradeType eq 5}">银联支付</c:if>	
						<c:if test="${bean.tradeType eq 6}">授信额度支付</c:if>
						<c:if test="${bean.tradeType eq 7}">后台授信充值消费</c:if>
					</td>
					<td>
						<c:if test="${bean.tradeStatus eq 0}">提交中</c:if>
						<c:if test="${bean.tradeStatus eq 1}">交易成功</c:if>
						<c:if test="${bean.tradeStatus eq 2}">交易失败</c:if>
					</td>
				</tr>
			</c:forEach>

		</table>

		<c:if test="${not empty pager.resultList}">
			<s:form action="tradeInfoAction!retrieveTradeLogByPage.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
					<input type="hidden"  name="name" value="${name }"/>
					<input type="hidden"  name="storeName" value="${storeName }"/>
					<input type="hidden"  name="addTime" value="${addTime }"/>
					<input type="hidden" name="endTime" value="${endTime }"/>
					<input type="hidden" name="tradeNo" value="${tradeNo }"/>
					<input type="hidden"  name="payTradeNo" value="${payTradeNo }"/>
					<select name="tradeType" style="display: none;">
					        <option value="" <c:if test="${empty tradeType}">selected="selected"</c:if>>全部</option>
					        <option value="0" <c:if test="${tradeType eq '0'}">selected="selected"</c:if>>订单余额支付</option>
					        <option value="1" <c:if test="${tradeType eq '1'}">selected="selected"</c:if>>订单待领金额支付</option>
					        <option value="2" <c:if test="${tradeType eq '2'}">selected="selected"</c:if>>责任消费金额支付</option>
					        <option value="3" <c:if test="${tradeType eq '3'}">selected="selected"</c:if>>微信支付</option>
					        <option value="4" <c:if test="${tradeType eq '4'}">selected="selected"</c:if>>支付宝支付</option>
					        <option value="5" <c:if test="${tradeType eq '5'}">selected="selected"</c:if>>银联支付</option>
					        <option value="6" <c:if test="${tradeType eq '6'}">selected="selected"</c:if>>授信额度支付</option>
					        <option value="7" <c:if test="${tradeType eq '7'}">selected="selected"</c:if>>后台授信充值消费</option>
					     </select>
					   <select name="tradeStatus" style="display: none;">
					        <option value="" <c:if test="${empty tradeStatus}">selected="selected"</c:if>>全部</option>
					        <option value="0" <c:if test="${tradeStatus eq '0'}">selected="selected"</c:if>>提交中</option>
					        <option value="1" <c:if test="${tradeStatus eq '1'}">selected="selected"</c:if>>交易成功</option>
					        <option value="2" <c:if test="${tradeStatus eq '2'}">selected="selected"</c:if>>交易失败</option>
					     </select>
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>