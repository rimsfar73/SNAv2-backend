package com.example.viajeros.service;

import com.example.viajeros.exception.ViajeroNotFoundException;
import com.example.viajeros.model.Viajero;
import com.example.viajeros.repository.ViajeroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViajeroService {

    private final ViajeroRepository viajeroRepository;

    public ViajeroService(ViajeroRepository viajeroRepository) {
        this.viajeroRepository = viajeroRepository;
    }

    public List<Viajero> listarTodos() {
        return viajeroRepository.findAll();
    }

    public Viajero buscarPorId(Long id) {
        return viajeroRepository.findById(id)
                .orElseThrow(() -> new ViajeroNotFoundException("Viajero con id " + id + " no encontrado"));
    }

    public Viajero guardar(Viajero viajero) {
        return viajeroRepository.save(viajero);
    }

    public void eliminar(Long id) {
        if (!viajeroRepository.existsById(id)) {
            throw new ViajeroNotFoundException("No se puede eliminar, viajero con id " + id + " no existe");
        }
        viajeroRepository.deleteById(id);
    }
}

