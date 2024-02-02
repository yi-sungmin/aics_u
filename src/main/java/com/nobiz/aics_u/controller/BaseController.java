package com.nobiz.aics_u.controller;

import com.nobiz.aics_u.service.impl.UserTypeServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BaseController {
    private final UserTypeServiceImpl userTypeService;

    @GetMapping("/")
    public String base() {
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String mainIndex(Model model) {
        model.addAttribute("userDivType", userTypeService.findAllUserType("Y"));
        return "main";
    }

    @GetMapping("/login-page")
    public String loginPage(@RequestParam(required = false) boolean hasMessage,
                            @RequestParam(required = false) String message,
                            Model model) {
        model.addAttribute("hasMessage", hasMessage);
        model.addAttribute("message", message);
        return "login/login";
    }

}
