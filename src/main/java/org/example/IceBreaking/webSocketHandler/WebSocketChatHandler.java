package org.example.IceBreaking.webSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.IceBreaking.domain.Chat;
import org.example.IceBreaking.domain.ConnectDto;
import org.example.IceBreaking.domain.User;
import org.example.IceBreaking.repository.chat.ChatRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {

    //    private final Set<WebSocketSession> sessionSet = new HashSet<>();         // 현재 연결된 세션들
    private final SimpMessagingTemplate template;
    private final ChatRepository chatRepository;
    private final HttpSession httpSession;

    @MessageMapping("/chat/enter")
    public void enter(ConnectDto connectDto) {
        connectDto.setMessage(connectDto.getUserName() + "님이 입장하였습니다.");

        // chat 저장
        Chat welcomeChat = new Chat(connectDto.getUserName(), connectDto.getMessage());
        chatRepository.saveChat(connectDto.getTeamId(), welcomeChat);

        template.convertAndSend("/sub/chat/room/" + connectDto.getTeamId(), connectDto);
    }


//    /**
//     * 클라이언트가 접속 시 호출되는 메서드
//     */
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        log.info(session + " 클라이언트 접속");
//
//        sessionSet.add(session);
//
//        // session 확인용
//        System.out.println("session = " + session);
//    }
//
//    /**
//     * 클라이언트가 접속 해제 시 호출되는 메서드
//     */
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        log.info(session + " 클라이언트 접속 해제");
//
//        // session 확인용
//        System.out.println("session = " + session);
//
//        sessionSet.remove(session);
//    }
//
//    /**
//     * 채팅 message send 메서드
//     */
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        String payload = message.getPayload();          // payload : 전송되는 데이터
//        log.info("payload : " + payload);
//
//        for (WebSocketSession sess : sessionSet) {
//            sess.sendMessage(message);              // 연결된 각 session에 payload를 send
//        }
//    }


}
