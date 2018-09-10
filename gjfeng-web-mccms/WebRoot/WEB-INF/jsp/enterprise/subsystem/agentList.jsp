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
		function updateStatus(id,token,agentMoney){
			if(agentMoney != 0){
				alert("未结算代理金额不为零!请先结算该代理金额!");
				return false;
			}
			var r = confirm("确定该操作吗？");
			if(r == true){
				$.ajax({
      				type: "post",
      				url: 'memberInfoAction!updateAgent.action',
      				dataType: "text",
      				data:{
      					"id":id,
						"token":token
						},
      				success:function(data){
      					var jsondata = eval("("+data+")");  
	        			alert(jsondata.msg);
	        			window.location.reload();
      				},
      				error:function(){
  						alert("出错啦！请稍后再试");
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
				当前位置: 代理商列表
			</div>
			<div class="clear"></div>
			
		</div>	
				
		<form action="memberInfoAction!findAllAgents.action" method="post">
			<table class="listTable2">
				<tr>
					<td>
						代理商名称：<input type="text" placeholder="请输入名称" name="name" value="${name }"/>&nbsp;&nbsp;
						代理开始时间：<input type="text" placeholder="请输入时间" name="startDate" value="${startDate }" onclick="WdatePicker()" style="width: 75px;"/>&nbsp;&nbsp;
						代理等级：
						 <select name="identity" >
							 	<option value="" <c:if test="${empty identity}">selected="selected"</c:if>>全部</option>
					   			<option value="1" <c:if test="${identity eq '1'}">selected="selected"</c:if>>个代</option>
				  				<option value="2" <c:if test="${identity eq '2'}">selected="selected"</c:if>>区代</option>
				 				<option value="3" <c:if test="${payStatus eq '3'}">selected="selected"</c:if>>市代</option>
		                 </select>&nbsp;&nbsp;
		                                是否启用： 
                          <select name="status">
							 	<option value=""  <c:if test="${empty status}">selected="selected"</c:if>>全部</option>
					   			<option value="0" <c:if test="${status eq '0'}">selected="selected"</c:if>>停用</option>
					  			<option value="1" <c:if test="${status eq '1'}">selected="selected"</c:if>>启用</option>
                 		  </select>&nbsp;&nbsp;
		                <input type="submit" style="margin-left:30px;" class="default_button" value='<s:text name="common.word.search" />' />
					</td>
				</tr>
			</table>
		</form>		
					
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>代理商名称</td>
				<td>未结算代理金额</td>
				<td>总获取代理金额</td>
				<td>开始代理时间</td>
				<td>结束代理时间</td>
				<td>代理等级</td>
				<td>是否启用</td>
				<td>操作</td>
			</tr>
			
			 
			<c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.name }</td>
					<td>${bean.agentMoney }</td>
					<td>${bean.agentTotalMoney }</td>
					<td>
						<fmt:formatDate value="${bean.startDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						<fmt:formatDate value="${bean.endDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						<c:choose>
							<c:when test="${bean.identity eq '1'}">个代</c:when>
							<c:when test="${bean.identity eq '2'}">区代</c:when>
							<c:when test="${bean.identity eq '3'}">市代</c:when>
						</c:choose>
					</td>
					<td>
						<c:if test="${bean.status eq '0'}">已停用</c:if>
						<c:if test="${bean.status eq '1'}">已启用</c:if>
					</td>
					<td>
					
					  
					
						<a href="memberInfoAction!views.action?id=${bean.id }&token=${bean.token}">查看信息</a>&nbsp;&nbsp;
					<security:authorize ifAnyGranted="MEMBER_AGENT_LIST_SETTLE">	
						<a href="memberInfoAction!settlement.action?id=${bean.id}&token=${bean.token}&agentMoney=${bean.agentMoney}">结算金额</a>&nbsp;&nbsp;
					 </security:authorize>  
						<a href="storeInfoAction!findStoreByAgent.action?id=${bean.id}&token=${bean.token}&identity=${bean.identity}">查看下级店铺</a>&nbsp;&nbsp;
					<c:if test="${bean.identity ne '1'}">
						<a href="storeInfoAction!findStoreBenefitByAgent.action?id=${bean.id}&token=${bean.token}&identity=${bean.identity}">查看旗下商家流水</a>&nbsp;&nbsp;
					</c:if>
					<security:authorize ifAnyGranted="MEMBER_AGENT_LIST_CANCEL">
					    <a href="javascript:void(0);" onclick=" return updateStatus(${bean.id},'${bean.token}',${bean.agentMoney})">取消代理</a>
					 </security:authorize>  
					</td>
				</tr>
	</c:forEach>
		</table>

		<c:if test="${not empty pager.resultList}">
			<s:form action="memberInfoAction!findAllAgents.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
					<input type="hidden" name="name" value="${name }"/>
					<input type="hidden" name="startDate" value="${startDate }"/>
					 <select name="identity" style="display: none;">
						 	<option value="" <c:if test="${empty identity}">selected="selected"</c:if>>全部</option>
				   			<option value="1" <c:if test="${identity eq '1'}">selected="selected"</c:if>>个代</option>
			  				<option value="2" <c:if test="${identity eq '2'}">selected="selected"</c:if>>区代</option>
			 				<option value="3" <c:if test="${payStatus eq '3'}">selected="selected"</c:if>>市代</option>
	                 </select>
                         <select name="status" style="display: none;" >
						 	<option value=""  <c:if test="${empty status}">selected="selected"</c:if>>全部</option>
				   			<option value="0" <c:if test="${status eq '0'}">selected="selected"</c:if>>停用</option>
				  			<option value="1" <c:if test="${status eq '1'}">selected="selected"</c:if>>启用</option>
                		  </select>
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>