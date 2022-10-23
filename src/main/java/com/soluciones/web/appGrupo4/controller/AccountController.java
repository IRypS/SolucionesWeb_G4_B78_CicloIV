package com.soluciones.web.appGrupo4.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
    public String createNewUser(@Validated User usr, BindingResult br, Model model) {

        String password = usr.getPassword().toString();
        String confirmPassword = usr.getConfirmPassword().toString();

        if( !password.equals(confirmPassword) ) {
            br.addError( new FieldError("user", "confirmPassword", "Las contraseñas no coinciden"));
        };

        if(br.hasErrors()) {
            model.addAttribute("user", usr);
            System.out.println("Souuuuuuuuuuuuuuuuuuuuuuuuu -------------------------------------->>>>>>>>>>>>>>>");
			return "sign_in";
		}
        

        model.addAttribute("user", usr);

        System.out.println(usr.getUsername());

        return "home";
    }
    
}
