package org.example.icebraking.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.icebraking.domain.User;
import org.example.icebraking.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String userLogin(@RequestParam("userId") String userId,
                            @RequestParam("password") String password) {

        Optional<User> loginedUser = userRepository.findById(userId);
        if (loginedUser.isEmpty() || !loginedUser.get().matchPassword(password)) {
            // 로그인 실패
            return "login_failed";
        } else {
            // 로그인 성공
            httpSession.setAttribute("loginedUser", loginedUser.get());
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String userLogout() {
        // 로그아웃 시 세션 제거 후 홈화면으로 redirect
        httpSession.removeAttribute("loginedUser");
        return "redirect:/";
    }

}
