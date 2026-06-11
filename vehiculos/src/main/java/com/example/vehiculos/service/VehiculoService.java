package com.example.vehiculos.service;

import com.example.vehiculos.model.Vehiculo;
import com.example.vehiculos.repository.VehiculoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class VehiculoService {

    private final VehiculoRepository repository;

    public List<Vehiculo> findAll() {
        log.info("Listando todos los vehículos");
        return repository.findAll();
    }

    public Vehiculo findById(Long id) {
        log.info("Buscando vehículo {}", id);

        return repository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("No se encontró vehículo con id " + id)
                );
    }

    public Vehiculo findByPatente(String patente) {
        log.info("Buscando vehículo por patente {}", patente);

        return repository.findByPatente(patente)
                .orElseThrow(() ->
                        new NoSuchElementException("No se encontró vehículo con patente " + patente)
                );
    }

    public List<Vehiculo> findByViajeroId(Long viajeroId) {
        log.info("Buscando vehículos del viajero {}", viajeroId);
        return repository.findByViajeroId(viajeroId);
    }

    public Vehiculo save(Vehiculo vehiculo) {
        log.info("Guardando vehículo con patente {}", vehiculo.getPatente());

        if (repository.existsByPatente(vehiculo.getPatente())) {
            throw new DataIntegrityViolationException("La patente ya está registrada");
        }

        return repository.save(vehiculo);
    }

    public Vehiculo update(Long id, Vehiculo vehiculo) {
        log.info("Actualizando vehículo {}", id);

        Vehiculo existing = findById(id);

        if (!existing.getPatente().equals(vehiculo.getPatente())
                && repository.existsByPatente(vehiculo.getPatente())) {

            throw new DataIntegrityViolationException(
                    "La patente ya está registrada en otro vehículo"
            );
        }

        existing.setViajeroId(vehiculo.getViajeroId());
        existing.setPatente(vehiculo.getPatente());
        existing.setMarca(vehiculo.getMarca());
        existing.setModelo(vehiculo.getModelo());
        existing.setAnio(vehiculo.getAnio());
        existing.setTipo(vehiculo.getTipo());

        return repository.save(existing);
    }

    public void delete(Long id) {
        log.warn("Eliminando vehículo {}", id);
        repository.delete(findById(id));
    }
}