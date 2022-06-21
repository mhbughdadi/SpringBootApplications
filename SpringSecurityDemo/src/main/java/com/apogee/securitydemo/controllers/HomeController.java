package com.apogee.securitydemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String getGreating(){
        return "Welcome Everyone in our Website";
    }
    
    @GetMapping("/user/welcome")
    public String getGreatingUser(){
        return "Welcome User in our Website";
    }
    
    @GetMapping("/admin/welcome")
    public String getGreatingAdmin(){
        return "Welcome Admin in our Website";
    }
    
}
