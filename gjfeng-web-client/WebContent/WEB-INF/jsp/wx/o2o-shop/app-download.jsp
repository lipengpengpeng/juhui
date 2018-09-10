<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<body>
    <div class="container">
       <!-- <iframe src="http://a.app.qq.com/o/simple.jsp?pkgname=cn.gjfeng_zhanjiang" width="100%" height="800"> 
 
       </iframe>  -->
       <ul class="agentList-ul">
            <li class="agentList-li">
                <div class="agentList-box cleafix">
                    <div class="agentList-item left file-name-item">
                        <img src="${imagePath}/wx/o2o-shop/download-head.png" class="agentList-icon">
                        <a href="http://jfh.jfeimao.com/gjfeng-web-client/upload/file/巨惠云商O2O.apk" class="download-link">最新app下载</a>
                    </div>
                    <div class="agentList-item right file-arrow-btn">
                        <img src="${imagePath}/wx/o2o-shop/arrow-btn.png" class="file-arrow">
                    </div>
                </div>
                <div class="agentList-box agentList-middle cleafix" style="display:none;">
                    <div class="file-row">
                        <label class="file-label">下载地址：</label>
                        <span class="file-text">http://jfh.jfeimao.com/gjfeng-web-client/upload/file/巨惠云商O2O.apk</span>
                    </div>
                </div>
            </li>
        </ul>
      
    </div>
    <script type="text/javascript">
        $(function(){
        	document.title = "app下载"; 
        	//window.location.href="http://a.app.qq.com/o/simple.jsp?pkgname=cn.gjfeng_zhanjiang";
        	$(function(){
        	document.title = "下载";
            $(".file-arrow-btn").on("click",function(){
                var that = $(this);
                var state = that.parents(".agentList-box").siblings().css("display");
                if(state == "none"){
                    that.css('-webkit-transform', 'rotate(180deg)');
                    that.parents(".agentList-box").siblings().slideDown();
                }else{
                    that.css('-webkit-transform', 'rotate(0deg)');
                    that.parents(".agentList-box").siblings().slideUp(); 
                }
            })
        })
        })
    </script>
</body>
</html>