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

		<script type="text/javascript" src="/gjfeng-web-mccms/js/My97DatePicker/WdatePicker.js"></script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 交易管理 - 分红权记录
			</div>
			<div class="clear"></div>
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
		  	 	   		<font color="#FFFFFF">分红权数量：${object.totalDiviNum }</font>
		 	  	   </td>
		 	  	     <td colspan="2" style="cl;font-size: 18px">
		  	 	   		<font color="#FFFFFF">分红权金额：${object.totalDiviMoney }</font>
		 	  	   </td>
			   </tr>
			   <tr id="nc" class="headbg" style="background-color: #1F6D9E;height: 50px;">
		  	 	   <td colspan="2" style="cl;font-size: 18px">
		  	 	   		<font color="#FFFFFF">计算分红权后分红权额度：${object.totalDiviMoneyBla }</font>
		 	  	   </td>
		 	  	     <td colspan="2" style="cl;font-size: 18px">
		  	 	   		<font color="#FFFFFF">单笔消费金额：${object.totalConsumption }</font>
		 	  	   </td>
			   </tr>
			</table>
		</div>
		<form action="tradeInfoAction!retrieveDiviByPager.action" method="post">
			<table class="listTable2">
				<tr>
					<td>
						用户名称：<input type="text" placeholder="请输入用户名称" name="name" value="${name }"/>&nbsp;&nbsp;
						<%-- 支付类型：
							 <select name="payType" id="payType">
							 	 <option value="" <c:if test="${empty payType}">selected="selected"</c:if>>全部</option>
							     <option value="0" <c:if test="${payType eq '0'}">selected="selected"</c:if>>微信支付 </option>
							     <option value="1" <c:if test="${payType eq '1'}">selected="selected"</c:if>>支付宝支付</option>
							     <option value="2" <c:if test="${payType eq '2'}">selected="selected"</c:if>>银联支付</option>
				              </select>&nbsp;&nbsp; --%>
						交易状态：
						 <select name="payStatus" id="payStatus">
							 	<option value="" <c:if test="${empty payStatus}">selected="selected"</c:if>>全部</option>
					   			<option value="0" <c:if test="${payStatus eq '0'}">selected="selected"</c:if>>待支付</option>
				  				<option value="1" <c:if test="${payStatus eq '1'}">selected="selected"</c:if>>支付成功</option>
				 				<option value="2" <c:if test="${payStatus eq '2'}">selected="selected"</c:if>>支付失败</option>
		                 </select>&nbsp;&nbsp;
		           		开始时间：<input type="text" placeholder="请选择日期" name="startDate" value="${startDate }" onclick="WdatePicker()" style="width: 75px;"/>&nbsp;&nbsp;
						-&nbsp;&nbsp;
						结束时间：<input type="text" placeholder="请选择日期" name="endDate" value="${endDate }"  onclick="WdatePicker()" style="width: 75px;"/>&nbsp;&nbsp;
		                                分红权类型： 
                          <select name="diviStatus" id="diviStatus">
							 	<option value=""  <c:if test="${empty diviStatus}">selected="selected"</c:if>>全部</option>
					   			<option value="0" <c:if test="${diviStatus eq '0'}">selected="selected"</c:if>>消费</option>
					  			<option value="1" <c:if test="${diviStatus eq '1'}">selected="selected"</c:if>>充值</option>
                 		  </select>&nbsp;&nbsp;
		                <input type="submit" style="margin-left:30px;" class="default_button" value='<s:text name="common.word.search" />' />
					</td>
				</tr>
			</table>
		</form>
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>用户ID</td>
				<td>用户</td>
				<td>店铺</td>
				<td>分红权交易号</td>
				<td>分红权数量</td>
				<td>分红权金额</td>
				<td>计算分红权后分红权额度</td>
				<td>单笔消费金额</td>
				<td>添加时间</td>
				<!-- <td>支付类型</td>
				<td>支付状态</td> -->
				<td>分红权类型</td>
			</tr>

			<c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.memberId}</td>
					<td>${bean.name}</td>
					<td>${bean.storeName}</td>
					<td>${bean.diviNo}</td>
					<td>${bean.diviNum}</td>
					<td>${bean.diviMoney}</td>
					<td>${bean.diviMoneyBla}</td>
					<td>${bean.consumptionMoney}</td>
					<td><fmt:formatDate value="${bean.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<%-- <td>
						<c:choose>
							<c:when test="${bean.payType eq 0}">微信支付</c:when>
							<c:when test="${bean.payType eq 1}">支付宝支付</c:when>
							<c:when test="${bean.payType eq 2}">银联支付</c:when>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${bean.payStatus eq 0}">待支付 </c:when>
							<c:when test="${bean.payStatus eq 1}">支付成功</c:when>
							<c:when test="${bean.payStatus eq 2}">支付失败</c:when>
						</c:choose>
					</td>--%>
					<td>
						<c:choose>
							<c:when test="${bean.diviStatus eq 0}">消费 </c:when>
							<c:when test="${bean.diviStatus eq 1}">充值</c:when>
						</c:choose>
					</td> 

					<%-- <td>
						<a href="gjfMemberTradeDiviAction!viewPage.action?id=${bean.id}">查看</a>
						&nbsp; &nbsp;
						<a href="gjfMemberTradeDiviAction!retrieveGjfMemberTradeDiviById.action?id=${bean.id}">编辑</a>
						&nbsp; &nbsp;
						<a href="gjfMemberTradeDiviAction!delGjfMemberTradeDivi.action?id=${bean.id}"
								onclick="{if(confirm('你真的要删除吗?')){return true;}return false;}">删除</a>
					</td> --%>
				</tr>
			</c:forEach>

		</table>

		<c:if test="${not empty pager.resultList}">
			<s:form action="tradeInfoAction!retrieveDiviByPager.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
					<input type="hidden"  name="name" value="${name }"/>
					<select name="payStatus" style="display: none;">
							 	<option value="" <c:if test="${empty payStatus}">selected="selected"</c:if>>全部</option>
					   			<option value="0" <c:if test="${payStatus eq '0'}">selected="selected"</c:if>>待支付</option>
				  				<option value="1" <c:if test="${payStatus eq '1'}">selected="selected"</c:if>>支付成功</option>
				 				<option value="2" <c:if test="${payStatus eq '2'}">selected="selected"</c:if>>支付失败</option>
		           </select>
                    <select name="diviStatus" style="display: none;">
							 	<option value=""  <c:if test="${empty diviStatus}">selected="selected"</c:if>>全部</option>
					   			<option value="0" <c:if test="${diviStatus eq '0'}">selected="selected"</c:if>>消费</option>
					  			<option value="1" <c:if test="${diviStatus eq '1'}">selected="selected"</c:if>>充值</option>
                 </select>
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>