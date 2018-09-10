<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<script src="${jsPath}/wx/o2o-shop/lrz.all.bundle.js"></script>
<body>
    <div class="container">
        <form action="${ctx}/wx/store/add" class="openShop-form" method="post" enctype="multipart/form-data">
           <div class="apply-wrap-1">
            <div class="shopInfo-title">店铺信息</div>
            <ul class="cont-list shopInfo-list">
                <li class="clearfix">
                    <label class="shopInfo-label left">店铺名称<span class="grey-tip">(不可更改)</span></label>
                    <span class="shopInfo-right right">
                        <input type="text" name="storeName" class="shopInfo-input" placeholder="请输入店铺名称">
                    </span>
                </li>
                <li class="clearfix">
                    <label class="shopInfo-label left">联系人</label>
                    <span class="shopInfo-right right">
                        <input type="text" name="sellerName" class="shopInfo-input" placeholder="请输入店铺联系人">
                    </span>
                </li>
                <li class="clearfix">
                    <label class="shopInfo-label left">联系电话</label>
                    <span class="shopInfo-right right">
                        <input type="text" name="sellerMobile" class="shopInfo-input" placeholder="请输入您的手机号码">
                    </span>
                </li>
               <!--  <li class="clearfix">
                    <label class="shopInfo-label left">电子邮箱</label>
                    <span class="shopInfo-right right">
                        <input type="text" name="sellerEmail" class="shopInfo-input" placeholder="请输入您的邮箱">
                    </span>
                </li> -->
            </ul>
            <div class="shopInfo-title">店铺地址</div>
            <ul class="cont-list shopInfo-list">
                <li class="clearfix">
                    <label class="shopInfo-label left">省份城市</label>
                    <span class="shopInfo-right right">
                        <input type="text" name="shopCity" class="shopInfo-input choose-city" placeholder="请选择" readonly="readonly" id="cityName">
                        <input type="hidden" name="storeCitys" id="storeCitys">
                        <i class="my-icon icon-more"></i>
                    </span>
                </li>
                <li class="clearfix">
                    <label class="shopInfo-label left">详细地址</label>
                    <span class="shopInfo-right right">
                        <input type="text" name="addressDetail" class="shopInfo-input" placeholder="街道、楼牌号码等">
                    </span>
                </li>
            </ul>
            <!-- <div class="shopInfo-title">结算账号</div>
            <ul class="cont-list shopInfo-list">
                <li class="clearfix">
                    <label class="shopInfo-label left">银行开户名</label>
                    <span class="shopInfo-right right">
                        <input type="text" name="bankAccountName" class="shopInfo-input" placeholder="请填写银行开户名">
                    </span>
                </li>
                <li class="clearfix">
                    <label class="shopInfo-label left">银行账号</label>
                    <span class="shopInfo-right right">
                        <input type="text" name="bankAccountNumber" class="shopInfo-input" placeholder="填写银行账号">
                    </span>
                </li>
            </ul> -->
            <div class="Id-approve clearfix">
                    <label class="shopInfo-label left">身份认证</label>
                    <span class="shopInfo-right right" >
                    	 <c:if test="${sessionScope.gjfMemberInfo.isReadName eq '0'}">
	                    	 <a href="${ctx}/wx/member/toRealName" class="approve-link" id="identity">
	                            <i class="approve-state approve-not" data-value="0">未认证</i>
	                            <i class="my-icon icon-more"></i>
	                        </a>
                    	 </c:if>   
                    	<c:if test="${sessionScope.gjfMemberInfo.isReadName eq '1'}">
	                    	 <a href="javascript:void(0)" class="approve-link" id="identity">
	                            <i class="approve-state approve-finish" data-value="1">已认证</i>
	                            <i class="my-icon icon-more"></i>
	                        </a>
                    	 </c:if>  
                    </span>
            </div>
            <div class="shopInfo-title">信息完善</div>
            <ul class="cont-list shopInfo-list">
                <li class="clearfix">
                    <label class="shopInfo-label left">营业执照信息（副本）</label>
                    <span class="shopInfo-right right">
                        <div class="approve-link" id="approve-1">
                            <i class="approve-state approve-not" data-value="0">未完善</i>
                            <!-- <i class="approve-state approve-finish" data-value="1">已完善</i> -->
                            <i class="my-icon icon-more"></i>
                        </div>
                    </span>
                </li>
            </ul>
             <div style="padding-bottom: 20px">
                   <input type="checkbox" name="memService" style="margin-left: 25px;box-shadow: none;margin-top: 20px;"><a href="${ctx}/wx/rule/storeService" style="margin-left:6px;font-size:13px">《凤凰云商o2o商家入驻协议》</a>
              </div>
            <div class="btn-box">
                <button type="submit" class="btn">提交申请</button>
            </div>
            <input type="hidden" name="storePro" value="0">
            <input type="hidden" name="storeType" value="0">
            </div>
            <div class="apply-wrap-2 hidden">
                <ul class="cont-list license-list">
                    <!-- <li class="clearfix">
                        <label class="shopInfo-label left">营业执照号</label>
                        <span class="shopInfo-right right">
                            <input type="text" name="businessLicenceNumber" class="shopInfo-input" placeholder="请填写营业执照号" id="licenseNum">
                        </span>
                    </li>
                    <li class="clearfix">
                        <label class="shopInfo-label left">营业执照所在地</label>
                        <span class="shopInfo-right right">
                            <input type="text" name="businessLicenceAddress" class="shopInfo-input choose-city" placeholder="请选择" id="licenseCity" readonly="readonly">
                            <input type="hidden" name="cityVAlue1" id="cityValue-1">
                            <i class="my-icon icon-more"></i>
                        </span>
                    </li> -->
                    <li class="clearfix" style="display:none">
                        <label class="shopInfo-label left">营业执照有效期</label>
                        <span class="shopInfo-right right">
                            <input type="number" name="businessLicenceStart" class="license-startTime" placeholder="20160901" id="licenseStartTime" value="20160901">
                            <i class="license-middle">-</i>
                            <input type="number" name="businessLicenceEnd" class="license-endTime" placeholder="20700901" id="licenseEndTime" value="20700901">
                        </span>
                    </li> 
                </ul>
                <div class="cont-list license-item">
                    <div class="license-title">营业执照电子版</div>
                    <p class="license-tip">图片大小请控制在1M之内，请确保图片清晰，文字可辨并有清晰的红色公章。</p>
                    <div class="licenseImg-box">
                        <img src="" class="license-img hidden">
                        <input type="file" capture="camera" accept="image/*" name="file" onchange="uploadImage(this)" class="licenseImg-btn" id="licenseImg">
                        <i class="licenseBtn-cover">+</i>
                        <input type="hidden" name="storefileName" id="storefileName">
                    </div>
                </div>
                <!-- <div class="cont-list license-item">
                    <div class="license-title">经营范围(选填)</div>
                    <div class="license-textBox">
                        <textarea class="license-textarea"  name="businessSphere" placeholder="填写您的经营范围" id="licenseArea"></textarea>
                    </div>
                </div> -->
                <div class="btn-box">
                    <button type="button" class="btn" id="save-btn-1">保存</button>
                </div>
            </div>
        </form>
    </div>
    <div class="loading-middle hidden">
       <img src="${imagePath}/wx/o2o-shop/load.gif" class="loadImg">
   </div>
   <div class="loading loadImg-box">
            <img src="${imagePath}/wx/o2o-shop/load.gif" class="loadImg">
            <p class="loadImg-text">上传成功！</p>
        </div>
       <div class="loading-cover"></div>
    <script type="text/javascript" src="${plugInPath}/larea/LArea.min.js"></script>
    <script type="text/javascript" src="${plugInPath}/larea/LAreaData2.js"></script>
    <script type="text/javascript">
    
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
    
    

    function setErrorText(txt){
        var txt = txt || '';
            txt&& layer.open({
                content: txt,
                skin: 'msg',
                style:'bottom:0;',
                time: 1 //2秒后自动关闭
            });
    }

    function validateApprove1() {
        var v1 = $("#licenseNum").val(),
            v2 = $("#licenseCity").val(),
            v3 = $("#licenseStartTime").val(),
            v4 = $("#licenseEndTime").val(),
            v5 = $("#licenseImg").val(),
            v6 = $("#licenseArea").val();
        var licenseTime = /^\d{8}$/,//8位数字
            licenseArea = /^\S{0,400}$/;//400字以内的经营范围
         /*if(v1==""){
            setErrorText('请填写营业执照号')
            return false;
        }
        if(v2==""){
            setErrorText('请选择营业执照所在地')
            return false;
        }
         if(v3==""){
            setErrorText('请输入营业执照有效期')
            return false;
        }else if(v3!="" && !licenseTime.test(v3)){
            setErrorText('请输入正确的营业执照日期')
            return false;
        }
        if(v4==""){
            setErrorText('请输入营业执照有效期')
            return false;
        }else if(v4!="" && !licenseTime.test(v4)){
            setErrorText('请输入正确的营业执照日期')
            return false;
        } */
        if(v5==""){
            setErrorText('请上传营业执照电子版')
            return false;
        }
        if(v6!="" && !licenseArea.test(v6)){
            setErrorText('经营范围控制在400个字以内')
            return false;
        }
        return true;
    }
        $(function() {
        	document.title = "个人入驻";
        	
        	$("#approve-1").on("click",function(e){//显示营业执照窗口
                e.preventDefault();
                $(".apply-wrap-2").removeClass("hidden");
                $(".apply-wrap-1").addClass("hidden");
                $(document).scrollTop(0);
                $(".apply-wrap-2").removeClass("apply-wrap-right").addClass("apply-wrap-active");
                $(".apply-wrap-1").removeClass("apply-wrap-active").addClass("apply-wrap-left");
            });

            $("#save-btn-1").on("click",function(e){//营业执照保存
                e.preventDefault();
                if(validateApprove1()){
                    $("#approve-1").find(".approve-state").attr("data-value","1")
                    $("#approve-1").find(".approve-state").removeClass('approve-not').addClass("approve-finish");
                    $("#approve-1").find(".approve-state").html("已完善");
                    $(".apply-wrap-1").removeClass("hidden");
                    $(".apply-wrap-1").removeClass("apply-wrap-left").addClass("apply-wrap-active");
                    $(".apply-wrap-2").removeClass("apply-wrap-active").addClass("apply-wrap-right");
                    $(".apply-wrap-2").addClass("hidden");
                }
            });
        	
            var area = new LArea();
            var area2 = new LArea();
            area.init({
                'trigger': '#cityName',//触发选择控件的文本框，同时选择完毕后name属性输出到该位置
                'valueTo': '#storeCitys',//选择完毕后id属性输出到该位置
                'keys': {
                    id: 'value',
                    name: 'text'
                },//绑定数据源相关字段 id对应valueTo的value属性输出 name对应trigger的value属性输出
                'type': 2,
                'data': [provs_data, citys_data, dists_data]
            });
            area.value=[18,0,2];//控制初始位置，注意：该方法并不会影响到input的value，现在是广东省广州市越秀区
            
            area2.init({
                'trigger': '#licenseCity',
                'valueTo': '#cityValue-1',
                'keys': {
                    id: 'value',
                    name: 'text'
                },
                'type': 2,
                'data': [provs_data, citys_data, dists_data]
            });
            area2.value=[18,0,2];
            
            jQuery.validator.addMethod("isEgName", function(value,element) {
            	var length = value.length;
            	var Ename = /^([A-Za-z]|[\u4E00-\u9FA5])+$/;
            	
            	return this.optional(element) || (Ename.test(value));

            	}, "只能输入汉字或英文");
            
            jQuery.validator.addMethod("isMobile", function(value,element) {
            	var length = value.length;
            	var mobile = /^1[3|4|5|7|8][0-9]{9}$/;
            	
            	return this.optional(element) || (mobile.test(value));

            	}, "输入的手机号码不正确");

            $(".openShop-form").validate({
                rules:{
                    storeName:{
                        required:true,
                        rangelength:[3,25]
                    },
                    sellerName:{
                        required:true,
                        rangelength:[1,25],
                        isEgName:true
                    },
                    sellerMobile:{
                        required:true,
                        isMobile:true
                    } ,
                    sellerEmail:{
                        required:true,
                        email:true
                    },
                    shopCity:{
                        required:true
                    },
                    addressDetail:{
                        required:true,
                        rangelength:[1,100]
                    },
                    bankAccountName:{
                        required:true,
                        rangelength:[1,50],
                        isEgName:true
                    },
                    bankAccountNumber:{
                        required:true,
                        creditcard:true
                    },
                    memService:{
                    	required:true,
                    }
                },
                messages:{
                    storeName:{
                        required:"请输入店铺名称",
                        rangelength:"店铺名称3-25个字符"
                    },
                    sellerName:{
                        required:"请输入店铺联系人",
                        rangelength:"店铺联系人1-25个字符"
                    },
                    sellerMobile:{
                        required:"请输入您的手机号码",
                        isMobile:"手机号码格式不正确"
                    },
                   /*  sellerEmail:{
                        required:"请输入您的邮箱",
                        email:"邮箱格式错误"
                    }, */
                    shopCity:{
                        required:"请选择省份城市"
                    },
                    addressDetail:{
                        required:"请填写详细地址",
                        rangelength:"详细地址1-100个字符"
                    },
                    /* bankAccountName:{
                        required:"请填写银行开户名",
                        rangelength:"银行开户名1-50个字符"
                    },
                    bankAccountNumber:{
                        required:"填写银行账号",
                        creditcard:"账户格式错误"
                    }, */
                    memService:{
                    	required:"请选择服务器协议",
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
                	
                	//var num = $("#identity").find(".approve-state").attr("data-value");//身份认证
                	var num="1";
                    var num2 = $("#approve-1").find(".approve-state").attr("data-value");//营业执照
    
                    if(num == "1" && num2 == "1"){
                    	$(".loading-middle").removeClass("hidden");
                      	$(".btn").attr("disabled","disabled");
                        form.submit();
                    }else if(num == "0"){
                        layer.open({
                            content: '请先进行身份认证',
                            btn: ['去认证', '取消'],
                            yes: function(index){//点击去认证页面，index为该特定层的索引
                                location.href='${ctx}/wx/member/toRealName';
                                layer.close(index);
                            },
                            no:function(index){
                                layer.close(index);
                            }
                        });
                        return false;
                    }else if(num2 == "0"){
                        layer.open({
                            content: '请先完善营业执照信息',
                            btn: '我知道了'
                        });
                    }
                	
                	
                	
                  	/* var num = $(".approve-state").attr("data-value");
                    if(num == "1"){
                   	  $(".loading-middle").removeClass("hidden");
                   	  $(".btn").attr("disabled","disabled");
                	  form.submit();
                    }else{
                        layer.open({
                            content: '请先进行身份认证',
                            btn: ['去认证', '取消'],
                            yes: function(index){//点击去认证页面，index为该特定层的索引
                                location.href='${ctx}/wx/member/toRealName';
                                layer.close(index);
                            },
                            no:function(index){
                                layer.close(index);
                            }
                        });
                    } */
                }    
            })
        });
    </script>
</body>
</html>