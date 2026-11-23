import java.util.concurrent.Callable
import java.util.concurrent.Executors

fun hitungKuadrat(n: Int): Int {
    Thread.sleep(1000) // Simulasi kerja berat
    return n * n
}

fun main() {
    val angka = listOf(1, 2, 3, 4)
    val startTime = System.currentTimeMillis()

    val executor = Executors.newFixedThreadPool(4)

    val futures = angka.map { n -> executor.submit(Callable { hitungKuadrat(n) }) }

    val hasil = futures.map { it.get() }
    println("Hasil: $hasil")

    executor.shutdown()

    val endTime = System.currentTimeMillis()
    println("Waktu: ${endTime - startTime}ms")
}

// Penjelasan:
// - Executors.newFixedThreadPool(4) membuat thread pool berisi 4 pekerja, jadi 4 task bisa berjalan paralel
// - executor.submit(Callable { ... }) langsung mengembalikan Future tanpa menunggu task selesai, sehingga keempat task dimulai hampir bersamaan
// - future.get() memblokir sampai hasil task tersedia; karena task paralel, total waktu ~1000ms (bukan ~4000ms)
// - executor.shutdown() wajib dipanggil karena thread pool bukan daemon thread, kalau tidak program tidak akan berhenti
