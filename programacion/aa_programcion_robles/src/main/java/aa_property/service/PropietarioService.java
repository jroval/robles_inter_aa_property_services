package aa_property.service;

import aa_property.dao.PropietarioDAO;
import aa_property.model.Propietario;
import aa_property.util.Validador;

import java.util.List;

public class PropietarioService {

    private final PropietarioDAO propietarioDAO;

    public PropietarioService() {
        this.propietarioDAO = new PropietarioDAO();
    }

    public boolean crearPropietario(String nombre, String apellidos, String email, String telefono) {
        if (!Validador.textoNoVacio(nombre) ||
                !Validador.textoNoVacio(apellidos) ||
                !Validador.emailValido(email) ||
                !Validador.telefonoValido(telefono)) {
            return false;
        }

        Propietario propietario = new Propietario(nombre, apellidos, email, telefono);
        return propietarioDAO.insertarPropietario(propietario);
    }

    public List<Propietario> listarPropietarios() {
        return propietarioDAO.listarPropietarios();
    }

    public Propietario buscarPorId(int id) {
        if (id <= 0) {
            return null;
        }
        return propietarioDAO.buscarPorId(id);
    }

    public boolean actualizarPropietario(int id, String nombre, String apellidos, String email, String telefono) {
        if (id <= 0 ||
                !Validador.textoNoVacio(nombre) ||
                !Validador.textoNoVacio(apellidos) ||
                !Validador.emailValido(email) ||
                !Validador.telefonoValido(telefono)) {
            return false;
        }

        Propietario propietario = propietarioDAO.buscarPorId(id);
        if (propietario == null) {
            return false;
        }

        propietario.setNombre(nombre);
        propietario.setApellidos(apellidos);
        propietario.setEmail(email);
        propietario.setTelefono(telefono);

        return propietarioDAO.actualizarPropietario(propietario);
    }

    public boolean eliminarPropietario(int id) {
        if (id <= 0) {
            return false;
        }
        return propietarioDAO.eliminarPropietario(id);
    }
}
