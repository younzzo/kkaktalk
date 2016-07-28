<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ include file="./include/header.jsp" %>

	<h1>
		상품등록
	</h1>

<div class="container">
<form method="post" action="/kkt/trade/write"  enctype="multipart/form-data">
 	<br>   
    <table id="fileview"  class="table table-striped">
        <tr>
            <td><input name="file" type="file"  multiple="multiple" class="btn btn-default"></td>
        </tr>
     	<tr>
			<th>category</th>
			<td><select id="category" name="category">
					<option  value="의류">의류</option>
					<option  value="가전제품">가전제품</option>
					<option  value="기타">기타</option>
			</select></td>
		</tr> 
        <tr>
			<th>title</th>
			<td><input type="text" name="title"/></td>
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
					<td colspan="2" class="view_text"><textarea rows="15"
							cols="80" title="content" id="content" name="content"></textarea></td>
				</tr>
   
    </table>
    <br/><input type="submit" value="상품등록"  class="btn btn-primary"/>
    
</form>
</div>
<%@ include file="./include/footer.jsp" %>