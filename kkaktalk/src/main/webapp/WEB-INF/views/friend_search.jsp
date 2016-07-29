<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<%@include file="./include/header.jsp" %>
 
<script type="text/javascript">
	function onSearch(){
		var friendsearch = document.getElementById("friendsearch").value;
		
		if(friendsearch!=""){
			location.href="/friend_search/" + friendsearch;	
		}
	}
</script>
 
<div class="container">
	<h3> 친구 찾기 </h3>
		<input type="text" id="friendsearch" name="friendsearch" onkeypress="if(event.keyCode==13) {onSearch();}" />
		<input class="btn btn-info" type="button" value="찾기" onclick="onSearch()" />
		
	<table class="table table-striped">
		<thead>
			<th>프로필사진</th>
			<th>이름</th>
			<th>친구추가</th>
		</thead>
		
	<c:forEach items="${friendSearch}" var="vo">
	
		<tr>
			<td><img width="100" height="100" src="/resources/image/${vo.thumbnail }"/></td>
			<td>${vo.name}</td>
			<td><input style="width: 60px; height: 30px" class="btn btn-info" type="button" value="추가" onclick="location.href='/friendAdd/${vo.email}';" /></td>
		</tr>
	
	</c:forEach>
	</table>
	<input class="btn btn-info" type="button" value="친구목록" onclick="location.href='/friend_list';" />
</div>

<%@include file="./include/footer.jsp" %>
