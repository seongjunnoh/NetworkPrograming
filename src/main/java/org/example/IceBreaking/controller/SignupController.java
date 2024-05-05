package org.example.IceBreaking.controller;

import lombok.RequiredArgsConstructor;
import org.example.IceBreaking.domain.User;
import org.example.IceBreaking.repository.user.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class SignupController {

    private final UserRepository userRepository;

    @GetMapping("/signup")
    public String showSignupForm() {
        return "/user/signup";
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
        System.out.println("savedUser.get().getInterest() = " + savedUser.get().getInterest());

        return "redirect:/";         // 회원가입 후 홈화면으로 redirect
    }
}




