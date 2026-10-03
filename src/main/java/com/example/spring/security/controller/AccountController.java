package com.example.spring.security.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
@CrossOrigin
public class AccountController {

    @GetMapping("/my-account")
    public String getAccountDetails(){
        return"Account Page";
    }
}
