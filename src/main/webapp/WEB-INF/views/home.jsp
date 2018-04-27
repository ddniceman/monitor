<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>  
<h2>文件上传实例</h2>  
  
<form action="http://127.0.0.1:8080/pic/uploatpic" method="post" enctype="multipart/form-data">  
    选择文件:<input type="file" name="file">  
    <input type="submit" value="提交">   
    <img alt="" src="http://127.0.0.1:8080/upload/20180427/1524800087812.png">
</form>  
  
</body> 
</html>
