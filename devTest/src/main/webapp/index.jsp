<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
//リクエストスコープからのデータの取得
String id = (String)request.getAttribute("id");
String pass = (String)request.getAttribute("pass");
String errMessage = (String)request.getAttribute("errMessage");
String isShowEMStyle = "";
 if(errMessage == null || errMessage.length() == 0){
	 isShowEMStyle = "style='display:none;'";
 }else{
	 isShowEMStyle = "style='color:red;'";
 }
 if(pass == null || pass.length() == 0){
	 pass = "";
 }
 if(id == null || id.length() == 0){
	 id = "";
 }
%>
 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ログイン画面</title>
	</head>
	<body>
		<div <%=isShowEMStyle%>><%= errMessage%></div>
		<div></div>
		<form action="LoginServlet" method="post">
			ユーザーID：<input type="text" name="id" value="<%=id%>"  required><br>
			パスワード：<input type="password" name="pass" value="<%=pass%>" required><br>
			<input type="submit" value="ログイン"><br>
		</form>
	</body>
</html>