package com.example.miprimeraappandroid;

// Esta importación es la que hace la magia
import com.google.gson.annotations.SerializedName;

public class Movimiento {

    @SerializedName("fecha")
    private String fecha;

    @SerializedName("producto") // Coincide con el nombre en tu SELECT de Node.js
    private String producto;

    @SerializedName("tipo") // Coincide con el tipo en tu SELECT de Node.js
    private String tipo;

    @SerializedName("cantidad")
    private int cantidad;

    // Constructor
    public Movimiento(String fecha, String producto, String tipo, int cantidad) {
        this.fecha = fecha;
        this.producto = producto;
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    // Getters necesarios para que el MovimientoAdapter pueda leer los datos
    public String getFecha() { return fecha; }
    public String getProducto() { return producto; }
    public String getTipo() { return tipo; }
    public int getCantidad() { return cantidad; }
}