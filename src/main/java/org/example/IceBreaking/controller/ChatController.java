package org.example.IceBreaking.controller;

import lombok.RequiredArgsConstructor;
import org.example.IceBreaking.domain.Chat;
import org.example.IceBreaking.domain.Team;
import org.example.IceBreaking.repository.chat.ChatRepository;
import org.example.IceBreaking.repository.team.TeamRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatRepository chatRepository;
    private final TeamRepository teamRepository;

    @GetMapping("/chat/{teamId}")
    public String showChatRoom(@PathVariable("teamId") int teamId, Model model) {
        List<Chat> chatList = chatRepository.findByTeamId(teamId);
        model.addAttribute("allChats", chatList);

        Optional<Team> team = teamRepository.findById(teamId);
        model.addAttribute("teamName", team.get().getTeamName());

        return "/chat/chatRoom";
    }

    @PostMapping("/send-message/{teamId}")
    public String sendMessage(@PathVariable("teamId") int teamId, @ModelAttribute Chat chat, RedirectAttributes attributes) {
        // parameter parsing 확인
        System.out.println("chat.getUserName() = " + chat.getUserName());       // 이건 모름 -> chatRoom.html에 이 정보가 있어야함
        System.out.println("chat.getMessage() = " + chat.getMessage());
//        System.out.println("chat.getTime() = " + chat.getTime());               // 이것도 chatRoom.html 에 추가해 줘야함

        chatRepository.saveByTeamId(teamId, chat);

        // RedirectAttributes를 사용하여 teamId를 다음 페이지로 전달
        attributes.addAttribute("teamId", teamId);

        return "redirect:/chat/{teamId}";
    }



}

//    private ServerSocket serverSocket;
//    private Socket[] clients;
//    private BufferedReader[] inStreams;
//    private PrintWriter[] outStreams;
//
//    @GetMapping("/chat/{teamId}")
//    public ModelAndView chatRoom(@PathVariable("teamId") String teamId) {
//        ModelAndView modelAndView = new ModelAndView("chat");
//        modelAndView.addObject("teamId", teamId);
//        return modelAndView;
//    }
//
//    @PostMapping("/startChat/{teamId}")
//    public ModelAndView startChat(@PathVariable("teamId") String teamId) {
//        try {
//            serverSocket = new ServerSocket(5000);
//            System.out.println("Server started. Waiting for clients to connect...");
//
//            clients = new Socket[4];
//            for (int i = 0; i < 4; i++) {
//                clients[i] = serverSocket.accept();
//                System.out.println("Client " + (i + 1) + " connected.");
//            }
//
//            inStreams = new BufferedReader[4];
//            outStreams = new PrintWriter[4];
//            for (int i = 0; i < 4; i++) {
//                inStreams[i] = new BufferedReader(new InputStreamReader(clients[i].getInputStream()));
//                outStreams[i] = new PrintWriter(clients[i].getOutputStream(), true);
//            }
//
//            return new ModelAndView("redirect:/chat/" + teamId);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new ModelAndView("error");
//        }
//    }
//
//    @PostMapping("/sendMessage/{teamId}")
//    public ModelAndView sendMessage(@PathVariable("teamId") String teamId, @RequestParam("message") String message) {
//        for (PrintWriter out : outStreams) {
//            out.println(message);
//        }
//        return new ModelAndView("redirect:/chat/" + teamId);
//    }
