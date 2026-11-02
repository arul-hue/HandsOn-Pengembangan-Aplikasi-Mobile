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

// Output:
// Skor lulus (>= 70): [100, 90, 85, 72]
// 1 3 5 7 9

// Penjelasan:
// - filterNotNull(): Membuang semua elemen null dari list, sehingga tipe hasilnya berubah dari List<Int?> menjadi List<Int> (aman dari null).
// - filter { it >= batasLulus }: Menyaring elemen list dengan lambda, `it` mewakili tiap elemen. Hanya skor yang >= batasLulus yang lolos.
// - sortedDescending(): Mengurutkan list dari nilai terbesar ke terkecil.
// - 1..sampai step 2: Range inklusif dari 1 sampai `sampai`, `step 2` membuat loncatan 2 angka sehingga hanya bilangan ganjil yang muncul.
// - print() dan println(): `print` mencetak tanpa pindah baris, `println` mencetak lalu pindah baris.