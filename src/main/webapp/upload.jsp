<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
    <title>Upload File</title>
</head>

<body>
<form action="/App/uploadFile.action" method="post" enctype="multipart/form-data">
    <!--文件域-->
    <input type="file" name="file" /> <input type="submit" value="上传">
</form>
</body>
</html>