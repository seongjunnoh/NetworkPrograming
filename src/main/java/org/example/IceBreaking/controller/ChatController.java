package org.example.IceBreaking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@Controller
public class ChatController {
    private ServerSocket serverSocket;
    private Socket[] clients;
    private BufferedReader[] inStreams;
    private PrintWriter[] outStreams;

    @GetMapping("/chat/{teamId}")
    public ModelAndView chatRoom(@PathVariable("teamId") String teamId) {
        ModelAndView modelAndView = new ModelAndView("chat");
        modelAndView.addObject("teamId", teamId);
        return modelAndView;
    }

    @PostMapping("/startChat/{teamId}")
    public ModelAndView startChat(@PathVariable("teamId") String teamId) {
        try {
            serverSocket = new ServerSocket(5000);
            System.out.println("Server started. Waiting for clients to connect...");

            clients = new Socket[4];
            for (int i = 0; i < 4; i++) {
                clients[i] = serverSocket.accept();
                System.out.println("Client " + (i + 1) + " connected.");
            }

            inStreams = new BufferedReader[4];
            outStreams = new PrintWriter[4];
            for (int i = 0; i < 4; i++) {
                inStreams[i] = new BufferedReader(new InputStreamReader(clients[i].getInputStream()));
                outStreams[i] = new PrintWriter(clients[i].getOutputStream(), true);
            }

            return new ModelAndView("redirect:/chat/" + teamId);
        } catch (IOException e) {
            e.printStackTrace();
            return new ModelAndView("error");
        }
    }

    @PostMapping("/sendMessage/{teamId}")
    public ModelAndView sendMessage(@PathVariable("teamId") String teamId, @RequestParam("message") String message) {
        for (PrintWriter out : outStreams) {
            out.println(message);
        }
        return new ModelAndView("redirect:/chat/" + teamId);
    }
}
