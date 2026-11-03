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
// - T itu tipe isi Box, R itu tipe isi Box hasil map
// - (T) -> R artinya lambda yang terima T dan balikin R
// - map bikin Box baru, Box lama tidak berubah
// - tipe T dan R ditebak sendiri sama Kotlin, tidak perlu ditulis
