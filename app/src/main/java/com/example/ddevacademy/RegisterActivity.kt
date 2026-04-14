package com.example.ddevacademy

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // 1. Inisialisasi View Lama (Pertahankan)
        val etFullName = findViewById<EditText>(R.id.etFullName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<TextInputEditText>(R.id.etPasswordReg)
        val etConfirmPassword = findViewById<TextInputEditText>(R.id.etConfirmPassword)
        val btnDoRegister = findViewById<Button>(R.id.btnDoRegister)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        // 2. Inisialisasi View Baru (Tambahan Tugas)
        val rgGender = findViewById<RadioGroup>(R.id.rgGender)
        val spProdi = findViewById<Spinner>(R.id.spProdi)
        val cbWeb = findViewById<CheckBox>(R.id.cbWeb)
        val cbMobile = findViewById<CheckBox>(R.id.cbMobile)
        val cbData = findViewById<CheckBox>(R.id.cbData)

        // 3. Setup Isi Spinner Program Studi
        val listProdi = arrayOf("Informatika", "Sistem Informasi", "Teknik Elektro")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listProdi)
        spProdi.adapter = adapter

        // 4. Logika Tombol Daftar
        btnDoRegister.setOnClickListener {
            val name = etFullName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val pass = etPassword.text.toString().trim()
            val confirmPass = etConfirmPassword.text.toString().trim()

            // Ambil data Jenis Kelamin
            val selectedGenderId = rgGender.checkedRadioButtonId
            val gender = if (selectedGenderId != -1) {
                findViewById<RadioButton>(selectedGenderId).text.toString()
            } else ""

            // Ambil data Program Studi
            val prodi = spProdi.selectedItem.toString()

            // Validasi: Cek apakah ada kolom yang kosong
            if (name.isEmpty() || email.isEmpty() || pass.isEmpty() || gender.isEmpty()) {
                Toast.makeText(this, "Semua kolom harus diisi!", Toast.LENGTH_SHORT).show()
            }
            else if (pass != confirmPass) {
                Toast.makeText(this, "Password tidak cocok!", Toast.LENGTH_SHORT).show()
            }
            else {
                // Tampilkan ALERT DIALOG Konfirmasi (Poin Tugas Dosen)
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Konfirmasi Data")
                builder.setMessage("Nama: $name\nJenis Kelamin: $gender\nProdi: $prodi\n\nApakah data sudah benar?")

                builder.setPositiveButton("Ya, Simpan") { _, _ ->
                    // Di sini nanti bisa ditambah insert ke SQLite
                    Toast.makeText(this, "Pendaftaran $name Berhasil!", Toast.LENGTH_SHORT).show()
                    finish()
                }

                builder.setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }

                val alertDialog = builder.create()
                alertDialog.show()
            }
        }

        // 5. Logika Tombol Loginn
        btnLogin.setOnClickListener {
            finish()
        }
    }
}