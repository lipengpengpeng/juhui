<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<script src="${jsPath}/wx/o2o-shop/lrz.all.bundle.js"></script>

<body>
    <div class="container" style="text-align: center;">
        <form class="id-form"  action="${ctx}/wx/member/realNameByAly" method="POST" id="id-form"  >
          
            <div class="id-tip clearfix">
                <span class="id-label left">身份信息</span>
                <span class="tip-text left">*请确保身份证信息真实有效，否则无法通过审核</span>
            </div>
            <ul class="cont-list id-list">
                <li>
                    <div  class="list-link clearfix">
                        <span class="list-text left">真实姓名</span>
                        <span class="list-more right">
                            <input type="text" name="idCardName" id="username" class="uername" value="${resultVo1.name}">
                        </span>    
                    </div>
                </li>
                <li>
                    <div class="list-link clearfix">
                        <span class="list-text left">身份证号码</span>
                        <span class="list-more right">
                            <input type="text" name="idCardNo" id="idCard" class="uername" value="${resultVo1.idCard}">
                        </span>    
                    </div>
                </li>
            </ul>

               <button type="button" class="btn" id="btn">提交</button> 
            </div>
        </form>
        <div class="loading-middle hidden">
          <img src="${imagePath}/wx/o2o-shop/load.gif" class="loadImg">
        </div>
        <div class="loading loadImg-box">
            <img src="images/load.gif" class="loadImg">
            <p class="loadImg-text">上传成功！</p>
        </div>
        <div class="loading-cover"></div>
    </div>
    <script type="text/javascript">
    
    function uploadImage(file){
        if(file.files && file.files[0]){
            lrz(file.files[0], {
                width: 1024,
                quality :0.8
            }).then(function (rst) {
                $(".loadImg-box").find(".loadImg").attr("src",'images/load.gif');
                $(".loadImg-box").find(".loadImg-text").text("图片上传中");
                $(".loadImg-box,.loading-cover").show();
                $.ajax({
                    url: "${ctx}/wx/base/imageUploadByBase64",
                    type: "POST",
                    data: {
                    	fileContent:rst.base64,
                    	fileName:"12312.png"
                    	},
                    dataType:"json",
                    success: function(json){
                        if(json==200){
                            $(file).siblings('img').attr("src",rst.base64);
                            $(".loadImg-box").find(".loadImg").attr("src",'images/upload-ok.png');
                            $(".loadImg-box").find(".loadImg-text").text("上传成功！");
                            $(".loadImg-box,.loading-cover").hide();
                        }else {
                            alert("报错")
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
            if(img.hasClass("idCard-front")){//身份证前
                img.attr("src","images/ID-card_front.png");
            }else if(img.hasClass("idCard-back")){//身份证后
                img.attr("src","images/ID-card_back.png");
            }else if(img.hasClass("idCard-hold")){//身份证手持
                img.attr("src","images/hold-ID-card.png");
            }
        }
    }
    

    
    document.title = "实名认证";
        

       /*  jQuery.validator.addMethod("character", function(value,element){
            var tel = /^[\u4e00-\u9fa5]+$/;
            return this .optional(element) || (tel.test(value));
        }, "请输入正确的真实姓名" ); */
        

        $(function() {      
            $(".id-form").validate({
                rules:{
                	idCardName:{
                        required:true,
                    },
                    idCardNo:{
                        required:true,
                    },
                   
                    /* idCardImg1:{
                        required:true,
                        creditcard:true
                    },  */
                },
                messages:{
                	idCardName:{
                        required:"真实姓名不能为空",
                    },
                    idCardNo:{
                        required:"身份证号码不能为空",                       
                    },                  
                    /* idCardImg1:{
                        required:"银行账号不能为空",
                        creditcard:"账户格式错误"
                    },  */
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
        
       var status="${resultVo.result.status}"
        $("#btn").click(function(){
        	if($("#id-form").valid()){ 
        		if(status==0){
        			$.ajax({
            			url:"${ctx}/wx/member/findMemberInfoByIdCard",
            			method:"GET",
            			data:{
            				idCard:$("#idCard").val(),
            			},
            			success:function(data){
            				if(data.result){
            					layer.open({
                                    content:"该身份证号码已经绑定，请选择其他身份证号码进行绑定",
                                    btn:'我知道了'
                                })
            				}else{
            					$("#id-form").submit();           	
            	            	$(".loading-middle").removeClass("hidden");
            	                $(".btn").attr("disabled","disabled"); 
            				}
            			}
            		})
        		}else{
        			$("#id-form").submit();           	
	            	$(".loading-middle").removeClass("hidden");
	                $(".btn").attr("disabled","disabled"); 
        		}        		     		        	                        	
        	}	
        	
        })
        
 
               
        
    </script>
</body>
</html>