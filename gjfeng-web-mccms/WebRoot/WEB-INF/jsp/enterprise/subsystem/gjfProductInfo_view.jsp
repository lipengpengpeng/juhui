<%--

	gjfProductInfo_view.jsp

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
			<div class="rpos">当前位置: 模块 - 子模块</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="location.href='${ctx}/subsystem/gjfProductInfoAction!retrieveAllGjfProductInfos.action'"/></div>
		</div>

		<table  align="center" class="listTable3">
				<tr>
					<td class="pn-flabel" width="100px">FK:店铺ID</td>
					<td>${gjfProductInfo.storeId}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品货号</td>
					<td>${gjfProductInfo.serialNum}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品名称</td>
					<td>${gjfProductInfo.name}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品关键字</td>
					<td>${gjfProductInfo.keywords}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品简介</td>
					<td>${gjfProductInfo.description}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品详细内容</td>
					<td>${gjfProductInfo.content}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品购买须知</td>
					<td>${gjfProductInfo.notice}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">实际价格</td>
					<td>${gjfProductInfo.price}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">市场价格</td>
					<td>${gjfProductInfo.marketPrice}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">成本价格</td>
					<td>${gjfProductInfo.gcostPrice}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">销量</td>
					<td>${gjfProductInfo.salesNum}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">浏览量</td>
					<td>${gjfProductInfo.viewNum}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">收藏量</td>
					<td>${gjfProductInfo.collectionNum}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">是否新品(0:否 1:是)</td>
					<td>${gjfProductInfo.isNew}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">是否促销(0:否 1:是)</td>
					<td>${gjfProductInfo.isSale}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">是否为热卖(0:否 1:是)</td>
					<td>${gjfProductInfo.isHot}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">是否为抢购(0:否 1:是)</td>
					<td>${gjfProductInfo.isQbuy}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">是否为推荐(0:否 1:是)</td>
					<td>${gjfProductInfo.isRecommend}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品缩略图</td>
					<td>${gjfProductInfo.imgUrl}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">添加时间</td>
					<td>${gjfProductInfo.addTime}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">更新时间</td>
					<td>${gjfProductInfo.updateTime}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">库存</td>
					<td>${gjfProductInfo.repertory}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品审核(0:未通过 1:通过 2:审核中)</td>
					<td>${gjfProductInfo.aduitStatus}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品备注</td>
					<td>${gjfProductInfo.remark}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品状态(0:下架 1:正常 2:违规)</td>
					<td>${gjfProductInfo.status}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">安全token</td>
					<td>${gjfProductInfo.token}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">para1</td>
					<td>${gjfProductInfo.para1}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">para2</td>
					<td>${gjfProductInfo.para2}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">para3</td>
					<td>${gjfProductInfo.para3}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">para4</td>
					<td>${gjfProductInfo.para4}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">para5</td>
					<td>${gjfProductInfo.para5}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">para6</td>
					<td>${gjfProductInfo.para6}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">para7</td>
					<td>${gjfProductInfo.para7}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">para8</td>
					<td>${gjfProductInfo.para8}</td>
				</tr>

		 </table>
	</body>
</html>