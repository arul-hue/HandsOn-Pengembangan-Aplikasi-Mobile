import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread

const val PESAN_SELESAI = "SELESAI"

fun main() {
    val queue = LinkedBlockingQueue<String>()

    val producer = thread {
        for (i in 1..5) {
            val pesan = "Pesan #$i"
            queue.put(pesan)
            println("[Producer] mengirim: $pesan")
            Thread.sleep(200)
        }
        queue.put(PESAN_SELESAI)
    }

    val consumer = thread {
        while (true) {
            val pesan = queue.take()

            if (pesan == PESAN_SELESAI) break

            println("[Consumer] menerima: $pesan")
        }
    }

    producer.join()
    consumer.join()
    println("Selesai!")
}

// Penjelasan:
// - Pola producer-consumer: satu thread menaruh data ke queue, thread lain mengambilnya, keduanya berkomunikasi lewat LinkedBlockingQueue yang thread-safe
// - queue.put() memasukkan pesan, sedangkan queue.take() bersifat blocking: consumer menunggu otomatis jika queue kosong
// - PESAN_SELESAI adalah sentinel value yang dikirim producer di akhir, sehingga consumer tahu kapan harus break dan tidak menunggu selamanya
// - producer.join() dan consumer.join() membuat main menunggu kedua thread selesai sebelum mencetak "Selesai!"
