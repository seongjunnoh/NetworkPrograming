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

@Controller
@RequiredArgsConstructor
public class EditController {

    private final UserRepository userRepository;

    @GetMapping("/user/showEdit")
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
        httpSession.setAttribute("loginedUser", user);

        // 수정 정보 확인
        System.out.println("user.getId() = " + user.getId());
        System.out.println("user.getUserId() = " + user.getUserId());
        System.out.println("user.getPassword() = " + user.getPassword());
        System.out.println("user.getName() = " + user.getName());
        System.out.println("user.getDepartment() = " + user.getDepartment());
        System.out.println("user.getStudentId() = " + user.getStudentId());
        System.out.println("user.getInterest() = " + user.getInterest());
        System.out.println("user.getSubInterest() = " + user.getSubInterest());
        
        return "redirect:/";            // 홈화면으로 redirect
    }
}
