<%@page import="tw.com.pcschool.domin.Users"%>
<%@page import="java.util.ArrayList"%>
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
<div>
您確認邀刪除以下資料嗎?<form action="UserController" method="post" onsubmit="return check()">
	<table style="border:2px solid;"rules="all">
<c:forEach items="${requestScope.userlist}" var="users">
	<tr><td><input type="checkbox" name="userid" value="${users.id }"> </td>
	<td align="right">使用者ID:</td><td>${users.id }</td>
	<td align="right">使用者:</td><td>${users.username }</td>
	<td align="right">密碼:</td><td>${users.pass }</td>
	<td align="right">E-mail:</td><td>${users.email }</td>
	<td align="right">生日:</td><td>${users.birthday }</td></tr></c:forEach>
</table><input type="hidden" name="command" value="DELETE">
<input type="submit" value="刪除"></form>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
function check() {
	var chk=[];
	$('input[name="userid"]:checked').each(function() {
		chk.push($(this).val());
	})
	if(chk==""){alert("選項未選取");return false;}
	else{return true;}
}
</script>
</body>
</html>