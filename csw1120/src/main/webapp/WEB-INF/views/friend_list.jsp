<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<%@include file="./include/header.jsp" %>

<script type="text/javascript">
	function onSearch(){
		var friend_name = document.getElementById("friendname").value;
		
		if(friend_name!=""){
			location.href="/friend_list/" + friend_name;
		}else{
			location.href="/friend_list";
		}
	}
</script>

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
<input class="btn btn-info" type="button" value="중고" onclick="location.href='/trade';"/>

	<h3> 친구 목록 </h3>
	
	<input type="text" id="friendname" name="friendname" onkeypress="if(event.keyCode==13) {onSearch();}" />
	<input class="btn btn-info" type="button" value="검색" onclick="onSearch()" />
		
	<table class="table table-striped">
		<thead>
			<th>프로필사진</th>
			<th>이름</th>
		</thead>
		
	<c:forEach items="${friendlist}" var="vo">
	
		<tr>
			<td><img width="100" height="100" src="/resources/image/${vo.thumbnail }"/></td>
			<td><a href="/chat/${vo.email }">${vo.name}</a>
			<input type="hidden" id="friend_email" name="friend_email" value="${vo.email }" /></td>
		</tr>
	
	</c:forEach>
	</table>

	<form name="search_form" method="post" action="/friend_search">
		<input class="btn btn-info" type="submit" value="친구찾기" />
	</form>
</div>

<%@include file="./include/footer.jsp" %>
