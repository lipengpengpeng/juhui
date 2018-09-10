var statusLock,delLock,addLock,updateLock = false;
var dbMsg="已经在提交中，无需重复提交";
var addMsg="操作失败，请检查输入是否正确";
var updateMsg="操作失败，请检查输入是否正确";

//异步添加资料
function ajaxAddOrEdit(form,url){
	var addLock = false;
	if(addLock){
		alert(dbMsg);
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
				var code=data.code;
				var msg=data.msg;
				if(code != 0){
					alert(msg);
					if(code == 200){
						return data;
					}
				}else{
					alert(addMsg);
				}
			}else{
				alert(addMsg);
			}
		},
		error:function(data,status,e){
			addLock = false;
			alert('网络异常，请重试');
		}
	});
}

//修改状态
function ajaxUpdate(url,data,msg,msgStatus) {
	if(statusLock){
		alert(dbMsg);
		return false;
	}
	if(!stringIsEmpty(msgStatus) && msgStatus==1){
		layer.open({
            content: msg,
            btn: ['确定', '取消'],
            yes:function(index){
            	ajaxBody(data);
            },
            no:function(index){
            	statusLock = false;
            }
        });
	}else{
		ajaxBody(data);
	}
}


function ajaxBody(data){
	statusLock=true;
	$.ajax({
			type: "post",
			url: url,
			dataType: "json",
			data: data,
			success:function(data){
				statusLock = false;
				if(!stringIsEmpty(data)){
					var code=data.code;
					var msg=data.msg;
					if(!stringIsEmpty(code)){
						if(code != 0){
							alert(msg);
							if(code == 200){
								return data;
							}
						}else{
							alert(updateMsg);
						}
					}else{
						alert(updateMsg);
					}
				}else{
					alert('操作失败');
				}
			},
			error:function(){
				statusLock = false;
				alert('网络异常');
			}
		});
}
