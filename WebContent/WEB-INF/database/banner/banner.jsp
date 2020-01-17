<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.btn-link {
border: none;
    outline: none;
    background: none;
    cursor: pointer;
    color: #0000EE;
    padding: 0;
    text-decoration: underline;
    font-family: inherit;
    font-size: inherit;
}
.向左靠攏{display:inline}
</style>
</head>
<body>
<table><tr><td><form action="Dispatcher" method="post">
<button type="submit" name="a" value="add" class="btn-link">加入使用者</button></form></td>
<td><form action="Dispatcher" method="post">
<button type="submit" name="a" value="find" class="btn-link">查詢使用者</button></form></td>
<td><form action="Dispatcher" method="post">
<button type="submit" name="a" value="list" class="btn-link">列出使用者</button></form>
</td></tr></table>
</body>
</html>