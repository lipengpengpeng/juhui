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
			function update(id,status){
				var proId = $("#proId").val();
				var r = confirm("确定该操作吗？");
				if(r == true){
					$.ajax({
	      				type: "post",
	      				url: 'productAttrStockAction!updateStatus.action',
	      				dataType: "text",
	      				data:{
	      					"id":id,
	      					"status":status
						},
	      				success:function(data){
	      					var jsondata = eval("("+data+")");  
		        			alert(jsondata.msg);
		        			window.location.href="productAttrStockAction!retrieveProductAttrStockByProId.action?proId="+proId;
	      				},
	      				error:function(){
	      					alert("操作出错!");
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
				<%--&nbsp;&nbsp;&nbsp;
				 <input type="button" value='<s:text name="common.word.new" />' onclick="location.href='productAttrAction!newProductAttr.action?proId=${proId}'"/> --%>
			</div>
			<div class="clear"></div>
		</div>
		
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>属性组合</td>
				<td>库存数量</td>
				<td>产品价格</td>
				<td>新增时间</td>
				<td>编辑时间</td>
				<td>状态</td>
				<td>操作</td>
			</tr>

			<c:forEach var="bean" items="${resultMap}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.key}</td>
					<td>${bean.value.repertory}</td>
					<td>${bean.value.price}</td>
					<td>
						<fmt:formatDate value="${bean.value.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/>	
					</td>
					<td>
						<fmt:formatDate value="${bean.value.editTime}" pattern="yyyy-MM-dd HH:mm:ss"/>	
					</td>
					<td>
						<c:if test="${bean.value.status eq '1'}">已启用</c:if>
						<c:if test="${bean.value.status eq '0'}">停用</c:if>
					</td>
					<td>
						<c:if test="${bean.value.status eq '1'}">
							<a href="javascript:void(0);" onclick="return update(${bean.value.id},0);">停用</a>
						</c:if>
						<c:if test="${bean.value.status eq '0'}">
							<a href="javascript:void(0);" onclick="return update(${bean.value.id},1);">启用</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>

		</table>

		<c:if test="${not empty gjfProductAttrStocks}">
			<s:form action="productAttrStockAction!retrieveProductAttrStockByProId.action"
					 method="post" name="form1" theme="simple" id="form1">
				<input type="hidden" readonly="readonly" id="proId" name="proId" value="${proId}" />
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>