import kotlin.reflect.full.memberProperties

// Hands-on 2: Baca Nilai Property Dinamis

data class Mahasiswa(val nim: String, val nama: String, val ipk: Double)

fun printFields(obj: Any) {
    for (property in obj::class.memberProperties) {
        val value = property.getter.call(obj)
        println("${property.name} = $value")
    }
}

fun main() {
    val mhs = Mahasiswa("122140000", "Budi Santoso", 3.75)
    printFields(mhs)
}

// Penjelasan:
// - obj::class memberi KClass dari objek apapun yang masuk ke printFields (generic reflection).
// - property.getter.call(obj) memanggil getter property secara dinamis untuk membaca nilainya tanpa hardcode nama field.
// - Hasilnya mirip toString() otomatis, tapi dibangun manual lewat iterasi memberProperties.
