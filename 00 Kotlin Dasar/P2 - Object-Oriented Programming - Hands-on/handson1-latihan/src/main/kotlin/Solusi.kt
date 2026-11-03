open class Vehicle(val name: String, val maxSpeed: Int) {

    open fun describe(): String {
        return "$name dapat melaju hingga $maxSpeed km/h"
    }
}

class Car(name: String, val numberOfDoors: Int) : Vehicle(name, 180) {
    override fun describe(): String {
        return "${super.describe()} dan punya $numberOfDoors pintu"
    }
}

class Motorcycle(name: String, val hasSidecar: Boolean) : Vehicle(name, 220) {
    override fun describe(): String {
        val info = if (hasSidecar) "dengan sidecar" else "tanpa sidecar"
        return "${super.describe()} ($info)"
    }
}

fun main() {
    val vehicles = listOf<Vehicle>(
        Car("Toyota", numberOfDoors = 4),
        Motorcycle("Ninja", hasSidecar = false)
    )

    vehicles.forEach { println(it.describe()) }
}

// Penjelasan:
// - class di Kotlin default-nya final, makanya Vehicle dan describe() perlu open
// - Car dan Motorcycle wajib memanggil constructor Vehicle, maxSpeed diisi nilai tetap (180 dan 220)
// - super.describe() dipakai supaya teks parent tidak ditulis ulang
// - walau disimpan di List<Vehicle>, yang jalan tetap describe() milik masing-masing