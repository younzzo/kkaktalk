<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page session="false" %>

<%@include file="./include/header.jsp" %>


<div class="container">

<div class="panel-heading">
	<head></head>

</div>

<h1>거래하기</h1>

<div id="container">

	<form id="ee">

		<input type="text" id="productDesc" name="productDesc" placeholder="상품설명"/>
		<input type="text" id="amount" name="amount" placeholder="금액을 입력하세요" />
		<input type="hidden" id="orderNo" name="orderNo" value="2015072012211"/>
		<input type="hidden" id="apiKey" name="apiKey" value="sk_test_apikey1234567890a"/>
		<input type="button" id="pass" name="pass" onclick="escrowStart()" value="컨트롤러로 넘겨서"/>
	</form>
 </div>

	
<%@include file="./include/footer.jsp" %>