package aa_property.controller;

import java.util.Scanner;

public class MenuPrincipal {

    private final Scanner sc;
    private final PropietarioController propietarioController;
    private final VillaController villaController;
    private final IncidenciaController incidenciaController;

    public MenuPrincipal() {
        this.sc = new Scanner(System.in);
        this.propietarioController = new PropietarioController(sc);
        this.villaController = new VillaController(sc);
        this.incidenciaController = new IncidenciaController(sc);
    }

    public void iniciar() {
        int opcion;

        do {
            System.out.println("\n==============================");
            System.out.println(" AA PROPERTY SERVICES");
            System.out.println("==============================");

            System.out.println("\n--- PROPIETARIOS ---");
            System.out.println("1. Añadir propietario");
            System.out.println("2. Listar propietarios");
            System.out.println("3. Buscar propietario por ID");
            System.out.println("4. Actualizar propietario");
            System.out.println("5. Eliminar propietario");

            System.out.println("\n--- VILLAS ---");
            System.out.println("6. Añadir villa");
            System.out.println("7. Listar villas");
            System.out.println("8. Buscar villa por ID");
            System.out.println("9. Actualizar villa");
            System.out.println("10. Eliminar villa");

            System.out.println("\n--- INCIDENCIAS ADMIN ---");
            System.out.println("11. Buscar incidencia por ID");
            System.out.println("12. Cambiar estado de incidencia");
            System.out.println("13. Eliminar incidencia");

            System.out.println("\n--- ZONA USUARIO ---");
            System.out.println("17. Login");
            System.out.println("18. Logout");
            System.out.println("19. Ver mi villa");
            System.out.println("20. Ver mis incidencias");
            System.out.println("21. Crear incidencia");

            System.out.println("\n0. Salir");
            System.out.print("Elige una opción: ");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    propietarioController.anadirPropietario();
                    break;

                case 2:
                    propietarioController.listarPropietarios();
                    break;

                case 3:
                    propietarioController.buscarPropietarioPorId();
                    break;

                case 4:
                    propietarioController.actualizarPropietario();
                    break;

                case 5:
                    propietarioController.eliminarPropietario();
                    break;

                case 6:
                    villaController.anadirVilla();
                    break;

                case 7:
                    villaController.listarVillas();
                    break;

                case 8:
                    villaController.buscarVillaPorId();
                    break;

                case 9:
                    villaController.actualizarVilla();
                    break;

                case 10:
                    villaController.eliminarVilla();
                    break;

                case 11:
                    incidenciaController.buscarIncidenciaPorId();
                    break;

                case 12:
                    incidenciaController.cambiarEstadoIncidencia();
                    break;

                case 13:
                    incidenciaController.eliminarIncidencia();
                    break;

                case 17:
                    propietarioController.login();
                    break;

                case 18:
                    propietarioController.logout();
                    break;

                case 19:
                    propietarioController.mostrarMiVilla();
                    break;

                case 20:
                    propietarioController.mostrarMisIncidencias();
                    break;

                case 21:
                    propietarioController.crearIncidencia();
                    break;

                case 0:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);

        sc.close();
    }
}
