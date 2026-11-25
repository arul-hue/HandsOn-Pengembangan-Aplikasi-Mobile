// Hands-on 2 tidak butuh kode Kotlin — tugasnya ada di build.gradle.kts
// module ini (custom task "printProjectInfo"). File ini hanya placeholder
// agar module tetap valid sebagai Kotlin/JVM source set.

fun main() {
    println("Jalankan: gradle :handson2-latihan:printProjectInfo")
}

// Penjelasan:
// - tasks.register("printProjectInfo") mendaftarkan task secara lazy; doLast {} berisi aksi yang jalan saat EXECUTION phase.
// - project.name dan project.version membaca properti project module ini; gradle.gradleVersion memberi versi Gradle yang menjalankan build.
