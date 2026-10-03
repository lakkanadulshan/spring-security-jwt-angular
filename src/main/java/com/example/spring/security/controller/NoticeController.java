package com.example.spring.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/notice")
public class NoticeController {

    @GetMapping("/my-notice")
    public String getNotice(){
        return"Notice Page";
    }

}
