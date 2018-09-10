<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@	include file="/common/content.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
		<script>
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
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				<!-- 当前位置: 基本配置 - 公共信息管理 --><s:text name="enterprise.system.standby.current.location" />
			</div>
			<div class="ropt"><input type="button" value='<s:text name="common.word.return.back" />' onclick="history.back()"/></div>
			<div class="clear"></div>
		</div>
		<form action="standbyAction!update.action" id="form1" name="form1" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${id}"/>
		    <table width="100%" class="listTable3">
		    	<c:forEach items="${standbys }" var="standby" begin="0" end="0">
		    	<tr>
		    		<td class="pn-flabel" style="width: 250px">新浪微博地址：</td>
		    		<td class="row">	
		    			<input type="text" id="" name="standby.standby1"
							value="${standby.standby1}" class="{required:true,maxlength:35}" />
					</td>
		    	</tr>
		    	<tr>
		    		<td class="pn-flabel" style="width: 250px"><!-- 公司英文简称 --><s:text name="enterprise.system.standby.company.short.name" />：</td>
		    		<td class="row">	
		    			<input type="text" id="" name="standby.standby2"
							value="${standby.standby2}" class="{required:true,maxlength:35}" />
					</td>
		    	</tr>
		    	 <tr>
		    		<td class="pn-flabel" style="width: 250px">在线客服：</td>
		    		<td class="row">	
		    			<input type="text" id="" name="standby.standby3"
							value="${standby.standby3}" class="{required:true,maxlength:35}" />
					</td>
		    	</tr>
		    	<tr>
		    		<td class="pn-flabel" style="width: 250px">首页站内搜索框上的快速索引条件：</td>
		    		<td class="row">	
		    			<input type="text" id="" name="standby.standby4"
							value="${standby.standby4}" class="{required:true,maxlength:35}" />
					</td>
		    	</tr>
		    	<!--<tr>
		    		<td class="pn-flabel" style="width: 250px">左栏联系人：</td>
		    		<td class="row">	
		    			<input type="text" id="" name="standby.standby4"
							value="${standby.standby4}" class="{required:true,maxlength:35}" />
					</td>
		    	</tr>
		    	<tr>
		    		<td class="pn-flabel" style="width: 250px">左栏地址：</td>
		    		<td class="row">	
		    			<input type="text" id="" name="standby.standby5"
							value="${standby.standby5}" class="{required:true,maxlength:35}" />
					</td>
		    	</tr>
		    	<tr>
		    		<td class="pn-flabel" style="width: 250px">左栏电话：</td>
		    		<td class="row">	
		    			<input type="text" id="" name="standby.standby6"
							value="${standby.standby6}" class="{required:true,maxlength:35}" />
					</td>
		    	</tr>
		    	<tr>
		    		<td class="pn-flabel" style="width: 250px">左栏传真：</td>
		    		<td class="row">	
		    			<input type="text" id="" name="standby.standby7"
							value="${standby.standby7}" class="{required:true,maxlength:35}" />
					</td>
		    	</tr>
		    	<tr>
		    		<td class="pn-flabel" style="width: 250px">左栏传真：</td>
		    		<td class="row">	
		    			<input type="text" id="" name="standby.standby8"
							value="${standby.standby8}" class="{required:true,maxlength:35}" />
					</td>
		    	</tr>
		    	<tr>
		    		<td class="pn-flabel" style="width: 250px">左栏传真：</td>
		    		<td class="row">	
		    			<input type="text" id="" name="standby.standby9"
							value="${standby.standby9}" class="{required:true,maxlength:35}" />
					</td>
		    	</tr>
		    	<tr>
		    		<td class="pn-flabel" style="width: 250px">左栏传真：</td>
		    		<td class="row">	
		    			<input type="text" id="" name="standby.standby10"
							value="${standby.standby10}" class="{required:true,maxlength:35}" />
					</td>
		    	</tr>
		    	<tr>
		    		<td class="pn-flabel" style="width: 250px">左栏传真：</td>
		    		<td class="row">	
		    			<input type="text" id="" name="standby.standby11"
							value="${standby.standby11}" class="{required:true,maxlength:35}" />
					</td>
		    	</tr>
		    	<tr>
		    		<td class="pn-flabel" style="width: 250px">左栏传真：</td>
		    		<td class="row">	
		    			<input type="text" id="" name="standby.standby12"
							value="${standby.standby12}" class="{required:true,maxlength:35}" />
					</td>
		    	</tr> -->
		    	<tr>
		    		<td class="pn-flabel" style="width: 250px">公司logo：</td>
		    		<td class="row">
		    		<a href="${ctx}/upload/enterprice/${standby.bgphoto1}"
							target="_blank" name="imgchange" id="imgchange">
							<img id="imgShowTeacherPhoto" name="imgShowTeacherPhoto" border="0"
								width="96"
								src="
										<c:choose>
											<c:when test="${empty standby.bgphoto1}">
													${ctx}/upload/NoExpertPhoto.JPG
											</c:when>
											<c:otherwise>
												${ctx}/upload/enterprice/${standby.bgphoto1}
											</c:otherwise>
										</c:choose>
										" />
						</a>
						<br />
						<input  type="file" id="upload" name="upload"
							 size="30" onChange="doSelectPhotoChanged('upload');"/>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td class="pn-flabel" style="width: 250px">微信二维码：</td>
		    		<td class="row">
		    		<a href="${ctx}/upload/enterprice/${standby.bgphoto2}"
							target="_blank" name="imgchange" id="imgchange">
							<img id="imgShowTeacherPhoto" name="imgShowTeacherPhoto" border="0"
								width="96"
								src="
										<c:choose>
											<c:when test="${empty standby.bgphoto2}">
													${ctx}/upload/NoExpertPhoto.JPG
											</c:when>
											<c:otherwise>
												${ctx}/upload/enterprice/${standby.bgphoto2}
											</c:otherwise>
										</c:choose>
										" />
						</a>
						<br />
						<input  type="file" id="upload2" name="upload2"
							 size="30" onChange="doSelectPhotoChanged('upload2');"/>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td class="pn-flabel" style="width: 250px">微网站二维码：</td>
		    		<td class="row">
		    		<a href="${ctx}/upload/enterprice/${standby.bgphoto3}"
							target="_blank" name="imgchange" id="imgchange">
							<img id="imgShowTeacherPhoto" name="imgShowTeacherPhoto" border="0"
								width="96"
								src="
										<c:choose>
											<c:when test="${empty standby.bgphoto3}">
													${ctx}/upload/NoExpertPhoto.JPG
											</c:when>
											<c:otherwise>
												${ctx}/upload/enterprice/${standby.bgphoto3}
											</c:otherwise>
										</c:choose>
										" />
						</a>
						<br />
						<input  type="file" id="upload3" name="upload3"
							 size="30" onChange="doSelectPhotoChanged('upload3');"/>
		    		</td>
		    	</tr>
		    	
				</c:forEach>
		    	<tr>
		    		<td colspan="2" class="row" style="text-align: center;">
		    		 	<input type="submit" value='<s:text name="common.word.submit" />' /> 
		    		</td>
		    	</tr>
				
		    </table>
    </form>
	</body>
</html>