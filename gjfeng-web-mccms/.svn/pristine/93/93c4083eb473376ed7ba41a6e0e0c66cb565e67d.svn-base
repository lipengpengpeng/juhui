<%--

	gjfProductAttr.jsp

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
						
			function openInfo(id){
				if(confirm("是否确认删除？")){
					$.ajax({
	      				type: "post",
	      				url: 'tradeInfoAction!deleteDivs.action',
	      				dataType: "text",
	      				data:{
	      					"setDivi.id":id
							},
	      				success:function(data){
	      					 location.reload();
	      				},
	      				error:function(){
	  
	      				}
					});
				}
			}
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 设置 - 分红权个数设置
			</div>+
			<div class="ropt">
				<%-- <input type="button" value='<s:text name="common.word.return.back" />' onclick="history.back();"/>
				&nbsp;&nbsp;&nbsp; --%>
				<input type="button" value='<s:text name="common.word.new" />' onclick="location.href='tradeInfoAction!goNewsDivis.action'"/>
			</div>
			<div class="clear"></div>
		</div>
		
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>累积消费最小金额</td>
				<td>累积消费最大金额</td>
				<td>分红权个数比例金额</td>
				<td>操作</td>
			</tr>

			<c:forEach var="bean" items="${resultVo.result}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.consumptionMin}</td>
					<td>${bean.consumptionMax}</td>
					<td>${bean.consumption}</td>					
					<td>					    
						<a href="${ctx}/subsystem/tradeInfoAction!goEditDivs.action?setDivi.id=${bean.id}" >编辑</a>
						<a href="javascript:void(0);" onclick="openInfo(${bean.id})">删除</a>
					</td>
				</tr>
			</c:forEach>

		</table>

		<c:if test="${not empty gjfProductAttrs}">
			<s:form action="productAttrAction!retrieveProductAttrById.action"
					 method="post" name="form1" theme="simple" id="form1">
				<input type="hidden" readonly="readonly" name="proId" value="${proId}" />
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>