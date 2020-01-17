<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%-- databasetest --%>
</head>
<body>
<div style="margin:0px auto;">
本網站為資料庫連接測試<p>
<form action="Dispatcher" method="post">
<input type="hidden" name="a" value="login">
帳號：<input type="text" name="name"><br>
密碼：<input type="password" name="pass"><br>
<input type="submit" value="進入"></form>
</div>


<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" >
function accountcheck() {
	/* var dbname=document.getElementsByName("dbname").value; */
	var name=$('input[name="name"]').val();
	var pass=$('input[name="pass"]').val();
	var auth;
	if(name.length==0||pass.length==0){
		alert("帳號或密碼未輸入");
		return(false);
	}else{
		auth=userauth(name,pass);
		if(auth){
			alert("帳號或密碼不正確");
			return(false);
		}else{
			return(true);
		}
	}
}
function userauth(name,pass) {
	var auth;
	$.ajax({
		url : 'UserController',
		method : 'post',
		data : {
			name : name,
			pass : pass,
			command : 'INDEXLOGIN'
		},
		async:false,
		cache:false
	}).done(function(data) {
		if(data=="fail"){
			auth=true;
		}else{
			auth=false;
		}
	}).fail(function(data){
		alert("fail");
	});
	return auth;
}
</script>
</body>
</html>