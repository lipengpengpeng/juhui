<%--

	gjfOrderGoods_new.jsp

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
				$("#gjfOrderGoodsForm").validate({
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
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="location.href='${ctx}/subsystem/gjfOrderGoodsAction!retrieveAllGjfOrderGoodss.action'"/></div>
		</div>

		<form action="gjfOrderGoodsAction!newGjfOrderGoods.action" method="post" id="gjfOrderGoodsForm" name="gjfOrderGoodsForm" enctype="multipart/form-data">
			<table  align="center" class="listTable3">
				<tr>
					<td class="pn-flabel" width="100px">FK:订单ID</td>
					<td><input type="text" id="orderId" name="gjfOrderGoods.orderId" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">FK:商品ID</td>
					<td><input type="text" id="goodsId" name="gjfOrderGoods.goodsId" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">FK:商品属性（网上商城商品才有）</td>
					<td><input type="text" id="goodsAttrId" name="gjfOrderGoods.goodsAttrId" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品名称</td>
					<td><input type="text" id="goodsName" name="gjfOrderGoods.goodsName" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品价格</td>
					<td><input type="text" id="goodsAmount" name="gjfOrderGoods.goodsAmount" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品支付价格</td>
					<td><input type="text" id="goodsPayAmount" name="gjfOrderGoods.goodsPayAmount" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品数量</td>
					<td><input type="text" id="goodsNum" name="gjfOrderGoods.goodsNum" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品图片</td>
					<td><input type="text" id="goodsImageUrl" name="gjfOrderGoods.goodsImageUrl" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品类型（1:默认 2:团购商品 3:限时折扣商品 4:组合套装 5:赠品)</td>
					<td><input type="text" id="goodsType" name="gjfOrderGoods.goodsType" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">促销活动ID（团购ID/限时折扣ID/优惠套装ID）与goods_type搭配使用</td>
					<td><input type="text" id="promotionsId" name="gjfOrderGoods.promotionsId" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺到账金额</td>
					<td><input type="text" id="storeRecAmount" name="gjfOrderGoods.storeRecAmount" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">店铺让利金额</td>
					<td><input type="text" id="storeBenefitAmount" name="gjfOrderGoods.storeBenefitAmount" /></td>
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