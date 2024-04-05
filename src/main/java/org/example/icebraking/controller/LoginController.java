package org.example.icebraking.controller;

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
            return "redirect:/";
        }
    }


}
