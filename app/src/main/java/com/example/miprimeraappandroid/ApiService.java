package com.example.miprimeraappandroid;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    // Rutas que ya teníamos
    @GET("api/movimientos-resumen")
    Call<List<Movimiento>> getMovimientos();

    @GET("productos")
    Call<List<Producto>> getProductos();

    // --- NUEVO ENDPOINT PARA LOGIN ---
    // Enviamos un Map (usuario, password) y recibimos un LoginResponse
    @POST("api/login")
    Call<LoginResponse> login(@Body Map<String, String> credenciales);
}