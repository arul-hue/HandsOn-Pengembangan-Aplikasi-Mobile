import kotlinx.coroutines.*

suspend fun checkOrderStatus(orderId: String): String {
    delay(2000)
    return "Order $orderId: SHIPPED"
}

fun main() = runBlocking {
    println("Menunggu status pesanan...")

    val status = checkOrderStatus("A100")
    println(status)
}

// Penjelasan:
// - checkOrderStatus adalah suspend function: delay(2000) mensimulasikan network call tanpa memblokir thread, hanya menangguhkan coroutine.
// - suspend fun hanya bisa dipanggil dari coroutine atau suspend fun lain, jadi pemanggilannya dibungkus runBlocking.
// - Pesan awal dicetak sebelum pemanggilan suspend; ini menunjukkan urutan eksekusi, bukan bukti main thread tidak diblokir.
// - runBlocking memblokir thread pemanggil sampai selesai, sedangkan delay menangguhkan coroutine dan memberi kesempatan coroutine lain berjalan.
// - Nilai kembalian suspend fun diambil langsung seperti fungsi biasa karena runBlocking menjalankan coroutine sampai selesai.
