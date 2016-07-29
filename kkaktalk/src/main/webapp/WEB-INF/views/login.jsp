<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@include file="./include/header.jsp" %>
<%-- 
<% session.removeAttribute("email"); %>
<% session.removeAttribute("pw"); %>
<% session.removeAttribute("phone_num"); %>
<% session.removeAttribute("name"); %>
<% session.removeAttribute("thumbnail"); %>
 --%>
<script type="text/javascript">
	function goLogin(){
		if(document.login_form.login_email.value==""){
			alert("이메일을 입력해주세요.");
			document.login_form.login_email.focus();
		}else if(document.login_form.login_pw.value==""){
			alert("패스워드를 입력해주세요.");
			document.login_form.login_pw.focus();
		}else{
			document.login_form.submit();
		}
	}
	function goJoin(){
		document.join_form.submit();
	}
</script>
<div class="container">
	
	<h3>로그인</h3>
	<form name="login_form" method="post" action="/friend_list">
		이메일 : <input type="text" id="login_email" name="login_email" /><br>
		패스워드 : <input type="password" id="login_pw" name="login_pw" onkeypress="if(event.keyCode==13) {goLogin();}" />
		<input class="btn btn-info" type="button" value="로그인" onclick="goLogin()" /><br>
	</form>
	<form action="/agree" method="POST" name="join_form">
		<input class="btn btn-info" type="button" value="회원가입" onclick="goJoin()" />
	</form>
</div>

<%@include file="./include/footer.jsp" %>
