<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-shop-top.jsp" %>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
<style>
   .layui-m-layercont{
       line-height: 1rem;
       font-size: 0.3rem;
       padding: 1rem 28px;
   } 

      
 .layui-m-layerchild{
      font-size: 31px;
  }
  .layui-m-layerbtn span{
     font-size: 31px;
  }
  
  .layui-m-layerbtn{ 
     height: 60px;
  }
</style>
<body style="background: #fff;">
     
	<section class="pay-affirm">
		<ul class="pay-af-ul">
			<li>
				<a href="${ctx}/wx/address/toFind?type=0&goodSource=${goodSource}" class="clearfix pay-aff-arr ">
					<c:if test="${not empty memberDefAddress.result}">
					<div class="pay-add">
						<p>
							<span>收货人：</span>
							<span class="pay-addname">${memberDefAddress.result.consigneeName}</span>
							<i>${memberDefAddress.result.mobile}</i></p>
						<p class="pay-af-add"><span>收货地址：</span><i>
						${memberDefAddress.result.proviceId.province}${memberDefAddress.result.cityId.city}
						<c:if test="${not empty memberDefAddress.result.areaId}">
							${memberDefAddress.result.areaId.area}
						</c:if>
						${memberDefAddress.result.addressDetail}
						</i></p>
						<input type="hidden" class="orderAddressId" value="${memberDefAddress.result.id}">
					</div>
					</c:if>
					<c:if test="${empty memberDefAddress.result}">
					<p class="pay-addnone">
						<i class='pay-addnonei'>添加收货人</i>
					</p>
					</c:if>
					<i class="pay-arrows"></i>
				</a>
			</li>
			<li class="pay-aff-Info"><span>订单信息</span></li>
			<c:forEach var="bean" items="${goodsVos}" varStatus="status">
			<li class="clearfix">
				<div class="pay-affirmimg"><img src="${bean.goodsImg}"></div>
				<div class="pay-aff-s">
					<p class="pay-aff-sp">${bean.goodsName}</p>
					<p class="pay-aff-tao">${bean.goodsAttr}</p>
					<p class="pay-aff-money">
					   <c:if test="${isWholesale==0}">
					       <span>${bean.goodsAmount}元</span>
					   </c:if>
					   <c:if test="${isWholesale==1}">
					      <c:if test="${merchantType==1 }">
					        <span>${bean.standardPrice}元</span>
					      </c:if>
					      <c:if test="${merchantType==2 }">
					        <span>${bean.honourPrice}元</span>
					      </c:if>				 
					   </c:if>
					   					    
					   <i class="goodNum">${bean.goodsNum}</i>
					</p>
				</div>
			</li>
			</c:forEach>

			<li class="pay-aff-com">
				<span>配送方式</span>
				<c:if test="${logist==0}">
				  <i class="partic-affcom-right" id="posInfo">快递&nbsp;&nbsp;￥${pos}</i>
				</c:if>
				<c:if test="${logist==1}">
				  <i class="partic-affcom-right">物流到付</i>
				</c:if>
				
			</li>
			<c:if test="${isCanUseCou==1}">
			  <li class="pay-aff-com">
				<span>支付明细：</span>
				<i class="partic-affcom-right">${totalAmount }积分+￥${pointNiceAmount}</i>			
			 </li>			
			</c:if>
			<c:if test="${isCanUseCou==2}">
			  <li class="pay-aff-com">
				<span>支付明细：</span>
				<i class="partic-affcom-right">${totalAmount }责任金额+￥${pointNiceAmount}</i>
			 </li>			
			</c:if>
			<c:if test="${isCanUseCou==3}">
			  <li class="pay-aff-com">
				<span>支付明细：</span>
				<i class="partic-affcom-right">${totalAmount }代金券+￥${pointNiceAmount}</i>
			 </li>			
			</c:if>
			<c:if test="${isCanUseCou==0}">
			  <li class="pay-aff-com">
				<span>支付明细：</span>
				<c:if test="${isWholesale==0}">
				   <i class="partic-affcom-right" class="totalPayMoney-detail">${totalAmount}</i>
				</c:if>
				
				<c:if test="${isWholesale==1}">
				    <c:if test="${merchantType==1}">
				      <i class="partic-affcom-right">${standardTotalAmount }</i>
				    </c:if>
				   <c:if test="${merchantType==2}">
				     <i class="partic-affcom-right">${honourTotalAmount }</i>
				   </c:if>
				</c:if>

			 </li>			
			</c:if>
			
			<c:if test="${isWholesale==1}">
			
			    <c:if test="${merchantType==1}">
				   <li class="pay-aff-com">
				    <a href="javascript:">
					  <span>升级企业版优惠</span>
					  <i class="partic-affcom-right ">${honourPreferentialMoney}</i>					  
				   </a>
				   
			      </li>
			      <li class="pay-aff-com">
				    
				   <a href="${ctx}/wx/trade/goMerchantUpgrade">
					  <span>点击立即升级</span>							 				 				  
				   </a>
			      </li>
			   </c:if> 
			   <%-- <c:if test="${merchantType==2}">
				  <li class="pay-aff-com">
				    <a href="javascript:">
					  <span>优惠</span>
					  <i class="partic-affcom-right ">${honourPreferentialMoney}</i>
				   </a>
			      </li>
			   </c:if> --%>
				
			</c:if>
			<!-- <li class="pay-aff-com">
				<a href="../member-discount.html">
					<span>优惠劵</span>
					<i class="partic-affcom-right partic-after">无卡劵使用</i>
				</a>
			</li> -->
			<li class="pay-aff-com">
				<a href="javascript:">
					<span>卖家留言：</span>	
					<input type="text" name="remark" placeholder="选填可告诉卖家您的特殊要求" class="pay-aff-text">
				</a>
			</li>
			
		</ul>
		<p class="pay-aff-p">总支付：
		    <c:if test="${isWholesale==0}">
		        <span class="pay-aff-smoney">${totalAmount+pointNiceAmount}</span>
		    </c:if>
		    
		    <c:if test="${isWholesale==1}">
		        <c:if test="${logist==0}">				  
		           <c:if test="${merchantType==1}">
			         <span class="pay-aff-smoney">${standardTotalAmount+pointNiceAmount}</span>			
			       </c:if>
			       <c:if test="${merchantType==2}">
			         <span class="pay-aff-smoney">${honourTotalAmount+pointNiceAmount}</span>				
			       </c:if>
				</c:if>
				<c:if test="${logist==1}">
				  <c:if test="${merchantType==1}">
			         <span class="pay-aff-smoney">${standardTotalAmount}</span>			
			       </c:if>
			       <c:if test="${merchantType==2}">
			         <span class="pay-aff-smoney">${honourTotalAmount}</span>				
			       </c:if>
				</c:if>
		    
		    </c:if>
		   		  
		</p>
		<c:if test="${goodSource==1}">
		   <div style="margin-top: 15px;margin-bottom: 10px;">
		      <span>请选择赠送方式：</span>				        
			  <span class="pos com0" data-value="1" style="padding: 5px;border: solid 1px #00ff08;margin-left: 20px;width: 134px;height: 16px;display: inline-block;">代金券(约${vocMoney})</span>  
		      <span class="pos com1" data-value="0" style="padding: 5px;border: solid 1px #9E9E9E;margin-left: 140px;display: inline-block;margin-top: 21px;width: 134px;height: 16px;">积分(约${pointMoney})</span>                        
		   </div>
		</c:if>
		
		<ul class="pay-topup-ul">
			<li>选择支付方式</li>
			
			<c:if test="${isCanUseCou==1}">
			  <li class="clearfix">
				<span class="pay-topupweixin">积分支付</span>
				<%-- <c:if test="${empty gjfMemberInfo.payPassword}">
					<a href="${ctx}/wx/member/toSetPay" class="pay-set-password">设置支付密码</a>
				</c:if> --%>
				<div class="pay-topup-radiobox">
					<input type="radio" name="pay-topup-pay"  status="7" id="pay-cou" >
					<label class="pay-topup-radio" for="pay-cou"></label>
				</div>
			  </li>
			</c:if>
			
			<c:if test="${isCanUseCou==2}">
			
		      <li class="clearfix">
				<span class="pay-topupweixin">责任消费险余额</span>
				<c:if test="${empty gjfMemberInfo.payPassword}">
					<a href="${ctx}/wx/member/toSetPay" class="pay-set-password">设置支付密码</a>
				</c:if>
				<div class="pay-topup-radiobox">
					<input type="radio" name="pay-topup-pay" status="8" id="pay-ins" class="payOnline" >
					<label class="pay-topup-radio" for="pay-ins"></label>
				</div>
			   </li>
	
			</c:if>
			
			<c:if test="${isCanUseCou==3}">
			
		      <li class="clearfix">
				<span class="pay-topupweixin">代金券金额</span>
				<c:if test="${empty gjfMemberInfo.payPassword}">
					<a href="${ctx}/wx/member/toSetPay" class="pay-set-password">设置支付密码</a>
				</c:if>
				<div class="pay-topup-radiobox">
					<input type="radio" name="pay-topup-pay" status="10" id="pay-voucher" class="payOnline" >
					<label class="pay-topup-radio" for="pay-ins"></label>
				</div>
			   </li>
	
			</c:if>
			
		   <c:if test="${isCanUseCou==0}">
		     <c:if test="${isWholesale==0}">
		       <%-- <c:if test="${totalAmount <= gjfMemberInfo.balanceMoney}"> --%>
			  <%--<li class="clearfix" style="height:1.2rem;">
				<span class="pay-topupweixin">会员余额</span>
				<c:if test="${empty gjfMemberInfo.payPassword}">
					<a href="${ctx}/wx/member/toSetPay" class="pay-set-password">设置支付密码</a>
				</c:if>
				<div class="pay-topup-radiobox">
					<input type="radio" name="pay-topup-pay" status="0" id="pay-banl" class="payOnline" >
					<label class="pay-topup-radio" for="pay-banl"></label>
				</div>
				<div style="float:left;width:100%;margin-bottom:.1rem;color: red">(余额支付需要额外支付10%税费)</div>
			  </li>--%>
			<%-- </c:if> --%>
			
			
			 <li class="clearfix" style="height:1.2rem;">
				<span class="pay-topupweixin">巨惠宝</span>
				<c:if test="${empty gjfMemberInfo.payPassword}">
					<a href="${ctx}/wx/member/toSetPay" class="pay-set-password">设置支付密码</a>
				</c:if>
				<div class="pay-topup-radiobox">
					<input type="radio" name="pay-topup-pay" status="9" id="pay-fh-treasure" class="payOnline" >
					<label class="pay-topup-radio" for="pay-fh-treasure"></label>
				</div>
				
			  </li>
		     </c:if>
		     
		     	
		 			
			<%-- <c:if test="${totalAmount > gjfMemberInfo.balanceMoney&&totalAmount >gjfMemberInfo.insuranceMoney}"> --%>
			<li class="clearfix" style="display: block" id="weixPay">
				<span class="pay-topupweixin pay-topupalipay" id="weixPay">微信钱包</span>
				<div class="pay-topup-radiobox">
					<input type="radio" name="pay-topup-pay" id="pay-alipay" status="1" >
					<label class="pay-topup-radio" for="pay-alipay"></label>
				</div>
			</li>
			 <%--<li class="clearfix" style="display: block" id="yilPay">
				<span class="pay-topupaplip pay-topupalipay" >银联支付</span>
				<div class="pay-topup-radiobox" >
					<input type="radio" name="pay-topup-pay" id="pay-h5" status="3" >
					<label class="pay-topup-radio" for="pay-h5"></label>
				</div>
			</li> --%>
			
			 <li class="clearfix" id="alyPay" style="display: none">
				<span class="pay-topupaplip pay-topupalipay" style="">支付宝支付</span>
				<div class="pay-topup-radiobox" >
					<input type="radio" name="pay-topup-pay" id="pay-aply" status="4" >
					<label class="pay-topup-radio" for="pay-aply"></label>
				</div>
			</li>  
			<%-- </c:if> --%>
		 </c:if> 
			
		</ul>
	</section>
	<div class="pay-dustbox">
		<div class="pay-password">
			<div class="pay-affpay-top">
				<h5>请输入会员卡支付密码</h5>
				<i class="pay-aff-de"></i>
				</div>
				<c:if test="${isCanUseCou==0}">
				
				   <c:if test="${isWholesale==0}">
					   <p class="pay-affmoney" id="mo"><fmt:formatNumber value="${(totalAmount+pos)*0.1+(totalAmount+pos)}" pattern="#.##" type="number"/></p>
				   </c:if>
				   <c:if test="${isWholesale==1}">
					  <c:if test="${merchantType==1 }">
					     <p class="pay-affmoney" id="mo"><fmt:formatNumber value="${(standardTotalAmount+pos)*0.1+(standardTotalAmount+pos)}" pattern="#.##" type="number"/></p>
					  </c:if>
					  <c:if test="${merchantType==2 }">
					        <p class="pay-affmoney" id="mo"><fmt:formatNumber value="${(honourTotalAmount+pos)*0.1+(honourTotalAmount+pos)}" pattern="#.##" type="number"/></p>
					  </c:if>				 
				  </c:if>
				   <%-- <c:if test="${merchantType==0}">
				      <p class="pay-affmoney" id="mo"><fmt:formatNumber value="${(totalAmount+pos)*0.1+(totalAmount+pos)}" pattern="#.##" type="number"/></p>
				   </c:if>
				    <c:if test="${merchantType==1}">
				      <p class="pay-affmoney" id="mo"><fmt:formatNumber value="${(standardTotalAmount+pos)*0.1+(standardTotalAmount+pos)}" pattern="#.##" type="number"/></p>
				   </c:if>
				    <c:if test="${merchantType==2}">
				      <p class="pay-affmoney" id="mo"><fmt:formatNumber value="${(honourTotalAmount+pos)*0.1+(honourTotalAmount+pos)}" pattern="#.##" type="number"/></p>
				   </c:if> --%>
				  
				</c:if>
				<c:if test="${isCanUseCou==1||isCanUseCou==2||isCanUseCou==3}">
				   <p class="pay-affmoney">${totalAmount+pos}</p>
				</c:if>
				
				<c:if test="${isCanUseCou==0}">
					<p class="pay-remaining" id="balance-money">
						余额：<span>${gjfMemberInfo.balanceMoney}</span>元
					</p>
					<p class="pay-remaining" id="fh-treasure-money">
						巨惠宝余额：<span>${fhTreasureInfo}</span>元
					</p>
					<%-- <p class="pay-remaining"  id="insurance-money">
						责任消费额：<span>${gjfMemberInfo.insuranceMoney}</span>元
					</p> --%>
				</c:if>
				<c:if test="${isCanUseCou==1}">
				<p class="pay-remaining">
					积分：<span>${gjfMemberInfo.consumptionMoney}</span>分
				</p>
				</c:if>
				<c:if test="${isCanUseCou==2}">
				    <p class="pay-remaining"  id="insurance-money">
						责任消费额：<span>${gjfMemberInfo.insuranceMoney}</span>元
					</p>
				</c:if>
				
				<c:if test="${isCanUseCou==3}">
				    <p class="pay-remaining"  id="insurance-money">
						代金券金额：<span>${gjfMemberInfo.memberVoucherMoney}</span>元
					</p>
				</c:if>
												
			<ul class="pay-inputPassword">
		      <li></li>
		      <li></li>
		      <li></li>
		      <li></li>
		      <li></li>
		      <li></li>
		    </ul>
		</div>
		<div class="pay-pawError">
			<p class="pay-errorRetry">支付密码错误，请重试</p>
			<div class="pay-paw">
				<a href="javascript:" class="pay-reg">重试</a>
				<a href="${ctx}/wx/member/toSetPay">忘记密码</a>
			</div>
		</div>
		 <div class="numb_box">
		    <div class="xiaq_tb"> <img src="${imagePath}/wx/online-shop/jftc_14.jpg" height="10"> </div>
		    <ul class="nub_ggg">
		      <li><a href="javascript:void(0);">1</a></li>
		      <li><a href="javascript:void(0);" class="zj_x">2</a></li>
		      <li><a href="javascript:void(0);">3</a></li>
		      <li><a href="javascript:void(0);">4</a></li>
		      <li><a href="javascript:void(0);" class="zj_x">5</a></li>
		      <li><a href="javascript:void(0);">6</a></li>
		      <li><a href="javascript:void(0);">7</a></li>
		      <li><a href="javascript:void(0);" class="zj_x">8</a></li>
		      <li><a href="javascript:void(0);">9</a></li>
		      <li><span></span></li>
		      <li><a href="javascript:void(0);" class="zj_x">0</a></li>
		      <li><span  class="del"> <img src="${imagePath}/wx/online-shop/jftc_18.jpg"   ></span></li>
		    </ul>
		  </div>
		<div class="pay-dust">
		</div>
	</div>
	<form  id="order-add-form" method="post">
		<c:forEach var="bean" items="${orderAddVos}" varStatus="status">
		   <c:if test="${goodSource==1}">
		     <input type="hidden" name="orderAddVos[${status.count-1}].goodsId" value="${proId}"/>
		   </c:if>
		   <c:if test="${goodSource==0||goodSource==2||goodSource==5}">
		     <input type="hidden" name="orderAddVos[${status.count-1}].goodsId" class="goodIds" value="${bean.goodsId}"/>
		   </c:if>
			
			<input type="hidden" name="orderAddVos[${status.count-1}].goodsAttrStockId" value="${bean.goodsAttrStockId}"/>
			<input type="hidden" name="orderAddVos[${status.count-1}].goodsAttrIds" value="${bean.goodsAttrIds}"/>
			<input type="hidden" name="orderAddVos[${status.count-1}].goodsAttr" value="${bean.goodsAttr}"/>
			<input type="hidden" name="orderAddVos[${status.count-1}].goodsNum" class="goodNums" value="${bean.goodsNum}"/>
		</c:forEach>
		<input type="hidden" class="payType-add" name="payType" value=""/>
		<input type="hidden" class="remark-add" name="remark" value=""/>
		<input type="hidden" class="couponsId-add" name="couponsId" value="0"/>
		<input type="hidden" class="orderAddressId-add" name="orderAddressId" value=""/>
		<input type="hidden" class="payPassword" name="payPassword" value=""/>
		<input type="hidden" class="logist" name="logist" value="${logist}"/>
		<input type="hidden" id="commission" name="commissionType" value="1"/>
		<input type="hidden" class="pointNiceMoney" name="pointNiceMoney" value="${pointNiceAmount}"/>
		<input type="hidden" class="orderSn" name="orderSn" value="${orderSn}"/>
		<input type="hidden" class="customerSn" name="customerSn" value="${customerSn}"/>
		<c:if test="${logist==0}">
		   <input type="hidden" class="postage" name="postage" value="${pos}"/>
		</c:if>
		<c:if test="${logist==1}">
		   <input type="hidden" class="postage" name="postage" value="0"/>
		</c:if>
		
	</form>
	
	<footer class="pay-aff-footer">
		<a href="javascript:" class="address-adda pay-affsub">确认支付</a>
	</footer>
	
	 <%-- <div class="loading-middle hidden">
            <img src="${imagePath}/wx/o2o-shop/load.gif" class="loadImg">
      </div> --%>
	<script type="text/javascript">
		document.title = "下单";				
		
		//判断在APP或者微信浏览器中
		var nvatTpye="0"
		if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)||/(Android)/i.test(navigator.userAgent)) {
			nvatTpye="0"
        } 
		
		if(/MicroMessenger/i.test(navigator.userAgent)){
        	nvatTpye="0"
        }
		 if(nvatTpye=="1"&&"${isCanUseCou}"=="0"&&"${goodSource}"!="2"){
			$("#alyPay")[0].style.display = 'block';
			$("#weixPay")[0].style.display = 'none';
			//$("#yilPay")[0].style.display = 'block'
		} 
		var i = 0;
		var numberarrAy=[];//存储输入密码的数组
		var payPassword="";
	    $(".pay-topup-ul .clearfix:first .pay-topup-radiobox input").attr("checked","checked")
		//$(function(){
			// 会员卡支付
			function paysunb(){
				//出现浮动层
				$(".pay-affsub").on("touchend",function(e){
					e.stopPropagation();//阻止冒泡
            		e.preventDefault();//取消冒泡
            		var address=$(".orderAddressId").val();
            		 if(address==null){
            			 layer.open({
                             content:"收货地址不能为空",
                             btn:'我知道了'
                         })
 		                return false;
 		            }
            		 var payPassword=$(".pay-set-password").text()
            		 var couVal=$('#pay-cou:checked').val();
            		
            		 var vouVal=$("#pay-voucher:checked").val()
					 
					 var balVal=$('#pay-banl:checked').val();
					 var insVal=$('#pay-ins:checked').val();
					 var fhVal=$('#pay-fh-treasure:checked').val()
					
					 
				
		            if(couVal==null&&balVal==null&&insVal==null&&fhVal==null&&vouVal==null){
		            	
		            	//微信支付
		            	var payAlipay=$("#pay-alipay:checked").val()
		            	if(payAlipay){
		            		 /*  layer.open({
	                                content:'微信支付功能暂未开放，敬请期待',
	                                btn:'我知道了'
	                            })  */ 
	                        if(nvatTpye=="1"){
	                        	/*  layer.open({
                                content:'微信支付功能暂未开放，敬请期待',
                                btn:'我知道了'
                               })  */
	                        	ajaxAddOrderInApp("1");
	                        }else{
	                        	
	                        	ajaxAddOrder("1");	
	                        }
	                        
	                        		            		
		            		return false;
		            	}
		            	//网银支付
		            	var payH5=$("#pay-h5:checked").val()
		            	if(payH5){
		            		if($(".pay-aff-smoney").html()<0){
		            			layer.open({
			                           content:"网银支付金额不能少于600元",
			                           btn:'我知道了'
			                     })
			                     return false;
		            		}else{
		            			addOrderByH5Pay("3");
			            		return false;	
		            		}
		            	}
		            	//支付宝支付
		            	 var apy=$("#pay-aply:checked").val()
		            	if(apy){
		            		ajaxAddOrderInApp("2");
		            		return false;
		            	} 
		            	
		            	//支付宝支付
		            	 var netapy=$("#net-pay-aply:checked").val()
		            	if(netapy){
		            		ajaxAddOrderInNetFriend("2");
		            		return false;
		            	} 
		            	
		            }else{
		            	
		            	if(payPassword&&couVal!="on"){
	            			
	            			 layer.open({
	                             content:"请先设置支付密码",
	                             btn:'我知道了'
	                         })
	 		                return false;
	            		 }
	            		
	            		 
	            		 /* if(payPassword&&vouVal!="on"){
	            			 layer.open({
	                             content:"请先设置支付密码",
	                             btn:'我知道了'
	                         })
	 		                return false;
	            		 } */
		            	
		            	//判断积分
		            	if("${orderGoods.result.totalAmount}" > "${gjfMemberInfo.consumptionMoney}"){		      
		            		layer.open({
		                           content:"积分不足，请选择其他支付方式",
		                           btn:'我知道了'
		                       })
		            		return false;
		            	} /* else{
		            		$(".pay-dustbox").show();
		            	}  */
		            	
		            	if(fhVal=="on"){
	            			$(".pay-dustbox").show();
	            			$("#fh-treasure-money").show();
	            			$("#balance-money").hide();
	            			var isW="${isWholesale}";
	            			if(isW=="0"){
	            				var payMo=${totalAmount+pos};
	            				$("#mo").html(payMo.toFixed(2));
	            			}else{
	            				var merchantType="${merchantType}";
	            				if(merchantType=="1"){
	            					var payMo=${standardTotalAmount+pos};
		            				$("#mo").html(payMo.toFixed(2));
	            				}else{
	            					var payMo=${honourTotalAmount+pos};
		            				$("#mo").html(payMo.toFixed(2));
	            				}
	            			}
	            		}
		            	var nicMoney="${pointNiceAmount}";		            	
		            	if(vouVal=="on"&&nicMoney==0){
		            		
	            			$(".pay-dustbox").show();
	            		}else if(vouVal=="on"&&nicMoney!=0){
	            			
	            			var status=$('#pay-voucher:checked').attr("status");
							ajaxAddOrder(status);	
	            		}
		            	
                        if(insVal=="on"&&nicMoney==0){
		            		
	            			$(".pay-dustbox").show();
	            		} else if(insVal=="on"&&nicMoney!=0){
	            			
	            			var status=$('#pay-ins:checked').attr("status");
	            			
							ajaxAddOrder(status);
	            		}
                        
                      
		            	
		            	//判断用户余额
		            	if("${orderGoods.result.totalAmount}" > "${gjfMemberInfo.balanceMoney}"){
		            		 layer.open({
		                           content:"余额不足，请选择其他支付方式",
		                           btn:'我知道了'
		                       })
		            		return false;
		            	}else{
		            		if(insVal=="on"){
		            		
		            			$("#insurance-money").show();
		            			$("#balance-money").hide();
		            			$("#fh-treasure-money").hide();
		            		}else if(balVal=="on"){
		            			$(".pay-dustbox").show();
		            			$("#insurance-money").hide();
		            			$("#balance-money").show();
		            			$("#fh-treasure-money").hide();
		            			var isW="${isWholesale}";
		            			if(isW=="0"){
		            				var payMo=${(totalAmount+pos)*0.1+(totalAmount+pos)};
		            				$("#mo").html(payMo.toFixed(2));
		            			}else{
		            				var merchantType="${merchantType}";
		            				if(merchantType=="1"){
		            					var payMo=${(standardTotalAmount+pos)*0.1+(standardTotalAmount+pos)};
			            				$("#mo").html(payMo.toFixed(2));
		            				}else{
		            					var payMo=${(honourTotalAmount+pos)*0.1+(honourTotalAmount+pos)};
			            				$("#mo").html(payMo.toFixed(2));
		            				}
		            			}
		            			
		            			
		            		}
		            				            	
		            		if(couVal!="on"){
		            			//$(".pay-dustbox").hide();
		            		}else{
		            			
		            			var status=$('#pay-cou:checked').attr("status");
								if(nvatTpye=="1"){
									ajaxAddOrderInApp(status)
								}else{
									ajaxAddOrder(status);
								}
		            		}
		            		
		            	}
		            }
				});	
					//关闭浮动
				$(".pay-aff-de").on("touchend",function(event){
					$(".pay-dustbox").hide();
					event.preventDefault();
				});
				//数字显示隐藏
				$(".xiaq_tb").on("touchend",function(){
					$(".numb_box").slideUp(500);
				});
				$(".pay-inputPassword").on("touchend",function(){
					$(".numb_box").slideDown(500);
				});
				
				
				
				$(".nub_ggg li a").on("touchend",function(event){
					
					i++
					numberarrAy.push($(this).html());
					payPassword=payPassword+$(this).html();
					if(i<6){
						$(".pay-inputPassword li").eq(i-1).addClass("mmdd");
					}else{
						$(".pay-inputPassword li").eq(i-1).addClass("mmdd");
						$(".pay-password").hide(); 
						$(".numb_box").hide();
						
						//进行密码校检，密码通过则下单成功
						$(".payPassword").val(payPassword);
						
						var couVals=$('#pay-cou:checked').val();
						var balVals=$('#pay-banl:checked').val();
						var insVals=$('#pay-ins:checked').val();						
						var fhVal=$('#pay-fh-treasure:checked').val()
						var vouVal=$("#pay-voucher:checked").val()
						
						if(vouVal){

							var status=$('#pay-voucher:checked').attr("status");
							ajaxAddOrder(status);														
						}
						
						if(couVals){
							var status=$('#pay-cou:checked').attr("status");
							if(nvatTpye=="1"){
								ajaxAddOrderInApp(status)
							}else{
								ajaxAddOrder(status);
							}
							
						}
						if(balVals){
							var status=$('#pay-banl:checked').attr("status");
							if(nvatTpye=="1"){
								ajaxAddOrderInApp(status)
							}else{
								ajaxAddOrder(status);
							}							
						}
						if(insVals){
							var status=$('#pay-ins:checked').attr("status");
							if(nvatTpye=="1"){
								ajaxAddOrderInApp(status)
							}else{
								ajaxAddOrder(status);
							}
						}
						
						if(fhVal){
							var status=$('#pay-fh-treasure:checked').attr("status");
							/* if(nvatTpye=="1"){
								ajaxAddOrderInApp(status)
							}else{ */
								ajaxAddOrder(status);
							//}
						}
						
					 }
					 event.preventDefault(); 
				});
				//删除
				$(".nub_ggg li .del").on("touchend",function(){
					if(i>0){
						i--
						numberarrAy.pop();
						$(".pay-inputPassword li").eq(i).removeClass("mmdd");
						i==0;
						payPassword=payPassword.substring(0,payPassword.length-1);
						console.log(payPassword);
					}
				});

				// 重试
				$(".pay-reg").on("touchend",function(){
					numberarrAy=[];
					i = 0;
					payPassword="";
					$(".pay-inputPassword li").removeClass("mmdd");
					$(".pay-pawError").hide();
					$(".pay-password").show(); 
					$(".numb_box").show();
				})
				
				
				$(".payOnline")
				
			}
			//paysunb();
		//})
		
		
	//微信下单
	function ajaxAddOrder(payType){
		$(".payType-add").val(payType);
		$(".remark-add").val($(".pay-aff-text").val());
		$(".orderAddressId-add").val($(".orderAddressId").val());
		var pos="${pos}"
		var pointNiceAmount="${pointNiceAmount}"
		var isUpgradePro="${isUpgradePro}"
       $.ajax({
           url: '${ctx}/wx/order/add',
           type: 'post',
           dataType: 'json',
           data: $("#order-add-form").serialize(),
           success:function(data){
        	   var code=data.code;
        	  
        	   if(payType=='0'||payType=='9'||(payType=='7'&&pointNiceAmount==0)||(payType=='8'&&pointNiceAmount==0)||(payType=='10'&&pointNiceAmount==0&&goodSource==0)||(payType=='10'&&pointNiceAmount==0&&goodSource==5)){
        		   if(code==401){
            		   setTimeout(function(){
    						$(".pay-pawError").show();  //500毫秒跳转
    					},500);
            	   }else if(code==200){
            		   $(".pay-dustbox").hide();
            		   onlinePay(isUpgradePro)           		  
            	   }else{
            		  numberarrAy=[];
   					  i = 0;
   					  payPassword="";
            		 
            		   $(".pay-inputPassword li").removeClass("mmdd");
            		  $(".pay-password").show(); 
            		  $(".pay-dustbox").hide();
            		  $(".numb_box").show();
            		  layer.open({
                          content:data.msg,
                          btn:'我知道了'
                      })
            	   }
        	   }else if(payType=='1'||(payType=='7'&&pointNiceAmount!=0)||(payType=='8'&&pointNiceAmount!=0)||(payType=='10'&&pointNiceAmount!=0&&goodSource==0)||(payType=='10'&&pointNiceAmount!=0&&goodSource==5)){
        		   
        		   if(code==200){        			           			
            		  //window.location.href="https://pay.swiftpass.cn/pay/jspay?token_id="+data.result;
        			 //利楚  
        			 /* if(data.result.orderInfo.suoceGood==5){
        				 layer.open({
                             content: "支付运费需要多加"+data.result.orderInfo.orderPostage+"元,总金额为"+data.result.orderInfo.orderTotalAmount,
                             btn: ['确定', '取消'],
                            yes: function(index){//跳转到个人中心              	                        		
                            	officeLinePay(data)
                                layer.close(index);
                             },
                             no:function(index){
                                  layer.close(index);
                            }
                        }); 
        			 }else{ */
        				 officeLinePay(data) 
        			 /* } */
        			
        			   
        		   }else{
        			  
        			   $(".pay-password").show(); 
        			   layer.open({
                           content:data.msg,
                           btn:'我知道了'
                       })
        		   }
        	   }
        	   //$(".pay-dustbox").hide();
           },
           error:function(){
        	   layer.open({
                   content:'网络异常',
                   btn:'我知道了'
               })
           }
       })
     }
	    
	   
	 //线上支付成功
	 function onlinePay(isUpgradePro){
		 if(isUpgradePro==0){
			   layer.open({
                 content:"下单成功",
                 btn:'我知道了',
                 yes:function(){
               	  window.location.href="${ctx}/wx/order/my?pageNo=1&pageSize=6&status=7&reqType=0"
                 }
               }) 
		   }else{
			   layer.open({
                 content:"<div id='showText'><div style='font-size: 0.3rem;'>恭喜您成为<h2 style='color:red;display: initial;font-size: 0.4rem'>vip会员<h2></div>"
                       +"<div style='padding-top: 0.2rem;font-size: 0.3rem;'>商城购买<h2 style='color:red;display: initial;font-size: 0.4rem'>立即省钱</h2></div>"
                       +"<div style='padding-top: 0.2rem;font-size: 0.3rem;'>分享<h2 style='color:red;display: initial;font-size: 0.4rem'>快速赚钱</h2></div></div>"
                       +"温馨提示：为保障客户自身权益，客户在签收商品前对应商品质量及数量当面验收，检查货物"+
    					 "数量及外包和内物，确认货物完好无损后，再进行签收。如客户未经检查既做签收，则视为客户已确认商品没有问题并签收",                          
                 btn:'我知道了',
                 yes:function(){
               	  window.location.href="${ctx}/wx/order/my?pageNo=1&pageSize=6&status=7&reqType=0"
                 }
               })   
		   }
		   
	 }
	 //线下支付
	 function officeLinePay(data){
		 wx.config({
			    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			    appId: data.result.payInfo.appId, // 必填，公众号的唯一标识
			    timestamp: data.result.payInfo.timeStamp, // 必填，生成签名的时间戳
			    nonceStr: data.result.payInfo.nonceStr, // 必填，生成签名的随机串
			    signature:data.result.payInfo.paySign,// 必填，签名，见附录1
			    jsApiList: ['chooseWXPay'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
			}); 
        	
			wx.ready(function(){
				wx.chooseWXPay({
				    timestamp: data.result.payInfo.timeStamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
				    nonceStr: data.result.payInfo.nonceStr, // 支付签名随机串，不长于 32 位
				    package: data.result.payInfo.package_,
				    // package: data.result.package_str, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
				    signType: "MD5", // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
				    paySign: data.result.payInfo.paySign, // 支付签名
				    success: function (res) {
				        // 支付成功后的回调函数   
				    	if(res.errMsg == "chooseWXPay:ok" ) {
				    		if(isUpgradePro==0){
	            			   layer.open({
	            				content:"下单成功",
	                               btn:'我知道了',
	                               yes:function(){
	                            	   window.location.href="${ctx}/wx/member/my"
	                               }
	                             }) 
	            		   }else{
	            			   layer.open({
	            				 content:"<div id='showText'><div style='font-size: 0.3rem;'>恭喜您成为<h2 style='color:red;display: initial;font-size: 0.4rem'>vip会员<h2></div>"
                                  +"<div style='padding-top: 0.2rem;font-size: 0.3rem;'>商城购买<h2 style='color:red;display: initial;font-size: 0.4rem'>立即省钱</h2></div>"
                                  +"<div style='padding-top: 0.2rem;font-size: 0.3rem;'>分享<h2 style='color:red;display: initial;font-size: 0.4rem'>快速赚钱</h2></div></div>"
                                  +"温馨提示：为保障客户自身权益，客户在签收商品前对应商品质量及数量当面验收，检查货物"+
               					 "数量及外包和内物，确认货物完好无损后，再进行签收。如客户未经检查既做签收，则视为客户已确认商品没有问题并签收",                                     
	                               btn:'我知道了',
	                               yes:function(){
	                            	window.location.href="${ctx}/wx/member/my"
	                               }
	                             })   
	            		   }
				    	}	 
				    	if(res.errMsg == "chooseWXPay:cancel" ) {
    				    	 layer.open({
    				                content: '取消支付',
    				                btn: ['我知道了'],
    				                yes: function(index){//点击去认证页面，index为该特定层的索引                  				                	
    				                   $(".loading-middle").addClass("hidden");
    		                            $(".btn").removeAttr("disabled");
    		                         
    				                    layer.close(index);
    				                }                   				               
    				           });	                       				     
    				     }
				    	if(res.errMsg == "chooseWXPay:fail" ) {
   				    	 layer.open({
   				                content: '支付失败',
   				                btn: ['我知道了'],
   				                yes: function(index){//点击去认证页面，index为该特定层的索引                  				                	
   				                	$(".loading-middle").addClass("hidden");
   		                            $(".btn").removeAttr("disabled");
   				                    layer.close(index);
   				                }                   				               
   				           });	
				    	}
				    	}, 
				    
				});
			});  
	 }
	    
	 
	 //微信H5下单
	 function addOrderByH5Pay(payType){
		    $(".payType-add").val(payType);
			$(".remark-add").val($(".pay-aff-text").val());
			$(".orderAddressId-add").val($(".orderAddressId").val());
			$("#order-add-form").attr("action","${ctx}/wx/order/payH5")
			$("#order-add-form").submit()	       
	 }
		
	 //选择收货地址
	 var addressId=sessionStorage.getItem("addreId")
	 
	 var goodSource="${goodSource}"
	 
	
	 if(addressId!=undefined&&addressId!=null){
		
		 $(".address-adda").removeClass("pay-affsub")
		 $(".pay-aff-arr").html("")
		 $.ajax({
			 url:"${ctx}/wx/order/getMemAddreById",
			 method:"GET",
			 data:{
				 orderAddressId:addressId,
				 goodSource:goodSource,
			 },
			 success:function(data){
				 var str="<div class='pay-add'><p>"
					    +"<span>收货人：</span>"
					    +"<span class='pay-addname'>"+data.result.consigneeName+"</span>"
					    +"<i>"+data.result.mobile+"</i></p>"
				        +"<p class='pay-af-add'><span>收货地址：</span><i>"
				        +data.result.proviceId.province
				        if(data.result.cityId){
				         str+=""+data.result.cityId.city
				        }
				       if(data.result.areaId){
			              str+=""+data.result.areaId.area
			           }
				       if(data.result.townId){
				              str+=""+data.result.townId.townName
				        }
				       str+=data.result.addressDetail					   
				        +"</i></p><input type='hidden' class='orderAddressId' value='"+data.result.id+"'></div>";
				        
				  $(".pay-aff-arr").html(str)
				  if(goodSource==5){
					  getPosMoney()
				  }
			 }
		 }) 
	 }else{
		 paysunb() 
	 }
	 
	 //获取邮费
	
	 function getPosMoney(){
		 //获取商品id
		 var goodIds="";
		 $(".goodIds").each(function(){
			  goodIds=$(this).val()+",";
		    })
		 goodIds=goodIds.substring(0,goodIds.length-1);
		 
		 var goodNums=""
		 $(".goodNums").each(function(){
			 goodNums=$(this).val()+",";
		 })
		 goodNums=goodNums.substring(0,goodNums.length-1)
		
		 $.ajax({
			 url:"${ctx}/wx/order/findOrderPos",
			 method:"get",
			 data:{
				 goodIds:goodIds,
				 goodNums:goodNums,
				 addsId:addressId
			 },
			 success:function(data){
				 var totMoney="${totalAmount}";
				 var merchantType="${merchantType}"
				 var isWholesale="${isWholesale}"
				 var pointNiceMoney="${pointNiceAmount}"
				 var pos="${pos}"
				 alert(pointNiceMoney)
				 totMoney=parseFloat(totMoney)+parseFloat(data.result.pos)+parseFloat(pointNiceMoney)-parseFloat(pos);				 
				 $(".orderSn").val(data.result.orderSn)
				 $(".customerSn").val(data.result.customerSn);
				 $("#posInfo").html("快递&nbsp;&nbsp;￥"+data.result.pos);
				 $(".totalPayMoney-detail").html("${totalAmount}"+data.result.pos);
				 var logist=${logist}
				 if(logist==0){
					 $(".postage").val(logist)
				 }
				
				 if(isWholesale==1){
					 if(merchantType==1){
						 var standardTotalAmount="${standardTotalAmount}" 
						 standardTotalAmount=parseFloat(standardTotalAmount)+parseFloat(data.result.pos);
						 $(".pay-aff-smoney").html(standardTotalAmount);
					 }else if(merchantType==2){
						 var honourTotalAmount="${honourTotalAmount}"
							 honourTotalAmount=parseFloat(honourTotalAmount)+parseFloat(data.result.pos);
						$(".pay-aff-smoney").html(honourTotalAmount);
					 }
							 
				 }else{
					 $(".pay-aff-smoney").html(totMoney);
				 }
				
				 $(".address-adda").addClass("pay-affsub");
				 paysunb()
			 }
		 })
		 
      }
	    
	     
	 
	 
	//友品集下单
	 function ajaxAddOrderInNetFriend(payType){
		    $(".payType-add").val(payType);
			$(".remark-add").val($(".pay-aff-text").val());
			$(".orderAddressId-add").val($(".orderAddressId").val());
			var pos="${pos}"
			var pointNiceAmount="${pointNiceAmount}"
	       $.ajax({
	           url: '${ctx}/appNeed/addOrder',
	           type: 'post',
	           dataType: 'json',
	           data: $("#order-add-form").serialize(),
	           success:function(data){
	        	   var code=data.code;
	        	   if(code==200){
	        		   netPay(data.result.jdOrderSn,"5"); 
	        	   }
	           },
	           error:function(){
	        	   layer.open({
	                   content:'网络异常',
	                   btn:'我知道了'
	               })
	           }
	       })
	 }
	 
	 //友品集商品支付
	 function netPay(orderSn,payType){
		 alert(orderSn)
		 window.location.href="${ctx}/wx/product/payNetFriend?osn="+orderSn+"&pay_type="+payType		
	 }
	 
	 
	    //点击赠送方式
	    $(".pos").each(function(){
	    	$(this).click(function(){
	    		var dataValue=$(this).attr("data-value")   		
	    		if(dataValue==0){
	    			$(".com0").css({border:"solid 1px #00ff08"});
	    			$(".com1").css({border:"solid 1px #9E9E9E"});
	    			$("#commission").val(dataValue)	    				    			
	    		}else{
	    			$(".com0").css({border:"solid 1px #9E9E9E"});
	    			$(".com1").css({border:"solid 1px #00ff08"}); 
	    			$("#commission").val(dataValue)	    			
	    		}
	    	})
	    })
	 
	</script>
</body>
</html>