<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <title>多文件上传示例</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>uploadify/uploadify.css">
	<script src="<%=basePath%>js/jquery-1.9.1.js"></script>
	<script src="<%=basePath%>uploadify/jquery.uploadify.js"></script>
	<!-- <script src="<%=basePath%>uploadify/jquery.uploadify.min.js"></script> -->
    <script type="text/javascript">
    	$(function(){
    		$("#cancelBtn").hide();
    		$("#info").hide();
    		
    		$("#strutsUploadFile").uploadify({
    	        uploader      : 'uploadFile!uploadFile.action',  
    	        method        : 'Post',
    	        swf           : 'uploadify/uploadify.swf',
    	        height        : 30,
    	        width         : 200,
    	        fileExt       : '*.png;*.gif;*.jpg;*.bmp;*.jpeg',
    	        fileDesc      : '图片文件(*.png;*.gif;*.jpg;*.bmp;*.jpeg)',
    	        simUploadLimit : 20,	//批量文件最多999
    	        buttonText    : '选择上传文件',
    	        fileObjName   : 'uploadFile',
    	        auto          : true,	//选择文件并确定后自动上传
    	        multi         : true,   //多文件上传
    	        //限制每次同时上传的文件数量 queueSizeLimit : 1,
    	        removeCompleted : false,  // 设置为true上传后自动删除队列
    	        onUploadSuccess   : function(file,data,response){
    	        	//每次每个文件上传成功后
    	        	$("#" + file.id).find(".data").html(" 上传完成");
    	        },
    	        onQueueComplete : function(data){
    	        	//所有文件都上传完成
    	        	$("#successCount").text(data.uploadsSuccessful);
    	        	$("#errorCount").text(data.uploadsErrored);
    	        	$("#info").show();
    	        },
    	        onFallback    : function (){
    	        	alert("需要安装Flash控件");
    	        }
    		});
    		/**
    			控件在选择文件时，会自动判定同名文件，仅仅是客户端判断，此判断与服务器无关
    			源文件jquery.uploadify.js中有提示信息：Do you want to replace the existing
    			可在源文件中搜索此信息，进行修改，或者注释掉功能模块
    		*/
    	});
    </script>
  
  </head>  
  
  <body>
    <input type="file" name="uploadFile" id="strutsUploadFile"/><br/> 
    <input type="button" id="cancelBtn" onclick="javascript:$('#strutsUploadFile').uploadifyClearQueue()" value="取消上传"/><br />
    <p id="info">文件成功上传<span id="successCount"></span>个，失败<span id="errorCount"></span>个</p>  
  </body>  
</html>  