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
 	 	String fileName="福利派发报表.xls";
	response.setContentType("application/vnd.ms-excel;charset=UTF-8");
   	response.setHeader("Content-disposition","attachment; filename= "+ new String( fileName.getBytes("gb2312"), "ISO8859-1")+"");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>福利派发报表</title>
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
									<td>日期</td>
									<td>池的金额</td>
									<td>派发资金</td>
									<td>分红权单价</td>
									<td>沉淀金额</td>
									<td>类型</td>
								</tr>
			</thead>
			      <c:forEach var="bean" items="${pager.resultList}" varStatus="status">
						<tr>
							<td><fmt:formatDate value="${bean.addTime }" pattern="yyyy-MM-dd"/></td>
							<td>${bean.poolMoney }</td>
							<td>${bean.payoutMoney }</td>
							<td>${bean.dividendsPrice }</td>
							<td>${bean.precipitationAmount }</td>
							<td>
								<c:if test="${bean.type eq '0'}">会员发放商户发放</c:if>
								<c:if test="${bean.type eq '1'}">商户发放</c:if>
							</td>
						</tr>
				</c:forEach>
		</table>
	</body>
</html>