package com.dpl.biomedical.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dpl.biomedical.dto.UserCredentials;
import com.dpl.biomedical.dto.UserResponse;
@CrossOrigin
@RestController
public class UserController {

    @PostMapping("/login")
    public UserResponse login(@RequestBody UserCredentials userCredentials) {
        String username = userCredentials.getUsername();
        String password = userCredentials.getPassword();

        if ("buddinipun".equals(username) && "password".equals(password)) {
            return new UserResponse(1L, "Buddhika Nipun", "62, ksndy road Danowita");
        } else {
            return null;
        }
    }
}
