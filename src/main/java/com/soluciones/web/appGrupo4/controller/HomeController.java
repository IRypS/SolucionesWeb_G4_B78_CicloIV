package com.soluciones.web.appGrupo4.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.soluciones.web.appGrupo4.model.Response;
import com.soluciones.web.appGrupo4.model.entities.E_User;
import com.soluciones.web.appGrupo4.service.interfaces.IRolService;
import com.soluciones.web.appGrupo4.service.interfaces.IUserService;

@Controller
public class HomeController {

    @Value("${web.title}")
    private String title;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRolService rolService;

    @RequestMapping("/redirect")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/app/administrator/dashboard";
        }
        return "redirect:/";
    }


    
    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("title", "Home | " + title);
        model.addAttribute("titleHero", title);
        
        Response<E_User> userDataResponse = userService.getUserInfo();

        if (userDataResponse.getState()) {
            model.addAttribute("activeSession", true);
            model.addAttribute("userObject", userDataResponse.getData());
            model.addAttribute("isAdmin", rolService.isAdmin(userDataResponse.getData().getRoles()));
        } else {
            model.addAttribute("activeSession", false);
        }

        return "home";
    };
}
