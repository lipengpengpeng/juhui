$.fn.extend({     
     
   workunitClick:function(obj){ 

   	var workunitName = $(this).attr('id');
   //alert(workunitName);
     $("#"+($(this).attr('id'))+"").after("<input type='button' id='showButton' class='default_button' value='选择'/>");
     
     $("#showButton").bind("click",function(){
     		show_DIV(workunitName,obj);
     		var key = $("#keyword").attr("value");
			searchKeyword(workunitName,key,obj);
     	});

    },
    
      nationShow:function(obj) {
     
      	var nationName = $(this).attr('id');
      	//alert($(this).attr('id'));
      	$("#"+($(this).attr('id'))+"").after("<input type='button' id='nationButton' class='default_button' value='选择'/>");
      	
      	$("#nationButton").bind("click",function(){
      	
     		show_nation(nationName,obj);
     		
     		var key = $("#nationkeyword").attr("value");
			searchNation(nationName,key,obj);
     		
     	});
     	
     	
     
      },
      
      businessShow:function(obj) {
     	var businessName = $(this).attr('id');
     	$("#"+($(this).attr('id'))+"").after("<input type='button' id='businessButton' class='default_button' value='选择'/>");
      	
      	$("#businessButton").bind("click",function(){
      	
     		show_business(businessName,obj);
     		
     		var key = $("#businesskeyword").attr("value");
			searchbusiness(businessName,key,obj);

     		
     	});

      },
      
      titleShow:function(obj) {
     	var titleName = $(this).attr('id');
     	$("#"+($(this).attr('id'))+"").after("<input type='button' id='titleButton' class='default_button' value='选择'/>");
      	
      	$("#titleButton").bind("click",function(){
      	
     		show_title(titleName,obj);
     		
     		var key = $("#titlekeyword").attr("value");
			searchtitle(titleName,key,obj);

     		
     	});

      },
      
      degreeShow:function(obj) {
     	var degreeName = $(this).attr('id');
     	$("#"+($(this).attr('id'))+"").after("<input type='button' id='degreeButton' class='default_button' value='选择'/>");
      	
      	$("#degreeButton").bind("click",function(){
      	
     		show_degree(degreeName,obj);
     		
     		var key = $("#degreekeyword").attr("value");
			searchdegree(degreeName,key,obj);

     		
     	});

      },
      
      expertTypeShow:function(obj) {
     	var expertTypeName = $(this).attr('id');
     	$("#"+($(this).attr('id'))+"").after("<input type='button' id='expertTypeButton' class='default_button' value='选择'/>");
      	
      	$("#expertTypeButton").bind("click",function(){
      	
     		show_expertType(expertTypeName,obj);
     		
     		var key = $("#expertTypekeyword").attr("value");
			searchexpertType(expertTypeName,key,obj);

     		
     	});

      },
      
      specialityShow:function(obj) {
     	var specialityName = $(this).attr('id');
     	$("#"+($(this).attr('id'))+"").after("<input type='button' id='specialityButton' class='default_button' value='选择'/>");
      	
      	$("#specialityButton").bind("click",function(){
      	
     		show_speciality(specialityName,obj);
     		
     		var key = $("#specialitykeyword").attr("value");
			searchspeciality(specialityName,key,obj);

     		
     	});

      }
         
     
});  

  function searchWorkunit(name,id) {

		sy_Box_Hide("divModifyPwd");

		//$(name).val(parseInt(id));
		
		$(name).val(id);
	}
	
	function searchKeyword(workunitName,key,obj) {
		
		$.ajax({
			type: "POST",
			url: '/expert/personal/expert!searchWorkunit.action',
			data: "keyword="+key,
			success: function(msg){
			
				var str = msg.split(" ");
				var strIn;
				$(".sy_box_in ul").empty();
				for(var i=0;i<str.length-1;i++) {
					strIn = str[i].split(",");
					workunitNames = '"#'+workunitName+'"';
					//alert(workunitNames);
					if(obj!=null) {
						//alert("aaa");
						var workvalue = '"'+(obj+strIn[1])+'"';
						$(".sy_box_in ul").append("<li><a href='javascript:searchWorkunit("+workunitNames+","+workvalue+")' workunitID="+strIn[0]+">"+strIn[1]+"</a></li>");
					}else{
						$(".sy_box_in ul").append("<li><a href='javascript:searchWorkunit("+workunitNames+","+strIn[0]+")' workunitID="+strIn[0]+">"+strIn[1]+"</a></li>");
					}
				}
			
				
			}
			});
	}	
	
	function searchNation(nationName,nationkey,obj) {
		
		$.ajax({
			type: "POST",
			url: '/expert/personal/expert!searchNation.action',
			data: "keyword="+nationkey,
			success: function(msg){
			
				var str = msg.split(" ");
				var strIn;
				$(".box_nation ul").empty();
				for(var i=0;i<str.length-1;i++) {
					strIn = str[i].split(",");
					nationNames = '"#'+nationName+'"';
					//alert(workunitNames);
					if(obj!=null) {
						//alert("aaa");
						var nationvalue = '"'+(obj+strIn[1])+'"';
						$(".box_nation ul").append("<li><a href='javascript:nationSelect("+nationNames+","+nationvalue+")' nationID="+strIn[0]+">"+strIn[1]+"</a></li>");
					}else{
						$(".box_nation ul").append("<li><a href='javascript:nationSelect("+nationNames+","+strIn[0]+")' nationID="+strIn[0]+">"+strIn[1]+"</a></li>");
					}
				}
			
				
			}
			});
	}
	
	function nationSelect(name,id) {
		
		sy_Box_Hide("nationDiv");

		//$(name).val(parseInt(id));
		
		$(name).val(id);
	}	
	
	
	function searchbusiness(businessName,businesskey,obj) {
		
		$.ajax({
			type: "POST",
			url: '/expert/personal/expert!searchBusiness.action',
			data: "keyword="+businesskey,
			success: function(msg){
			
				var str = msg.split(" ");
				var strIn;
				$(".box_business ul").empty();
				for(var i=0;i<str.length-1;i++) {
					strIn = str[i].split(",");
					businessNames = '"#'+businessName+'"';
					//alert(workunitNames);
					if(obj!=null) {
						//alert("aaa");
						var businessvalue = '"'+(obj+strIn[1])+'"';
						$(".box_business ul").append("<li><a href='javascript:businessSelect("+businessNames+","+businessvalue+")' businessID="+strIn[0]+">"+strIn[1]+"</a></li>");
					}else{
						$(".box_business ul").append("<li><a href='javascript:businessSelect("+businessNames+","+strIn[0]+")' businessID="+strIn[0]+">"+strIn[1]+"</a></li>");
					}
				}
			
				
			}
			});
	}
	
	function businessSelect(name,id) {
		
		sy_Box_Hide("businessDiv");

		//$(name).val(parseInt(id));
		
		$(name).val(id);
	}
	
	
	function searchtitle(titleName,titlekey,obj) {
		
		$.ajax({
			type: "POST",
			url: '/expert/personal/expert!searchTitle.action',
			data: "keyword="+titlekey,
			success: function(msg){
			
				var str = msg.split(" ");
				var strIn;
				$(".box_title ul").empty();
				for(var i=0;i<str.length-1;i++) {
					strIn = str[i].split(",");
					titleNames = '"#'+titleName+'"';
					//alert(workunitNames);
					if(obj!=null) {
						//alert("aaa");
						var titlevalue = '"'+(obj+strIn[1])+'"';
						$(".box_title ul").append("<li><a href='javascript:titleSelect("+titleNames+","+titlevalue+")' titleID="+strIn[0]+">"+strIn[1]+"</a></li>");
					}else{
						$(".box_title ul").append("<li><a href='javascript:titleSelect("+titleNames+","+strIn[0]+")' titleID="+strIn[0]+">"+strIn[1]+"</a></li>");
					}
				}
			
				
			}
			});
	}
	
	function titleSelect(name,id) {
		
		sy_Box_Hide("titleDiv");

		//$(name).val(parseInt(id));
		
		$(name).val(id);
	}
	
	function searchdegree(degreeName,degreekey,obj) {
		
		$.ajax({
			type: "POST",
			url: '/expert/personal/expert!searchDegree.action',
			data: "keyword="+degreekey,
			success: function(msg){
			
				var str = msg.split(" ");
				var strIn;
				$(".box_degree ul").empty();
				for(var i=0;i<str.length-1;i++) {
					strIn = str[i].split(",");
					degreeNames = '"#'+degreeName+'"';
					//alert(workunitNames);
					if(obj!=null) {
						//alert("aaa");
						var degreevalue = '"'+(obj+strIn[1])+'"';
						$(".box_degree ul").append("<li><a href='javascript:degreeSelect("+degreeNames+","+degreevalue+")' degreeID="+strIn[0]+">"+strIn[1]+"</a></li>");
					}else{
						$(".box_degree ul").append("<li><a href='javascript:degreeSelect("+degreeNames+","+strIn[0]+")' degreeID="+strIn[0]+">"+strIn[1]+"</a></li>");
					}
				}
			
				
			}
			});
	}
	
	function degreeSelect(name,id) {
		
		sy_Box_Hide("degreeDiv");

		//$(name).val(parseInt(id));
		
		$(name).val(id);
	}
	
	function searchexpertType(expertTypeName,expertTypekey,obj) {
		
		$.ajax({
			type: "POST",
			url: '/expert/personal/expert!searchExpertType.action',
			data: "keyword="+expertTypekey,
			success: function(msg){
			
				var str = msg.split(" ");
				var strIn;
				$(".box_expertType ul").empty();
				for(var i=0;i<str.length-1;i++) {
					strIn = str[i].split(",");
					expertTypeNames = '"#'+expertTypeName+'"';
					//alert(workunitNames);
					if(obj!=null) {
						//alert("aaa");
						var expertTypevalue = '"'+(obj+strIn[1])+'"';
						$(".box_expertType ul").append("<li><a href='javascript:expertTypeSelect("+expertTypeNames+","+expertTypevalue+")' expertTypeID="+strIn[0]+">"+strIn[1]+"</a></li>");
					}else{
						$(".box_expertType ul").append("<li><a href='javascript:expertTypeSelect("+expertTypeNames+","+strIn[0]+")' expertTypeID="+strIn[0]+">"+strIn[1]+"</a></li>");
					}
				}
			
				
			}
			});
	}
	
	function expertTypeSelect(name,id) {
		
		sy_Box_Hide("expertTypeDiv");

		//$(name).val(parseInt(id));
		
		$(name).val(id);
	}
	
	function searchspeciality(specialityName,specialitykey,obj) {
		
		$.ajax({
			type: "POST",
			url: '/expert/personal/expert!searchSpeciality.action',
			data: "keyword="+specialitykey,
			success: function(msg){
			
				var str = msg.split(" ");
				var strIn;
				$(".box_speciality ul").empty();
				for(var i=0;i<str.length-1;i++) {
					strIn = str[i].split(",");
					specialityNames = '"#'+specialityName+'"';
					//alert(workunitNames);
					if(obj!=null) {
						//alert("aaa");
						var specialityvalue = '"'+(obj+strIn[1])+'"';
						$(".box_speciality ul").append("<li><a href='javascript:specialitySelect("+specialityNames+","+specialityvalue+")' specialityID="+strIn[0]+">"+strIn[1]+"</a></li>");
					}else{
						$(".box_speciality ul").append("<li><a href='javascript:specialitySelect("+specialityNames+","+strIn[0]+")' specialityID="+strIn[0]+">"+strIn[1]+"</a></li>");
					}
				}
			
				
			}
			});
	}
	
	function specialitySelect(name,id) {
		
		sy_Box_Hide("specialityDiv");

		//$(name).val(parseInt(id));
		
		$(name).val(id);
	}