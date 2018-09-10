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
			function del(id){
				var r = confirm("确定该操作吗？");
				if(r == true){
					$.ajax({
	      				type: "post",
	      				url: 'productAttrAction!delProductAttrById.action',
	      				dataType: "text",
	      				data:{
	      					"id":id
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
				当前位置: 模块 - 子模块
			</div>
			<div class="ropt">
				<input type="button" value='<s:text name="common.word.return.back" />' onclick="history.back();"/>
				&nbsp;&nbsp;&nbsp;
				<input type="button" value='<s:text name="common.word.new" />' onclick="location.href='productAttrAction!newProductAttr.action?proId=${proId}'"/>
			</div>
			<div class="clear"></div>
		</div>
		
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>属性类型</td>
				<td>属性值</td>
				<td>排序</td>
				<td>操作</td>
			</tr>

			<c:forEach var="bean" items="${gjfProductAttrs}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.attrValueId.attrId.attrName}</td>
					<td>${bean.attrValueId.attrValue}</td>
					<td>${bean.orderBy}</td>
					<td>
						<%-- <a href="javascript:void(0);" onclick="ajaxUpdateDel(${bean.id},this,${ctx}/ajaxProductAttrAction!delProductAttrById)">删除</a> --%>
						<a href="javascript:void(0);" onclick="del(${bean.id})">删除</a>
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