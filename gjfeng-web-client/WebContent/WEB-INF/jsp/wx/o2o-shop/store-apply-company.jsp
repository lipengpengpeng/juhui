<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<script src="${jsPath}/wx/o2o-shop/lrz.all.bundle.js"></script>
<body>
    <div class="container">
        <form action="${ctx}/wx/store/add" class="openShop-form" method="post" enctype="multipart/form-data">
            <div class="apply-wrap-1">
                <div class="shopInfo-title">公司信息</div>
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
                            <input type="text" name="sellerName" class="shopInfo-input" placeholder="请输入企业联系人">
                        </span>
                    </li>
                    <li class="clearfix">
                        <label class="shopInfo-label left">联系电话</label>
                        <span class="shopInfo-right right">
                            <input type="text" name="sellerMobile" class="shopInfo-input" placeholder="请输入联系人的手机号码">
                        </span>
                    </li>
                    <!-- <li class="clearfix">
                        <label class="shopInfo-label left">电子邮箱</label>
                        <span class="shopInfo-right right">
                            <input type="text" name="sellerEmail" class="shopInfo-input" placeholder="请输入联系人的邮箱">
                        </span>
                    </li> 
                   <li class="clearfix">
                        <label class="shopInfo-label left">注册资金</label>
                        <span class="shopInfo-right right">
                            <input type="text" name="companyRegisteredCapital" class="shopInfo-input register-money" placeholder="请填写数字"><i class="money-unit">万元</i>
                        </span>
                    </li> -->
                </ul>
                <div class="shopInfo-title">公司地址</div>
                <ul class="cont-list shopInfo-list">
                    <li class="clearfix">
                        <label class="shopInfo-label left">省份城市</label>
                        <span class="shopInfo-right right">
                            <input type="text" name="companyValue" class="shopInfo-input choose-city" placeholder="请选择" readonly="readonly" id="cityName">
                            <input type="hidden" name="storeCitys" id="cityValue">
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
                   <!--  <li class="clearfix">
                        <label class="shopInfo-label left">财务资质信息</label>
                        <span class="shopInfo-right right">
                            <div class="approve-link" id="approve-2">
                                <i class="approve-state approve-not" data-value="0">未完善</i>
                                <i class="approve-state approve-finish" data-value="1">已完善</i>
                                <i class="my-icon icon-more"></i>
                            </div>
                        </span>
                    </li>
                    <li class="clearfix">
                        <label class="shopInfo-label left">组织机构代码</label>
                        <span class="shopInfo-right right">
                            <input type="text" name="organizationCode" class="shopInfo-input" placeholder="企业组织机构代码">
                        </span>
                    </li>
                    <li class="clearfix">
                        <label class="shopInfo-label left">税务登记</label>
                        <span class="shopInfo-right right">
                            <input type="text" name="taxRegistrationCertificate" class="shopInfo-input" placeholder="税务登记证号">
                        </span>
                    </li> -->
                </ul>
                
                <div style="padding-bottom: 20px">
                   <input type="checkbox" name="memService" style="margin-left: 25px;box-shadow: none;margin-top: 20px;"><a href="${ctx}/wx/rule/storeService" style="margin-left:6px;font-size:13px">《巨惠云商o2商家入驻协议》</a>
                </div>
                
                <div class="btn-box">
                    <button type="submit" class="btn">提交申请</button>
                </div>
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
                            <input type="hidden" name="licenseCity" id="cityValue-1">
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
                        <textarea class="license-textarea" name="businessSphere" placeholder="填写您的经营范围" id="licenseArea"></textarea>
                    </div>
                </div> -->
                <div class="btn-box">
                    <button type="button" class="btn" id="save-btn-1">保存</button>
                </div>
            </div>
            <div class="apply-wrap-3 hidden">
                <div class="shopInfo-title clearfix">
                    <span class="left">开户银行信息</span>
                    <span class="choose-btn right" id="be-account">
                        <i class="main-sprite icon-no"></i>
                        <i class="choose-text">设为结算账号</i>
                        <input type="hidden" name="isSettlementAccount" id="isSettlementAccount">
                    </span>
                </div>
                <ul class="cont-list shopInfo-list">
                    <li class="clearfix">
                        <label class="shopInfo-label left">银行开户名</label>
                        <span class="shopInfo-right right">
                            <input type="text" name="bankAccountName" class="shopInfo-input" placeholder="请输入银行开户名" id="bankName1">
                        </span>
                    </li>
                    <li class="clearfix">
                        <label class="shopInfo-label left">公司银行账号</label>
                        <span class="shopInfo-right right">
                            <input type="text" name="bankAccountNumber" class="shopInfo-input" placeholder="请输入公司银行账号" id="bankId1">
                        </span>
                    </li>
                    <li class="clearfix">
                        <label class="shopInfo-label left">开户银行支行名称</label>
                        <span class="shopInfo-right right">
                            <input type="text" name="bankName" class="shopInfo-input" placeholder="请输入开户银行支行名称" id="branchName1">
                        </span>
                    </li>
                    <li class="clearfix">
                        <label class="shopInfo-label left">开户银行所在地</label>
                        <span class="shopInfo-right right">
                            <input type="text" name="bankAddress" class="shopInfo-input choose-city" placeholder="请选择" id="bankAdress1" readonly="readonly">
                            <input type="hidden" name="bankValue1" id="bankValue1">
                            <i class="my-icon icon-more"></i>
                        </span>
                    </li>
                </ul>
                <div class="shopInfo-title">结算帐号信息</div>
                <ul class="cont-list shopInfo-list">
                    <li class="clearfix">
                        <label class="shopInfo-label left">银行开户名</label>
                        <span class="shopInfo-right right">
                            <input type="text" name="settlementBankAccountName" class="shopInfo-input" placeholder="请输入银行开户名" id="bankName2">
                        </span>
                    </li>
                    <li class="clearfix">
                        <label class="shopInfo-label left">公司银行账号</label>
                        <span class="shopInfo-right right">
                            <input type="text" name="settlementBankAccountNumber" class="shopInfo-input" placeholder="请输入公司银行账号" id="bankId2">
                        </span>
                    </li>
                    <li class="clearfix">
                        <label class="shopInfo-label left">开户银行支行名称</label>
                        <span class="shopInfo-right right">
                            <input type="text" name="settlementBankName" class="shopInfo-input" placeholder="请输入开户银行支行名称" id="branchName2">
                        </span>
                    </li>
                    <li class="clearfix">
                        <label class="shopInfo-label left">开户银行所在地</label>
                        <span class="shopInfo-right right">
                            <input type="text" name="settlementBankAddress" class="shopInfo-input choose-city" placeholder="请选择" id="bankAdress2" readonly="readonly">
                             <input type="hidden" name="cityValue2" id="bankValue2">
                            <i class="my-icon icon-more"></i>
                        </span>
                    </li>
                </ul>
                <input type="hidden" name="storePro" value="0">
                <input type="hidden" name="storeType" value="1">
                <div class="btn-box">
                    <button type="button" class="btn" id="save-btn-2">保存</button>
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
    
        $(function() { 
        	document.title = "企业入驻";
            $("#approve-1").on("click",function(e){
                e.preventDefault();
                $(".apply-wrap-2").removeClass("hidden");
                $(".apply-wrap-1").addClass("hidden");
                $(".apply-wrap-3").addClass("hidden");
                $(document).scrollTop(0);
                $(".apply-wrap-2").removeClass("apply-wrap-right").addClass("apply-wrap-active");
                $(".apply-wrap-1").removeClass("apply-wrap-active").addClass("apply-wrap-left");
            });

            $("#approve-2").on("click",function(e){
                e.preventDefault();
                $(".apply-wrap-2").addClass("hidden");
                $(".apply-wrap-1").addClass("hidden");
                $(".apply-wrap-3").removeClass("hidden");
                $(document).scrollTop(0);
                $(".apply-wrap-3").removeClass("apply-wrap-right").addClass("apply-wrap-active");
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
                    $(".apply-wrap-3").addClass("hidden");
                }
            });

            $("#save-btn-2").on("click",function(e){//财务资质信息保存
                e.preventDefault();
                if(validateApprove2()){
                    $("#approve-2").find(".approve-state").attr("data-value","1");
                    $("#approve-2").find(".approve-state").removeClass('approve-not').addClass("approve-finish");
                    $("#approve-2").find(".approve-state").html("已完善");
                    $(".apply-wrap-1").removeClass("hidden");
                    $(".apply-wrap-1").removeClass("apply-wrap-left").addClass("apply-wrap-active");
                    $(".apply-wrap-3").removeClass("apply-wrap-active").addClass("apply-wrap-right");
                    $(".apply-wrap-3").addClass("hidden");
                    $(".apply-wrap-2").addClass("hidden");
                }
            });

            var area1 = new LArea();
            var area2 = new LArea();
            var area3 = new LArea();
            var area4 = new LArea();

            area1.init({
                'trigger': '#cityName',//触发选择控件的文本框，同时选择完毕后name属性输出到该位置
                'valueTo': '#cityValue',//选择完毕后id属性输出到该位置
                'keys': {
                    id: 'value',
                    name: 'text'
                },//绑定数据源相关字段 id对应valueTo的value属性输出 name对应trigger的value属性输出
                'type': 2,
                'data': [provs_data, citys_data, dists_data]
            });
            area1.value=[18,0,2];//控制初始位置，注意：该方法并不会影响到input的value，现在是广东省广州市越秀区
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

            area3.init({
                'trigger': '#bankAdress1',
                'valueTo': '#bankValue1',
                'keys': {
                    id: 'value',
                    name: 'text'
                },
                'type': 2,
                'data': [provs_data, citys_data, dists_data]
            });
            area3.value=[18,0,2];

            area4.init({
                'trigger': '#bankAdress2',
                'valueTo': '#bankValue2',
                'keys': {
                    id: 'value',
                    name: 'text'
                },
                'type': 2,
                'data': [provs_data, citys_data, dists_data]
            });
            area4.value=[18,0,2];
            
            var flag = true;
            $("#be-account").on("touchend",function(){
                var that = $(this);
                if(flag){//选中，设为结算账号
                    that.find(".main-sprite").removeClass('icon-no').addClass('icon-yes');
                    var v1 = $("#bankName1").val(),
                        v2 = $("#bankId1").val(),
                        v3 = $("#branchName1").val(),
                        v4 = $("#bankAdress1").val(),
                        v5 = $("#bankValue1").val();
                        $("#bankName2").val(v1),
                        $("#bankId2").val(v2),
                        $("#branchName2").val(v3),
                        $("#bankAdress2").val(v4),
                        $("#bankValue2").val(v5);
                        $("#isSettlementAccount").val("1");
                    flag = false;
                }else{//不选中，不设为结算账号
                    that.find(".main-sprite").removeClass('icon-yes').addClass('icon-no');
                    $("#bankName2").val(""),
                    $("#bankId2").val(""),
                    $("#branchName2").val(""),
                    $("#bankAdress2").val(""),
                    $("#bankValue2").val("");
                    $("#isSettlementAccount").val("0");
                    flag = true;
                }
            });

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
                /* if(v1==""){
                    setErrorText('请填写营业执照号')
                    return false;
                }
                if(v2==""){
                    setErrorText('请选择营业执照所在地')
                    return false;
                } */
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
                }
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

            function validateApprove2(){
            	 var v1 = $("#bankName1").val(),
                 v2 = $("#bankId1").val(),
                 v3 = $("#branchName1").val(),
                 v4 = $("#bankAdress1").val(),
                 v5 = $("#bankName2").val(),
                 v6 = $("#bankId2").val(),
                 v7 = $("#branchName2").val(),
                 v8 = $("#bankAdress2").val();
           	 var regBankName = /^[\u4e00-\u9fa5]+$/,//银行卡名称
                regCredit = /^\d{19}$/;//银行卡卡号19位
             if(v1==""){
                 setErrorText('请输入银行开户名')
                 return false;
             }else if(v1!="" && !regBankName.test(v1)){
                 setErrorText('请输入正确的银行开户名')
                 return false;
             }
             if(v2==""){
                 setErrorText('请输入公司银行账号')
                 return false;
             }else if(v2!="" && !regCredit.test(v2)){
                 setErrorText('请输入正确的银行账号')
                 return false;
             }
             if(v3==""){
                 setErrorText('请输入开户银行支行名称')
                 return false;
             }else if(v3!="" && !regBankName.test(v3)){
                 setErrorText('请输入正确的开户银行支行名称')
                 return false;
             }
             if(v4==""){
                 setErrorText('请选择支行地址')
                 return false;
             }
             if(v5==""){
                 setErrorText('请输入银行开户名')
                 return false;
             }else if(v5!="" && !regBankName.test(v5)){
                 setErrorText('请输入正确的银行开户名')
                 return false;
             }
             if(v6==""){
                 setErrorText('请输入公司银行账号')
                 return false;
             }else if(v6!="" && !regCredit.test(v6)){
                 setErrorText('请输入正确的银行账号')
                 return false;
             }
             if(v7==""){
                 setErrorText('请输入开户银行支行名称')
                 return false;
             }else if(v7!="" && !regBankName.test(v7)){
                 setErrorText('请输入正确的开户银行支行名称')
                 return false;
             }
             if(v8==""){
                 setErrorText('请选择支行地址')
                 return false;
             }
             return true;
         }

            jQuery.validator.addMethod("character", function(value,element){
                var tel = /^[\u4e00-\u9fa5]+$/;
                  return this .optional(element) || (tel.test(value));
          }, "请输入正确的真实姓名");

          jQuery.validator.addMethod("characterAll", function(value,element){
                var tel = /^[\u4e00-\u9fa5A-Za-z]*$/;
                  return this .optional(element) || (tel.test(value));
          }, "请输入正确的真实姓名");


          jQuery.validator.addMethod("isPhone",function(value,element){
              var length = value.length;
              //支持13 15 17 18(056789)开头,同时支持xxx-xxxx-xxxx格式手机输入
              var mobile = /^13\d{9}$|15\d{9}$|17\d{9}$|18\d{1}\d{8}|13\d{1}-\d{4}-\d{4}$|15\d{1}-\d{4}-\d{4}$|17\d{1}-\d{4}-\d{4}$|18\d{1}-\d{4}-\d{4}$/; 
              var result = this.optional(element) || (mobile.test(value));
              return result;
          },"请输入正确的手机号码");

            $(".openShop-form").validate({
                rules:{
                    storeName:{
                        required:true,
                        rangelength:[3,25],
                        character:true
                    },
                    sellerName:{
                        required:true,
                        rangelength:[1,25],
                        characterAll:true
                    },
                    sellerMobile:{
                        required:true,
                        isPhone:true
                    },
                    /* sellerEmail:{
                        required:true,
                        email:true
                    },
                    companyRegisteredCapital:{
                        required:true,
                        number:true,
                        min:0
                    }, */
                    companyValue:{
                        required:true,
                    },
                    addressDetail:{
                        required:true,
                        rangelength:[1,100]
                    },
                    /* organizationCode:{
                        required:true,
                        rangelength:[1,50]
                    },
                    taxRegistrationCertificate:{
                        required:true,
                        rangelength:[1,50]
                    }, */
                    memService:{
                    	 required:true,
                    }
                    
                },
                messages:{
                    storeName:{
                        required:"请输入店铺名称",
                        rangelength:"店铺名称3-25个字符",
                        character:"请输入正确的店铺名称",
                    },
                    sellerName:{
                        required:"请输入企业联系人",
                        rangelength:"企业联系人1-25个字符",
                        characterAll:"请输入正确的联系人姓名"
                    },
                    sellerMobile:{
                        required:"请输入联系人的手机号码",
                        isPhone:"请输入正确的手机号码"
                    },
                   /*  sellerEmail:{
                        required:"请输入联系人的邮箱",
                        email:"邮箱格式错误"
                    },
                    companyRegisteredCapital:{
                        required:"请填写注册资金",
                        number:"注册资金必须为数字",
                        min:"注册资金必须大于0"
                    }, */
                    companyValue:{
                        required:"请选择省份城市",
                    },
                    addressDetail:{
                        required:"请填写详细地址",
                        rangelength:"详细地址1-100个字符"
                    },
                   /*  organizationCode:{
                        required:"请填写组织代码",
                        rangelength:"组织代码1-50个字符"
                    },
                    taxRegistrationCertificate:{
                        required:"请填写税务登记号",
                        rangelength:"税务登记号1-50个字符"
                    }, */
                    memService:{
                   	 required:"请选择服务协议",
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
                    var num1 = $("#approve-1").find(".approve-state").attr("data-value");
                   // var num2 = $("#approve-2").find(".approve-state").attr("data-value");
                   var num2="1";
                    if(num1 == "1" && num2 == "1"){
                    	 $(".loading-middle").removeClass("hidden");
                      	 $(".btn").attr("disabled","disabled");
                    	form.submit();
                    }else if(num1 == "0"){
                        layer.open({
                            content: '请先完善营业执照信息',
                            btn: '我知道了'
                        });
                        return false;
                    }else if(num2 == "0"){
                        layer.open({
                            content: '请先完善财务资质信息',
                            btn: '我知道了'
                        });
                    }
                }    
            })
        });
    </script>
</body>
</html>