package com.example.viajeros.controller;

import com.example.viajeros.model.Viajero;
import com.example.viajeros.service.ViajeroService;
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
@RequestMapping("api/v1/viajeros")
@RequiredArgsConstructor
public class ViajeroController {

    private final ViajeroService service;

    /*
     * Obtiene todos los viajeros registrados.
     */
    @GetMapping
    public List<Viajero> getAll() {

        log.info("GET api/v1/viajeros");

        return service.findAll();
    }

    /*
     * Busca viajero por documento.
     *
     * Ejemplo:
     * GET /api/v1/viajeros?documento=12345678-9
     */
    @GetMapping(params = "documento")
    public Viajero getByDocumento(@RequestParam String documento) {

        log.info("GET api/v1/viajeros?documento={}", documento);

        return service.findByDocumento(documento);
    }

    /*
     * Crea un nuevo viajero.
     */
    @PostMapping
    public Viajero create(@Valid @RequestBody Viajero viajero) {

        log.info("POST api/v1/viajeros");

        return service.save(viajero);
    }

    /*
     * Actualiza viajero existente.
     */
    @PutMapping("/{id}")
    public Viajero update(
            @PathVariable Long id,
            @Valid @RequestBody Viajero viajero
    ) {

        log.info("PUT api/v1/viajeros/{}", id);

        return service.update(id, viajero);
    }

    /*
     * Elimina viajero por ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        log.warn("DELETE api/v1/viajeros/{}", id);

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    /*
     * Obtiene viajero por ID usando HATEOAS.
     */
    @GetMapping("/{id}")
    public Viajero getById(@PathVariable Long id) {

        log.info("GET api/v1/viajeros/{}", id);

        Viajero viajero = service.findById(id);

        viajero.add(
                linkTo(
                        methodOn(ViajeroController.class).getById(id)
                ).withSelfRel()
        );

        viajero.add(
                linkTo(
                        methodOn(ViajeroController.class).getAll()
                ).withRel("todos")
        );

        viajero.add(
                linkTo(
                        methodOn(ViajeroController.class).delete(id)
                ).withRel("eliminar")
        );

        return viajero;
    }
}