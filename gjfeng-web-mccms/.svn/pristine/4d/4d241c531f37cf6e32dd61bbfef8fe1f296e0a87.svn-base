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
  addressInit('cmbProvince', 'cmbArea', 'county', '<s:text name="common.word.guangdong" />', '${tusers.area}', '${tusers.county}'); 		
		$("#name").focus();
		$("#userForm").validate({
			 rules: { 
				name: { 
        			required: true, 
        			maxlength:20
    			},
    			area: {
    				required: true,
    				maxlength:12
    			},
    			workunit: {
    				required: true,
    				maxlength:50
    			},    			
    			workphone:
    			{
    				isPhoneCall: true,
    				maxlength:50
    			},
    			address:{
    				required: true,
    				maxlength:30
    			},
    			idcardno: {
                    isidcard: true , 
        			maxlength:30
    			},
            	email: {
                    email: true , 
        			maxlength:25
    			},
        	    fax:
        	    {
        	    	isPhoneCall: true,
        	    	maxlength:25
				},
            	mobile: {
                    isPhoneCall: true,
			        maxlength:50
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
 	 	   	<!-- 当前位置: 用户管理 --><s:text name="enterprise.system.user.current.location" /> - 
			<!-- 编辑用户信息 --><s:text name="enterprise.system.user.current.location.edit" />
 	 	</div>
 	 	<div class="ropt"><input type="button" value='<s:text name="common.word.return.back" />' onclick="history.back()"/></div>
    	<div class="clear"></div>
  	</div>
		<form action="usersAction!updateInfo.action" method="post" id="userForm"
		 name="userForm"  enctype="multipart/form-data">
			<input type="hidden" name="id" value="${tusers.id}"/>
		<table  align="center" class="listTable3" width="90%">
			<tr>
				<td class="pn-flabel"  width="100px"><!-- 用户名 --><s:text name="enterprise.system.user.name" /></td><td>${tusers.loginName}</td>
			</tr>
			<tr>
				<td class="pn-flabel"><!-- 真实名字 --><s:text name="enterprise.system.user.real.name" /></td><td><input type="text" name="name" 
				value="${tusers.name}"/></td>
			</tr>
			<tr>
			  <td class="pn-flabel"><!-- 所属角色 --><s:text name="enterprise.system.user.belong.to.role" /></td>
			  <td>
					<c:forEach var="usersRolesList" items="${usersRolesList}">
									${usersRolesList.roles.name}
										&nbsp;&nbsp;
									
					</c:forEach>
			  </td>
			</tr>
			
			 <!-- <tr>
				<td class="pn-flabel">性别</td>
				<td>
				  <select name="sex">
						<option value="1">男</option>
						<option value="0" 
						<c:if test="${tusers.sex==0}">selected</c:if>>
							女
						</option>
					</select> 
				</td>
			</tr>-->
			<tr>
				<td class="pn-flabel"><!-- 地区 --><s:text name="enterprise.system.user.area" /></td><td><input type="text" name="area" value="${tusers.area}" readonly="readonly"/></td>
			</tr>
			<tr>
				<td class="pn-flabel"><!-- 区/县 --><s:text name="enterprise.system.user.region" /></td>
				<td>
					<select id="cmbProvince" disabled></select><!-- 省 --><s:text name="enterprise.system.user.province" />&nbsp;&nbsp;
					<select id="cmbArea"    disabled></select><!-- 市 --><s:text name="enterprise.system.user.city" />&nbsp; &nbsp;
					<select id="county"  disabled></select><!-- 区/县 --><s:text name="enterprise.system.user.region" />&nbsp;&nbsp;
					<input type="hidden" name="county" value="${tusers.county}"/>
				</td>
			</tr>			
			<tr>
				<td class="pn-flabel"><!-- 地址 --><s:text name="enterprise.system.user.address" /></td><td><input type="text" name="address" value="${tusers.address}" /></td>
			</tr>
			<tr>
				<td class="pn-flabel"><!-- 工作单位 --><s:text name="enterprise.system.user.work.company" /></td><td><input type="text" name="workunit" value="${tusers.workunit}" readonly="readonly"/></td>
			</tr>
			<tr>
				<td class="pn-flabel"><!-- 联系电话 --><s:text name="enterprise.system.user.phone" /></td><td><input type="text" name="workphone" value="${tusers.workphone}"/></td>
			</tr>
			<tr>
				<td class="pn-flabel"><!-- 传真号码 --><s:text name="enterprise.system.user.fax" /></td><td><input type="text" name="fax" value="${tusers.fax}"/></td>
			</tr>
			<tr>
				<td class="pn-flabel"><!-- 联系E-mail --><s:text name="enterprise.system.user.email" /></td><td><input type="text" name="email" value="${tusers.email}"/></td>
			</tr>
			<tr>
				<td class="pn-flabel"><!-- 移动电话 --><s:text name="enterprise.system.user.mobile" /></td><td><input type="text" name="mobile" value="${tusers.mobile}"/></td>
			</tr>

			<tr>
				<td></td>
				<td >
				<input type="submit" value='<s:text name="common.word.submit" />' />
				</td>
			</tr>
	 	</table>
	 	<s:token></s:token>
	</form>
	</body>
</html>

