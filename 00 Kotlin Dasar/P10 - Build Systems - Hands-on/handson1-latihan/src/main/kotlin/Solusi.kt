import java.util.Properties

// Hands-on 1: Resource & Packaging
// Tugas: Baca file app.properties yang ada di src/main/resources melalui
// classpath (bukan lewat File API biasa), lalu tampilkan isinya.

fun main() {
    val inputStream = object {}.javaClass.classLoader.getResourceAsStream("app.properties")

    val properties = Properties()
    inputStream?.use { properties.load(it) }

    println("Nama aplikasi: ${properties.getProperty("app.name")}")
    println("Versi: ${properties.getProperty("app.version")}")
}

// Penjelasan:
// - getResourceAsStream("app.properties") membaca resource dari classpath; Gradle otomatis mem-package isi src/main/resources ke classpath hasil build.
// - Properties.load() mengurai file .properties (key=value) menjadi pasangan key-value.
// - use {} menutup InputStream secara otomatis setelah selesai (try-with-resources bawaan Kotlin).
