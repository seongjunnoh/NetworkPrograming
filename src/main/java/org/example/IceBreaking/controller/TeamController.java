package org.example.IceBreaking.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.IceBreaking.domain.Team;
import org.example.IceBreaking.domain.User;
import org.example.IceBreaking.repository.team.TeamRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TeamController {

    private final TeamRepository teamRepository;

    @GetMapping("/team/create")
    public String showCreateTeamForm() {
        return "/team/create";
    }

    @PostMapping("/team/create")
    public String createTeam(@RequestParam("teamName") String teamName) {
        Team team = new Team(teamName);
        teamRepository.save(team);

        return "redirect:/";            // 홈화면으로 redirect
    }

    @GetMapping("/team/all")
    public String showAllTeams(Model model) {
        List<Team> allTeams = teamRepository.findAll();
        model.addAttribute("allTeams", allTeams);

        return "/team/all";
    }
}
