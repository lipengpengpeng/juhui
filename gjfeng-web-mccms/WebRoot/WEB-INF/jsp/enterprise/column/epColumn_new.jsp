<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
		<link href="${ctx}/js/validate/jquery.validate.css" type="text/css"
			rel="stylesheet" />
			<link href="${ctx}/js/AsyncBox/skins/ZCMS/asyncbox.css" type="text/css" rel="stylesheet" />
			<script type="text/javascript" src="${ctx}/js/AsyncBox/AsyncBox.v1.4.js"></script>
		<script src="${ctx}/js/validate/jquery.validate.js"
			type="text/javascript"></script>
		<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
		<script src="${ctx}/js/live-preview.js" type="text/javascript"></script>
		<script type="text/javascript">

			$(document).ready(function(){
				$("#names").focus();
				$("#columnForm").validate({
					 rules: { 
						names: { 
		        			required: true, 
		        			maxlength:50,
		        			remote: "epColumnAction!checkName.action?orgName=''&columnName="+$("#father").attr("value")+"&math="+Math.random()
		    			},
		            	shortName: {
		                    required: true ,
					        maxlength:50
		        	    },
		        	    orderColumn:{
		        	    	required: true ,
		        	    	number:true
		        	    },
		        	    linkUrl:{
		        	    	url:true,
		        	    	maxlength:100
		        	    },
		        	    typeColumn:{
		        	    	required: true ,
		        	    	maxlength:50
		        	    },
		        	    frontNum:{
		        	    	maxlength:20
		        	    },
		        	    num:
		        	    {
		        	    	maxlength:20
		        	    },
		        	    intro:
		        	    {
		        	    	maxlength:500
		        	    }
					}
				});
				
				document.getElementById("imgchange").rel=$("#imgShowTeacherPhoto").attr("src");
			});

			//论坛栏目树
			function listEnterpriseColumn() {
				asyncbox.open({
					//论坛树
					title: '栏目树',
					url : '${ctx}/epColumnTreeAction!generateTree.action',
					data : {
						<c:if test="${not empty father}">
							father : ${father},
						</c:if>
					},
					width : 300,
		    		height : 440,
					btnsbar : $.btn.OKCANCEL,
					callback : function(action, iframe) {
						//alert(iframe.returnValue);
						if(action == 'ok') {
							$("#columnName").val(iframe.returnValue);
							$("#father").val(iframe.returnId);
						}
					}
				});
			}

			//当选择图片时发生
			function doSelectPhotoChanged(elepath){
				filePath = document.getElementById(elepath).value;
				
				if(checkFileUpload(elepath)){
					if(window.navigator.userAgent.indexOf("Chrome")<1){
						/*document.getElementById(elepath).value = "";
						document.getElementById("imgShowTeacherPhoto").src=filePath;
						document.getElementById("imgchange").rel=$("#imgShowTeacherPhoto").attr("src");*/
					}
				}
			}
			
			//文件上传验证
			function checkFileUpload(elepath)
			{
			  var path = document.getElementById(elepath).value;
			  if(window.navigator.userAgent.indexOf("Chrome")<1){
			  	   if(path.indexOf(".jpg")<1 && path.indexOf(".gif")<1 &&  path.indexOf(".bmp")<1 &&  path.indexOf(".png")<1 && path.indexOf(".jpeg")<1){
			  	   	//图片类型不正确，请上传正确的格式！
				    alert('<s:text name="enterprise.collection.message.phototype.error" />\n(jpg,gif,bmp,png,jpeg)');
			  	   		cleanFilePath(elepath);
				   		return false;
			  	   }
			  }else{
				  if(!sy_FilterFile(path,"jpg,gif,bmp,png,jpeg")){
				    //图片类型不正确，请上传正确的格式！
				    alert('<s:text name="enterprise.collection.message.phototype.error" />\n(jpg,gif,bmp,png,jpeg)');
				    cleanFilePath(elepath);
				    return false;
				  }
				  if(sy_GetImageToGetSize(path).fileSize>1024000){
				    //上传文件大小不能够超过1MB！
				    alert('<s:text name="enterprise.collection.message.photosize.error" />');
				    cleanFilePath(elepath);
				    return false;
				  }
			  }
			  return true;
			}
			//提交表单前验证准备上传的文件
			function doBeforeSubmit(){
				if(document.getElementById("upload").value!=""){
					if(!checkFileUpload()){
						return false;
					}
				}
			}
			//验证上传文件错误后删除file框内路径
			function cleanFilePath(elepath){
				if(window.navigator.userAgent.indexOf("MSIE")<1){
					$("#"+elepath).attr("value","");
				}else{
					var file = $("#"+elepath);
					file.after(file.clone().val(""));
					file.remove();
				}
			}
		</script>
		<style>
			#typeColumn optgroup{background-color:#C1EBFF;}
		</style>
	</head>
	<body style="padding: 8px;">
		<div class="rhead">
			<div class="rpos">
				<!-- 当前位置: 栏目配置 --><s:text name="enterprise.column.current.location" /> - 
				<!-- 添加栏目信息 --><s:text name="enterprise.column.current.location.addcolumn" />
			</div>
			<div class="ropt">
				<input type="button" value='<s:text name="common.word.return.back" />' onclick="history.back()" />
			</div>
			<div class="clear"></div>
		</div>
		<form action="epColumnAction!add.action" method="post" id="columnForm" enctype="multipart/form-data">
			<table align="center" class="listTable3" width="90%">
				<tr>
					<td class="pn-flabel">
						<!-- 父级栏目 --><s:text name="enterprise.column.father.column" />
					</td>
					<td>
					<input type="text" name="columnName" id="columnName" value="${fatherName}" onfocus="listEnterpriseColumn();" readonly/>
							<font color="red"><!-- 注意选好父栏目，免得以后重新选 --><s:text name="enterprise.column.care.select.father" /></font>
							<input type="hidden" id="father" name="enterpriseColumn.father" value="<s:property value='father'/>"/>
						</td>
				</tr>
				<tr>
					<td class="pn-flabel">
						<!-- 栏目名称 --><s:text name="enterprise.column.name" />
					</td>
					<td>
						<input type="text" id="names" name="names" size="60" />
						<!-- 请不要重复 --><s:text name="enterprise.column.do.not.duplicate" />
					</td>
				</tr>
				<tr>
					<td class="pn-flabel">
						<!-- 栏目简称 --><s:text name="enterprise.column.short.name" />
					</td>
					<td>
						<input type="text" name="shortName" size="60" />
						<!-- 一般显示在前台 --><s:text name="enterprise.column.show.in.front" />
					</td>
				</tr>
				<tr>
					<td class="pn-flabel">
						<!-- 栏目模块标识 --><s:text name="enterprise.column.module.name" />
					</td>
					<td>
						<input type="text" name="frontNum" size="60" />
						<!-- 模块标识的设置将影响到页面的显示效果,请参考开发文档 -->
						<s:text name="enterprise.column.module.name.warn" />
					</td>
				</tr>
				<tr>
					<td class="pn-flabel">
						<!-- 栏目排序 --><s:text name="enterprise.column.order" />
					</td>
					<td>
						<input type="text" name="orderColumn" size="60" value="${orderColumn}"/>
						<!-- 数字越小,排序越前,同级同栏目下请不要重复,以免影响效果 -->
						<s:text name="enterprise.column.module.order.warn" />
					</td>
				</tr>
				<tr>
					<td class="pn-flabel">
						<!-- 模板类型 --><s:text name="enterprise.column.template.type" />
					</td>
					<td>
						<select id="typeColumn" name="enterpriseColumn.typeColumn.id" id="typeColumn">
							<c:forEach var="mostlistType" items="${pageTypeOut.mostlistTypes}" varStatus="info">
								<c:if test="${info.first}">
									<!-- 模块列表模型(mostlist) -->
									<optgroup label="<s:text name="enterprise.column.modulelist.model" />">
								</c:if>
									<option value="${mostlistType.id}">
										${mostlistType.name}(${mostlistType.templateType})
									</option>
								<c:if test="${info.last}">
									</optgroup>
								</c:if>
							</c:forEach>
							<c:forEach var="listType" items="${pageTypeOut.listTypes}" varStatus="info">
								<c:if test="${info.first}">
									<!-- 列表模型(list) -->
									<optgroup label="<s:text name="enterprise.column.list.model" />">
								</c:if>
									<option value="${listType.id}">
										${listType.name}(${listType.templateType})
									</option>
								<c:if test="${info.last}">
									</optgroup>
								</c:if>
							</c:forEach>
							<c:forEach var="contentType" items="${pageTypeOut.contentTypes}" varStatus="info">
								<c:if test="${info.first}">
									<!-- 单页模型(content) -->
									<optgroup label="<s:text name="enterprise.column.singlecontent.model" />">
								</c:if>
									<option value="${contentType.id}">
										${contentType.name}(${contentType.templateType})
									</option>
								<c:if test="${info.last}">
									</optgroup>
								</c:if>
							</c:forEach>
							<c:if test="${not empty pageTypeOut.linkType}">
								<option value="${pageTypeOut.linkType.id}">
									<!-- 单链接模型(link) --><s:text name="enterprise.column.singlelink.model" />
								</option>
							</c:if>
							<c:forEach var="productType" items="${pageTypeOut.productTypes}" varStatus="info">
								<c:if test="${info.first}">
									<!-- 产品模型(product) -->
									<optgroup label="<s:text name="enterprise.column.product.model" />">
								</c:if>
									<option value="${productType.id}">
										${productType.name}(${productType.templateType})
									</option>
								<c:if test="${info.last}">
									</optgroup>
								</c:if>
							</c:forEach>
							<c:forEach var="otherType" items="${pageTypeOut.otherTypes}" varStatus="info">
								<c:if test="${info.first}">
									<!-- 定制模型(other) -->
									<optgroup label="<s:text name="enterprise.column.other.model" />">
								</c:if>
									<option value="${otherType.id}">
										${otherType.name}(${otherType.templateType})
									</option>
								<c:if test="${info.last}">
									</optgroup>
								</c:if>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel">
						<!-- 是否在主导航显示 --><%-- <s:text name="enterprise.column.is.showed.on.index" /> --%>
						是否在首页显示
					</td>
					<td>
						<select name="enterpriseColumn.isValidInNav">
							<option value="0">
								<!-- 否 --><s:text name="common.word.no" />
							</option>
							<option value="1">
								<!-- 是 --><s:text name="common.word.yes" />
							</option>
						</select>
						<!-- (只有一级导航才能进行设置生效) --><s:text name="enterprise.column.is.showed.on.index.desc" />
					</td>
				</tr>

				<tr>
					<td class="pn-flabel">
						<!-- 链接地址 --><s:text name="enterprise.column.url" />
					</td>
					<td>
						<input type="text" name="linkUrl" size="60" />
						<!-- 只有栏目设置为link时,此字段才有效果 --><s:text name="enterprise.column.url.desc" />
					</td>
				</tr>

				<tr>
					<td class="pn-flabel">
						<!-- 栏目编号 --><s:text name="enterprise.column.id" />
					</td>
					<td>
						<input type="text" name="num" size="60" />
						<!-- 可自定义 --><s:text name="enterprise.column.can.custom" />
					</td>
				</tr>
				<tr>
					<td class="pn-flabel">
						栏目banner
					</td>
					<td>
						<%-- <a href="${ctx}/upload/NoExpertPhoto.JPG"
							target="_blank" name="imgchange" id="imgchange"
							class="screenshot" rel="doSelectPhotoChanged('upload')"><img
								id="imgShowTeacherPhoto" name="imgShowTeacherPhoto" border="0"
								width="96" src="${ctx}/upload/NoExpertPhoto.JPG" />
						</a>
						<br /> --%>
						<div id="divPreview0">
			          		<img id="upload" name="upload" src="${ctx}/upload/NoExpertPhoto.JPG"  width="96" border="0"  />
			          	</div>
						<input type="file" id="upload" name="upload" onchange="PreviewImage(this,'upload','divPreview0')" size="30" />
					</td>
				</tr>
				<tr>
					<td class="pn-flabel">
						栏目图片
					</td>
					<td>
						<%-- <a href="${ctx}/upload/NoExpertPhoto.JPG"
							target="_blank" name="imgchange" id="imgchange"
							class="screenshot" rel="doSelectPhotoChanged('upload1')"><img
								id="imgShowTeacherPhoto" name="imgShowTeacherPhoto" border="0"
								width="96" src="${ctx}/upload/NoExpertPhoto.JPG" />
						</a>
						<br /> --%>
						<div id="divPreview1">
			          		<img id="upload1" name="upload1" src="${ctx}/upload/NoExpertPhoto.JPG"  width="96" border="0"  />
			          	</div>
						<input type="file" id="upload1" name="upload1" onchange="PreviewImage(this,'upload1','divPreview1')" size="30" />
					</td>
				</tr>
				<tr>
					<td class="pn-flabel">
						<!-- 栏目介绍 --><s:text name="enterprise.column.description" />
					</td>
					<td>
						<textarea rows="10" cols="47" name="intro"></textarea>
						<!-- 可自定义 --><s:text name="enterprise.column.can.custom" />
					</td>
				</tr>
				<tr>
					<td class="pn-flabel">
						<!-- 状态 --><s:text name="common.word.status" />
					</td>
					<td>
						<select name="enterpriseColumn.state">
							<option value="1">
								<!-- 已启用 --><s:text name="common.word.has.opened" />
							</option>
							<option value="0">
								<!-- 已停用 --><s:text name="common.word.has.closed" />
							</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value='<s:text name="common.word.submit"/>' />
						<input type="button" value='<s:text name="common.word.return.back"/>' onclick="history.back()" />
					</td>
				</tr>
			</table>
			<s:token></s:token>
		</form>
	</body>
</html>

