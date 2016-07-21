<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page session="false" %>

<%@include file="./include/header.jsp" %>


<div class="container">


	<table class="table table-stripped">
		<tr>
			<th>글제목</th>
			<td>${vo.title}</td>
		</tr>
		<tr>
			<th>가격</th>
			<td>${vo.price}원</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><c:out value="${vo.content}" escapeXml="false"/></td>			
		</tr>	
	
	</table>

			<c:forEach items="${vo2}" var="vo2" >
			<c:if test="${vo.no==vo2.trade_no}">
				<td><img src="/resources/image/${vo2.image}" width="350" /></td>	
			</c:if>
			</c:forEach>
		</div>

<form action="/trade/${vo.no}" method="post">
	<input type="hidden" id="_method" name="_method" value="delete"/>
	<input type="submit" class="btn btn-primary" value="삭제"/>
</form>


	<form action="/trade/${vo.no}/updateList" method="GET">
	<input type="hidden" id="update_method" name="update_method" value="update"/>
	<input type="submit" class="btn btn-primary" value="수정"/>
</form>


<script>

</script>
	
<%@include file="./include/footer.jsp" %>