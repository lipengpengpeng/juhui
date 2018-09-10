
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
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 会员管理- 商家列表
			</div>
			<div class="clear"></div>
		</div>	
		
			
		<form action="${ctx}/subsystem/memberInfoAction!findAllSeller.action" method="get">
				<table class="listTable2">
					<tr>
						<td>
								名称：<input type="text" placeholder="请输入名称" name="name" value="${name }"/> &nbsp;&nbsp;&nbsp;
								账号：<input type="text" placeholder="请输入账号" name="mobile" value="${mobile }"/> &nbsp;&nbsp;&nbsp;
							    <input type="submit" value="查询"/>
						</td>
					</tr>
				</table>
	</form>	        
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>名称</td>
				<td>昵称</td>
				<td>账号(手机号码)</td>
				<td>余额</td>
				<td>可提现额</td>
				<td>最后登录时间</td>
				<!-- <td>操作</td> -->
			</tr>
			
			 
		<c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.name }</td>
					<td>${bean.nickName }</td>
					<td>${bean.mobile }</td>
					<td>${bean.balanceMoney }</td>
					<td>${bean.withdrawalMoney }</td>
					<td>${bean.lastLoginTime }</td>
					<%-- <td>
						<a href="">财务结算</a>
					</td> --%>
				</tr>
		</c:forEach>
			 
			
			

		</table>

		<c:if test="${not empty pager.resultList}">
			<s:form action="memberInfoAction!findAllSeller.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
					<input type="hidden"  name="name" value="${name }"/>
					<input type="hidden"  name="mobile" value="${mobile }"/>
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>