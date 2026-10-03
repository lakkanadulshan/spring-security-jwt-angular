package com.example.spring.security.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/loan")
@CrossOrigin
public class LoanController {

    @GetMapping("/my-loan")
    public String getLoanDetails(){
        return"Loan Page";
    }
}
