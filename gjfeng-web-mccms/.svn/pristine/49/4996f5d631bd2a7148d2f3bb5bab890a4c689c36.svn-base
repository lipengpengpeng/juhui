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
 	 	String fileName="提现明细.xls";
	response.setContentType("application/vnd.ms-excel;charset=UTF-8");
   	response.setHeader("Content-disposition","attachment; filename= "+ new String( fileName.getBytes("gb2312"), "ISO8859-1")+"");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>提现明细</title>
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
									<td>序号</td>
									<td>收款方户名</td>
									<td>金额（元）</td>
									<td>收款方银行账号</td>	
									<td>收款方银行编码</td>
									<td>开户行所在省</td>
									<td>开户行所在市</td>			
									<td>开户行支行名称</td>
									<td>开户行支行编码</td>
									<td>对公（2）、对私（1）</td>
									<td>摘要</td>
									<td>最近交易时间</td>		
								</tr>
			</thead>
			      <c:forEach var="bean" items="${pager.resultList}" varStatus="status">
						<tr>
									<td>${status.count}</td>
									<td>${bean[0].bankId.holder}</td>
									<td>${bean[0].tradeMoney}</td>
									<td>'${bean[0].bankId.bankCard}</td>
									<td>${bean[0].bankId.bankId.bankCode}</td>
									<td>${bean[0].bankId.bankProvinceId.province}</td>
									<td>${bean[0].bankId.bankCityId.city}</td>	
									<td>${bean[0].bankId.bankSub}</td>
									<td>${bean[0].bankId.bankId.bankCode}</td>
									<td>1</td>
									<td>'${bean[0].id}</td>
									<td style="vnd.ms-excel.numberformat:yyyy-mm-dd HH:mm:ss"><fmt:formatDate value="${bean[1]}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					</tr>
			</c:forEach>
		</table>
	</body>
</html>