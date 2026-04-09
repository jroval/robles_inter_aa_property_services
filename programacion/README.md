

Este proyecto consiste en el desarrollo de una aplicación en Java que simula la gestión interna de la empresa AA Property Services, dedicada al mantenimiento, supervisión y servicios asociados a villas.

La aplicación permite gestionar propietarios, villas e incidencias mediante una interfaz por consola, conectada a una base de datos MySQL mediante JDBC.

 Objetivo

El objetivo es desarrollar una aplicación funcional que:

Gestione datos reales de una empresa ficticia
Utilice base de datos relacional (MySQL)
Se conecte mediante JDBC
Permita realizar operaciones CRUD
Esté organizada en clases y paquetes
- Tecnologías utilizadas
Java (JDK 17)
IntelliJ IDEA
MySQL (XAMPP + phpMyAdmin)
JDBC
Maven

 Estructura del proyecto
src/main/java/
└── aa_property_services
    ├── model        → Clases que representan las entidades
    ├── dao          → Acceso a base de datos (consultas SQL)
    ├── util         → Conexión a la base de datos
    └── Main.java    → Menú principal de la aplicación

Funcionalidades
- Propietarios
Añadir propietario
Listar propietarios
Buscar por ID
Actualizar datos
Eliminar propietario
-Villas
Añadir villa
Listar villas
Buscar por ID
Actualizar villa
Eliminar villa
- Incidencias
Crear incidencia
Listar incidencias
Buscar por ID
Listar incidencias por villa
Cambiar estado (PENDIENTE, EN_PROCESO, RESUELTA)
Eliminar incidencia
- Relación entre entidades
Un propietario puede tener una villa
Una villa puede tener múltiples incidencias
- Cómo ejecutar el proyecto
Iniciar XAMPP y arrancar MySQL
Crear la base de datos aa_property_services
Ejecutar los scripts SQL de creación de tablas
Abrir el proyecto en IntelliJ
Ejecutar la clase Main