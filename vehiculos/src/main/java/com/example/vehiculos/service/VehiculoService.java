package com.example.vehiculos.service;

import com.example.vehiculos.exception.VehiculoNotFoundException;
import com.example.vehiculos.model.Vehiculo;
import com.example.vehiculos.repository.VehiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    public VehiculoService(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    public List<Vehiculo> listarTodos() {
        return vehiculoRepository.findAll();
    }

    public Vehiculo buscarPorId(Long id) {
        return vehiculoRepository.findById(id)
                .orElseThrow(() -> new VehiculoNotFoundException("Vehículo con id " + id + " no encontrado"));
    }

    public Vehiculo guardar(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    public void eliminar(Long id) {
        if (!vehiculoRepository.existsById(id)) {
            throw new VehiculoNotFoundException("No se puede eliminar, vehículo con id " + id + " no existe");
        }
        vehiculoRepository.deleteById(id);
    }
}

