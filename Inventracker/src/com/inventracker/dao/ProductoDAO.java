package com.inventracker.dao;

import com.inventracker.model.producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public boolean registrar(producto pro) {
        String sql = "INSERT INTO producto (nombre, precio, stock, id_marca, id_proveedor) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            // Usando los nombres de tu diagrama de clases
            ps.setString(1, pro.getNombreProducto()); 
            ps.setDouble(2, pro.getPrecioVenta());
            ps.setInt(3, pro.getStockActual());
            ps.setInt(4, pro.getIdCategoria()); // Ajustado según tu diseño
            ps.setInt(5, 1); // Valor temporal para proveedor si no lo tienes en el objeto
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error SQL al registrar: " + e.getMessage());
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
                p.setIdProducto(rs.getInt("id_producto"));
                p.setNombreProducto(rs.getString("nombre"));
                p.setPrecioVenta(rs.getDouble("precio"));
                p.setStockActual(rs.getInt("stock"));
                // p.setIdCategoria(rs.getInt("id_marca")); // Opcional según necesites
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }
}