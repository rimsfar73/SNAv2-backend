package com.example.vehiculos.exception;

public class VehiculoNotFoundException extends RuntimeException {

    public VehiculoNotFoundException(Long id) {
        super("No se encontró vehículo con id " + id);
    }

    public VehiculoNotFoundException(String patente) {
        super("No se encontró vehículo con patente " + patente);
    }
}

