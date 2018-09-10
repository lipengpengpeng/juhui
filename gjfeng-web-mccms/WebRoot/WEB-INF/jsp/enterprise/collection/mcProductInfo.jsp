<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
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
			
			.ratio{
			
				width: 55px;
			}
			
		</style>
 <script src="${ctx}/js/jquery-1.11.2.min.js" type="text/javascript"></script>	
 <script src="${ctx}/js/layer/layer.js" type="text/javascript"></script>
		<script><!-- 
			function doYouWantToDel() {
				if(confirm('<s:text name="common.word.want.to.delete" />')) {
					return true;
				}
				return false;
			}
			function opProduct(t) {
			   
				var op = t.getAttribute("op");
				var uri = t.getAttribute("href2");
				var id = t.getAttribute("class_id");
				if( op== "downProduct")
				{
			        layer.use('extend/layer.ext.js', function(){
					    layer.prompt({
						  title: '请输入违规原因',
						  maxlength: 300, //可输入文本的最大长度，默认500,
						  formType: 2 //prompt风格，支持0-2
						}, function(text){
							$.ajax({
								type:"post",
								dataType:"text",
								url: uri , 
								data:{
									reason : text,
									id : id
								},
								success:function(data){
									if(data != null){
										layer.msg(data);
									}
									setTimeout("location.reload();",1000);
								},
								error:function(){
									layer.msg("操作失败！");
								}
							});
						  });
					   });
				     return false;
				}
				else if(op == "passProduct")
				{
					
					/* if(t.getAttribute("class_id")=="" ||t.getAttribute("class_id")==null){
						layer.msg("该商品还没有编辑商品类别！");
						return false;
					} */
					layer.confirm('确定通过审核？', {
					  btn: ['确定','取消'] //按钮
					  
					}, function(){
						ajaxSubmit(uri,id);
					}, function(){//取消
					});
					
				}
				else if( op == "notPassProduct")
				{
					layer.confirm('确定不通过审核？', {
					  btn: ['确定','取消'] //按钮
					}, function(){
						ajaxSubmit(uri,id);
					}, function(){//取消
					});
				}else if( op == "downPro")
				{
					layer.confirm('确定下架该商品？', {
					  btn: ['确定','取消'] //按钮
					}, function(){
						ajaxSubmit(uri,id);
					}, function(){//取消
					});
				}
				else if( op == "upProduct")
				{
					layer.confirm('确定上架该商品？', {
					  btn: ['确定','取消'] //按钮
					}, function(){
						ajaxSubmit(uri,id);
					}, function(){//取消
					});
				}
				 
			}
			
			 function ajaxSubmit(uri,id){
			 	$.ajax({
					type:"post",
					dataType:"text",
					url: uri , 
					data:{
						id : id
					},
					success:function(data){
						if(data != null){
							layer.msg(data);
						}
						setTimeout("location.reload();",1000);
					},
					error:function(){
						layer.msg("操作失败！");
					}
				});
			 }
		 </script>
		 
		 <script type="text/javascript">
			function realIncomeRatioChange(s){
			    var par=s.parentNode;
				var chils= par.childNodes;
				var  realIncomeRatio = s.value;
				if( isNaN(realIncomeRatio ) || realIncomeRatio==""){
					alert('您输入的“实收比例”不是有效数字,请重新输入！');
					chils[1].value=100.0;
					chils[1].onchange();
					
					return false;
				}
				if(realIncomeRatio>100){
					alert('请输入的0~100的数字！');
					chils[1].value=100.0;
					chils[1].onchange();
					return false;
				}
				if(realIncomeRatio<0){
					alert('请输入的0~100以内的数字！');
					chils[1].value=100.0;
					chils[1].onchange();
					return false;
				}
				 
				var d = 0.0;
				d = (100.0- realIncomeRatio).toFixed(2);
				var par=s.parentNode;
				var chils= par.childNodes; 
				chils[3].value = d;
			
			}
			
			function editClick(s){
				var par=s.parentNode;
				var chils= par.childNodes; 
				chils[1].disabled=false;
				chils[7].disabled=false;
			}
			
			function saveEdit(s){
				
			    var par=s.parentNode;
				var chils= par.childNodes;
				
				chils[7].disabled=true; 
			    var realIncomeRatio= chils[1].value;
				var gfitRatio=chils[3].value;
				
				if(isNaN(realIncomeRatio)){
					alert('您输入的“实收比例”不是有效数字,请重新输入！');
					chils[1].value=100.0;
					chils[1].onchange();
					return false;
				}
				if(isNaN(gfitRatio)){
					alert('您输入的“赠与比例”不是有效数字,请重新输入！');
					chils[1].value=100.0;
					chils[1].onchange();
					return false;
				}
				 
				if(parseFloat(realIncomeRatio) + parseFloat(gfitRatio) > 100 ){
					alert("您输入的“比例”超过100%,请重新输入！");
					chils[1].value=100.0;
					chils[1].onchange();
					return false;
				}
			     
				$.ajax({     
				    url:'collection/mcProductInfoAction!updataProductRatio.htm',     
				    type:'post',     
				    data:{
				    	productId:chils[9].value,
				    	realIncomeRatio:chils[1].value,
				    	giftRatio:chils[3].value
				    },     
				    async : false, //默认为true 异步     
				    error:function(){     
				        chils[7].disabled=true; 
				    },     
				    success:function(data){   
				       alert(eval('(' + data + ')').msg);
				    	//编辑框
				       chils[1].disabled=true;
				       chils[3].disabled=true;
				       $("#giftPoint"+chils[9].value).html(eval('(' + data + ')').giftPoint);
				    }  
				});  
			
			}
		 
		 </script>
		
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 产品库 - 产品库列表
			</div>
			<div class="ropt">
				<input type="button" value='<s:text name="common.word.new" />' onclick="location.href='${ctx}/collection/mcProductInfoAction!newPage.action?father=${column.id}'"/>
			</div>
			<div class="clear"></div>
		</div>
		<form action="mcProductInfoAction!query.action" method="post">
			<table class="listTable2">
				<tr>
					<td class="pn-flabel" style="text-align:left;padding-left:15px;">
					产品名称：<input type="text" placeholder="请输入产品名称" name="title" value="${title }"/>&nbsp;&nbsp;
					店铺名称：<input type="text" placeholder="请输入店铺名称" name="storeName" value="${storeName }"/>&nbsp;&nbsp;
					商品状态： <select name="status" id="status">
							       <option value=""  <c:if test="${empty status}">selected="selected"</c:if>>全部</option>
						           <option value="0" <c:if test="${status eq '0'}">selected="selected"</c:if>>下架 </option>
						           <option value="1" <c:if test="${status eq '1'}">selected="selected"</c:if>>正常</option>
								   <option value="2" <c:if test="${status eq '2'}">selected="selected"</c:if>>违规 </option>
					  		</select>&nbsp;&nbsp;
					审核状态：
					  <select name="aduitStatus" id="aduitStatus">
					   <option value=""  <c:if test="${empty aduitStatus}">selected="selected"</c:if>>全部</option>
					   <option value="0" <c:if test="${aduitStatus eq '0'}">selected="selected"</c:if>>未通过</option>
					   <option value="1" <c:if test="${aduitStatus eq '1'}">selected="selected"</c:if>>通过 </option>
					   <option value="2" <c:if test="${aduitStatus eq '2'}">selected="selected"</c:if>>审核中 </option>
					 </select> 
						  <input type="submit" style="margin-left:30px;" class="default_button" value='<s:text name="common.word.search" />' />
					</td>
				</tr>
			</table>
		</form>
		<table id="tableId" class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td><!-- 序号  --><s:text name="common.word.id" /></td>
				<td style="width: 20%;"><!-- 产品名称 --><s:text name="enterprise.collection.product.name" /></td>
				<td><!-- 商家名称 --> 店铺名称  </td>
				<td>产品类型</td>
				<td>价格</td>
				<td><!--  --><s:text name="common.word.update.time" /></td>
				<td>商品状态 </td>
				<td>审核状态 </td>
				
				<td><!-- 操作  --><s:text name="common.word.handle" /></td>
			</tr>

			<c:forEach var="bean" items="${gjfProductInfos}"
				varStatus="status">
				<tr>
					<td style="text-align:center;">${status.count}</td>
					<td>${bean.name}</td>
					<td>
						<c:if test="${bean.storeId.id != 0}">${bean.storeId.storeName}</c:if>
					</td>
					<td>
						<c:choose>
							<c:when test="${bean.storeId.storePro eq 0 }">O2O</c:when>
							<c:when test="${bean.storeId.storePro eq 1 }">网上商城</c:when>
						</c:choose>
					</td>
					<td>
						零售价：<font color="#333333"><fmt:formatNumber value="${bean.price}" pattern="0.00"></fmt:formatNumber></font><br/>
						市场价：<font color="red"><fmt:formatNumber value="${bean.marketPrice}" pattern="0.00"></fmt:formatNumber></font><br/>
					</td>
					
					<td style="text-align:center;"><fmt:formatDate value="${bean.updateTime}" pattern="yyyy-MM-dd" /></td>
					
					<td style="text-align:center;" class="checkStatusTr">
						 	<c:choose>
								<c:when test="${bean.status == 0}">
								下架
								</c:when>
								<c:when test="${bean.status == 1}">
								正常
								</c:when>
								<c:when test="${bean.status == 2}">
								违规
								</c:when>
								<c:otherwise></c:otherwise>
							</c:choose>
					</td>
					
					
					<td style="text-align:center;" class="checkVerifyTr">
						 	<c:choose>
								<c:when test="${bean.aduitStatus eq '0'}">未通过</c:when>
								<c:when test="${bean.aduitStatus eq '1'}">通过</c:when>
								<c:when test="${bean.aduitStatus eq '2'}">审核中</c:when>
								<c:otherwise> </c:otherwise>
							</c:choose>
					</td>

					<td style="text-align:left;">
						  
						<a href="mcProductInfoAction!viewPage.action?id=${bean.id}">查看</a>
						&nbsp;
						<security:authorize ifAnyGranted="GJF_PRODUCT_LIST_UPDATE">
							<a href="mcProductInfoAction!retrieveMcProductInfoById.action?id=${bean.id}"><!-- 编辑  --><s:text name="common.word.edit" /></a>
							&nbsp; 
						</security:authorize>
						<%-- <security:authorize ifAnyGranted="GJF_PRODUCT_LIST_DELETE">
							<a href="mcProductInfoAction!delMcProductInfo.action?father=${father}&&id=${bean.id}"
									onclick="return doYouWantToDel();"><!-- 删除  --><s:text name="common.word.delete" /></a>
							&nbsp; 
						</security:authorize> --%>
						<a href="${ctx}/subsystem/productAttrStockAction!retrieveProductAttrStockByProId.action?proId=${bean.id}">查看属性</a>
						<security:authorize ifAnyGranted="GJF_PRODUCT_LIST_CHECK">
						<!-- 审核通过  违规下架 -->
				        <c:choose>
							<c:when test="${bean.aduitStatus eq '0'}"> <!-- 未通过    -->
							&nbsp;&nbsp;  
							</c:when>
							<c:when test="${bean.aduitStatus eq '1'}"> <!-- 通过   -->
							&nbsp;&nbsp;  
							  <c:if  test="${bean.status eq '1'}">
							  	 <a href="javaScript:void(0);" href2="mcProductInfoAction!updateProductStatus.action?op=downProduct"
							      op="downProduct" onclick="return opProduct(this);" class_id="${bean.id}">违规</a>
							      &nbsp;&nbsp;
							      <a href="javaScript:void(0);" href2="mcProductInfoAction!updateProductStatus.action?op=downPro"
							      op="downPro" onclick="return opProduct(this);" class_id="${bean.id}">下架</a>
							  </c:if>

							  <c:if  test="${bean.status eq '0'||bean.status eq '2'}">

							  	 <a href="javaScript:void(0);" href2="mcProductInfoAction!updateProductStatus.action?op=upProduct"
							      op="upProduct" onclick="return opProduct(this);" class_id="${bean.id}">商品上架</a>
							  </c:if>
							</c:when>
						<c:when test="${bean.aduitStatus eq '2'}"> <!-- 审核中   -->
						&nbsp;&nbsp;   
						<a href="javaScript:void(0);" href2="mcProductInfoAction!updateProductStatus.action?op=passProduct"
							op="passProduct" onclick="return opProduct(this);" class_id="${bean.id}" >通过</a>
						&nbsp;&nbsp;  
						<a href="javaScript:void(0);" href2="mcProductInfoAction!updateProductStatus.action?op=notPassProduct"
							op="notPassProduct" onclick="return opProduct(this);" class_id="${bean.id}" >不通过</a>
							</c:when>
							<c:otherwise> </c:otherwise>
						</c:choose>
						&nbsp;&nbsp; 
						</security:authorize> 
						<a href="mcProductInfoAction!findProductCommentById.action?productId=${bean.id}">查看评论</a>
					</td>
				</tr>
			</c:forEach>

		</table>


		<c:if test="${not empty gjfProductInfos}">
			<s:form action="mcProductInfoAction!query.action" method="post" id="form1" name="form1" theme="simple">
				<input type="hidden" name="father" value="${father}"/>
				<input type="hidden"  name="title" value="${title }"/>
				<input type="hidden" name="storeName" value="${storeName }"/>
				<select name="status" style="display: none;">
				       <option value=""  <c:if test="${empty status}">selected="selected"</c:if>>全部</option>
			           <option value="0" <c:if test="${status eq '0'}">selected="selected"</c:if>>下架 </option>
			           <option value="1" <c:if test="${status eq '1'}">selected="selected"</c:if>>正常</option>
					   <option value="2" <c:if test="${status eq '2'}">selected="selected"</c:if>>违规 </option>
				</select>
		  		<select name="aduitStatus" style="display: none;">
				   <option value=""  <c:if test="${empty aduitStatus}">selected="selected"</c:if>>全部</option>
				   <option value="0" <c:if test="${aduitStatus eq '0'}">selected="selected"</c:if>>未通过</option>
				   <option value="1" <c:if test="${aduitStatus eq '1'}">selected="selected"</c:if>>通过 </option>
				   <option value="2" <c:if test="${aduitStatus eq '2'}">selected="selected"</c:if>>审核中 </option>
				 </select> 
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
				
			</s:form>
		</c:if>
	</body>
</html>