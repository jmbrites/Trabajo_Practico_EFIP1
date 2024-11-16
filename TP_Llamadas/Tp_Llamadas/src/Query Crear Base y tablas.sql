CREATE DATABASE GestionLlamadas; 
USE GestionLlamadas;
Tabla Cliente
CREATE TABLE GestionLlamadas.Cliente ( 
ID_Cliente INT AUTO_INCREMENT PRIMARY KEY, 
Nombre VARCHAR(50) NOT NULL, 
Apellido VARCHAR(50) NOT NULL, 
Telefono VARCHAR(15), 
Direccion VARCHAR(100), 
Email VARCHAR(100)
 );
/* Tabla Asesor */
CREATE TABLE GestionLlamadas.Asesor ( 
ID_Asesor INT AUTO_INCREMENT PRIMARY KEY, 
Nombre VARCHAR(50) NOT NULL, 
Apellido VARCHAR(50) NOT NULL, 
Telefono VARCHAR(15), 
Email VARCHAR(100)
 );

/* Tabla Llamada */
CREATE TABLE GestionLlamadas.Llamada (
 ID_Llamada INT AUTO_INCREMENT PRIMARY KEY, 
Fecha_Hora DATETIME NOT NULL, 
Duracion INT NOT NULL, -- Duración en segundos
 Estado ENUM('Pendiente', 'Finalizada', 'Cancelada') NOT NULL, 
ID_Cliente INT, 
ID_Asesor INT,
 FOREIGN KEY (ID_Cliente) REFERENCES Cliente(ID_Cliente), 
FOREIGN KEY (ID_Asesor) REFERENCES Asesor(ID_Asesor) 
);

/* Tabla Resultado */
CREATE TABLE GestionLlamadas.Resultado (
 ID_Resultado INT AUTO_INCREMENT PRIMARY KEY, 
Descripcion VARCHAR(255) NOT NULL, 
ID_Llamada INT UNIQUE, 
FOREIGN KEY (ID_Llamada) REFERENCES GestionLlamadas.Llamada(ID_Llamada) 
);
