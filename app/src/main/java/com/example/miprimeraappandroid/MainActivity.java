package com.example.miprimeraappandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private RecyclerView rvMovimientos;
    private MovimientoAdapter miAdaptador;
    private TextView tvCantProductos, tvCantAlertas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. VINCULAR ELEMENTOS DE LA INTERFAZ
        tvCantProductos = findViewById(R.id.tvCantProductos);
        tvCantAlertas = findViewById(R.id.tvCantAlertas);
        rvMovimientos = findViewById(R.id.rvMovimientos);
        rvMovimientos.setLayoutManager(new LinearLayoutManager(this));

        // 2. CONFIGURACIÓN DEL MENU LATERAL (DRAWER)
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // 3. LÓGICA DE NAVEGACIÓN DEL MENÚ
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_salir) {
                finishAffinity();
            } else if (id == R.id.nav_productos) {
                // Navegación a la pantalla de Productos que diseñamos
                Intent intent = new Intent(MainActivity.this, ProductosActivity.class);
                startActivity(intent);
            } else if (id == R.id.nav_dashboard) {
                Toast.makeText(this, "Ya estás en el Dashboard", Toast.LENGTH_SHORT).show();
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // 4. CARGAR DATOS INICIALES (SQL vía API)
        cargarDatosDesdeApi();

        // 5. BOTÓN ACTUALIZAR
        Button btnActualizar = findViewById(R.id.btnActualizar);
        btnActualizar.setOnClickListener(v -> {
            Toast.makeText(this, "Actualizando desde la base de datos...", Toast.LENGTH_SHORT).show();
            cargarDatosDesdeApi();
        });
    }

    private void cargarDatosDesdeApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.66:3001/") // Tu IP de Node.js
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        apiService.getMovimientos().enqueue(new Callback<List<Movimiento>>() {
            @Override
            public void onResponse(Call<List<Movimiento>> call, Response<List<Movimiento>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // LLENA LA TABLA DE MOVIMIENTOS RECIENTES
                    miAdaptador = new MovimientoAdapter(response.body());
                    rvMovimientos.setAdapter(miAdaptador);

                    // ACTUALIZA LAS TARJETAS (Puedes poner valores fijos o traerlos de la API)
                    if (tvCantProductos != null) tvCantProductos.setText("156");
                    if (tvCantAlertas != null) tvCantAlertas.setText("23");
                }
            }

            @Override
            public void onFailure(Call<List<Movimiento>> call, Throwable t) {
                Log.e("API_ERROR", "Error: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Error: Revisa que el servidor Node.js esté activo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}