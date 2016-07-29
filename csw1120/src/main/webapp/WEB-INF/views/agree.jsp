<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<html>
<head>
	<title>회원가입</title>
</head>
<body>
<h1>
	약관동의
</h1>

<script type="text/javascript">
	function onAgree(){
		var agree1 = document.getElementById("agree1").checked;
		var agree2 = document.getElementById("agree2").checked;
		
		if(agree1+agree2=="2"){
			document.agree_form.submit();
		}
	}
</script>

<form name="agree_form" action="/join" method="GET" enctype="multipart/form-data" >
	<textarea rows="10" cols="100">이용약관</textarea><br>
	<input type="checkbox" id="agree1" onclick="onAgree()" /><label for="agree1">동의합니다.</label><br>
	
	<textarea rows="10" cols="100">개인정보 수집 및 이용안내</textarea><br>
	<input type="checkbox" id="agree2" onclick="onAgree()" /><label for="agree2">동의하고 계속 진행합니다.</label>
</form>

</body>
</html>
