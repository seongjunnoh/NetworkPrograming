package org.example.IceBreaking.controller;

import lombok.RequiredArgsConstructor;
import org.example.IceBreaking.domain.User;
import org.example.IceBreaking.repository.user.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequiredArgsConstructor
public class SignupController {

    private final UserRepository userRepository;

    @GetMapping("/showSignup")
    public String showSignupForm() {
        return "/user/signup";
    }

    @PostMapping("/signup")
    public String createUser(@ModelAttribute User user) {
        user.setId();           // User 객체 고유의 Id값 set
        userRepository.save(user);

        // 검증용
        // 회원가입한 유저들의 정보를 콘솔에 print
        User savedUser = userRepository.findById(user.getUserId());
        System.out.println("savedUser.get().getId() = " + savedUser.getId());
        System.out.println("savedUser.get().getUserId() = " + savedUser.getUserId());
        System.out.println("savedUser.get().getPassword() = " + savedUser.getPassword());
        System.out.println("savedUser.get().getName() = " + savedUser.getName());
        System.out.println("savedUser.get().getDepartment() = " + savedUser.getDepartment());
        System.out.println("savedUser.get().getStudentId() = " + savedUser.getStudentId());
        System.out.println("savedUser.get().getInterests() = " + Arrays.toString(savedUser.getInterests()));

        return "redirect:/showLogin";         // 회원가입 후 로그인 화면으로 redirect
    }
}




