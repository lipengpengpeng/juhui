<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>查看模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">当前位置: 店铺管理- 审核</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="javascript:history.go(-1)"/></div>
		</div>

		<table  align="center" class="listTable3">
				<tr>
					<td class="pn-flabel" width="100px">店铺名称</td>
					<td>${resultVo.result.storeId.storeName}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺编码</td>
					<td>${resultVo.result.storeId.storeNum}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店主卖家联系人</td>
					<td>${resultVo.result.storeId.sellerName}</td>
				</tr>
				 <tr>
					<td class="pn-flabel" width="100px">店主联系电话</td>
					<td>${resultVo.result.storeId.sellerMobile}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店主邮箱</td>
					<td>${resultVo.result.storeId.sellerEmail}</td>
				</tr> 
				<tr>
					<td class="pn-flabel" width="100px">店铺所在省份</td>
					<td>${resultVo.result.storeId.provinceId.province}</td>
				</tr>
				
				<tr>
					<td class="pn-flabel" width="100px">店铺所在城市</td>
					<td>${resultVo.result.storeId.cityId.city}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺所在区县</td>
					<td>${resultVo.result.storeId.areaId.area}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">详细地区</td>
					<td>${resultVo.result.storeId.addressDetail}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺申请时间</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${resultVo.result.storeId.storeAddTime}"></fmt:formatDate></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺审核时间</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${resultVo.result.storeId.storeAduitTime}"></fmt:formatDate></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺开店时间</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${resultVo.result.storeId.storeStartTime}"></fmt:formatDate></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺关闭时间</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${resultVo.result.storeId.storeEndTime}"></fmt:formatDate></td>
				</tr> 
				<tr>
					<td class="pn-flabel" width="100px">店铺关闭原因</td>
					<td>${resultVo.result.storeId.storeCloseRemark}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺logo</td>
					<td>
					<a href="${resultVo.result.storeId.storeLogoUrl}"
									target="_blank" name="imgchange" id="imgchange"
									class="screenshot" ><img
										id="imgShowTeacherPhoto" name="imgShowTeacherPhoto" border="0"
										width="96"
										src="
											<c:choose>
												<c:when test="${empty resultVo.result.storeId.storeLogoUrl}">
														${ctx}/upload/NoExpertPhoto.JPG
												</c:when>
												<c:otherwise>
													${resultVo.result.storeId.storeLogoUrl}
												</c:otherwise>
											</c:choose>
											"/>
								</a>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺横幅</td>
					<td>
					<a href="${resultVo.result.storeId.storeBanner}"
									target="_blank" name="imgchange" id="imgchange"
									class="screenshot" ><img
										id="imgShowTeacherPhoto" name="imgShowTeacherPhoto" border="0"
										width="96"
										src="
											<c:choose>
												<c:when test="${empty resultVo.result.storeId.storeBanner}">
														${ctx}/upload/NoExpertPhoto.JPG
												</c:when>
												<c:otherwise>
													${resultVo.result.storeId.storeBanner}
												</c:otherwise>
											</c:choose>
											"/>
								</a>
					</td>					
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺关键字</td>
					<td>${resultVo.result.storeId.storeKeywords}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺描述</td>
					<td>${resultVo.result.storeId.storeDescription}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">推荐</td>
					<c:if test="${resultVo.result.storeId.storeRecommend==0}">
					    <td>否</td>
					</c:if>
					<c:if test="${resultVo.result.storeId.storeRecommend==1}">
					    <td>是</td>
					</c:if>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺信用</td>
					<td>${resultVo.result.storeId.storeCreditScore}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">描述相符度分数</td>
					<td>${resultVo.result.storeId.storeDesccreditScore}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">服务态度分数</td>
					<td>${resultVo.result.storeId.storeServiceCreditScore}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">发货速度分数</td>
					<td>${resultVo.result.storeId.storeDeliveryCreditScore}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺收藏数量</td>
					<td>${resultVo.result.storeId.storeCollectNum}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">售前客服</td>
					<td>${resultVo.result.storeId.storeBefCustomer}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">售后客服</td>
					<td>${resultVo.result.storeId.storeAftCustomer}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">工作时间</td>
					<td>${resultVo.result.storeId.storeWorkingTime}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">超出该金额免运费</td>
					<td>${resultVo.result.storeId.storeFreePrice}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">是否自营店铺</td>					
					  <c:if test="${resultVo.result.storeId.storeIsOwnShop==0}">
					    <td>否</td>
					</c:if>
					<c:if test="${resultVo.result.storeId.storeIsOwnShop==1}">
					    <td>是</td>
					</c:if>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">保证金金额</td>
					<td>${resultVo.result.storeId.storeMargin}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">实收比例</td>
					
					  <td>${resultVo.result.storeId.storeRealIncomeRatio}</td>
	
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">赠与比例</td>
					
					  <td>${resultVo.result.storeId.storeRealGiftRatio}</td>
	
				</tr>
				
				<tr>
					<td class="pn-flabel" width="100px">店铺营业执照</td>
					<td>
					 <a href="${resultVo.result.businessLicenceNumberElectronic}"
									target="_blank" name="imgchange" id="imgchange"
									class="screenshot" ><img
										id="imgShowTeacherPhoto" name="imgShowTeacherPhoto" border="0"
										width="96"
										src="
											<c:choose>
												<c:when test="${empty resultVo.result.businessLicenceNumberElectronic}">
														${ctx}/upload/NoExpertPhoto.JPG
												</c:when>
												<c:otherwise>
													${resultVo.result.businessLicenceNumberElectronic}
												</c:otherwise>
											</c:choose>
											"/>
								</a>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">营业执照号</td>
					<td>${resultVo.result.businessLicenceNumber}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">营业执所在地</td>
					<td>${resultVo.result.businessLicenceAddress}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">营业执照有效期开始</td>
					<td>${resultVo.result.businessLicenceStart}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">营业执照有效期结束</td>
					<td>${resultVo.result.businessLicenceEnd}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">法定经营范围</td>
					<td>${resultVo.result.businessSphere}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">注册资金</td>
					<td>${resultVo.result.companyRegisteredCapital}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">税务登记号</td>
					<td>${resultVo.result.taxRegistrationCertificate}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">组织机构代码</td>
					<td>${resultVo.result.organizationCode}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">银行开户名</td>
					<td>${resultVo.result.bankAccountName}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">公司银行账号</td>
					<td>${resultVo.result.bankAccountNumber}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">开户银行支行名称</td>
					<td>${resultVo.result.bankName}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">开户银行所在地</td>
					<td>${resultVo.result.bankAddress}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">开户行账号是否为结算账号</td>
					<td>
						<c:choose>
							<c:when test="${resultVo.result.isSettlementAccount eq '0'}">否</c:when>
							<c:when test="${resultVo.result.isSettlementAccount eq '1'}">是</c:when>
						</c:choose>
					</td>
				</tr>
				<c:if test="${not empty resultVo.result.settlementBankAccountName }">		
						<tr>
							<td class="pn-flabel" width="100px">结算银行开户名</td>
							<td>${resultVo.result.settlementBankAccountName}</td>
						</tr>
					</c:if>	
					<c:if test="${not empty resultVo.result.settlementBankAccountNumber }">	
						<tr>
							<td class="pn-flabel" width="100px">结算公司银行账号</td>
							<td>${resultVo.result.settlementBankAccountNumber}</td>
						</tr>
					</c:if>	
					<c:if test="${not empty resultVo.result.settlementBankName }">		
						<tr>
							<td class="pn-flabel" width="100px">结算开户银行支行名称</td>
							<td>${resultVo.result.settlementBankName}</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultVo.result.settlementBankAddress }">			
						<tr>
							<td class="pn-flabel" width="100px">结算开户银行所在地</td>
							<td>${resultVo.result.settlementBankAddress}</td>
						</tr>
					</c:if>
				
				<tr>
					<td class="pn-flabel" width="100px">店铺属性</td>
					<c:if test="${resultVo.result.storeId.storePro==0}">
					   <td>O2O</td>
					</c:if>
					<c:if test="${resultVo.result.storeId.storePro==1}">
					   <td>网上商城</td>
					</c:if>
					
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺类型</td>
					<c:if test="${resultVo.result.storeId.storeType==0}">
					   <td>个体入驻</td>
					</c:if>
					<c:if test="${resultVo.result.storeId.storeType==1}">
					   <td>企业入驻</td>
					</c:if>
					
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺状态</td>
					<td>
					  <select id="storeStatus">
					      <c:if test="${resultVo.result.storeId.storeStatus==0}">
					          <option selected value="0">关闭</option>
					          <option value="1">开启</option>
					          <option value="2">审核中</option>
					          <option value="3">审核失败</option>
					      </c:if>
					      <c:if test="${resultVo.result.storeId.storeStatus==1}">
					          <option value="0">关闭</option>
					          <option value="1" selected>开启</option>
					          <option value="2">审核中</option>
					          <option value="3">审核失败</option>
					      </c:if>
					      <c:if test="${resultVo.result.storeId.storeStatus==2}">
					           <option value="0">关闭</option>
					          <option value="1">开启</option>
					          <option value="2" selected>审核中</option>
					          <option value="3">审核失败</option>
					      </c:if>
					       <c:if test="${resultVo.result.storeId.storeStatus==3}">
					          <option value="0">关闭</option>
					          <option value="1">开启</option>
					          <option value="2">审核中</option>
					          <option selected value="3">审核失败</option>
					      </c:if>
					   </select>
					   </td>
				</tr>
				
				<tr>
					<td class="pn-flabel" width="100px">审核状态理由</td>
					
					  <td><textarea rows="10" cols="100" id="auditStatusReason">${resultVo.result.storeId.auditStatusReason}</textarea></td>
	
				</tr>
				
				<tr>
					<td></td>
					<td>
						<input type="button" onclick="auditStatus(${resultVo.result.storeId.id},'${resultVo.result.storeId.token}')" class="defaultButton" id="defaultButton" value=" 提 交 "/>
					</td>
				</tr>

		 </table>
	</body>
    <script>
		   //提交审核信息
		  
		   
		   function auditStatus(id,token){
			   $.ajax({
				   url:"${ctx}/subsystem/storeInfoAction!modifyAduitStatus.action",
				   method:"post",
				   data:{
					   id:id,
					   token:token,
					   aduitStatus:$("#storeStatus option:selected").val(),
					   auditStatusReason:$("#auditStatusReason").val()
				   },
				   success:function(data){
					   alert("审核成功");
					   window.location.href="${ctx}/subsystem/storeInfoAction!retrieveStoreByPager.action";
				   }
				   
			   })
		   }
		     
		</script>
</html>