package com.haeun.firstproject.provider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class SessionGroup {
    private String room;
    private WebSocketSession session;
}


@Component
public class WebSocketProvider extends TextWebSocketHandler{ // WebSocket은 Handler라서 Handler폴더를 하나 생성 후 거기 안에 정의해두는 것이 맞음
    
    //handleBinaryMessage 파일 전송해주는 핸들러도 있음
    //* ws: 웹소켓 서버를 사용하기 때문에 주소에 http가 아니라 ws;//localhost:4040/web-socket으로 요청을 보내야 함
    //* 핸들러 만들어 줘야하는 3가지가 있음
    

    private List<SessionGroup> sessionList = new ArrayList<SessionGroup>();

    //* 1. 연결
    @Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
         //getHandshakeHeaders: Request에 존재하는 Headers와 동일한 것 키를 통해서 값을 가져올 수  있음
         //getFirst를 이용해서 키에 대한 값을 가져올 수  있음
        String room = session.getHandshakeHeaders().getFirst("room");
        sessionList.add(new SessionGroup(room, session));
        
        System.out.println(room + " / " + session.getId() + " Web Socket Connected!!");
    }
    
    //* 2. 메세지 송수신    
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //System.out.println(message.getPayload());
        //실제로 메세지 송수신 방법
       String room = session.getHandshakeHeaders().getFirst("room");
        for (SessionGroup sessionGroup: sessionList)  {
            if (sessionGroup.getRoom().equals(room) && !sessionGroup.getSession().equals(session)) { //해당하는 룸에만 메세지를 보낼 수 있게 됨
                sessionGroup.getSession().sendMessage(message);
            } 
        }

        
    }
    
    //* 3. 연결 해제
    @Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println(session.getId() + " Web Socket Closed!!");
        for (SessionGroup sessionGroup: sessionList) {
            if (sessionGroup.getSession().equals(session))
                sessionList.remove(sessionGroup);
        }
    }



}
