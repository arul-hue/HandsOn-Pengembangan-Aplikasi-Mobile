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
// - out T (covariant): T hanya boleh dipakai sebagai hasil (produce), tidak boleh jadi parameter (consume).
// - Efeknya: karena Cat <: Animal, maka Container<Cat> <: Container<Animal>.
// - Tanpa out: generics bersifat invariant, jadi Container<Cat> ditolak (TYPE_MISMATCH).
// - open class: Animal ditandai open supaya bisa diwarisi oleh Cat.
// - Pewarisan: Cat memanggil constructor parent lewat : Animal(name).
// - override: CatContainer mengisi fungsi get() dari interface.
