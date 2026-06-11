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

    /*
     * ID del viajero propietario del vehículo.
     * Permite relacionar el vehículo con un viajero.
     */
    @NotNull(message = "El id del viajero no puede estar vacío")
    private Long viajeroId;

    /*
     * Patente única del vehículo.
     * @NotBlank: no permite null, vacío ni solo espacios.
     * @Pattern: exige formato tipo AA-BB-11.
     * @Column(unique = true): evita patentes duplicadas en la BD.
     */
    @NotBlank(message = "La patente no puede estar vacía")
    @Pattern(
            regexp = "^[A-Z]{2}-[A-Z]{2}-[0-9]{2}$",
            message = "Formato de patente inválido. Ejemplo válido: AA-BB-11"
    )
    @Column(unique = true, nullable = false)
    private String patente;

    /*
     * Marca del vehículo.
     * @Size: controla el largo mínimo y máximo.
     */
    @NotBlank(message = "La marca no puede estar vacía")
    @Size(min = 2, max = 50,
            message = "La marca debe tener entre 2 y 50 caracteres")
    private String marca;

    /*
     * Modelo del vehículo.
     */
    @NotBlank(message = "El modelo no puede estar vacío")
    @Size(min = 2, max = 50,
            message = "El modelo debe tener entre 2 y 50 caracteres")
    private String modelo;

    /*
     * Año de fabricación.
     * @Min y @Max evitan años irreales.
     */
    @NotNull(message = "El año no puede estar vacío")
    @Min(value = 1900, message = "El año no puede ser menor a 1900")
    @Max(value = 2100, message = "El año no puede ser mayor a 2100")
    private Integer anio;

    /*
     * Tipo de vehículo permitido.
     */
    @NotBlank(message = "El tipo no puede estar vacío")
    @Pattern(
            regexp = "AUTOMOVIL|CAMION|BUS|MOTO|FURGON",
            message = "Tipo inválido. Valores permitidos: AUTOMOVIL, CAMION, BUS, MOTO, FURGON"
    )
    private String tipo;
}