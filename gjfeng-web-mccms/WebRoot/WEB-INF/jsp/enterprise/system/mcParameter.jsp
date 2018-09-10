<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
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
				<!-- 当前位置: 系统配置 - 产品参数管理 --><s:text name="enterprise.system.parameter.current.location" />
			</div>
			<div class="ropt">
			</div>
			<div class="clear"></div>
		</div>
		
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td><!-- 序号 --><s:text name="common.word.id" /></td>
				<td><!-- 中文字段名称 --><s:text name="enterprise.system.parameter.name" /></td>
				<td><!-- 调用代码 --><s:text name="enterprise.system.parameter.invoke.code" /></td>
				<td><!-- 字段排序 --><s:text name="enterprise.system.parameter.order" /></td>
				<td><!-- 字段类型 --><s:text name="enterprise.system.parameter.type" /></td>
				<td><!-- 是否开启该字段 --><s:text name="enterprise.system.parameter.status" /></td>
				<td><!-- 操作 --><s:text name="common.word.handle" /></td>
			</tr>

			<c:forEach var="bean" items="${mcParameters}"
				varStatus="status">
				<tr>
					<td>${bean.id}</td>
					<td>${bean.name}</td>
					<td>${bean.mark}</td>
					<td>${bean.noOrder}</td>
					
					<td>
						<c:if test="${bean.type==0}"><!-- 简短字段 --><s:text name="enterprise.system.parameter.short.field" /></c:if>
						<c:if test="${bean.type==1}"><!-- 备用字段 --><s:text name="enterprise.system.parameter.backup.field" /></c:if>
						<c:if test="${bean.type==2}"><!-- 上传字段 --><s:text name="enterprise.system.parameter.upload.field" /></c:if>
					
					</td>

				
					<td>
						<c:if test="${bean.wrOk==1}"><!-- 已启用 --><s:text name="common.word.has.opened" /></c:if>
						<c:if test="${bean.wrOk==0}"><!-- 已停用 --><s:text name="common.word.has.closed" /></c:if>
					</td>
					<td style="text-align:center;">
						&nbsp; &nbsp;
						<a href="mcParameterAction!retrieveMcParameterById.action?id=${bean.id}"><!-- 编辑  --><s:text name="common.word.edit" /></a>
						&nbsp; &nbsp;
					</td>
				</tr>
			</c:forEach>

		</table>


	</body>
</html>