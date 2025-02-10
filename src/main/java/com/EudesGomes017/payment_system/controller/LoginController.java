package com.EudesGomes017.payment_system.controller;

import com.EudesGomes017.payment_system.config.security.TokenService;
import com.EudesGomes017.payment_system.dto.AuthenticationRequest;
import com.EudesGomes017.payment_system.dto.AuthenticationResponse;
import com.EudesGomes017.payment_system.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class LoginController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequest authenticationRequest){
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                authenticationRequest.email(), authenticationRequest.password()
        );
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken( (Users) auth.getPrincipal());

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }
}
