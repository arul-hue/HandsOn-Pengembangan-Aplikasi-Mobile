# Hands-on Pertemuan 3 (Kotlin Dasar) — Generics

Proyek Kotlin/JVM untuk 3 latihan praktikum Pertemuan 3 track **Kotlin Dasar** (IF25-22017 — Pengembangan Aplikasi Mobile, ITERA). Track ini adalah materi pendukung/dasar Kotlin, terpisah dari jadwal pertemuan utama mata kuliah.

## Capaian Pembelajaran

- Membuat dan menggunakan generic class dengan type parameter
- Menerapkan bounded type parameter (upper bound) untuk membatasi tipe generik
- Memahami konsep variance (`out`/`in`) dan star-projection pada generics Kotlin
- Menjelaskan mengapa generic Kotlin bersifat invariant secara default

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

### 1. `handson1-latihan` — file latihan dan solusi — Generic Class `Box<T>`
Lengkapi class generik `Box<T>` dengan fungsi `map<R>(transform: (T) -> R): Box<R>` yang mentransformasikan isi Box menjadi Box baru bertipe berbeda.

- **Konsep:** generic class, type parameter, type inference.

### 2. `handson2-latihan` — file latihan dan solusi — Bounded Type Parameter
Implementasikan `findMax` generik yang mencari nilai terbesar dari sebuah `List<T>`, dengan constraint `T : Comparable<T>` agar elemen bisa dibandingkan.

- **Konsep:** bounded type parameter (upper bound), `Comparable<T>`.

### 3. `handson3-latihan` — file latihan dan solusi — Variance (`out`)
Tandai `Container<T>` dengan modifier variance yang tepat agar `Container<Cat>` bisa dianggap sebagai `Container<Animal>`.

- **Catatan:** file `Latihan.kt` di modul ini **sengaja tidak bisa di-compile** sebelum kamu melengkapi TODO — itu bagian dari latihan.
- **Konsep:** declaration-site variance, covariance (`out`), subtyping generic.

## Troubleshooting

| Error | Penyebab | Solusi |
|---|---|---|
| `Type mismatch. Required: Container<Animal>. Found: Container<Cat>` | Generic Kotlin invariant secara default — `Container<Cat>` tidak otomatis jadi subtipe `Container<Animal>` | Tambahkan modifier `out` pada type parameter jika hanya diproduksi (`fun get(): T`), tidak pernah dikonsumsi |
| `[TYPE_VARIANCE_CONFLICT_ERROR] Type parameter T is declared as 'out' but occurs in 'in' position` | T ditandai `out` tapi masih dipakai sebagai parameter fungsi (posisi konsumsi) | Gunakan `in` jika T hanya dikonsumsi, atau biarkan invariant (tanpa modifier) jika T dikonsumsi & diproduksi |
| `Cannot use 'T' as reified type parameter` / unchecked cast warning saat memakai star-projection `<*>` | Star-projection (`Holder<*>`) tidak menyimpan informasi tipe konkret, sehingga tidak bisa memanggil fungsi yang butuh T spesifik sebagai parameter | Gunakan bounded type parameter atau generic function biasa jika tipe konkret dibutuhkan, star-projection hanya untuk kasus "tidak peduli tipe apa" |

## Sumber Pustaka

- [Kotlin Generics](https://kotlinlang.org/docs/generics.html)
- [Declaration-site variance](https://kotlinlang.org/docs/generics.html#declaration-site-variance)
- [Type projections (use-site variance)](https://kotlinlang.org/docs/generics.html#type-projections)
