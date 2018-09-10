<%--

	gjfMemberInfo_edit.jsp

	Create by MCGT

	Create time 2017-01-10

	Version 1.0

	Copyright 2013 Messcat, Inc. All rights reserved.

 --%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>更新模板</title>
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
			var userAgent = "IE";
			
			if(window.navigator.userAgent.indexOf("Chrome")>=1){
				userAgent = "Chrome";
			}
			
			//当选择图片时发生
			function doSelectPhotoChanged(){
				filePath = document.getElementById("uploadImage").value;
				if(checkFileUpload(filePath)){					
					if(userAgent != "Chrome"){	
						  document.getElementById("uploadImage").value = "";
						  document.getElementById("imgShowTeacherPhoto").src=filePath;
						  document.getElementById("imgchange").rel=$("#imgShowTeacherPhoto").attr("src");
				 	}
					
				}
			}
			
			//图片上传验证
			function checkFileUpload()
			{
			  var path = $("#upload").attr("value");
			  if(userAgent != "Chrome"){
			  	   if(path.indexOf(".jpg")<1 && path.indexOf(".gif")<1 &&  path.indexOf(".bmp")<1 &&  path.indexOf(".png")<1 && path.indexOf(".jpeg")<1){
			  	   		//图片类型不正确，请上传正确的格式！
			  	   		alert('<s:text name="enterprise.collection.message.phototype.error" />\n(jpg,gif,bmp,png,jpeg)');
			  	   		$("#uploadImage").attr("value","");
				   		return false;
			  	   }
			  }else{	
				  if(!sy_FilterFile(path,"jpg,gif,bmp,png,jpeg")){
				    //图片类型不正确，请上传正确的格式！
			  	   	alert('<s:text name="enterprise.collection.message.phototype.error" />\n(jpg,gif,bmp,png,jpeg)');
				    $("#uploadImage").attr("value","");
				    return false;
				  }
				  if(sy_GetImageToGetSize(path).fileSize>1024000){
				    //上传图片大小不能够超过1MB！
				    alert('<s:text name="enterprise.collection.message.photosize.error" />');
				    $("#uploadImage").attr("value","");
				    return false;
				  }
			   }
			  return true;
			}
			
			//圖片上傳
			
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">当前位置: 会员管理- 编辑会员</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="location.href='${ctx}/subsystem/memberInfoAction!findAllMember.action'"/></div>
		</div>

		<form action="memberInfoAction!updateMemberInfo.action" method="post" id="editMemberInfoForm" name="editMemberInfoForm" enctype="multipart/form-data">
			<table  align="center" class="listTable3">
				<input type="hidden" name="id" value="${member.id}"/>
				<input type="hidden" name="token" value="${member.token}"/>  
				<tr>
					<td class="pn-flabel" width="100px">用户微信号</td>
					<td><input type="text" id="unionId" disabled="disabled" name="unionId" value="${member.unionId}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户名称</td>
					<td><input type="text" id="name" name="name" value="${member.name}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户账号（手机号码）</td>
					<td><input type="text" id="mobile" name="mobile" readonly="readonly" value="${member.mobile}" /></td>
				</tr>
				
				<tr>
					<td class="pn-flabel" width="100px">用户昵称</td>
					<td><input type="text" id="nickName" name="nickName" value="${member.nickName}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户性别</td>
					<td>
					   <select name="sex">
					      <c:if test="${member.sex==0}">
					          <option selected="selected" value="0">未知</option>
					          <option value="1">男</option>
					          <option value="2">女</option>
					      </c:if>
					      <c:if test="${member.sex==1}">
					          <option value="0">未知</option>
					          <option selected="selected" value="1">男</option>
					          <option value="2">女</option>
					      </c:if>
					      <c:if test="${member.sex==2}">
					           <option  value="0">未知</option>
					          <option  value="1">男</option>
					          <option selected="selected"  value="2">女</option>
					      </c:if>
					   </select>
					</td>					
				</tr> 
				<tr>
					<td class="pn-flabel" width="100px">用户邮箱</td>
					<td><input type="text" id="email" name="email" value="${member.email}" /></td>
				</tr>
				 <tr>
					<td class="pn-flabel" width="100px">用户身份证</td>
					<td><input type="text" id="idCard" name="idCard" value="${member.idCard}" /></td>
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
									class="screenshot" rel="doSelectPhotoChanged()"><img
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
								<br />
								<input type="file" id="uploadImage" name="uploadImage" value=""
									onchange="doSelectPhotoChanged();"  size="30"/>
																	
					</td>
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
				<c:if test="${member.isReadName==0}">
				 <tr>
					<td class="pn-flabel" width="100px">用户推荐人姓名</td>
					<td>
						<input type="text" disabled="disabled" value="${superName}"/>
					</td>
				 </tr>
				 <tr>
					<td class="pn-flabel" width="100px">用户推荐人电话</td>
					<td>
					  <input type="text" disabled="disabled" value="${superMobile}"/>						
					</td>
				 </tr>
				</c:if>
				<c:if test="${member.isReadName==1}">
					 <tr>
						<td class="pn-flabel" width="100px">用户推荐人</td>
						<td>
							<input type="text" disabled="disabled" value="${superName}
							<c:if test="${not empty superMobile}">(${superMobile})</c:if>						
							"/>
						</td>
					 </tr>				 
				</c:if>
				<%-- <tr>
					<td class="pn-flabel" width="100px">用户推荐人</td>
					<td>
						<select name="superId">
							<c:if test="${not empty superId }">
								<option value="${superId }">${superName }</option>
							</c:if>
							<c:forEach items="${superiors }" var="bean">
								<option value="${bean.id }">${bean.name }</option>
							</c:forEach>
						</select>
					</td>
				</tr> --%>
				<%-- <tr>
					<td class="pn-flabel" width="100px">用户所属省份</td>
					<td><input type="text" id="proviceId" name="proviceId.proviceId" value="${member.proviceId.province}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户所属城市</td>
					<td><input type="text" id="cityId" name="cityId.city" value="${member.cityId.city}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户所属区县</td>
					<td><input type="text" id="areaId" name="areaId.area" value="${member.areaId.area}" /></td>
				</tr>  --%>
				<tr>
					<td class="pn-flabel" width="100px">用户详细地址</td>
					<td><input type="text" id="adrress" name="adrress" value="${member.adrress}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户简介</td>
					<td><input type="text" id="remark" name="remark" value="${member.remark}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户余额</td>
					<td><input type="text"  disabled="disabled" id="balanceMoney" name="balanceMoney" value="${member.balanceMoney}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户待领消费金额</td>
					<td><input type="text" disabled="disabled" id="consumptionMoney" name="consumptionMoney" value="${member.consumptionMoney}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户累计消费金额</td>
					<td><input type="text" id="cumulativeMoney" name="cumulativeMoney" disabled="disabled" value="${member.cumulativeMoney}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户可提现额</td>
					<td><input type="text" id="withdrawalMoney" disabled="disabled" name="withdrawalMoney" value="${member.withdrawalMoney}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户分红剩余计算金额</td>
					<td><input type="text" id="dividendsMoneyBla" name="dividendsMoneyBla" disabled="disabled" value="${member.dividendsMoneyBla}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户累计分红金额</td>
					<td><input type="text" id="dividendsTotalMoney" name="dividendsTotalMoney" disabled="disabled" value="${member.dividendsTotalMoney}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户分红权个数</td>
					<td><input type="text" id="dividendsNum" name="dividendsNum" disabled="disabled" value="${member.dividendsNum}" /></td>
				</tr>
				
				<tr>
					<td class="pn-flabel" width="100px">是否实名认证</td>
					<td>
					   <%-- <select name="isReadName">
					     <c:if test="${member.isReadName==0}">
					        <option value="0" selected="selected">否</option>
					        <option value="1">是</option>
					     </c:if>
					     <c:if test="${member.isReadName==1}">
					        <option value="0" >否</option>
					        <option value="1" selected="selected">是</option>
					     </c:if>
					   </select> --%>
					   <c:if test="${member.isReadName eq '1'}">
					        <input type="text" value="是" disabled="disabled"/>					        
					    </c:if>
					    
					    <c:if test="${member.isReadName eq '0'}">
					        <input type="text" value="否" disabled="disabled"/>					        
					    </c:if>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户类型</td>
					<td>
					 <select name="type">
					     <c:if test="${member.type==0}">
					        <option value="0" selected="selected">普通用户</option>
					        <option value="1">商家</option>
					     </c:if>
					     <c:if test="${member.type==1}">
					        <option value="0" >普通用户</option>
					        <option value="1" selected="selected">商家</option>
					     </c:if>
					  </select>
					</td>
				</tr>
				<%-- <tr>
					<td class="pn-flabel" width="100px">用户身份</td>
					<td>
					 <select name="identity">
					     <c:if test="${member.identity==0}">
					        <option value="0" selected="selected">普通用户</option>
					        <option value="1">个代</option>
					        <option value="2">区代</option>
					        <option value="3">市代</option>
					     </c:if>
					     <c:if test="${member.identity==1}">
					        <option value="0">普通用户</option>
					        <option value="1"  selected="selected">个代</option>
					        <option value="2">区代</option>
					        <option value="3">市代</option>
					     </c:if>
					      <c:if test="${member.identity==2}">
					        <option value="0">普通用户</option>
					        <option value="1"  >个代</option>
					        <option value="2" selected="selected">区代</option>
					        <option value="3">市代</option>
					     </c:if>
					     <c:if test="${member.identity==3}">
					        <option value="0">普通用户</option>
					        <option value="1"  >个代</option>
					        <option value="2" >区代</option>
					        <option value="3" selected="selected">市代</option>
					     </c:if>
					  </select>	 				
					</td>
				</tr> --%>
				<tr>
					<td class="pn-flabel" width="100px">是否可以举报</td>
					<td>
					<select id="isReport">
					     <c:if test="${member.isReport==0}">
					        <option value="0" selected="selected">否</option>
					        <option value="1">是</option>
					     </c:if>
					     <c:if test="${member.isReport==1}">
					        <option value="0" >否</option>
					        <option value="1" selected="selected">是</option>
					     </c:if>
					   </select>			
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">是否可以购买商品</td>
					<td>
					<select name="isBuy">
					     <c:if test="${member.isBuy==0}">
					        <option value="0" selected="selected">否</option>
					        <option value="1">是</option>
					     </c:if>
					     <c:if test="${member.isBuy==1}">
					        <option value="0" >否</option>
					        <option value="1" selected="selected">是</option>
					     </c:if>
					   </select>	
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">是否可以接收站内信</td>
					<td>
					<select name="isMessage">
					     <c:if test="${member.isMessage==0}">
					        <option value="0" selected="selected">否</option>
					        <option value="1">是</option>
					     </c:if>
					     <c:if test="${member.isMessage==1}">
					        <option value="0" >否</option>
					        <option value="1" selected="selected">是</option>
					     </c:if>
					  </select>	
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">是否可以评论</td>
					<td>
					 <select name="isComments">
					     <c:if test="${member.isComments==0}">
					        <option value="0" selected="selected">否</option>
					        <option value="1">是</option>
					     </c:if>
					     <c:if test="${member.isComments==1}">
					        <option value="0" >否</option>
					        <option value="1" selected="selected">是</option>
					     </c:if>
					  </select>	
					</td>
				</tr>
				<tr>


					<td class="pn-flabel" width="100px">是否可以分红</td>
					<td>
					 <select name="isDivi">
					     <c:if test="${member.isDivi==0}">
					        <option value="0" selected="selected">否</option>
					        <option value="1">是</option>
					     </c:if>
					     <c:if test="${member.isDivi==1}">
					        <option value="0" >否</option>
					        <option value="1" selected="selected">是</option>
					     </c:if>
					  </select>	
					</td>
				</tr>
				<tr>

					<td class="pn-flabel" width="100px">用户状态</td>
					<td>
					  <select name="status">
					     <c:if test="${member.status==0}">
					        <option value="0" selected="selected">已停用</option>
					        <option value="1">已启用</option>
					     </c:if>
					     <c:if test="${member.status==1}">
					        <option value="0" >已停用</option>
					        <option value="1" selected="selected">已启用</option>
					     </c:if>
					  </select>	
				   </td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">实名认证状态</td>
					<td>
					  <select name="realNameState">
						<option value="0" <c:if test="${member.realNameState eq '0'}">selected="selected"</c:if>>未提交审核</option>
				        <option value="1" <c:if test="${member.realNameState eq '1'}">selected="selected"</c:if>>审核中</option>
				        <option value="2" <c:if test="${member.realNameState eq '2'}">selected="selected"</c:if>>审核成功</option>
				        <option value="3" <c:if test="${member.realNameState eq '3'}">selected="selected"</c:if>>审核失败</option>		
					 </select>

				   </td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户等级</td>
					<td>
					  <select name="isActivityMember">
						<option value="0" <c:if test="${member.isActiveMember eq '0'}">selected="selected"</c:if>>一星</option>
				        <option value="1" <c:if test="${member.isActiveMember eq '1'}">selected="selected"</c:if>>二星</option>
				        <option value="2" <c:if test="${member.isActiveMember eq '2'}">selected="selected"</c:if>>三星</option>
				        <option value="3" <c:if test="${member.isActiveMember eq '3'}">selected="selected"</c:if>>四星</option>		
					 </select>

				   </td>
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