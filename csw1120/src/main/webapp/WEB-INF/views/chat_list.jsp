<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<%@include file="./include/header.jsp" %>

<div class="container">
	<h3> 내프로필 </h3>
	<table>
		<c:forEach items="${memberinfo }" var="vo">
			<tr>
				<td><img width="100" height="100" src="/resources/image/${vo.thumbnail }"/></td>
			</tr>
			<tr>
				<td>이메일 : ${vo.email }</td>
			</tr>
			<tr>
				<td>이름 : ${vo.name }</td>
			</tr>
			<tr>
				<td>패스워드 : ${vo.pw }</td>
			</tr>
		</c:forEach>
	</table>
	
	<input class="btn btn-info" type="button" value="프로필수정" onclick="location.href='/memberUpdate';" />
	<input class="btn btn-info" type="submit" value="로그아웃" onclick="location.href='/logout';" /><br><br>

<input class="btn btn-info" type="button" value="친구" onclick="location.href='/friend_list';" />
<input class="btn btn-info" type="button" value="채팅" onclick="location.href='/chat_list';" />
<input class="btn btn-info" type="button" value="중고"  onclick="location.href='/trade';" />

	<h3> 채팅 목록 </h3>
	
	<table class="table table-striped">
		<thead>
			<th>채팅자사진</th>
			<th>채팅자</th>
			<th>내용</th>
		</thead>
		
	<c:forEach items="${chatList}" var="vo">

		<tr>
			<td><img width="100" height="100" src="/resources/image/${vo.thumbnail }"/></td>
			<td>${vo.name}</td>
			<td><a href="/chat/${vo.email }">${vo.msg}</a></td>
		</tr>
	
	</c:forEach>
	</table>
</div>

<%@include file="./include/footer.jsp" %>
