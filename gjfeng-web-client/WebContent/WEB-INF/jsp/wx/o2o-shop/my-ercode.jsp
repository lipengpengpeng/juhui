<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<script type="text/javascript" src="${plugInPath}/qrcode demo/qrcode.min.js"></script>
<!--  <body>
    <div class="container">
        <div class="code-box">
             <img src="${resultVo.result.imgHeadUrl}" class="code-head">
             <div id="qrcode" class="qrcode"><img src="${resultVo.result.imgQrUrl}"></div> 
             <%-- <div id="qrcode" class="qrcode"><img src="${resultVo.result.imgAppQrUrl}"></div> --%>
            <p class="code-text">免费领券省钱，推荐分享赚钱！</p>
        </div>
    </div>
</body>-->
<script type="text/javascript">
document.title = "我的二维码";
window.location.href="${ctx}/appNeed/appDownLoadGuide?superId="+${resultVo.result.id}
//二维码
/* var qrcode = new QRCode(document.getElementById("qrcode"), {
	  text: 'weixin://wxpay/bizpayurl?pr=CZ5RPxu',
	  width: 256,
	  height: 256,
	  colorDark : '#5A3117',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H,
 });

   qrcode.makeCode("${resultVo.result.imgQrUrl}"); */
</script>
</html>