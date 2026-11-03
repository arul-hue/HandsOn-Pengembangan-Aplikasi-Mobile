class ScoreBoard(private val skorMentah: List<Int?>) {
    val skorValid: List<Int> = skorMentah.filterNotNull()

    fun skorKelulusan(batasLulus: Int): List<Int> {
        return skorValid.filter { it >= batasLulus }.sortedDescending()
    }
}

fun cetakRentangGanjil(sampai: Int) {
    for (i in 1..sampai step 2) {
        if (i > 1) print(" ")
        print(i)
    }
    println()
}

fun main() {
    val papan = ScoreBoard(listOf(85, null, 72, 90, null, 55, 100))
    println("Skor lulus (>= 70): ${papan.skorKelulusan(70)}")

    cetakRentangGanjil(10)
}

// Penjelasan:
// - filterNotNull() buang semua null, jadi List<Int?> berubah jadi List<Int>
// - filter { it >= batasLulus } cuma ambil skor yang lulus, lalu sortedDescending() urut dari terbesar
// - 1..sampai step 2 loncat 2 angka, jadi yang muncul ganjil saja
// - print tidak pindah baris, println pindah baris