package com.example.spring.security.controller;

import com.example.spring.security.model.User;
import com.example.spring.security.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin
public class LoginController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        ResponseEntity response = null;
        try {
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashedPassword);
            User savedUser = userRepo.save(user);
            if (savedUser.getId() > 0) {
                response = ResponseEntity.status(
                        HttpStatus.CREATED
                ).body("User Details Successfully Registered");
            }

        } catch (Exception exception) {
            response = ResponseEntity.status(
                    HttpStatus.CREATED
            ).body("An Exception occured due to " + exception.getMessage());

        }
        return response;
    }

}
