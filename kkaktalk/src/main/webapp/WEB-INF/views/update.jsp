<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page session="false" %>
<%@include file="./include/header.jsp" %>

<html>

<head>
	<title>중고나라</title>
</head>
<body>

<div class="container">

<h1>
	상품 수정
</h1>

	<form action="/trade/update" method="post" id="frm" name="frm" enctype="multipart/form-data">
	<input type="hidden" id="no" name="no" value="${vo.no }">
 	<table class="w3-table w3-bordered w3-striped w3-card-4">
         <tbody>
         
           <tr>
               <th>글 번호</th>
               <td>${vo.no}
              </td>
            </tr>
            
            <tr>
               <th>카테고리</th>
               <td><select id="category" name="category" value="${vo.category}" selected>
                     <option value="${vo.category}">의류</option>
                     <option value="${vo.category}">가전제품</option>
                     <option value="${vo.category}">기타</option>
               </select></td>
            </tr>
                        
            <tr>
               <th>제목</th>
               <td><input type="text" id="title" name="title" value="${vo.title}"/></td>
            </tr>
            <tr>
               <th>가격</th>
               <td><input type="text" id="price" name="price" value="${vo.price}"/></td>
            </tr>
                    
            <tr>
               <td colspan="2" class="view_text"><textarea rows="15"
                     cols="80" title="내용" id="content" name="content" >${vo.content}</textarea></td>
            </tr>
      <!--        <tr>
            <td><input name="file" type="file" multiple="multiple" ></td>
        </tr> -->
  
    <tr>
        <th>첨부파일</th>   
        
        <td> 
			<c:forEach items="${vo2}" var="vo2" >
				<c:if test="${vo.no==vo2.trade_no}">
				 <form action="/trade/${vo.no}/${vo2.image_no}" method="post" id="delete_img" name="delete_img">
				${vo2.image}
				<input type="hidden" id="image_no" name="image_no" value="${vo2.image_no}"/>
				<!--  <input type="hidden" id="no" name="no" value="${vo2.trade_no}"/>-->
				<input type="button" class="btn btn-primary" value="삭제" onclick="delete_image()"/></form></br>
				</c:if>
			</c:forEach>
			
		 	<div id="addFile" ></div>
		</td>
		
     </tr>
    
         </tbody>
      </table>
      <input type="hidden" id="no" name="no" value="${vo.no }"/>
      <input type="hidden" id="addFile" name="addFile" value=""/>
      <input type="submit" class="btn btn-primary" value="수정완료" />
	</form>
	  <input type="button" id="plus" class="btn btn-primary" value="파일추가" onclick="create_addFile()" />
	 
	  
</div>
<%@include file="./include/footer.jsp" %>
</body>

</html>
