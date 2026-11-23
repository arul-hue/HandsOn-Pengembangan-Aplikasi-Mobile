import kotlin.concurrent.thread

class Counter {
    private var c = 0

    fun increment() {
        synchronized(this) { c++ }
    }

    fun value(): Int {
        return synchronized(this) { c }
    }
}

fun main() {
    val counter = Counter()
    val iterasi = 100_000

    val t1 = thread {
        repeat(iterasi) { counter.increment() }
    }
    val t2 = thread {
        repeat(iterasi) { counter.increment() }
    }

    t1.join()
    t2.join()

    println("Hasil akhir: ${counter.value()} (seharusnya ${iterasi * 2})")
}

// Penjelasan:
// - c++ bukan operasi atomik (baca-ubah-tulis), jadi dua thread bisa saling menyela dan hasilnya salah (race condition)
// - synchronized(this) memakai intrinsic lock (monitor) milik objek Counter, jadi hanya satu thread yang boleh menjalankan blok itu pada satu waktu
// - increment() dan value() memakai lock yang sama, sehingga hasil akhir selalu 200_000 dan nilai yang dibaca selalu terbaru
// - t1.join() dan t2.join() membuat main menunggu kedua thread selesai sebelum mencetak hasil
