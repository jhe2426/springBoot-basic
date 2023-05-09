package com.haeun.firstproject.provider;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


@Component
public class WebSocketProvider extends TextWebSocketHandler{ // WebSocket은 Handler라서 Handler폴더를 하나 생성후 거기 안에 정의해두는 것이 좋음
    
    //* 핸들러 만들어줘야하는 3가지가 있음
    
    //* 1. 연결
    @Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
	
    }
    
    //* 2. 메세지 송수신    
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
	
    }
    
    //* 3. 연결 해제
    @Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
	
    }
}
