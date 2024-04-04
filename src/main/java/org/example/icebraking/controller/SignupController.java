package org.example.icebraking.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.icebraking.domain.User;
import org.example.icebraking.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class SignupController {

    private final UserRepository userRepository;

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "form";
    }

    @PostMapping("/signup")
    public String createUser(@ModelAttribute User user) {

        userRepository.save(user);

        // 검증용
        // 회원가입한 유저들의 정보를 콘솔에 print
        Optional<User> savedUser = userRepository.findById(user.getUserId());
        System.out.println("savedUser.get().getUserId() = " + savedUser.get().getUserId());
        System.out.println("savedUser.get().getPassword() = " + savedUser.get().getPassword());
        System.out.println("savedUser.get().getName() = " + savedUser.get().getName());
        System.out.println("savedUser.get().getDepartment() = " + savedUser.get().getDepartment());
        System.out.println("savedUser.get().getStudentId() = " + savedUser.get().getStudentId());
        
        return "redirect:/";         // 회원가입 후 홈화면으로 redirect
    }
}




