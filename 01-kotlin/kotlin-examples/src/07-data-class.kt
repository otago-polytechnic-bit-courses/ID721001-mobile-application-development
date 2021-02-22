data class Person(val id: Int, var firstName: String, var lastName: String)

fun main() {
    val person = Person(1, "John", "Doe")
    person.firstName = "Joe"
    println(person)
    println(person.firstName)
}
