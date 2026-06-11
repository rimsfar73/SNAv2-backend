package com.example.vehiculos.repository;

import com.example.vehiculos.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

    /*
     * Busca vehículo por patente.
     */
    Optional<Vehiculo> findByPatente(String patente);

    /*
     * Obtiene todos los vehículos asociados
     * a un viajero específico.
     */
    List<Vehiculo> findByViajeroId(Long viajeroId);

    /*
     * Verifica si ya existe una patente registrada.
     */
    boolean existsByPatente(String patente);
}