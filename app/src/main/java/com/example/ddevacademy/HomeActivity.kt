package com.example.ddevacademy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.view.View
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
        val tvSeminarEmpty = findViewById<TextView>(R.id.tvSeminarEmpty)
        val llSeminarSummary = findViewById<android.widget.LinearLayout>(R.id.llSeminarSummary)
        val tvLatestNameValue = findViewById<TextView>(R.id.tvLatestNameValue)
        val tvLatestEmailValue = findViewById<TextView>(R.id.tvLatestEmailValue)
        val tvLatestPhoneValue = findViewById<TextView>(R.id.tvLatestPhoneValue)
        val tvLatestGenderValue = findViewById<TextView>(R.id.tvLatestGenderValue)
        val tvLatestSeminarValue = findViewById<TextView>(R.id.tvLatestSeminarValue)
        val btnDaftarSeminar = findViewById<Button>(R.id.btnDaftarSeminar)
        val btnProfile = findViewById<Button>(R.id.btnProfile)
        val btnAbout = findViewById<Button>(R.id.btnAbout)
        val btnHelp = findViewById<Button>(R.id.btnHelp)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        // Mengatur padding otomatis untuk menghindari status bar/navigation bar
        // Ini memastikan ScrollView kamu benar-benar "lega" di bagian atas
        val mainView = findViewById<android.widget.ScrollView>(R.id.main_scroll_view)
        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom)
            insets
        }

        // 3. Set Nama User
        val username = intent.getStringExtra("username").orEmpty()
        tvUserName.text = if (username.isBlank()) getString(R.string.default_student_name) else username

        fun renderLatestSeminar() {
            val seminarResult = SeminarSessionStore.latestSeminarResult
            if (seminarResult == null) {
                tvSeminarEmpty.visibility = View.VISIBLE
                llSeminarSummary.visibility = View.GONE
                return
            }

            tvSeminarEmpty.visibility = View.GONE
            llSeminarSummary.visibility = View.VISIBLE
            tvLatestNameValue.text = seminarResult.name
            tvLatestEmailValue.text = seminarResult.email
            tvLatestPhoneValue.text = seminarResult.phone
            tvLatestGenderValue.text = seminarResult.gender
            tvLatestSeminarValue.text = seminarResult.seminar
        }

        renderLatestSeminar()

        btnDaftarSeminar.setOnClickListener {
            val intent = Intent(this, SeminarRegistrationActivity::class.java)
            intent.putExtra("username", tvUserName.text.toString())
            startActivity(intent)
        }

        btnProfile.setOnClickListener {
            Toast.makeText(this, getString(R.string.profil_segera), Toast.LENGTH_SHORT).show()
        }

        btnAbout.setOnClickListener {
            Toast.makeText(this, getString(R.string.tentang_aplikasi), Toast.LENGTH_SHORT).show()
        }

        btnHelp.setOnClickListener {
            Toast.makeText(this, getString(R.string.bantuan_segera), Toast.LENGTH_SHORT).show()
        }

        // 4. Logika Tombol Logout
        btnLogout.setOnClickListener {
            SeminarSessionStore.clear()
            Toast.makeText(this, getString(R.string.berhasil_keluar), Toast.LENGTH_SHORT).show()

            // Kembali ke MainActivity (Halaman Login)
            val intent = Intent(this, MainActivity::class.java)

            // FLAG_ACTIVITY_CLEAR_TASK akan menghapus semua riwayat halaman sebelumnya
            // Jadi user tidak bisa menekan tombol 'Back' untuk masuk lagi tanpa login
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
            finish()
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }

    override fun onResume() {
        super.onResume()
        val tvSeminarEmpty = findViewById<TextView>(R.id.tvSeminarEmpty)
        val llSeminarSummary = findViewById<android.widget.LinearLayout>(R.id.llSeminarSummary)
        val seminarResult = SeminarSessionStore.latestSeminarResult
        if (seminarResult == null) {
            tvSeminarEmpty.visibility = View.VISIBLE
            llSeminarSummary.visibility = View.GONE
        } else {
            findViewById<TextView>(R.id.tvLatestNameValue).text = seminarResult.name
            findViewById<TextView>(R.id.tvLatestEmailValue).text = seminarResult.email
            findViewById<TextView>(R.id.tvLatestPhoneValue).text = seminarResult.phone
            findViewById<TextView>(R.id.tvLatestGenderValue).text = seminarResult.gender
            findViewById<TextView>(R.id.tvLatestSeminarValue).text = seminarResult.seminar
            tvSeminarEmpty.visibility = View.GONE
            llSeminarSummary.visibility = View.VISIBLE
        }
    }
}