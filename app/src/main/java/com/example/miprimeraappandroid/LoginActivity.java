package com.example.miprimeraappandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsuario, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 1. Vincular componentes XML
        etUsuario = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        // 2. Evento del botón de Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarLogin();
            }
        });
    }

    private void realizarLogin() {
        String user = etUsuario.getText().toString().trim();
        String pass = etPassword.getText().toString().trim();

        // Validación básica en el cliente
        if (user.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Por favor completa ambos campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // 3. Configurar Retrofit con tu IP LOCAL .66
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.66:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        // Preparamos los datos a enviar
        Map<String, String> credenciales = new HashMap<>();
        credenciales.put("usuario", user);
        credenciales.put("password", pass);

        // 4. Hacer la petición POST al servidor
        apiService.login(credenciales).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse res = response.body();

                    if (res.isSuccess()) {
                        Toast.makeText(LoginActivity.this, "¡Bienvenido, " + user + "!", Toast.LENGTH_SHORT).show();

                        // NAVEGACIÓN EXITOSA: Ir al Dashboard (MainActivity)
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish(); // Cerramos el Login para no volver atrás
                    } else {
                        // El servidor denegó el acceso
                        Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Error de servidor. Revisa credenciales.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Error de red (Servidor apagado o IP incorrecta)
                Toast.makeText(LoginActivity.this, "Error de conexión: Revisa el servidor Node.js", Toast.LENGTH_LONG).show();
            }
        });
    }
}