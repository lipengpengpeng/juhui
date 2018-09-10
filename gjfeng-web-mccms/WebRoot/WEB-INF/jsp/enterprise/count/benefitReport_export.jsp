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
 	 	String fileName="让利日报表.xls";
	response.setContentType("application/vnd.ms-excel;charset=UTF-8");
   	response.setHeader("Content-disposition","attachment; filename= "+ new String( fileName.getBytes("gb2312"), "ISO8859-1")+"");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>让利日报表</title>
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
									<td>交易额</td>
									<td>让利额</td>
									<td>余额</td>
									<td>微信支付</td>
									<td>H5支付</td>
									<td>线下支付</td>
									<td>授信支付</td>
									<td>日期</td>
									<td>类型</td>
								</tr>
			</thead>
			      <c:forEach var="bean" items="${pager.resultList}" varStatus="status">
						<tr>
									<td>${bean.trddeMoney }</td>
									<td>${bean.benefitMoney }</td>
									<td>${bean.balance }</td>
									<td>${bean.weChatPayment }</td>
									<td>${bean.h5Payment }</td>
									<td>${bean.offLinePayment }</td>
									<td>${bean.shouXinPayment }</td> 
									<td style="vnd.ms-excel.numberformat:yyyy-mm-dd HH:mm:ss"><fmt:formatDate value="${bean.addTime }" pattern="yyyy-MM-dd"/></td>
									<td>
										<c:choose>
											<c:when test="${bean.type eq '0'}">O2O</c:when>
											<c:when test="${bean.type eq '1'}">网上商城</c:when>
										</c:choose>
									</td>
						</tr>
				</c:forEach>
		</table>
	</body>
</html>