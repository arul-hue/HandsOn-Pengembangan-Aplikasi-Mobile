import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaField

// Hands-on 3: Custom Annotation + Reflection Validator

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Required

data class RegistrasiForm(
    @Required val nama: String,
    @Required val email: String,
    val alamat: String?
)

fun validate(obj: Any): List<String> {
    val gagal = mutableListOf<String>()

    for (property in obj::class.memberProperties) {
        val field = property.javaField ?: continue
        if (field.getAnnotation(Required::class.java) != null) {
            val value = property.getter.call(obj)
            if (value == null || (value is String && value.isBlank())) {
                gagal.add(property.name)
            }
        }
    }

    return gagal
}

fun main() {
    val formValid = RegistrasiForm("Siti", "siti@mail.com", null)
    println("Form valid -> gagal: ${validate(formValid)}")

    val formInvalid = RegistrasiForm("", "budi@mail.com", "Jl. Merdeka")
    println("Form invalid -> gagal: ${validate(formInvalid)}")
}

// Penjelasan:
// - annotation class Required diberi @Target(FIELD) dan @Retention(RUNTIME) agar anotasinya menempel di field dan tetap terbaca saat runtime oleh reflection.
// - validate() mengiterasi memberProperties, lalu memeriksa field Java-nya via javaField.getAnnotation(Required::class.java).
// - Property beranotasi @Required yang nilainya null atau String blank dimasukkan ke daftar gagal — pola validasi generik seperti di framework Spring.
