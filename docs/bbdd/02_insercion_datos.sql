USE aa_property_services;

-- PLANES
INSERT INTO plan (nombre, descripcion, precio_mensual) VALUES
('Plan Básico', 'Incluye inspecciones semanales', 100.00),
('Plan Básico Plus', 'Incluye limpieza y jardinería', 200.00);

-- PROPIETARIOS
INSERT INTO propietario (nombre, apellidos, email, telefono) VALUES
('Juan', 'Pérez', 'juan@email.com', '600111222'),
('Laura', 'García', 'laura@email.com', '600333444');

-- COMPLEJOS
INSERT INTO complejo (nombre, ubicacion) VALUES
('Andryala', 'Oropesa del Mar'),
('Barlovento', 'Oropesa del Mar');

-- SERVICIOS
INSERT INTO servicio (nombre, categoria, precio) VALUES
('Limpieza', 'Mantenimiento', 50.00),
('Jardinería', 'Mantenimiento', 60.00),
('Chef privado', 'Premium', 150.00),
('Chofer', 'Transporte', 80.00);

-- VILLAS
INSERT INTO villa (codigo_villa, estado, id_propietario, id_complejo, id_plan) VALUES
('ANDRYALA 1A', 'Activa', 1, 1, 2),
('BARLOVENTO 2B', 'Activa', 2, 2, 1);

-- RELACIÓN PLAN-SERVICIO
INSERT INTO plan_servicio (id_plan, id_servicio) VALUES
(1, 1), -- básico incluye limpieza
(1, 2), -- básico incluye jardinería
(2, 1),
(2, 2),
(2, 3),
(2, 4);