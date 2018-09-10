var statusLock,delLock,addLock,updateLock = false;
var dbMsg="已经在提交中，无需重复提交";
var addMsg="添加失败，请检查输入是否正确";
var updateMsg="修改失败，请检查输入是否正确";
var delMsg="删除失败，请检查输入是否正确";


//修改停启用状态
function ajaxUpdateStatus(id,status,obj,url) {
	if(statusLock){
		bootbox.alert(dbMsg);
		return false;
	}
	if(!stringIsEmpty(status)){
		//var msg= (status=='0') ? : "确定启用吗？";
		bootbox.confirm("确定该操作吗？", function(result) {
			if(result){
				statusLock=true;
				$.ajax({
      				type: "post",
      				url: url,
      				dataType: "json",
      				data: {
      					"id":id,
      					"status":status,
      					"token":$(obj).siblings(".security-token").val()
      				},
      				success:function(data){
      					statusLock = false;
      					if(!stringIsEmpty(data)){
      						var code=data.resultVo.code;
      						var msg=data.resultVo.msg;
      						if(!stringIsEmpty(code)){
      							if(code == 200){
      								bootbox.confirm('修改成功',function(result){
										window.location.reload();
									});
	      						}else if(code != 0){
	      							bootbox.alert(msg);
	      						}else{
	      							bootbox.alert(updateMsg);
	      						}
      						}else{
      							bootbox.alert(updateMsg);
      						}
      					}else{
      						bootbox.alert('修改失败');
      					}
      				},
      				error:function(){
      					statusLock = false;
      					bootbox.alert('网络异常');
      				}
      			});
			}else{
				statusLock = false;
			}
		});
	}else{
		statusLock = false;
		bootbox.alert("操作失败，参数异常");
	}
}

//删除
function ajaxUpdateDel(id,obj,url) {
	if(delLock){
		bootbox.alert(dbMsg);
		return false;
	}
	bootbox.confirm("确定删除吗?", function(result) {
	if(result){
		delLock=true;
		$.ajax({
 				type: "post",
 				url: url,
 				dataType: "json",
 				data: {
 					"id":id,
 					"token":$(obj).siblings(".security-token").val()
 				},
 				success:function(data){
 					delLock = false;
 					if(!stringIsEmpty(data)){
 						var code=data.resultVo.code;
 						var msg=data.resultVo.msg;
 						if(!stringIsEmpty(code)){
 							if(code == 200){
 								bootbox.confirm('删除成功',function(result){
								window.location.reload();
							});
  						}else if(code != 0){
  							bootbox.alert(msg);
  						}else{
  							bootbox.alert(delMsg);
  						}
 						}else{
 							bootbox.alert(delMsg);
 						}
 					}else{
 						bootbox.alert('删除失败');
 					}
 				},
 				error:function(){
 					delLock = false;
 					bootbox.alert('网络异常');
 				}
 			});
	}else{
		delLock = false;
	}
 });
}

//异步添加资料
function ajaxAdd(form,url){
	var addLock = false;
	if(addLock){
		bootbox.alert(dbMsg);
		return false;
	}
	addLock=true;
  	$.ajax({
		type: "post",
		url: url,
		dataType: "json",
		data:  $(form).serialize(),
		success:function(data){
			addLock = false;
			if(!stringIsEmpty(data)){
				var code=data.resultVo.code;
				var msg=data.resultVo.msg;
				if(null != code && "" != code){
					if(code == 200){
						bootbox.confirm(msg,function(result){
							if(result){
								if(code == 200){
									self.location=document.referrer;
								}	
							}
							
						});
					}else if(code != 0){
						bootbox.alert(msg);
					}else{
						bootbox.alert(addMsg);
					}
				}else{
					bootbox.alert(addMsg);
				}
			}else{
				bootbox.alert(addMsg);
			}
		},
		error:function(data,status,e){
			addLock = false;
			bootbox.alert('网络异常，请重试');
		}
	});
}

//异步修改资料
function ajaxUpdate(form,url){
	if(updateLock){
		bootbox.alert(dbMsg);
		return false;
	}
	updateLock=true;
	$.ajax({
		type: "post",
		url: url,
		dataType: "json",
		data:  $(form).serialize(),
		success:function(data){
			updateLock=false;
			if(!stringIsEmpty(data)){
				var code=data.resultVo.code;
				var msg=data.resultVo.msg;
				if(null != code && "" != code){
					if(code != 0){
						bootbox.confirm(msg,function(result){
							if(result){
								if(code == 200){
									self.location=document.referrer;
								}	
							}
						});
					}else{
						bootbox.alert(updateMsg);
					}
				}else{
					bootbox.alert(updateMsg);
				}
			}else{
				bootbox.alert(updateMsg);
			}
		},
		error:function(data,status,e){
			updateLock=false;
			bootbox.alert('网络异常，请重试');
		}
	});
}
