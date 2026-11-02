class Box<T>(val value: T) {
    fun <R> map(transform: (T) -> R): Box<R> {
        return Box(transform(value))
    }
}

fun main() {
    val intBox = Box(23)
    println("intBox.value = ${intBox.value}")

    val cupBox = Box("cup")
    println("cupBox.value = ${cupBox.value}")

    val stringBox = intBox.map { "Angka: $it" }
    println("stringBox.value = ${stringBox.value}")

    val lengthBox = cupBox.map { it.length }
    println("lengthBox.value = ${lengthBox.value}")
}

// Penjelasan:
// - class Box<T>: T adalah type parameter, jadi Box bisa menyimpan tipe apa saja (Int, String, dll).
// - fun <R> map: Fungsi generik dengan type parameter sendiri (R) untuk tipe hasil transformasi.
// - (T) -> R: Tipe fungsi (lambda) yang menerima T dan menghasilkan R.
// - Type inference: Kotlin menebak T dan R sendiri, jadi tidak perlu tulis Box<Int> atau map<String>.
// - Trailing lambda: lambda ditaruh di luar kurung, misal map { ... }.
// - it: Nama otomatis untuk parameter tunggal lambda.
// - String template: $it menyisipkan nilai ke dalam string.
