package com.inventracker.test;

import com.inventracker.dao.ProductoDAO;
import com.inventracker.model.producto;
import java.util.List;

public class principal {
    public static void main(String[] args) {
        ProductoDAO dao = new ProductoDAO();
        
        // 1. Probar Inserción 
        // Nota: Los nombres ahora coinciden con tu Diagrama de Clases
        producto nuevo = new producto();
        nuevo.setNombreProducto("Oso de Peluche Gigante"); // Antes setNombre
        nuevo.setPrecioVenta(45.50);                      // Antes setPrecio
        nuevo.setStockActual(20);                         // Antes setStock
        nuevo.setIdCategoria(1);                          // Antes setId_marca (según tu diagrama)

        if(dao.registrar(nuevo)){
            System.out.println("¡Producto registrado con éxito en Inventracker!");
        }

        // 2. Probar Consulta
        List<producto> lista = dao.listar();
        System.out.println("--- LISTA DE PRODUCTOS ---");
        for (producto p : lista) {
            // Actualizado para usar los nuevos getters
            System.out.println("ID: " + p.getIdProducto() + " - Nombre: " + p.getNombreProducto());
        }
    }
}