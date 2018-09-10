<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
    <div class="container">
        <form class="change-form">
            <div class="form-item">
                <label for="" class="label-item"><i class="lock-icon"></i></label>
                <input type="password" name="pwd1" class="pwd-input" placeholder="请输入原密码">
            </div>
            <div class="form-item">
                <label for="" class="label-item"><i class="lock-icon"></i></label>
                <input type="password" name="pwd2" class="pwd-input" placeholder="请输入新密码" id="pwd-2">
            </div>
            <div class="form-item">
                <label for="" class="label-item"><i class="lock-icon"></i></label>
                <input type="password" name="pwd3" class="pwd-input" placeholder="请再次输入密码">
            </div>
            <div class="btn-box">
                <button type="submit" class="btn">完成</button>
            </div>
        </form>
    </div>
    <script type="text/javascript">
        $(function() {  
            $(".change-form").validate({
                rules:{
                    pwd1:{
                        required:true,
                    },
                    pwd2:{
                        required:true,
                        equalTo:'#pwd-2',
                    },
                    pwd3:{
                        required:true,
                    }
                },
                messages:{
                    pwd1:{
                        required:"请输入原密码",
                    },
                    pwd2:{
                        required:"请输入新密码",
                        equalTo:'两个密码不一致',
                    },
                    pwd3:{
                        required:"请再次输入密码",
                    }
                },
                highlight: function (e) {
                    $(e).parent().addClass('has-error');
                },
                success: function (e) {
                    $(e).parents(".form-item").removeClass('has-error');
                    $(e).remove();
                },
                errorElement:'div',
                errorPlacement:function(error,element) {  
                    error.insertAfter(element.parent());
                }
            })
        });
    </script>
</body>
</html>