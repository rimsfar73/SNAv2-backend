package com.example.viajeros.service;

import com.example.viajeros.exception.ViajeroNotFoundException;
import com.example.viajeros.model.Viajero;
import com.example.viajeros.repository.ViajeroRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ViajeroService {

    private final ViajeroRepository repository;

    public List<Viajero> findAll() {
        log.info("Listando todos los viajeros");
        return repository.findAll();
    }

    public Viajero findById(Long id) {
        log.info("Buscando viajero {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new ViajeroNotFoundException(id));
    }

    public Viajero findByRut(String rut) {
        log.info("Buscando viajero por rut {}", rut);
        return repository.findByRut(rut)
                .orElseThrow(() -> new ViajeroNotFoundException(rut));
    }

    public Viajero save(Viajero viajero) {
        log.info("Guardando viajero {}", viajero);
        return repository.save(viajero);
    }

    public Viajero update(Long id, Viajero viajero) {
        log.info("Actualizando viajero {}", id);

        Viajero existing = findById(id);

        existing.setNombre(viajero.getNombre());
        existing.setApellido(viajero.getApellido());
        existing.setRut(viajero.getRut());
        existing.setEmail(viajero.getEmail());

        return repository.save(existing);
    }

    public void delete(Long id) {
        log.warn("Eliminando viajero {}", id);
        repository.delete(findById(id));
    }
}


