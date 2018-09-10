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
				  var path = $("#uploadImage").attr("value");
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
				
				
			});
			
			//圖片上傳
			
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">当前位置: 会员管理- 审核会员</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="location.href='${ctx}/subsystem/memberInfoAction!findAllMember.action'"/></div>
		</div>

		<form action="memberInfoAction!updateMemberAuditState.action" method="post" id="editMemberInfoForm" name="editMemberInfoForm" enctype="multipart/form-data">
			<table  align="center" class="listTable3">
				 <input type="hidden" name="mobile" value="${member.mobile}"/> 
				<tr>
					<td class="pn-flabel" width="100px">用户微信号unionid</td>
					<td><input type="text" id="unionId" name="unionId" disabled="disabled" value="${member.unionId}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户名称</td>
					<td><input type="text" id="name" name="name" disabled="disabled" value="${member.name}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户账号（手机号码）</td>
					<td><input type="text" id="mobile"  disabled="disabled" value="${member.mobile}" /></td>
				</tr>
				
				<tr>
					<td class="pn-flabel" width="100px">用户昵称</td>
					<td><input type="text" id="nickName" name="nickName" value="${member.nickName}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户性别</td>
					<td>
					
					      <c:if test="${member.sex==0}">
					        <input type="text" disabled="disabled" value="未知" />
					          
					      </c:if>
					      <c:if test="${member.sex==1}">
					          <input type="text" disabled="disabled" value="男" />					         
					      </c:if>
					      <c:if test="${member.sex==2}">
					         <input type="text" disabled="disabled" value="女" />						         
					      </c:if>
					   
					</td>					
				</tr> 
				<tr>
					<td class="pn-flabel" width="100px">用户邮箱</td>
					<td><input type="text" id="email" name="email" disabled="disabled" value="${member.email}" /></td>
				</tr>
				 <tr>
					<td class="pn-flabel" width="100px">用户身份证</td>
					<td><input type="text" id="idCard" name="idCard" disabled="disabled" value="${member.idCard}" /></td>
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
					<td class="pn-flabel" width="100px">用户手持身份证</td>
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
								<!-- <br />
								<input type="file" id="file"  id="upload" name="upload" value=""
									onchange="doSelectPhotoChanged();"  size="30"/> -->
																	
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
				
				<tr>
					<td class="pn-flabel" width="100px">用户所属省份</td>
					<td><input type="text" id="proviceId" name="proviceId.proviceId" disabled="disabled" value="${member.proviceId.province}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户所属城市</td>
					<td><input type="text" id="cityId" name="cityId.city" disabled="disabled" value="${member.cityId.city}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户所属区县</td>
					<td><input type="text" id="areaId" name="areaId.area" disabled="disabled" value="${member.areaId.area}" /></td>
				</tr> 
				<tr>
					<td class="pn-flabel" width="100px">用户详细地址</td>
					<td><input type="text" id="adrress" name="adrress" disabled="disabled" value="${member.adrress}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户简介</td>
					<td><input type="text" id="remark" name="remark" disabled="disabled" value="${member.remark}" /></td>
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
				<%-- <tr>
					<td class="pn-flabel" width="100px">用户（商家）累计让利金额</td>
					<td><input type="text" id="benefitMoney" name="benefitMoney" disabled="disabled" value="${member.benefitMoney}" /></td>
				</tr> --%>
				<tr>
					<td class="pn-flabel" width="100px">用户可提现额</td>
					<td><input type="text" id="withdrawalMoney" disabled="disabled" name="withdrawalMoney" value="${member.withdrawalMoney}" /></td>
				</tr>
				<%-- <tr>
					<td class="pn-flabel" width="100px">用户分红金额</td>
					<td><input type="text" id="dividendsMoney" name="dividendsMoney" disabled="disabled" value="${member.dividendsMoney}" /></td>
				</tr> --%>
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
					<td class="pn-flabel" width="100px">用户是否实名认证</td>
					<td>
					     <c:if test="${member.isReadName==0}">
					       <input type="text" disabled="disabled" value="否" />	   
					     </c:if>
					     <c:if test="${member.isReadName==1}">
					        <input type="text" disabled="disabled" value="是" />					      
					     </c:if>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户类型</td>
					<td>
					     <c:if test="${member.type==0}">
					        <input type="text" disabled="disabled" value="普通用户" />					       
					     </c:if>
					     <c:if test="${member.type==1}">
					        <input type="text" disabled="disabled" value="商家" />						        
					     </c:if>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户身份</td>
					<td>

					     <c:if test="${member.identity==0}">
					       <input type="text" disabled="disabled" value="普通用户" />
					        
					     </c:if>
					     <c:if test="${member.identity==1}">
					       <input type="text" disabled="disabled" value="个代" />					        
					     </c:if>
					      <c:if test="${member.identity==2}">
					        <input type="text" disabled="disabled" value="区代" />						        
					     </c:if>
					     <c:if test="${member.identity==3}">
					        <input type="text" disabled="disabled" value="市代" />					       					       
					     </c:if>
				
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户是否可以举报</td>
					<td>
					     <c:if test="${member.isReport==0}">
					       <input type="text" disabled="disabled" value="否" />					       
					     </c:if>
					     <c:if test="${member.isReport==1}">
					       <input type="text" disabled="disabled" value="是" />					        
					     </c:if>					 			
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户是否可以购买商品</td>
					<td>

					     <c:if test="${member.isBuy==0}">
					        <input type="text" disabled="disabled" value="否" />					        
					     </c:if>
					     <c:if test="${member.isBuy==1}">
					        <input type="text" disabled="disabled" value="是" />					        
					     </c:if>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户是否可以接收站内信</td>
					<td>
					     <c:if test="${member.isMessage==0}">
					        <input type="text" disabled="disabled" value="否" />	
					     </c:if>
					     <c:if test="${member.isMessage==1}">
					        <input type="text" disabled="disabled" value="是" />	
					     </c:if>

					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户是否可以评论</td>
					<td>
					     <c:if test="${member.isComments==0}">
					        <input type="text" disabled="disabled" value="否" />
					     </c:if>
					     <c:if test="${member.isComments==1}">
					         <input type="text" disabled="disabled" value="是" />	
					     </c:if>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户状态</td>
					<td>
					     <c:if test="${member.status==0}">
					        <input type="text" disabled="disabled" value="已停用" />	
					        <input type="hidden" name="token" value="${member.token}"/>					       
					     </c:if>
					     <c:if test="${member.status==1}">
					        <input type="text" disabled="disabled" value="已启用" />					       
					     </c:if>

				   </td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">实名认证状态</td>
					<td>
					  <select name="realNameState">
					  <c:if test="${member.realNameState eq '1'}">
					        <option value="1"  selected="selected">审核中</option>
					        <option value="2" >审核成功</option>
					        <option value="3" >审核失败</option>		
					   </c:if>
					   <c:if test="${member.realNameState eq '2'}">
					    	 <option value="1" >审核中</option>
					         <option value="2" selected="selected">审核成功</option>
					         <option value="3" >审核失败</option>		
					  </c:if>
					   <c:if test="${member.realNameState eq '3'}">
					        <option value="1" >审核中</option>
					         <option value="2">审核成功</option>
					         <option value="3"  selected="selected">审核失败</option>		
					   </c:if>			       
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