open class Dog {
    open fun sound() {
        println("bow wow wow!")
    }
}

class Bulldog : Dog() {
    override fun sound() {
        println("woof woof woof!")
    }
}

open class Tiger(val region: String, val sound: String) {
    fun sound() {
        println("A tiger from $region says $sound!")
    }
}

class SiberianTiger : Tiger("Sikhote-Alin", "roooaaar") 

class BengalTiger(region: String) : Tiger(region = region, sound = "roooaaar")

fun main() {
    val dog: Dog = Bulldog()
    dog.sound()

    val siberianTiger: Tiger = SiberianTiger()
    siberianTiger.sound()

    val bengalTiger: Tiger = BengalTiger("Sundarbans")
    bengalTiger.sound()
}
