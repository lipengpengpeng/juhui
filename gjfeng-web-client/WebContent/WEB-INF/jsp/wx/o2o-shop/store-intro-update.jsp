<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
    <div class="container">
        <form action="#" class="editShop-form">
            <div class="shopShow-input">
                <c:if test="${not empty resultVo.result.storeBanner}">
	        		<img src="${resultVo.result.storeBanner}" class="shopShowImg">
	        	</c:if>
	        	<%-- <c:if test="${empty resultVo.result.storeBanner}">
	        		<img src="${imagePath}/business.png" class="shopShowImg">
	        	</c:if> --%>
                <!-- <input type="file" capture="camera" accept="image/*" name="shopShow" onchange="previewImage(this,700,150)" class="uploadShop-btn"> -->
            </div>
            <textarea class="shop-textarea" name="description" placeholder="请输入店铺简介">${resultVo.result.storeDescription}</textarea>
            <div class="shop-btn">
                <button type="submit" class="edit-btn">提交简介</button>
            </div>
        </form>
    </div>
    <script type="text/javascript">
    	document.title = "店铺简介";
        $(function() {      
            $(".editShop-form").validate({
                rules:{
                	description:{
                        required:true,
                        rangelength:[1,200],
                    }
                },
                messages:{
                	description:{
                        required:"简介不能为空",
                        rangelength:"简介1-200个字符",
                    }
                },
                onfocusout:false,
                onkeyup:false,
                focusInvalid:false,
                showErrors: function(errorMap, errorList) {
                    var msg = "";  
                    $.each(errorList, function(i,v){  
                      msg = (v.message);  
                      return false; 
                    });  
                    if(msg!=""){
                        layer.open({
                            content: msg,
                            skin: 'msg',
                            style:'bottom:0;',
                            time: 1 //2秒后自动关闭
                        }); 
                    } 
                },
                submitHandler:function(form){
                	$.ajax({
                		url:"${ctx}/wx/store/updateIntro",
                		method:"POST",
                		data: $(form).serialize(),
                		success:function(data){
                			if(data.code==200){
                				layer.open({
                                    content: '编辑成功',
                                    btn: ['确定'],
                                    yes: function(index){//点击去认证页面，index为该特定层的索引
                                        location.href='${ctx}/wx/store/intro';
                                        layer.close(index);
                                    },
                                   
                                });  
                			}else{
                				layer.open({
                                    content: data.msg,
                                    btn: ['确定'],
                                    yes: function(index){//点击去认证页面，index为该特定层的索引                                     
                                        layer.close(index);
                                    },
                                   
                                });  
                			}
                			
                		}
                	})
                  	//ajaxAddOrEdit(form,"wx/store/updateIntro");
                } 
            })
        });
    </script>
</body>
</html>