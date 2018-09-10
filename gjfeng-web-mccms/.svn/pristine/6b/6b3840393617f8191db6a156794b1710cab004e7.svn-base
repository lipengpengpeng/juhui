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
				当前位置: 店铺管理--授信充值记录
			</div>
			<div class="ropt"><a href="storeInfoAction!findLineCreditRechargeByPage.action?tradeNo=${tradeNo }&name=${name }&addTime=${addTime}&endTime=${endTime}&tradeStatus=${tradeStatus }&payType=${payType }&ste=1" onclick="return doYouWantToExport();">导出</a></div> 
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
		  	 	   		<font color="#FFFFFF">申请金额：${object.totalApplyMoney }</font>
		 	  	   </td>
		  	 	   <td colspan="2" style="cl;font-size: 18px">
		  	 	   		<font color="#FFFFFF">交易金额：${object.totalTradeMoney }</font>
		 	  	   </td>
			   </tr>
			</table>
		</div>
	<form action="${ctx}/subsystem/storeInfoAction!findLineCreditRechargeByPage.action" method="get">
		<table class="listTable2">
			<tr>
				<td>
						&nbsp;&nbsp;&nbsp;交易流水号：<input type="text" placeholder="请输入交易流水号" name="tradeNo" value="${tradeNo }" style="width: 120px;"/> &nbsp;&nbsp;&nbsp;
						用户：<input type="text" placeholder="请输入用户名称" name="name" value="${name }" style="width: 100px;"/> &nbsp;&nbsp;&nbsp;
					       开始时间：<input type="text" placeholder="选择添加时间" name="addTime" value="${addTime}"  onclick="WdatePicker()" style="width: 85px;"/>&nbsp;&nbsp;&nbsp;
					    -&nbsp;&nbsp;
					       结束时间：<input type="text" placeholder="选择添加时间" name="endTime" value="${endTime}"  onclick="WdatePicker()" style="width: 85px;"/>&nbsp;&nbsp;&nbsp;
					       交易状态：<select name="tradeStatus">
					        <option value="" <c:if test="${empty tradeStatus}">selected="selected"</c:if>>全部</option>
					        <option value="0" <c:if test="${tradeStatus eq '0'}">selected="selected"</c:if>>未支付</option>
					        <option value="1" <c:if test="${tradeStatus eq '1'}">selected="selected"</c:if>>交易成功</option>
					        <option value="2" <c:if test="${tradeStatus eq '2'}">selected="selected"</c:if>>交易失败</option>
					     </select>&nbsp;&nbsp;&nbsp;
					     支付类型：<select name="payType">
					        <option value="" <c:if test="${empty payType}">selected="selected"</c:if>>全部</option>
					        <option value="0" <c:if test="${payType eq '0'}">selected="selected"</c:if>>余额支付</option>
					        <option value="1" <c:if test="${payType eq '1'}">selected="selected"</c:if>>微信支付</option>
					        <option value="2" <c:if test="${payType eq '2'}">selected="selected"</c:if>>支付宝支付</option>
					        <option value="3" <c:if test="${payType eq '3'}">selected="selected"</c:if>>银联支付</option>
					        <option value="4" <c:if test="${payType eq '4'}">selected="selected"</c:if>>H5支付</option>
					        <option value="5" <c:if test="${payType eq '5'}">selected="selected"</c:if>>后台充值消费</option>
					     </select>&nbsp;&nbsp;&nbsp;
					     <input type="submit" value="查询"/>
				</td>
			</tr>
		</table>
	</form>
	
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>交易流水号</td>
				<td>用户</td>
				<td>申请额度</td>
				<td>交易金额</td>
				<td>添加时间</td>
				<!-- <td>审核时间</td>	
				<td>付款时间</td> -->
				<td>交易状态</td>			
				<td>支付类型</td>
			</tr>
                <c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.tradeNo }</td>
					<td>${bean.name }</td>
					<td>${bean.applyMoney }</td>
					<td>${bean.tradeMoney }</td>
					<td>
						<fmt:formatDate value="${bean.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<%-- <td>
						<fmt:formatDate value="${bean.dealTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						<fmt:formatDate value="${bean.tradeTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td> --%>
					<td>
						<c:if test="${bean.tradeStatus eq '0'}">未支付</c:if>
						<c:if test="${bean.tradeStatus eq '1'}">交易成功</c:if>
						<c:if test="${bean.tradeStatus eq '2'}">交易失败</c:if>
					</td>
					<td>
						<c:if test="${bean.payType eq '0'}">余额支付</c:if>
						<c:if test="${bean.payType eq '1'}">微信支付</c:if>
						<c:if test="${bean.payType eq '2'}">支付宝支付</c:if>
						<c:if test="${bean.payType eq '3'}">银联支付</c:if>
						<c:if test="${bean.payType eq '4'}">H5支付</c:if>
						<c:if test="${bean.payType eq '5'}">后台充值消费</c:if>
					</td>
				</tr>
			  </c:forEach>
			

		</table>

		<c:if test="${not empty pager.resultList}">
			<s:form action="storeInfoAction!findLineCreditRechargeByPage.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
					<input type="hidden" name="tradeNo" value="${tradeNo }"/> 
					<input type="hidden" name="name" value="${name }"/> 
					<input type="hidden" name="addTime" value="${addTime}"/>
					<input type="hidden" name="endTime" value="${endTime}"/>
					<select name="tradeStatus" style="display: none;">
					        <option value="" <c:if test="${empty tradeStatus}">selected="selected"</c:if>>全部</option>
					        <option value="0" <c:if test="${tradeStatus eq '0'}">selected="selected"</c:if>>待审核</option>
					        <option value="1" <c:if test="${tradeStatus eq '1'}">selected="selected"</c:if>>交易成功</option>
					        <option value="2" <c:if test="${tradeStatus eq '2'}">selected="selected"</c:if>>交易失败</option>
					     </select>
					<select name="payType" style="display: none;">
					        <option value="" <c:if test="${empty payType}">selected="selected"</c:if>>全部</option>
					        <option value="0" <c:if test="${payType eq '0'}">selected="selected"</c:if>>余额支付</option>
					        <option value="1" <c:if test="${payType eq '1'}">selected="selected"</c:if>>微信支付</option>
					        <option value="2" <c:if test="${payType eq '2'}">selected="selected"</c:if>>支付宝支付</option>
					        <option value="3" <c:if test="${payType eq '3'}">selected="selected"</c:if>>银联支付</option>
					        <option value="4" <c:if test="${payType eq '4'}">selected="selected"</c:if>>H5支付</option>
					        <option value="5" <c:if test="${payType eq '5'}">selected="selected"</c:if>>后台充值消费</option>
					</select>
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>