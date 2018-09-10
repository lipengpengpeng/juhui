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
		<script src="${ctx}/js/live-preview.js" type="text/javascript"></script>
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
			
			//圖片上傳
			//当选择图片时发生
			function doSelectPhotoChanged1(){
				filePath = document.getElementById("uploadImageBanner").value;
				
				if(checkFileUpload1(filePath)){
					if(userAgent != "Chrome"){
						  document.getElementById("uploadImageBanner").value = "";
						  document.getElementById("imgShowTeacherPhoto1").src=filePath;
						  document.getElementById("imgchange1").rel=$("#imgShowTeacherPhoto1").attr("src");
				 	}
				}
			}
			
			//图片上传验证
			function checkFileUpload1()
			{
			  var path = $("#uploadImageBanner").attr("value");
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
				    $("#uploadImageBanner").attr("value","");
				    return false;
				  }
			   }
			  return true;
			}
			
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">当前位置: 店铺管理- 编辑店铺</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="location.href='${ctx}/subsystem/storeInfoAction!retrieveStoreByPager.action'"/></div>
		</div>

		<form action="storeInfoAction!updateStore.action" method="post" id="gjfMemberInfoForm" name="gjfMemberInfoForm" enctype="multipart/form-data">
		  <input type="hidden" name="id" value="${resultVo.result.id}"/>
		  <input type="hidden" name="token" value="${resultVo.result.token}"/>
			<table  align="center" class="listTable3">
				<%-- <input type="hidden" name="id" value="${member.id}"/> --%>
				<tr>
					<td class="pn-flabel" width="100px">店铺名称</td>
					<td><input type="text" id="unionId" name="storeName" value="${resultVo.result.storeName}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺编码</td>
					<td><input type="text" id="name" name="storeNum" value="${resultVo.result.storeNum}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店主卖家联系人</td>
					<td><input type="text" id="mobile" name="sellerName" value="${resultVo.result.sellerName}" /></td>
				</tr>
				 <tr>
					<td class="pn-flabel" width="100px">店主联系电话</td>
					<td><input type="text" id="password" name="sellerMobile" value="${resultVo.result.sellerMobile}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店主邮箱</td>
					<td><input type="text" id="payPassword" name="sellerEmail" value="${resultVo.result.sellerEmail}" /></td>
				</tr> 
				<tr>
					<td class="pn-flabel" width="100px">店铺所在省份</td>
					<td>
					   <select id="provice" name="provinceId.id">
					     
					   </select>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺所在城市</td>
					<td>
					   <select id="city" name="cityId.id">
					     
					   </select>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺所在区县</td>
					<td>
					   <select id="area" name="areaId.id">
					     
					   </select>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">详细地区</td>
					<td><input type="text" id="mobile" name="addressDetail" value="${resultVo.result.addressDetail}" /></td>					
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺申请时间</td>
					<td><input type="text" disabled="disabled" id="idCard" name="storeAduitTime" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${resultVo.result.storeAddTime}"></fmt:formatDate>" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺审核时间</td>
					<td><input type="text" disabled="disabled" id="idCard" name="storeAduitTime" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${resultVo.result.storeAduitTime}"></fmt:formatDate>" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺开店时间</td>
					<td><input type="text" disabled="disabled" id="idCard" name="storeStartTime" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${resultVo.result.storeStartTime}"></fmt:formatDate>" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺开店时间</td>
					<td><input type="text" disabled="disabled" id="idCard" name="storeEndTime" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${resultVo.result.storeEndTime}"></fmt:formatDate>" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺关闭原因</td>
					<td><input type="text" id="mobile" name="storeCloseRemark" value="${resultVo.result.storeCloseRemark}" /></td>					
				</tr>
								
				<tr>
					<td class="pn-flabel" width="100px">店铺logo</td>
					<td>
						<div id="divPreview0">
			          		<img id="imgShowTeacherPhoto" name="imgShowTeacherPhoto" width="96" border="0" 
			          		src="<c:choose>
										<c:when test="${empty resultVo.result.storeLogoUrl}">
												${ctx}/upload/NoExpertPhoto.JPG
										</c:when>
										<c:otherwise>
											${resultVo.result.storeLogoUrl}
										</c:otherwise>
									</c:choose>
									" />
			          	</div>
						<input type="file" id="uploadImage" name="uploadImage" onchange="PreviewImage(this,'imgShowTeacherPhoto','divPreview0')" size="30"/>
						<font color="#FF0000">所有图片仅支持jpg,jpeg,png,gif格式</font>
						
					<%-- <a href="${resultVo.result.storeLogoUrl}"
									target="_blank" name="imgchange" id="imgchange"
									class="screenshot" rel="doSelectPhotoChanged()"><img
										id="imgShowTeacherPhoto" name="imgShowTeacherPhoto" border="0"
										width="96"
										src="
											<c:choose>
												<c:when test="${empty resultVo.result.storeLogoUrl}">
														${ctx}/upload/NoExpertPhoto.JPG
												</c:when>
												<c:otherwise>
													${resultVo.result.storeLogoUrl}
												</c:otherwise>
											</c:choose>
											"/>
								</a>
								<br />
								<input type="file"   id="uploadImage" name="uploadImage" value=""
									onchange="doSelectPhotoChanged();"  size="30"/>	 --%>			  					  
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺横幅</td>
					<td>
					<div id="divPreview1">
			          		<img id="imgShowTeacherPhoto1" name="imgShowTeacherPhoto1" width="96" border="0" 
			          		src="<c:choose>
										<c:when test="${empty resultVo.result.storeBanner}">
												${ctx}/upload/NoExpertPhoto.JPG
										</c:when>
										<c:otherwise>
											${resultVo.result.storeBanner}
										</c:otherwise>
									</c:choose>
									" />
			          	</div>
						<input type="file" id="uploadImageBanner" name="uploadImageBanner" onchange="PreviewImage(this,'imgShowTeacherPhoto1','divPreview1')" size="30"/>
					
					<%-- <a href="${resultVo.result.storeBanner}"
									target="_blank" name="imgchange1" id="imgchange1"
									class="screenshot" ><img
										id="imgShowTeacherPhoto1" name="imgShowTeacherPhoto1" border="0"
										width="96" 
										src="
											<c:choose>
												<c:when test="${empty resultVo.result.storeBanner}">
														${ctx}/upload/NoExpertPhoto.JPG
												</c:when>
												<c:otherwise>
													${resultVo.result.storeBanner}
												</c:otherwise>
											</c:choose>
											"/>
								</a>
								<br />
								<input type="file"  id="uploadImageBanner" name="uploadImageBanner" value=""
									onchange="doSelectPhotoChanged1();"  size="30"/> --%>
							
					  
					</td>
				</tr>
				
				 <tr>
					<td class="pn-flabel" width="100px">店铺关键字</td>
					<td><input type="text" id="superId" name="storeKeywords" value="${resultVo.result.storeKeywords}" /></td>
				</tr> 
				<tr>
					<td class="pn-flabel" width="100px">推荐</td>
					<td>
					<select name="storeRecommend">
					   <c:if test="${resultVo.result.storeRecommend==0}">
					       <option value="0" selected="selected">否</option>
					       <option value="1" >是</option>
					   </c:if>
					   <c:if test="${resultVo.result.storeRecommend==1}">
					       <option value="0" >否</option>
					       <option value="1" selected="selected">是</option>
					   </c:if>
					</select>
				  </td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">是否可分红</td>
					<td>
					<select name="isDivi">
					   <c:if test="${resultVo.result.isDivi==0}">
					       <option value="0" selected="selected">否</option>
					       <option value="1" >是</option>
					   </c:if>
					   <c:if test="${resultVo.result.isDivi==1}">
					       <option value="0" >否</option>
					       <option value="1" selected="selected">是</option>
					   </c:if>
					</select>
				  </td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺信用</td>
					<td><input type="text" disabled="disabled" id="cityId" name="member.cityId" value="${resultVo.result.storeCreditScore}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">描述相符度分数</td>
					<td><input type="text" disabled="disabled" id="areaId" name="member.areaId" value="${resultVo.result.storeDesccreditScore}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">服务态度分数</td>
					<td><input type="text" disabled="disabled" id="adrress" name="adrress" value="${resultVo.result.storeServiceCreditScore}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">发货速度分数</td>
					<td><input type="text" disabled="disabled" id="remark" name="remark" value="${resultVo.result.storeDeliveryCreditScore}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺收藏数量</td>
					<td><input type="text"  disabled="disabled" id="balanceMoney" name="balanceMoney" value="${resultVo.result.storeCollectNum}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">售前客服</td>
					<td><input type="text"  id="consumptionMoney" name="storeBefCustomer" value="${resultVo.result.storeBefCustomer}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">售后客服</td>
					<td><input type="text" id="cumulativeMoney" name="cumulativeMoney"  value="${resultVo.result.storeAftCustomer}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">工作时间</td>
					<td><input type="text" id="benefitMoney" name="storeWorkingTime"  value="${resultVo.result.storeWorkingTime}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">超出该金额免运费</td>
					<td><input type="text" id="withdrawalMoney"  name="storeFreePrice" value="${resultVo.result.storeFreePrice}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">是否自营店铺</td>
					<td>
					<select name="storeIsOwnShop">
					   <c:if test="${resultVo.result.storeIsOwnShop==0}">
					       <option value="0" selected="selected">否</option>
					       <option value="1" >是</option>
					   </c:if>
					   <c:if test="${resultVo.result.storeIsOwnShop==1}">
					       <option value="0" >否</option>
					       <option value="1" selected="selected">是</option>
					   </c:if>
					</select>
				 </td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">保证金金额</td>
					<td><input type="text" id="dividendsMoneyBla" name="storeMargin"  value="${resultVo.result.storeMargin}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">实收比例</td>
					<td><input type="text" id="dividendsTotalMoney" name="storeRealIncomeRatio"  value="${resultVo.result.storeRealIncomeRatio}" />%</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">赠与比例</td>
					<td><input type="text" id="dividendsNum" name="storeRealGiftRatio"  value="${resultVo.result.storeRealGiftRatio}" />%</td>
				</tr>
				
				<tr>
					<td class="pn-flabel" width="100px">店铺属性</td>
					<td>
					   <select name="storePro">
					     <c:if test="${resultVo.result.storePro==0}">
					        <option value="0" selected="selected">O2O</option>
					        <option value="1">网上商城</option>
					     </c:if>
					     <c:if test="${member.storePro==1}">
					        <option value="0" >O2O</option>
					        <option value="1" selected="selected">网上商城</option>
					     </c:if>
					   </select>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺类型</td>
					<td>
					 <select name="storeType">
					     <c:if test="${resultVo.result.storeType==0}">
					        <option value="0" selected="selected">个体入驻</option>
					        <option value="1">企业入驻</option>
					     </c:if>
					     <c:if test="${resultVo.result.storeType==1}">
					        <option value="0" >个体入驻</option>
					        <option value="1" selected="selected">企业入驻</option>
					     </c:if>
					  </select>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺状态）</td>
					<td>
					<select name="storeStatus">
					     <c:if test="${resultVo.result.storeStatus==0}">
					        <option value="0" selected="selected">关闭</option>
					        <option value="1">开启</option>
					        <option value="2">审核中</option>
					        <option value="3">审核失败</option>
					     </c:if>
					     <c:if test="${resultVo.result.storeStatus==1}">
					        <option value="0" >关闭</option>
					        <option value="1" selected="selected">开启</option>
					        <option value="2">审核中</option>
					        <option value="3">审核失败</option>
					     </c:if>
					      <c:if test="${resultVo.result.storeStatus==2}">
					        <option value="0" >关闭</option>
					        <option value="1">开启</option>
					        <option value="2" selected="selected">审核中</option>
					        <option value="3">审核失败</option>
					     </c:if>
					     <c:if test="${resultVo.result.storeStatus==3}">
					        <option value="0" >关闭</option>
					        <option value="1">开启</option>
					        <option value="2">审核中</option>
					        <option value="3" selected="selected">审核失败</option>
					     </c:if>
					  </select>					
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">审核状态理由</td>
					
					  <td><textarea rows="10"  cols="100" id="auditStatusReason" name="auditStatusReason">${resultVo.result.auditStatusReason}</textarea></td>
	
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺支付商户号</td>
					
					  <td><input id="payMchId" name="payMchId" value="${resultVo.result.payMchId}"/></td>
	
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺支付秘钥</td>
					
					  <td><input id="payKey" name="payKey" value="${resultVo.result.payKey}"/></td>
	
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
	
<script type="text/javascript">
var proviceId="${resultVo.result.provinceId.provinceId}"
var cityId="${resultVo.result.cityId.cityId}"
var areaId="${resultVo.result.areaId.areaId}"
//获取所有省份
$.ajax({
	   url:"${ctx}/subsystem/orderInfoAction!findAllProvice.action",
	   method:"GET",	  
	   data:{
		   fatherId:"",
		   addressType:1
	   },
	   success:function(data){
		   $("#provice").html(data);
		   $("#provice option").each(function(){
			 if(proviceId==$(this).attr("provinceId")){
				 $(this).attr("selected", true)
			 }
		   })
	   },
	   error:function(xth,type,e){		  
		   
	   }
})

//省份改變
$("#provice").change(function(){
	getCity($("#provice option:selected").attr("provinceId"))
	
    getArea($("#city option:selected").attr("cityId"))
	
})

//城市改變
$("#city").change(function(){
	getArea($("#city option:selected").attr("cityId"))
})


//获取省份下的城市
getCity(proviceId)
function getCity(proviceIds){
	 $.ajax({
		   url:"${ctx}/subsystem/orderInfoAction!findAllCity.action",
		   method:"GET",
		   data:{
			   fatherId:proviceIds,
			   addressType:2
		   },
		   success:function(data){			   
			   $("#city").html(data) 
			 $("#city option").each(function(){
			   if(cityId==$(this).attr("cityId")){
				 $(this).attr("selected", true)
			   }
		     })
		      getArea($("#city option:selected").attr("cityId"))
		   }
	     }) 

    }
		 

//获取城市下的区域
getArea(cityId)
function getArea(cityIds){
	  $.ajax({
		   url:"${ctx}/subsystem/orderInfoAction!findAllArea.action",
		   method:"GET",
		   data:{
			   fatherId:cityIds,
			   addressType:3
		   },
		   success:function(data){			  
			  $("#area").html(data)
			  $("#area option").each(function(){
			   if(areaId==$(this).attr("areaId")){
				 $(this).attr("selected", true)
			   }
		     })
		   }
	     })   	 
 	  }
		
</script>
</html>