<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>MCCMS</title>
	
<script>
$(document).ready(function(){

		$("#names").focus();
		$("#columnForm").validate({
			 rules: { 
				names: { 
        			required: true, 
        			maxlength:20,
        			remote: "webSkinAction!checkName.action?orgName=${webSkin.names}&math="+Math.random()
    			},
            	filename: {
                    required: true ,
			        maxlength:20
        	    },
        	    content:{
        	    	required: true ,
        	    	maxlength:200
        	    }
			},
			messages: {
				names: {
					//该模板名称已存在
					remote: '<s:text name="enterprise.style.template.name.exist" />'
				}
			},
			success: function(label) {
				label.html("&nbsp;").addClass("checked");
	        }		
		});
	});	
</script>
	</head>
	<body>
	 <table class="listTable2">
			<tr>
				<td width="20px">
					<img src="${ctx}/image/jwc/tb.gif" width="16" height="16" />
				</td>
				<td width="90%">
					<!-- 当前位置: 界面风格 --><s:text name="enterprise.style.current.location" /> - 
					<!-- 模板管理 --><s:text name="enterprise.style.current.location.template" /> - 
					<!-- 编辑模板信息 --><s:text name="enterprise.style.current.location.template.edit" />
				</td>
				<td>
					
				</td>
			</tr>
		</table>
 	 
	<form action="webSkinAction!update.action" method="post" id="columnForm">
		<input type="hidden" name="webSkin.id" value="${webSkin.id}"/>
		<table class="listTable3" onmouseover="changeto()"  onmouseout="changeback()">
			
			<tr>
				<td><!-- 模板名称 --><s:text name="enterprise.style.template.name" /></td><td><input type="text" name="names" value="${webSkin.names}"  size="60" /></td>
			</tr>
			<tr>
				<td><!-- 模板文件夹名称 --><s:text name="enterprise.style.template.folder.name" /></td><td><input type="text" name="filename" value="${webSkin.filename}" size="60" /></td>
			</tr>
			<tr>
				<td><!-- 模板描述 --><s:text name="enterprise.style.template.description" /></td>
				<td>
					<textarea rows="10" cols="47" name="content">${webSkin.content}</textarea>
				</td>
			</tr>
			<tr>
				<td><!-- 状态--><s:text name="common.word.status" /></td>
				<td>
					<select name="webSkin.state">
						<option value="1"><!-- 已启用 --><s:text name="common.word.has.opened" /></option>
						<option value="0" <c:if test="${webSkin.state==0}">selected</c:if> ><!-- 已停用 --><s:text name="common.word.has.closed" /></option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value='<s:text name="common.word.submit" />' />
					<input type="button" value='<s:text name="common.word.return.back" />' onclick="history.back()"/>
				</td>
			</tr>
		</table>
		<s:token></s:token>
	</form>
	</body>
</html>

