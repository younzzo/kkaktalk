<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page session="false" %>

<%@include file="./include/header.jsp" %>
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<div class="w3-main">

<header class="w3-container">
<h1 style="text-align:center">중고나라 </h1>
	<a href="/trade/new"><button class="w3-btn w3-white" style="margin-left:110px" ><i class="fa fa-plus w3-margin-right"></i>상품등록</button></a>
         
      <form id="form2" class="w3-form w3-margin-bottom" action="/trade/searchList" style="float:left" method="get">
        <div class="w3-dropdown-click" style="text-align:center">
            <select id="keyfield" name="keyfield" size="1">
            <option value="전체" id="all" name="all">전체</option>
                <option value="의류" id="clothes" name="clothes"> 의류 </option>
                <option value=가전제품 id="DAppliance" name="DAppliance"> 가전제품 </option>
                <option value="기타" id="etc" name="etc" > 기타 </option>
            </select>
                 <input type="text" size="16" placeholder="전체"  name="keyword" value="${keyword}"/>              
   				 <button class="w3-btn" onClick="goSearch()" value="검색"><i class="fa fa-search" ></i></button>
   		  </form>		 
          
      </div>
    </div>
</header>
	
	 <!-- First Photo Grid-->
<c:forEach items="${trade_list }" var="trade_list" >
  <div class="w3-row-padding" style="float:left" >
    <div class="w3-third w3-container w3-margin-bottom" style="float:left">

		<c:set var="doneLoop" value="false"/>
			<c:forEach items="${uploadFileList}" var="vo1" >
				<c:if test="${not doneLoop }">
			 	<c:if test="${trade_list.no==vo1.trade_no}"> 
    			<a href="/trade/${trade_list.no}"><img src="/resources/image/${vo1.image}" alt="Norway" style="width:330" class="w3-hover-opacity"></a>
      	<c:set var="doneLoop" value="true"/>
      			</c:if>
      			</c:if>
      	</c:forEach>
      
      <div class="w3-container w3-white" >
      </br>
        <p><b>${trade_list.title}</b></p>
        <p>${trade_list.price}원</p>
      </div>
    </div>
  </div>
 </c:forEach>


<%@include file="./include/footer.jsp" %>

