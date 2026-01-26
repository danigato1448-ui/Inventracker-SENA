package com.inventracker.dao;

import com.inventracker.model.producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public boolean registrar(producto pro) {
        // Nota: id_producto es AUTO_INCREMENT, no se envía en el INSERT
        String sql = "INSERT INTO producto (nombre, precio, stock, id_marca, id_proveedor) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, pro.getNombre());
            ps.setDouble(2, pro.getPrecio());
            ps.setInt(3, pro.getStock());
            ps.setInt(4, pro.getId_marca());
            ps.setInt(5, pro.getId_proveedor());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
            return false;
        }
    }

    public List<producto> listar() {
        List<producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM producto";
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                producto p = new producto();
                p.setId_producto(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
                p.setId_marca(rs.getInt("id_marca"));
                p.setId_proveedor(rs.getInt("id_proveedor"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM producto WHERE id_producto = ?";
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}
