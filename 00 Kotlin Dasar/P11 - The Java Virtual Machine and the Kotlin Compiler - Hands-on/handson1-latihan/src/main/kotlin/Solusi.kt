// Hands-on 1: Inline Function & Reified Generics
//
// Konteks: JVM menerapkan TYPE ERASURE — informasi tipe generik dihapus saat
// kompilasi ke bytecode, sehingga fungsi generik biasa tidak bisa melakukan
// `value is T`. Kombinasi `inline` + `reified` membuat compiler menyalin
// fungsi beserta tipe konkretnya ke tempat pemanggilan, sebelum type erasure
// terjadi.

inline fun <reified T> isType(value: Any): Boolean {
    return value is T
}

fun main() {
    println(isType<String>("Hello"))   // true
    println(isType<Int>("Hello"))      // false
    println(isType<String>(123))       // false
}

// Penjelasan:
// - `inline` membuat compiler menyalin isi fungsi ke titik pemanggilan; `reified` menjaga tipe T tetap konkret sehingga `is T` bisa dicek di runtime.
// - Tanpa inline+reified, `value is T` mustahil karena JVM sudah menghapus informasi tipe generik (type erasure) — itulah kenapa pendekatan generik biasa gagal.
// - Karena inline, T pada tiap pemanggilan digantikan tipe aslinya (String/Int), sehingga hasilnya akurat per-call.
