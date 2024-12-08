-- Crear la base de datos con conjunto de caracteres utf8mb4 para una mejor compatibilidad
CREATE DATABASE IF NOT EXISTS MiAnimal CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE MiAnimal;

-- Crear la tabla Cliente
CREATE TABLE Cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(15),
    direccion VARCHAR(255),
    email VARCHAR(100) UNIQUE -- Se asegura que no haya correos duplicados
);

-- Crear la tabla Mascota
CREATE TABLE Mascota (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    especie ENUM('Perro', 'Gato', 'Conejo', 'Ave', 'Otro') DEFAULT 'Otro', -- Usar ENUM en lugar de VARCHAR
    raza VARCHAR(50),
    edad INT CHECK (edad >= 0), -- Se asegura de que la edad no sea negativa
    cliente_id INT,
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id) ON DELETE CASCADE -- Si se elimina un cliente, se eliminan sus mascotas
);

-- Crear la tabla Veterinario
CREATE TABLE Veterinario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    especialidad VARCHAR(100),
    telefono VARCHAR(15),
    email VARCHAR(100) UNIQUE -- Se asegura que no haya correos duplicados
);

-- Crear la tabla Cita
CREATE TABLE Cita (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fechaHora DATETIME NOT NULL,
    motivo VARCHAR(255),
    mascota_id INT,  
    veterinario_id INT,
    recordatorioEnviado BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (mascota_id) REFERENCES Mascota(id) ON DELETE CASCADE,
    FOREIGN KEY (veterinario_id) REFERENCES Veterinario(id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS Usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(50) NOT NULL UNIQUE,
    contraseña VARCHAR(100) NOT NULL,
    rol ENUM('admin', 'veterinario', 'recepcionista') NOT NULL DEFAULT 'recepcionista'
);

-- Crear los usuarios
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';
CREATE USER 'veterinario'@'localhost' IDENTIFIED BY 'veterinario';
CREATE USER 'recepcionista'@'localhost' IDENTIFIED BY 'recepcionista';

-- Crear los roles
CREATE ROLE role_admin;
CREATE ROLE role_veterinario;
CREATE ROLE role_recepcionista;

-- Asignar roles a los usuarios
GRANT role_admin TO 'admin'@'localhost';
GRANT role_veterinario TO 'veterinario'@'localhost';
GRANT role_recepcionista TO 'recepcionista'@'localhost';

-- Asignar privilegios a los roles
GRANT ALL PRIVILEGES ON MiAnimal.* TO role_admin;
GRANT SELECT, INSERT, UPDATE ON MiAnimal.Cita TO role_veterinario;
GRANT SELECT ON MiAnimal.Cliente TO role_veterinario;
GRANT SELECT ON MiAnimal.Mascota TO role_veterinario;

GRANT SELECT, INSERT ON MiAnimal.Cliente TO role_recepcionista;
GRANT SELECT, INSERT, UPDATE ON MiAnimal.Cita TO role_recepcionista;

-- Limpiar tablas antes de insertar registros
DELETE FROM Cita;
DELETE FROM Mascota;
DELETE FROM Cliente;
DELETE FROM Veterinario;

-- Insertar Clientes
INSERT INTO Cliente (nombre, telefono, direccion, email) 
VALUES 
('Juan Pérez', '555-1234', 'Calle Principal 123', 'juan.perez@example.com'),
('María López', '555-5678', 'Avenida Siempreviva 456', 'maria.lopez@example.com');

-- Insertar Mascotas
INSERT INTO Mascota (nombre, especie, raza, edad, cliente_id) 
VALUES 
('Bobby', 'Perro', 'Labrador', 5, 1), 
('Michi', 'Gato', 'Siames', 3, 2);

-- Insertar Veterinarios
INSERT INTO Veterinario (nombre, especialidad, telefono, email) 
VALUES 
('Dra. Ana Martínez', 'Cirugía', '555-4321', 'ana.martinez@example.com'),
('Dr. Luis Rodríguez', 'Dermatología', '555-8764', 'luis.rodriguez@example.com');

-- Insertar Citas
INSERT INTO Cita (fechaHora, motivo, mascota_id, veterinario_id, recordatorioEnviado) 
VALUES 
('2024-12-05 10:00:00', 'Vacunación anual', 1, 1, FALSE),
('2024-12-06 15:00:00', 'Consulta por alergias', 2, 2, TRUE);

INSERT INTO Usuario (nombre_usuario, contraseña, rol) 
VALUES 
('admin', 'admin_password', 'admin'), 
('vet1', 'vet_password', 'veterinario'), 
('recep1', 'recep_password', 'recepcionista');

