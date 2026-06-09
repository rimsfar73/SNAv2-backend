package com.example.viajeros.controller;

import com.example.viajeros.model.Viajero;
import com.example.viajeros.service.ViajeroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viajeros")
public class ViajeroController {

    private final ViajeroService viajeroService;

    public ViajeroController(ViajeroService viajeroService) {
        this.viajeroService = viajeroService;
    }

    @GetMapping
    public ResponseEntity<List<Viajero>> listar() {
        return ResponseEntity.ok(viajeroService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Viajero> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(viajeroService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Viajero> crear(@RequestBody Viajero viajero) {
        return ResponseEntity.ok(viajeroService.guardar(viajero));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        viajeroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
