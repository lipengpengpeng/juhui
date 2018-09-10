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
 	String fileName="联盟商家升级记录.xls";
	response.setContentType("application/vnd.ms-excel;charset=UTF-8");
   	response.setHeader("Content-disposition","attachment; filename= "+ new String( fileName.getBytes("gb2312"), "ISO8859-1")+"");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>联盟商家升级记录</title>
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
			  <tr id="nc" class="headbg">
				<td>序号</td>
				<td>用户名称</td>
				<td>用户号码</td>			
				<td>交易金额</td>				
				<td>交易类型</td>
				<td>支付类型</td>
				<td>赠送类型</td>
				<td>推荐人电话</td>
				<td>推荐人姓名</td>
				<td>推荐人奖励金额</td>
				<td>交易状态</td>			
				<td>添加时间</td>	
		
			  </tr>
			</thead>
			      <c:forEach var="bean" items="${pager.resultList}" varStatus="status">
						<tr>
					<td>${status.count}</td>
					<td>${bean.memberName}</td>
					<td>${bean.memberMobile}</td>
					<td>${bean.tradeMoney}</td>	
					<td>
					   <c:if test="${bean.tradeType==0}">普通会员</c:if>
					  <c:if test="${bean.tradeType==1}">商家版</c:if>
					  <c:if test="${bean.tradeType==2}">企业版</c:if>
					  <c:if test="${bean.tradeType==3}">商家代理版</c:if>
					  <c:if test="${bean.tradeType==4}">企业代理版</c:if>
					  <c:if test="${bean.tradeType==5}">地级市代理</c:if>
					  <c:if test="${bean.tradeType==6}">市级代理</c:if>
					  <c:if test="${bean.tradeType==7}">直辖市代理</c:if>
					</td>			
					<td>
					   <c:if test="${bean.payType==0}">微信</c:if>
					  <c:if test="${bean.payType==1}">支付宝</c:if>
					  <c:if test="${bean.payType==2}">后台充值</c:if>
					  <c:if test="${bean.payType==3}">凤凰宝</c:if>					 
					</td>
					<td>									   
					   <c:if test="${bean.giveType==0}">充值</c:if>                      
					   <c:if test="${bean.giveType==1}">赠送</c:if>		
					</td>	
					<td>${bean.directMemberName}</td>	
					<td>${bean.directMemberMobile}</td>	
					<td>${bean.directMemberMoney}</td>
					<td>
					  <c:if test="${bean.tradeStatus==0}">待支付</c:if>
					  <c:if test="${bean.tradeStatus==1}">支付成</c:if>
					  <c:if test="${bean.tradeStatus==2}">支付失败</c:if>
								 
					</td>					
					<td style="vnd.ms-excel.numberformat:yyyy-mm-dd HH:mm:ss"><fmt:formatDate value="${bean.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>									
					
				</tr>
			</c:forEach>
		</table>
	</body>
</html>