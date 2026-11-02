interface Payable {
    fun calculateSalary(): Double
}

data class Employee(val name: String, val baseSalary: Double, val bonus: Double) : Payable {

    override fun calculateSalary(): Double {
        return baseSalary + bonus
    }
}

fun main() {
    val alice = Employee("Alice", baseSalary = 5_000_000.0, bonus = 500_000.0)

    val bob = alice.copy(name = "Bob")

    println("Gaji ${alice.name}: ${alice.calculateSalary()}")
    println("Gaji ${bob.name}: ${bob.calculateSalary()}")

    val aliceDuplicate = Employee("Alice", baseSalary = 5_000_000.0, bonus = 500_000.0)
    println("alice == aliceDuplicate? ${alice == aliceDuplicate}")

    println(alice)
}

// Penjelasan:
// - interface Payable: Kontrak yang hanya berisi fungsi abstrak calculateSalary() tanpa isi. Interface tidak boleh menyimpan state.
// - data class: Otomatis menghasilkan equals(), hashCode(), toString(), componentN(), dan copy() dari property di primary constructor.
// - : Payable + override: Class mengimplementasikan interface dan wajib meng-override fungsi abstraknya dengan isi nyata (baseSalary + bonus).
// - copy(name = "Bob"): Menyalin object dengan mengubah sebagian property saja, sisanya tetap sama.
// - ==: Pada data class, == memanggil equals() yang membandingkan ISI property, bukan alamat object di memori.