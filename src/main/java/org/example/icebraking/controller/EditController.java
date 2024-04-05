package org.example.icebraking.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.icebraking.domain.User;
import org.example.icebraking.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class EditController {

    private final UserRepository userRepository;

    @GetMapping("/user/edit")
    public String showEditForm(Model model, HttpSession httpSession) {
        // 세션에서 로그인된 유저 정보 가져오기
        User loginedUser = (User) httpSession.getAttribute("loginedUser");

        // model에 추가하여 view에 전달
        model.addAttribute("user", loginedUser);

        return "/user/edit";
    }

    @PostMapping("/user/edit")
    public String editUserInfo(
            @RequestParam("userId") String userId,
            @RequestParam("password") String password,
            @RequestParam("name") String name,
            @RequestParam("department") String department,
            @RequestParam("studentId") String studentId,
            HttpSession httpSession) {

        User user = new User(userId, password, name, department, studentId);
        userRepository.changeUserInfo(user);

        // 세션 정보 update
        httpSession.setAttribute("loginedUser", user);;

        return "redirect:/";            // 홈화면으로 redirect
    }
}
