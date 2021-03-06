﻿<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
		<style>
			.headbg1 {
				background: #c9e6f5;
				font-weight: bold;
				padding-left: 15px;
			}
			
			.headbg1 td {
				font-weight: bold;
				text-align: center;
			}
		</style>

		<script type="text/javascript">
				function doYouWantToExport() {
					if(confirm('确定要导出所有记录吗?')) {
						return true;
					}
					return false;
				}
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 交易管理 - 订单列表
			</div>
			<!-- <div class="ropt">
				<a href="gjfOrderGoodsAction!newPage.action">新增</a>
			</div> -->
			 <div class="ropt"><a href="orderInfoAction!findAllOrderInfo.action?orderSn=${orderSn }&storeName=${storeName }&goodsName=${goodsName }&name=${name }&nickName=${nickName }&payType=${payType }&orderType=${orderType }&orderStatus=${orderStatus }&startDate=${startDate }&endDate=${endDate }&ste=1" onclick="return doYouWantToExport();">导出</a></div> 
		</div>
		<form action="orderInfoAction!findAllOrderInfo.action" method="post">
			<table class="listTable2">
				<tr>
					<td>
						订单号：<input type="text" placeholder="请输入订单号" name="orderSn" value="${orderSn }"/>&nbsp;&nbsp;
						店铺名称：<input type="text" placeholder="请输入店铺名称" name="storeName" value="${storeName }"/>&nbsp;&nbsp;
						商品名称：<input type="text" placeholder="请输入商品名称" name="goodsName" value="${goodsName }"/>&nbsp;&nbsp;
						用户名称：<input type="text" placeholder="请输入用户名称" name="name" value="${name }"/>&nbsp;&nbsp;
						<br/>
						用户昵称：<input type="text" placeholder="请输入用户昵称" name="nickName" value="${nickName }"/>&nbsp;&nbsp;
						
						开始时间：<input type="text" placeholder="请选择开始时间" name="startDate" value="${startDate }" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width: 150px;"/>&nbsp;&nbsp;
						结束时间：<input type="text" placeholder="请选择结束时间" name="endDate" value="${endDate }" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width: 150px;"/>&nbsp;&nbsp;
						支付方式：
						 <select name="payType" >
							 	<option value="" <c:if test="${empty payType}">selected="selected"</c:if>>全部</option>
					   			<option value="0" <c:if test="${payType eq '0'}">selected="selected"</c:if>>线上余额</option>
				  				<option value="1" <c:if test="${payType eq '1'}">selected="selected"</c:if>>微信支付</option>
				 				<option value="2" <c:if test="${payType eq '2'}">selected="selected"</c:if>>支付宝</option>
				 				<option value="3" <c:if test="${payType eq '3'}">selected="selected"</c:if>>银联支付</option>
				  				<option value="4" <c:if test="${payType eq '4'}">selected="selected"</c:if>>余额+微信</option>
				 				<option value="5" <c:if test="${payType eq '5'}">selected="selected"</c:if>>余额+支付宝</option>
				 				<option value="6" <c:if test="${payType eq '6'}">selected="selected"</c:if>>余额+银联</option>
				  				<option value="7" <c:if test="${payType eq '7'}">selected="selected"</c:if>>待领消费金额支付</option>
				  				<option value="8" <c:if test="${payType eq '8'}">selected="selected"</c:if>>责任消费金额支付</option>
				  				<option value="9" <c:if test="${payType eq '9'}">selected="selected"</c:if>>凤凰宝</option>
				  				<option value="10" <c:if test="${payType eq '10'}">selected="selected"</c:if>>代金券</option>
		                 </select>&nbsp;&nbsp;
		                               订单类型： 
                          <select name="orderType">
							 	<option value=""  <c:if test="${empty orderType}">selected="selected"</c:if>>全部</option>
					   			<option value="0" <c:if test="${orderType eq '0'}">selected="selected"</c:if>>O2O</option>
					  			<option value="1" <c:if test="${orderType eq '1'}">selected="selected"</c:if>>商城</option>
                 		  </select>&nbsp;&nbsp;
                 		<br/>订单状态： 
                          <select name="orderStatus">
							 	<option value=""  <c:if test="${empty orderStatus}">selected="selected"</c:if>>全部</option>
					   			<option value="0" <c:if test="${orderStatus eq '0'}">selected="selected"</c:if>>未付款</option>
					  			<option value="1" <c:if test="${orderStatus eq '1'}">selected="selected"</c:if>>已付款</option>
					  			<option value="2" <c:if test="${orderStatus eq '2'}">selected="selected"</c:if>>已发货</option>
					  			<option value="3" <c:if test="${orderStatus eq '3'}">selected="selected"</c:if>>已收货</option>
					  			<option value="4" <c:if test="${orderStatus eq '4'}">selected="selected"</c:if>>已过期</option>
					  			<option value="5" <c:if test="${orderStatus eq '5'}">selected="selected"</c:if>>已取消</option>
					  			<option value="6" <c:if test="${orderStatus eq '6'}">selected="selected"</c:if>>已退款</option>
					  			<option value="6" <c:if test="${orderStatus eq '7'}">selected="selected"</c:if>>已结算</option>
                 		  </select>&nbsp;&nbsp;
                 		商品来源： 
                          <select name="goodSource">
							 	<option value=""  <c:if test="${empty orderStatus}">selected="selected"</c:if>>全部</option>
					   			<option value="0" <c:if test="${suorceOrder eq '0'}">selected="selected"</c:if>>平台</option>
					  			<option value="2" <c:if test="${suorceOrder eq '2'}">selected="selected"</c:if>>京东</option>		  			
                 		  </select>&nbsp;&nbsp;
                 		  京东订单号：<input type="text" placeholder="请输入订单号" name="jdOrderSn" value="${jdOrderSn }"/>&nbsp;&nbsp;
		                <input type="submit" style="margin-left:30px;" class="default_button" value='<s:text name="common.word.search" />' />
					</td>
				</tr>
			</table>
		</form>		
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>订单号</td>
				<td>添加时间</td>
				<td>用户名</td>
				<td>用户昵称</td>
				<td>店铺名称</td>
				<td>商品名称</td>
				<td>商品属性</td>
				<td>购买数量</td>
				<td>商品单价格</td>
				<td>线上支付金额</td>
				<td>线下支付金额</td>
				<td>用户昵称</td>
				<td>支付方式</td>
				<td>订单类型</td>
				<td>订单状态</td>
				<td>京东单号</td>
				<td>配送方式</td>
				<td>是否采购</td>
				<!-- <td>促销活动ID（团购ID/限时折扣ID/优惠套装ID）与goods_type搭配使用</td>
				<td>店铺到账金额</td>
				<td>店铺让利金额</td> -->

				 <td>操作</td>
			</tr>

			<c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.orderSn}</td>
					<td><fmt:formatDate value="${bean.addTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${bean.memName}</td>
					<td>${bean.nickName}</td>
					<td>${bean.storeName}</td>
					<td>${bean.goosName}</td>
					<td>${bean.goodAttr}</td>
					<td>${bean.goodNum}</td>
					<td>${bean.goodsAmount}</td>
					<td>${bean.onlinePayAmount}</td>
					<td>${bean.offlinePayAmount}</td>
					<td>${bean.nickName}</td>
					
					<%-- <c:if test="${empty bean.payType}">
					   
					    <td></td>
					</c:if> --%>
					<c:if test="${bean.payType eq '0'}">
					    <td>线上余额</td>
					</c:if>
					<c:if test="${bean.payType eq '1'}">
					    <td>微信支付</td>
					</c:if>
					<c:if test="${bean.payType eq '2'}">
					    <td>支付宝</td>
					</c:if>
					<c:if test="${bean.payType eq '3'}">
					    <td>银联支付</td>
					</c:if>
					<c:if test="${bean.payType eq '4'}">
					    <td>余额+微信</td>
					</c:if>
					<c:if test="${bean.payType eq '5'}">
					    <td>余额+支付宝</td>
					</c:if>
					<c:if test="${bean.payType eq '6'}">
					    <td>余额+银联</td>
					</c:if>
					<c:if test="${bean.payType eq '7'}">
					    <td>待领消费金额</td>
					</c:if>
					<c:if test="${bean.payType eq '8'}">
					    <td>责任消费金额</td>
					</c:if>
					<c:if test="${bean.payType eq '9'}">
					    <td>凤凰宝</td>
					</c:if>
					<c:if test="${bean.payType eq '10'}">
					    <td>代金券</td>
					</c:if>
					
					<c:if test="${bean.orderType eq '0'}">
					    <td>o2o</td>
					</c:if>
					<c:if test="${bean.orderType eq '1'}">
					    <td>商城</td>
					</c:if>
					
					<c:if test="${bean.orderStatus eq '0'}">
					    <td>未付款</td>
					</c:if>
					<c:if test="${bean.orderStatus eq '1'}">
					    <td>已付款</td>
					</c:if>
					<c:if test="${bean.orderStatus eq '2'}">
					    <td>已发货</td>
					</c:if>
					<c:if test="${bean.orderStatus eq '3'}">
					    <td>已收货</td>
					</c:if>
					<c:if test="${bean.orderStatus eq '4'}">
					    <td>已过期</td>
					</c:if>
					<c:if test="${bean.orderStatus eq '5'}">
					    <td>已取消</td>
					</c:if>
					<c:if test="${bean.orderStatus eq '6'}">
					    <td>已退款</td>
					</c:if>	
					<c:if test="${bean.orderStatus eq '7'}">
					    <td>已结算</td>
					</c:if>	
					
					<td>${bean.jdOrderSn}</td>	
					<c:if test="${bean.logist eq '0'}">
					    <td>快递</td>
					</c:if>	
					<c:if test="${bean.logist eq '1'}">
					    <td>物流</td>
					</c:if>	
					<c:if test="${bean.isWholesalse eq '0'}">
					    <td>否</td>
					</c:if>	
					<c:if test="${bean.isWholesalse eq '1'}">
					    <td>是</td>
					</c:if>												
					<td>
					
					<security:authorize ifAnyGranted="GJF_TRADE_ORDER_LIST_VIEW">
					   <a href="orderInfoAction!findOrderInfo.action?orderId=${bean.id}&orderSn=${bean.orderSn}&token=${bean.token}">查看</a>
						&nbsp; &nbsp;
					</security:authorize>
					<security:authorize ifAnyGranted="GJF_TRADE_ORDER_BACK">
						<c:if test="${bean.orderType eq 1 && bean.orderStatus eq 1}">
						   <a href="#" onclick="orderBack('${bean.id}','${bean.orderSn}','${bean.token}')">退单</a>
							&nbsp; &nbsp;
						</c:if>
					</security:authorize>
					<security:authorize ifAnyGranted="GJF_TRADE_ORDER_LIST_WRITER">
					   <c:if test="${bean.orderType eq 1 && bean.orderStatus eq 1}">
					    <a href="orderInfoAction!goAddAddress.action?orderSn=${bean.orderSn}&token=${bean.token}">填写发货单</a>
					    &nbsp; &nbsp;						
					   </c:if>
					  
					</security:authorize>
					
					<a href="${ctx}/subsystem/orderInfoAction!closeOrder.action?tradeNo=${bean.orderSn}&token=${bean.token}"
									onclick="{if(confirm('你确定要关闭订单吗?')){return true;}return false;}">关闭订单</a>
							
					</td>
					
					

					<%-- <td>
						<a href="gjfOrderGoodsAction!viewPage.action?id=${bean.id}">查看</a>
						&nbsp; &nbsp;
						<a href="gjfOrderGoodsAction!retrieveGjfOrderGoodsById.action?id=${bean.id}">编辑</a>
						&nbsp; &nbsp;
						<a href="gjfOrderGoodsAction!delGjfOrderGoods.action?id=${bean.id}"
								onclick="{if(confirm('你真的要删除吗?')){return true;}return false;}">删除</a>
					</td> --%>
				</tr>
			</c:forEach>

		</table>

		<c:if test="${not empty pager.resultList}">
			<s:form action="orderInfoAction!findAllOrderInfo.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
					<input type="hidden"  name="orderSn" value="${orderSn }"/>
					<input type="hidden"  name="storeName" value="${storeName }"/>
					<input type="hidden"  name="goodsName" value="${goodsName }"/>
					<input type="hidden"  name="name" value="${name }"/>
					<input type="hidden"  name="nickName" value="${nickName }"/>
					<input type="hidden"  name="startDate" value="${startDate }"/>
					<input type="hidden"  name="endDate" value="${endDate }"/>
					 <select name="payType" style="display: none;">
							 	<option value="" <c:if test="${empty payType}">selected="selected"</c:if>>全部</option>
					   			<option value="0" <c:if test="${payType eq '0'}">selected="selected"</c:if>>线上余额</option>
				  				<option value="1" <c:if test="${payType eq '1'}">selected="selected"</c:if>>微信支付</option>
				 				<option value="2" <c:if test="${payType eq '2'}">selected="selected"</c:if>>支付宝</option>
				 				<option value="3" <c:if test="${payType eq '3'}">selected="selected"</c:if>>银联支付</option>
				  				<option value="4" <c:if test="${payType eq '4'}">selected="selected"</c:if>>余额+微信</option>
				 				<option value="5" <c:if test="${payType eq '5'}">selected="selected"</c:if>>余额+支付宝</option>
				 				<option value="6" <c:if test="${payType eq '6'}">selected="selected"</c:if>>余额+银联</option>
				  				<option value="7" <c:if test="${payType eq '7'}">selected="selected"</c:if>>待领消费金额支付</option>
				  				<option value="8" <c:if test="${payType eq '8'}">selected="selected"</c:if>>责任消费金额支付</option>
		                 </select>
                          <select name="orderType"  style="display: none;">
							 	<option value=""  <c:if test="${empty orderType}">selected="selected"</c:if>>全部</option>
					   			<option value="0" <c:if test="${orderType eq '0'}">selected="selected"</c:if>>O2O</option>
					  			<option value="1" <c:if test="${orderType eq '1'}">selected="selected"</c:if>>商城</option>
                 		  </select>
                          <select name="orderStatus"  style="display: none;">
							 	<option value=""  <c:if test="${empty orderStatus}">selected="selected"</c:if>>全部</option>
					   			<option value="0" <c:if test="${orderStatus eq '0'}">selected="selected"</c:if>>未付款</option>
					  			<option value="1" <c:if test="${orderStatus eq '1'}">selected="selected"</c:if>>已付款</option>
					  			<option value="2" <c:if test="${orderStatus eq '2'}">selected="selected"</c:if>>已发货</option>
					  			<option value="3" <c:if test="${orderStatus eq '3'}">selected="selected"</c:if>>已收货</option>
					  			<option value="4" <c:if test="${orderStatus eq '4'}">selected="selected"</c:if>>已过期</option>
					  			<option value="5" <c:if test="${orderStatus eq '5'}">selected="selected"</c:if>>已取消</option>
					  			<option value="6" <c:if test="${orderStatus eq '6'}">selected="selected"</c:if>>已取消</option>
                 		  </select>
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	<script type="text/javascript">
				function orderBack(orderId,orderSn,token) {
					if(confirm('确定退单吗?')) {
						$.ajax({
							url:'${ctx}/subsystem/ajaxOrderInfoAction!orderBack.action',
							type:'post',
							data:{
								orderSn:orderSn,
								token:token
							},
							success:function(data){
								var obj = eval('(' + data + ')');
								console.log(data);
								alert(obj.msg);
							}
						})
					}
				}
		</script>
	</body>
</html>