package net.nigne.kkt.chat;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WSHandler extends TextWebSocketHandler {

	private static final Logger logger = LoggerFactory.getLogger(WSHandler.class);
	
	private Set<WebSocketSession> wsSession = new HashSet<WebSocketSession>();
	
	public WSHandler(){
		logger.info("������ �������Դϴ�");
	}

	//1.���������
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("����");
		wsSession.add(session);
		super.afterConnectionEstablished(session);
	}
	
	//2.������ ����������
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("����X");
		wsSession.remove(session);
		super.afterConnectionClosed(session, status);
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		super.handleMessage(session, message);
		System.out.println("session : " + session);
		
		System.out.println("message : " + message);
		logger.info("received message : " + message);
		
		sendMessage(session, message.getPayload().toString());
	}

	private void sendMessage(WebSocketSession session, String msg) {
		String room_num = session.getUri().toString().substring(10);
		for(WebSocketSession s : wsSession){
			if(s.isOpen() && !s.getId().equals(session.getId()) && room_num.equals(s.getUri().toString().substring(10))){
				try {
					System.out.println("session : " + session);
					System.out.println("msg : " + msg);
					s.sendMessage(new TextMessage("����:"+msg));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	//���ۿ���
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		super.handleTransportError(session, exception);
	}

	@Override
	public boolean supportsPartialMessages() {
		return super.supportsPartialMessages();
	}
	
	
}
