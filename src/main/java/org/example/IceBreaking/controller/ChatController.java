package org.example.IceBreaking.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.IceBreaking.domain.Chat;
import org.example.IceBreaking.domain.Team;
import org.example.IceBreaking.domain.User;
import org.example.IceBreaking.repository.chat.ChatRepository;
import org.example.IceBreaking.repository.team.TeamRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatRepository chatRepository;
    private final TeamRepository teamRepository;
    private final HttpSession httpSession;

    @GetMapping("/chat/{teamId}")
    public String showChatRoom(@PathVariable("teamId") int teamId, Model model) {
        Optional<Team> team = teamRepository.findById(teamId);
        model.addAttribute("team", team.get());         // Optional에서 Team 객체만을 추출

        User loginedUser = (User) httpSession.getAttribute("loginedUser");
        model.addAttribute("user", loginedUser);

        int teamCreatorId = teamRepository.findTeamCreatorId(teamId);
        System.out.println("teamCreatorId = " + teamCreatorId);
        model.addAttribute("teamCreatorId", teamCreatorId);

        return "/chat/chatRoomWebSocket";
    }

    @GetMapping("/api/chatList/{teamId}")
    @ResponseBody
    public ResponseEntity<List<Chat>> getChatList(@PathVariable("teamId") int teamId) {
        List<Chat> chatList = chatRepository.findChatList(teamId);
        return new ResponseEntity<>(chatList, HttpStatus.OK);
    }

}

