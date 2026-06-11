package com.example.viajeros.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Entity
@Table(name = "viajero")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Viajero extends RepresentationModel<Viajero> {

    /*
     * ID único del viajero.
     * Se genera automáticamente en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long viajeroId;

    /*
     * Nombre completo del viajero.
     *
     * @NotBlank:
     * - Evita valores null
     * - Evita cadenas vacías ""
     * - Evita espacios en blanco
     *
     * @Size:
     * - Define longitud mínima y máxima permitida
     * - Ayuda a evitar datos demasiado cortos o excesivos
     */
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(
            min = 3,
            max = 100,
            message = "El nombre debe tener entre 3 y 100 caracteres"
    )
    private String nombre;

    /*
     * Documento identificador del viajero.
     * Puede representar RUT, pasaporte o documento internacional.
     *
     * @Column(unique = true):
     * - Evita documentos duplicados en la base de datos
     *
     * @Column(nullable = false):
     * - Obliga a que el campo exista en MySQL
     *
     * @Size:
     * - Controla longitud mínima y máxima
     */
    @NotBlank(message = "El documento no puede estar vacío")
    @Column(unique = true, nullable = false)
    @Size(
            min = 8,
            max = 20,
            message = "El documento debe tener entre 8 y 20 caracteres"
    )
    private String documento;

    /*
     * Número telefónico del viajero.
     *
     * @Pattern:
     * - Valida mediante expresión regular (regex)
     * - Solo permite números
     * - Exige entre 8 y 15 dígitos
     *
     * Ejemplos válidos:
     * 912345678
     * 56912345678
     *
     * Ejemplos inválidos:
     * +56912345678
     * abc123
     */
    @NotBlank(message = "El teléfono no puede estar vacío")
    @Pattern(
            regexp = "^[0-9]{8,15}$",
            message = "El teléfono debe contener solo números y entre 8 y 15 dígitos"
    )
    private String telefono;

    /*
     * Dirección del viajero.
     *
     * @Size:
     * - Evita direcciones demasiado cortas
     * - Evita textos excesivamente largos
     */
    @NotBlank(message = "La dirección no puede estar vacía")
    @Size(
            min = 5,
            max = 200,
            message = "La dirección debe tener entre 5 y 200 caracteres"
    )
    private String direccion;

    /*
     * Correo electrónico del viajero.
     *
     * @Email:
     * - Valida formato correcto de email
     *
     * @Column(unique = true):
     * - Evita correos repetidos
     */
    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email no tiene un formato válido")
    @Column(unique = true)
    private String email;

    @ElementCollection
    private List<Menor> menores = new ArrayList<>();
}