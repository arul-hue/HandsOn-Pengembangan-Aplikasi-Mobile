// Hands-on 1: Try-Catch-Finally Dasar
// Tugas: Lengkapi fungsi safeDivide agar menangani pembagian dengan nol
// menggunakan try-catch-finally, tanpa membuat program crash.

fun safeDivide(a: Int, b: Int): Int {
    return try {
        a / b
    } catch (e: ArithmeticException) {
        println("Error: tidak bisa membagi dengan nol")
        0
    } finally {
        println("safeDivide($a, $b) selesai diproses")
    }
}

fun main() {
    println("Hasil 1: ${safeDivide(10, 2)}")  // sukses -> 5
    println("Hasil 2: ${safeDivide(10, 0)}")  // b == 0 -> harus ditangani, hasil 0
    println("Hasil 3: ${safeDivide(9, 3)}")   // sukses -> 3
}

// Penjelasan:
// - try-catch-finally membungkus a / b; ArithmeticException (pembagian nol) ditangkap sehingga program tidak crash.
// - catch mengembalikan 0 sebagai nilai default, try-as-expression membuat fungsi mengembalikan nilai blok try atau catch; finally tidak menentukan nilai ekspresi.
// - finally selalu dieksekusi baik try sukses maupun gagal, jadi pesan "selesai diproses" tercetak 3 kali.
