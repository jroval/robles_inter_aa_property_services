package aa_property.service;

import aa_property.dao.IncidenciaDAO;
import aa_property.dao.VillaDAO;
import aa_property.model.Incidencia;
import aa_property.util.Validador;

import java.sql.Date;
import java.util.List;

public class IncidenciaService {

    private final IncidenciaDAO incidenciaDAO;
    private final VillaDAO villaDAO;

    public IncidenciaService() {
        this.incidenciaDAO = new IncidenciaDAO();
        this.villaDAO = new VillaDAO();
    }

    public boolean crearIncidencia(String titulo, String descripcion, Date fechaCreacion, String estado, int idVilla) {
        if (!Validador.textoNoVacio(titulo) ||
                !Validador.textoNoVacio(descripcion) ||
                fechaCreacion == null ||
                !Validador.estadoIncidenciaValido(estado) ||
                idVilla <= 0) {
            return false;
        }

        if (villaDAO.buscarVillaPorId(idVilla) == null) {
            return false;
        }

        Incidencia incidencia = new Incidencia(titulo, descripcion, fechaCreacion, estado, idVilla);
        return incidenciaDAO.insertarIncidencia(incidencia);
    }


    public Incidencia buscarPorId(int id) {
        if (id <= 0) {
            return null;
        }
        return incidenciaDAO.buscarIncidenciaPorId(id);
    }

    public List<Incidencia> listarPorVilla(int idVilla) {
        if (idVilla <= 0) {
            return List.of();
        }
        return incidenciaDAO.listarIncidenciasPorVilla(idVilla);
    }

    public boolean actualizarEstado(int idIncidencia, String nuevoEstado) {
        if (idIncidencia <= 0 || !Validador.estadoIncidenciaValido(nuevoEstado)) {
            return false;
        }

        Incidencia incidencia = incidenciaDAO.buscarIncidenciaPorId(idIncidencia);
        if (incidencia == null) {
            return false;
        }

        return incidenciaDAO.actualizarEstadoIncidencia(idIncidencia, nuevoEstado);
    }

    public boolean eliminarIncidencia(int id) {
        if (id <= 0) {
            return false;
        }
        return incidenciaDAO.eliminarIncidencia(id);
    }
}
