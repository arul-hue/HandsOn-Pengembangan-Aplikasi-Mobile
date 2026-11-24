// Hands-on 3: Kotlin Sugar - try sebagai expression, require(), error(), multi-catch
// Tugas: Buat parser tiket sederhana dari format teks "nama/umur",
// misalnya "Budi/25", menggunakan gaya penanganan error khas Kotlin.

data class Tiket(val nama: String, val umur: Int)

fun parseTiket(input: String): Tiket {
    val bagian = input.split("/")

    require(bagian.size == 2) { "Format harus 'nama/umur', diterima: $input" }

    val nama = bagian[0]

    val umur: Int = try {
        bagian[1].toInt()
    } catch (e: NumberFormatException) {
        error("Umur tidak valid: ${bagian[1]}")
    }

    require(umur >= 0) { "Umur harus lebih besar atau sama dengan 0, diterima: $umur" }
    require(nama.isNotEmpty()) { "Nama tidak boleh kosong" }

    return Tiket(nama, umur)
}

fun main() {
    val inputs = listOf("Budi/25", "Siti/17", "format-salah", "Andi/bukan-angka", "/30")

    for (input in inputs) {
        try {
            println(parseTiket(input))
        } catch (e: IllegalArgumentException) {
            println("Input tidak valid [$input]: ${e.message}")
        } catch (e: IllegalStateException) {
            println("Error [$input]: ${e.message}")
        }
    }
}

// Penjelasan:
// - require() melempar IllegalArgumentException untuk validasi input (jumlah bagian, umur >= 0, nama tidak kosong).
// - try-as-expression mengubah bagian[1].toInt() menjadi nilai umur; catch NumberFormatException memanggil error() yang melempar IllegalStateException.
// - main memakai multi-catch untuk menangani IllegalArgumentException dan IllegalStateException secara terpisah, jadi satu input gagal tidak menghentikan loop.
