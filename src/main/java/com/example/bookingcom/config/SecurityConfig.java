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
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.http.HttpSession;

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

        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("Admin")
//                .antMatchers("/profile/**").hasAnyRole("Admin","User")
                .antMatchers("/login", "/*")
                .permitAll();

        http.formLogin()
                .loginProcessingUrl("/signin")
                .loginPage("/login")
                .failureUrl("/login?error")
                .usernameParameter("user_email")
                .passwordParameter("user_password")
                .permitAll();

        http.logout()
                .logoutUrl("/signout")
                .logoutSuccessUrl("/")
                .permitAll()
                .invalidateHttpSession(true);

        http.csrf().disable();

        return http.build();
    }






}
