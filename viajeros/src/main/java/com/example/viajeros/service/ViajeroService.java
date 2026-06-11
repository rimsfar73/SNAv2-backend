package com.example.viajeros.service;

import com.example.viajeros.model.Menor;
import com.example.viajeros.model.Viajero;
import com.example.viajeros.repository.ViajeroRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ViajeroService {

    private final ViajeroRepository repository;

    /*
     * Obtiene todos los viajeros registrados.
     */
    public List<Viajero> findAll() {
        log.info("Listando todos los viajeros");
        return repository.findAll();
    }

    /*
     * Busca viajero por ID.
     */
    public Viajero findById(Long id) {
        log.info("Buscando viajero con id {}", id);

        return repository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("No se encontró viajero con id " + id)
                );
    }

    /*
     * Busca viajero por documento.
     */
    public Viajero findByDocumento(String documento) {
        log.info("Buscando viajero por documento {}", documento);

        return repository.findByDocumento(documento)
                .orElseThrow(() ->
                        new NoSuchElementException("No se encontró viajero con documento " + documento)
                );
    }

    /*
     * Guarda un nuevo viajero.
     */
    public Viajero save(Viajero viajero) {
        log.info("Guardando viajero {}", viajero);

        if (repository.existsByDocumento(viajero.getDocumento())) {
            throw new DataIntegrityViolationException("El documento ya está registrado");
        }

        if (repository.existsByEmail(viajero.getEmail())) {
            throw new DataIntegrityViolationException("El email ya está registrado");
        }

        return repository.save(viajero);
    }

    /*
     * Actualiza datos de un viajero existente.
     */
    public Viajero update(Long id, Viajero viajero) {
        log.info("Actualizando viajero {}", id);

        Viajero existing = findById(id);

        existing.setNombre(viajero.getNombre());
        existing.setDocumento(viajero.getDocumento());
        existing.setTelefono(viajero.getTelefono());
        existing.setDireccion(viajero.getDireccion());
        existing.setEmail(viajero.getEmail());
        existing.setMenores(viajero.getMenores()); // <-- IMPORTANTE

        return repository.save(existing);
    }

    /*
     * Elimina un viajero por ID.
     */
    public void delete(Long id) {
        log.warn("Eliminando viajero {}", id);
        repository.delete(findById(id));
    }

    // ============================================================
    // ===============   LÓGICA DE MENORES   ======================
    // ============================================================

    public List<Menor> obtenerMenores(Long idViajero) {
        Viajero viajero = findById(idViajero);
        return viajero.getMenores();
    }

    public Menor agregarMenor(Long idViajero, Menor menor) {
        Viajero viajero = findById(idViajero);

        viajero.getMenores().add(menor);
        repository.save(viajero);

        return menor;
    }

    public void eliminarMenor(Long idViajero, int index) {
        Viajero viajero = findById(idViajero);

        if (index < 0 || index >= viajero.getMenores().size()) {
            throw new NoSuchElementException("El menor indicado no existe");
        }

        viajero.getMenores().remove(index);
        repository.save(viajero);
    }
}
