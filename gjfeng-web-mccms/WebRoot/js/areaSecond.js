		$(function(){
			$(document).ready(function(){
			$.ajax({
				url:ctx+"/areaTest.htm",
				data:{step:"getP"},
				type:"post",
				dataType:"json",
				success:function(data){
					for(var i=0;i<data.length;i++){
				 
				 		$("#priSecond").append("<option value=\'"+data[i]["provinceId"]+"\'>"+data[i]["province"]+"</option>");
				 }
				},
				error:function(){
					alert("fail");
				}
			});
			/*
				$.get("areaTest.htm",{step:"getP"},function(data){
				var obj = JSON.parse(data);
				 for(var i=0;i<data.length;i++){
				 
				 	$("#pri").append("<option value=\'"+obj[i]["provinceId"]+"\'>"+obj[i]["province"]+"</option>");
				 }
				});*/
			}); 	
		});
		
		function priChangeSecond(){
		   	jQuery("#citySecond").empty();
		   	jQuery("#areaSecond").empty();
		   	$("#citySecond").append('<option value="nc">--城市--</option>');
		   	$("#areaSecond").append('<option value="nc">--地区--</option>');
		   	if($("#priSecond").val()=="nc"){
				return false;
			}
			var pid=jQuery("#priSecond").val();
        	$.ajax({
				url:ctx+"/areaTest.htm",
				data:{step:"getCity",pid:pid},
				type:"post",
				dataType:"json",
				success:function(data){
					
					for(var i=0;i<data.length;i++){
				 		$("#citySecond").append("<option value=\'"+data[i]["cityId"]+"\'>"+data[i]["city"]+"</option>");
				    }
				},
				error:function(){
					alert("fail");
				}
			});
       	}
       	function cityChangeSecond(){
       		
		   	jQuery("#areaSecond").empty();
		   	$("#areaSecond").append('<option value="nc">--地区--</option>');
		   	if($("#citySecond").val()=="nc"){
				return false;
			}
		   	
		   	var cid=jQuery("#citySecond").val();
        	$.ajax({
				url:ctx+"/areaTest.htm",
				data:{step:"getArea",cid:cid},
				type:"post",
				dataType:"json",
				success:function(data){
					
					for(var i=0;i<data.length;i++){
				 
				 		$("#areaSecond").append("<option value=\'"+data[i]["areaId"]+"\'>"+data[i]["area"]+"</option>");
				 }
				},
				error:function(){
					alert("fail");
				}
			});
       	}