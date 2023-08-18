package com.apassignemnt.ap.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AauthController {

    @GetMapping("/auth")
    public String getAuth(){
        return "him shanky";
    }

    @PostMapping("/auth")
    public String generateAuthToken(){

    }
}
