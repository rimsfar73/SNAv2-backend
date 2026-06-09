package com.example.vehiculos.service;

import com.example.vehiculos.exception.VehiculoNotFoundException;
import com.example.vehiculos.model.Vehiculo;
import com.example.vehiculos.repository.VehiculoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .orElseThrow(() -> new VehiculoNotFoundException(id));
    }

    public Vehiculo findByPatente(String patente) {
        log.info("Buscando vehículo por patente {}", patente);
        return repository.findByPatente(patente)
                .orElseThrow(() -> new VehiculoNotFoundException(patente));
    }

    public Vehiculo save(Vehiculo vehiculo) {
        log.info("Guardando vehículo {}", vehiculo);
        return repository.save(vehiculo);
    }

    public Vehiculo update(Long id, Vehiculo vehiculo) {
        log.info("Actualizando vehículo {}", id);

        Vehiculo existing = findById(id);

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


