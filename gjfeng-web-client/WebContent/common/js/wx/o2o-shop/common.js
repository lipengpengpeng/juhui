function previewImage(file,maxWidth,maxHeight,state){//图片上传预览
    //console.log(file.files);
    var MAXWIDTH  = maxWidth;
    var MAXHEIGHT = maxHeight;
    var img = $(file).siblings("img");
    img.attr("src","");
    img.onload = function(){
        var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
        img.width  =  rect.width;
        img.height =  rect.height;
    }
    if (file.files && file.files[0]){
        var reader = new FileReader();
        reader.onload = function(evt){
        	img.attr("src",evt.target.result);
        }
       
       
        reader.readAsDataURL(file.files[0]);
        //headImg = file.files[0];
        if(img.hasClass('hidden')){
            img.removeClass('hidden')
        }
        if(img.hasClass("business-img")){
            var uploadImg = file.files[0];
           /* $.ajax({
                url: '',
                type: 'POST',
                dataType: 'json',
                data: {param1: 'value1'},
            })*/
        }
    }else{//没有图片;
        if(img.hasClass("instrut-img")||img.hasClass("shopShowImg")){//评价页面、编辑商店页面
            img.attr("src","images/upload-pictures.png");
        }else if(img.hasClass("user-img")){//修改个人信息的头像上传
            return false;
        }else if(img.hasClass("idCard-front")){//身份证前
            img.attr("src","images/ID-card_front.png");
        }else if(img.hasClass("idCard-back")){//身份证后
            img.attr("src","images/ID-card_back.png");
        }else if(img.hasClass("idCard-hold")){//身份证手持
            img.attr("src","images/hold-ID-card.png");
        }else{
            img.attr("src","");
            img.addClass("hidden");
        }
    }
}
function clacImgZoomParam( maxWidth, maxHeight, width, height ){
    var param = {top:0, left:0, width:width, height:height};
    if( width>maxWidth || height>maxHeight ){
        rateWidth = width / maxWidth;
        rateHeight = height / maxHeight;
        if( rateWidth > rateHeight ){
            param.width =  maxWidth;
            param.height = Math.round(height / rateWidth);
        }else{
            param.width = Math.round(width / rateHeight);
            param.height = maxHeight;
        }
    }
    param.left = Math.round((maxWidth - param.width) / 2);
    param.top = Math.round((maxHeight - param.height) / 2);
    return param;
}