package com.apassignemnt.ap.controller;

import com.apassignemnt.ap.entity.AuthRequest;
import com.apassignemnt.ap.service.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/api/auth")
    public String getAuth(){
        return "him shanky";
    }

    @PostMapping("/token")
    public ResponseEntity<String> generateAuthToken(@RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(authService.generateToken(authRequest));
    }
}
