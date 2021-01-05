interface BodyParts {
    fun fingerCount(): Int = 10
    fun legCount(): Int
}

class Person : BodyParts {
    override fun legCount() = 2
}

fun main() {
    val person = Person()
    println(person.fingerCount())
    println(person.legCount())
}
