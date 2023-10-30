create table clientes (
    id_cliente INT(8) PRIMARY KEY,
    nombre VARCHAR(30),
    direccion VARCHAR(50),
    telefono VARCHAR(10)
    );

INSERT INTO clientes VALUES (44556987,'Angel Rodriguez','Rosario 23','967556644');
INSERT INTO clientes VALUES (44987632,'Leopoldo Arias','Ancha 33','967112233');

SELECT * FROM clientes;

SELECT id_cliente FROM clientes WHERE nombre = 'pepe';

SELECT nombre, telefono FROM clientes;
