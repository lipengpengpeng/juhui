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
	if($.browser.msie)
		window.attachEvent('onbeforeunload',function(){$("#submitButton").val('<s:text name="common.word.wait" />').attr("disabled","true");});
	else//在chrome中不起作用
 		window.addEventListener('onbeforeunload',function(){$("#submitButton").val('<s:text name="common.word.wait" />').attr("disabled","true");});
 //初始化地址
 /*  addressInit('cmbProvince', 'cmbArea', 'county', '广东', '${users.area}', '');
  $("#area").change(function(){
		 	$("#showArea").val($(this).val());
   }); */

		$("#name").focus();
		$("#userForm").validate({
			 rules: { 
			 	loginName: 
			 	{ 
			 		required: true, 
        			maxlength:12,
        			remote: "usersAction!checkName.action?orgName=''&math="+Math.random(),
        			isLoginName: true
			 	},
				name: { 
        			required: true, 
        			maxlength:12
    			},
    			password: {
    				required: true, 
        			maxlength:16,
        			maxlength:12
    			},
    			repassword: {
    				required: true, 
        			maxlength:16,
        			maxlength:12,
        			equalTo: "#password"
    			},
    			checkmenu: {
    				required: true
    			},
    			workphone:
    			{
    				isPhoneCall: true,
    				maxlength:50
    			},
    			area: {
    				required: true,
    				maxlength:12
    			},
    			workunit: {
    				required: true,
    				maxlength:50
    			},
    			address: {
                    required: true , 
        			maxlength:30
    			},
            	email: {
                    email: true, 
        			maxlength:25
    			},
        	    fax:{
        	    	isPhoneCall: true,
        	    	maxlength:25
				},
            	 mobile: {
                         isPhoneCall: true,
			 			maxlength:50
				}
			},
			messages: {
				loginName: {
					//该用户已存在
					remote: '<s:text name="enterprise.system.user.name.exist" />'
				}
			},
			success: function(label) {
				label.html("&nbsp;").addClass("checked");
	        }	
		});
	});		
 </script>
 <style>
 <!--
	#userRole label.error{
	 	position: absolute;
		background-position-y: 6px;
	 	margin-left: 341px;
	}
 -->
 </style>
	</head>
	<body>
		<div class="rhead">
		<div class="rpos">
			<!-- 当前位置: 系统配置 - 用户管理 --><s:text name="enterprise.system.user.current.location" /> - 
			<!-- 添加用户信息 --><s:text name="enterprise.system.user.current.location.add" />
		</div>
		<div class="ropt"><input type="button" value='<s:text name="common.word.return.back" />' onclick="history.back()"/></div>
		<div class="clear"></div>
		</div>
		<form action="usersAction!add.action" method="post" id="userForm"
		 name="userForm"  enctype="multipart/form-data">
		
		<table  align="center" class="listTable3" width="90%">
			<tr>
				<td width="60px"  class="pn-flabel"><!-- 用户名 --><s:text name="enterprise.system.user.name" /></td><td><input type="text" name="loginName"/></td>
			</tr>
			<tr>
				<td  class="pn-flabel"><!-- 密码 --><s:text name="enterprise.system.user.password" /></td>
				<td>
					<input type="password" id="password" name="password"/>
				</td>
			</tr>
			<tr>
				<td  class="pn-flabel"><!-- 重复密码 --><s:text name="enterprise.system.user.repeat.password" /></td>
				<td>
					<input type="password" name="repassword"/>
				</td>
			</tr>
			<tr>
				<td  class="pn-flabel"><!-- 真实名字 --><s:text name="enterprise.system.user.real.name" /></td><td><input type="text" name="name"/></td>
			</tr>
			<tr>
			  <td  class="pn-flabel"><!-- 所属角色 --><s:text name="enterprise.system.user.belong.to.role" /></td>
			  <td id="userRole"><!-- 省级管理员或者超级管理员 -->
			      <c:forEach var="roles" items="${rolesList}">
					<input type="checkbox" value="${roles.id}" name="checkmenu"/>${roles.name}&nbsp;&nbsp;
				  </c:forEach>
			  </td>
			</tr>
			<!-- <tr>
			 	<td  class="pn-flabel">性别</td>
				<td>
				   <select name="users.sex">
						<option value="1">男</option>
						<option value="0">女	</option>
					</select>
				</td>
			</tr>-->
			<tr>
				<td  class="pn-flabel"><!-- 工作单位 --><s:text name="enterprise.system.user.work.company" /></td><td><input type="text" name="workunit" value=""/></td>
			</tr>
			<tr>
				<td  class="pn-flabel"><!-- 地区 --><s:text name="enterprise.system.user.area" /></td><td><input type="text" name="area" id="showArea"
					<c:if test="${isAdmin==2}"> value="${users.area}" readonly</c:if>
				/></td>
			</tr>
			<%-- <tr>
				<td class="pn-flabel"><!-- 区/县 --><s:text name="enterprise.system.user.region" /></td>
				<td>
					<select id="cmbProvince" ></select><!-- 省 --><s:text name="enterprise.system.user.province" />&nbsp;&nbsp;
					<select id="cmbArea"  <c:if test="${isAdmin==2}">  disabled</c:if>></select><!-- 市 --><s:text name="enterprise.system.user.city" />&nbsp; &nbsp;
					<select id="county" name="county"></select><!-- 区/县 --><s:text name="enterprise.system.user.region" />&nbsp;&nbsp;
				</td>
			</tr> --%>

			 <!--<tr>
				<td  class="pn-flabel">身份证号码</td><td><input type="text" name="address"/></td>
			</tr>  -->
			<tr>
				<td class="pn-flabel"><!-- 地址 --><s:text name="enterprise.system.user.address" /></td><td><input type="text" name="address"  value="${tusers.address}"/></td>
			</tr>			
			 <tr>
				<td  class="pn-flabel"><!-- 联系电话 --><s:text name="enterprise.system.user.phone" /></td><td><input type="text" name="workphone"/></td>
			</tr>
			<tr>
				<td  class="pn-flabel"><!-- 传真号码 --><s:text name="enterprise.system.user.fax" /></td><td><input type="text" name="fax"/></td>
			</tr>
			
			<tr>
				<td  class="pn-flabel"><!-- 联系E-mail --><s:text name="enterprise.system.user.email" /></td><td><input type="text" name="email"/></td>
			</tr>
			<tr>
				<td class="pn-flabel"><!-- 移动电话 --><s:text name="enterprise.system.user.mobile" /></td><td><input type="text" name="mobile"/></td>
			</tr>
			<tr>
				<td class="pn-flabel"><!-- 状态 --><s:text name="common.word.status" /></td>
				<td>
					<select name="state">
						<option value="1"><!-- 已启用 --><s:text name="common.word.has.opened" /></option>
						<option value="0"><font color="red"><!-- 已停用 --><s:text name="common.word.has.closed" /></font></option>
					</select>
				</td>
			</tr>
			<tr>
				<td></td>
				<td  align="center" align="center">
				<input type="submit" id="submitButton" value='<s:text name="common.word.submit" />' />
				<input type="button" value='<s:text name="common.word.return.back" />' onclick="history.back()"/>
			
				</td>
			</tr>
	 	</table>
	 	<s:token></s:token>
	</form>
	</body>
</html>

