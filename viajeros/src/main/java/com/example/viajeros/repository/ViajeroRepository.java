package com.example.viajeros.repository;

import com.example.viajeros.model.Viajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ViajeroRepository extends JpaRepository<Viajero, Long> {

    Optional<Viajero> findByDocumento(String documento);

    boolean existsByDocumento(String documento);

    boolean existsByEmail(String email);
}