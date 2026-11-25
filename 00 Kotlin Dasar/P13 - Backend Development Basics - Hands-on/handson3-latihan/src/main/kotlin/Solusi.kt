import com.sun.net.httpserver.HttpServer
import java.net.InetSocketAddress

// Hands-on 3: Request Handling & Status Code
//   1. POST /echo — kembalikan body request apa adanya (status 200).
//   2. Handler default "/" — 404 Not Found untuk path yang tidak dikenal.

fun main() {
    val server = HttpServer.create(InetSocketAddress("0.0.0.0", 8080), 0)

    server.createContext("/echo") { exchange ->
        if (exchange.requestURI.path != "/echo") {
            val body = "Not Found: ${exchange.requestURI.path}".toByteArray()
            exchange.sendResponseHeaders(404, body.size.toLong())
            exchange.responseBody.use { it.write(body) }
            return@createContext
        }

        if (exchange.requestMethod != "POST") {
            exchange.sendResponseHeaders(405, -1)
            exchange.close()
            return@createContext
        }

        val requestBytes = exchange.requestBody.readBytes()
        exchange.sendResponseHeaders(200, requestBytes.size.toLong())
        exchange.responseBody.use { it.write(requestBytes) }
    }

    server.createContext("/") { exchange ->
        val body = "Not Found: ${exchange.requestURI.path}".toByteArray()
        exchange.sendResponseHeaders(404, body.size.toLong())
        exchange.responseBody.use { it.write(body) }
    }

    server.start()
    println("Server berjalan di http://localhost:8080")
    println("Coba: curl -X POST -d 'halo dunia' http://localhost:8080/echo")
    println("Coba: curl -i http://localhost:8080/tidak-ada")

    Thread.sleep(10_000)
    server.stop(0)
    println("Server dihentikan")
}

// Penjelasan:
// - exchange.requestMethod dicek dulu: selain POST dibalas 405 Method Not Allowed (sendResponseHeaders(405, -1) = body kosong), lalu exchange.close().
// - exchange.requestBody.readBytes() membaca seluruh body request menjadi ByteArray, yang langsung dikirim balik sebagai response (echo) dengan status 200.
// - Context "/" adalah handler default: semua path yang tidak match context lain masuk ke sini dan dibalas 404 dengan pesan "Not Found: <path>".

// HttpServer mencocokkan prefix context: pemeriksaan path di atas membuat /echo-lain tetap 404.
