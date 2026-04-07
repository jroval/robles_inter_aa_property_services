USE aa_property_services;

-- 1. Listado de propietarios con sus villas
SELECT p.nombre, p.apellidos, v.codigo_villa
FROM propietario p
JOIN villa v ON p.id_propietario = v.id_propietario;

-- 2. Mostrar todas las villas con su complejo
SELECT v.codigo_villa, c.nombre AS complejo
FROM villa v
JOIN complejo c ON v.id_complejo = c.id_complejo;

-- 3. Ver qué plan tiene cada villa
SELECT v.codigo_villa, pl.nombre AS plan
FROM villa v
JOIN plan pl ON v.id_plan = pl.id_plan;

-- 4. Servicios incluidos en cada plan
SELECT pl.nombre AS plan, s.nombre AS servicio
FROM plan pl
JOIN plan_servicio ps ON pl.id_plan = ps.id_plan
JOIN servicio s ON ps.id_servicio = s.id_servicio;

-- 5. Mostrar todos los servicios del plan "Plan Básico Plus"
SELECT s.nombre
FROM servicio s
JOIN plan_servicio ps ON s.id_servicio = ps.id_servicio
JOIN plan pl ON ps.id_plan = pl.id_plan
WHERE pl.nombre = 'Plan Básico Plus';

-- 6. Número de villas por complejo
SELECT c.nombre, COUNT(v.id_villa) AS total_villas
FROM complejo c
LEFT JOIN villa v ON c.id_complejo = v.id_complejo
GROUP BY c.nombre;

-- 7. Número de villas por propietario
SELECT p.nombre, p.apellidos, COUNT(v.id_villa) AS total_villas
FROM propietario p
LEFT JOIN villa v ON p.id_propietario = v.id_propietario
GROUP BY p.id_propietario;

-- 8. Precio medio de los planes
SELECT AVG(precio_mensual) AS precio_medio
FROM plan;

-- 9. Listar servicios ordenados por precio
SELECT nombre, precio
FROM servicio
ORDER BY precio DESC;

-- 10. Villas con su propietario, complejo y plan (consulta completa)
SELECT 
    v.codigo_villa,
    p.nombre AS propietario,
    c.nombre AS complejo,
    pl.nombre AS plan
FROM villa v
JOIN propietario p ON v.id_propietario = p.id_propietario
JOIN complejo c ON v.id_complejo = c.id_complejo
JOIN plan pl ON v.id_plan = pl.id_plan;