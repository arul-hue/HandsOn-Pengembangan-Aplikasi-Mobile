open class Animal(val name: String)
class Cat(name: String) : Animal(name)

interface Container<out T> {
    fun get(): T
}

class CatContainer(private val cat: Cat) : Container<Cat> {
    override fun get(): Cat = cat
}

fun printAnimalName(container: Container<Animal>) {
    println("Nama hewan: ${container.get().name}")
}

fun main() {
    val catContainer: Container<Cat> = CatContainer(Cat("Whiskers"))
    printAnimalName(catContainer)
}

// Penjelasan:
// - out T artinya T cuma boleh keluar (jadi return), tidak boleh jadi parameter
// - karena itu Container<Cat> bisa dianggap Container<Animal>
// - tanpa out, generics itu invariant, jadi muncul error TYPE_MISMATCH