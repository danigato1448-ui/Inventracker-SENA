package com.inventracker.model;

public class producto {
    // Nombres ajustados según tu diagrama de clases 
    private int idProducto;
    private String nombreProducto;
    private String descripcion; // Atributo presente en tu diagrama 
    private double precioVenta;
    private int stockActual;
    private int idCategoria;    // Atributo presente en tu diagrama 

    public producto() {}

    // Getters y Setters con nombres estándar (camelCase) [cite: 56]
    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(double precioVenta) { this.precioVenta = precioVenta; }

    public int getStockActual() { return stockActual; }
    public void setStockActual(int stockActual) { this.stockActual = stockActual; }

    public int getIdCategoria() { return idCategoria; }
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }
}

