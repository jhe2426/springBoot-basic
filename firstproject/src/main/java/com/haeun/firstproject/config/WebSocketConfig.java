package com.haeun.firstproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.haeun.firstproject.provider.WebSocketProvider;

import lombok.RequiredArgsConstructor;

@EnableWebSocket
@Configuration
@RequiredArgsConstructor
public class WebSocketConfig  implements WebSocketConfigurer{

    private final WebSocketProvider webSocketProvider;


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        
        //첫 번째 인자에는 핸들러 객체가 들어와야함 두 번째 인자에는 URL지정해서 넣으면 됨(endPoint 작성하면 됨)
        registry
            .addHandler(webSocketProvider, "/web-socket")
            .setAllowedOrigins("*"); //모든 출처에 대해서 허용한 것
    }
    
}
