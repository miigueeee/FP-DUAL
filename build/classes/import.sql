-- Tabla de alumnos
INSERT INTO alumno (nombre, apellidos, dni, telefono, email, curso, cicloFormativo) VALUES ('María','García López','12345678A','600123456','m.garcia@alumno.com','1º DAM','DAM');
INSERT INTO alumno (nombre, apellidos, dni, telefono, email, curso, cicloFormativo) VALUES ('Javier','Rodríguez Ruiz','23456789B','600234567','j.rodriguez@alumno.com','2º DAM','DAM');
INSERT INTO alumno (nombre, apellidos, dni, telefono, email, curso, cicloFormativo) VALUES ('Lucía','Martínez Pérez','34567890C','600345678','l.martinez@alumno.com','1º DAM','DAM');
INSERT INTO alumno (nombre, apellidos, dni, telefono, email, curso, cicloFormativo) VALUES ('Carlos','Sánchez Gómez','45678901D','600456789','c.sanchez@alumno.com','2º DAM','DAM');
INSERT INTO alumno (nombre, apellidos, dni, telefono, email, curso, cicloFormativo) VALUES ('Ana','Fernández Díaz','56789012E','600567890','a.fernandez@alumno.com','1º DAM','DAM');
INSERT INTO alumno (nombre, apellidos, dni, telefono, email, curso, cicloFormativo) VALUES ('David','López Torres','67890123F','600678901','d.lopez@alumno.com','2º DAM','DAM');
INSERT INTO alumno (nombre, apellidos, dni, telefono, email, curso, cicloFormativo) VALUES ('Sara','Gómez Núñez','78901234G','600789012','s.gomez@alumno.com','1º DAM','DAM');
INSERT INTO alumno (nombre, apellidos, dni, telefono, email, curso, cicloFormativo) VALUES ('Miguel','Hernández Ruiz','89012345H','600890123','m.hernandez@alumno.com','2º DAM','DAM');
INSERT INTO alumno (nombre, apellidos, dni, telefono, email, curso, cicloFormativo) VALUES ('Elena','Jiménez Morán','90123456J','600901234','e.jimenez@alumno.com','1º DAM','DAM');
INSERT INTO alumno (nombre, apellidos, dni, telefono, email, curso, cicloFormativo) VALUES ('Pablo','Castro Blanco','01234567K','600012345','p.castro@alumno.com','2º DAM','DAM');

-- Tabla de empresas
INSERT INTO empresa (nombre, cif, sector, contacto, telefono, email, direccion) VALUES ('TechNova', 'B12345678', 'Tecnología', 'Laura Gómez', '600123456', 'technova@empresa.com', 'Calle Innovación 12, Madrid');
INSERT INTO empresa (nombre, cif, sector, contacto, telefono, email, direccion) VALUES ('EcoBuild', 'C23456789', 'Construcción', 'Carlos Ruiz', '600234567', 'ecobuild@empresa.com', 'Avenida Verde 34, Barcelona');
INSERT INTO empresa (nombre, cif, sector, contacto, telefono, email, direccion) VALUES ('HealthPlus', 'D34567890', 'Salud', 'María López', '600345678', 'healthplus@empresa.com', 'Paseo de la Salud 56, Valencia');
INSERT INTO empresa (nombre, cif, sector, contacto, telefono, email, direccion) VALUES ('Foodies', 'J90123456', 'Alimentación', 'Sofía Romero', '600901234', 'foodies@empresa.com', 'Mercado Central 89, Alicante');
INSERT INTO empresa (nombre, cif, sector, contacto, telefono, email, direccion) VALUES ('EduSmart', 'E45678901', 'Educación', 'Javier Martínez', '600456789', 'edusmart@empresa.com', 'Calle del Saber 78, Sevilla');
INSERT INTO empresa (nombre, cif, sector, contacto, telefono, email, direccion) VALUES ('GreenEnergy', 'H78901234', 'Energía', 'Lucía Fernández', '600789012', 'greenenergy@empresa.com', 'Avenida Solar 45, Málaga');
INSERT INTO empresa (nombre, cif, sector, contacto, telefono, email, direccion) VALUES ('AgroLife', 'F56789012', 'Agricultura', 'Ana Torres', '600567890', 'agrolife@empresa.com', 'Camino Rural 90, Zaragoza');
INSERT INTO empresa (nombre, cif, sector, contacto, telefono, email, direccion) VALUES ('TravelWorld', 'I89012345', 'Turismo', 'Pedro Díaz', '600890123', 'travelworld@empresa.com', 'Calle Viajes 67, Granada');
INSERT INTO empresa (nombre, cif, sector, contacto, telefono, email, direccion) VALUES ('FinSecure', 'G67890123', 'Finanzas', 'Miguel Sánchez', '600678901', 'finsecure@empresa.com', 'Plaza Financiera 23, Bilbao');
INSERT INTO empresa (nombre, cif, sector, contacto, telefono, email, direccion) VALUES ('AutoDrive', 'K01234567', 'Automoción', 'Andrés Pérez', '600012345', 'autodrive@empresa.com', 'Carretera Nacional 101, Valladolid');


-- Tabla de modulos
INSERT INTO modulo (nombre, modalidad) VALUES ('Sistemas informáticos', 'Si');
INSERT INTO modulo (nombre, modalidad) VALUES ('Bases de datos', 'Si');
INSERT INTO modulo (nombre, modalidad) VALUES ('Programación', 'Si');
INSERT INTO modulo (nombre, modalidad) VALUES ('Lenguajes de marcas y sistemas de gestión de información', 'Si');
INSERT INTO modulo (nombre, modalidad) VALUES ('Entornos de desarrollo', 'Si');
INSERT INTO modulo (nombre, modalidad) VALUES ('Acceso a datos', 'Si');
INSERT INTO modulo (nombre, modalidad) VALUES ('Desarrollo de interfaces', 'Si');
INSERT INTO modulo (nombre, modalidad) VALUES ('Programación multimedia y dispositivos móviles', 'Si');
INSERT INTO modulo (nombre, modalidad) VALUES ('Programación de servicios y procesos', 'Si');
INSERT INTO modulo (nombre, modalidad) VALUES ('Sistemas de gestión empresarial', 'No');
INSERT INTO modulo (nombre, modalidad) VALUES ('Inglés profesional', 'No');
INSERT INTO modulo (nombre, modalidad) VALUES ('Itinerario personal para la empleabilidad I', 'No');
INSERT INTO modulo (nombre, modalidad) VALUES ('Itinerario personal para la empleabilidad II', 'No');
INSERT INTO modulo (nombre, modalidad) VALUES ('Digitalización aplicada a los sectores productivos', 'No');
INSERT INTO modulo (nombre, modalidad) VALUES ('Sostenibilidad aplicada al sistema productivo', 'No');
INSERT INTO modulo (nombre, modalidad) VALUES ('Proyecto de desarrollo de aplicaciones multiplataforma', 'Si');

-- Tabla de periodos de formacion
INSERT INTO periodo_formacion (id_alumno, id_empresa, fechaInicio, fechaFin, duracion_total) VALUES (1,  3, '2024-10-01', '2024-12-15', 432);
INSERT INTO periodo_formacion (id_alumno, id_empresa, fechaInicio, fechaFin, duracion_total) VALUES (2,  5, '2024-11-01', '2025-01-31', 528);
INSERT INTO periodo_formacion (id_alumno, id_empresa, fechaInicio, fechaFin, duracion_total) VALUES (3,  2, '2025-01-10', '2025-03-20', 400);
INSERT INTO periodo_formacion (id_alumno, id_empresa, fechaInicio, fechaFin, duracion_total) VALUES (4,  7, '2025-02-01', '2025-04-30', 504);
INSERT INTO periodo_formacion (id_alumno, id_empresa, fechaInicio, fechaFin, duracion_total) VALUES (5,  1, '2025-03-15', '2025-05-30', 440);
INSERT INTO periodo_formacion (id_alumno, id_empresa, fechaInicio, fechaFin, duracion_total) VALUES (6,  9, '2025-04-01', '2025-06-30', 520);
INSERT INTO periodo_formacion (id_alumno, id_empresa, fechaInicio, fechaFin, duracion_total) VALUES (7,  4, '2025-05-10', '2025-07-20', 400);
INSERT INTO periodo_formacion (id_alumno, id_empresa, fechaInicio, fechaFin, duracion_total) VALUES (8, 10, '2025-06-01', '2025-08-15', 440);
INSERT INTO periodo_formacion (id_alumno, id_empresa, fechaInicio, fechaFin, duracion_total) VALUES (9,  6, '2025-07-01', '2025-09-01', 360);
INSERT INTO periodo_formacion (id_alumno, id_empresa, fechaInicio, fechaFin, duracion_total) VALUES (10, 8, '2025-08-01', '2025-09-01', 176);

-- Tabla de resultados de aprendizaje asociados a módulos duales
INSERT INTO resultado_aprendizaje (descripcion, id_modulo) VALUES ('Configuración y mantenimiento de sistemas operativos y redes', 1);
INSERT INTO resultado_aprendizaje (descripcion, id_modulo) VALUES ('Diseño y gestión de bases de datos', 2);
INSERT INTO resultado_aprendizaje (descripcion, id_modulo) VALUES ('Implementación de algoritmos y estructuras de datos', 3);
INSERT INTO resultado_aprendizaje (descripcion, id_modulo) VALUES ('Desarrollo y maquetación de páginas web', 4);
INSERT INTO resultado_aprendizaje (descripcion, id_modulo) VALUES ('Configuración de entornos IDE', 5);
INSERT INTO resultado_aprendizaje (descripcion, id_modulo) VALUES ('Gestión avanzada de bases de datos', 6);
INSERT INTO resultado_aprendizaje (descripcion, id_modulo) VALUES ('Desarrollo interfaces gráficas de usuario', 7);
INSERT INTO resultado_aprendizaje (descripcion, id_modulo) VALUES ('Creación de aplicaciones móviles y multimedia', 8);
INSERT INTO resultado_aprendizaje (descripcion, id_modulo) VALUES ('Diseño de servicios y procesos concurrentes', 9);
INSERT INTO resultado_aprendizaje (descripcion, id_modulo) VALUES ('Gestión del ciclo completo de un proyecto multiplataforma', 16);