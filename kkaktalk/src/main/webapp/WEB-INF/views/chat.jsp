<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@include file="./include/header.jsp" %>

<script type="text/javascript">

	var wsUrl = "ws://192.168.10.60/chatting/${chattingCheck.chatting_room_no}";
	var ws;
	var email = "<%=(String)session.getAttribute("login_email")%>";
	
	$(document).ready(function(){
		//alert(wsUrl);
		ws = new WebSocket(wsUrl);
		
		ws.onopen = function(evt){
			//alert(evt);
			onOpen(evt);
		};
		
		ws.onmessage = function(evt){
			onMessage(evt);
		};
		
		ws.onerror = function(evt){
			onError(evt);
		};
	});
	/* 
	function init(){
		ws = new WebSocket(wsUrl);
		
		ws.onopen = function(evt){
			onOpen(evt);
		};
		
		ws.onmessage = function(evt){
			onMessage(evt);
		};
		
		ws.onerror = function(evt){
			onError(evt);
		};
	}
	 */
	function onOpen(evt){
		var messageWindow = document.getElementById("messageWindow");
		showMessage("연결", "center");
		//alert("test1");
		<c:forEach items="${msgList }" var="vo">
			if("${vo.user_email}" == email){
				showMessage("${vo.name} : ${vo.msg}", "right");
			}else{
				showMessage("${vo.name} : ${vo.msg}", "left");
			}
			//alert("test2");
		</c:forEach>
		messageWindow.scrollTop = messageWindow.scrollHeight;
		document.msg_form.msg.focus();
	}
	
	function onMessage(evt){
		showMessage(evt.data, "left");
	}
	
	function onError(evt){
		showMessage("에러", "center");
	}
	
	function showMessage(msg, align){
		var messageWindow = document.getElementById("messageWindow");
		var msgBox = document.createElement("div");
		
		msgBox.style.textAlign = align;
		msgBox.innerHTML = msg;
		messageWindow.appendChild(msgBox);
		
		//messageWindow.scrollTop = messageWindow.scrollHeight;
	}
	
	function sendMessage(){
		var msg = document.getElementById("msg");
		if(msg.value != ""){
			<c:forEach items="${memberinfo }" var="vo">
				showMessage("${vo.name} : "+msg.value, "right");
			</c:forEach>
			ws.send(msg.value);
			document.msg_form.submit();
		}
	}
	
</script>

<div class="container">
	<h3>${friend_email }님과 채팅중</h3>
	
	<form id="msg_form" name="msg_form" method="post" action="/msg_insert">
		<textarea id="messageWindow" name="messageWindow" rows="20" cols="50" readonly="readonly" ></textarea><br/>
		<input id="msg" name="msg" type="text" onkeyup="if(event.keyCode == 13){sendMessage();}" />
		<input id="email" name="email" type="hidden" value="<%=(String)session.getAttribute("login_email")%>" />
		<input id="chatting_room_no" name="chatting_room_no" type="hidden" value="${chattingCheck.chatting_room_no}" />
		<input id="friend_email" name="friend_email" type="hidden" value="${friend_email}" />
		<input class="btn btn-info" type="button" value="전송" onclick="sendMessage()" />
	</form>
	
	<input class="btn btn-info" type="button" value="친구목록" onclick="location.href='/friend_list';" />
	<input class="btn btn-info" type="button" value="채팅목록" onclick="location.href='/chat_list';" />
</div>

<%@include file="./include/footer.jsp" %>
