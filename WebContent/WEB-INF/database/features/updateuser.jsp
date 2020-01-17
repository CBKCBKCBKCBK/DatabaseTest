<%@page import="tw.com.pcschool.domin.Users"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
資料修改<form action="UserController" method="post" onsubmit="return updateuser()">
	<table style="border:2px solid;"rules="all">
<c:forEach items="${requestScope.userlist }" var="users">
<tr><td align="right">使用者ID:</td><td>${users.id }<input type="hidden" name="userid" value="${users.id }"></td>
	<td align="right">使用者:</td><td><input type="text" name="username" value="${users.username }" style="width: 50px;"></td>
	<td align="right">密碼:</td><td><input type="text" name="pass" value="${users.pass }" style="width: 50px;"></td>
	<td align="right">E-mail:</td><td><input type="text" name="email" value="${users.email }" style="width: 100px;"></td>
	<td align="right">生日:</td><td><input type="text" name="birthday" value="${users.birthday }" style="width: 100px;"></td></tr>
</c:forEach>
</table><input type="hidden" name="command" value="UPDATE">
<input type="submit" value="修改"></form>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
function updateuser() {
	var chk=[];
	var flagarr=[];
	var message="";
	$('input[name="userid"]').each(function() {
		chk.push($(this).val());
	})
	for(i=0;i<chk.length;i++){
		var name=$('input[name="username"]')[i].value;
		var pass=$('input[name="pass"]')[i].value;
		var email=$('input[name="email"]')[i].value;
		var birthday=$('input[name="birthday"]')[i].value;
		var flag=[namefilter(name,pass),emailfilter(email),datefilter(birthday)];
		var returnflag;
		if(flag[0]==false||flag[1]==false||flag[2]==false){
			returnflag=false;}else{returnflag=true;}
		if(returnflag==false){
			message+=flagmessage(flag,chk[i]);	//訊息還未輸出
			flagarr.push(chk[i]);	//flagarr為fail的項目
		}
	}
	var finalflag=true;
	for(var e in flagarr){
		if(e==false){
			finalflag=false;
		}
	}
	if(finalflag==false){
		alert(message);
		return false;
	}else{
		return true;
	}
}
function namefilter(name,pass) {
	var regex=/^[A-Za-z][A-Za-z0-9_]*$/;
	if(!regex.test(name)||!regex.test(pass)){return false;}else{return true;}
}
function emailfilter(email) {
	var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if(!regex.test(email)) {return false;}else{return true;}
}
function datefilter(date) {
	var regex=/^[1-9]\d{3}-([1-9]|0[1-9]|1[0-2])-([1-9]|0[1-9]|[1-2][0-9]|3[0-1])$/;
	if(!regex.test(date)){return false;}else{return true;}
}
function flagmessage(flag,chk) {
	var alerttips="";
	alerttips+="使用者ID "+chk+" :\n";
	if(flag[0]==false){alerttips+="帳號或密碼不能有標點符號,首碼不能為數字\n";}
	if(flag[1]==false){alerttips+="email格式不正確\n";}
	if(flag[2]==false){alerttips+="日期格式應該為yyyy-mm-dd\n\(yyyy為年,mm為月,dd為日\)\n\n";}
	return alerttips;
}
</script>
</body>
</html>