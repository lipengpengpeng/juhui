<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
		<link href="${ctx}/js/validate/jquery.validate.css" type="text/css"
			rel="stylesheet" />
		<script src="${ctx}/js/validate/jquery.validate.js"
			type="text/javascript"></script>
		<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
		<script>
			function doYouWantToDel() {
				if(confirm('<s:text name="common.word.want.to.delete" />')) {
					return true;
				}
				return false;
			}
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				<!-- 当前位置: 栏目配置 --><s:text name="enterprise.column.current.location" /> - 
				<!-- 栏目信息列表 --><s:text name="enterprise.column.current.location.columnlist" />
			</div>
			<security:authorize ifAnyGranted="COLUMN_ADD">
				<div class="ropt">
					<a href="epColumnAction!add_page.action?father=${father}"><!-- 新增 --><s:text name="common.word.new" /></a>
				</div>
			</security:authorize>
			<div class="clear"></div>
		</div>
		<table class="listTable3" onmouseover="changeto()"
			onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>
					<!-- 栏目排序 --><s:text name="enterprise.column.order" />
				</td>
				<td>
					<!-- 栏目名称 --><s:text name="enterprise.column.name" />
				</td>
				<td>
					<!-- 栏目模块名称 --><s:text name="enterprise.column.module.name" />
				</td>
				<td>
					<!-- 模板类型 --><s:text name="enterprise.column.template.type" />
				</td>
				<td>
					<!-- 是否在主导航显示 --><%-- <s:text name="enterprise.column.is.showed.on.index" /> --%>
					是否在首页显示
				</td>
				<td>
					<!-- 状态 --><s:text name="common.word.status" />
				</td>
				<td>
					<!-- 操作 --><s:text name="common.word.handle" />
				</td>
			</tr>
			<c:forEach var="epColumn" items="${enterpriseColumnList}">
				<tr style="color: blue;">
					<td>
						${epColumn.orderColumn}
					</td>
					<td>
						${epColumn.names}
					</td>
					<td>
						${epColumn.frontNum}
					</td>
					<td>
						${epColumn.typeColumn.name}(${epColumn.typeColumn.templateType})
					</td>
					<td>
						<c:if test="${epColumn.isValidInNav=='1'}">
							<!-- 是 --><s:text name="common.word.yes" />
						</c:if>
						<c:if test="${epColumn.isValidInNav=='0'}">
							<!-- 否 --><s:text name="common.word.no" />
						</c:if>
					</td>
					<td>
						<c:if test="${epColumn.state == 1}"><!-- 已启用 --><s:text name="common.word.has.opened" /></c:if>
						<c:if test="${epColumn.state == 0}">
							<font color="red"><!-- 已停用 --><s:text name="common.word.has.closed" /></font>
						</c:if>
					</td>
					<td>
						<a href="epColumnAction!edit.action?id=${epColumn.id}"><!-- 编辑  --><s:text name="common.word.edit" /></a>&nbsp;
						&nbsp;
						<c:if test="${empty epColumnList}">
							<a href="epColumnAction!delete.action?id=${epColumn.id}"
								onclick="return doYouWantToDel();">
								<!-- 删除  --><s:text name="common.word.delete" /> </a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>

