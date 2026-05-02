package aa_property.service;

import aa_property.dao.PropietarioDAO;
import aa_property.dao.VillaDAO;
import aa_property.model.Villa;
import aa_property.util.Validador;

import java.util.List;

public class VillaService {

    private final VillaDAO villaDAO;
    private final PropietarioDAO propietarioDAO;

    public VillaService() {
        this.villaDAO = new VillaDAO();
        this.propietarioDAO = new PropietarioDAO();
    }

    public boolean crearVilla(String codigoVilla, String complejo, String ubicacion, Integer idPropietario) {
        if (!Validador.textoNoVacio(codigoVilla) ||
                !Validador.textoNoVacio(complejo) ||
                !Validador.textoNoVacio(ubicacion)) {
            return false;
        }

        if (idPropietario != null && propietarioDAO.buscarPorId(idPropietario) == null) {
            return false;
        }

        Villa villa = new Villa(codigoVilla, complejo, ubicacion, idPropietario);
        return villaDAO.insertarVilla(villa);
    }

    public List<Villa> listarVillas() {
        return villaDAO.listarVillas();
    }

    public Villa buscarPorId(int id) {
        if (id <= 0) {
            return null;
        }
        return villaDAO.buscarVillaPorId(id);
    }

    public boolean actualizarVilla(int id, String codigoVilla, String complejo, String ubicacion, Integer idPropietario) {
        if (id <= 0 ||
                !Validador.textoNoVacio(codigoVilla) ||
                !Validador.textoNoVacio(complejo) ||
                !Validador.textoNoVacio(ubicacion)) {
            return false;
        }

        if (idPropietario != null && propietarioDAO.buscarPorId(idPropietario) == null) {
            return false;
        }

        Villa villa = villaDAO.buscarVillaPorId(id);
        if (villa == null) {
            return false;
        }

        villa.setCodigoVilla(codigoVilla);
        villa.setComplejo(complejo);
        villa.setUbicacion(ubicacion);
        villa.setIdPropietario(idPropietario);

        return villaDAO.actualizarVilla(villa);
    }

    public boolean eliminarVilla(int id) {
        if (id <= 0) {
            return false;
        }
        return villaDAO.eliminarVilla(id);
    }
}
