<%@ page contentType="text/html;charset=utf-8"%>
<%@include file="/common/wx-o2o-top.jsp" %>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
<body>
    <div class="container">
        <div class="city-item clearfix">
            <div class="city-left left">
                <label class="now-city">当前城市:</label>
                <span class="city-text" id="currentCity">
	                <c:if test="${not empty sessionScope.cityName}">${sessionScope.cityName}</c:if>
	                <c:if test="${empty sessionScope.cityName}">广州市</c:if>
                </span>
            </div>
            <div class="city-right right">
                <span class="city-tip" id="position">定位</span>
            </div>
        </div>
        <div class="city-title">
            <span class="c-title-span">热门城市</span>
        </div>
        <div class="city-wrap clearfix hotCity">
            <a href="#" class="city-wrap-link left hot-city" data-code='110100'>北京市</a>
            <a href="#" class="city-wrap-link left hot-city" data-code='110100'>上海市</a>
            <a href="#" class="city-wrap-link left hot-city" data-code='110100'>广州市</a>
            <a href="#" class="city-wrap-link left hot-city" data-code='110100'>深圳市</a>
            <a href="#" class="city-wrap-link left hot-city" data-code='110100'>杭州市</a>
            <a href="#" class="city-wrap-link left hot-city" data-code='110100'>南京市</a>
            <a href="#" class="city-wrap-link left hot-city" data-code='110100'>苏州市</a>
            <a href="#" class="city-wrap-link left hot-city" data-code='110100'>武汉市</a>
        </div>
        <div class="city-title">
            <span class="c-title-span">全部城市</span>
        </div>
        <div class="city-wrap clearfix">
            <a href="#A" class="city-wrap-link left city-letter">A</a>
            <a href="#B" class="city-wrap-link left city-letter">B</a>
            <a href="#C" class="city-wrap-link left city-letter">C</a>
            <a href="#D" class="city-wrap-link left city-letter">D</a>
            <a href="#E" class="city-wrap-link left city-letter">E</a>
            <a href="#F" class="city-wrap-link left city-letter">F</a>
            <a href="#G" class="city-wrap-link left city-letter">G</a>
            <a href="#H" class="city-wrap-link left city-letter">H</a>
            <a href="#I" class="city-wrap-link left city-letter">I</a>
            <a href="#J" class="city-wrap-link left city-letter">J</a>
            <a href="#K" class="city-wrap-link left city-letter">K</a>
            <a href="#L" class="city-wrap-link left city-letter">L</a>
            <a href="#M" class="city-wrap-link left city-letter">M</a>
            <a href="#N" class="city-wrap-link left city-letter">N</a>
            <a href="#P" class="city-wrap-link left city-letter">P</a>
            <a href="#Q" class="city-wrap-link left city-letter">Q</a>
            <a href="#R" class="city-wrap-link left city-letter">R</a>
            <a href="#S" class="city-wrap-link left city-letter">S</a>
            <a href="#T" class="city-wrap-link left city-letter">T</a>
            <a href="#W" class="city-wrap-link left city-letter">W</a>
            <a href="#X" class="city-wrap-link left city-letter">X</a>
            <a href="#Y" class="city-wrap-link left city-letter">Y</a>
            <a href="#Z" class="city-wrap-link left city-letter">Z</a>
        </div>
        <div id="allCity"></div>
        
    </div>
</body>
<script>
	document.title = "切换城市";
	var localStatus="${sessionScope.localStatus}";
	if(null == localStatus || "" == localStatus || localStatus != "1"){
		wxAddress();
	}
	   //定位
    	$("#position").on("touchend",function(){
    		wxAddress();
    	})
    	
    	//点击热门城市
    	$(".hot-city").each(function(){
    		$(this).click(function(){
    			var city=$(this).text()
    			$("#currentCity").html(city);
    			setCityName(city);
    		})
    	})
    	
    	//根据字母获取城市
    	getCiyt()
    	function getCiyt(){
    		var letterArr = new Array("A", "B", "C","D","E","F","G","H","I","J","K","L","M","N","P","Q","R","S","T","W","X","Y","Z");
    	    var allInfo=""
    	    for(var i=0;i<letterArr.length;i++){
    	    	var let=letterArr[i]
    	    	$.ajax({
    	    		url:"${ctx}/wx/address/getProvinceByLetter",
    	    		method:"GET",
    	    		async:false,
    	    		data:{
    	    			letter:let,
    	    			type:2
    	    		},
    	    		success:function(data){
    	    			try {
    	    				if(null != data.result && "" != data.result && data.result != undefined){
    	    					var str="<div class='city-filed' id='"+data.result[0].letter+"'>"
   	                          +"<div class='city-title'>"
                              +"<span class='c-title-big'>"+data.result[0].letter+"</span></div>"
                              +"<div class='city-wrap clearfix'>"
                              
  			    			for(var j=0;j<data.result.length;j++){
  			    				str+="<a  class='city-wrap-link left city-small' data-code='"+data.result[j].cityId+"'>"+data.result[j].city+"</a>";	          	                
  			    			}
  	                        str+="</div></div>";
  	                            //alert($("#allCity .city-filed:last"))
  	                            //$("#allCity .city-filed:last").append(str)
  	                            $("#allCity").append(str)
  	                            //$("#allCity").after(str)
    	    				}
						} catch (e) {
							console.log(e);
						}
                     
                     //点击城市
                     $(".city-small").each(function(){
                    	 $(this).click(function(){
                    		 $("#currentCity").html($(this).text());
                    		 setCityName($(this).text(),$(this).attr("data-code"));
                    	 })
                     })
    	    		}
    	    	})
    	    }
    	   
	   }

	   //设置城市名称
	   function setCityName(cityName,cityCode){
		   $.ajax({
               url: '${ctx}/wx/address/setCityName',
               type: 'post',
               dataType: 'json',
               data: {
               	 "cityName":cityName,
               	 "cityCode":cityCode
               },
               success:function(data){
            	   self.location=document.referrer;
               },
               error:function(){
               	
               }
           }); 
	   }
	   
	   //微信定位
	   function wxAddress(){
		   $.ajax({
				type: "POST", //用POST方式传输
				url: '${ctx}/wx/localSign', //目标地址
				data: {
					lastPath:"/wx/address/toLocal"
				},
				success:function(data){
					//wxAddress(data);
					//alert(location.href.split('#')[0]) 
					var appId=data.appId;
					var timestamp=data.timestamp;
					var nonceStr=data.noncestr;
					var signature=data.signature
			    	wx.config({
			    		debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			    	    appId: appId, // 必填，公众号的唯一标识
			    	    timestamp:timestamp, // 必填，生成签名的时间戳
			    	    nonceStr: nonceStr, // 必填，生成签名的随机串
			    	    signature: signature,// 必填，签名，见附录1
			    	    jsApiList: ['getLocation'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
			    	});    		        	
			 		
					wx.ready(function(){
						wx.getLocation({
		    			    type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
		    			    success: function (res) {
		    			        var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
		    			        var longitude = res.longitude ; // 经度，浮点数，范围为180 ~ -180。
		    			        var speed = res.speed; // 速度，以米/每秒计
		    			        var accuracy = res.accuracy; // 位置精度
		    			        $.ajax({
		    		                url: '${ctx}/wx/address/getCityName',
		    		                type: 'post',
		    		                dataType: 'json',
		    		                data: {
		    		                	"latitude":latitude,
		    		                	"longitude":longitude
		    		                },
		    		                success:function(data){
		    		                	$("#currentCity").text(data.result);
		    		                },
		    		                error:function(){
		    		                	
		    		                }
		    		            });
		    			    },		   			    
		    			});
				    });
					 wx.error(function(res) {
				    	 //alert(res.errMsg);	    	
				     }); 
			     
			 	},
			 	
			});
	   }
</script>
</html>