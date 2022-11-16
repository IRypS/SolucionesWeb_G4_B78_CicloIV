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

        User usr = new User();

        model.addAttribute("title", "Login | " + title);
        model.addAttribute("user", usr);

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

        // Compare Passwords 
        String password = usr.getPassword().toString();
        String confirmPassword = usr.getConfirmPassword().toString();
        
        if( !password.equals(confirmPassword) ) {
            br.addError( new FieldError("user", "confirmPassword", "Las contraseñas no coinciden"));
        };


        // Verify errors
        if(br.hasErrors()) {
            model.addAttribute("user", usr);
            
			return "sign_in";
		}
        

        model.addAttribute("user", usr);

        System.out.println("*** ---> Datos de usuario");
        System.out.println(usr.getEmail());
        System.out.println(usr.getUsername());

        // return "home";
        return "redirect:/app/trailers";
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
