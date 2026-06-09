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

    @GetMapping
    public List<Vehiculo> getAll() {
        log.info("GET api/v1/vehiculos");
        return service.findAll();
    }

    @GetMapping(params = "patente")
    public Vehiculo getByPatente(@RequestParam String patente) {
        log.info("GET api/v1/vehiculos?patente={}", patente);
        return service.findByPatente(patente);
    }

    @PostMapping
    public Vehiculo create(@Valid @RequestBody Vehiculo vehiculo) {
        log.info("POST api/v1/vehiculos");
        return service.save(vehiculo);
    }

    @PutMapping("/{id}")
    public Vehiculo update(@PathVariable Long id, @Valid @RequestBody Vehiculo vehiculo) {
        log.info("PUT api/v1/vehiculos/{}", id);
        return service.update(id, vehiculo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.warn("DELETE api/v1/vehiculos/{}", id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ÚNICO método GET BY ID (con HATEOAS)
    @GetMapping("/{id}")
    public Vehiculo getById(@PathVariable Long id) {
        log.info("GET api/v1/vehiculos/{}", id);

        Vehiculo v = service.findById(id);

        v.add(linkTo(methodOn(VehiculoController.class).getById(id)).withSelfRel());
        v.add(linkTo(methodOn(VehiculoController.class).getAll()).withRel("todos"));
        v.add(linkTo(methodOn(VehiculoController.class).delete(id)).withRel("eliminar"));

        return v;
    }
}
