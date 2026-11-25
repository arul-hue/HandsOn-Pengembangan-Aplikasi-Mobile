import kotlin.reflect.full.memberProperties

// Hands-on 1: Inspeksi Kelas dengan KClass

data class Product(val nama: String, val harga: Int)

fun main() {
    val product = Product("Kopi Susu", 18000)

    val kClass = product::class
    println("Kelas: ${kClass.simpleName}")
    println("Properties:")
    for (property in kClass.memberProperties) {
        println("- ${property.name}")
    }
}

// Penjelasan:
// - product::class menghasilkan KClass<Product>, entry point Kotlin Reflection.
// - memberProperties (dari kotlin.reflect.full) mengembalikan semua property kelas secara dinamis saat runtime.
// - Nama property diambil lewat property.name tanpa hardcode, jadi inspeksi struktur kelas sepenuhnya generik.
