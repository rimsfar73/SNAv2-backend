package com.example.auth.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Usuario no encontrado
    @ExceptionHandler(AuthNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(AuthNotFoundException ex) {

        HashMap<String, String> detalles = new HashMap<>();
        detalles.put("detalle", ex.getMessage());

        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Usuario no encontrado",
                detalles
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Credenciales inválidas
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentials(BadCredentialsException ex) {

        HashMap<String, String> detalles = new HashMap<>();
        detalles.put("login", "Usuario o contraseña incorrectos");

        ErrorResponse error = new ErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                "Credenciales inválidas",
                detalles
        );

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    // Token expirado
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponse> handleExpiredToken(ExpiredJwtException ex) {

        HashMap<String, String> detalles = new HashMap<>();
        detalles.put("token", "El token ha expirado");

        ErrorResponse error = new ErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                "Token expirado",
                detalles
        );

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    // Token inválido o corrupto
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorResponse> handleJwtException(JwtException ex) {

        HashMap<String, String> detalles = new HashMap<>();
        detalles.put("token", "Token inválido o mal formado");

        ErrorResponse error = new ErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                "Token inválido",
                detalles
        );

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    // Cualquier otro error general
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {

        HashMap<String, String> detalles = new HashMap<>();
        detalles.put("detalle", ex.getMessage());

        ErrorResponse error = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error interno del servidor",
                detalles
        );

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
