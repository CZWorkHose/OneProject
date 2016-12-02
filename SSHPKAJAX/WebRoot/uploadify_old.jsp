<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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

<link rel="stylesheet" type="text/css"
	href="<%=basePath%>js/uploadify/uploadify.css">

<script src="<%=basePath%>js/jquery-1.9.1.js"></script>
<script src="<%=basePath%>js/uploadify/jquery.uploadify.js"></script>
<script src="<%=basePath%>js/uploadify/jquery.uploadify.min.js"></script>

<script>
     $(function(){
 		$("#img_file").uploadify({
 			auto:false,		//是否自动上传
			height: 30,  
          	buttonText:'选择图片',
			cancelImage:'<%=basePath%>img/uploadify/uploadify-cancel.png',
			swf : '<%=basePath%>js/uploadify/uploadify.swf',
			 	// expressInstall:'js/uploadify/expressInstall.swf',
			uploader : '<%=basePath%>json/fileUploadAction.action', //后台处理上传文件的action 
			width : 120,
			'multi' : true, //设置为true将允许多文件上传 
			'filesSelected' : '4',
			queueID : 'uploadfileQueue',
			fileObjName : 'img_file', //与后台Action中file属性一样
			/*
			formData:{ //附带值       
							'userid':'111',
							'username':'tom', 
						   'rnd':'111'
						},
			 */
			fileTypeDesc : '上传文件支持的文件格式:jpg,jpge,gif,png',
			fileTypeExts : '*.jpg;*.jpge;*.gif;*.png',//*.jpg;*.jpge;*.gif;*.png
			queueSizeLimit : 4,//只能一次上传4张图片 
			// simUploadLimit:1,//一次可以上传1个文件
			fileSizeLimit : '2097152',//上传文件最大值   单位为字节   2M
			//返回一个错误，选择文件的时候触发
			onSelectError : function(file, errorCode, errorMsg) {
				switch (errorCode) {
				case -100:
					alert("上传的文件数量已经超出系统限制的4个文件！");
					break;
				case -110:
					alert("文件 [" + file.name + "] 大小超出系统限制的2M大小！");
					break;
				case -120:
					alert("文件 [" + file.name + "] 大小异常！");
					break;
				case -130:
					alert("文件 [" + file.name + "] 类型不正确！");
					break;
				}
			}, //
			//上传到服务器，服务器返回相应信息到data里
			onUploadSuccess : function(file, data, response) {
				var objs = eval('(' + data + ')');
				var phtml = "<span><img style='width:150;height:150' src='/uploads/" + objs.filename + "'></span>";
				if ($("#imgs span").length == 0) {
					$("#imgs").html(phtml);
				} else {
					$("#imgs span:last").after(phtml);
				}
			},
			onSelect : function(file) {
				//alert(file.name);         
			},
			//removeCompleted:true,//上传的文件进度条是否消失
			requeueErrors : false,
			// removeTimeout:2,//进度条消失的时间，默认为3
			progressData : "percentage",//显示上传的百分比
			onUploadError : function(file, errorCode, errorMsg,
					errorString, swfuploadifyQueue) { //这里是取消的时候发生  
				// $("#dialog-message").html(errorString);  
			}
		});

	});

	//上传文件
	function myUpload() {
		$("#img_file").uploadify('upload', '*');
	}
</script>

</head>

<body>
	<form action="" method="post">
		<input type="file" name="img_file" id="img_file">
		<div id="uploadfileQueue"></div>
		<div id="imgs"></div>
		<div id="dialog-message"></div>
	</form>

	<p>
		<a href="javascript:void(0);" onclick="myUpload()">上传</a> <a
			href="javascript:$('#img_file').uploadify('cancel')">取消上传</a>
	</p>
</body>
</html>
