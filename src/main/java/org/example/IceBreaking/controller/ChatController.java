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

        return "/chat/chatRoomWebSocket";
    }

    @GetMapping("/api/chatList/{teamId}")
    @ResponseBody
    public ResponseEntity<List<Chat>> getChatList(@PathVariable("teamId") int teamId) {
        List<Chat> chatList = chatRepository.findChatList(teamId);
        return new ResponseEntity<>(chatList, HttpStatus.OK);
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
