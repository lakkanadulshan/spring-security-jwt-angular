package com.example.spring.security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
@CrossOrigin
public class AccountController {

    @GetMapping("/my-account")
//    @Secured("ROLE_ADMIN")
//    @PreAuthorize("hasAuthority('ROLE_USER')") CAN USE THOSE WHEN SEC_CONFIG SET AS AUTHENTICATED

    public String getAccountDetails(){

        return"Account Page";
    }
}
