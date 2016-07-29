<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<%@ include file="./include/header.jsp" %>
<div class="container">
<table class="table table-striped">

	<tr>
		<th>No</th>
		<td><c:out value="${vo.no}"/></td>
	</tr>
	<tr>
		<th>Title</th>
		<td><c:out value="${vo.category}"/></td>
	</tr>
	<tr>
		<th>Content</th>
		<td><c:out value="${vo.title}"/></td>
	</tr>
	<tr>
		<th>Name</th>
		<td><c:out value="${vo.price}"/></td>
	</tr>
	<tr>
		<th>Content</th>
		<td><c:out value="${vo.content}"/></td>
	</tr>
	<tr>
	<th>Date</th>
		<td><c:out value="${vo.date}"/></td>
	</tr>
	<tr>
	<th>Image</th>
		<c:forEach items="${imagelist}" var="vo1" >
			<c:if test="${vo.no== vo1.trade_no }">
				<td><img src="/resources/image/${vo1.image}"  height="500" width="500" /></td>
			</c:if>
		</c:forEach>
	</tr>
</table>
</div>
<%@ include file="./include/footer.jsp" %>
