<%@ page contentType="text/html;charset=UTF-8"%>
	

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<title>模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
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
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 模块 - 子模块
			</div>
			<!-- <div class="ropt">
				<a href="gjfOrderInfoAction!newPage.action">新增</a>
			</div> -->
			<div class="clear"></div>
		</div>
		
		
		<form action="orderInfoAction!checkAccount.action" method="post">
			<table class="listTable2">
				<tr>
					<td>
						开始时间：<input type="text" placeholder="请选择开始时间" name="startDate" value="${startDate }" onclick="WdatePicker({dateFmt:'yyyyMMdd HHmmss'})" style="width: 150px;"/>&nbsp;&nbsp;
						结束时间：<input type="text" placeholder="请选择结束时间" name="endDate" value="${endDate }" onclick="WdatePicker({dateFmt:'yyyyMMdd HHmmss'})" style="width: 150px;"/>&nbsp;&nbsp;
						&nbsp;&nbsp;
		                <input type="submit" style="margin-left:30px;" class="default_button" value='<s:text name="common.word.search" />' />						 
					</td>
					
				</tr>
			</table>
		</form>		
		
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
		    
		    <tr id="nc" class="headbg">
				<td>剩余余额</td>			    				
			</tr>
			<tr style="text-align: center">
				<td>${resultVo.result.balance}</td>			    				
			</tr>
		  
			<tr id="nc" class="headbg">
				<td>序号</td>
			     <td>扣除金额</td>
				<td>支付时间</td>
				<td>明细</td> 
				
			</tr>
			

			<c:forEach var="bean" items="${resultVo.result.accounts}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.changeMoney}</td>
					<td>${bean.changeTime}</td>
					<td>${bean.changeReason}</td>						
				</tr>
			</c:forEach>

		</table>

		<c:if test="${not empty gjfOrderInfos}">
			<s:form action="gjfOrderInfoAction!retrieveAllGjfOrderInfos.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>