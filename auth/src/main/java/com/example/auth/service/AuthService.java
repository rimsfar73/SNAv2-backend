package com.example.auth.service;

import com.example.auth.model.User;
import com.example.auth.repository.UserRepository;
import com.example.auth.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repo;
    private final JwtService jwt;
    private final PasswordEncoder encoder;

    public String login(String username, String password) {

        User user = repo.findByUsername(username)
                .orElseThrow(() ->
                        new NoSuchElementException("Usuario no existe")
                );

        if (!encoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Credenciales inválidas");
        }

        return jwt.generateToken(username);
    }

    public void register(String username, String password, String role) {

        if (repo.findByUsername(username).isPresent()) {
            throw new DataIntegrityViolationException("El usuario ya existe");
        }

        User user = User.builder()
                .username(username)
                .password(encoder.encode(password))
                .role(role)
                .build();

        repo.save(user);
    }
}