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
				当前位置: 商品管理 - 库存列表
			</div>
			<div class="ropt">
				<input type="button" value='<s:text name="common.word.new" />' onclick="location.href='${ctx}/collection/mcProductInfoAction!newPage.action?father=${column.id}'"/>
			</div>
			<div class="clear"></div>
		</div>
		<form action="mcProductInfoAction!queryByTitle.action" method="post">
			<table class="listTable2">
				<tr>
					<td class="pn-flabel" style="text-align:left;padding-left:15px;">
					产品名称：<input type="text" placeholder="请输入产品名称" name="title" value="${title }"/>&nbsp;&nbsp;
					
						 <input type="submit" style="margin-left:30px;" class="default_button" value='<s:text name="common.word.search" />' />
					</td>
				</tr>
			</table>
		</form>
		<table id="tableId" class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td style="width:10%;"><!-- 序号  --><s:text name="common.word.id" /></td>
				<td style="width: 20%;"><!-- 产品名称 --><s:text name="enterprise.collection.product.name" /></td>
				<td style="width: 20%;">商品库存</td>
				<td><!-- 操作  --><s:text name="common.word.handle" /></td>
			</tr>

			<c:forEach var="bean" items="${gjfProductInfos}"
				varStatus="status">
				<tr>
					<td style="text-align:center;">${status.count}</td>
					<td>${bean.name}</td>
					<td>${bean.repertory}</td>
					 	
					
					<td style="text-align:left;">
						  
						
					        &nbsp;&nbsp;
							<a href="mcProductInfoAction!retrieveMcProductInfoById.action?id=${bean.id}"><!-- 编辑  --><s:text name="common.word.edit" /></a>
					
						
				       
					</td>
				</tr>
			</c:forEach>

		</table>


		<c:if test="${not empty gjfProductInfos}">
			<s:form action="mcProductInfoAction!queryforProNum.action" method="post" id="form1" name="form1" theme="simple">
				<input type="hidden" name="father" value="${father}"/>
				<input type="hidden"  name="title" value="${title }"/>
				
				
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
				
			</s:form>
		</c:if>
	</body>
</html>