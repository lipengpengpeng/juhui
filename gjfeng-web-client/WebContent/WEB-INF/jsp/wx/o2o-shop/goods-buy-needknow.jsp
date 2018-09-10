<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
    <div class="container">
        <form class="release-form" action="#">
                <textarea placeholder="编辑购买须知，让顾客更加放心购买" name="buyText" class="buy-text"></textarea>
            </div>
            <div class="fixed-btn-box">
                <button type="submit" class="fixed-btn">完成</button>
            </div>
        </form>
    </div>
    <script type="text/javascript">
        $(function(){
        	document.title = "购买须知";
            $(".buy-text").on("input",function(){//根据输入长度，改变输入框高度
                var height = $(this).outerHeight();
                var scrollheight = this.scrollHeight;
                if(scrollheight>height){
                    $(".buy-text").css('height',scrollheight+'px');  
                }
            })

            $(".release-form").validate({
                rules:{
                    buyText:{
                        required:true,
                        maxlength:500
                    }
                },
                messages:{
                    buyText:{
                        required:"购买须知不能为空",
                        maxlength:"输入的字符不能大于500"
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
                }
            })
        });
    </script>
</body>
</html>