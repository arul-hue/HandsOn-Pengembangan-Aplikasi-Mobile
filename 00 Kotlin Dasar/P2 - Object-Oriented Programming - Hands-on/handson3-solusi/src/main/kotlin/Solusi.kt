sealed class NetworkResult

object Loading : NetworkResult()

data class Success(val data: String) : NetworkResult()

data class Error(val message: String) : NetworkResult()

fun describe(result: NetworkResult): String {
    return when (result) {
        is Loading -> "Sedang memuat..."
        is Success -> "Berhasil: ${result.data}"
        is Error -> "Gagal: ${result.message}"
    }
}

fun main() {
    println(describe(Loading))
    println(describe(Success("Data pengguna berhasil diambil")))
    println(describe(Error("Koneksi terputus")))
}

// Penjelasan:
// - sealed class: semua turunannya sudah pasti di-compile, jadi when tidak butuh else
// - Loading pakai object karena tidak bawa data, Success dan Error pakai data class karena bawa data
// - kalau nanti ada state baru dan when belum diupdate, compiler langsung kasih error