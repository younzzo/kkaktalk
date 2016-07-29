<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ include file="./include/header.jsp"%>

<style>
h3, h1 {
	text-align: center;
}
</style>
<h1>상품리스트</h1>
<a href="trade/new"><h3>상품등록</h3></a>
<br />
<div class="container">
	<table class="table table-striped">
		<thead>
			<tr>
				<th>No</th>
				<th>Category</th>
				<th>Title</th>
				<th>Price</th>
				<th>Content</th>
				<th>Date</th>
				<th>Image</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="vo"  >
				<tr>
					<td><c:out value="${vo.no}" /></td>
					<td><c:out value="${vo.category}" /></td>
					<td><c:out value="${vo.title}" /></td>
					<td><c:out value="${vo.price}" /></td>
					<td><c:out value="${vo.content}" /></td>
					<td><c:out value="${vo.date}" /></td>
					<c:set var="doneLoop" value="false"/>
					<c:forEach items="${imagelist}" var="vo1" >
						<c:if test="${not doneLoop }">
							<c:if test="${vo.no== vo1.trade_no }">
								<td><img src="resources/image/${vo1.image}"  height="100" width="100"  onclick="location.href='/kkt/trade/${vo.no}'"/></td>
					<c:set var="doneLoop" value="true"/>
							</c:if>
						</c:if>
					</c:forEach>
					<form action="trade/delete" method="POST">
						<input type="hidden" name="no" id="no" value="${vo.no}"/>
						<td><input type="submit" id="btn" name="btn" value="삭제"/></td>
					</form>
				</tr>
				</c:forEach>
		</tbody>
	</table>
</div>
<%@ include file="./include/footer.jsp"%>