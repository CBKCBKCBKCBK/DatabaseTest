<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page isErrorPage="true" %>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="refresh" content="6;url=Dispatcher?a=add">
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
日期格式不正確，5秒後返回加入使用者...<br>
<form action="Dispatcher" method="post">
<button type="submit" name="a" value="add" class="btn-link">點此處立即返回加入使用者</button></form>
<script type="text/javascript">
setTimeout(function() {	document.forms[0].submit();	}, 5000);
</script>
</body>
</html>