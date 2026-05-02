package aa_property.controller;

import aa_property.model.Propietario;
import aa_property.service.PropietarioService;
import aa_property.dao.PropietarioDAO;
import aa_property.dao.IncidenciaDAO;
import aa_property.model.Incidencia;
import java.util.List;
import java.util.Scanner;
import aa_property.dao.VillaDAO;
import aa_property.model.Villa;
import aa_property.service.IncidenciaService;

public class PropietarioController {

    private final Scanner sc;
    private final PropietarioService propietarioService;
    private Propietario propietarioLogueado;

    public PropietarioController(Scanner sc) {
        this.sc = sc;
        this.propietarioService = new PropietarioService();
    }

    public void anadirPropietario() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellidos: ");
        String apellidos = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();

        boolean insertado = propietarioService.crearPropietario(nombre, apellidos, email, telefono);

        if (insertado) {
            System.out.println("Propietario añadido correctamente.");
        } else {
            System.out.println("No se pudo añadir el propietario.");
        }
    }

    public void listarPropietarios() {
        List<Propietario> lista = propietarioService.listarPropietarios();

        if (lista.isEmpty()) {
            System.out.println("No hay propietarios registrados.");
        } else {
            System.out.println("\n--- LISTADO DE PROPIETARIOS ---");
            for (Propietario p : lista) {
                System.out.println(p);
            }
        }
    }

    public void buscarPropietarioPorId() {
        int id = leerEntero("Introduce ID: ");
        Propietario propietario = propietarioService.buscarPorId(id);

        if (propietario != null) {
            System.out.println(propietario);
        } else {
            System.out.println("No encontrado.");
        }
    }

    public void actualizarPropietario() {
        int id = leerEntero("ID del propietario a actualizar: ");
        Propietario propietario = propietarioService.buscarPorId(id);

        if (propietario == null) {
            System.out.println("No existe ese propietario.");
            return;
        }

        System.out.print("Nuevo nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Nuevo apellido: ");
        String apellidos = sc.nextLine();

        System.out.print("Nuevo email: ");
        String email = sc.nextLine();

        System.out.print("Nuevo teléfono: ");
        String telefono = sc.nextLine();

        boolean actualizado = propietarioService.actualizarPropietario(id, nombre, apellidos, email, telefono);

        if (actualizado) {
            System.out.println("Actualizado correctamente.");
        } else {
            System.out.println("Error al actualizar.");
        }
    }

    public void eliminarPropietario() {
        int id = leerEntero("ID del propietario a eliminar: ");
        boolean eliminado = propietarioService.eliminarPropietario(id);

        if (eliminado) {
            System.out.println("Eliminado correctamente.");
        } else {
            System.out.println("Error al eliminar.");
        }
    }

    private int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debes introducir un número válido.");
            }
        }
    }
    public void login() {
        System.out.println("\n--- LOGIN ---");

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Contraseña: ");
        String password = sc.nextLine();

        PropietarioDAO propietarioDAO = new PropietarioDAO();
        Propietario propietario = propietarioDAO.login(email, password);

        if (propietario != null) {
            propietarioLogueado = propietario;
            System.out.println("Login correcto.");
            System.out.println("Bienvenido: " + propietario.getEmail());

            if ("ADMIN".equalsIgnoreCase(propietario.getRol())) {
                System.out.println("Acceso como administrador.");
            } else {
                System.out.println("Acceso como usuario.");
                System.out.println("ID Villa: " + propietario.getIdVilla());
            }

        } else {
            System.out.println("Email o contraseña incorrectos.");
        }
    }
    public boolean haySesionIniciada() {
        return propietarioLogueado != null;
    }
    public Propietario getPropietarioLogueado() {
        return propietarioLogueado;
    }
    public void logout() {
        if (propietarioLogueado != null) {
            System.out.println("Sesión cerrada de: " + propietarioLogueado.getEmail());
            propietarioLogueado = null;
        } else {
            System.out.println("No hay ninguna sesión iniciada.");
        }
    }
    public void mostrarMiVilla() {
        if (propietarioLogueado == null) {
            System.out.println("Debes iniciar sesión primero.");
            return;
        }

        VillaDAO villaDAO = new VillaDAO();
        Villa villa = villaDAO.buscarVillaPorId(propietarioLogueado.getIdVilla().intValue());

        if (villa != null) {
            System.out.println("\n--- MI VILLA ---");
            System.out.println("ID: " + villa.getIdVilla());
            System.out.println("Código: " + villa.getCodigoVilla());
            System.out.println("Complejo: " + villa.getComplejo());
            System.out.println("Ubicación: " + villa.getUbicacion());
        } else {
            System.out.println("No se encontró la villa del usuario.");
        }
    }
    public void mostrarMisIncidencias() {
        if (propietarioLogueado == null) {
            System.out.println("Debes iniciar sesión primero.");
            return;
        }

        IncidenciaDAO incidenciaDAO = new IncidenciaDAO();
        List<Incidencia> incidencias = incidenciaDAO.listarPorVilla(propietarioLogueado.getIdVilla().intValue());

        System.out.println("\n--- MIS INCIDENCIAS ---");

        if (incidencias.isEmpty()) {
            System.out.println("No tienes incidencias registradas.");
            return;
        }

        for (Incidencia incidencia : incidencias) {
            System.out.println("ID: " + incidencia.getIdIncidencia());
            System.out.println("Título: " + incidencia.getTitulo());
            System.out.println("Descripción: " + incidencia.getDescripcion());
            System.out.println("Fecha: " + incidencia.getFechaCreacion());
            System.out.println("Estado: " + incidencia.getEstado());
            System.out.println("Villa ID: " + incidencia.getIdVilla());
            System.out.println("---------------------------");
        }
    }
    public void crearIncidencia() {
        if (propietarioLogueado == null) {
            System.out.println("Debes iniciar sesión primero.");
            return;
        }

        System.out.println("\n--- CREAR INCIDENCIA ---");

        System.out.print("Título: ");
        String titulo = sc.nextLine();

        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();

        String estado = "pendiente";

        java.util.Date fecha = new java.util.Date();
        java.sql.Date fechaSql = new java.sql.Date(fecha.getTime());

        int idVilla = propietarioLogueado.getIdVilla().intValue();

        IncidenciaService incidenciaService = new IncidenciaService();

        boolean creada = incidenciaService.crearIncidencia(
                titulo,
                descripcion,
                fechaSql,
                estado,
                idVilla
        );

        if (creada) {
            System.out.println("Incidencia creada correctamente.");
        } else {
            System.out.println("Error al crear la incidencia.");
        }
    }
}
