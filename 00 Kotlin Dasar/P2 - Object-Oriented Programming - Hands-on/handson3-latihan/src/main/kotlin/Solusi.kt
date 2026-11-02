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
// - sealed class: Semua turunannya harus diketahui saat compile, jadi when bisa dicek lengkap tanpa else.
// - object: Singleton, cuma ada satu instance. Cocok untuk state tanpa data seperti Loading.
// - data class: Otomatis punya equals, hashCode, toString, copy. Dipakai untuk state yang membawa data.
// - when (expression): Dalam Kotlin, when bisa berfungsi sebagai ekspresi yang langsung menghasilkan nilai.
// - is: Mengecek tipe objek di cabang when.
// - String template: ${...} menyisipkan nilai property ke dalam string.
