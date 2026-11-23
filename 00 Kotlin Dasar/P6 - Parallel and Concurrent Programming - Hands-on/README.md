# Hands-on Pertemuan 6 (Kotlin Dasar) — Parallel and Concurrent Programming

Proyek Kotlin/JVM untuk 3 latihan praktikum Pertemuan 6 track **Kotlin Dasar**
(IF25-22017 — Pengembangan Aplikasi Mobile, ITERA). Materi ini fokus pada
`Thread` mentah dan `java.util.concurrent` di JVM (bukan Kotlin Coroutines —
itu dibahas di Pertemuan 7, Asynchronous Programming in Kotlin), sebagai
fondasi untuk memahami *mengapa* coroutines dibutuhkan.

## Capaian Pembelajaran

- Membedakan konsep parallel vs concurrent programming
- Membuat dan menjalankan thread di Kotlin/JVM (`Thread`, `Runnable`, `kotlin.concurrent.thread`)
- Menjelaskan penyebab race condition pada shared mutable state
- Menerapkan mekanisme sinkronisasi (`synchronized`, lock) untuk mengamankan shared state
- Menggunakan `ExecutorService`/`Future` dan `BlockingQueue` dari `java.util.concurrent`

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

### 1. `handson1-latihan` — file latihan dan solusi — Race Condition
Dua thread meng-increment shared `Counter` sebanyak 100.000x masing-masing secara bersamaan tanpa sinkronisasi. Buktikan hasil akhirnya sering **salah** (bukan 200.000), lalu perbaiki dengan `synchronized`.

- **Target:** hasil akhir SELALU tepat 200.000, walau dijalankan berkali-kali.
- **Konsep:** race condition, shared mutable state, `synchronized`, intrinsic lock (monitor).

### 2. `handson2-latihan` — file latihan dan solusi — ExecutorService & Future
Jalankan 4 perhitungan "berat" (disimulasikan dengan `Thread.sleep`) secara paralel menggunakan thread pool, kumpulkan hasilnya dengan `Future.get()`.

- **Target:** total waktu eksekusi ~1000ms (bukan ~4000ms jika sequential).
- **Konsep:** `Executors.newFixedThreadPool`, `Callable`, `Future`, `shutdown()`.

### 3. `handson3-latihan` — file latihan dan solusi — Producer-Consumer dengan BlockingQueue
Implementasikan pola producer-consumer: satu thread menaruh pesan ke `LinkedBlockingQueue`, satu thread lain mengambil dan mencetaknya, berhenti saat menerima sentinel value.

- **Catatan:** file `Latihan.kt` di modul ini **sengaja tidak bisa di-compile** sebelum kamu melengkapi TODO — itu bagian dari latihan.
- **Konsep:** `LinkedBlockingQueue`, `put()`/`take()` (blocking), sentinel value.

## Troubleshooting

| Error / Gejala | Penyebab | Solusi |
|---|---|---|
| Hasil akhir counter tidak konsisten (kadang 200000, kadang kurang) | Dua thread membaca-ubah-tulis `var c` secara bersamaan tanpa sinkronisasi (race condition) | Bungkus operasi baca-ubah-tulis dengan `synchronized(this) { }` atau pakai `AtomicInteger` |
| Program tidak pernah berhenti (hang) setelah `future.get()` selesai | Lupa memanggil `executor.shutdown()` — thread pool bukan daemon thread | Selalu panggil `executor.shutdown()` setelah selesai memakai `ExecutorService` |
| `IllegalMonitorStateException` | Memanggil `wait()`/`notify()` di luar blok `synchronized` pada objek yang sama | Pastikan `wait()`/`notify()` dipanggil di dalam `synchronized(objek)` pada `objek` yang sama |
| Consumer hang menunggu selamanya di `queue.take()` | Producer tidak pernah mengirim sentinel/tanda selesai ke queue | Pastikan producer selalu `queue.put(PESAN_SELESAI)` di akhir, walau lewat jalur exception |

## Sumber Pustaka

- [Kotlin: Shared mutable state and concurrency](https://kotlinlang.org/docs/shared-mutable-state-and-concurrency.html)
- [Java `java.util.concurrent` package docs](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/package-summary.html)
- [Baeldung — Guide to the Java ExecutorService](https://www.baeldung.com/java-executor-service-tutorial)
