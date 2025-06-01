-- Tabla de alumnos
INSERT INTO alumno (nombre, apellidos, dni, telefono, email, curso, cicloFormativo) VALUES ('Mar�a','Garc�a L�pez','12345678A','600123456','m.garcia@alumno.com','1� DAM','DAM');
INSERT INTO alumno (nombre, apellidos, dni, telefono, email, curso, cicloFormativo) VALUES ('Javier','Rodr�guez Ruiz','23456789B','600234567','j.rodriguez@alumno.com','2� DAM','DAM');
INSERT INTO alumno (nombre, apellidos, dni, telefono, email, curso, cicloFormativo) VALUES ('Luc�a','Mart�nez P�rez','34567890C','600345678','l.martinez@alumno.com','1� DAM','DAM');
INSERT INTO alumno (nombre, apellidos, dni, telefono, email, curso, cicloFormativo) VALUES ('Carlos','S�nchez G�mez','45678901D','600456789','c.sanchez@alumno.com','2� DAM','DAM');
INSERT INTO alumno (nombre, apellidos, dni, telefono, email, curso, cicloFormativo) VALUES ('Ana','Fern�ndez D�az','56789012E','600567890','a.fernandez@alumno.com','1� DAM','DAM');
INSERT INTO alumno (nombre, apellidos, dni, telefono, email, curso, cicloFormativo) VALUES ('David','L�pez Torres','67890123F','600678901','d.lopez@alumno.com','2� DAM','DAM');
INSERT INTO alumno (nombre, apellidos, dni, telefono, email, curso, cicloFormativo) VALUES ('Sara','G�mez N��ez','78901234G','600789012','s.gomez@alumno.com','1� DAM','DAM');
INSERT INTO alumno (nombre, apellidos, dni, telefono, email, curso, cicloFormativo) VALUES ('Miguel','Hern�ndez Ruiz','89012345H','600890123','m.hernandez@alumno.com','2� DAM','DAM');
INSERT INTO alumno (nombre, apellidos, dni, telefono, email, curso, cicloFormativo) VALUES ('Elena','Jim�nez Mor�n','90123456J','600901234','e.jimenez@alumno.com','1� DAM','DAM');
INSERT INTO alumno (nombre, apellidos, dni, telefono, email, curso, cicloFormativo) VALUES ('Pablo','Castro Blanco','01234567K','600012345','p.castro@alumno.com','2� DAM','DAM');

-- Tabla de empresas
INSERT INTO empresa (nombre, cif, sector, contacto, telefono, email, direccion) VALUES ('TechNova', 'B12345678', 'Tecnolog�a', 'Laura G�mez', '600123456', 'technova@empresa.com', 'Calle Innovaci�n 12, Madrid');
INSERT INTO empresa (nombre, cif, sector, contacto, telefono, email, direccion) VALUES ('EcoBuild', 'C23456789', 'Construcci�n', 'Carlos Ruiz', '600234567', 'ecobuild@empresa.com', 'Avenida Verde 34, Barcelona');
INSERT INTO empresa (nombre, cif, sector, contacto, telefono, email, direccion) VALUES ('HealthPlus', 'D34567890', 'Salud', 'Mar�a L�pez', '600345678', 'healthplus@empresa.com', 'Paseo de la Salud 56, Valencia');
INSERT INTO empresa (nombre, cif, sector, contacto, telefono, email, direccion) VALUES ('Foodies', 'J90123456', 'Alimentaci�n', 'Sof�a Romero', '600901234', 'foodies@empresa.com', 'Mercado Central 89, Alicante');
INSERT INTO empresa (nombre, cif, sector, contacto, telefono, email, direccion) VALUES ('EduSmart', 'E45678901', 'Educaci�n', 'Javier Mart�nez', '600456789', 'edusmart@empresa.com', 'Calle del Saber 78, Sevilla');
INSERT INTO empresa (nombre, cif, sector, contacto, telefono, email, direccion) VALUES ('GreenEnergy', 'H78901234', 'Energ�a', 'Luc�a Fern�ndez', '600789012', 'greenenergy@empresa.com', 'Avenida Solar 45, M�laga');
INSERT INTO empresa (nombre, cif, sector, contacto, telefono, email, direccion) VALUES ('AgroLife', 'F56789012', 'Agricultura', 'Ana Torres', '600567890', 'agrolife@empresa.com', 'Camino Rural 90, Zaragoza');
INSERT INTO empresa (nombre, cif, sector, contacto, telefono, email, direccion) VALUES ('TravelWorld', 'I89012345', 'Turismo', 'Pedro D�az', '600890123', 'travelworld@empresa.com', 'Calle Viajes 67, Granada');
INSERT INTO empresa (nombre, cif, sector, contacto, telefono, email, direccion) VALUES ('FinSecure', 'G67890123', 'Finanzas', 'Miguel S�nchez', '600678901', 'finsecure@empresa.com', 'Plaza Financiera 23, Bilbao');
INSERT INTO empresa (nombre, cif, sector, contacto, telefono, email, direccion) VALUES ('AutoDrive', 'K01234567', 'Automoci�n', 'Andr�s P�rez', '600012345', 'autodrive@empresa.com', 'Carretera Nacional 101, Valladolid');


-- Tabla de modulos
INSERT INTO modulo (nombre, modalidad) VALUES ('Sistemas inform�ticos', 'Si');
INSERT INTO modulo (nombre, modalidad) VALUES ('Bases de datos', 'Si');
INSERT INTO modulo (nombre, modalidad) VALUES ('Programaci�n', 'Si');
INSERT INTO modulo (nombre, modalidad) VALUES ('Lenguajes de marcas y sistemas de gesti�n de informaci�n', 'Si');
INSERT INTO modulo (nombre, modalidad) VALUES ('Entornos de desarrollo', 'Si');
INSERT INTO modulo (nombre, modalidad) VALUES ('Acceso a datos', 'Si');
INSERT INTO modulo (nombre, modalidad) VALUES ('Desarrollo de interfaces', 'Si');
INSERT INTO modulo (nombre, modalidad) VALUES ('Programaci�n multimedia y dispositivos m�viles', 'Si');
INSERT INTO modulo (nombre, modalidad) VALUES ('Programaci�n de servicios y procesos', 'Si');
INSERT INTO modulo (nombre, modalidad) VALUES ('Sistemas de gesti�n empresarial', 'No');
INSERT INTO modulo (nombre, modalidad) VALUES ('Ingl�s profesional', 'No');
INSERT INTO modulo (nombre, modalidad) VALUES ('Itinerario personal para la empleabilidad I', 'No');
INSERT INTO modulo (nombre, modalidad) VALUES ('Itinerario personal para la empleabilidad II', 'No');
INSERT INTO modulo (nombre, modalidad) VALUES ('Digitalizaci�n aplicada a los sectores productivos', 'No');
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

-- Tabla de resultados de aprendizaje asociados a m�dulos duales
INSERT INTO resultado_aprendizaje (descripcion, id_modulo) VALUES ('Configuraci�n y mantenimiento de sistemas operativos y redes', 1);
INSERT INTO resultado_aprendizaje (descripcion, id_modulo) VALUES ('Dise�o y gesti�n de bases de datos', 2);
INSERT INTO resultado_aprendizaje (descripcion, id_modulo) VALUES ('Implementaci�n de algoritmos y estructuras de datos', 3);
INSERT INTO resultado_aprendizaje (descripcion, id_modulo) VALUES ('Desarrollo y maquetaci�n de p�ginas web', 4);
INSERT INTO resultado_aprendizaje (descripcion, id_modulo) VALUES ('Configuraci�n de entornos IDE', 5);
INSERT INTO resultado_aprendizaje (descripcion, id_modulo) VALUES ('Gesti�n avanzada de bases de datos', 6);
INSERT INTO resultado_aprendizaje (descripcion, id_modulo) VALUES ('Desarrollo interfaces gr�ficas de usuario', 7);
INSERT INTO resultado_aprendizaje (descripcion, id_modulo) VALUES ('Creaci�n de aplicaciones m�viles y multimedia', 8);
INSERT INTO resultado_aprendizaje (descripcion, id_modulo) VALUES ('Dise�o de servicios y procesos concurrentes', 9);
INSERT INTO resultado_aprendizaje (descripcion, id_modulo) VALUES ('Gesti�n del ciclo completo de un proyecto multiplataforma', 16);