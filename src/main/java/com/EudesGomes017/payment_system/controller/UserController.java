package com.EudesGomes017.payment_system.controller;

import com.EudesGomes017.payment_system.dto.UserRequest;
import com.EudesGomes017.payment_system.dto.UserResponse;
import com.EudesGomes017.payment_system.entity.Users;
import com.EudesGomes017.payment_system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    public ResponseEntity<UserResponse> registerUser(@RequestBody @Valid UserRequest userRequest) {

        Users user = userRequest.toModel();

        UserResponse userSaved =  service.registerUser(user);

        return  ResponseEntity.ok().body(userSaved);

    }

}
