package org.example.IceBreaking.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(HttpServletRequest req, Model model) {
        boolean loggedIn = req.getSession().getAttribute("loggedIn") != null;
        model.addAttribute("loggedIn", loggedIn);
        return "home";
    }

}
