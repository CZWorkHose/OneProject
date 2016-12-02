<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
	<script>
	var page = 1;
	
	$(function(){
		
		getData(page); //初始化数据
		
		//上一页
		$("#prePage").click(function(){
			if(page<2){
				page = 1;
			}else{
				page--;
			}
			getData(page);
		});
		
		//下一页
		$("#nextPage").click(function(){
			if(page<=1){
				page = 2;
			}else{
				page++;
			}
			getData(page);
		});
		
		function getData(pageIndex){
			$.ajax({ 
				url:'findActorJson!findActorByPageAJAX.action', 
				type:'post', 		//数据发送方式 
				dataType:'json', 	//接受数据格式 (常用的有html,xml,js,json) 
				data: {
					page : pageIndex
				}, 					//传递的参数
				error: function(){ 	//失败 
					alert('error'); 
				}, 
				success: function(data){ //成功
					$("#actorInfo tr:gt(0)").remove();//移除第二行tr及之后的tr数据
					var viewData = "";
					var dataObj = eval("("+data+")"); //解析成二维数组
					
					$.each(dataObj, function(i, n){	//遍历数组第一层
						
						viewData += "<tr>";
						for(var i=0; i<n.length; i++){	//取数组第二层的值
							viewData += "<td>" + n[i] + "</td>";
						}
						viewData += "</tr>";
					});
					
					$("#actorInfo").append(viewData);  //绑定并显示tr数据
				} 
				}); 
		}
		
	});
	
	
	
	
	</script>
  </head>
  
  <body>
  	<table border="1" id="actorInfo">
  		<tr>
  			<td>演员ID</td>
  			<td>演员姓</td>
  			<td>演员名</td>
  		</tr>
  	</table>
  	<a href="javascript:void(0);" onclick="" id="prePage">上一页</a>
	 | 
	<a href="javascript:void(0);" onclick="" id="nextPage">下一页</a>
  </body>
</html>
