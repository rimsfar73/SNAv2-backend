package com.example.vehiculos.controller;

import com.example.vehiculos.model.Vehiculo;
import com.example.vehiculos.service.VehiculoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RestController
@RequestMapping("api/v1/vehiculos")
@RequiredArgsConstructor
public class VehiculoController {

    private final VehiculoService service;

    /*
     * Obtiene todos los vehículos registrados.
     */
    @GetMapping
    public List<Vehiculo> getAll() {

        log.info("GET api/v1/vehiculos");

        return service.findAll();
    }

    /*
     * Busca vehículo por patente.
     * Ejemplo:
     * GET /api/v1/vehiculos?patente=AA-BB-11
     */
    @GetMapping(params = "patente")
    public Vehiculo getByPatente(@RequestParam String patente) {

        log.info("GET api/v1/vehiculos?patente={}", patente);

        return service.findByPatente(patente);
    }

    /*
     * Obtiene todos los vehículos asociados a un viajero.
     * Ejemplo:
     * GET /api/v1/vehiculos/viajero/1
     */
    @GetMapping("/viajero/{viajeroId}")
    public List<Vehiculo> getByViajeroId(@PathVariable Long viajeroId) {

        log.info("GET api/v1/vehiculos/viajero/{}", viajeroId);

        return service.findByViajeroId(viajeroId);
    }

    /*
     * Crea un nuevo vehículo.
     */
    @PostMapping
    public Vehiculo create(@Valid @RequestBody Vehiculo vehiculo) {

        log.info("POST api/v1/vehiculos");

        return service.save(vehiculo);
    }

    /*
     * Actualiza un vehículo existente.
     */
    @PutMapping("/{id}")
    public Vehiculo update(
            @PathVariable Long id,
            @Valid @RequestBody Vehiculo vehiculo
    ) {

        log.info("PUT api/v1/vehiculos/{}", id);

        return service.update(id, vehiculo);
    }

    /*
     * Elimina un vehículo por ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        log.warn("DELETE api/v1/vehiculos/{}", id);

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    /*
     * Obtiene vehículo por ID usando HATEOAS.
     */
    @GetMapping("/{id}")
    public Vehiculo getById(@PathVariable Long id) {

        log.info("GET api/v1/vehiculos/{}", id);

        Vehiculo vehiculo = service.findById(id);

        vehiculo.add(
                linkTo(
                        methodOn(VehiculoController.class).getById(id)
                ).withSelfRel()
        );

        vehiculo.add(
                linkTo(
                        methodOn(VehiculoController.class).getAll()
                ).withRel("todos")
        );

        vehiculo.add(
                linkTo(
                        methodOn(VehiculoController.class).getByPatente(vehiculo.getPatente())
                ).withRel("buscarPorPatente")
        );

        vehiculo.add(
                linkTo(
                        methodOn(VehiculoController.class).getByViajeroId(vehiculo.getViajeroId())
                ).withRel("vehiculosDelViajero")
        );

        vehiculo.add(
                linkTo(
                        methodOn(VehiculoController.class).delete(id)
                ).withRel("eliminar")
        );

        return vehiculo;
    }
}