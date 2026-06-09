package com.example.viajeros.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "viajeros")
@Data
public class Viajero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false, unique = true)
    private String email;

    // 🔹 Este SÍ se guarda en la BD
    @Column(name = "vehiculo_id")
    private Long vehiculoId;

    // 🔹 Este NO se guarda en la BD (DTO externo)
    @Transient
    private Vehiculo vehiculo;
}
