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
		</style>
		<script type="text/javascript" src="${ctx}/js/echarts.js"></script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 统计 - 会员（商家）总数
			</div>
			<div class="ropt">
				<!-- <a href="">新增</a> -->
			</div>
			<div class="clear"></div>
		</div>
		
		<table class="listTable2">
			<input type="hidden" name="sellerMemberNum" id="sellerMemberNum" value="${sellerMemberNum }" />
			<input type="hidden" name="regularMemberNum" id="regularMemberNum" value="${regularMemberNum }" />		
			<tr>	
				<td style="text-align: center;font-size: 20px;">
					所有会员总数：<strong  style="color:red;">${memberAmount }</strong>	
				</td>
				<td style="text-align: center;font-size: 20px;">
					普通会员总数：<strong  style="color:red;">${regularMemberNum }</strong>
				</td>
				<td style="text-align: center;font-size: 20px;">
					商家会员总数：<strong  style="color:red;">${sellerMemberNum }</strong>
				</td>
			</tr>
		</table>
		
		<div id="main" style="width: 100%;height:420px;">
			
		</div>
		
		<div id="center" style="width:100%;height:420px;margin-top: 20px;display: none;">
			
		</div>
		<c:forEach items="${memberAdd }" var="m">
			<input type="hidden" name="memberAdd" value="${m }" />
		</c:forEach>
		
		<c:forEach items="${regularMemberAdd }" var="r">
			<input type="hidden" name="regularMemberAdd" value="${r }" />
		</c:forEach>
		
		<c:forEach items="${sellerAdd }" var="s">
			<input type="hidden" name="sellerAdd" value="${s }" />
		</c:forEach>
		
		<script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        var sellerMemberNum = $("#sellerMemberNum").val();
	 	var regularMemberNum = $("#regularMemberNum").val();
	 	
        // 指定图表的配置项和数据
        var option = {
        		 title : {
        		        text: '普通会员和商家比例',
        		        subtext: '',
        		        x:'center'
        		    },
        		    tooltip : {
        		        trigger: 'item',
        		        formatter: "{a} <br/>{b} : {c} ({d}%)"
        		    },
        		    legend: {
        		        orient: 'vertical',
        		        left: 'left',
        		        data: ['普通会员','商家']
        		    },
        		    series : [
        		        {
        		            name: '会员',
        		            type: 'pie',
        		            radius : '55%',
        		            center: ['50%', '60%'],
        		            data:[
        		                {value:regularMemberNum, name:'普通会员'},
        		                {value:sellerMemberNum, name:'商家'},
        		            ],
        		            itemStyle: {
        		                emphasis: {
        		                    shadowBlur: 10,
        		                    shadowOffsetX: 0,
        		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
        		                }
        		            }
        		        }
        		    ]
        };
     	// 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);  
        
        
     	

        var chart = echarts.init(document.getElementById('center'));
        var myDate=new Date();
		//设置日期，当前日期的前七天
		var myDate = new Date(); //获取今天日期
		myDate.setDate(myDate.getDate() - 30);
		var dateArray = []; 
		var dateTemp; 
		var flag = 1; 
		for (var i = 0; i < 30; i++) {
		    dateTemp =myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate();
		    dateArray.push(dateTemp);
		    myDate.setDate(myDate.getDate() + flag);
		}
		var memberAdd = document.getElementsByName("memberAdd");
		var regularMemberAdd = document.getElementsByName("regularMemberAdd");
		var sellerAdd = document.getElementsByName("sellerAdd");
		var memberArray = [];
		var regularArray = [];
		var sellerArray = [];
		for(var i=0;i<memberAdd.length;i++)
        {
			memberArray.push(memberAdd[i].value);
        }
		for(var i=0;i<regularMemberAdd.length;i++)
        {
			regularArray.push(regularMemberAdd[i].value);
        }
		for(var i=0;i<sellerAdd.length;i++)
        {
			sellerArray.push(sellerAdd[i].value);
        }
		
        var option1 = {
        	    title : {
        	        text: '近一个月会员增长数据',
        	    },
        	    tooltip : {
        	        trigger: 'axis'
        	    },
        	    legend: {
        	        data:['增长会员总数','增长普通会员数','增长商家会员数']
        	    },
        	    toolbox: {
        	        show : true,
        	        feature : {
        	            dataView : {show: true, readOnly: false},
        	            magicType : {show: true, type: ['line', 'bar']},
        	            restore : {show: true},
        	            saveAsImage : {show: true}
        	        }
        	    },
        	    calculable : true,
        	    xAxis : [
        	        {
        	            type : 'category',
        	            data : dateArray
        	        }
        	    ],
        	    yAxis : [
        	        {
        	            type : 'value'
        	        }
        	    ],
        	    series : [
        	        {
        	            name:'增长会员总数',
        	            type:'bar',
        	            data:memberArray,
        	          
        	        },
        	        {
        	            name:'增长普通会员数',
        	            type:'bar',
        	            data:regularArray,
        	           
        	        },
        	         {
        	            name:'增长商家会员数',
        	            type:'bar',
        	            data:sellerArray,
        	           
        	        }
        	    ]
        	};

			chart.setOption(option1);
    </script>
		
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>会员名称</td>
				<td>会员昵称</td>
				<td>会员性别</td>
				<td>会员类型</td>
			</tr>
			
			<c:forEach var="bean" items="${pager.resultList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.name }</td>
					<td>${bean.nickName }</td>
					<td>
						<c:choose>
							<c:when test="${bean.sex eq 0}">未知</c:when>
							<c:when test="${bean.sex eq 1}">男</c:when>
							<c:when test="${bean.sex eq 2}">女</c:when>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${bean.type eq 0}">普通用户</c:when>
							<c:when test="${bean.type eq 1}">商家</c:when>
						</c:choose>
					</td>
				</tr>
			</c:forEach>

		</table>
		
		<c:if test="${not empty pager.resultList}">
			<s:form action="countInfoAction!countMemberInfoAmount.action?op=countMembers"
					namespace="/subsystem" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>

	</body>
</html>