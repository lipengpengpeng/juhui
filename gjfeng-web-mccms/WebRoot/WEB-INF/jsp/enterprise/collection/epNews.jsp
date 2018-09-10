<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
		
<script type="text/javascript">
	$(document).ready(function(){
		
		$("#alllist").click(
			function(){
				if($(this).attr("checked")){
					$(this).attr("checked",true);
					$(".list").attr("checked",true);
				}else{
					$(this).attr("checked",false);
					$(".list").attr("checked",false);
				}
			}
		);
	});
	
	function submitForm(message,flag){
		if(confirm(message)){
			if(flag==1){
				$("#flag").attr("value","true");
			}else{
				$("#flag").attr("value","false");
			}
			$("#enterpriseList").submit();
			return true;
		}
		return false;
	}
	
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
			<div class="rpos"><!-- 当前位置: 内容管理 --><s:text name="enterprise.collection.current.location" /> - 
				<c:if test="${empty column.names}"><!-- 总站 --><s:text name="common.word.site.center" /></c:if>${column.names}</div>
				<c:if test="${isShowInsert}">
					<div class="ropt">
						<input type="button" value='<s:text name="common.word.new" />' onclick="location.href='${ctx}/collection/epNewsAction!add_page.action?father=${column.id}'"/>
					</div>
				</c:if>
			<div class="clear"></div>
		</div>
		<form action="epNewsAction!queryByTitleAndState.action" method="post">
		<input type="hidden" name="father" value="<c:if test="${empty column.id}">0</c:if><c:if test="${not empty column.id}">${column.id}</c:if>" />
		<table class="listTable2">
			<tr>
				<td class="pn-flabel" style="text-align:left;padding-left:15px;"><!-- 标题 --><s:text name="common.word.title" />：<input type="text" placeholder="请输入标题" name="title" value="${title}" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<!-- 状态  --><s:text name="common.word.status" />：
					<select name="state">
						<option value="-1"><!-- 全部  --><s:text name="common.word.all" /></option>
						<option value="1" <c:if test="${state==1}">selected</c:if>><!-- 已通过--><s:text name="enterprise.collection.passed" /></option>
						<option value="0" <c:if test="${state==0}">selected</c:if>><!-- 不通过--><s:text name="enterprise.collection.not.pass" /></option>
						<option value="2" <c:if test="${state==2}">selected</c:if>><!-- 待审核--><s:text name="enterprise.collection.pending.approve" /></option>
					</select>
					<input type="submit" style="margin-left:30px;" class="default_button" value='<s:text name="common.word.search" />' />
				</td>
			</tr>
		</table>
		</form>
		<table class="listTable3" onmouseover="changeto()"
			onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<!-- 
				<td>
					<input type="checkbox" id="alllist" value="${news.id}" />
		   			<input type="hidden" id="flag" name="flag" value="false"/>
				</td>
				-->
				<td width="480px;">
					<!-- 标题 --><s:text name="common.word.title" />
				</td>
				<td>
					<!-- 文档作者 --><s:text name="common.word.author" />
				</td>
				<td width="100px;">
					<!-- 更新时间 --><s:text name="common.word.update.time" />
				</td>
				<td width="60px;">
					<!-- 新闻类型 --><s:text name="enterprise.collection.news.type" />
				</td>
				<td>
					<!-- 状态  --><s:text name="common.word.status" />
				</td>
				<td>
					<!-- 操作  --><s:text name="common.word.handle" />
				</td>
			</tr>
			<c:forEach var="news" items="${enterpriseNewsList}">
				<tr>
					<!-- 
					<td width="25px;">
			   			<input type="checkbox" class="list" name="list" value="${news.id}" />
			   		</td>
			   		 -->
					<td>
						<font style="font-weight:bold;overflow:hidden;">[${news.enterpriseColumn.names}]</font>
						<c:choose>
	                          <c:when test="${fn:length(news.title)>25}">
	                                ${fn:substring(news.title,0,25)}...
	                          </c:when>
	                          <c:otherwise>
	                                ${news.title}
	                          </c:otherwise>
	                    </c:choose>
					</td>
					<td style="width:129px;text-align:center;">
						${news.users.workunit}
					</td>
					<td style="width:70px;text-align:center;">
						<fmt:formatDate value="${news.editTime}" pattern="yyyy-MM-dd" />
					</td>
					<td style="width:70px;text-align:center;">
						<c:if test="${news.isPrimPhoto == '1'}">
							<font color="red"><!-- 图片新闻 --><s:text name="enterprise.collection.select.photonews" /></font>
						</c:if>
						<c:if test="${news.isPrimPhoto == '0'}">
							<!-- 普通新闻 --><s:text name="enterprise.collection.select.commonnews" />
						</c:if>
						<c:if test="${news.isPrimPhoto == '2'}">
							<font color="blue"><!-- 文件新闻 --><s:text name="enterprise.collection.select.filenews" /></font>
						</c:if>
					</td>
					<td style="width:70px;text-align:center;">
						<c:if test="${news.state == 1}"><!-- 已通过--><s:text name="enterprise.collection.passed" /></c:if>
						<c:if test="${news.state == 0}"><font color="red"><!-- 不通过--><s:text name="enterprise.collection.not.pass" /></font></c:if>
						<c:if test="${news.state == 2}"><font color="blue"><!-- 待审核--><s:text name="enterprise.collection.pending.approve" /></font></c:if>
					</td>
					<td style="text-align:center;">
						<a href="epNewsAction!view.action?id=${news.id}"><!-- 查看  --><s:text name="common.word.view" /></a>&nbsp;
						&nbsp;
						<c:if test="${news.state eq '2' }">
								<a href="epNewsAction!toAudit.action?id=${news.id}">审核</a>&nbsp;
						</c:if>
						<c:if test="${news.enterpriseColumn.typeColumn.templateType != 'content'}">
							<c:if test="${(news.users.loginName == currentUserName) or (isAdmin==1)}">
								<a href="epNewsAction!edit.action?father=${father}&&id=${news.id}&&pageSize=${pager.pageSize}&&pageNo=${pager.pageNo}"><!-- 编辑  --><s:text name="common.word.edit" /></a>&nbsp;
								&nbsp;
								<a href="epNewsAction!delete.action?father=${father}&&id=${news.id}"
									onclick="return doYouWantToDel();"><!-- 删除  --><s:text name="common.word.delete" /></a>
							</c:if>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
		
		<c:if test="${not empty enterpriseNewsList}">
		<s:form action="epNewsAction!query.action" method="post" id="form1" name="form1" theme="simple">
			<input type="hidden" name="father" value="${father}"/>
			<input type="hidden" name="title" value="<c:if test="${not empty title}">${title}</c:if>" />
			<input type="hidden" name="state" value="<c:if test="${empty state}">-1</c:if><c:if test="${not empty state}">${state}</c:if>" />

			<!-- 分页 -->
			<%@ include file="../../common/pager.jsp"%>

		</s:form>
		</c:if>
	</body>
</html>

