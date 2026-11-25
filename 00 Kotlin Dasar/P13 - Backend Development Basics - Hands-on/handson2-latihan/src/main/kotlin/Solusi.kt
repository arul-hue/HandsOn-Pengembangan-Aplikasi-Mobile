import com.sun.net.httpserver.HttpServer
import java.net.InetSocketAddress

// Hands-on 2: Routing & JSON Sederhana

data class User(val id: Int, val name: String)

val users = listOf(
    User(1, "Andi"),
    User(2, "Budi"),
    User(3, "Citra")
)

fun usersToJson(users: List<User>): String {
    val items = users.joinToString(",") { user ->
        """{"id":${user.id},"name":"${user.name}"}"""
    }
    return "[$items]"
}

fun main() {
    val server = HttpServer.create(InetSocketAddress("0.0.0.0", 8080), 0)

    server.createContext("/users") { exchange ->
        val body = usersToJson(users).toByteArray()
        exchange.responseHeaders.set("Content-Type", "application/json")
        exchange.sendResponseHeaders(200, body.size.toLong())
        exchange.responseBody.use { it.write(body) }
    }

    server.start()
    println("Server berjalan di http://localhost:8080 — coba: curl http://localhost:8080/users")

    Thread.sleep(10_000)
    server.stop(0)
    println("Server dihentikan")
}

// Penjelasan:
// - usersToJson membangun string JSON manual dengan joinToString: tiap User diubah jadi {"id":..,"name":".."} lalu digabung dalam array [...].
// - exchange.responseHeaders.set("Content-Type", "application/json") dipanggil SEBELUM sendResponseHeaders agar client tahu responsnya JSON.
// - Pola response sama seperti hands-on 1: sendResponseHeaders(200, panjangBody) lalu tulis body lewat responseBody.
