package com.soluciones.web.appGrupo4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.soluciones.web.appGrupo4.model.Response;
import com.soluciones.web.appGrupo4.model.User;
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
    public String sigin(Model model) {

        E_User usr = new E_User();

        model.addAttribute("title", "Sign in | " + title);
        model.addAttribute("user", usr);

        return "sign_in";
    };

    @PostMapping("/createNewUser")
    public String createNewUser(@Validated E_User usr, BindingResult br, Model model) {

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

    @PostMapping("/accessAccount")
    public String accessAccount(User usr, BindingResult br, Model md) {


        // Simulated login credentiasl
        String email = "demo@gmail.com";
        String password = "123456";

        if( !usr.getEmail().equals(email) ) {
            System.out.println("Los correos NO coinciden !!!");
            br.addError( new FieldError("user", "email", "Correo inválido: (intenta: demo@gmail.com)"));
        };

        if( !usr.getPassword().equals(password) ) {
            System.out.println("Las contraseñas NO coinciden !!!");
            br.addError( new FieldError("user", "password", "Contraseña inválida: (intenta: 123456)"));
        };

        if(br.hasErrors()) {
            return "login";
        };

        System.out.println("*** ---> Ingresando a la cuenta:");
        System.out.println(usr.getEmail());
        System.out.println(usr.getPassword());


        return "redirect:/app/trailers";
    };
    
}
