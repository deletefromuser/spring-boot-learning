package com.example.springboot.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {

    @GetMapping("/login")
    public String login() {

        return "service.index()";
    }

    @GetMapping("/logout")
    public String logout() {

        return "service.index()";
    }

    // https://www.baeldung.com/get-user-in-spring-security
    // https://github.com/spring-guides/tut-spring-boot-oauth2/tree/main/simple
    @GetMapping("/userinfo")
    public String userinfo(Principal principal, Authentication authentication) {
        Authentication authenticationByManual = SecurityContextHolder.getContext().getAuthentication();

        log.info(principal.toString());
        log.info(authentication.toString());
        log.info(authenticationByManual.toString());
        log.info(new Gson().toJson(principal));
        log.info(principal.getName());

        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) principal;
        // GrantedAuthority authority = token.getAuthorities().stream()
        // .filter(auth ->
        // "authority".equals(auth.getAuthority())).findFirst().orElse(null);

        
        token.getAuthorities().stream().forEach(auth -> log.info(auth.toString()));
        log.info(token.getPrincipal().getAttributes().toString());
        // authentication.getAuthorities().stream().filter(authentication ->
        // authentication).findFirst();

        return "index";
    }

}
