package com.soluciones.web.appGrupo4.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.soluciones.web.appGrupo4.service.JpaUserDetailsService;




@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
	private JpaUserDetailsService userDetailsService;

    private BCryptPasswordEncoder passwordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain ( HttpSecurity http ) throws Exception {
        http.authorizeRequests(
            (req) -> req
                        .antMatchers("/resources/**", "/lib/**","/styles/**","/utils/**").permitAll()
                        .antMatchers("/account/login", "/account/signin", "/account/createNewUser").permitAll()
                        .anyRequest().authenticated()
        ).formLogin()
            .loginPage("/account/login")
            .loginProcessingUrl("/account/login")
            .defaultSuccessUrl("/", true);

        return http.build();
    }

    public void configureGlobal(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

}
