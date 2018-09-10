
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
				$("#sysConfigRateForm").validate({
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
			<div class="rpos">当前位置: 设置 - 费率设置</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="history.back();"/></div>
		</div>

		<form action="sysConfigRateAction!editSysConfigRate.action" method="post" id="sysConfigRateForm" name="sysConfigRateForm" >
			<table  align="center" class="listTable3" >
				<input type="hidden" name="id" value="${sysConfigRate.id}"/>
				<tr>
					<td>星期</td>
					<td>一</td>
					<td>二</td>
					<td>三</td>
					<td>四</td>
					<td>五</td>
					<td>六</td>
					<td>日</td>
					<td>签到生成天数</td>
					<td>分红权（如:20进1填20）</td>
					<td>千分比</td>
					<!-- <td>设计</td> -->
				</tr>
				<tr>
					<td>签到费率</td>
					<td><input type="text" size="5" id="bigMonRate" name="sysConfigRate.bigMonRate" value="<fmt:formatNumber value='${sysConfigRate.bigMonRate}' pattern='##.#####'></fmt:formatNumber>" /></td>
					<td><input type="text" size="5" id="bigTueRate" name="sysConfigRate.bigTueRate" value="<fmt:formatNumber value='${sysConfigRate.bigTueRate}' pattern='##.#####'></fmt:formatNumber>" /></td>
					<td><input type="text" size="5" id="bigWedRate" name="sysConfigRate.bigWedRate" value="<fmt:formatNumber value='${sysConfigRate.bigWedRate}' pattern='##.#####'></fmt:formatNumber>" /></td>
					<td><input type="text" size="5" id="bigThuRate" name="sysConfigRate.bigThuRate" value="<fmt:formatNumber value='${sysConfigRate.bigThuRate}' pattern='##.#####'></fmt:formatNumber>" /></td>
					<td><input type="text" size="5" id="bigFriRate" name="sysConfigRate.bigFriRate" value="<fmt:formatNumber value='${sysConfigRate.bigFriRate}' pattern='##.#####'></fmt:formatNumber>" /></td>
					<td><input type="text" size="5" id="bigSatRate" name="sysConfigRate.bigSatRate" value="<fmt:formatNumber value='${sysConfigRate.bigSatRate}' pattern='##.#####'></fmt:formatNumber>" /></td>
					<td><input type="text" size="5" id="bigSunRate" name="sysConfigRate.bigSunRate" value="<fmt:formatNumber value='${sysConfigRate.bigSunRate}' pattern='##.#####'></fmt:formatNumber>" /></td>
					<td><input type="text" size="5" name="sysConfigRate.signDay" value="${sysConfigRate.signDay}" /></td>
					<td><input type="text" size="5" name="sysConfigRate.rankScale" value="${sysConfigRate.rankScale}" /></td>
					<td>‰</td>
					<!-- <td>
						<a href="javascript:avgBigRate();" style="color: blue;">试算</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:void(0);" onclick="$('#sysConfigRateForm').submit();" style="color: blue;">确定</a>
					</td> -->
				</tr>
				
				<%-- <tr>
					<td>普通消费费率</td>
					<td><input type="text" size="5" id="normalMonRate" name="sysConfigRate.normalMonRate" value="<fmt:formatNumber value='${sysConfigRate.normalMonRate}' pattern='##.#####'></fmt:formatNumber>" /></td>
					<td><input type="text" size="5" id="normalTueRate" name="sysConfigRate.normalTueRate" value="<fmt:formatNumber value='${sysConfigRate.normalTueRate}' pattern='##.#####'></fmt:formatNumber>" /></td>
					<td><input type="text" size="5" id="normalWedRate" name="sysConfigRate.normalWedRate" value="<fmt:formatNumber value='${sysConfigRate.normalWedRate}' pattern='##.#####'></fmt:formatNumber>" /></td>
					<td><input type="text" size="5" id="normalThuRate" name="sysConfigRate.normalThuRate" value="<fmt:formatNumber value='${sysConfigRate.normalThuRate}' pattern='##.#####'></fmt:formatNumber>" /></td>
					<td><input type="text" size="5" id="normalFriRate" name="sysConfigRate.normalFriRate" value="<fmt:formatNumber value='${sysConfigRate.normalFriRate}' pattern='##.#####'></fmt:formatNumber>" /></td>
					<td><input type="text" size="5" id="normalSatRate" name="sysConfigRate.normalSatRate" value="<fmt:formatNumber value='${sysConfigRate.normalSatRate}' pattern='##.#####'></fmt:formatNumber>" /></td>
					<td><input type="text" size="5" id="normalSunRate" name="sysConfigRate.normalSunRate" value="<fmt:formatNumber value='${sysConfigRate.normalSunRate}' pattern='##.#####'></fmt:formatNumber>" /></td>
					<td></td>
					<td></td>
					<td>‰</td>
					<td>
						<a href="javascript:avgNormalRate()" style="color: blue;">试算</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:void(0);" onclick="$('#sysConfigRateForm').submit();" style="color: blue;">确定</a>
					</td>
				</tr>
				
				<tr>
					<td>小额消费费率</td>
					<td><input type="text" size="5" id="smallMonRate" name="sysConfigRate.smallMonRate" value="<fmt:formatNumber value='${sysConfigRate.smallMonRate}' pattern='##.#####'></fmt:formatNumber>" /></td>
					<td><input type="text" size="5" id="smallTueRate" name="sysConfigRate.smallTueRate" value="<fmt:formatNumber value='${sysConfigRate.smallTueRate}' pattern='##.#####'></fmt:formatNumber>" /></td>
					<td><input type="text" size="5" id="smallWedRate" name="sysConfigRate.smallWedRate" value="<fmt:formatNumber value='${sysConfigRate.smallWedRate}' pattern='##.#####'></fmt:formatNumber>" /></td>
					<td><input type="text" size="5" id="smallThuRate" name="sysConfigRate.smallThuRate" value="<fmt:formatNumber value='${sysConfigRate.smallThuRate}' pattern='##.#####'></fmt:formatNumber>" /></td>
					<td><input type="text" size="5" id="smallFriRate" name="sysConfigRate.smallFriRate" value="<fmt:formatNumber value='${sysConfigRate.smallFriRate}' pattern='##.#####'></fmt:formatNumber>" /></td>
					<td><input type="text" size="5" id="smallSatRate" name="sysConfigRate.smallSatRate" value="<fmt:formatNumber value='${sysConfigRate.smallSatRate}' pattern='##.#####'></fmt:formatNumber>" /></td>
					<td><input type="text" size="5" id="smallSunRate" name="sysConfigRate.smallSunRate" value="<fmt:formatNumber value='${sysConfigRate.smallSunRate}' pattern='##.#####'></fmt:formatNumber>" /></td>
					<td></td>
					<td></td>
					<td>‰</td>
					<td>
						<a href="javascript:avgSmallRate();" style="color: blue;">试算</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:void(0);" onclick="$('#sysConfigRateForm').submit();" style="color: blue;">确定</a>
					</td>
				</tr>
				<tr>
					<td colspan="10">&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2">系统截留费用（单位：%）</td>
					<td colspan="3">支付定义设置（单位：元）</td>
					<td colspan="3">消费试算比率（单位：‰）</td>
					<td colspan="2">运算基数（单位：%）</td>
				</tr>
				<tr>
					<td>到账基数截留%</td>
					<td>红包点击截留%</td>
					<td>小额支付</td>
					<td>普通消费</td>
					<td>大宗交易</td>
					<td>小额支付</td>
					<td>普通消费</td>
					<td>大宗交易</td>
					<td>普通消费</td>
					<td>餐饮旅业</td>
				</tr>
				<tr>
					<td><input type="text" size="5" id="sysFeeRate" name="sysConfigRate.sysFeeRate" value="${sysConfigRate.sysFeeRate}" /></td>
					<td><input type="text" size="5" id="sysRedbagRate" name="sysConfigRate.sysRedbagRate" value="${sysConfigRate.sysRedbagRate}" /></td>
					<td><input type="text" size="5" id="smallFeeNum" name="sysConfigRate.smallFeeNum" value="${sysConfigRate.smallFeeNum}" /></td>
					<td><input type="text" size="5" id="normalFeeNum" name="sysConfigRate.normalFeeNum" value="${sysConfigRate.normalFeeNum}" /></td>
					<td><input type="text" size="5" id="bigFeeNum" name="sysConfigRate.bigFeeNum" value="${sysConfigRate.bigFeeNum}" /></td>
					<td id="avgSmallRate" style="color: blue;">${avgSmallRate}</td>
					<td id="avgNormalRate" style="color: blue;">${avgNormalRate}</td>
					<td id="avgBigRate" style="color: blue;">${avgBigRate}</td>
					<td><input type="text" size="5" id="yunsuanNormalFee" name="sysConfigRate.yunsuanNormalFee" value="${sysConfigRate.yunsuanNormalFee}" /></td>
					<td><input type="text" size="5" id="yunsuanFoodTourFee" name="sysConfigRate.yunsuanFoodTourFee" value="${sysConfigRate.yunsuanFoodTourFee}" /></td>
				</tr>
				<tr>
					<td colspan="10">&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td colspan="10">系统积分收入波动调节阀（积分=贝壳）</td>
				</tr>
				<tr>
					<td>增加%</td>
					<td><input type="text" size="5" id="pointWareIncrease1" name="sysConfigRate.pointWareIncrease1" value="${sysConfigRate.pointWareIncrease1}" /></td>
					<td><input type="text" size="5" id="pointWareIncrease2" name="sysConfigRate.pointWareIncrease2" value="${sysConfigRate.pointWareIncrease2}" /></td>
					<td><input type="text" size="5" id="pointWareIncrease3" name="sysConfigRate.pointWareIncrease3" value="${sysConfigRate.pointWareIncrease3}" /></td>
					<td>周期/天</td>
					<td>减少%</td>
					<td><input type="text" size="5" id="pointWareReduce1" name="sysConfigRate.pointWareReduce1" value="${sysConfigRate.pointWareReduce1}" /></td>
					<td><input type="text" size="5" id="pointWareReduce2" name="sysConfigRate.pointWareReduce2" value="${sysConfigRate.pointWareReduce2}" /></td>
					<td><input type="text" size="5" id="pointWareReduce3" name="sysConfigRate.pointWareReduce3" value="${sysConfigRate.pointWareReduce3}" /></td>
					<td>周期/天</td>
				</tr>
				<tr>
					<td>上升‰</td>
					<td><input type="text" size="5" id="pointWareIncRate1" name="sysConfigRate.pointWareIncRate1" value="${sysConfigRate.pointWareIncRate1}" /></td>
					<td><input type="text" size="5" id="pointWareIncRate2" name="sysConfigRate.pointWareIncRate2" value="${sysConfigRate.pointWareIncRate2}" /></td>
					<td><input type="text" size="5" id="pointWareIncRate3" name="sysConfigRate.pointWareIncRate3" value="${sysConfigRate.pointWareIncRate3}" /></td>
					<td><input type="text" size="5" id="pointWareIncDays" name="sysConfigRate.pointWareIncDays" value="${sysConfigRate.pointWareIncDays}" /></td>
					<td>下降‰</td>
					<td><input type="text" size="5" id="pointWareReduceRate1" name="sysConfigRate.pointWareReduceRate1" value="${sysConfigRate.pointWareReduceRate1}" /></td>
					<td><input type="text" size="5" id="pointWareReduceRate2" name="sysConfigRate.pointWareReduceRate2" value="${sysConfigRate.pointWareReduceRate2}" /></td>
					<td><input type="text" size="5" id="pointWareReduceRate3" name="sysConfigRate.pointWareReduceRate3" value="${sysConfigRate.pointWareReduceRate3}" /></td>
					<td><input type="text" size="5" id="pointWareReduceDays" name="sysConfigRate.pointWareReduceDays" value="${sysConfigRate.pointWareReduceDays}" /></td>
				</tr> --%>
				<tr>
					<td colspan="11">&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td colspan="11"><input type="submit" value='保存' /></td>
				</tr>

		 	</table>
		</form>
		<style>
			.listTable3 tr td {
				text-align: center;
			}
		</style>
	</body>
	<script>
		function avgBigRate(){
			var bigMonRate = parseFloat($("#bigMonRate").val());
			var bigTueRate = parseFloat($("#bigTueRate").val());
			var bigWedRate = parseFloat($("#bigWedRate").val());
			var bigThuRate = parseFloat($("#bigThuRate").val());
			var bigFriRate = parseFloat($("#bigFriRate").val());
			var bigSatRate = parseFloat($("#bigSatRate").val());
			var bigSunRate = parseFloat($("#bigSunRate").val());
			
			var avgBigRateNum = bigMonRate + bigTueRate + bigWedRate 
			+ bigThuRate + bigFriRate + bigSatRate + bigSunRate;
			
			avgBigRateNum = avgBigRateNum/7;
			
			$("#avgBigRate").html(avgBigRateNum.toFixed(2));
		}
		
		function avgNormalRate(){
			var normalMonRate = parseFloat($("#normalMonRate").val());
			var normalTueRate = parseFloat($("#normalTueRate").val());
			var normalWedRate = parseFloat($("#normalWedRate").val());
			var normalThuRate = parseFloat($("#normalThuRate").val());
			var normalFriRate = parseFloat($("#normalFriRate").val());
			var normalSatRate = parseFloat($("#normalSatRate").val());
			var normalSunRate = parseFloat($("#normalSunRate").val());
			
			var avgNormalRateNum = normalMonRate + normalTueRate + normalWedRate 
			+ normalThuRate + normalFriRate + normalSatRate + normalSunRate;
			
			avgNormalRateNum = avgNormalRateNum/7;
			
			$("#avgNormalRate").html(avgNormalRateNum.toFixed(2));
		}
		
		function avgSmallRate(){
			var smallMonRate = parseFloat($("#smallMonRate").val());
			var smallTueRate = parseFloat($("#smallTueRate").val());
			var smallWedRate = parseFloat($("#smallWedRate").val());
			var smallThuRate = parseFloat($("#smallThuRate").val());
			var smallFriRate = parseFloat($("#smallFriRate").val());
			var smallSatRate = parseFloat($("#smallSatRate").val());
			var smallSunRate = parseFloat($("#smallSunRate").val());

			var avgSmallRateNum = smallMonRate + smallTueRate + smallWedRate 
			+ smallThuRate + smallFriRate + smallSatRate + smallSunRate;
			
			avgSmallRateNum = avgSmallRateNum/7;
			
			$("#avgSmallRate").html(avgSmallRateNum.toFixed(2));
		}
	</script>
</html>