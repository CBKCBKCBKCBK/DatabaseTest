<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page isErrorPage="true" %>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="refresh" content="5;url=${pageContext.request.contextPath }/index.jsp">
<title>Insert title here</title>
</head>
<body>
資料庫不正確，5秒後返回登入畫面...<br>
<a href="${pageContext.request.contextPath }index.jsp">點此處立即返回登入畫面</a>
</body>
</html>