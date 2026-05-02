package aa_property.dao;

import aa_property.model.Incidencia;
import aa_property.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IncidenciaDAO {

    public boolean insertarIncidencia(Incidencia incidencia) {
        String sql = "INSERT INTO incidencias (titulo, descripcion, fecha_creacion, estado, id_villa) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setString(1, incidencia.getTitulo());
            stmt.setString(2, incidencia.getDescripcion());
            stmt.setDate(3, incidencia.getFechaCreacion());
            stmt.setString(4, incidencia.getEstado());
            stmt.setInt(5, incidencia.getIdVilla());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar incidencia: " + e.getMessage());
            return false;
        }
    }

    public List<Incidencia> listarPorVilla(int idVilla) {
        List<Incidencia> lista = new ArrayList<>();

        String sql = "SELECT * FROM incidencias WHERE id_villa = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setInt(1, idVilla);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Incidencia incidencia = new Incidencia(
                        rs.getInt("id_incidencia"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getDate("fecha_creacion"),
                        rs.getString("estado"),
                        rs.getInt("id_villa")
                );
                lista.add(incidencia);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar incidencias: " + e.getMessage());
        }

        return lista;
    }

    public Incidencia buscarIncidenciaPorId(int id) {
        String sql = "SELECT * FROM incidencias WHERE id_incidencia = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Incidencia(
                        rs.getInt("id_incidencia"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getDate("fecha_creacion"),
                        rs.getString("estado"),
                        rs.getInt("id_villa")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar incidencia: " + e.getMessage());
        }

        return null;
    }

    public List<Incidencia> listarIncidenciasPorVilla(int idVilla) {
        List<Incidencia> lista = new ArrayList<>();
        String sql = "SELECT * FROM incidencias WHERE id_villa = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setInt(1, idVilla);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Incidencia incidencia = new Incidencia(
                        rs.getInt("id_incidencia"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getDate("fecha_creacion"),
                        rs.getString("estado"),
                        rs.getInt("id_villa")
                );
                lista.add(incidencia);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar incidencias por villa: " + e.getMessage());
        }

        return lista;
    }

    public boolean actualizarEstadoIncidencia(int idIncidencia, String nuevoEstado) {
        String sql = "UPDATE incidencias SET estado = ? WHERE id_incidencia = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setString(1, nuevoEstado);
            stmt.setInt(2, idIncidencia);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar estado de la incidencia: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarIncidencia(int id) {
        String sql = "DELETE FROM incidencias WHERE id_incidencia = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar incidencia: " + e.getMessage());
            return false;
        }
    }
}
