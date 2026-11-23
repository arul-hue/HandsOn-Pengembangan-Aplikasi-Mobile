# Hands-on Pertemuan 2 (Kotlin Dasar) — Object-Oriented Programming

Proyek Kotlin/JVM untuk 3 latihan praktikum materi **Kotlin Dasar — Object-Oriented Programming** (IF25-22017 — Pengembangan Aplikasi Mobile, ITERA). Ini adalah bagian dari track suplemen **"Kotlin Dasar"** (dasar-dasar bahasa Kotlin di luar Compose/KMP) dan **berbeda** dari folder `P2 - Advanced Kotlin Coroutines Flow - Hands-on` di root repo, yang merupakan hands-on untuk Pertemuan 2 mata kuliah utama (Advanced Kotlin, Coroutines, Flow).

## Capaian Pembelajaran

- Memahami konsep class, object, constructor (primary/secondary), dan `init` block
- Menerapkan inheritance dengan `open class` dan `override`
- Membedakan penggunaan `interface` (abstraksi tanpa state) dan `data class` (pembawa data)
- Menggunakan `sealed class` untuk memodelkan state yang terbatas dan `when` yang exhaustive

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

### 1. `handson1-latihan` — file latihan dan solusi — Class & Inheritance
Buat hierarki class `Vehicle` (open class) dengan turunan `Car` dan `Motorcycle` yang meng-override `describe()` untuk menambahkan info spesifik masing-masing kendaraan.

- **Konsep:** `open class`, `override`, primary constructor, `super`, polymorphism lewat `List<Vehicle>`.

### 2. `handson2-latihan` — file latihan dan solusi — Interface & Data Class
Buat interface `Payable` dengan `calculateSalary()`, implementasikan lewat `data class Employee`. Manfaatkan `copy()` untuk membuat variasi objek dan `equals()` bawaan untuk membandingkan berdasarkan isi (bukan referensi).

- **Konsep:** `interface`, `data class`, `copy()`, `equals()`/`toString()` otomatis.

### 3. `handson3-latihan` — file latihan dan solusi — Sealed Class untuk State
Modelkan hasil pemanggilan network sebagai `sealed class NetworkResult` dengan 3 state: `Loading` (object), `Success` (data class), dan `Error` (data class), lalu tangani semuanya dengan `when` yang exhaustive.

- **Catatan:** file `Latihan.kt` di modul ini **sengaja tidak bisa di-compile** sebelum kamu melengkapi TODO — itu bagian dari latihan.
- **Konsep:** `sealed class`, `object` sebagai singleton, `when` exhaustive tanpa `else`.

## Troubleshooting

| Error | Penyebab | Solusi |
|---|---|---|
| `This type is final, so it cannot be inherited from` | Class induk belum ditandai `open` | Tambahkan keyword `open` di depan `class` dan fungsi yang ingin di-override |
| `'describe' overrides nothing` | Fungsi induk belum ditandai `open`, atau nama/signature tidak cocok | Pastikan fungsi induk `open fun ...` dan signature (nama + parameter) identik |
| `'when' expression must be exhaustive` | Ada subclass `sealed class` yang belum ditangani di `when` | Tambahkan cabang `is NamaSubclass -> ...` untuk semua subclass, atau gunakan `else` sebagai fallback |

## Sumber Pustaka

- [Classes and Objects](https://kotlinlang.org/docs/classes.html)
- [Inheritance](https://kotlinlang.org/docs/inheritance.html)
- [Data Classes](https://kotlinlang.org/docs/data-classes.html)
- [Sealed Classes and Interfaces](https://kotlinlang.org/docs/sealed-classes.html)
