<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="top" class="devTest.TopBean" scope="request" />
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<title>TOP</title>
	</head>
	<body>
		ログインが成功しました!
	<p><%=top.getName() %> さん、ようこそ！</p>
	</body>
</html>