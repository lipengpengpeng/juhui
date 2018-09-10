<%--

	gjfMemberDiviHistory.jsp

	Create by MCGT

	Create time 2017-01-20

	Version 1.0

	Copyright 2013 Messcat, Inc. All rights reserved.

 --%>
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<style>
			.headbg1 {
				background: #c9e6f5;
				font-weight: bold;
				padding-left: 15px;
			}
			
			.headbg1 td {
				font-weight: bold;
				text-align: center;
			}
		</style>

		<script type="text/javascript">
				function doYouWantToExport() {
					if(confirm('确定要导出所有记录吗?')) {
						return true;
					}
					return false;
				}
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 模块 - 子模块
			</div>
			<!-- <div class="ropt">
				<a href="gjfMemberDiviHistoryAction!newPage.action">新增</a>
			</div> -->
			 <div class="ropt"><a href="${ctx}/subsystem/tradeInfoAction!findMemberVoucherc.action?memberName=${memberName }&mobile=${mobile}&ste=1" onclick="return doYouWantToExport();">导出</a></div> 
			<div class="clear"></div>
		</div>
		
		<form action="tradeInfoAction!findMemberVoucherc.action" method="post">
			<table class="listTable2">
				<tr>
					<td>
						&nbsp;&nbsp;用户名称：<input type="text" placeholder="请输入用户名称" name="memberName" value="${memberName }"/>
						&nbsp;&nbsp;用户电话：<input type="text" placeholder="请输入用户电话" name="mobile" value="${mobile }"/>&nbsp;&nbsp;												                         
		                <input type="submit" style="margin-left:30px;" class="default_button" value='<s:text name="common.word.search" />' />
					</td>
				</tr>
			</table>
		</form>

		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>用户名称</td>
				<td>用户号码</td>			
				<td>交易金额</td>
				<td>到账金额</td>
				<td>交易类型</td>
				<td>交易状态</td>
			
				<td>添加时间</td>	
				<!-- <td>操作</td>		 -->		
			</tr>

			<c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
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
					<td><fmt:formatDate value="${bean.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>									
					
				</tr>
			</c:forEach>

		</table>

		<c:if test="${not empty pager.resultList}">
			<s:form action="tradeInfoAction!findMemberVoucherc.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<input type="hidden" name="memberName" value="${memberName }"/>
				<input type="hidden" name="mobile" value="${mobile }"/>
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>