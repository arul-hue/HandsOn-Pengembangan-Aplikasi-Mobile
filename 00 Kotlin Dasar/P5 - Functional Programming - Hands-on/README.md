# Hands-on Pertemuan 5 (Kotlin Dasar) — Functional Programming

Proyek Kotlin/JVM untuk 3 latihan praktikum Pertemuan 5 track **Kotlin Dasar** (IF25-22017 — Pengembangan Aplikasi Mobile, ITERA). Materi ini melengkapi pertemuan utama mata kuliah dengan dasar-dasar bahasa Kotlin.

## Capaian Pembelajaran

- Memahami higher-order function (fungsi yang menerima/mengembalikan fungsi lain)
- Membedakan dan menggunakan lambda expression dan function reference (`::fungsi`)
- Menjelaskan konsep closure dan bagaimana ia menangkap (capture) variabel dari scope luar
- Menerapkan gaya functional (`map`, `filter`, komposisi fungsi) sebagai alternatif loop konvensional

## Cara Menjalankan

`Latihan.kt` dan `Solusi.kt` berada berdampingan di
`handson1-latihan/src/main/kotlin/` (demikian juga hands-on 2 dan 3).
Folder modul tetap memakai nama `-latihan`; folder modul `-solusi` sudah digabung.

Dari terminal dalam folder pertemuan ini (memerlukan Gradle 8.7 dan JDK 17/21):

```powershell
# Jalankan solusi hands-on 1 (pilihan bawaan).
gradle :handson1-latihan:run

# Jalankan latihan hands-on 1 setelah TODO dilengkapi.
gradle :handson1-latihan:run -Pversi=latihan

# Buat JAR solusi, termasuk dependency dan resource yang dibutuhkan.
gradle :handson1-latihan:jar
java -jar handson1-latihan/build/libs/handson1-latihan.jar
```

Ganti angka `1` menjadi `2` atau `3` sesuai hands-on. Gunakan `-Pversi=latihan`
pada task `jar` jika ingin membuat JAR latihan; file JAR sebelumnya ditimpa.
Gradle hanya mengompilasi satu versi, sehingga nama class/fungsi yang sama
serta TODO latihan tidak mengganggu kompilasi solusi. Pilihan bawaan di IDE
juga solusi; gunakan perintah terminal di atas untuk memilih latihan.

Untuk contoh tanpa library tambahan, bisa langsung memakai compiler Kotlin.
Buka terminal **di folder yang berisi `Solusi.kt`**, lalu:

```powershell
kotlinc Solusi.kt -include-runtime -d Solusi.jar
java -jar Solusi.jar
```

Sebutkan satu file, jangan `*.kt`. Cara singkat ini tidak otomatis memasukkan
library tambahan atau resource. Gunakan task Gradle `jar` untuk P7, P12,
dan P10 hands-on 1/3. Detail lengkap ada di `CARA_MENJALANKAN.md` pada folder utama.

## Daftar Latihan

### 1. `handson1-latihan` — file latihan dan solusi — Higher-Order Function
Lengkapi fungsi `calculate(a, b, operation)` yang menjalankan `operation` terhadap dua angka, lalu panggil dengan lambda berbeda (tambah, kurang, kali) tanpa mengubah signature fungsinya.

- **Konsep:** higher-order function, function type `(Int, Int) -> Int`, trailing lambda syntax.

### 2. `handson2-latihan` — file latihan dan solusi — Lambda & Function Reference
Bangun 2 pipeline `filter` + `map` terhadap list nama mahasiswa: satu memakai lambda inline, satu lagi memakai function reference (`::isEvenLength`, `::toUpper`). Hasil keduanya harus identik.

- **Konsep:** lambda expression, function reference (`::`), functions sebagai first-class citizen.

### 3. `handson3-latihan` — file latihan dan solusi — Closure (Counter Factory)
Implementasikan `makeCounter(): () -> Int` yang mengembalikan closure — sebuah lambda yang mengingat (capture) variabel `count` di luar scope `makeCounter`, dan bertambah setiap kali dipanggil.

- **Catatan:** file `Latihan.kt` di modul ini **sengaja tidak bisa di-compile** sebelum kamu melengkapi TODO — itu bagian dari latihan.
- **Konsep:** closure, variable capturing, state tersembunyi pada fungsi.

## Troubleshooting

| Error / Kebingungan | Penyebab | Solusi |
|---|---|---|
| `A function type was expected` | Mengirim value biasa ke parameter yang bertipe fungsi, misal `calculate(10, 4, 14)` | Kirim lambda atau function reference: `calculate(10, 4) { a, b -> a + b }` atau `calculate(10, 4, ::tambah)` |
| Function reference tidak match | `::namaFungsi` tidak cocok dengan function type yang diharapkan (parameter/return type beda) | Pastikan signature fungsi persis sama dengan tipe yang diminta, misal `(String) -> Boolean` |
| Closure "mengingat" nilai lama / tidak berubah | Lupa bahwa setiap pemanggilan fungsi pembuat closure (mis. `makeCounter()`) membuat variabel captured yang BARU dan independen | Gunakan `var` (bukan `val`) untuk variabel yang ingin diubah closure, dan simpan hasil `makeCounter()` sekali ke variabel jika ingin state yang sama dipakai berulang |

## Sumber Pustaka

- [Kotlin — Higher-order functions and lambdas](https://kotlinlang.org/docs/lambdas.html)
- [Kotlin — Functions](https://kotlinlang.org/docs/functions.html)
- [Arrow-kt — Functional companion to Kotlin's std lib](https://arrow-kt.io/) (untuk eksplorasi FP lebih dalam, disebutkan di slide P5)
