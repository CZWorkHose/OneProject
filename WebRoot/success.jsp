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
  </head>
  
  <body>
  	<table border="1">
  		<tr>
  			<td>演员ID</td>
  			<td>演员名字</td>
  			<td>出演电影</td>
  		</tr>
  	<s:iterator id="faList" value="#request.filmActorList">
        <tr>
			<td>${faList.actor.actorId}</td>
			<td>${faList.actor.firstName} ${faList.actor.lastName}</td>
			<td>${faList.film.title}</td>
        </tr>
	</s:iterator>
	</table>
  </body>
</html>
