# Hands-on Pertemuan 4 (Kotlin Dasar) — Collections and Co.

Proyek Kotlin/JVM untuk 3 latihan praktikum Pertemuan 4 Kotlin Dasar (IF25-22017 — Pengembangan Aplikasi Mobile, ITERA). Materi ini adalah bagian dari track suplemen **Kotlin Dasar** (di luar jadwal pertemuan utama P1-P4), berdasarkan slide `Kotlin Dasar/P4 - Collections and co..pdf`.

## Capaian Pembelajaran

- Memahami taksonomi collection di Kotlin: `Collection` vs `MutableCollection`, `List`, `Set`, `Map`
- Menggunakan operator fungsional dasar (`filter`, `map`, `sortedBy`) untuk mentransformasi collection
- Mengelompokkan dan mengagregasi data dengan `groupBy`, `sumOf`, dan `associateBy`
- Membedakan evaluasi *eager* (List) dan *lazy* (`Sequence`) serta tahu kapan masing-masing lebih tepat digunakan

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

### 1. `handson1-latihan` — file latihan dan solusi — Transformasi Collection
Dari daftar `Product` (nama, harga, stok), hasilkan daftar **nama** produk yang harganya di atas threshold tertentu, terurut dari yang termurah.

- **Konsep:** `filter`, `sortedBy`, `map`.

### 2. `handson2-latihan` — file latihan dan solusi — Grouping & Aggregation
Dari daftar `Transaksi` (id, kategori, nominal), hitung total nominal per kategori, dan buat index pencarian transaksi berdasarkan ID.

- **Konsep:** `groupBy`, `mapValues`, `sumOf`, `associateBy`.

### 3. `handson3-latihan` — file latihan dan solusi — Sequence vs List (Lazy Evaluation)
Bandingkan performa pipeline `filter -> map -> take(5)` pada 1 juta angka menggunakan `List` biasa (eager) vs `Sequence` (lazy).

- **Catatan:** file `Latihan.kt` di modul ini **sengaja tidak bisa di-compile** sebelum kamu melengkapi TODO (menggunakan `TODO()`) — itu bagian dari latihan.
- **Konsep:** `asSequence()`, evaluasi lazy vs eager, terminal operation (`toList()`).

## Troubleshooting

| Error | Penyebab | Solusi |
|---|---|---|
| `ConcurrentModificationException` | Menambah/menghapus elemen `MutableList` saat sedang di-*iterate* dengan `for` biasa | Gunakan `MutableIterator.remove()`, atau buat salinan collection dulu sebelum diubah |
| `NoSuchElementException` pada `first()` / `single()` | Memanggil `first()`/`single()` pada collection kosong, atau `single()` pada collection dengan >1 elemen | Gunakan `firstOrNull()` / `singleOrNull()` dan tangani hasil `null`-nya |
| Hasil `Sequence` tidak muncul / mencetak `kotlin.sequences.TakeSequence@...` | Lupa memanggil operasi terminal (`toList()`, `count()`, `forEach()`, dll) — Sequence bersifat lazy dan baru dievaluasi saat operasi terminal dipanggil | Tambahkan operasi terminal di akhir chain, misalnya `.toList()` |

## Sumber Pustaka

- [Kotlin Collections Overview](https://kotlinlang.org/docs/collections-overview.html)
- [Sequences](https://kotlinlang.org/docs/sequences.html)
- [Collection Grouping](https://kotlinlang.org/docs/grouping.html)
