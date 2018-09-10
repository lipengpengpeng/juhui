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
				当前位置: 提现日报表
			</div>
			<div class="clear"></div>
			<div class="ropt"><a href="countInfoAction!toWithdrawReport.action?addTime=${addTime }&endTime=${endTime }&ste=1" onclick="return doYouWantToExport();">导出</a></div>
		</div>	
		<form action="countInfoAction!toWithdrawReport.action" method="get">
			<table class="listTable2">
				<tr>
					<td>
					开始日期：<input type="text" readonly="readonly" placeholder="请选择日期" value="${addTime }" name="addTime" onclick="WdatePicker()" style="width: 75px;"/>&nbsp;&nbsp;&nbsp;
				-&nbsp;&nbsp;&nbsp;	
				结束日期：<input type="text" readonly="readonly" placeholder="请选择日期" value="${endTime }" name="endTime" onclick="WdatePicker()" style="width: 75px;"/>&nbsp;&nbsp;&nbsp;
					 <input type="submit" style="margin-left:30px;" class="default_button" value='<s:text name="common.word.search" />' />
					</td>
				</tr>
			</table>
		</form>				
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>日期</td>
				<td>金额</td>
				<td>提现成功金额</td>
				<td>提现失败金额</td>
			</tr>
			
			 
			 <c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td><fmt:formatDate value="${bean.addTime }" pattern="yyyy-MM-dd"/></td>
					<td>${bean.money }</td>
					<td>${bean.successMoney }</td>
					<td>${bean.failMoney }</td>	
				</tr>
			</c:forEach>
		</table>

		<c:if test="${not empty pager.resultList}">
			<s:form action="countInfoAction!toWithdrawReport.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
					<input type="hidden" readonly="readonly" value="${addTime }" name="addTime"/>
					<input type="hidden" readonly="readonly" value="${endTime }" name="endTime"/>
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>