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
									<td>到账用户名称</td>
									<td>到账用户电话</td>
									<td>易金额  </td>
									<td>交易类型</td>
									<td>交易状态</td>
									
								</tr>
			</thead>
			      <c:forEach var="bean" items="${pager.resultList}" varStatus="status">
						<tr>
									<td>'${bean.tradeNo}</td>
									<td style="vnd.ms-excel.numberformat:yyyy-mm-dd HH:mm:ss"><fmt:formatDate value="${bean.addTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td>${bean.memberName}</td>
									<td>${bean.memebrMobile}</td>
									<td>${bean.transferMemberName}</td>
									<td>${bean.transferMemberMobile}</td>
									<td>${bean.memberTreasureTradeMoney}</td>
									<td>
					                   <c:if test="${bean.tradeType==0}">储值</c:if>
					                   <c:if test="${bean.tradeType==1}">用户转移</c:if>
					                   <c:if test="${bean.tradeType==2}">让利支付</c:if>
					                   <c:if test="${bean.tradeType==3}">余额转入</c:if>
					                   <c:if test="${bean.tradeType==4}">用户提现</c:if>
					                   <c:if test="${bean.tradeType==5}">商城支付</c:if>
					                   <c:if test="${bean.tradeType==6}">提现退回</c:if>
					                   <c:if test="${bean.tradeType==7}">赠送代金券</c:if>
					                   <c:if test="${bean.tradeType==8}">充值商家联盟</c:if>
					                   <c:if test="${bean.tradeType==9}">赠送商家联盟</c:if>
					                   <c:if test="${bean.tradeType==10}">奖励转入</c:if>
					                   <c:if test="${bean.tradeType==11}">商城退款</c:if>
					                </td>
					                <td>					
					                  <c:if test="${bean.tradeType==4}">
					                    <c:if test="${bean.tradeStatus==0}">待审核</c:if>
                                     </c:if>
					                   <c:if test="${bean.tradeStatus==1}">交易成功</c:if>
					                   <c:if test="${bean.tradeStatus==2}">交易失败</c:if>
					                </td>	
									
						</tr>
					</c:forEach>
		</table>
	</body>
</html>