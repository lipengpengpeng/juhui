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
            	newpassword: {
                    required: true, 
        			maxlength:16,
        			minlength:5
        	    },
        	    repassword:{
        	    	required: true, 
        			maxlength:16,
        			minlength:5,
        			equalTo: "#newpassword"
        	    }
			},
			messages: {
				oldpassword: {
					//原密码错误
					remote: '<s:text name="enterprise.system.password.old.error" />'
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
			<div class="rpos">
				<!-- 当前位置: 重置密码 --><s:text name="enterprise.system.password.current.location.reset" />
			</div>
			<div class="clear"></div>
		</div>

		<form action="updatePasswordAction!reset.action" method="post"
			name="passwordForm" id="passwordForm">
			<input type="hidden" name="id" value="${id }"/>
			<table  class="listTable3">
				<!-- <tr>
				<td>原密码</td><td><input type="password" name="oldpassword"/></td>
			</tr>-->
				<tr>
					<td width="100px" class="pn-flabel">
						<!-- 新密码 --><s:text name="enterprise.system.password.new" />
					</td>
					<td>
						<input type="password" name="newpassword" id="newpassword"/>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel">
						<!-- 确认密码 --><s:text name="enterprise.system.password.new.confirm" />
					</td>
					<td>
						<input type="password" name="repassword" id="repassword" />
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<input type="submit" value='<s:text name="enterprise.system.password.change.button" />' />
					</td>
				</tr>
			</table>

		</form>
	</body>
</html>

