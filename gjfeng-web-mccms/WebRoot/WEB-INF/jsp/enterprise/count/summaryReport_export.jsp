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
 	 	String fileName="总报表.xls";
	response.setContentType("application/vnd.ms-excel;charset=UTF-8");
   	response.setHeader("Content-disposition","attachment; filename= "+ new String( fileName.getBytes("gb2312"), "ISO8859-1")+"");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>总报表</title>
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
									<td>交易额</td>
									<td>让利额</td>
									<td>会员池当天收入</td>
									<td>商家池当天收入</td>
									<td>会员福利派发额</td>
									<td>会员福利待派发额</td>
									<td>商户福利派发额</td>
									<td>商户福利待派发额</td>
									<td>直推会员奖励额</td>
									<td>直推商家奖励额</td>
									<td>市代理奖励额</td>
									<td>区代理奖励额</td>
									<td>个代理奖励额</td>
									<td>平台收益</td>
								</tr>
			</thead>
			      <c:forEach var="bean" items="${pager.resultList}" varStatus="status">
						<tr>
									<td style="vnd.ms-excel.numberformat:yyyy-mm-dd HH:mm:ss"><fmt:formatDate value="${bean.addTime }" pattern="yyyy-MM-dd"/></td>
									<td>${bean.turnOver }</td>
									<td>
											${bean.benefitMoney }
									</td>
									<td>
										<c:choose>
											<c:when test="${empty bean.memberIncomeMoney }">0.00</c:when>
											<c:otherwise>${bean.memberIncomeMoney }</c:otherwise>
										</c:choose>
									</td>
									<td>
										<c:choose>
											<c:when test="${empty bean.merchantsIncomeMoney }">0.00</c:when>
											<c:otherwise>${bean.merchantsIncomeMoney }</c:otherwise>
										</c:choose>
									</td>
									<td>${bean.memberWealPayout }</td>
									<td>${bean.memberWealWaitPayout }</td>
									<td>${bean.storeWealPayout }</td>
									<td>${bean.storeWealWaitPayout }</td>
									<td>${bean.directDriveMemberAward }</td>
									<td>${bean.directDriveStoreAward }</td>
									<td>${bean.agentSysCityAward }</td>
									<td>${bean.agentSysAreaAwara }</td>
									<td>${bean.agentSysIndiAwara }</td>
									<td>${bean.platFormEarnings }</td>
						</tr>
				</c:forEach>
		</table>
	</body>
</html>