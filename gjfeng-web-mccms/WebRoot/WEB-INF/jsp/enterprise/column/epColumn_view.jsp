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
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				<!-- 当前位置: 栏目配置 --><s:text name="enterprise.column.current.location" /> - 
				<!-- 查看栏目信息 --><s:text name="enterprise.column.current.location.viewcolumn" />
			</div>
			<div class="clear"></div>
		</div>
		<table align="center" class="listView" width="90%">
			<tr>
				<td class="pn-flabel">
					<!-- 栏目名称 --><s:text name="enterprise.column.name" />
				</td>
				<td>
					${enterpriseColumn.names}
				</td>
			</tr>
			<tr>
				<td class="pn-flabel">
					<!-- 栏目简称 --><s:text name="enterprise.column.short.name" />
				</td>
				<td>
					${enterpriseColumn.shortName}
				</td>
			</tr>
			<tr>
				<td>
					<!-- 栏目编号 --><s:text name="enterprise.column.id" />
				</td>
				<td>
					${enterpriseColumn.num}
				</td>
			</tr>
			<tr>
				<td>
					<!-- 栏目模块名称 --><s:text name="enterprise.column.module.name" />
				</td>
				<td>
					${enterpriseColumn.frontNum}
				</td>
			</tr>
			<tr>
				<td>
					<!-- 栏目介绍 --><s:text name="enterprise.column.description" />
				</td>
				<td>
					${enterpriseColumn.intro}
				</td>
			</tr>
			<tr>
				<td>
					<!-- 栏目等级 --><s:text name="enterprise.column.class" />
				</td>
				<td>
					<c:choose>
						<c:when test="${enterpriseColumn.father == 0}">
							<strong><!-- 一级栏目 --><s:text name="enterprise.column.first.level" /></strong>
						</c:when>
						<c:otherwise>
							<!-- 二级栏目 --><s:text name="enterprise.column.second.level" />
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>
					<!-- 状态 --><s:text name="common.word.status" />
				</td>
				<td>
					<c:if test="${enterpriseColumn.state == 1}"><!-- 已启用 --><s:text name="common.word.has.opened" /></c:if>
					<c:if test="${enterpriseColumn.state == 0}">
						<font color="red"><!-- 已停用 --><s:text name="common.word.has.closed" /></font>
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value='<s:text name="common.word.return.back"/>' onclick="history.back()" />
				</td>
			</tr>
		</table>
		<br />

	</body>
</html>

