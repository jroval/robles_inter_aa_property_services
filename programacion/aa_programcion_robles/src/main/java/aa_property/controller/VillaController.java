package aa_property.controller;

import aa_property.model.Villa;
import aa_property.service.VillaService;

import java.util.List;
import java.util.Scanner;

public class VillaController {

    private final Scanner sc;
    private final VillaService villaService;

    public VillaController(Scanner sc) {
        this.sc = sc;
        this.villaService = new VillaService();
    }

    public void anadirVilla() {
        System.out.print("Código de la villa: ");
        String codigoVilla = sc.nextLine();

        System.out.print("Complejo: ");
        String complejo = sc.nextLine();

        System.out.print("Ubicación: ");
        String ubicacion = sc.nextLine();

        System.out.print("ID del propietario (o vacío si no tiene): ");
        String idPropietarioTexto = sc.nextLine();

        Integer idPropietario = null;
        if (!idPropietarioTexto.isEmpty()) {
            try {
                idPropietario = Integer.parseInt(idPropietarioTexto);
            } catch (NumberFormatException e) {
                System.out.println("El ID del propietario debe ser un número.");
                return;
            }
        }

        boolean insertada = villaService.crearVilla(codigoVilla, complejo, ubicacion, idPropietario);

        if (insertada) {
            System.out.println("Villa añadida correctamente.");
        } else {
            System.out.println("No se pudo añadir la villa.");
        }
    }

    public void listarVillas() {
        List<Villa> villas = villaService.listarVillas();

        if (villas.isEmpty()) {
            System.out.println("No hay villas registradas.");
        } else {
            System.out.println("\n--- LISTADO DE VILLAS ---");
            for (Villa v : villas) {
                System.out.println(v);
            }
        }
    }

    public void buscarVillaPorId() {
        int id = leerEntero("Introduce ID de la villa: ");
        Villa villa = villaService.buscarPorId(id);

        if (villa != null) {
            System.out.println(villa);
        } else {
            System.out.println("No se encontró la villa.");
        }
    }

    public void actualizarVilla() {
        int id = leerEntero("ID de la villa a actualizar: ");
        Villa villa = villaService.buscarPorId(id);

        if (villa == null) {
            System.out.println("No existe esa villa.");
            return;
        }

        System.out.print("Nuevo código de villa: ");
        String codigoVilla = sc.nextLine();

        System.out.print("Nuevo complejo: ");
        String complejo = sc.nextLine();

        System.out.print("Nueva ubicación: ");
        String ubicacion = sc.nextLine();

        System.out.print("Nuevo ID del propietario (o vacío si no tiene): ");
        String idPropietarioTexto = sc.nextLine();

        Integer idPropietario = null;
        if (!idPropietarioTexto.isEmpty()) {
            try {
                idPropietario = Integer.parseInt(idPropietarioTexto);
            } catch (NumberFormatException e) {
                System.out.println("El ID del propietario debe ser un número.");
                return;
            }
        }

        boolean actualizada = villaService.actualizarVilla(id, codigoVilla, complejo, ubicacion, idPropietario);

        if (actualizada) {
            System.out.println("Villa actualizada correctamente.");
        } else {
            System.out.println("Error al actualizar la villa.");
        }
    }

    public void eliminarVilla() {
        int id = leerEntero("ID de la villa a eliminar: ");
        boolean eliminada = villaService.eliminarVilla(id);

        if (eliminada) {
            System.out.println("Villa eliminada correctamente.");
        } else {
            System.out.println("Error al eliminar la villa.");
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
