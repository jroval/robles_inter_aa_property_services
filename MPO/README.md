Este proyecto forma parte del trabajo intermodular del ciclo DAW y representa el sistema de gestión interna de AA Property Services, una empresa dedicada al mantenimiento y supervisión de villas.

La aplicación desarrollada en Java permite al personal administrador gestionar:

Propietarios
Villas
Incidencias

Todo ello conectado a una base de datos MySQL mediante JDBC.

- Arquitectura del proyecto

En el módulo MPO he refactorizado la aplicación para adoptar una estructura por capas, mejorando la organización y mantenibilidad del código.

La estructura final es:

aa_property
 ├─ controller   → Gestión de menús e interacción con el usuario
 ├─ service      → Lógica de negocio y validaciones
 ├─ dao          → Acceso a base de datos (JDBC)
 ├─ model        → Clases del dominio (entidades)
 ├─ util         → Utilidades (conexión, validaciones)
 └─ Main         → Punto de entrada de la aplicación
- Funcionamiento
Main arranca la aplicación.
MenuPrincipal gestiona la navegación.
Cada módulo (Propietario, Villa, Incidencia) tiene su propio Controller.
La lógica pasa por la capa Service.
El acceso a datos se realiza mediante DAO.

- Mejora MPO aplicada

Se ha realizado una refactorización completa del código para mejorar su calidad estructural.

Las principales mejoras son:

Separación de responsabilidades en capas (controller, service, dao, model)
Eliminación de lógica de negocio del Main
Implementación de clases Service para centralizar la lógica
Creación de un sistema de validación reutilizable (Validador)
Mejora de la legibilidad y mantenibilidad del código

- Tecnologías utilizadas
Java
JDBC
MySQL
IntelliJ IDEA
Git / GitHub