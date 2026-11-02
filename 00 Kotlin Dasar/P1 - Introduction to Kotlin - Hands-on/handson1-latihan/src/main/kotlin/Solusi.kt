fun describeProfile(nama: String, umur: Int?, kota: String = "Tidak diketahui"): String {
    val umurText = umur?.let { "$it tahun" } ?: "umur tidak diketahui"

    return "Nama: $nama, Umur: $umurText, Kota: $kota"
}

fun main() {
    println(describeProfile("Andi", 20, "Bandar Lampung"))
    println(describeProfile("Budi", null))
    println(describeProfile(nama = "Citra", umur = 19))
}

//Penjelasan: 
// umur?.let { ... }: Hanya dijalankan jika umur tidak null (safe call).   
// ?: (elvis operator): Memberi nilai fallback (cadangan) saat sisi kiri bernilai null.   
// Default parameter (kota): Nilai bawaan yang membuat argumen bisa diabaikan saat memanggil fungsi.  
// Named argument (nama = ..., umur = ...): Mempermudah pemanggilan tanpa harus menghafal urutan parameter.
