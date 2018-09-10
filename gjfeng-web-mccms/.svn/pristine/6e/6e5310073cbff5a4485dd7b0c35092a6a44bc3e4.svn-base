
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<style>
			.headbg1 {
				background: #c9e6f5;
				font-weight: bold;
				padding-left: 15px;
			}
			
			.headbg1 td {
				font-weight: bold;
				text-align: center;
			}
			.ratio{
			
				width: 55px;
			}
		</style>

		<script type="text/javascript">
		
		//根据年份更改比例
        function yearChange(obj,s){
        	//默认比例  
		var a1=0;
		var r1=100;
		var a2=15;
		var r2=150;
		var a3=30;
		var r3=180;
		var a5=50;
		var r5=200;
		var ratio =$('#'+s+'').val();
        var ratios=ratio.split(";");
        for (i = 0; i < ratios.length; i++) {
              var temp = ratios[i].split(":"); 
			  if (temp[0] == "1") {  
			    var tempRatio = temp[1].split(",");
			    r1=tempRatio[0];
		        a1=tempRatio[1];
			  }
			   else if (temp[0] == "2") {  
			    var tempRatio = temp[1].split(",");
			    r2=tempRatio[0];
		        a2=tempRatio[1];
			  }  
			   else if (temp[0] == "3") {  
			    var tempRatio = temp[1].split(",");
			    r3=tempRatio[0];
		        a3=tempRatio[1];
			  }  
			   else if (temp[0] == "5") {  
			    var tempRatio = temp[1].split(",");
			    r5=tempRatio[0];
		        a5=tempRatio[1];
			  }    
			}
         //obj年
          if(obj==1){
           $('#agentAmountRatio'+s+'').val(a1);
           $('#returnAmountRatio'+s+'').val(r1);
          }
           else if(obj==2){
           $('#agentAmountRatio'+s+'').val(a2);
           $('#returnAmountRatio'+s+'').val(r2);
          }
           else if(obj==3){
           $('#agentAmountRatio'+s+'').val(a3);
           $('#returnAmountRatio'+s+'').val(r3);
          }
           else if(obj==5){
           $('#agentAmountRatio'+s+'').val(a5);
           $('#returnAmountRatio'+s+'').val(r5);
          }
          var ratio_val="1:"+r1+","+a1+";"+"2:"+r2+","+a2+";"+"3:"+r3+","+a3+";"+"5:"+r5+","+a5;	
          $('#'+s+'').val(ratio_val)
        }
        //存储各年段授权比例
        function amount(obj,s){
	        var a1=0;
			var r1=100;
			var a2=15;
			var r2=150;
			var a3=30;
			var r3=180;
			var a5=50;
			var r5=200;
			var ratio =$('#'+s+'').val();
	        var ratios=ratio.split(";");
	        for (i = 0; i < ratios.length; i++) {
	              var temp = ratios[i].split(":"); 
				  if (temp[0] == "1") {  
				    var tempRatio = temp[1].split(",");
				    r1=tempRatio[0];
			        a1=tempRatio[1];
				  }
				   else if (temp[0] == "2") {  
				    var tempRatio = temp[1].split(",");
				    r2=tempRatio[0];
			        a2=tempRatio[1];
				  }  
				   else if (temp[0] == "3") {  
				    var tempRatio = temp[1].split(",");
				    r3=tempRatio[0];
			        a3=tempRatio[1];
				  }  
				   else if (temp[0] == "5") {  
				    var tempRatio = temp[1].split(",");
				    r5=tempRatio[0];
			        a5=tempRatio[1];
				  }    
				}
        
            var par=obj.parentNode;
			var chils= par.childNodes; 
			var year =chils[1].value;
			var temp =chils[5].value;
			var patrn=/^(?:0|[1-9]\d*)(\.\d{1,2})?$/;
			var msg="您输入的比例有误,请重新输入！";
        if(temp==""||temp==null){
             alert(msg);
              if(year==1)chils[5].value=r1;
		 else if(year==2)chils[5].value=r2;
		 else if(year==3)chils[5].value=r3;
		 else if(year==5)chils[5].value=r5;
            return false;
        }
		if (!patrn.exec(temp)){
		  alert(msg);
		      if(year==1)chils[5].value=r1;
		 else if(year==2)chils[5].value=r2;
		 else if(year==3)chils[5].value=r3;
		 else if(year==5)chils[5].value=r5;
		  return false;
		}
           if(year==1){
            a1=temp;
          }
           else if(year==2){
            a2=temp;
          }
           else if(year==3){
            a3=temp;
          }
           else if(year==5){
            a5=temp;
          }
           var ratio_val="1:"+r1+","+a1+";"+"2:"+r2+","+a2+";"+"3:"+r3+","+a3+";"+"5:"+r5+","+a5;	
          $('#'+s+'').val(ratio_val)
        }
        //存储各年段返回比例
        function reAmount(obj,s){
            var a1=0;
			var r1=100;
			var a2=15;
			var r2=150;
			var a3=30;
			var r3=180;
			var a5=50;
			var r5=200;
			var ratio =$('#'+s+'').val();
	        var ratios=ratio.split(";");
	        for (i = 0; i < ratios.length; i++) {
	              var temp = ratios[i].split(":"); 
				  if (temp[0] == "1") {  
				    var tempRatio = temp[1].split(",");
				    r1=tempRatio[0];
			        a1=tempRatio[1];
				  }
				  else if (temp[0] == "2") {  
				    var tempRatio = temp[1].split(",");
				    r2=tempRatio[0];
			        a2=tempRatio[1];
				  }  
				  else if(temp[0] == "3") {  
				    var tempRatio = temp[1].split(",");
				    r3=tempRatio[0];
			        a3=tempRatio[1];
				  }  
				  else if (temp[0] == "5") {  
				    var tempRatio = temp[1].split(",");
				    r5=tempRatio[0];
			        a5=tempRatio[1];
				  }    
				}
        
            var par=obj.parentNode;
			var chils= par.childNodes; 
			var year =chils[1].value;
			var temp =chils[3].value;
			var patrn=/^(?:0|[1-9]\d*)(\.\d{1,2})?$/;
			var msg="您输入的比例有误,请重新输入！";
        if(temp==""||temp==null){
             alert(msg);
              if(year==1)chils[3].value=r1;
		 else if(year==2)chils[3].value=r2;
		 else if(year==3)chils[3].value=r3;
		 else if(year==5)chils[3].value=r5;
            return false;
        }
        if (!patrn.exec(temp)){
		  alert(msg);
		  if(year==1)chils[3].value=r1;
		 else if(year==2)chils[3].value=r2;
		 else if(year==3)chils[3].value=r3;
		 else if(year==5)chils[3].value=r5;
		  return false;
		}
           if(year==1){
            r1=temp;
          }
           else if(year==2){
            r2=temp;
          }
           else if(year==3){
            r3=temp;
          }
           else if(year==5){
            r5=temp;
          }
          var ratio_val="1:"+r1+","+a1+";"+"2:"+r2+","+a2+";"+"3:"+r3+","+a3+";"+"5:"+r5+","+a5;	
          $('#'+s+'').val(ratio_val)
        }
			function editClick(s){
				var par=s.parentNode;
				var chils= par.childNodes; 
				chils[0].disabled=false;
				chils[4].disabled=false;
			}
			function editClick2(s){
				var par=s.parentNode;
				var chils= par.childNodes; 
				chils[1].disabled=false;
				chils[3].disabled=false;
				chils[5].disabled=false;
				chils[9].disabled=false;
			}
			
			function saveEdit(s,obj,obj2,id){
				
			    var par=s.parentNode;
				var chils= par.childNodes;
				var tempId=chils[6].value;
				var dataNum=chils[0].value;
				if(id==null){
				    var num= chils[0].value;
				     var patrn=/^(?:0|[1-9]\d*)(\.\d{1,2})?$/;
				     //判断输入的是金额还是比例
				    if(obj2==1){
					  var msg="您输入的金额有误,请重新输入！";
				    }else if(obj2==2){
					  var msg="您输入的比例有误,请重新输入！";
				    }
					if (!patrn.exec(num)){
					  alert(msg);
					  chils[0].value=obj.toFixed(2);
					  return false;
					}
				}else{
				    tempId = id;
				   	dataNum =$('#'+id+'').val();
				}
				$.ajax({     
				    url:'${ctx}/sysAgentConfigAction!editSysAgentConfig.htm',     
				    type:'post', 
				    dataType: "json",    
				    data:{
				    	id:tempId,
				    	dataNum:dataNum,
				    	op:obj2
				    },     
				    async : false, //默认为true 异步     
				    error:function(){     
				        alert("修改失败!");
				        chils[0].value=obj.toFixed(2);
				        location.href = "memberAgentAction!retrieveAllMemberAgents.action"; 
				    },     
				    success:function(data){   
				       if(data.msg == 'success') {
	                        alert("修改成功!");
		                        //编辑框
		                        if(id==null){
							       chils[0].disabled=true;
							       chils[4].disabled=true;
							      }
							     else{
							        chils[1].disabled=true;
							        chils[3].disabled=true;
									chils[5].disabled=true;
									chils[9].disabled=true;
							     }  
	                        }else{
								alert("修改失败!");
								chils[0].value=obj.toFixed(2);
							}	
				    	
				    }  
				});  
				
			}
			
		 
		 </script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 设置 - 代理区域配置
			</div>
			<div class="ropt">
			 <input type="submit" onclick="location.href='sysAgentConfigAction!newPage.action'" style="margin-left:30px;" class="default_button" value="新增">
<!--				<a href="storeJoininAction!newPage.action">新增</a>  -->
			</div>
			<div class="clear"></div>
		</div>
		<form action="sysAgentConfigAction!retrieveAllSysAgentConfigs.action" method="post">
			<table class="listTable2">
				<tbody>
				<tr > 
				<td class="pn-flabel" style="text-align:left;padding-left:15px;">
				  区域名称：<input type="text" name="areaInfo" value="<s:property value='areaInfo' />"/><!--
				  店主：<input type="text" name="owner_and_name" value="<s:property value='owner_and_name' />">
				  所属等级：
				  <select name="grade_id"  id ="storeGrades" >
			           <option value="">全部</option>
			     <c:forEach var="storeGrade"  items="${storeGrades}">
                 <c:choose>
		           <c:when test="${storeGrade.sgId  == grade_id}">
				     <option  value="${storeGrade.sgId }"  selected="selected">  ${storeGrade.sgName}  </option>
		          </c:when>
		          <c:otherwise>
				   <option  value="${storeGrade.sgId }">  ${storeGrade.sgName}  </option>
		          </c:otherwise>
		         </c:choose>
			  </c:forEach>
		           </select>
				  状态： 
				   -->
				   &nbsp;
				   <!--<select name="joinin_state"  id ="status" >
			           <option value="">全部</option>
			           <s:iterator value="storeJoininStatusMap">
			      	      <option <s:if test="key==joinin_state">selected</s:if> value="<s:property 	value="key"/>"><s:property value="value"/></option>
			           </s:iterator>
		           </select>
				  
					--><input type="submit" style="margin-left:30px;" class="default_button" value="查询">
				</td>
				</tr>
			    </tbody>
			</table>
		</form>
		 
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>代理区域</td>
				<td>授权金额</td>
				<!--<td>返还比例</td>
				--><td>收费及返还</td>
				<td>状态</td>
				<td>操作</td>
			</tr>

			<c:forEach var="bean" items="${sysAgentConfigs}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.areainfo}</td>
					<td><input disabled="disabled" type="text" class="ratio"  value="${bean.agentAmount}"/>&nbsp;&nbsp;元&nbsp;/&nbsp;年 <input type="button" class="realIncomeRatioEdit" onClick="editClick(this)"  value="编辑"/>
						 <input disabled="disabled" type="button" class="ratioSave" value="保存" onClick="saveEdit(this,${bean.agentAmount},1,null)" />
						 <input type="hidden" class="sysAgentConfigClass"    value="${bean.id}" />
						 </td>
					<td>返还年限：<select disabled="disabled" id="year" onchange="yearChange(this.value,${bean.id})">
						<option value="1">1年</option>
						<option value="2">2年</option>
						<option value="3">3年</option>
						<option value="5">5年</option>
						</select>
						 <c:set value="${ fn:split(bean.ratio, ';') }" var="str" />
						 <c:set value="${ fn:split(str[0], ':') }" var="str2" />
						 <c:set value="${ fn:split(str2[1], ',') }" var="str3" />
						返还递增：<input disabled="disabled" class="ratio"  id="returnAmountRatio${bean.id}"" name="returnAmountRatio" value="${str3[0]}" onchange="reAmount(this,${bean.id})" />&nbsp;&nbsp;%&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						授权金额：<input disabled="disabled" class="ratio"  id="agentAmountRatio${bean.id}"" name="agentAmountRatio" value="${str3[1]}" onchange="amount(this,${bean.id})"/>&nbsp;&nbsp;%
					    <input type="button" class="realIncomeRatioEdit" onClick="editClick2(this)"  value="编辑"/>
						 <input disabled="disabled" type="button" class="ratioSave" value="保存" onClick="saveEdit(this,null,2,${bean.id})" />
						 <input type="hidden" id="${bean.id}" class="sysAgentConfigClass"  value="${bean.ratio}" />
					    </td>
					<td>
						<c:if test="${bean.state eq 0}">已关闭</c:if>
						<c:if test="${bean.state eq 1}">已启动</c:if>
					</td>
					<td>&nbsp;&nbsp;
					   <a href="sysAgentConfigAction!viewPage.action?id=${bean.id }">查看</a>&nbsp;&nbsp;
					   <a href="sysAgentConfigAction!retrieveSysAgentConfigById.action?id=${bean.id }">编辑</a>&nbsp;&nbsp;
					   <c:if test="${bean.state eq 0}"><a href="sysAgentConfigAction!delSysAgentConfig.action?id=${bean.id }" onclick="{if(confirm('你真的要开启吗?')){return true;}return false;}">启动</a>&nbsp;&nbsp;</c:if>
					   <c:if test="${bean.state eq 1}"><a href="sysAgentConfigAction!delSysAgentConfig.action?id=${bean.id }" onclick="{if(confirm('你真的要关闭吗?')){return true;}return false;}">关闭</a>&nbsp;&nbsp;</c:if>
					   <a href="sysAgentConfigAction!delSysAgentConfig.action?op=del&id=${bean.id }"
								onclick="{if(confirm('你真的要删除吗?')){return true;}return false;}">删除</a>
					</td>
				</tr>
			</c:forEach>
           <c:if test="${empty sysAgentConfigs}">
			<tbody>
                <tr style="background: rgb(255, 255, 255) none repeat scroll 0% 0%;" class="no_data">
             <td colspan="11">没有符合条件的记录</td>
               </tr>
              </tbody>
            </c:if>
		</table>
		<c:if test="${not empty sysAgentConfigs}">
			<s:form action="sysAgentConfigAction!retrieveAllSysAgentConfigs.action"
					 method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
				<input type="hidden" value="<s:property value='areaInfo' />" name="areaInfo"/>
			</s:form>
		</c:if>
	</body>
</html>