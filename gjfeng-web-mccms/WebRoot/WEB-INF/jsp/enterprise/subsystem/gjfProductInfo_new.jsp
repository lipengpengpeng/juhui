<%--

	gjfProductInfo_new.jsp

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
				$("#gjfProductInfoForm").validate({
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
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="location.href='${ctx}/subsystem/gjfProductInfoAction!retrieveAllGjfProductInfos.action'"/></div>
		</div>

		<form action="gjfProductInfoAction!newGjfProductInfo.action" method="post" id="gjfProductInfoForm" name="gjfProductInfoForm" enctype="multipart/form-data">
			<table  align="center" class="listTable3">
				<tr>
					<td class="pn-flabel" width="100px">FK:店铺ID</td>
					<td><input type="text" id="storeId" name="gjfProductInfo.storeId" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品货号</td>
					<td><input type="text" id="serialNum" name="gjfProductInfo.serialNum" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品名称</td>
					<td><input type="text" id="name" name="gjfProductInfo.name" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品关键字</td>
					<td><input type="text" id="keywords" name="gjfProductInfo.keywords" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品简介</td>
					<td><input type="text" id="description" name="gjfProductInfo.description" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品详细内容</td>
					<td><input type="text" id="content" name="gjfProductInfo.content" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品购买须知</td>
					<td><input type="text" id="notice" name="gjfProductInfo.notice" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">实际价格</td>
					<td><input type="text" id="price" name="gjfProductInfo.price" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">市场价格</td>
					<td><input type="text" id="marketPrice" name="gjfProductInfo.marketPrice" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">成本价格</td>
					<td><input type="text" id="gcostPrice" name="gjfProductInfo.gcostPrice" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">销量</td>
					<td><input type="text" id="salesNum" name="gjfProductInfo.salesNum" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">浏览量</td>
					<td><input type="text" id="viewNum" name="gjfProductInfo.viewNum" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">收藏量</td>
					<td><input type="text" id="collectionNum" name="gjfProductInfo.collectionNum" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">是否新品(0:否 1:是)</td>
					<td><input type="text" id="isNew" name="gjfProductInfo.isNew" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">是否促销(0:否 1:是)</td>
					<td><input type="text" id="isSale" name="gjfProductInfo.isSale" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">是否为热卖(0:否 1:是)</td>
					<td><input type="text" id="isHot" name="gjfProductInfo.isHot" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">是否为抢购(0:否 1:是)</td>
					<td><input type="text" id="isQbuy" name="gjfProductInfo.isQbuy" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">是否为推荐(0:否 1:是)</td>
					<td><input type="text" id="isRecommend" name="gjfProductInfo.isRecommend" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品缩略图</td>
					<td><input type="text" id="imgUrl" name="gjfProductInfo.imgUrl" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">添加时间</td>
					<td><input type="text" id="addTime" name="gjfProductInfo.addTime" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">更新时间</td>
					<td><input type="text" id="updateTime" name="gjfProductInfo.updateTime" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">库存</td>
					<td><input type="text" id="repertory" name="gjfProductInfo.repertory" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品审核(0:未通过 1:通过 2:审核中)</td>
					<td><input type="text" id="aduitStatus" name="gjfProductInfo.aduitStatus" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品备注</td>
					<td><input type="text" id="remark" name="gjfProductInfo.remark" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品状态(0:下架 1:正常 2:违规)</td>
					<td><input type="text" id="status" name="gjfProductInfo.status" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">安全token</td>
					<td><input type="text" id="token" name="gjfProductInfo.token" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">para1</td>
					<td><input type="text" id="para1" name="gjfProductInfo.para1" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">para2</td>
					<td><input type="text" id="para2" name="gjfProductInfo.para2" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">para3</td>
					<td><input type="text" id="para3" name="gjfProductInfo.para3" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">para4</td>
					<td><input type="text" id="para4" name="gjfProductInfo.para4" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">para5</td>
					<td><input type="text" id="para5" name="gjfProductInfo.para5" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">para6</td>
					<td><input type="text" id="para6" name="gjfProductInfo.para6" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">para7</td>
					<td><input type="text" id="para7" name="gjfProductInfo.para7" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">para8</td>
					<td><input type="text" id="para8" name="gjfProductInfo.para8" /></td>
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