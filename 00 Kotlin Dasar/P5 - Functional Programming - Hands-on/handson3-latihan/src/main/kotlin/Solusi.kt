fun makeCounter(): () -> Int {
    var count = 0
    return {
        count += 1
        count
    }
}

fun main() {
    val counterA = makeCounter()
    val counterB = makeCounter()

    println(counterA())
    println(counterA())
    println(counterA())

    println(counterB())
    println(counterB())
}

// Penjelasan:
// - count dibuat di dalam makeCounter, tapi lambda yang dikembalikan "menangkap" dan membawanya (closure)
// - jadi count tidak hilang setelah makeCounter selesai, nilainya terus diingat lambda
// - baris terakhir lambda (count) otomatis jadi nilai kembaliannya
// - tiap makeCounter() bikin count baru, jadi counterA dan counterB tidak saling ganggu
//