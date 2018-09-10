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
			<div class="rpos">当前位置: 交易管理 - 提现列表 - 审核</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="javascript :history.back(-1);"/></div>
		</div>

		<table  align="center" class="listTable3">
				<tr>
					<td class="pn-flabel" width="100px">交易流水号</td>
					<td>${resultVo.result.tradeNo}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">申请金额</td>
					<td>${resultVo.result.applyMoney}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">交易金额</td>
					<td>${resultVo.result.tradeMoney}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">税收金额</td>
					<td>${resultVo.result.taxMoney}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">责任险金额</td>
					<td>${resultVo.result.insuranceMoney}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">添加时间</td>
					<td>
						<mtag:longToDate value="${resultVo.result.addTime}" pattern="yyyy-MM-dd HH:mm"/>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">审核时间</td>
					<td>
					<c:if test="${not empty resultVo.result.dealTime}">
						<mtag:longToDate value="${resultVo.result.dealTime}" pattern="yyyy-MM-dd HH:mm"/>
					</c:if>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">交易时间</td>
					<td>
					<c:if test="${not empty resultVo.result.tradeTime}">
						<mtag:longToDate value="${resultVo.result.tradeTime}" pattern="yyyy-MM-dd HH:mm"/>
					</c:if>
					</td>
				</tr>
				 <tr>
					<td class="pn-flabel" width="100px">提现用户</td>
					<td>${resultVo.result.memberName}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">提现银行卡号</td>
					<td>${resultVo.result.bankCard}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">持卡人姓名</td>
					<td>${resultVo.result.holder}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">银行名称</td>
					<td>${resultVo.result.bankName}</td>
				</tr>
				<%-- <tr>
					<td class="pn-flabel" width="100px">银行地址</td>
					<td>${resultVo.result.bankId.bankProvinceId.province}${resultVo.result.bankId.bankCityId.city}</td>
				</tr> --%>
				<tr>
					<td class="pn-flabel" width="100px">支行名称</td>
					<td>${resultVo.result.bankSub}</td>
				</tr>
				
				<tr>
					<td class="pn-flabel" width="100px">交易状态</td>
					<td>
					  <select id="tradeStatus">     
					        <option value="1">交易成功</option>
					        <option value="2">交易失败</option>
					   </select>
					   </td>
				</tr>
				
				<tr>
					<td></td>
					<td>
						<input type="button" onclick="auditStatus(${id},'${token}')" class="defaultButton" id="defaultButton" value=" 提 交 "/>
					</td>
				</tr>

		 </table>
	</body>
    <script> 
		   //提交审核信息
		   function auditStatus(id,token){
			   $.ajax({
				   url:"${ctx}/ajaxTradeInfo!auditWithdrawal.action",
				   method:"post",
				   data:{
					   id:id,
					   token:token,
					   tradeStatus:$("#tradeStatus").val(),
				   },
				   success:function(data){
					   if(data==200){
						   alert("审核成功");
						   window.location.href="${ctx}/subsystem/tradeInfoAction!findAllTrade.action";
					   }else{
						   alert("审核失败");
					   }
				   }
				   
			   })
		   }
		     
		</script>
</html>