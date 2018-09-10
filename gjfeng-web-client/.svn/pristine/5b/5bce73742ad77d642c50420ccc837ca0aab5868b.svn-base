<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
    <div class="container">
        <form class="info-form" action="${ctx}/wx/member/update" enctype="multipart/form-data" method="post">
            <ul class="cont-list">
                <li class="head-li">
                    <a href="javascript:void(0);" class="list-link head-link clearfix">
                        <span class="list-text left">头像</span>
                        <span class="list-more right" id="image-box">
                        	<c:if test="${not empty resultVo.result.imgHeadUrl}">
                        		<img src="${resultVo.result.imgHeadUrl}" id="userhead">
                        	</c:if>
                        	<c:if test="${empty resultVo.result.imgHeadUrl}">
                        		<img src="${imagePath}/head.jpg" id="userhead">
                        	</c:if>
                            <input type="file" capture="camera" accept="image/*" name="headImage" onchange="previewImage(this,60,60)" class="head-input">
                            <i class="my-icon icon-more"></i>
                        </span>    
                    </a>
                </li>
                <li>
                	<c:if test="${resultVo.result.isReadName eq '1'}">
                    <a href="javascript:void(0)" class="list-link clearfix">
                        <span class="list-text left">身份实名</span>
                        <span class="list-more right">
                            <span class="real-name">${resultVo.result.name}</span>
                            <!-- <i class="my-icon icon-more"></i> -->
                        </span>    
                    </a>
                    </c:if>
                    <c:if test="${resultVo.result.isReadName eq '0'}">
                    <a href="${ctx}/wx/member/toRealName" class="list-link clearfix">
                        <span class="list-text left">身份实名认证</span>
                        <span class="list-more right">
                            <span class="real-name">
                            	<c:if test="${resultVo.result.realNameState eq '0'}">未认证</c:if>
                            	<c:if test="${resultVo.result.realNameState eq '1'}">审核中</c:if>
                            	<c:if test="${resultVo.result.realNameState eq '3'}">审核失败</c:if>
                            </span>
                            <i class="my-icon icon-more"></i>
                        </span>    
                    </a>
                    </c:if>
                </li>
                <li>
                    <a href="javascript:void(0);" class="list-link clearfix">
                        <span class="list-text left">昵称</span>
                        <span class="list-more right">
                            <input type="text" name="nickName" id="nickName" class="uername" value="${resultVo.result.nickName}" placeholder="请输入昵称">
                        </span>    
                    </a>
                </li>
                <li class="introduct-li">
                    <a href="javascript:void(0);" class="list-link introduct-link clearfix">
                        <span class="list-text left">简介</span>
                        <span class="list-more introduct-box right">
                            <textarea name="remark" class="user-introduct" placeholder="请输入简介">${resultVo.result.remark}</textarea>
                        </span>
                    </a>
                </li>
            </ul>
            <div class="btn-box">
                <button type="submit" class="btn">保存</button>
            </div>
        </form>
    </div>
    <script type="text/javascript">
        $(function() {      
        	document.title = "会员信息";
            $(".info-form").validate({
                rules:{
                    nickName:{
                        required:true,
                        rangelength:[3,25]
                    },
                    remark:{
                        rangelength:[0,30],
                    }
                },
                messages:{
                    nickName:{
                        required:"昵称不能为空",
                        rangelength:"昵称3-25个字符"
                    },
                    remark:{
                        rangelength:"简介限制在30个字符",
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