<%@ page contentType="text/html;charset=UTF-8"%>

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
			
			.totalBe{

			   width: 119.4px;
               display: inline-block;
               text-align: center
			}
			
			.totalShop{

			   width: 118.3px;
               display: inline-block;
               text-align: center
			}
			.totalShou{

			   width: 118.4px;
               display: inline-block;
               text-align: center
			}
			
			.totalPay{

			   width: 118.6px;
               display: inline-block;
               text-align: center
			}
			.totalMemDi{

			   width: 118.6px;
               display: inline-block;
               text-align: center
			}
			.totalMcDi{

			   width: 118.9px;
               display: inline-block;
               text-align: center
			}
			.totalDr{
			  
			   width: 118.2px;
               display: inline-block;
               text-align: center			
			}
			.totalYE{

			   width: 117px;
               display: inline-block;
               text-align: center
			}
			.totalDe{

			   width: 119px;
               display: inline-block;
               text-align: center				
			}
			.totalMeYe{

			   width: 119px;
               display: inline-block;
               text-align: center
			}
			.totalZE{

			   width: 118px;
               display: inline-block;
               text-align: center
			}
			.totalYiS{

			   width: 118px;
               display: inline-block;
               text-align: center
			}
			.totalDaiLi{
			   width: 58px;
               display: inline-block;
               text-align: center
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
	<body style="width: 8850px">
		<div class="rhead">
			<div class="rpos">
				当前位置: 总报表
			</div>
			<div class="clear"></div>
			<%-- <div class="ropt"><a href="countInfoAction!toSummaryReport.action?addTime=${addTime }&endTime=${endTime }&ste=1" onclick="return doYouWantToExport();">导出</a></div> --%>
		</div>			
		<form action="countInfoAction!countTotalDataByTime.action" method="get">
			<table class="listTable2">
				<tr>
					<td>
						开始日期：<input type="text" readonly="readonly" placeholder="请选择日期" value="${addTime }" name="addTime" onclick="WdatePicker()" style="width: 75px;"/>&nbsp;&nbsp;&nbsp;
						-&nbsp;&nbsp;&nbsp;	
						结束日期：<input type="text" readonly="readonly" placeholder="请选择日期" value="${endTime }" name="endTime" onclick="WdatePicker()" style="width: 75px;"/>&nbsp;&nbsp;&nbsp;				
						<input type="submit" style="margin-left:30px;" class="default_button" value='<s:text name="common.word.search" />' />
					</td>
				</tr>
			</table>
		</form>				
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
		   <!-- 第一列 -->
		   <tr id="nc" class="headbg">
		        <td style="width: 80px"></td>
				<td style="width: 750px;background-color: #C00000">销售录入</td>
				<td style="width: 630px;background-color: #FFC000">商城消费</td>
				<td style="width: 875px;background-color: #FFFF00">授信充值</td>
				<td style="width: 1200px;background-color: #92D050">支付总额</td>
				<td style="width: 750px;background-color: #00B050">会员分红</td>
				<td style="width: 750px;background-color: #00B0F0">商户分红</td>
				<td style="width: 250px;background-color: #0070C0">直推奖励</td>
				<td style="width: 500px;background-color: #8EA9DB">总余额</td>
				<td style="width: 630px;background-color: #D9E1F2">提现</td>
				<td style="width: 500px;background-color: #E2EFDA">会员余额</td>
				<td style="width: 380px;background-color: #C6E0B4">责任消费</td>
				<td style="width: 120px;background-color: #FCE4D6">会员人数</td>
				<td style="width: 120px;background-color: #F8CBAD">商家数量</td>
				<td style="width: 380px;background-color: #F4B084">代理收入</td>
				<td style="width: 120px;background-color: #C65911">会员商户池</td>
				<td style="width: 120px;background-color: #FFE699">平台池</td>
				<td style="width: 120px;background-color: #FFD966">收款</td>
				<td style="width: 120px;background-color: #BF8F00">支出</td>
				<td style="width: 250px;background-color: #E2EFDA">营收</td>
			</tr>
			
			<!-- 第二列 -->
			<tr >	
			    <td style="width: 80px">累计</td>			
                <td style="width: 750px">
                   <span  class="totalBe">${resultVo.result.benerfigTradeMoney}</span>
				   <span  class="totalBe">${resultVo.result.benerfitDateMoney}</span>
				   <span  class="totalBe">${resultVo.result.benerfitWeipayMoney}</span>
				   <span  class="totalBe">${resultVo.result.benerfitYinlMoney}</span>
				   <span  class="totalBe">${resultVo.result.benerfitSxMoney}</span>
				   <span  class="totalBe">${resultVo.result.benerfitZhibaoMoney}</span>					   
                </td>
				<td style="width: 630px">
				   <span  class="totalShop">${resultVo.result.mallWeiPayMoney}</span>
				   <span  class="totalShop">${resultVo.result.mallYinlPay}</span>
				   <span  class="totalShop">${resultVo.result.mallZenrnPayMoney}</span>
				   <span  class="totalShop">${resultVo.result.mallBanlanMoney}</span>
				   <span  class="totalShop">${resultVo.result.mallZhibaoMoney}</span>
				</td>
				<td style="width: 875px">
                   <span  class="totalShou"><fmt:formatNumber value="${resultVo.result.shouxinLinesMoney}" maxFractionDigits="4"></fmt:formatNumber></span>
				   <span  class="totalShou">${resultVo.result.shouxinWeixinPayMoney}</span>
				   <span  class="totalShou">${resultVo.result.shouxinYinlPayMoney}</span>
				   <span  class="totalShou">${resultVo.result.shouxinHouTaiPayMoney}</span>
				   <span  class="totalShou"><fmt:formatNumber value="${resultVo.result.shouxinCousumptionMoney}" maxFractionDigits="4"></fmt:formatNumber></span>
				   <span  class="totalShou">${resultVo.result.shouxinShenyuMoney}</span>
				    <span  class="totalShou">${resultVo.result.shouxinZhibaoMoney}</span>
                </td>
				<td style="width: 1200px">
				   <span  class="totalPay">${resultVo.result.totalWeiPayMoney}</span>
				   <span  class="totalPay">${resultVo.result.totalWeixShouxupayMoney}</span>
				   <span  class="totalPay">${resultVo.result.totalWeixinGetMoney}</span>
				   <span  class="totalPay">${resultVo.result.totalYinlpayMoney}</span>
				   <span  class="totalPay">${resultVo.result.totalYinlShouyuMoney}</span>
				   <span  class="totalPay">${resultVo.result.totalYinlGetmoney}</span>
				   <span  class="totalPay">${resultVo.result.totalZhifubaoMoney}</span>
				   <span  class="totalPay">${resultVo.result.totalZhifubaoShouxupayMoney}</span>
				   <span  class="totalPay">${resultVo.result.totalZhifubaoGetMoney}</span>
				</td>
				<td style="width: 750px">
                   <span  class="totalMemDi"></span>
                   <span  class="totalMemDi"></span>
				   <span  class="totalMemDi"></span>
				   <span  class="totalMemDi">${resultVo.result.memberShiJipayMoney}</span>
				   <span  class="totalMemDi">${resultVo.result.memberPoolMoney}</span>
				   <span  class="totalMemDi">${resultVo.result.memberChayiMoney}</span>
                </td>
				<td style="width: 750px">
				   <span  class="totalMcDi"></span>
                   <span  class="totalMcDi"></span>
				   <span  class="totalMcDi"></span>
				   <span  class="totalMcDi">${resultVo.result.merchantsShiJiPay}</span>
				   <span  class="totalMcDi">${resultVo.result.merchantsPoolMoney}</span>
				   <span  class="totalMcDi">${resultVo.result.merchantsChaYiMoney}</span>
				</td>
				<td style="width: 250px;" >
				   <span  class="totalDr">${resultVo.result.zhituiMemberMoney}</span>
                   <span  class="totalDr">${resultVo.result.zhituiMerchantsMoney}</span>				   
				</td>
				<td style="width: 500px;">
				    <span  class="totalYE">${resultVo.result.totalBanlanDateMoney}</span>
                    <span  class="totalYE">${resultVo.result.totalBanlanPoolMoney}</span>
                    <span  class="totalYE">${resultVo.result.totalBanlanZhituiMoney}</span>
                    <span  class="totalYE">${resultVo.result.totalBanlanChayiMoney}</span>
				</td>
				<td style="width: 630px">
				    <span  class="totalDe">${resultVo.result.withdrawalMoney}</span>
                    <span  class="totalDe">${resultVo.result.withdrawalShuishouMoney}</span>
                    <span  class="totalDe">${resultVo.result.withdrawalZenrenMoney}</span>
                    <span  class="totalDe">${resultVo.result.withdrawalDaozMoney}</span>
                    <span  class="totalDe">${resultVo.result.withdrawalShouxuMoney}</span>
				</td>
				<td style="width: 500px">				
				    <span  class="totalMeYe">${resultVo.result.memberBanlanFafangMoney}</span>
                    <span  class="totalMeYe">${resultVo.result.memberBanlanHuaChuMoney}</span>
                    <span  class="totalMeYe">${resultVo.result.memberBanlanXiaofeiMoney}</span>
                    <span  class="totalMeYe">${resultVo.result.memberBanlanShenYuMoney}</span>
				</td>
				<td style="width: 380px">
                    <span  class="totalZE">${resultVo.result.zenrenDateMoney}</span>
                    <span  class="totalZE">${resultVo.result.zenrenXiaoHaoMoney}</span>
                    <span  class="totalZE">${resultVo.result.zenrenshenyumoney}</span>
                </td>
				<td style="width: 120px;">${resultVo.result.memberNumber}</td>
				<td style="width: 120px;">${resultVo.result.merchantsNumber}</td>
				<td style="width: 380px;">
				　　　　<span  class="totalDaiLi">${resultVo.result.geDaiMoney}</span>
				　　　　<span  class="totalDaiLi">${resultVo.result.ereaDaiMoney}</span>
				　　　　<span  class="totalDaiLi">${resultVo.result.cityDaiMoney}</span>
				</td>
				<td style="width: 120px;">${resultVo.result.merchantsPoolTotalYiShou}</td>
				<td style="width: 120px;"><fmt:formatNumber value="${resultVo.result.pingtaiTotalYiShou}"></fmt:formatNumber></td>
				<td style="width: 120px;">${resultVo.result.shijiShouKuaiMoney}</td>
				<td style="width: 120px;">${resultVo.result.zhichuShiJipayMoney}</td>
				<td style="width: 250px;">
				   <span  class="totalYiS"><fmt:formatNumber value="${resultVo.result.yishouYUYInshouMoney}" maxFractionDigits="4"></fmt:formatNumber></span>
				   <span  class="totalYiS">${resultVo.result.yishouShiJiYiShou}</span>
				</td>
							
			</tr>
												
			<!-- 第三列 -->		
			<tr >	
			    <td style="width: 80px">日期</td>			
                <td style="width: 750px;background-color: #C00000">
                   <span class="totalBe">交易额</span>
				   <span class="totalBe">日营业额</span>
				   <span class="totalBe">微信支付金额</span>
				   <span class="totalBe">银联支付金额</span>
				   <span class="totalBe">授信支付金额</span>
				    <span class="totalBe">支付宝支付金额</span>
                </td>
				<td style="width: 630px;background-color: #FFC000">
				   <span class="totalShop">微信支付金额</span>
				   <span class="totalShop">银联支付金额</span>
				   <span class="totalShop">责任消费金额</span>
				   <span class="totalShop">余额</span>
				   <span class="totalShop">支付宝支付金额</span>
				</td>
				<td style="width: 875px;background-color: #FFFF00">
                   <span class="totalShou">充值额度</span>
				   <span class="totalShou">微信支付</span>
				   <span class="totalShou">银联支付</span>
				   <span class="totalShou">后台充值</span>				   
				   <span class="totalShou">消耗额度</span>
				   <span class="totalShou">剩余</span>
				   <span class="totalShou">支付宝支付</span>
                </td>
				<td style="width: 1200px;background-color: #92D050">
				   <span class="totalPay">微信支付总额</span>
				   <span class="totalPay">手续费</span>
				   <span class="totalPay">到账金额</span>
				   <span class="totalPay">银联支付总额</span>
				   <span class="totalPay">手续费</span>
				   <span class="totalPay">到账金额</span>
				   <span class="totalPay">支付宝支付总额</span>
				   <span class="totalPay">手续费</span>
				   <span class="totalPay">到账金额</span>
				</td>
				<td style="width: 750px;background-color: #00B050">
                   <span class="totalMemDi">新增</span>
                   <span class="totalMemDi">可参与分红权数</span>
				   <span class="totalMemDi">单价</span>
				   <span class="totalMemDi">实际发放金额</span>
				   <span class="totalMemDi">会员池收入</span>
				   <span class="totalMemDi">差异</span>
                </td>
				<td style="width: 750px;background-color: #00B0F0">
				   <span class="totalMcDi">新增</span>
                   <span class="totalMcDi">可参与分红权数</span>
				   <span class="totalMcDi">单价</span>
				   <span class="totalMcDi">实际发放金额</span>
				   <span class="totalMcDi">商户池收入</span>
				   <span class="totalMcDi">差异</span>
				</td>
				<td style="width: 250px;background-color: #0070C0">
				   <span class="totalDr">直推会员</span>
                   <span class="totalDr">直推商家</span>				   
				</td>
				<td style="width: 500px;background-color: #8EA9DB">
				    <span class="totalYE">日发放额</span>
                    <span class="totalYE">商户会员池收入</span>
                    <span class="totalYE">直推奖励总额</span>
                    <span class="totalYE">差异</span>
				</td>
				<td style="width: 630px;background-color: #D9E1F2">
				    <span  class="totalDe">提现金额</span>
                    <span  class="totalDe">税收</span>
                    <span  class="totalDe">责任消费</span>
                    <span  class="totalDe">到账金额</span>
                    <span  class="totalDe">手续费</span>
				</td>
				<td style="width: 500px;background-color: #E2EFDA">				
				    <span class="totalMeYe">发放额</span>
                    <span class="totalMeYe">提现划出</span>
                    <span class="totalMeYe">消费</span>
                    <span class="totalMeYe">剩余</span>
				</td>
				<td style="width: 380px;background-color: #C6E0B4">
                    <span class="totalZE">日责任消费额</span>
                    <span class="totalZE">消耗额</span>
                    <span class="totalZE">剩余额</span>
                </td>
				<td style="width: 120px;background-color: #FCE4D6">新增</td>
				<td style="width: 120px;background-color: #F8CBAD">新增</td>
				<td style="width: 380px;background-color: #F4B084">
				    <span class="totalZE">个代</span>
				    <span class="totalZE">区代</span>
				    <span class="totalZE">市代</span>
				    
				</td>
				<td style="width: 120px;background-color: #C65911">总营收</td>
				<td style="width: 120px;background-color: #FFE699">总营收</td>
				<td style="width: 120px;background-color: #FFD966">实际收款</td>
				<td style="width: 120px;background-color: #BF8F00">实际支出</td>
				<td style="width: 250px;background-color: #E2EFDA">
				   <span class="totalYiS">预日营收</span>
				   <span class="totalYiS">实际应收</span>
				</td>
				<td style="width: 120px;background-color: #BF8F00">操作</td>			
			</tr>
			
			 
			 <c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				<tr >	
			    <td style="width: 80px"><fmt:formatDate pattern="yyyy-MM-dd" value="${bean.addTime}"/></td>			
                <td style="width: 750px">
                   <span class="totalBe">${bean.benerfitTradeMoney }</span>
				   <%-- <span class="totalBe">${bean.info.sumBeWpay+bean.info.sumBeHpay+bean.info.sumBeSpay}</span> --%>
				   <span class="totalBe">${bean.benerfitDateMoney}</span> 
				   <span class="totalBe">${bean.benerfitWeipayMoney}</span>
				   <span class="totalBe">${bean.benerfitYinlMoney}</span>
				   <span class="totalBe">${bean.benerfitSxMoney}</span>
				   <span class="totalBe">${bean.benerfitZhibaoMoney}</span>
                </td>
				<td style="width: 630px">
				   <span class="totalShop">${bean.mallWeipayMoney}</span>
				   <span class="totalShop">${bean.mallYinlpayMoney}</span>
				   <span class="totalShop">${bean.mallZerenpayMoney}</span>
				   <span class="totalShop">${bean.mallBanlanMoney}</span>
				   <span class="totalShop">${bean.mallZhibaoMoney}</span>
				</td>
				<td style="width: 875px">
                   <%-- <span class="totalShou">${bean.info.sumSXWpay/0.12+bean.info.sumSXHpay/0.12+sumSXHTpay/0.12}</span> --%>
                   <span class="totalShou">${bean.shouxinLinesMoney}</span>
				   <span class="totalShou">${bean.shouxinWeixinpayMoney}</span>
				   <span class="totalShou">${bean.shouxinYinlPayMoney}</span>
				   <span class="totalShou">${bean.shouxinHoutaipayMoney}</span>
				   
				  <%--  <span class="totalShou">${bean.info.sumBeSpay/0.12}</span> --%>
				  <span class="totalShou">${bean.shouxinConsumptionMoney}</span>
				   <span class="totalShou">${bean.shouxinShenyuMoney}</span>
				   <span class="totalShou">${bean.shouxinZhibaoMoney}</span>
                </td>
				<td style="width: 1200px">
				   <%-- <span class="totalPay">${bean.info.sumBeWpay+bean.info.sumSXWpay+bean.info.sumOrderWpay}</span> --%>
				   <span class="totalPay">${bean.totalWeipayMoney}</span>
				   <span class="totalPay">${bean.totalWeixShouxupayMoney }</span>
				   <span class="totalPay">${bean.totalWeiXinGetMoney}</span>
				   <%-- <span class="totalPay">${bean.info.sumBeHpay+bean.info.sumOrderHpay+bean.info.sumSXWpay}</span> --%>
				   <span class="totalPay">${bean.totalYinlpayMoney}</span>
				   <span class="totalPay">${bean.totalYinlShouxuMoney}</span>
				   <span class="totalPay">${bean.totalYinlGetMoney}</span>
				   <span class="totalPay">${bean.totalZhifubaoMoney}</span>
				   <span class="totalPay">${bean.totalZhifubaoShouxupayMoney}</span>
				   <span class="totalPay">${bean.totalZhifubaoGetMoney}</span>
				</td>
				<td style="width: 750px">
				  <%-- <c:if test="${status.index==0}">
				    <span class="totalMemDi">0</span>
				  </c:if>
				  <c:if test="${status.index!=0}">
				    <span class="totalMemDi">${pager.resultList[status.index-1].benefitDay.memberDiviNum-pager.resultList[status.index].benefitDay.memberDiviNum}</span>
				  </c:if> --%>
                  <span class="totalMemDi">${bean.memberAddDivi}</span>
                   <span class="totalMemDi">${bean.memberCanCanYu_Divi}</span>
				   <span class="totalMemDi">${bean.memberDanJia}</span>
				   <span class="totalMemDi">${bean.memberShiJiPayMoney}</span>
				   <span class="totalMemDi">${bean.memberPoolMoney}</span>
				  <%--  <span class="totalMemDi">${bean.benefitDay.memberRealMoney-bean.benefitDay.memberIncomeMoney}</span> --%>
				  <span class="totalMemDi">${bean.memberChaYiMoney}</span>
                </td>
				<td style="width: 750px">
				   <%-- <c:if test="${status.index==0}">
				    <span class="totalMcDi">0</span>
				  </c:if>
				  <c:if test="${status.index!=0}">
				    
				    <span class="totalMcDi">${pager.resultList[status.index-1].benefitDay.merchantsDiviNum-pager.resultList[status.index].benefitDay.merchantsDiviNum}</span>
				  </c:if> --%>
				   <span class="totalMcDi">${bean.merchantsAddDivi}</span>
                   <span class="totalMcDi">${bean.merchantsCanCanyuDivi}</span>
				   <span class="totalMcDi">${bean.merchantsDanjia}</span>
				   <span class="totalMcDi">${bean.merchantsShijipayMoney}</span>
				   <span class="totalMcDi">${bean.merchantsPoolMoney}</span>
				   <%-- <span class="totalMcDi">${bean.benefitDay.merchantsRealMoney-bean.benefitDay.merchantsIncomeMoney}</span> --%>
				   <span class="totalMcDi">${bean.merchantsChayiMoney}</span>
				</td>
				<td style="width: 250px;">
				   <span class="totalDr">${bean.zhituiMemberMoney}</span>
                   <span class="totalDr">${bean.zhituiMerchantsMoney}</span>				   
				</td>
				<td style="width: 500px;">
				    <%-- <span class="totalYE">${bean.benefitDay.memberRealMoney+bean.benefitDay.merchantsRealMoney+bean.info.directMeMoney+bean.info.directChMoney}</span> --%>
				    <span class="totalYE">${bean.totalBanlanDateMoney}</span>
				    <span class="totalYE">${bean.totalBanlanPoolMoney}</span>
				    <span class="totalYE">${bean.totalBanlanZhituiMoney}</span>
				    <span class="totalYE">${bean.totalBanlanChayiMoney}</span>
                    <%-- <span class="totalYE">${bean.benefitDay.memberIncomeMoney+bean.benefitDay.merchantsIncomeMoney}</span> --%>
                    <%-- <span class="totalYE">${bean.info.directMeMoney+bean.info.directChMoney}</span> --%>
                   <%--  <span class="totalYE">${(bean.info.memberRealMoney+bean.benefitDay.merchantsRealMoney+bean.info.directMeMoney+bean.info.directChMoney+bean.benefitDay.memberIncomeMoney+bean.benefitDay.merchantsIncomeMoney)-(bean.info.directMeMoney+bean.info.directChMoney)}</span> --%>
				</td>
				<td style="width: 630px">
				    <span class="totalDe">${bean.withdrawalMoney}</span>
                    <span class="totalDe">${bean.withdrawalShuishouMoney}</span>
                    <span class="totalDe">${bean.withdrawalZenrenMoney}</span>
                    <span class="totalDe">${bean.withdrawalDaozMoney}</span>
                    <span class="totalDe">${bean.withdrawalShouxuMoney}</span>
				</td>
				<td style="width: 500px">				
				    <span class="totalMeYe">${bean.memberBanlanFafangMoney}</span>
                    <span class="totalMeYe">${bean.memberBanlanHuaChuMoney}</span>
                    <span class="totalMeYe">${bean.memberBanlanXianFeiMoney}</span>
                    <span class="totalMeYe">${bean.memberBanlanShenyuMoney}</span>
				</td>
				<td style="width: 380px">
                    <span class="totalZE">${bean.zenrenDateMoney}</span>
                    <span class="totalZE">${bean.zenrenXiaoHouMoney}</span>
                    <span class="totalZE">${bean.zenrenShenyuMoney}</span>
                </td>
				<td style="width: 120px;">${bean.memberNumber}</td>
				<td style="width: 120px;">${bean.merchantsNumber}</td>
				<td style="width: 380px;">
				     <span class="totalZE">${bean.gedaiMoney}</span>
				     <span class="totalZE">${bean.ereadaiMoney}</span>
                     <span class="totalZE">${bean.citydaiMoney}</span>
				</td>
				<td style="width: 120px;">${bean.merchantsPoolTotalYiShou}</td>
				<td style="width: 120px;">${bean.pingTaiTotalYiShou}</td>
				<td style="width: 120px;">${bean.shijiShoukuaiMoney}</td>
				<td style="width: 120px;">${bean.zhichuDShijiPayMoney}</td>
				<td style="width: 250px;">
				   <span class="totalYiS">${bean.yishouYuYinShouMoney}</span>
				   <span class="totalYiS">${bean.yiShouShiJiYiShouMoney}</span>
				</td>
				<td style="width: 120px;"><a href="${ctx}/subsystem/countInfoAction!upateCountInfo.action?id=${bean.id}">更新</a></td>			
			  </tr>
			</c:forEach>
		</table>

		<c:if test="${not empty pager.resultList}">
			<s:form action="countInfoAction!countTotalDataByTime.action"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
					<input type="hidden" readonly="readonly" value="${addTime }" name="addTime"/>
					<input type="hidden" readonly="readonly" value="${endTime }" name="endTime"/>
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
	
</html>