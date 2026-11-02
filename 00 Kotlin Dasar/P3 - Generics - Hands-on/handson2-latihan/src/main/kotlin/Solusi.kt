fun <T : Comparable<T>> findMax(items: List<T>): T {
    if (items.isEmpty()) {
        throw IllegalArgumentException("List tidak boleh kosong")
    }
    var max = items[0]
    for (item in items) {
        if (item.compareTo(max) > 0) {
            max = item
        }
    }
    return max
}

fun main() {
    println(findMax(listOf(3, 7, 2, 9, 4)))
    println(findMax(listOf(1.5, 2.8, 0.3)))
    println(findMax(listOf("apel", "jeruk", "duku")))
}

// Penjelasan:
// - <T : Comparable<T>>: Bounded type parameter (upper bound). T boleh tipe apa saja, asal bisa dibandingkan dengan sesama T.
// - fun <T> ...: Fungsi generik, jadi satu findMax dipakai untuk Int, Double, dan String.
// - throw: Melempar exception kalau list kosong, supaya tidak ada hasil yang tidak masuk akal.
// - var max = items[0]: var karena nilainya akan diganti; tipenya di-infer menjadi T.
// - for (item in items): Loop untuk mengecek tiap elemen list.
// - compareTo: Hasilnya positif jika item lebih besar dari max, nol jika sama, negatif jika lebih kecil.
