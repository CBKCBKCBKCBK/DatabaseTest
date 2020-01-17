<%@page import="java.util.ArrayList"%>
<%@page import="tw.com.pcschool.domin.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<header style="height: 40px;overflow: hidden;">
<jsp:include page="../banner/banner.jsp"></jsp:include></header>
<div><form action="UserController" method="post">
要搜尋的使用者ID:<input type="text" name="idsearch">或是使用者資料:<input type="text" name="datasearch">
<input type="submit" value="搜尋">
<input type="hidden" name="command" value="READ">
</form></div>
<div>
您查詢的使用者資料如下<form action="UserController" method="post">
	<table style="border:2px solid;"rules="all">
<c:forEach items="${requestScope.userlist }" var="users">
<tr><td><input type="checkbox" name="userid" value="${users.id }"> </td>
	<td align="right">使用者ID:</td><td>${users.id }</td>
	<td align="right">使用者:</td><td>${users.username }</td>
	<td align="right">密碼:</td><td>${users.pass }</td>
	<td align="right">E-mail:</td><td>${users.email }</td>
	<td align="right">生日:</td><td>${users.birthday }</td></tr>
</c:forEach>
</table><input type="submit" name="submitfix" value="修改">
<input type="submit" name="submitdel" value="刪除"></form>
</div>
</body>
</html>