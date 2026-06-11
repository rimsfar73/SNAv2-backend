CREATE TABLE viajero_menores (
    viajero_id BIGINT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    edad INT NOT NULL,
    parentesco VARCHAR(50),
    PRIMARY KEY (viajero_id, nombre),
    FOREIGN KEY (viajero_id) REFERENCES viajero(id)
);
