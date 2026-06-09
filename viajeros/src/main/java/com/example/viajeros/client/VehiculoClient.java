package com.example.viajeros.client;

import com.example.viajeros.model.Vehiculo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class VehiculoClient {

    private final WebClient vehiculoWebClient;

    public Mono<Vehiculo> obtenerVehiculo(Long id, String token) {
        return vehiculoWebClient.get()
                .uri("/{id}", id)
                .header("Authorization", token)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                        Mono.error(new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Vehículo no encontrado")))
                .bodyToMono(Vehiculo.class);
    }
}
