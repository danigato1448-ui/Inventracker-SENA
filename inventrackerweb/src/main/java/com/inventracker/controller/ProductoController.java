package com.inventracker.controller;

import com.inventracker.model.Producto;
import com.inventracker.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos") // Esta será la URL para probarlo
@CrossOrigin(origins = "*") 
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    // Método para obtener todos los productos
    @GetMapping
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    // Método para guardar un producto nuevo
    @PostMapping
    public Producto guardar(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }
}
