<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>上传图片</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="../js/common/jquery.min.js"></script>
</head>
<body>
	<div>
		<a href="/main/mainManager.do">返回首页</a>
	</div>
	<button id = "request_body">演示@RequestBody</button>

	<!--注意当form表单中的   enctype="multipart/form-data" 时需要在springmvc配置文件上传所要对应的Bean,否则传参数失败,或
	者只能用servlet来解决流文件-->
	"普通的表单提交方式"
	<form id="uploadForm" action="upload/uploadImage.do" method="post" enctype="multipart/form-data">
		请选择上传的图片或文件:<input type="file" name="image" /> 文件名: &nbsp 
		<input type="text"> <input type="submit" value="上传" />
	</form>
	
	"演示通过一个表单外的按钮事件来提交表单"
	<button id="submit">提交上传图片</button><br/><br/>
	
	<!--上传成功图片的回显
	注意: src="/image/1453561537207.jpg"
	其中,image为我在tomcat中定义的虚拟文件服务器的访问路径,真实路径在本地硬盘,可以查看
	1453561537207.jpg 为图片的名字 -->
	<img alt="" src="/image/${imageName}">


	<div>下面是一个图片</div>
	<img src="/file/001.jpg"/>
	"演示异步上传图片,可以有返回值"
	<button id="upload2">异步上传</button>
	
	

</body>
<script type="text/javascript">
<!--通过按钮的方式,提交上传图片  -->
$("#submit").click(function(){
	$("#uploadForm").submit();
});

/*异步上传,带返回值  */
 $("#upload2").click(function(){
	 var formData = new FormData($("#uploadForm")[0]);
	 $.ajax({
         url: '/upload/uploadImage2.do' ,
         type: 'POST',
         data: formData,
         async: false,
         cache: false,
         contentType: false,
         processData: false,
         success: function (returndata) {
             alert("success")
             alert(returndata);
         },
         error: function (returndata) {
             alert(returndata);
         }
     });
 });
	
	$("#request_body").click(function () {
		var saveDataAry=[];
		var data1={"userName":"test","password":"gz"};
		var data2={"userName":"ququ","password":"gr"};
		saveDataAry.push(data1);
		saveDataAry.push(data2);
		$.ajax({
			type:"POST",
			url:"/user/insert_user",
			dataType:"json",
			contentType:"application/json",
			data:JSON.stringify(data1),
			success:function(data){
				alert(data);
			}
		});
	});
</script>
	
</html>
