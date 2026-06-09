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

    @GetMapping
    public List<Viajero> getAll() {
        log.info("GET api/v1/viajeros");
        return service.findAll();
    }

    @GetMapping(params = "rut")
    public Viajero getByRut(@RequestParam String rut) {
        log.info("GET api/v1/viajeros?rut={}", rut);
        return service.findByRut(rut);
    }

    @PostMapping
    public Viajero create(@Valid @RequestBody Viajero viajero) {
        log.info("POST api/v1/viajeros");
        return service.save(viajero);
    }

    @PutMapping("/{id}")
    public Viajero update(@PathVariable Long id, @Valid @RequestBody Viajero viajero) {
        log.info("PUT api/v1/viajeros/{}", id);
        return service.update(id, viajero);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.warn("DELETE api/v1/viajeros/{}", id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ÚNICO MÉTODO GET BY ID (con HATEOAS)
    @GetMapping("/{id}")
    public Viajero getById(@PathVariable Long id) {
        log.info("GET api/v1/viajeros/{}", id);

        Viajero v = service.findById(id);

        v.add(linkTo(methodOn(ViajeroController.class).getById(id)).withSelfRel());
        v.add(linkTo(methodOn(ViajeroController.class).getAll()).withRel("todos"));
        v.add(linkTo(methodOn(ViajeroController.class).delete(id)).withRel("eliminar"));

        return v;
    }
}
