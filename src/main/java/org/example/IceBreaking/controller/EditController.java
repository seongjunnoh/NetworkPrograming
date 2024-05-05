package org.example.IceBreaking.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.IceBreaking.domain.User;
import org.example.IceBreaking.repository.user.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String editUserInfo(@ModelAttribute User user, HttpSession httpSession) {
        userRepository.changeUserInfo(user);

        // 세션 정보 update
        httpSession.setAttribute("loginedUser", user);;

        return "redirect:/";            // 홈화면으로 redirect
    }
}
