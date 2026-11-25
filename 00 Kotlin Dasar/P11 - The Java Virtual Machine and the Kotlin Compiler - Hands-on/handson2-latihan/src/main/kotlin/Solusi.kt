// Hands-on 2: Tailrec Optimization
//
// Konteks: Rekursi biasa menumpuk stack frame baru tiap pemanggilan sampai
// StackOverflowError. `tailrec` memberitahu compiler bahwa pemanggilan
// rekursif ada di posisi terakhir, sehingga ia bisa dioptimasi menjadi loop
// tanpa menambah stack frame.

fun factorialBiasa(n: Long, acc: Long = 1): Long {
    if (n <= 1) return acc
    return factorialBiasa(n - 1, acc * n)
}

tailrec fun factorialTailrec(n: Long, acc: Long = 1): Long {
    if (n <= 1) return acc
    return factorialTailrec(n - 1, acc * n)
}

fun main() {
    println("factorialBiasa(20) = ${factorialBiasa(20)}")

    println("factorialTailrec(20) = ${factorialTailrec(20)}")

    try {
        println("factorialBiasa(100000) = ${factorialBiasa(100000)}")
    } catch (e: StackOverflowError) {
        println("factorialBiasa(100000) -> StackOverflowError! (dibatasi ukuran stack JVM)")
    }

    println("factorialTailrec(100000) = ${factorialTailrec(100000)} (tidak overflow stack)")
}

// Penjelasan:
// - factorialTailrec punya bentuk sama dengan factorialBiasa, tetapi diberi modifier tailrec karena pemanggilan rekursifnya berada di posisi terakhir (tail call).
// - Compiler mengubah rekursi tail-call menjadi loop di level bytecode, sehingga tidak ada penambahan stack frame — aman untuk n = 100.000.
// - factorialBiasa tetap rekursi biasa, jadi tetap menumpuk frame dan akhirnya StackOverflowError untuk n besar.
