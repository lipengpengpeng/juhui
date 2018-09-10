<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
		<style type="text/css">
		</style>
		<script>
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">当前位置: 产品库 -  
				<!-- 查看新闻信息 --><s:text name="enterprise.collection.current.location.viewproduct" /></div>
			<div class="ropt"><input type="button" value='<s:text name="common.word.return.back" />' onclick="history.back();"/></div>
		</div>

		<table  align="center" class="listTable3">
				<tr>
					<td class="pn-flabel" width="100px"><!-- 产品名称 --><s:text name="enterprise.collection.product.name" /></td>
					<td>${productInfo.name}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺</td>
					<td>
						${productInfo.storeId.storeName}
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="${ctx}/subsystem/storeInfoAction!retrieveStoreById.action?id=${productInfo.storeId.id}&token=${productInfo.storeId.token}">点击查看店铺</a>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">栏目</td>
					<td>
						${productInfo.columnId.names}
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品类型</td>
					<td>
						<c:if test="${productInfo.storeId.storePro eq 0 }">
							O2O商品
						</c:if>
						<c:if test="${productInfo.storeId.storePro eq 1 }">
							自营商品
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品货号</td>
					<td>${productInfo.serialNum }</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">零售价</td>
					<td>${productInfo.price }</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">市场价</td>
					<td>${productInfo.marketPrice }</td>
				</tr>
				<c:if test="${not empty productInfo.gcostPrice }">
					<tr>
						<td class="pn-flabel" width="100px">成本价</td>
						<td>${productInfo.gcostPrice }</td>
					</tr>
				</c:if>
				<tr>
					<td class="pn-flabel" width="100px">销量</td>
					<td>${productInfo.salesNum }</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">收藏量</td>
					<td>${productInfo.collectionNum }</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px"><!-- 点击次数 --><s:text name="enterprise.collection.click.times" /></td>
					<td>${productInfo.viewNum}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px"><!-- 关键字 --><s:text name="enterprise.collection.keyword" /></td>
					  <td>
					 	 ${productInfo.keywords}
					  </td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px"><!-- 简短描述 --><s:text name="enterprise.collection.short.description" /></td>
					  <td>
					 	 ${productInfo.description}
					  </td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">购买须知</td>
					  <td>
					 	 ${productInfo.notice}
					  </td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">备注</td>
					  <td>
					 	 ${productInfo.remarkInfo}
					  </td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品缩略图</td>
					<td>
						<img src="
							<c:choose>
								<c:when test="${empty productInfo.imgUrl}">
										${ctx}/upload/MissPhoto.JPG
								</c:when>
								<c:otherwise>
									${productInfo.imgUrl}
								</c:otherwise>
							</c:choose>
						" width="96" height="96" />
					</td>
				</tr>
				<c:if test="${not empty productInfo.para1}">
					<tr >
							<td class="pn-flabel" width="100px">商品展示图</td>
							<td>
								<img width="96" height="96" src="${productInfo.para1}" />
							</td>
					</tr>
				</c:if>
				<c:if test="${not empty productInfo.para2}">
					<tr >
							<td class="pn-flabel" width="100px">商品展示图</td>
							<td>
								<img width="96" height="96" src="${productInfo.para2}" />
							</td>
					</tr>
				</c:if>
				<c:if test="${not empty productInfo.para3}">
					<tr >
							<td class="pn-flabel" width="100px">商品展示图</td>
							<td>
								<img width="96" height="96" src="${productInfo.para3}" />
							</td>
					</tr>
				</c:if>
				<c:if test="${not empty productInfo.para4}">
					<tr >
							<td class="pn-flabel" width="100px">商品展示图</td>
							<td>
								<img width="96" height="96" src="${gjfProductInfo.para4}" />
							</td>
					</tr>
				</c:if>
				<c:if test="${not empty productInfo.para5}">
					<tr >
							<td class="pn-flabel" width="100px">商品展示图</td>
							<td>
								<img width="96" height="96" src="${productInfo.para5}" />
							</td>
					</tr>
				</c:if>
				<%-- <tr>
					<td class="pn-flabel" width="100px"><!-- 产品库存 --><s:text name="产品库存" /></td>
					<td>${gjfProductInfo.repertory}</td>
				</tr> --%>
				<tr>
					<td class="pn-flabel" width="100px"><!-- 是否为最新产品：--><s:text name="enterprise.collection.is.new.product" /></td>
					<td>
						<c:if test="${productInfo.isNew==0}"><!-- 否 --><s:text name="common.word.no" /></c:if>
						<c:if test="${productInfo.isNew==1}"><!-- 是 --><s:text name="common.word.yes" /></c:if>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px"><!-- 是否为促销产品：--><s:text name="enterprise.collection.is.sale.product" /></td>
					<td>
						<c:if test="${productInfo.isSale==0}"><!-- 否 --><s:text name="common.word.no" /></c:if>
						<c:if test="${productInfo.isSale==1}"><!-- 是 --><s:text name="common.word.yes" /></c:if>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px"><!-- 是否为热卖产品：--><s:text name="enterprise.collection.is.hot.product" /></td>
					<td>
						<c:if test="${productInfo.isHot==0}"><!-- 否 --><s:text name="common.word.no" /></c:if>
						<c:if test="${productInfo.isHot==1}"><!-- 是 --><s:text name="common.word.yes" /></c:if>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px"><!-- 是否为抢购产品：--><s:text name="enterprise.collection.is.qbuy.product" /></td>
					<td>
						<c:if test="${productInfo.isQbuy==0}"><!-- 否 --><s:text name="common.word.no" /></c:if>
						<c:if test="${productInfo.isQbuy==1}"><!-- 是 --><s:text name="common.word.yes" /></c:if>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px"><!-- 是否为推荐产品：--><s:text name="enterprise.collection.is.recommend.product" /></td>
					<td>
						<c:if test="${productInfo.isRecommend==0}"><!-- 否 --><s:text name="common.word.no" /></c:if>
						<c:if test="${productInfo.isRecommend==1}"><!-- 是 --><s:text name="common.word.yes" /></c:if>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px"><!-- 更新时间 --><s:text name="common.word.update.time" /></td>
					<td><fmt:formatDate value="${productInfo.updateTime}" pattern="yyyy-MM-dd HH:mm:SS" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px"><!-- 添加时间 --><s:text name="enterprise.collection.add.time" /></td>
					<td><fmt:formatDate value="${productInfo.addTime}" pattern="yyyy-MM-dd HH:mm:SS" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">审核状态</td>
					<td>
						<c:if test="${productInfo.aduitStatus==0}">未通过</c:if>
						<c:if test="${productInfo.aduitStatus==1}">通过</c:if>
						<c:if test="${productInfo.aduitStatus==2}">审核中</c:if>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品状态</td>
					<td>
						<c:if test="${productInfo.status==0}">下架</c:if>
						<c:if test="${productInfo.status==1}">正常</c:if>
						<c:if test="${productInfo.status==2}">违规</c:if>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">详细描述</td>
					  <td>
					 		<div>
					 			${productInfo.content}
					 		</div>
					  </td>
				</tr>
		 </table>
	</body>
</html>