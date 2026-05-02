package aa_property.util;

public class Validador {

    private Validador() {
    }

    public static boolean textoNoVacio(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    public static boolean emailValido(String email) {
        return textoNoVacio(email) && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public static boolean telefonoValido(String telefono) {
        return textoNoVacio(telefono) && telefono.matches("^\\d{9,15}$");
    }

    public static boolean estadoIncidenciaValido(String estado) {
        return textoNoVacio(estado) &&
                (estado.equalsIgnoreCase("PENDIENTE")
                        || estado.equalsIgnoreCase("EN_PROCESO")
                        || estado.equalsIgnoreCase("RESUELTA"));
    }
}
