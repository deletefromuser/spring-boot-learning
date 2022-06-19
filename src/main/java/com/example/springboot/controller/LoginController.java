package com.example.springboot.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Principal;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {

    @Value("${spring.security.oauth2.client.registration.google.clientId}")
    private String clientId;

    // @GetMapping("/login")
    // public String login() {

    // return "index";
    // }

    // https://developers.google.com/identity/gsi/web/guides/get-google-api-clientid
    @GetMapping("/goauth")
    public String goauth() {
        return "goauth";
    }

    // https://developers.google.com/identity/gsi/web/guides/get-google-api-clientid
    // /login/oauth2/code/google url must explictly setting in cloud api oauth2.0
    // setting 承認済みのリダイレクト URI
    @PostMapping("/getgoauth")
    public String getgoauth(@RequestParam(value = "g_csrf_token", required = false) String gCsrfToken,
            @RequestParam String credential,
            @RequestParam(value = "select_by", required = false) String selectBy)
            throws IOException, GeneralSecurityException {
        log.info("g_csrf_token: {}\r\n credential: {}\r\n select_by: {}", gCsrfToken, credential, selectBy);

        // https://developers.google.com/identity/gsi/web/reference/html-reference#server-side
        // https://developers.google.com/identity/gsi/web/guides/verify-google-id-token
        // https://developers.google.com/api-client-library/java/google-api-java-client/setup#google-api-client-servlet
        // https://stackoverflow.com/a/63515005/19120213
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                // Specify the CLIENT_ID of the app that accesses the backend:
                .setAudience(Collections.singletonList(clientId))
                // Or, if multiple clients access the backend:
                // .setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                .build();

        // (Receive idTokenString by HTTPS POST)

        GoogleIdToken idToken = verifier.verify(credential);
        if (idToken != null) {
            Payload payload = idToken.getPayload();

            log.info("payload: " + payload.toPrettyString());
        } else {
            log.info("Invalid ID token.");
        }

        return "goauth";
    }

    // @GetMapping("/logout")
    // public String logout() {

    // return "index";
    // }

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
        token.getAuthorities().stream().forEach(auth -> log.info(auth.toString()));
        log.info(token.getPrincipal().getAttributes().toString());
        log.info(token.getPrincipal().getAttributes().getOrDefault("login",
                "null").toString());

        return "index";
    }

}
