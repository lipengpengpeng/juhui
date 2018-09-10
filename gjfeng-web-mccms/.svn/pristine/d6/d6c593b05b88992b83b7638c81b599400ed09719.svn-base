<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>MCCMS</title>
<script language="javascript" type="text/javascript">
<!--
	var userAgent = "IE";
	
	if(window.navigator.userAgent.indexOf("Chrome")>=1){
		userAgent = "Chrome";
	}
//-->
</script>
<script>
	$(document).ready(function(){
		
		$("#title").focus();
		$("#epNewsForm").validate({
			 rules: { 
			 	contents: {
        			maxlength:200000
				}
			},
			success: function(label) {
				label.html("&nbsp;").addClass("checked");
	        }	
		});
		
		$("#epNewsForm").submit(function() {
			
		  	 if($("#isPrimPhoto").attr("value")=='1' && $("#uploadImage").attr("value")==''){
		  	 	//主要图片新闻必须上传图片,请上传图片之后再提交！
	  	   		alert('<s:text name="enterprise.collection.message.photonews.error" />');
		  	 	return false;
		  	 }
		  	 if($("#isPrimPhoto").attr("value")=='2' && $("#uploadFile").attr("value")==''){
		  	 	//文件下载新闻必须上传文件,请上传文件之后再提交！
	  	   		alert('<s:text name="enterprise.collection.message.filenews.error" />');
		  	 	return false;
		  	 }
		 });
		
		document.getElementById("imgchange").rel=$("#imgShowTeacherPhoto").attr("src");
		
		$("#isPrimPhoto").change(function (){
			if(this.options[this.selectedIndex].value=='1'){
				$('#uploadImageTr').show();
				$("#uploadImage").attr("disabled","");
				
				$('#uploadFileTr').hide();
				$('#uploadFile').attr("disabled","false");
			 }else if(this.options[this.selectedIndex].value=='2'){
				$('#uploadImageTr').show();
				$("#uploadImage").attr("disabled","");
				
				$('#uploadFileTr').show();
				$('#uploadFile').attr("disabled","");
			 }else{
				$('#uploadImageTr').hide();
				$("#uploadImage").attr("disabled","false");
				
				$('#uploadFileTr').hide();
				$('#uploadFile').attr("disabled","false");
			 }
		});
	
	});		

	//当选择图片时发生
	function doSelectPhotoChanged(){
		filePath = document.getElementById("uploadImage").value;
		
		if(checkFileUpload(filePath)){
			if(userAgent != "Chrome"){
				  document.getElementById("uploadImage").value = "";
				  document.getElementById("imgShowTeacherPhoto").src=filePath;
				  document.getElementById("imgchange").rel=$("#imgShowTeacherPhoto").attr("src");
		 	}
		}
	}
	
	//图片上传验证
	function checkFileUpload()
	{
	  var path = $("#uploadImage").attr("value");
	  if(userAgent != "Chrome"){
	  	   if(path.indexOf(".jpg")<1 && path.indexOf(".gif")<1 &&  path.indexOf(".bmp")<1 &&  path.indexOf(".png")<1 && path.indexOf(".jpeg")<1){
	  	   		//图片类型不正确，请上传正确的格式！
	  	   		alert('<s:text name="enterprise.collection.message.phototype.error" />\n(jpg,gif,bmp,png,jpeg)');
	  	   		$("#uploadImage").attr("value","");
		   		return false;
	  	   }
	  }else{	
		  if(!sy_FilterFile(path,"jpg,gif,bmp,png,jpeg")){
		    //图片类型不正确，请上传正确的格式！
	  	   	alert('<s:text name="enterprise.collection.message.phototype.error" />\n(jpg,gif,bmp,png,jpeg)');
		    $("#uploadImage").attr("value","");
		    return false;
		  }
		  if(sy_GetImageToGetSize(path).fileSize>1024000){
		    //上传图片大小不能够超过1MB！
		    alert('<s:text name="enterprise.collection.message.photosize.error" />');
		    $("#uploadImage").attr("value","");
		    return false;
		  }
	   }
	  return true;
	}
	//提交表单前验证准备上传的文件
	function doBeforeSubmit(){
		if(document.getElementById("uploadImage").value!=""){
			if(!checkFileUpload()){
				return false;
			}
		}
	}
	//文件上传验证
	function checkDoFileUpload()
	{
	  var path = $("#uploadFile").attr("value");
	  if(userAgent != "Chrome"){
	  	   if(path.indexOf(".doc")<1 && path.indexOf(".xls")<1 &&  path.indexOf(".pdf")<1 &&  path.indexOf(".docx")<1 && path.indexOf(".rar")<1 && path.indexOf(".txt")<1 && path.indexOf(".zip")<1){
	  	   		//文件类型不正确，请上传正确的格式！
	  	   		alert('<s:text name="enterprise.collection.message.filetype.error" />\n(doc,xls,pdf,docx,rar,txt,zip)');
	  	   		$("#uploadFile").attr("value","");
		   		return false;
	  	   }
	  }else{
		  if(!sy_FilterFile(path,"doc,xls,pdf,docx,rar,txt,zip")){
		    //文件类型不正确，请上传正确的格式！
	  	   		alert('<s:text name="enterprise.collection.message.filetype.error" />\n(doc,xls,pdf,docx,rar,txt,zip)');
		    $("#uploadFile").attr("value","");
		    return false;
		  }
		  if(sy_GetFileToGetSize(path).fileSize>4096000){
		    //上传文件大小不能够超过4MB！
		    alert('<s:text name="enterprise.collection.message.filesize.error" />');
		    $("#uploadFile").attr("value","");
		    return false;
		  }
	  }
	  return true;
	}
	
	//提交表单前验证准备上传的文件
	function doFileBeforeSubmit(){
		if(document.getElementById("uploadFile").value!=""){
			if(!checkDoFileUpload()){
				return false;
			}
		}
	}
	
</script>
	<style>
		.notview{
			display:none;
		}
	</style>
	</head>
	<body>
	
 	    <div class="rhead">
			<div class="rpos"><!-- 当前位置: 内容管理 --><s:text name="enterprise.collection.current.location" /> - ${column.names} - 
			<!-- 添加新闻信息 --><s:text name="enterprise.collection.current.location.newsadd" /></div>
			<div class="ropt">
				<!-- 返回列表 -->
				<input type="button" value='<s:text name="common.word.return.back" />' onclick="history.back()"/>
			</div>
			<div class="clear"></div>
		</div>
		<form action="epNewsAction!add.action" method="post" name="epNewsForm" id="epNewsForm" enctype="multipart/form-data" method="post">
		<table  align="center" class="listTable3">
			<tr>
				<td class="pn-flabel"><font color="red">*</font><!-- 栏目 --><s:text name="enterprise.collection.column" /></td>
				<td>
					<input type="hidden" name="enterpriseNews.enterpriseColumn.id" value="${column.id}"/>
					<input type="hidden" name="enterpriseNews.enterpriseColumn.typeColumn.id" value="${column.typeColumn.id}"/>
					&nbsp;&nbsp;${column.names}
				</td>
			</tr>
			<tr>
				<td class="pn-flabel"><!-- 选择文章类型: --><s:text name="enterprise.collection.select.newstype" /></td>
				<td>
				<!-- 当为下载模板的时候不上传图片，只上传文件 -->
						<select name="enterpriseNews.isPrimPhoto" id="isPrimPhoto">
							<option value="0"><!-- 普通新闻 --><s:text name="enterprise.collection.select.commonnews" /></option>
							<option value="2"><!-- 文件新闻 --><s:text name="enterprise.collection.select.filenews" /></option>
							<option value="1"><!-- 图片新闻 --><s:text name="enterprise.collection.select.photonews" /></option>
						</select>
				</td>
			</tr>
				<tr class="pic-box" id="uploadImageTr" style="display:none;">
					<td class="pn-flabel"><!-- 上传图片 --><s:text name="enterprise.collection.upload.photo" /></td>
					<td>
						<a href="${ctx}/upload/enterprice/${enterpriseNews.photo}"
									target="_blank" name="imgchange" id="imgchange"
									class="screenshot" rel="doSelectPhotoChanged()"><img
										id="imgShowTeacherPhoto" name="imgShowTeacherPhoto" border="0"
										width="96"
										src="
											<c:choose>
												<c:when test="${empty enterpriseNews.photo}">
														${ctx}/upload/NoExpertPhoto.JPG
												</c:when>
												<c:otherwise>
													${ctx}/upload/enterprice/${enterpriseNews.photo}
												</c:otherwise>
											</c:choose>
											"/>
								</a>
								<br />
								<input type="file" id="uploadImage" name="uploadImage" value=""
									onchange="doSelectPhotoChanged();"  size="30"/>
								<br/>
								<label class="comments" style="color:red">
									<!-- （图片的大小请不要超过20M,建议为50K左右,图片显示的最优像素为：宽:185px * 高:152px。） -->
									*首页轮播图图片规格为：776Px * 300Px
								</label>
								<br/>
								<!-- 是否生成缩略图： --><s:text name="enterprise.collection.upload.photo.thumbnails" />
								<input type="radio" name="autoCutPhoto" value="1"/>
								<!-- 是 --><s:text name="common.word.yes" />&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="autoCutPhoto" value="0"  checked="checked"/>
								<!-- 否 --><s:text name="common.word.no" />
					</td>
				</tr>
				
				<tr class="pic-box" id="uploadFileTr" style="display:none;">
					<td class="pn-flabel"><!-- 上传文件 --><s:text name="enterprise.collection.upload.file" /></td>
					<td>
						<input type="file" id="uploadFile" name="upload" value=""
							onchange="doFileBeforeSubmit();" size="30" disabled="disabled"/>
					</td>
				</tr>			
		
			<tr>
				<td class="pn-flabel"><font color="red">*</font>
					<!-- 文档标题 --><s:text name="enterprise.collection.title" />
				</td><td><input type="text" name="enterpriseNews.title" size="100" class="{required:true,maxlength:200}"/></td>
			</tr>
			<tr class="notview"<security:authorize ifAnyGranted="COLLECTION_NEWS_LINK"> style="display:table-row;"</security:authorize>>
				<td class="pn-flabel">
					<!-- 外部链接 --><s:text name="enterprise.collection.outside.link" />
				</td><td><input type="text" name="enterpriseNews.otherUrl" class="{maxlength:100,url:true}" size="100"/></td>
			</tr>
			<tr class="notview"<security:authorize ifAnyGranted="COLLECTION_NEWS_KEYWORD"> style="display:table-row;"</security:authorize>>
				<td class="pn-flabel" >
					<!-- 关键字 --><s:text name="enterprise.collection.keyword" />/首页轮播图背景颜色
				</td><td><input type="text" name="enterpriseNews.priKey"  class="{maxlength:500}" size="100"/></td>
			</tr>
			<tr class="notview"<security:authorize ifAnyGranted="COLLECTION_NEWS_SHORTMETA"> style="display:table-row;"</security:authorize>>
				<td class="pn-flabel">
					<!-- 简短描述 --><s:text name="enterprise.collection.short.description" />
				</td>
				<td>
					<textarea rows="10" cols="77" name="enterpriseNews.shortMeta"  class="{maxlength:500}"></textarea>
				</td>
			</tr>
			<tr class="notview"<security:authorize ifAnyGranted="COLLECTION_NEWS_AUTHOR"> style="display:table-row;"</security:authorize>>
				<td class="pn-flabel">
					<!-- 文档作者 --><s:text name="common.word.author" />
				</td><td><input type="text" name="enterpriseNews.autor" size="100"  class="{maxlength:20}"/></td>
			</tr>
			<tr class="notview"<security:authorize ifAnyGranted="COLLECTION_NEWS_SOURCE"> style="display:table-row;"</security:authorize>>
				<td class="pn-flabel">
					<!-- 来源 --><s:text name="enterprise.collection.reference" />
				</td><td><input type="text" name="enterpriseNews.source" size="100"  class="{maxlength:50}"/></td>
			</tr>
			
			<tr class="notview"<security:authorize ifAnyGranted="COLLECTION_NEWS_SPECIAL"> style="display:table-row;"</security:authorize>>
				<td class="pn-flabel"><!-- 特殊设置--><s:text name="enterprise.collection.special.setting" /></td>
				<td>
					<div class="notview" style="float:left;<security:authorize ifAnyGranted="COLLECTION_NEWS_ISTOP">display:block;</security:authorize>">
						<!-- 是否为置顶文档:--><s:text name="enterprise.collection.is.top" />
						<select name="enterpriseNews.isTop">
							<option value="0"><!-- 否 --><s:text name="common.word.no" /></option>
							<option value="1"><!-- 是 --><s:text name="common.word.yes" /></option>
						</select>
					</div>
					<div class="notview" style="float:left;margin-left:10px;<security:authorize ifAnyGranted="COLLECTION_NEWS_ISCOMMEND">display:block;</security:authorize>">
						<!-- 是否为推荐文档:--><s:text name="enterprise.collection.is.recommend" />
						<select name="enterpriseNews.isCommend">
							<option value="0"><!-- 否 --><s:text name="common.word.no" /></option>
							<option value="1"><!-- 是 --><s:text name="common.word.yes" /></option>
						</select>
					</div>
					<div class="notview" style="float:left;margin-left:10px;<security:authorize ifAnyGranted="COLLECTION_NEWS_ISONLYCONTENT">display:block;</security:authorize>">
						<!-- 是否为单独内容样式:--><s:text name="enterprise.collection.is.only.content" />
						<select name="enterpriseNews.isOnlyContent">
							<option value="0"><!-- 否 --><s:text name="common.word.no" /></option>
							<option value="1"><!-- 是 --><s:text name="common.word.yes" /></option>
						</select>
					</div>
					<div class="notview" style="float:left;margin-left:10px;<security:authorize ifAnyGranted="COLLECTION_NEWS_ISSHOWONINDEX">display:block;</security:authorize>">	
						<!-- 是否为首页滚动显示:--><s:text name="enterprise.collection.is.showed.onindex" />
						<select name="enterpriseNews.isShowedOnIndex">
							<option value="1"><!-- 是 --><s:text name="common.word.yes" /></option>
							<option value="0"><!-- 否 --><s:text name="common.word.no" /></option>
						</select>
					</div>
				</td>
			</tr>

			<tr style="display:none;">
				<td class="pn-flabel">
					<!-- 点击次数--><s:text name="enterprise.collection.click.times" />
				</td><td><input type="text" name="enterpriseNews.clickTimes" value="0"  size="100"/></td>
			</tr>
			<tr style="display:none;">
				<td class="pn-flabel">
					<!-- 显示时间--><s:text name="common.word.time.show" />
				</td>
				<td>
					<input type="text" name="enterpriseNews.initTime" onchange="checkDate()" id="initTime" readonly/>
					<input id="starttimeBt" type="button" class="default_button" value='<s:text name="enterprise.collection.time.select" />'/>
					<script type="text/javascript">
						Calendar.setup({
							inputField     : "initTime",
							button	  	   : "starttimeBt",
							/*ifFormat       :    "%Y-%m-%d %H:%M",							
					        showsTime      :    true,
					        timeFormat     :    "24"*/
							ifFormat       :    "%Y-%m-%d"
						});
					</script>
				<!-- 至--><s:text name="enterprise.collection.time.to" />
					<input type="text" name="enterpriseNews.endTime" onchange="checkDate()" id="endTime" readonly/>
					<input id="endtimeBt" type="button" class="default_button" value='<s:text name="enterprise.collection.time.select" />'/>
					<script type="text/javascript">
						Calendar.setup({
							inputField     : "endTime",
							button	  	   : "endtimeBt",
							/*ifFormat       :    "%Y-%m-%d %H:%M",							
					        showsTime      :    true,
					        timeFormat     :    "24"*/
							ifFormat       :    "%Y-%m-%d"
						});
					</script>
				</td>
			</tr>
				
			<tr>
				<td class="pn-flabel"><!-- 内容--><s:text name="enterprise.collection.contents" /></td>
				<td>
					<FCK:editor instanceName="contents" width="870px" height="400px">
						<jsp:attribute name="value">
				    	</jsp:attribute>
					</FCK:editor>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:center;height:34px;">
					<input style="height:34px;" type="submit" value='<s:text name="common.word.submit" />'/>
				</td>
			</tr>
	 	</table>
	 	<s:token></s:token>
	</form>
	</body>
</html>

