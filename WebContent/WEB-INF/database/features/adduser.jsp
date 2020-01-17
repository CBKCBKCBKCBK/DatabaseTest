<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
新增使用者
<form action="UserController" method="post" onsubmit="return adduserscript()"><table>
<tr><td align="right">帳號:</td><td><input type="text" name="username"></td></tr>
<tr><td align="right">密碼:</td><td><input type="password" name="pass"></td></tr>
<tr><td align="right">E-mail:</td><td><input type="email" name="email"></td></tr>
<tr><td align="right">生日:</td><td><input type="date" name="birthday"></td></tr>
<tr><td colspan="2"> 
日期格式為yyyy-mm-dd<br>(y是西元年,m是月份,要二位數,d是日期,<br>要二位數,一位數須在前面補0)</td></tr>
<tr><td colspan="2" align="right"><input type="submit" value="新增"></td></tr>
</table><input type="hidden" name="command" value="ADD"> </form>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
function adduserscript() {
	var name=$('input[name="username"]').val();
	var pass=$('input[name="pass"]').val();
	var email=$('input[name="email"]').val();
	var birthday=$('input[name="birthday"]').val();
	var flag=[namefilter(name,pass),emailfilter(email),datefilter(birthday)];
	var returnflag=flagfilter(flag);
	return returnflag;
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
function flagfilter(flag) {
	var alerttips="";
	if(flag[0]==false){alerttips+="帳號或密碼不能有標點符號,首碼不能為數字\n";}
	if(flag[1]==false){alerttips+="email格式不正確\n";}
	if(flag[2]==false){alerttips+="日期格式應該為yyyy-mm-dd\n\(yyyy為年,mm為月,dd為日\)";}
	if(alerttips!=""){alert(alerttips);}
	if(flag[0]==false||flag[1]==false||flag[2]==false){
		return false;}else{return true;}
}
</script>
</body>
</html>