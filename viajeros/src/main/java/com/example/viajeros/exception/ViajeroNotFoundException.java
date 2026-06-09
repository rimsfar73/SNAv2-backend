package com.example.viajeros.exception;

public class ViajeroNotFoundException extends RuntimeException {

    public ViajeroNotFoundException(Long id) {
        super("No se encontró viajero con id " + id);
    }

    public ViajeroNotFoundException(String rut) {
        super("No se encontró viajero con rut " + rut);
    }
}


