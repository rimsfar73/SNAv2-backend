package com.example.auth.service;

import com.example.auth.model.User;
import com.example.auth.repository.UserRepository;
import com.example.auth.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repo;
    private final JwtService jwt;
    private final PasswordEncoder encoder;

    public String login(String username, String password) {

        User u = repo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no existe"));

        if (!encoder.matches(password, u.getPassword()))
            throw new RuntimeException("Credenciales inválidas");

        return jwt.generateToken(username);
    }
}


