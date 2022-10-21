package com.soluciones.web.appGrupo4.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.soluciones.web.appGrupo4.model.User;

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

        User usr = new User();

        model.addAttribute("title", "Sign in | " + title);
        model.addAttribute("user", usr);

        return "sign_in";
    };

    @PostMapping("/createNewUser")
    public String a(@Validated User usr, BindingResult br, Model model) {

        if(br.hasErrors()) {
		
			return "sign_in";
		}

        return "home";
    }
    
}
