# AA Property Services - Base de Datos

## Descripción

Este proyecto representa el diseño y desarrollo de la base de datos para la empresa AA Property Services, orientada a la gestión de villas, propietarios, planes y servicios.

La base de datos está diseñada para reflejar la estructura básica del negocio y su relación con la web desarrollada en el proyecto.

---

## Estructura de la base de datos

La base de datos se compone de las siguientes entidades:

- PROPIETARIO
- COMPLEJO
- VILLA
- PLAN
- SERVICIO
- PLAN_SERVICIO

La entidad principal es VILLA, ya que conecta con propietario, complejo y plan.

---

## Relaciones

- Un propietario tiene varias villas (1:N)
- Un complejo tiene varias villas (1:N)
- Un plan puede estar en varias villas (1:N)
- Un plan incluye varios servicios (N:M)

---

## Archivos incluidos

### Documentación

- `docs/bbdd/diagrama-er.png` → Diagrama entidad-relación
- `docs/bbdd/modelo-relacional.md` → Modelo relacional


### Scripts SQL

- `sql/01_creacion_tablas.sql` → Creación de tablas
- `sql/02_insercion_datos.sql` → Inserción de datos de ejemplo
- `sql/03_consultas.sql` → Consultas SQL

---

## Uso

1. Crear la base de datos
2. Ejecutar el script de creación de tablas
3. Ejecutar el script de inserción de datos
4. Ejecutar las consultas

---

## Tecnologías utilizadas

- MySQL
- phpMyAdmin
- XAMPP