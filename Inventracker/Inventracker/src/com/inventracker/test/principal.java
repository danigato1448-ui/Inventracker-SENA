package com.inventracker.test;

import com.inventracker.dao.ProductoDAO;
import com.inventracker.model.producto;
import java.util.List;

public class principal {
    public static void main(String[] args) {
        ProductoDAO dao = new ProductoDAO();
        
        // 1. Probar Inserción 
        // IMPORTANTE: Asegúrate que en tu BD existan la marca 1 y el proveedor 1
        producto nuevo = new producto();
        nuevo.setNombre("Laptop Gamer");
        nuevo.setPrecio(1500.00);
        nuevo.setStock(15);
        nuevo.setId_marca(1); 
        nuevo.setId_proveedor(1);

        if(dao.registrar(nuevo)){
            System.out.println("¡Producto registrado con éxito!");
        }

        // 2. Probar Consulta
        List<producto> lista = dao.listar();
        for (producto p : lista) {
            System.out.println("ID: " + p.getId_producto() + " - Nombre: " + p.getNombre());
        }
    }
}
