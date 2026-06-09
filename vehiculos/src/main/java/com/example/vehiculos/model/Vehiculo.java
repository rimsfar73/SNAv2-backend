package com.example.vehiculos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.RepresentationModel;

@Slf4j
@Entity
@Table(name = "vehiculo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehiculo extends RepresentationModel<Vehiculo> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehiculoId;

    @NotBlank
    @Column(unique = true)
    private String patente;

    @NotBlank
    private String marca;

    @NotBlank
    private String modelo;

    @Positive
    private Integer anio;

    @NotBlank
    private String tipo;
}


