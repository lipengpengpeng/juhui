<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>MCCMS</title>
	</head>
	<body>
	
	<div class="rhead">
			<div class="rpos">
				<!-- 当前位置: 界面风格 --><s:text name="enterprise.style.current.location" /> - 
				<!-- 模板管理 --><s:text name="enterprise.style.current.location.template" />
			</div>
			<div class="ropt">
				<a href="webSkinAction!add_page.action"><!-- 新增 --><s:text name="common.word.new" /></a>
			</div>
			<div class="clear"></div>
	 </div>
 	 
 	 <table class="listTable3" onmouseover="changeto()"  onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>
					<!-- 模板名称 --><s:text name="enterprise.style.template.name" />
				</td>
				<td>
					<!-- 模板文件夹名称 --><s:text name="enterprise.style.template.folder.name" />
				</td>
				<td>
					<!-- 状态 --><s:text name="common.word.status" />
				</td>
				<td>
					<!-- 操作 --><s:text name="common.word.handle" />
				</td>
		   </tr>
		    <c:forEach var="webSkin" items="${webSkinList}">
		    <tr>
		   		 <td>
					${webSkin.names}
				</td>
				<td>
					${webSkin.filename}
				</td>
				<td>
					<c:if test="${webSkin.state == 1}"><!-- 已启用 --><s:text name="common.word.has.opened" /></c:if>
					<c:if test="${webSkin.state == 0}"><font color="red"><!-- 已停用 --><s:text name="common.word.has.closed" /></font></c:if>
				</td>
				<td>
					<c:if test="${webSite.defaultSkin==webSkin.id}"><!-- 已启用 --><s:text name="common.word.has.opened" />
	            	</c:if>
	            	<c:if test="${webSite.defaultSkin!=webSkin.id}">
	            	<a href="webSkinAction!updateDefaultSkin.action?id=${webSkin.id}">
	            	<!-- 更改为默认  --><s:text name="enterprise.style.template.set.to.default" />
	            	 </a>
	            	</c:if>
		            &nbsp;&nbsp;|
		          	 <a href="webSkinAction!edit.action?id=${webSkin.id}"><!-- 编辑  --><s:text name="common.word.edit" />
		            </a>
		         	 <!--|
		         	 <a href="webSkinAction!delete.action?id=${webSkin.id}">
		            	<img src="${ctx}/image/jwc/del.gif" width="16" height="16" border="0px;" />删除
		            </a>
		            -->
				</td>
		    	</tr>
		    </c:forEach>
	</table>
  
	</body>
</html>

           