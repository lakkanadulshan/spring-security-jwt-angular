package com.example.spring.security.controller;

import com.example.spring.security.dto.AuthResponse;
import com.example.spring.security.model.User;
import com.example.spring.security.repository.UserRepo;
import com.example.spring.security.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin
public class LoginController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        ResponseEntity response = null;
        try {
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            user.setRole("ROLE_" + user.getRole()); //when use hasRole
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

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            String token = jwtService.generateToken(authentication.getName());
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (AuthenticationException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }


}
