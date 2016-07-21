<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page session="false" %>

<%@include file="./include/header.jsp" %>

	<div class="container">
	<form action="/trade/update" method="POST"  id="frm" name="frm" enctype="multipart/form-data" class="form-horizontal">
				<input type="hidden" id="vo" name="vo" value="${tvo.no}"/>
 				<table class="w3-table w3-bordered w3-striped w3-card-4">
					<tr>
						<th>No.</th>
						<td><input type="text" id="no" name="no" value="${tvo.no}"/></td>
					</tr>
					<tr>
						<th>카테고리</th>
						<td><select id="category" name="category" value="${tvo.category}" >
							<c:if test="${tvo.category=='의류'}">
								<option value="가전제품" >가전제품</option>
								<option value="${tvo.category}" selected="selected">의류</option>
								<option value="기타">기타</option>
							</c:if>
				             <c:if test="${tvo.category=='가전제품'}">
								  <option value="${tvo.category}" selected="selected">가전제품</option>
								  <option value="의류" >의류</option>
								  <option value="기타">기타</option>
							</c:if>
				               <c:if test="${tvo.category=='기타'}">
				                 <option value="가전제품">가전제품</option>
								  <option value="의류" >의류</option>
								  <option value="${tvo.category}" selected="selected">기타</option>
							</c:if>
              				 </select>
              			</td>
					</tr>
					<tr>
						<th>상품가격</th>
						<td><input type="text" id="price" name="price"  value="${tvo.price}" /></td>
					</tr>
					<tr>
						<th>상품명</th>
						<td><input type="text" id="title"  name="title" value="${tvo.title}" /></td>
					</tr>
					<tr>
						<th>상세설명</th>
						<td><textarea rows="10" cols="90" title="내용" id="content" name="content">${tvo.content}</textarea></td>
					</tr>
					<tr>
        <th>첨부파일</th>   
        
        <td> 
			<c:forEach items="${pvo}" var="pvo" >
				<c:if test="${tvo.no==pvo.trade_no}">
				 <form action="/trade/${tvo.no}/${pvo.image_no}" method="post" id="delete_img" name="delete_img">
					${pvo.file_name}
					<input type="hidden" id="image_no" name="image_no" value="${pvo.image_no}"/>
					<input type="button" class="btn btn-primary" value="삭제" onclick="delete_image()"/>
				</form></br>
				</c:if>
			</c:forEach>
			
		 	<div id="addFile" ></div>
		</td>
     </tr>
					</table>
						
						<input type="hidden" id="addFile" name="addFile" value=""/>
      					<input type="submit" class="btn btn-primary" value="수정완료" />
		</form>
		  <input type="button" id="plus" class="btn btn-primary" value="파일추가" onclick="create_addFile()" />
		</div>
<%@include file="./include/footer.jsp" %>
