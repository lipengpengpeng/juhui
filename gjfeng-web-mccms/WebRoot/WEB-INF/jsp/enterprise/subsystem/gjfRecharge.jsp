<%--

	gjfAttrValue.jsp

	Create by MCGT

	Create time 2017-01-10

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

		<script>
			function updateStatus(id,status,obj){
				var r = confirm("确定该操作吗？");
				if(r == true){
					$.ajax({
	      				type: "post",
	      				url: 'attrValueAction!modifyAttrValueStatus.action',
	      				dataType: "text",
	      				data:{
	      					"id":id,
							"status":status,
							"token":$(obj).siblings(".security-token").val()
							},
	      				success:function(data){
	      					 location.reload();
	      				},
	      				error:function(){
	  
	      				}
					});
				}
				return false;
			}
		
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 充值管理 - 代付充值列表
			</div>
			<div class="ropt">
				<input type="button" value='<s:text name="common.word.new" />' onclick="location.href='${ctx}/subsystem/tradeInfoAction!goNewPaidRecodePage.action'"/>
			</div>
			<div class="clear"></div>
		</div>
		
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>订单号</td>
				<td>银行卡号</td>
				<td>持卡人姓名</td>
				<td>操作者姓名</td>
				<td>充值金额</td>
				<td>添加时间</td>
				
				<td>备注</td>
				<td>状态</td>
			</tr>
			<c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.orderSn}</td>
					<td>${bean.accNo}</td>
					<td>${bean.holerName }</td>
					<td>${bean.operationName}</td>
					<td>${bean.tanAmt}</td>
					<td><fmt:formatDate value="${bean.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${bean.rmark}</td>				
					<td>
						<c:if test="${bean.orderStatus eq 1}">
							<p>代付充值成功</p>
						</c:if>
						<c:if test="${bean.orderStatus eq 0}">
							<p>代付充值失败</p>
						</c:if>
					</td>				
				</tr>
			</c:forEach>

		</table>

		<c:if test="${not empty pager.resultList}">
			<s:form action="tradeInfoAction!goPaidPayPage.action"
				namespace="/subsystem"	 method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>