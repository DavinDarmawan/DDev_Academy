package com.example.ddevacademy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Mengaktifkan fitur Edge-to-Edge agar fitsSystemWindows di XML berjalan
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        // 2. Inisialisasi View
        val tvUserName = findViewById<TextView>(R.id.tvUserName)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        // Mengatur padding otomatis untuk menghindari status bar/navigation bar
        // Ini memastikan ScrollView kamu benar-benar "lega" di bagian atas
        val mainView = findViewById<android.widget.ScrollView>(R.id.main_scroll_view) // Pastikan ID ini ada di XML ScrollView kamu
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom)
            insets
        }

        // 3. Set Nama User
        tvUserName.text = "DDev Academy Student"

        // 4. Logika Tombol Logout
        btnLogout.setOnClickListener {
            Toast.makeText(this, "Berhasil Keluar", Toast.LENGTH_SHORT).show()

            // Kembali ke MainActivity (Halaman Login)
            val intent = Intent(this, MainActivity::class.java)

            // FLAG_ACTIVITY_CLEAR_TASK akan menghapus semua riwayat halaman sebelumnya
            // Jadi user tidak bisa menekan tombol 'Back' untuk masuk lagi tanpa login
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
            finish()
        }
    }
}