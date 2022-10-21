package com.soluciones.web.appGrupo4.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Value("${web.title}")
    private String title;

    @GetMapping("/login")
    public String login(Model model) {

        model.addAttribute("title", "Login | " + title);

        return "login";
    };

    @GetMapping("/signin")
    public String sigin(Model model) {

        model.addAttribute("title", "Sign in | " + title);

        return "sign_in";
    };
    
}
