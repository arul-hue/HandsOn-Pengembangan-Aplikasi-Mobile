fun isEvenLength(s: String): Boolean = s.length % 2 == 0

fun toUpper(s: String): String = s.uppercase()

fun main() {
    val mahasiswa = listOf("Andi", "Budi", "Citra", "Dewi", "Eka", "Fajar")

    val hasilLambda: List<String> = mahasiswa
        .filter { it.length % 2 == 0 }
        .map { it.uppercase() }

    val hasilReference: List<String> = mahasiswa
        .filter(::isEvenLength)
        .map(::toUpper)

    println("Lambda   : $hasilLambda")
    println("Reference: $hasilReference")
    println("Sama? ${hasilLambda == hasilReference}")
}

// Penjelasan:
// - filter dan map sama-sama menerima fungsi, boleh berupa lambda atau function reference
// - { it.length % 2 == 0 } itu lambda yang ditulis langsung di tempat
// - ::isEvenLength menunjuk ke fungsi yang sudah dibuat, tanpa tanda kurung dan tanpa argumen
// - hasilnya sama karena isi fungsinya sama, cuma cara menuliskannya yang beda
// - == pada List membandingkan isinya, bukan alamat objeknya