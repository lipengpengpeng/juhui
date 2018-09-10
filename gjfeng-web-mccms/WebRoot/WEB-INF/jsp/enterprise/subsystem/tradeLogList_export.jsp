<%@page import="cc.modules.util.DateHelper"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%-- <%@ page contentType="application/vnd.ms-excel;charset=gbk"%> --%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@ include file="/common/taglibs.jsp"%>
<%
	//HttpServletResponse response = ServletActionContext.getResponse();
	//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
 	 String fileName="支付明细.xls";
	response.setContentType("application/vnd.ms-excel;charset=UTF-8");
   	response.setHeader("Content-disposition","attachment; filename= "+ new String( fileName.getBytes("gb2312"), "ISO8859-1")+"");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>支付明细</title>
		<style>
			.headbg {/*background: #c9e6f5;*/font-weight: bold;padding-left: 15px;height: 30px;}
			.txt{mso-number-format:"\@";}
			.headbg td {font-weight: bold;/* text-align: left; */}
			.footbg td{font-weight: bold;text-align: left;}
			.footbg1 td{text-align: left;}
		</style>
	</head>
	<body>
	<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()" border="1">
			<thead>
								<tr>
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
			</thead>
			      <c:forEach var="bean" items="${pager.resultList}" varStatus="status">
						<tr>
									<td>'${bean.tradeNo }</td>
									<td>'${bean.payTradeNo}</td>
									<td>${bean.tradeMoney }</td>
									<%-- <td>${bean.applyMoney }</td> --%>
									<td style="vnd.ms-excel.numberformat:yyyy-mm-dd HH:mm:ss"><fmt:formatDate value="${bean.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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
	</body>
</html>