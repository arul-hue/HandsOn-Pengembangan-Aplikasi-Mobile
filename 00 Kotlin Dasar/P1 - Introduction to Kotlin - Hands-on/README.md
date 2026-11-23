# Hands-on Pertemuan 1 — Introduction to Kotlin

Proyek Kotlin/JVM untuk 3 latihan praktikum Pertemuan 1 — Kotlin Dasar (IF25-22017 — Pengembangan Aplikasi Mobile, ITERA).

## Capaian Pembelajaran

- Memahami perbedaan `val` (immutable) dan `var` (mutable), serta `const val`
- Menulis fungsi Kotlin, termasuk single-expression function dan default parameter
- Menggunakan `if` dan `when` sebagai expression untuk mengontrol alur program
- Menggunakan loop (`for`, ranges, `step`) dan operasi dasar pada `List`
- Menerapkan null safety: safe call (`?.`), elvis operator (`?:`), dan `filterNotNull()`

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

### 1. `handson1-latihan` — file latihan dan solusi — Variabel, Fungsi & String Template
Lengkapi `describeProfile()` yang mencetak deskripsi profil menggunakan string template, default parameter, dan penanganan nilai `Int?` yang mungkin null.

- **Konsep:** `val`/`var`, string template (`$var`, `${expr}`), default parameter, named argument, safe call (`?.`), elvis operator (`?:`).

### 2. `handson2-latihan` — file latihan dan solusi — Control Flow dengan `when` Expression
Lengkapi `gradeOf()` yang mengklasifikasikan nilai ujian (0-100) menjadi huruf mutu A-E menggunakan `when` sebagai **expression** dalam single-expression function.

- **Catatan:** sebelum dilengkapi, fungsi ini memanggil `TODO()` — kode tetap **bisa di-compile**, tapi akan melempar `NotImplementedError` saat dijalankan.
- **Konsep:** `when` sebagai expression, range (`in 90..100`), single-expression function (`fun ... = ...`).

### 3. `handson3-latihan` — file latihan dan solusi — Loops, Ranges & Null Safety
Lengkapi `ScoreBoard` agar bisa menyaring skor valid (membuang `null`) yang memenuhi batas kelulusan, lalu cetak bilangan ganjil menggunakan range ber-`step`.

- **Catatan:** file `Latihan.kt` di modul ini **sengaja tidak bisa di-compile** sebelum kamu melengkapi TODO — itu bagian dari latihan.
- **Konsep:** `filterNotNull()`, `filter`, `sortedDescending()`, range dengan `step`, class dengan constructor property.

## Troubleshooting

| Error | Penyebab | Solusi |
|---|---|---|
| `Val cannot be reassigned` | Mencoba mengubah nilai `val` setelah diinisialisasi | Gunakan `var` jika nilai memang perlu diubah, atau pastikan hanya `val` yang dibaca |
| `Unresolved reference: skorValid` (handson3) | Property `skorValid` belum dideklarasikan (masih berupa komentar TODO) | Tambahkan `val skorValid: List<Int> = skorMentah.filterNotNull()` di dalam class |
| `An operation is not implemented: ...` (`NotImplementedError`) | Fungsi masih memanggil `TODO()` dan belum diimplementasikan | Ganti pemanggilan `TODO()` dengan implementasi sesuai instruksi komentar |

## Sumber Pustaka

- [Kotlin Basic Syntax](https://kotlinlang.org/docs/basic-syntax.html)
- [Kotlin Null Safety](https://kotlinlang.org/docs/null-safety.html)
- [Kotlin Control Flow (if/when)](https://kotlinlang.org/docs/control-flow.html)
