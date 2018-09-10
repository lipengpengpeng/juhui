<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<script src="${jsPath}/wx/o2o-shop/lrz.all.bundle.js"></script>
<body>
    <div class="container">
        <form class="assess-form" id="assess-form" action="${ctx}/wx/comment/newProCommet" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="memberId" value="${resultVo.result.memberId.id}"></input>
            <input type="hidden" name="proId" value="${proId}"></input>
            <input type="hidden" name="orderSn" value="${resultVo.result.orderSn}"></input>
            <input type="hidden" name="comScore" id="comScore" value=""></input>
            <input type="hidden" name="content" id="content" value=""></input>
            <input type="hidden" name="fileContent" id="fileContent" value=""></input>
            <ul class="cont-list">
               <c:forEach var="li" items="${pros}" varStatus="status">
                <li>
                    <a href="javascript:void(0);" class="list-link clearfix">
                        <span class="list-text left">总分</span>
                        <span class="list-more left">
                            <i class="contMenu-icon icon-crown"></i>
                            <i class="contMenu-icon icon-crown"></i>
                            <i class="contMenu-icon icon-crown"></i>
                            <i class="contMenu-icon icon-crown"></i>
                            <i class="contMenu-icon icon-crown"></i>
                            <span class="crown-points">0.0分</span>
                            <input type="hidden"  class="hidden-point" name="score${status.count}"value="">
                        </span>    
                    </a>
                </li>
                <li class="assessment-li">
                    <a href="javascript:void(0);" class="list-link clearfix">
                        <div class="list-num">商品名称：${li.name}</div>
                        <div class="list-cont clearfix" style="position: relative;">
                            <img src="${ctx}/common/image/wx/o2o-shop/upload-pictures.png" class="instrut-img left" id="img1">
                            <textarea type="text" class="instruct-text left" placeholder="请填写您的评论..." name="con${status.count}"></textarea>
                             <input type="file" capture="camera" accept="image/*" name="uploadImg" class="upload-btn" onchange="uploadImage(this)">
                           
                             <input type="hidden" name="fileName" value="13121.png"></input> 
                        </div>  
                    </a>
                </li>      
               </c:forEach>
                        
            </ul>
            <div class="btn-box">
                <button type="submit" class="btn" id="submitForm">发布</button>
            </div>
            <div class="loading loadImg-box">
            <img src="images/load.gif" class="loadImg">
            <p class="loadImg-text">上传成功！</p>
        </div>
        <div class="loading-cover"></div>
        </form>
    </div>
    <script type="text/javascript">
    
    function uploadImage(file){
        if(file.files && file.files[0]){
        	$(".loadImg-box").find(".loadImg").attr("src",'${imagePath}/wx/o2o-shop/load.gif');
            $(".loadImg-box").find(".loadImg-text").text("图片上传中");
            $(".loadImg-box,.loading-cover").show();
            lrz(file.files[0], {
                width: 500,
                quality :0.8
            }).then(function (rst) {
                $.ajax({
                    url: "${ctx}/wx/comment/uploadComImg",
                    type: "POST",
                    data: {
                    	fileContent:rst.base64,
                    	fileName:"121312.png"
                    	},
                    dataType:"json",
                    success: function(json){
                        if(json.status==200){
                        	$(file).siblings('img').attr("src",rst.base64);
	                        $(".loadImg-box").find(".loadImg").attr("src",'${imagePath}/wx/o2o-shop/upload-ok.png');
	                        $(".loadImg-box").find(".loadImg-text").text("上传成功！");
	                        setTimeout(function(){$(".loadImg-box,.loading-cover").hide();},800);
                            var img=""
                            img+=json.result+",";                           
                            var imgs=$("#fileContent").val()+img;
                            $("#fileContent").val(imgs)
                        }else if(json.status==400){
                            alert("网络错误，请稍后重试")
                        }
                    },
                    error:function(XMLHttpRequest, textStatus, errorThrown){ //上传失败 
                       alert(textStatus);
                    }
                });
  
            })
            .catch(function (err) {
                alert(err);
            })
        }else{//用户取消，没有图片
            //console.log(file);
            var img = $(file).siblings("img");
            var imgFile=$("#fileContent").val()
            if(imgFile!=""&&imgFile!=null){
            	img.attr("src",imgFile.substring(0,imgFile.length-1));
            }else{
            	img.attr("src","${imagePath}/wx/o2o-shop/upload-pictures.png");
            }
            
        }
    }
    
        $(function() {     
        	document.title = "评论";
            $(".icon-crown").on("touchend",function(){
            	var that = $(this);
                var index = $(this).index();
                for (var i = 0; i < index+1; i++) {
                	that.parent().find(".icon-crown").eq(i).addClass("icon-crown-active").nextAll().removeClass("icon-crown-active")
                }
                that.siblings(".crown-points").html(index+1+".0分");
                that.siblings(".hidden-point").val(index+1);
            })
           /*  var proId="${proId}"
            var proIds=proId.split(",")
            alert(proIds.length) */
            $(".assess-form").validate({
                ignore:'',
                rules:{               	
                	score1:{
                        required:true,
                    },
                    score2:{
                        required:true,
                    },
                    score3:{
                        required:true,
                    },
                    score4:{
                        required:true,
                    },
                    score5:{
                        required:true,
                    },
                    score6:{
                        required:true,
                    },
                    score7:{
                        required:true,
                    },
                    con1:{
                        required:true,
                        maxlength:200
                    },
                    con2:{
                        required:true,
                        maxlength:200
                    },
                    con3:{
                        required:true,
                        maxlength:200
                    },
                    con4:{
                        required:true,
                        maxlength:200
                    },
                    con5:{
                        required:true,
                        maxlength:200
                    },
                    con6:{
                        required:true,
                        maxlength:200
                    },
                    con7:{
                        required:true,
                        maxlength:200
                    },
                },
                messages:{
                	score1:{
                        required:"总分不能为空",
                    },
                    score2:{
                        required:"总分不能为空",
                    },
                    score3:{
                        required:"总分不能为空",
                    },
                    score4:{
                        required:"总分不能为空",
                    },
                    score5:{
                        required:"总分不能为空",
                    },
                    score6:{
                        required:"总分不能为空",
                    },
                    score7:{
                        required:"总分不能为空",
                    },
                    con1:{
                        required:"评论不能为空",
                        maxlength:"评论内容不能超过200字符"
                    },
                    con2:{
                        required:"评论不能为空",
                        maxlength:"评论内容不能超过200字符"
                    },
                    con3:{
                        required:"评论不能为空",
                        maxlength:"评论内容不能超过200字符"
                    },
                    con4:{
                        required:"评论不能为空",
                        maxlength:"评论内容不能超过200字符"
                    },
                    con5:{
                        required:"评论不能为空",
                        maxlength:"评论内容不能超过200字符"
                    },
                    con6:{
                        required:"评论不能为空",
                        maxlength:"评论内容不能超过200字符"
                    },
                    con7:{
                        required:"评论不能为空",
                        maxlength:"评论内容不能超过200字符"
                    },
                },

                highlight: function (e) {
                    $(e).parents("li").addClass('has-error');
                },
                success: function (e) {
                    $(e).parents("li").removeClass('has-error');
                    $(e).remove();
                },
                errorElement:'div',
                errorPlacement:function(error,element) {  
                    error.addClass('info-error');
                    element.parents("ul").after(error);
                }
            })
        });
        
        $("#submitForm").click(function(){
        	var comScore=""
        	$(".hidden-point").each(function(){
        		 comScore+=$(this).val()+","
        	})
        	var comScores=comScore.substring(0,comScore.length-1)
        	$("#comScore").val(comScores)
        	var content="";
        	$(".instruct-text").each(function(){
        		content+=$(this).val()+"&";
        	})
        	var contents=content.substring(0,content.length-1)
        	$("#content").val(contents)
        	//$("#assess-form").submit();
        	
        })
    </script>
</body>
</html>