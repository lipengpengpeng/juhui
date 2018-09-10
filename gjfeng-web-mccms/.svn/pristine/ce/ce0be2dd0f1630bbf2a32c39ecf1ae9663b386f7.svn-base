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
						"shippingFeeAmount" : {
							required : true							
						},
						"courierName" : {
							required : true
							
						},
						"shippingCode" : {
							required : true
						},
						
					},
					messages:{
						"shippingFeeAmount" : {
							required : "运费不能为空",				
						},
						"courierName" : {
							required : "快递名称不能为空",							
						},
						"shippingCode" : {
							required : "快递单号不能为空",	
						},
						
					},
					success: function(label) {
						label.html("&nbsp;").addClass("checked");
			        }	
				});
				
				$("#submitForm").click(function(){
					if($("#gjfOrderGoodsForm").valida()){
						if(confirm("是否提交发货信息?")){
							$("#gjfOrderGoodsForm").submit()
						}
					}
				})
				
				
			});		
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">当前位置:订单管理 - 添加发货信息</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="location.href='${ctx}/subsystem/orderInfoAction!findAllOrderInfo.action'"/></div>
		</div>

		<form action="${ctx}/subsystem/orderInfoAction!addAddress.action" method="post" id="gjfOrderGoodsForm" name="gjfOrderGoodsForm" enctype="multipart/form-data">
			<table  align="center" class="listTable3">
			    <input type="hidden" name="orderId" value="${resultVo.result.orderId.id}"></input>
			    <input type="hidden" name="addreId" value="${resultVo.result.id}"></input>
				<tr>
					<td class="pn-flabel" width="100px">收货人姓名</td>
					<td><input type="text" id="orderId" disabled="disabled" name="reciverName" value="${resultVo.result.reciverName}"/></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">收货人联系电话</td>
					<td><input type="text" id="goodsId" disabled="disabled" name="reciverMobile" value="${resultVo.result.reciverMobile}"/></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">省份</td>
					<td>
					   <!-- <select  name="reciverProvinceId" id="provice">
					      <option></option>
					   </select> -->
					   <input type="text" disabled="disabled" value="${resultVo.result.reciverProvinceId.province}"/>
					   <%-- <input type="hidden" value="${resultVo.result.memberAddressId.proviceId.id}" name="reciverProvinceId"/> --%>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">城市</td>
					<td>
					  <!--  <select  name="reciverCityId" id="city">
					      <option></option>
					   </select> -->
					    <input type="text" disabled="disabled" value="${resultVo.result.reciverCityId.city}"/>
					   <%-- <input type="hidden" value="${resultVo.result.memberAddressId.cityId.id}" name="reciverCityId"/> --%>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px" >区域</td>
					<td>
					   <!-- <select  name="reciverAreaId" id="area">
					      <option></option>
					   </select> -->
					   <input type="text" disabled="disabled" value="${resultVo.result.reciverAreaId.area}"/>
					   <%-- <input type="hidden" value="${resultVo.result.memberAddressId.areaId.id}" name="reciverAreaId"/> --%>
					</td>
				</tr> 
				<tr>
					<td class="pn-flabel" width="100px">详细地址</td>
					<td><input type="text" value="${resultVo.result.reciverDetailAddress}" /></td>
				</tr> 
				<tr>
					<td class="pn-flabel" width="100px">运费</td>
					<td><input type="text" id="goodsName" name="shippingFeeAmount" /></td>
				</tr> 
				<tr>
					<td class="pn-flabel" width="100px">快递名称</td>
					<td><input type="text" id="goodsAmount" name="courierName" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">快递单号</td>
					<td><input type="text" id="goodsPayAmount" name="shippingCode" /></td>
				</tr>				
				<tr>
					<td></td>
					<td>
						<input type="submit" class="defaultButton" id="submitForm" value=" 提 交 "/>
					</td>
				</tr>
		 	</table>
		</form>
	</body>
</html>

<script type="text/javascript">
//获取所有省份
$.ajax({
	   url:"${ctx}/subsystem/orderInfoAction!findAllProvice.action",
	   method:"GET",
	   dataType:"html",	  
	   data:{
		   fatherId:"",
		   addressType:1
	   },
	   success:function(data){		   
		    $("#provice").html(data);
		    getCity($("#provice option:selected").attr("provinceId")) 
	   },
	   error:function(xth,type,e){
		   alert(xth.status);
		   alert(type)
	   }
})

//省份改变
$("#provice").change(function(){
	getCity($("#provice option:selected").attr("provinceId"))

})

//城市改变
$("#city").change(function(){
	getArea($("#city option:selected").attr("cityId"))
})


function getCity(proviceIds){
	 $.ajax({
		   url:"${ctx}/subsystem/orderInfoAction!findAllCity.action",
		   method:"GET",
		   data:{
			   fatherId:proviceIds,
			   addressType:2
		   },
		   success:function(data){
			 $("#city").html(data)
			 getArea($("#city option:selected").attr("cityId"))
		   }
	     }) 

    }
		 

 //获取城市下的区域
function getArea(cityIds){
	  $.ajax({
		   url:"${ctx}/subsystem/orderInfoAction!findAllArea.action",
		   method:"GET",
		   data:{
			   fatherId:cityIds,
			   addressType:3
		   },
		   success:function(data){
			  
			  $("#area").html(data)
		   }
	     })   	 
 	  }
		
</script>