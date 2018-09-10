<%--

	gjfMemberInfo_view.jsp

	Create by MCGT

	Create time 2017-01-10

	Version 1.0

	Copyright 2013 Messcat, Inc. All rights reserved.

 --%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>查看模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<script>
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">当前位置: 會員管理- 查看</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="history.back();"/></div>
		</div>

		<table  align="center" class="listTable3">
				<tr>
					<td class="pn-flabel" width="100px">用户微信号</td>
					<td>${member.unionId}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户名称</td>
					<td>${member.name}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户账号（手机号码）</td>
					<td>${member.mobile}</td>
				</tr>
				<%-- <tr>
					<td class="pn-flabel" width="100px">用户密码</td>
					<td>${gjfMemberInfo.password}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户支付密码</td>
					<td>${gjfMemberInfo.payPassword}</td>
				</tr> --%>
				<tr>
					<td class="pn-flabel" width="100px">用户昵称</td>
					<td>${member.nickName}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户性别（</td>
					<c:if test="${member.sex==0}">
					  <td>未知</td>
					</c:if>
					<c:if test="${member.sex==1}">
					  <td>男</td>
					</c:if>
					<c:if test="${member.sex==2}">
					  <td>女</td>
					</c:if>
					
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户邮箱</td>
					<td>${member.email}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户身份证</td>
					<td>${member.idCard}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户身份证正面</td>
					
					<td>
					<a href="${member.imgIdCardBeforeUrl}"
									target="_blank" name="imgchange" id="imgchange"
									class="screenshot" ><img
										id="imgShowTeacherPhoto" name="imgShowTeacherPhoto" border="0"
										width="96"
										src="
											<c:choose>
												<c:when test="${empty member.imgIdCardBeforeUrl}">
														${ctx}/upload/NoExpertPhoto.JPG
												</c:when>
												<c:otherwise>
													${member.imgIdCardBeforeUrl}
												</c:otherwise>
											</c:choose>
											"/>
								</a>
					
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户身份证反面</td>
					<td>	
					<a href="${member.imgIdCardBehindUrl}"
									target="_blank" name="imgchange" id="imgchange"
									class="screenshot" ><img
										id="imgShowTeacherPhoto" name="imgShowTeacherPhoto" border="0"
										width="96"
										src="
											<c:choose>
												<c:when test="${empty member.imgIdCardBehindUrl}">
														${ctx}/upload/NoExpertPhoto.JPG
												</c:when>
												<c:otherwise>
													${member.imgIdCardBehindUrl}
												</c:otherwise>
											</c:choose>
											"/>
								</a>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户手持省份证图片</td>
					<td>
					<a href="${member.imgIdCardHandheldUrl}"
									target="_blank" name="imgchange" id="imgchange"
									class="screenshot" ><img
										id="imgShowTeacherPhoto" name="imgShowTeacherPhoto" border="0"
										width="96"
										src="
											<c:choose>
												<c:when test="${empty member.imgIdCardHandheldUrl}">
														${ctx}/upload/NoExpertPhoto.JPG
												</c:when>
												<c:otherwise>
													${member.imgIdCardHandheldUrl}
												</c:otherwise>
											</c:choose>
											"/>
								</a>
					  
					</td>
				</tr> 
				<tr>
					<td class="pn-flabel" width="100px">用户头像</td>
					<td>
					<a href="${member.imgHeadUrl}"
									target="_blank" name="imgchange" id="imgchange"
									class="screenshot" ><img
										id="imgShowTeacherPhoto" name="imgShowTeacherPhoto" border="0"
										width="96"
										src="
											<c:choose>
												<c:when test="${empty member.imgHeadUrl}">
														${ctx}/upload/NoExpertPhoto.JPG
												</c:when>
												<c:otherwise>
													${member.imgHeadUrl}
												</c:otherwise>
											</c:choose>
											"/>
								</a>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户二维码</td>
					<td>
					<a href="${member.imgQrUrl}"
									target="_blank" name="imgchange" id="imgchange"
									class="screenshot" ><img
										id="imgShowTeacherPhoto" name="imgShowTeacherPhoto" border="0"
										width="96"
										src="
											<c:choose>
												<c:when test="${empty member.imgQrUrl}">
														${ctx}/upload/NoExpertPhoto.JPG
												</c:when>
												<c:otherwise>
													${member.imgQrUrl}
												</c:otherwise>
											</c:choose>
											"/>
								</a>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户推荐人</td>
					<td>${superName}</td>
				</tr>
				<c:if test="${not empty member.proviceId}">
					<tr>
						<td class="pn-flabel" width="100px">用户所属省份</td>
						<td>${member.proviceId.province}</td>
					</tr>
				</c:if>
				<c:if test="${not empty member.cityId}">
					<tr>
						<td class="pn-flabel" width="100px">用户所属城市</td>
						<td>${member.cityId.city}</td>
					</tr>
				</c:if>
				<c:if test="${not empty member.areaId}">
					<tr>
						<td class="pn-flabel" width="100px">用户所属区县</td>
						<td>${member.areaId.area}</td>
					</tr>
				</c:if>
				<tr>
					<td class="pn-flabel" width="100px">用户详细地址</td>
					<td>${member.adrress}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户简介</td>
					<td>${member.remark}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户余额</td>
					<td>${member.balanceMoney}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户待领消费金额</td>
					<td>${member.consumptionMoney}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户累计消费金额</td>
					<td>${member.cumulativeMoney}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户可提现额</td>
					<td>${member.withdrawalMoney}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户分红剩余计算金额</td>
					<td>${member.dividendsMoneyBla}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户累计分红金额</td>
					<td>${member.dividendsTotalMoney}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户分红权个数</td>
					<td>${member.dividendsNum}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户直推会员分红总金额</td>
					<td>${member.directMemberTotalMoney}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户直推商家分红总金额</td>
					<td>${member.directMerchantsTotalMoney}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户当前未结算代理金额</td>
					<td>${member.agentMoney}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户总获取代理金额</td>
					<td>${member.agentTotalMoney}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户责任消费险金额</td>
					<td>${member.insuranceMoney}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户注册时间</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${member.addTime}"/></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户编辑时间</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${member.editTime}"/></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户最后登录时间</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${member.lastLoginTime}"/></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户关系绑定时间</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${member.bindTime}"/></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户是否实名认证</td>
					<c:if test="${member.isReadName==0}">
					     <td>否</td>
					</c:if>
					<c:if test="${member.isReadName==1}">
					     <td>是</td>
					</c:if>
					
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户类型</td>
					<c:if test="${member.type==0}">
					   <td>普通用户</td>
					</c:if>
					<c:if test="${member.type==1}">
					   <td>商家</td>
					</c:if>
					
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户身份</td>
					<c:if test="${member.identity==0}">
					   <td>普通用户</td>
					</c:if>
					<c:if test="${member.identity==1}">
					   <td>个代</td>
					</c:if>
					<c:if test="${member.identity==2}">
					   <td>区代</td>
					</c:if>
					<c:if test="${member.identity==3}">
					   <td>市代</td>
					</c:if>
					
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户是否可以举报</td>
					<c:if test="${member.isReport==0}">
					   <td>否</td>
					</c:if>
					<c:if test="${member.isReport==1}">
					   <td>是</td>
					</c:if>					
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户是否可以购买商品</td>
					<c:if test="${member.isBuy==0}">
					   <td>否</td>
					</c:if>
					<c:if test="${member.isBuy==1}">
					   <td>是</td>
					</c:if>					
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户是否可以接收站内信</td>
					<c:if test="${member.isMessage==0}">
					   <td>否</td>
					</c:if>
					<c:if test="${member.isMessage==1}">
					   <td>是</td>
					</c:if>							
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户是否可以评论</td>
					<c:if test="${member.isComments==0}">
					   <td>否</td>
					</c:if>
					<c:if test="${member.isComments==1}">
					   <td>是</td>
					</c:if>						
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户状态</td>
					<c:if test="${member.status==0}">
					   <td>已停用</td>
					</c:if>
					<c:if test="${member.status==1}">
					   <td>已启用</td>
					</c:if>						
				</tr>
				<%-- <tr>
					<td class="pn-flabel" width="100px">用户删除状态(0:已删除 1:正常)</td>
					<td>${gjfMemberInfo.isDel}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">安全token</td>
					<td>${gjfMemberInfo.token}</td>
				</tr> --%>

		 </table>
	</body>
</html>