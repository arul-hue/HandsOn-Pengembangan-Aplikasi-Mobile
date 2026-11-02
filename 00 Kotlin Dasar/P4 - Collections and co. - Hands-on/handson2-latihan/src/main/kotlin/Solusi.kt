data class Transaksi(val id: String, val kategori: String, val nominal: Int)

fun totalPerKategori(transaksi: List<Transaksi>): Map<String, Int> {
    return transaksi
        .groupBy { it.kategori }
        .mapValues { entry -> entry.value.sumOf { it.nominal } }
}

fun transaksiById(transaksi: List<Transaksi>): Map<String, Transaksi> {
    return transaksi.associateBy { it.id }
}

fun main() {
    val transaksi = listOf(
        Transaksi("TRX01", "Makanan", 50_000),
        Transaksi("TRX02", "Transportasi", 20_000),
        Transaksi("TRX03", "Makanan", 35_000),
        Transaksi("TRX04", "Hiburan", 100_000),
        Transaksi("TRX05", "Transportasi", 15_000)
    )

    println("Total per kategori: ${totalPerKategori(transaksi)}")

    val byId = transaksiById(transaksi)
    println("Cari TRX03: ${byId["TRX03"]}")
}

// Penjelasan:
// - groupBy: Mengelompokkan list menjadi Map<key, List<elemen>> berdasarkan key dari lambda.
// - mapValues: Mengubah tiap value di Map (key tetap), hasilnya Map baru.
// - sumOf: Menjumlahkan nilai yang diambil dari tiap elemen (di sini nominal).
// - associateBy: Mengubah List menjadi Map dengan key dari lambda dan value berupa elemen itu sendiri.
// - Map [] : byId["TRX03"] memanggil get(key), hasilnya nullable (Transaksi?).
// - data class: toString otomatis berbentuk Transaksi(id=..., kategori=..., nominal=...).
// - String template: ${...} menyisipkan hasil ekspresi ke dalam string.
