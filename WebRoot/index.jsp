<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

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
   <p><a href="actorAction!findFilmActor.action">演员电影关联查询</a></p>
   <p><a href="actorAction!findActorByPage.action?page=1">分页查询演员信息</a></p>
   <p><a href="json.jsp">AJAX分页查询</a></p>
   <p><a href="uploadify.jsp">上传文件uploadify插件</a></p>
   <p><a href="upload.jsp">上传文件基本</a></p>
  </body>
</html>
