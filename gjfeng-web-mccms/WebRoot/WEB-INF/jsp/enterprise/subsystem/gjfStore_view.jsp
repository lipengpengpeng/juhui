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
			<div class="rpos">当前位置: 店铺管理- 查看</div>
			<div class="ropt"><input type="button" value='<s:text name="common.word.return.back" />' onclick="history.back();"/></div>
		</div>

		<table  align="center" class="listTable3">
				<tr>
					<td class="pn-flabel" width="100px">店铺名称</td>
					<td>${resultVo.result.storeName}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺编码</td>
					<td>${resultVo.result.storeNum}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店主卖家联系人</td>
					<td>${resultVo.result.sellerName}</td>
				</tr>
				 <tr>
					<td class="pn-flabel" width="100px">店主联系电话</td>
					<td>${resultVo.result.sellerMobile}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店主邮箱</td>
					<td>${resultVo.result.sellerEmail}</td>
				</tr> 
				<tr>
					<td class="pn-flabel" width="100px">店铺所在省份</td>
					<td>${resultVo.result.provinceId.province}</td>
				</tr>
				
				<tr>
					<td class="pn-flabel" width="100px">店铺所在城市</td>
					<td>${resultVo.result.cityId.city}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺所在区县</td>
					<td>${resultVo.result.areaId.area}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">详细地区</td>
					<td>${resultVo.result.addressDetail}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺申请时间</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${resultVo.result.storeAddTime}"></fmt:formatDate></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺审核时间</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${resultVo.result.storeAduitTime}"></fmt:formatDate></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺开店时间</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${resultVo.result.storeStartTime}"></fmt:formatDate></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺关闭时间</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${resultVo.result.storeEndTime}"></fmt:formatDate></td>
				</tr> 
				<tr>
					<td class="pn-flabel" width="100px">店铺关闭原因</td>
					<td>${resultVo.result.storeCloseRemark}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺logo</td>
					
					
					<td>
					<a href="${resultVo.result.storeLogoUrl}"
									target="_blank" name="imgchange" id="imgchange"
									class="screenshot" ><img
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
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺横幅</td>
					<td>
					<a href="${resultVo.result.storeBanner}"
									target="_blank" name="imgchange" id="imgchange"
									class="screenshot" ><img
										id="imgShowTeacherPhoto" name="imgShowTeacherPhoto" border="0"
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
					<%-- <c:set var="imgStr" value="${fn:split(resultVo.result.storeBanner,';')}"></c:set>
					<c:forEach var="img" items="${imgStr}">
					    <img src="${img}"/> 
					</c:forEach> --%>	
					</td>				
				</tr>
				<c:if test="${not empty storeJoin }">
					<c:if test="${not empty storeJoin.businessLicenceNumber }">
						<tr>
							<td class="pn-flabel" width="100px">营业执照号</td>
							<td>${storeJoin.businessLicenceNumber}</td>
						</tr>
					</c:if>
					<c:if test="${not empty storeJoin.businessLicenceAddress }">
						<tr>
							<td class="pn-flabel" width="100px">营业执所在地</td>
							<td>${storeJoin.businessLicenceAddress}</td>
						</tr>
					</c:if>
					<c:if test="${not empty storeJoin.businessLicenceStart }">
						<tr>
							<td class="pn-flabel" width="100px">营业执照有效期开始</td>
							<td>${storeJoin.businessLicenceStart}</td>
						</tr>
					</c:if>	
					<c:if test="${not empty storeJoin.businessLicenceEnd }">
						<tr>
							<td class="pn-flabel" width="100px">营业执照有效期结束</td>
							<td>${storeJoin.businessLicenceEnd}</td>
						</tr>
					</c:if>	
					<c:if test="${not empty storeJoin.businessSphere }">	
						<tr>
							<td class="pn-flabel" width="100px">法定经营范围</td>
							<td>${storeJoin.businessSphere}</td>
						</tr>
					</c:if>	
					<c:if test="${not empty storeJoin.taxRegistrationCertificate }">	
						<tr>
							<td class="pn-flabel" width="100px">税务登记号</td>
							<td>${storeJoin.taxRegistrationCertificate}</td>
						</tr>
					</c:if>	
					<c:if test="${not empty storeJoin.organizationCode }">	
						<tr>
							<td class="pn-flabel" width="100px">组织机构代码</td>
							<td>${storeJoin.organizationCode}</td>
						</tr>
					</c:if>	
					<c:if test="${not empty storeJoin.bankAccountName }">	
						<tr>
							<td class="pn-flabel" width="100px">银行开户名</td>
							<td>${storeJoin.bankAccountName}</td>
						</tr>
					</c:if>	
					<c:if test="${not empty storeJoin.bankAccountNumber }">	
						<tr>
							<td class="pn-flabel" width="100px">公司银行账号</td>
							<td>${storeJoin.bankAccountNumber}</td>
						</tr>
					</c:if>	
					<c:if test="${not empty storeJoin.bankName }">	
						<tr>
							<td class="pn-flabel" width="100px">开户银行支行名称</td>
							<td>${storeJoin.bankName}</td>
						</tr>
					</c:if>	
					<c:if test="${not empty storeJoin.bankAddress }">	
						<tr>
							<td class="pn-flabel" width="100px">开户银行所在地</td>
							<td>${storeJoin.bankAddress}</td>
						</tr>
					</c:if>	
					<c:if test="${not empty storeJoin.isSettlementAccount }">	
						<tr>
							<td class="pn-flabel" width="100px">开户行账号是否为结算账号</td>
							<td>
								<c:choose>
									<c:when test="${storeJoin.isSettlementAccount eq '0'}">否</c:when>
									<c:when test="${storeJoin.isSettlementAccount eq '1'}">是</c:when>
								</c:choose>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty storeJoin.settlementBankAccountName }">		
						<tr>
							<td class="pn-flabel" width="100px">结算银行开户名</td>
							<td>${storeJoin.settlementBankAccountName}</td>
						</tr>
					</c:if>	
					<c:if test="${not empty storeJoin.settlementBankAccountNumber }">	
						<tr>
							<td class="pn-flabel" width="100px">结算公司银行账号</td>
							<td>${storeJoin.settlementBankAccountNumber}</td>
						</tr>
					</c:if>	
					<c:if test="${not empty storeJoin.settlementBankName }">		
						<tr>
							<td class="pn-flabel" width="100px">结算开户银行支行名称</td>
							<td>${storeJoin.settlementBankName}</td>
						</tr>
					</c:if>
					<c:if test="${not empty storeJoin.settlementBankAddress }">			
						<tr>
							<td class="pn-flabel" width="100px">结算开户银行所在地</td>
							<td>${storeJoin.settlementBankAddress}</td>
						</tr>
					</c:if>
					<c:if test="${not empty storeJoin.businessLicenceNumberElectronic }">			
						<tr>
							<td class="pn-flabel" width="100px">店铺申请营业执照</td>
								<td>
									<a href="${storeJoin.businessLicenceNumberElectronic}"
											target="_blank" name="imgchange" id="imgchange"
											class="screenshot" ><img
												id="imgShowTeacherPhoto" name="imgShowTeacherPhoto" border="0"
												width="96"
												src="
													<c:choose>
														<c:when test="${empty storeJoin.businessLicenceNumberElectronic}">
																${ctx}/upload/NoExpertPhoto.JPG
														</c:when>
														<c:otherwise>
															${storeJoin.businessLicenceNumberElectronic}
														</c:otherwise>
													</c:choose>
													"/>
									</a>
							</td>
						</tr>
					</c:if>
					
				</c:if>
				<tr>
					<td class="pn-flabel" width="100px">店铺关键字</td>
					<td>${resultVo.result.storeKeywords}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺描述</td>
					<td>${resultVo.result.storeDescription}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">推荐</td>
					<c:if test="${resultVo.result.storeRecommend==0}">
					    <td>否</td>
					</c:if>
					<c:if test="${resultVo.result.storeRecommend==1}">
					    <td>是</td>
					</c:if>
				</tr>
				
				<tr>
					<td class="pn-flabel" width="100px">店铺获得分红金额</td>
					<td>${resultVo.result.storeDividendsTotalMoney}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺待分红总额</td>
					<td>${resultVo.result.storeDividendsTotalMoneyBla}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺让利剩余计算金额</td>
					<td>${resultVo.result.storeDividendsMoneyBla}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺分红权个数</td>
					<td>${resultVo.result.storeDividendsNum}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺销售总额</td>
					<td>${resultVo.result.storeSaleTotalMoney}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺让利总额</td>
					<td>${resultVo.result.storeBenefitTotalMoney}</td>
				</tr>
				
				<tr>
					<td class="pn-flabel" width="100px">店铺信用</td>
					<td>${resultVo.result.storeCreditScore}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">描述相符度分数</td>
					<td>${resultVo.result.storeDesccreditScore}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">服务态度分数</td>
					<td>${resultVo.result.storeServiceCreditScore}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">发货速度分数</td>
					<td>${resultVo.result.storeDeliveryCreditScore}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺收藏数量</td>
					<td>${resultVo.result.storeCollectNum}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">售前客服</td>
					<td>${resultVo.result.storeBefCustomer}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">售后客服</td>
					<td>${resultVo.result.storeAftCustomer}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">工作时间</td>
					<td>${resultVo.result.storeWorkingTime}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">超出该金额免运费</td>
					<td>${resultVo.result.storeFreePrice}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">是否自营店铺</td>					
					  <c:if test="${resultVo.result.storeIsOwnShop==0}">
					    <td>否</td>
					</c:if>
					<c:if test="${resultVo.result.storeIsOwnShop==1}">
					    <td>是</td>
					</c:if>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">保证金金额</td>
					<td>${resultVo.result.storeMargin}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">实收比例</td>
					
					  <td>${resultVo.result.storeRealIncomeRatio}%</td>
	
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">赠与比例</td>
					
					  <td>${resultVo.result.storeRealGiftRatio}%</td>
	
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺属性</td>
					<c:if test="${resultVo.result.storePro==0}">
					   <td>O2O</td>
					</c:if>
					<c:if test="${resultVo.result.storePro==1}">
					   <td>网上商城</td>
					</c:if>
					
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺类型</td>
					<c:if test="${resultVo.result.storeType==0}">
					   <td>个体入驻</td>
					</c:if>
					<c:if test="${resultVo.result.storeType==1}">
					   <td>企业入驻</td>
					</c:if>
					
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺状态</td>
					<c:if test="${resultVo.result.storeStatus==0}">
					   <td>关闭</td>
					</c:if>
					<c:if test="${resultVo.result.storeStatus==1}">
					   <td>开启</td>
					</c:if>
					<c:if test="${resultVo.result.storeStatus==2}">
					   <td>审核中</td>
					</c:if>
					<c:if test="${resultVo.result.storeStatus==3}">
					   <td>审核失败</td>
					</c:if>
					
				</tr>
				
				<tr>
					<td class="pn-flabel" width="100px">审核状态理由</td>
					
					  <td>
					  		<div>
					 			${resultVo.result.auditStatusReason}
					 		</div>
					  </td>
	
				</tr>

		 </table>
	</body>
</html>