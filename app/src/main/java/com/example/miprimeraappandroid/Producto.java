package com.example.miprimeraappandroid;

public class Producto {
    // NOMBRES EXACTOS DE TU IMAGEN DE SQL
    private int id_producto;    // Cambiado: debe ser igual a tu columna #1
    private String nombre_producto;
    private String referencia;
    private String id_categoria;
    private double precio_venta;
    private int stock_actual;

    // Getters ajustados (No importa el nombre del método, importa la variable que retornan)
    public int getId() { return id_producto; }
    public String getNombre() { return nombre_producto; }
    public String getReferencia() { return referencia; }
    public String getCategoria() { return id_categoria; }
    public int getStock() { return stock_actual; }
    public double getPrecio() { return precio_venta; }
}