package com.example.miprimeraappandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

public class ProductosActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private RecyclerView rvProductos;
    private TextView tvTot, tvBajo;
    // private ProductoAdapter adaptador; // Se activará cuando crees la clase ProductoAdapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        // 1. CONFIGURACIÓN DEL TOOLBAR Y MENÚ LATERAL
        Toolbar toolbar = findViewById(R.id.toolbarProductos);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view); // Revisa que este ID coincida en activity_productos.xml

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // 2. LÓGICA DE NAVEGACIÓN (Para volver al Dashboard)
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_dashboard) {
                Intent intent = new Intent(ProductosActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            } else if (id == R.id.nav_productos) {
                drawerLayout.closeDrawers();
            } else if (id == R.id.nav_salir) {
                finishAffinity();
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // 3. VINCULAR ELEMENTOS DE LA INTERFAZ
        tvTot = findViewById(R.id.tvTotProd);
        tvBajo = findViewById(R.id.tvStockBajo);
        rvProductos = findViewById(R.id.rvProductosList);
        rvProductos.setLayoutManager(new LinearLayoutManager(this));

        // 4. BOTÓN AGREGAR
        Button btnAdd = findViewById(R.id.btnAgregarProd);
        btnAdd.setOnClickListener(v -> Toast.makeText(this, "Módulo Agregar: Implementación futura", Toast.LENGTH_SHORT).show());

        // 5. LLAMAR A LA CARGA DE DATOS
        cargarProductosDesdeApi();
    }

    private void cargarProductosDesdeApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.66:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        apiService.getProductos().enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // ¡AHORA SÍ FUNCIONA!
                    ProductoAdapter adaptador = new ProductoAdapter(response.body());
                    rvProductos.setAdapter(adaptador);
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Log.e("API_ERROR", "error en productos: " + t.getMessage());
                Toast.makeText(ProductosActivity.this, "Error de conexión con SQL", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
