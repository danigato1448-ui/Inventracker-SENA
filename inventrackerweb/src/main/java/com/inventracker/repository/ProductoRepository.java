package com.inventracker.repository;

import com.inventracker.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    // No escribimos código aquí, Spring Boot ya nos da los métodos para 
    // Guardar, Listar, Editar y Eliminar automáticamente.
}