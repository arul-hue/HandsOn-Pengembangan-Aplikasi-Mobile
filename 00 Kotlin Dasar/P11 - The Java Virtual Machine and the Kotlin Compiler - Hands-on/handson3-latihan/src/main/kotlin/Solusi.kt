// Hands-on 3: companion object & @JvmStatic
//
// Konteks: Fungsi di companion object default-nya jadi method instance pada
// inner class Companion, bukan static method sungguhan di JVM bytecode.
// @JvmStatic membuat compiler menghasilkan static method langsung di kelas
// utama, sehingga terlihat oleh reflection Java biasa (java.lang.Class).

class Greeter {
    companion object {
        @JvmStatic
        fun greet(name: String): String {
            return "Halo, $name!"
        }
    }
}

fun main() {
    println(Greeter.greet("Mahasiswa"))

    val staticMethodNames = Greeter::class.java.methods.map { it.name }
    println("Method pada Greeter.class: $staticMethodNames")

    val punyaStaticGreet = staticMethodNames.contains("greet")
    println("Greeter.class punya static method 'greet'? $punyaStaticGreet")

    check(punyaStaticGreet) {
        "greet() belum jadi static method di Greeter.class — tambahkan @JvmStatic!"
    }
}

// Penjelasan:
// - @JvmStatic di atas greet() membuat Kotlin compiler menghasilkan static method 'greet' langsung di Greeter.class (bukan hanya di Greeter.Companion).
// - Greeter::class.java.methods memanfaatkan reflection Java biasa untuk membuktikan method static tersebut benar-benar ada di bytecode.
// - Tanpa @JvmStatic, 'greet' hanya menjadi instance method pada Companion sehingga tidak muncul di daftar method Greeter.class.
