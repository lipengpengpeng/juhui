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
		$("#oldpassword").focus();
		$("#passwordForm").validate({
			 rules: { 
				oldpassword: { 
        			required: true, 
        			maxlength:16,
        			minlength:5,
        			remote: "updatePasswordAction!checkPassword.action?math="+Math.random()
    			},
            	newpassword: {
                    required: true, 
        			maxlength:16,
        			minlength:5
        	    },
        	    repassword:{
        	    	required: true, 
        			maxlength:16,
        			minlength:5,
        			equalTo:"#newpassword"
        	    }
			},
			messages: {
				oldpassword: {
					//原密码错误
					remote: '<s:text name="enterprise.system.password.old.error" />'
				},
				repassword:{
					//两次密码输入不一致
					equalTo:'<s:text name="enterprise.system.password.two.different" />'
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
		
		<div class="rhead">
			<div class="rpos"><!-- 当前位置: 修改密码 --><s:text name="enterprise.system.password.current.location" /></div>
			<div class="clear"></div>
		</div>
		<form action="updatePasswordAction!update.action" method="post" name="passwordForm" id="passwordForm">
		<table class="listTable3">
			<tr>
				<td class="pn-flabel" width="100px"><!-- 原密码 --><s:text name="enterprise.system.password.old" /></td><td><input type="password" name="oldpassword"/></td>
			</tr>
			<tr>
				<td class="pn-flabel"><!-- 新密码 --><s:text name="enterprise.system.password.new" /></td><td><input type="password" name="newpassword" id="newpassword"/></td>
			</tr>
			<tr>
				<td class="pn-flabel"><!-- 确认密码 --><s:text name="enterprise.system.password.new.confirm" /></td>
				<td>
					<input type="password" name="repassword" />
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<!-- 修改密码 -->
					<input type="submit" value='<s:text name="enterprise.system.password.change.button" />' />
				</td>
			</tr>
		</table>
		
		</form>
	</body>
</html>

