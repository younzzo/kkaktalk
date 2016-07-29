<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ include file="./include/header.jsp" %>
<h1>
	상품등록
</h1>
<form action="/kkt/trade" method="post" >
	<table>
		<tr>
			<th>타이틀</th>
			<td><input type="text" name="title"/></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><input type="text" name="content"></td>
		</tr>
		<tr>
			<th>email</th>
			<td><input type="text" name="member_email"></td>
		</tr>
		<tr>
			<th>price</th>
			<td><input type="text" name="price"></td>
		</tr>
		<tr>
			<th>category</th>
			<td><input type="text" name="category"></td>
		</tr>
	</table>
	<input type="submit" value="등록">
</form>
<%@ include file="./include/footer.jsp" %>