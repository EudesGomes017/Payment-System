package com.EudesGomes017.payment_system.controller;

import com.EudesGomes017.payment_system.dto.UserRequest;
import com.EudesGomes017.payment_system.dto.UserResponse;
import com.EudesGomes017.payment_system.entity.Users;
import com.EudesGomes017.payment_system.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public ResponseEntity<UserResponse> registerUser(@RequestBody @Valid UserRequest userRequest) throws MessagingException, UnsupportedEncodingException {

        Users user = userRequest.toModel();

        UserResponse userSaved = service.registerUser(user);

        return ResponseEntity.ok().body(userSaved);

    }

    @GetMapping(value = "/verify", produces = "application/json")
    public String verifyUser(@RequestParam("code") String code) {

        if (service.verify(code)) {
            return "verify_success";
        } else {
            return "verify_fail";
        }

    }

}
