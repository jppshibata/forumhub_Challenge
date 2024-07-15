package com.forumhub_challenge.controller;

import com.forumhub_challenge.domain.user.AuthenticationData;
import com.forumhub_challenge.domain.user.User;
import com.forumhub_challenge.infra.security.TokenDataJWT;
import com.forumhub_challenge.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity logIn(@RequestBody @Valid AuthenticationData data){
        try {
            var authToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = manager.authenticate(authToken);
            var tokenJWT = tokenService.tokenGenerate((User) auth.getPrincipal());
            return ResponseEntity.ok(new TokenDataJWT(tokenJWT));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
