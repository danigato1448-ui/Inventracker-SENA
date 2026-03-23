package com.example.miprimeraappandroid;

public class LoginResponse {
    // Estas variables deben coincidir con lo que envía tu Node.js: res.json({ success: true, message: ... })
    private boolean success;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}