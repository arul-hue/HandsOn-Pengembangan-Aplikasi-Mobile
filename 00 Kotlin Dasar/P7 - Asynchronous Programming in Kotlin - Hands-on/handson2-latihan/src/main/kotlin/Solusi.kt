import kotlinx.coroutines.*

suspend fun writeAccessLog(message: String) {
    delay(500)
    println("[LOG] $message")
}

suspend fun calculateTotalPrice(prices: List<Int>): Int {
    delay(700)
    return prices.sum()
}

fun main() = runBlocking {
    val prices = listOf(15000, 25000, 10000)

    val logJob = launch {
        writeAccessLog("Checkout dimulai")
    }

    val totalDeferred = async {
        calculateTotalPrice(prices)
    }

    val total = totalDeferred.await()
    println("Total belanja: Rp$total")

    logJob.join()
}

// Penjelasan:
// - launch dipakai untuk writeAccessLog karena tugas ini fire-and-forget: hasilnya tidak dibutuhkan, hanya efek samping println.
// - async dipakai untuk calculateTotalPrice karena hasil Int-nya dibutuhkan; async mengembalikan Deferred<Int>.
// - await() menangguhkan coroutine sampai Deferred selesai dan mengambil nilai kembalian calculateTotalPrice.
// - logJob.join() memastikan program tidak berakhir sebelum coroutine log selesai mencetak pesan.
