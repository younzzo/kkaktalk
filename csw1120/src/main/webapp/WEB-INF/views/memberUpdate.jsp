<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<%@include file="./include/header.jsp" %>

<script type="text/javascript">
	function previewImage(targetObj, previewId) {
		var preview = document.getElementById(previewId); //div id   
		var ua = window.navigator.userAgent;
		if (ua.indexOf("MSIE") > -1) { //ie일때
			targetObj.select();
			try {
				var src = document.selection.createRange().text; // get file full path 
				var ie_preview_error = document.getElementById("ie_preview_error_" + previewId);
				if (ie_preview_error) {
					preview.removeChild(ie_preview_error); //error가 있으면 delete
				}
				var img = document.getElementById(previewId); //이미지가 뿌려질 곳
				img.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"
				+ src + "', sizingMethod='scale')"; //이미지 로딩, sizingMethod는 div에 맞춰서 사이즈를 자동조절 하는 역할
	     
			} catch (e) {
				if (!document.getElementById("ie_preview_error_" + previewId)) {
					var info = document.createElement("<p>");
					info.id = "ie_preview_error_" + previewId;
					info.innerHTML = "a";
					preview.insertBefore(info, null);
				}
			}
		}else { //ie가 아닐때
			var files = targetObj.files;
			for ( var i = 0; i < files.length; i++) {
				var file = files[i];
				var imageType = /image.*/; //이미지 파일일경우만.. 뿌려준다.
	         
				if (!file.type.match(imageType)){
					continue;
				}
				var prevImg = document.getElementById("prev_" + previewId); //이전에 미리보기가 있다면 삭제
	   
				if (prevImg) {
					preview.removeChild(prevImg);
				}
				var img = document.createElement("img"); //크롬은 div에 이미지가 뿌려지지 않는다. 그래서 자식Element를 만든다.
	         
				img.id = "prev_" + previewId;
				img.classList.add("obj");
				img.file = file;
				img.style.width = '100px'; //기본설정된 div의 안에 뿌려지는 효과를 주기 위해서 div크기와 같은 크기를 지정해준다.
				img.style.height = '100px';
	         
				preview.appendChild(img);
				if (window.FileReader) { // FireFox, Chrome, Opera 확인.
					var reader = new FileReader();
					reader.onloadend = (function(aImg) {
						return function(e) {
						aImg.src = e.target.result;
						};
					})(img);
	               
				reader.readAsDataURL(file);
	     
				} else { // safari is not supported FileReader
					//alert('not supported FileReader');
					if (!document.getElementById("sfr_preview_error_" + previewId)) {
						var info = document.createElement("p");
						info.id = "sfr_preview_error_" + previewId;
						info.innerHTML = "not supported FileReader";
						preview.insertBefore(info, null);
					}
				}
			}
		}
	}
	
	function onChange(){
		document.getElementById("image").remove();
	}
	
</script> 

<div class="container">

	<h1>
		프로필수정
	</h1>
	
	<form name="join_form" action="/memberUpdate" method="POST" enctype="multipart/form-data">
		<c:forEach items="${memberinfo }" var="vo">
			<div id='previewId' style='width:100px; height: 100px;'><img id="image" width="100" height="100" src="/resources/image/${vo.thumbnail }"/></div>
			<input type="file" id=file name="file" onchange="previewImage(this, 'previewId')" style="width:76px;height:25px;margin:0px;filter:alpha(opacity=0);cursor:pointer;" onclick="onChange()" /><br>
			이메일 : ${vo.email } <input type="hidden" id="email" name="email" value="${vo.email }" /><br>
			패스워드 : <input type="password" id="pw" name="pw" value="${vo.pw }" /><br>
			폰번호 : <input type="text" id="phone_num" name="phone_num" value="${vo.phone_num }" /><br>
			이름 : <input type="text" id="name" name="name" value="${vo.name }" /><br>
			<input class="btn btn-info" type="submit" value="수정" />
		</c:forEach>
	</form>
</div>

<%@include file="./include/footer.jsp" %>

