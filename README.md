
# Pengumpulan UTS Pemrograman Mobile

## Identitas Mahasiswa

- Nama: Arul Jeconiah Jaya Pratama
- NIM: 2305101002
- Kelas: 7B
- Program Studi: Teknik Informatika
- Mata Kuliah: Pemrograman Mobile
- Tahun Akademik: 2026/2027

## Deskripsi

Repository ini berisi source code, dokumentasi, dan hasil implementasi aplikasi mobile untuk pengerjaan Ujian Tengah Semester (UTS) mata kuliah Pemrograman Mobile.

## Deskripsi Pengerjaan UTS

Aplikasi **FrozenBox** merupakan aplikasi Android yang dibuat untuk membantu pengguna melihat dan memilih berbagai produk frozen food. Aplikasi ini menyediakan informasi produk berupa nama, gambar, harga, dan deskripsi produk.

Pengguna dapat melihat produk melalui halaman utama maupun kategori produk. Produk yang dipilih dapat dimasukkan ke keranjang belanja. Selanjutnya pengguna dapat mengatur jumlah produk dan melanjutkan ke halaman checkout untuk mengisi informasi penerima serta memilih jenis pengiriman.

Aplikasi ini dikembangkan menggunakan Android Studio dengan bahasa pemrograman Java dan XML sebagai tampilan antarmuka. Penyimpanan data keranjang menggunakan SharedPreferences sehingga data dapat disimpan secara lokal pada perangkat.

## Teknologi yang Digunakan

- Bahasa Pemrograman: Java
- Layout: XML
- Platform: Android Native
- Tools: Android Studio, Gradle (Kotlin DSL), Android Emulator, Git & GitHub
- Penyimpanan Data: SharedPreferences
- Database: Belum menggunakan database

## Fitur yang Diimplementasikan

1. **Halaman Home**: menampilkan halaman utama yang berisi informasi promosi, kategori, dan produk pilihan.
2. **Pencarian Produk**: membantu pengguna mencari produk frozen food berdasarkan nama produk.
3. **Kategori Produk**: menampilkan pilihan kategori produk seperti nugget, sosis, seafood, kentang, dan olahan ayam.
4. **Detail Produk**: menampilkan informasi produk berupa gambar, nama, harga, dan deskripsi.
5. **Keranjang Belanja**: menambahkan produk ke keranjang serta mengatur jumlah produk yang dipilih.
6. **Pengelolaan Keranjang**: mengubah jumlah produk, menghapus produk, dan menghitung total belanja.
7. **Checkout**: menyediakan halaman untuk mengisi nama penerima, nomor telepon, alamat, dan pilihan pengiriman.
8. **Navigasi Aplikasi**: menyediakan menu Home, Kategori, Keranjang, Pesanan, dan Profil.

## Struktur Folder

- `uts/`: folder utama yang berisi project dan file pendukung UTS.
- `uts/app/`: source code utama aplikasi Android.
- `uts/app/src/main/java/com/example/frozenfood/`: berisi file Java untuk mengatur aktivitas dan fungsi aplikasi.
- `uts/app/src/main/res/layout/`: berisi file XML untuk tampilan halaman aplikasi.
- `uts/app/src/main/res/drawable/`: berisi gambar produk, ikon, dan resource tampilan.
- `uts/app/src/main/AndroidManifest.xml`: konfigurasi aktivitas dan komponen aplikasi.

## Persyaratan Menjalankan Aplikasi

- Android Studio.
- Android SDK sesuai konfigurasi project.
- JDK yang kompatibel dengan versi Gradle project.
- Android Emulator atau perangkat Android.
- Koneksi internet untuk proses sinkronisasi Gradle pertama kali.

## Cara Menjalankan Aplikasi

1. Clone repository menggunakan perintah berikut:

   ```bash
   git clone https://github.com/Aruljjp23/2305101002-7B-PROJECT.git
   ```

2. Buka Android Studio.
3. Pilih **File > Open**.
4. Arahkan ke folder project Android di dalam folder `uts/`.
5. Tunggu proses Gradle Sync sampai selesai.
6. Jalankan Android Emulator atau hubungkan perangkat Android ke komputer.
7. Klik tombol **Run** untuk menjalankan aplikasi.

## Screenshot Hasil Implementasi

Screenshot diambil dari aplikasi FrozenBox yang dijalankan melalui Android Emulator. dan disimpan pada folder `uts/screenshots/`.

| Halaman | Berkas |
| --- | --- |
| Halaman utama (Home) | `halaman-utama.png` |
| Halaman kedua (Detail-Produk) | `halaman-kedua.png` |
| Halaman implementasi | `halaman-implementasi.png` |

## File APK

File APK tidak dilampirkan pada repository. APK dapat dibuat melalui Android Studio dengan memilih menu **Build > Build Bundle(s) / APK(s) > Build APK(s)**.

Hasil APK debug dapat ditemukan pada folder:

`app/build/outputs/apk/debug/app-debug.apk`

## Referensi

- Android Developers: https://developer.android.com/
- Android Studio: https://developer.android.com/studio
- Java Documentation: https://docs.oracle.com/en/java/
