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
 	String fileName="凤凰宝交易记录.xls";
	response.setContentType("application/vnd.ms-excel;charset=UTF-8");
   	response.setHeader("Content-disposition","attachment; filename= "+ new String( fileName.getBytes("gb2312"), "ISO8859-1")+"");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>订单列表信息</title>
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
									<td>订单号</td>
									<td>时间</td>
									<td>用户名</td>									
									<td>电话号码</td>																	
									<td>交易金额  </td>
									<td>到账代金券  </td>
									<td>交易类型</td>
									<td>交易状态</td>
									
								</tr>
			</thead>
			      <c:forEach var="bean" items="${pager.resultList}" varStatus="status">
						<tr>
									<td>'${bean.tradeNo}</td>
									<td style="vnd.ms-excel.numberformat:yyyy-mm-dd HH:mm:ss"><fmt:formatDate value="${bean.addTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td>${bean.memberName}</td>
									<td>${bean.memberMobile}</td>
									<td>${bean.realTreadeMoney}</td>
									<td>${bean.tradeMoney}</td>
									<td>
					  <c:if test="${bean.payType==0}">微信</c:if>
					  <c:if test="${bean.payType==1}">支付宝</c:if>
					  <c:if test="${bean.payType==2}">网银</c:if>
					  <c:if test="${bean.payType==4}">授信额</c:if>
					  <c:if test="${bean.payType==6}">天天宝</c:if>
					</td>
					<td>					
					   
					   <c:if test="${bean.tradeStatus==0}">待支付</c:if>                      
					   <c:if test="${bean.tradeStatus==1}">交易成功</c:if>
					   <c:if test="${bean.tradeStatus==2}">交易失败</c:if>
					</td>	
									
						</tr>
					</c:forEach>
		</table>
	</body>
</html>