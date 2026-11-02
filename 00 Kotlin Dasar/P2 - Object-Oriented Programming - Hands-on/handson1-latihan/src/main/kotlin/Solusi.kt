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
// - open class: Secara default class di Kotlin bersifat final. Kata kunci `open` membuat Vehicle bisa diwarisi oleh Car dan Motorcycle.
// - open fun: Fungsi juga harus `open` agar bisa di-override oleh subclass.
// - Vehicle(name, 180): Class turunan wajib memanggil constructor parent. Parameter `name` di Car tanpa val/var hanya diteruskan ke Vehicle, sedangkan maxSpeed diisi nilai tetap.
// - override fun: Menimpa describe() milik parent dengan versi sendiri. `super.describe()` memanggil versi parent agar teksnya tidak ditulis ulang.
// - Polymorphism: List<Vehicle> berisi Car dan Motorcycle, tapi describe() yang jalan adalah versi milik tiap subclass.