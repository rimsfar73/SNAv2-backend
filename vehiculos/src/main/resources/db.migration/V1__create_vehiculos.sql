CREATE TABLE vehiculo (
    vehiculo_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    viajero_id BIGINT NOT NULL,
    patente VARCHAR(10) NOT NULL UNIQUE,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    anio INT NOT NULL,
    tipo VARCHAR(20) NOT NULL
);