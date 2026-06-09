package com.example.viajeros.model;

import lombok.Data;

@Data
public class Vehiculo {
    private Long id;
    private String patente;
    private String marca;
    private String modelo;
    private int anio;
    private String tipo;
}


