package com.example.viajeros.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Embeddable
public class Menor {

    @NotBlank(message = "El nombre del menor es obligatorio")
    private String nombre;

    @Min(value = 0, message = "La edad no puede ser negativa")
    @Max(value = 17, message = "El menor no puede tener 18 o más años")
    private int edad;

    @NotBlank(message = "El parentesco es obligatorio")
    private String parentesco;
}
