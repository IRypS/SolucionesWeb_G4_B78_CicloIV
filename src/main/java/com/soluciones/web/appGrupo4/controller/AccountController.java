package com.soluciones.web.appGrupo4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.soluciones.web.appGrupo4.model.Response;
import com.soluciones.web.appGrupo4.model.entities.E_User;
import com.soluciones.web.appGrupo4.service.interfaces.IUserService;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Value("${web.title}")
    private String title;

    @Autowired
    private IUserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Login | " + title);
        return "login";
    };

    @GetMapping("/signin")
    public String sigin(Model model, @RequestParam(value = "error", required = false) String error) {

        E_User usr = new E_User();

        model.addAttribute("title", "Sign in | " + title);
        model.addAttribute("user", usr);

        return "sign_in";
    };

    @PostMapping("/createNewUser")
    public String createNewUser(@Validated E_User usr, BindingResult br, Model model) {

        if (userService.userEmailExist(usr.getEmail())) {
            return "redirect:/account/signin/?error";
        }

        // Verify errors
        if(br.hasErrors()) {
            model.addAttribute("user", usr);
			return "sign_in";
		}

        Response<E_User> saveUserResponse = userService.createUser(usr);

        if (saveUserResponse.getState()) {
            return "redirect:/account/login";

        } else {
            model.addAttribute("title", title + " | Error al registrarse");
            model.addAttribute("error", "Bad request");
            model.addAttribute("status", "400");
            model.addAttribute("response", saveUserResponse.getMessage());
			model.addAttribute("errorMessage", saveUserResponse.getErrorMessage());
            return "error/400";
        }

    }

    @GetMapping("/logout")
    public String logout() {
		SecurityContextHolder.getContext().setAuthentication(null);
		return "redirect:/login";
	}
    
}
