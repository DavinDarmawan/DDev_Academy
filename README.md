# DDev_Academy

Proyek ini adalah aplikasi yang dikembangkan menggunakan bahasa pemrograman **Kotlin** [2]. Repositori ini dibuat untuk pengerjaan tugas dari Davin Darmawan.

## 💻 Teknologi yang Digunakan

- **Bahasa Pemrograman:** Kotlin (100%) [2]
- **Build Tool:** Gradle (dikonfigurasi menggunakan skrip Kotlin DSL melalui `build.gradle.kts` dan `settings.gradle.kts`) [1]
- **Lingkungan Pengembangan:** Berisi direktori `.idea` dan modul `app`, yang umumnya digunakan pada proyek Android Studio atau IntelliJ IDEA [1].

## 📂 Struktur Proyek

Beberapa direktori dan berkas utama di dalam repositori ini meliputi [1]:

- `app/` — Modul utama kode aplikasi.
- `gradle/`, `gradlew`, `gradlew.bat` — Berkas _wrapper_ Gradle yang berfungsi untuk menjalankan _build_ tanpa harus menginstal Gradle secara manual.
- `build.gradle.kts` & `settings.gradle.kts` — Konfigurasi _build_ sistem Gradle.
- `gradle.properties` — Konfigurasi _properties_ untuk _environment_ Gradle.
- `.gitignore` — Berkas konfigurasi untuk mengabaikan direktori atau berkas tertentu agar tidak masuk ke dalam Git.

# DDev Academy 2 - UTS Seminar Registration

Aplikasi Android Kotlin untuk:

- Login & Register
- Pendaftaran Seminar Mahasiswa
- Validasi input real-time
- Dialog konfirmasi submit
- Halaman hasil pendaftaran

## Fitur Utama

### 1) Login

- User login sederhana (hardcode)
- Validasi field wajib

### 2) Register

- Form registrasi user
- Konfirmasi data

### 3) Home

- Menampilkan nama user
- Tombol ke form seminar
- Menampilkan hasil seminar terakhir (session sementara)

### 4) Form Pendaftaran Seminar

Input:

- Nama
- Email
- Nomor HP
- Jenis kelamin (RadioButton)
- Pilihan seminar (Spinner, hardcode >= 5)
- Persetujuan data (CheckBox)

Validasi:

- Semua field wajib
- Email harus mengandung "@"
- Nomor HP harus angka, 10-13 digit, diawali "08"
- Error muncul real-time saat user mengetik/mengubah input
- CheckBox wajib dicentang

### 5) Dialog Konfirmasi

Saat submit:

- Muncul dialog: "Apakah data yang Anda isi sudah benar?"
- Ya -> ke halaman hasil
- Tidak -> tetap di form

### 6) Halaman Hasil

Menampilkan:

- Nama
- Email
- Nomor HP
- Jenis Kelamin
- Seminar dipilih
- Pesan "Pendaftaran Berhasil"
- Tombol kembali ke home

## Video Penjelasan (Wajib UTS)

Link video: https://youtu.be/7SsnwzWoZFs

Isi video:

1. Penjelasan halaman Login
2. Penjelasan halaman Utama
3. Penjelasan Form Pendaftaran
4. Penjelasan Validasi (real-time error)
5. Penjelasan Dialog
6. Penjelasan Halaman Hasil
7. Penjelasan kode program

## Teknologi

- Kotlin
- Android SDK
- Material Design Components
- Gradle Kotlin DSL

## Kontributor

- Davin Darmawan
