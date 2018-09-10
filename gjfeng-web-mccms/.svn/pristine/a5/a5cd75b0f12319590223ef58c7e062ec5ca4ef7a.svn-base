		$(function(){
			var pri_var =$("#pri_var").val();
			//var pri_var2 = '${requestScope.pri}';
			$.ajax({
				url:ctx+"/areaTest.htm",
				data:{step:"getP"},
				type:"post",
				dataType:"json",
				success:function(data){
					var a = "";
					for(var i=0;i<data.length;i++){
						if(pri_var!=null && pri_var == data[i]["provinceId"]){
							var a = "selected='selected'";
						}
						$("#pri").append("<option value='"+data[i]["provinceId"]+"'"+a+">"+data[i]["province"]+"</option>");
				 }
				},
				error:function(){
					console.log("省份获取失败");
				}
			});
		});
		
		function priChange(){
		   	jQuery("#city").empty();
		   	jQuery("#area").empty();
		   	$("#city").append('<option value="nc">--城市--</option>');
		   	$("#area").append('<option value="nc">--地区--</option>');
		   	if($("#pri").val()=="nc"){
				return false;
			}
			var pid=jQuery("#pri").val();
        	$.ajax({
				url:ctx+"/areaTest.htm",
				data:{step:"getCity",pid:pid},
				type:"post",
				dataType:"json",
				success:function(data){
					for(var i=0;i<data.length;i++){
				 		$("#city").append("<option value=\'"+data[i]["cityId"]+"\'>"+data[i]["city"]+"</option>");
				    }
				},
				error:function(){
					console.log("区域获取失败");
				}
			});
       	}
       	function cityChange(){
       		
		   	jQuery("#area").empty();
		   	$("#area").append('<option value="nc">--地区--</option>');
		   	if($("#city").val()=="nc"){
				return false;
			}
		   	
		   	var cid=jQuery("#city").val();
        	$.ajax({
				url:ctx+"/areaTest.htm",
				data:{step:"getArea",cid:cid},
				type:"post",
				dataType:"json",
				success:function(data){
					for(var i=0;i<data.length;i++){
				 		$("#area").append("<option value=\'"+data[i]["areaId"]+"\'>"+data[i]["area"]+"</option>");
				 }
				},
				error:function(){
					console.log("城市获取失败");
				}
			});
       	}