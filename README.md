# DDev_Academy

Proyek ini adalah aplikasi yang dikembangkan menggunakan bahasa pemrograman **Kotlin** [2]. Repositori ini dibuat untuk pengerjaan tugas dari Davin Darmawan (berdasarkan riwayat *commit* "Commit tugas") [1, 2].

## 💻 Teknologi yang Digunakan
- **Bahasa Pemrograman:** Kotlin (100%) [2]
- **Build Tool:** Gradle (dikonfigurasi menggunakan skrip Kotlin DSL melalui `build.gradle.kts` dan `settings.gradle.kts`) [1]
- **Lingkungan Pengembangan:** Berisi direktori `.idea` dan modul `app`, yang umumnya digunakan pada proyek Android Studio atau IntelliJ IDEA [1].

## 📂 Struktur Proyek
Beberapa direktori dan berkas utama di dalam repositori ini meliputi [1]:
- `app/` — Modul utama kode aplikasi.
- `gradle/`, `gradlew`, `gradlew.bat` — Berkas *wrapper* Gradle yang berfungsi untuk menjalankan *build* tanpa harus menginstal Gradle secara manual.
- `build.gradle.kts` & `settings.gradle.kts` — Konfigurasi *build* sistem Gradle.
- `gradle.properties` — Konfigurasi *properties* untuk *environment* Gradle.
- `.gitignore` — Berkas konfigurasi untuk mengabaikan direktori atau berkas tertentu agar tidak masuk ke dalam Git.

## 🚀 Cara Menjalankan Proyek
1. Lakukan *clone* pada repositori ini ke komputer Anda:
   ```bash
   git clone https://github.com/DavinDarmawan/DDev_Academy.git
Buka folder proyek menggunakan Android Studio atau IntelliJ IDEA.
Tunggu proses sinkronisasi Gradle hingga selesai.
Jalankan aplikasi (Run) melalui IDE tersebut menggunakan emulator atau perangkat fisik Anda.
👤 Kontributor
Davin Darmawan (@DavinDarmawan)