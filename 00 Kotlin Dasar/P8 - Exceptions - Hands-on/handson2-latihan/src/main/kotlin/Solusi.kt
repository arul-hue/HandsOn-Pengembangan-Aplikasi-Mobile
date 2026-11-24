// Hands-on 2: Custom Exception & Exception Hierarchy
// Tugas: Buat exception kustom untuk kasus saldo tidak cukup pada rekening
// bank sederhana, lalu tangani exception tersebut secara spesifikan.

class InsufficientBalanceException(message: String) : Exception(message)

class BankAccount(private val owner: String, startBalance: Double) {
    var balance: Double = startBalance
        private set

    fun deposit(amount: Double) {
        require(amount > 0) { "Jumlah deposit harus lebih besar dari 0" }
        balance += amount
    }

    fun withdraw(amount: Double) {
        require(amount > 0) { "Jumlah penarikan harus lebih besar dari 0" }

        if (amount > balance) {
            throw InsufficientBalanceException(
                "Saldo $owner tidak cukup: saldo Rp$balance, diminta Rp$amount"
            )
        }
        balance -= amount
    }
}

fun main() {
    val account = BankAccount("Andi", 100_000.0)

    account.deposit(50_000.0)
    println("Saldo setelah deposit: ${account.balance}")

    try {
        account.withdraw(500_000.0)
    } catch (e: InsufficientBalanceException) {
        println("Error: ${e.message}")
    }

    account.withdraw(30_000.0)
    println("Saldo akhir: ${account.balance}")
}

// Penjelasan:
// - InsufficientBalanceException adalah custom exception yang meng-extend Exception, dipakai untuk kasus saldo kurang secara spesifik.
// - withdraw memeriksa amount > balance lalu melempar exception dengan pesan yang menyebut pemilik, saldo, dan jumlah diminta.
// - catch (e: InsufficientBalanceException) menangkap hanya exception tersebut sehingga program lanjut dan saldo tidak berubah.
