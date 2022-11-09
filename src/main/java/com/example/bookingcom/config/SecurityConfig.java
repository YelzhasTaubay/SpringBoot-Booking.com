package com.example.bookingcom.config;

import com.example.bookingcom.service.UserService;
import com.example.bookingcom.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,proxyTargetClass = true,securedEnabled = true)
public class SecurityConfig {

    @Bean
    public UserService userService(){
        return new UserServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userService()).passwordEncoder(passwordEncoder());


        http.authorizeRequests().antMatchers("/css/**","/js/**").permitAll();

        http.formLogin()
                .loginProcessingUrl("/auth").permitAll() // form action="/auth" method="post"
                .defaultSuccessUrl("/profile")    //response.sendRedirect("/profile")
                .failureUrl("/signin?error") //if auth failed
                .loginPage("/signin").permitAll()  //authentication page
                .usernameParameter("user_email")   // <input type="email" name="user_email">
                .passwordParameter("user_password"); // <ipput type="password" name="user_password">

        http.logout()
                .logoutSuccessUrl("/signin")
                .logoutUrl("/signout");

        http.csrf().disable();

        return http.build();


    }




}