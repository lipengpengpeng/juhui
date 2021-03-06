<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-shop-top.jsp" %>
<style>
  .layui-m-layercont {
      padding: 1rem 30px;
      line-height: 1rem;
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
<body>
	<section class="add-choice-sec">
	   <c:forEach var="address" items="${resultVo.result}">
	     <c:if test="${address.isDefault==1}">
	        <ul class="add-choice-ul" >
			<li class="clearfix">
				<div class="add-choice-data add-choice-activec" onclick="choiceAddre(${address.id})">
					<p><span class="add-choic-name">${address.consigneeName}</span><span>${address.mobile}</span></p>
					<p class="add-choic-p">${address.proviceId.province}${address.cityId.city}${address.areaId.area}${address.townId.townName}   ${address.addressDetail}</p>
				</div>
				<div class="add-choice-input">
					<input type="radio" name="add-choicsole" checked="checked">
					<span class="add-choice-radio add-choice-activebg" onclick="updataDefaultAddre(${address.id})" "></span>
				</div>
			</li>
			<li>
				<div class="add-choice-ad">
					<a href="${ctx}/pc/merchant/toUpdate?id=${address.id}&goodSource=${goodSource}" class="add-choice-alter"></a>
					<i onclick="delectAdd(${address.id})" class="add-choice-delete"></i>
				</div>
			</li>
		 </ul>
	     </c:if>
	     <c:if test="${address.isDefault==0}">
	     <ul class="add-choice-ul" >
			<li class="clearfix">
				<div class="add-choice-data" onclick="choiceAddre(${address.id})">
					<p><span class="add-choic-name">${address.consigneeName}</span><span>${address.mobile}</span></p>
					<p class="add-choic-p">${address.proviceId.province}${address.cityId.city}${address.areaId.area}${address.townId.townName}   ${address.addressDetail}</p>
				</div>
				<div class="add-choice-input">
					<input type="radio" name="add-choicsole" >
					<span class="add-choice-radio" onclick="updataDefaultAddre(${address.id})"></span>
				</div>
			</li>
			<li>
				<div class="add-choice-ad">
					<a href="${ctx}/pc/merchant/toUpdate?id=${address.id}&goodSource=${goodSource}" class="add-choice-alter"></a>
					<i onclick="delectAdd(${address.id})" class="add-choice-delete"></i>
				</div>
			</li>
		  </ul>
	     </c:if>
	   </c:forEach>
	</section>
	<footer class="address-choicefooter">
		<a href="${ctx}/pc/merchant/goNewsAddress?type=${type}&goodSource=${goodSource}" class="address-adda">+添加新地址</a>
	</footer>
	<script src="../js/jquery-2.1.4.min.js"></script>
	<script src="../js/common.js"></script>
	<script type="text/javascript">
		$(function(){
			document.title = "地址管理";
			// 选中
			/* function addChoiceRadio(){
				$(".add-choice-sec").on("touchend",".add-choice-ul>li.clearfix",function(){
					$(this).find(".add-choice-radio").addClass("add-choice-activebg").parents(".add-choice-ul").siblings().find(".add-choice-radio").removeClass("add-choice-activebg");
					$(this).find(".add-choice-radio").siblings("input").prop("checked",true);
					$(this).find(".add-choice-radio").parent().siblings(".add-choice-data").addClass("add-choice-activec").parents(".add-choice-ul").siblings().find(".add-choice-data").removeClass("add-choice-activec");
					var addId=$(this).find(".add-choice-activebg").attr("addId")
					updataDefaultAddre(addId);
				})
			}addChoiceRadio(); */

			/* // 删除
			function Delete(){
				$(".add-choice-sec").on("touchend",".add-choice-delete",function(){
					$(this).parents(".add-choice-ul").remove();
				})
			}Delete(); */

		})
		
		//设置默认地址
		function updataDefaultAddre(addId){
			$.ajax({
				url:"${ctx}/wx/address/default",
				data:{
					id:addId,
					goodSource:"${goodSource}"
				},
				method:"POST",
				success:function(data){
					if(data.code==200){
						layer.open({
                            content: '设置默认地址成功',
                            btn: ['确定'],
                            yes: function(index){//点击去认证页面，index为该特定层的索引
                            	location.href='${ctx}/wx/address/toFind?type=0&goodSource=${goodSource}';
                                layer.close(index);
                            },                          
                        });
					}
				}
			})
		}
		
		//删除收货地址
		function delectAdd(i){
			
			layer.open({
	            content: '是否删除收货地址',
	            btn: ['是', '否'],
	            yes: function(index){//点击去认证页面，index为该特定层的索引
	            	$.ajax({
	    				url:"${ctx}/wx/address/del",
	    				method:"POST",
	    				data:{
	    					id:i
	    				},
	    				success:function(data){
	    					 layer.open({
	                             content: '删除成功',
	                             btn: ['确定'],
	                             yes: function(index){//点击去认证页面，index为该特定层的索引
	                                 location.href='${ctx}/pc/merchant/toFind?type=0&goodSource=${goodSource}';
	                                 layer.close(index);
	                             },
	                            
	                         });    				
	    				},
	    			})		     	     
	                layer.close(index);
	            },
	            no:function(index){
	                layer.close(index);
	            }
	        });
			
		}
		
		//点击收货地址		
		function choiceAddre(addreId){
			
			var type="${type}"
			
			sessionStorage.setItem("addreId",addreId);
			
			if(type==0&&type!=""){				
				window.history.go(-1)
				window.location.href = document.referrer;
			}else if(type==1||type==""){				
				window.history.go(-3)
				window.location.href = document.referrer;
			}else if(type==2){
				
			}
			
		}
		
	</script>
</body>
</html>