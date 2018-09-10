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
 	 	String fileName="福利产出报表.xls";
	response.setContentType("application/vnd.ms-excel;charset=UTF-8");
   	response.setHeader("Content-disposition","attachment; filename= "+ new String( fileName.getBytes("gb2312"), "ISO8859-1")+"");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>福利产出报表</title>
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
									<td>历史交易总额</td>
									<td>商户分红权总额</td>
									<td>会员分红权总额</td>
									<td>一档会员分红权总数</td>
									<td>一档会员分红权人数</td>
									<td>二档会员分红权总数</td>
									<td>二档会员分红权人数</td>
									<td>三档会员分红权总数</td>
									<td>三档会员分红权人数</td>
									<td>一档商户分红权总数</td>
									<td>一档商户分红权人数</td>
									<td>二档商户分红权总数</td>
									<td>二档商户分红权人数</td>
									<td>三档商户分红权总数</td>
									<td>三档商户分红权人数</td>
									<td>日期</td>
								</tr>
			</thead>
			      <c:forEach var="bean" items="${pager.resultList}" varStatus="status">
						<tr>
							<td>${bean.turnOver }</td>
							<td>${bean.storeDividendsTotal }</td>
							<td>${bean.memberDividendsTotal }</td>
							<td>${bean.gradeOneMemberDividendsTotal }</td>
							<td>${bean.gradeOneMemberDividendsAmount }</td>
							<td>${bean.gradeTwoMemberDividendsTotal }</td>
							<td>${bean.gradeTwoMemberDividendsAmount }</td>
							<td>${bean.gradeThreeMemberDividendsTotal }</td>
							<td>${bean.gradeThreeMemberDividendsAmount }</td>
							<td>${bean.gradeOneStoreDividendsTotal }</td>
							<td>${bean.gradeOneStoreDividendsAmount }</td>
							<td>${bean.gradeTwoStoreDividendsTotal }</td>
							<td>${bean.gradeTwoStoreDividendsAmount }</td>
							<td>${bean.gradeThreeStoreDividendsTotal }</td>
							<td>${bean.gradeThreeStoreDividendsAmount }</td>
							<td style="vnd.ms-excel.numberformat:yyyy-mm-dd"><fmt:formatDate value="${bean.addTime}" pattern="yyyy-MM-dd"/></td>
						</tr>
				</c:forEach>
		</table>
	</body>
</html>