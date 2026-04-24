package com.example.ddevacademy

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Memastikan tampilan layar penuh
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Mengatur padding agar tidak tertutup notch (PENTING!)
        val mainView = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Inisialisasi View
        val etUsername = findViewById<TextInputEditText>(R.id.etUsername)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val btnLogin = findViewById<MaterialButton>(R.id.btnLogin)
        val btnRegister = findViewById<MaterialButton>(R.id.btnRegister)

        // 2. Logika Tombol Login
        btnLogin.setOnClickListener {
            val username = etUsername.text?.toString().orEmpty().trim()
            val password = etPassword.text?.toString().orEmpty().trim()

            if (username.isBlank() || password.isBlank()) {
                Toast.makeText(this, getString(R.string.login_field_wajib), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Contoh login sederhana
            if (username == "admin" && password == "12345") {
                Toast.makeText(this, getString(R.string.login_berhasil), Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("username", username)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, getString(R.string.login_gagal), Toast.LENGTH_SHORT).show()
            }
        }

        // 3. Logika Tombol Register
        btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}