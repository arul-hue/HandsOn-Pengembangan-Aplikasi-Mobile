fun prosesDenganList(data: List<Int>): List<Int> {
    return data
        .filter { it % 2 == 0 }
        .map { it * it }
        .take(5)
}

fun prosesDenganSequence(data: List<Int>): List<Int> {
    return data
        .asSequence()
        .filter { it % 2 == 0 }
        .map { it * it }
        .take(5)
        .toList()
}

fun main() {
    val data = (1..1_000_000).toList()

    val startList = System.currentTimeMillis()
    val hasilList = prosesDenganList(data)
    val waktuList = System.currentTimeMillis() - startList
    println("List  : $hasilList (${waktuList}ms)")

    val startSeq = System.currentTimeMillis()
    val hasilSequence = prosesDenganSequence(data)
    val waktuSequence = System.currentTimeMillis() - startSeq
    println("Sequence: $hasilSequence (${waktuSequence}ms)")
}

// Penjelasan:
// - filter: Menyaring elemen, hanya yang kondisinya true yang lolos (di sini angka genap).
// - it % 2 == 0: Sisa bagi 2 sama dengan 0 berarti genap.
// - map: Mengubah tiap elemen (di sini dikuadratkan).
// - take(5): Mengambil 5 elemen pertama saja.
// - List (eager): Tiap operasi memproses SEMUA elemen dan membuat list perantara.
// - asSequence(): Mengubah List menjadi Sequence yang lazy.
// - Sequence (lazy): Elemen diproses satu per satu lewat seluruh pipeline, berhenti begitu take(5) terpenuhi.
// - toList(): Operasi terminal, yang memicu pipeline Sequence benar-benar berjalan.