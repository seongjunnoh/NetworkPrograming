package org.example.IceBreaking.webSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.IceBreaking.domain.Chat;
import org.example.IceBreaking.domain.ConnectDto;
import org.example.IceBreaking.domain.User;
import org.example.IceBreaking.domain.UserInterestDto;
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

    private final SimpMessagingTemplate template;
    private final ChatRepository chatRepository;
    private final static String chatBot = "IceBreaker";

    @MessageMapping("/chat/enter")
    public void enter(ConnectDto connectDto) {
        connectDto.setMessage(connectDto.getUserName() + "님이 입장하였습니다.");

        // chat 저장
        Chat welcomeChat = new Chat(connectDto.getUserName(), connectDto.getMessage());
        chatRepository.saveChat(connectDto.getTeamId(), welcomeChat);

        template.convertAndSend("/sub/chat/room/" + connectDto.getTeamId(), connectDto);
    }

    @MessageMapping("/chat/message")
    public void message(ConnectDto message) {
        log.info("message(message = {})", message.toString());

        // chat 저장
        Chat chat = new Chat(message.getUserName(), message.getMessage());
        chatRepository.saveChat(message.getTeamId(), chat);

        template.convertAndSend("/sub/chat/room/" + message.getTeamId(), message);
    }

    @MessageMapping("/chat/question")
    public void question(ConnectDto question) {
        log.info("question(question = {})", question.toString());

        question.setUserName(chatBot);

        // chat 저장
        Chat chat = new Chat(question.getUserName(), question.getMessage());
        chatRepository.saveChat(question.getTeamId(), chat);

        template.convertAndSend("/sub/chat/room/" + question.getTeamId(), question);
    }

    @MessageMapping("/chat/exit")
    public void exit(ConnectDto connectDto) {
        connectDto.setMessage(connectDto.getUserName() + "님이 나갔습니다.");

        // chat 저장
        Chat exitChat = new Chat(connectDto.getUserName(), connectDto.getMessage());
        chatRepository.saveChat(connectDto.getTeamId(), exitChat);

        template.convertAndSend("/sub/chat/room/" + connectDto.getTeamId(), connectDto);
    }

    @MessageMapping("/interests")
    public void getTeamInterests(UserInterestDto userInterestDto) {
        log.info("getTeamInterests(interests = {})", userInterestDto.toString());

        // userInterests 값을 받아서 서버에 저장 & 다른 팀원들의 관심사와 겹치는게 있는지 체크


    }
}
