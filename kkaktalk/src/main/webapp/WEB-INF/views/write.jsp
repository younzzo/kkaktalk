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
	상품등록
</h1>

	<form action="/trade" method="post"  enctype="multipart/form-data">
 	<table class="w3-table w3-bordered w3-striped w3-card-4">
         <tbody>
            <tr>
               <th>카테고리</th>
               <td><select id="category" name="category">
                     <option value="의류">의류</option>
                     <option value="가전제품">가전제품</option>
                     <option value="기타">기타</option>
               </select></td>
            </tr>
            <tr>
               <th>제목</th>
               <td><input type="text" id="title" name="title" /></td>
            </tr>
            <tr>
               <th>가격</th>
               <td><input type="text" id="price" name="price" /></td>
            </tr>
                    
            <tr>
               <td colspan="2" class="view_text"><textarea rows="15"
                     cols="80" title="내용" id="content" name="content"></textarea></td>
            </tr>
             <tr>
            <td><input name="file" type="file"  multiple="multiple" ></td>
        </tr>
         </tbody>
      </table>
      <input type="submit" value="상품등록" />
   
	</form>
</div>
<%@include file="./include/footer.jsp" %>
</body>

</html>
