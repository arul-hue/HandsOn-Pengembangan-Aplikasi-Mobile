fun calculate(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    return operation(a, b)
}

fun main() {
    val tambah = calculate(10, 4) { x, y -> x + y }
    println("Tambah: $tambah")

    val kurang = calculate(10, 4) { x, y -> x - y }
    println("Kurang: $kurang")

    val kali = calculate(10, 4) { x, y -> x * y }
    println("Kali: $kali")
}

// Penjelasan:
// - operation: (Int, Int) -> Int adalah parameter bertipe fungsi, jadi calculate menerima "aksi" dari luar
// - operation(a, b) memanggil fungsi itu dengan a dan b, hasilnya dikembalikan
// - lambda ditaruh di luar kurung (trailing lambda), signature calculate tidak berubah
// - cuma lambdanya yang diganti (+, -, *), calculate-nya tetap sama