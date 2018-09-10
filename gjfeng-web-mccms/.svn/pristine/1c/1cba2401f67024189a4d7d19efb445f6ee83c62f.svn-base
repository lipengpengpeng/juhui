<%--

	pageType_new.jsp

	Create by MCGT

	Create time 2013-08-20

	Version 1.0

	Copyright 2013 Messcat, Inc. All rights reserved.

 --%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>添加模板</title>
		<script>
			$(document).ready(function(){
				$("#pageTypeForm").validate({
					rules: { 
						"pageType.name": { 
		        			required: true
		    			},
						"pageType.templateUrl": { 
		        			required: function(){
					 			if($("#templateType").val() == 'link'){
					 				return false;
					 			}
				 			}
		    			},
						"pageType.featuresUrl": { 
		        			required: function(){
					 			if($("#templateType").val() != 'other'){
					 				return false;
					 			}
				 			}
		    			}
					},
					success: function(label) {
						label.html("&nbsp;").addClass("checked");
			        }	
				});
				
			});
			
			function isShowFeatures(obj){
				if($(obj).val() == 'link'){
					$("#template").hide();
					$("#templateUrl").val('');
				}else{
					$("#template").show();
				}
				if($(obj).val() == 'other'){
					$("#features").show();
				}else{
					$("#features").hide();
					$("#featuresUrl").val('');
				}
			}
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				<!-- 当前位置: 系统配置 - 页面类型管理 --><s:text name="enterprise.system.page.type.current.location" /> -
				<!-- 新增页面类型信息 --><s:text name="enterprise.system.page.type.current.location.add" />
			</div>
			<div class="ropt"><input type="button" value="<s:text name="common.word.return.back" />" onclick="location.href='${ctx}/pageTypeAction!retrieveAllPageTypes.action'"/></div>
		</div>

		<form action="pageTypeAction!newPageType.action" method="post" id="pageTypeForm" name="pageTypeForm" enctype="multipart/form-data">
			<table  align="center" class="listTable3">
				<tr>
					<td class="pn-flabel" width="100px"><!-- 类型名称 --><s:text name="enterprise.system.page.type.name" /></td>
					<td><input type="text" id="name" name="pageType.name" size="100" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px"><!-- 模板类型 --><s:text name="enterprise.system.page.type.template.type" /></td>
					<td>
						<select id="templateType" name="pageType.templateType" onchange="isShowFeatures(this);">
							<option value="mostlist">
								<!-- 模块列表模型(mostlist) --><s:text name="enterprise.column.modulelist.model" />
							</option>
							<option value="list">
								<!-- 列表模型(list) --><s:text name="enterprise.column.list.model" />
							</option>
							<option value="content">
								<!-- 单页模型(content) --><s:text name="enterprise.column.singlecontent.model" />
							</option>
							<c:if test="${isShowLink}">
								<option value="link">
									<!-- 单链接模型(link) --><s:text name="enterprise.column.singlelink.model" />
								</option>
							</c:if>
							<option value="product">
								<!-- 产品模型(product) --><s:text name="enterprise.column.product.model" />
							</option>
							<option value="other">
								<!-- 定制模型(other) --><s:text name="enterprise.column.other.model" />
							</option>
						</select>
					</td>
				</tr>
				<tr id="template">
					<td class="pn-flabel" width="100px"><!-- URL生成模板 --><s:text name="enterprise.system.page.type.template.url" /></td>
					<td><input type="text" id="templateUrl" name="pageType.templateUrl" size="100" /></td>
				</tr>
				<tr style="display:none;" id="features">
					<td class="pn-flabel" width="100px"><!-- 新功能URL --><s:text name="enterprise.system.page.type.features.url" /></td>
					<td><input type="text" id="featuresUrl" name="pageType.featuresUrl" size="100" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px"><!-- 简介 --><s:text name="enterprise.system.page.type.intro" /></td>
					<td><textarea rows="10" cols="87" name="pageType.intro"></textarea></td>
				</tr>

				<tr>
					<td colspan="2" style="text-align:center;">
						<input type="submit" value=' <s:text name="common.word.submit" /> '/>
					</td>
				</tr>
		 	</table>
		 	<s:token></s:token>
		</form>
	</body>
</html>