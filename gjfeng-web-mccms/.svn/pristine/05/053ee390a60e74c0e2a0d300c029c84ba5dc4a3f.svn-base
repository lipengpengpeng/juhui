
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>直销配置</title>
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
		<style>
		input {
		    width: 80px;
		}
		</style>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">当前位置: 设置 - 直销配置</div>
			<!-- <div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="history.back();"/></div> -->
		</div>

		<form action="sysSalesConfigAction!edit.action" method="post" id="sysSalesConfigForm" name="sysSalesConfigForm" >
			<input type="hidden" name="id" value="${sysSalesConfig.id}"/>
			<table  align="center" class="listTable3" >
				<tr>
					<td>分销等级</td>
					<td>
						<select name="sysSalesConfig.salesLevel">
							<option value="1">1</option>
							<option value="2" <c:if test="${sysSalesConfig.salesLevel == 2 }">selected</c:if>>2</option>
							<option value="3" <c:if test="${sysSalesConfig.salesLevel == 3 }">selected</c:if>>3</option>
						</select>&nbsp;&nbsp;级
					</td>
				</tr>
				<tr>
					<td colspan="10">&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td>分销级别</td>
					<td>一级</td>
					<td>二级</td>
					<td>三级</td>
				</tr>
				<tr>
					<td>分销比例（购物）</td>
					<td><input type="text" size="6" id="" name="sysSalesConfig.levelOneBuy" value="${sysSalesConfig.levelOneBuy}" /></td>
					<td><input type="text" size="6" id="" name="sysSalesConfig.levelTwoBuy" value="${sysSalesConfig.levelTwoBuy}" /></td>
					<td><input type="text" size="6" id="" name="sysSalesConfig.levelThreeBuy" value="${sysSalesConfig.levelThreeBuy}" /></td>
				</tr>
				<tr>
					<td>分销比例（创业会员）</td>
					<td><input type="text" size="6" id="" name="sysSalesConfig.levelOneUpgradeEntre" value="${sysSalesConfig.levelOneUpgradeEntre}" /></td>
					<td><input type="text" size="6" id="" name="sysSalesConfig.levelTwoUpgradeEntre" value="${sysSalesConfig.levelTwoUpgradeEntre}" /></td>
					<td><input type="text" size="6" id="" name="sysSalesConfig.levelThreeUpgradeEntre" value="${sysSalesConfig.levelThreeUpgradeEntre}" /></td>
				</tr>
				<tr>
					<td>分销比例（联盟会员）</td>
					<td><input type="text" size="6" id="" name="sysSalesConfig.levelOneUpgradeUnion" value="${sysSalesConfig.levelOneUpgradeUnion}" /></td>
					<td><input type="text" size="6" id="" name="sysSalesConfig.levelTwoUpgradeUnion" value="${sysSalesConfig.levelTwoUpgradeUnion}" /></td>
					<td><input type="text" size="6" id="" name="sysSalesConfig.levelThreeUpgradeUnion" value="${sysSalesConfig.levelThreeUpgradeUnion}" /></td>
				</tr>
				
				<%-- <tr>
					<td colspan="10">&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td>奖励标准</td>
					<td>星级</td>
					<td>钻石</td>
					<td>皇冠</td>
				</tr>
				<tr>
					<td>达到标准（元）</td>
					<td><input type="text" size="6" id="" name="sysSalesConfig.starStandard" value="${sysSalesConfig.starStandard}" /></td>
					<td><input type="text" size="6" id="" name="sysSalesConfig.diamondStandard" value="${sysSalesConfig.diamondStandard}" /></td>
					<td><input type="text" size="6" id="" name="sysSalesConfig.crownStandard" value="${sysSalesConfig.crownStandard}" /></td>
				</tr>
				
				<tr>
					<td colspan="10">&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td>区代理支出</td>
					<td>县代理支出</td>
					<td>市代理支出</td>
					<td>省代理支出</td>
					<td>代理平均</td>
				</tr>
				<tr>
					<td><input type="text" size="5" id="districtExp" name="sysSalesConfig.districtExp" value="${sysSalesConfig.districtExp}" /></td>
					<td><input type="text" size="5" id="countryExp" name="sysSalesConfig.countryExp" value="${sysSalesConfig.countryExp}" /></td>
					<td><input type="text" size="5" id="cityExp" name="sysSalesConfig.cityExp" value="${sysSalesConfig.cityExp}" /></td>
					<td><input type="text" size="5" id="provinceExp" name="sysSalesConfig.provinceExp" value="${sysSalesConfig.provinceExp}" /></td>
					<td><input type="text" size="5" id="avgExp" name="" value="" readonly="readonly" /></td>
				</tr>
				<tr>
					<td colspan="10">&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td>星级分红支出</td>
					<td>钻石分红支出</td>
					<td>皇冠分红支出</td>
					<td>团队总支出</td>
					<td>股东支出</td>
				</tr>
				<tr>
					<td><input type="text" size="5" id="" name="sysSalesConfig.starExp" value="${sysSalesConfig.starExp}" /></td>
					<td><input type="text" size="5" id="" name="sysSalesConfig.diamondExp" value="${sysSalesConfig.diamondExp}" /></td>
					<td><input type="text" size="5" id="" name="sysSalesConfig.crownExp" value="${sysSalesConfig.crownExp}" /></td>
					<td><input type="text" size="5" id="" name="sysSalesConfig.teamExp" value="${sysSalesConfig.teamExp}" /></td>
					<td><input type="text" size="5" id="" name="sysSalesConfig.partnerExp" value="${sysSalesConfig.partnerExp}" /></td>
				</tr> --%>
				
				<tr>
					<td colspan="10"><input type="submit" value='保存' /></td>
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
		$(function(){
			avgAgentRate();
		});
		function avgAgentRate(){
			var districtExp = parseFloat($("#districtExp").val());
			var countryExp = parseFloat($("#countryExp").val());
			var cityExp = parseFloat($("#cityExp").val());
			var provinceExp = parseFloat($("#provinceExp").val());
			
			var avgExp = (districtExp + countryExp + cityExp + provinceExp)*10000/4 ;
			avgExp = Math.round(avgExp)/10000
			
			$("#avgExp").val(avgExp);
		}
	</script>
</html>