package com.example.viajeros.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.RepresentationModel;

@Slf4j
@Entity
@Table(name = "viajero")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Viajero extends RepresentationModel<Viajero> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long viajeroId;

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    @NotBlank
    @Column(unique = true)
    private String rut;

    @Email
    private String email;
}

