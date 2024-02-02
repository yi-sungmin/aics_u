package com.nobiz.aics_u.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
@Slf4j
public class DashboardController {
    @GetMapping
    public String dashboard() {
        return "dashboard/dashboard";
    }
}
