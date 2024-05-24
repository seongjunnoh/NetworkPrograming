package org.example.IceBreaking.webSocketHandler;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.IceBreaking.domain.Chat;
import org.example.IceBreaking.dto.ConnectDto;
import org.example.IceBreaking.dto.UserInterestDto;
import org.example.IceBreaking.repository.chat.ChatRepository;
import org.example.IceBreaking.repository.question.QuestionRepository;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {

    private final SimpMessagingTemplate template;
    private final ChatRepository chatRepository;
    private final QuestionRepository questionRepository;

    private final static String chatBot = "IceBreaker";

    @MessageMapping("/chat/enter")
    public void enter(ConnectDto connectDto, SimpMessageHeaderAccessor headerAccessor) {
        connectDto.setMessage(connectDto.getUserName() + "님이 입장하였습니다.");

        // chat 저장
        Chat welcomeChat = new Chat(connectDto.getUserName(), connectDto.getMessage());
        chatRepository.saveChat(connectDto.getTeamId(), welcomeChat);

        // SimpMessageHeaderAccessor에 teamId, userName 추가
        headerAccessor.getSessionAttributes().put("teamId", connectDto.getTeamId());
        headerAccessor.getSessionAttributes().put("userName", connectDto.getUserName());

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

    @MessageMapping("/interests")
    public void getTeamInterests(UserInterestDto userInterestDto) {
        log.info("getTeamInterests(userInterestDto = {})", userInterestDto.toString());

        // userInterests 값을 받아서 팀별로 구분해 서버에 저장
        questionRepository.saveInterestsByTeam(userInterestDto);
    }

    @EventListener
    public void chatRoomExitListener(SessionDisconnectEvent event) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(event.getMessage());
        int teamId = (int) headerAccessor.getSessionAttributes().get("teamId");
        String userName = (String) headerAccessor.getSessionAttributes().get("userName");

        // chat 저장
        Chat exitChat = new Chat();
        exitChat.setUserName(userName);
        exitChat.setMessage(userName + "님이 나갔습니다.");
        chatRepository.saveChat(teamId, exitChat);

        // message send
        ConnectDto connectDto = new ConnectDto(teamId, userName, exitChat.getMessage());
        template.convertAndSend("/sub/chat/room/" + connectDto.getTeamId(), connectDto);
    }

}
