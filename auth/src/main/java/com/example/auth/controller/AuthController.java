package com.example.auth.controller;

import com.example.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> body) {

        String token = service.login(
                body.get("username"),
                body.get("password")
        );

        return Map.of("token", token);
    }

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody Map<String, String> body) {

        service.register(
                body.get("username"),
                body.get("password"),
                body.get("role")
        );

        return Map.of(
                "message", "Usuario registrado correctamente"
        );
    }
}