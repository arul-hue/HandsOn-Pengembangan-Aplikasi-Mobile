import com.sun.net.httpserver.HttpServer
import java.net.InetSocketAddress

// Hands-on 1: HTTP Server Sederhana
// Tugas: Buat HTTP server (pakai HttpServer bawaan JDK, tanpa framework)
// yang listen di port 8080 dan merespon GET /hello dengan teks
// "Hello, Kotlin Backend!" serta status code 200 OK.

fun main() {
    val server = HttpServer.create(InetSocketAddress("0.0.0.0", 8080), 0)

    server.createContext("/hello") { exchange ->
        val body = "Hello, Kotlin Backend!".toByteArray()
        exchange.sendResponseHeaders(200, body.size.toLong())
        exchange.responseBody.use { it.write(body) }
    }

    server.start()
    println("Server berjalan di http://localhost:8080/hello")

    Thread.sleep(10_000)
    server.stop(0)
    println("Server dihentikan")
}

// Penjelasan:
// - HttpServer.create(InetSocketAddress("0.0.0.0", 8080), 0) membuat server bawaan JDK yang listen di semua interface port 8080.
// - createContext("/hello") mendaftarkan handler untuk path tersebut; di dalamnya body dikirim sebagai ByteArray.
// - sendResponseHeaders(200, body.size.toLong()) mengirim status 200 OK + header Content-Length; responseBody.use {} menulis body lalu menutup stream otomatis.
// - server.start() menjalankan server; Thread.sleep + server.stop(0) membuat proses berhenti otomatis setelah 10 detik.
