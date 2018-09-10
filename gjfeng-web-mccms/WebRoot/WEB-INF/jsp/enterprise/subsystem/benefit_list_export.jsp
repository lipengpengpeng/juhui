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
 	String fileName="销售录入.xls";
	response.setContentType("application/vnd.ms-excel;charset=UTF-8");
   	response.setHeader("Content-disposition","attachment; filename= "+ new String( fileName.getBytes("gb2312"), "ISO8859-1")+"");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>销售录入</title>
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
									<td>用户</td>
									<td>商家</td>
									<td>消费金额</td>
									<td>让利金额</td>
									<td>产生会员分红权</td>
									<td>产生商家分红权</td>
									<td>个人推荐奖励</td>
									<td>商家推荐奖励</td>
									<td>奖励会员</td>
									<td>奖励商家</td>
									<td>支付类型</td>
									<td>交易状态</td>
									<td>时间</td>
								</tr>
			</thead>
			      <c:forEach var="bean" items="${gjfMemberTradeBenefits}">
						<tr>
									<td>${bean.name}</td>
									<td>
										${bean.storeName}
									</td>
									<td>${bean.tradeMoney }</td>
									<td>${bean.benefitMoney}</td>
									<td>${bean.memberDividendsNum }</td>
									<td>${bean.merchantsDividendsNum }</td>
									<td>${bean.directMemberMoney }</td>
									<td>${bean.directMerchantsMoney }</td>
									<td>${bean.directMemberName }</td>
									<td>${bean.directSellerName}</td>
									<td>
											<c:choose>
												<c:when test="${bean.payType eq '0'}">微信支付</c:when>
												<c:when test="${bean.payType eq '1'}">支付宝支付</c:when>
												<c:when test="${bean.payType eq '2'}">银联支付</c:when>
												<c:when test="${bean.payType eq '4'}">授信金额支付</c:when>
												<c:when test="${bean.payType eq '5'}">余额支付</c:when>
											</c:choose>
									</td>
									<td>
											<c:choose>
												<c:when test="${bean.tradeStatus eq '0'}">待支付</c:when>
												<c:when test="${bean.tradeStatus eq '1'}">已支付</c:when>
												<c:when test="${bean.tradeStatus eq '2'}">支付失败</c:when>
												<c:when test="${bean.tradeStatus eq '3'}">取消</c:when>
											</c:choose>
									</td>
									<td style="vnd.ms-excel.numberformat:yyyy-mm-dd HH:mm:ss"><fmt:formatDate value="${bean.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						</tr>
					</c:forEach>
		</table>
	</body>
</html>