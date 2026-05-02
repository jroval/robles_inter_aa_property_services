package aa_property.dao;

import aa_property.model.Villa;
import aa_property.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VillaDAO {

    public boolean insertarVilla(Villa villa) {
        String sql = "INSERT INTO villas (codigo_villa, complejo, ubicacion, id_propietario) VALUES (?, ?, ?, ?)";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setString(1, villa.getCodigoVilla());
            stmt.setString(2, villa.getComplejo());
            stmt.setString(3, villa.getUbicacion());

            if (villa.getIdPropietario() != null) {
                stmt.setInt(4, villa.getIdPropietario());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar villa: " + e.getMessage());
            return false;
        }
    }

    public List<Villa> listarVillas() {
        List<Villa> lista = new ArrayList<>();
        String sql = "SELECT * FROM villas";

        try (Connection conexion = ConexionBD.conectar();
             Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Villa villa = new Villa(
                        rs.getInt("id_villa"),
                        rs.getString("codigo_villa"),
                        rs.getString("complejo"),
                        rs.getString("ubicacion"),
                        (Integer) rs.getObject("id_propietario")
                );
                lista.add(villa);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar villas: " + e.getMessage());
        }

        return lista;
    }

    public Villa buscarVillaPorId(int idVilla) {
        Villa villa = null;

        String sql = "SELECT * FROM villas WHERE id_villa = ? AND activa = true";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setInt(1, idVilla);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                villa = new Villa(
                        rs.getInt("id_villa"),
                        rs.getString("codigo_villa"),
                        rs.getString("complejo"),
                        rs.getString("ubicacion"),
                        null // 👈 ya no existe id_propietario
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar villa: " + e.getMessage());
        }

        return villa;
    }

    public boolean actualizarVilla(Villa villa) {
        String sql = "UPDATE villas SET codigo_villa=?, complejo=?, ubicacion=?, id_propietario=? WHERE id_villa=?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setString(1, villa.getCodigoVilla());
            stmt.setString(2, villa.getComplejo());
            stmt.setString(3, villa.getUbicacion());

            if (villa.getIdPropietario() != null) {
                stmt.setInt(4, villa.getIdPropietario());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }

            stmt.setInt(5, villa.getIdVilla());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar villa: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarVilla(int id) {
        String sql = "DELETE FROM villas WHERE id_villa = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar villa: " + e.getMessage());
            return false;
        }
    }


}
