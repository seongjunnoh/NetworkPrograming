package org.example.icebraking.controller;

import lombok.RequiredArgsConstructor;
import org.example.icebraking.domain.User;
import org.example.icebraking.repository.UserRepository;
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
    public String createUser(
            @RequestParam("userId") String userId,
            @RequestParam("password") String password,
            @RequestParam("name") String name,
            @RequestParam("department") String department,
            @RequestParam("studentId") String studentId) {

        User user = new User(
                userId,
                password,
                name,
                department,
                studentId
        );
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




