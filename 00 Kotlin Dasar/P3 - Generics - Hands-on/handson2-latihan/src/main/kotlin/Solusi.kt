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
// - <T : Comparable<T>> artinya T boleh tipe apa saja asal bisa dibandingkan
// - makanya satu findMax bisa dipakai buat Int, Double, dan String
// - kalau list kosong, lempar IllegalArgumentException
// - item.compareTo(max) > 0 artinya item lebih besar dari max, jadi max diganti