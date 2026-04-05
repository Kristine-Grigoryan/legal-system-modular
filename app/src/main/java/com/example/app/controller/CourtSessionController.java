package com.example.app.controller;

import com.example.app.service.SpringUser;
import com.example.model.CourtSession;
import com.example.service.CourtSessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CourtSessionController {


    private final CourtSessionService courtSessionService;


    @GetMapping("/courtSessions")
    public String courtSessions(ModelMap modelMap, @AuthenticationPrincipal SpringUser springUser) {
        List<CourtSession> courtSessions = courtSessionService.findAll();
        modelMap.addAttribute("courtSessions", courtSessions);

        return "courtSessions";
    }

    @GetMapping("/courtSessions/delete")
    public String deleteCourtSessions(@RequestParam("id") int id) {
        courtSessionService.deleteById ((long) id);
        return "redirect:/courtSessions";
    }

    @GetMapping("/courtSessions/add")
    public String addCourtSessions() {
        return "addCourtSessions";
    }

    @PostMapping("/courtSessions/add")
    public String addCourtSessions(@ModelAttribute CourtSession courtSession) {
        courtSessionService.save(courtSession);
        return "redirect:/courtSessions";

    }
}





