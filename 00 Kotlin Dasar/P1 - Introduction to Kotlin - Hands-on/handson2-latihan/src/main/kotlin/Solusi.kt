fun gradeOf(nilai: Int): String = when (nilai) {
    in 90..100 -> "A"
    in 80..89 -> "B"
    in 70..79 -> "C"
    in 60..69 -> "D"
    else -> "E"
}

fun main() {
    val nilaiMahasiswa = listOf(95, 82, 71, 60, 45)
    for (nilai in nilaiMahasiswa) {
        println("Nilai $nilai -> Grade ${gradeOf(nilai)}")
    }
}

// Penjelasan:
// - Single-expression function: Menggunakan tanda sama dengan (=) tanpa kurung kurawal ({ ... }) dan tanpa kata kunci return, karena fungsinya hanya berisi satu ekspresi utama.
// - when (expression): Dalam Kotlin, `when` bisa berfungsi sebagai ekspresi yang langsung menghasilkan nilai. Nilai dari cabang yang cocok akan otomatis menjadi nilai kembalian fungsi.
// - in a..b: Operator `in` digunakan untuk mengecek apakah suatu angka berada di dalam rentang (range) a sampai b.