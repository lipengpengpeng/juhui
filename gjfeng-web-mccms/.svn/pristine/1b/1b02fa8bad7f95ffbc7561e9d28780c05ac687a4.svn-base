<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>MCCMS</title>
<link href="${ctx}/js/AsyncBox/skins/ZCMS/asyncbox.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${ctx}/js/AsyncBox/AsyncBox.v1.4.js"></script>
<script>
	$(document).ready(function(){
		$("#name").focus();
		$("#linksForm").validate({
			 rules: { 
			 	fatherAuthName : {
			 		required: function(){
			 			if(authoritiesType == '2'){
			 				return false;
			 			}
			 		}
			 	},
				name: { 
        			required: true, 
        			maxlength:50,
        			remote: "authoritiesAction!checkName.action?orgName=''&math="+Math.random()
    			},
    			displayName:
    			{
    				required: true, 
        			maxlength:50,
        			remote: "authoritiesAction!checkDisplayName.action?orgName=''&math="+Math.random()
    			}
			},
			messages: {
				fatherAuthName : {
			 		//请选择父级权限
					required: '<s:text name="enterprise.system.authority.select.father" />'
			 	},
				name: {
					//该权限名称已存在
					remote: '<s:text name="enterprise.system.authority.name.exist" />'
				},
				displayName:
				{
					//该显示名称已存在
					remote: '<s:text name="enterprise.system.authority.display.name.exist" />'
				}
			},
			success: function(label) {
				label.html("&nbsp;").addClass("checked");
	        }	
		});
		
		changeType();
		$("#authoritiesType").change(function(){
			changeType();
			//清空父级权限框
			$('#fatherAuthName').val('');
		});
	});	
	
	var authoritiesType;
	//父级权限树
	function listAuthorities() {
		asyncbox.open({
			//父级权限
			title: '<s:text name="enterprise.system.authority.father.level" />',
			url : 'authoritiesAction!generateTree.action',
			data : {
				<c:if test="${not empty id}">
					fatherAuthId : ${id},
				</c:if>
				type : authoritiesType
			},
			width : 300,
    		height : 440,
			btnsbar : $.btn.OKCANCEL,
			callback : function(action, iframe) {
				if(action == 'ok') {
					$("#fatherAuthName").val(iframe.returnValue);
				}
			}
		});
	}
	
	function changeType(){
		authoritiesType = $('#authoritiesType').val();
		if(authoritiesType == '2'){
			$('#fatherAuthName').parent().parent().hide();
		}else{
			$('#fatherAuthName').parent().parent().show();
		}
	}
</script>
 	</head>
	<body style="padding:8px;">

	<div class="rhead">
		<div class="rpos">
			<!-- 当前位置: 系统配置 - 权限管理 --><s:text name="enterprise.system.authority.current.location" /> - 
			<!-- 添加权限信息 --><s:text name="enterprise.system.authority.current.location.add" />
		</div>
		<div class="ropt"><input type="button" value='<s:text name="common.word.return.back" />' onclick="history.back()"/></div>
		<div class="clear"></div>
  	</div>
 	 
	<form action="authoritiesAction!add.action" method="post" id="linksForm" enctype="multipart/form-data" method="post">
		<table  align="center" class="listTable3" width="90%">
			<tr>
				<td><!-- 父级权限 --><s:text name="enterprise.system.authority.father.level" /></td><td><input type="text" name="fatherAuthName" id="fatherAuthName" value="${fatherAuthName}" onfocus="listAuthorities();" readonly/></td>
			</tr>
			<tr>
				<td><!-- 权限名称 --><s:text name="enterprise.system.authority.name" /></td><td><input type="text" name="name"/></td>
			</tr>
			<tr>
				<td><!-- 显示名称 --><s:text name="enterprise.system.authority.display.name" /></td><td><input type="text" name="displayName"/></td>
			</tr>
			<tr>
				<td>
					<!-- 权限类型 --><s:text name="enterprise.system.authority.type" />
				</td>
				<td>
					<select name="authorities.authoritiesType" id="authoritiesType">
						<option value="0"><!-- 菜单 --><s:text name="enterprise.system.authority.menu" /></option>
						<option value="1" <c:if test="${type == 1}">selected</c:if>>
							<!-- 栏目 --><s:text name="enterprise.system.authority.column" />
						</option>
						<option value="2" <c:if test="${type == 2}">selected</c:if>>
							<!-- 功能 --><s:text name="enterprise.system.authority.function" />
						</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="submit" value='<s:text name="common.word.submit" />' />
				</td>
			</tr>
	 	</table>
	 	<s:token></s:token>
	</form>
	</body>
</html>

