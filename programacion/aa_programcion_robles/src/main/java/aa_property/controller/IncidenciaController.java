package aa_property.controller;

import aa_property.model.Incidencia;
import aa_property.service.IncidenciaService;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class IncidenciaController {

    private final Scanner sc;
    private final IncidenciaService incidenciaService;

    public IncidenciaController(Scanner sc) {
        this.sc = sc;
        this.incidenciaService = new IncidenciaService();
    }

    public void crearIncidencia() {
        System.out.print("Título de la incidencia: ");
        String titulo = sc.nextLine();

        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();

        Date fechaCreacion;
        try {
            System.out.print("Fecha de creación (YYYY-MM-DD): ");
            fechaCreacion = Date.valueOf(sc.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println("Fecha no válida.");
            return;
        }

        System.out.print("Estado (PENDIENTE / EN_PROCESO / RESUELTA): ");
        String estado = sc.nextLine();

        int idVilla = leerEntero("ID de la villa: ");

        boolean creada = incidenciaService.crearIncidencia(titulo, descripcion, fechaCreacion, estado, idVilla);

        if (creada) {
            System.out.println("Incidencia creada correctamente.");
        } else {
            System.out.println("No se pudo crear la incidencia.");
        }
    }

    public void buscarIncidenciaPorId() {
        int id = leerEntero("Introduce ID de la incidencia: ");
        Incidencia incidencia = incidenciaService.buscarPorId(id);

        if (incidencia != null) {
            System.out.println(incidencia);
        } else {
            System.out.println("No se encontró la incidencia.");
        }
    }

    public void listarIncidenciasPorVilla() {
        int idVilla = leerEntero("Introduce ID de la villa: ");
        List<Incidencia> incidencias = incidenciaService.listarPorVilla(idVilla);

        if (incidencias.isEmpty()) {
            System.out.println("No hay incidencias para esa villa.");
        } else {
            System.out.println("\n--- INCIDENCIAS DE LA VILLA ---");
            for (Incidencia incidencia : incidencias) {
                System.out.println(incidencia);
            }
        }
    }

    public void cambiarEstadoIncidencia() {
        int id = leerEntero("ID de la incidencia: ");

        System.out.print("Nuevo estado (PENDIENTE / EN_PROCESO / RESUELTA): ");
        String nuevoEstado = sc.nextLine();

        boolean actualizada = incidenciaService.actualizarEstado(id, nuevoEstado);

        if (actualizada) {
            System.out.println("Estado actualizado correctamente.");
        } else {
            System.out.println("No se pudo actualizar el estado.");
        }
    }

    public void eliminarIncidencia() {
        int id = leerEntero("ID de la incidencia a eliminar: ");
        boolean eliminada = incidenciaService.eliminarIncidencia(id);

        if (eliminada) {
            System.out.println("Incidencia eliminada correctamente.");
        } else {
            System.out.println("Error al eliminar la incidencia.");
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
}
