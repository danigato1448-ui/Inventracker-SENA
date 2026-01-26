package com.inventracker.model;

public class producto {
    private int id_producto;
    private String nombre;
    private double precio;
    private int stock;
    private int id_marca;
    private int id_proveedor;

    public producto() {}

    // Getters y Setters
    public int getId_producto() { return id_producto; }
    public void setId_producto(int id_producto) { this.id_producto = id_producto; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public int getId_marca() { return id_marca; }
    public void setId_marca(int id_marca) { this.id_marca = id_marca; }

    public int getId_proveedor() { return id_proveedor; }
    public void setId_proveedor(int id_proveedor) { this.id_proveedor = id_proveedor; }
}
