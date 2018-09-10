<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="${jsPath}/wx/o2o-shop/lrz.all.bundle.js"></script>
<body>
    <div class="container">
       <!--  <form class="buy-bonus-form"> -->
            <div class="bonus-wrap">
                <div class="shouxin-now">现有授信额度:${shouXinMenoy}</div>
                <div class="bonus-item clearfix">
                    <label class="bonus-label left">充值额度：</label>
                    <input type="number" name="" placeholder="请输入您要充值的授信额度" class="bonus-input right" id="bonus-num">
                </div>
            </div>
            <div class="cont-list license-item">
                    <div class="license-title">支付凭证</div>
                    <p class="license-tip">图片大小请控制在1M之内，请确保图片清晰</p>
                    <div class="licenseImg-box">
                        <img src="" class="license-img hidden">
                        <input type="file"  accept="image/*" name="file" onchange="uploadImage(this)" class="licenseImg-btn" id="licenseImg">
                        <i class="licenseBtn-cover">+</i>
                        <input type="hidden" name="storefileName" id="storefileName">
                    </div>
            </div>
            <div class="bonus-pay">
                <div class="bonusPay-top clearfix">
                    <label class="bonusPay-label left">应付金额</label>
                    <div class="bonusPay-money right" id="nicePay">0</div>
                </div>
                <input type="hidden" name="type" id="type" value="0"/>               
            </div>
            <div class="btn-box">
                <button type="button" class="btn" id="saveToPay">确认购买</button>
            </div>
            <div class="loading-middle hidden">
              <img src="${imagePath}/wx/o2o-shop/load.gif" class="loadImg">
           </div>
           <div style="margin-top: -48px;">
               <div class="license-title" style="margin-left: 13px;">使用说明：<p style="color:red;margin-left: 31px;
    margin-top: 12px;">(提交后24小时内处理)</p></div>
               <p class="license-tip" style="margin-left: 44px;line-height: 0;">请选择转账方式：
                    <input type="radio" class="payClass" name="radio" checked="checked" value="1" style="box-shadow:none;margin-left: 10px" id="pay1"> <label for="pay1" style="margin-left: 5px;">支付宝</label>  
                    <input type="radio" class="payClass" name="radio" value="2" style="box-shadow:none;margin-left: 10px" id="pay2"><label for="pay2" style="margin-left: 5px;">微信</label>
                    <input type="radio" class="payClass" name="radio" value="3" style="box-shadow:none;margin-left: 10px" id="pay3"><label for="pay2" style="margin-left: 5px;">其他</label>
                </p>
               <img id="payImg" src="${imagePath}/wx/o2o-shop/pay_1.png" class="license-img" style=" margin-left: 99px; margin-top: 20px;width: 15rem;height: 15rem;">
               <p id="payFont" style="margin-left: 141px;margin-top: 10px;">支付宝扫码支付</p>
           </div>
        <!-- </form> -->
    </div>
    <script type="text/javascript">
    
        $(".payClass").each(function(){
        	$(this).click(function(){
        		if($(this).val()==1){
        			var img="pay_"+$(this).val()+".png";
        			$("#payImg").attr("src","${imagePath}/wx/o2o-shop/"+img);
        			$("#payFont").html("支付宝扫码支付")
        		}
        		if($(this).val()==2){
        			var img="pay_"+$(this).val()+".png";
        			$("#payImg").attr("src","${imagePath}/wx/o2o-shop/"+img);
        			$("#payFont").html("微信扫码支付")
        		}
        		if($(this).val()==3){
        			var img="pay_"+$(this).val()+".png";
        			$("#payImg").attr("src","${imagePath}/wx/o2o-shop/"+img);
        			$("#payFont").html("其他")
        		}
        		
        	})
        })
    
        $(function(){
        	document.title = "授信额度";
            $(".icon-circle").on("click",function(){
                var that = $(this);
                $(".icon-circle").removeClass('icon-circle-active');
                that.addClass("icon-circle-active");
            });
            
            $("#bonus-num").blur(function(){
            	$("#nicePay").html($("#bonus-num").val()*0.12)
            })

            $("#saveToPay").on("click",function(){
                var num = $(".icon-circle-active").parents("li").attr("data-id");
                var bonusNum = $("#bonus-num").val();//分权数值，字符串类型
                var intBonus = parseInt(bonusNum);//转整型
                var reg = /^(([1-9]\d*)|([0-9]))$/;
                var fileImg=$("#storefileName").val();
                if(bonusNum == "" || bonusNum == undefined){
                    layer.open({
                        content:'请填写授信额度',
                        btn:'我知道了'
                    })
                }else{
                    if(!reg.test(bonusNum) || intBonus===0){
                        layer.open({
                            content:'授信额度为非负非零整数',
                            btn:'我知道了'
                        })
                    }else if(fileImg==""||fileImg== undefined){
                          layer.open({
                              content:'请上传支付凭证',
                              btn:'我知道了'
                          })
                        
                    }else{
                    	addShouXin("6")
                    }
                    
                }
                
            })
        })
        
        function addShouXin(type){
        	layer.open({
                content: '是否确认充值',
                btn: ['确定', '取消'],
                yes: function(index){//点击去认证页面，index为该特定层的索引
                	layer.close(index);
                	$(".loading-middle").removeClass("hidden");
                    $(".btn").attr("disabled","disabled");
                    $.ajax({
                    	url:"${ctx}/wx/member/addShouXin",
                    	method:"POST",
                    	data:{
                    		type:type,
                    		tradeMoney:$("#bonus-num").val(),
                    		shouType:"1",
                    		fileImage:$("#storefileName").val(),
                    	},
                    	success:function(data){
                    		if(data.code==200){
                				window.location.href="${ctx}/wx/member/myWallet";
        	            	}else{ 
        	            		layer.open({
                                    content:data.msg,
                                    btn:'我知道了'
                                })
        	            	 } 
                    	}
                    })                           	
                },
                no:function(index){
                    layer.close(index);
                }
            });	        
        	
        }
        
        function uploadImage(file){
       	 $(".loadImg-box").find(".loadImg").attr("src",'${imagePath}/wx/o2o-shop/load.gif');
            $(".loadImg-box").find(".loadImg-text").text("图片上传中");
            $(".loadImg-box,.loading-cover").show();
           if(file.files && file.files[0]){
               lrz(file.files[0], {
                   width: 1024,
                   quality :0.8
               }).then(function (rst) {
                  
                   $.ajax({
                       url: "${ctx}/wx/store/imageUploadBStore",
                       type: "POST",
                       data: {
                       	fileContent:rst.base64,
                       	fileName:"12312.png"
                       	},
                       dataType:"json",
                       success: function(json){
                           if(json.status==200){
                               $(file).siblings('img').attr("src",rst.base64);
                               $(file).siblings('.license-img').removeClass("hidden");
                               $("#storefileName").val(json.result);
                               $(".loadImg-box").find(".loadImg").attr("src",'${imagePath}/wx/o2o-shop/upload-ok.png');
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
           }
       }
        
    </script>
</body>
</html>