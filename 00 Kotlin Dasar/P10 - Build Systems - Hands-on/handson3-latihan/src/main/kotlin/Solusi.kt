import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun main() {
    val now = Clock.System.now()
    val today = now.toLocalDateTime(TimeZone.currentSystemDefault()).date
    println("Hari ini: $today")
}

// Penjelasan:
// - Dependency "org.jetbrains.kotlinx:kotlinx-datetime:0.6.0" dideklarasikan di build.gradle.kts module ini dengan configuration implementation.
// - Clock.System.now() mengambil waktu saat ini sebagai Instant; toLocalDateTime mengubahnya ke tanggal/waktu lokal, lalu .date mengambil tanggalnya.
