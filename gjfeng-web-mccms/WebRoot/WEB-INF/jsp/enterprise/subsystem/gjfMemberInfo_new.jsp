<%--

	gjfMemberInfo_new.jsp

	Create by MCGT

	Create time 2017-01-10

	Version 1.0

	Copyright 2013 Messcat, Inc. All rights reserved.

 --%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>添加模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<script>
			$(document).ready(function(){
				$("#gjfMemberInfoForm").validate({
					 rules: { 
			
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
			<div class="rpos">当前位置: 模块 - 子模块</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="location.href='${ctx}/subsystem/gjfMemberInfoAction!retrieveAllGjfMemberInfos.action'"/></div>
		</div>

		<form action="gjfMemberInfoAction!newGjfMemberInfo.action" method="post" id="gjfMemberInfoForm" name="gjfMemberInfoForm" enctype="multipart/form-data">
			<table  align="center" class="listTable3">
				<tr>
					<td class="pn-flabel" width="100px">用户微信号unionid</td>
					<td><input type="text" id="unionId" name="gjfMemberInfo.unionId" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户名称</td>
					<td><input type="text" id="name" name="gjfMemberInfo.name" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户账号（手机号码）</td>
					<td><input type="text" id="mobile" name="gjfMemberInfo.mobile" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户密码</td>
					<td><input type="text" id="password" name="gjfMemberInfo.password" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户支付密码</td>
					<td><input type="text" id="payPassword" name="gjfMemberInfo.payPassword" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户昵称</td>
					<td><input type="text" id="nickName" name="gjfMemberInfo.nickName" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户性别（0:未知 1:男 2:女）</td>
					<td><input type="text" id="sex" name="gjfMemberInfo.sex" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户邮箱</td>
					<td><input type="text" id="email" name="gjfMemberInfo.email" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户身份证</td>
					<td><input type="text" id="idCard" name="gjfMemberInfo.idCard" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户身份证正面</td>
					<td><input type="text" id="imgIdCardBeforeUrl" name="gjfMemberInfo.imgIdCardBeforeUrl" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户身份证反面</td>
					<td><input type="text" id="imgIdCardBehindUrl" name="gjfMemberInfo.imgIdCardBehindUrl" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户头像</td>
					<td><input type="text" id="imgHeadUrl" name="gjfMemberInfo.imgHeadUrl" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户二维码</td>
					<td><input type="text" id="imgQrUrl" name="gjfMemberInfo.imgQrUrl" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户推荐人（上级，平台推荐默认为0）</td>
					<td><input type="text" id="superId" name="gjfMemberInfo.superId" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户所属省份</td>
					<td><input type="text" id="proviceId" name="gjfMemberInfo.proviceId" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户所属城市</td>
					<td><input type="text" id="cityId" name="gjfMemberInfo.cityId" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户所属区县</td>
					<td><input type="text" id="areaId" name="gjfMemberInfo.areaId" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户详细地址</td>
					<td><input type="text" id="adrress" name="gjfMemberInfo.adrress" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户简介</td>
					<td><input type="text" id="remark" name="gjfMemberInfo.remark" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户余额</td>
					<td><input type="text" id="balanceMoney" name="gjfMemberInfo.balanceMoney" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户待领消费金额</td>
					<td><input type="text" id="consumptionMoney" name="gjfMemberInfo.consumptionMoney" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户累计消费金额</td>
					<td><input type="text" id="cumulativeMoney" name="gjfMemberInfo.cumulativeMoney" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户（商家）累计让利金额</td>
					<td><input type="text" id="benefitMoney" name="gjfMemberInfo.benefitMoney" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户可提现额</td>
					<td><input type="text" id="withdrawalMoney" name="gjfMemberInfo.withdrawalMoney" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户分红金额</td>
					<td><input type="text" id="dividendsMoney" name="gjfMemberInfo.dividendsMoney" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户分红剩余计算金额</td>
					<td><input type="text" id="dividendsMoneyBla" name="gjfMemberInfo.dividendsMoneyBla" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户累计分红金额</td>
					<td><input type="text" id="dividendsTotalMoney" name="gjfMemberInfo.dividendsTotalMoney" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户分红权个数</td>
					<td><input type="text" id="dividendsNum" name="gjfMemberInfo.dividendsNum" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户注册时间</td>
					<td><input type="text" id="addTime" name="gjfMemberInfo.addTime" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户编辑时间</td>
					<td><input type="text" id="editTime" name="gjfMemberInfo.editTime" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户最后登录时间</td>
					<td><input type="text" id="lastLoginTime" name="gjfMemberInfo.lastLoginTime" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户是否实名认证（0:否 1:是）</td>
					<td><input type="text" id="isReadName" name="gjfMemberInfo.isReadName" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户类型（0:普通用户 1:商家）</td>
					<td><input type="text" id="type" name="gjfMemberInfo.type" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户身份（0:普通用户 1:个代 2:区代 3:市代）</td>
					<td><input type="text" id="identity" name="gjfMemberInfo.identity" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户是否可以举报（0:否 1:是）</td>
					<td><input type="text" id="isReport" name="gjfMemberInfo.isReport" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户是否可以购买商品（0:否 1:是）</td>
					<td><input type="text" id="isBuy" name="gjfMemberInfo.isBuy" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户是否可以接收站内信（0:否 1:是）</td>
					<td><input type="text" id="isMessage" name="gjfMemberInfo.isMessage" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户是否可以评论（0:否 1:是）</td>
					<td><input type="text" id="isComments" name="gjfMemberInfo.isComments" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户状态(0:已停用 1:已启用)</td>
					<td><input type="text" id="status" name="gjfMemberInfo.status" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户删除状态(0:已删除 1:正常)</td>
					<td><input type="text" id="isDel" name="gjfMemberInfo.isDel" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">安全token</td>
					<td><input type="text" id="token" name="gjfMemberInfo.token" /></td>
				</tr>

				<tr>
					<td></td>
					<td>
						<input type="submit" class="defaultButton" value=" 提 交 "/>
					</td>
				</tr>
		 	</table>
		</form>
	</body>
</html>