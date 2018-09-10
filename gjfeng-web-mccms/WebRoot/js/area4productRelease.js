		$(function(){
			$(document).ready(function(){
			$.ajax({
				url:ctx+"/areaTest.htm",
				data:{step:"getP"},
				type:"post",
				dataType:"json",
				success:function(data){
					for(var i=0;i<data.length;i++){
				 		$("#pri").append("<option value=\'"+data[i]["provinceId"]+"\'>"+data[i]["province"]+"</option>");
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
		
		function priChange(){
			
		   	jQuery("#city").empty();
		   	var text = jQuery("#pri").find("option:selected").text();
		   	$("#city").append('<option value="0">'+text+'(*)</option>');
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
					alert("fail");
				}
			});
       	}
       