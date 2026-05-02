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

Implementación de Login por Consola

 1. Objetivo

Como mejora adicional al proyecto base, se ha implementado un sistema de autenticación de usuarios en entorno de consola.
El objetivo principal ha sido simular un acceso real a la aplicación, diferenciando entre usuarios autenticados y no autenticados, y permitiendo el acceso a funcionalidades específicas según el estado de sesión.

Esta funcionalidad no era obligatoria en el enunciado, pero se ha añadido para aportar mayor realismo al sistema y acercarlo a un entorno de aplicación real.

---

 2. Descripción general del funcionamiento

El sistema de login permite a un usuario (propietario) autenticarse mediante:

* Email
* Contraseña

Estos datos se validan contra la base de datos mediante la clase `PropietarioDAO`.

Si las credenciales son correctas:

* Se crea una sesión en memoria
* Se almacena el usuario logueado
* Se habilitan funcionalidades específicas

Si son incorrectas:

* Se informa al usuario del error

---

 3. Gestión de sesión

Se ha implementado una simulación de sesión mediante una variable en memoria:

```java
private Propietario propietarioLogueado;
```

Esta variable se gestiona desde `PropietarioController` y permite:

* Saber si hay sesión iniciada (`haySesionIniciada()`)
* Acceder al usuario actual (`getPropietarioLogueado()`)
* Cerrar sesión (`logout()`)

---

 4. Funcionalidades asociadas al usuario logueado

Una vez autenticado, el usuario puede acceder a funcionalidades personalizadas:

 4.1 Ver su villa

Cada propietario está asociado a una villa.
Tras el login, el sistema obtiene automáticamente el `idVilla` del usuario y consulta la base de datos para mostrar:

* Código de villa
* Complejo
* Ubicación

Esto evita que el usuario tenga que introducir datos manualmente.

---

 4.2 Ver sus incidencias

Se ha implementado una funcionalidad que permite al usuario visualizar únicamente las incidencias asociadas a su villa.

Para ello:

* Se utiliza el `idVilla` del usuario logueado
* Se consulta la base de datos mediante `IncidenciaDAO.listarPorVilla()`

Esto introduce un primer nivel de control de acceso a los datos.

---

 4.3 Crear incidencias

El usuario puede crear incidencias directamente sin necesidad de indicar la villa manualmente.

El sistema:

* Obtiene automáticamente el `idVilla` del usuario logueado
* Asocia la incidencia a dicha villa
* Inserta el registro en base de datos

Esto mejora la usabilidad y evita errores de introducción de datos.

---

 5. Control de acceso

Se ha implementado control básico de acceso en el menú mediante comprobaciones como:

```java
if (propietarioController.haySesionIniciada())
```

De este modo:

* Las funcionalidades sensibles solo están disponibles si hay sesión iniciada
* Se evita el acceso no autorizado

---

 6. Diferenciación de roles

El sistema contempla dos tipos de usuario:

* ADMIN
* USER

 7. Mejora sobre el modelo inicial

Con esta mejora:

* Se automatiza la asociación usuario–villa
* Se filtran los datos según el usuario
* Se mejora la coherencia del sistema

Esto supone un paso hacia una arquitectura más cercana a una aplicación real.

 8. Conclusión

La implementación del login por consola ha permitido:

* Introducir autenticación en el sistema
* Simular sesiones de usuario
* Asociar datos a un usuario concreto
* Mejorar la lógica de negocio

- Tecnologías utilizadas
Java
JDBC
MySQL
IntelliJ IDEA
Git / GitHub